package com.example.budgetapp.analytics.investments.multicurrency

import android.content.Context
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.text.NumberFormat
import java.util.*

class CurrencyService(private val context: Context) {
    
    private val exchangeRateApiKey = "demo" // Replace with actual API key
    private val exchangeRatesBaseUrl = "https://v6.exchangerate-api.com/v6"
    private val openExchangeRatesUrl = "https://openexchangerates.org/api"
    
    suspend fun getExchangeRate(fromCurrency: String, toCurrency: String): ExchangeRate? = withContext(Dispatchers.IO) {
        try {
            val url = "$exchangeRatesBaseUrl/$exchangeRateApiKey/pair/$fromCurrency/$toCurrency"
            val response = makeApiCall(url)
            parseExchangeRate(response, fromCurrency, toCurrency)
        } catch (e: Exception) {
            null
        }
    }
    
    suspend fun getAllExchangeRates(baseCurrency: String = "USD"): Map<String, Double> = withContext(Dispatchers.IO) {
        try {
            val url = "$exchangeRatesBaseUrl/$exchangeRateApiKey/latest/$baseCurrency"
            val response = makeApiCall(url)
            parseAllExchangeRates(response)
        } catch (e: Exception) {
            emptyMap()
        }
    }
    
    suspend fun convertCurrency(amount: Double, fromCurrency: String, toCurrency: String): CurrencyConversion? = withContext(Dispatchers.IO) {
        val exchangeRate = getExchangeRate(fromCurrency, toCurrency)
        if (exchangeRate != null) {
            val convertedAmount = amount * exchangeRate.rate
            CurrencyConversion(
                originalAmount = amount,
                originalCurrency = fromCurrency,
                convertedAmount = convertedAmount,
                targetCurrency = toCurrency,
                exchangeRate = exchangeRate.rate,
                conversionDate = System.currentTimeMillis()
            )
        } else null
    }
    
    suspend fun getHistoricalExchangeRate(fromCurrency: String, toCurrency: String, date: String): ExchangeRate? = withContext(Dispatchers.IO) {
        try {
            val url = "$exchangeRatesBaseUrl/$exchangeRateApiKey/history/$fromCurrency/$date"
            val response = makeApiCall(url)
            parseHistoricalExchangeRate(response, fromCurrency, toCurrency, date)
        } catch (e: Exception) {
            null
        }
    }
    
    suspend fun getCurrencyTrends(baseCurrency: String, targetCurrencies: List<String>, days: Int = 30): List<CurrencyTrend> = withContext(Dispatchers.IO) {
        val trends = mutableListOf<CurrencyTrend>()
        
        targetCurrencies.forEach { targetCurrency ->
            try {
                // Get historical rates for the past 30 days
                val historicalRates = mutableListOf<CurrencyRatePoint>()
                val calendar = Calendar.getInstance()
                
                repeat(days) { dayOffset ->
                    calendar.add(Calendar.DAY_OF_YEAR, -1)
                    val dateString = "${calendar.get(Calendar.YEAR)}-${String.format("%02d", calendar.get(Calendar.MONTH) + 1)}-${String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH))}"
                    
                    val rate = getHistoricalExchangeRate(baseCurrency, targetCurrency, dateString)
                    if (rate != null) {
                        historicalRates.add(
                            CurrencyRatePoint(
                                date = calendar.timeInMillis,
                                rate = rate.rate
                            )
                        )
                    }
                }
                
                if (historicalRates.isNotEmpty()) {
                    val currentRate = historicalRates.first().rate
                    val oldestRate = historicalRates.last().rate
                    val change = currentRate - oldestRate
                    val changePercent = (change / oldestRate) * 100
                    
                    trends.add(
                        CurrencyTrend(
                            baseCurrency = baseCurrency,
                            targetCurrency = targetCurrency,
                            currentRate = currentRate,
                            change = change,
                            changePercent = changePercent,
                            historicalRates = historicalRates.sortedBy { it.date },
                            periodDays = days,
                            lastUpdated = System.currentTimeMillis()
                        )
                    )
                }
            } catch (e: Exception) {
                // Skip this currency if we can't get data
            }
        }
        
        trends
    }
    
