package com.example.budgetapp.automation.ml

import android.content.Context
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.Expense
import com.example.budgetapp.database.entities.Income
import com.example.budgetapp.database.entities.Budget
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.*
import java.util.*

class MachineLearningInsightEngine(private val context: Context) {
    
    private val database = BudgetDatabase.getDatabase(context)
    
    suspend fun generateSmartInsights(): List<SmartInsight> = withContext(Dispatchers.IO) {
        val insights = mutableListOf<SmartInsight>()
        
        try {
            // Spending pattern insights
            insights.addAll(analyzeSpendingPatterns())
        } catch (e: Exception) {
            android.util.Log.w("MLInsightEngine", "Error analyzing spending patterns: ${e.message}")
        }
        
        try {
            // Income insights
            insights.addAll(analyzeIncomePatterns())
        } catch (e: Exception) {
            android.util.Log.w("MLInsightEngine", "Error analyzing income patterns: ${e.message}")
        }
        
        try {
            // Budget optimization insights
            insights.addAll(analyzeBudgetOptimization())
        } catch (e: Exception) {
            android.util.Log.w("MLInsightEngine", "Error analyzing budget optimization: ${e.message}")
        }
        
        try {
            // Saving opportunities
            insights.addAll(identifySavingOpportunities())
        } catch (e: Exception) {
            android.util.Log.w("MLInsightEngine", "Error identifying saving opportunities: ${e.message}")
        }
        
        try {
            // Financial health insights
            insights.addAll(assessFinancialHealth())
        } catch (e: Exception) {
            android.util.Log.w("MLInsightEngine", "Error assessing financial health: ${e.message}")
        }
        
        try {
            // Anomaly detection
            insights.addAll(detectSpendingAnomalies())
        } catch (e: Exception) {
            android.util.Log.w("MLInsightEngine", "Error detecting anomalies: ${e.message}")
        }
        
        try {
            // Seasonal insights
            insights.addAll(generateSeasonalInsights())
        } catch (e: Exception) {
            android.util.Log.w("MLInsightEngine", "Error generating seasonal insights: ${e.message}")
        }
        
        try {
            // Goal achievement insights
            insights.addAll(analyzeGoalProgress())
        } catch (e: Exception) {
            android.util.Log.w("MLInsightEngine", "Error analyzing goal progress: ${e.message}")
        }
        
        // Sort by priority and confidence
        insights.sortedWith(compareByDescending<SmartInsight> { it.priority.value }.thenByDescending { it.confidence })
    }
    
    suspend fun trainCategorizationModel(): CategorizationModel = withContext(Dispatchers.IO) {
        val expenses = database.expenseDao().getAllExpensesSnapshot()
        val model = CategorizationModel()
        
        // Create feature vectors for each transaction
        for (expense in expenses) {
            val features = extractTransactionFeatures(expense.description ?: "", expense.amount, expense.date)
            model.addTrainingData(features, expense.category ?: "")
        }
        
        // Train the model using a simple naive Bayes approach
        model.train()
        
        model
    }
    
    suspend fun predictTransactionCategory(
        description: String,
        amount: Double,
        date: Long = System.currentTimeMillis()
    ): CategoryPrediction = withContext(Dispatchers.IO) {
        
        val model = trainCategorizationModel()
        val features = extractTransactionFeatures(description, amount, date)
        
        val predictions = model.predict(features)
        val topPrediction = predictions.maxByOrNull { it.confidence }
        
        CategoryPrediction(
            suggestedCategory = topPrediction?.category ?: "Other",
            confidence = topPrediction?.confidence ?: 0.5,
            alternativeCategories = predictions.filter { it != topPrediction }.take(2),
            reasoningFactors = model.getReasoningFactors(features)
        )
    }
    
    suspend fun generatePersonalizedRecommendations(): List<PersonalizedRecommendation> = withContext(Dispatchers.IO) {
        val recommendations = mutableListOf<PersonalizedRecommendation>()
        
        val userProfile = buildUserProfile()
        val spendingBehavior = analyzeSpendingBehavior()
        val financialGoals = getFinancialGoals()
        
        // Spending reduction recommendations
        recommendations.addAll(generateSpendingRecommendations(userProfile, spendingBehavior))
        
        // Saving recommendations
        recommendations.addAll(generateSavingRecommendations(userProfile))
        
        // Investment recommendations
        recommendations.addAll(generateInvestmentRecommendations(userProfile, financialGoals))
        
        // Budget optimization recommendations
        recommendations.addAll(generateBudgetRecommendations(userProfile, spendingBehavior))
        
        // Automation recommendations
        recommendations.addAll(generateAutomationRecommendations(spendingBehavior))
        
        recommendations.sortedByDescending { it.impact * it.confidence }
    }
    
