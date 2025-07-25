package com.example.budgetapp.services;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0011\u0010\u0007\u001a\u00020\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000b"}, d2 = {"Lcom/example/budgetapp/services/BudgetNotificationWorker;", "Landroidx/work/CoroutineWorker;", "context", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "CashInCashOut_release"})
public final class BudgetNotificationWorker extends androidx.work.CoroutineWorker {
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String WORK_NAME_BUDGET_ALERTS = "budget_alerts_work";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String WORK_NAME_DAILY_SUMMARY = "daily_summary_work";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String WORK_NAME_WEEKLY_SUMMARY = "weekly_summary_work";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String KEY_NOTIFICATION_TYPE = "notification_type";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String TYPE_BUDGET_ALERTS = "budget_alerts";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String TYPE_DAILY_SUMMARY = "daily_summary";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String TYPE_WEEKLY_SUMMARY = "weekly_summary";
    @org.jetbrains.annotations.NotNull
    public static final com.example.budgetapp.services.BudgetNotificationWorker.Companion Companion = null;
    
    public BudgetNotificationWorker(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    androidx.work.WorkerParameters workerParams) {
        super(null, null);
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.Nullable
    public java.lang.Object doWork(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super androidx.work.ListenableWorker.Result> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000eH\u0002J\b\u0010\u0010\u001a\u00020\fH\u0002J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/example/budgetapp/services/BudgetNotificationWorker$Companion;", "", "()V", "KEY_NOTIFICATION_TYPE", "", "TYPE_BUDGET_ALERTS", "TYPE_DAILY_SUMMARY", "TYPE_WEEKLY_SUMMARY", "WORK_NAME_BUDGET_ALERTS", "WORK_NAME_DAILY_SUMMARY", "WORK_NAME_WEEKLY_SUMMARY", "calculateDelayToTime", "", "hour", "", "minute", "calculateDelayToWeeklyTime", "cancelAllNotificationWork", "", "context", "Landroid/content/Context;", "scheduleBudgetAlerts", "scheduleDailySummary", "scheduleWeeklySummary", "CashInCashOut_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final void scheduleBudgetAlerts(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
        }
        
        public final void scheduleDailySummary(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
        }
        
        public final void scheduleWeeklySummary(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
        }
        
        public final void cancelAllNotificationWork(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
        }
        
        private final long calculateDelayToTime(int hour, int minute) {
            return 0L;
        }
        
        private final long calculateDelayToWeeklyTime() {
            return 0L;
        }
    }
}