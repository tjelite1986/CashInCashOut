package com.example.budgetapp.analytics.investments

import android.content.Context
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.analytics.investments.entities.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.*

/**
 * Service for investment performance tracking and analytics
 * Calculates returns, risk metrics, and provides investment insights
 */
class InvestmentAnalyticsService(private val context: Context) {
    
    private val database = BudgetDatabase.getDatabase(context)
    private val investmentDao = database.investmentDao()
    
    companion object {
        const val DAYS_IN_YEAR = 365.25
        const val RISK_FREE_RATE = 0.02 // 2% risk-free rate assumption
    }
    
    /**
     * Calculate comprehensive performance metrics for an investment
     */
    suspend fun calculateInvestmentPerformance(investmentId: Long): InvestmentPerformance? = 
        withContext(Dispatchers.IO) {
            try {
                val investment = investmentDao.getInvestmentById(investmentId) ?: return@withContext null
                val transactions = investmentDao.getTransactionsForInvestment(investmentId)
                val priceHistory = investmentDao.getPriceHistoryForInvestment(investmentId)
                val dividends = investmentDao.getDividendsForInvestment(investmentId)
                
                // Collect data
                val transactionList = mutableListOf<InvestmentTransaction>()
                val priceList = mutableListOf<InvestmentPriceHistory>()
                val dividendList = mutableListOf<InvestmentDividend>()
                
                transactions.collect { transactionList.addAll(it) }
                priceHistory.collect { priceList.addAll(it) }
                dividends.collect { dividendList.addAll(it) }
                
                calculatePerformanceMetrics(investment, transactionList, priceList, dividendList)
            } catch (e: Exception) {
                com.example.budgetapp.utils.ErrorHandler.logError("InvestmentAnalytics", "Error calculating performance", e)
                null
            }
        }
    
    /**
     * Calculate portfolio-level performance metrics
     */
    suspend fun calculatePortfolioPerformance(portfolioId: Long? = null): PortfolioPerformance = 
        withContext(Dispatchers.IO) {
            try {
                val investments = if (portfolioId != null) {
                    val investmentList = mutableListOf<Investment>()
                    investmentDao.getInvestmentsInPortfolio(portfolioId).collect { investmentList.addAll(it) }
                    investmentList
                } else {
                    val investmentList = mutableListOf<Investment>()
                    investmentDao.getAllActiveInvestments().collect { investmentList.addAll(it) }
                    investmentList
                }
                
                val performanceList = investments.mapNotNull { investment ->
                    calculateInvestmentPerformance(investment.id)
                }
                
                calculatePortfolioMetrics(performanceList, investments)
            } catch (e: Exception) {
                com.example.budgetapp.utils.ErrorHandler.logError("InvestmentAnalytics", "Error calculating portfolio performance", e)
                PortfolioPerformance()
            }
        }
    
    /**
     * Calculate individual investment performance metrics
     */
    private fun calculatePerformanceMetrics(
        investment: Investment,
        transactions: List<InvestmentTransaction>,
        priceHistory: List<InvestmentPriceHistory>,
        dividends: List<InvestmentDividend>
    ): InvestmentPerformance {
        
        val currentPrice = priceHistory.maxByOrNull { it.recordedAt }?.price ?: investment.currentValue
        val totalCost = investment.averageCost * investment.quantity
        val currentValue = currentPrice * investment.quantity
        val totalDividends = dividends.sumOf { it.totalAmount }
        
        // Basic metrics
        val absoluteGain = currentValue - totalCost + totalDividends
        val percentageGain = if (totalCost > 0) (absoluteGain / totalCost) * 100 else 0.0
        
        // Time-based metrics
        val holdingPeriodDays = (System.currentTimeMillis() - investment.purchaseDate) / (1000 * 60 * 60 * 24)
        val annualizedReturn = if (holdingPeriodDays > 0) {
            val years = holdingPeriodDays / DAYS_IN_YEAR
            if (years >= 1) {
                ((currentValue + totalDividends) / totalCost).pow(1.0 / years) - 1.0
            } else {
                percentageGain / 100.0
            }
        } else 0.0
        
        // Volatility calculation (standard deviation of returns)
        val dailyReturns = calculateDailyReturns(priceHistory)
        val volatility = calculateVolatility(dailyReturns)
        val annualizedVolatility = volatility * sqrt(DAYS_IN_YEAR)
        
        // Sharpe ratio
        val sharpeRatio = if (annualizedVolatility > 0) {
            (annualizedReturn - RISK_FREE_RATE) / annualizedVolatility
        } else 0.0
        
        // Dividend yield
        val dividendYield = if (currentValue > 0) (totalDividends / currentValue) * 100 else 0.0
        
        // Max drawdown
        val maxDrawdown = calculateMaxDrawdown(priceHistory)
        
        return InvestmentPerformance(
            investmentId = investment.id,
            investmentName = investment.name,
            investmentType = investment.type,
            totalCost = totalCost,
            currentValue = currentValue,
            absoluteGain = absoluteGain,
            percentageGain = percentageGain,
            annualizedReturn = annualizedReturn * 100, // Convert to percentage
            dividendIncome = totalDividends,
            dividendYield = dividendYield,
            volatility = annualizedVolatility * 100, // Convert to percentage
            sharpeRatio = sharpeRatio,
            maxDrawdown = maxDrawdown * 100, // Convert to percentage
            holdingPeriodDays = holdingPeriodDays.toInt(),
            lastPriceUpdate = priceHistory.maxByOrNull { it.recordedAt }?.recordedAt ?: investment.lastUpdated
        )
    }
    