    fun formatCurrency(amount: Double, currencyCode: String, locale: Locale = Locale.getDefault()): String {
        return try {
            val format = NumberFormat.getCurrencyInstance(locale)
            val currency = Currency.getInstance(currencyCode)
            format.currency = currency
            format.format(amount)
        } catch (e: Exception) {
            "$currencyCode ${"%.2f".format(amount)}"
        }
    }
    
    fun getSupportedCurrencies(): List<SupportedCurrency> {
        return listOf(
            // Major currencies
            SupportedCurrency("USD", "US Dollar", "$", "United States"),
            SupportedCurrency("EUR", "Euro", "€", "European Union"),
            SupportedCurrency("GBP", "British Pound", "£", "United Kingdom"),
            SupportedCurrency("JPY", "Japanese Yen", "¥", "Japan"),
            SupportedCurrency("CHF", "Swiss Franc", "CHF", "Switzerland"),
            SupportedCurrency("CAD", "Canadian Dollar", "C$", "Canada"),
            SupportedCurrency("AUD", "Australian Dollar", "A$", "Australia"),
            SupportedCurrency("NZD", "New Zealand Dollar", "NZ$", "New Zealand"),
            SupportedCurrency("SEK", "Swedish Krona", "kr", "Sweden"),
            SupportedCurrency("NOK", "Norwegian Krone", "kr", "Norway"),
            SupportedCurrency("DKK", "Danish Krone", "kr", "Denmark"),
            
            // Asian currencies
            SupportedCurrency("CNY", "Chinese Yuan", "¥", "China"),
            SupportedCurrency("KRW", "South Korean Won", "₩", "South Korea"),
            SupportedCurrency("SGD", "Singapore Dollar", "S$", "Singapore"),
            SupportedCurrency("HKD", "Hong Kong Dollar", "HK$", "Hong Kong"),
            SupportedCurrency("INR", "Indian Rupee", "₹", "India"),
            SupportedCurrency("THB", "Thai Baht", "฿", "Thailand"),
            SupportedCurrency("PHP", "Philippine Peso", "₱", "Philippines"),
            SupportedCurrency("MYR", "Malaysian Ringgit", "RM", "Malaysia"),
            SupportedCurrency("IDR", "Indonesian Rupiah", "Rp", "Indonesia"),
            SupportedCurrency("VND", "Vietnamese Dong", "₫", "Vietnam"),
            
            // Latin American currencies
            SupportedCurrency("BRL", "Brazilian Real", "R$", "Brazil"),
            SupportedCurrency("MXN", "Mexican Peso", "$", "Mexico"),
            SupportedCurrency("ARS", "Argentine Peso", "$", "Argentina"),
            SupportedCurrency("CLP", "Chilean Peso", "$", "Chile"),
            SupportedCurrency("COP", "Colombian Peso", "$", "Colombia"),
            SupportedCurrency("PEN", "Peruvian Sol", "S/", "Peru"),
            
            // African currencies
            SupportedCurrency("ZAR", "South African Rand", "R", "South Africa"),
            SupportedCurrency("EGP", "Egyptian Pound", "£", "Egypt"),
            SupportedCurrency("MAD", "Moroccan Dirham", "DH", "Morocco"),
            SupportedCurrency("NGN", "Nigerian Naira", "₦", "Nigeria"),
            SupportedCurrency("KES", "Kenyan Shilling", "KSh", "Kenya"),
            
            // Middle Eastern currencies
            SupportedCurrency("AED", "UAE Dirham", "د.إ", "United Arab Emirates"),
            SupportedCurrency("SAR", "Saudi Riyal", "﷼", "Saudi Arabia"),
            SupportedCurrency("QAR", "Qatari Riyal", "﷼", "Qatar"),
            SupportedCurrency("KWD", "Kuwaiti Dinar", "د.ك", "Kuwait"),
            SupportedCurrency("BHD", "Bahraini Dinar", ".د.ب", "Bahrain"),
            SupportedCurrency("OMR", "Omani Rial", "﷼", "Oman"),
            SupportedCurrency("JOD", "Jordanian Dinar", "د.ا", "Jordan"),
            SupportedCurrency("LBP", "Lebanese Pound", "£", "Lebanon"),
            SupportedCurrency("ILS", "Israeli Shekel", "₪", "Israel"),
            SupportedCurrency("TRY", "Turkish Lira", "₺", "Turkey"),
            SupportedCurrency("IRR", "Iranian Rial", "﷼", "Iran"),
            
            // European currencies (non-Euro)
            SupportedCurrency("PLN", "Polish Zloty", "zł", "Poland"),
            SupportedCurrency("CZK", "Czech Koruna", "Kč", "Czech Republic"),
            SupportedCurrency("HUF", "Hungarian Forint", "Ft", "Hungary"),
            SupportedCurrency("RON", "Romanian Leu", "lei", "Romania"),
            SupportedCurrency("BGN", "Bulgarian Lev", "лв", "Bulgaria"),
            SupportedCurrency("HRK", "Croatian Kuna", "kn", "Croatia"),
            SupportedCurrency("RSD", "Serbian Dinar", "дин", "Serbia"),
            SupportedCurrency("UAH", "Ukrainian Hryvnia", "₴", "Ukraine"),
            SupportedCurrency("RUB", "Russian Ruble", "₽", "Russia"),
            SupportedCurrency("BYN", "Belarusian Ruble", "Br", "Belarus"),
            
            // Pacific currencies
            SupportedCurrency("FJD", "Fijian Dollar", "FJ$", "Fiji"),
            SupportedCurrency("TOP", "Tongan Paʻanga", "T$", "Tonga"),
            SupportedCurrency("WST", "Samoan Tala", "WS$", "Samoa"),
            SupportedCurrency("VUV", "Vanuatu Vatu", "VT", "Vanuatu"),
            SupportedCurrency("SBD", "Solomon Islands Dollar", "SI$", "Solomon Islands"),
            
            // Caribbean currencies
            SupportedCurrency("BBD", "Barbadian Dollar", "Bds$", "Barbados"),
            SupportedCurrency("BSD", "Bahamian Dollar", "B$", "Bahamas"),
            SupportedCurrency("BMD", "Bermudian Dollar", "BD$", "Bermuda"),
            SupportedCurrency("KYD", "Cayman Islands Dollar", "CI$", "Cayman Islands"),
            SupportedCurrency("JMD", "Jamaican Dollar", "J$", "Jamaica"),
            SupportedCurrency("TTD", "Trinidad and Tobago Dollar", "TT$", "Trinidad and Tobago"),
            
            // Cryptocurrencies (for reference)
            SupportedCurrency("BTC", "Bitcoin", "₿", "Cryptocurrency"),
            SupportedCurrency("ETH", "Ethereum", "Ξ", "Cryptocurrency"),
            SupportedCurrency("LTC", "Litecoin", "Ł", "Cryptocurrency"),
            SupportedCurrency("BCH", "Bitcoin Cash", "BCH", "Cryptocurrency"),
            SupportedCurrency("XRP", "Ripple", "XRP", "Cryptocurrency"),
            SupportedCurrency("ADA", "Cardano", "ADA", "Cryptocurrency"),
            SupportedCurrency("DOT", "Polkadot", "DOT", "Cryptocurrency"),
            SupportedCurrency("LINK", "Chainlink", "LINK", "Cryptocurrency"),
            SupportedCurrency("XLM", "Stellar", "XLM", "Cryptocurrency"),
            SupportedCurrency("USDT", "Tether", "USDT", "Cryptocurrency"),
            SupportedCurrency("USDC", "USD Coin", "USDC", "Cryptocurrency"),
            SupportedCurrency("BNB", "Binance Coin", "BNB", "Cryptocurrency")
        )
    }
    
