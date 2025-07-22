package com.example.budgetapp.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "products",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index(value = ["categoryId"])]
)
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val categoryId: Long? = null,
    val hasDeposit: Boolean = false,
    val depositAmount: Double? = null,
    val barcode: String? = null,
    val productName: String? = null, // Ändrat från brand till productName
    val description: String? = null,
    val amount: Double? = null, // Ny: mängd t.ex. 330
    val unit: String? = "st", // st, g, kg, cl, ml, l, mb, gb
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)