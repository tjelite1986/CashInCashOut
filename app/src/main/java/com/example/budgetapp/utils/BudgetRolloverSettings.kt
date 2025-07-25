package com.example.budgetapp.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.budgetapp.services.BudgetRolloverBehavior
import com.example.budgetapp.services.BudgetRolloverStrategy

class BudgetRolloverSettings(context: Context) {
    
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    
    companion object {
        private const val PREFS_NAME = "budget_rollover_settings"
        
        // Main rollover settings
        const val PREF_ROLLOVER_ENABLED = "rollover_enabled"
        const val PREF_ROLLOVER_STRATEGY = "rollover_strategy"
        const val PREF_ROLLOVER_BEHAVIOR = "rollover_behavior"
        
        // Period-specific settings
        const val PREF_ROLLOVER_WEEKLY = "rollover_weekly"
        const val PREF_ROLLOVER_MONTHLY = "rollover_monthly"
        const val PREF_ROLLOVER_QUARTERLY = "rollover_quarterly"
        const val PREF_ROLLOVER_YEARLY = "rollover_yearly"
        
        // Amount settings
        const val PREF_MINIMUM_ROLLOVER_AMOUNT = "minimum_rollover_amount"
        const val PREF_ROLLOVER_PERCENTAGE = "rollover_percentage"
        const val PREF_FIXED_ROLLOVER_AMOUNT = "fixed_rollover_amount"
        const val PREF_ROLLOVER_WEIGHT = "rollover_weight"
        
        // Automation settings
        const val PREF_AUTO_ROLLOVER_ENABLED = "auto_rollover_enabled"
        const val PREF_ROLLOVER_NOTIFICATIONS = "rollover_notifications"
        
        // Default values
        const val DEFAULT_ROLLOVER_ENABLED = true
        const val DEFAULT_ROLLOVER_WEEKLY = true
        const val DEFAULT_ROLLOVER_MONTHLY = true
        const val DEFAULT_ROLLOVER_QUARTERLY = true
        const val DEFAULT_ROLLOVER_YEARLY = false
        const val DEFAULT_MINIMUM_ROLLOVER_AMOUNT = 10.0
        const val DEFAULT_ROLLOVER_PERCENTAGE = 50.0
        const val DEFAULT_FIXED_ROLLOVER_AMOUNT = 100.0
        const val DEFAULT_ROLLOVER_WEIGHT = 0.3
        const val DEFAULT_AUTO_ROLLOVER_ENABLED = true
        const val DEFAULT_ROLLOVER_NOTIFICATIONS = true
    }
    
    // Main rollover settings
    var rolloverEnabled: Boolean
        get() = prefs.getBoolean(PREF_ROLLOVER_ENABLED, DEFAULT_ROLLOVER_ENABLED)
        set(value) = prefs.edit().putBoolean(PREF_ROLLOVER_ENABLED, value).apply()
    
    var rolloverStrategy: BudgetRolloverStrategy
        get() {
            val strategyName = prefs.getString(PREF_ROLLOVER_STRATEGY, BudgetRolloverStrategy.SMART.name)
            return try {
                BudgetRolloverStrategy.valueOf(strategyName ?: BudgetRolloverStrategy.SMART.name)
            } catch (e: IllegalArgumentException) {
                BudgetRolloverStrategy.SMART
            }
        }
        set(value) = prefs.edit().putString(PREF_ROLLOVER_STRATEGY, value.name).apply()
    
    var rolloverBehavior: BudgetRolloverBehavior
        get() {
            val behaviorName = prefs.getString(PREF_ROLLOVER_BEHAVIOR, BudgetRolloverBehavior.ADD_TO_ORIGINAL.name)
            return try {
                BudgetRolloverBehavior.valueOf(behaviorName ?: BudgetRolloverBehavior.ADD_TO_ORIGINAL.name)
            } catch (e: IllegalArgumentException) {
                BudgetRolloverBehavior.ADD_TO_ORIGINAL
            }
        }
        set(value) = prefs.edit().putString(PREF_ROLLOVER_BEHAVIOR, value.name).apply()
    
