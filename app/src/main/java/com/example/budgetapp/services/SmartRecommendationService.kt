package com.example.budgetapp.services

import com.example.budgetapp.data.*
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.*
import kotlinx.coroutines.flow.first
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class SmartRecommendationService(private val database: BudgetDatabase) {

    companion object {
        private const val PRICE_TREND_DAYS = 30L * 24 * 60 * 60 * 1000 // 30 days
        private const val RECENT_PRICE_DAYS = 7L * 24 * 60 * 60 * 1000 // 7 days
        private const val GOOD_DEAL_THRESHOLD = 0.15 // 15% below average
        private const val HIGH_PRICE_THRESHOLD = 0.20 // 20% above average
        private const val PRICE_DROP_THRESHOLD = 0.10 // 10% price drop
    }

    suspend fun getShoppingListRecommendations(shoppingListId: Long): ShoppingListRecommendations? {
        val shoppingList = database.shoppingListDao().getShoppingListById(shoppingListId) ?: return null
        val items = database.shoppingListItemDao().getItemsForShoppingListSuspend(shoppingListId)
        
        if (items.isEmpty()) {
            return null
        }
        
        val itemRecommendations = mutableListOf<ItemRecommendation>()
        var totalEstimatedSavings = 0.0
        
        for (item in items) {
            val recommendation = getItemRecommendation(item)
            if (recommendation != null) {
                itemRecommendations.add(recommendation)
                totalEstimatedSavings += recommendation.potentialSavings * item.quantity
            }
        }
        
        // Group recommendations by store to find best stores
        val storeGroups = itemRecommendations
            .flatMap { it.priceRecommendations }
            .groupBy { it.storeId }
        
        val storeRecommendations = storeGroups.mapNotNull { (storeId, recommendations) ->
            val store = database.storeDao().getStoreById(storeId) ?: return@mapNotNull null // Null safety fix
            val totalSavings = recommendations.sumOf { it.savingsAmount }
            val itemCount = recommendations.size
            
            StoreRecommendation(
                store = store,
                totalSavings = totalSavings,
                itemCount = itemCount
            )
        }.sortedByDescending { it.totalSavings }
        
        return ShoppingListRecommendations(
            shoppingListId = shoppingListId,
            shoppingListName = shoppingList.name,
            totalEstimatedSavings = totalEstimatedSavings,
            recommendedStores = storeRecommendations,
            itemRecommendations = itemRecommendations
        )
    }
    
    private suspend fun getItemRecommendation(item: ShoppingListItem): ItemRecommendation? {
        val productId = item.productId ?: return null
        val product = database.productDao().getProductById(productId) ?: return null
        
        val priceHistory = database.priceHistoryDao().getPriceHistoryForProductSuspend(productId)
        if (priceHistory.isEmpty()) return null
        
        val priceRecommendations = generatePriceRecommendations(productId, product.name ?: "Unknown", priceHistory)
        val bestPrice = priceRecommendations.minByOrNull { it.currentPrice }
        
        val currentEstimate = item.estimatedPrice ?: 
            if (priceRecommendations.isNotEmpty()) priceRecommendations.map { it.currentPrice }.average() else 0.0
        val bestPriceValue = bestPrice?.currentPrice ?: currentEstimate
        val potentialSavings = max(0.0, currentEstimate - bestPriceValue)
        
        return ItemRecommendation(
            itemId = item.id,
            productName = product.name ?: "Unknown",
            customItemName = item.customItemName,
            quantity = item.quantity,
            unit = item.unit ?: "st",
            estimatedPrice = item.estimatedPrice,
            priceRecommendations = priceRecommendations,
            bestPrice = bestPrice,
            potentialSavings = potentialSavings
        )
    }
    
    private suspend fun generatePriceRecommendations(
        productId: Long,
        productName: String,
        priceHistory: List<PriceHistory>
    ): List<PriceRecommendation> {
        val recommendations = mutableListOf<PriceRecommendation>()
        
        // Group by store to analyze each store's pricing
        val storeGroups = priceHistory.groupBy { it.storeId }
        
        // Calculate overall statistics
        val allPrices = priceHistory.map { it.price }
        if (allPrices.isEmpty()) return emptyList()
        
        val overallAverage = allPrices.average()
        val overallMin = allPrices.minOrNull() ?: 0.0
        val overallMax = allPrices.maxOrNull() ?: 0.0
        
        for ((storeId, storeHistory) in storeGroups) {
            val store = database.storeDao().getStoreById(storeId) ?: continue
            val currentPrice = storeHistory.maxByOrNull { it.recordedAt }?.price ?: continue
            
            val storePrices = storeHistory.map { it.price }
            if (storePrices.isEmpty()) continue
            val storeAverage = storePrices.average()
            val savingsAmount = max(0.0, overallAverage - currentPrice)
            val savingsPercent = if (overallAverage > 0) (savingsAmount / overallAverage) * 100 else 0.0
            
            // Determine recommendation type
            val recommendationType = determineRecommendationType(
                currentPrice,
                overallAverage,
                storeHistory,
                overallMin
            )
            
            val confidence = calculateConfidence(storeHistory, allPrices.size)
            
            recommendations.add(
                PriceRecommendation(
                    productId = productId,
                    productName = productName,
                    storeId = storeId,
                    storeName = store.name ?: "Unknown Store",
                    currentPrice = currentPrice,
                    averagePrice = storeAverage,
                    lowestPrice = overallMin,
                    highestPrice = overallMax,
                    savingsAmount = savingsAmount,
                    savingsPercent = savingsPercent,
                    recommendationType = recommendationType,
                    confidence = confidence,
                    lastUpdated = storeHistory.maxOf { it.recordedAt }
                )
            )
        }
        
        return recommendations.sortedBy { it.currentPrice }.take(10) // Limit to top 10 stores for performance
    }
    
    private fun determineRecommendationType(
        currentPrice: Double,
        overallAverage: Double,
        storeHistory: List<PriceHistory>,
        overallMin: Double
    ): RecommendationType {
        val priceDiffPercent = if (overallAverage > 0) abs(currentPrice - overallAverage) / overallAverage else 0.0
        
        // Check if this is the best price
        if (currentPrice <= overallMin + 0.01) { // Small tolerance for floating point
            return RecommendationType.BEST_PRICE
        }
        
        // Check for recent price drops
        val recentHistory = storeHistory.filter { 
            System.currentTimeMillis() - it.recordedAt <= RECENT_PRICE_DAYS 
        }
        if (recentHistory.size >= 2) {
            val oldestRecent = recentHistory.minByOrNull { it.recordedAt }
            val newestRecent = recentHistory.maxByOrNull { it.recordedAt }
            if (oldestRecent != null && newestRecent != null && oldestRecent.price > 0) {
                val priceChange = (newestRecent.price - oldestRecent.price) / oldestRecent.price
                if (priceChange <= -PRICE_DROP_THRESHOLD) {
                    return RecommendationType.PRICE_DROP
                }
                if (priceChange >= PRICE_DROP_THRESHOLD) {
                    return RecommendationType.PRICE_TREND_UP
                }
            }
        }
        
        // Check price relative to average
        return when {
            priceDiffPercent >= HIGH_PRICE_THRESHOLD && currentPrice > overallAverage -> 
                RecommendationType.AVOID_HIGH_PRICE
            priceDiffPercent >= GOOD_DEAL_THRESHOLD && currentPrice < overallAverage -> 
                RecommendationType.GOOD_DEAL
            else -> RecommendationType.PRICE_TREND_DOWN
        }
    }
    
    private fun calculateConfidence(storeHistory: List<PriceHistory>, totalDataPoints: Int): Double {
        if (storeHistory.isEmpty() || totalDataPoints <= 0) return 0.1
        
        val dataPointWeight = min(1.0, storeHistory.size.toDouble() / max(5.0, totalDataPoints * 0.2))
        val recencyWeight = if (storeHistory.isNotEmpty()) {
            val latestUpdate = storeHistory.maxOf { it.recordedAt }
            val daysSinceUpdate = (System.currentTimeMillis() - latestUpdate) / (24 * 60 * 60 * 1000)
            max(0.1, 1.0 - (daysSinceUpdate / 30.0)) // Decrease confidence over 30 days
        } else 0.1
        
        return min(1.0, (dataPointWeight * 0.6) + (recencyWeight * 0.4))
    }
    
    suspend fun getSmartSuggestions(): List<SmartSuggestion> {
        val suggestions = mutableListOf<SmartSuggestion>()
        
        // Get active shopping lists for optimization suggestions
        val activeShoppingLists = database.shoppingListDao().getShoppingListsByStatusSuspend(false)
        
        for (shoppingList in activeShoppingLists) {
            val recommendations = getShoppingListRecommendations(shoppingList.id)
            if (recommendations != null && recommendations.totalEstimatedSavings > 10.0) {
                suggestions.add(
                    SmartSuggestion(
                        id = "shopping_optimization_${shoppingList.id}",
                        title = "Spara ${String.format("%.0f", recommendations.totalEstimatedSavings)} kr",
                        description = "Vi hittade bättre priser för ${recommendations.itemRecommendations.size} varor i '${shoppingList.name}'",
                        actionText = "Se rekommendationer",
                        type = SuggestionType.SHOPPING_OPTIMIZATION,
                        priority = 1,
                        data = mapOf("shopping_list_id" to shoppingList.id)
                    )
                )
            }
        }
        
        // Get price alerts for significant price drops
        val recentPriceDrops = findRecentPriceDrops()
        for (drop in recentPriceDrops.take(5)) { // Limit to top 5 price drops
            suggestions.add(
                SmartSuggestion(
                    id = "price_drop_${drop.productId}_${drop.storeId}",
                    title = "Prisfall upptäckt!",
                    description = "${drop.productName} hos ${drop.storeName} sjönk med ${String.format("%.0f", drop.savingsPercent)}%",
                    actionText = "Se priser",
                    type = SuggestionType.PRICE_ALERT,
                    priority = 1,
                    data = mapOf(
                        "product_id" to drop.productId,
                        "store_id" to drop.storeId
                    )
                )
            )
        }
        
        return suggestions.sortedBy { it.priority }
    }
    
    private suspend fun findRecentPriceDrops(): List<PriceRecommendation> {
        val cutoffTime = System.currentTimeMillis() - RECENT_PRICE_DAYS
        val recentHistory = database.priceHistoryDao().getRecentPriceHistorySuspend(cutoffTime)
        
        val priceDrops = mutableListOf<PriceRecommendation>()
        
        // Group by product and store to find significant drops
        val groups = recentHistory.groupBy { "${it.productId}_${it.storeId}" }
        
        for (history in groups.values) {
            if (history.size < 2) continue
            
            val sorted = history.sortedBy { it.recordedAt }
            val oldest = sorted.first()
            val newest = sorted.last()
            
            if (oldest.price <= 0) continue // Avoid division by zero
            val priceChange = (newest.price - oldest.price) / oldest.price
            if (priceChange <= -PRICE_DROP_THRESHOLD) {
                val product = database.productDao().getProductById(newest.productId) ?: continue
                val store = database.storeDao().getStoreById(newest.storeId) ?: continue
                
                priceDrops.add(
                    PriceRecommendation(
                        productId = newest.productId,
                        productName = product.name,
                        storeId = newest.storeId,
                        storeName = store.name ?: "Unknown Store",
                        currentPrice = newest.price,
                        averagePrice = history.map { it.price }.average(),
                        lowestPrice = newest.price,
                        highestPrice = oldest.price,
                        savingsAmount = oldest.price - newest.price,
                        savingsPercent = abs(priceChange.toDouble()) * 100,
                        recommendationType = RecommendationType.PRICE_DROP,
                        confidence = 0.9,
                        lastUpdated = newest.recordedAt
                    )
                )
            }
        }
        
        return priceDrops.sortedByDescending { it.savingsPercent }
    }
}