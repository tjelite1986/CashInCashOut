package com.example.budgetapp.analytics.investments.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import com.example.budgetapp.analytics.investments.multicurrency.ExchangeRate
import com.example.budgetapp.analytics.investments.multicurrency.CurrencyAlert
import com.example.budgetapp.analytics.investments.multicurrency.MultiCurrencyTransaction

@Dao
interface ExchangeRateDao {
    
    @Query("SELECT * FROM exchange_rates ORDER BY lastUpdated DESC")
    fun getAllExchangeRates(): Flow<List<ExchangeRate>>
    
    @Query("SELECT * FROM exchange_rates WHERE fromCurrency = :fromCurrency AND toCurrency = :toCurrency ORDER BY lastUpdated DESC LIMIT 1")
    suspend fun getLatestExchangeRate(fromCurrency: String, toCurrency: String): ExchangeRate?
    
    @Query("SELECT * FROM exchange_rates WHERE fromCurrency = :fromCurrency ORDER BY lastUpdated DESC")
    fun getExchangeRatesFromCurrency(fromCurrency: String): Flow<List<ExchangeRate>>
    
    @Query("SELECT * FROM exchange_rates WHERE toCurrency = :toCurrency ORDER BY lastUpdated DESC")
    fun getExchangeRatesToCurrency(toCurrency: String): Flow<List<ExchangeRate>>
    
    @Query("SELECT * FROM exchange_rates WHERE lastUpdated >= :since ORDER BY lastUpdated DESC")
    fun getRecentExchangeRates(since: Long): Flow<List<ExchangeRate>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExchangeRate(exchangeRate: ExchangeRate): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExchangeRates(exchangeRates: List<ExchangeRate>): List<Long>
    
    @Update
    suspend fun updateExchangeRate(exchangeRate: ExchangeRate)
    
    @Delete
    suspend fun deleteExchangeRate(exchangeRate: ExchangeRate)
    
    @Query("DELETE FROM exchange_rates WHERE lastUpdated < :cutoffTime")
    suspend fun deleteOldExchangeRates(cutoffTime: Long): Int
    
    @Query("DELETE FROM exchange_rates")
    suspend fun deleteAllExchangeRates()
    
    // Currency Alert methods
    @Query("SELECT * FROM currency_alerts WHERE isActive = 1 ORDER BY createdAt DESC")
    fun getActiveCurrencyAlerts(): Flow<List<CurrencyAlert>>
    
    @Query("SELECT * FROM currency_alerts ORDER BY createdAt DESC")
    fun getAllCurrencyAlerts(): Flow<List<CurrencyAlert>>
    
    @Query("SELECT * FROM currency_alerts WHERE fromCurrency = :fromCurrency AND toCurrency = :toCurrency AND isActive = 1")
    fun getCurrencyAlertsForPair(fromCurrency: String, toCurrency: String): Flow<List<CurrencyAlert>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencyAlert(alert: CurrencyAlert): Long
    
    @Update
    suspend fun updateCurrencyAlert(alert: CurrencyAlert)
    
    @Query("UPDATE currency_alerts SET isActive = 0 WHERE id = :alertId")
    suspend fun deactivateCurrencyAlert(alertId: Long)
    
    @Query("UPDATE currency_alerts SET isActive = 0, triggeredAt = :triggeredAt WHERE id = :alertId")
    suspend fun triggerCurrencyAlert(alertId: Long, triggeredAt: Long)
    
    @Delete
    suspend fun deleteCurrencyAlert(alert: CurrencyAlert)
    
    // Multi-Currency Transaction methods
    @Query("SELECT * FROM multi_currency_transactions ORDER BY transactionDate DESC")
    fun getAllMultiCurrencyTransactions(): Flow<List<MultiCurrencyTransaction>>
    
    @Query("SELECT * FROM multi_currency_transactions WHERE currency = :currency ORDER BY transactionDate DESC")
    fun getTransactionsByCurrency(currency: String): Flow<List<MultiCurrencyTransaction>>
    
    @Query("SELECT * FROM multi_currency_transactions WHERE baseCurrency = :baseCurrency ORDER BY transactionDate DESC")
    fun getTransactionsByBaseCurrency(baseCurrency: String): Flow<List<MultiCurrencyTransaction>>
    
    @Query("SELECT * FROM multi_currency_transactions WHERE category = :category ORDER BY transactionDate DESC")
    fun getTransactionsByCategory(category: String): Flow<List<MultiCurrencyTransaction>>
    
    @Query("SELECT * FROM multi_currency_transactions WHERE transactionDate BETWEEN :startDate AND :endDate ORDER BY transactionDate DESC")
    fun getTransactionsByDateRange(startDate: Long, endDate: Long): Flow<List<MultiCurrencyTransaction>>
    
    @Query("SELECT DISTINCT currency FROM multi_currency_transactions ORDER BY currency")
    suspend fun getUsedCurrencies(): List<String>
    
    @Query("SELECT DISTINCT baseCurrency FROM multi_currency_transactions ORDER BY baseCurrency")
    suspend fun getUsedBaseCurrencies(): List<String>
    
    @Query("SELECT DISTINCT category FROM multi_currency_transactions ORDER BY category")
    suspend fun getUsedCategories(): List<String>
    
    @Query("SELECT SUM(convertedAmount) FROM multi_currency_transactions WHERE baseCurrency = :baseCurrency AND transactionDate BETWEEN :startDate AND :endDate")
    suspend fun getTotalAmountInBaseCurrency(baseCurrency: String, startDate: Long, endDate: Long): Double?
    
    @Query("SELECT SUM(amount) FROM multi_currency_transactions WHERE currency = :currency AND transactionDate BETWEEN :startDate AND :endDate")
    suspend fun getTotalAmountInCurrency(currency: String, startDate: Long, endDate: Long): Double?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMultiCurrencyTransaction(transaction: MultiCurrencyTransaction): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMultiCurrencyTransactions(transactions: List<MultiCurrencyTransaction>): List<Long>
    
    @Update
    suspend fun updateMultiCurrencyTransaction(transaction: MultiCurrencyTransaction)
    
    @Delete
    suspend fun deleteMultiCurrencyTransaction(transaction: MultiCurrencyTransaction)
    
    @Query("DELETE FROM multi_currency_transactions WHERE id = :transactionId")
    suspend fun deleteMultiCurrencyTransactionById(transactionId: Long)
    
    @Query("DELETE FROM multi_currency_transactions")
    suspend fun deleteAllMultiCurrencyTransactions()
}