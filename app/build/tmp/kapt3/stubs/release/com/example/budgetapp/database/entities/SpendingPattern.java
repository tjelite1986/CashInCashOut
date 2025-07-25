package com.example.budgetapp.database.entities;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b4\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u0097\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\t\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0011\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\u0016\u001a\u00020\t\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u0003\u0012\u0006\u0010\u001a\u001a\u00020\f\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u001cJ\t\u00109\u001a\u00020\u0003H\u00c6\u0003J\t\u0010:\u001a\u00020\u0013H\u00c6\u0003J\t\u0010;\u001a\u00020\u0011H\u00c6\u0003J\u0010\u0010<\u001a\u0004\u0018\u00010\tH\u00c6\u0003\u00a2\u0006\u0002\u0010-J\t\u0010=\u001a\u00020\tH\u00c6\u0003J\t\u0010>\u001a\u00020\u0018H\u00c6\u0003J\t\u0010?\u001a\u00020\u0003H\u00c6\u0003J\t\u0010@\u001a\u00020\fH\u00c6\u0003J\t\u0010A\u001a\u00020\u0003H\u00c6\u0003J\t\u0010B\u001a\u00020\u0005H\u00c6\u0003J\t\u0010C\u001a\u00020\u0007H\u00c6\u0003J\t\u0010D\u001a\u00020\tH\u00c6\u0003J\t\u0010E\u001a\u00020\tH\u00c6\u0003J\t\u0010F\u001a\u00020\fH\u00c6\u0003J\t\u0010G\u001a\u00020\u000eH\u00c6\u0003J\t\u0010H\u001a\u00020\tH\u00c6\u0003J\t\u0010I\u001a\u00020\u0011H\u00c6\u0003J\u00ba\u0001\u0010J\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00112\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u0016\u001a\u00020\t2\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u00032\b\b\u0002\u0010\u001a\u001a\u00020\f2\b\b\u0002\u0010\u001b\u001a\u00020\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010KJ\u0013\u0010L\u001a\u00020M2\b\u0010N\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010O\u001a\u00020\fH\u00d6\u0001J\t\u0010P\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0019\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0014\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010 R\u0011\u0010\u001b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001eR\u0011\u0010\u001a\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010(R\u0011\u0010\u0017\u001a\u00020\u0018\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001eR\u0015\u0010\u0015\u001a\u0004\u0018\u00010\t\u00a2\u0006\n\n\u0002\u0010.\u001a\u0004\b,\u0010-R\u0011\u0010\u0012\u001a\u00020\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0011\u0010\n\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010\"R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b4\u0010(R\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u00106R\u0011\u0010\u000f\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u0010\"R\u0011\u0010\u0016\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b8\u0010\"\u00a8\u0006Q"}, d2 = {"Lcom/example/budgetapp/database/entities/SpendingPattern;", "", "id", "", "category", "", "timeframe", "Lcom/example/budgetapp/database/entities/PatternTimeframe;", "averageAmount", "", "totalAmount", "transactionCount", "", "trendDirection", "Lcom/example/budgetapp/database/entities/TrendDirection;", "trendPercentage", "confidenceLevel", "", "seasonality", "Lcom/example/budgetapp/database/entities/SeasonalityType;", "anomalyScore", "predictedNextAmount", "volatility", "frequency", "Lcom/example/budgetapp/database/entities/SpendingFrequency;", "analysisDate", "dataPoints", "createdAt", "(JLjava/lang/String;Lcom/example/budgetapp/database/entities/PatternTimeframe;DDILcom/example/budgetapp/database/entities/TrendDirection;DFLcom/example/budgetapp/database/entities/SeasonalityType;FLjava/lang/Double;DLcom/example/budgetapp/database/entities/SpendingFrequency;JIJ)V", "getAnalysisDate", "()J", "getAnomalyScore", "()F", "getAverageAmount", "()D", "getCategory", "()Ljava/lang/String;", "getConfidenceLevel", "getCreatedAt", "getDataPoints", "()I", "getFrequency", "()Lcom/example/budgetapp/database/entities/SpendingFrequency;", "getId", "getPredictedNextAmount", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getSeasonality", "()Lcom/example/budgetapp/database/entities/SeasonalityType;", "getTimeframe", "()Lcom/example/budgetapp/database/entities/PatternTimeframe;", "getTotalAmount", "getTransactionCount", "getTrendDirection", "()Lcom/example/budgetapp/database/entities/TrendDirection;", "getTrendPercentage", "getVolatility", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JLjava/lang/String;Lcom/example/budgetapp/database/entities/PatternTimeframe;DDILcom/example/budgetapp/database/entities/TrendDirection;DFLcom/example/budgetapp/database/entities/SeasonalityType;FLjava/lang/Double;DLcom/example/budgetapp/database/entities/SpendingFrequency;JIJ)Lcom/example/budgetapp/database/entities/SpendingPattern;", "equals", "", "other", "hashCode", "toString", "CashInCashOut_release"})
@androidx.room.Entity(tableName = "spending_patterns", indices = {@androidx.room.Index(value = {"category", "timeframe"}), @androidx.room.Index(value = {"analysisDate"})})
public final class SpendingPattern {
    @androidx.room.PrimaryKey(autoGenerate = true)
    private final long id = 0L;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String category = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.entities.PatternTimeframe timeframe = null;
    private final double averageAmount = 0.0;
    private final double totalAmount = 0.0;
    private final int transactionCount = 0;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.entities.TrendDirection trendDirection = null;
    private final double trendPercentage = 0.0;
    private final float confidenceLevel = 0.0F;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.entities.SeasonalityType seasonality = null;
    private final float anomalyScore = 0.0F;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Double predictedNextAmount = null;
    private final double volatility = 0.0;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.entities.SpendingFrequency frequency = null;
    private final long analysisDate = 0L;
    private final int dataPoints = 0;
    private final long createdAt = 0L;
    
