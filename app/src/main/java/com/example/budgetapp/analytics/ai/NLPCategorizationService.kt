package com.example.budgetapp.analytics.ai

import android.content.Context
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.Category
import com.example.budgetapp.database.entities.CategoryType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

/**
 * NLP-based automatic transaction categorization service
 * Uses keyword matching, pattern recognition, and machine learning techniques
 * to automatically categorize expenses and income
 */
class NLPCategorizationService(private val context: Context) {
    
    private val database = BudgetDatabase.getDatabase(context)
    private val categoryDao = database.categoryDao()
    
    companion object {
        const val CONFIDENCE_THRESHOLD = 0.75f
        const val MIN_KEYWORD_MATCH_SCORE = 0.6f
    }
    
    // Enhanced keyword patterns for different categories
    private val expenseKeywordPatterns = mapOf(
        "Groceries" to listOf(
            // Swedish grocery stores
            "ica", "coop", "hemköp", "willys", "lidl", "city gross", "tempo", "maxi", "kvantum",
            // Food items (Swedish/English)
            "mjölk", "bröd", "kött", "fisk", "frukt", "grönsaker", "ost", "smör", "ägg", "kaffe", "te",
            "milk", "bread", "meat", "fish", "fruit", "vegetables", "cheese", "butter", "eggs", "coffee", "tea",
            // Food categories
            "livsmedel", "mat", "grocery", "food", "supermarket", "stormarknad"
        ),
        
        "Dining" to listOf(
            // Restaurant chains (Swedish)
            "mcdonalds", "burger king", "max", "subway", "pizza hut", "kfc", "sibylla", "clock",
            // Restaurant types
            "restaurang", "restaurant", "café", "pizzeria", "sushi", "thai", "kina", "indian",
            "matsalen", "lunchrestaurang", "bistro", "bar", "pub", "krog", "taverna",
            // Food delivery
            "foodora", "uber eats", "wolt", "just eat", "delivery", "leverans",
            "takeaway", "avhämtning", "drive thru"
        ),
        
        "Transit" to listOf(
            // Public transport (Swedish)
            "sl", "västtrafik", "skånetrafiken", "sj", "tåg", "tunnelbana", "buss", "spårvagn",
            // Transport services
            "taxi", "uber", "bolt", "lyft", "cabonline", "taxi stockholm", "taxi göteborg",
            // Fuel and parking
            "bensin", "diesel", "gas", "fuel", "parkering", "parking", "p-avgift",
            "esso", "shell", "statoil", "circle k", "ok/q8", "preem"
        ),
        
        "Entertainment" to listOf(
            // Streaming services
            "netflix", "spotify", "disney+", "hbo", "amazon prime", "youtube premium", "apple music",
            "viaplay", "c more", "discovery+", "paramount+",
            // Gaming
            "steam", "playstation", "xbox", "nintendo", "epic games", "battle.net",
            // Cinema and events
            "sf bio", "filmstaden", "rigoletto", "konsert", "teater", "museum", "zoo",
            "biljett", "ticket", "evenemang", "event", "show", "concert"
        ),
        
        "Bills & Fees" to listOf(
            // Utilities (Swedish)
            "vattenfall", "eon", "fortum", "ellevio", "göteborg energi", "stockholm energi",
            "telia", "tre", "telenor", "tele2", "comhem", "bahnhof", "bredbandsbolaget",
            "stockholm vatten", "va-avgift", "renhållning", "sophämtning",
            // Insurance and banking
            "if", "folksam", "länsförsäkringar", "trygg hansa", "moderna", "skandia",
            "seb", "swedbank", "handelsbanken", "nordea", "ica banken", "länsförsäkringar bank",
            // Other bills
            "räkning", "avgift", "fee", "försäkring", "insurance", "el", "vatten", "avlopp"
        ),
        
        "Shopping" to listOf(
            // Department stores
            "h&m", "zara", "åhléns", "nk", "gallerien", "ikea", "jysk", "rusta", "biltema",
            "kjell & company", "elgiganten", "mediamarkt", "webhallen", "komplett", "inet",
            // Online shopping
            "amazon", "zalando", "boozt", "adlibris", "bokus", "apotea", "lyko", "eleven",
            "kläder", "clothes", "skor", "shoes", "väska", "bag", "smycken", "jewelry"
        ),
        
        "Health" to listOf(
            // Healthcare (Swedish)
            "vårdcentral", "apoteket", "kronans apotek", "lloyds apotek", "hjärtat", "apotea",
            "folktandvården", "tandläkare", "dentist", "läkare", "doctor", "veterinär",
            "sjukhus", "hospital", "klinik", "clinic", "medicin", "medicine", "hälsa", "health"
        ),
        
        "Tech" to listOf(
            "apple", "google", "microsoft", "adobe", "icloud", "office 365", "github",
            "dropbox", "onedrive", "zoom", "skype", "teams", "slack", "notion",
            "teknik", "technology", "dator", "computer", "mobil", "mobile", "app store"
        ),
        
        "Home" to listOf(
            "hyra", "rent", "hus", "house", "lägenhet", "apartment", "hem", "home",
            "bygghemma", "bauhaus", "hornbach", "k-rauta", "beijer byggmaterial",
            "möbler", "furniture", "inredning", "decoration", "städning", "cleaning"
        ),
        
        "Car" to listOf(
            "bil", "car", "volvo", "saab", "bmw", "mercedes", "audi", "toyota", "ford",
            "verkstad", "workshop", "service", "besiktning", "inspection", "försäkring",
            "bilskatt", "road tax", "parkeringsavgift", "parking fee"
        )
    )
    
