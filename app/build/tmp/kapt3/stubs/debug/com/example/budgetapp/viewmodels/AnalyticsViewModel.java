package com.example.budgetapp.viewmodels;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0011\u0010$\u001a\u00020\u0007H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010%J\u0011\u0010&\u001a\u00020\'H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010%J\u0011\u0010(\u001a\u00020\'H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010%J\u000e\u0010)\u001a\u00020\'2\u0006\u0010*\u001a\u00020+J\u0012\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0\u00100\u000fJ\u0014\u0010-\u001a\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020+0.H\u0002J\u001a\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0\u00100\u000f2\u0006\u00100\u001a\u00020\nJ\u001a\u00101\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0\u00100\u000f2\u0006\u00102\u001a\u000203J\u0010\u00104\u001a\u00020\n2\u0006\u00105\u001a\u00020+H\u0002J\u000e\u00106\u001a\u00020\'2\u0006\u0010*\u001a\u00020+J\u000e\u00107\u001a\u00020\'2\u0006\u0010*\u001a\u00020+J\u0006\u00108\u001a\u00020\'R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00070\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\u001e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0013R\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\r0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0013R\u001d\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0\u00100\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0013\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00069"}, d2 = {"Lcom/example/budgetapp/viewmodels/AnalyticsViewModel;", "Landroidx/lifecycle/ViewModel;", "database", "Lcom/example/budgetapp/database/BudgetDatabase;", "(Lcom/example/budgetapp/database/BudgetDatabase;)V", "_financialHealthScore", "Landroidx/lifecycle/MutableLiveData;", "", "_insightCounts", "", "", "", "_isLoading", "", "activeGoals", "Landroidx/lifecycle/LiveData;", "", "Lcom/example/budgetapp/database/entities/FinancialGoal;", "getActiveGoals", "()Landroidx/lifecycle/LiveData;", "expenseDao", "Lcom/example/budgetapp/database/dao/ExpenseDao;", "financialGoalDao", "Lcom/example/budgetapp/database/dao/FinancialGoalDao;", "financialHealthScore", "getFinancialHealthScore", "financialInsightDao", "Lcom/example/budgetapp/database/dao/FinancialInsightDao;", "incomeDao", "Lcom/example/budgetapp/database/dao/IncomeDao;", "insightCounts", "getInsightCounts", "isLoading", "unreadInsights", "Lcom/example/budgetapp/database/entities/FinancialInsight;", "getUnreadInsights", "calculateExpenseStability", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "calculateFinancialHealthScore", "", "calculateInsightCounts", "deleteInsight", "insightId", "", "getActionableInsights", "getCurrentMonthRange", "Lkotlin/Pair;", "getInsightsByCategory", "category", "getInsightsBySeverity", "severity", "Lcom/example/budgetapp/database/entities/InsightSeverity;", "getWeekKey", "timestamp", "markInsightAsActedUpon", "markInsightAsRead", "refreshData", "CashInCashOut_debug"})
public final class AnalyticsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.BudgetDatabase database = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.dao.FinancialInsightDao financialInsightDao = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.dao.FinancialGoalDao financialGoalDao = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.dao.ExpenseDao expenseDao = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.dao.IncomeDao incomeDao = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.util.List<com.example.budgetapp.database.entities.FinancialInsight>> unreadInsights = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.util.List<com.example.budgetapp.database.entities.FinancialGoal>> activeGoals = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.Double> _financialHealthScore = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.lang.Double> financialHealthScore = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.util.Map<java.lang.String, java.lang.Integer>> _insightCounts = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.util.Map<java.lang.String, java.lang.Integer>> insightCounts = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isLoading = null;
    
    public AnalyticsViewModel(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.BudgetDatabase database) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.example.budgetapp.database.entities.FinancialInsight>> getUnreadInsights() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.example.budgetapp.database.entities.FinancialGoal>> getActiveGoals() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.Double> getFinancialHealthScore() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.Map<java.lang.String, java.lang.Integer>> getInsightCounts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isLoading() {
        return null;
    }
    
    public final void refreshData() {
    }
    
    private final java.lang.Object calculateFinancialHealthScore(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object calculateExpenseStability(kotlin.coroutines.Continuation<? super java.lang.Double> $completion) {
        return null;
    }
    
    private final java.lang.Object calculateInsightCounts(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    public final void markInsightAsRead(long insightId) {
    }
    
    public final void markInsightAsActedUpon(long insightId) {
    }
    
    public final void deleteInsight(long insightId) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.example.budgetapp.database.entities.FinancialInsight>> getInsightsByCategory(@org.jetbrains.annotations.NotNull
    java.lang.String category) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.example.budgetapp.database.entities.FinancialInsight>> getInsightsBySeverity(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.InsightSeverity severity) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.example.budgetapp.database.entities.FinancialInsight>> getActionableInsights() {
        return null;
    }
    
    private final kotlin.Pair<java.lang.Long, java.lang.Long> getCurrentMonthRange() {
        return null;
    }
    
    private final java.lang.String getWeekKey(long timestamp) {
        return null;
    }
}