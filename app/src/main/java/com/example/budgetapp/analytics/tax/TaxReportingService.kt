package com.example.budgetapp.analytics.tax

import android.content.Context
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.*
import com.example.budgetapp.analytics.investments.entities.Investment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

/**
 * Service for automatic tax category reporting and deduction identification
 * Helps users categorize expenses for Swedish tax purposes
 */
class TaxReportingService(private val context: Context) {
    
    private val database = BudgetDatabase.getDatabase(context)
    private val expenseDao = database.expenseDao()
    private val incomeDao = database.incomeDao()
    private val investmentDao = database.investmentDao()
    
    companion object {
        const val TAX_YEAR_START_MONTH = 1 // January
        const val TAX_YEAR_START_DAY = 1
        const val TAX_YEAR_END_MONTH = 12 // December
        const val TAX_YEAR_END_DAY = 31
    }
    
    // Swedish tax categories for deductions
    private val taxDeductibleCategories = mapOf(
        // Household services (RUT)
        TaxCategory.RUT_SERVICES to listOf(
            "städning", "cleaning", "trädgård", "garden", "snöröjning", "snow removal",
            "barnpassning", "childcare", "matlagning", "cooking", "tvätt", "laundry",
            "strykning", "ironing", "hemservice", "home service", "fönsterputs", "window cleaning"
        ),
        
        // Home renovation/construction (ROT)
        TaxCategory.ROT_SERVICES to listOf(
            "renovering", "renovation", "målning", "painting", "golv", "flooring",
            "badrum", "bathroom", "kök", "kitchen", "tak", "roof", "fasad", "facade",
            "el", "electrical", "vvs", "plumbing", "byggnad", "construction",
            "isolering", "insulation", "fönster", "windows", "dörr", "doors"
        ),
        
        // Medical expenses
        TaxCategory.MEDICAL_EXPENSES to listOf(
            "läkare", "doctor", "tandläkare", "dentist", "medicin", "medicine",
            "apotek", "pharmacy", "sjukvård", "healthcare", "behandling", "treatment",
            "operation", "surgery", "fysioterapi", "physiotherapy", "psykolog", "psychologist",
            "sjukgymnast", "physical therapy", "glasögon", "glasses", "hörapparat", "hearing aid"
        ),
        
        // Travel expenses (work-related)
        TaxCategory.TRAVEL_EXPENSES to listOf(
            "resa", "travel", "hotell", "hotel", "flyg", "flight", "tåg", "train",
            "taxi", "buss", "bus", "hyrbil", "rental car", "bensin", "gas", "diesel",
            "parkering", "parking", "bro", "bridge", "vägtull", "toll", "milersättning", "mileage"
        ),
        
        // Work-related equipment
        TaxCategory.WORK_EQUIPMENT to listOf(
            "dator", "computer", "skrivare", "printer", "telefon", "phone", "mobil", "mobile",
            "programvara", "software", "kontorsmaterial", "office supplies", "skrivbord", "desk",
            "stol", "chair", "kontor", "office", "arbetskläder", "work clothes",
            "verktyg", "tools", "utrustning", "equipment"
        ),
        
        // Professional development
        TaxCategory.PROFESSIONAL_DEVELOPMENT to listOf(
            "utbildning", "education", "kurs", "course", "konferens", "conference",
            "seminarium", "seminar", "bok", "book", "tidning", "magazine", "facklitteratur",
            "professional literature", "certifiering", "certification", "licens", "license"
        ),
        
        // Charitable donations
        TaxCategory.CHARITABLE_DONATIONS to listOf(
            "donation", "gåva", "gift", "välgörenhet", "charity", "röda korset", "red cross",
            "unicef", "rädda barnen", "save the children", "cancerfonden", "cancer foundation",
            "hjärt-lungfonden", "heart-lung foundation", "allmännyttig", "public benefit"
        ),
        
        // Union fees and professional memberships
        TaxCategory.PROFESSIONAL_FEES to listOf(
            "fackavgift", "union fee", "a-kassa", "unemployment insurance", "medlemsavgift", "membership fee",
            "yrkesförsäkring", "professional insurance", "kammarregistrering", "chamber registration",
            "branschorganisation", "industry organization"
        )
    )
    
