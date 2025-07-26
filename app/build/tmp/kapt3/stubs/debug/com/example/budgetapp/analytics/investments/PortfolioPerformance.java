package com.example.budgetapp.analytics.investments;

/**
 * Data class representing portfolio-level performance
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b$\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u00a1\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\f\u001a\u00020\u0005\u0012\b\b\u0002\u0010\r\u001a\u00020\u0005\u0012\u0014\b\u0002\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00050\u000f\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0015\u00a2\u0006\u0002\u0010\u0016J\t\u0010*\u001a\u00020\u0003H\u00c6\u0003J\t\u0010+\u001a\u00020\u0005H\u00c6\u0003J\u0015\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00050\u000fH\u00c6\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0012H\u00c6\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0012H\u00c6\u0003J\t\u0010/\u001a\u00020\u0015H\u00c6\u0003J\t\u00100\u001a\u00020\u0005H\u00c6\u0003J\t\u00101\u001a\u00020\u0005H\u00c6\u0003J\t\u00102\u001a\u00020\u0005H\u00c6\u0003J\t\u00103\u001a\u00020\u0005H\u00c6\u0003J\t\u00104\u001a\u00020\u0005H\u00c6\u0003J\t\u00105\u001a\u00020\u0005H\u00c6\u0003J\t\u00106\u001a\u00020\u0005H\u00c6\u0003J\t\u00107\u001a\u00020\u0005H\u00c6\u0003J\u00a5\u0001\u00108\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u00052\u0014\b\u0002\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00050\u000f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u00c6\u0001J\u0013\u00109\u001a\u00020:2\b\u0010;\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010<\u001a\u00020\u0003H\u00d6\u0001J\t\u0010=\u001a\u00020>H\u00d6\u0001R\u0011\u0010\t\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001d\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00050\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0011\u0010\u0014\u001a\u00020\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018R\u0011\u0010\f\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0018R\u0011\u0010\r\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0018R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0018R\u0011\u0010\n\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0018R\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0018R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\'R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0018R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\"\u00a8\u0006?"}, d2 = {"Lcom/example/budgetapp/analytics/investments/PortfolioPerformance;", "", "totalInvestments", "", "totalValue", "", "totalCost", "totalGain", "portfolioReturn", "annualizedReturn", "totalDividendIncome", "averageDividendYield", "portfolioVolatility", "sharpeRatio", "assetAllocation", "", "Lcom/example/budgetapp/analytics/investments/entities/InvestmentType;", "topPerformer", "Lcom/example/budgetapp/analytics/investments/InvestmentPerformance;", "worstPerformer", "lastUpdated", "", "(IDDDDDDDDDLjava/util/Map;Lcom/example/budgetapp/analytics/investments/InvestmentPerformance;Lcom/example/budgetapp/analytics/investments/InvestmentPerformance;J)V", "getAnnualizedReturn", "()D", "getAssetAllocation", "()Ljava/util/Map;", "getAverageDividendYield", "getLastUpdated", "()J", "getPortfolioReturn", "getPortfolioVolatility", "getSharpeRatio", "getTopPerformer", "()Lcom/example/budgetapp/analytics/investments/InvestmentPerformance;", "getTotalCost", "getTotalDividendIncome", "getTotalGain", "getTotalInvestments", "()I", "getTotalValue", "getWorstPerformer", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "", "CashInCashOut_debug"})
public final class PortfolioPerformance {
    private final int totalInvestments = 0;
    private final double totalValue = 0.0;
    private final double totalCost = 0.0;
    private final double totalGain = 0.0;
    private final double portfolioReturn = 0.0;
    private final double annualizedReturn = 0.0;
    private final double totalDividendIncome = 0.0;
    private final double averageDividendYield = 0.0;
    private final double portfolioVolatility = 0.0;
    private final double sharpeRatio = 0.0;
    @org.jetbrains.annotations.NotNull
    private final java.util.Map<com.example.budgetapp.analytics.investments.entities.InvestmentType, java.lang.Double> assetAllocation = null;
    @org.jetbrains.annotations.Nullable
    private final com.example.budgetapp.analytics.investments.InvestmentPerformance topPerformer = null;
    @org.jetbrains.annotations.Nullable
    private final com.example.budgetapp.analytics.investments.InvestmentPerformance worstPerformer = null;
    private final long lastUpdated = 0L;
    
    public PortfolioPerformance(int totalInvestments, double totalValue, double totalCost, double totalGain, double portfolioReturn, double annualizedReturn, double totalDividendIncome, double averageDividendYield, double portfolioVolatility, double sharpeRatio, @org.jetbrains.annotations.NotNull
    java.util.Map<com.example.budgetapp.analytics.investments.entities.InvestmentType, java.lang.Double> assetAllocation, @org.jetbrains.annotations.Nullable
    com.example.budgetapp.analytics.investments.InvestmentPerformance topPerformer, @org.jetbrains.annotations.Nullable
    com.example.budgetapp.analytics.investments.InvestmentPerformance worstPerformer, long lastUpdated) {
        super();
    }
    
    public final int getTotalInvestments() {
        return 0;
    }
    
    public final double getTotalValue() {
        return 0.0;
    }
    
    public final double getTotalCost() {
        return 0.0;
    }
    
    public final double getTotalGain() {
        return 0.0;
    }
    
    public final double getPortfolioReturn() {
        return 0.0;
    }
    
    public final double getAnnualizedReturn() {
        return 0.0;
    }
    
    public final double getTotalDividendIncome() {
        return 0.0;
    }
    
    public final double getAverageDividendYield() {
        return 0.0;
    }
    
    public final double getPortfolioVolatility() {
        return 0.0;
    }
    
    public final double getSharpeRatio() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Map<com.example.budgetapp.analytics.investments.entities.InvestmentType, java.lang.Double> getAssetAllocation() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.example.budgetapp.analytics.investments.InvestmentPerformance getTopPerformer() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.example.budgetapp.analytics.investments.InvestmentPerformance getWorstPerformer() {
        return null;
    }
    
    public final long getLastUpdated() {
        return 0L;
    }
    
    public PortfolioPerformance() {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    public final double component10() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Map<com.example.budgetapp.analytics.investments.entities.InvestmentType, java.lang.Double> component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.example.budgetapp.analytics.investments.InvestmentPerformance component12() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.example.budgetapp.analytics.investments.InvestmentPerformance component13() {
        return null;
    }
    
    public final long component14() {
        return 0L;
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
    
    public final double component6() {
        return 0.0;
    }
    
    public final double component7() {
        return 0.0;
    }
    
    public final double component8() {
        return 0.0;
    }
    
    public final double component9() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.analytics.investments.PortfolioPerformance copy(int totalInvestments, double totalValue, double totalCost, double totalGain, double portfolioReturn, double annualizedReturn, double totalDividendIncome, double averageDividendYield, double portfolioVolatility, double sharpeRatio, @org.jetbrains.annotations.NotNull
    java.util.Map<com.example.budgetapp.analytics.investments.entities.InvestmentType, java.lang.Double> assetAllocation, @org.jetbrains.annotations.Nullable
    com.example.budgetapp.analytics.investments.InvestmentPerformance topPerformer, @org.jetbrains.annotations.Nullable
    com.example.budgetapp.analytics.investments.InvestmentPerformance worstPerformer, long lastUpdated) {
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