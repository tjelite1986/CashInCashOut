package com.example.budgetapp.database.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0011\bg\u0018\u00002\u00020\u0001J\u0011\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u001b\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nH\'J\u001b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n2\u0006\u0010\u0014\u001a\u00020\u0015H\'J\'\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019J!\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u0019\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001dJ\u001f\u0010\u001e\u001a\u00020\u00032\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 J)\u0010!\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020\u000eH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010%\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006&"}, d2 = {"Lcom/example/budgetapp/database/dao/SpendingForecastDao;", "", "deleteAllForecasts", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteExpiredForecasts", "currentTime", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllForecasts", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/budgetapp/database/entities/SpendingForecast;", "getAverageAccuracyByMethod", "", "method", "Lcom/example/budgetapp/database/entities/ForecastMethod;", "(Lcom/example/budgetapp/database/entities/ForecastMethod;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCompletedForecasts", "getForecastsByCategory", "category", "", "getForecastsInRange", "startDate", "endDate", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getForecastsNeedingActuals", "insertForecast", "forecast", "(Lcom/example/budgetapp/database/entities/SpendingForecast;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertForecasts", "forecasts", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateForecastActual", "id", "actual", "accuracy", "(JDDLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "CashInCashOut_release"})
@androidx.room.Dao
public abstract interface SpendingForecastDao {
    
    @androidx.room.Query(value = "SELECT * FROM spending_forecasts ORDER BY forecastDate DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.SpendingForecast>> getAllForecasts();
    
    @androidx.room.Query(value = "SELECT * FROM spending_forecasts WHERE category = :category ORDER BY forecastDate DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.SpendingForecast>> getForecastsByCategory(@org.jetbrains.annotations.NotNull
    java.lang.String category);
    
    @androidx.room.Query(value = "SELECT * FROM spending_forecasts WHERE forecastDate BETWEEN :startDate AND :endDate ORDER BY forecastDate ASC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getForecastsInRange(long startDate, long endDate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.database.entities.SpendingForecast>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM spending_forecasts WHERE actualAmount IS NULL AND forecastDate < :currentTime")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getForecastsNeedingActuals(long currentTime, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.database.entities.SpendingForecast>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM spending_forecasts WHERE actualAmount IS NOT NULL")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getCompletedForecasts(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.database.entities.SpendingForecast>> $completion);
    
    @androidx.room.Query(value = "SELECT AVG(accuracy) FROM spending_forecasts WHERE accuracy IS NOT NULL AND forecastMethod = :method")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getAverageAccuracyByMethod(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.ForecastMethod method, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertForecast(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.SpendingForecast forecast, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertForecasts(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.database.entities.SpendingForecast> forecasts, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE spending_forecasts SET actualAmount = :actual, accuracy = :accuracy WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateForecastActual(long id, double actual, double accuracy, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM spending_forecasts WHERE validUntil < :currentTime")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteExpiredForecasts(long currentTime, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM spending_forecasts")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteAllForecasts(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}