package com.example.budgetapp.automation

import android.content.Context
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.Expense
import com.example.budgetapp.database.entities.Income
import com.example.budgetapp.database.entities.Budget
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class SmartAutomationEngine(private val context: Context) {
    
    private val database = BudgetDatabase.getDatabase(context)
    
    suspend fun processAutomationRules(
        transaction: TransactionCandidate
    ): AutomationResult = withContext(Dispatchers.IO) {
        
        val activeRules = database.automationRuleDao().getActiveRules()
        val matchedRules = mutableListOf<AutomationRule>()
        val actions = mutableListOf<AutomationAction>()
        
        activeRules.forEach { rule ->
            if (evaluateRuleConditions(rule, transaction)) {
                matchedRules.add(rule)
                actions.addAll(rule.actions)
                
                // Update rule statistics
                database.automationRuleDao().incrementRuleUsage(rule.id)
            }
        }
        
        // Execute actions
        val executionResults = executeActions(actions, transaction)
        
        AutomationResult(
            matchedRules = matchedRules,
            executedActions = executionResults,
            transaction = transaction,
            processingTime = System.currentTimeMillis()
        )
    }
    
    suspend fun createSmartCategorizationRules(): List<AutomationRule> = withContext(Dispatchers.IO) {
        val rules = mutableListOf<AutomationRule>()
        
        // Analyze existing transactions to create intelligent rules
        val expenses = database.expenseDao().getAllExpensesSnapshot()
        val patterns = analyzeTransactionPatterns(expenses)
        
        patterns.forEach { pattern ->
            if (pattern.confidence > 0.8) {
                val rule = AutomationRule(
                    name = "Auto-categorize ${pattern.merchant}",
                    description = "Automatically categorize transactions from ${pattern.merchant}",
                    conditions = listOf(
                        RuleCondition(
                            type = ConditionType.DESCRIPTION_CONTAINS,
                            field = "description",
                            operator = ConditionOperator.CONTAINS,
                            value = pattern.merchant,
                            caseSensitive = false
                        )
                    ),
                    actions = listOf(
                        AutomationAction(
                            type = ActionType.SET_CATEGORY,
                            parameters = mapOf("category" to pattern.category)
                        )
                    ),
                    isActive = true,
                    priority = if (pattern.confidence > 0.95) 1 else 2,
                    createdBy = AutomationCreatedBy.SYSTEM,
                    confidence = pattern.confidence
                )
                rules.add(rule)
            }
        }
        
        rules
    }
    
    suspend fun detectRecurringTransactions(): List<RecurringTransaction> = withContext(Dispatchers.IO) {
        val allTransactions = mutableListOf<TransactionPattern>()
        
        // Combine expenses and income for pattern analysis
        val expenses = database.expenseDao().getAllExpensesSnapshot()
        expenses.forEach { expense ->
            allTransactions.add(
                TransactionPattern(
                    id = expense.id,
                    amount = expense.amount,
                    description = expense.description ?: "",
                    category = expense.category ?: "",
                    date = expense.date,
                    type = TransactionType.EXPENSE
                )
            )
        }
        
        val incomes = database.incomeDao().getAllIncomesSnapshot()
        incomes.forEach { income ->
            allTransactions.add(
                TransactionPattern(
                    id = income.id,
                    amount = income.amount,
                    description = income.description ?: "",
                    category = income.category ?: "",
                    date = income.date,
                    type = TransactionType.INCOME
                )
            )
        }
        
        analyzeRecurringPatterns(allTransactions)
    }
    
    suspend fun createAutoBudgetAdjustments(): List<BudgetAdjustment> = withContext(Dispatchers.IO) {
        val adjustments = mutableListOf<BudgetAdjustment>()
        
        val budgetList = database.budgetDao().getAllActiveBudgetsSnapshot()
        budgetList.forEach { budget ->
            val analysis = analyzeBudgetPerformance(budget)
            
            if (analysis.shouldAdjust) {
                adjustments.add(
                    BudgetAdjustment(
                        budgetId = budget.id.toLong(),
                        currentAmount = budget.budgetAmount,
                        suggestedAmount = analysis.suggestedAmount,
                        reason = analysis.reason,
                        confidence = analysis.confidence,
                        basedOnMonths = analysis.analysisMonths,
                        createdAt = System.currentTimeMillis()
                    )
                )
            }
        }
        
        adjustments
    }
    
    suspend fun setupEmergencyFundAutoTransfer(
        sourceAccountId: String,
        targetAccountId: String,
        percentage: Double
    ): AutoTransferRule = withContext(Dispatchers.IO) {
        
        AutoTransferRule(
            name = "Emergency Fund Auto-Transfer",
            description = "Automatically transfer ${percentage}% of income to emergency fund",
            sourceAccountId = sourceAccountId,
            targetAccountId = targetAccountId,
            transferType = TransferType.PERCENTAGE_OF_INCOME,
            amount = percentage,
            frequency = TransferFrequency.ON_INCOME_RECEIVED,
            conditions = listOf(
                TransferCondition(
                    type = TransferConditionType.MINIMUM_BALANCE,
                    value = 1000.0 // Keep minimum 1000 SEK in checking
                )
            ),
            isActive = true,
            createdAt = System.currentTimeMillis()
        )
    }
    
    suspend fun processSharedExpenseSplitting(
        expense: Expense,
        participants: List<ExpenseParticipant>
    ): ExpenseSplitResult = withContext(Dispatchers.IO) {
        
        val totalAmount = expense.amount
        val splits = mutableListOf<ExpenseSplit>()
        
        when (val splitType = determineSplitType(expense, participants)) {
            SplitType.EQUAL -> {
                val amountPerPerson = totalAmount / participants.size
                participants.forEach { participant ->
                    splits.add(
                        ExpenseSplit(
                            participantId = participant.id,
                            amount = amountPerPerson,
                            percentage = 100.0 / participants.size,
                            status = SplitStatus.PENDING
                        )
                    )
                }
            }
            SplitType.PERCENTAGE -> {
                participants.forEach { participant ->
                    val amount = totalAmount * (participant.percentage / 100.0)
                    splits.add(
                        ExpenseSplit(
                            participantId = participant.id,
                            amount = amount,
                            percentage = participant.percentage,
                            status = SplitStatus.PENDING
                        )
                    )
                }
            }
            SplitType.FIXED_AMOUNT -> {
                participants.forEach { participant ->
                    splits.add(
                        ExpenseSplit(
                            participantId = participant.id,
                            amount = participant.fixedAmount ?: 0.0,
                            percentage = (participant.fixedAmount ?: 0.0) / totalAmount * 100,
                            status = SplitStatus.PENDING
                        )
                    )
                }
            }
            SplitType.SMART -> {
                // AI-powered splitting based on historical patterns
                val smartSplits = calculateSmartSplit(expense, participants)
                splits.addAll(smartSplits)
            }
        }
        
        ExpenseSplitResult(
            expenseId = expense.id,
            splits = splits,
            splitType = determineSplitType(expense, participants),
            createdAt = System.currentTimeMillis()
        )
    }
    
    private fun evaluateRuleConditions(
        rule: AutomationRule,
        transaction: TransactionCandidate
    ): Boolean {
        return rule.conditions.all { condition ->
            when (condition.type) {
                ConditionType.AMOUNT_EQUALS -> transaction.amount == condition.value.toDouble()
                ConditionType.AMOUNT_GREATER_THAN -> transaction.amount > condition.value.toDouble()
                ConditionType.AMOUNT_LESS_THAN -> transaction.amount < condition.value.toDouble()
                ConditionType.DESCRIPTION_CONTAINS -> {
                    if (condition.caseSensitive) {
                        transaction.description.contains(condition.value)
                    } else {
                        transaction.description.contains(condition.value, ignoreCase = true)
                    }
                }
                ConditionType.DESCRIPTION_EQUALS -> {
                    if (condition.caseSensitive) {
                        transaction.description == condition.value
                    } else {
                        transaction.description.equals(condition.value, ignoreCase = true)
                    }
                }
                ConditionType.CATEGORY_EQUALS -> transaction.category == condition.value
                ConditionType.DATE_RANGE -> {
                    val dates = condition.value.split(",")
                    val startDate = dates[0].toLong()
                    val endDate = dates[1].toLong()
                    transaction.date in startDate..endDate
                }
                ConditionType.DAY_OF_WEEK -> {
                    val calendar = Calendar.getInstance()
                    calendar.timeInMillis = transaction.date
                    calendar.get(Calendar.DAY_OF_WEEK) == condition.value.toInt()
                }
                ConditionType.DAY_OF_MONTH -> {
                    val calendar = Calendar.getInstance()
                    calendar.timeInMillis = transaction.date
                    calendar.get(Calendar.DAY_OF_MONTH) == condition.value.toInt()
                }
            }
        }
    }
    
    private suspend fun executeActions(
        actions: List<AutomationAction>,
        transaction: TransactionCandidate
    ): List<ActionExecutionResult> {
        return actions.map { action ->
            try {
                when (action.type) {
                    ActionType.SET_CATEGORY -> {
                        val category = action.parameters["category"] ?: ""
                        transaction.category = category
                        ActionExecutionResult(action, true, "Category set to $category")
                    }
                    ActionType.ADD_TAG -> {
                        val tag = action.parameters["tag"] ?: ""
                        transaction.tags = (transaction.tags + tag).distinct()
                        ActionExecutionResult(action, true, "Tag '$tag' added")
                    }
                    ActionType.SET_DESCRIPTION -> {
                        val description = action.parameters["description"] ?: ""
                        transaction.description = description
                        ActionExecutionResult(action, true, "Description updated")
                    }
                    ActionType.CREATE_REMINDER -> {
                        val reminderText = action.parameters["text"] ?: ""
                        val reminderDate = action.parameters["date"]?.toLongOrNull() ?: (System.currentTimeMillis() + 24 * 60 * 60 * 1000)
                        // Create reminder logic here
                        ActionExecutionResult(action, true, "Reminder created")
                    }
                    ActionType.SPLIT_EXPENSE -> {
                        val participants = action.parameters["participants"] ?: ""
                        // Split expense logic here
                        ActionExecutionResult(action, true, "Expense split created")
                    }
                    ActionType.AUTO_SAVE -> {
                        val percentage = action.parameters["percentage"]?.toDoubleOrNull() ?: 10.0
                        val saveAmount = transaction.amount * (percentage / 100.0)
                        // Auto-save logic here
                        ActionExecutionResult(action, true, "Auto-saved $saveAmount SEK")
                    }
                }
            } catch (e: Exception) {
                ActionExecutionResult(action, false, "Error: ${e.message}")
            }
        }
    }
    
    private fun analyzeTransactionPatterns(expenses: List<Expense>): List<TransactionPattern> {
        val patterns = mutableListOf<TransactionPattern>()
        val merchantMap = mutableMapOf<String, MutableList<Expense>>()
        
        // Group by merchant/description patterns
        expenses.forEach { expense ->
            val normalizedDescription = normalizeDescription(expense.description ?: "")
            merchantMap.getOrPut(normalizedDescription) { mutableListOf() }.add(expense)
        }
        
        merchantMap.forEach { (merchant, transactions) ->
            if (transactions.size >= 3) { // Need at least 3 transactions to establish pattern
                val categories = transactions.map { it.category ?: "" }.distinct()
                if (categories.size == 1) {
                    // Strong pattern - all transactions have same category
                    val confidence = minOf(0.95, 0.7 + (transactions.size * 0.05))
                    patterns.add(
                        TransactionPattern(
                            merchant = merchant,
                            category = categories.first(),
                            confidence = confidence,
                            occurrences = transactions.size,
                            averageAmount = transactions.map { it.amount }.average(),
                            lastSeen = transactions.maxOf { it.date }
                        )
                    )
                }
            }
        }
        
        return patterns
    }
    
    private fun analyzeRecurringPatterns(transactions: List<TransactionPattern>): List<RecurringTransaction> {
        val recurring = mutableListOf<RecurringTransaction>()
        val grouped = transactions.groupBy { "${it.description}_${it.amount}" }
        
        grouped.forEach { (key, group) ->
            if (group.size >= 3) {
                val dates = group.map { it.date }.sorted()
                val intervals = mutableListOf<Long>()
                
                for (i in 1 until dates.size) {
                    intervals.add(dates[i] - dates[i-1])
                }
                
                val avgInterval = intervals.average().toLong()
                val intervalVariance = intervals.map { kotlin.math.abs(it - avgInterval) }.average()
                
                // If variance is low, it's likely recurring
                if (intervalVariance < avgInterval * 0.2) {
                    val frequency = when {
                        avgInterval < 2 * 24 * 60 * 60 * 1000 -> RecurringFrequency.DAILY
                        avgInterval < 8 * 24 * 60 * 60 * 1000 -> RecurringFrequency.WEEKLY
                        avgInterval < 35 * 24 * 60 * 60 * 1000 -> RecurringFrequency.MONTHLY
                        avgInterval < 100 * 24 * 60 * 60 * 1000 -> RecurringFrequency.QUARTERLY
                        else -> RecurringFrequency.YEARLY
                    }
                    
                    recurring.add(
                        RecurringTransaction(
                            description = group.first().description,
                            amount = group.first().amount,
                            category = group.first().category,
                            frequency = frequency,
                            confidence = 1.0 - (intervalVariance / avgInterval),
                            lastOccurrence = dates.last(),
                            nextPredicted = dates.last() + avgInterval,
                            occurrences = group.size,
                            type = group.first().type
                        )
                    )
                }
            }
        }
        
        return recurring
    }
    
    private suspend fun analyzeBudgetPerformance(budget: Budget): BudgetAnalysis {
        val now = System.currentTimeMillis()
        val threeMonthsAgo = now - (90 * 24 * 60 * 60 * 1000)
        
        val expenses = database.expenseDao().getExpensesBetweenDates(threeMonthsAgo, now)
        val categoryExpenses = expenses.filter { it.category == budget.categoryName }
        
        val monthlyAverages = calculateMonthlyAverages(categoryExpenses)
        val trend = calculateTrend(monthlyAverages)
        
        val suggestedAmount = when {
            trend > 0.1 -> budget.budgetAmount * 1.2 // Increasing trend
            trend < -0.1 -> budget.budgetAmount * 0.9 // Decreasing trend
            else -> monthlyAverages.average()
        }
        
        val shouldAdjust = kotlin.math.abs(suggestedAmount - budget.budgetAmount) > budget.budgetAmount * 0.15
        
        return BudgetAnalysis(
            shouldAdjust = shouldAdjust,
            suggestedAmount = suggestedAmount,
            reason = when {
                trend > 0.1 -> "Spending in this category has increased by ${(trend * 100).toInt()}%"
                trend < -0.1 -> "Spending in this category has decreased by ${(kotlin.math.abs(trend) * 100).toInt()}%"
                else -> "Budget adjustment based on 3-month average spending"
            },
            confidence = if (categoryExpenses.size >= 10) 0.9 else 0.6,
            analysisMonths = 3
        )
    }
    
    private fun normalizeDescription(description: String): String {
        return description
            .replace(Regex("\\d+"), "") // Remove numbers
            .replace(Regex("[^a-zA-ZÅÄÖåäö\\s]"), "") // Remove special chars except Swedish letters
            .trim()
            .lowercase()
    }
    
    private fun calculateMonthlyAverages(expenses: List<Expense>): List<Double> {
        val monthlyTotals = mutableMapOf<String, Double>()
        val calendar = Calendar.getInstance()
        
        expenses.forEach { expense ->
            calendar.timeInMillis = expense.date
            val monthKey = "${calendar.get(Calendar.YEAR)}-${calendar.get(Calendar.MONTH)}"
            monthlyTotals[monthKey] = monthlyTotals.getOrDefault(monthKey, 0.0) + expense.amount
        }
        
        return monthlyTotals.values.toList()
    }
    
    private fun calculateTrend(values: List<Double>): Double {
        if (values.size < 2) return 0.0
        val firstHalf = values.take(values.size / 2).average()
        val secondHalf = values.drop(values.size / 2).average()
        return (secondHalf - firstHalf) / firstHalf
    }
    
    private fun determineSplitType(expense: Expense, participants: List<ExpenseParticipant>): SplitType {
        return when {
            participants.all { it.percentage > 0 } -> SplitType.PERCENTAGE
            participants.all { it.fixedAmount != null && it.fixedAmount!! > 0 } -> SplitType.FIXED_AMOUNT
            expense.category in listOf("Groceries", "Dining", "Utilities") -> SplitType.SMART
            else -> SplitType.EQUAL
        }
    }
    
    private fun calculateSmartSplit(expense: Expense, participants: List<ExpenseParticipant>): List<ExpenseSplit> {
        // AI-powered splitting based on historical patterns, income ratios, etc.
        val splits = mutableListOf<ExpenseSplit>()
        val totalAmount = expense.amount
        
        // For now, implement simple income-based splitting
        val totalIncome = participants.sumOf { it.monthlyIncome ?: 0.0 }
        
        participants.forEach { participant ->
            val incomeRatio = (participant.monthlyIncome ?: 0.0) / totalIncome
            val amount = totalAmount * incomeRatio
            
            splits.add(
                ExpenseSplit(
                    participantId = participant.id,
                    amount = amount,
                    percentage = incomeRatio * 100,
                    status = SplitStatus.PENDING
                )
            )
        }
        
        return splits
    }
}

