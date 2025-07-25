package com.example.budgetapp.services

import android.content.Context
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.*

class AdvancedAnalyticsService(private val context: Context) {
    
    private val database = BudgetDatabase.getDatabase(context)
    private val expenseDao = database.expenseDao()
    private val incomeDao = database.incomeDao()
    private val insightDao = database.financialInsightDao()
    private val goalDao = database.financialGoalDao()
    
    companion object {
        const val ANOMALY_THRESHOLD = 2.0 // Standard deviations for anomaly detection
        const val CONFIDENCE_THRESHOLD = 0.7f
        const val ANALYSIS_PERIOD_DAYS = 90
    }
    
    /**
     * Main analysis function that generates all types of insights
     */
    suspend fun generateAllInsights() = withContext(Dispatchers.IO) {
        try {
            // Clean up old insights first
            cleanupOldInsights()
            
            // Generate different types of insights
            generateSpendingAnomalyInsights()
            generateBudgetWarningInsights()
            generateSavingOpportunityInsights()
            generateCashFlowPredictions()
            generateGoalProgressInsights()
            generateSeasonalPatternInsights()
            generateFinancialHealthInsights()
            
        } catch (e: Exception) {
            com.example.budgetapp.utils.ErrorHandler.logError("AdvancedAnalytics", "Failed to generate insights", e)
        }
    }
    