    /**
     * Calculate portfolio-level metrics
     */
    private fun calculatePortfolioMetrics(
        performances: List<InvestmentPerformance>,
        investments: List<Investment>
    ): PortfolioPerformance {
        
        if (performances.isEmpty()) {
            return PortfolioPerformance()
        }
        
        val totalValue = performances.sumOf { it.currentValue }
        val totalCost = performances.sumOf { it.totalCost }
        val totalGain = performances.sumOf { it.absoluteGain }
        val totalDividends = performances.sumOf { it.dividendIncome }
        
        val portfolioReturn = if (totalCost > 0) (totalGain / totalCost) * 100 else 0.0
        
        // Weighted average metrics
        val weightedAnnualizedReturn = performances.sumOf { 
            (it.currentValue / totalValue) * it.annualizedReturn 
        }
        
        val weightedSharpeRatio = performances.sumOf { 
            (it.currentValue / totalValue) * it.sharpeRatio 
        }
        
        // Asset allocation
        val assetAllocation = investments.groupBy { it.type }
            .mapValues { (_, invs) -> 
                invs.sumOf { it.currentValue * it.quantity } 
            }
            .mapValues { (_, value) -> 
                if (totalValue > 0) (value / totalValue) * 100 else 0.0 
            }
        
        // Risk metrics
        val portfolioVolatility = calculatePortfolioVolatility(performances, totalValue)
        
        return PortfolioPerformance(
            totalInvestments = investments.size,
            totalValue = totalValue,
            totalCost = totalCost,
            totalGain = totalGain,
            portfolioReturn = portfolioReturn,
            annualizedReturn = weightedAnnualizedReturn,
            totalDividendIncome = totalDividends,
            averageDividendYield = if (totalValue > 0) (totalDividends / totalValue) * 100 else 0.0,
            portfolioVolatility = portfolioVolatility,
            sharpeRatio = weightedSharpeRatio,
            assetAllocation = assetAllocation,
            topPerformer = performances.maxByOrNull { it.percentageGain },
            worstPerformer = performances.minByOrNull { it.percentageGain },
            lastUpdated = System.currentTimeMillis()
        )
    }
    
    /**
     * Calculate daily returns from price history
     */
    private fun calculateDailyReturns(priceHistory: List<InvestmentPriceHistory>): List<Double> {
        if (priceHistory.size < 2) return emptyList()
        
        val sortedPrices = priceHistory.sortedBy { it.recordedAt }
        val returns = mutableListOf<Double>()
        
        for (i in 1 until sortedPrices.size) {
            val previousPrice = sortedPrices[i-1].price
            val currentPrice = sortedPrices[i].price
            
            if (previousPrice > 0) {
                val dailyReturn = (currentPrice - previousPrice) / previousPrice
                returns.add(dailyReturn)
            }
        }
        
        return returns
    }
    
    /**
     * Calculate volatility (standard deviation of returns)
     */
    private fun calculateVolatility(returns: List<Double>): Double {
        if (returns.size < 2) return 0.0
        
        val mean = returns.average()
        val variance = returns.sumOf { (it - mean).pow(2) } / (returns.size - 1)
        return sqrt(variance)
    }
    
    /**
     * Calculate maximum drawdown
     */
    private fun calculateMaxDrawdown(priceHistory: List<InvestmentPriceHistory>): Double {
        if (priceHistory.size < 2) return 0.0
        
        val sortedPrices = priceHistory.sortedBy { it.recordedAt }
        var maxPrice = sortedPrices[0].price
        var maxDrawdown = 0.0
        
        for (price in sortedPrices) {
            if (price.price > maxPrice) {
                maxPrice = price.price
            } else {
                val drawdown = (maxPrice - price.price) / maxPrice
                maxDrawdown = maxOf(maxDrawdown, drawdown)
            }
        }
        
        return maxDrawdown
    }
    
    /**
     * Calculate portfolio volatility considering correlations
     */
    private fun calculatePortfolioVolatility(
        performances: List<InvestmentPerformance>,
        totalValue: Double
    ): Double {
        // Simplified portfolio volatility calculation
        // In reality, this would require correlation matrix between assets
        val weightedVolatilities = performances.sumOf { 
            val weight = it.currentValue / totalValue
            weight * weight * (it.volatility / 100).pow(2)
        }
        
        return sqrt(weightedVolatilities) * 100 // Convert back to percentage
    }
    
