package com.example.budgetapp.analytics.investments.multicurrency;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J+\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0006H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ \u0010\u0010\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00062\b\b\u0002\u0010\u0012\u001a\u00020\u0013J\'\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\f0\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u0006H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u0006J7\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010\u0016\u001a\u00020\u00062\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00060\u001c2\b\b\u0002\u0010\u001f\u001a\u00020 H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010!J#\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0006H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010$J+\u0010%\u001a\u0004\u0018\u00010#2\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u0006H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\'J\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00190\u001cJ\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00190\u001cJ\u0010\u0010*\u001a\u00020\u00062\u0006\u0010+\u001a\u00020\u0006H\u0002J\u001c\u0010,\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\f0\u00152\u0006\u0010-\u001a\u00020\u0006H\u0002J\"\u0010.\u001a\u0004\u0018\u00010#2\u0006\u0010-\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u0006H\u0002J*\u0010/\u001a\u0004\u0018\u00010#2\u0006\u0010-\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u0006H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00060"}, d2 = {"Lcom/example/budgetapp/analytics/investments/multicurrency/CurrencyService;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "exchangeRateApiKey", "", "exchangeRatesBaseUrl", "openExchangeRatesUrl", "convertCurrency", "Lcom/example/budgetapp/analytics/investments/multicurrency/CurrencyConversion;", "amount", "", "fromCurrency", "toCurrency", "(DLjava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "formatCurrency", "currencyCode", "locale", "Ljava/util/Locale;", "getAllExchangeRates", "", "baseCurrency", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCurrencyByCode", "Lcom/example/budgetapp/analytics/investments/multicurrency/SupportedCurrency;", "code", "getCurrencyTrends", "", "Lcom/example/budgetapp/analytics/investments/multicurrency/CurrencyTrend;", "targetCurrencies", "days", "", "(Ljava/lang/String;Ljava/util/List;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getExchangeRate", "Lcom/example/budgetapp/analytics/investments/multicurrency/ExchangeRate;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getHistoricalExchangeRate", "date", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPopularCurrencies", "getSupportedCurrencies", "makeApiCall", "urlString", "parseAllExchangeRates", "response", "parseExchangeRate", "parseHistoricalExchangeRate", "CashInCashOut_debug"})
public final class CurrencyService {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String exchangeRateApiKey = "demo";
    @org.jetbrains.annotations.NotNull
    private final java.lang.String exchangeRatesBaseUrl = "https://v6.exchangerate-api.com/v6";
    @org.jetbrains.annotations.NotNull
    private final java.lang.String openExchangeRatesUrl = "https://openexchangerates.org/api";
    
    public CurrencyService(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getExchangeRate(@org.jetbrains.annotations.NotNull
    java.lang.String fromCurrency, @org.jetbrains.annotations.NotNull
    java.lang.String toCurrency, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.analytics.investments.multicurrency.ExchangeRate> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getAllExchangeRates(@org.jetbrains.annotations.NotNull
    java.lang.String baseCurrency, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.Map<java.lang.String, java.lang.Double>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object convertCurrency(double amount, @org.jetbrains.annotations.NotNull
    java.lang.String fromCurrency, @org.jetbrains.annotations.NotNull
    java.lang.String toCurrency, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.analytics.investments.multicurrency.CurrencyConversion> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getHistoricalExchangeRate(@org.jetbrains.annotations.NotNull
    java.lang.String fromCurrency, @org.jetbrains.annotations.NotNull
    java.lang.String toCurrency, @org.jetbrains.annotations.NotNull
    java.lang.String date, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.analytics.investments.multicurrency.ExchangeRate> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getCurrencyTrends(@org.jetbrains.annotations.NotNull
    java.lang.String baseCurrency, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> targetCurrencies, int days, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.analytics.investments.multicurrency.CurrencyTrend>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String formatCurrency(double amount, @org.jetbrains.annotations.NotNull
    java.lang.String currencyCode, @org.jetbrains.annotations.NotNull
    java.util.Locale locale) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.budgetapp.analytics.investments.multicurrency.SupportedCurrency> getSupportedCurrencies() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.example.budgetapp.analytics.investments.multicurrency.SupportedCurrency getCurrencyByCode(@org.jetbrains.annotations.NotNull
    java.lang.String code) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.budgetapp.analytics.investments.multicurrency.SupportedCurrency> getPopularCurrencies() {
        return null;
    }
    
    private final java.lang.String makeApiCall(java.lang.String urlString) {
        return null;
    }
    
    private final com.example.budgetapp.analytics.investments.multicurrency.ExchangeRate parseExchangeRate(java.lang.String response, java.lang.String fromCurrency, java.lang.String toCurrency) {
        return null;
    }
    
    private final java.util.Map<java.lang.String, java.lang.Double> parseAllExchangeRates(java.lang.String response) {
        return null;
    }
    
    private final com.example.budgetapp.analytics.investments.multicurrency.ExchangeRate parseHistoricalExchangeRate(java.lang.String response, java.lang.String fromCurrency, java.lang.String toCurrency, java.lang.String date) {
        return null;
    }
}