    /**
     * Generate comprehensive tax report for a given tax year
     */
    suspend fun generateTaxReport(taxYear: Int): TaxReport = withContext(Dispatchers.IO) {
        try {
            val (startDate, endDate) = getTaxYearPeriod(taxYear)
            
            // Get all transactions for the tax year
            val expenses = expenseDao.getExpensesBetweenDates(startDate, endDate)
            val allIncomes = incomeDao.getAllIncomesSnapshot()
            val incomes = allIncomes.filter { it.date >= startDate && it.date <= endDate }
            val investments = mutableListOf<Investment>()
            investmentDao.getAllActiveInvestments().collect { investments.addAll(it) }
            
            // Categorize expenses for tax purposes
            val categorizedExpenses = categorizeExpensesForTax(expenses)
            
            // Calculate deductible amounts
            val deductibleAmounts = calculateDeductibleAmounts(categorizedExpenses)
            
            // Get investment information for capital gains/losses
            val investmentSummary = calculateInvestmentTaxInfo(investments, startDate, endDate)
            
            // Generate income summary
            val incomeSummary = generateIncomeSummary(incomes)
            
            TaxReport(
                taxYear = taxYear,
                periodStart = startDate,
                periodEnd = endDate,
                incomeSummary = incomeSummary,
                deductibleExpenses = deductibleAmounts,
                categorizedExpenses = categorizedExpenses,
                investmentSummary = investmentSummary,
                totalDeductions = deductibleAmounts.values.sum(),
                suggestions = generateTaxOptimizationSuggestions(categorizedExpenses, deductibleAmounts),
                generatedAt = System.currentTimeMillis()
            )
            
        } catch (e: Exception) {
            com.example.budgetapp.utils.ErrorHandler.logError("TaxReporting", "Error generating tax report", e)
            TaxReport(taxYear = taxYear)
        }
    }
    
    /**
     * Automatically categorize an expense for tax purposes
     */
    suspend fun categorizeExpenseForTax(expense: Expense): TaxCategorization? = 
        withContext(Dispatchers.IO) {
            val text = "${expense.title} ${expense.description ?: ""} ${expense.store ?: ""}".lowercase()
            
            for ((category, keywords) in taxDeductibleCategories) {
                val matchScore = calculateKeywordMatchScore(text, keywords)
                if (matchScore > 0.6f) {
                    return@withContext TaxCategorization(
                        expense = expense,
                        suggestedCategory = category,
                        confidence = matchScore,
                        matchedKeywords = keywords.filter { text.contains(it) },
                        deductibleAmount = calculateDeductibleAmount(expense, category),
                        notes = generateTaxNotes(category, expense)
                    )
                }
            }
            
            null
        }
    
