package com.example.budgetapp.database.entities;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b.\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B\u008b\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\b\u0012\b\b\u0002\u0010\u0011\u001a\u00020\b\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0016J\t\u0010-\u001a\u00020\u0003H\u00c6\u0003J\t\u0010.\u001a\u00020\bH\u00c6\u0003J\u0010\u0010/\u001a\u0004\u0018\u00010\bH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0018J\u0010\u00100\u001a\u0004\u0018\u00010\bH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0018J\t\u00101\u001a\u00020\u0003H\u00c6\u0003J\t\u00102\u001a\u00020\u0003H\u00c6\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u00104\u001a\u00020\u0003H\u00c6\u0003J\t\u00105\u001a\u00020\bH\u00c6\u0003J\t\u00106\u001a\u00020\bH\u00c6\u0003J\t\u00107\u001a\u00020\u000bH\u00c6\u0003J\t\u00108\u001a\u00020\rH\u00c6\u0003J\t\u00109\u001a\u00020\u000fH\u00c6\u0003J\t\u0010:\u001a\u00020\bH\u00c6\u0003J\u00a0\u0001\u0010;\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\b2\b\b\u0002\u0010\u0011\u001a\u00020\b2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u0014\u001a\u00020\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010<J\u0013\u0010=\u001a\u00020>2\b\u0010?\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010@\u001a\u00020\u000fH\u00d6\u0001J\t\u0010A\u001a\u00020\u0005H\u00d6\u0001R\u0015\u0010\u0013\u001a\u0004\u0018\u00010\b\u00a2\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u0015\u0010\u0012\u001a\u0004\u0018\u00010\b\u00a2\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u001a\u0010\u0018R\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\t\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0014\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010$R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\'R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010$R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010 R\u0011\u0010\u0010\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010 R\u0011\u0010\u0011\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010 R\u0011\u0010\u0015\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010$\u00a8\u0006B"}, d2 = {"Lcom/example/budgetapp/database/entities/SpendingForecast;", "", "id", "", "category", "", "forecastDate", "predictedAmount", "", "confidenceInterval", "confidenceLevel", "", "forecastMethod", "Lcom/example/budgetapp/database/entities/ForecastMethod;", "basedOnDays", "", "seasonalityFactor", "trendFactor", "actualAmount", "accuracy", "createdAt", "validUntil", "(JLjava/lang/String;JDDFLcom/example/budgetapp/database/entities/ForecastMethod;IDDLjava/lang/Double;Ljava/lang/Double;JJ)V", "getAccuracy", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getActualAmount", "getBasedOnDays", "()I", "getCategory", "()Ljava/lang/String;", "getConfidenceInterval", "()D", "getConfidenceLevel", "()F", "getCreatedAt", "()J", "getForecastDate", "getForecastMethod", "()Lcom/example/budgetapp/database/entities/ForecastMethod;", "getId", "getPredictedAmount", "getSeasonalityFactor", "getTrendFactor", "getValidUntil", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JLjava/lang/String;JDDFLcom/example/budgetapp/database/entities/ForecastMethod;IDDLjava/lang/Double;Ljava/lang/Double;JJ)Lcom/example/budgetapp/database/entities/SpendingForecast;", "equals", "", "other", "hashCode", "toString", "CashInCashOut_release"})
@androidx.room.Entity(tableName = "spending_forecasts", indices = {@androidx.room.Index(value = {"category", "forecastDate"}), @androidx.room.Index(value = {"forecastDate"})})
public final class SpendingForecast {
    @androidx.room.PrimaryKey(autoGenerate = true)
    private final long id = 0L;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String category = null;
    private final long forecastDate = 0L;
    private final double predictedAmount = 0.0;
    private final double confidenceInterval = 0.0;
    private final float confidenceLevel = 0.0F;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.entities.ForecastMethod forecastMethod = null;
    private final int basedOnDays = 0;
    private final double seasonalityFactor = 0.0;
    private final double trendFactor = 0.0;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Double actualAmount = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Double accuracy = null;
    private final long createdAt = 0L;
    private final long validUntil = 0L;
    
    public SpendingForecast(long id, @org.jetbrains.annotations.Nullable
    java.lang.String category, long forecastDate, double predictedAmount, double confidenceInterval, float confidenceLevel, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.ForecastMethod forecastMethod, int basedOnDays, double seasonalityFactor, double trendFactor, @org.jetbrains.annotations.Nullable
    java.lang.Double actualAmount, @org.jetbrains.annotations.Nullable
    java.lang.Double accuracy, long createdAt, long validUntil) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getCategory() {
        return null;
    }
    
    public final long getForecastDate() {
        return 0L;
    }
    
    public final double getPredictedAmount() {
        return 0.0;
    }
    
    public final double getConfidenceInterval() {
        return 0.0;
    }
    
    public final float getConfidenceLevel() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.ForecastMethod getForecastMethod() {
        return null;
    }
    
    public final int getBasedOnDays() {
        return 0;
    }
    
    public final double getSeasonalityFactor() {
        return 0.0;
    }
    
    public final double getTrendFactor() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double getActualAmount() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double getAccuracy() {
        return null;
    }
    
    public final long getCreatedAt() {
        return 0L;
    }
    
    public final long getValidUntil() {
        return 0L;
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final double component10() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double component11() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double component12() {
        return null;
    }
    
    public final long component13() {
        return 0L;
    }
    
    public final long component14() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component2() {
        return null;
    }
    
    public final long component3() {
        return 0L;
    }
    
    public final double component4() {
        return 0.0;
    }
    
    public final double component5() {
        return 0.0;
    }
    
    public final float component6() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.ForecastMethod component7() {
        return null;
    }
    
    public final int component8() {
        return 0;
    }
    
    public final double component9() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.SpendingForecast copy(long id, @org.jetbrains.annotations.Nullable
    java.lang.String category, long forecastDate, double predictedAmount, double confidenceInterval, float confidenceLevel, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.ForecastMethod forecastMethod, int basedOnDays, double seasonalityFactor, double trendFactor, @org.jetbrains.annotations.Nullable
    java.lang.Double actualAmount, @org.jetbrains.annotations.Nullable
    java.lang.Double accuracy, long createdAt, long validUntil) {
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