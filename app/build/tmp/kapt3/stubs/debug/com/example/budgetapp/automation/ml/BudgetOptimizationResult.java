package com.example.budgetapp.automation.ml;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003\u00a2\u0006\u0002\u0010\fJ\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0018\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0006H\u00c6\u0003J\u000f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003H\u00c6\u0003JQ\u0010\u001b\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\u00062\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003H\u00c6\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001J\t\u0010!\u001a\u00020\u000bH\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\t\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011\u00a8\u0006\""}, d2 = {"Lcom/example/budgetapp/automation/ml/BudgetOptimizationResult;", "", "adjustments", "", "Lcom/example/budgetapp/automation/ml/BudgetAdjustment;", "totalCurrentBudget", "", "totalOptimizedBudget", "potentialSavings", "overallConfidence", "implementationRecommendations", "", "(Ljava/util/List;DDDDLjava/util/List;)V", "getAdjustments", "()Ljava/util/List;", "getImplementationRecommendations", "getOverallConfidence", "()D", "getPotentialSavings", "getTotalCurrentBudget", "getTotalOptimizedBudget", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "CashInCashOut_debug"})
public final class BudgetOptimizationResult {
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.example.budgetapp.automation.ml.BudgetAdjustment> adjustments = null;
    private final double totalCurrentBudget = 0.0;
    private final double totalOptimizedBudget = 0.0;
    private final double potentialSavings = 0.0;
    private final double overallConfidence = 0.0;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<java.lang.String> implementationRecommendations = null;
    
    public BudgetOptimizationResult(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.automation.ml.BudgetAdjustment> adjustments, double totalCurrentBudget, double totalOptimizedBudget, double potentialSavings, double overallConfidence, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> implementationRecommendations) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.budgetapp.automation.ml.BudgetAdjustment> getAdjustments() {
        return null;
    }
    
    public final double getTotalCurrentBudget() {
        return 0.0;
    }
    
    public final double getTotalOptimizedBudget() {
        return 0.0;
    }
    
    public final double getPotentialSavings() {
        return 0.0;
    }
    
    public final double getOverallConfidence() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.String> getImplementationRecommendations() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.budgetapp.automation.ml.BudgetAdjustment> component1() {
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
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.String> component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.automation.ml.BudgetOptimizationResult copy(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.automation.ml.BudgetAdjustment> adjustments, double totalCurrentBudget, double totalOptimizedBudget, double potentialSavings, double overallConfidence, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> implementationRecommendations) {
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