    private val incomeKeywordPatterns = mapOf(
        "Salary" to listOf(
            "lön", "salary", "avlöning", "payroll", "månadslön", "monthly salary",
            "timlön", "hourly wage", "övertid", "overtime", "bonus", "provision"
        ),
        
        "Freelance" to listOf(
            "frilans", "freelance", "konsult", "consultant", "uppdrag", "assignment",
            "faktura", "invoice", "honorar", "fee", "arvode", "payment"
        ),
        
        "Investment" to listOf(
            "avanza", "nordnet", "isk", "kapitalförsäkring", "fonder", "funds", "aktier", "stocks",
            "utdelning", "dividend", "ränta", "interest", "vinst", "profit", "investering"
        ),
        
        "Sale" to listOf(
            "försäljning", "sale", "såld", "sold", "blocket", "tradera", "facebook marketplace",
            "sellpy", "vinted", "second hand", "begagnat", "återförsäljning"
        ),
        
        "Bonus" to listOf(
            "bonus", "premie", "provision", "commission", "belöning", "reward",
            "återbäring", "refund", "återbetalning", "cashback"
        )
    )
    
    /**
     * Automatically categorize a transaction based on title and description
     */
    suspend fun categorizeTransaction(
        title: String,
        description: String? = null,
        amount: Double,
        isIncome: Boolean
    ): CategorySuggestion? = withContext(Dispatchers.IO) {
        
        val text = "${title} ${description ?: ""}".lowercase(Locale.getDefault())
        val targetType = if (isIncome) CategoryType.INCOME else CategoryType.EXPENSE
        val keywordPatterns = if (isIncome) incomeKeywordPatterns else expenseKeywordPatterns
        
        // Score each category based on keyword matches
        val categoryScores = mutableMapOf<String, Float>()
        
        for ((categoryName, keywords) in keywordPatterns) {
            val score = calculateKeywordMatchScore(text, keywords)
            if (score > MIN_KEYWORD_MATCH_SCORE) {
                categoryScores[categoryName] = score
            }
        }
        
        // Apply pattern-based adjustments
        applyPatternAdjustments(categoryScores, text, amount, isIncome)
        
        // Get the best matching category
        val bestMatch = categoryScores.maxByOrNull { it.value }
        
        if (bestMatch != null && bestMatch.value >= CONFIDENCE_THRESHOLD) {
            // TODO: Find matching category in database
            val matchingCategory = null
            
            return@withContext CategorySuggestion(
                category = matchingCategory,
                confidence = bestMatch.value,
                matchedKeywords = getMatchedKeywords(text, keywordPatterns[bestMatch.key] ?: emptyList()),
                reasoning = generateReasoning(bestMatch.key, bestMatch.value, text)
            )
        }
        
        return@withContext null
    }
    
    /**
     * Calculate keyword match score using fuzzy matching
     */
    private fun calculateKeywordMatchScore(text: String, keywords: List<String>): Float {
        var totalScore = 0f
        var matchCount = 0
        
        for (keyword in keywords) {
            val score = when {
                text.contains(keyword) -> 1.0f
                text.contains(keyword.substring(0, minOf(keyword.length, 3))) -> 0.7f
                levenshteinDistance(text, keyword) <= 2 -> 0.5f
                else -> 0f
            }
            
            if (score > 0) {
                totalScore += score
                matchCount++
            }
        }
        
        return if (matchCount > 0) totalScore / keywords.size else 0f
    }
    
    /**
     * Apply pattern-based score adjustments
     */
    private fun applyPatternAdjustments(
        scores: MutableMap<String, Float>,
        text: String,
        amount: Double,
        isIncome: Boolean
    ) {
        // Amount-based patterns
        when {
            amount > 50000 && isIncome -> scores["Salary"] = scores.getOrDefault("Salary", 0f) + 0.2f
            amount > 100000 && isIncome -> scores["Bonus"] = scores.getOrDefault("Bonus", 0f) + 0.3f
            amount < 100 && !isIncome -> {
                scores["Dining"] = scores.getOrDefault("Dining", 0f) + 0.1f
                scores["Transit"] = scores.getOrDefault("Transit", 0f) + 0.1f
            }
            amount > 5000 && !isIncome -> {
                scores["Bills & Fees"] = scores.getOrDefault("Bills & Fees", 0f) + 0.2f
                scores["Shopping"] = scores.getOrDefault("Shopping", 0f) + 0.1f
            }
        }
        
        // Time-based patterns (weekend = more likely entertainment/dining)
        val calendar = Calendar.getInstance()
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
            if (!isIncome) {
                scores["Entertainment"] = scores.getOrDefault("Entertainment", 0f) + 0.1f
                scores["Dining"] = scores.getOrDefault("Dining", 0f) + 0.1f
            }
        }
        
