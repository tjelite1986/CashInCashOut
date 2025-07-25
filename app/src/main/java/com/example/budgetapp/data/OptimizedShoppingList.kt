package com.example.budgetapp.data

import com.example.budgetapp.database.entities.ShoppingListItem

data class OptimizedShoppingList(
    val storeName: String,
    val storeAddress: String?,
    val items: List<ShoppingListItemOptimized>,
    val totalEstimatedCost: Double,
    val totalSavings: Double // Vad man sparar jämfört med genomsnittspris
) {
    fun getItemCount(): Int = items.size
    
    fun getTotalQuantity(): Int = items.sumOf { it.item.quantity }
}

data class ShoppingListItemOptimized(
    val item: ShoppingListItem,
    val productName: String?,
    val cheapestStorePrice: Double, // Inkl. pant och kampanjpriser
    val averagePrice: Double?, // För att beräkna besparingar
    val storeName: String,
    val hasCampaignPrice: Boolean,
    val campaignText: String? // "3 för 20 kr" etc
) {
    fun getSavingsPerItem(): Double {
        return if (averagePrice != null && averagePrice > cheapestStorePrice) {
            averagePrice - cheapestStorePrice
        } else 0.0
    }
    
    fun getTotalSavings(): Double {
        return getSavingsPerItem() * item.quantity
    }
    
    fun getTotalCost(): Double {
        return cheapestStorePrice * item.quantity
    }
}