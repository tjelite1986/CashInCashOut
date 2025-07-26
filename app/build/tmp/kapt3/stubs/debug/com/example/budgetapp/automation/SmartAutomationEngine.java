package com.example.budgetapp.automation;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u00a6\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u001c\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\rH\u0002J\u001c\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\r2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\rH\u0002J\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\r2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\rH\u0002J$\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\r2\u0006\u0010\u0018\u001a\u00020\u00132\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\rH\u0002J\u0016\u0010\u001b\u001a\u00020\u00152\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00150\rH\u0002J\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\rH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001fJ\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\rH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001fJ\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001fJ\u001e\u0010#\u001a\u00020$2\u0006\u0010\u0018\u001a\u00020\u00132\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\rH\u0002J\u0018\u0010%\u001a\u00020&2\u0006\u0010\'\u001a\u00020!2\u0006\u0010(\u001a\u00020)H\u0002J-\u0010*\u001a\b\u0012\u0004\u0012\u00020+0\r2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020-0\r2\u0006\u0010(\u001a\u00020)H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010.J\u0010\u0010/\u001a\u0002002\u0006\u00101\u001a\u000200H\u0002J\u0019\u00102\u001a\u0002032\u0006\u0010(\u001a\u00020)H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00104J\'\u00105\u001a\u0002062\u0006\u0010\u0018\u001a\u00020\u00132\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\rH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00107J)\u00108\u001a\u0002092\u0006\u0010:\u001a\u0002002\u0006\u0010;\u001a\u0002002\u0006\u0010<\u001a\u00020\u0015H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010=R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006>"}, d2 = {"Lcom/example/budgetapp/automation/SmartAutomationEngine;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "database", "Lcom/example/budgetapp/database/BudgetDatabase;", "analyzeBudgetPerformance", "Lcom/example/budgetapp/automation/BudgetAnalysis;", "budget", "Lcom/example/budgetapp/database/entities/Budget;", "(Lcom/example/budgetapp/database/entities/Budget;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "analyzeRecurringPatterns", "", "Lcom/example/budgetapp/automation/RecurringTransaction;", "transactions", "Lcom/example/budgetapp/automation/TransactionPattern;", "analyzeTransactionPatterns", "expenses", "Lcom/example/budgetapp/database/entities/Expense;", "calculateMonthlyAverages", "", "calculateSmartSplit", "Lcom/example/budgetapp/automation/ExpenseSplit;", "expense", "participants", "Lcom/example/budgetapp/automation/ExpenseParticipant;", "calculateTrend", "values", "createAutoBudgetAdjustments", "Lcom/example/budgetapp/automation/BudgetAdjustment;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createSmartCategorizationRules", "Lcom/example/budgetapp/automation/AutomationRule;", "detectRecurringTransactions", "determineSplitType", "Lcom/example/budgetapp/automation/SplitType;", "evaluateRuleConditions", "", "rule", "transaction", "Lcom/example/budgetapp/automation/TransactionCandidate;", "executeActions", "Lcom/example/budgetapp/automation/ActionExecutionResult;", "actions", "Lcom/example/budgetapp/automation/AutomationAction;", "(Ljava/util/List;Lcom/example/budgetapp/automation/TransactionCandidate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "normalizeDescription", "", "description", "processAutomationRules", "Lcom/example/budgetapp/automation/AutomationResult;", "(Lcom/example/budgetapp/automation/TransactionCandidate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processSharedExpenseSplitting", "Lcom/example/budgetapp/automation/ExpenseSplitResult;", "(Lcom/example/budgetapp/database/entities/Expense;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setupEmergencyFundAutoTransfer", "Lcom/example/budgetapp/automation/AutoTransferRule;", "sourceAccountId", "targetAccountId", "percentage", "(Ljava/lang/String;Ljava/lang/String;DLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "CashInCashOut_debug"})
public final class SmartAutomationEngine {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.BudgetDatabase database = null;
    
    public SmartAutomationEngine(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object processAutomationRules(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.automation.TransactionCandidate transaction, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.automation.AutomationResult> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object createSmartCategorizationRules(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.automation.AutomationRule>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object detectRecurringTransactions(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.automation.RecurringTransaction>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object createAutoBudgetAdjustments(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.automation.BudgetAdjustment>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object setupEmergencyFundAutoTransfer(@org.jetbrains.annotations.NotNull
    java.lang.String sourceAccountId, @org.jetbrains.annotations.NotNull
    java.lang.String targetAccountId, double percentage, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.automation.AutoTransferRule> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object processSharedExpenseSplitting(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Expense expense, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.automation.ExpenseParticipant> participants, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.automation.ExpenseSplitResult> $completion) {
        return null;
    }
    
    private final boolean evaluateRuleConditions(com.example.budgetapp.automation.AutomationRule rule, com.example.budgetapp.automation.TransactionCandidate transaction) {
        return false;
    }
    
    private final java.lang.Object executeActions(java.util.List<com.example.budgetapp.automation.AutomationAction> actions, com.example.budgetapp.automation.TransactionCandidate transaction, kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.automation.ActionExecutionResult>> $completion) {
        return null;
    }
    
    private final java.util.List<com.example.budgetapp.automation.TransactionPattern> analyzeTransactionPatterns(java.util.List<com.example.budgetapp.database.entities.Expense> expenses) {
        return null;
    }
    
    private final java.util.List<com.example.budgetapp.automation.RecurringTransaction> analyzeRecurringPatterns(java.util.List<com.example.budgetapp.automation.TransactionPattern> transactions) {
        return null;
    }
    
    private final java.lang.Object analyzeBudgetPerformance(com.example.budgetapp.database.entities.Budget budget, kotlin.coroutines.Continuation<? super com.example.budgetapp.automation.BudgetAnalysis> $completion) {
        return null;
    }
    
    private final java.lang.String normalizeDescription(java.lang.String description) {
        return null;
    }
    
    private final java.util.List<java.lang.Double> calculateMonthlyAverages(java.util.List<com.example.budgetapp.database.entities.Expense> expenses) {
        return null;
    }
    
    private final double calculateTrend(java.util.List<java.lang.Double> values) {
        return 0.0;
    }
    
    private final com.example.budgetapp.automation.SplitType determineSplitType(com.example.budgetapp.database.entities.Expense expense, java.util.List<com.example.budgetapp.automation.ExpenseParticipant> participants) {
        return null;
    }
    
    private final java.util.List<com.example.budgetapp.automation.ExpenseSplit> calculateSmartSplit(com.example.budgetapp.database.entities.Expense expense, java.util.List<com.example.budgetapp.automation.ExpenseParticipant> participants) {
        return null;
    }
}