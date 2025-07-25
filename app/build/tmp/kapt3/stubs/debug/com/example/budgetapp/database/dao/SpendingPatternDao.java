package com.example.budgetapp.database.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\bg\u0018\u00002\u00020\u0001J\u0011\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\nH\'J!\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J\u001c\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n2\u0006\u0010\u0012\u001a\u00020\u0013H\'J\u001c\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\n2\u0006\u0010\u0015\u001a\u00020\u0016H\'J\u001f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\u0018\u001a\u00020\u0019H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001aJ\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001eJ\u001f\u0010\u001f\u001a\u00020\u00032\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010!\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\""}, d2 = {"Lcom/example/budgetapp/database/dao/SpendingPatternDao;", "", "deleteAllPatterns", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteOldPatterns", "cutoffTime", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllPatterns", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/budgetapp/database/entities/SpendingPattern;", "getAnomalousPatterns", "threshold", "", "(FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPatternsByCategory", "category", "", "getPatternsByTimeframe", "timeframe", "Lcom/example/budgetapp/database/entities/PatternTimeframe;", "getPatternsByTrend", "direction", "Lcom/example/budgetapp/database/entities/TrendDirection;", "(Lcom/example/budgetapp/database/entities/TrendDirection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getSeasonalPatterns", "insertPattern", "pattern", "(Lcom/example/budgetapp/database/entities/SpendingPattern;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertPatterns", "patterns", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "CashInCashOut_debug"})
@androidx.room.Dao
public abstract interface SpendingPatternDao {
    
    @androidx.room.Query(value = "SELECT * FROM spending_patterns ORDER BY analysisDate DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.SpendingPattern>> getAllPatterns();
    
    @androidx.room.Query(value = "SELECT * FROM spending_patterns WHERE category = :category ORDER BY analysisDate DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.SpendingPattern>> getPatternsByCategory(@org.jetbrains.annotations.NotNull
    java.lang.String category);
    
    @androidx.room.Query(value = "SELECT * FROM spending_patterns WHERE timeframe = :timeframe ORDER BY analysisDate DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.SpendingPattern>> getPatternsByTimeframe(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.PatternTimeframe timeframe);
    
    @androidx.room.Query(value = "SELECT * FROM spending_patterns WHERE anomalyScore >= :threshold ORDER BY anomalyScore DESC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getAnomalousPatterns(float threshold, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.database.entities.SpendingPattern>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM spending_patterns WHERE trendDirection = :direction ORDER BY analysisDate DESC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getPatternsByTrend(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.TrendDirection direction, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.database.entities.SpendingPattern>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM spending_patterns WHERE seasonality != \'NONE\' ORDER BY analysisDate DESC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getSeasonalPatterns(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.database.entities.SpendingPattern>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertPattern(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.SpendingPattern pattern, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertPatterns(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.database.entities.SpendingPattern> patterns, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM spending_patterns WHERE analysisDate < :cutoffTime")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteOldPatterns(long cutoffTime, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM spending_patterns")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteAllPatterns(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}