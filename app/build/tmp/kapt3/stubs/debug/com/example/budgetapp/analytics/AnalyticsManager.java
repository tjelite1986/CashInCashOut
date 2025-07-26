package com.example.budgetapp.analytics;

/**
 * Central coordinator for all Stage 1 Analytics & Intelligence features
 * Provides unified access to AI, Investment, and Tax analytics services
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0011\u0010\u0015\u001a\u00020\u0016H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J\u0006\u0010\u0018\u001a\u00020\u0019R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001b\u0010\u000b\u001a\u00020\f8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0010\u001a\u00020\u00118FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\n\u001a\u0004\b\u0012\u0010\u0013\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001b"}, d2 = {"Lcom/example/budgetapp/analytics/AnalyticsManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "investmentService", "Lcom/example/budgetapp/analytics/investments/InvestmentAnalyticsService;", "getInvestmentService", "()Lcom/example/budgetapp/analytics/investments/InvestmentAnalyticsService;", "investmentService$delegate", "Lkotlin/Lazy;", "nlpService", "Lcom/example/budgetapp/analytics/ai/NLPCategorizationService;", "getNlpService", "()Lcom/example/budgetapp/analytics/ai/NLPCategorizationService;", "nlpService$delegate", "taxService", "Lcom/example/budgetapp/analytics/tax/TaxReportingService;", "getTaxService", "()Lcom/example/budgetapp/analytics/tax/TaxReportingService;", "taxService$delegate", "initialize", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isStage1Available", "", "Companion", "CashInCashOut_debug"})
public final class AnalyticsManager {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy nlpService$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy investmentService$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy taxService$delegate = null;
    @kotlin.jvm.Volatile
    @org.jetbrains.annotations.Nullable
    private static volatile com.example.budgetapp.analytics.AnalyticsManager INSTANCE;
    @org.jetbrains.annotations.NotNull
    public static final com.example.budgetapp.analytics.AnalyticsManager.Companion Companion = null;
    
    private AnalyticsManager(android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.analytics.ai.NLPCategorizationService getNlpService() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.analytics.investments.InvestmentAnalyticsService getInvestmentService() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.analytics.tax.TaxReportingService getTaxService() {
        return null;
    }
    
    /**
     * Initialize all analytics services
     * Should be called once during app startup
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object initialize(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Check if all Stage 1 features are available
     */
    public final boolean isStage1Available() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/example/budgetapp/analytics/AnalyticsManager$Companion;", "", "()V", "INSTANCE", "Lcom/example/budgetapp/analytics/AnalyticsManager;", "getInstance", "context", "Landroid/content/Context;", "CashInCashOut_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.budgetapp.analytics.AnalyticsManager getInstance(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
            return null;
        }
    }
}