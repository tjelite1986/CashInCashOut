package com.example.budgetapp.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.budgetapp.MainActivity
import com.example.budgetapp.R
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.ReminderType
import com.example.budgetapp.database.entities.Income
import com.example.budgetapp.database.entities.Expense
import com.example.budgetapp.database.entities.Loan
import com.example.budgetapp.database.entities.TransactionNotification
import com.example.budgetapp.database.entities.TransactionType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit

class NotificationService(private val context: Context) {
    
    private val notificationManager = NotificationManagerCompat.from(context)
    private val database = BudgetDatabase.getDatabase(context)
    
    companion object {
        const val CHANNEL_ID = "budget_reminders"
        const val CHANNEL_NAME = "Budget Påminnelser"
        const val CHANNEL_DESCRIPTION = "Påminnelser för budget och transaktioner"
        
        // Notification IDs
        const val DAILY_REMINDER_ID = 1001
        const val HOURS_24_REMINDER_ID = 1002
        const val DAILY_ALWAYS_REMINDER_ID = 1003
        const val UPCOMING_TRANSACTIONS_ID = 1004
        const val SPECIFIC_TRANSACTION_BASE_ID = 2000 // Base ID for specific transaction notifications
    }
    
    init {
        createNotificationChannel()
    }
    
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = CHANNEL_DESCRIPTION
                enableVibration(true)
                setShowBadge(true)
            }
            
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
    
    fun scheduleAllReminders() {
        CoroutineScope(Dispatchers.IO).launch {
            val enabledReminders = database.reminderSettingsDao().getEnabledReminders()
            
            enabledReminders.forEach { reminder ->
                when (reminder.reminderType) {
                    "DAILY_IF_NOT_OPENED" -> scheduleDailyIfNotOpenedReminder(reminder.alertTime)
                    "HOURS_24_FROM_LAST_OPEN" -> schedule24HoursFromLastOpenReminder()
                    "DAILY_ALWAYS" -> scheduleDailyAlwaysReminder(reminder.alertTime)
                }
            }
            
            scheduleUpcomingTransactionsCheck()
            scheduleSpecificTransactionNotifications()
        }
    }
    
    fun scheduleDailyIfNotOpenedReminder(alertTime: String) {
        // Denna kommer att kallas av AlarmManager
        CoroutineScope(Dispatchers.IO).launch {
            val lastOpened = database.appUsageDao().getLastOpenedTime()
            val today = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }.timeInMillis
            
            // Om appen inte öppnats idag, visa notifikation
            if (lastOpened == null || lastOpened < today) {
                showReminderNotification(
                    DAILY_REMINDER_ID,
                    "Glöm inte din budget!",
                    "Du har inte öppnat appen idag. Dags att registrera dina utgifter?"
                )
            }
        }
    }
    
    fun schedule24HoursFromLastOpenReminder() {
        CoroutineScope(Dispatchers.IO).launch {
            val lastOpened = database.appUsageDao().getLastOpenedTime()
            
            if (lastOpened != null) {
                val hoursSinceLastOpen = TimeUnit.MILLISECONDS.toHours(
                    System.currentTimeMillis() - lastOpened
                )
                
                if (hoursSinceLastOpen >= 24) {
                    showReminderNotification(
                        HOURS_24_REMINDER_ID,
                        "24 timmar sedan senaste besöket",
                        "Det har gått 24 timmar sedan du senast använde appen. Kolla din budget!"
                    )
                }
            }
        }
    }
    
    fun scheduleDailyAlwaysReminder(alertTime: String) {
        showReminderNotification(
            DAILY_ALWAYS_REMINDER_ID,
            "Daglig budget-påminnelse",
            "Dags att kolla din budget och registrera eventuella nya transaktioner!"
        )
    }
    
    fun scheduleUpcomingTransactionsCheck() {
        CoroutineScope(Dispatchers.IO).launch {
            val upcomingTransactions = getUpcomingTransactions()
            
            if (upcomingTransactions.isNotEmpty()) {
                val transactionText = upcomingTransactions.joinToString("\n") { 
                    "• ${it.first}: ${it.second} kr" 
                }
                
                showReminderNotification(
                    UPCOMING_TRANSACTIONS_ID,
                    "Kommande återkommande transaktioner",
                    "Du har ${upcomingTransactions.size} kommande transaktioner:\n$transactionText"
                )
            }
        }
    }
    
    private suspend fun getUpcomingTransactions(): List<Pair<String, Double>> {
        val upcomingTransactions = mutableListOf<Pair<String, Double>>()
        val today = System.currentTimeMillis()
        
        // Hämta återkommande inkomster som ska träffa inom de närmaste 7 dagarna
        val recurringIncomes = database.incomeDao().getRecurringIncomes()
        recurringIncomes.forEach { income ->
            if (shouldShowUpcomingTransaction(income.date, income.recurringType, today)) {
                upcomingTransactions.add(Pair("Inkomst: ${income.title}", income.amount))
            }
        }
        
        // Hämta återkommande utgifter som ska träffa inom de närmaste 7 dagarna
        val recurringExpenses = database.expenseDao().getRecurringExpenses()
        recurringExpenses.forEach { expense ->
            if (shouldShowUpcomingTransaction(expense.date, expense.recurringType, today)) {
                upcomingTransactions.add(Pair("Utgift: ${expense.title}", expense.amount))
            }
        }
        
        return upcomingTransactions.take(5) // Visa max 5 transaktioner
    }
    
    private fun shouldShowUpcomingTransaction(transactionDate: Long, recurringType: String?, today: Long): Boolean {
        if (recurringType == null) return false
        
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = transactionDate
        
        val todayCalendar = Calendar.getInstance()
        todayCalendar.timeInMillis = today
        
        return when (recurringType) {
            "MONTHLY" -> {
                val nextMonth = Calendar.getInstance().apply {
                    timeInMillis = today
                    add(Calendar.MONTH, 1)
                }
                
                // Om transaktionsdagen i nästa månad är inom 7 dagar
                val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
                nextMonth.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                
                val daysDifference = TimeUnit.MILLISECONDS.toDays(
                    nextMonth.timeInMillis - today
                )
                
                daysDifference in 0..7
            }
            "WEEKLY" -> {
                val nextWeek = Calendar.getInstance().apply {
                    timeInMillis = today
                    add(Calendar.WEEK_OF_YEAR, 1)
                }
                
                val daysDifference = TimeUnit.MILLISECONDS.toDays(
                    nextWeek.timeInMillis - today
                )
                
                daysDifference in 0..7
            }
            "YEARLY" -> {
                val nextYear = Calendar.getInstance().apply {
                    timeInMillis = today
                    add(Calendar.YEAR, 1)
                }
                
                val daysDifference = TimeUnit.MILLISECONDS.toDays(
                    nextYear.timeInMillis - today
                )
                
                daysDifference in 0..7
            }
            else -> false
        }
    }
    
    fun showReminderNotification(id: Int, title: String, message: String) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_budget)
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()
        
        try {
            notificationManager.notify(id, notification)
        } catch (e: SecurityException) {
            // Hantera fallet där användaren inte har gett notifikationstillstånd
            e.printStackTrace()
        }
    }
    
    fun updateAppUsage() {
        CoroutineScope(Dispatchers.IO).launch {
            val currentTime = System.currentTimeMillis()
            database.appUsageDao().updateLastOpened(currentTime)
        }
    }
    
    fun cancelAllNotifications() {
        notificationManager.cancelAll()
    }
    
    fun cancelNotification(id: Int) {
        notificationManager.cancel(id)
    }
    
    // New methods for specific transaction notifications
    
    fun scheduleSpecificTransactionNotifications() {
        CoroutineScope(Dispatchers.IO).launch {
            val activeNotifications = database.transactionNotificationDao().getActiveNotifications()
            
            activeNotifications.forEach { notification ->
                checkAndShowSpecificTransactionNotification(notification)
            }
        }
    }
    
    private suspend fun checkAndShowSpecificTransactionNotification(notification: TransactionNotification) {
        val currentTime = System.currentTimeMillis()
        val reminderTime = getReminderTimeForTransaction(notification, currentTime)
        
        if (reminderTime != null && currentTime >= reminderTime) {
            val transactionDetails = getTransactionDetails(notification)
            if (transactionDetails != null) {
                val (title, amount, description) = transactionDetails
                
                val notificationTitle = notification.customMessage 
                    ?: "Påminnelse: ${notification.transactionTitle}"
                
                val notificationMessage = when (notification.transactionType) {
                    TransactionType.INCOME -> "Inkomst: $title - ${amount} kr"
                    TransactionType.EXPENSE -> "Utgift: $title - ${amount} kr"
                    TransactionType.LOAN_DUE -> "Lån förfaller: $title - ${amount} kr"
                }
                
                val fullMessage = if (description.isNotEmpty() && description != title) {
                    "$notificationMessage\n$description"
                } else {
                    notificationMessage
                }
                
                showReminderNotification(
                    SPECIFIC_TRANSACTION_BASE_ID + notification.id.toInt(),
                    notificationTitle,
                    fullMessage
                )
            }
        }
    }
    
    private suspend fun getReminderTimeForTransaction(
        notification: TransactionNotification,
        currentTime: Long
    ): Long? {
        val transactionDetails = getTransactionDetails(notification) ?: return null
        val (_, _, _, date, isRecurring, recurringType) = transactionDetails
        
        if (!isRecurring) {
            // For non-recurring transactions, remind X days before the transaction date
            val daysBefore = notification.reminderDaysBefore
            val reminderTime = date - (daysBefore * 24 * 60 * 60 * 1000L)
            return if (reminderTime > currentTime - (24 * 60 * 60 * 1000L)) reminderTime else null
        } else {
            // For recurring transactions, calculate next occurrence and remind before that
            val nextOccurrence = calculateNextRecurringOccurrence(date, recurringType, currentTime)
            if (nextOccurrence != null) {
                val daysBefore = notification.reminderDaysBefore
                val reminderTime = nextOccurrence - (daysBefore * 24 * 60 * 60 * 1000L)
                return if (reminderTime > currentTime - (24 * 60 * 60 * 1000L)) reminderTime else null
            }
        }
        
        return null
    }
    
    private suspend fun getTransactionDetails(notification: TransactionNotification): TransactionDetails? {
        return when (notification.transactionType) {
            TransactionType.INCOME -> {
                database.incomeDao().getIncomeById(notification.transactionId)?.let { income ->
                    TransactionDetails(
                        title = income.title,
                        amount = income.amount,
                        description = income.description ?: "",
                        date = income.date,
                        isRecurring = income.isRecurring,
                        recurringType = income.recurringType
                    )
                }
            }
            TransactionType.EXPENSE -> {
                database.expenseDao().getExpenseById(notification.transactionId)?.let { expense ->
                    TransactionDetails(
                        title = expense.title,
                        amount = expense.amount,
                        description = expense.description ?: "",
                        date = expense.date,
                        isRecurring = expense.isRecurring,
                        recurringType = expense.recurringType
                    )
                }
            }
            TransactionType.LOAN_DUE -> {
                database.loanDao().getLoanById(notification.transactionId)?.let { loan ->
                    TransactionDetails(
                        title = loan.title,
                        amount = loan.amount,
                        description = loan.description ?: "",
                        date = loan.dueDate ?: loan.createdAt,
                        isRecurring = false, // Loans are typically not recurring
                        recurringType = null
                    )
                }
            }
        }
    }
    
    private fun calculateNextRecurringOccurrence(
        originalDate: Long,
        recurringType: String?,
        currentTime: Long
    ): Long? {
        if (recurringType == null) return null
        
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = originalDate
        val currentCalendar = Calendar.getInstance()
        currentCalendar.timeInMillis = currentTime
        
        when (recurringType) {
            "WEEKLY" -> {
                while (calendar.timeInMillis <= currentTime) {
                    calendar.add(Calendar.WEEK_OF_YEAR, 1)
                }
                return calendar.timeInMillis
            }
            "MONTHLY" -> {
                while (calendar.timeInMillis <= currentTime) {
                    calendar.add(Calendar.MONTH, 1)
                }
                return calendar.timeInMillis
            }
            "YEARLY" -> {
                while (calendar.timeInMillis <= currentTime) {
                    calendar.add(Calendar.YEAR, 1)
                }
                return calendar.timeInMillis
            }
            else -> return null
        }
    }
    
    fun showSpecificTransactionNotification(transactionNotification: TransactionNotification) {
        CoroutineScope(Dispatchers.IO).launch {
            checkAndShowSpecificTransactionNotification(transactionNotification)
        }
    }
    
    fun cancelSpecificTransactionNotification(transactionNotificationId: Long) {
        cancelNotification(SPECIFIC_TRANSACTION_BASE_ID + transactionNotificationId.toInt())
    }
    
    // Data class for transaction details
    private data class TransactionDetails(
        val title: String,
        val amount: Double,
        val description: String,
        val date: Long,
        val isRecurring: Boolean,
        val recurringType: String?
    )
}