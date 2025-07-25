package com.example.budgetapp.database.entities

import androidx.room.TypeConverter

class AnalyticsTypeConverters {
    
    // Financial Insight Enums
    @TypeConverter
    fun fromInsightType(value: InsightType): String = value.name
    
    @TypeConverter
    fun toInsightType(value: String): InsightType = InsightType.valueOf(value)
    
    @TypeConverter
    fun fromInsightSeverity(value: InsightSeverity): String = value.name
    
    @TypeConverter
    fun toInsightSeverity(value: String): InsightSeverity = InsightSeverity.valueOf(value)
    
    // Financial Goal Enums
    @TypeConverter
    fun fromGoalType(value: GoalType): String = value.name
    
    @TypeConverter
    fun toGoalType(value: String): GoalType = GoalType.valueOf(value)
    
    @TypeConverter
    fun fromGoalPriority(value: GoalPriority): String = value.name
    
    @TypeConverter
    fun toGoalPriority(value: String): GoalPriority = GoalPriority.valueOf(value)
    
    @TypeConverter
    fun fromReminderFrequency(value: ReminderFrequency): String = value.name
    
    @TypeConverter
    fun toReminderFrequency(value: String): ReminderFrequency = ReminderFrequency.valueOf(value)
    
    // Spending Pattern Enums
    @TypeConverter
    fun fromPatternTimeframe(value: PatternTimeframe): String = value.name
    
    @TypeConverter
    fun toPatternTimeframe(value: String): PatternTimeframe = PatternTimeframe.valueOf(value)
    
    @TypeConverter
    fun fromTrendDirection(value: TrendDirection): String = value.name
    
    @TypeConverter
    fun toTrendDirection(value: String): TrendDirection = TrendDirection.valueOf(value)
    
    @TypeConverter
    fun fromSeasonalityType(value: SeasonalityType): String = value.name
    
    @TypeConverter
    fun toSeasonalityType(value: String): SeasonalityType = SeasonalityType.valueOf(value)
    
    @TypeConverter
    fun fromSpendingFrequency(value: SpendingFrequency): String = value.name
    
    @TypeConverter
    fun toSpendingFrequency(value: String): SpendingFrequency = SpendingFrequency.valueOf(value)
    
    @TypeConverter
    fun fromForecastMethod(value: ForecastMethod): String = value.name
    
    @TypeConverter
    fun toForecastMethod(value: String): ForecastMethod = ForecastMethod.valueOf(value)
}