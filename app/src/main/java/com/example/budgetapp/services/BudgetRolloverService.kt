package com.example.budgetapp.services

import android.content.Context
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.Budget
import com.example.budgetapp.database.entities.BudgetPeriod
import com.example.budgetapp.repository.BudgetRepository
import com.example.budgetapp.utils.BudgetRolloverSettings
import kotlinx.coroutines.flow.first
import java.util.*

class BudgetRolloverService(
    private val database: BudgetDatabase,
    private val context: Context
) {
    
    private val budgetRepository = BudgetRepository(database, context)
    private val rolloverSettings = BudgetRolloverSettings(context)
    
    suspend fun processExpiredBudgets(): BudgetRolloverResult {
        val currentTime = System.currentTimeMillis()
        val allBudgets = budgetRepository.getAllBudgets().first()
        
        val expiredBudgets = allBudgets.filter { budget ->
            budget.isActive && budget.endDate < currentTime
        }
        
        if (expiredBudgets.isEmpty()) {
            return BudgetRolloverResult(
                processedBudgets = 0,
                rolledOverBudgets = 0,
                totalRolloverAmount = 0.0,
                newBudgetsCreated = 0,
                details = emptyList()
            )
        }
        
        val rolloverDetails = mutableListOf<BudgetRolloverDetail>()
        var totalRolloverAmount = 0.0
        var newBudgetsCreated = 0
        var rolledOverBudgets = 0
        
        for (expiredBudget in expiredBudgets) {
            val rolloverDetail = processExpiredBudget(expiredBudget, currentTime)
            rolloverDetails.add(rolloverDetail)
            
            if (rolloverDetail.wasRolledOver) {
                rolledOverBudgets++
                totalRolloverAmount += rolloverDetail.rolloverAmount
                
                if (rolloverDetail.newBudgetCreated) {
                    newBudgetsCreated++
                }
            }
            
            // Deactivate the expired budget
            budgetRepository.deactivateBudget(expiredBudget.id)
        }
        
        return BudgetRolloverResult(
            processedBudgets = expiredBudgets.size,
            rolledOverBudgets = rolledOverBudgets,
            totalRolloverAmount = totalRolloverAmount,
            newBudgetsCreated = newBudgetsCreated,
            details = rolloverDetails
        )
    }
    
    private suspend fun processExpiredBudget(
        expiredBudget: Budget,
        currentTime: Long
    ): BudgetRolloverDetail {
        val budgetProgress = budgetRepository.getBudgetProgress(expiredBudget)
        
        // Check if rollover is enabled for this budget type
        if (!shouldRolloverBudget(expiredBudget, budgetProgress)) {
            return BudgetRolloverDetail(
                originalBudget = expiredBudget,
                spentAmount = budgetProgress.spentAmount,
                remainingAmount = budgetProgress.remainingAmount,
                rolloverAmount = 0.0,
                wasRolledOver = false,
                rolloverReason = getRolloverRejectReason(expiredBudget, budgetProgress),
                newBudgetCreated = false,
                newBudgetId = null
            )
        }
        
        // Calculate rollover amount based on settings
        val rolloverAmount = calculateRolloverAmount(expiredBudget, budgetProgress)
        
        if (rolloverAmount <= 0) {
            return BudgetRolloverDetail(
                originalBudget = expiredBudget,
                spentAmount = budgetProgress.spentAmount,
                remainingAmount = budgetProgress.remainingAmount,
                rolloverAmount = 0.0,
                wasRolledOver = false,
                rolloverReason = "Inget belopp att rulla över",
                newBudgetCreated = false,
                newBudgetId = null
            )
        }
        
        // Create new budget with rollover amount
        val newBudget = createRolloverBudget(expiredBudget, rolloverAmount, currentTime)
        val newBudgetId = budgetRepository.insertBudget(newBudget)
        
        return BudgetRolloverDetail(
            originalBudget = expiredBudget,
            spentAmount = budgetProgress.spentAmount,
            remainingAmount = budgetProgress.remainingAmount,
            rolloverAmount = rolloverAmount,
            wasRolledOver = true,
            rolloverReason = "Automatisk rullning av oanvänt belopp",
            newBudgetCreated = true,
            newBudgetId = newBudgetId.toInt()
        )
    }
    
    private fun shouldRolloverBudget(budget: Budget, progress: com.example.budgetapp.repository.BudgetProgress): Boolean {
        // Don't rollover if over budget (negative remaining amount)
        if (progress.isOverBudget) return false
        
        // Check global rollover settings
        if (!rolloverSettings.rolloverEnabled) return false
        
        // Check minimum rollover amount
        if (progress.remainingAmount < rolloverSettings.minimumRolloverAmount) return false
        
        // Check period-specific settings
        return when (budget.period) {
            BudgetPeriod.WEEKLY -> rolloverSettings.rolloverWeeklyBudgets
            BudgetPeriod.MONTHLY -> rolloverSettings.rolloverMonthlyBudgets
            BudgetPeriod.QUARTERLY -> rolloverSettings.rolloverQuarterlyBudgets
            BudgetPeriod.YEARLY -> rolloverSettings.rolloverYearlyBudgets
        }
    }
    
    private fun getRolloverRejectReason(budget: Budget, progress: com.example.budgetapp.repository.BudgetProgress): String {
        return when {
            progress.isOverBudget -> "Budget överskriden - ingen rullning"
            !rolloverSettings.rolloverEnabled -> "Automatisk rullning är inaktiverad"
            progress.remainingAmount < rolloverSettings.minimumRolloverAmount -> 
                "Kvarvarande belopp under minimigräns (${rolloverSettings.minimumRolloverAmount} kr)"
            !shouldRolloverBudget(budget, progress) -> "Rullning inte aktiverad för ${getPeriodName(budget.period)}"
            else -> "Okänd anledning"
        }
    }
    
    private fun calculateRolloverAmount(budget: Budget, progress: com.example.budgetapp.repository.BudgetProgress): Double {
        val remainingAmount = progress.remainingAmount
        
        return when (rolloverSettings.rolloverStrategy) {
            BudgetRolloverStrategy.FULL_AMOUNT -> {
                // Roll over the full remaining amount
                remainingAmount
            }
            BudgetRolloverStrategy.PERCENTAGE -> {
                // Roll over a percentage of the remaining amount
                remainingAmount * (rolloverSettings.rolloverPercentage / 100.0)
            }
            BudgetRolloverStrategy.FIXED_AMOUNT -> {
                // Roll over a fixed amount, up to the remaining amount
                minOf(rolloverSettings.fixedRolloverAmount, remainingAmount)
            }
            BudgetRolloverStrategy.SMART -> {
                // Smart rollover based on historical spending patterns
                // For now, use 50% of remaining amount as fallback
                progress.remainingAmount * 0.5
            }
        }
    }
    
    private suspend fun calculateSmartRollover(budget: Budget, progress: com.example.budgetapp.repository.BudgetProgress): Double {
        // For smart rollover, analyze spending patterns over the last few periods
        try {
            val historicalBudgets = budgetRepository.getAllBudgets().first()
                .filter { it.categoryName == budget.categoryName && it.period == budget.period && !it.isActive }
                .sortedByDescending { it.endDate }
                .take(3) // Last 3 periods
            
            if (historicalBudgets.isEmpty()) {
                // No history available, use 50% of remaining amount
                return progress.remainingAmount * 0.5
            }
            
            // Calculate average utilization rate
            val utilizationRates = historicalBudgets.map { historicalBudget ->
                val historicalProgress = budgetRepository.getBudgetProgress(historicalBudget)
                if (historicalBudget.budgetAmount > 0) {
                    historicalProgress.spentAmount / historicalBudget.budgetAmount
                } else 0.0
            }
            
            val avgUtilization = utilizationRates.average()
            
            return when {
                avgUtilization > 0.9 -> progress.remainingAmount * 0.8 // High utilization, roll over more
                avgUtilization > 0.7 -> progress.remainingAmount * 0.6 // Medium utilization
                avgUtilization > 0.5 -> progress.remainingAmount * 0.4 // Low utilization
                else -> progress.remainingAmount * 0.2 // Very low utilization, roll over less
            }
        } catch (e: Exception) {
            // Fallback to 50% if calculation fails
            return progress.remainingAmount * 0.5
        }
    }
    
    private fun createRolloverBudget(originalBudget: Budget, rolloverAmount: Double, currentTime: Long): Budget {
        val (newStartDate, newEndDate) = budgetRepository.createBudgetPeriod(originalBudget.period, Date(currentTime))
        
        val newBudgetAmount = when (rolloverSettings.rolloverBehavior) {
            BudgetRolloverBehavior.ADD_TO_ORIGINAL -> {
                // Add rollover amount to the original budget amount
                originalBudget.budgetAmount + rolloverAmount
            }
            BudgetRolloverBehavior.REPLACE_WITH_ROLLOVER -> {
                // Use only the rollover amount as the new budget
                rolloverAmount
            }
            BudgetRolloverBehavior.WEIGHTED_AVERAGE -> {
                // Create a weighted average between original budget and rollover
                val weight = rolloverSettings.rolloverWeight
                (originalBudget.budgetAmount * (1 - weight)) + (rolloverAmount * weight)
            }
        }
        
        return Budget(
            name = "${originalBudget.name} (Rullad)",
            categoryName = originalBudget.categoryName,
            budgetAmount = newBudgetAmount,
            period = originalBudget.period,
            startDate = newStartDate,
            endDate = newEndDate,
            isActive = true,
            alertThreshold = originalBudget.alertThreshold,
            createdAt = currentTime,
            updatedAt = currentTime
        )
    }
    
    private fun getPeriodName(period: BudgetPeriod): String {
        return when (period) {
            BudgetPeriod.WEEKLY -> "veckobudgetar"
            BudgetPeriod.MONTHLY -> "månadsbudgetar"
            BudgetPeriod.QUARTERLY -> "kvartalsbudgetar"
            BudgetPeriod.YEARLY -> "årsbudgetar"
        }
    }
    
    suspend fun previewRollover(): BudgetRolloverPreview {
        val currentTime = System.currentTimeMillis()
        val allBudgets = budgetRepository.getAllBudgets().first()
        
        val expiredBudgets = allBudgets.filter { budget ->
            budget.isActive && budget.endDate < currentTime
        }
        
        val previews = expiredBudgets.map { budget ->
            val progress = budgetRepository.getBudgetProgress(budget)
            val wouldRollover = shouldRolloverBudget(budget, progress)
            val rolloverAmount = if (wouldRollover) {
                calculateRolloverAmount(budget, progress)
            } else 0.0
            
            BudgetRolloverPreviewItem(
                budgetName = budget.name,
                categoryName = budget.categoryName,
                originalAmount = budget.budgetAmount,
                spentAmount = progress.spentAmount,
                remainingAmount = progress.remainingAmount,
                rolloverAmount = rolloverAmount,
                wouldRollover = wouldRollover,
                reason = if (wouldRollover) "Kommer att rullas över" else getRolloverRejectReason(budget, progress)
            )
        }
        
        return BudgetRolloverPreview(
            totalExpiredBudgets = expiredBudgets.size,
            budgetsToRollover = previews.count { it.wouldRollover },
            totalRolloverAmount = previews.sumOf { it.rolloverAmount },
            previews = previews
        )
    }
}

