package com.example.budgetapp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminder_settings")
data class ReminderSettings(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val reminderType: String,
    val isEnabled: Boolean = true,
    val alertTime: String = "20:00", // Format: HH:mm
    val title: String,
    val message: String,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

enum class ReminderType {
    DAILY_IF_NOT_OPENED,    // Om appen inte öppnats idag
    HOURS_24_FROM_LAST_OPEN, // 24 timmar från senaste öppning
    DAILY_ALWAYS            // Varje dag
}

@Entity(tableName = "app_usage")
data class AppUsage(
    @PrimaryKey
    val id: Long = 1, // Vi behöver bara en rad för att spåra senaste användning
    val lastOpenedAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)