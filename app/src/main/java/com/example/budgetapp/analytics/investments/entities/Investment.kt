package com.example.budgetapp.analytics.investments.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "investments")
data class Investment(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val symbol: String? = null, // Stock symbol like "AAPL", "MSFT"
    val type: InvestmentType,
    val initialValue: Double,
    val currentValue: Double = initialValue,
    val quantity: Double = 1.0, // Number of shares/units
    val averageCost: Double = initialValue,
    val purchaseDate: Long = System.currentTimeMillis(),
    val currency: String = "SEK",
    val broker: String? = null, // "Avanza", "Nordnet", etc.
    val category: InvestmentCategory = InvestmentCategory.EQUITY,
    val notes: String? = null,
    val isActive: Boolean = true,
    val lastUpdated: Long = System.currentTimeMillis(),
    val createdAt: Long = System.currentTimeMillis()
)

enum class InvestmentType {
    STOCK,              // Individual stocks
    FUND,               // Mutual funds
    ETF,                // Exchange-traded funds
    BOND,               // Government or corporate bonds
    CRYPTO,             // Cryptocurrency
    REAL_ESTATE,        // REITs or direct real estate
    COMMODITY,          // Gold, silver, oil, etc.
    SAVINGS_ACCOUNT,    // High-yield savings
    PENSION,            // Pension/retirement accounts
    OTHER
}

enum class InvestmentCategory {
    EQUITY,             // Stocks, equity funds
    FIXED_INCOME,       // Bonds, fixed deposits
    MIXED,              // Balanced funds
    ALTERNATIVE,        // Crypto, commodities, REITs
    CASH_EQUIVALENT     // Savings accounts, money market
}

@Entity(tableName = "investment_transactions")
data class InvestmentTransaction(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val investmentId: Long,
    val type: InvestmentTransactionType,
    val quantity: Double,
    val pricePerUnit: Double,
    val totalAmount: Double,
    val fees: Double = 0.0,
    val transactionDate: Long = System.currentTimeMillis(),
    val notes: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)

enum class InvestmentTransactionType {
    BUY,
    SELL,
    DIVIDEND,
    SPLIT,
    MERGER,
    SPIN_OFF,
    RIGHTS_ISSUE,
    BONUS_SHARES
}

@Entity(tableName = "investment_price_history")
data class InvestmentPriceHistory(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val investmentId: Long,
    val price: Double,
    val currency: String = "SEK",
    val recordedAt: Long = System.currentTimeMillis(),
    val source: String? = null // "manual", "api", "import"
)

@Entity(tableName = "investment_dividends")
data class InvestmentDividend(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val investmentId: Long,
    val amountPerShare: Double,
    val totalAmount: Double,
    val currency: String = "SEK",
    val paymentDate: Long,
    val exDividendDate: Long? = null,
    val taxWithheld: Double = 0.0,
    val notes: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "investment_portfolios")
data class InvestmentPortfolio(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String? = null,
    val targetAllocation: String? = null, // JSON string of target percentages
    val riskProfile: RiskProfile = RiskProfile.MODERATE,
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis()
)

enum class RiskProfile {
    CONSERVATIVE,
    MODERATE,
    AGGRESSIVE,
    SPECULATIVE
}

@Entity(tableName = "portfolio_investments")
data class PortfolioInvestment(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val portfolioId: Long,
    val investmentId: Long,
    val targetPercentage: Double? = null,
    val addedAt: Long = System.currentTimeMillis()
)