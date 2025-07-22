package com.example.budgetapp.data

import com.example.budgetapp.database.entities.Store

data class PriceRecommendation(
    val productId: Long,
    val productName: String,
    val storeId: Long,
    val storeName: String,
    val currentPrice: Double,
    val averagePrice: Double,
    val lowestPrice: Double,
    val highestPrice: Double,
    val savingsAmount: Double,
    val savingsPercent: Double,
    val recommendationType: RecommendationType,
    val confidence: Double, // 0.0 to 1.0
    val lastUpdated: Long
)

enum class RecommendationType {
    BEST_PRICE,          // Lowest current price
    PRICE_DROP,          // Recent price decrease
    GOOD_DEAL,           // Below average price
    AVOID_HIGH_PRICE,    // Above average price - warning
    PRICE_TREND_UP,      // Price trending upward
    PRICE_TREND_DOWN     // Price trending downward
}

data class ShoppingListRecommendations(
    val shoppingListId: Long,
    val shoppingListName: String,
    val totalEstimatedSavings: Double,
    val recommendedStores: List<StoreRecommendation>,
    val itemRecommendations: List<ItemRecommendation>
)

data class StoreRecommendation(
    val store: Store,
    val totalSavings: Double,
    val itemCount: Int,
    val averageDistance: Double? = null // For future location-based features
)

data class ItemRecommendation(
    val itemId: Long,
    val productName: String,
    val customItemName: String?,
    val quantity: Int,
    val unit: String,
    val estimatedPrice: Double?,
    val priceRecommendations: List<PriceRecommendation>,
    val bestPrice: PriceRecommendation?,
    val potentialSavings: Double
)

data class SmartSuggestion(
    val id: String,
    val title: String,
    val description: String,
    val actionText: String,
    val type: SuggestionType,
    val priority: Int, // 1 = high, 2 = medium, 3 = low
    val data: Map<String, Any> = emptyMap()
)

enum class SuggestionType {
    PRICE_ALERT,
    SHOPPING_OPTIMIZATION,
    BUDGET_TIP,
    STORE_RECOMMENDATION,
    SEASONAL_DEAL
}