    suspend fun detectSpendingBehaviorChanges(): List<BehaviorChangeInsight> = withContext(Dispatchers.IO) {
        val insights = mutableListOf<BehaviorChangeInsight>()
        
        val recentExpenses = database.expenseDao().getExpensesBetweenDates(
            System.currentTimeMillis() - (30 * 24 * 60 * 60 * 1000), // Last 30 days
            System.currentTimeMillis()
        )
        
        val previousExpenses = database.expenseDao().getExpensesBetweenDates(
            System.currentTimeMillis() - (60 * 24 * 60 * 60 * 1000), // 30-60 days ago
            System.currentTimeMillis() - (30 * 24 * 60 * 60 * 1000)
        )
        
        // Analyze category-wise changes
        val recentByCategory = recentExpenses.groupBy { it.category ?: "" }
        val previousByCategory = previousExpenses.groupBy { it.category ?: "" }
        
        for ((category, recent) in recentByCategory) {
            val previous = previousByCategory[category] ?: emptyList()
            
            val recentTotal = recent.sumOf { it.amount }
            val previousTotal = previous.sumOf { it.amount }
            
            if (previousTotal > 0) {
                val changePercent = ((recentTotal - previousTotal) / previousTotal) * 100
                
                if (abs(changePercent) > 20) { // Significant change threshold
                    insights.add(
                        BehaviorChangeInsight(
                            category = category,
                            changeType = if (changePercent > 0) ChangeType.INCREASE else ChangeType.DECREASE,
                            changePercent = abs(changePercent),
                            previousAmount = previousTotal,
                            currentAmount = recentTotal,
                            significance = when {
                                abs(changePercent) > 50 -> ChangeSignificance.HIGH
                                abs(changePercent) > 30 -> ChangeSignificance.MEDIUM
                                else -> ChangeSignificance.LOW
                            },
                            possibleReasons = generateChangeReasons(category, changePercent),
                            detectedAt = System.currentTimeMillis()
                        )
                    )
                }
            }
        }
        
        insights
    }
    
    suspend fun optimizeBudgetAllocations(): BudgetOptimizationResult = withContext(Dispatchers.IO) {
        val currentBudgets = database.budgetDao().getAllActiveBudgetsSnapshot()
        val expenses = database.expenseDao().getExpensesBetweenDates(
            System.currentTimeMillis() - (90 * 24 * 60 * 60 * 1000), // Last 3 months
            System.currentTimeMillis()
        )
        
        val optimizations = mutableListOf<BudgetAdjustment>()
        val expensesByCategory = expenses.groupBy { it.category ?: "" }
        
        for (budget in currentBudgets) {
            val categoryExpenses = expensesByCategory[budget.categoryName] ?: emptyList()
            val actualSpending = categoryExpenses.sumOf { it.amount } / 3 // Monthly average
            
            val variance = actualSpending - budget.budgetAmount
            val variancePercent = if (budget.budgetAmount > 0) (variance / budget.budgetAmount) * 100 else 0.0
            
            // Suggest adjustments if variance is significant
            if (abs(variancePercent) > 15) {
                val suggestedAmount = actualSpending * 1.1 // 10% buffer
                
                optimizations.add(
                    BudgetAdjustment(
                        categoryId = budget.id.toLong(),
                        category = budget.categoryName,
                        currentAmount = budget.budgetAmount,
                        suggestedAmount = suggestedAmount,
                        variance = variance,
                        variancePercent = variancePercent,
                        reason = generateBudgetAdjustmentReason(variancePercent, actualSpending),
                        confidence = calculateAdjustmentConfidence(categoryExpenses.size, variancePercent)
                    )
                )
            }
        }
        
        // Calculate total impact
        val totalCurrentBudget = currentBudgets.sumOf { it.budgetAmount }
        val totalSuggestedBudget = optimizations.fold(totalCurrentBudget) { acc, adjustment ->
            acc - adjustment.currentAmount + adjustment.suggestedAmount
        }
        
        BudgetOptimizationResult(
            adjustments = optimizations,
            totalCurrentBudget = totalCurrentBudget,
            totalOptimizedBudget = totalSuggestedBudget,
            potentialSavings = totalCurrentBudget - totalSuggestedBudget,
            overallConfidence = optimizations.map { it.confidence }.average().takeIf { optimizations.isNotEmpty() } ?: 0.0,
            implementationRecommendations = generateImplementationRecommendations(optimizations)
        )
    }
    
