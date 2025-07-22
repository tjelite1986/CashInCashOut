package com.example.budgetapp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(
    tableName = "receipts",
    foreignKeys = [
        ForeignKey(
            entity = Expense::class,
            parentColumns = ["id"],
            childColumns = ["expenseId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Receipt(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val expenseId: Long,
    val receiptNumber: String? = null,
    val totalAmount: Double,
    val tax: Double = 0.0,
    val discount: Double = 0.0,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)