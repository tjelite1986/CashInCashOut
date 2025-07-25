package com.example.budgetapp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "financial_goals")
data class FinancialGoal(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    val name: String,
    val description: String? = null,
    val goalType: GoalType,
    val targetAmount: Double,
    val currentAmount: Double = 0.0,
    val targetDate: Long? = null,
    val monthlyTarget: Double? = null,
    val priority: GoalPriority,
    val category: String? = null,
    val isActive: Boolean = true,
    val isCompleted: Boolean = false,
    val completedAt: Long? = null,
    val autoTransferEnabled: Boolean = false,
    val autoTransferAmount: Double? = null,
    val linkedAccountId: String? = null,
    val reminderEnabled: Boolean = true,
    val reminderFrequency: ReminderFrequency = ReminderFrequency.WEEKLY,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) {
    val progressPercentage: Double
        get() = if (targetAmount > 0) (currentAmount / targetAmount * 100) else 0.0
        
    val remainingAmount: Double
        get() = maxOf(0.0, targetAmount - currentAmount)
}

enum class GoalType {
    EMERGENCY_FUND,    // Emergency fund building
    VACATION,          // Vacation savings
    HOME_DOWN_PAYMENT, // House down payment
    CAR_PURCHASE,      // Vehicle purchase
    DEBT_PAYOFF,       // Debt elimination
    RETIREMENT,        // Retirement savings
    EDUCATION,         // Education funding
    INVESTMENT,        // Investment goals
    BUSINESS,          // Business funding
    WEDDING,           // Wedding expenses
    HEALTH,            // Medical expenses
    TECHNOLOGY,        // Tech purchases
    HOME_IMPROVEMENT,  // Home renovation
    CHARITY,           // Charitable giving
    CUSTOM            // User-defined goal
}

enum class GoalPriority {
    LOW,
    MEDIUM, 
    HIGH,
    CRITICAL
}

enum class ReminderFrequency {
    DAILY,
    WEEKLY,
    BIWEEKLY,
    MONTHLY,
    QUARTERLY,
    NEVER
}