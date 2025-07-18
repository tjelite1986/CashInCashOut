package com.example.budgetapp.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "product_stores",
    foreignKeys = [
        ForeignKey(
            entity = Product::class,
            parentColumns = ["id"],
            childColumns = ["productId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Store::class,
            parentColumns = ["id"],
            childColumns = ["storeId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["productId"]),
        Index(value = ["storeId"]),
        Index(value = ["productId", "storeId"], unique = true)
    ]
)
data class ProductStore(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val productId: Long,
    val storeId: Long,
    val price: Double,
    val hasCampaignPrice: Boolean = false,
    val campaignQuantity: Int? = null,
    val campaignPrice: Double? = null,
    val lastSeen: Long = System.currentTimeMillis(), // När priset senast observerades
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)