    public SpendingPattern(long id, @org.jetbrains.annotations.NotNull
    java.lang.String category, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.PatternTimeframe timeframe, double averageAmount, double totalAmount, int transactionCount, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.TrendDirection trendDirection, double trendPercentage, float confidenceLevel, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.SeasonalityType seasonality, float anomalyScore, @org.jetbrains.annotations.Nullable
    java.lang.Double predictedNextAmount, double volatility, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.SpendingFrequency frequency, long analysisDate, int dataPoints, long createdAt) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getCategory() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.PatternTimeframe getTimeframe() {
        return null;
    }
    
    public final double getAverageAmount() {
        return 0.0;
    }
    
    public final double getTotalAmount() {
        return 0.0;
    }
    
    public final int getTransactionCount() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.TrendDirection getTrendDirection() {
        return null;
    }
    
    public final double getTrendPercentage() {
        return 0.0;
    }
    
    public final float getConfidenceLevel() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.SeasonalityType getSeasonality() {
        return null;
    }
    
    public final float getAnomalyScore() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double getPredictedNextAmount() {
        return null;
    }
    
    public final double getVolatility() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.SpendingFrequency getFrequency() {
        return null;
    }
    
    public final long getAnalysisDate() {
        return 0L;
    }
    
    public final int getDataPoints() {
        return 0;
    }
    
    public final long getCreatedAt() {
        return 0L;
    }
    
    public final long component1() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.SeasonalityType component10() {
        return null;
    }
    
    public final float component11() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double component12() {
        return null;
    }
    
    public final double component13() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.SpendingFrequency component14() {
        return null;
    }
    
    public final long component15() {
        return 0L;
    }
    
    public final int component16() {
        return 0;
    }
    
    public final long component17() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.PatternTimeframe component3() {
        return null;
    }
    
    public final double component4() {
        return 0.0;
    }
    
    public final double component5() {
        return 0.0;
    }
    
    public final int component6() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.TrendDirection component7() {
        return null;
    }
    
    public final double component8() {
        return 0.0;
    }
    
    public final float component9() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.SpendingPattern copy(long id, @org.jetbrains.annotations.NotNull
    java.lang.String category, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.PatternTimeframe timeframe, double averageAmount, double totalAmount, int transactionCount, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.TrendDirection trendDirection, double trendPercentage, float confidenceLevel, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.SeasonalityType seasonality, float anomalyScore, @org.jetbrains.annotations.Nullable
    java.lang.Double predictedNextAmount, double volatility, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.SpendingFrequency frequency, long analysisDate, int dataPoints, long createdAt) {
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