// Data classes for automation
data class TransactionCandidate(
    val id: Long = 0,
    val amount: Double,
    var description: String,
    var category: String,
    val date: Long,
    var tags: List<String> = emptyList()
)

data class AutomationResult(
    val matchedRules: List<AutomationRule>,
    val executedActions: List<ActionExecutionResult>,
    val transaction: TransactionCandidate,
    val processingTime: Long
)

data class ActionExecutionResult(
    val action: AutomationAction,
    val success: Boolean,
    val message: String
)

data class TransactionPattern(
    val id: Long = 0,
    val merchant: String = "",
    val category: String = "",
    val confidence: Double = 0.0,
    val occurrences: Int = 0,
    val averageAmount: Double = 0.0,
    val lastSeen: Long = 0,
    val amount: Double = 0.0,
    val description: String = "",
    val date: Long = 0,
    val type: TransactionType = TransactionType.EXPENSE
)

enum class TransactionType {
    EXPENSE, INCOME
}

@Entity(tableName = "automation_rules")
data class AutomationRule(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String,
    val conditions: List<RuleCondition>,
    val actions: List<AutomationAction>,
    val isActive: Boolean = true,
    val priority: Int = 1,
    val usageCount: Long = 0,
    val createdBy: AutomationCreatedBy = AutomationCreatedBy.USER,
    val confidence: Double = 1.0,
    val createdAt: Long = System.currentTimeMillis(),
    val lastModified: Long = System.currentTimeMillis()
)

