package com.example.budgetapp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val amount: Double,
    val description: String? = null,
    val category: String? = null, // t.ex. "Mat", "Transport", "Hygien", "Nöje", "Räkningar", "Övrigt"
    val store: String? = null, // Vilken butik/plats utgiften gjordes
    val date: Long = System.currentTimeMillis(), // Datum för utgiften
    val isRecurring: Boolean = false, // Om det är en återkommande utgift
    val recurringType: String? = null, // "monthly", "weekly", "yearly"
    val paymentMethod: String? = null, // "Kort", "Kontant", "Swish", "Faktura"
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)