    fun getCurrencyByCode(code: String): SupportedCurrency? {
        return getSupportedCurrencies().find { it.code == code }
    }
    
    fun getPopularCurrencies(): List<SupportedCurrency> {
        val popularCodes = listOf("USD", "EUR", "GBP", "JPY", "CHF", "CAD", "AUD", "SEK", "NOK", "DKK", "CNY", "INR")
        return getSupportedCurrencies().filter { it.code in popularCodes }
    }
    
    private fun makeApiCall(urlString: String): String {
        val url = URL(urlString)
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.connectTimeout = 10000
        connection.readTimeout = 10000
        
        return connection.inputStream.bufferedReader().use { it.readText() }
    }
    
    private fun parseExchangeRate(response: String, fromCurrency: String, toCurrency: String): ExchangeRate? {
        return try {
            val json = JSONObject(response)
            if (json.getString("result") == "success") {
                ExchangeRate(
                    fromCurrency = fromCurrency,
                    toCurrency = toCurrency,
                    rate = json.getDouble("conversion_rate"),
                    lastUpdated = json.getLong("time_last_update_unix") * 1000
                )
            } else null
        } catch (e: Exception) {
            null
        }
    }
    
    private fun parseAllExchangeRates(response: String): Map<String, Double> {
        return try {
            val json = JSONObject(response)
            if (json.getString("result") == "success") {
                val rates = json.getJSONObject("conversion_rates")
                val ratesMap = mutableMapOf<String, Double>()
                
                rates.keys().forEach { currency ->
                    ratesMap[currency] = rates.getDouble(currency)
                }
                
                ratesMap
            } else emptyMap()
        } catch (e: Exception) {
            emptyMap()
        }
    }
    