    /**
     * Generate investment recommendations based on performance analysis
     */
    suspend fun generateInvestmentRecommendations(): List<InvestmentRecommendation> = 
        withContext(Dispatchers.IO) {
            val recommendations = mutableListOf<InvestmentRecommendation>()
            
            try {
                val portfolioPerformance = calculatePortfolioPerformance()
                val investments = mutableListOf<Investment>()
                investmentDao.getAllActiveInvestments().collect { investments.addAll(it) }
                
                // Diversification recommendations
                if (portfolioPerformance.assetAllocation.size < 3) {
                    recommendations.add(
                        InvestmentRecommendation(
                            type = InvestmentRecommendationType.DIVERSIFICATION,
                            title = "Förbättra diversifiering",
                            description = "Din portfölj är koncentrerad till få tillgångsklasser. Överväg att diversifiera.",
                            priority = RecommendationPriority.HIGH,
                            actionItems = listOf("Lägg till fonder eller ETFer", "Överväg obligationer för stabilitet")
                        )
                    )
                }
                
                // Performance recommendations
                val underperformers = investments.filter { investment ->
                    val performance = calculateInvestmentPerformance(investment.id)
                    performance?.annualizedReturn ?: 0.0 < -5.0 // Underperforming by more than 5%
                }
                
                if (underperformers.isNotEmpty()) {
                    recommendations.add(
                        InvestmentRecommendation(
                            type = InvestmentRecommendationType.PERFORMANCE,
                            title = "Granska underpresterande investeringar",
                            description = "Du har ${underperformers.size} investeringar som underpresterat betydligt.",
                            priority = RecommendationPriority.MEDIUM,
                            actionItems = underperformers.map { "Överväg att sälja ${it.name}" }
                        )
                    )
                }
                
                // High volatility warning
                if (portfolioPerformance.portfolioVolatility > 25.0) {
                    recommendations.add(
                        InvestmentRecommendation(
                            type = InvestmentRecommendationType.RISK_MANAGEMENT,
                            title = "Hög portföljvolatilitet",
                            description = "Din portfölj har hög volatilitet (${String.format("%.1f", portfolioPerformance.portfolioVolatility)}%). Överväg att minska risken.",
                            priority = RecommendationPriority.MEDIUM,
                            actionItems = listOf("Lägg till mer stabila tillgångar", "Minska exponering mot volatila aktier")
                        )
                    )
                }
                
                // Dividend opportunity
                if (portfolioPerformance.averageDividendYield < 2.0) {
                    recommendations.add(
                        InvestmentRecommendation(
                            type = InvestmentRecommendationType.INCOME,
                            title = "Låg utdelningsavkastning",
                            description = "Din portfölj genererar låg utdelningsavkastning. Överväg utdelningsaktier.",
                            priority = RecommendationPriority.LOW,
                            actionItems = listOf("Investera i utdelningsfonder", "Lägg till utdelningsaktier")
                        )
                    )
                }
                
            } catch (e: Exception) {
                com.example.budgetapp.utils.ErrorHandler.logError("InvestmentAnalytics", "Error generating recommendations", e)
            }
            
            recommendations
        }
}

/**
 * Data class representing investment performance metrics
 */
data class InvestmentPerformance(
    val investmentId: Long,
    val investmentName: String,
    val investmentType: InvestmentType,
    val totalCost: Double,
    val currentValue: Double,
    val absoluteGain: Double,
    val percentageGain: Double,
    val annualizedReturn: Double,
    val dividendIncome: Double,
    val dividendYield: Double,
    val volatility: Double,
    val sharpeRatio: Double,
    val maxDrawdown: Double,
    val holdingPeriodDays: Int,
    val lastPriceUpdate: Long
)

/**
 * Data class representing portfolio-level performance
 */
data class PortfolioPerformance(
    val totalInvestments: Int = 0,
    val totalValue: Double = 0.0,
    val totalCost: Double = 0.0,
    val totalGain: Double = 0.0,
    val portfolioReturn: Double = 0.0,
    val annualizedReturn: Double = 0.0,
    val totalDividendIncome: Double = 0.0,
    val averageDividendYield: Double = 0.0,
    val portfolioVolatility: Double = 0.0,
    val sharpeRatio: Double = 0.0,
    val assetAllocation: Map<InvestmentType, Double> = emptyMap(),
    val topPerformer: InvestmentPerformance? = null,
    val worstPerformer: InvestmentPerformance? = null,
    val lastUpdated: Long = System.currentTimeMillis()
)

/**
 * Investment recommendation data class
 */
data class InvestmentRecommendation(
    val type: InvestmentRecommendationType,
    val title: String,
    val description: String,
    val priority: RecommendationPriority,
    val actionItems: List<String> = emptyList(),
    val createdAt: Long = System.currentTimeMillis()
)

enum class InvestmentRecommendationType {
    DIVERSIFICATION,
    PERFORMANCE,
    RISK_MANAGEMENT,
    INCOME,
    REBALANCING,
    TAX_EFFICIENCY
}

enum class RecommendationPriority {
    HIGH,
    MEDIUM,
    LOW
}