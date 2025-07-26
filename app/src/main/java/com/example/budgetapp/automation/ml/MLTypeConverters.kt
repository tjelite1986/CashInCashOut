package com.example.budgetapp.automation.ml

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MLTypeConverters {

    @TypeConverter
    fun fromInsightType(value: InsightType): String {
        return value.name
    }

    @TypeConverter
    fun toInsightType(value: String): InsightType {
        return InsightType.valueOf(value)
    }

    @TypeConverter
    fun fromInsightPriority(value: InsightPriority): String {
        return value.name
    }

    @TypeConverter
    fun toInsightPriority(value: String): InsightPriority {
        return InsightPriority.valueOf(value)
    }

    @TypeConverter
    fun fromImpactLevel(value: ImpactLevel): String {
        return value.name
    }

    @TypeConverter
    fun toImpactLevel(value: String): ImpactLevel {
        return ImpactLevel.valueOf(value)
    }

}