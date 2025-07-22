package com.example.budgetapp.repository

import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.Budget
import com.example.budgetapp.database.entities.BudgetPeriod
import kotlinx.coroutines.flow.Flow
import java.util.*

class BudgetRepository(private val database: BudgetDatabase) {
    
    private val budgetDao = database.budgetDao()
    private val expenseDao = database.expenseDao()
    
    fun getAllActiveBudgets(): Flow<List<Budget>> = budgetDao.getAllActiveBudgets()
    
    fun getAllBudgets(): Flow<List<Budget>> = budgetDao.getAllBudgets()
    
    suspend fun getBudgetById(id: Int): Budget? = budgetDao.getBudgetById(id)
    
    suspend fun getActiveBudgetForCategory(categoryName: String): Budget? {
        val currentDate = System.currentTimeMillis()
        return budgetDao.getActiveBudgetForCategory(categoryName, currentDate)
    }
    
    fun getBudgetCategories(): Flow<List<String>> = budgetDao.getBudgetCategories()
    
    suspend fun insertBudget(budget: Budget): Long {
        val updatedBudget = budget.copy(
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )
        return budgetDao.insertBudget(updatedBudget)
    }
    
    suspend fun updateBudget(budget: Budget) {
        val updatedBudget = budget.copy(updatedAt = System.currentTimeMillis())
        budgetDao.updateBudget(updatedBudget)
    }
    
    suspend fun deleteBudget(budget: Budget) = budgetDao.deleteBudget(budget)
    
    suspend fun deactivateBudget(id: Int) = budgetDao.deactivateBudget(id)
    
    suspend fun getTotalActiveBudgetAmount(): Double {
        val currentDate = System.currentTimeMillis()
        return budgetDao.getTotalActiveBudgetAmount(currentDate) ?: 0.0
    }
    
    suspend fun getSpentAmountForCategory(categoryName: String, budget: Budget): Double {
        val expenses = expenseDao.getExpensesBetweenDates(budget.startDate, budget.endDate)
        return expenses.filter { it.category == categoryName }.sumOf { it.amount }
    }
    
    suspend fun getBudgetProgress(budget: Budget): BudgetProgress {
        val spentAmount = getSpentAmountForCategory(budget.categoryName, budget)
        val percentage = if (budget.budgetAmount > 0) {
            (spentAmount / budget.budgetAmount * 100).coerceAtMost(100.0)
        } else 0.0
        
        val remainingAmount = (budget.budgetAmount - spentAmount).coerceAtLeast(0.0)
        val isOverBudget = spentAmount > budget.budgetAmount
        val isNearLimit = percentage >= budget.alertThreshold
        
        return BudgetProgress(
            budget = budget,
            spentAmount = spentAmount,
            remainingAmount = remainingAmount,
            percentage = percentage,
            isOverBudget = isOverBudget,
            isNearLimit = isNearLimit
        )
    }
    
    suspend fun getBudgetProgressList(budgets: List<Budget>): List<BudgetProgress> {
        if (budgets.isEmpty()) return emptyList()
        
        // Get all expenses in one query for the entire date range
        val startDate = budgets.minOfOrNull { it.startDate } ?: return emptyList()
        val endDate = budgets.maxOfOrNull { it.endDate } ?: return emptyList()
        val expenses = expenseDao.getExpensesBetweenDates(startDate, endDate)
        
        // Group expenses by category for efficient lookup
        val expensesByCategory = expenses.groupBy { it.category }
        
        // Calculate progress for each budget using cached expenses
        return budgets.map { budget ->
            val categoryExpenses = expensesByCategory[budget.categoryName] ?: emptyList()
            val spentAmount = categoryExpenses
                .filter { it.date >= budget.startDate && it.date <= budget.endDate }
                .sumOf { it.amount }
            
            val percentage = if (budget.budgetAmount > 0) {
                (spentAmount / budget.budgetAmount * 100).coerceAtMost(100.0)
            } else 0.0
            
            val remainingAmount = (budget.budgetAmount - spentAmount).coerceAtLeast(0.0)
            val isOverBudget = spentAmount > budget.budgetAmount
            val isNearLimit = percentage >= budget.alertThreshold
            
            BudgetProgress(
                budget = budget,
                spentAmount = spentAmount,
                remainingAmount = remainingAmount,
                percentage = percentage,
                isOverBudget = isOverBudget,
                isNearLimit = isNearLimit
            )
        }
    }
    
    fun createBudgetPeriod(period: BudgetPeriod, startDate: Date = Date()): Pair<Long, Long> {
        val calendar = Calendar.getInstance()
        calendar.time = startDate
        
        // Sätt start av perioden
        when (period) {
            BudgetPeriod.WEEKLY -> {
                calendar.set(Calendar.DAY_OF_WEEK, calendar.firstDayOfWeek)
            }
            BudgetPeriod.MONTHLY -> {
                calendar.set(Calendar.DAY_OF_MONTH, 1)
            }
            BudgetPeriod.QUARTERLY -> {
                val currentMonth = calendar.get(Calendar.MONTH)
                val quarterStartMonth = (currentMonth / 3) * 3
                calendar.set(Calendar.MONTH, quarterStartMonth)
                calendar.set(Calendar.DAY_OF_MONTH, 1)
            }
            BudgetPeriod.YEARLY -> {
                calendar.set(Calendar.DAY_OF_YEAR, 1)
            }
        }
        
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val periodStart = calendar.timeInMillis
        
        // Sätt slut av perioden
        when (period) {
            BudgetPeriod.WEEKLY -> {
                calendar.add(Calendar.WEEK_OF_YEAR, 1)
                calendar.add(Calendar.DAY_OF_WEEK, -1)
            }
            BudgetPeriod.MONTHLY -> {
                calendar.add(Calendar.MONTH, 1)
                calendar.add(Calendar.DAY_OF_MONTH, -1)
            }
            BudgetPeriod.QUARTERLY -> {
                calendar.add(Calendar.MONTH, 3)
                calendar.add(Calendar.DAY_OF_MONTH, -1)
            }
            BudgetPeriod.YEARLY -> {
                calendar.add(Calendar.YEAR, 1)
                calendar.add(Calendar.DAY_OF_YEAR, -1)
            }
        }
        
        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        calendar.set(Calendar.MILLISECOND, 999)
        val periodEnd = calendar.timeInMillis
        
        return Pair(periodStart, periodEnd)
    }
}

data class BudgetProgress(
    val budget: Budget,
    val spentAmount: Double,
    val remainingAmount: Double,
    val percentage: Double,
    val isOverBudget: Boolean,
    val isNearLimit: Boolean
)