package com.example.budgetapp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Index

@Entity(
    tableName = "spending_patterns",
    indices = [
        Index(value = ["category", "timeframe"]),
        Index(value = ["analysisDate"])
    ]
)
data class SpendingPattern(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    val category: String,
    val timeframe: PatternTimeframe,
    val averageAmount: Double,
    val totalAmount: Double,
    val transactionCount: Int,
    val trendDirection: TrendDirection,
    val trendPercentage: Double, // Percentage change from previous period
    val confidenceLevel: Float, // 0.0 to 1.0
    val seasonality: SeasonalityType,
    val anomalyScore: Float, // 0.0 to 1.0 (higher = more anomalous)
    val predictedNextAmount: Double? = null,
    val volatility: Double, // Standard deviation of amounts
    val frequency: SpendingFrequency,
    val analysisDate: Long = System.currentTimeMillis(),
    val dataPoints: Int, // Number of transactions used for analysis
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(
    tableName = "spending_forecasts",
    indices = [
        Index(value = ["category", "forecastDate"]),
        Index(value = ["forecastDate"])
    ]
)
data class SpendingForecast(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    
    val category: String? = null, // null for total spending forecast
    val forecastDate: Long, // Date this forecast is for
    val predictedAmount: Double,
    val confidenceInterval: Double, // +/- range
    val confidenceLevel: Float, // 0.0 to 1.0
    val forecastMethod: ForecastMethod,
    val basedOnDays: Int, // Number of historical days used
    val seasonalityFactor: Double = 1.0,
    val trendFactor: Double = 1.0,
    val actualAmount: Double? = null, // Filled in after the date passes
    val accuracy: Double? = null, // How accurate the prediction was
    val createdAt: Long = System.currentTimeMillis(),
    val validUntil: Long = System.currentTimeMillis() + (30 * 24 * 60 * 60 * 1000) // 30 days
)

enum class PatternTimeframe {
    DAILY,
    WEEKLY,
    MONTHLY,
    QUARTERLY,
    YEARLY
}

enum class TrendDirection {
    INCREASING,
    DECREASING,
    STABLE,
    VOLATILE,
    UNKNOWN
}

enum class SeasonalityType {
    NONE,
    WEEKLY,   // Weekend vs weekday patterns
    MONTHLY,  // End of month vs beginning
    QUARTERLY, // Seasonal patterns
    YEARLY,   // Holiday/annual patterns
    CUSTOM    // User-specific patterns
}

enum class SpendingFrequency {
    DAILY,
    WEEKLY,
    BIWEEKLY,
    MONTHLY,
    IRREGULAR,
    RARE
}

enum class ForecastMethod {
    LINEAR_REGRESSION,
    MOVING_AVERAGE,
    EXPONENTIAL_SMOOTHING,
    SEASONAL_DECOMPOSITION,
    MACHINE_LEARNING,
    HYBRID
}