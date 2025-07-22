package com.example.budgetapp.database.dao

import androidx.room.*
import com.example.budgetapp.database.entities.ReceiptItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ReceiptItemDao {
    @Query("SELECT * FROM receipt_items ORDER BY createdAt DESC")
    fun getAllReceiptItems(): Flow<List<ReceiptItem>>

    @Query("SELECT * FROM receipt_items WHERE id = :itemId")
    suspend fun getReceiptItemById(itemId: Long): ReceiptItem?

    @Query("SELECT * FROM receipt_items WHERE receiptId = :receiptId ORDER BY createdAt ASC")
    suspend fun getReceiptItemsByReceiptId(receiptId: Long): List<ReceiptItem>

    @Query("SELECT * FROM receipt_items WHERE receiptId = :receiptId ORDER BY createdAt ASC")
    fun getReceiptItemsByReceiptIdFlow(receiptId: Long): Flow<List<ReceiptItem>>

    @Query("SELECT * FROM receipt_items WHERE productId = :productId ORDER BY createdAt DESC")
    fun getReceiptItemsByProductId(productId: Long): Flow<List<ReceiptItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReceiptItem(receiptItem: ReceiptItem): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReceiptItems(receiptItems: List<ReceiptItem>)

    @Update
    suspend fun updateReceiptItem(receiptItem: ReceiptItem)

    @Delete
    suspend fun deleteReceiptItem(receiptItem: ReceiptItem)

    @Query("DELETE FROM receipt_items WHERE id = :itemId")
    suspend fun deleteReceiptItemById(itemId: Long)

    @Query("DELETE FROM receipt_items WHERE receiptId = :receiptId")
    suspend fun deleteReceiptItemsByReceiptId(receiptId: Long)
}