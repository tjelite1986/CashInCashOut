package com.example.budgetapp.automation.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import com.example.budgetapp.automation.AutomationRule
import com.example.budgetapp.automation.AutoTransferRule
import com.example.budgetapp.automation.ml.SmartInsight

@Dao
interface AutomationRuleDao {
    
    // Automation Rules
    @Query("SELECT * FROM automation_rules WHERE isActive = 1 ORDER BY priority ASC, usageCount DESC")
    suspend fun getActiveRules(): List<AutomationRule>
    
    @Query("SELECT * FROM automation_rules ORDER BY createdAt DESC")
    fun getAllRules(): Flow<List<AutomationRule>>
    
    @Query("SELECT * FROM automation_rules WHERE id = :ruleId")
    suspend fun getRuleById(ruleId: Long): AutomationRule?
    
    @Query("SELECT * FROM automation_rules WHERE createdBy = :createdBy ORDER BY createdAt DESC")
    fun getRulesByCreator(createdBy: String): Flow<List<AutomationRule>>
    
    @Query("SELECT * FROM automation_rules WHERE usageCount > :minUsage ORDER BY usageCount DESC")
    suspend fun getPopularRules(minUsage: Long = 5): List<AutomationRule>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRule(rule: AutomationRule): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRules(rules: List<AutomationRule>): List<Long>
    
    @Update
    suspend fun updateRule(rule: AutomationRule)
    
    @Delete
    suspend fun deleteRule(rule: AutomationRule)
    
    @Query("UPDATE automation_rules SET isActive = :isActive WHERE id = :ruleId")
    suspend fun toggleRuleStatus(ruleId: Long, isActive: Boolean)
    
    @Query("UPDATE automation_rules SET usageCount = usageCount + 1, lastModified = :timestamp WHERE id = :ruleId")
    suspend fun incrementRuleUsage(ruleId: Long, timestamp: Long = System.currentTimeMillis())
    
    @Query("DELETE FROM automation_rules WHERE isActive = 0 AND usageCount = 0 AND createdAt < :cutoffTime")
    suspend fun deleteUnusedRules(cutoffTime: Long): Int
    
    @Query("SELECT COUNT(*) FROM automation_rules WHERE isActive = 1")
    suspend fun getActiveRuleCount(): Int
    
    @Query("SELECT AVG(usageCount) FROM automation_rules WHERE isActive = 1")
    suspend fun getAverageRuleUsage(): Double
    
    // Auto Transfer Rules
    @Query("SELECT * FROM auto_transfer_rules WHERE isActive = 1 ORDER BY createdAt DESC")
    suspend fun getActiveTransferRules(): List<AutoTransferRule>
    
    @Query("SELECT * FROM auto_transfer_rules ORDER BY createdAt DESC")
    fun getAllTransferRules(): Flow<List<AutoTransferRule>>
    
    @Query("SELECT * FROM auto_transfer_rules WHERE id = :ruleId")
    suspend fun getTransferRuleById(ruleId: Long): AutoTransferRule?
    
    @Query("SELECT * FROM auto_transfer_rules WHERE sourceAccountId = :accountId OR targetAccountId = :accountId")
    suspend fun getTransferRulesByAccount(accountId: String): List<AutoTransferRule>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransferRule(rule: AutoTransferRule): Long
    
    @Update
    suspend fun updateTransferRule(rule: AutoTransferRule)
    
    @Delete
    suspend fun deleteTransferRule(rule: AutoTransferRule)
    
    @Query("UPDATE auto_transfer_rules SET isActive = :isActive WHERE id = :ruleId")
    suspend fun toggleTransferRuleStatus(ruleId: Long, isActive: Boolean)
    
    @Query("UPDATE auto_transfer_rules SET lastExecuted = :timestamp, totalTransferred = totalTransferred + :amount WHERE id = :ruleId")
    suspend fun recordTransferExecution(ruleId: Long, amount: Double, timestamp: Long = System.currentTimeMillis())
    
    // Smart Insights
    @Query("SELECT * FROM smart_insights WHERE isDismissed = 0 AND (expiresAt IS NULL OR expiresAt > :currentTime) ORDER BY priority DESC, createdAt DESC")
    suspend fun getActiveInsights(currentTime: Long = System.currentTimeMillis()): List<SmartInsight>
    
    @Query("SELECT * FROM smart_insights WHERE type = :type AND isDismissed = 0 ORDER BY createdAt DESC")
    suspend fun getInsightsByType(type: String): List<SmartInsight>
    
    @Query("SELECT * FROM smart_insights WHERE category = :category AND isDismissed = 0 ORDER BY priority DESC")
    suspend fun getInsightsByCategory(category: String): List<SmartInsight>
    
