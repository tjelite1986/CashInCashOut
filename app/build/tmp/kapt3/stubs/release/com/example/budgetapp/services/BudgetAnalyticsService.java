package com.example.budgetapp.services;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J\u0011\u0010\u000e\u001a\u00020\u000fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J\u001c\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\f2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J\u0016\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\fH\u0002J$\u0010\u0017\u001a\u00020\u00182\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u001c0\u001a2\u0006\u0010\u001d\u001a\u00020\u001bH\u0002J%\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\f2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\fH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 J!\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\f2\b\b\u0002\u0010#\u001a\u00020\u001cH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010$J\u0010\u0010%\u001a\u00020\u00182\u0006\u0010&\u001a\u00020\u001bH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\'"}, d2 = {"Lcom/example/budgetapp/services/BudgetAnalyticsService;", "", "database", "Lcom/example/budgetapp/database/BudgetDatabase;", "(Lcom/example/budgetapp/database/BudgetDatabase;)V", "budgetRepository", "Lcom/example/budgetapp/repository/BudgetRepository;", "expenseDao", "Lcom/example/budgetapp/database/dao/ExpenseDao;", "calculateBudgetEfficiency", "Lcom/example/budgetapp/services/BudgetEfficiency;", "progressList", "", "Lcom/example/budgetapp/repository/BudgetProgress;", "generateBudgetAnalytics", "Lcom/example/budgetapp/services/BudgetAnalytics;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateCategoryBreakdown", "Lcom/example/budgetapp/services/CategorySpending;", "generatePeriodAnalysis", "Lcom/example/budgetapp/services/PeriodAnalysis;", "budgets", "Lcom/example/budgetapp/database/entities/Budget;", "generatePeriodRecommendation", "", "periodCounts", "", "Lcom/example/budgetapp/database/entities/BudgetPeriod;", "", "mostCommon", "generateSpendingTrend", "Lcom/example/budgetapp/services/SpendingTrendPoint;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getBudgetPerformanceHistory", "Lcom/example/budgetapp/services/BudgetPerformancePoint;", "days", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPeriodName", "period", "CashInCashOut_release"})
public final class BudgetAnalyticsService {
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.BudgetDatabase database = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.repository.BudgetRepository budgetRepository = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.dao.ExpenseDao expenseDao = null;
    
    public BudgetAnalyticsService(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.BudgetDatabase database) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object generateBudgetAnalytics(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.services.BudgetAnalytics> $completion) {
        return null;
    }
    
    private final java.util.List<com.example.budgetapp.services.CategorySpending> generateCategoryBreakdown(java.util.List<com.example.budgetapp.repository.BudgetProgress> progressList) {
        return null;
    }
    
    private final java.lang.Object generateSpendingTrend(java.util.List<com.example.budgetapp.database.entities.Budget> budgets, kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.services.SpendingTrendPoint>> $completion) {
        return null;
    }
    
    private final com.example.budgetapp.services.BudgetEfficiency calculateBudgetEfficiency(java.util.List<com.example.budgetapp.repository.BudgetProgress> progressList) {
        return null;
    }
    
    private final com.example.budgetapp.services.PeriodAnalysis generatePeriodAnalysis(java.util.List<com.example.budgetapp.database.entities.Budget> budgets) {
        return null;
    }
    
    private final java.lang.String generatePeriodRecommendation(java.util.Map<com.example.budgetapp.database.entities.BudgetPeriod, java.lang.Integer> periodCounts, com.example.budgetapp.database.entities.BudgetPeriod mostCommon) {
        return null;
    }
    
    private final java.lang.String getPeriodName(com.example.budgetapp.database.entities.BudgetPeriod period) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getBudgetPerformanceHistory(int days, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.services.BudgetPerformancePoint>> $completion) {
        return null;
    }
}