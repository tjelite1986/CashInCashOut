package com.example.budgetapp.database.dao

import androidx.room.*
import com.example.budgetapp.database.entities.Budget
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetDao {
    
    @Query("SELECT * FROM budgets WHERE isActive = 1 ORDER BY createdAt DESC")
    fun getAllActiveBudgets(): Flow<List<Budget>>
    
    @Query("SELECT * FROM budgets ORDER BY createdAt DESC")
    fun getAllBudgets(): Flow<List<Budget>>
    
    @Query("SELECT * FROM budgets WHERE id = :id")
    suspend fun getBudgetById(id: Int): Budget?
    
    @Query("SELECT * FROM budgets WHERE categoryName = :categoryName AND isActive = 1 AND startDate <= :currentDate AND endDate >= :currentDate")
    suspend fun getActiveBudgetForCategory(categoryName: String, currentDate: Long): Budget?
    
    @Query("SELECT DISTINCT categoryName FROM budgets WHERE isActive = 1")
    fun getBudgetCategories(): Flow<List<String>>
    
    @Insert
    suspend fun insertBudget(budget: Budget): Long
    
    @Update
    suspend fun updateBudget(budget: Budget)
    
    @Delete
    suspend fun deleteBudget(budget: Budget)
    
    @Query("UPDATE budgets SET isActive = 0, updatedAt = :timestamp WHERE id = :id")
    suspend fun deactivateBudget(id: Int, timestamp: Long = System.currentTimeMillis())
    
    @Query("DELETE FROM budgets WHERE id = :id")
    suspend fun deleteBudgetById(id: Int)
    
    @Query("SELECT SUM(budgetAmount) FROM budgets WHERE isActive = 1 AND startDate <= :currentDate AND endDate >= :currentDate")
    suspend fun getTotalActiveBudgetAmount(currentDate: Long): Double?
}