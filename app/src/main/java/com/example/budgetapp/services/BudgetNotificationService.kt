package com.example.budgetapp.services

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.budgetapp.MainActivity
import com.example.budgetapp.R
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.repository.BudgetRepository
import com.example.budgetapp.repository.BudgetProgress
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.*

class BudgetNotificationService(private val context: Context) {
    
    private val budgetRepository by lazy {
        BudgetRepository(BudgetDatabase.getDatabase(context))
    }
    
    private val notificationManager by lazy {
        NotificationManagerCompat.from(context)
    }
    
    private val numberFormat = NumberFormat.getCurrencyInstance(Locale("sv", "SE"))
    
    companion object {
        const val CHANNEL_ID_BUDGET_ALERTS = "budget_alerts"
        const val CHANNEL_ID_BUDGET_SUMMARY = "budget_summary"
        
        const val NOTIFICATION_ID_OVER_BUDGET = 1001
        const val NOTIFICATION_ID_NEAR_LIMIT = 1002
        const val NOTIFICATION_ID_DAILY_SUMMARY = 1003
        const val NOTIFICATION_ID_WEEKLY_SUMMARY = 1004
        const val NOTIFICATION_ID_PERIOD_END = 1005
        
        private const val PREFS_NAME = "budget_notifications"
        private const val PREF_LAST_ALERT_CHECK = "last_alert_check"
        private const val PREF_LAST_DAILY_SUMMARY = "last_daily_summary"
        private const val PREF_LAST_WEEKLY_SUMMARY = "last_weekly_summary"
    }
    
    init {
        createNotificationChannels()
    }
    
    private fun createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Budget alerts channel (high priority)
            val alertsChannel = NotificationChannel(
                CHANNEL_ID_BUDGET_ALERTS,
                "Budgetvarningar",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Varningar när du närmar dig eller överskrider dina budgetgränser"
                enableVibration(true)
                setShowBadge(true)
            }
            
