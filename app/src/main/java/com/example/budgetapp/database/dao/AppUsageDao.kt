package com.example.budgetapp.database.dao

import androidx.room.*
import com.example.budgetapp.database.entities.AppUsage

@Dao
interface AppUsageDao {
    
    @Query("SELECT * FROM app_usage WHERE id = 1 LIMIT 1")
    suspend fun getAppUsage(): AppUsage?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateAppUsage(appUsage: AppUsage)
    
    @Query("UPDATE app_usage SET lastOpenedAt = :lastOpened, updatedAt = :updatedAt WHERE id = 1")
    suspend fun updateLastOpened(lastOpened: Long, updatedAt: Long = System.currentTimeMillis())
    
    @Query("SELECT lastOpenedAt FROM app_usage WHERE id = 1 LIMIT 1")
    suspend fun getLastOpenedTime(): Long?
}