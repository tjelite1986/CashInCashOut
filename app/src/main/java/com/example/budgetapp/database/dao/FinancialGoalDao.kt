package com.example.budgetapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.budgetapp.database.entities.FinancialGoal
import com.example.budgetapp.database.entities.GoalType
import com.example.budgetapp.database.entities.GoalPriority
import kotlinx.coroutines.flow.Flow

data class GoalTypeCount(
    val goalType: String,
    val count: Int
)

data class GoalTypeProgress(
    val goalType: String,
    val avgProgress: Double
)

data class GoalTypeTarget(
    val goalType: String,
    val totalTarget: Double
)

data class GoalPriorityCount(
    val priority: String,
    val count: Int
)

@Dao
interface FinancialGoalDao {
    
    @Query("SELECT * FROM financial_goals ORDER BY priority DESC, targetDate ASC")
    fun getAllGoals(): Flow<List<FinancialGoal>>
    
    @Query("SELECT * FROM financial_goals WHERE isActive = 1 ORDER BY priority DESC, targetDate ASC")
    fun getActiveGoals(): Flow<List<FinancialGoal>>
    
    @Query("SELECT * FROM financial_goals WHERE isCompleted = 1 ORDER BY completedAt DESC")
    fun getCompletedGoals(): Flow<List<FinancialGoal>>
    
    @Query("SELECT * FROM financial_goals WHERE goalType = :type ORDER BY priority DESC, targetDate ASC")
    fun getGoalsByType(type: GoalType): Flow<List<FinancialGoal>>
    
    @Query("SELECT * FROM financial_goals WHERE priority = :priority ORDER BY targetDate ASC")
    fun getGoalsByPriority(priority: GoalPriority): Flow<List<FinancialGoal>>
    
    @Query("SELECT * FROM financial_goals WHERE id = :goalId")
    suspend fun getGoalById(goalId: Long): FinancialGoal?
    
    @Query("SELECT * FROM financial_goals WHERE targetDate IS NOT NULL AND targetDate < :date AND isCompleted = 0")
    suspend fun getOverdueGoals(date: Long = System.currentTimeMillis()): List<FinancialGoal>
    
    @Query("SELECT * FROM financial_goals WHERE targetDate IS NOT NULL AND targetDate BETWEEN :startDate AND :endDate")
    suspend fun getGoalsDueInRange(startDate: Long, endDate: Long): List<FinancialGoal>
    
    @Query("SELECT * FROM financial_goals WHERE (currentAmount / targetAmount * 100) >= 100 AND isCompleted = 0")
    suspend fun getGoalsReadyForCompletion(): List<FinancialGoal>
    
    @Query("SELECT * FROM financial_goals WHERE reminderEnabled = 1 AND isActive = 1 AND isCompleted = 0")
    suspend fun getGoalsWithReminders(): List<FinancialGoal>
    
    @Query("SELECT * FROM financial_goals WHERE autoTransferEnabled = 1 AND isActive = 1 AND isCompleted = 0")
    suspend fun getGoalsWithAutoTransfer(): List<FinancialGoal>
    
    @Query("SELECT COUNT(*) FROM financial_goals WHERE isActive = 1")
    fun getActiveGoalCount(): LiveData<Int>
    
    @Query("SELECT COUNT(*) FROM financial_goals WHERE isCompleted = 1")
    fun getCompletedGoalCount(): LiveData<Int>
    
    @Query("SELECT SUM(targetAmount) FROM financial_goals WHERE isActive = 1")
    suspend fun getTotalTargetAmount(): Double?
    
    @Query("SELECT SUM(currentAmount) FROM financial_goals WHERE isActive = 1")
    suspend fun getTotalCurrentAmount(): Double?
    
    @Query("SELECT AVG(currentAmount / targetAmount * 100) FROM financial_goals WHERE isActive = 1")
    suspend fun getAverageProgress(): Double?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoal(goal: FinancialGoal): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGoals(goals: List<FinancialGoal>)
    
    @Update
    suspend fun updateGoal(goal: FinancialGoal)
    
    @Query("UPDATE financial_goals SET currentAmount = :amount, updatedAt = :timestamp WHERE id = :goalId")
    suspend fun updateGoalProgress(goalId: Long, amount: Double, timestamp: Long = System.currentTimeMillis())
    
    @Query("UPDATE financial_goals SET currentAmount = currentAmount + :amount, updatedAt = :timestamp WHERE id = :goalId")
    suspend fun addToGoalProgress(goalId: Long, amount: Double, timestamp: Long = System.currentTimeMillis())
    
    @Query("""
        UPDATE financial_goals 
        SET isCompleted = 1, 
            completedAt = :timestamp, 
            updatedAt = :timestamp 
        WHERE id = :goalId
    """)
    suspend fun markGoalCompleted(goalId: Long, timestamp: Long = System.currentTimeMillis())
    
    @Query("UPDATE financial_goals SET isActive = 0, updatedAt = :timestamp WHERE id = :goalId")
    suspend fun deactivateGoal(goalId: Long, timestamp: Long = System.currentTimeMillis())
    
    @Query("UPDATE financial_goals SET isActive = 1, updatedAt = :timestamp WHERE id = :goalId")
    suspend fun activateGoal(goalId: Long, timestamp: Long = System.currentTimeMillis())
    
    @Delete
    suspend fun deleteGoal(goal: FinancialGoal)
    
    @Query("DELETE FROM financial_goals WHERE id = :goalId")
    suspend fun deleteGoalById(goalId: Long)
    
    @Query("DELETE FROM financial_goals WHERE isCompleted = 1 AND completedAt < :cutoffTime")
    suspend fun deleteOldCompletedGoals(cutoffTime: Long)
    
    @Query("DELETE FROM financial_goals")
    suspend fun deleteAllGoals()
    
    // Analytics queries
    @Query("""
        SELECT goalType, COUNT(*) as count 
        FROM financial_goals 
        WHERE isActive = 1 
        GROUP BY goalType
    """)
    suspend fun getActiveGoalCountsByType(): List<GoalTypeCount>
    
    @Query("""
        SELECT goalType, AVG(currentAmount / targetAmount * 100) as avgProgress 
        FROM financial_goals 
        WHERE isActive = 1 
        GROUP BY goalType
    """)
    suspend fun getAverageProgressByType(): List<GoalTypeProgress>
    
    @Query("""
        SELECT goalType, SUM(targetAmount) as totalTarget 
        FROM financial_goals 
        WHERE isActive = 1 
        GROUP BY goalType
    """)
    suspend fun getTotalTargetByType(): List<GoalTypeTarget>
    
    @Query("""
        SELECT priority, COUNT(*) as count 
        FROM financial_goals 
        WHERE isActive = 1 
        GROUP BY priority
    """)
    suspend fun getActiveGoalCountsByPriority(): List<GoalPriorityCount>
}