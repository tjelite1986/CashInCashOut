package com.example.budgetapp.services;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0011\u0010\u0007\u001a\u00020\bH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0010"}, d2 = {"Lcom/example/budgetapp/services/BudgetRolloverWorker;", "Landroidx/work/CoroutineWorker;", "context", "Landroid/content/Context;", "workerParams", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logRolloverActivity", "", "result", "Lcom/example/budgetapp/services/BudgetRolloverResult;", "sendRolloverNotification", "Companion", "CashInCashOut_debug"})
public final class BudgetRolloverWorker extends androidx.work.CoroutineWorker {
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String WORK_NAME_BUDGET_ROLLOVER = "budget_rollover_work";
    @org.jetbrains.annotations.NotNull
    public static final com.example.budgetapp.services.BudgetRolloverWorker.Companion Companion = null;
    
    public BudgetRolloverWorker(@org.jetbrains.annotations.NotNull
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
    
    private final void sendRolloverNotification(com.example.budgetapp.services.BudgetRolloverResult result) {
    }
    
    private final void logRolloverActivity(com.example.budgetapp.services.BudgetRolloverResult result) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/example/budgetapp/services/BudgetRolloverWorker$Companion;", "", "()V", "WORK_NAME_BUDGET_ROLLOVER", "", "cancelRolloverWork", "", "context", "Landroid/content/Context;", "scheduleRolloverCheck", "triggerImmediateRollover", "CashInCashOut_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final void scheduleRolloverCheck(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
        }
        
        public final void cancelRolloverWork(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
        }
        
        public final void triggerImmediateRollover(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
        }
    }
}