package com.example.budgetapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.budgetapp.database.entities.PriceHistory

@Dao
interface PriceHistoryDao {
    @Query("SELECT * FROM price_history WHERE productId = :productId ORDER BY recordedAt DESC")
    fun getPriceHistoryForProduct(productId: Long): LiveData<List<PriceHistory>>

    @Query("SELECT * FROM price_history WHERE productId = :productId AND storeId = :storeId ORDER BY recordedAt DESC")
    fun getPriceHistoryForProductAtStore(productId: Long, storeId: Long): LiveData<List<PriceHistory>>

    @Query("SELECT * FROM price_history WHERE storeId = :storeId ORDER BY recordedAt DESC")
    fun getPriceHistoryForStore(storeId: Long): LiveData<List<PriceHistory>>

    @Query("SELECT * FROM price_history WHERE productId = :productId AND recordedAt BETWEEN :startDate AND :endDate ORDER BY recordedAt DESC")
    fun getPriceHistoryForProductInDateRange(productId: Long, startDate: Long, endDate: Long): LiveData<List<PriceHistory>>

    @Query("SELECT * FROM price_history WHERE productId = :productId AND storeId = :storeId AND recordedAt BETWEEN :startDate AND :endDate ORDER BY recordedAt DESC")
    fun getPriceHistoryForProductAtStoreInDateRange(productId: Long, storeId: Long, startDate: Long, endDate: Long): LiveData<List<PriceHistory>>

    @Query("SELECT * FROM price_history WHERE recordedAt >= :date ORDER BY recordedAt DESC")
    fun getRecentPriceHistory(date: Long): LiveData<List<PriceHistory>>

    @Query("SELECT MIN(price) as minPrice, MAX(price) as maxPrice, AVG(price) as avgPrice FROM price_history WHERE productId = :productId")
    suspend fun getPriceStatisticsForProduct(productId: Long): PriceStatistics?

    @Query("SELECT MIN(price) as minPrice, MAX(price) as maxPrice, AVG(price) as avgPrice FROM price_history WHERE productId = :productId AND storeId = :storeId")
    suspend fun getPriceStatisticsForProductAtStore(productId: Long, storeId: Long): PriceStatistics?

    @Query("SELECT * FROM price_history WHERE productId = :productId AND storeId = :storeId ORDER BY recordedAt DESC LIMIT 1")
    suspend fun getLatestPriceForProductAtStore(productId: Long, storeId: Long): PriceHistory?

    @Query("SELECT * FROM price_history WHERE productId = :productId ORDER BY price ASC LIMIT 1")
    suspend fun getLowestPriceForProduct(productId: Long): PriceHistory?

    @Query("SELECT * FROM price_history WHERE productId = :productId ORDER BY price DESC LIMIT 1")
    suspend fun getHighestPriceForProduct(productId: Long): PriceHistory?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPriceHistory(priceHistory: PriceHistory): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPriceHistoryList(priceHistories: List<PriceHistory>): List<Long>

    @Update
    suspend fun updatePriceHistory(priceHistory: PriceHistory)

    @Delete
    suspend fun deletePriceHistory(priceHistory: PriceHistory)

    @Query("DELETE FROM price_history WHERE id = :id")
    suspend fun deletePriceHistoryById(id: Long)

    @Query("DELETE FROM price_history WHERE productId = :productId")
    suspend fun deletePriceHistoryForProduct(productId: Long)

    @Query("DELETE FROM price_history WHERE storeId = :storeId")
    suspend fun deletePriceHistoryForStore(storeId: Long)

    @Query("DELETE FROM price_history WHERE recordedAt < :cutoffDate")
    suspend fun deleteOldPriceHistory(cutoffDate: Long)

    @Query("SELECT COUNT(*) FROM price_history WHERE productId = :productId")
    fun getPriceHistoryCountForProduct(productId: Long): LiveData<Int>

    @Query("SELECT COUNT(DISTINCT storeId) FROM price_history WHERE productId = :productId")
    suspend fun getStoreCountWithPriceHistoryForProduct(productId: Long): Int

    data class PriceStatistics(
        val minPrice: Double,
        val maxPrice: Double,
        val avgPrice: Double
    )
    
    // Suspend functions for background thread access
    @Query("SELECT * FROM price_history WHERE productId = :productId ORDER BY recordedAt DESC")
    suspend fun getPriceHistoryForProductSuspend(productId: Long): List<PriceHistory>
    
    @Query("SELECT * FROM price_history WHERE recordedAt >= :date ORDER BY recordedAt DESC")
    suspend fun getRecentPriceHistorySuspend(date: Long): List<PriceHistory>
}