    /**
     * Get tax optimization suggestions
     */
    suspend fun getTaxOptimizationSuggestions(): List<TaxOptimizationSuggestion> = 
        withContext(Dispatchers.IO) {
            val suggestions = mutableListOf<TaxOptimizationSuggestion>()
            val currentYear = Calendar.getInstance().get(Calendar.YEAR)
            
            try {
                val currentYear = Calendar.getInstance().get(Calendar.YEAR)
                val (startDate, endDate) = getTaxYearPeriod(currentYear)
                val expenses = expenseDao.getExpensesBetweenDates(startDate, endDate)
                val categorizedExpenses = categorizeExpensesForTax(expenses)
                
                // RUT/ROT optimization
                val rutExpenses = categorizedExpenses[TaxCategory.RUT_SERVICES]?.sumOf { it.amount } ?: 0.0
                val rotExpenses = categorizedExpenses[TaxCategory.ROT_SERVICES]?.sumOf { it.amount } ?: 0.0
                
                if (rutExpenses > 0 && rutExpenses < 50000) { // RUT max is 50,000 SEK deduction
                    suggestions.add(
                        TaxOptimizationSuggestion(
                            type = OptimizationType.RUT_OPTIMIZATION,
                            title = "Optimera RUT-avdrag",
                            description = "Du har använt ${String.format("%.0f", rutExpenses)} SEK av 50,000 SEK RUT-avdrag.",
                            potentialSaving = (50000 - rutExpenses) * 0.5, // 50% deduction
                            actionRequired = "Överväg fler hushållstjänster innan årets slut",
                            priority = if (rutExpenses < 25000) Priority.HIGH else Priority.MEDIUM
                        )
                    )
                }
                
                if (rotExpenses > 0 && rotExpenses < 50000) { // ROT max varies but let's use 50,000 as example
                    suggestions.add(
                        TaxOptimizationSuggestion(
                            type = OptimizationType.ROT_OPTIMIZATION,
                            title = "Optimera ROT-avdrag",
                            description = "Du har använt ${String.format("%.0f", rotExpenses)} SEK av möjligt ROT-avdrag.",
                            potentialSaving = (50000 - rotExpenses) * 0.3, // 30% deduction
                            actionRequired = "Planera renoveringsarbeten för skatteoptimering",
                            priority = Priority.MEDIUM
                        )
                    )
                }
                
                // Medical expenses optimization
                val medicalExpenses = categorizedExpenses[TaxCategory.MEDICAL_EXPENSES]?.sumOf { it.amount } ?: 0.0
                if (medicalExpenses > 5000) { // Threshold for medical expense deduction
                    suggestions.add(
                        TaxOptimizationSuggestion(
                            type = OptimizationType.MEDICAL_EXPENSES,
                            title = "Sjukvårdsavdrag tillgängligt",
                            description = "Dina sjukvårdskostnader överstiger självrisk-nivån.",
                            potentialSaving = (medicalExpenses - 5000) * 0.31, // Marginal tax rate
                            actionRequired = "Samla alla kvitton för sjukvårdskostnader",
                            priority = Priority.HIGH
                        )
                    )
                }
                
                // Investment optimization
                suggestions.addAll(getInvestmentTaxSuggestions())
                
            } catch (e: Exception) {
                com.example.budgetapp.utils.ErrorHandler.logError("TaxReporting", "Error generating suggestions", e)
            }
            
            suggestions.sortedByDescending { it.potentialSaving }
        }
    
    /**
     * Export tax report to different formats
     */
    suspend fun exportTaxReport(taxReport: TaxReport, format: ExportFormat): String = 
        withContext(Dispatchers.IO) {
            when (format) {
                ExportFormat.CSV -> exportTaxReportToCsv(taxReport)
                ExportFormat.PDF -> exportTaxReportToPdf(taxReport)
                ExportFormat.EXCEL -> exportTaxReportToExcel(taxReport)
                ExportFormat.SIE -> exportTaxReportToSie(taxReport) // Swedish accounting format
            }
        }
    
    // Private helper methods
    