    @Query("SELECT * FROM smart_insights WHERE priority = :priority AND isDismissed = 0 ORDER BY createdAt DESC")
    suspend fun getInsightsByPriority(priority: String): List<SmartInsight>
    
    @Query("SELECT * FROM smart_insights WHERE isRead = 0 AND isDismissed = 0 ORDER BY priority DESC, createdAt DESC")
    suspend fun getUnreadInsights(): List<SmartInsight>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInsight(insight: SmartInsight): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInsights(insights: List<SmartInsight>): List<Long>
    
    @Update
    suspend fun updateInsight(insight: SmartInsight)
    
    @Delete
    suspend fun deleteInsight(insight: SmartInsight)
    
    @Query("UPDATE smart_insights SET isRead = 1 WHERE id = :insightId")
    suspend fun markInsightAsRead(insightId: String)
    
    @Query("UPDATE smart_insights SET isDismissed = 1 WHERE id = :insightId")
    suspend fun dismissInsight(insightId: String)
    
    @Query("UPDATE smart_insights SET isRead = 1 WHERE isDismissed = 0")
    suspend fun markAllInsightsAsRead()
    
    @Query("DELETE FROM smart_insights WHERE isDismissed = 1 AND createdAt < :cutoffTime")
    suspend fun deleteDismissedInsights(cutoffTime: Long): Int
    
    @Query("DELETE FROM smart_insights WHERE expiresAt IS NOT NULL AND expiresAt < :currentTime")
    suspend fun deleteExpiredInsights(currentTime: Long = System.currentTimeMillis()): Int
    
    @Query("SELECT COUNT(*) FROM smart_insights WHERE isRead = 0 AND isDismissed = 0")
    suspend fun getUnreadInsightCount(): Int
    
    @Query("SELECT COUNT(*) FROM smart_insights WHERE priority = :priority AND isDismissed = 0")
    suspend fun getInsightCountByPriority(priority: String): Int
    
    // Analytics and Statistics
    @Query("""
        SELECT 
            COUNT(*) as totalRules,
            SUM(CASE WHEN isActive = 1 THEN 1 ELSE 0 END) as activeRules,
            SUM(usageCount) as totalUsage,
            AVG(usageCount) as averageUsage
        FROM automation_rules
    """)
    suspend fun getAutomationStatistics(): AutomationStatistics
    
    @Query("""
        SELECT 
            type,
            COUNT(*) as count,
            AVG(confidence) as averageConfidence
        FROM smart_insights 
        WHERE isDismissed = 0 
        GROUP BY type
    """)
    suspend fun getInsightStatistics(): List<InsightTypeStatistics>
    
    @Query("""
        SELECT 
            DATE(createdAt/1000, 'unixepoch') as date,
            COUNT(*) as count
        FROM automation_rules 
        WHERE createdAt > :startTime
        GROUP BY DATE(createdAt/1000, 'unixepoch')
        ORDER BY date DESC
    """)
    suspend fun getRuleCreationHistory(startTime: Long): List<RuleCreationHistory>
    
    @Query("""
        SELECT 
            name,
            usageCount,
            (usageCount * 1.0 / :totalUsage) * 100 as usagePercentage
        FROM automation_rules 
        WHERE isActive = 1 AND usageCount > 0
        ORDER BY usageCount DESC
        LIMIT 10
    """)
    suspend fun getTopUsedRules(totalUsage: Long): List<RuleUsageStatistics>
    
    // Backup and Restore
    @Query("SELECT * FROM automation_rules")
    suspend fun getAllRulesForBackup(): List<AutomationRule>
    
    @Query("SELECT * FROM auto_transfer_rules")
    suspend fun getAllTransferRulesForBackup(): List<AutoTransferRule>
    
    @Query("SELECT * FROM smart_insights WHERE isDismissed = 0")
    suspend fun getAllActiveInsightsForBackup(): List<SmartInsight>
    
    @Query("DELETE FROM automation_rules")
    suspend fun deleteAllRules()
    
    @Query("DELETE FROM auto_transfer_rules")
    suspend fun deleteAllTransferRules()
    
    @Query("DELETE FROM smart_insights")
    suspend fun deleteAllInsights()
}

// Data classes for statistics and analytics
data class AutomationStatistics(
    val totalRules: Int,
    val activeRules: Int,
    val totalUsage: Long,
    val averageUsage: Double
)

data class InsightTypeStatistics(
    val type: String,
    val count: Int,
    val averageConfidence: Double
)

data class RuleCreationHistory(
    val date: String,
    val count: Int
)

data class RuleUsageStatistics(
    val name: String,
    val usageCount: Long,
    val usagePercentage: Double
)