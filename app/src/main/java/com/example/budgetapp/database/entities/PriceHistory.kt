package com.example.budgetapp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "price_history",
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
        Index(value = ["recordedAt"]),
        Index(value = ["productId", "storeId", "recordedAt"])
    ]
)
data class PriceHistory(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val productId: Long,
    val storeId: Long,
    val price: Double,
    val campaignPrice: Double? = null,
    val campaignQuantity: Int? = null,
    val hasCampaignPrice: Boolean = false,
    val recordedAt: Long = System.currentTimeMillis(),
    val source: String = "manual", // manual, scan, import, etc.
    val notes: String? = null
)