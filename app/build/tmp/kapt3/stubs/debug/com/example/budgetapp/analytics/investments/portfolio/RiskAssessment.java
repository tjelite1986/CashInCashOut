package com.example.budgetapp.analytics.investments.portfolio;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0010\r\u001a\u00020\u000e\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010 \u001a\u00020\u0003H\u00c6\u0003J\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u00c6\u0003J\t\u0010#\u001a\u00020\u000eH\u00c6\u0003J_\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u00c6\u0001J\u0013\u0010%\u001a\u00020&2\b\u0010\'\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010(\u001a\u00020)H\u00d6\u0001J\t\u0010*\u001a\u00020\fH\u00d6\u0001R\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0011\u00a8\u0006+"}, d2 = {"Lcom/example/budgetapp/analytics/investments/portfolio/RiskAssessment;", "", "overallRiskScore", "", "riskLevel", "Lcom/example/budgetapp/analytics/investments/portfolio/RiskLevel;", "volatilityRisk", "concentrationRisk", "currencyRisk", "liquidityRisk", "recommendations", "", "", "lastUpdated", "", "(DLcom/example/budgetapp/analytics/investments/portfolio/RiskLevel;DDDDLjava/util/List;J)V", "getConcentrationRisk", "()D", "getCurrencyRisk", "getLastUpdated", "()J", "getLiquidityRisk", "getOverallRiskScore", "getRecommendations", "()Ljava/util/List;", "getRiskLevel", "()Lcom/example/budgetapp/analytics/investments/portfolio/RiskLevel;", "getVolatilityRisk", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "CashInCashOut_debug"})
public final class RiskAssessment {
    private final double overallRiskScore = 0.0;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.analytics.investments.portfolio.RiskLevel riskLevel = null;
    private final double volatilityRisk = 0.0;
    private final double concentrationRisk = 0.0;
    private final double currencyRisk = 0.0;
    private final double liquidityRisk = 0.0;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<java.lang.String> recommendations = null;
    private final long lastUpdated = 0L;
    
    public RiskAssessment(double overallRiskScore, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.portfolio.RiskLevel riskLevel, double volatilityRisk, double concentrationRisk, double currencyRisk, double liquidityRisk, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> recommendations, long lastUpdated) {
        super();
    }
    
    public final double getOverallRiskScore() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.analytics.investments.portfolio.RiskLevel getRiskLevel() {
        return null;
    }
    
    public final double getVolatilityRisk() {
        return 0.0;
    }
    
    public final double getConcentrationRisk() {
        return 0.0;
    }
    
    public final double getCurrencyRisk() {
        return 0.0;
    }
    
    public final double getLiquidityRisk() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.String> getRecommendations() {
        return null;
    }
    
    public final long getLastUpdated() {
        return 0L;
    }
    
    public final double component1() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.analytics.investments.portfolio.RiskLevel component2() {
        return null;
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
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.String> component7() {
        return null;
    }
    
    public final long component8() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.analytics.investments.portfolio.RiskAssessment copy(double overallRiskScore, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.portfolio.RiskLevel riskLevel, double volatilityRisk, double concentrationRisk, double currencyRisk, double liquidityRisk, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> recommendations, long lastUpdated) {
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