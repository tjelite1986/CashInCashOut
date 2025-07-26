package com.example.budgetapp.automation;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BG\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\bH\u00c6\u0003J\t\u0010\u001b\u001a\u00020\bH\u00c6\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\rH\u00c6\u0003JW\u0010\u001e\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00032\b\b\u0002\u0010\f\u001a\u00020\rH\u00c6\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\"\u001a\u00020#H\u00d6\u0001J\t\u0010$\u001a\u00020\u000bH\u00d6\u0001R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\t\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016\u00a8\u0006%"}, d2 = {"Lcom/example/budgetapp/automation/BudgetOptimizationReport;", "", "optimizations", "", "Lcom/example/budgetapp/automation/BudgetOptimization;", "newBudgetRecommendations", "Lcom/example/budgetapp/automation/NewBudgetRecommendation;", "totalPotentialSavings", "", "overallScore", "implementationPriority", "", "generatedAt", "", "(Ljava/util/List;Ljava/util/List;DDLjava/util/List;J)V", "getGeneratedAt", "()J", "getImplementationPriority", "()Ljava/util/List;", "getNewBudgetRecommendations", "getOptimizations", "getOverallScore", "()D", "getTotalPotentialSavings", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "CashInCashOut_debug"})
public final class BudgetOptimizationReport {
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.example.budgetapp.automation.BudgetOptimization> optimizations = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.example.budgetapp.automation.NewBudgetRecommendation> newBudgetRecommendations = null;
    private final double totalPotentialSavings = 0.0;
    private final double overallScore = 0.0;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<java.lang.String> implementationPriority = null;
    private final long generatedAt = 0L;
    
    public BudgetOptimizationReport(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.automation.BudgetOptimization> optimizations, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.automation.NewBudgetRecommendation> newBudgetRecommendations, double totalPotentialSavings, double overallScore, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> implementationPriority, long generatedAt) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.budgetapp.automation.BudgetOptimization> getOptimizations() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.budgetapp.automation.NewBudgetRecommendation> getNewBudgetRecommendations() {
        return null;
    }
    
    public final double getTotalPotentialSavings() {
        return 0.0;
    }
    
    public final double getOverallScore() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.String> getImplementationPriority() {
        return null;
    }
    
    public final long getGeneratedAt() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.budgetapp.automation.BudgetOptimization> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.budgetapp.automation.NewBudgetRecommendation> component2() {
        return null;
    }
    
    public final double component3() {
        return 0.0;
    }
    
    public final double component4() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.String> component5() {
        return null;
    }
    
    public final long component6() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.automation.BudgetOptimizationReport copy(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.automation.BudgetOptimization> optimizations, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.automation.NewBudgetRecommendation> newBudgetRecommendations, double totalPotentialSavings, double overallScore, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> implementationPriority, long generatedAt) {
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