    suspend fun predictFutureSpending(category: String, months: Int = 6): SpendingPrediction = withContext(Dispatchers.IO) {
        val allExpenses = database.expenseDao().getAllExpensesSnapshot()
        val historicalExpenses = allExpenses.filter { it.category == category }
        
        if (historicalExpenses.size < 3) {
            return@withContext SpendingPrediction(
                category = category,
                predictions = emptyList(),
                confidence = 0.0,
                trend = SpendingTrend.STABLE,
                seasonalFactors = emptyMap()
            )
        }
        
        // Group by month
        val monthlySpending = mutableMapOf<String, Double>()
        val calendar = Calendar.getInstance()
        
        for (expense in historicalExpenses) {
            calendar.timeInMillis = expense.date
            val monthKey = "${calendar.get(Calendar.YEAR)}-${calendar.get(Calendar.MONTH)}"
            monthlySpending[monthKey] = monthlySpending.getOrDefault(monthKey, 0.0) + expense.amount
        }
        
        val values = monthlySpending.values.toList()
        val trend = calculateTrend(values)
        val seasonalFactors = calculateSeasonalFactors(historicalExpenses)
        
        // Generate predictions
        val predictions = mutableListOf<MonthlySpendingPrediction>()
        val baseAmount = values.average()
        
        repeat(months) { month ->
            calendar.timeInMillis = System.currentTimeMillis()
            calendar.add(Calendar.MONTH, month + 1)
            
            val seasonalFactor = seasonalFactors[calendar.get(Calendar.MONTH)] ?: 1.0
            val trendFactor = 1.0 + (trend * (month + 1) / 12.0) // Apply trend
            
            val predictedAmount = baseAmount * seasonalFactor * trendFactor
            val confidence = maxOf(0.3, 0.9 - (month * 0.1)) // Decreasing confidence over time
            
            predictions.add(
                MonthlySpendingPrediction(
                    month = month + 1,
                    predictedAmount = predictedAmount,
                    confidence = confidence,
                    factors = mapOf(
                        "base" to baseAmount,
                        "seasonal" to seasonalFactor,
                        "trend" to trendFactor
                    )
                )
            )
        }
        
        SpendingPrediction(
            category = category,
            predictions = predictions,
            confidence = predictions.map { it.confidence }.average(),
            trend = when {
                trend > 0.05 -> SpendingTrend.INCREASING
                trend < -0.05 -> SpendingTrend.DECREASING
                else -> SpendingTrend.STABLE
            },
            seasonalFactors = seasonalFactors
        )
    }
    
    // Helper methods
    private fun extractTransactionFeatures(description: String, amount: Double, date: Long): TransactionFeatures {
        val normalizedDescription = description.lowercase().trim()
        
        // Extract keywords
        val keywords = normalizedDescription.split("\\s+".toRegex())
        
        // Time features
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = date
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        
        // Amount features
        val amountRange = when {
            amount < 100 -> "small"
            amount < 500 -> "medium"
            amount < 1000 -> "large"
            else -> "very_large"
        }
        
        return TransactionFeatures(
            keywords = keywords,
            amount = amount,
            amountRange = amountRange,
            dayOfWeek = dayOfWeek,
            hourOfDay = hourOfDay,
            dayOfMonth = dayOfMonth,
            isWeekend = dayOfWeek in listOf(Calendar.SATURDAY, Calendar.SUNDAY),
            containsNumbers = normalizedDescription.contains("\\d".toRegex()),
            length = normalizedDescription.length
        )
    }
    
