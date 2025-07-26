package com.example.budgetapp.analytics.ai

// Temporarily commented out until DAO methods are properly implemented
/*
import android.content.Context
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.*

/**
 * Service for analyzing spending patterns and predicting credit score impact
 * Uses financial behavior analysis to provide credit score insights
 */
class CreditScoreAnalysisService(private val context: Context) {
    
    private val database = BudgetDatabase.getDatabase(context)
    private val expenseDao = database.expenseDao()
    private val incomeDao = database.incomeDao()
    private val loanDao = database.loanDao()
    
    companion object {
        // Credit score ranges (Swedish/European style: 1-999)
        const val EXCELLENT_SCORE = 800
        const val GOOD_SCORE = 650
        const val FAIR_SCORE = 500
        const val POOR_SCORE = 350
        
        // Spending ratios that affect credit score
        const val IDEAL_DEBT_TO_INCOME_RATIO = 0.30
        const val IDEAL_HOUSING_RATIO = 0.30
        const val IDEAL_SAVINGS_RATE = 0.20
        
        // Risk factors
        const val HIGH_RISK_EXPENSE_CATEGORIES = 30.0 // % of income
        const val GAMBLING_RED_FLAG_RATIO = 0.05 // 5% of income
    }
    
    /**
     * Analyze current financial behavior and predict credit score impact
     */
    suspend fun analyzeCreditScoreImpact(): CreditScoreAnalysis = withContext(Dispatchers.IO) {
        try {
            val currentTime = System.currentTimeMillis()
            val sixMonthsAgo = currentTime - (6 * 30 * 24 * 60 * 60 * 1000L)
            
            // Get financial data for analysis
            val expenses = expenseDao.getExpensesForPeriod(sixMonthsAgo, currentTime)
            val incomes = incomeDao.getIncomesForPeriod(sixMonthsAgo, currentTime)
            val loans = loanDao.getAllActiveLoans()
            
            // Calculate financial metrics
            val monthlyIncome = calculateMonthlyIncome(incomes)
            val monthlyExpenses = calculateMonthlyExpenses(expenses)
            val debtPayments = calculateDebtPayments(loans)
            val categoryBreakdown = analyzeCategorySpending(expenses)
            
            // Calculate credit score factors
            val paymentHistory = analyzePaymentHistory(expenses, loans)
            val creditUtilization = analyzeCreditUtilization(expenses, monthlyIncome)
            val debtToIncomeRatio = if (monthlyIncome > 0) debtPayments / monthlyIncome else 0.0
            val spendingStability = analyzeSpendingStability(expenses)
            val riskFactors = identifyRiskFactors(expenses, monthlyIncome, categoryBreakdown)
            
            // Predict credit score impact
            val currentScore = predictCurrentCreditScore(
                paymentHistory, creditUtilization, debtToIncomeRatio, 
                spendingStability, riskFactors
            )
            
            val scoreImpact = calculateScoreImpact(
                paymentHistory, creditUtilization, debtToIncomeRatio,
                spendingStability, riskFactors
            )
            
            // Generate recommendations
            val recommendations = generateCreditRecommendations(
                paymentHistory, creditUtilization, debtToIncomeRatio,
                spendingStability, riskFactors, monthlyIncome
            )
            
            CreditScoreAnalysis(
                predictedScore = currentScore,
                scoreRange = determineScoreRange(currentScore),
                scoreImpact = scoreImpact,
                paymentHistoryScore = paymentHistory.score,
                creditUtilizationScore = creditUtilization.score,
                debtToIncomeRatio = debtToIncomeRatio,
                spendingStabilityScore = spendingStability.score,
                riskFactors = riskFactors,
                monthlyIncome = monthlyIncome,
                monthlyExpenses = monthlyExpenses,
                savingsRate = calculateSavingsRate(monthlyIncome, monthlyExpenses),
                recommendations = recommendations,
                lastAnalyzed = currentTime
            )
            
        } catch (e: Exception) {
            com.example.budgetapp.utils.ErrorHandler.logError("CreditScoreAnalysis", "Error analyzing credit score", e)
            CreditScoreAnalysis()
        }
    }
    
