package com.example.budgetapp.automation;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0019\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u0011\u0010\u0010\u001a\u00020\u0011H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0012"}, d2 = {"Lcom/example/budgetapp/automation/BudgetOptimizationService;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "database", "Lcom/example/budgetapp/database/BudgetDatabase;", "generateNewBudgetRecommendations", "", "Lcom/example/budgetapp/automation/NewBudgetRecommendation;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateSmartBudgetSuggestions", "Lcom/example/budgetapp/automation/SmartBudgetSuggestions;", "monthlyIncome", "", "(DLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "optimizeAllBudgets", "Lcom/example/budgetapp/automation/BudgetOptimizationReport;", "CashInCashOut_debug"})
public final class BudgetOptimizationService {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.BudgetDatabase database = null;
    
    public BudgetOptimizationService(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object optimizeAllBudgets(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.automation.BudgetOptimizationReport> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object generateSmartBudgetSuggestions(double monthlyIncome, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.automation.SmartBudgetSuggestions> $completion) {
        return null;
    }
    
    private final java.lang.Object generateNewBudgetRecommendations(kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.automation.NewBudgetRecommendation>> $completion) {
        return null;
    }
}