package com.example.budgetapp.receivers;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rJ\b\u0010\u000e\u001a\u00020\nH\u0002J\u0006\u0010\u000f\u001a\u00020\nJ \u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\nH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/example/budgetapp/receivers/AlarmManagerHelper;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "alarmManager", "Landroid/app/AlarmManager;", "database", "Lcom/example/budgetapp/database/BudgetDatabase;", "cancelAllReminders", "", "cancelReminder", "reminderType", "", "schedule24HoursCheck", "scheduleAllReminders", "scheduleDailyReminder", "alertTime", "action", "requestCode", "", "scheduleUpcomingTransactionsCheck", "Companion", "CashInCashOut_release"})
public final class AlarmManagerHelper {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    private final android.app.AlarmManager alarmManager = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.BudgetDatabase database = null;
    private static final int DAILY_IF_NOT_OPENED_REQUEST_CODE = 1001;
    private static final int DAILY_ALWAYS_REQUEST_CODE = 1002;
    private static final int HOURS_24_CHECK_REQUEST_CODE = 1003;
    private static final int UPCOMING_TRANSACTIONS_REQUEST_CODE = 1004;
    @org.jetbrains.annotations.NotNull
    public static final com.example.budgetapp.receivers.AlarmManagerHelper.Companion Companion = null;
    
    public AlarmManagerHelper(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    public final void scheduleAllReminders() {
    }
    
    private final void scheduleDailyReminder(java.lang.String alertTime, java.lang.String action, int requestCode) {
    }
    
    private final void schedule24HoursCheck() {
    }
    
    private final void scheduleUpcomingTransactionsCheck() {
    }
    
    public final void cancelAllReminders() {
    }
    
    public final void cancelReminder(@org.jetbrains.annotations.NotNull
    java.lang.String reminderType) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/example/budgetapp/receivers/AlarmManagerHelper$Companion;", "", "()V", "DAILY_ALWAYS_REQUEST_CODE", "", "DAILY_IF_NOT_OPENED_REQUEST_CODE", "HOURS_24_CHECK_REQUEST_CODE", "UPCOMING_TRANSACTIONS_REQUEST_CODE", "CashInCashOut_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}