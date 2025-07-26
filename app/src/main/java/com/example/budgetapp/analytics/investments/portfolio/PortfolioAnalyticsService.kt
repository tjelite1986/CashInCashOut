package com.example.budgetapp.analytics.investments.portfolio

import android.content.Context
import com.example.budgetapp.analytics.investments.entities.*
import com.example.budgetapp.analytics.investments.marketdata.MarketDataService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.*

class PortfolioAnalyticsService(
    private val context: Context,
    private val marketDataService: MarketDataService
) {
    
    suspend fun calculatePortfolioPerformance(
        investments: List<Investment>,
        transactions: List<InvestmentTransaction>,
        priceHistory: List<InvestmentPriceHistory>
    ): PortfolioPerformance = withContext(Dispatchers.IO) {
        
        val totalValue = calculateTotalValue(investments)
        val totalCost = calculateTotalCost(investments, transactions)
        val totalGainLoss = totalValue - totalCost
        val totalReturn = if (totalCost > 0) (totalGainLoss / totalCost) * 100 else 0.0
        
        val dayChange = calculateDayChange(investments, priceHistory)
        val allocation = calculateAssetAllocation(investments)
        val riskMetrics = calculateRiskMetrics(investments, priceHistory)
        val diversification = calculateDiversificationScore(investments)
        
        PortfolioPerformance(
            totalValue = totalValue,
            totalCost = totalCost,
            totalGainLoss = totalGainLoss,
            totalReturnPercent = totalReturn,
            dayChange = dayChange.first,
            dayChangePercent = dayChange.second,
            assetAllocation = allocation,
            riskScore = riskMetrics.riskScore,
            sharpeRatio = riskMetrics.sharpeRatio,
            volatility = riskMetrics.volatility,
            diversificationScore = diversification,
            lastUpdated = System.currentTimeMillis()
        )
    }
    
    suspend fun calculateInvestmentPerformance(
        investment: Investment,
        transactions: List<InvestmentTransaction>,
        priceHistory: List<InvestmentPriceHistory>
    ): InvestmentPerformance = withContext(Dispatchers.IO) {
        
        val currentValue = investment.currentValue * investment.quantity
        val totalCost = calculateInvestmentCost(investment, transactions)
        val gainLoss = currentValue - totalCost
        val returnPercent = if (totalCost > 0) (gainLoss / totalCost) * 100 else 0.0
        
        val annualizedReturn = calculateAnnualizedReturn(investment, transactions, priceHistory)
        val volatility = calculateVolatility(priceHistory)
        val maxDrawdown = calculateMaxDrawdown(priceHistory)
        val beta = calculateBeta(investment, priceHistory)
        
        InvestmentPerformance(
            investmentId = investment.id,
            currentValue = currentValue,
            totalCost = totalCost,
            gainLoss = gainLoss,
            returnPercent = returnPercent,
            annualizedReturn = annualizedReturn,
            volatility = volatility,
            maxDrawdown = maxDrawdown,
            beta = beta,
            lastUpdated = System.currentTimeMillis()
        )
    }
    
    suspend fun generateRebalancingRecommendations(
        portfolio: InvestmentPortfolio,
        investments: List<Investment>,
        targetAllocation: Map<InvestmentCategory, Double>
    ): List<RebalancingRecommendation> = withContext(Dispatchers.IO) {
        
        val currentAllocation = calculateAssetAllocation(investments)
        val totalValue = calculateTotalValue(investments)
        val recommendations = mutableListOf<RebalancingRecommendation>()
        
        targetAllocation.forEach { (category, targetPercent) ->
            val currentPercent = currentAllocation[category] ?: 0.0
            val difference = targetPercent - currentPercent
            
            if (abs(difference) > 5.0) { // Only recommend if difference > 5%
                val targetValue = totalValue * (targetPercent / 100)
                val currentValue = totalValue * (currentPercent / 100)
                val adjustmentAmount = targetValue - currentValue
                
                recommendations.add(
                    RebalancingRecommendation(
                        category = category,
                        currentPercent = currentPercent,
                        targetPercent = targetPercent,
                        difference = difference,
                        adjustmentAmount = adjustmentAmount,
                        action = if (difference > 0) RebalancingAction.BUY else RebalancingAction.SELL,
                        priority = when {
                            abs(difference) > 15 -> RebalancingPriority.HIGH
                            abs(difference) > 10 -> RebalancingPriority.MEDIUM
                            else -> RebalancingPriority.LOW
                        }
                    )
                )
            }
        }
        
        recommendations.sortedByDescending { abs(it.difference) }
    }
    
    suspend fun calculateRiskAssessment(
        investments: List<Investment>,
        transactions: List<InvestmentTransaction>,
        priceHistory: List<InvestmentPriceHistory>
    ): RiskAssessment = withContext(Dispatchers.IO) {
        
        val portfolioValue = calculateTotalValue(investments)
        val volatility = calculatePortfolioVolatility(investments, priceHistory)
        val concentrationRisk = calculateConcentrationRisk(investments)
        val currencyRisk = calculateCurrencyRisk(investments)
        val liquidityRisk = calculateLiquidityRisk(investments)
        
        val overallRiskScore = (volatility * 0.3 + concentrationRisk * 0.25 + 
                               currencyRisk * 0.25 + liquidityRisk * 0.2)
        
        val riskLevel = when {
            overallRiskScore < 20 -> RiskLevel.LOW
            overallRiskScore < 40 -> RiskLevel.MODERATE_LOW
            overallRiskScore < 60 -> RiskLevel.MODERATE
            overallRiskScore < 80 -> RiskLevel.MODERATE_HIGH
            else -> RiskLevel.HIGH
        }
        
        RiskAssessment(
            overallRiskScore = overallRiskScore,
            riskLevel = riskLevel,
            volatilityRisk = volatility,
            concentrationRisk = concentrationRisk,
            currencyRisk = currencyRisk,
            liquidityRisk = liquidityRisk,
            recommendations = generateRiskRecommendations(overallRiskScore, concentrationRisk, currencyRisk),
            lastUpdated = System.currentTimeMillis()
        )
    }
    
    private fun calculateTotalValue(investments: List<Investment>): Double {
        return investments.filter { it.isActive }.sumOf { it.currentValue * it.quantity }
    }
    
    private fun calculateTotalCost(
        investments: List<Investment>,
        transactions: List<InvestmentTransaction>
    ): Double {
        return investments.filter { it.isActive }.sumOf { investment ->
            calculateInvestmentCost(investment, transactions.filter { it.investmentId == investment.id })
        }
    }
    
    private fun calculateInvestmentCost(
        investment: Investment,
        transactions: List<InvestmentTransaction>
    ): Double {
        val buyTransactions = transactions.filter { 
            it.type == InvestmentTransactionType.BUY && it.investmentId == investment.id 
        }
        return buyTransactions.sumOf { it.totalAmount + it.fees }
    }
    
    private fun calculateDayChange(
        investments: List<Investment>,
        priceHistory: List<InvestmentPriceHistory>
    ): Pair<Double, Double> {
        val today = System.currentTimeMillis()
        val yesterday = today - 24 * 60 * 60 * 1000
        
        var totalChangeAmount = 0.0
        var totalCurrentValue = 0.0
        
        investments.filter { it.isActive }.forEach { investment ->
            val currentPrice = investment.currentValue
            val yesterdayPrice = priceHistory
                .filter { it.investmentId == investment.id && it.recordedAt >= yesterday }
                .minByOrNull { abs(it.recordedAt - yesterday) }?.price ?: currentPrice
            
            val changeAmount = (currentPrice - yesterdayPrice) * investment.quantity
            totalChangeAmount += changeAmount
            totalCurrentValue += currentPrice * investment.quantity
        }
        
        val changePercent = if (totalCurrentValue > 0) 
            (totalChangeAmount / (totalCurrentValue - totalChangeAmount)) * 100 else 0.0
        
        return Pair(totalChangeAmount, changePercent)
    }
    
    private fun calculateAssetAllocation(investments: List<Investment>): Map<InvestmentCategory, Double> {
        val totalValue = calculateTotalValue(investments)
        if (totalValue == 0.0) return emptyMap()
        
        return investments.filter { it.isActive }
            .groupBy { it.category }
            .mapValues { (_, investments) ->
                (investments.sumOf { it.currentValue * it.quantity } / totalValue) * 100
            }
    }
    
    private fun calculateRiskMetrics(
        investments: List<Investment>,
        priceHistory: List<InvestmentPriceHistory>
    ): RiskMetrics {
        val portfolioReturns = calculatePortfolioReturns(investments, priceHistory)
        val avgReturn = portfolioReturns.average()
        val volatility = sqrt(portfolioReturns.map { (it - avgReturn).pow(2) }.average())
        val riskFreeRate = 0.02 // Assume 2% risk-free rate
        val sharpeRatio = if (volatility > 0) (avgReturn - riskFreeRate) / volatility else 0.0
        val riskScore = min(100.0, volatility * 100)
        
        return RiskMetrics(riskScore, sharpeRatio, volatility)
    }
    
    private fun calculatePortfolioReturns(
        investments: List<Investment>,
        priceHistory: List<InvestmentPriceHistory>
    ): List<Double> {
        // Simplified calculation - in practice, would use more sophisticated methods
        val returns = mutableListOf<Double>()
        val sortedHistory = priceHistory.sortedBy { it.recordedAt }
        
        for (i in 1 until sortedHistory.size) {
            val previousPrice = sortedHistory[i - 1].price
            val currentPrice = sortedHistory[i].price
            val return_ = (currentPrice - previousPrice) / previousPrice
            returns.add(return_)
        }
        
        return returns
    }
    
    private fun calculateDiversificationScore(investments: List<Investment>): Double {
        val activeInvestments = investments.filter { it.isActive }
        if (activeInvestments.isEmpty()) return 0.0
        
        val categoryCount = activeInvestments.map { it.category }.distinct().size
        val typeCount = activeInvestments.map { it.type }.distinct().size
        val currencyCount = activeInvestments.map { it.currency }.distinct().size
        
        // Simple diversification score based on variety
        val maxScore = 100.0
        val categoryScore = min(50.0, categoryCount * 10.0)
        val typeScore = min(30.0, typeCount * 5.0)
        val currencyScore = min(20.0, currencyCount * 10.0)
        
        return categoryScore + typeScore + currencyScore
    }
    
    private fun calculateAnnualizedReturn(
        investment: Investment,
        transactions: List<InvestmentTransaction>,
        priceHistory: List<InvestmentPriceHistory>
    ): Double {
        val daysSinceFirstPurchase = (System.currentTimeMillis() - investment.purchaseDate) / (24 * 60 * 60 * 1000)
        if (daysSinceFirstPurchase < 1) return 0.0
        
        val currentValue = investment.currentValue * investment.quantity
        val initialValue = investment.initialValue * investment.quantity
        val totalReturn = (currentValue - initialValue) / initialValue
        
        val years = daysSinceFirstPurchase / 365.0
        return if (years > 0) ((1 + totalReturn).pow(1 / years) - 1) * 100 else 0.0
    }
    
    private fun calculateVolatility(priceHistory: List<InvestmentPriceHistory>): Double {
        if (priceHistory.size < 2) return 0.0
        
        val returns = mutableListOf<Double>()
        val sortedHistory = priceHistory.sortedBy { it.recordedAt }
        
        for (i in 1 until sortedHistory.size) {
            val previousPrice = sortedHistory[i - 1].price
            val currentPrice = sortedHistory[i].price
            val return_ = (currentPrice - previousPrice) / previousPrice
            returns.add(return_)
        }
        
        val avgReturn = returns.average()
        val variance = returns.map { (it - avgReturn).pow(2) }.average()
        return sqrt(variance) * 100 // Convert to percentage
    }
    
    private fun calculateMaxDrawdown(priceHistory: List<InvestmentPriceHistory>): Double {
        if (priceHistory.size < 2) return 0.0
        
        val sortedHistory = priceHistory.sortedBy { it.recordedAt }
        var maxPrice = sortedHistory.first().price
        var maxDrawdown = 0.0
        
        sortedHistory.forEach { history ->
            if (history.price > maxPrice) {
                maxPrice = history.price
            } else {
                val drawdown = (maxPrice - history.price) / maxPrice
                if (drawdown > maxDrawdown) {
                    maxDrawdown = drawdown
                }
            }
        }
        
        return maxDrawdown * 100 // Convert to percentage
    }
    
    private fun calculateBeta(investment: Investment, priceHistory: List<InvestmentPriceHistory>): Double {
        // Simplified beta calculation - in practice would need market index data
        return 1.0 // Default beta
    }
    
    private fun calculatePortfolioVolatility(
        investments: List<Investment>,
        priceHistory: List<InvestmentPriceHistory>
    ): Double {
        val returns = calculatePortfolioReturns(investments, priceHistory)
        if (returns.isEmpty()) return 0.0
        
        val avgReturn = returns.average()
        val variance = returns.map { (it - avgReturn).pow(2) }.average()
        return sqrt(variance) * 100
    }
    
    private fun calculateConcentrationRisk(investments: List<Investment>): Double {
        val totalValue = calculateTotalValue(investments)
        if (totalValue == 0.0) return 0.0
        
        val maxConcentration = investments.filter { it.isActive }
            .maxOfOrNull { (it.currentValue * it.quantity) / totalValue } ?: 0.0
        
        return when {
            maxConcentration > 0.5 -> 100.0 // Very high risk
            maxConcentration > 0.3 -> 75.0  // High risk
            maxConcentration > 0.2 -> 50.0  // Moderate risk
            maxConcentration > 0.1 -> 25.0  // Low risk
            else -> 0.0                     // Very low risk
        }
    }
    
    private fun calculateCurrencyRisk(investments: List<Investment>): Double {
        val currencyExposure = investments.filter { it.isActive }
            .groupBy { it.currency }
            .mapValues { (_, investments) ->
                investments.sumOf { it.currentValue * it.quantity }
            }
        
        val totalValue = currencyExposure.values.sum()
        if (totalValue == 0.0) return 0.0
        
        val nonBaseCurrencyExposure = currencyExposure
            .filterKeys { it != "SEK" }
            .values.sum() / totalValue
        
        return nonBaseCurrencyExposure * 100
    }
    
    private fun calculateLiquidityRisk(investments: List<Investment>): Double {
        val totalValue = calculateTotalValue(investments)
        if (totalValue == 0.0) return 0.0
        
        val illiquidValue = investments.filter { it.isActive }
            .filter { it.type in listOf(InvestmentType.REAL_ESTATE, InvestmentType.COMMODITY) }
            .sumOf { it.currentValue * it.quantity }
        
        return (illiquidValue / totalValue) * 100
    }
    
    private fun generateRiskRecommendations(
        overallRisk: Double,
        concentrationRisk: Double,
        currencyRisk: Double
    ): List<String> {
        val recommendations = mutableListOf<String>()
        
        if (overallRisk > 70) {
            recommendations.add("Consider reducing portfolio risk through diversification")
        }
        
        if (concentrationRisk > 50) {
            recommendations.add("Portfolio is too concentrated - consider spreading investments")
        }
        
        if (currencyRisk > 30) {
            recommendations.add("High currency exposure - consider hedging foreign currency risk")
        }
        
        return recommendations
    }
}