    private suspend fun analyzeSpendingPatterns(): List<SmartInsight> {
        val insights = mutableListOf<SmartInsight>()
        
        try {
            val expenses = database.expenseDao().getExpensesBetweenDates(
                System.currentTimeMillis() - (90 * 24 * 60 * 60 * 1000),
                System.currentTimeMillis()
            )
            
            if (expenses.isEmpty()) {
                return insights
            }
            
            // Most expensive categories
            val categoryTotals = expenses.groupBy { it.category ?: "Other" }
                .mapValues { (_, expenseList) -> expenseList.sumOf { expense -> expense.amount } }
                .toList()
                .sortedByDescending { it.second }
            
            if (categoryTotals.isNotEmpty()) {
                val topCategory = categoryTotals.first()
                val totalExpenses = expenses.sumOf { it.amount }
                
                if (totalExpenses > 0) {
                    val percentage = ((topCategory.second / totalExpenses) * 100).toInt()
                    insights.add(
                        SmartInsight(
                            id = UUID.randomUUID().toString(),
                            type = InsightType.SPENDING_PATTERN,
                            title = "Högsta utgiftskategori",
                            description = "${topCategory.first} är din största utgift med ${topCategory.second.toInt()} SEK (${percentage}% av total)",
                            actionable = true,
                            actions = listOf("Granska ${topCategory.first} utgifter", "Sätt en budget för ${topCategory.first}"),
                            priority = InsightPriority.HIGH,
                            confidence = 0.95,
                            category = topCategory.first,
                            impact = if (topCategory.second > 5000) ImpactLevel.HIGH else ImpactLevel.MEDIUM,
                            createdAt = System.currentTimeMillis()
                        )
                    )
                }
            }
        } catch (e: Exception) {
            android.util.Log.e("MLInsightEngine", "Error in analyzeSpendingPatterns", e)
        }
        
        return insights
    }
    
    private suspend fun analyzeIncomePatterns(): List<SmartInsight> {
        // Implementation for income pattern analysis
        return emptyList()
    }
    
    private suspend fun analyzeBudgetOptimization(): List<SmartInsight> {
        // Implementation for budget optimization insights
        return emptyList()
    }
    
    private suspend fun identifySavingOpportunities(): List<SmartInsight> {
        // Implementation for saving opportunity identification
        return emptyList()
    }
    
    private suspend fun assessFinancialHealth(): List<SmartInsight> {
        // Implementation for financial health assessment
        return emptyList()
    }
    
    private suspend fun detectSpendingAnomalies(): List<SmartInsight> {
        // Implementation for anomaly detection
        return emptyList()
    }
    
    private suspend fun generateSeasonalInsights(): List<SmartInsight> {
        // Implementation for seasonal insights
        return emptyList()
    }
    
    private suspend fun analyzeGoalProgress(): List<SmartInsight> {
        // Implementation for goal progress analysis
        return emptyList()
    }
    
    private suspend fun buildUserProfile(): UserProfile {
        val monthlyIncome = calculateMonthlyIncome()
        val monthlyExpenses = calculateMonthlyExpenses()
        val savingsRate = if (monthlyIncome > 0) ((monthlyIncome - monthlyExpenses) / monthlyIncome) * 100 else 0.0
        
        return UserProfile(
            monthlyIncome = monthlyIncome,
            monthlyExpenses = monthlyExpenses,
            savingsRate = savingsRate,
            riskTolerance = assessRiskTolerance(),
            financialGoals = getFinancialGoals(),
            spendingPersonality = determineSpendingPersonality()
        )
    }
    
    private fun calculateTrend(values: List<Double>): Double {
        if (values.size < 2) return 0.0
        
        val n = values.size
        val sumX = (1..n).sum()
        val sumY = values.sum()
        val sumXY = values.mapIndexed { index, value -> (index + 1) * value }.sum()
        val sumX2 = (1..n).map { it * it }.sum()
        
        val slope = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX)
        val mean = values.average()
        
