package com.example.budgetapp.services;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u000f\u0018\u0000 :2\u00020\u0001:\u0001:B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0017J\u0006\u0010\u0018\u001a\u00020\u0014J\b\u0010\u0019\u001a\u00020\u0014H\u0002J\u0016\u0010\u001a\u001a\u00020\u00142\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0002J\u0016\u0010\u001e\u001a\u00020\u00142\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0002J\u0010\u0010\u001f\u001a\n \u0012*\u0004\u0018\u00010 0 H\u0002J\b\u0010!\u001a\u00020\"H\u0002J\u001f\u0010#\u001a\u00020\u00142\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010$J\b\u0010%\u001a\u00020\"H\u0002J\b\u0010&\u001a\u00020\"H\u0002J\u0006\u0010\'\u001a\u00020\u0014J\u0016\u0010(\u001a\u00020\u00142\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0002J6\u0010*\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020,2\u0006\u0010.\u001a\u00020,2\u0006\u0010/\u001a\u00020\u00172\u0006\u00100\u001a\u00020\u0017J\u0016\u00101\u001a\u00020\u00142\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0002J\u0016\u00103\u001a\u00020\u00142\u0006\u00104\u001a\u00020,2\u0006\u00105\u001a\u00020\u0017J\u0006\u00106\u001a\u00020\u0014J\b\u00107\u001a\u00020\u0014H\u0002J\b\u00108\u001a\u00020\u0014H\u0002J\b\u00109\u001a\u00020\u0014H\u0002R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0010\u001a\n \u0012*\u0004\u0018\u00010\u00110\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006;"}, d2 = {"Lcom/example/budgetapp/services/BudgetNotificationService;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "budgetRepository", "Lcom/example/budgetapp/repository/BudgetRepository;", "getBudgetRepository", "()Lcom/example/budgetapp/repository/BudgetRepository;", "budgetRepository$delegate", "Lkotlin/Lazy;", "notificationManager", "Landroidx/core/app/NotificationManagerCompat;", "getNotificationManager", "()Landroidx/core/app/NotificationManagerCompat;", "notificationManager$delegate", "numberFormat", "Ljava/text/NumberFormat;", "kotlin.jvm.PlatformType", "cancelAllNotifications", "", "cancelNotification", "id", "", "checkBudgetAlerts", "createNotificationChannels", "generateDailySummary", "progressList", "", "Lcom/example/budgetapp/repository/BudgetProgress;", "generateWeeklySummary", "getPrefs", "Landroid/content/SharedPreferences;", "hasNotificationPermission", "", "processAlerts", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "shouldShowDailySummary", "shouldShowWeeklySummary", "showDailySummary", "showNearLimitAlert", "nearLimitItems", "showNotification", "channelId", "", "title", "message", "priority", "icon", "showOverBudgetAlert", "overBudgetItems", "showPeriodEndReminder", "budgetName", "daysLeft", "showWeeklySummary", "updateLastAlertCheck", "updateLastDailySummary", "updateLastWeeklySummary", "Companion", "CashInCashOut_release"})
public final class BudgetNotificationService {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy budgetRepository$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy notificationManager$delegate = null;
    private final java.text.NumberFormat numberFormat = null;
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String CHANNEL_ID_BUDGET_ALERTS = "budget_alerts";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String CHANNEL_ID_BUDGET_SUMMARY = "budget_summary";
    public static final int NOTIFICATION_ID_OVER_BUDGET = 1001;
    public static final int NOTIFICATION_ID_NEAR_LIMIT = 1002;
    public static final int NOTIFICATION_ID_DAILY_SUMMARY = 1003;
    public static final int NOTIFICATION_ID_WEEKLY_SUMMARY = 1004;
    public static final int NOTIFICATION_ID_PERIOD_END = 1005;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String PREFS_NAME = "budget_notifications";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String PREF_LAST_ALERT_CHECK = "last_alert_check";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String PREF_LAST_DAILY_SUMMARY = "last_daily_summary";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String PREF_LAST_WEEKLY_SUMMARY = "last_weekly_summary";
    @org.jetbrains.annotations.NotNull
    public static final com.example.budgetapp.services.BudgetNotificationService.Companion Companion = null;
    
    public BudgetNotificationService(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    private final com.example.budgetapp.repository.BudgetRepository getBudgetRepository() {
        return null;
    }
    
    private final androidx.core.app.NotificationManagerCompat getNotificationManager() {
        return null;
    }
    
    private final void createNotificationChannels() {
    }
    
    public final void checkBudgetAlerts() {
    }
    
    private final java.lang.Object processAlerts(java.util.List<com.example.budgetapp.repository.BudgetProgress> progressList, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final void showOverBudgetAlert(java.util.List<com.example.budgetapp.repository.BudgetProgress> overBudgetItems) {
    }
    
    private final void showNearLimitAlert(java.util.List<com.example.budgetapp.repository.BudgetProgress> nearLimitItems) {
    }
    
    public final void showDailySummary() {
    }
    
    private final void generateDailySummary(java.util.List<com.example.budgetapp.repository.BudgetProgress> progressList) {
    }
    
    public final void showWeeklySummary() {
    }
    
    private final void generateWeeklySummary(java.util.List<com.example.budgetapp.repository.BudgetProgress> progressList) {
    }
    
    public final void showPeriodEndReminder(@org.jetbrains.annotations.NotNull
    java.lang.String budgetName, int daysLeft) {
    }
    
    public final void showNotification(int id, @org.jetbrains.annotations.NotNull
    java.lang.String channelId, @org.jetbrains.annotations.NotNull
    java.lang.String title, @org.jetbrains.annotations.NotNull
    java.lang.String message, int priority, int icon) {
    }
    
    private final boolean hasNotificationPermission() {
        return false;
    }
    
    private final android.content.SharedPreferences getPrefs() {
        return null;
    }
    
    private final boolean shouldShowDailySummary() {
        return false;
    }
    
    private final boolean shouldShowWeeklySummary() {
        return false;
    }
    
    private final void updateLastAlertCheck() {
    }
    
    private final void updateLastDailySummary() {
    }
    
    private final void updateLastWeeklySummary() {
    }
    
    public final void cancelAllNotifications() {
    }
    
    public final void cancelNotification(int id) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/example/budgetapp/services/BudgetNotificationService$Companion;", "", "()V", "CHANNEL_ID_BUDGET_ALERTS", "", "CHANNEL_ID_BUDGET_SUMMARY", "NOTIFICATION_ID_DAILY_SUMMARY", "", "NOTIFICATION_ID_NEAR_LIMIT", "NOTIFICATION_ID_OVER_BUDGET", "NOTIFICATION_ID_PERIOD_END", "NOTIFICATION_ID_WEEKLY_SUMMARY", "PREFS_NAME", "PREF_LAST_ALERT_CHECK", "PREF_LAST_DAILY_SUMMARY", "PREF_LAST_WEEKLY_SUMMARY", "CashInCashOut_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}