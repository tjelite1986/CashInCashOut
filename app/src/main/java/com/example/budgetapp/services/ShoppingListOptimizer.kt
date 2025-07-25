package com.example.budgetapp.services

import com.example.budgetapp.data.OptimizedShoppingList
import com.example.budgetapp.data.ShoppingListItemOptimized
import com.example.budgetapp.data.ShoppingListItemWithProduct
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.dao.ProductDao

class ShoppingListOptimizer(private val database: BudgetDatabase) {
    
    /**
     * Optimerar en shoppinglista baserat på billigaste priser per butik
     */
    suspend fun optimizeShoppingList(items: List<ShoppingListItemWithProduct>): List<OptimizedShoppingList> {
        val optimizedItems = mutableListOf<ShoppingListItemOptimized>()
        
        // För varje vara, hitta billigaste butik
        for (itemWithProduct in items) {
            val item = itemWithProduct.item
            val product = itemWithProduct.product
            
            if (product != null) {
                // Hämta billigaste butik för denna produkt
                val cheapestStore = database.productDao().getCheapestStoreForProduct(product.id)
                val avgPrice = database.productDao().getPriceStatsWithCampaignAndDeposit(product.id)?.getTotalAvgPrice()
                
                if (cheapestStore != null) {
                    val campaignText = if (cheapestStore.hasCampaignPrice && cheapestStore.campaignQuantity != null) {
                        "${cheapestStore.campaignQuantity} för ${String.format("%.2f", cheapestStore.campaignPrice ?: cheapestStore.price)} kr"
                    } else null
                    
                    optimizedItems.add(
                        ShoppingListItemOptimized(
                            item = item,
                            productName = product.name,
                            cheapestStorePrice = cheapestStore.getTotalPrice(),
                            averagePrice = avgPrice,
                            storeName = cheapestStore.store_name,
                            hasCampaignPrice = cheapestStore.hasCampaignPrice,
                            campaignText = campaignText
                        )
                    )
                }
            } else {
                // För custom items utan produkt-koppling, använd inmatat pris
                optimizedItems.add(
                    ShoppingListItemOptimized(
                        item = item,
                        productName = item.customItemName,
                        cheapestStorePrice = item.estimatedPrice ?: 0.0,
                        averagePrice = null,
                        storeName = "Valfri butik",
                        hasCampaignPrice = false,
                        campaignText = null
                    )
                )
            }
        }
        
        // Gruppera per butik
        val itemsByStore = optimizedItems.groupBy { it.storeName }
        
        // Skapa optimerade listor per butik
        return itemsByStore.map { (storeName, storeItems) ->
            val totalCost = storeItems.sumOf { it.getTotalCost() }
            val totalSavings = storeItems.sumOf { it.getTotalSavings() }
            val storeAddress = storeItems.firstOrNull()?.let { 
                // Hämta adress från första item (alla har samma butik)
                if (it.item.productId != null) {
                    database.productDao().getCheapestStoreForProduct(it.item.productId!!)?.store_address
                } else null
            }
            
            OptimizedShoppingList(
                storeName = storeName,
                storeAddress = storeAddress,
                items = storeItems,
                totalEstimatedCost = totalCost,
                totalSavings = totalSavings
            )
        }.sortedByDescending { it.totalEstimatedCost } // Sortera efter total kostnad
    }
    
    /**
     * Beräknar total besparing jämfört med att handla allt på genomsnittspriser
     */
    suspend fun calculateTotalSavings(optimizedLists: List<OptimizedShoppingList>): Double {
        return optimizedLists.sumOf { it.totalSavings }
    }
    
    /**
     * Hittar den mest ekonomiska kombinationen av butiker
     * (för framtida implementering - för nu returnerar vi alla butiker)
     */
    suspend fun findOptimalStoreCombination(items: List<ShoppingListItemWithProduct>): List<OptimizedShoppingList> {
        return optimizeShoppingList(items)
    }
}