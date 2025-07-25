package com.example.budgetapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.FinancialGoal
import com.example.budgetapp.database.entities.FinancialInsight
import com.example.budgetapp.database.entities.InsightSeverity
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class AnalyticsViewModel(private val database: BudgetDatabase) : ViewModel() {
    
    private val financialInsightDao = database.financialInsightDao()
    private val financialGoalDao = database.financialGoalDao()
    private val expenseDao = database.expenseDao()
    private val incomeDao = database.incomeDao()
    
    // Live data for UI
    val unreadInsights = financialInsightDao.getUnreadInsights().asLiveData()
    val activeGoals = financialGoalDao.getActiveGoals().asLiveData()
    
    private val _financialHealthScore = MutableLiveData<Double>()
    val financialHealthScore: LiveData<Double> = _financialHealthScore
    
    private val _insightCounts = MutableLiveData<Map<String, Int>>()
    val insightCounts: LiveData<Map<String, Int>> = _insightCounts
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    init {
        refreshData()
    }
    
    fun refreshData() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                calculateFinancialHealthScore()
                calculateInsightCounts()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    private suspend fun calculateFinancialHealthScore() {
        try {
            // Get current month data
            val currentMonth = getCurrentMonthRange()
            val monthlyIncome = incomeDao.getTotalIncomeByDateRange(
                currentMonth.first, 
                currentMonth.second
            ) ?: 0.0
            val monthlyExpenses = expenseDao.getTotalExpensesByDateRange(
                currentMonth.first, 
                currentMonth.second
            ) ?: 0.0
            
            // Calculate savings rate
            val savingsRate = if (monthlyIncome > 0) {
                (monthlyIncome - monthlyExpenses) / monthlyIncome
            } else 0.0
            
            // Get goal progress
            val goals = financialGoalDao.getActiveGoals().asLiveData().value ?: emptyList()
            val goalProgress = if (goals.isNotEmpty()) {
                goals.map { it.progressPercentage }.average() / 100.0
            } else 0.5
            
            // Calculate expense stability (lower variance = higher score)
            val expenseStability = calculateExpenseStability()
            
            // Weighted health score
            val healthScore = (savingsRate * 0.4 + goalProgress * 0.3 + expenseStability * 0.3)
                .coerceIn(0.0, 1.0)
            
            _financialHealthScore.value = healthScore
            
        } catch (e: Exception) {
            e.printStackTrace()
            _financialHealthScore.value = 0.0
        }
    }
    
    private suspend fun calculateExpenseStability(): Double {
        return try {
            val endDate = System.currentTimeMillis()
            val startDate = endDate - (90 * 24 * 60 * 60 * 1000L) // Last 3 months
            
            val expenses = expenseDao.getExpensesBetweenDates(startDate, endDate)
            val weeklyTotals = expenses
                .groupBy { getWeekKey(it.date) }
                .values
                .map { weekExpenses -> weekExpenses.sumOf { it.amount } }
            
            if (weeklyTotals.size < 2) return 0.5
            
            val mean = weeklyTotals.average()
            val variance = weeklyTotals.map { (it - mean) * (it - mean) }.average()
            val standardDeviation = kotlin.math.sqrt(variance)
            val coefficientOfVariation = if (mean > 0) standardDeviation / mean else 1.0
            
            // Lower variation = higher stability score
            (1.0 - coefficientOfVariation.coerceIn(0.0, 1.0))
            
        } catch (e: Exception) {
            e.printStackTrace()
            0.5
        }
    }
    
    private suspend fun calculateInsightCounts() {
        try {
            val thirtyDaysAgo = System.currentTimeMillis() - (30 * 24 * 60 * 60 * 1000L)
            
            val counts = mutableMapOf<String, Int>()
            
            // Count by severity
            InsightSeverity.values().forEach { severity ->
                val count = financialInsightDao.getUnreadCountBySeverity(severity)
                counts[severity.name] = count
            }
            
            _insightCounts.value = counts
            
        } catch (e: Exception) {
            e.printStackTrace()
            _insightCounts.value = emptyMap()
        }
    }
    
    fun markInsightAsRead(insightId: Long) {
        viewModelScope.launch {
            try {
                financialInsightDao.markAsRead(insightId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    
    fun markInsightAsActedUpon(insightId: Long) {
        viewModelScope.launch {
            try {
                financialInsightDao.markAsActedUpon(insightId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    
    fun deleteInsight(insightId: Long) {
        viewModelScope.launch {
            try {
                financialInsightDao.deleteInsightById(insightId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    
    // Get insights by category
    fun getInsightsByCategory(category: String) = 
        financialInsightDao.getInsightsByCategory(category).asLiveData()
    
    // Get insights by severity
    fun getInsightsBySeverity(severity: InsightSeverity) = 
        financialInsightDao.getInsightsBySeverity(severity).asLiveData()
    
    // Get actionable insights
    fun getActionableInsights() = 
        financialInsightDao.getActionableInsights().asLiveData()
    
    private fun getCurrentMonthRange(): Pair<Long, Long> {
        val calendar = java.util.Calendar.getInstance()
        calendar.set(java.util.Calendar.DAY_OF_MONTH, 1)
        calendar.set(java.util.Calendar.HOUR_OF_DAY, 0)
        calendar.set(java.util.Calendar.MINUTE, 0)
        calendar.set(java.util.Calendar.SECOND, 0)
        calendar.set(java.util.Calendar.MILLISECOND, 0)
        
        val startOfMonth = calendar.timeInMillis
        
        calendar.add(java.util.Calendar.MONTH, 1)
        calendar.add(java.util.Calendar.MILLISECOND, -1)
        
        val endOfMonth = calendar.timeInMillis
        
        return Pair(startOfMonth, endOfMonth)
    }
    
    private fun getWeekKey(timestamp: Long): String {
        val calendar = java.util.Calendar.getInstance()
        calendar.timeInMillis = timestamp
        val year = calendar.get(java.util.Calendar.YEAR)
        val week = calendar.get(java.util.Calendar.WEEK_OF_YEAR)
        return "$year-W$week"
    }
}

class AnalyticsViewModelFactory(private val database: BudgetDatabase) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AnalyticsViewModel::class.java)) {
            return AnalyticsViewModel(database) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}