package com.example.budgetapp.analytics.investments.marketdata

import android.content.Context
import com.example.budgetapp.analytics.investments.entities.Investment
import com.example.budgetapp.analytics.investments.entities.InvestmentPriceHistory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class MarketDataService(private val context: Context) {
    
    private val alphaVantageApiKey = "demo" // Replace with actual API key
    private val finnhubApiKey = "demo" // Replace with actual API key
    private val coinGeckoBaseUrl = "https://api.coingecko.com/api/v3"
    
    suspend fun getStockPrice(symbol: String): StockPrice? = withContext(Dispatchers.IO) {
        try {
            // Using Alpha Vantage API for stock prices
            val url = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=$symbol&apikey=$alphaVantageApiKey"
            val response = makeApiCall(url)
            parseStockPrice(response, symbol)
        } catch (e: Exception) {
            null
        }
    }
    
    suspend fun getCryptoPrice(cryptoId: String): CryptoPrice? = withContext(Dispatchers.IO) {
        try {
            // Using CoinGecko API for crypto prices
            val url = "$coinGeckoBaseUrl/simple/price?ids=$cryptoId&vs_currencies=usd,eur,sek&include_24hr_change=true"
            val response = makeApiCall(url)
            parseCryptoPrice(response, cryptoId)
        } catch (e: Exception) {
            null
        }
    }
    
    suspend fun getExchangeRate(fromCurrency: String, toCurrency: String): ExchangeRate? = withContext(Dispatchers.IO) {
        try {
            // Using Alpha Vantage FX API
            val url = "https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=$fromCurrency&to_currency=$toCurrency&apikey=$alphaVantageApiKey"
            val response = makeApiCall(url)
            parseExchangeRate(response, fromCurrency, toCurrency)
        } catch (e: Exception) {
            null
        }
    }
    
    suspend fun getMarketIndices(): List<MarketIndex> = withContext(Dispatchers.IO) {
        try {
            val indices = listOf(
                "SPY", // S&P 500
                "VTI", // Total Stock Market
                "EFA", // EAFE
                "BND", // Total Bond Market
                "VNQ"  // REITs
            )
            
            indices.mapNotNull { symbol ->
                getStockPrice(symbol)?.let { price ->
                    MarketIndex(
                        symbol = symbol,
                        name = getIndexName(symbol),
                        price = price.currentPrice,
                        change = price.changePercent,
                        currency = price.currency
                    )
                }
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    suspend fun getStockQuote(symbol: String): StockQuote? = withContext(Dispatchers.IO) {
        try {
            // Enhanced quote with more details using Finnhub
            val url = "https://finnhub.io/api/v1/quote?symbol=$symbol&token=$finnhubApiKey"
            val response = makeApiCall(url)
            parseStockQuote(response, symbol)
        } catch (e: Exception) {
            null
        }
    }
    
    suspend fun searchSymbols(query: String): List<SecuritySearchResult> = withContext(Dispatchers.IO) {
        try {
            // Search for stocks using Finnhub
            val url = "https://finnhub.io/api/v1/search?q=$query&token=$finnhubApiKey"
            val response = makeApiCall(url)
            parseSearchResults(response)
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    suspend fun getCompanyProfile(symbol: String): CompanyProfile? = withContext(Dispatchers.IO) {
        try {
            val url = "https://finnhub.io/api/v1/stock/profile2?symbol=$symbol&token=$finnhubApiKey"
            val response = makeApiCall(url)
            parseCompanyProfile(response, symbol)
        } catch (e: Exception) {
            null
        }
    }
    
    private fun makeApiCall(urlString: String): String {
        val url = URL(urlString)
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.connectTimeout = 10000
        connection.readTimeout = 10000
        
        return connection.inputStream.bufferedReader().use { it.readText() }
    }
    
    private fun parseStockPrice(response: String, symbol: String): StockPrice? {
        return try {
            val json = JSONObject(response)
            val quote = json.getJSONObject("Global Quote")
            
            StockPrice(
                symbol = symbol,
                currentPrice = quote.getString("05. price").toDouble(),
                previousClose = quote.getString("08. previous close").toDouble(),
                change = quote.getString("09. change").toDouble(),
                changePercent = quote.getString("10. change percent").replace("%", "").toDouble(),
                currency = "USD", // Default for most stocks
                lastUpdated = System.currentTimeMillis()
            )
        } catch (e: Exception) {
            null
        }
    }
    
    private fun parseCryptoPrice(response: String, cryptoId: String): CryptoPrice? {
        return try {
            val json = JSONObject(response)
            val crypto = json.getJSONObject(cryptoId)
            
            CryptoPrice(
                id = cryptoId,
                currentPriceUSD = crypto.getDouble("usd"),
                currentPriceEUR = crypto.getDouble("eur"),
                currentPriceSEK = crypto.getDouble("sek"),
                change24h = crypto.getDouble("usd_24h_change"),
                lastUpdated = System.currentTimeMillis()
            )
        } catch (e: Exception) {
            null
        }
    }
    
    private fun parseExchangeRate(response: String, fromCurrency: String, toCurrency: String): ExchangeRate? {
        return try {
            val json = JSONObject(response)
            val realtimeData = json.getJSONObject("Realtime Currency Exchange Rate")
            
            ExchangeRate(
                fromCurrency = fromCurrency,
                toCurrency = toCurrency,
                rate = realtimeData.getString("5. Exchange Rate").toDouble(),
                lastUpdated = System.currentTimeMillis()
            )
        } catch (e: Exception) {
            null
        }
    }
    
    private fun parseStockQuote(response: String, symbol: String): StockQuote? {
        return try {
            val json = JSONObject(response)
            
            StockQuote(
                symbol = symbol,
                currentPrice = json.getDouble("c"),
                openPrice = json.getDouble("o"),
                highPrice = json.getDouble("h"),
                lowPrice = json.getDouble("l"),
                previousClose = json.getDouble("pc"),
                change = json.getDouble("d"),
                changePercent = json.getDouble("dp"),
                lastUpdated = System.currentTimeMillis()
            )
        } catch (e: Exception) {
            null
        }
    }
    
    private fun parseSearchResults(response: String): List<SecuritySearchResult> {
        return try {
            val json = JSONObject(response)
            val results = json.getJSONArray("result")
            
            (0 until results.length()).map { i ->
                val result = results.getJSONObject(i)
                SecuritySearchResult(
                    symbol = result.getString("symbol"),
                    description = result.getString("description"),
                    type = result.getString("type"),
                    displaySymbol = result.getString("displaySymbol")
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    private fun parseCompanyProfile(response: String, symbol: String): CompanyProfile? {
        return try {
            val json = JSONObject(response)
            
            CompanyProfile(
                symbol = symbol,
                name = json.getString("name"),
                country = json.getString("country"),
                currency = json.getString("currency"),
                exchange = json.getString("exchange"),
                ipo = json.getString("ipo"),
                marketCapitalization = json.getDouble("marketCapitalization"),
                shareOutstanding = json.getDouble("shareOutstanding"),
                logo = json.getString("logo"),
                phone = json.getString("phone"),
                weburl = json.getString("weburl"),
                industry = json.getString("finnhubIndustry")
            )
        } catch (e: Exception) {
            null
        }
    }
    
    private fun getIndexName(symbol: String): String = when (symbol) {
        "SPY" -> "S&P 500"
        "VTI" -> "Total Stock Market"
        "EFA" -> "EAFE"
        "BND" -> "Total Bond Market"
        "VNQ" -> "REITs"
        else -> symbol
    }
}

data class StockPrice(
    val symbol: String,
    val currentPrice: Double,
    val previousClose: Double,
    val change: Double,
    val changePercent: Double,
    val currency: String,
    val lastUpdated: Long
)

data class CryptoPrice(
    val id: String,
    val currentPriceUSD: Double,
    val currentPriceEUR: Double,
    val currentPriceSEK: Double,
    val change24h: Double,
    val lastUpdated: Long
)

data class ExchangeRate(
    val fromCurrency: String,
    val toCurrency: String,
    val rate: Double,
    val lastUpdated: Long
)

data class StockQuote(
    val symbol: String,
    val currentPrice: Double,
    val openPrice: Double,
    val highPrice: Double,
    val lowPrice: Double,
    val previousClose: Double,
    val change: Double,
    val changePercent: Double,
    val lastUpdated: Long
)

data class MarketIndex(
    val symbol: String,
    val name: String,
    val price: Double,
    val change: Double,
    val currency: String
)

data class SecuritySearchResult(
    val symbol: String,
    val description: String,
    val type: String,
    val displaySymbol: String
)

data class CompanyProfile(
    val symbol: String,
    val name: String,
    val country: String,
    val currency: String,
    val exchange: String,
    val ipo: String,
    val marketCapitalization: Double,
    val shareOutstanding: Double,
    val logo: String,
    val phone: String,
    val weburl: String,
    val industry: String
)