            // Budget summary channel (normal priority)
            val summaryChannel = NotificationChannel(
                CHANNEL_ID_BUDGET_SUMMARY,
                "Budgetsammanfattningar",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Dagliga och veckovisa budgetsammanfattningar"
                setShowBadge(false)
            }
            
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(alertsChannel)
            manager.createNotificationChannel(summaryChannel)
        }
    }
    
    fun checkBudgetAlerts() {
        if (!hasNotificationPermission()) return
        
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val budgets = budgetRepository.getAllActiveBudgets()
                budgets.collect { budgetList ->
                    if (budgetList.isNotEmpty()) {
                        val progressList = budgetRepository.getBudgetProgressList(budgetList)
                        processAlerts(progressList)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    
    private suspend fun processAlerts(progressList: List<BudgetProgress>) {
        val overBudgetItems = progressList.filter { it.isOverBudget }
        val nearLimitItems = progressList.filter { it.isNearLimit && !it.isOverBudget }
        
        // Alert for over-budget items
        if (overBudgetItems.isNotEmpty()) {
            showOverBudgetAlert(overBudgetItems)
        }
        
        // Alert for near-limit items
        if (nearLimitItems.isNotEmpty()) {
            showNearLimitAlert(nearLimitItems)
        }
        
        updateLastAlertCheck()
    }
    
    private fun showOverBudgetAlert(overBudgetItems: List<BudgetProgress>) {
        val title = if (overBudgetItems.size == 1) {
            "Budget överskriden!"
        } else {
            "${overBudgetItems.size} budgetar överskridna!"
        }
        
        val message = if (overBudgetItems.size == 1) {
            val item = overBudgetItems.first()
            "${item.budget.name}: ${numberFormat.format(item.spentAmount)} av ${numberFormat.format(item.budget.budgetAmount)}"
        } else {
            val totalOver = overBudgetItems.sumOf { it.spentAmount - it.budget.budgetAmount }
            "Du har överskridit budgetar med totalt ${numberFormat.format(totalOver)}"
        }
        
        showNotification(
            id = NOTIFICATION_ID_OVER_BUDGET,
            channelId = CHANNEL_ID_BUDGET_ALERTS,
            title = title,
            message = message,
            priority = NotificationCompat.PRIORITY_HIGH,
            icon = R.drawable.ic_warning
        )
    }
    
    private fun showNearLimitAlert(nearLimitItems: List<BudgetProgress>) {
        val title = if (nearLimitItems.size == 1) {
            "Budget närmar sig gränsen"
        } else {
            "${nearLimitItems.size} budgetar nära gränsen"
        }
        
        val message = if (nearLimitItems.size == 1) {
            val item = nearLimitItems.first()
            "${item.budget.name}: ${item.percentage.toInt()}% använt (${numberFormat.format(item.remainingAmount)} kvar)"
        } else {
            val avgPercentage = nearLimitItems.map { it.percentage }.average().toInt()
            "Genomsnittligt $avgPercentage% av budgetar används"
        }
        
        showNotification(
            id = NOTIFICATION_ID_NEAR_LIMIT,
            channelId = CHANNEL_ID_BUDGET_ALERTS,
            title = title,
            message = message,
            priority = NotificationCompat.PRIORITY_DEFAULT,
            icon = R.drawable.ic_warning
        )
    }
    
    fun showDailySummary() {
        if (!hasNotificationPermission() || !shouldShowDailySummary()) return
        
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val budgets = budgetRepository.getAllActiveBudgets()
                budgets.collect { budgetList ->
                    if (budgetList.isNotEmpty()) {
                        val progressList = budgetRepository.getBudgetProgressList(budgetList)
                        generateDailySummary(progressList)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    
    private fun generateDailySummary(progressList: List<BudgetProgress>) {
        val totalBudget = progressList.sumOf { it.budget.budgetAmount }
        val totalSpent = progressList.sumOf { it.spentAmount }
        val avgPercentage = progressList.map { it.percentage }.average()
        
        val title = "Daglig budgetsammanfattning"
        val message = "Du har använt ${avgPercentage.toInt()}% av dina budgetar idag. " +
                "Spenderat: ${numberFormat.format(totalSpent)} av ${numberFormat.format(totalBudget)}"
        
        showNotification(
            id = NOTIFICATION_ID_DAILY_SUMMARY,
            channelId = CHANNEL_ID_BUDGET_SUMMARY,
            title = title,
            message = message,
            priority = NotificationCompat.PRIORITY_LOW,
            icon = R.drawable.ic_budget
        )
        
        updateLastDailySummary()
    }
    
    fun showWeeklySummary() {
        if (!hasNotificationPermission() || !shouldShowWeeklySummary()) return
        
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val budgets = budgetRepository.getAllActiveBudgets()
                budgets.collect { budgetList ->
                    if (budgetList.isNotEmpty()) {
                        val progressList = budgetRepository.getBudgetProgressList(budgetList)
                        generateWeeklySummary(progressList)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    
    private fun generateWeeklySummary(progressList: List<BudgetProgress>) {
        val onTrackCount = progressList.count { !it.isNearLimit && !it.isOverBudget }
        val nearLimitCount = progressList.count { it.isNearLimit && !it.isOverBudget }
        val overBudgetCount = progressList.count { it.isOverBudget }
        
        val title = "Veckosammanfattning"
        val message = buildString {
            append("På rätt spår: $onTrackCount, ")
            append("Nära gränsen: $nearLimitCount, ")
            append("Över budget: $overBudgetCount")
        }
        
        showNotification(
            id = NOTIFICATION_ID_WEEKLY_SUMMARY,
            channelId = CHANNEL_ID_BUDGET_SUMMARY,
            title = title,
            message = message,
            priority = NotificationCompat.PRIORITY_LOW,
            icon = R.drawable.ic_statistics
        )
        
        updateLastWeeklySummary()
    }
    
    fun showPeriodEndReminder(budgetName: String, daysLeft: Int) {
        if (!hasNotificationPermission()) return
        
        val title = "Budgetperiod slutar snart"
        val message = "$budgetName slutar om $daysLeft ${if (daysLeft == 1) "dag" else "dagar"}"
        
        showNotification(
            id = NOTIFICATION_ID_PERIOD_END,
            channelId = CHANNEL_ID_BUDGET_ALERTS,
            title = title,
            message = message,
            priority = NotificationCompat.PRIORITY_DEFAULT,
            icon = R.drawable.ic_calendar
        )
    }
    
    fun showNotification(
        id: Int,
        channelId: String,
        title: String,
        message: String,
        priority: Int,
        icon: Int
    ) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("navigate_to", "budget")
        }
        
        val pendingIntent = PendingIntent.getActivity(
            context,
            id,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(icon)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(priority)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .build()
        
        try {
            notificationManager.notify(id, notification)
        } catch (e: SecurityException) {
            // Permission denied, handle gracefully
            e.printStackTrace()
        }
    }
    
    private fun hasNotificationPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
    }
    
    private fun getPrefs() = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    
    private fun shouldShowDailySummary(): Boolean {
        val lastSummary = getPrefs().getLong(PREF_LAST_DAILY_SUMMARY, 0)
        val now = System.currentTimeMillis()
        val oneDayMs = 24 * 60 * 60 * 1000
        return now - lastSummary > oneDayMs
    }
    
    private fun shouldShowWeeklySummary(): Boolean {
        val lastSummary = getPrefs().getLong(PREF_LAST_WEEKLY_SUMMARY, 0)
        val now = System.currentTimeMillis()
        val oneWeekMs = 7 * 24 * 60 * 60 * 1000
        return now - lastSummary > oneWeekMs
    }
    
    private fun updateLastAlertCheck() {
        getPrefs().edit()
            .putLong(PREF_LAST_ALERT_CHECK, System.currentTimeMillis())
            .apply()
    }
    
    private fun updateLastDailySummary() {
        getPrefs().edit()
            .putLong(PREF_LAST_DAILY_SUMMARY, System.currentTimeMillis())
            .apply()
    }
    
    private fun updateLastWeeklySummary() {
        getPrefs().edit()
            .putLong(PREF_LAST_WEEKLY_SUMMARY, System.currentTimeMillis())
            .apply()
    }
    
    fun cancelAllNotifications() {
        notificationManager.cancelAll()
    }
    
    fun cancelNotification(id: Int) {
        notificationManager.cancel(id)
    }
}