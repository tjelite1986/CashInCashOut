package com.example.budgetapp.analytics.investments.banking

import android.content.Context
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class BankingIntegrationService(private val context: Context) {
    
    // Simulated banking integration - in reality would integrate with Open Banking APIs
    private val supportedBanks = mapOf(
        "SEB" to BankInfo("SEB", "SE", "https://api.seb.se", "SEB Bank"),
        "SWEDBANK" to BankInfo("SWEDBANK", "SE", "https://api.swedbank.se", "Swedbank"),
        "HANDELSBANKEN" to BankInfo("HANDELSBANKEN", "SE", "https://api.handelsbanken.se", "Svenska Handelsbanken"),
        "NORDEA" to BankInfo("NORDEA", "SE", "https://api.nordea.com", "Nordea Bank"),
        "DANSKE" to BankInfo("DANSKE", "SE", "https://api.danskebank.com", "Danske Bank"),
        "ICA" to BankInfo("ICA", "SE", "https://api.icabanken.se", "ICA Banken"),
        "LANSFORSAKRINGAR" to BankInfo("LANSFORSAKRINGAR", "SE", "https://api.lansforsakringar.se", "Länsförsäkringar Bank"),
        "SPARBANKEN" to BankInfo("SPARBANKEN", "SE", "https://api.sparbanken.se", "Sparbanken"),
        "SBAB" to BankInfo("SBAB", "SE", "https://api.sbab.se", "SBAB"),
        "AVANZA" to BankInfo("AVANZA", "SE", "https://api.avanza.se", "Avanza Bank"),
        "NORDNET" to BankInfo("NORDNET", "SE", "https://api.nordnet.se", "Nordnet")
    )
    
    suspend fun getSupportedBanks(): List<BankInfo> = withContext(Dispatchers.IO) {
        supportedBanks.values.toList()
    }
    
    suspend fun initiateBankConnection(bankCode: String, userId: String): BankConnectionResult = withContext(Dispatchers.IO) {
        try {
            val bank = supportedBanks[bankCode]
            if (bank != null) {
                // Simulate OAuth flow initiation
                val authUrl = "${bank.apiBaseUrl}/oauth/authorize?client_id=demo&redirect_uri=budgetapp://callback&response_type=code&scope=accounts:read transactions:read"
                
                BankConnectionResult.Success(
                    authorizationUrl = authUrl,
                    connectionId = UUID.randomUUID().toString(),
                    expiresIn = 3600
                )
            } else {
                BankConnectionResult.Error("Unsupported bank: $bankCode")
            }
        } catch (e: Exception) {
            BankConnectionResult.Error("Failed to initiate bank connection: ${e.message}")
        }
    }
    
    suspend fun completeBankConnection(
        connectionId: String,
        authorizationCode: String
    ): BankConnectionResult = withContext(Dispatchers.IO) {
        try {
            // Simulate token exchange
            val accessToken = "demo_access_token_${UUID.randomUUID()}"
            val refreshToken = "demo_refresh_token_${UUID.randomUUID()}"
            
            BankConnectionResult.Success(
                accessToken = accessToken,
                refreshToken = refreshToken,
                connectionId = connectionId,
                expiresIn = 3600
            )
        } catch (e: Exception) {
            BankConnectionResult.Error("Failed to complete bank connection: ${e.message}")
        }
    }
    
    suspend fun getConnectedAccounts(accessToken: String): List<BankAccount> = withContext(Dispatchers.IO) {
        try {
            // Simulate fetching accounts
            listOf(
                BankAccount(
                    id = "acc_001",
                    name = "Personkonto",
                    type = AccountType.CHECKING,
                    balance = 25430.50,
                    currency = "SEK",
                    iban = "SE35 5000 0000 0549 1000 0003",
                    bic = "SEBSSESS",
                    bankName = "SEB",
                    isActive = true,
                    lastSynced = System.currentTimeMillis()
                ),
                BankAccount(
                    id = "acc_002",
                    name = "Sparkonto",
                    type = AccountType.SAVINGS,
                    balance = 125000.00,
                    currency = "SEK",
                    iban = "SE35 5000 0000 0549 1000 0004",
                    bic = "SEBSSESS",
                    bankName = "SEB",
                    isActive = true,
                    lastSynced = System.currentTimeMillis()
                ),
                BankAccount(
                    id = "acc_003",
                    name = "Investeringssparkonto",
                    type = AccountType.INVESTMENT,
                    balance = 450000.00,
                    currency = "SEK",
                    iban = "SE35 5000 0000 0549 1000 0005",
                    bic = "SEBSSESS",
                    bankName = "SEB",
                    isActive = true,
                    lastSynced = System.currentTimeMillis()
                )
            )
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    suspend fun getBankTransactions(
        accessToken: String,
        accountId: String,
        fromDate: Long? = null,
        toDate: Long? = null,
        limit: Int = 100
    ): List<BankTransaction> = withContext(Dispatchers.IO) {
        try {
            // Simulate fetching transactions
            val transactions = mutableListOf<BankTransaction>()
            val random = Random()
            
            repeat(limit.coerceAtMost(50)) { i ->
                val amount = -random.nextDouble() * 1000 + random.nextDouble() * 200
                transactions.add(
                    BankTransaction(
                        id = "txn_${UUID.randomUUID()}",
                        accountId = accountId,
                        amount = amount,
                        currency = "SEK",
                        description = getRandomTransactionDescription(),
                        date = System.currentTimeMillis() - (i * 24 * 60 * 60 * 1000),
                        category = getRandomCategory(),
                        counterparty = getRandomCounterparty(),
                        reference = "REF${random.nextInt(999999)}",
                        balance = 25430.50 + amount,
                        transactionType = if (amount > 0) TransactionType.CREDIT else TransactionType.DEBIT,
                        status = TransactionStatus.COMPLETED,
                        lastUpdated = System.currentTimeMillis()
                    )
                )
            }
            
            transactions.sortedByDescending { it.date }
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    suspend fun syncAccountBalances(accessToken: String): Map<String, Double> = withContext(Dispatchers.IO) {
        try {
            val accounts = getConnectedAccounts(accessToken)
            accounts.associate { it.id to it.balance }
        } catch (e: Exception) {
            emptyMap()
        }
    }
    
    suspend fun categorizeTransactions(transactions: List<BankTransaction>): List<CategorizedTransaction> = withContext(Dispatchers.IO) {
        transactions.map { transaction ->
            val suggestedCategory = when {
                transaction.description.contains("ICA", true) -> "Groceries"
                transaction.description.contains("SHELL", true) -> "Transport"
                transaction.description.contains("SPOTIFY", true) -> "Entertainment"
                transaction.description.contains("NETFLIX", true) -> "Entertainment"
                transaction.description.contains("SWISH", true) -> "Transfer"
                transaction.description.contains("SALARY", true) -> "Income"
                transaction.description.contains("RENT", true) -> "Housing"
                transaction.amount > 0 -> "Income"
                else -> "Other"
            }
            
            val confidence = when {
                transaction.description.contains("ICA", true) -> 0.95
                transaction.description.contains("SHELL", true) -> 0.90
                transaction.description.contains("SPOTIFY", true) -> 0.98
                transaction.description.contains("NETFLIX", true) -> 0.98
                transaction.description.contains("SWISH", true) -> 0.85
                transaction.description.contains("SALARY", true) -> 0.92
                transaction.description.contains("RENT", true) -> 0.88
                else -> 0.60
            }
            
            CategorizedTransaction(
                transaction = transaction,
                suggestedCategory = suggestedCategory,
                confidence = confidence,
                existingCategory = transaction.category
            )
        }
    }
    
    suspend fun detectDuplicateTransactions(
        newTransactions: List<BankTransaction>,
        existingTransactions: List<BankTransaction>
    ): List<DuplicateTransactionPair> = withContext(Dispatchers.IO) {
        val duplicates = mutableListOf<DuplicateTransactionPair>()
        
        newTransactions.forEach { newTx ->
            existingTransactions.forEach { existingTx ->
                val similarity = calculateTransactionSimilarity(newTx, existingTx)
                if (similarity > 0.85) {
                    duplicates.add(
                        DuplicateTransactionPair(
                            newTransaction = newTx,
                            existingTransaction = existingTx,
                            similarity = similarity
                        )
                    )
                }
            }
        }
        
        duplicates.sortedByDescending { it.similarity }
    }
    
    suspend fun generateBankingInsights(accounts: List<BankAccount>): BankingInsights = withContext(Dispatchers.IO) {
        val totalBalance = accounts.sumOf { it.balance }
        val checkingBalance = accounts.filter { it.type == AccountType.CHECKING }.sumOf { it.balance }
        val savingsBalance = accounts.filter { it.type == AccountType.SAVINGS }.sumOf { it.balance }
        val investmentBalance = accounts.filter { it.type == AccountType.INVESTMENT }.sumOf { it.balance }
        
        val insights = mutableListOf<String>()
        
        if (checkingBalance > savingsBalance * 2) {
            insights.add("Consider transferring some funds from checking to savings for better interest")
        }
        
        if (savingsBalance < totalBalance * 0.1) {
            insights.add("Your emergency fund seems low. Aim for 3-6 months of expenses in savings")
        }
        
        if (investmentBalance < totalBalance * 0.2 && totalBalance > 100000) {
            insights.add("Consider increasing your investment allocation for long-term growth")
        }
        
        BankingInsights(
            totalBalance = totalBalance,
            balanceDistribution = mapOf(
                "Checking" to checkingBalance,
                "Savings" to savingsBalance,
                "Investment" to investmentBalance
            ),
            insights = insights,
            lastUpdated = System.currentTimeMillis()
        )
    }
    
    private fun calculateTransactionSimilarity(tx1: BankTransaction, tx2: BankTransaction): Double {
        val amountSimilarity = if (kotlin.math.abs(tx1.amount - tx2.amount) < 0.01) 1.0 else 0.0
        val dateSimilarity = if (kotlin.math.abs(tx1.date - tx2.date) < 24 * 60 * 60 * 1000) 1.0 else 0.0
        val descriptionSimilarity = if (tx1.description == tx2.description) 1.0 else 0.0
        
        return (amountSimilarity + dateSimilarity + descriptionSimilarity) / 3.0
    }
    
    private fun getRandomTransactionDescription(): String {
        val descriptions = listOf(
            "ICA MAXI STOCKHOLM",
            "SHELL STOCKHOLM",
            "SPOTIFY SUBSCRIPTION",
            "NETFLIX MONTHLY",
            "SWISH PAYMENT",
            "SALARY PAYMENT",
            "RENT PAYMENT",
            "PHARMACY PURCHASE",
            "RESTAURANT MEAL",
            "PUBLIC TRANSPORT",
            "ONLINE SHOPPING",
            "UTILITY BILL",
            "INSURANCE PAYMENT",
            "PHONE BILL",
            "INTERNET BILL"
        )
        return descriptions.random()
    }
    
    private fun getRandomCategory(): String {
        val categories = listOf(
            "Groceries", "Transport", "Entertainment", "Housing", "Healthcare",
            "Shopping", "Dining", "Utilities", "Insurance", "Other"
        )
        return categories.random()
    }
    
    private fun getRandomCounterparty(): String {
        val counterparties = listOf(
            "ICA MAXI", "SHELL", "SPOTIFY AB", "NETFLIX", "LANDLORD",
            "PHARMACY", "RESTAURANT", "SL", "ONLINE STORE", "UTILITY COMPANY"
        )
        return counterparties.random()
    }
}

data class BankInfo(
    val code: String,
    val country: String,
    val apiBaseUrl: String,
    val displayName: String
)

sealed class BankConnectionResult {
    data class Success(
        val authorizationUrl: String? = null,
        val accessToken: String? = null,
        val refreshToken: String? = null,
        val connectionId: String,
        val expiresIn: Long
    ) : BankConnectionResult()
    
    data class Error(val message: String) : BankConnectionResult()
}

@Entity(tableName = "bank_accounts")
data class BankAccount(
    @PrimaryKey
    val id: String,
    val name: String,
    val type: AccountType,
    val balance: Double,
    val currency: String,
    val iban: String? = null,
    val bic: String? = null,
    val bankName: String,
    val isActive: Boolean = true,
    val lastSynced: Long = System.currentTimeMillis()
)

enum class AccountType {
    CHECKING, SAVINGS, INVESTMENT, CREDIT, LOAN
}

@Entity(tableName = "bank_transactions")
data class BankTransaction(
    @PrimaryKey
    val id: String,
    val accountId: String,
    val amount: Double,
    val currency: String,
    val description: String,
    val date: Long,
    val category: String? = null,
    val counterparty: String? = null,
    val reference: String? = null,
    val balance: Double? = null,
    val transactionType: TransactionType,
    val status: TransactionStatus = TransactionStatus.COMPLETED,
    val lastUpdated: Long = System.currentTimeMillis()
)

enum class TransactionType {
    DEBIT, CREDIT
}

enum class TransactionStatus {
    PENDING, COMPLETED, FAILED, CANCELLED
}

data class CategorizedTransaction(
    val transaction: BankTransaction,
    val suggestedCategory: String,
    val confidence: Double,
    val existingCategory: String?
)

data class DuplicateTransactionPair(
    val newTransaction: BankTransaction,
    val existingTransaction: BankTransaction,
    val similarity: Double
)

data class BankingInsights(
    val totalBalance: Double,
    val balanceDistribution: Map<String, Double>,
    val insights: List<String>,
    val lastUpdated: Long
)