    private fun getTaxYearPeriod(taxYear: Int): Pair<Long, Long> {
        val calendar = Calendar.getInstance()
        
        // Start of tax year
        calendar.set(taxYear, TAX_YEAR_START_MONTH - 1, TAX_YEAR_START_DAY, 0, 0, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        val startDate = calendar.timeInMillis
        
        // End of tax year
        calendar.set(taxYear, TAX_YEAR_END_MONTH - 1, TAX_YEAR_END_DAY, 23, 59, 59)
        calendar.set(Calendar.MILLISECOND, 999)
        val endDate = calendar.timeInMillis
        
        return Pair(startDate, endDate)
    }
    
    private suspend fun categorizeExpensesForTax(expenses: List<Expense>): Map<TaxCategory, List<Expense>> {
        val categorized = mutableMapOf<TaxCategory, MutableList<Expense>>()
        
        for (expense in expenses) {
            val categorization = categorizeExpenseForTax(expense)
            if (categorization != null) {
                categorized.getOrPut(categorization.suggestedCategory) { mutableListOf() }
                    .add(expense)
            }
        }
        
        return categorized
    }
    
    private fun calculateDeductibleAmounts(categorizedExpenses: Map<TaxCategory, List<Expense>>): Map<TaxCategory, Double> {
        return categorizedExpenses.mapValues { (category, expenses) ->
            expenses.sumOf { expense ->
                calculateDeductibleAmount(expense, category)
            }
        }
    }
    
    private fun calculateDeductibleAmount(expense: Expense, category: TaxCategory): Double {
        return when (category) {
            TaxCategory.RUT_SERVICES -> expense.amount * 0.5 // 50% deduction, max 50,000 SEK
            TaxCategory.ROT_SERVICES -> expense.amount * 0.3 // 30% deduction
            TaxCategory.MEDICAL_EXPENSES -> maxOf(0.0, expense.amount) // Above threshold
            TaxCategory.TRAVEL_EXPENSES -> expense.amount // Full deduction if work-related
            TaxCategory.WORK_EQUIPMENT -> expense.amount // Full deduction
            TaxCategory.PROFESSIONAL_DEVELOPMENT -> expense.amount // Full deduction
            TaxCategory.CHARITABLE_DONATIONS -> minOf(expense.amount, 6000.0) // Max 6,000 SEK
            TaxCategory.PROFESSIONAL_FEES -> expense.amount // Full deduction
            TaxCategory.OTHER -> 0.0
        }
    }
    
    private fun calculateKeywordMatchScore(text: String, keywords: List<String>): Float {
        val matches = keywords.count { keyword ->
            text.contains(keyword)
        }
        return matches.toFloat() / keywords.size
    }
    
    private fun generateIncomeSummary(incomes: List<Income>): IncomeSummary {
        val salaryIncome = incomes.filter { it.category == "Lön" }.sumOf { it.amount }
        val freelanceIncome = incomes.filter { it.category == "Freelance" }.sumOf { it.amount }
        val investmentIncome = incomes.filter { it.category == "Investering" }.sumOf { it.amount }
        val otherIncome = incomes.filter { 
            !listOf("Lön", "Freelance", "Investering").contains(it.category) 
        }.sumOf { it.amount }
        
        return IncomeSummary(
            totalIncome = incomes.sumOf { it.amount },
            salaryIncome = salaryIncome,
            freelanceIncome = freelanceIncome,
            investmentIncome = investmentIncome,
            otherIncome = otherIncome
        )
    }
    
    private suspend fun calculateInvestmentTaxInfo(
        investments: List<Investment>, 
        startDate: Long, 
        endDate: Long
    ): InvestmentTaxSummary {
        var totalDividends = 0.0
        var totalCapitalGains = 0.0
        
        for (investment in investments) {
            // Calculate dividends for the period
            val dividends = investmentDao.getTotalDividendsForPeriod(investment.id, startDate, endDate) ?: 0.0
            totalDividends += dividends
            
            // Calculate capital gains (simplified)
            val currentValue = investment.currentValue * investment.quantity
            val cost = investment.averageCost * investment.quantity
            if (currentValue > cost) {
                totalCapitalGains += (currentValue - cost)
            }
        }
        
        return InvestmentTaxSummary(
            totalDividends = totalDividends,
            totalCapitalGains = totalCapitalGains,
            estimatedTax = (totalDividends + totalCapitalGains) * 0.3 // 30% capital gains tax
        )
    }
    
    private fun generateTaxOptimizationSuggestions(
        categorizedExpenses: Map<TaxCategory, List<Expense>>,
        deductibleAmounts: Map<TaxCategory, Double>
    ): List<String> {
        val suggestions = mutableListOf<String>()
        
        // Check if close to RUT/ROT limits
        val rutAmount = deductibleAmounts[TaxCategory.RUT_SERVICES] ?: 0.0
        if (rutAmount > 40000) {
            suggestions.add("Du närmar dig gränsen för RUT-avdrag (50,000 SEK)")
        }
        
        // Check for missing categories
        if (categorizedExpenses[TaxCategory.MEDICAL_EXPENSES]?.isEmpty() != false) {
            suggestions.add("Glöm inte att spara kvitton på sjukvårdskostnader")
        }
        
        return suggestions
    }
    
    private fun generateTaxNotes(category: TaxCategory, expense: Expense): String {
        return when (category) {
            TaxCategory.RUT_SERVICES -> "RUT-avdrag: 50% av kostnaden, max 50,000 SEK per år"
            TaxCategory.ROT_SERVICES -> "ROT-avdrag: 30% av arbetskostnaden"
            TaxCategory.MEDICAL_EXPENSES -> "Avdrag för sjukvårdskostnader över självrisk"
            TaxCategory.TRAVEL_EXPENSES -> "Avdragsgill om resan är arbetsrelaterad"
            TaxCategory.WORK_EQUIPMENT -> "Avdragsgill arbetsrelaterad utrustning"
            TaxCategory.PROFESSIONAL_DEVELOPMENT -> "Avdragsgill fortbildning"
            TaxCategory.CHARITABLE_DONATIONS -> "Max 6,000 SEK per år i avdrag"
            TaxCategory.PROFESSIONAL_FEES -> "Fackavgifter och yrkesrelaterade avgifter"
            TaxCategory.OTHER -> "Övrig kategori - kontrollera avdragsrätt"
        }
    }
    
    private suspend fun getInvestmentTaxSuggestions(): List<TaxOptimizationSuggestion> {
        // Implementation for investment-specific tax suggestions
        return emptyList() // Placeholder
    }
    
    private fun exportTaxReportToCsv(taxReport: TaxReport): String {
        // CSV export implementation
        return "CSV export not implemented yet"
    }
    
    private fun exportTaxReportToPdf(taxReport: TaxReport): String {
        // PDF export implementation
        return "PDF export not implemented yet"
    }
    
    private fun exportTaxReportToExcel(taxReport: TaxReport): String {
        // Excel export implementation
        return "Excel export not implemented yet"
    }
    
    private fun exportTaxReportToSie(taxReport: TaxReport): String {
        // SIE format export implementation
        return "SIE export not implemented yet"
    }
}

// Data classes for tax reporting

enum class TaxCategory {
    RUT_SERVICES,           // Household services
    ROT_SERVICES,           // Renovation/construction
    MEDICAL_EXPENSES,       // Healthcare costs
    TRAVEL_EXPENSES,        // Work-related travel
    WORK_EQUIPMENT,         // Professional equipment
    PROFESSIONAL_DEVELOPMENT, // Education/training
    CHARITABLE_DONATIONS,   // Charitable gifts
    PROFESSIONAL_FEES,      // Union fees, professional memberships
    OTHER
}

data class TaxCategorization(
    val expense: Expense,
    val suggestedCategory: TaxCategory,
    val confidence: Float,
    val matchedKeywords: List<String>,
    val deductibleAmount: Double,
    val notes: String
)

data class TaxReport(
    val taxYear: Int,
    val periodStart: Long = 0L,
    val periodEnd: Long = 0L,
    val incomeSummary: IncomeSummary = IncomeSummary(),
    val deductibleExpenses: Map<TaxCategory, Double> = emptyMap(),
    val categorizedExpenses: Map<TaxCategory, List<Expense>> = emptyMap(),
    val investmentSummary: InvestmentTaxSummary = InvestmentTaxSummary(),
    val totalDeductions: Double = 0.0,
    val suggestions: List<String> = emptyList(),
    val generatedAt: Long = System.currentTimeMillis()
)

data class IncomeSummary(
    val totalIncome: Double = 0.0,
    val salaryIncome: Double = 0.0,
    val freelanceIncome: Double = 0.0,
    val investmentIncome: Double = 0.0,
    val otherIncome: Double = 0.0
)

data class InvestmentTaxSummary(
    val totalDividends: Double = 0.0,
    val totalCapitalGains: Double = 0.0,
    val estimatedTax: Double = 0.0
)

data class TaxOptimizationSuggestion(
    val type: OptimizationType,
    val title: String,
    val description: String,
    val potentialSaving: Double,
    val actionRequired: String,
    val priority: Priority
)

enum class OptimizationType {
    RUT_OPTIMIZATION,
    ROT_OPTIMIZATION,
    MEDICAL_EXPENSES,
    INVESTMENT_TIMING,
    CHARITABLE_GIVING,
    PROFESSIONAL_EXPENSES
}

enum class Priority {
    HIGH,
    MEDIUM,
    LOW
}

enum class ExportFormat {
    CSV,
    PDF,
    EXCEL,
    SIE
}