package com.example.budgetapp.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.budgetapp.services.NotificationService

class NotificationReceiver : BroadcastReceiver() {
    
    companion object {
        const val ACTION_DAILY_IF_NOT_OPENED = "com.example.budgetapp.DAILY_IF_NOT_OPENED"
        const val ACTION_DAILY_ALWAYS = "com.example.budgetapp.DAILY_ALWAYS"
        const val ACTION_24_HOURS_CHECK = "com.example.budgetapp.24_HOURS_CHECK"
        const val ACTION_UPCOMING_TRANSACTIONS = "com.example.budgetapp.UPCOMING_TRANSACTIONS"
        const val ACTION_BOOT_COMPLETED = "android.intent.action.BOOT_COMPLETED"
        const val ACTION_MY_PACKAGE_REPLACED = "android.intent.action.MY_PACKAGE_REPLACED"
    }
    
    override fun onReceive(context: Context, intent: Intent) {
        val notificationService = NotificationService(context)
        
        when (intent.action) {
            ACTION_DAILY_IF_NOT_OPENED -> {
                val alertTime = intent.getStringExtra("alertTime") ?: "20:00"
                notificationService.scheduleDailyIfNotOpenedReminder(alertTime)
            }
            
            ACTION_DAILY_ALWAYS -> {
                val alertTime = intent.getStringExtra("alertTime") ?: "20:00"
                notificationService.scheduleDailyAlwaysReminder(alertTime)
            }
            
            ACTION_24_HOURS_CHECK -> {
                notificationService.schedule24HoursFromLastOpenReminder()
            }
            
            ACTION_UPCOMING_TRANSACTIONS -> {
                notificationService.scheduleUpcomingTransactionsCheck()
            }
            
            ACTION_BOOT_COMPLETED, ACTION_MY_PACKAGE_REPLACED -> {
                // Återställ alla påminnelser efter omstart eller appuppdatering
                val alarmManager = AlarmManagerHelper(context)
                alarmManager.scheduleAllReminders()
            }
        }
    }
}