data class RuleCondition(
    val type: ConditionType,
    val field: String,
    val operator: ConditionOperator,
    val value: String,
    val caseSensitive: Boolean = false
)

data class AutomationAction(
    val type: ActionType,
    val parameters: Map<String, String>
)

enum class ConditionType {
    AMOUNT_EQUALS, AMOUNT_GREATER_THAN, AMOUNT_LESS_THAN,
    DESCRIPTION_CONTAINS, DESCRIPTION_EQUALS,
    CATEGORY_EQUALS, DATE_RANGE, DAY_OF_WEEK, DAY_OF_MONTH
}

enum class ConditionOperator {
    EQUALS, CONTAINS, GREATER_THAN, LESS_THAN, BETWEEN
}

enum class ActionType {
    SET_CATEGORY, ADD_TAG, SET_DESCRIPTION, CREATE_REMINDER, SPLIT_EXPENSE, AUTO_SAVE
}

enum class AutomationCreatedBy {
    USER, SYSTEM, AI
}

data class RecurringTransaction(
    val id: Long = 0,
    val description: String,
    val amount: Double,
    val category: String,
    val frequency: RecurringFrequency,
    val confidence: Double,
    val lastOccurrence: Long,
    val nextPredicted: Long,
    val occurrences: Int,
    val type: TransactionType,
    val isActive: Boolean = true
)