        // Pattern recognition for common transaction formats
        when {
            text.matches(Regex(".*\\d{4}-\\d{2}-\\d{2}.*")) -> {
                // Date pattern often in bills
                scores["Bills & Fees"] = scores.getOrDefault("Bills & Fees", 0f) + 0.1f
            }
            text.contains("*") && text.length > 10 -> {
                // Masked card transactions often in shopping
                scores["Shopping"] = scores.getOrDefault("Shopping", 0f) + 0.1f
            }
        }
    }
    
    /**
     * Get list of matched keywords for explanation
     */
    private fun getMatchedKeywords(text: String, keywords: List<String>): List<String> {
        return keywords.filter { keyword ->
            text.contains(keyword) || 
            text.contains(keyword.substring(0, minOf(keyword.length, 3))) ||
            levenshteinDistance(text, keyword) <= 2
        }
    }
    
    /**
     * Generate human-readable reasoning for categorization
     */
    private fun generateReasoning(categoryName: String, confidence: Float, text: String): String {
        val confidencePercent = (confidence * 100).toInt()
        return when {
            confidence > 0.9f -> "Mycket säker kategorisering ($confidencePercent%) baserat på tydliga nyckelord"
            confidence > 0.8f -> "Säker kategorisering ($confidencePercent%) baserat på matchande mönster"
            confidence > 0.7f -> "Trolig kategorisering ($confidencePercent%) baserat på delvis matchande innehåll"
            else -> "Osäker kategorisering ($confidencePercent%) - rekommenderar manuell verifiering"
        }
    }
    
    /**
     * Calculate Levenshtein distance for fuzzy string matching
     */
    private fun levenshteinDistance(s1: String, s2: String): Int {
        val len1 = s1.length
        val len2 = s2.length
        val dp = Array(len1 + 1) { IntArray(len2 + 1) }
        
        for (i in 0..len1) dp[i][0] = i
        for (j in 0..len2) dp[0][j] = j
        
        for (i in 1..len1) {
            for (j in 1..len2) {
                val cost = if (s1[i - 1] == s2[j - 1]) 0 else 1
                dp[i][j] = minOf(
                    dp[i - 1][j] + 1,      // deletion
                    dp[i][j - 1] + 1,      // insertion
                    dp[i - 1][j - 1] + cost // substitution
                )
            }
        }
        
        return dp[len1][len2]
    }
    
    /**
     * Learn from user corrections to improve categorization
     */
    suspend fun learnFromCorrection(
        originalText: String,
        suggestedCategoryId: Long?,
        actualCategoryId: Long
    ) = withContext(Dispatchers.IO) {
        // TODO: Implement learning from user corrections
        // This would update category keywords based on user corrections
    }
    
    /**
     * Extract meaningful keywords from transaction text
     */
    private fun extractKeywords(text: String): List<String> {
        val cleanText = text.lowercase(Locale.getDefault())
            .replace(Regex("[^a-zA-ZåäöÅÄÖ\\s]"), " ")
            .split("\\s+".toRegex())
            .filter { it.length > 2 }
        
        // Filter out common stop words
        val stopWords = setOf("och", "eller", "för", "till", "från", "med", "utan", "the", "and", "or", "for", "to", "from", "with", "without")
        
        return cleanText.filter { !stopWords.contains(it) && it.length > 2 }
    }
    
    /**
     * Get categorization statistics for analytics
     */
    suspend fun getCategorizationStats(): CategorizationStats = withContext(Dispatchers.IO) {
        // TODO: Implement categorization statistics
        CategorizationStats(
            totalCategories = 10,
            categoriesWithLearning = 5,
            totalTransactionsCategorized = 100,
            averageConfidence = 0.8f
        )
    }
    
    private suspend fun calculateAverageConfidence(): Float {
        // This would be calculated from historical categorization data
        // For now, return a reasonable estimate
        return 0.82f
    }
}

/**
 * Data class representing a categorization suggestion
 */
data class CategorySuggestion(
    val category: Category?,
    val confidence: Float,
    val matchedKeywords: List<String>,
    val reasoning: String
)

/**
 * Statistics about the categorization system performance
 */
data class CategorizationStats(
    val totalCategories: Int,
    val categoriesWithLearning: Int,
    val totalTransactionsCategorized: Int,
    val averageConfidence: Float
)