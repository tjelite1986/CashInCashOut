package com.example.budgetapp.database.dao

import androidx.room.*
import com.example.budgetapp.database.entities.TransactionNotification
import com.example.budgetapp.database.entities.TransactionType

@Dao
interface TransactionNotificationDao {
    
    @Query("SELECT * FROM transaction_notifications WHERE isEnabled = 1")
    suspend fun getActiveNotifications(): List<TransactionNotification>
    
    @Query("SELECT * FROM transaction_notifications")
    suspend fun getAllNotifications(): List<TransactionNotification>
    
    @Query("SELECT * FROM transaction_notifications WHERE transactionId = :transactionId AND transactionType = :type")
    suspend fun getNotificationForTransaction(transactionId: Long, type: TransactionType): TransactionNotification?
    
    @Query("SELECT * FROM transaction_notifications WHERE transactionType = :type")
    suspend fun getNotificationsByType(type: TransactionType): List<TransactionNotification>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotification(notification: TransactionNotification): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotifications(notifications: List<TransactionNotification>)
    
    @Update
    suspend fun updateNotification(notification: TransactionNotification)
    
    @Delete
    suspend fun deleteNotification(notification: TransactionNotification)
    
    @Query("DELETE FROM transaction_notifications WHERE transactionId = :transactionId AND transactionType = :type")
    suspend fun deleteNotificationForTransaction(transactionId: Long, type: TransactionType)
    
    @Query("DELETE FROM transaction_notifications WHERE transactionType = :type")
    suspend fun deleteNotificationsByType(type: TransactionType)
    
    @Query("UPDATE transaction_notifications SET isEnabled = :enabled WHERE id = :id")
    suspend fun toggleNotification(id: Long, enabled: Boolean)
    
    @Query("SELECT COUNT(*) FROM transaction_notifications WHERE isEnabled = 1")
    suspend fun getActiveNotificationCount(): Int
}