    /**
     * Predict future credit score based on spending plan changes
     */
    suspend fun predictScoreWithChanges(changes: SpendingPlanChanges): CreditScorePrediction = 
        withContext(Dispatchers.IO) {
            try {
                val currentAnalysis = analyzeCreditScoreImpact()
                
                // Apply hypothetical changes
                val newMonthlyExpenses = currentAnalysis.monthlyExpenses + changes.expenseChange
                val newDebtPayments = calculateNewDebtPayments(changes)
                val newSavingsRate = (currentAnalysis.monthlyIncome - newMonthlyExpenses) / currentAnalysis.monthlyIncome
                
                // Recalculate metrics with changes
                val newDebtToIncomeRatio = if (currentAnalysis.monthlyIncome > 0) {
                    newDebtPayments / currentAnalysis.monthlyIncome
                } else 0.0
                
                val newUtilization = CreditUtilization(
                    ratio = maxOf(0.0, minOf(1.0, newMonthlyExpenses / currentAnalysis.monthlyIncome)),
                    score = calculateUtilizationScore(newMonthlyExpenses / currentAnalysis.monthlyIncome)
                )
                
                // Predict new score
                val newScore = predictCurrentCreditScore(
                    currentAnalysis.paymentHistoryScore.let { PaymentHistory(score = it) },
                    newUtilization,
                    newDebtToIncomeRatio,
                    currentAnalysis.spendingStabilityScore.let { SpendingStability(score = it) },
                    currentAnalysis.riskFactors
                )
                
                val scoreChange = newScore - currentAnalysis.predictedScore
                val timeToImprovement = estimateTimeToImprovement(scoreChange)
                
                CreditScorePrediction(
                    currentScore = currentAnalysis.predictedScore,
                    predictedScore = newScore,
                    scoreChange = scoreChange,
                    timeToImpact = timeToImprovement,
                    improvementPlan = generateImprovementPlan(changes, scoreChange),
                    confidenceLevel = calculatePredictionConfidence(changes)
                )
                
            } catch (e: Exception) {
                com.example.budgetapp.utils.ErrorHandler.logError("CreditScoreAnalysis", "Error predicting score changes", e)
                CreditScorePrediction()
            }
        }
    
    /**
     * Get personalized credit improvement recommendations
     */
    suspend fun getCreditImprovementPlan(): CreditImprovementPlan = withContext(Dispatchers.IO) {
        try {
            val analysis = analyzeCreditScoreImpact()
            val quickWins = mutableListOf<CreditAction>()
            val longTermActions = mutableListOf<CreditAction>()
            
            // Quick wins (0-3 months)
            if (analysis.debtToIncomeRatio > 0.4) {
                quickWins.add(
                    CreditAction(
                        title = "Minska skulder",
                        description = "Din skuldsättningsgrad är ${(analysis.debtToIncomeRatio * 100).toInt()}%. Fokusera på att betala ner högräntelån först.",
                        impact = CreditImpact.HIGH,
                        timeframe = "1-3 månader",
                        potentialScoreIncrease = 30,
                        category = ActionCategory.DEBT_REDUCTION
                    )
                )
            }
            
            if (analysis.savingsRate < 0.1) {
                quickWins.add(
                    CreditAction(
                        title = "Öka sparandet",
                        description = "Du sparar ${(analysis.savingsRate * 100).toInt()}% av inkomsten. Försök nå 10-20% för bättre kreditvärdighet.",
                        impact = CreditImpact.MEDIUM,
                        timeframe = "1-2 månader",
                        potentialScoreIncrease = 15,
                        category = ActionCategory.SAVINGS_IMPROVEMENT
                    )
                )
            }
            
            // Long term actions (3-12 months)
            if (analysis.spendingStabilityScore < 70) {
                longTermActions.add(
                    CreditAction(
                        title = "Stabilisera utgifter",
                        description = "Dina utgifter varierar mycket mellan månader. Skapa en mer förutsägbar budget.",
                        impact = CreditImpact.MEDIUM,
                        timeframe = "3-6 månader",
                        potentialScoreIncrease = 25,
                        category = ActionCategory.STABILITY_IMPROVEMENT
                    )
                )
            }
            
            if (analysis.riskFactors.isNotEmpty()) {
                longTermActions.add(
                    CreditAction(
                        title = "Eliminera riskfaktorer",
                        description = "Identifierade riskfaktorer: ${analysis.riskFactors.joinToString { it.type.name }}",
                        impact = CreditImpact.HIGH,
                        timeframe = "6-12 månader",
                        potentialScoreIncrease = 40,
                        category = ActionCategory.RISK_REDUCTION
                    )
                )
            }
            
            CreditImprovementPlan(
                currentScore = analysis.predictedScore,
                targetScore = minOf(999, analysis.predictedScore + quickWins.sumOf { it.potentialScoreIncrease } + longTermActions.sumOf { it.potentialScoreIncrease }),
                quickWins = quickWins,
                longTermActions = longTermActions,
                estimatedTimeToTarget = "${maxOf(3, longTermActions.maxOfOrNull { extractMonthsFromTimeframe(it.timeframe) } ?: 3)} månader",
                monthlyProgressTracking = generateMonthlyMilestones(quickWins + longTermActions)
            )
            
        } catch (e: Exception) {
            com.example.budgetapp.utils.ErrorHandler.logError("CreditScoreAnalysis", "Error creating improvement plan", e)
            CreditImprovementPlan()
        }
    }
    
