package com.example.budgetapp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "financial_insights")
data class FinancialInsight(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    val insightType: InsightType,
    val title: String,
    val description: String,
    val severity: InsightSeverity,
    val category: String? = null,
    val amount: Double? = null,
    val percentage: Double? = null,
    val confidenceScore: Float, // 0.0 to 1.0
    val actionable: Boolean = true,
    val suggestedAction: String? = null,
    val isRead: Boolean = false,
    val isActedUpon: Boolean = false,
    val validUntil: Long? = null, // Timestamp when insight expires
    val data: String? = null, // JSON string for additional data
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) : Serializable

enum class InsightType : Serializable {
    SPENDING_ANOMALY,      // Unusual spending detected
    BUDGET_WARNING,        // Approaching budget limits
    SAVING_OPPORTUNITY,    // Found potential savings
    INCOME_PATTERN,        // Income pattern analysis
    EXPENSE_TREND,         // Expense trend analysis
    GOAL_PROGRESS,         // Goal achievement insights
    SEASONAL_PATTERN,      // Seasonal spending patterns
    CATEGORY_ANALYSIS,     // Category-specific insights
    CASH_FLOW_PREDICTION,  // Future cash flow predictions
    INVESTMENT_OPPORTUNITY, // Investment suggestions
    DEBT_OPTIMIZATION,     // Debt payoff strategies
    TAX_OPTIMIZATION,      // Tax saving opportunities
    BILL_OPTIMIZATION,     // Recurring bill analysis
    FINANCIAL_HEALTH,      // Overall financial health
    FRAUD_ALERT           // Suspicious activity
}

enum class InsightSeverity : Serializable {
    INFO,     // General information
    LOW,      // Minor concern
    MEDIUM,   // Moderate attention needed
    HIGH,     // Important action required
    CRITICAL  // Urgent attention required
}