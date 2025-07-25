package com.example.budgetapp.services;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b \n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001Bq\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\r\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u00a2\u0006\u0002\u0010\u0015J\t\u0010\'\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00100\rH\u00c6\u0003J\t\u0010)\u001a\u00020\u0012H\u00c6\u0003J\t\u0010*\u001a\u00020\u0014H\u00c6\u0003J\t\u0010+\u001a\u00020\u0005H\u00c6\u0003J\t\u0010,\u001a\u00020\u0005H\u00c6\u0003J\t\u0010-\u001a\u00020\u0005H\u00c6\u0003J\t\u0010.\u001a\u00020\u0005H\u00c6\u0003J\t\u0010/\u001a\u00020\u0003H\u00c6\u0003J\t\u00100\u001a\u00020\u0003H\u00c6\u0003J\t\u00101\u001a\u00020\u0003H\u00c6\u0003J\u000f\u00102\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u00c6\u0003J\u008d\u0001\u00103\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\r2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\u00c6\u0001J\u0013\u00104\u001a\u0002052\b\u00106\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00107\u001a\u00020\u0003H\u00d6\u0001J\t\u00108\u001a\u000209H\u00d6\u0001R\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\n\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u000b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001bR\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001bR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0013\u001a\u00020\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001fR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0017R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001bR\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0017\u00a8\u0006:"}, d2 = {"Lcom/example/budgetapp/services/BudgetAnalytics;", "", "totalBudgets", "", "totalBudgetAmount", "", "totalSpentAmount", "totalRemainingAmount", "averageSpendingPercentage", "budgetsOverLimit", "budgetsNearLimit", "budgetsOnTrack", "categoryBreakdown", "", "Lcom/example/budgetapp/services/CategorySpending;", "spendingTrend", "Lcom/example/budgetapp/services/SpendingTrendPoint;", "budgetEfficiency", "Lcom/example/budgetapp/services/BudgetEfficiency;", "periodAnalysis", "Lcom/example/budgetapp/services/PeriodAnalysis;", "(IDDDDIIILjava/util/List;Ljava/util/List;Lcom/example/budgetapp/services/BudgetEfficiency;Lcom/example/budgetapp/services/PeriodAnalysis;)V", "getAverageSpendingPercentage", "()D", "getBudgetEfficiency", "()Lcom/example/budgetapp/services/BudgetEfficiency;", "getBudgetsNearLimit", "()I", "getBudgetsOnTrack", "getBudgetsOverLimit", "getCategoryBreakdown", "()Ljava/util/List;", "getPeriodAnalysis", "()Lcom/example/budgetapp/services/PeriodAnalysis;", "getSpendingTrend", "getTotalBudgetAmount", "getTotalBudgets", "getTotalRemainingAmount", "getTotalSpentAmount", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "", "CashInCashOut_debug"})
public final class BudgetAnalytics {
    private final int totalBudgets = 0;
    private final double totalBudgetAmount = 0.0;
    private final double totalSpentAmount = 0.0;
    private final double totalRemainingAmount = 0.0;
    private final double averageSpendingPercentage = 0.0;
    private final int budgetsOverLimit = 0;
    private final int budgetsNearLimit = 0;
    private final int budgetsOnTrack = 0;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.example.budgetapp.services.CategorySpending> categoryBreakdown = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.example.budgetapp.services.SpendingTrendPoint> spendingTrend = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.services.BudgetEfficiency budgetEfficiency = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.services.PeriodAnalysis periodAnalysis = null;
    
    public BudgetAnalytics(int totalBudgets, double totalBudgetAmount, double totalSpentAmount, double totalRemainingAmount, double averageSpendingPercentage, int budgetsOverLimit, int budgetsNearLimit, int budgetsOnTrack, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.services.CategorySpending> categoryBreakdown, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.services.SpendingTrendPoint> spendingTrend, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.services.BudgetEfficiency budgetEfficiency, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.services.PeriodAnalysis periodAnalysis) {
        super();
    }
    
    public final int getTotalBudgets() {
        return 0;
    }
    
    public final double getTotalBudgetAmount() {
        return 0.0;
    }
    
    public final double getTotalSpentAmount() {
        return 0.0;
    }
    
    public final double getTotalRemainingAmount() {
        return 0.0;
    }
    
    public final double getAverageSpendingPercentage() {
        return 0.0;
    }
    
    public final int getBudgetsOverLimit() {
        return 0;
    }
    
    public final int getBudgetsNearLimit() {
        return 0;
    }
    
    public final int getBudgetsOnTrack() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.budgetapp.services.CategorySpending> getCategoryBreakdown() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.budgetapp.services.SpendingTrendPoint> getSpendingTrend() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.services.BudgetEfficiency getBudgetEfficiency() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.services.PeriodAnalysis getPeriodAnalysis() {
        return null;
    }
    
    public final int component1() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.budgetapp.services.SpendingTrendPoint> component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.services.BudgetEfficiency component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.services.PeriodAnalysis component12() {
        return null;
    }
    
    public final double component2() {
        return 0.0;
    }
    
    public final double component3() {
        return 0.0;
    }
    
    public final double component4() {
        return 0.0;
    }
    
    public final double component5() {
        return 0.0;
    }
    
    public final int component6() {
        return 0;
    }
    
    public final int component7() {
        return 0;
    }
    
    public final int component8() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.budgetapp.services.CategorySpending> component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.services.BudgetAnalytics copy(int totalBudgets, double totalBudgetAmount, double totalSpentAmount, double totalRemainingAmount, double averageSpendingPercentage, int budgetsOverLimit, int budgetsNearLimit, int budgetsOnTrack, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.services.CategorySpending> categoryBreakdown, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.services.SpendingTrendPoint> spendingTrend, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.services.BudgetEfficiency budgetEfficiency, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.services.PeriodAnalysis periodAnalysis) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public java.lang.String toString() {
        return null;
    }
}