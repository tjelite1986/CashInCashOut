package com.example.budgetapp.analytics.investments.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0006\n\u0002\b\u001d\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0011\u0010\u0007\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u0011\u0010\t\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u0019\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u0019\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u0019\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0014H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J\u0019\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u001d0\u001cH\'J\u0014\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u001d0\u001cH\'J\u0014\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u001d0\u001cH\'J\u0014\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u001d0\u001cH\'J$\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u001d0\u001c2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#H\'J\u001c\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u001d0\u001c2\u0006\u0010\"\u001a\u00020#H\'J\u001c\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u001d0\u001c2\u0006\u0010$\u001a\u00020#H\'J#\u0010\'\u001a\u0004\u0018\u00010\u00102\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010(J\u001c\u0010)\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u001d0\u001c2\u0006\u0010*\u001a\u00020\u0005H\'J+\u0010+\u001a\u0004\u0018\u00010,2\u0006\u0010-\u001a\u00020#2\u0006\u0010.\u001a\u00020\u00052\u0006\u0010/\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00100J+\u00101\u001a\u0004\u0018\u00010,2\u0006\u00102\u001a\u00020#2\u0006\u0010.\u001a\u00020\u00052\u0006\u0010/\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00100J\u001c\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u001d0\u001c2\u0006\u0010-\u001a\u00020#H\'J\u001c\u00104\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u001d0\u001c2\u0006\u00105\u001a\u00020#H\'J\u001c\u00106\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u001d0\u001c2\u0006\u00102\u001a\u00020#H\'J$\u00107\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u001d0\u001c2\u0006\u0010.\u001a\u00020\u00052\u0006\u0010/\u001a\u00020\u0005H\'J\u0017\u00108\u001a\b\u0012\u0004\u0012\u00020#0\u001dH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u0017\u00109\u001a\b\u0012\u0004\u0012\u00020#0\u001dH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u0017\u0010:\u001a\b\u0012\u0004\u0012\u00020#0\u001dH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u0019\u0010;\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u0019\u0010<\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J%\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00050\u001d2\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00100\u001dH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010?J\u0019\u0010@\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0014H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J%\u0010A\u001a\b\u0012\u0004\u0012\u00020\u00050\u001d2\f\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00140\u001dH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010?J!\u0010C\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010D\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010EJ\u0019\u0010F\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u0019\u0010G\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u0019\u0010H\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0014H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006I"}, d2 = {"Lcom/example/budgetapp/analytics/investments/dao/ExchangeRateDao;", "", "deactivateCurrencyAlert", "", "alertId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllExchangeRates", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllMultiCurrencyTransactions", "deleteCurrencyAlert", "alert", "Lcom/example/budgetapp/analytics/investments/multicurrency/CurrencyAlert;", "(Lcom/example/budgetapp/analytics/investments/multicurrency/CurrencyAlert;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteExchangeRate", "exchangeRate", "Lcom/example/budgetapp/analytics/investments/multicurrency/ExchangeRate;", "(Lcom/example/budgetapp/analytics/investments/multicurrency/ExchangeRate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteMultiCurrencyTransaction", "transaction", "Lcom/example/budgetapp/analytics/investments/multicurrency/MultiCurrencyTransaction;", "(Lcom/example/budgetapp/analytics/investments/multicurrency/MultiCurrencyTransaction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteMultiCurrencyTransactionById", "transactionId", "deleteOldExchangeRates", "", "cutoffTime", "getActiveCurrencyAlerts", "Lkotlinx/coroutines/flow/Flow;", "", "getAllCurrencyAlerts", "getAllExchangeRates", "getAllMultiCurrencyTransactions", "getCurrencyAlertsForPair", "fromCurrency", "", "toCurrency", "getExchangeRatesFromCurrency", "getExchangeRatesToCurrency", "getLatestExchangeRate", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRecentExchangeRates", "since", "getTotalAmountInBaseCurrency", "", "baseCurrency", "startDate", "endDate", "(Ljava/lang/String;JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTotalAmountInCurrency", "currency", "getTransactionsByBaseCurrency", "getTransactionsByCategory", "category", "getTransactionsByCurrency", "getTransactionsByDateRange", "getUsedBaseCurrencies", "getUsedCategories", "getUsedCurrencies", "insertCurrencyAlert", "insertExchangeRate", "insertExchangeRates", "exchangeRates", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertMultiCurrencyTransaction", "insertMultiCurrencyTransactions", "transactions", "triggerCurrencyAlert", "triggeredAt", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateCurrencyAlert", "updateExchangeRate", "updateMultiCurrencyTransaction", "CashInCashOut_debug"})
@androidx.room.Dao
public abstract interface ExchangeRateDao {
    
