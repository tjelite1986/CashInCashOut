package com.example.budgetapp.analytics.investments.marketdata;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u0006H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ\u001b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0006H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ#\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0006H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0002J\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019J\u001b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u000b\u001a\u00020\u0006H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ\u001b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u000b\u001a\u00020\u0006H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ\u0010\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u0006H\u0002J\u001a\u0010 \u001a\u0004\u0018\u00010\n2\u0006\u0010!\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0002J\u001a\u0010\"\u001a\u0004\u0018\u00010\u000e2\u0006\u0010!\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0002J\"\u0010#\u001a\u0004\u0018\u00010\u00112\u0006\u0010!\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0006H\u0002J\u0016\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u00172\u0006\u0010!\u001a\u00020\u0006H\u0002J\u001a\u0010&\u001a\u0004\u0018\u00010\u001b2\u0006\u0010!\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0002J\u001a\u0010\'\u001a\u0004\u0018\u00010\u001d2\u0006\u0010!\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0002J\u001f\u0010(\u001a\b\u0012\u0004\u0012\u00020%0\u00172\u0006\u0010)\u001a\u00020\u0006H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006*"}, d2 = {"Lcom/example/budgetapp/analytics/investments/marketdata/MarketDataService;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "alphaVantageApiKey", "", "coinGeckoBaseUrl", "finnhubApiKey", "getCompanyProfile", "Lcom/example/budgetapp/analytics/investments/marketdata/CompanyProfile;", "symbol", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCryptoPrice", "Lcom/example/budgetapp/analytics/investments/marketdata/CryptoPrice;", "cryptoId", "getExchangeRate", "Lcom/example/budgetapp/analytics/investments/marketdata/ExchangeRate;", "fromCurrency", "toCurrency", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getIndexName", "getMarketIndices", "", "Lcom/example/budgetapp/analytics/investments/marketdata/MarketIndex;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getStockPrice", "Lcom/example/budgetapp/analytics/investments/marketdata/StockPrice;", "getStockQuote", "Lcom/example/budgetapp/analytics/investments/marketdata/StockQuote;", "makeApiCall", "urlString", "parseCompanyProfile", "response", "parseCryptoPrice", "parseExchangeRate", "parseSearchResults", "Lcom/example/budgetapp/analytics/investments/marketdata/SecuritySearchResult;", "parseStockPrice", "parseStockQuote", "searchSymbols", "query", "CashInCashOut_debug"})
public final class MarketDataService {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String alphaVantageApiKey = "demo";
    @org.jetbrains.annotations.NotNull
    private final java.lang.String finnhubApiKey = "demo";
    @org.jetbrains.annotations.NotNull
    private final java.lang.String coinGeckoBaseUrl = "https://api.coingecko.com/api/v3";
    
    public MarketDataService(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getStockPrice(@org.jetbrains.annotations.NotNull
    java.lang.String symbol, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.analytics.investments.marketdata.StockPrice> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getCryptoPrice(@org.jetbrains.annotations.NotNull
    java.lang.String cryptoId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.analytics.investments.marketdata.CryptoPrice> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getExchangeRate(@org.jetbrains.annotations.NotNull
    java.lang.String fromCurrency, @org.jetbrains.annotations.NotNull
    java.lang.String toCurrency, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.analytics.investments.marketdata.ExchangeRate> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getMarketIndices(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.analytics.investments.marketdata.MarketIndex>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getStockQuote(@org.jetbrains.annotations.NotNull
    java.lang.String symbol, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.analytics.investments.marketdata.StockQuote> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object searchSymbols(@org.jetbrains.annotations.NotNull
    java.lang.String query, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.analytics.investments.marketdata.SecuritySearchResult>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getCompanyProfile(@org.jetbrains.annotations.NotNull
    java.lang.String symbol, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.analytics.investments.marketdata.CompanyProfile> $completion) {
        return null;
    }
    
    private final java.lang.String makeApiCall(java.lang.String urlString) {
        return null;
    }
    
    private final com.example.budgetapp.analytics.investments.marketdata.StockPrice parseStockPrice(java.lang.String response, java.lang.String symbol) {
        return null;
    }
    
    private final com.example.budgetapp.analytics.investments.marketdata.CryptoPrice parseCryptoPrice(java.lang.String response, java.lang.String cryptoId) {
        return null;
    }
    
    private final com.example.budgetapp.analytics.investments.marketdata.ExchangeRate parseExchangeRate(java.lang.String response, java.lang.String fromCurrency, java.lang.String toCurrency) {
        return null;
    }
    
    private final com.example.budgetapp.analytics.investments.marketdata.StockQuote parseStockQuote(java.lang.String response, java.lang.String symbol) {
        return null;
    }
    
    private final java.util.List<com.example.budgetapp.analytics.investments.marketdata.SecuritySearchResult> parseSearchResults(java.lang.String response) {
        return null;
    }
    
    private final com.example.budgetapp.analytics.investments.marketdata.CompanyProfile parseCompanyProfile(java.lang.String response, java.lang.String symbol) {
        return null;
    }
    
    private final java.lang.String getIndexName(java.lang.String symbol) {
        return null;
    }
}