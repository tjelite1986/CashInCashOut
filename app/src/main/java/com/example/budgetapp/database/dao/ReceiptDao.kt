package com.example.budgetapp.database.dao

import androidx.room.*
import com.example.budgetapp.database.entities.Receipt
import kotlinx.coroutines.flow.Flow

@Dao
interface ReceiptDao {
    @Query("SELECT * FROM receipts ORDER BY createdAt DESC")
    fun getAllReceipts(): Flow<List<Receipt>>

    @Query("SELECT * FROM receipts WHERE id = :receiptId")
    suspend fun getReceiptById(receiptId: Long): Receipt?

    @Query("SELECT * FROM receipts WHERE expenseId = :expenseId")
    suspend fun getReceiptByExpenseId(expenseId: Long): Receipt?

    @Query("SELECT * FROM receipts WHERE createdAt >= :startDate AND createdAt <= :endDate ORDER BY createdAt DESC")
    fun getReceiptsByDateRange(startDate: Long, endDate: Long): Flow<List<Receipt>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReceipt(receipt: Receipt): Long

    @Update
    suspend fun updateReceipt(receipt: Receipt)

    @Delete
    suspend fun deleteReceipt(receipt: Receipt)

    @Query("DELETE FROM receipts WHERE id = :receiptId")
    suspend fun deleteReceiptById(receiptId: Long)
}