    @androidx.room.Query(value = "SELECT * FROM exchange_rates ORDER BY lastUpdated DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.analytics.investments.multicurrency.ExchangeRate>> getAllExchangeRates();
    
    @androidx.room.Query(value = "SELECT * FROM exchange_rates WHERE fromCurrency = :fromCurrency AND toCurrency = :toCurrency ORDER BY lastUpdated DESC LIMIT 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getLatestExchangeRate(@org.jetbrains.annotations.NotNull
    java.lang.String fromCurrency, @org.jetbrains.annotations.NotNull
    java.lang.String toCurrency, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.analytics.investments.multicurrency.ExchangeRate> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM exchange_rates WHERE fromCurrency = :fromCurrency ORDER BY lastUpdated DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.analytics.investments.multicurrency.ExchangeRate>> getExchangeRatesFromCurrency(@org.jetbrains.annotations.NotNull
    java.lang.String fromCurrency);
    
    @androidx.room.Query(value = "SELECT * FROM exchange_rates WHERE toCurrency = :toCurrency ORDER BY lastUpdated DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.analytics.investments.multicurrency.ExchangeRate>> getExchangeRatesToCurrency(@org.jetbrains.annotations.NotNull
    java.lang.String toCurrency);
    
    @androidx.room.Query(value = "SELECT * FROM exchange_rates WHERE lastUpdated >= :since ORDER BY lastUpdated DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.analytics.investments.multicurrency.ExchangeRate>> getRecentExchangeRates(long since);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertExchangeRate(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.multicurrency.ExchangeRate exchangeRate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertExchangeRates(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.analytics.investments.multicurrency.ExchangeRate> exchangeRates, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.Long>> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateExchangeRate(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.multicurrency.ExchangeRate exchangeRate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteExchangeRate(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.multicurrency.ExchangeRate exchangeRate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM exchange_rates WHERE lastUpdated < :cutoffTime")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteOldExchangeRates(long cutoffTime, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "DELETE FROM exchange_rates")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteAllExchangeRates(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM currency_alerts WHERE isActive = 1 ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.analytics.investments.multicurrency.CurrencyAlert>> getActiveCurrencyAlerts();
    
    @androidx.room.Query(value = "SELECT * FROM currency_alerts ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.analytics.investments.multicurrency.CurrencyAlert>> getAllCurrencyAlerts();
    
    @androidx.room.Query(value = "SELECT * FROM currency_alerts WHERE fromCurrency = :fromCurrency AND toCurrency = :toCurrency AND isActive = 1")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.analytics.investments.multicurrency.CurrencyAlert>> getCurrencyAlertsForPair(@org.jetbrains.annotations.NotNull
    java.lang.String fromCurrency, @org.jetbrains.annotations.NotNull
    java.lang.String toCurrency);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertCurrencyAlert(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.multicurrency.CurrencyAlert alert, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateCurrencyAlert(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.multicurrency.CurrencyAlert alert, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE currency_alerts SET isActive = 0 WHERE id = :alertId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deactivateCurrencyAlert(long alertId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE currency_alerts SET isActive = 0, triggeredAt = :triggeredAt WHERE id = :alertId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object triggerCurrencyAlert(long alertId, long triggeredAt, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteCurrencyAlert(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.multicurrency.CurrencyAlert alert, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM multi_currency_transactions ORDER BY transactionDate DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.analytics.investments.multicurrency.MultiCurrencyTransaction>> getAllMultiCurrencyTransactions();
    
    @androidx.room.Query(value = "SELECT * FROM multi_currency_transactions WHERE currency = :currency ORDER BY transactionDate DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.analytics.investments.multicurrency.MultiCurrencyTransaction>> getTransactionsByCurrency(@org.jetbrains.annotations.NotNull
    java.lang.String currency);
    
    @androidx.room.Query(value = "SELECT * FROM multi_currency_transactions WHERE baseCurrency = :baseCurrency ORDER BY transactionDate DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.analytics.investments.multicurrency.MultiCurrencyTransaction>> getTransactionsByBaseCurrency(@org.jetbrains.annotations.NotNull
    java.lang.String baseCurrency);
    
    @androidx.room.Query(value = "SELECT * FROM multi_currency_transactions WHERE category = :category ORDER BY transactionDate DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.analytics.investments.multicurrency.MultiCurrencyTransaction>> getTransactionsByCategory(@org.jetbrains.annotations.NotNull
    java.lang.String category);
    
    @androidx.room.Query(value = "SELECT * FROM multi_currency_transactions WHERE transactionDate BETWEEN :startDate AND :endDate ORDER BY transactionDate DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.analytics.investments.multicurrency.MultiCurrencyTransaction>> getTransactionsByDateRange(long startDate, long endDate);
    
    @androidx.room.Query(value = "SELECT DISTINCT currency FROM multi_currency_transactions ORDER BY currency")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getUsedCurrencies(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.String>> $completion);
    
    @androidx.room.Query(value = "SELECT DISTINCT baseCurrency FROM multi_currency_transactions ORDER BY baseCurrency")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getUsedBaseCurrencies(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.String>> $completion);
    
    @androidx.room.Query(value = "SELECT DISTINCT category FROM multi_currency_transactions ORDER BY category")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getUsedCategories(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.String>> $completion);
    
    @androidx.room.Query(value = "SELECT SUM(convertedAmount) FROM multi_currency_transactions WHERE baseCurrency = :baseCurrency AND transactionDate BETWEEN :startDate AND :endDate")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getTotalAmountInBaseCurrency(@org.jetbrains.annotations.NotNull
    java.lang.String baseCurrency, long startDate, long endDate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    @androidx.room.Query(value = "SELECT SUM(amount) FROM multi_currency_transactions WHERE currency = :currency AND transactionDate BETWEEN :startDate AND :endDate")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getTotalAmountInCurrency(@org.jetbrains.annotations.NotNull
    java.lang.String currency, long startDate, long endDate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertMultiCurrencyTransaction(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.multicurrency.MultiCurrencyTransaction transaction, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertMultiCurrencyTransactions(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.analytics.investments.multicurrency.MultiCurrencyTransaction> transactions, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.Long>> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateMultiCurrencyTransaction(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.multicurrency.MultiCurrencyTransaction transaction, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteMultiCurrencyTransaction(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.multicurrency.MultiCurrencyTransaction transaction, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM multi_currency_transactions WHERE id = :transactionId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteMultiCurrencyTransactionById(long transactionId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM multi_currency_transactions")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteAllMultiCurrencyTransactions(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}