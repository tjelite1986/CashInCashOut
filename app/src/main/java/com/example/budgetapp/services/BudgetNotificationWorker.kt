package com.example.budgetapp.services

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

class BudgetNotificationWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    
    override suspend fun doWork(): Result {
        return try {
            val notificationService = BudgetNotificationService(applicationContext)
            
            when (inputData.getString(KEY_NOTIFICATION_TYPE)) {
                TYPE_BUDGET_ALERTS -> {
                    notificationService.checkBudgetAlerts()
                }
                TYPE_DAILY_SUMMARY -> {
                    notificationService.showDailySummary()
                }
                TYPE_WEEKLY_SUMMARY -> {
                    notificationService.showWeeklySummary()
                }
            }
            
            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.retry()
        }
    }
    
    companion object {
        const val WORK_NAME_BUDGET_ALERTS = "budget_alerts_work"
        const val WORK_NAME_DAILY_SUMMARY = "daily_summary_work"
        const val WORK_NAME_WEEKLY_SUMMARY = "weekly_summary_work"
        
        const val KEY_NOTIFICATION_TYPE = "notification_type"
        const val TYPE_BUDGET_ALERTS = "budget_alerts"
        const val TYPE_DAILY_SUMMARY = "daily_summary"
        const val TYPE_WEEKLY_SUMMARY = "weekly_summary"
        
        fun scheduleBudgetAlerts(context: Context) {
            val alertsRequest = PeriodicWorkRequestBuilder<BudgetNotificationWorker>(
                repeatInterval = 4, // Check every 4 hours
                repeatIntervalTimeUnit = TimeUnit.HOURS
            )
                .setInputData(
                    workDataOf(KEY_NOTIFICATION_TYPE to TYPE_BUDGET_ALERTS)
                )
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                        .setRequiresBatteryNotLow(true)
                        .build()
                )
                .build()
            
            WorkManager.getInstance(context)
                .enqueueUniquePeriodicWork(
                    WORK_NAME_BUDGET_ALERTS,
                    ExistingPeriodicWorkPolicy.KEEP,
                    alertsRequest
                )
        }
        
        fun scheduleDailySummary(context: Context) {
            val dailyRequest = PeriodicWorkRequestBuilder<BudgetNotificationWorker>(
                repeatInterval = 1,
                repeatIntervalTimeUnit = TimeUnit.DAYS
            )
                .setInputData(
                    workDataOf(KEY_NOTIFICATION_TYPE to TYPE_DAILY_SUMMARY)
                )
                .setInitialDelay(calculateDelayToTime(20, 0), TimeUnit.MILLISECONDS) // 8:00 PM
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                        .build()
                )
                .build()
            
            WorkManager.getInstance(context)
                .enqueueUniquePeriodicWork(
                    WORK_NAME_DAILY_SUMMARY,
                    ExistingPeriodicWorkPolicy.KEEP,
                    dailyRequest
                )
        }
        
        fun scheduleWeeklySummary(context: Context) {
            val weeklyRequest = PeriodicWorkRequestBuilder<BudgetNotificationWorker>(
                repeatInterval = 7,
                repeatIntervalTimeUnit = TimeUnit.DAYS
            )
                .setInputData(
                    workDataOf(KEY_NOTIFICATION_TYPE to TYPE_WEEKLY_SUMMARY)
                )
                .setInitialDelay(calculateDelayToWeeklyTime(), TimeUnit.MILLISECONDS) // Sunday 6:00 PM
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                        .build()
                )
                .build()
            
            WorkManager.getInstance(context)
                .enqueueUniquePeriodicWork(
                    WORK_NAME_WEEKLY_SUMMARY,
                    ExistingPeriodicWorkPolicy.KEEP,
                    weeklyRequest
                )
        }
        
        fun cancelAllNotificationWork(context: Context) {
            WorkManager.getInstance(context).apply {
                cancelUniqueWork(WORK_NAME_BUDGET_ALERTS)
                cancelUniqueWork(WORK_NAME_DAILY_SUMMARY)
                cancelUniqueWork(WORK_NAME_WEEKLY_SUMMARY)
            }
        }
        
        private fun calculateDelayToTime(hour: Int, minute: Int): Long {
            val calendar = java.util.Calendar.getInstance()
            val now = calendar.timeInMillis
            
            calendar.set(java.util.Calendar.HOUR_OF_DAY, hour)
            calendar.set(java.util.Calendar.MINUTE, minute)
            calendar.set(java.util.Calendar.SECOND, 0)
            
            var targetTime = calendar.timeInMillis
            
            // If the time has already passed today, schedule for tomorrow
            if (targetTime <= now) {
                targetTime += 24 * 60 * 60 * 1000 // Add 24 hours
            }
            
            return targetTime - now
        }
        
        private fun calculateDelayToWeeklyTime(): Long {
            val calendar = java.util.Calendar.getInstance()
            val now = calendar.timeInMillis
            
            // Set to Sunday 6:00 PM
            calendar.set(java.util.Calendar.DAY_OF_WEEK, java.util.Calendar.SUNDAY)
            calendar.set(java.util.Calendar.HOUR_OF_DAY, 18)
            calendar.set(java.util.Calendar.MINUTE, 0)
            calendar.set(java.util.Calendar.SECOND, 0)
            
            var targetTime = calendar.timeInMillis
            
            // If the time has already passed this week, schedule for next week
            if (targetTime <= now) {
                targetTime += 7 * 24 * 60 * 60 * 1000 // Add 7 days
            }
            
            return targetTime - now
        }
    }
}