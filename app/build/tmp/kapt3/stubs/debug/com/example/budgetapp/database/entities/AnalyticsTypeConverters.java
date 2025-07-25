package com.example.budgetapp.database.entities;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\bH\u0007J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\nH\u0007J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\fH\u0007J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u000eH\u0007J\u0010\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0010H\u0007J\u0010\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0012H\u0007J\u0010\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0014H\u0007J\u0010\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0016H\u0007J\u0010\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0018H\u0007J\u0010\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J\u0010\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J\u0010\u0010\u001b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J\u0010\u0010\u001c\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J\u0010\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J\u0010\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J\u0010\u0010\u001f\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J\u0010\u0010 \u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J\u0010\u0010!\u001a\u00020\u00162\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J\u0010\u0010\"\u001a\u00020\u00182\u0006\u0010\u0005\u001a\u00020\u0004H\u0007\u00a8\u0006#"}, d2 = {"Lcom/example/budgetapp/database/entities/AnalyticsTypeConverters;", "", "()V", "fromForecastMethod", "", "value", "Lcom/example/budgetapp/database/entities/ForecastMethod;", "fromGoalPriority", "Lcom/example/budgetapp/database/entities/GoalPriority;", "fromGoalType", "Lcom/example/budgetapp/database/entities/GoalType;", "fromInsightSeverity", "Lcom/example/budgetapp/database/entities/InsightSeverity;", "fromInsightType", "Lcom/example/budgetapp/database/entities/InsightType;", "fromPatternTimeframe", "Lcom/example/budgetapp/database/entities/PatternTimeframe;", "fromReminderFrequency", "Lcom/example/budgetapp/database/entities/ReminderFrequency;", "fromSeasonalityType", "Lcom/example/budgetapp/database/entities/SeasonalityType;", "fromSpendingFrequency", "Lcom/example/budgetapp/database/entities/SpendingFrequency;", "fromTrendDirection", "Lcom/example/budgetapp/database/entities/TrendDirection;", "toForecastMethod", "toGoalPriority", "toGoalType", "toInsightSeverity", "toInsightType", "toPatternTimeframe", "toReminderFrequency", "toSeasonalityType", "toSpendingFrequency", "toTrendDirection", "CashInCashOut_debug"})
public final class AnalyticsTypeConverters {
    
    public AnalyticsTypeConverters() {
        super();
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final java.lang.String fromInsightType(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.InsightType value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.InsightType toInsightType(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final java.lang.String fromInsightSeverity(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.InsightSeverity value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.InsightSeverity toInsightSeverity(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final java.lang.String fromGoalType(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.GoalType value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.GoalType toGoalType(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final java.lang.String fromGoalPriority(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.GoalPriority value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.GoalPriority toGoalPriority(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final java.lang.String fromReminderFrequency(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.ReminderFrequency value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.ReminderFrequency toReminderFrequency(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final java.lang.String fromPatternTimeframe(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.PatternTimeframe value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.PatternTimeframe toPatternTimeframe(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final java.lang.String fromTrendDirection(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.TrendDirection value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.TrendDirection toTrendDirection(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final java.lang.String fromSeasonalityType(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.SeasonalityType value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.SeasonalityType toSeasonalityType(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final java.lang.String fromSpendingFrequency(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.SpendingFrequency value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.SpendingFrequency toSpendingFrequency(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final java.lang.String fromForecastMethod(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.ForecastMethod value) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.ForecastMethod toForecastMethod(@org.jetbrains.annotations.NotNull
    java.lang.String value) {
        return null;
    }
}