    // Private helper methods
    
    private suspend fun calculateMonthlyIncome(incomes: List<Income>): Double {
        val monthlyIncomes = incomes.groupBy { getMonthYear(it.date) }
        return monthlyIncomes.values.map { it.sumOf { income -> income.amount } }.average()
    }
    
    private suspend fun calculateMonthlyExpenses(expenses: List<Expense>): Double {
        val monthlyExpenses = expenses.groupBy { getMonthYear(it.date) }
        return monthlyExpenses.values.map { it.sumOf { expense -> expense.amount } }.average()
    }
    
    private suspend fun calculateDebtPayments(loans: List<Loan>): Double {
        return loans.filter { !it.isPaidBack && it.type == LoanType.BORROWED }
            .sumOf { it.amount * 0.05 } // Assume 5% monthly payment
    }
    
    private fun analyzeCategorySpending(expenses: List<Expense>): Map<String, Double> {
        return expenses.groupBy { it.category ?: "Övrigt" }
            .mapValues { (_, expenses) -> expenses.sumOf { it.amount } }
    }
    
    private fun analyzePaymentHistory(expenses: List<Expense>, loans: List<Loan>): PaymentHistory {
        // Analyze payment regularity and timeliness
        val regularPayments = expenses.filter { 
            it.category in listOf("Räkningar", "Boende") 
        }
        
        val paymentRegularity = if (regularPayments.isNotEmpty()) {
            val monthlyGroups = regularPayments.groupBy { getMonthYear(it.date) }
            val consistentMonths = monthlyGroups.count { it.value.isNotEmpty() }
            (consistentMonths.toDouble() / monthlyGroups.size * 100).toInt()
        } else 100
        
        val loanPaymentHistory = loans.count { it.isPaidBack }.toDouble() / maxOf(1, loans.size) * 100
        
        val overallScore = ((paymentRegularity + loanPaymentHistory) / 2).toInt()
        
        return PaymentHistory(
            score = overallScore,
            onTimePayments = paymentRegularity,
            missedPayments = 100 - paymentRegularity
        )
    }
    
    private fun analyzeCreditUtilization(expenses: List<Expense>, monthlyIncome: Double): CreditUtilization {
        val creditRelatedExpenses = expenses.filter { 
            it.paymentMethod == "Kort" 
        }.sumOf { it.amount }
        
        val utilizationRatio = if (monthlyIncome > 0) {
            creditRelatedExpenses / (monthlyIncome * 6) // 6 months of data
        } else 0.0
        
        val score = calculateUtilizationScore(utilizationRatio)
        
        return CreditUtilization(
            ratio = utilizationRatio,
            score = score
        )
    }
    
    private fun calculateUtilizationScore(ratio: Double): Int {
        return when {
            ratio < 0.1 -> 100
            ratio < 0.3 -> 80
            ratio < 0.5 -> 60
            ratio < 0.7 -> 40
            else -> 20
        }
    }
    
