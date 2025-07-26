package com.example.budgetapp.analytics.investments.dao

import androidx.room.*
import com.example.budgetapp.analytics.investments.entities.*
import kotlinx.coroutines.flow.Flow

@Dao
interface InvestmentDao {
    
    // Investment CRUD operations
    @Query("SELECT * FROM investments WHERE isActive = 1 ORDER BY name ASC")
    fun getAllActiveInvestments(): Flow<List<Investment>>
    
    @Query("SELECT * FROM investments ORDER BY createdAt DESC")
    fun getAllInvestments(): Flow<List<Investment>>
    
    @Query("SELECT * FROM investments WHERE id = :investmentId")
    suspend fun getInvestmentById(investmentId: Long): Investment?
    
    @Query("SELECT * FROM investments WHERE type = :type AND isActive = 1")
    fun getInvestmentsByType(type: InvestmentType): Flow<List<Investment>>
    
    @Query("SELECT * FROM investments WHERE category = :category AND isActive = 1")
    fun getInvestmentsByCategory(category: InvestmentCategory): Flow<List<Investment>>
    
    @Insert
    suspend fun insertInvestment(investment: Investment): Long
    
    @Update
    suspend fun updateInvestment(investment: Investment)
    
    @Delete
    suspend fun deleteInvestment(investment: Investment)
    
    @Query("UPDATE investments SET isActive = 0 WHERE id = :investmentId")
    suspend fun deactivateInvestment(investmentId: Long)
    
    // Investment transactions
    @Query("SELECT * FROM investment_transactions WHERE investmentId = :investmentId ORDER BY transactionDate DESC")
    fun getTransactionsForInvestment(investmentId: Long): Flow<List<InvestmentTransaction>>
    
    @Query("SELECT * FROM investment_transactions ORDER BY transactionDate DESC LIMIT :limit")
    fun getRecentTransactions(limit: Int = 50): Flow<List<InvestmentTransaction>>
    
    @Insert
    suspend fun insertTransaction(transaction: InvestmentTransaction): Long
    
    @Update
    suspend fun updateTransaction(transaction: InvestmentTransaction)
    
    @Delete
    suspend fun deleteTransaction(transaction: InvestmentTransaction)
    
    // Price history
    @Query("SELECT * FROM investment_price_history WHERE investmentId = :investmentId ORDER BY recordedAt DESC")
    fun getPriceHistoryForInvestment(investmentId: Long): Flow<List<InvestmentPriceHistory>>
    
    @Query("SELECT * FROM investment_price_history WHERE investmentId = :investmentId ORDER BY recordedAt DESC LIMIT 1")
    suspend fun getLatestPriceForInvestment(investmentId: Long): InvestmentPriceHistory?
    
    @Insert
    suspend fun insertPriceHistoryEntry(priceHistory: InvestmentPriceHistory): Long
    
    @Query("DELETE FROM investment_price_history WHERE investmentId = :investmentId AND recordedAt < :cutoffDate")
    suspend fun cleanupOldPriceHistory(investmentId: Long, cutoffDate: Long)
    
    // Dividends
    @Query("SELECT * FROM investment_dividends WHERE investmentId = :investmentId ORDER BY paymentDate DESC")
    fun getDividendsForInvestment(investmentId: Long): Flow<List<InvestmentDividend>>
    
    @Query("SELECT SUM(totalAmount) FROM investment_dividends WHERE investmentId = :investmentId AND paymentDate >= :startDate AND paymentDate <= :endDate")
    suspend fun getTotalDividendsForPeriod(investmentId: Long, startDate: Long, endDate: Long): Double?
    
    @Insert
    suspend fun insertDividend(dividend: InvestmentDividend): Long
    
    @Update
    suspend fun updateDividend(dividend: InvestmentDividend)
    
    @Delete
    suspend fun deleteDividend(dividend: InvestmentDividend)
    
    // Portfolio management
    @Query("SELECT * FROM investment_portfolios WHERE isActive = 1 ORDER BY name ASC")
    fun getAllActivePortfolios(): Flow<List<InvestmentPortfolio>>
    
    @Query("SELECT * FROM investment_portfolios WHERE id = :portfolioId")
    suspend fun getPortfolioById(portfolioId: Long): InvestmentPortfolio?
    
    @Insert
    suspend fun insertPortfolio(portfolio: InvestmentPortfolio): Long
    
    @Update
    suspend fun updatePortfolio(portfolio: InvestmentPortfolio)
    
    @Query("UPDATE investment_portfolios SET isActive = 0 WHERE id = :portfolioId")
    suspend fun deactivatePortfolio(portfolioId: Long)
    
    // Portfolio investments
    @Query("""
        SELECT i.* FROM investments i 
        INNER JOIN portfolio_investments pi ON i.id = pi.investmentId 
        WHERE pi.portfolioId = :portfolioId AND i.isActive = 1
        ORDER BY i.name ASC
    """)
    fun getInvestmentsInPortfolio(portfolioId: Long): Flow<List<Investment>>
    
    @Insert
    suspend fun addInvestmentToPortfolio(portfolioInvestment: PortfolioInvestment): Long
    
    @Query("DELETE FROM portfolio_investments WHERE portfolioId = :portfolioId AND investmentId = :investmentId")
    suspend fun removeInvestmentFromPortfolio(portfolioId: Long, investmentId: Long)
    
    // Analytics queries
    @Query("""
        SELECT 
            type,
            COUNT(*) as count,
            SUM(currentValue * quantity) as totalValue,
            SUM((currentValue - averageCost) * quantity) as totalGainLoss
        FROM investments 
        WHERE isActive = 1 
        GROUP BY type
    """)
    suspend fun getInvestmentTypeBreakdown(): List<InvestmentTypeBreakdown>
    
    @Query("""
        SELECT 
            SUM(currentValue * quantity) as totalValue,
            SUM(initialValue * quantity) as totalCost,
            SUM((currentValue - averageCost) * quantity) as totalGainLoss,
            COUNT(*) as totalInvestments
        FROM investments 
        WHERE isActive = 1
    """)
    suspend fun getPortfolioSummary(): PortfolioSummary?
    
    @Query("""
        SELECT * FROM investments 
        WHERE isActive = 1 
        ORDER BY ((currentValue - averageCost) / averageCost) DESC 
        LIMIT :limit
    """)
    suspend fun getTopPerformers(limit: Int = 5): List<Investment>
    
    @Query("""
        SELECT * FROM investments 
        WHERE isActive = 1 
        ORDER BY ((currentValue - averageCost) / averageCost) ASC 
        LIMIT :limit
    """)
    suspend fun getWorstPerformers(limit: Int = 5): List<Investment>
    
    @Query("""
        SELECT SUM(totalAmount) FROM investment_dividends 
        WHERE paymentDate >= :startDate AND paymentDate <= :endDate
    """)
    suspend fun getTotalDividendIncomeForPeriod(startDate: Long, endDate: Long): Double?
}

// Data classes for analytics
data class InvestmentTypeBreakdown(
    val type: InvestmentType,
    val count: Int,
    val totalValue: Double,
    val totalGainLoss: Double
)

data class PortfolioSummary(
    val totalValue: Double,
    val totalCost: Double,
    val totalGainLoss: Double,
    val totalInvestments: Int
) {
    val totalGainLossPercentage: Double
        get() = if (totalCost > 0) (totalGainLoss / totalCost) * 100.0 else 0.0
}