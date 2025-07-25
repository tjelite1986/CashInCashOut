package com.example.budgetapp.services;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u000b\u0018\u0000 62\u00020\u0001:\u000267B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J)\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\nH\u0002\u00a2\u0006\u0002\u0010\u000fJ\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\nJ\u0019\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0019H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001aJ\b\u0010\u001b\u001a\u00020\u0011H\u0002J#\u0010\u001c\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u000e\u001a\u00020\nH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001dJ\u001b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010\u0018\u001a\u00020\u0019H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001aJ#\u0010 \u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020#0\"0!H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010$J\u0006\u0010%\u001a\u00020\u0011J\u0006\u0010&\u001a\u00020\u0011J\u000e\u0010\'\u001a\u00020\u00112\u0006\u0010(\u001a\u00020\rJ\u000e\u0010)\u001a\u00020\u00112\u0006\u0010(\u001a\u00020\rJ\u0006\u0010*\u001a\u00020\u0011J\u0006\u0010+\u001a\u00020\u0011J\"\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010/\u001a\u00020\nH\u0002J\u001e\u00100\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u00101\u001a\u00020\r2\u0006\u00102\u001a\u00020\rJ\u000e\u00103\u001a\u00020\u00112\u0006\u00104\u001a\u00020\u0019J\u0006\u00105\u001a\u00020\u0011R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00068"}, d2 = {"Lcom/example/budgetapp/services/NotificationService;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "database", "Lcom/example/budgetapp/database/BudgetDatabase;", "notificationManager", "Landroidx/core/app/NotificationManagerCompat;", "calculateNextRecurringOccurrence", "", "originalDate", "recurringType", "", "currentTime", "(JLjava/lang/String;J)Ljava/lang/Long;", "cancelAllNotifications", "", "cancelNotification", "id", "", "cancelSpecificTransactionNotification", "transactionNotificationId", "checkAndShowSpecificTransactionNotification", "notification", "Lcom/example/budgetapp/database/entities/TransactionNotification;", "(Lcom/example/budgetapp/database/entities/TransactionNotification;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createNotificationChannel", "getReminderTimeForTransaction", "(Lcom/example/budgetapp/database/entities/TransactionNotification;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTransactionDetails", "Lcom/example/budgetapp/services/NotificationService$TransactionDetails;", "getUpcomingTransactions", "", "Lkotlin/Pair;", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "schedule24HoursFromLastOpenReminder", "scheduleAllReminders", "scheduleDailyAlwaysReminder", "alertTime", "scheduleDailyIfNotOpenedReminder", "scheduleSpecificTransactionNotifications", "scheduleUpcomingTransactionsCheck", "shouldShowUpcomingTransaction", "", "transactionDate", "today", "showReminderNotification", "title", "message", "showSpecificTransactionNotification", "transactionNotification", "updateAppUsage", "Companion", "TransactionDetails", "CashInCashOut_debug"})
public final class NotificationService {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.core.app.NotificationManagerCompat notificationManager = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.BudgetDatabase database = null;
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String CHANNEL_ID = "budget_reminders";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String CHANNEL_NAME = "Budget P\u00e5minnelser";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String CHANNEL_DESCRIPTION = "P\u00e5minnelser f\u00f6r budget och transaktioner";
    public static final int DAILY_REMINDER_ID = 1001;
    public static final int HOURS_24_REMINDER_ID = 1002;
    public static final int DAILY_ALWAYS_REMINDER_ID = 1003;
    public static final int UPCOMING_TRANSACTIONS_ID = 1004;
    public static final int SPECIFIC_TRANSACTION_BASE_ID = 2000;
    @org.jetbrains.annotations.NotNull
    public static final com.example.budgetapp.services.NotificationService.Companion Companion = null;
    