data class BudgetRolloverResult(
    val processedBudgets: Int,
    val rolledOverBudgets: Int,
    val totalRolloverAmount: Double,
    val newBudgetsCreated: Int,
    val details: List<BudgetRolloverDetail>
)

data class BudgetRolloverDetail(
    val originalBudget: Budget,
    val spentAmount: Double,
    val remainingAmount: Double,
    val rolloverAmount: Double,
    val wasRolledOver: Boolean,
    val rolloverReason: String,
    val newBudgetCreated: Boolean,
    val newBudgetId: Int?
)

data class BudgetRolloverPreview(
    val totalExpiredBudgets: Int,
    val budgetsToRollover: Int,
    val totalRolloverAmount: Double,
    val previews: List<BudgetRolloverPreviewItem>
)

data class BudgetRolloverPreviewItem(
    val budgetName: String,
    val categoryName: String,
    val originalAmount: Double,
    val spentAmount: Double,
    val remainingAmount: Double,
    val rolloverAmount: Double,
    val wouldRollover: Boolean,
    val reason: String
)

enum class BudgetRolloverStrategy {
    FULL_AMOUNT,    // Roll over all remaining amount
    PERCENTAGE,     // Roll over a percentage of remaining amount
    FIXED_AMOUNT,   // Roll over a fixed amount
    SMART          // Smart rollover based on historical patterns
}

enum class BudgetRolloverBehavior {
    ADD_TO_ORIGINAL,      // Add rollover to original budget amount
    REPLACE_WITH_ROLLOVER, // Use only rollover amount as new budget
    WEIGHTED_AVERAGE      // Weighted average between original and rollover
}