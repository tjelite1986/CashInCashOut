package com.example.budgetapp.services

import android.content.Context
import androidx.work.*
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.utils.BudgetRolloverSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.NumberFormat
import java.util.*
import java.util.concurrent.TimeUnit

class BudgetRolloverWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        return@withContext try {
            val database = BudgetDatabase.getDatabase(applicationContext)
            val rolloverService = BudgetRolloverService(database, applicationContext)
            val rolloverSettings = BudgetRolloverSettings(applicationContext)
            
            // Check if automatic rollover is enabled
            if (!rolloverSettings.autoRolloverEnabled) {
                return@withContext Result.success()
            }
            
            // Process expired budgets
            val rolloverResult = rolloverService.processExpiredBudgets()
            
            // Send notification if any budgets were processed and notifications are enabled
            if (rolloverResult.processedBudgets > 0 && rolloverSettings.rolloverNotificationsEnabled) {
                sendRolloverNotification(rolloverResult)
            }
            
            // Log rollover activity
            logRolloverActivity(rolloverResult)
            
            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.retry()
        }
    }
    
    private fun sendRolloverNotification(result: BudgetRolloverResult) {
        try {
            val notificationService = BudgetNotificationService(applicationContext)
            val numberFormat = NumberFormat.getCurrencyInstance(Locale("sv", "SE"))
            
            val title = when {
                result.rolledOverBudgets == 0 -> "Budgetperioder avslutade"
                result.rolledOverBudgets == 1 -> "Budget rullad över"
                else -> "${result.rolledOverBudgets} budgetar rullades över"
            }
            
            val message = when {
                result.rolledOverBudgets == 0 -> {
                    "${result.processedBudgets} budgetperioder avslutades utan rullning"
                }
                result.totalRolloverAmount > 0 -> {
                    "Totalt ${numberFormat.format(result.totalRolloverAmount)} rullades över till nya budgetar"
                }
                else -> {
                    "${result.rolledOverBudgets} budgetar rullades över"
                }
            }
            
            notificationService.showNotification(
                id = 2001,
                channelId = BudgetNotificationService.CHANNEL_ID_BUDGET_SUMMARY,
                title = title,
                message = message,
                priority = android.app.Notification.PRIORITY_DEFAULT,
                icon = com.example.budgetapp.R.drawable.ic_budget
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    private fun logRolloverActivity(result: BudgetRolloverResult) {
        // In a production app, you might want to log this to analytics or a logging service
        println("Budget Rollover Activity:")
        println("  Processed budgets: ${result.processedBudgets}")
        println("  Rolled over budgets: ${result.rolledOverBudgets}")
        println("  Total rollover amount: ${result.totalRolloverAmount}")
        println("  New budgets created: ${result.newBudgetsCreated}")
    }
    
    companion object {
        const val WORK_NAME_BUDGET_ROLLOVER = "budget_rollover_work"
        
        fun scheduleRolloverCheck(context: Context) {
            val rolloverSettings = BudgetRolloverSettings(context)
            
            if (!rolloverSettings.autoRolloverEnabled) {
                // Cancel existing work if auto rollover is disabled
                WorkManager.getInstance(context).cancelUniqueWork(WORK_NAME_BUDGET_ROLLOVER)
                return
            }
            
            val rolloverRequest = PeriodicWorkRequestBuilder<BudgetRolloverWorker>(
                repeatInterval = 6, // Check every 6 hours
                repeatIntervalTimeUnit = TimeUnit.HOURS
            )
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                        .setRequiresBatteryNotLow(true)
                        .build()
                )
                .setBackoffCriteria(
                    BackoffPolicy.EXPONENTIAL,
                    WorkRequest.MIN_BACKOFF_MILLIS,
                    TimeUnit.MILLISECONDS
                )
                .build()
            
            WorkManager.getInstance(context)
                .enqueueUniquePeriodicWork(
                    WORK_NAME_BUDGET_ROLLOVER,
                    ExistingPeriodicWorkPolicy.KEEP,
                    rolloverRequest
                )
        }
        
        fun cancelRolloverWork(context: Context) {
            WorkManager.getInstance(context).cancelUniqueWork(WORK_NAME_BUDGET_ROLLOVER)
        }
        
        fun triggerImmediateRollover(context: Context) {
            val rolloverRequest = OneTimeWorkRequestBuilder<BudgetRolloverWorker>()
                .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
                .build()
            
            WorkManager.getInstance(context)
                .enqueueUniqueWork(
                    "immediate_budget_rollover",
                    ExistingWorkPolicy.REPLACE,
                    rolloverRequest
                )
        }
    }
}

