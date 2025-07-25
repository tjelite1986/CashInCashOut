package com.example.budgetapp.services

import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.Budget
import com.example.budgetapp.database.entities.BudgetPeriod
import com.example.budgetapp.repository.BudgetProgress
import com.example.budgetapp.repository.BudgetRepository
import kotlinx.coroutines.flow.first
import java.util.*

class BudgetAnalyticsService(private val database: BudgetDatabase) {
    
    private val budgetRepository = BudgetRepository(database)
    private val expenseDao = database.expenseDao()
    
    suspend fun generateBudgetAnalytics(): BudgetAnalytics {
        val activeBudgets = budgetRepository.getAllActiveBudgets().first()
        val progressList = budgetRepository.getBudgetProgressList(activeBudgets)
        
        return BudgetAnalytics(
            totalBudgets = activeBudgets.size,
            totalBudgetAmount = activeBudgets.sumOf { it.budgetAmount },
            totalSpentAmount = progressList.sumOf { it.spentAmount },
            totalRemainingAmount = progressList.sumOf { it.remainingAmount },
            averageSpendingPercentage = if (progressList.isNotEmpty()) {
                progressList.map { it.percentage }.average()
            } else 0.0,
            budgetsOverLimit = progressList.count { it.isOverBudget },
            budgetsNearLimit = progressList.count { it.isNearLimit && !it.isOverBudget },
            budgetsOnTrack = progressList.count { !it.isNearLimit && !it.isOverBudget },
            categoryBreakdown = generateCategoryBreakdown(progressList),
            spendingTrend = generateSpendingTrend(activeBudgets),
            budgetEfficiency = calculateBudgetEfficiency(progressList),
            periodAnalysis = generatePeriodAnalysis(activeBudgets)
        )
    }
    
    private fun generateCategoryBreakdown(progressList: List<BudgetProgress>): List<CategorySpending> {
        return progressList.map { progress ->
            CategorySpending(
                categoryName = progress.budget.categoryName,
                budgetAmount = progress.budget.budgetAmount,
                spentAmount = progress.spentAmount,
                remainingAmount = progress.remainingAmount,
                percentage = progress.percentage,
                status = when {
                    progress.isOverBudget -> BudgetStatus.OVER_BUDGET
                    progress.isNearLimit -> BudgetStatus.NEAR_LIMIT
                    else -> BudgetStatus.ON_TRACK
                }
            )
        }.sortedByDescending { it.spentAmount }
    }
    
    private suspend fun generateSpendingTrend(budgets: List<Budget>): List<SpendingTrendPoint> {
        if (budgets.isEmpty()) return emptyList()
        
        val calendar = Calendar.getInstance()
        val endTime = calendar.timeInMillis
        
        // Generate trend for last 30 days
        val trendPoints = mutableListOf<SpendingTrendPoint>()
        
        for (i in 29 downTo 0) {
            calendar.timeInMillis = endTime
            calendar.add(Calendar.DAY_OF_YEAR, -i)
            
            val startOfDay = calendar.apply {
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }.timeInMillis
            
            val endOfDay = calendar.apply {
                set(Calendar.HOUR_OF_DAY, 23)
                set(Calendar.MINUTE, 59)
                set(Calendar.SECOND, 59)
                set(Calendar.MILLISECOND, 999)
            }.timeInMillis
            
            val dayExpenses = expenseDao.getExpensesBetweenDates(startOfDay, endOfDay)
            val totalSpent = dayExpenses.sumOf { it.amount }
            
            trendPoints.add(
                SpendingTrendPoint(
                    date = startOfDay,
                    amount = totalSpent,
                    dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
                )
            )
        }
        
        return trendPoints
    }
    
    private fun calculateBudgetEfficiency(progressList: List<BudgetProgress>): BudgetEfficiency {
        if (progressList.isEmpty()) {
            return BudgetEfficiency(0.0, 0.0, 0.0, "Inga budgetar att analysera")
        }
        
        val totalBudget = progressList.sumOf { it.budget.budgetAmount }
        val totalSpent = progressList.sumOf { it.spentAmount }
        val utilizationRate = if (totalBudget > 0) (totalSpent / totalBudget) * 100 else 0.0
        
        val accuracy = progressList.count { progress ->
            progress.percentage in 70.0..100.0 && !progress.isOverBudget
        }.toDouble() / progressList.size * 100
        
        val efficiency = when {
            utilizationRate > 100 -> 0.0 // Over budget is inefficient
            utilizationRate < 50 -> utilizationRate * 0.8 // Under-utilization penalty
            else -> utilizationRate
        }
        
        val recommendation = when {
            utilizationRate > 120 -> "Överskrider budgetar kraftigt - överväg att öka budgetarna eller minska utgifterna"
            utilizationRate > 100 -> "Överskrider budgetar - justera antingen budgetar eller utgifter"
            utilizationRate > 90 -> "Mycket bra budgethantering - ligger nära planerat"
            utilizationRate > 70 -> "Bra budgethantering med utrymme för justering"
            utilizationRate > 50 -> "Konservativ budgethantering - kan öka flexibiliteten"
            else -> "Mycket konservativa budgetar - överväg att justera nedåt"
        }
        
        return BudgetEfficiency(utilizationRate, accuracy, efficiency, recommendation)
    }
    
    private fun generatePeriodAnalysis(budgets: List<Budget>): PeriodAnalysis {
        val periodCounts = budgets.groupingBy { it.period }.eachCount()
        val mostCommonPeriod = periodCounts.maxByOrNull { it.value }?.key ?: BudgetPeriod.MONTHLY
        
        val averageBudgetByPeriod = budgets.groupBy { it.period }
            .mapValues { (_, budgets) -> budgets.map { it.budgetAmount }.average() }
        
        return PeriodAnalysis(
            periodDistribution = periodCounts,
            mostCommonPeriod = mostCommonPeriod,
            averageBudgetByPeriod = averageBudgetByPeriod,
            recommendation = generatePeriodRecommendation(periodCounts, mostCommonPeriod)
        )
    }
    
