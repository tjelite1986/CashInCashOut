package com.example.budgetapp.analytics.investments.crypto

import android.content.Context
import com.example.budgetapp.analytics.investments.marketdata.MarketDataService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class CryptoTrackingService(
    private val context: Context,
    private val marketDataService: MarketDataService
) {
    
    private val coinGeckoBaseUrl = "https://api.coingecko.com/api/v3"
    
    suspend fun getTopCryptocurrencies(limit: Int = 100): List<CryptoCurrency> = withContext(Dispatchers.IO) {
        try {
            val url = "$coinGeckoBaseUrl/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=$limit&page=1&sparkline=false"
            val response = makeApiCall(url)
            parseCryptocurrencies(response)
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    suspend fun searchCryptocurrencies(query: String): List<CryptoCurrency> = withContext(Dispatchers.IO) {
        try {
            val url = "$coinGeckoBaseUrl/search?query=$query"
            val response = makeApiCall(url)
            parseSearchResults(response)
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    suspend fun getCryptocurrencyDetails(id: String): CryptoCurrencyDetails? = withContext(Dispatchers.IO) {
        try {
            val url = "$coinGeckoBaseUrl/coins/$id?localization=false&tickers=false&market_data=true&community_data=true&developer_data=true"
            val response = makeApiCall(url)
            parseCryptocurrencyDetails(response)
        } catch (e: Exception) {
            null
        }
    }
    
    suspend fun getCryptocurrencyHistory(id: String, days: Int = 30): List<CryptoPricePoint> = withContext(Dispatchers.IO) {
        try {
            val url = "$coinGeckoBaseUrl/coins/$id/market_chart?vs_currency=usd&days=$days"
            val response = makeApiCall(url)
            parsePriceHistory(response)
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    suspend fun getPortfolioCryptoValue(cryptoHoldings: List<CryptoHolding>): CryptoPortfolioValue = withContext(Dispatchers.IO) {
        var totalValueUSD = 0.0
        var totalValueSEK = 0.0
        var total24hChange = 0.0
        val holdings = mutableListOf<CryptoHoldingValue>()
        
        cryptoHoldings.forEach { holding ->
            try {
                val cryptoPrice = marketDataService.getCryptoPrice(holding.cryptoId)
                if (cryptoPrice != null) {
                    val valueUSD = holding.quantity * cryptoPrice.currentPriceUSD
                    val valueSEK = holding.quantity * cryptoPrice.currentPriceSEK
                    val change24h = holding.quantity * cryptoPrice.currentPriceUSD * (cryptoPrice.change24h / 100)
                    
                    totalValueUSD += valueUSD
                    totalValueSEK += valueSEK
                    total24hChange += change24h
                    
                    holdings.add(
                        CryptoHoldingValue(
                            cryptoId = holding.cryptoId,
                            symbol = holding.symbol,
                            quantity = holding.quantity,
                            currentPriceUSD = cryptoPrice.currentPriceUSD,
                            currentPriceSEK = cryptoPrice.currentPriceSEK,
                            valueUSD = valueUSD,
                            valueSEK = valueSEK,
                            change24h = change24h,
                            changePercent24h = cryptoPrice.change24h
                        )
                    )
                }
            } catch (e: Exception) {
                // Skip this crypto if price fetch fails
            }
        }
        
        CryptoPortfolioValue(
            totalValueUSD = totalValueUSD,
            totalValueSEK = totalValueSEK,
            total24hChangeUSD = total24hChange,
            total24hChangePercent = if (totalValueUSD > 0) (total24hChange / (totalValueUSD - total24hChange)) * 100 else 0.0,
            holdings = holdings,
            lastUpdated = System.currentTimeMillis()
        )
    }
    
    suspend fun getMarketOverview(): CryptoMarketOverview = withContext(Dispatchers.IO) {
        try {
            val url = "$coinGeckoBaseUrl/global"
            val response = makeApiCall(url)
            parseMarketOverview(response)
        } catch (e: Exception) {
            CryptoMarketOverview(
                totalMarketCap = 0.0,
                totalVolume24h = 0.0,
                bitcoinDominance = 0.0,
                ethereumDominance = 0.0,
                marketCapChange24h = 0.0,
                activeCryptocurrencies = 0,
                lastUpdated = System.currentTimeMillis()
            )
        }
    }
    
    suspend fun getTrendingCryptocurrencies(): List<TrendingCrypto> = withContext(Dispatchers.IO) {
        try {
            val url = "$coinGeckoBaseUrl/search/trending"
            val response = makeApiCall(url)
            parseTrendingCryptos(response)
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    suspend fun getCryptoFearGreedIndex(): FearGreedIndex? = withContext(Dispatchers.IO) {
        try {
            // Alternative Fear & Greed Index API
            val url = "https://api.alternative.me/fng/"
            val response = makeApiCall(url)
            parseFearGreedIndex(response)
        } catch (e: Exception) {
            null
        }
    }
    
    suspend fun calculateCryptoTaxImplications(
        transactions: List<CryptoTransaction>,
        currentPrices: Map<String, Double>
    ): CryptoTaxReport = withContext(Dispatchers.IO) {
        var totalGains = 0.0
        var totalLosses = 0.0
        var shortTermGains = 0.0
        var longTermGains = 0.0
        val taxEvents = mutableListOf<CryptoTaxEvent>()
        
        val sellTransactions = transactions.filter { it.type == CryptoTransactionType.SELL }
        
        sellTransactions.forEach { sellTx ->
            val buyTx = transactions.find { 
                it.cryptoId == sellTx.cryptoId && 
                it.type == CryptoTransactionType.BUY && 
                it.timestamp < sellTx.timestamp 
            }
            
            if (buyTx != null) {
                val gainLoss = (sellTx.pricePerUnit - buyTx.pricePerUnit) * sellTx.quantity
                val holdingPeriod = sellTx.timestamp - buyTx.timestamp
                val isLongTerm = holdingPeriod > (365 * 24 * 60 * 60 * 1000) // > 1 year
                
                if (gainLoss > 0) {
                    totalGains += gainLoss
                    if (isLongTerm) longTermGains += gainLoss else shortTermGains += gainLoss
                } else {
                    totalLosses += kotlin.math.abs(gainLoss)
                }
                
                taxEvents.add(
                    CryptoTaxEvent(
                        cryptoId = sellTx.cryptoId,
                        symbol = sellTx.symbol,
                        quantity = sellTx.quantity,
                        buyPrice = buyTx.pricePerUnit,
                        sellPrice = sellTx.pricePerUnit,
                        gainLoss = gainLoss,
                        isLongTerm = isLongTerm,
                        buyDate = buyTx.timestamp,
                        sellDate = sellTx.timestamp
                    )
                )
            }
        }
        
        CryptoTaxReport(
            totalGains = totalGains,
            totalLosses = totalLosses,
            shortTermGains = shortTermGains,
            longTermGains = longTermGains,
            netGainLoss = totalGains - totalLosses,
            taxEvents = taxEvents,
            reportGenerated = System.currentTimeMillis()
        )
    }
    
    private fun makeApiCall(urlString: String): String {
        val url = URL(urlString)
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.connectTimeout = 10000
        connection.readTimeout = 10000
        
        return connection.inputStream.bufferedReader().use { it.readText() }
    }
    
    private fun parseCryptocurrencies(response: String): List<CryptoCurrency> {
        return try {
            val jsonArray = JSONArray(response)
            (0 until jsonArray.length()).map { i ->
                val crypto = jsonArray.getJSONObject(i)
                CryptoCurrency(
                    id = crypto.getString("id"),
                    symbol = crypto.getString("symbol").uppercase(),
                    name = crypto.getString("name"),
                    currentPrice = crypto.getDouble("current_price"),
                    marketCap = crypto.optDouble("market_cap", 0.0),
                    marketCapRank = crypto.optInt("market_cap_rank", 0),
                    volume24h = crypto.optDouble("total_volume", 0.0),
                    priceChange24h = crypto.optDouble("price_change_24h", 0.0),
                    priceChangePercent24h = crypto.optDouble("price_change_percentage_24h", 0.0),
                    circulatingSupply = crypto.optDouble("circulating_supply", 0.0),
                    totalSupply = crypto.optDouble("total_supply", 0.0),
                    maxSupply = crypto.optDouble("max_supply", 0.0),
                    ath = crypto.optDouble("ath", 0.0),
                    athDate = crypto.optString("ath_date", ""),
                    atl = crypto.optDouble("atl", 0.0),
                    atlDate = crypto.optString("atl_date", ""),
                    image = crypto.optString("image", ""),
                    lastUpdated = System.currentTimeMillis()
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    private fun parseSearchResults(response: String): List<CryptoCurrency> {
        return try {
            val json = JSONObject(response)
            val coins = json.getJSONArray("coins")
            
            (0 until coins.length()).take(20).map { i ->
                val coin = coins.getJSONObject(i)
                CryptoCurrency(
                    id = coin.getString("id"),
                    symbol = coin.getString("symbol").uppercase(),
                    name = coin.getString("name"),
                    currentPrice = 0.0, // Will be fetched separately
                    marketCap = 0.0,
                    marketCapRank = coin.optInt("market_cap_rank", 0),
                    volume24h = 0.0,
                    priceChange24h = 0.0,
                    priceChangePercent24h = 0.0,
                    circulatingSupply = 0.0,
                    totalSupply = 0.0,
                    maxSupply = 0.0,
                    ath = 0.0,
                    athDate = "",
                    atl = 0.0,
                    atlDate = "",
                    image = coin.optString("large", ""),
                    lastUpdated = System.currentTimeMillis()
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    private fun parseCryptocurrencyDetails(response: String): CryptoCurrencyDetails? {
        return try {
            val json = JSONObject(response)
            val marketData = json.getJSONObject("market_data")
            
            CryptoCurrencyDetails(
                id = json.getString("id"),
                symbol = json.getString("symbol").uppercase(),
                name = json.getString("name"),
                description = json.getJSONObject("description").optString("en", ""),
                homepage = json.getJSONObject("links").getJSONArray("homepage").optString(0, ""),
                blockchainSite = json.getJSONObject("links").getJSONArray("blockchain_site").optString(0, ""),
                currentPrice = marketData.getJSONObject("current_price").optDouble("usd", 0.0),
                marketCap = marketData.getJSONObject("market_cap").optDouble("usd", 0.0),
                totalVolume = marketData.getJSONObject("total_volume").optDouble("usd", 0.0),
                high24h = marketData.getJSONObject("high_24h").optDouble("usd", 0.0),
                low24h = marketData.getJSONObject("low_24h").optDouble("usd", 0.0),
                priceChange24h = marketData.optDouble("price_change_24h", 0.0),
                priceChangePercent24h = marketData.optDouble("price_change_percentage_24h", 0.0),
                priceChangePercent7d = marketData.optDouble("price_change_percentage_7d", 0.0),
                priceChangePercent30d = marketData.optDouble("price_change_percentage_30d", 0.0),
                circulatingSupply = marketData.optDouble("circulating_supply", 0.0),
                totalSupply = marketData.optDouble("total_supply", 0.0),
                maxSupply = marketData.optDouble("max_supply", 0.0),
                ath = marketData.getJSONObject("ath").optDouble("usd", 0.0),
                atl = marketData.getJSONObject("atl").optDouble("usd", 0.0),
                image = json.getJSONObject("image").optString("large", ""),
                lastUpdated = System.currentTimeMillis()
            )
        } catch (e: Exception) {
            null
        }
    }
    
    private fun parsePriceHistory(response: String): List<CryptoPricePoint> {
        return try {
            val json = JSONObject(response)
            val prices = json.getJSONArray("prices")
            
            (0 until prices.length()).map { i ->
                val priceData = prices.getJSONArray(i)
                CryptoPricePoint(
                    timestamp = priceData.getLong(0),
                    price = priceData.getDouble(1)
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    private fun parseMarketOverview(response: String): CryptoMarketOverview {
        return try {
            val json = JSONObject(response)
            val data = json.getJSONObject("data")
            val marketCapPercentage = data.getJSONObject("market_cap_percentage")
            
            CryptoMarketOverview(
                totalMarketCap = data.getJSONObject("total_market_cap").optDouble("usd", 0.0),
                totalVolume24h = data.getJSONObject("total_volume").optDouble("usd", 0.0),
                bitcoinDominance = marketCapPercentage.optDouble("btc", 0.0),
                ethereumDominance = marketCapPercentage.optDouble("eth", 0.0),
                marketCapChange24h = data.optDouble("market_cap_change_percentage_24h_usd", 0.0),
                activeCryptocurrencies = data.optInt("active_cryptocurrencies", 0),
                lastUpdated = System.currentTimeMillis()
            )
        } catch (e: Exception) {
            CryptoMarketOverview(0.0, 0.0, 0.0, 0.0, 0.0, 0, System.currentTimeMillis())
        }
    }
    
    private fun parseTrendingCryptos(response: String): List<TrendingCrypto> {
        return try {
            val json = JSONObject(response)
            val coins = json.getJSONArray("coins")
            
            (0 until coins.length()).map { i ->
                val coinData = coins.getJSONObject(i)
                val coin = coinData.getJSONObject("item")
                
                TrendingCrypto(
                    id = coin.getString("id"),
                    symbol = coin.getString("symbol"),
                    name = coin.getString("name"),
                    marketCapRank = coin.optInt("market_cap_rank", 0),
                    image = coin.optString("large", ""),
                    score = coin.optInt("score", 0)
                )
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    private fun parseFearGreedIndex(response: String): FearGreedIndex? {
        return try {
            val json = JSONObject(response)
            val data = json.getJSONArray("data").getJSONObject(0)
            
            FearGreedIndex(
                value = data.getInt("value"),
                valueClassification = data.getString("value_classification"),
                timestamp = data.getLong("timestamp"),
                timeUntilUpdate = data.optString("time_until_update", "")
            )
        } catch (e: Exception) {
            null
        }
    }
}

data class CryptoCurrency(
    val id: String,
    val symbol: String,
    val name: String,
    val currentPrice: Double,
    val marketCap: Double,
    val marketCapRank: Int,
    val volume24h: Double,
    val priceChange24h: Double,
    val priceChangePercent24h: Double,
    val circulatingSupply: Double,
    val totalSupply: Double,
    val maxSupply: Double,
    val ath: Double,
    val athDate: String,
    val atl: Double,
    val atlDate: String,
    val image: String,
    val lastUpdated: Long
)

data class CryptoCurrencyDetails(
    val id: String,
    val symbol: String,
    val name: String,
    val description: String,
    val homepage: String,
    val blockchainSite: String,
    val currentPrice: Double,
    val marketCap: Double,
    val totalVolume: Double,
    val high24h: Double,
    val low24h: Double,
    val priceChange24h: Double,
    val priceChangePercent24h: Double,
    val priceChangePercent7d: Double,
    val priceChangePercent30d: Double,
    val circulatingSupply: Double,
    val totalSupply: Double,
    val maxSupply: Double,
    val ath: Double,
    val atl: Double,
    val image: String,
    val lastUpdated: Long
)

data class CryptoPricePoint(
    val timestamp: Long,
    val price: Double
)

data class CryptoHolding(
    val cryptoId: String,
    val symbol: String,
    val quantity: Double
)

data class CryptoHoldingValue(
    val cryptoId: String,
    val symbol: String,
    val quantity: Double,
    val currentPriceUSD: Double,
    val currentPriceSEK: Double,
    val valueUSD: Double,
    val valueSEK: Double,
    val change24h: Double,
    val changePercent24h: Double
)

data class CryptoPortfolioValue(
    val totalValueUSD: Double,
    val totalValueSEK: Double,
    val total24hChangeUSD: Double,
    val total24hChangePercent: Double,
    val holdings: List<CryptoHoldingValue>,
    val lastUpdated: Long
)

data class CryptoMarketOverview(
    val totalMarketCap: Double,
    val totalVolume24h: Double,
    val bitcoinDominance: Double,
    val ethereumDominance: Double,
    val marketCapChange24h: Double,
    val activeCryptocurrencies: Int,
    val lastUpdated: Long
)

data class TrendingCrypto(
    val id: String,
    val symbol: String,
    val name: String,
    val marketCapRank: Int,
    val image: String,
    val score: Int
)

data class FearGreedIndex(
    val value: Int,
    val valueClassification: String,
    val timestamp: Long,
    val timeUntilUpdate: String
)

data class CryptoTransaction(
    val id: Long = 0,
    val cryptoId: String,
    val symbol: String,
    val type: CryptoTransactionType,
    val quantity: Double,
    val pricePerUnit: Double,
    val totalAmount: Double,
    val fees: Double = 0.0,
    val timestamp: Long = System.currentTimeMillis()
)

enum class CryptoTransactionType {
    BUY, SELL, TRANSFER_IN, TRANSFER_OUT, MINING, STAKING_REWARD, AIRDROP
}

data class CryptoTaxEvent(
    val cryptoId: String,
    val symbol: String,
    val quantity: Double,
    val buyPrice: Double,
    val sellPrice: Double,
    val gainLoss: Double,
    val isLongTerm: Boolean,
    val buyDate: Long,
    val sellDate: Long
)

data class CryptoTaxReport(
    val totalGains: Double,
    val totalLosses: Double,
    val shortTermGains: Double,
    val longTermGains: Double,
    val netGainLoss: Double,
    val taxEvents: List<CryptoTaxEvent>,
    val reportGenerated: Long
)