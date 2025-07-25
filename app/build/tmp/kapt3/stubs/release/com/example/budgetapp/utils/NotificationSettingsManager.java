package com.example.budgetapp.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000 32\u00020\u0001:\u00013B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010-\u001a\u00020.J\u0006\u0010/\u001a\u00020.J\u0006\u00100\u001a\u00020\u0016J\u0006\u00101\u001a\u00020.J\b\u00102\u001a\u00020.H\u0002R$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR$\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0002\u001a\n \u0012*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R$\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0011R$\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u00168F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR$\u0010\u001c\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u001d\u0010\u000f\"\u0004\b\u001e\u0010\u0011R$\u0010\u001f\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b \u0010\u000f\"\u0004\b!\u0010\u0011R\u000e\u0010\"\u001a\u00020#X\u0082\u0004\u00a2\u0006\u0002\n\u0000R$\u0010%\u001a\u00020$2\u0006\u0010\u0005\u001a\u00020$8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b&\u0010\'\"\u0004\b(\u0010)R$\u0010*\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b+\u0010\u000f\"\u0004\b,\u0010\u0011\u00a8\u00064"}, d2 = {"Lcom/example/budgetapp/utils/NotificationSettingsManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "value", "", "alertThreshold", "getAlertThreshold", "()D", "setAlertThreshold", "(D)V", "", "budgetAlertsEnabled", "getBudgetAlertsEnabled", "()Z", "setBudgetAlertsEnabled", "(Z)V", "kotlin.jvm.PlatformType", "dailySummaryEnabled", "getDailySummaryEnabled", "setDailySummaryEnabled", "", "dailySummaryTime", "getDailySummaryTime", "()Ljava/lang/String;", "setDailySummaryTime", "(Ljava/lang/String;)V", "overBudgetAlertsEnabled", "getOverBudgetAlertsEnabled", "setOverBudgetAlertsEnabled", "periodEndRemindersEnabled", "getPeriodEndRemindersEnabled", "setPeriodEndRemindersEnabled", "prefs", "Landroid/content/SharedPreferences;", "", "weeklySummaryDay", "getWeeklySummaryDay", "()I", "setWeeklySummaryDay", "(I)V", "weeklySummaryEnabled", "getWeeklySummaryEnabled", "setWeeklySummaryEnabled", "disableAllNotifications", "", "enableDefaultNotifications", "getNotificationSummary", "initializeNotifications", "updateWorkScheduling", "Companion", "CashInCashOut_release"})
public final class NotificationSettingsManager {
    @org.jetbrains.annotations.NotNull
    private final android.content.SharedPreferences prefs = null;
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String PREFS_NAME = "notification_settings";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PREF_BUDGET_ALERTS_ENABLED = "budget_alerts_enabled";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PREF_DAILY_SUMMARY_ENABLED = "daily_summary_enabled";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PREF_WEEKLY_SUMMARY_ENABLED = "weekly_summary_enabled";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PREF_PERIOD_END_REMINDERS = "period_end_reminders";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PREF_ALERT_THRESHOLD = "alert_threshold";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PREF_OVER_BUDGET_ALERTS = "over_budget_alerts";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PREF_DAILY_SUMMARY_TIME = "daily_summary_time";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PREF_WEEKLY_SUMMARY_DAY = "weekly_summary_day";
    public static final boolean DEFAULT_BUDGET_ALERTS = true;
    public static final boolean DEFAULT_DAILY_SUMMARY = false;
    public static final boolean DEFAULT_WEEKLY_SUMMARY = true;
    public static final boolean DEFAULT_PERIOD_END_REMINDERS = true;
    public static final double DEFAULT_ALERT_THRESHOLD = 80.0;
    public static final boolean DEFAULT_OVER_BUDGET_ALERTS = true;
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String DEFAULT_DAILY_SUMMARY_TIME = "20:00";
    public static final int DEFAULT_WEEKLY_SUMMARY_DAY = 1;
    @org.jetbrains.annotations.NotNull
    public static final com.example.budgetapp.utils.NotificationSettingsManager.Companion Companion = null;
    
    public NotificationSettingsManager(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    public final boolean getBudgetAlertsEnabled() {
        return false;
    }
    
    public final void setBudgetAlertsEnabled(boolean value) {
    }
    
    public final boolean getOverBudgetAlertsEnabled() {
        return false;
    }
    
    public final void setOverBudgetAlertsEnabled(boolean value) {
    }
    
    public final double getAlertThreshold() {
        return 0.0;
    }
    
    public final void setAlertThreshold(double value) {
    }
    
    public final boolean getDailySummaryEnabled() {
        return false;
    }
    
    public final void setDailySummaryEnabled(boolean value) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getDailySummaryTime() {
        return null;
    }
    
    public final void setDailySummaryTime(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
    }
    
    public final boolean getWeeklySummaryEnabled() {
        return false;
    }
    
    public final void setWeeklySummaryEnabled(boolean value) {
    }
    
    public final int getWeeklySummaryDay() {
        return 0;
    }
    
    public final void setWeeklySummaryDay(int value) {
    }
    
    public final boolean getPeriodEndRemindersEnabled() {
        return false;
    }
    
    public final void setPeriodEndRemindersEnabled(boolean value) {
    }
    
    public final void initializeNotifications() {
    }
    
    private final void updateWorkScheduling() {
    }
    
    public final void disableAllNotifications() {
    }
    
    public final void enableDefaultNotifications() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getNotificationSummary() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\tX\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/example/budgetapp/utils/NotificationSettingsManager$Companion;", "", "()V", "DEFAULT_ALERT_THRESHOLD", "", "DEFAULT_BUDGET_ALERTS", "", "DEFAULT_DAILY_SUMMARY", "DEFAULT_DAILY_SUMMARY_TIME", "", "DEFAULT_OVER_BUDGET_ALERTS", "DEFAULT_PERIOD_END_REMINDERS", "DEFAULT_WEEKLY_SUMMARY", "DEFAULT_WEEKLY_SUMMARY_DAY", "", "PREFS_NAME", "PREF_ALERT_THRESHOLD", "PREF_BUDGET_ALERTS_ENABLED", "PREF_DAILY_SUMMARY_ENABLED", "PREF_DAILY_SUMMARY_TIME", "PREF_OVER_BUDGET_ALERTS", "PREF_PERIOD_END_REMINDERS", "PREF_WEEKLY_SUMMARY_DAY", "PREF_WEEKLY_SUMMARY_ENABLED", "CashInCashOut_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}