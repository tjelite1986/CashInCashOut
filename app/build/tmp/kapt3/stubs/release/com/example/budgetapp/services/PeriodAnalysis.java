package com.example.budgetapp.services;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b0\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000bJ\u0015\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0004H\u00c6\u0003J\u0015\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b0\u0003H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\nH\u00c6\u0003JI\u0010\u0017\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00042\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b0\u00032\b\b\u0002\u0010\t\u001a\u00020\nH\u00c6\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001b\u001a\u00020\u0005H\u00d6\u0001J\t\u0010\u001c\u001a\u00020\nH\u00d6\u0001R\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b0\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006\u001d"}, d2 = {"Lcom/example/budgetapp/services/PeriodAnalysis;", "", "periodDistribution", "", "Lcom/example/budgetapp/database/entities/BudgetPeriod;", "", "mostCommonPeriod", "averageBudgetByPeriod", "", "recommendation", "", "(Ljava/util/Map;Lcom/example/budgetapp/database/entities/BudgetPeriod;Ljava/util/Map;Ljava/lang/String;)V", "getAverageBudgetByPeriod", "()Ljava/util/Map;", "getMostCommonPeriod", "()Lcom/example/budgetapp/database/entities/BudgetPeriod;", "getPeriodDistribution", "getRecommendation", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "CashInCashOut_release"})
public final class PeriodAnalysis {
    @org.jetbrains.annotations.NotNull
    private final java.util.Map<com.example.budgetapp.database.entities.BudgetPeriod, java.lang.Integer> periodDistribution = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.entities.BudgetPeriod mostCommonPeriod = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.Map<com.example.budgetapp.database.entities.BudgetPeriod, java.lang.Double> averageBudgetByPeriod = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String recommendation = null;
    
    public PeriodAnalysis(@org.jetbrains.annotations.NotNull
    java.util.Map<com.example.budgetapp.database.entities.BudgetPeriod, java.lang.Integer> periodDistribution, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.BudgetPeriod mostCommonPeriod, @org.jetbrains.annotations.NotNull
    java.util.Map<com.example.budgetapp.database.entities.BudgetPeriod, java.lang.Double> averageBudgetByPeriod, @org.jetbrains.annotations.NotNull
    java.lang.String recommendation) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Map<com.example.budgetapp.database.entities.BudgetPeriod, java.lang.Integer> getPeriodDistribution() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.BudgetPeriod getMostCommonPeriod() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Map<com.example.budgetapp.database.entities.BudgetPeriod, java.lang.Double> getAverageBudgetByPeriod() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getRecommendation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Map<com.example.budgetapp.database.entities.BudgetPeriod, java.lang.Integer> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.BudgetPeriod component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Map<com.example.budgetapp.database.entities.BudgetPeriod, java.lang.Double> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.services.PeriodAnalysis copy(@org.jetbrains.annotations.NotNull
    java.util.Map<com.example.budgetapp.database.entities.BudgetPeriod, java.lang.Integer> periodDistribution, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.BudgetPeriod mostCommonPeriod, @org.jetbrains.annotations.NotNull
    java.util.Map<com.example.budgetapp.database.entities.BudgetPeriod, java.lang.Double> averageBudgetByPeriod, @org.jetbrains.annotations.NotNull
    java.lang.String recommendation) {
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