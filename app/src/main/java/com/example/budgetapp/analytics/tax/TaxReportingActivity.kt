package com.example.budgetapp.analytics.tax

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
// import com.example.budgetapp.adapters.TaxCategoryAdapter
// import com.example.budgetapp.adapters.TaxSuggestionAdapter
import com.example.budgetapp.database.BudgetDatabase
// import com.example.budgetapp.databinding.ActivityTaxReportingBinding
import com.example.budgetapp.analytics.tax.TaxReportingService
import com.example.budgetapp.analytics.tax.TaxReport
import com.example.budgetapp.analytics.tax.TaxCategory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.*

// Temporarily commented out until layout files are created
/*
class TaxReportingActivity : AppCompatActivity() {
    
    // private lateinit var binding: ActivityTaxReportingBinding
    private lateinit var database: BudgetDatabase
    private lateinit var taxService: TaxReportingService
    private lateinit var categoryAdapter: TaxCategoryAdapter
    private lateinit var suggestionAdapter: TaxSuggestionAdapter
    
    private val numberFormat = NumberFormat.getCurrencyInstance(Locale("sv", "SE"))
    private var currentTaxReport: TaxReport? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaxReportingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupToolbar()
        initializeServices()
        setupRecyclerViews()
        setupYearSpinner()
        setupClickListeners()
        loadTaxOptimizationSuggestions()
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Skattedeklaration & Avdrag"
            setDisplayHomeAsUpEnabled(true)
        }
    }
    
    private fun initializeServices() {
        database = BudgetDatabase.getDatabase(this)
        taxService = TaxReportingService(this)
    }
    
    private fun setupRecyclerViews() {
        // Tax category breakdown
        categoryAdapter = TaxCategoryAdapter { category, expenses ->
            showCategoryDetails(category, expenses)
        }
        
        binding.recyclerViewTaxCategories.apply {
            layoutManager = LinearLayoutManager(this@TaxReportingActivity)
            adapter = categoryAdapter
        }
        
        // Tax optimization suggestions
        suggestionAdapter = TaxSuggestionAdapter { suggestion ->
            showSuggestionDetails(suggestion)
        }
        
        binding.recyclerViewSuggestions.apply {
            layoutManager = LinearLayoutManager(this@TaxReportingActivity)
            adapter = suggestionAdapter
        }
    }
    
    private fun setupYearSpinner() {
        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        val years = (currentYear - 5..currentYear).toList().reversed()
        
        val yearAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, years)
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerTaxYear.adapter = yearAdapter
        
        binding.spinnerTaxYear.setOnItemSelectedListener(object : android.widget.AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: android.widget.AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                val selectedYear = years[position]
                generateTaxReport(selectedYear)
            }
            
            override fun onNothingSelected(parent: android.widget.AdapterView<*>?) {}
        })
        
        // Set current year as default
        binding.spinnerTaxYear.setSelection(0)
    }
    
    private fun setupClickListeners() {
        binding.buttonGenerateReport.setOnClickListener {
            val selectedYear = binding.spinnerTaxYear.selectedItem as Int
            generateTaxReport(selectedYear)
        }
        
        binding.buttonExportReport.setOnClickListener {
            currentTaxReport?.let { exportTaxReport(it) }
        }
        
        binding.cardIncomeSummary.setOnClickListener {
            showIncomeSummaryDetails()
        }
        
        binding.cardDeductionsSummary.setOnClickListener {
            showDeductionsSummaryDetails()
        }
    }
    
    private fun generateTaxReport(taxYear: Int) {
        binding.progressBar.visibility = android.view.View.VISIBLE
        binding.layoutReportContent.visibility = android.view.View.GONE
        
        lifecycleScope.launch {
            try {
                val taxReport = taxService.generateTaxReport(taxYear)
                currentTaxReport = taxReport
                
                runOnUiThread {
                    displayTaxReport(taxReport)
                    binding.progressBar.visibility = android.view.View.GONE
                    binding.layoutReportContent.visibility = android.view.View.VISIBLE
                }
            } catch (e: Exception) {
                runOnUiThread {
                    binding.progressBar.visibility = android.view.View.GONE
                    showErrorDialog("Fel vid generering av skatterapport: ${e.message}")
                }
            }
        }
    }
    
    private fun displayTaxReport(taxReport: TaxReport) {
        // Display income summary
        binding.apply {
            textTotalIncome.text = numberFormat.format(taxReport.incomeSummary.totalIncome)
            textSalaryIncome.text = numberFormat.format(taxReport.incomeSummary.salaryIncome)
            textFreelanceIncome.text = numberFormat.format(taxReport.incomeSummary.freelanceIncome)
            textInvestmentIncome.text = numberFormat.format(taxReport.incomeSummary.investmentIncome)
        }
        
        // Display deduction summary
        binding.apply {
            textTotalDeductions.text = numberFormat.format(taxReport.totalDeductions)
            textRutDeductions.text = numberFormat.format(
                taxReport.deductibleExpenses[TaxCategory.RUT_SERVICES] ?: 0.0
            )
            textRotDeductions.text = numberFormat.format(
                taxReport.deductibleExpenses[TaxCategory.ROT_SERVICES] ?: 0.0
            )
            textMedicalDeductions.text = numberFormat.format(
                taxReport.deductibleExpenses[TaxCategory.MEDICAL_EXPENSES] ?: 0.0
            )
        }
        
        // Display investment tax info
        binding.apply {
            textDividendIncome.text = numberFormat.format(taxReport.investmentSummary.totalDividends)
            textCapitalGains.text = numberFormat.format(taxReport.investmentSummary.totalCapitalGains)
            textEstimatedTax.text = numberFormat.format(taxReport.investmentSummary.estimatedTax)
        }
        
        // Update recycler views
        val categoryBreakdown = taxReport.categorizedExpenses.map { (category, expenses) ->
            TaxCategoryBreakdown(
                category = category,
                expenses = expenses,
                totalAmount = expenses.sumOf { it.amount },
                deductibleAmount = taxReport.deductibleExpenses[category] ?: 0.0
            )
        }
        
        categoryAdapter.submitList(categoryBreakdown)
        
        // Show suggestions if available
        if (taxReport.suggestions.isNotEmpty()) {
            binding.textSuggestionsTitle.visibility = android.view.View.VISIBLE
            binding.textSuggestions.visibility = android.view.View.VISIBLE
            binding.textSuggestions.text = taxReport.suggestions.joinToString("\n• ", "• ")
        } else {
            binding.textSuggestionsTitle.visibility = android.view.View.GONE
            binding.textSuggestions.visibility = android.view.View.GONE
        }
    }
    
    private fun loadTaxOptimizationSuggestions() {
        lifecycleScope.launch {
            try {
                val suggestions = taxService.getTaxOptimizationSuggestions()
                suggestionAdapter.submitList(suggestions)
                
                if (suggestions.isEmpty()) {
                    binding.textNoSuggestions.visibility = android.view.View.VISIBLE
                    binding.recyclerViewSuggestions.visibility = android.view.View.GONE
                } else {
                    binding.textNoSuggestions.visibility = android.view.View.GONE
                    binding.recyclerViewSuggestions.visibility = android.view.View.VISIBLE
                }
            } catch (e: Exception) {
                com.example.budgetapp.utils.ErrorHandler.logError("TaxReporting", "Error loading suggestions", e)
            }
        }
    }
    
    private fun showCategoryDetails(category: TaxCategory, expenses: List<com.example.budgetapp.database.entities.Expense>) {
        val categoryName = formatTaxCategoryName(category)
        val totalAmount = expenses.sumOf { it.amount }
        val expenseList = expenses.joinToString("\n") { 
            "• ${it.title}: ${numberFormat.format(it.amount)}"
        }
        
        val message = """
            Kategori: $categoryName
            Antal utgifter: ${expenses.size}
            Totalt belopp: ${numberFormat.format(totalAmount)}
            
            Utgifter:
            $expenseList
        """.trimIndent()
        
        MaterialAlertDialogBuilder(this)
            .setTitle("Detaljerad kategorivisning")
            .setMessage(message)
            .setPositiveButton("Stäng", null)
            .show()
    }
    
    private fun showSuggestionDetails(suggestion: com.example.budgetapp.services.TaxOptimizationSuggestion) {
        val message = """
            ${suggestion.description}
            
            Potentiell besparing: ${numberFormat.format(suggestion.potentialSaving)}
            
            Åtgärd krävs:
            ${suggestion.actionRequired}
            
            Prioritet: ${formatPriority(suggestion.priority)}
        """.trimIndent()
        
        MaterialAlertDialogBuilder(this)
            .setTitle(suggestion.title)
            .setMessage(message)
            .setPositiveButton("Stäng", null)
            .show()
    }
    
    private fun showIncomeSummaryDetails() {
        currentTaxReport?.let { report ->
            val message = """
                Löner: ${numberFormat.format(report.incomeSummary.salaryIncome)}
                Frilansintäkter: ${numberFormat.format(report.incomeSummary.freelanceIncome)}
                Investeringsintäkter: ${numberFormat.format(report.incomeSummary.investmentIncome)}
                Övriga intäkter: ${numberFormat.format(report.incomeSummary.otherIncome)}
                
                Total inkomst: ${numberFormat.format(report.incomeSummary.totalIncome)}
            """.trimIndent()
            
            MaterialAlertDialogBuilder(this)
                .setTitle("Inkomstöversikt")
                .setMessage(message)
                .setPositiveButton("Stäng", null)
                .show()
        }
    }
    
    private fun showDeductionsSummaryDetails() {
        currentTaxReport?.let { report ->
            val deductionDetails = report.deductibleExpenses.entries.joinToString("\n") { (category, amount) ->
                "${formatTaxCategoryName(category)}: ${numberFormat.format(amount)}"
            }
            
            val message = """
                Avdragsgilla utgifter:
                
                $deductionDetails
                
                Totala avdrag: ${numberFormat.format(report.totalDeductions)}
            """.trimIndent()
            
            MaterialAlertDialogBuilder(this)
                .setTitle("Avdragsöversikt")
                .setMessage(message)
                .setPositiveButton("Stäng", null)
                .show()
        }
    }
    
    private fun exportTaxReport(taxReport: TaxReport) {
        val exportOptions = arrayOf("CSV", "PDF", "Excel", "SIE")
        
        MaterialAlertDialogBuilder(this)
            .setTitle("Exportera skatterapport")
            .setItems(exportOptions) { _, which ->
                val format = when (which) {
                    0 -> com.example.budgetapp.services.ExportFormat.CSV
                    1 -> com.example.budgetapp.services.ExportFormat.PDF
                    2 -> com.example.budgetapp.services.ExportFormat.EXCEL
                    3 -> com.example.budgetapp.services.ExportFormat.SIE
                    else -> com.example.budgetapp.services.ExportFormat.CSV
                }
                
                lifecycleScope.launch {
                    try {
                        val result = taxService.exportTaxReport(taxReport, format)
                        runOnUiThread {
                            MaterialAlertDialogBuilder(this@TaxReportingActivity)
                                .setTitle("Export slutförd")
                                .setMessage("Rapporten har exporterats: $result")
                                .setPositiveButton("OK", null)
                                .show()
                        }
                    } catch (e: Exception) {
                        runOnUiThread {
                            showErrorDialog("Fel vid export: ${e.message}")
                        }
                    }
                }
            }
            .show()
    }
    
    private fun showErrorDialog(message: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Fel")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }
    
    private fun formatTaxCategoryName(category: TaxCategory): String {
        return when (category) {
            TaxCategory.RUT_SERVICES -> "RUT-tjänster"
            TaxCategory.ROT_SERVICES -> "ROT-arbeten"
            TaxCategory.MEDICAL_EXPENSES -> "Sjukvårdskostnader"
            TaxCategory.TRAVEL_EXPENSES -> "Resekostnader"
            TaxCategory.WORK_EQUIPMENT -> "Arbetsutrustning"
            TaxCategory.PROFESSIONAL_DEVELOPMENT -> "Kompetensutveckling"
            TaxCategory.CHARITABLE_DONATIONS -> "Välgörenhet"
            TaxCategory.PROFESSIONAL_FEES -> "Yrkesavgifter"
            TaxCategory.OTHER -> "Övrigt"
        }
    }
    
    private fun formatPriority(priority: com.example.budgetapp.services.Priority): String {
        return when (priority) {
            com.example.budgetapp.services.Priority.HIGH -> "Hög"
            com.example.budgetapp.services.Priority.MEDIUM -> "Medel"
            com.example.budgetapp.services.Priority.LOW -> "Låg"
        }
    }
    
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tax_reporting_menu, menu)
        return true
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.action_help -> {
                showTaxHelpDialog()
                true
            }
            R.id.action_settings -> {
                // TODO: Open tax settings
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    
    private fun showTaxHelpDialog() {
        val helpText = """
            Skattedeklarationshjälp:
            
            • RUT-avdrag: 50% av kostnaden för hushållstjänster, max 50,000 SEK
            • ROT-avdrag: 30% av arbetskostnaden för renovering/reparation
            • Sjukvård: Avdrag för kostnader över självrisk (ca 1,200 SEK)
            • Resekostnader: Arbetsrelaterade resor kan vara avdragsgilla
            • Välgörenhet: Max 6,000 SEK per år
            
            Denna app hjälper dig att kategorisera utgifter automatiskt baserat på beskrivningar och butiker.
        """.trimIndent()
        
        MaterialAlertDialogBuilder(this)
            .setTitle("Hjälp")
            .setMessage(helpText)
            .setPositiveButton("Stäng", null)
            .show()
    }
}

// Data class for tax category breakdown display
data class TaxCategoryBreakdown(
    val category: TaxCategory,
    val expenses: List<com.example.budgetapp.database.entities.Expense>,
    val totalAmount: Double,
    val deductibleAmount: Double
)*/
