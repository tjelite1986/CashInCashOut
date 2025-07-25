package com.example.budgetapp.data

import com.example.budgetapp.database.entities.Product
import com.example.budgetapp.database.entities.ProductStore
import com.example.budgetapp.database.entities.Store

data class ProductWithPrices(
    val product: Product,
    val prices: List<ProductStoreWithStore>
) {
    private fun getTotalPrice(productStore: ProductStore): Double {
        val basePrice = productStore.price
        val depositAmount = if (product.hasDeposit) product.depositAmount ?: 0.0 else 0.0
        return basePrice + depositAmount
    }
    
    val lowestPrice: Double?
        get() = prices.minOfOrNull { getTotalPrice(it.productStore) }
    
    val highestPrice: Double?
        get() = prices.maxOfOrNull { getTotalPrice(it.productStore) }
    
    val averagePrice: Double?
        get() = if (prices.isNotEmpty()) {
            prices.map { getTotalPrice(it.productStore) }.average()
        } else null
}

data class ProductStoreWithStore(
    val productStore: ProductStore,
    val store: Store
)