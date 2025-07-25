package com.example.budgetapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.budgetapp.database.entities.ReminderSettings

@Dao
interface ReminderSettingsDao {
    
    @Query("SELECT * FROM reminder_settings ORDER BY createdAt DESC")
    fun getAllReminders(): LiveData<List<ReminderSettings>>
    
    @Query("SELECT * FROM reminder_settings WHERE isEnabled = 1")
    suspend fun getEnabledReminders(): List<ReminderSettings>
    
    @Query("SELECT * FROM reminder_settings WHERE reminderType = :type LIMIT 1")
    suspend fun getReminderByType(type: String): ReminderSettings?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(reminder: ReminderSettings): Long
    
    @Update
    suspend fun updateReminder(reminder: ReminderSettings)
    
    @Delete
    suspend fun deleteReminder(reminder: ReminderSettings)
    
    @Query("UPDATE reminder_settings SET isEnabled = :enabled WHERE id = :id")
    suspend fun toggleReminder(id: Long, enabled: Boolean)
    
    @Query("DELETE FROM reminder_settings")
    suspend fun deleteAllReminders()
}