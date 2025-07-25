package com.example.budgetapp.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\u0002\u0010\u0006J\u001d\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u0011\u0010\u0010\u001a\u00020\fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J$\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u0018J\u0019\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u001bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001cJ\u0019\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u001fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 J\u001b\u0010!\u001a\u0004\u0018\u00010\u001f2\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u0012\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0$0#J\u0012\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001f0$0#J\u001b\u0010&\u001a\u0004\u0018\u00010\u001f2\u0006\u0010\u001a\u001a\u00020\u001bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001cJ\u0012\u0010\'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0$0#J\u0019\u0010(\u001a\u00020)2\u0006\u0010\u001e\u001a\u00020\u001fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 J%\u0010*\u001a\b\u0012\u0004\u0012\u00020)0$2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u001f0$H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010,J!\u0010-\u001a\u00020.2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u001fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010/J\u0011\u00100\u001a\u00020.H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u0019\u00101\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u001fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 J\u0019\u00102\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u001fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00063"}, d2 = {"Lcom/example/budgetapp/repository/BudgetRepository;", "", "database", "Lcom/example/budgetapp/database/BudgetDatabase;", "context", "Landroid/content/Context;", "(Lcom/example/budgetapp/database/BudgetDatabase;Landroid/content/Context;)V", "budgetDao", "Lcom/example/budgetapp/database/dao/BudgetDao;", "expenseDao", "Lcom/example/budgetapp/database/dao/ExpenseDao;", "checkAndTriggerBudgetAlerts", "", "categoryName", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkPeriodEndReminders", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createBudgetPeriod", "Lkotlin/Pair;", "", "period", "Lcom/example/budgetapp/database/entities/BudgetPeriod;", "startDate", "Ljava/util/Date;", "deactivateBudget", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteBudget", "budget", "Lcom/example/budgetapp/database/entities/Budget;", "(Lcom/example/budgetapp/database/entities/Budget;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveBudgetForCategory", "getAllActiveBudgets", "Lkotlinx/coroutines/flow/Flow;", "", "getAllBudgets", "getBudgetById", "getBudgetCategories", "getBudgetProgress", "Lcom/example/budgetapp/repository/BudgetProgress;", "getBudgetProgressList", "budgets", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSpentAmountForCategory", "", "(Ljava/lang/String;Lcom/example/budgetapp/database/entities/Budget;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTotalActiveBudgetAmount", "insertBudget", "updateBudget", "CashInCashOut_debug"})
public final class BudgetRepository {
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.BudgetDatabase database = null;
    @org.jetbrains.annotations.Nullable
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.dao.BudgetDao budgetDao = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.dao.ExpenseDao expenseDao = null;
    
    public BudgetRepository(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.BudgetDatabase database, @org.jetbrains.annotations.Nullable
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.Budget>> getAllActiveBudgets() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.Budget>> getAllBudgets() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getBudgetById(int id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.database.entities.Budget> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getActiveBudgetForCategory(@org.jetbrains.annotations.NotNull
    java.lang.String categoryName, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.database.entities.Budget> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<java.lang.String>> getBudgetCategories() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object insertBudget(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Budget budget, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object updateBudget(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Budget budget, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteBudget(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Budget budget, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deactivateBudget(int id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getTotalActiveBudgetAmount(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getSpentAmountForCategory(@org.jetbrains.annotations.NotNull
    java.lang.String categoryName, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Budget budget, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getBudgetProgress(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Budget budget, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.repository.BudgetProgress> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getBudgetProgressList(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.database.entities.Budget> budgets, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.repository.BudgetProgress>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlin.Pair<java.lang.Long, java.lang.Long> createBudgetPeriod(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.BudgetPeriod period, @org.jetbrains.annotations.NotNull
    java.util.Date startDate) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object checkAndTriggerBudgetAlerts(@org.jetbrains.annotations.Nullable
    java.lang.String categoryName, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object checkPeriodEndReminders(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}