    private fun analyzeSpendingStability(expenses: List<Expense>): SpendingStability {
        val monthlyTotals = expenses.groupBy { getMonthYear(it.date) }
            .mapValues { it.value.sumOf { expense -> expense.amount } }
        
        if (monthlyTotals.size < 2) {
            return SpendingStability(score = 50, variance = 0.0)
        }
        
        val mean = monthlyTotals.values.average()
        val variance = monthlyTotals.values.map { (it - mean).pow(2) }.average()
        val coefficientOfVariation = if (mean > 0) sqrt(variance) / mean else 0.0
        
        val stabilityScore = maxOf(0, (100 - coefficientOfVariation * 100).toInt())
        
        return SpendingStability(
            score = stabilityScore,
            variance = coefficientOfVariation
        )
    }
    
    private fun identifyRiskFactors(
        expenses: List<Expense>, 
        monthlyIncome: Double, 
        categoryBreakdown: Map<String, Double>
    ): List<RiskFactor> = 
        mutableListOf<RiskFactor>().apply {
            // High-risk spending categories
            val gamblingSpending = categoryBreakdown["Nöje"] ?: 0.0
            if (gamblingSpending > monthlyIncome * GAMBLING_RED_FLAG_RATIO * 6) {
                add(RiskFactor(
                    type = RiskType.HIGH_RISK_SPENDING,
                    severity = RiskSeverity.HIGH,
                    description = "Höga utgifter för nöje/spel",
                    impact = -50
                ))
            }
            
            // Irregular income
            val incomeCategories = categoryBreakdown.keys.intersect(listOf("Lön", "Freelance"))
            if (incomeCategories.size > 1) {
                add(RiskFactor(
                    type = RiskType.IRREGULAR_INCOME,
                    severity = RiskSeverity.MEDIUM,
                    description = "Oregelbunden inkomst från flera källor",
                    impact = -20
                ))
            }
            
            // High housing costs
            val housingCosts = categoryBreakdown["Boende"] ?: 0.0
            if (housingCosts > monthlyIncome * 0.4 * 6) {
                add(RiskFactor(
                    type = RiskType.HIGH_HOUSING_COSTS,
                    severity = RiskSeverity.MEDIUM,
                    description = "Bostadskostnader över 40% av inkomst",
                    impact = -15
                ))
            }
        }
    
    private fun predictCurrentCreditScore(
        paymentHistory: PaymentHistory,
        creditUtilization: CreditUtilization,
        debtToIncomeRatio: Double,
        spendingStability: SpendingStability,
        riskFactors: List<RiskFactor>
    ): Int {
        // Weighted credit score calculation
        var baseScore = 500 // Average starting point
        
        // Payment history (35% weight)
        baseScore += (paymentHistory.score - 50) * 0.35 * 3
        
        // Credit utilization (30% weight)
        baseScore += (creditUtilization.score - 50) * 0.30 * 3
        
        // Debt to income (20% weight)
        val debtScore = when {
            debtToIncomeRatio < 0.2 -> 100
            debtToIncomeRatio < 0.3 -> 80
            debtToIncomeRatio < 0.4 -> 60
            debtToIncomeRatio < 0.5 -> 40
            else -> 20
        }
        baseScore += (debtScore - 50) * 0.20 * 3
        
        // Spending stability (15% weight)
        baseScore += (spendingStability.score - 50) * 0.15 * 3
        
        // Risk factors
        baseScore += riskFactors.sumOf { it.impact }
        
        return maxOf(300, minOf(999, baseScore.toInt()))
    }
    
    private fun calculateScoreImpact(
        paymentHistory: PaymentHistory,
        creditUtilization: CreditUtilization,
        debtToIncomeRatio: Double,
        spendingStability: SpendingStability,
        riskFactors: List<RiskFactor>
    ): ScoreImpact {
        val positiveFactors = mutableListOf<String>()
        val negativeFactors = mutableListOf<String>()
        
        if (paymentHistory.score > 80) positiveFactors.add("Utmärkt betalhistorik")
        else if (paymentHistory.score < 60) negativeFactors.add("Bristfällig betalhistorik")
        
        if (creditUtilization.ratio < 0.3) positiveFactors.add("Låg kreditanvändning")
        else if (creditUtilization.ratio > 0.7) negativeFactors.add("Hög kreditanvändning")
        
        if (debtToIncomeRatio < 0.3) positiveFactors.add("Låg skuldsättningsgrad")
        else if (debtToIncomeRatio > 0.4) negativeFactors.add("Hög skuldsättningsgrad")
        
        riskFactors.forEach { negativeFactors.add(it.description) }
        
        return ScoreImpact(
            positiveFactors = positiveFactors,
            negativeFactors = negativeFactors,
            netImpact = positiveFactors.size - negativeFactors.size
        )
    }
    