        return slope / mean // Normalized slope
    }
    
    private fun calculateSeasonalFactors(expenses: List<Expense>): Map<Int, Double> {
        val monthlyTotals = mutableMapOf<Int, MutableList<Double>>()
        val calendar = Calendar.getInstance()
        
        for (expense in expenses) {
            calendar.timeInMillis = expense.date
            val month = calendar.get(Calendar.MONTH)
            monthlyTotals.getOrPut(month) { mutableListOf() }.add(expense.amount)
        }
        
        val overallAverage = expenses.map { it.amount }.average()
        
        return monthlyTotals.mapValues { (_, amounts) ->
            val monthlyAverage = amounts.average()
            monthlyAverage / overallAverage
        }
    }
    
    // Placeholder implementations
    private suspend fun calculateMonthlyIncome(): Double = 30000.0
    private suspend fun calculateMonthlyExpenses(): Double = 25000.0
    private fun assessRiskTolerance(): RiskTolerance = RiskTolerance.MODERATE
    private suspend fun getFinancialGoals(): List<FinancialGoal> = emptyList()
    private fun determineSpendingPersonality(): SpendingPersonality = SpendingPersonality.BALANCED
    private suspend fun analyzeSpendingBehavior(): SpendingBehavior = SpendingBehavior()
    private fun generateSpendingRecommendations(profile: UserProfile, behavior: SpendingBehavior): List<PersonalizedRecommendation> = emptyList()
    private fun generateSavingRecommendations(profile: UserProfile): List<PersonalizedRecommendation> = emptyList()
    private fun generateInvestmentRecommendations(profile: UserProfile, goals: List<FinancialGoal>): List<PersonalizedRecommendation> = emptyList()
    private fun generateBudgetRecommendations(profile: UserProfile, behavior: SpendingBehavior): List<PersonalizedRecommendation> = emptyList()
    private fun generateAutomationRecommendations(behavior: SpendingBehavior): List<PersonalizedRecommendation> = emptyList()
    private fun generateChangeReasons(category: String, changePercent: Double): List<String> = emptyList()
    private fun generateBudgetAdjustmentReason(variancePercent: Double, actualSpending: Double): String = ""
    private fun calculateAdjustmentConfidence(transactionCount: Int, variancePercent: Double): Double = 0.8
    private fun generateImplementationRecommendations(adjustments: List<BudgetAdjustment>): List<String> = emptyList()
}

// Data classes for ML insights
@Entity(tableName = "smart_insights")
data class SmartInsight(
    @PrimaryKey
    val id: String,
    val type: InsightType,
    val title: String,
    val description: String,
    val actionable: Boolean,
    val actions: List<String>,
    val priority: InsightPriority,
    val confidence: Double,
    val category: String? = null,
    val impact: ImpactLevel,
    val isRead: Boolean = false,
    val isDismissed: Boolean = false,
    val expiresAt: Long? = null,
    val createdAt: Long,
    val updatedAt: Long = System.currentTimeMillis()
)

enum class InsightType {
    SPENDING_PATTERN, INCOME_PATTERN, BUDGET_OPTIMIZATION, SAVING_OPPORTUNITY,
    FINANCIAL_HEALTH, ANOMALY_DETECTION, SEASONAL_INSIGHT, GOAL_PROGRESS,
    CATEGORY_ANALYSIS, TREND_ANALYSIS
}

enum class InsightPriority(val value: Int) {
    LOW(1), MEDIUM(2), HIGH(3), CRITICAL(4)
}

enum class ImpactLevel {
    LOW, MEDIUM, HIGH
}

data class CategoryPrediction(
    val suggestedCategory: String,
    val confidence: Double,
    val alternativeCategories: List<CategoryConfidence>,
    val reasoningFactors: List<String>
)

data class CategoryConfidence(
    val category: String,
    val confidence: Double
)

data class CategorizationModel(
    private val categoryFeatures: MutableMap<String, MutableList<TransactionFeatures>> = mutableMapOf(),
    private val categoryProbabilities: MutableMap<String, Double> = mutableMapOf()
) {
    fun addTrainingData(features: TransactionFeatures, category: String) {
        categoryFeatures.getOrPut(category) { mutableListOf() }.add(features)
    }
    
    fun train() {
        val totalTransactions = categoryFeatures.values.sumOf { it.size }
        for ((category, features) in categoryFeatures) {
            categoryProbabilities[category] = features.size.toDouble() / totalTransactions
        }
    }
    
    fun predict(features: TransactionFeatures): List<CategoryConfidence> {
        return categoryProbabilities.map { (category, _) ->
            val confidence = calculateCategoryConfidence(features, category)
            CategoryConfidence(category, confidence)
        }.sortedByDescending { it.confidence }
    }
    
    fun getReasoningFactors(features: TransactionFeatures): List<String> {
        return listOf(
            "Amount: ${features.amountRange}",
            "Keywords: ${features.keywords.take(3).joinToString()}",
            "Time: ${if (features.isWeekend) "Weekend" else "Weekday"}"
        )
    }
    
    private fun calculateCategoryConfidence(features: TransactionFeatures, category: String): Double {
        val categoryFeaturesList = categoryFeatures[category] ?: return 0.0
        
        var score = categoryProbabilities[category] ?: 0.0
        
        // Keyword matching
        val keywordMatches = categoryFeaturesList.count { categoryFeature ->
            features.keywords.any { keyword -> categoryFeature.keywords.contains(keyword) }
        }
        score += (keywordMatches.toDouble() / categoryFeaturesList.size) * 0.4
        
        // Amount range matching
        val amountMatches = categoryFeaturesList.count { it.amountRange == features.amountRange }
        score += (amountMatches.toDouble() / categoryFeaturesList.size) * 0.3
        
        // Time pattern matching
        val timeMatches = categoryFeaturesList.count { it.isWeekend == features.isWeekend }
        score += (timeMatches.toDouble() / categoryFeaturesList.size) * 0.3
        
        return minOf(1.0, score)
    }
}