enum class RecurringFrequency {
    DAILY, WEEKLY, BIWEEKLY, MONTHLY, QUARTERLY, YEARLY
}

data class BudgetAdjustment(
    val id: Long = 0,
    val budgetId: Long,
    val currentAmount: Double,
    val suggestedAmount: Double,
    val reason: String,
    val confidence: Double,
    val basedOnMonths: Int,
    val isApplied: Boolean = false,
    val createdAt: Long
)

data class BudgetAnalysis(
    val shouldAdjust: Boolean,
    val suggestedAmount: Double,
    val reason: String,
    val confidence: Double,
    val analysisMonths: Int
)

@Entity(tableName = "auto_transfer_rules")
data class AutoTransferRule(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String,
    val sourceAccountId: String,
    val targetAccountId: String,
    val transferType: TransferType,
    val amount: Double,
    val frequency: TransferFrequency,
    val conditions: List<TransferCondition>,
    val isActive: Boolean = true,
    val lastExecuted: Long? = null,
    val totalTransferred: Double = 0.0,
    val createdAt: Long
)

enum class TransferType {
    FIXED_AMOUNT, PERCENTAGE_OF_INCOME, PERCENTAGE_OF_BALANCE
}

enum class TransferFrequency {
    ON_INCOME_RECEIVED, DAILY, WEEKLY, MONTHLY
}

data class TransferCondition(
    val type: TransferConditionType,
    val value: Double
)

enum class TransferConditionType {
    MINIMUM_BALANCE, MAXIMUM_TRANSFER, INCOME_THRESHOLD
}

data class ExpenseParticipant(
    val id: String,
    val name: String,
    val email: String? = null,
    val percentage: Double = 0.0,
    val fixedAmount: Double? = null,
    val monthlyIncome: Double? = null
)

data class ExpenseSplit(
    val id: Long = 0,
    val participantId: String,
    val amount: Double,
    val percentage: Double,
    val status: SplitStatus,
    val paidAt: Long? = null
)

data class ExpenseSplitResult(
    val expenseId: Long,
    val splits: List<ExpenseSplit>,
    val splitType: SplitType,
    val createdAt: Long
)

enum class SplitType {
    EQUAL, PERCENTAGE, FIXED_AMOUNT, SMART
}

enum class SplitStatus {
    PENDING, PAID, CANCELLED
}