    private fun generateCreditRecommendations(
        paymentHistory: PaymentHistory,
        creditUtilization: CreditUtilization,
        debtToIncomeRatio: Double,
        spendingStability: SpendingStability,
        riskFactors: List<RiskFactor>,
        monthlyIncome: Double
    ): List<String> {
        val recommendations = mutableListOf<String>()
        
        if (paymentHistory.score < 70) {
            recommendations.add("Förbättra betalhistoriken genom att betala räkningar i tid")
        }
        
        if (creditUtilization.ratio > 0.5) {
            recommendations.add("Minska kreditkortsanvändningen till under 30% av inkomsten")
        }
        
        if (debtToIncomeRatio > 0.4) {
            recommendations.add("Fokusera på att betala ner skulder för att förbättra skuldsättningsgraden")
        }
        
        if (spendingStability.score < 60) {
            recommendations.add("Skapa en mer stabil budget för att visa finansiell disciplin")
        }
        
        riskFactors.forEach { riskFactor ->
            when (riskFactor.type) {
                RiskType.HIGH_RISK_SPENDING -> recommendations.add("Minska utgifter för högriskaktiviteter")
                RiskType.IRREGULAR_INCOME -> recommendations.add("Bygg upp en buffert för oregelbunden inkomst")
                RiskType.HIGH_HOUSING_COSTS -> recommendations.add("Överväg att minska bostadskostnader")
            }
        }
        
        return recommendations
    }
    
    private fun calculateSavingsRate(monthlyIncome: Double, monthlyExpenses: Double): Double {
        return if (monthlyIncome > 0) {
            maxOf(0.0, (monthlyIncome - monthlyExpenses) / monthlyIncome)
        } else 0.0
    }
    
    private fun determineScoreRange(score: Int): CreditScoreRange {
        return when {
            score >= EXCELLENT_SCORE -> CreditScoreRange.EXCELLENT
            score >= GOOD_SCORE -> CreditScoreRange.GOOD
            score >= FAIR_SCORE -> CreditScoreRange.FAIR
            else -> CreditScoreRange.POOR
        }
    }
    
    private fun getMonthYear(timestamp: Long): String {
        val calendar = java.util.Calendar.getInstance()
        calendar.timeInMillis = timestamp
        return "${calendar.get(java.util.Calendar.YEAR)}-${calendar.get(java.util.Calendar.MONTH)}"
    }
    
    private fun calculateNewDebtPayments(changes: SpendingPlanChanges): Double {
        // Simplified calculation - would need more complex logic in real implementation
        return changes.debtPaymentChange
    }
    
    private fun estimateTimeToImprovement(scoreChange: Double): Int {
        // Estimate months based on score change magnitude
        return when {
            scoreChange > 50 -> 3
            scoreChange > 20 -> 6
            scoreChange > 0 -> 12
            else -> 24
        }
    }
    
    private fun generateImprovementPlan(changes: SpendingPlanChanges, scoreChange: Double): List<String> {
        val plan = mutableListOf<String>()
        
        if (changes.expenseChange < 0) {
            plan.add("Minska utgifter med ${-changes.expenseChange.toInt()} SEK per månad")
        }
        
        if (changes.debtPaymentChange > 0) {
            plan.add("Öka skuldbetalningar med ${changes.debtPaymentChange.toInt()} SEK per månad")
        }
        
        if (scoreChange > 0) {
            plan.add("Förväntat resultat: +${scoreChange.toInt()} poäng i kreditbetyg")
        }
        
        return plan
    }
    