    private fun parseHistoricalExchangeRate(response: String, fromCurrency: String, toCurrency: String, date: String): ExchangeRate? {
        return try {
            val json = JSONObject(response)
            if (json.getString("result") == "success") {
                val rates = json.getJSONObject("conversion_rates")
                ExchangeRate(
                    fromCurrency = fromCurrency,
                    toCurrency = toCurrency,
                    rate = rates.getDouble(toCurrency),
                    lastUpdated = System.currentTimeMillis()
                )
            } else null
        } catch (e: Exception) {
            null
        }
    }
}

@Entity(tableName = "exchange_rates")
data class ExchangeRate(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val fromCurrency: String,
    val toCurrency: String,
    val rate: Double,
    val lastUpdated: Long
)

data class CurrencyConversion(
    val originalAmount: Double,
    val originalCurrency: String,
    val convertedAmount: Double,
    val targetCurrency: String,
    val exchangeRate: Double,
    val conversionDate: Long
)

data class SupportedCurrency(
    val code: String,
    val name: String,
    val symbol: String,
    val country: String
)

data class CurrencyRatePoint(
    val date: Long,
    val rate: Double
)

data class CurrencyTrend(
    val baseCurrency: String,
    val targetCurrency: String,
    val currentRate: Double,
    val change: Double,
    val changePercent: Double,
    val historicalRates: List<CurrencyRatePoint>,
    val periodDays: Int,
    val lastUpdated: Long
)

@Entity(tableName = "currency_alerts")
data class CurrencyAlert(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val fromCurrency: String,
    val toCurrency: String,
    val targetRate: Double,
    val alertType: CurrencyAlertType,
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis(),
    val triggeredAt: Long? = null
)

enum class CurrencyAlertType {
    ABOVE, BELOW, CHANGE_PERCENT
}

@Entity(tableName = "multi_currency_transactions")
data class MultiCurrencyTransaction(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val amount: Double,
    val currency: String,
    val convertedAmount: Double,
    val baseCurrency: String,
    val exchangeRate: Double,
    val category: String,
    val description: String,
    val transactionDate: Long = System.currentTimeMillis(),
    val createdAt: Long = System.currentTimeMillis()
)