data class TransactionFeatures(
    val keywords: List<String>,
    val amount: Double,
    val amountRange: String,
    val dayOfWeek: Int,
    val hourOfDay: Int,
    val dayOfMonth: Int,
    val isWeekend: Boolean,
    val containsNumbers: Boolean,
    val length: Int
)

data class PersonalizedRecommendation(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String,
    val type: RecommendationType,
    val impact: Double, // 0.0 to 1.0
    val confidence: Double, // 0.0 to 1.0
    val effort: EffortLevel,
    val timeframe: String,
    val steps: List<String>,
    val potentialSavings: Double? = null,
    val category: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)

enum class RecommendationType {
    SPENDING_REDUCTION, SAVING_INCREASE, INVESTMENT, BUDGET_OPTIMIZATION, AUTOMATION
}

enum class EffortLevel {
    LOW, MEDIUM, HIGH
}

data class BehaviorChangeInsight(
    val category: String,
    val changeType: ChangeType,
    val changePercent: Double,
    val previousAmount: Double,
    val currentAmount: Double,
    val significance: ChangeSignificance,
    val possibleReasons: List<String>,
    val detectedAt: Long
)

enum class ChangeType {
    INCREASE, DECREASE
}

enum class ChangeSignificance {
    LOW, MEDIUM, HIGH
}

data class BudgetOptimizationResult(
    val adjustments: List<BudgetAdjustment>,
    val totalCurrentBudget: Double,
    val totalOptimizedBudget: Double,
    val potentialSavings: Double,
    val overallConfidence: Double,
    val implementationRecommendations: List<String>
)

data class BudgetAdjustment(
    val categoryId: Long,
    val category: String,
    val currentAmount: Double,
    val suggestedAmount: Double,
    val variance: Double,
    val variancePercent: Double,
    val reason: String,
    val confidence: Double
)

data class SpendingPrediction(
    val category: String,
    val predictions: List<MonthlySpendingPrediction>,
    val confidence: Double,
    val trend: SpendingTrend,
    val seasonalFactors: Map<Int, Double>
)

data class MonthlySpendingPrediction(
    val month: Int,
    val predictedAmount: Double,
    val confidence: Double,
    val factors: Map<String, Double>
)

enum class SpendingTrend {
    INCREASING, STABLE, DECREASING
}

data class UserProfile(
    val monthlyIncome: Double,
    val monthlyExpenses: Double,
    val savingsRate: Double,
    val riskTolerance: RiskTolerance,
    val financialGoals: List<FinancialGoal>,
    val spendingPersonality: SpendingPersonality
)

enum class RiskTolerance {
    CONSERVATIVE, MODERATE, AGGRESSIVE
}

data class FinancialGoal(
    val id: String,
    val name: String,
    val targetAmount: Double,
    val currentAmount: Double,
    val targetDate: Long
)

enum class SpendingPersonality {
    FRUGAL, BALANCED, SPENDER, IMPULSE_BUYER
}

data class SpendingBehavior(
    val averageTransactionSize: Double = 0.0,
    val transactionFrequency: Double = 0.0,
    val topCategories: List<String> = emptyList(),
    val spendingTimes: List<Int> = emptyList(), // Hours of day
    val spendingDays: List<Int> = emptyList() // Days of week
)