    private fun calculatePredictionConfidence(changes: SpendingPlanChanges): Double {
        // Simple confidence calculation based on change magnitude
        val changeSize = abs(changes.expenseChange) + abs(changes.debtPaymentChange)
        return when {
            changeSize < 1000 -> 0.6
            changeSize < 5000 -> 0.8
            else -> 0.9
        }
    }
    
    private fun extractMonthsFromTimeframe(timeframe: String): Int {
        // Extract number of months from timeframe string
        val regex = "(\\d+)".toRegex()
        val match = regex.find(timeframe)
        return match?.value?.toIntOrNull() ?: 6
    }
    
    private fun generateMonthlyMilestones(actions: List<CreditAction>): List<String> {
        return listOf(
            "Månad 1: Börja med snabba förbättringar",
            "Månad 3: Utvärdera framsteg och justera plan",
            "Månad 6: Mätbar förbättring av kreditbetyg",
            "Månad 12: Uppnå målsatt kreditbetyg"
        )
    }
}

// Data classes for credit score analysis

data class CreditScoreAnalysis(
    val predictedScore: Int = 500,
    val scoreRange: CreditScoreRange = CreditScoreRange.FAIR,
    val scoreImpact: ScoreImpact = ScoreImpact(),
    val paymentHistoryScore: Int = 50,
    val creditUtilizationScore: Int = 50,
    val debtToIncomeRatio: Double = 0.0,
    val spendingStabilityScore: Int = 50,
    val riskFactors: List<RiskFactor> = emptyList(),
    val monthlyIncome: Double = 0.0,
    val monthlyExpenses: Double = 0.0,
    val savingsRate: Double = 0.0,
    val recommendations: List<String> = emptyList(),
    val lastAnalyzed: Long = System.currentTimeMillis()
)

data class ScoreImpact(
    val positiveFactors: List<String> = emptyList(),
    val negativeFactors: List<String> = emptyList(),
    val netImpact: Int = 0
)

data class PaymentHistory(
    val score: Int,
    val onTimePayments: Int = 0,
    val missedPayments: Int = 0
)

data class CreditUtilization(
    val ratio: Double,
    val score: Int
)

data class SpendingStability(
    val score: Int,
    val variance: Double
)

data class RiskFactor(
    val type: RiskType,
    val severity: RiskSeverity,
    val description: String,
    val impact: Int
)

data class CreditScorePrediction(
    val currentScore: Int = 500,
    val predictedScore: Int = 500,
    val scoreChange: Double = 0.0,
    val timeToImpact: Int = 6,
    val improvementPlan: List<String> = emptyList(),
    val confidenceLevel: Double = 0.5
)

data class SpendingPlanChanges(
    val expenseChange: Double = 0.0,
    val debtPaymentChange: Double = 0.0,
    val savingsChange: Double = 0.0
)

data class CreditImprovementPlan(
    val currentScore: Int = 500,
    val targetScore: Int = 600,
    val quickWins: List<CreditAction> = emptyList(),
    val longTermActions: List<CreditAction> = emptyList(),
    val estimatedTimeToTarget: String = "6 månader",
    val monthlyProgressTracking: List<String> = emptyList()
)

data class CreditAction(
    val title: String,
    val description: String,
    val impact: CreditImpact,
    val timeframe: String,
    val potentialScoreIncrease: Int,
    val category: ActionCategory
)

enum class CreditScoreRange(val displayName: String, val description: String) {
    EXCELLENT("Utmärkt", "800-999 poäng"),
    GOOD("Bra", "650-799 poäng"),
    FAIR("Godkänt", "500-649 poäng"),
    POOR("Dålig", "300-499 poäng")
}

enum class RiskType {
    HIGH_RISK_SPENDING,
    IRREGULAR_INCOME,
    HIGH_HOUSING_COSTS,
    MISSED_PAYMENTS,
    HIGH_DEBT_UTILIZATION
}

enum class RiskSeverity {
    LOW, MEDIUM, HIGH, CRITICAL
}

enum class CreditImpact {
    LOW, MEDIUM, HIGH
}

enum class ActionCategory {
    DEBT_REDUCTION,
    SAVINGS_IMPROVEMENT,
    STABILITY_IMPROVEMENT,
    RISK_REDUCTION,
    PAYMENT_HISTORY
}*/
