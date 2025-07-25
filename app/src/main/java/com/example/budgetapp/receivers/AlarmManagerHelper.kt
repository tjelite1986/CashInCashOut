package com.example.budgetapp.receivers

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.ReminderType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AlarmManagerHelper(private val context: Context) {
    
    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    private val database = BudgetDatabase.getDatabase(context)
    
    companion object {
        private const val DAILY_IF_NOT_OPENED_REQUEST_CODE = 1001
        private const val DAILY_ALWAYS_REQUEST_CODE = 1002
        private const val HOURS_24_CHECK_REQUEST_CODE = 1003
        private const val UPCOMING_TRANSACTIONS_REQUEST_CODE = 1004
    }
    
    fun scheduleAllReminders() {
        CoroutineScope(Dispatchers.IO).launch {
            val enabledReminders = database.reminderSettingsDao().getEnabledReminders()
            
            enabledReminders.forEach { reminder ->
                when (reminder.reminderType) {
                    "DAILY_IF_NOT_OPENED" -> {
                        scheduleDailyReminder(
                            reminder.alertTime,
                            NotificationReceiver.ACTION_DAILY_IF_NOT_OPENED,
                            DAILY_IF_NOT_OPENED_REQUEST_CODE
                        )
                    }
                    "DAILY_ALWAYS" -> {
                        scheduleDailyReminder(
                            reminder.alertTime,
                            NotificationReceiver.ACTION_DAILY_ALWAYS,
                            DAILY_ALWAYS_REQUEST_CODE
                        )
                    }
                    "HOURS_24_FROM_LAST_OPEN" -> {
                        schedule24HoursCheck()
                    }
                }
            }
            
            scheduleUpcomingTransactionsCheck()
        }
    }
    
    private fun scheduleDailyReminder(alertTime: String, action: String, requestCode: Int) {
        try {
            val timeParts = alertTime.split(":")
            val hour = timeParts[0].toInt()
            val minute = timeParts[1].toInt()
            
            val calendar = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, hour)
                set(Calendar.MINUTE, minute)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
                
                // Om tiden redan passerat idag, schemalägg för imorgon
                if (timeInMillis <= System.currentTimeMillis()) {
                    add(Calendar.DAY_OF_YEAR, 1)
                }
            }
            
            val intent = Intent(context, NotificationReceiver::class.java).apply {
                this.action = action
                putExtra("alertTime", alertTime)
            }
            
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                requestCode,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            
            // Schemalägg återkommande alarm
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
            
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    private fun schedule24HoursCheck() {
        // Schemalägg kontroll var 4:e timme för att kolla om 24 timmar passerat
        val intent = Intent(context, NotificationReceiver::class.java).apply {
            action = NotificationReceiver.ACTION_24_HOURS_CHECK
        }
        
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            HOURS_24_CHECK_REQUEST_CODE,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        // Starta om 4 timmar och upprepa var 4:e timme
        val triggerTime = System.currentTimeMillis() + (4 * 60 * 60 * 1000) // 4 timmar
        
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            triggerTime,
            4 * 60 * 60 * 1000L, // 4 timmar
            pendingIntent
        )
    }
    
    private fun scheduleUpcomingTransactionsCheck() {
        // Schemalägg kontroll av kommande transaktioner en gång per dag kl 09:00
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 9)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            
            // Om tiden redan passerat idag, schemalägg för imorgon
            if (timeInMillis <= System.currentTimeMillis()) {
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }
        
        val intent = Intent(context, NotificationReceiver::class.java).apply {
            action = NotificationReceiver.ACTION_UPCOMING_TRANSACTIONS
        }
        
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            UPCOMING_TRANSACTIONS_REQUEST_CODE,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }
    
    fun cancelAllReminders() {
        val requestCodes = listOf(
            DAILY_IF_NOT_OPENED_REQUEST_CODE,
            DAILY_ALWAYS_REQUEST_CODE,
            HOURS_24_CHECK_REQUEST_CODE,
            UPCOMING_TRANSACTIONS_REQUEST_CODE
        )
        
        requestCodes.forEach { requestCode ->
            val intent = Intent(context, NotificationReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                requestCode,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
            alarmManager.cancel(pendingIntent)
        }
    }
    
    fun cancelReminder(reminderType: String) {
        val requestCode = when (reminderType) {
            "DAILY_IF_NOT_OPENED" -> DAILY_IF_NOT_OPENED_REQUEST_CODE
            "DAILY_ALWAYS" -> DAILY_ALWAYS_REQUEST_CODE
            "HOURS_24_FROM_LAST_OPEN" -> HOURS_24_CHECK_REQUEST_CODE
            else -> return
        }
        
        val intent = Intent(context, NotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            requestCode,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(pendingIntent)
    }
}