    private fun generatePeriodRecommendation(
        periodCounts: Map<BudgetPeriod, Int>,
        mostCommon: BudgetPeriod
    ): String {
        return when {
            periodCounts.size == 1 -> "Du använder enbart ${getPeriodName(mostCommon)} budgetar. Överväg att testa andra perioder för bättre kontroll."
            mostCommon == BudgetPeriod.MONTHLY -> "Månadsbudgetar är ett bra val för de flesta kategorier. Bra balans mellan kontroll och flexibilitet."
            mostCommon == BudgetPeriod.WEEKLY -> "Veckobudgetar ger mycket god kontroll men kan vara krävande att hantera. Bra för snabböränderliga utgifter."
            mostCommon == BudgetPeriod.YEARLY -> "Årsbudgetar är bra för stora, sällan förekommande utgifter men ger mindre daglig kontroll."
            else -> "Du har en bra mix av budgetperioder som ger flexibilitet för olika utgiftstyper."
        }
    }
    
    private fun getPeriodName(period: BudgetPeriod): String {
        return when (period) {
            BudgetPeriod.WEEKLY -> "vecko"
            BudgetPeriod.MONTHLY -> "månads"
            BudgetPeriod.QUARTERLY -> "kvartals"
            BudgetPeriod.YEARLY -> "års"
        }
    }
    
    suspend fun getBudgetPerformanceHistory(days: Int = 30): List<BudgetPerformancePoint> {
        val budgets = budgetRepository.getAllActiveBudgets().first()
        if (budgets.isEmpty()) return emptyList()
        
        val calendar = Calendar.getInstance()
        val endTime = calendar.timeInMillis
        val performancePoints = mutableListOf<BudgetPerformancePoint>()
        
        for (i in days - 1 downTo 0) {
            calendar.timeInMillis = endTime
            calendar.add(Calendar.DAY_OF_YEAR, -i)
            
            val dayStart = calendar.apply {
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }.timeInMillis
            
            val dayEnd = calendar.apply {
                set(Calendar.HOUR_OF_DAY, 23)
                set(Calendar.MINUTE, 59)
                set(Calendar.SECOND, 59)
                set(Calendar.MILLISECOND, 999)
            }.timeInMillis
            
            // Calculate cumulative spending up to this day for each budget
            val progressList = budgets.map { budget ->
                val expenses = expenseDao.getExpensesBetweenDates(budget.startDate, dayEnd)
                    .filter { it.category == budget.categoryName }
                val spentAmount = expenses.sumOf { it.amount }
                
                val percentage = if (budget.budgetAmount > 0) {
                    (spentAmount / budget.budgetAmount * 100).coerceAtMost(150.0) // Cap at 150% for visualization
                } else 0.0
                
                BudgetProgress(
                    budget = budget,
                    spentAmount = spentAmount,
                    remainingAmount = (budget.budgetAmount - spentAmount).coerceAtLeast(0.0),
                    percentage = percentage,
                    isOverBudget = spentAmount > budget.budgetAmount,
                    isNearLimit = percentage >= budget.alertThreshold
                )
            }
            
            val avgPerformance = if (progressList.isNotEmpty()) {
                progressList.map { it.percentage }.average()
            } else 0.0
            
            val budgetsOverLimit = progressList.count { it.isOverBudget }
            val budgetsOnTrack = progressList.count { !it.isNearLimit && !it.isOverBudget }
            
            performancePoints.add(
                BudgetPerformancePoint(
                    date = dayStart,
                    averagePercentage = avgPerformance,
                    budgetsOverLimit = budgetsOverLimit,
                    budgetsOnTrack = budgetsOnTrack,
                    totalBudgets = budgets.size
                )
            )
        }
        
        return performancePoints
    }
}

data class BudgetAnalytics(
    val totalBudgets: Int,
    val totalBudgetAmount: Double,
    val totalSpentAmount: Double,
    val totalRemainingAmount: Double,
    val averageSpendingPercentage: Double,
    val budgetsOverLimit: Int,
    val budgetsNearLimit: Int,
    val budgetsOnTrack: Int,
    val categoryBreakdown: List<CategorySpending>,
    val spendingTrend: List<SpendingTrendPoint>,
    val budgetEfficiency: BudgetEfficiency,
    val periodAnalysis: PeriodAnalysis
)

data class CategorySpending(
    val categoryName: String,
    val budgetAmount: Double,
    val spentAmount: Double,
    val remainingAmount: Double,
    val percentage: Double,
    val status: BudgetStatus
)

data class SpendingTrendPoint(
    val date: Long,
    val amount: Double,
    val dayOfWeek: Int
)

data class BudgetEfficiency(
    val utilizationRate: Double,
    val accuracy: Double,
    val efficiency: Double,
    val recommendation: String
)

data class PeriodAnalysis(
    val periodDistribution: Map<BudgetPeriod, Int>,
    val mostCommonPeriod: BudgetPeriod,
    val averageBudgetByPeriod: Map<BudgetPeriod, Double>,
    val recommendation: String
)

data class BudgetPerformancePoint(
    val date: Long,
    val averagePercentage: Double,
    val budgetsOverLimit: Int,
    val budgetsOnTrack: Int,
    val totalBudgets: Int
)

enum class BudgetStatus {
    ON_TRACK,
    NEAR_LIMIT,
    OVER_BUDGET
}