    /**
     * Detect spending anomalies using statistical analysis
     */
    private suspend fun generateSpendingAnomalyInsights() = withContext(Dispatchers.IO) {
        val endDate = System.currentTimeMillis()
        val startDate = endDate - (ANALYSIS_PERIOD_DAYS * 24 * 60 * 60 * 1000L)
        
        val expenses = expenseDao.getExpensesBetweenDates(startDate, endDate)
        val categorySpending = expenses.groupBy { it.category ?: "Uncategorized" }
        
        categorySpending.forEach { (category, categoryExpenses) ->
            val amounts = categoryExpenses.map { it.amount }
            if (amounts.size >= 7) { // Need at least 7 data points
                val stats = calculateStatistics(amounts)
                val recentExpenses = categoryExpenses.takeLast(3)
                
                recentExpenses.forEach { expense ->
                    val zScore = abs((expense.amount - stats.mean) / stats.standardDeviation)
                    
                    if (zScore > ANOMALY_THRESHOLD) {
                        val insight = FinancialInsight(
                            insightType = InsightType.SPENDING_ANOMALY,
                            title = "Unusual ${category.lowercase()} spending detected",
                            description = "You spent ${formatCurrency(expense.amount)} on ${expense.title}, which is ${
                                String.format("%.1f", zScore)
                            }x higher than your average of ${formatCurrency(stats.mean)}",
                            severity = when {
                                zScore > 4.0 -> InsightSeverity.CRITICAL
                                zScore > 3.0 -> InsightSeverity.HIGH
                                else -> InsightSeverity.MEDIUM
                            },
                            category = category,
                            amount = expense.amount,
                            percentage = ((expense.amount - stats.mean) / stats.mean * 100),
                            confidenceScore = min(1.0f, (zScore / 5.0).toFloat()),
                            suggestedAction = "Review this transaction and consider if it aligns with your budget goals"
                        )
                        
                        insightDao.insertInsight(insight)
                    }
                }
            }
        }
    }
    
    /**
     * Generate budget warning insights
     */
    private suspend fun generateBudgetWarningInsights() = withContext(Dispatchers.IO) {
        val currentMonth = getCurrentMonthRange()
        val monthlyExpenses = expenseDao.getExpensesBetweenDates(currentMonth.first, currentMonth.second)
        val categorySpending = monthlyExpenses.groupBy { it.category ?: "Uncategorized" }
        
        // Get historical average for each category
        val historicalData = getHistoricalAverages()
        
        categorySpending.forEach { (category, expenses) ->
            val currentSpending = expenses.sumOf { it.amount }
            val historicalAverage = historicalData[category] ?: 0.0
            
            if (historicalAverage > 0 && currentSpending > historicalAverage * 0.8) {
                val percentageOfBudget = (currentSpending / historicalAverage * 100)
                
                val insight = FinancialInsight(
                    insightType = InsightType.BUDGET_WARNING,
                    title = "${category} budget alert",
                    description = when {
                        currentSpending > historicalAverage -> "You've exceeded your typical ${category.lowercase()} spending by ${formatCurrency(currentSpending - historicalAverage)}"
                        percentageOfBudget > 90 -> "You've used ${String.format("%.0f", percentageOfBudget)}% of your typical ${category.lowercase()} budget"
                        else -> "You're approaching your ${category.lowercase()} spending limit"
                    },
                    severity = when {
                        currentSpending > historicalAverage * 1.5 -> InsightSeverity.CRITICAL
                        currentSpending > historicalAverage -> InsightSeverity.HIGH
                        percentageOfBudget > 90 -> InsightSeverity.MEDIUM
                        else -> InsightSeverity.LOW
                    },
                    category = category,
                    amount = currentSpending,
                    percentage = percentageOfBudget,
                    confidenceScore = 0.9f,
                    suggestedAction = "Consider reducing ${category.lowercase()} expenses for the rest of the month"
                )
                
                insightDao.insertInsight(insight)
            }
        }
    }
    
    /**
     * Identify saving opportunities
     */
    private suspend fun generateSavingOpportunityInsights() = withContext(Dispatchers.IO) {
        val endDate = System.currentTimeMillis()
        val startDate = endDate - (30 * 24 * 60 * 60 * 1000L) // Last 30 days
        
        val expenses = expenseDao.getExpensesBetweenDates(startDate, endDate)
        val recurringExpenses = identifyRecurringExpenses(expenses)
        
        recurringExpenses.forEach { (pattern, expenseList) ->
            val totalAmount = expenseList.sumOf { it.amount }
            val averageAmount = totalAmount / expenseList.size
            
            // Look for optimization opportunities
            if (expenseList.size >= 3 && averageAmount >= 50.0) {
                val potentialSavings = averageAmount * 0.1 // Assume 10% savings possible
                
                val insight = FinancialInsight(
                    insightType = InsightType.SAVING_OPPORTUNITY,
                    title = "Potential savings in ${pattern}",
                    description = "You spend an average of ${formatCurrency(averageAmount)} on ${pattern.lowercase()}. " +
                            "Consider negotiating or finding alternatives to save approximately ${formatCurrency(potentialSavings)} per month.",
                    severity = when {
                        potentialSavings > 200 -> InsightSeverity.HIGH
                        potentialSavings > 100 -> InsightSeverity.MEDIUM
                        else -> InsightSeverity.LOW
                    },
                    category = pattern,
                    amount = potentialSavings,
                    percentage = 10.0,
                    confidenceScore = 0.7f,
                    suggestedAction = "Research alternatives or negotiate better rates for ${pattern.lowercase()}"
                )
                
                insightDao.insertInsight(insight)
            }
        }
    }
    
    /**
     * Generate cash flow predictions
     */
    private suspend fun generateCashFlowPredictions() = withContext(Dispatchers.IO) {
        val historicalIncome = getHistoricalMonthlyIncome()
        val historicalExpenses = getHistoricalMonthlyExpenses()
        
        if (historicalIncome.isNotEmpty() && historicalExpenses.isNotEmpty()) {
            val avgIncome = historicalIncome.average()
            val avgExpenses = historicalExpenses.average()
            val netCashFlow = avgIncome - avgExpenses
            
            val insight = FinancialInsight(
                insightType = InsightType.CASH_FLOW_PREDICTION,
                title = "Monthly cash flow forecast",
                description = when {
                    netCashFlow > 1000 -> "Your predicted monthly surplus is ${formatCurrency(netCashFlow)}. Great opportunity to increase savings!"
                    netCashFlow > 0 -> "Your predicted monthly surplus is ${formatCurrency(netCashFlow)}. Consider setting aside more for goals."
                    netCashFlow > -500 -> "Your cash flow is tight with a predicted deficit of ${formatCurrency(abs(netCashFlow))}. Review expenses."
                    else -> "Warning: Predicted monthly deficit of ${formatCurrency(abs(netCashFlow))}. Immediate action needed."
                },
                severity = when {
                    netCashFlow > 500 -> InsightSeverity.INFO
                    netCashFlow > 0 -> InsightSeverity.LOW
                    netCashFlow > -500 -> InsightSeverity.MEDIUM
                    else -> InsightSeverity.CRITICAL
                },
                amount = abs(netCashFlow),
                percentage = (abs(netCashFlow) / avgIncome * 100),
                confidenceScore = calculatePredictionConfidence(historicalIncome, historicalExpenses),
                suggestedAction = when {
                    netCashFlow > 500 -> "Consider increasing your savings rate or setting new financial goals"
                    netCashFlow > 0 -> "Look for opportunities to optimize your budget"
                    else -> "Review your expenses and look for areas to cut spending"
                }
            )
            
            insightDao.insertInsight(insight)
        }
    }
    
    /**
     * Analyze goal progress and generate insights
     */
    private suspend fun generateGoalProgressInsights() = withContext(Dispatchers.IO) {
        val activeGoals = goalDao.getActiveGoals()
        
        activeGoals.collect { goals ->
            goals.forEach { goal ->
                val daysRemaining = goal.targetDate?.let { targetDate ->
                    ((targetDate - System.currentTimeMillis()) / (24 * 60 * 60 * 1000)).toInt()
                }
                
                val progressPercentage = goal.progressPercentage
                val remainingAmount = goal.remainingAmount
                
                // Generate insights based on goal progress
                when {
                    progressPercentage >= 100 -> {
                        val insight = FinancialInsight(
                            insightType = InsightType.GOAL_PROGRESS,
                            title = "🎉 Goal achieved: ${goal.name}",
                            description = "Congratulations! You've reached your goal of ${formatCurrency(goal.targetAmount)} for ${goal.name}.",
                            severity = InsightSeverity.INFO,
                            amount = goal.targetAmount,
                            percentage = progressPercentage,
                            confidenceScore = 1.0f,
                            suggestedAction = "Mark this goal as completed and consider setting a new challenge!"
                        )
                        insightDao.insertInsight(insight)
                    }
                    
                    daysRemaining != null && daysRemaining <= 30 && progressPercentage < 80 -> {
                        val dailyRequirement = remainingAmount / daysRemaining
                        val insight = FinancialInsight(
                            insightType = InsightType.GOAL_PROGRESS,
                            title = "Goal deadline approaching: ${goal.name}",
                            description = "You need to save ${formatCurrency(dailyRequirement)} daily to reach your ${goal.name} goal in $daysRemaining days.",
                            severity = when {
                                progressPercentage < 50 -> InsightSeverity.HIGH
                                progressPercentage < 70 -> InsightSeverity.MEDIUM
                                else -> InsightSeverity.LOW
                            },
                            amount = dailyRequirement,
                            percentage = progressPercentage,
                            confidenceScore = 0.9f,
                            suggestedAction = "Consider adjusting your savings rate or extending the deadline"
                        )
                        insightDao.insertInsight(insight)
                    }
                    
                    progressPercentage > 0 && progressPercentage < 100 -> {
                        // Progress update
                        val insight = FinancialInsight(
                            insightType = InsightType.GOAL_PROGRESS,
                            title = "Progress update: ${goal.name}",
                            description = "You're ${String.format("%.1f", progressPercentage)}% towards your ${goal.name} goal. ${formatCurrency(remainingAmount)} remaining.",
                            severity = InsightSeverity.INFO,
                            percentage = progressPercentage,
                            amount = remainingAmount,
                            confidenceScore = 1.0f,
                            suggestedAction = "Keep up the great work! You're making steady progress."
                        )
                        insightDao.insertInsight(insight)
                    }
                }
            }
        }
    }
    
    /**
     * Identify seasonal spending patterns
     */
    private suspend fun generateSeasonalPatternInsights() = withContext(Dispatchers.IO) {
        // This would require historical data analysis across different seasons
        // For now, implement basic month-to-month comparison
        val currentMonth = getCurrentMonthRange()
        val lastMonth = getLastMonthRange()
        
        val currentExpenses = expenseDao.getExpensesBetweenDates(currentMonth.first, currentMonth.second)
        val lastMonthExpenses = expenseDao.getExpensesBetweenDates(lastMonth.first, lastMonth.second)
        
        val currentTotal = currentExpenses.sumOf { it.amount }
        val lastMonthTotal = lastMonthExpenses.sumOf { it.amount }
        
        if (lastMonthTotal > 0) {
            val changePercentage = ((currentTotal - lastMonthTotal) / lastMonthTotal * 100)
            
            if (abs(changePercentage) > 20) {
                val insight = FinancialInsight(
                    insightType = InsightType.SEASONAL_PATTERN,
                    title = "Monthly spending pattern detected",
                    description = when {
                        changePercentage > 0 -> "Your spending increased by ${String.format("%.1f", changePercentage)}% compared to last month"
                        else -> "Your spending decreased by ${String.format("%.1f", abs(changePercentage))}% compared to last month"
                    },
                    severity = when {
                        abs(changePercentage) > 50 -> InsightSeverity.HIGH
                        abs(changePercentage) > 30 -> InsightSeverity.MEDIUM
                        else -> InsightSeverity.LOW
                    },
                    amount = abs(currentTotal - lastMonthTotal),
                    percentage = abs(changePercentage),
                    confidenceScore = 0.8f,
                    suggestedAction = when {
                        changePercentage > 20 -> "Review what caused the spending increase"
                        else -> "Great job reducing your expenses!"
                    }
                )
                
                insightDao.insertInsight(insight)
            }
        }
    }
    
    /**
     * Calculate overall financial health score
     */
    private suspend fun generateFinancialHealthInsights() = withContext(Dispatchers.IO) {
        val healthScore = calculateFinancialHealthScore()
        
        val insight = FinancialInsight(
            insightType = InsightType.FINANCIAL_HEALTH,
            title = "Financial Health Score: ${String.format("%.0f", healthScore * 100)}/100",
            description = getHealthScoreDescription(healthScore),
            severity = when {
                healthScore >= 0.8 -> InsightSeverity.INFO
                healthScore >= 0.6 -> InsightSeverity.LOW
                healthScore >= 0.4 -> InsightSeverity.MEDIUM
                else -> InsightSeverity.HIGH
            },
            percentage = healthScore * 100,
            confidenceScore = 0.9f,
            suggestedAction = getHealthScoreRecommendation(healthScore)
        )
        
        insightDao.insertInsight(insight)
    }
    
    // Helper functions
    private fun calculateStatistics(values: List<Double>): Statistics {
        val mean = values.average()
        val variance = values.map { (it - mean).pow(2) }.average()
        val standardDeviation = sqrt(variance)
        
        return Statistics(mean, variance, standardDeviation, values.minOrNull() ?: 0.0, values.maxOrNull() ?: 0.0)
    }
    
    private fun formatCurrency(amount: Double): String {
        return String.format("%.0f kr", amount)
    }
    
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
    
    private fun getLastMonthRange(): Pair<Long, Long> {
        val calendar = java.util.Calendar.getInstance()
        calendar.add(java.util.Calendar.MONTH, -1)
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
    
    private suspend fun getHistoricalAverages(): Map<String, Double> {
        val endDate = System.currentTimeMillis()
        val startDate = endDate - (90 * 24 * 60 * 60 * 1000L) // Last 3 months
        
        val expenses = expenseDao.getExpensesBetweenDates(startDate, endDate)
        return expenses.groupBy { it.category ?: "Uncategorized" }
            .mapValues { (_, expenses) -> expenses.sumOf { it.amount } / 3.0 } // Average per month
    }
    
    private fun identifyRecurringExpenses(expenses: List<Expense>): Map<String, List<Expense>> {
        return expenses.groupBy { it.title }
            .filter { (_, expenseList) -> expenseList.size >= 2 }
    }
    
    private suspend fun getHistoricalMonthlyIncome(): List<Double> {
        // Implementation to get historical monthly income
        val endDate = System.currentTimeMillis()
        val startDate = endDate - (365 * 24 * 60 * 60 * 1000L) // Last year
        
        val incomes = incomeDao.getAllIncomesSnapshot()
        return incomes.filter { it.date >= startDate }
            .groupBy { getMonthKey(it.date) }
            .values
            .map { monthlyIncomes -> monthlyIncomes.sumOf { it.amount } }
    }
    
    private suspend fun getHistoricalMonthlyExpenses(): List<Double> {
        val endDate = System.currentTimeMillis()
        val startDate = endDate - (365 * 24 * 60 * 60 * 1000L) // Last year
        
        val expenses = expenseDao.getExpensesBetweenDates(startDate, endDate)
        return expenses.groupBy { getMonthKey(it.date) }
            .values
            .map { monthlyExpenses -> monthlyExpenses.sumOf { it.amount } }
    }
    
    private fun getMonthKey(timestamp: Long): String {
        val calendar = java.util.Calendar.getInstance()
        calendar.timeInMillis = timestamp
        return "${calendar.get(java.util.Calendar.YEAR)}-${calendar.get(java.util.Calendar.MONTH)}"
    }
    
    private fun calculatePredictionConfidence(incomes: List<Double>, expenses: List<Double>): Float {
        val incomeVariability = calculateVariability(incomes)
        val expenseVariability = calculateVariability(expenses)
        
        // Lower variability = higher confidence
        return (1.0 - (incomeVariability + expenseVariability) / 2.0).toFloat().coerceIn(0.0f, 1.0f)
    }
    
    private fun calculateVariability(values: List<Double>): Double {
        if (values.isEmpty()) return 1.0
        val mean = values.average()
        val standardDeviation = sqrt(values.map { (it - mean).pow(2) }.average())
        return (standardDeviation / mean).coerceIn(0.0, 1.0)
    }
    
    private suspend fun calculateFinancialHealthScore(): Double {
        val currentMonth = getCurrentMonthRange()
        val monthlyIncome = incomeDao.getTotalIncomeByDateRange(currentMonth.first, currentMonth.second) ?: 0.0
        val monthlyExpenses = expenseDao.getTotalExpensesByDateRange(currentMonth.first, currentMonth.second) ?: 0.0
        
        val savingsRate = if (monthlyIncome > 0) {
            (monthlyIncome - monthlyExpenses) / monthlyIncome
        } else 0.0
        
        val activeGoals = goalDao.getActiveGoals()
        var goalProgress = 0.0
        activeGoals.collect { goals ->
            goalProgress = if (goals.isNotEmpty()) {
                goals.map { it.progressPercentage }.average() / 100.0
            } else 0.5
        }
        
        // Weighted health score
        return (savingsRate * 0.4 + goalProgress * 0.3 + 0.3).coerceIn(0.0, 1.0)
    }
    
    private fun getHealthScoreDescription(score: Double): String {
        return when {
            score >= 0.8 -> "Excellent! Your financial health is outstanding."
            score >= 0.6 -> "Good financial health with room for improvement."
            score >= 0.4 -> "Fair financial health. Consider optimizing your budget."
            else -> "Your financial health needs attention. Focus on savings and expense reduction."
        }
    }
    
    private fun getHealthScoreRecommendation(score: Double): String {
        return when {
            score >= 0.8 -> "Keep up the excellent work! Consider advanced investment strategies."
            score >= 0.6 -> "Focus on increasing your savings rate and working towards your goals."
            score >= 0.4 -> "Review your budget and look for areas to reduce expenses."
            else -> "Create a basic budget, track expenses, and start an emergency fund."
        }
    }
    
    private suspend fun cleanupOldInsights() {
        insightDao.deleteExpiredInsights()
        val thirtyDaysAgo = System.currentTimeMillis() - (30 * 24 * 60 * 60 * 1000L)
        insightDao.deleteOldInsights(thirtyDaysAgo)
    }
    
    data class Statistics(
        val mean: Double,
        val variance: Double,
        val standardDeviation: Double,
        val min: Double,
        val max: Double
    )
}