    // Period-specific rollover settings
    var rolloverWeeklyBudgets: Boolean
        get() = prefs.getBoolean(PREF_ROLLOVER_WEEKLY, DEFAULT_ROLLOVER_WEEKLY)
        set(value) = prefs.edit().putBoolean(PREF_ROLLOVER_WEEKLY, value).apply()
    
    var rolloverMonthlyBudgets: Boolean
        get() = prefs.getBoolean(PREF_ROLLOVER_MONTHLY, DEFAULT_ROLLOVER_MONTHLY)
        set(value) = prefs.edit().putBoolean(PREF_ROLLOVER_MONTHLY, value).apply()
    
    var rolloverQuarterlyBudgets: Boolean
        get() = prefs.getBoolean(PREF_ROLLOVER_QUARTERLY, DEFAULT_ROLLOVER_QUARTERLY)
        set(value) = prefs.edit().putBoolean(PREF_ROLLOVER_QUARTERLY, value).apply()
    
    var rolloverYearlyBudgets: Boolean
        get() = prefs.getBoolean(PREF_ROLLOVER_YEARLY, DEFAULT_ROLLOVER_YEARLY)
        set(value) = prefs.edit().putBoolean(PREF_ROLLOVER_YEARLY, value).apply()
    
    // Amount settings
    var minimumRolloverAmount: Double
        get() = prefs.getFloat(PREF_MINIMUM_ROLLOVER_AMOUNT, DEFAULT_MINIMUM_ROLLOVER_AMOUNT.toFloat()).toDouble()
        set(value) = prefs.edit().putFloat(PREF_MINIMUM_ROLLOVER_AMOUNT, value.toFloat()).apply()
    
    var rolloverPercentage: Double
        get() = prefs.getFloat(PREF_ROLLOVER_PERCENTAGE, DEFAULT_ROLLOVER_PERCENTAGE.toFloat()).toDouble()
        set(value) = prefs.edit().putFloat(PREF_ROLLOVER_PERCENTAGE, value.toFloat()).apply()
    
    var fixedRolloverAmount: Double
        get() = prefs.getFloat(PREF_FIXED_ROLLOVER_AMOUNT, DEFAULT_FIXED_ROLLOVER_AMOUNT.toFloat()).toDouble()
        set(value) = prefs.edit().putFloat(PREF_FIXED_ROLLOVER_AMOUNT, value.toFloat()).apply()
    
    var rolloverWeight: Double
        get() = prefs.getFloat(PREF_ROLLOVER_WEIGHT, DEFAULT_ROLLOVER_WEIGHT.toFloat()).toDouble()
        set(value) = prefs.edit().putFloat(PREF_ROLLOVER_WEIGHT, value.toFloat()).apply()
    
    // Automation settings
    var autoRolloverEnabled: Boolean
        get() = prefs.getBoolean(PREF_AUTO_ROLLOVER_ENABLED, DEFAULT_AUTO_ROLLOVER_ENABLED)
        set(value) = prefs.edit().putBoolean(PREF_AUTO_ROLLOVER_ENABLED, value).apply()
    
    var rolloverNotificationsEnabled: Boolean
        get() = prefs.getBoolean(PREF_ROLLOVER_NOTIFICATIONS, DEFAULT_ROLLOVER_NOTIFICATIONS)
        set(value) = prefs.edit().putBoolean(PREF_ROLLOVER_NOTIFICATIONS, value).apply()
    
    fun getStrategyDisplayName(): String {
        return when (rolloverStrategy) {
            BudgetRolloverStrategy.FULL_AMOUNT -> "Hela beloppet"
            BudgetRolloverStrategy.PERCENTAGE -> "Procent av kvarvarande"
            BudgetRolloverStrategy.FIXED_AMOUNT -> "Fast belopp"
            BudgetRolloverStrategy.SMART -> "Smart rullning"
        }
    }
    
