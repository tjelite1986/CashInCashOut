package com.example.budgetapp;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\rH\u0002J\b\u0010\u0011\u001a\u00020\rH\u0002J\u0012\u0010\u0012\u001a\u00020\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\u0010\u0010\u0015\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0016\u0010\u0016\u001a\u00020\r2\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\u0002J\u001e\u0010\u001a\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0018H\u0002J\u0016\u0010\u001d\u001a\u00020\r2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0018H\u0002J\u0016\u0010\u001e\u001a\u00020\r2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u0018H\u0002J\b\u0010!\u001a\u00020\rH\u0002J\u0010\u0010\"\u001a\u00020\r2\u0006\u0010#\u001a\u00020$H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lcom/example/budgetapp/BudgetAnalyticsActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "analyticsService", "Lcom/example/budgetapp/services/BudgetAnalyticsService;", "binding", "Lcom/example/budgetapp/databinding/ActivityBudgetAnalyticsBinding;", "dateFormat", "Ljava/text/SimpleDateFormat;", "numberFormat", "Ljava/text/NumberFormat;", "kotlin.jvm.PlatformType", "displayAnalytics", "", "analytics", "Lcom/example/budgetapp/services/BudgetAnalytics;", "initializeService", "loadAnalytics", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupBudgetStatusChart", "setupCategoryPieChart", "categoryBreakdown", "", "Lcom/example/budgetapp/services/CategorySpending;", "setupCharts", "performanceHistory", "Lcom/example/budgetapp/services/BudgetPerformancePoint;", "setupPerformanceChart", "setupSpendingTrendChart", "spendingTrend", "Lcom/example/budgetapp/services/SpendingTrendPoint;", "setupToolbar", "updateEfficiencyColors", "utilizationRate", "", "CashInCashOut_release"})
public final class BudgetAnalyticsActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.budgetapp.databinding.ActivityBudgetAnalyticsBinding binding;
    private com.example.budgetapp.services.BudgetAnalyticsService analyticsService;
    private final java.text.NumberFormat numberFormat = null;
    @org.jetbrains.annotations.NotNull
    private final java.text.SimpleDateFormat dateFormat = null;
    
    public BudgetAnalyticsActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupToolbar() {
    }
    
    private final void initializeService() {
    }
    
    private final void loadAnalytics() {
    }
    
    private final void displayAnalytics(com.example.budgetapp.services.BudgetAnalytics analytics) {
    }
    
    private final void updateEfficiencyColors(double utilizationRate) {
    }
    
    private final void setupCharts(com.example.budgetapp.services.BudgetAnalytics analytics, java.util.List<com.example.budgetapp.services.BudgetPerformancePoint> performanceHistory) {
    }
    
    private final void setupCategoryPieChart(java.util.List<com.example.budgetapp.services.CategorySpending> categoryBreakdown) {
    }
    
    private final void setupSpendingTrendChart(java.util.List<com.example.budgetapp.services.SpendingTrendPoint> spendingTrend) {
    }
    
    private final void setupBudgetStatusChart(com.example.budgetapp.services.BudgetAnalytics analytics) {
    }
    
    private final void setupPerformanceChart(java.util.List<com.example.budgetapp.services.BudgetPerformancePoint> performanceHistory) {
    }
}