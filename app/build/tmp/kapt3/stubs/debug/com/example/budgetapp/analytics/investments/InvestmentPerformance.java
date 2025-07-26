package com.example.budgetapp.analytics.investments;

/**
 * Data class representing investment performance metrics
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\'\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B}\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\u0006\u0010\f\u001a\u00020\t\u0012\u0006\u0010\r\u001a\u00020\t\u0012\u0006\u0010\u000e\u001a\u00020\t\u0012\u0006\u0010\u000f\u001a\u00020\t\u0012\u0006\u0010\u0010\u001a\u00020\t\u0012\u0006\u0010\u0011\u001a\u00020\t\u0012\u0006\u0010\u0012\u001a\u00020\t\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0016J\t\u0010+\u001a\u00020\u0003H\u00c6\u0003J\t\u0010,\u001a\u00020\tH\u00c6\u0003J\t\u0010-\u001a\u00020\tH\u00c6\u0003J\t\u0010.\u001a\u00020\tH\u00c6\u0003J\t\u0010/\u001a\u00020\tH\u00c6\u0003J\t\u00100\u001a\u00020\u0014H\u00c6\u0003J\t\u00101\u001a\u00020\u0003H\u00c6\u0003J\t\u00102\u001a\u00020\u0005H\u00c6\u0003J\t\u00103\u001a\u00020\u0007H\u00c6\u0003J\t\u00104\u001a\u00020\tH\u00c6\u0003J\t\u00105\u001a\u00020\tH\u00c6\u0003J\t\u00106\u001a\u00020\tH\u00c6\u0003J\t\u00107\u001a\u00020\tH\u00c6\u0003J\t\u00108\u001a\u00020\tH\u00c6\u0003J\t\u00109\u001a\u00020\tH\u00c6\u0003J\u009f\u0001\u0010:\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\t2\b\b\u0002\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\t2\b\b\u0002\u0010\u0011\u001a\u00020\t2\b\b\u0002\u0010\u0012\u001a\u00020\t2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010;\u001a\u00020<2\b\u0010=\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010>\u001a\u00020\u0014H\u00d6\u0001J\t\u0010?\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u000b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\r\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0011\u0010\n\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0011\u0010\u000e\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0011\u0010\u000f\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u0011\u0010\u0013\u001a\u00020\u0014\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u0015\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010 R\u0011\u0010\u0012\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0018R\u0011\u0010\f\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u0018R\u0011\u0010\u0011\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0018R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0018R\u0011\u0010\u0010\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0018\u00a8\u0006@"}, d2 = {"Lcom/example/budgetapp/analytics/investments/InvestmentPerformance;", "", "investmentId", "", "investmentName", "", "investmentType", "Lcom/example/budgetapp/analytics/investments/entities/InvestmentType;", "totalCost", "", "currentValue", "absoluteGain", "percentageGain", "annualizedReturn", "dividendIncome", "dividendYield", "volatility", "sharpeRatio", "maxDrawdown", "holdingPeriodDays", "", "lastPriceUpdate", "(JLjava/lang/String;Lcom/example/budgetapp/analytics/investments/entities/InvestmentType;DDDDDDDDDDIJ)V", "getAbsoluteGain", "()D", "getAnnualizedReturn", "getCurrentValue", "getDividendIncome", "getDividendYield", "getHoldingPeriodDays", "()I", "getInvestmentId", "()J", "getInvestmentName", "()Ljava/lang/String;", "getInvestmentType", "()Lcom/example/budgetapp/analytics/investments/entities/InvestmentType;", "getLastPriceUpdate", "getMaxDrawdown", "getPercentageGain", "getSharpeRatio", "getTotalCost", "getVolatility", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "CashInCashOut_debug"})
public final class InvestmentPerformance {
    private final long investmentId = 0L;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String investmentName = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.analytics.investments.entities.InvestmentType investmentType = null;
    private final double totalCost = 0.0;
    private final double currentValue = 0.0;
    private final double absoluteGain = 0.0;
    private final double percentageGain = 0.0;
    private final double annualizedReturn = 0.0;
    private final double dividendIncome = 0.0;
    private final double dividendYield = 0.0;
    private final double volatility = 0.0;
    private final double sharpeRatio = 0.0;
    private final double maxDrawdown = 0.0;
    private final int holdingPeriodDays = 0;
    private final long lastPriceUpdate = 0L;
    
    public InvestmentPerformance(long investmentId, @org.jetbrains.annotations.NotNull
    java.lang.String investmentName, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.entities.InvestmentType investmentType, double totalCost, double currentValue, double absoluteGain, double percentageGain, double annualizedReturn, double dividendIncome, double dividendYield, double volatility, double sharpeRatio, double maxDrawdown, int holdingPeriodDays, long lastPriceUpdate) {
        super();
    }
    
    public final long getInvestmentId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getInvestmentName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.analytics.investments.entities.InvestmentType getInvestmentType() {
        return null;
    }
    
    public final double getTotalCost() {
        return 0.0;
    }
    
    public final double getCurrentValue() {
        return 0.0;
    }
    
    public final double getAbsoluteGain() {
        return 0.0;
    }
    
    public final double getPercentageGain() {
        return 0.0;
    }
    
    public final double getAnnualizedReturn() {
        return 0.0;
    }
    
    public final double getDividendIncome() {
        return 0.0;
    }
    
    public final double getDividendYield() {
        return 0.0;
    }
    
    public final double getVolatility() {
        return 0.0;
    }
    
    public final double getSharpeRatio() {
        return 0.0;
    }
    
    public final double getMaxDrawdown() {
        return 0.0;
    }
    
    public final int getHoldingPeriodDays() {
        return 0;
    }
    
    public final long getLastPriceUpdate() {
        return 0L;
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final double component10() {
        return 0.0;
    }
    
    public final double component11() {
        return 0.0;
    }
    
    public final double component12() {
        return 0.0;
    }
    
    public final double component13() {
        return 0.0;
    }
    
    public final int component14() {
        return 0;
    }
    
    public final long component15() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.analytics.investments.entities.InvestmentType component3() {
        return null;
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
    public final com.example.budgetapp.analytics.investments.InvestmentPerformance copy(long investmentId, @org.jetbrains.annotations.NotNull
    java.lang.String investmentName, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.entities.InvestmentType investmentType, double totalCost, double currentValue, double absoluteGain, double percentageGain, double annualizedReturn, double dividendIncome, double dividendYield, double volatility, double sharpeRatio, double maxDrawdown, int holdingPeriodDays, long lastPriceUpdate) {
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