    fun getBehaviorDisplayName(): String {
        return when (rolloverBehavior) {
            BudgetRolloverBehavior.ADD_TO_ORIGINAL -> "Lägg till originalbudget"
            BudgetRolloverBehavior.REPLACE_WITH_ROLLOVER -> "Ersätt med rullat belopp"
            BudgetRolloverBehavior.WEIGHTED_AVERAGE -> "Viktad genomsnitt"
        }
    }
    
    fun getActivePeriodsCount(): Int {
        var count = 0
        if (rolloverWeeklyBudgets) count++
        if (rolloverMonthlyBudgets) count++
        if (rolloverQuarterlyBudgets) count++
        if (rolloverYearlyBudgets) count++
        return count
    }
    
    fun getRolloverSummary(): String {
        if (!rolloverEnabled) return "Automatisk rullning inaktiverad"
        
        val strategy = getStrategyDisplayName()
        val periodsCount = getActivePeriodsCount()
        val periodsText = when (periodsCount) {
            0 -> "inga perioder"
            1 -> "1 period"
            else -> "$periodsCount perioder"
        }
        
        return "$strategy för $periodsText"
    }
    
    fun resetToDefaults() {
        rolloverEnabled = DEFAULT_ROLLOVER_ENABLED
        rolloverStrategy = BudgetRolloverStrategy.SMART
        rolloverBehavior = BudgetRolloverBehavior.ADD_TO_ORIGINAL
        rolloverWeeklyBudgets = DEFAULT_ROLLOVER_WEEKLY
        rolloverMonthlyBudgets = DEFAULT_ROLLOVER_MONTHLY
        rolloverQuarterlyBudgets = DEFAULT_ROLLOVER_QUARTERLY
        rolloverYearlyBudgets = DEFAULT_ROLLOVER_YEARLY
        minimumRolloverAmount = DEFAULT_MINIMUM_ROLLOVER_AMOUNT
        rolloverPercentage = DEFAULT_ROLLOVER_PERCENTAGE
        fixedRolloverAmount = DEFAULT_FIXED_ROLLOVER_AMOUNT
        rolloverWeight = DEFAULT_ROLLOVER_WEIGHT
        autoRolloverEnabled = DEFAULT_AUTO_ROLLOVER_ENABLED
        rolloverNotificationsEnabled = DEFAULT_ROLLOVER_NOTIFICATIONS
    }
    
    fun isValidConfiguration(): Boolean {
        return when {
            !rolloverEnabled -> true // Always valid if disabled
            getActivePeriodsCount() == 0 -> false // Must have at least one period enabled
            minimumRolloverAmount < 0 -> false // Minimum amount must be positive
            rolloverPercentage < 0 || rolloverPercentage > 100 -> false // Percentage must be 0-100
            fixedRolloverAmount < 0 -> false // Fixed amount must be positive
            rolloverWeight < 0 || rolloverWeight > 1 -> false // Weight must be 0-1
            else -> true
        }
    }
    
    fun getValidationErrors(): List<String> {
        val errors = mutableListOf<String>()
        
        if (rolloverEnabled) {
            if (getActivePeriodsCount() == 0) {
                errors.add("Minst en budgetperiod måste vara aktiverad för rullning")
            }
            if (minimumRolloverAmount < 0) {
                errors.add("Minimibelopp för rullning måste vara positivt")
            }
            if (rolloverPercentage < 0 || rolloverPercentage > 100) {
                errors.add("Rullningsprocent måste vara mellan 0-100%")
            }
            if (fixedRolloverAmount < 0) {
                errors.add("Fast rullningsbelopp måste vara positivt")
            }
            if (rolloverWeight < 0 || rolloverWeight > 1) {
                errors.add("Rullningsvikt måste vara mellan 0-1")
            }
        }
        
        return errors
    }
}