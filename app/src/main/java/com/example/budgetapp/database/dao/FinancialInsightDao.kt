package com.example.budgetapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.budgetapp.database.entities.FinancialInsight
import com.example.budgetapp.database.entities.InsightType
import com.example.budgetapp.database.entities.InsightSeverity
import kotlinx.coroutines.flow.Flow

data class InsightTypeCount(
    val insightType: String,
    val count: Int
)

data class CategoryCount(
    val category: String,
    val count: Int
)

@Dao
interface FinancialInsightDao {
    
    @Query("SELECT * FROM financial_insights ORDER BY createdAt DESC")
    fun getAllInsights(): Flow<List<FinancialInsight>>
    
    @Query("SELECT * FROM financial_insights WHERE isRead = 0 ORDER BY severity DESC, createdAt DESC")
    fun getUnreadInsights(): Flow<List<FinancialInsight>>
    
    @Query("SELECT * FROM financial_insights WHERE insightType = :type ORDER BY createdAt DESC")
    fun getInsightsByType(type: InsightType): Flow<List<FinancialInsight>>
    
    @Query("SELECT * FROM financial_insights WHERE severity = :severity ORDER BY createdAt DESC")
    fun getInsightsBySeverity(severity: InsightSeverity): Flow<List<FinancialInsight>>
    
    @Query("SELECT * FROM financial_insights WHERE category = :category ORDER BY createdAt DESC")
    fun getInsightsByCategory(category: String): Flow<List<FinancialInsight>>
    
    @Query("SELECT * FROM financial_insights WHERE actionable = 1 AND isActedUpon = 0 ORDER BY severity DESC, createdAt DESC")
    fun getActionableInsights(): Flow<List<FinancialInsight>>
    
    @Query("SELECT * FROM financial_insights WHERE validUntil IS NULL OR validUntil > :currentTime ORDER BY createdAt DESC")
    suspend fun getValidInsights(currentTime: Long = System.currentTimeMillis()): List<FinancialInsight>
    
    @Query("SELECT * FROM financial_insights WHERE confidenceScore >= :minConfidence ORDER BY confidenceScore DESC, createdAt DESC")
    suspend fun getHighConfidenceInsights(minConfidence: Float = 0.7f): List<FinancialInsight>
    
    @Query("SELECT COUNT(*) FROM financial_insights WHERE isRead = 0")
    fun getUnreadCount(): LiveData<Int>
    
    @Query("SELECT COUNT(*) FROM financial_insights WHERE severity = :severity AND isRead = 0")
    suspend fun getUnreadCountBySeverity(severity: InsightSeverity): Int
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInsight(insight: FinancialInsight): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInsights(insights: List<FinancialInsight>)
    
    @Update
    suspend fun updateInsight(insight: FinancialInsight)
    
    @Query("UPDATE financial_insights SET isRead = 1, updatedAt = :timestamp WHERE id = :id")
    suspend fun markAsRead(id: Long, timestamp: Long = System.currentTimeMillis())
    
    @Query("UPDATE financial_insights SET isRead = 1, updatedAt = :timestamp WHERE id IN (:ids)")
    suspend fun markAsRead(ids: List<Long>, timestamp: Long = System.currentTimeMillis())
    
    @Query("UPDATE financial_insights SET isActedUpon = 1, updatedAt = :timestamp WHERE id = :id")
    suspend fun markAsActedUpon(id: Long, timestamp: Long = System.currentTimeMillis())
    
    @Delete
    suspend fun deleteInsight(insight: FinancialInsight)
    
    @Query("DELETE FROM financial_insights WHERE id = :id")
    suspend fun deleteInsightById(id: Long)
    
    @Query("DELETE FROM financial_insights WHERE validUntil IS NOT NULL AND validUntil < :currentTime")
    suspend fun deleteExpiredInsights(currentTime: Long = System.currentTimeMillis())
    
    @Query("DELETE FROM financial_insights WHERE createdAt < :cutoffTime")
    suspend fun deleteOldInsights(cutoffTime: Long)
    
    @Query("DELETE FROM financial_insights")
    suspend fun deleteAllInsights()
    
    @Query("""
        SELECT COUNT(*) FROM financial_insights 
        WHERE insightType = :type 
        AND createdAt > :since
    """)
    suspend fun getInsightCountByTypeSince(type: InsightType, since: Long): Int
    
    @Query("""
        SELECT AVG(confidenceScore) FROM financial_insights 
        WHERE insightType = :type 
        AND createdAt > :since
    """)
    suspend fun getAverageConfidenceByType(type: InsightType, since: Long): Double?
    
    // Analytics queries
    @Query("""
        SELECT insightType, COUNT(*) as count 
        FROM financial_insights 
        WHERE createdAt > :since 
        GROUP BY insightType
    """)
    suspend fun getInsightCountsByType(since: Long): List<InsightTypeCount>
    
    @Query("""
        SELECT category, COUNT(*) as count 
        FROM financial_insights 
        WHERE category IS NOT NULL AND createdAt > :since 
        GROUP BY category
    """)
    suspend fun getInsightCountsByCategory(since: Long): List<CategoryCount>
}