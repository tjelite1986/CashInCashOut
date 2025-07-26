package com.example.budgetapp.analytics.investments.portfolio;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\nH\u00c6\u0003J\t\u0010\u001f\u001a\u00020\fH\u00c6\u0003JO\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u00c6\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010$\u001a\u00020%H\u00d6\u0001J\t\u0010&\u001a\u00020\'H\u00d6\u0001R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011\u00a8\u0006("}, d2 = {"Lcom/example/budgetapp/analytics/investments/portfolio/RebalancingRecommendation;", "", "category", "Lcom/example/budgetapp/analytics/investments/entities/InvestmentCategory;", "currentPercent", "", "targetPercent", "difference", "adjustmentAmount", "action", "Lcom/example/budgetapp/analytics/investments/portfolio/RebalancingAction;", "priority", "Lcom/example/budgetapp/analytics/investments/portfolio/RebalancingPriority;", "(Lcom/example/budgetapp/analytics/investments/entities/InvestmentCategory;DDDDLcom/example/budgetapp/analytics/investments/portfolio/RebalancingAction;Lcom/example/budgetapp/analytics/investments/portfolio/RebalancingPriority;)V", "getAction", "()Lcom/example/budgetapp/analytics/investments/portfolio/RebalancingAction;", "getAdjustmentAmount", "()D", "getCategory", "()Lcom/example/budgetapp/analytics/investments/entities/InvestmentCategory;", "getCurrentPercent", "getDifference", "getPriority", "()Lcom/example/budgetapp/analytics/investments/portfolio/RebalancingPriority;", "getTargetPercent", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "", "CashInCashOut_debug"})
public final class RebalancingRecommendation {
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.analytics.investments.entities.InvestmentCategory category = null;
    private final double currentPercent = 0.0;
    private final double targetPercent = 0.0;
    private final double difference = 0.0;
    private final double adjustmentAmount = 0.0;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.analytics.investments.portfolio.RebalancingAction action = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.analytics.investments.portfolio.RebalancingPriority priority = null;
    
    public RebalancingRecommendation(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.entities.InvestmentCategory category, double currentPercent, double targetPercent, double difference, double adjustmentAmount, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.portfolio.RebalancingAction action, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.portfolio.RebalancingPriority priority) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.analytics.investments.entities.InvestmentCategory getCategory() {
        return null;
    }
    
    public final double getCurrentPercent() {
        return 0.0;
    }
    
    public final double getTargetPercent() {
        return 0.0;
    }
    
    public final double getDifference() {
        return 0.0;
    }
    
    public final double getAdjustmentAmount() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.analytics.investments.portfolio.RebalancingAction getAction() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.analytics.investments.portfolio.RebalancingPriority getPriority() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.analytics.investments.entities.InvestmentCategory component1() {
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
    public final com.example.budgetapp.analytics.investments.portfolio.RebalancingAction component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.analytics.investments.portfolio.RebalancingPriority component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.analytics.investments.portfolio.RebalancingRecommendation copy(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.entities.InvestmentCategory category, double currentPercent, double targetPercent, double difference, double adjustmentAmount, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.portfolio.RebalancingAction action, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.portfolio.RebalancingPriority priority) {
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