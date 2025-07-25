package com.example.budgetapp.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 F2\u00020\u0001:\u0001FB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010;\u001a\u00020<J\u0006\u0010=\u001a\u00020>J\u0006\u0010?\u001a\u00020>J\u0006\u0010@\u001a\u00020>J\f\u0010A\u001a\b\u0012\u0004\u0012\u00020>0BJ\u0006\u0010C\u001a\u00020\u0006J\u0006\u0010D\u001a\u00020ER$\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR$\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R$\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0005\u001a\u00020\u00178F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR$\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\u001e\u0010\t\"\u0004\b\u001f\u0010\u000bR$\u0010 \u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b!\u0010\t\"\u0004\b\"\u0010\u000bR$\u0010#\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b$\u0010\t\"\u0004\b%\u0010\u000bR$\u0010&\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b\'\u0010\u000f\"\u0004\b(\u0010\u0011R$\u0010)\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b*\u0010\t\"\u0004\b+\u0010\u000bR$\u0010-\u001a\u00020,2\u0006\u0010\u0005\u001a\u00020,8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R$\u00102\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b3\u0010\t\"\u0004\b4\u0010\u000bR$\u00105\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\f8F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b6\u0010\u000f\"\u0004\b7\u0010\u0011R$\u00108\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068F@FX\u0086\u000e\u00a2\u0006\f\u001a\u0004\b9\u0010\t\"\u0004\b:\u0010\u000b\u00a8\u0006G"}, d2 = {"Lcom/example/budgetapp/utils/BudgetRolloverSettings;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "value", "", "autoRolloverEnabled", "getAutoRolloverEnabled", "()Z", "setAutoRolloverEnabled", "(Z)V", "", "fixedRolloverAmount", "getFixedRolloverAmount", "()D", "setFixedRolloverAmount", "(D)V", "minimumRolloverAmount", "getMinimumRolloverAmount", "setMinimumRolloverAmount", "prefs", "Landroid/content/SharedPreferences;", "Lcom/example/budgetapp/services/BudgetRolloverBehavior;", "rolloverBehavior", "getRolloverBehavior", "()Lcom/example/budgetapp/services/BudgetRolloverBehavior;", "setRolloverBehavior", "(Lcom/example/budgetapp/services/BudgetRolloverBehavior;)V", "rolloverEnabled", "getRolloverEnabled", "setRolloverEnabled", "rolloverMonthlyBudgets", "getRolloverMonthlyBudgets", "setRolloverMonthlyBudgets", "rolloverNotificationsEnabled", "getRolloverNotificationsEnabled", "setRolloverNotificationsEnabled", "rolloverPercentage", "getRolloverPercentage", "setRolloverPercentage", "rolloverQuarterlyBudgets", "getRolloverQuarterlyBudgets", "setRolloverQuarterlyBudgets", "Lcom/example/budgetapp/services/BudgetRolloverStrategy;", "rolloverStrategy", "getRolloverStrategy", "()Lcom/example/budgetapp/services/BudgetRolloverStrategy;", "setRolloverStrategy", "(Lcom/example/budgetapp/services/BudgetRolloverStrategy;)V", "rolloverWeeklyBudgets", "getRolloverWeeklyBudgets", "setRolloverWeeklyBudgets", "rolloverWeight", "getRolloverWeight", "setRolloverWeight", "rolloverYearlyBudgets", "getRolloverYearlyBudgets", "setRolloverYearlyBudgets", "getActivePeriodsCount", "", "getBehaviorDisplayName", "", "getRolloverSummary", "getStrategyDisplayName", "getValidationErrors", "", "isValidConfiguration", "resetToDefaults", "", "Companion", "CashInCashOut_debug"})
public final class BudgetRolloverSettings {
    @org.jetbrains.annotations.NotNull
    private final android.content.SharedPreferences prefs = null;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String PREFS_NAME = "budget_rollover_settings";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PREF_ROLLOVER_ENABLED = "rollover_enabled";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PREF_ROLLOVER_STRATEGY = "rollover_strategy";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PREF_ROLLOVER_BEHAVIOR = "rollover_behavior";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PREF_ROLLOVER_WEEKLY = "rollover_weekly";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PREF_ROLLOVER_MONTHLY = "rollover_monthly";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PREF_ROLLOVER_QUARTERLY = "rollover_quarterly";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PREF_ROLLOVER_YEARLY = "rollover_yearly";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PREF_MINIMUM_ROLLOVER_AMOUNT = "minimum_rollover_amount";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PREF_ROLLOVER_PERCENTAGE = "rollover_percentage";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PREF_FIXED_ROLLOVER_AMOUNT = "fixed_rollover_amount";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PREF_ROLLOVER_WEIGHT = "rollover_weight";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PREF_AUTO_ROLLOVER_ENABLED = "auto_rollover_enabled";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String PREF_ROLLOVER_NOTIFICATIONS = "rollover_notifications";
    public static final boolean DEFAULT_ROLLOVER_ENABLED = true;
    public static final boolean DEFAULT_ROLLOVER_WEEKLY = true;
    public static final boolean DEFAULT_ROLLOVER_MONTHLY = true;
    public static final boolean DEFAULT_ROLLOVER_QUARTERLY = true;
    public static final boolean DEFAULT_ROLLOVER_YEARLY = false;
    public static final double DEFAULT_MINIMUM_ROLLOVER_AMOUNT = 10.0;
    public static final double DEFAULT_ROLLOVER_PERCENTAGE = 50.0;
    public static final double DEFAULT_FIXED_ROLLOVER_AMOUNT = 100.0;
    public static final double DEFAULT_ROLLOVER_WEIGHT = 0.3;
    public static final boolean DEFAULT_AUTO_ROLLOVER_ENABLED = true;
    public static final boolean DEFAULT_ROLLOVER_NOTIFICATIONS = true;
    @org.jetbrains.annotations.NotNull
    public static final com.example.budgetapp.utils.BudgetRolloverSettings.Companion Companion = null;
    
    public BudgetRolloverSettings(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    public final boolean getRolloverEnabled() {
        return false;
    }
    
    public final void setRolloverEnabled(boolean value) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.services.BudgetRolloverStrategy getRolloverStrategy() {
        return null;
    }
    
    public final void setRolloverStrategy(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.services.BudgetRolloverStrategy value) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.services.BudgetRolloverBehavior getRolloverBehavior() {
        return null;
    }
    
    public final void setRolloverBehavior(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.services.BudgetRolloverBehavior value) {
    }
    
    public final boolean getRolloverWeeklyBudgets() {
        return false;
    }
    
    public final void setRolloverWeeklyBudgets(boolean value) {
    }
    
    public final boolean getRolloverMonthlyBudgets() {
        return false;
    }
    
    public final void setRolloverMonthlyBudgets(boolean value) {
    }
    
    public final boolean getRolloverQuarterlyBudgets() {
        return false;
    }
    
    public final void setRolloverQuarterlyBudgets(boolean value) {
    }
    
    public final boolean getRolloverYearlyBudgets() {
        return false;
    }
    
    public final void setRolloverYearlyBudgets(boolean value) {
    }
    
    public final double getMinimumRolloverAmount() {
        return 0.0;
    }
    
    public final void setMinimumRolloverAmount(double value) {
    }
    
    public final double getRolloverPercentage() {
        return 0.0;
    }
    
    public final void setRolloverPercentage(double value) {
    }
    
    public final double getFixedRolloverAmount() {
        return 0.0;
    }
    
    public final void setFixedRolloverAmount(double value) {
    }
    
    public final double getRolloverWeight() {
        return 0.0;
    }
    
    public final void setRolloverWeight(double value) {
    }
    
    public final boolean getAutoRolloverEnabled() {
        return false;
    }
    
    public final void setAutoRolloverEnabled(boolean value) {
    }
    
    public final boolean getRolloverNotificationsEnabled() {
        return false;
    }
    
    public final void setRolloverNotificationsEnabled(boolean value) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getStrategyDisplayName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getBehaviorDisplayName() {
        return null;
    }
    
    public final int getActivePeriodsCount() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getRolloverSummary() {
        return null;
    }
    
    public final void resetToDefaults() {
    }
    
    public final boolean isValidConfiguration() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.String> getValidationErrors() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u000e\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0011X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0011X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0011X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0011X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0011X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0011X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0011X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0011X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0011X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0011X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0011X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0011X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/example/budgetapp/utils/BudgetRolloverSettings$Companion;", "", "()V", "DEFAULT_AUTO_ROLLOVER_ENABLED", "", "DEFAULT_FIXED_ROLLOVER_AMOUNT", "", "DEFAULT_MINIMUM_ROLLOVER_AMOUNT", "DEFAULT_ROLLOVER_ENABLED", "DEFAULT_ROLLOVER_MONTHLY", "DEFAULT_ROLLOVER_NOTIFICATIONS", "DEFAULT_ROLLOVER_PERCENTAGE", "DEFAULT_ROLLOVER_QUARTERLY", "DEFAULT_ROLLOVER_WEEKLY", "DEFAULT_ROLLOVER_WEIGHT", "DEFAULT_ROLLOVER_YEARLY", "PREFS_NAME", "", "PREF_AUTO_ROLLOVER_ENABLED", "PREF_FIXED_ROLLOVER_AMOUNT", "PREF_MINIMUM_ROLLOVER_AMOUNT", "PREF_ROLLOVER_BEHAVIOR", "PREF_ROLLOVER_ENABLED", "PREF_ROLLOVER_MONTHLY", "PREF_ROLLOVER_NOTIFICATIONS", "PREF_ROLLOVER_PERCENTAGE", "PREF_ROLLOVER_QUARTERLY", "PREF_ROLLOVER_STRATEGY", "PREF_ROLLOVER_WEEKLY", "PREF_ROLLOVER_WEIGHT", "PREF_ROLLOVER_YEARLY", "CashInCashOut_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}