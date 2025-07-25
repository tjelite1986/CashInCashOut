package com.example.budgetapp.data

import androidx.room.Embedded
import androidx.room.Relation
import com.example.budgetapp.database.entities.Product
import com.example.budgetapp.database.entities.ShoppingListItem

data class ShoppingListItemWithProduct(
    @Embedded val item: ShoppingListItem,
    @Relation(
        parentColumn = "productId",
        entityColumn = "id"
    )
    val product: Product?
) {
    fun getDisplayName(): String {
        return if (product != null) {
            product.name
        } else {
            item.customItemName ?: "Unnamed item"
        }
    }
}