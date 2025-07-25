package com.example.budgetapp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_notifications")
data class TransactionNotification(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val transactionType: TransactionType,
    val transactionId: Long,
    val transactionTitle: String, // Cached title for display
    val isEnabled: Boolean = true,
    val reminderDaysBefore: Int = 1, // Days before due/recurring date
    val customMessage: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

enum class TransactionType {
    INCOME,
    EXPENSE,
    LOAN_DUE
}