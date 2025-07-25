package com.example.budgetapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.budgetapp.database.entities.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SpendingPatternDao {
    
    @Query("SELECT * FROM spending_patterns ORDER BY analysisDate DESC")
    fun getAllPatterns(): Flow<List<SpendingPattern>>
    
    @Query("SELECT * FROM spending_patterns WHERE category = :category ORDER BY analysisDate DESC")
    fun getPatternsByCategory(category: String): Flow<List<SpendingPattern>>
    
    @Query("SELECT * FROM spending_patterns WHERE timeframe = :timeframe ORDER BY analysisDate DESC")
    fun getPatternsByTimeframe(timeframe: PatternTimeframe): Flow<List<SpendingPattern>>
    
    @Query("SELECT * FROM spending_patterns WHERE anomalyScore >= :threshold ORDER BY anomalyScore DESC")
    suspend fun getAnomalousPatterns(threshold: Float = 0.7f): List<SpendingPattern>
    
    @Query("SELECT * FROM spending_patterns WHERE trendDirection = :direction ORDER BY analysisDate DESC")
    suspend fun getPatternsByTrend(direction: TrendDirection): List<SpendingPattern>
    
    @Query("SELECT * FROM spending_patterns WHERE seasonality != 'NONE' ORDER BY analysisDate DESC")
    suspend fun getSeasonalPatterns(): List<SpendingPattern>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPattern(pattern: SpendingPattern): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPatterns(patterns: List<SpendingPattern>)
    
    @Query("DELETE FROM spending_patterns WHERE analysisDate < :cutoffTime")
    suspend fun deleteOldPatterns(cutoffTime: Long)
    
    @Query("DELETE FROM spending_patterns")
    suspend fun deleteAllPatterns()
}

@Dao
interface SpendingForecastDao {
    
    @Query("SELECT * FROM spending_forecasts ORDER BY forecastDate DESC")
    fun getAllForecasts(): Flow<List<SpendingForecast>>
    
    @Query("SELECT * FROM spending_forecasts WHERE category = :category ORDER BY forecastDate DESC")
    fun getForecastsByCategory(category: String): Flow<List<SpendingForecast>>
    
    @Query("SELECT * FROM spending_forecasts WHERE forecastDate BETWEEN :startDate AND :endDate ORDER BY forecastDate ASC")
    suspend fun getForecastsInRange(startDate: Long, endDate: Long): List<SpendingForecast>
    
    @Query("SELECT * FROM spending_forecasts WHERE actualAmount IS NULL AND forecastDate < :currentTime")
    suspend fun getForecastsNeedingActuals(currentTime: Long = System.currentTimeMillis()): List<SpendingForecast>
    
    @Query("SELECT * FROM spending_forecasts WHERE actualAmount IS NOT NULL")
    suspend fun getCompletedForecasts(): List<SpendingForecast>
    
    @Query("SELECT AVG(accuracy) FROM spending_forecasts WHERE accuracy IS NOT NULL AND forecastMethod = :method")
    suspend fun getAverageAccuracyByMethod(method: ForecastMethod): Double?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForecast(forecast: SpendingForecast): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForecasts(forecasts: List<SpendingForecast>)
    
    @Query("UPDATE spending_forecasts SET actualAmount = :actual, accuracy = :accuracy WHERE id = :id")
    suspend fun updateForecastActual(id: Long, actual: Double, accuracy: Double)
    
    @Query("DELETE FROM spending_forecasts WHERE validUntil < :currentTime")
    suspend fun deleteExpiredForecasts(currentTime: Long = System.currentTimeMillis())
    
    @Query("DELETE FROM spending_forecasts")
    suspend fun deleteAllForecasts()
}