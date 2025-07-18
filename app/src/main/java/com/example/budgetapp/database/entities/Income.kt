package com.example.budgetapp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "incomes")
data class Income(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val amount: Double,
    val description: String? = null,
    val category: String? = null, // t.ex. "Lön", "Bonus", "Investering", "Övrigt"
    val date: Long = System.currentTimeMillis(), // Datum för inkomsten
    val isRecurring: Boolean = false, // Om det är en återkommande inkomst
    val recurringType: String? = null, // "monthly", "weekly", "yearly"
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)