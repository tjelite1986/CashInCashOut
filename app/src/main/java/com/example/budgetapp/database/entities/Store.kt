package com.example.budgetapp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stores")
data class Store(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val address: String? = null,
    val city: String? = null,
    val chain: String? = null, // t.ex. "ICA", "Coop", "Hemköp"
    val latitude: Double? = null,
    val longitude: Double? = null,
    val phoneNumber: String? = null,
    val website: String? = null,
    val openingHours: String? = null,
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)