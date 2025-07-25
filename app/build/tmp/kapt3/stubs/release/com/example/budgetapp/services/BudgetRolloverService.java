package com.example.budgetapp.services;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J!\u0010\u0011\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J \u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0018\u0010\u001c\u001a\u00020\u00192\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0011\u0010\u001d\u001a\u00020\u001eH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001fJ!\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u0017H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010#J\u0011\u0010$\u001a\u00020%H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001fJ\u0018\u0010&\u001a\u00020\'2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006("}, d2 = {"Lcom/example/budgetapp/services/BudgetRolloverService;", "", "database", "Lcom/example/budgetapp/database/BudgetDatabase;", "context", "Landroid/content/Context;", "(Lcom/example/budgetapp/database/BudgetDatabase;Landroid/content/Context;)V", "budgetRepository", "Lcom/example/budgetapp/repository/BudgetRepository;", "rolloverSettings", "Lcom/example/budgetapp/utils/BudgetRolloverSettings;", "calculateRolloverAmount", "", "budget", "Lcom/example/budgetapp/database/entities/Budget;", "progress", "Lcom/example/budgetapp/repository/BudgetProgress;", "calculateSmartRollover", "(Lcom/example/budgetapp/database/entities/Budget;Lcom/example/budgetapp/repository/BudgetProgress;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createRolloverBudget", "originalBudget", "rolloverAmount", "currentTime", "", "getPeriodName", "", "period", "Lcom/example/budgetapp/database/entities/BudgetPeriod;", "getRolloverRejectReason", "previewRollover", "Lcom/example/budgetapp/services/BudgetRolloverPreview;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processExpiredBudget", "Lcom/example/budgetapp/services/BudgetRolloverDetail;", "expiredBudget", "(Lcom/example/budgetapp/database/entities/Budget;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processExpiredBudgets", "Lcom/example/budgetapp/services/BudgetRolloverResult;", "shouldRolloverBudget", "", "CashInCashOut_release"})
public final class BudgetRolloverService {
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.BudgetDatabase database = null;
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.repository.BudgetRepository budgetRepository = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.utils.BudgetRolloverSettings rolloverSettings = null;
    
    public BudgetRolloverService(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.BudgetDatabase database, @org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object processExpiredBudgets(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.services.BudgetRolloverResult> $completion) {
        return null;
    }
    
    private final java.lang.Object processExpiredBudget(com.example.budgetapp.database.entities.Budget expiredBudget, long currentTime, kotlin.coroutines.Continuation<? super com.example.budgetapp.services.BudgetRolloverDetail> $completion) {
        return null;
    }
    
    private final boolean shouldRolloverBudget(com.example.budgetapp.database.entities.Budget budget, com.example.budgetapp.repository.BudgetProgress progress) {
        return false;
    }
    
    private final java.lang.String getRolloverRejectReason(com.example.budgetapp.database.entities.Budget budget, com.example.budgetapp.repository.BudgetProgress progress) {
        return null;
    }
    
    private final double calculateRolloverAmount(com.example.budgetapp.database.entities.Budget budget, com.example.budgetapp.repository.BudgetProgress progress) {
        return 0.0;
    }
    
    private final java.lang.Object calculateSmartRollover(com.example.budgetapp.database.entities.Budget budget, com.example.budgetapp.repository.BudgetProgress progress, kotlin.coroutines.Continuation<? super java.lang.Double> $completion) {
        return null;
    }
    
    private final com.example.budgetapp.database.entities.Budget createRolloverBudget(com.example.budgetapp.database.entities.Budget originalBudget, double rolloverAmount, long currentTime) {
        return null;
    }
    
    private final java.lang.String getPeriodName(com.example.budgetapp.database.entities.BudgetPeriod period) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object previewRollover(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.services.BudgetRolloverPreview> $completion) {
        return null;
    }
}