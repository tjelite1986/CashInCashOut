package com.example.budgetapp.analytics.investments.portfolio;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J,\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\fH\u0002J\"\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\b0\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\fH\u0002J\u001e\u0010\u0014\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\fH\u0002J\u0016\u0010\u0015\u001a\u00020\b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\fH\u0002J\u0016\u0010\u0016\u001a\u00020\b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\fH\u0002J0\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u00182\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\fH\u0002J\u0016\u0010\u0019\u001a\u00020\b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\fH\u0002J\u001e\u0010\u001a\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J5\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001dJ\u0016\u0010\u001e\u001a\u00020\b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\fH\u0002J\u0016\u0010\u001f\u001a\u00020\b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\fH\u0002J;\u0010 \u001a\u00020!2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\f2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\"J*\u0010#\u001a\b\u0012\u0004\u0012\u00020\b0\f2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\fH\u0002J$\u0010$\u001a\u00020\b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\fH\u0002J;\u0010%\u001a\u00020&2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\f2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\"J$\u0010\'\u001a\u00020(2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\fH\u0002J$\u0010)\u001a\u00020\b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\f2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J\u0016\u0010*\u001a\u00020\b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\fH\u0002J\u0016\u0010+\u001a\u00020\b2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\fH\u0002JA\u0010,\u001a\b\u0012\u0004\u0012\u00020-0\f2\u0006\u0010.\u001a\u00020/2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\f2\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\b0\u0011H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00101J&\u00102\u001a\b\u0012\u0004\u0012\u0002030\f2\u0006\u00104\u001a\u00020\b2\u0006\u00105\u001a\u00020\b2\u0006\u00106\u001a\u00020\bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00067"}, d2 = {"Lcom/example/budgetapp/analytics/investments/portfolio/PortfolioAnalyticsService;", "", "context", "Landroid/content/Context;", "marketDataService", "Lcom/example/budgetapp/analytics/investments/marketdata/MarketDataService;", "(Landroid/content/Context;Lcom/example/budgetapp/analytics/investments/marketdata/MarketDataService;)V", "calculateAnnualizedReturn", "", "investment", "Lcom/example/budgetapp/analytics/investments/entities/Investment;", "transactions", "", "Lcom/example/budgetapp/analytics/investments/entities/InvestmentTransaction;", "priceHistory", "Lcom/example/budgetapp/analytics/investments/entities/InvestmentPriceHistory;", "calculateAssetAllocation", "", "Lcom/example/budgetapp/analytics/investments/entities/InvestmentCategory;", "investments", "calculateBeta", "calculateConcentrationRisk", "calculateCurrencyRisk", "calculateDayChange", "Lkotlin/Pair;", "calculateDiversificationScore", "calculateInvestmentCost", "calculateInvestmentPerformance", "Lcom/example/budgetapp/analytics/investments/portfolio/InvestmentPerformance;", "(Lcom/example/budgetapp/analytics/investments/entities/Investment;Ljava/util/List;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "calculateLiquidityRisk", "calculateMaxDrawdown", "calculatePortfolioPerformance", "Lcom/example/budgetapp/analytics/investments/portfolio/PortfolioPerformance;", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "calculatePortfolioReturns", "calculatePortfolioVolatility", "calculateRiskAssessment", "Lcom/example/budgetapp/analytics/investments/portfolio/RiskAssessment;", "calculateRiskMetrics", "Lcom/example/budgetapp/analytics/investments/portfolio/RiskMetrics;", "calculateTotalCost", "calculateTotalValue", "calculateVolatility", "generateRebalancingRecommendations", "Lcom/example/budgetapp/analytics/investments/portfolio/RebalancingRecommendation;", "portfolio", "Lcom/example/budgetapp/analytics/investments/entities/InvestmentPortfolio;", "targetAllocation", "(Lcom/example/budgetapp/analytics/investments/entities/InvestmentPortfolio;Ljava/util/List;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateRiskRecommendations", "", "overallRisk", "concentrationRisk", "currencyRisk", "CashInCashOut_debug"})
public final class PortfolioAnalyticsService {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.analytics.investments.marketdata.MarketDataService marketDataService = null;
    
    public PortfolioAnalyticsService(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.marketdata.MarketDataService marketDataService) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object calculatePortfolioPerformance(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.analytics.investments.entities.Investment> investments, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.analytics.investments.entities.InvestmentTransaction> transactions, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.analytics.investments.entities.InvestmentPriceHistory> priceHistory, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.analytics.investments.portfolio.PortfolioPerformance> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object calculateInvestmentPerformance(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.entities.Investment investment, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.analytics.investments.entities.InvestmentTransaction> transactions, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.analytics.investments.entities.InvestmentPriceHistory> priceHistory, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.analytics.investments.portfolio.InvestmentPerformance> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object generateRebalancingRecommendations(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.entities.InvestmentPortfolio portfolio, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.analytics.investments.entities.Investment> investments, @org.jetbrains.annotations.NotNull
    java.util.Map<com.example.budgetapp.analytics.investments.entities.InvestmentCategory, java.lang.Double> targetAllocation, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.analytics.investments.portfolio.RebalancingRecommendation>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object calculateRiskAssessment(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.analytics.investments.entities.Investment> investments, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.analytics.investments.entities.InvestmentTransaction> transactions, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.analytics.investments.entities.InvestmentPriceHistory> priceHistory, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.analytics.investments.portfolio.RiskAssessment> $completion) {
        return null;
    }
    
    private final double calculateTotalValue(java.util.List<com.example.budgetapp.analytics.investments.entities.Investment> investments) {
        return 0.0;
    }
    
    private final double calculateTotalCost(java.util.List<com.example.budgetapp.analytics.investments.entities.Investment> investments, java.util.List<com.example.budgetapp.analytics.investments.entities.InvestmentTransaction> transactions) {
        return 0.0;
    }
    
    private final double calculateInvestmentCost(com.example.budgetapp.analytics.investments.entities.Investment investment, java.util.List<com.example.budgetapp.analytics.investments.entities.InvestmentTransaction> transactions) {
        return 0.0;
    }
    
    private final kotlin.Pair<java.lang.Double, java.lang.Double> calculateDayChange(java.util.List<com.example.budgetapp.analytics.investments.entities.Investment> investments, java.util.List<com.example.budgetapp.analytics.investments.entities.InvestmentPriceHistory> priceHistory) {
        return null;
    }
    
    private final java.util.Map<com.example.budgetapp.analytics.investments.entities.InvestmentCategory, java.lang.Double> calculateAssetAllocation(java.util.List<com.example.budgetapp.analytics.investments.entities.Investment> investments) {
        return null;
    }
    
    private final com.example.budgetapp.analytics.investments.portfolio.RiskMetrics calculateRiskMetrics(java.util.List<com.example.budgetapp.analytics.investments.entities.Investment> investments, java.util.List<com.example.budgetapp.analytics.investments.entities.InvestmentPriceHistory> priceHistory) {
        return null;
    }
    
    private final java.util.List<java.lang.Double> calculatePortfolioReturns(java.util.List<com.example.budgetapp.analytics.investments.entities.Investment> investments, java.util.List<com.example.budgetapp.analytics.investments.entities.InvestmentPriceHistory> priceHistory) {
        return null;
    }
    
    private final double calculateDiversificationScore(java.util.List<com.example.budgetapp.analytics.investments.entities.Investment> investments) {
        return 0.0;
    }
    
    private final double calculateAnnualizedReturn(com.example.budgetapp.analytics.investments.entities.Investment investment, java.util.List<com.example.budgetapp.analytics.investments.entities.InvestmentTransaction> transactions, java.util.List<com.example.budgetapp.analytics.investments.entities.InvestmentPriceHistory> priceHistory) {
        return 0.0;
    }
    
    private final double calculateVolatility(java.util.List<com.example.budgetapp.analytics.investments.entities.InvestmentPriceHistory> priceHistory) {
        return 0.0;
    }
    
    private final double calculateMaxDrawdown(java.util.List<com.example.budgetapp.analytics.investments.entities.InvestmentPriceHistory> priceHistory) {
        return 0.0;
    }
    
    private final double calculateBeta(com.example.budgetapp.analytics.investments.entities.Investment investment, java.util.List<com.example.budgetapp.analytics.investments.entities.InvestmentPriceHistory> priceHistory) {
        return 0.0;
    }
    
    private final double calculatePortfolioVolatility(java.util.List<com.example.budgetapp.analytics.investments.entities.Investment> investments, java.util.List<com.example.budgetapp.analytics.investments.entities.InvestmentPriceHistory> priceHistory) {
        return 0.0;
    }
    
    private final double calculateConcentrationRisk(java.util.List<com.example.budgetapp.analytics.investments.entities.Investment> investments) {
        return 0.0;
    }
    
    private final double calculateCurrencyRisk(java.util.List<com.example.budgetapp.analytics.investments.entities.Investment> investments) {
        return 0.0;
    }
    
    private final double calculateLiquidityRisk(java.util.List<com.example.budgetapp.analytics.investments.entities.Investment> investments) {
        return 0.0;
    }
    
    private final java.util.List<java.lang.String> generateRiskRecommendations(double overallRisk, double concentrationRisk, double currencyRisk) {
        return null;
    }
}