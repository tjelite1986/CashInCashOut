package com.example.budgetapp.analytics.investments.multicurrency;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\u0002\u0010\u0010J\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010 \u001a\u00020\u0006H\u00c6\u0003J\t\u0010!\u001a\u00020\u0006H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0006H\u00c6\u0003J\u000f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u00c6\u0003J\t\u0010$\u001a\u00020\rH\u00c6\u0003J\t\u0010%\u001a\u00020\u000fH\u00c6\u0003J_\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u00c6\u0001J\u0013\u0010\'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010*\u001a\u00020\rH\u00d6\u0001J\t\u0010+\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\b\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0012\u00a8\u0006,"}, d2 = {"Lcom/example/budgetapp/analytics/investments/multicurrency/CurrencyTrend;", "", "baseCurrency", "", "targetCurrency", "currentRate", "", "change", "changePercent", "historicalRates", "", "Lcom/example/budgetapp/analytics/investments/multicurrency/CurrencyRatePoint;", "periodDays", "", "lastUpdated", "", "(Ljava/lang/String;Ljava/lang/String;DDDLjava/util/List;IJ)V", "getBaseCurrency", "()Ljava/lang/String;", "getChange", "()D", "getChangePercent", "getCurrentRate", "getHistoricalRates", "()Ljava/util/List;", "getLastUpdated", "()J", "getPeriodDays", "()I", "getTargetCurrency", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "toString", "CashInCashOut_debug"})
public final class CurrencyTrend {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String baseCurrency = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String targetCurrency = null;
    private final double currentRate = 0.0;
    private final double change = 0.0;
    private final double changePercent = 0.0;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.example.budgetapp.analytics.investments.multicurrency.CurrencyRatePoint> historicalRates = null;
    private final int periodDays = 0;
    private final long lastUpdated = 0L;
    
    public CurrencyTrend(@org.jetbrains.annotations.NotNull
    java.lang.String baseCurrency, @org.jetbrains.annotations.NotNull
    java.lang.String targetCurrency, double currentRate, double change, double changePercent, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.analytics.investments.multicurrency.CurrencyRatePoint> historicalRates, int periodDays, long lastUpdated) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getBaseCurrency() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getTargetCurrency() {
        return null;
    }
    
    public final double getCurrentRate() {
        return 0.0;
    }
    
    public final double getChange() {
        return 0.0;
    }
    
    public final double getChangePercent() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.budgetapp.analytics.investments.multicurrency.CurrencyRatePoint> getHistoricalRates() {
        return null;
    }
    
    public final int getPeriodDays() {
        return 0;
    }
    
    public final long getLastUpdated() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component2() {
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
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.budgetapp.analytics.investments.multicurrency.CurrencyRatePoint> component6() {
        return null;
    }
    
    public final int component7() {
        return 0;
    }
    
    public final long component8() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.analytics.investments.multicurrency.CurrencyTrend copy(@org.jetbrains.annotations.NotNull
    java.lang.String baseCurrency, @org.jetbrains.annotations.NotNull
    java.lang.String targetCurrency, double currentRate, double change, double changePercent, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.analytics.investments.multicurrency.CurrencyRatePoint> historicalRates, int periodDays, long lastUpdated) {
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