data class PortfolioPerformance(
    val totalValue: Double,
    val totalCost: Double,
    val totalGainLoss: Double,
    val totalReturnPercent: Double,
    val dayChange: Double,
    val dayChangePercent: Double,
    val assetAllocation: Map<InvestmentCategory, Double>,
    val riskScore: Double,
    val sharpeRatio: Double,
    val volatility: Double,
    val diversificationScore: Double,
    val lastUpdated: Long
)

data class InvestmentPerformance(
    val investmentId: Long,
    val currentValue: Double,
    val totalCost: Double,
    val gainLoss: Double,
    val returnPercent: Double,
    val annualizedReturn: Double,
    val volatility: Double,
    val maxDrawdown: Double,
    val beta: Double,
    val lastUpdated: Long
)

data class RiskMetrics(
    val riskScore: Double,
    val sharpeRatio: Double,
    val volatility: Double
)

data class RebalancingRecommendation(
    val category: InvestmentCategory,
    val currentPercent: Double,
    val targetPercent: Double,
    val difference: Double,
    val adjustmentAmount: Double,
    val action: RebalancingAction,
    val priority: RebalancingPriority
)

enum class RebalancingAction {
    BUY, SELL
}

enum class RebalancingPriority {
    LOW, MEDIUM, HIGH
}

data class RiskAssessment(
    val overallRiskScore: Double,
    val riskLevel: RiskLevel,
    val volatilityRisk: Double,
    val concentrationRisk: Double,
    val currencyRisk: Double,
    val liquidityRisk: Double,
    val recommendations: List<String>,
    val lastUpdated: Long
)

enum class RiskLevel {
    LOW, MODERATE_LOW, MODERATE, MODERATE_HIGH, HIGH
}