    public NotificationService(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    private final void createNotificationChannel() {
    }
    
    public final void scheduleAllReminders() {
    }
    
    public final void scheduleDailyIfNotOpenedReminder(@org.jetbrains.annotations.NotNull
    java.lang.String alertTime) {
    }
    
    public final void schedule24HoursFromLastOpenReminder() {
    }
    
    public final void scheduleDailyAlwaysReminder(@org.jetbrains.annotations.NotNull
    java.lang.String alertTime) {
    }
    
    public final void scheduleUpcomingTransactionsCheck() {
    }
    
    private final java.lang.Object getUpcomingTransactions(kotlin.coroutines.Continuation<? super java.util.List<kotlin.Pair<java.lang.String, java.lang.Double>>> $completion) {
        return null;
    }
    
    private final boolean shouldShowUpcomingTransaction(long transactionDate, java.lang.String recurringType, long today) {
        return false;
    }
    
    public final void showReminderNotification(int id, @org.jetbrains.annotations.NotNull
    java.lang.String title, @org.jetbrains.annotations.NotNull
    java.lang.String message) {
    }
    
    public final void updateAppUsage() {
    }
    
    public final void cancelAllNotifications() {
    }
    
    public final void cancelNotification(int id) {
    }
    
    public final void scheduleSpecificTransactionNotifications() {
    }
    
    private final java.lang.Object checkAndShowSpecificTransactionNotification(com.example.budgetapp.database.entities.TransactionNotification notification, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object getReminderTimeForTransaction(com.example.budgetapp.database.entities.TransactionNotification notification, long currentTime, kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    private final java.lang.Object getTransactionDetails(com.example.budgetapp.database.entities.TransactionNotification notification, kotlin.coroutines.Continuation<? super com.example.budgetapp.services.NotificationService.TransactionDetails> $completion) {
        return null;
    }
    
    private final java.lang.Long calculateNextRecurringOccurrence(long originalDate, java.lang.String recurringType, long currentTime) {
        return null;
    }
    
    public final void showSpecificTransactionNotification(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.TransactionNotification transactionNotification) {
    }
    
    public final void cancelSpecificTransactionNotification(long transactionNotificationId) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/example/budgetapp/services/NotificationService$Companion;", "", "()V", "CHANNEL_DESCRIPTION", "", "CHANNEL_ID", "CHANNEL_NAME", "DAILY_ALWAYS_REMINDER_ID", "", "DAILY_REMINDER_ID", "HOURS_24_REMINDER_ID", "SPECIFIC_TRANSACTION_BASE_ID", "UPCOMING_TRANSACTIONS_ID", "CashInCashOut_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0016\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\bH\u00c6\u0003J\t\u0010\u001a\u001a\u00020\nH\u00c6\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003JG\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001J\u0013\u0010\u001d\u001a\u00020\n2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001J\t\u0010!\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0013R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012\u00a8\u0006\""}, d2 = {"Lcom/example/budgetapp/services/NotificationService$TransactionDetails;", "", "title", "", "amount", "", "description", "date", "", "isRecurring", "", "recurringType", "(Ljava/lang/String;DLjava/lang/String;JZLjava/lang/String;)V", "getAmount", "()D", "getDate", "()J", "getDescription", "()Ljava/lang/String;", "()Z", "getRecurringType", "getTitle", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "CashInCashOut_debug"})
    static final class TransactionDetails {
        @org.jetbrains.annotations.NotNull
        private final java.lang.String title = null;
        private final double amount = 0.0;
        @org.jetbrains.annotations.NotNull
        private final java.lang.String description = null;
        private final long date = 0L;
        private final boolean isRecurring = false;
        @org.jetbrains.annotations.Nullable
        private final java.lang.String recurringType = null;
        
        public TransactionDetails(@org.jetbrains.annotations.NotNull
        java.lang.String title, double amount, @org.jetbrains.annotations.NotNull
        java.lang.String description, long date, boolean isRecurring, @org.jetbrains.annotations.Nullable
        java.lang.String recurringType) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getTitle() {
            return null;
        }
        
        public final double getAmount() {
            return 0.0;
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getDescription() {
            return null;
        }
        
        public final long getDate() {
            return 0L;
        }
        
        public final boolean isRecurring() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getRecurringType() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String component1() {
            return null;
        }
        
        public final double component2() {
            return 0.0;
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String component3() {
            return null;
        }
        
        public final long component4() {
            return 0L;
        }
        
        public final boolean component5() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String component6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.budgetapp.services.NotificationService.TransactionDetails copy(@org.jetbrains.annotations.NotNull
        java.lang.String title, double amount, @org.jetbrains.annotations.NotNull
        java.lang.String description, long date, boolean isRecurring, @org.jetbrains.annotations.Nullable
        java.lang.String recurringType) {
            return null;
        }
        
        @java.lang.Override
        public boolean equals(@org.jetbrains.annotations.Nullable
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override
        @org.jetbrains.annotations.NotNull
        public java.lang.String toString() {
            return null;
        }
    }
}