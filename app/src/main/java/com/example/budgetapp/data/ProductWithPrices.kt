package com.example.budgetapp.data

import com.example.budgetapp.database.entities.Product
import com.example.budgetapp.database.entities.ProductStore
import com.example.budgetapp.database.entities.Store

data class ProductWithPrices(
    val product: Product,
    val prices: List<ProductStoreWithStore>
) {
    val lowestPrice: Double?
        get() = prices.minOfOrNull { it.productStore.price }
    
    val highestPrice: Double?
        get() = prices.maxOfOrNull { it.productStore.price }
    
    val averagePrice: Double?
        get() = if (prices.isNotEmpty()) {
            prices.map { it.productStore.price }.average()
        } else null
}

data class ProductStoreWithStore(
    val productStore: ProductStore,
    val store: Store
)