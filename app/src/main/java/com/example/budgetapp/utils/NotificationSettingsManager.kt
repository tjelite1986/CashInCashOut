package com.example.budgetapp.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.budgetapp.services.BudgetNotificationWorker

class NotificationSettingsManager(context: Context) {
    
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val context = context.applicationContext
    
    companion object {
        private const val PREFS_NAME = "notification_settings"
        
        // Notification preferences
        const val PREF_BUDGET_ALERTS_ENABLED = "budget_alerts_enabled"
        const val PREF_DAILY_SUMMARY_ENABLED = "daily_summary_enabled"
        const val PREF_WEEKLY_SUMMARY_ENABLED = "weekly_summary_enabled"
        const val PREF_PERIOD_END_REMINDERS = "period_end_reminders"
        
        // Alert thresholds
        const val PREF_ALERT_THRESHOLD = "alert_threshold"
        const val PREF_OVER_BUDGET_ALERTS = "over_budget_alerts"
        
        // Timing preferences
        const val PREF_DAILY_SUMMARY_TIME = "daily_summary_time"
        const val PREF_WEEKLY_SUMMARY_DAY = "weekly_summary_day"
        
        // Default values
        const val DEFAULT_BUDGET_ALERTS = true
        const val DEFAULT_DAILY_SUMMARY = false
        const val DEFAULT_WEEKLY_SUMMARY = true
        const val DEFAULT_PERIOD_END_REMINDERS = true
        const val DEFAULT_ALERT_THRESHOLD = 80.0
        const val DEFAULT_OVER_BUDGET_ALERTS = true
        const val DEFAULT_DAILY_SUMMARY_TIME = "20:00" // 8:00 PM
        const val DEFAULT_WEEKLY_SUMMARY_DAY = 1 // Sunday
    }
    
    // Budget alerts
    var budgetAlertsEnabled: Boolean
        get() = prefs.getBoolean(PREF_BUDGET_ALERTS_ENABLED, DEFAULT_BUDGET_ALERTS)
        set(value) {
            prefs.edit().putBoolean(PREF_BUDGET_ALERTS_ENABLED, value).apply()
            updateWorkScheduling()
        }
    
    var overBudgetAlertsEnabled: Boolean
        get() = prefs.getBoolean(PREF_OVER_BUDGET_ALERTS, DEFAULT_OVER_BUDGET_ALERTS)
        set(value) = prefs.edit().putBoolean(PREF_OVER_BUDGET_ALERTS, value).apply()
    
    var alertThreshold: Double
        get() = prefs.getFloat(PREF_ALERT_THRESHOLD, DEFAULT_ALERT_THRESHOLD.toFloat()).toDouble()
        set(value) = prefs.edit().putFloat(PREF_ALERT_THRESHOLD, value.toFloat()).apply()
    
    // Daily summary
    var dailySummaryEnabled: Boolean
        get() = prefs.getBoolean(PREF_DAILY_SUMMARY_ENABLED, DEFAULT_DAILY_SUMMARY)
        set(value) {
            prefs.edit().putBoolean(PREF_DAILY_SUMMARY_ENABLED, value).apply()
            updateWorkScheduling()
        }
    
    var dailySummaryTime: String
        get() = prefs.getString(PREF_DAILY_SUMMARY_TIME, DEFAULT_DAILY_SUMMARY_TIME) ?: DEFAULT_DAILY_SUMMARY_TIME
        set(value) {
            prefs.edit().putString(PREF_DAILY_SUMMARY_TIME, value).apply()
            if (dailySummaryEnabled) {
                BudgetNotificationWorker.scheduleDailySummary(context)
            }
        }
    
    // Weekly summary
    var weeklySummaryEnabled: Boolean
        get() = prefs.getBoolean(PREF_WEEKLY_SUMMARY_ENABLED, DEFAULT_WEEKLY_SUMMARY)
        set(value) {
            prefs.edit().putBoolean(PREF_WEEKLY_SUMMARY_ENABLED, value).apply()
            updateWorkScheduling()
        }
    
    var weeklySummaryDay: Int
        get() = prefs.getInt(PREF_WEEKLY_SUMMARY_DAY, DEFAULT_WEEKLY_SUMMARY_DAY)
        set(value) {
            prefs.edit().putInt(PREF_WEEKLY_SUMMARY_DAY, value).apply()
            if (weeklySummaryEnabled) {
                BudgetNotificationWorker.scheduleWeeklySummary(context)
            }
        }
    
    // Period end reminders
    var periodEndRemindersEnabled: Boolean
        get() = prefs.getBoolean(PREF_PERIOD_END_REMINDERS, DEFAULT_PERIOD_END_REMINDERS)
        set(value) = prefs.edit().putBoolean(PREF_PERIOD_END_REMINDERS, value).apply()
    
    fun initializeNotifications() {
        updateWorkScheduling()
    }
    
    private fun updateWorkScheduling() {
        // Cancel all existing work
        BudgetNotificationWorker.cancelAllNotificationWork(context)
        
        // Schedule enabled notifications
        if (budgetAlertsEnabled) {
            BudgetNotificationWorker.scheduleBudgetAlerts(context)
        }
        
        if (dailySummaryEnabled) {
            BudgetNotificationWorker.scheduleDailySummary(context)
        }
        
        if (weeklySummaryEnabled) {
            BudgetNotificationWorker.scheduleWeeklySummary(context)
        }
    }
    
    fun disableAllNotifications() {
        budgetAlertsEnabled = false
        dailySummaryEnabled = false
        weeklySummaryEnabled = false
        periodEndRemindersEnabled = false
        
        BudgetNotificationWorker.cancelAllNotificationWork(context)
    }
    
    fun enableDefaultNotifications() {
        budgetAlertsEnabled = DEFAULT_BUDGET_ALERTS
        dailySummaryEnabled = DEFAULT_DAILY_SUMMARY
        weeklySummaryEnabled = DEFAULT_WEEKLY_SUMMARY
        periodEndRemindersEnabled = DEFAULT_PERIOD_END_REMINDERS
        
        updateWorkScheduling()
    }
    
    fun getNotificationSummary(): String {
        val enabledNotifications = mutableListOf<String>()
        
        if (budgetAlertsEnabled) enabledNotifications.add("Budgetvarningar")
        if (dailySummaryEnabled) enabledNotifications.add("Daglig sammanfattning")
        if (weeklySummaryEnabled) enabledNotifications.add("Veckosammanfattning")
        if (periodEndRemindersEnabled) enabledNotifications.add("Periodpåminnelser")
        
        return if (enabledNotifications.isEmpty()) {
            "Inga notifikationer aktiverade"
        } else {
            enabledNotifications.joinToString(", ")
        }
    }
}