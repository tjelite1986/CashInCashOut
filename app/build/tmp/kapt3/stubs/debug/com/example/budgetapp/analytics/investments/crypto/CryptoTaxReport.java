package com.example.budgetapp.analytics.investments.crypto;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001BC\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0002\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u00c6\u0003J\t\u0010\u001e\u001a\u00020\fH\u00c6\u0003JU\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u00c6\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010#\u001a\u00020$H\u00d6\u0001J\t\u0010%\u001a\u00020&H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000f\u00a8\u0006\'"}, d2 = {"Lcom/example/budgetapp/analytics/investments/crypto/CryptoTaxReport;", "", "totalGains", "", "totalLosses", "shortTermGains", "longTermGains", "netGainLoss", "taxEvents", "", "Lcom/example/budgetapp/analytics/investments/crypto/CryptoTaxEvent;", "reportGenerated", "", "(DDDDDLjava/util/List;J)V", "getLongTermGains", "()D", "getNetGainLoss", "getReportGenerated", "()J", "getShortTermGains", "getTaxEvents", "()Ljava/util/List;", "getTotalGains", "getTotalLosses", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "", "CashInCashOut_debug"})
public final class CryptoTaxReport {
    private final double totalGains = 0.0;
    private final double totalLosses = 0.0;
    private final double shortTermGains = 0.0;
    private final double longTermGains = 0.0;
    private final double netGainLoss = 0.0;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.example.budgetapp.analytics.investments.crypto.CryptoTaxEvent> taxEvents = null;
    private final long reportGenerated = 0L;
    
    public CryptoTaxReport(double totalGains, double totalLosses, double shortTermGains, double longTermGains, double netGainLoss, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.analytics.investments.crypto.CryptoTaxEvent> taxEvents, long reportGenerated) {
        super();
    }
    
    public final double getTotalGains() {
        return 0.0;
    }
    
    public final double getTotalLosses() {
        return 0.0;
    }
    
    public final double getShortTermGains() {
        return 0.0;
    }
    
    public final double getLongTermGains() {
        return 0.0;
    }
    
    public final double getNetGainLoss() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.budgetapp.analytics.investments.crypto.CryptoTaxEvent> getTaxEvents() {
        return null;
    }
    
    public final long getReportGenerated() {
        return 0L;
    }
    
    public final double component1() {
        return 0.0;
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
    public final java.util.List<com.example.budgetapp.analytics.investments.crypto.CryptoTaxEvent> component6() {
        return null;
    }
    
    public final long component7() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.analytics.investments.crypto.CryptoTaxReport copy(double totalGains, double totalLosses, double shortTermGains, double longTermGains, double netGainLoss, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.analytics.investments.crypto.CryptoTaxEvent> taxEvents, long reportGenerated) {
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