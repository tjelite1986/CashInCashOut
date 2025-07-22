package com.example.budgetapp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "shopping_list_items",
    foreignKeys = [
        ForeignKey(
            entity = ShoppingList::class,
            parentColumns = ["id"],
            childColumns = ["shoppingListId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Product::class,
            parentColumns = ["id"],
            childColumns = ["productId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [
        Index(value = ["shoppingListId"]),
        Index(value = ["productId"])
    ]
)
data class ShoppingListItem(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val shoppingListId: Long,
    val productId: Long? = null,
    val customItemName: String? = null,
    val quantity: Int = 1,
    val unit: String? = null,
    val estimatedPrice: Double? = null,
    val actualPrice: Double? = null,
    val notes: String? = null,
    val isCompleted: Boolean = false,
    val priority: Int = 0, // 0 = normal, 1 = high, -1 = low
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis(),
    val completedAt: Long? = null
)