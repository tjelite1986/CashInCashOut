package com.example.budgetapp.analytics.investments

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgetapp.analytics.investments.adapters.InvestmentAdapter
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.analytics.investments.entities.*
import com.example.budgetapp.analytics.investments.marketdata.MarketDataService
import com.example.budgetapp.analytics.investments.portfolio.PortfolioAnalyticsService
import com.example.budgetapp.analytics.investments.crypto.CryptoTrackingService
import com.example.budgetapp.analytics.investments.multicurrency.CurrencyService
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.*
// Temporarily commented out until layout files are created
/*
class InvestmentTrackingActivity : AppCompatActivity() {
    
    // private lateinit var binding: ActivityInvestmentTrackingBinding
    private lateinit var database: BudgetDatabase
    private lateinit var marketDataService: MarketDataService
    private lateinit var portfolioAnalyticsService: PortfolioAnalyticsService
    private lateinit var cryptoService: CryptoTrackingService
    private lateinit var currencyService: CurrencyService
    private lateinit var investmentAdapter: InvestmentAdapter
    
    private val numberFormat = NumberFormat.getCurrencyInstance(Locale("sv", "SE"))
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInvestmentTrackingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupToolbar()
        initializeServices()
        setupRecyclerView()
        setupClickListeners()
        loadData()
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Investeringsportfölj"
            setDisplayHomeAsUpEnabled(true)
        }
    }
    
    private fun initializeServices() {
        database = BudgetDatabase.getDatabase(this)
        marketDataService = MarketDataService(this)
        portfolioAnalyticsService = PortfolioAnalyticsService(this, marketDataService)
        cryptoService = CryptoTrackingService(this, marketDataService)
        currencyService = CurrencyService(this)
    }
    
    private fun setupRecyclerView() {
        investmentAdapter = InvestmentAdapter(
            onInvestmentClick = { investment -> 
                showInvestmentDetails(investment)
            },
            onEditClick = { investment ->
                editInvestment(investment)
            },
            onDeleteClick = { investment ->
                confirmDeleteInvestment(investment)
            }
        )
        
        binding.recyclerViewInvestments.apply {
            layoutManager = LinearLayoutManager(this@InvestmentTrackingActivity)
            adapter = investmentAdapter
        }
    }
    
    private fun setupClickListeners() {
        binding.fabAddInvestment.setOnClickListener {
            addNewInvestment()
        }
        
        binding.cardPortfolioSummary.setOnClickListener {
            showPortfolioAnalytics()
        }
        
        binding.buttonViewRecommendations.setOnClickListener {
            showInvestmentRecommendations()
        }
    }
    
    private fun loadData() {
        // Load investments
        lifecycleScope.launch {
            database.investmentDao().getAllActiveInvestments().collect { investments ->
                investmentAdapter.submitList(investments)
                updateEmptyState(investments.isEmpty())
            }
        }
        
        // Load portfolio summary
        loadPortfolioSummary()
    }
    
    private fun loadPortfolioSummary() {
        lifecycleScope.launch {
            try {
                val portfolioPerformance = analyticsService.calculatePortfolioPerformance()
                updatePortfolioSummaryUI(portfolioPerformance)
            } catch (e: Exception) {
                // Handle error silently for now
                com.example.budgetapp.utils.ErrorHandler.logError("InvestmentTracking", "Error loading portfolio summary", e)
            }
        }
    }
    
    private fun updatePortfolioSummaryUI(performance: PortfolioPerformance) {
        binding.apply {
            textTotalValue.text = numberFormat.format(performance.totalValue)
            textTotalGain.text = numberFormat.format(performance.totalGain)
            textPortfolioReturn.text = "${String.format("%.2f", performance.portfolioReturn)}%"
            
            // Set color based on performance
            val color = if (performance.totalGain >= 0) {
                getColor(R.color.success_color)
            } else {
                getColor(R.color.error_color)
            }
            
            textTotalGain.setTextColor(color)
            textPortfolioReturn.setTextColor(color)
            
            textTotalInvestments.text = "${performance.totalInvestments} investeringar"
            textAnnualizedReturn.text = "${String.format("%.2f", performance.annualizedReturn)}% årlig"
            textDividendIncome.text = numberFormat.format(performance.totalDividendIncome)
        }
    }
    
    private fun updateEmptyState(isEmpty: Boolean) {
        binding.apply {
            if (isEmpty) {
                recyclerViewInvestments.visibility = android.view.View.GONE
                layoutEmptyState.visibility = android.view.View.VISIBLE
            } else {
                recyclerViewInvestments.visibility = android.view.View.VISIBLE
                layoutEmptyState.visibility = android.view.View.GONE
            }
        }
    }
    
    private fun addNewInvestment() {
        val intent = Intent(this, AddEditInvestmentActivity::class.java)
        startActivity(intent)
    }
    
    private fun editInvestment(investment: Investment) {
        val intent = Intent(this, AddEditInvestmentActivity::class.java).apply {
            putExtra("INVESTMENT_ID", investment.id)
        }
        startActivity(intent)
    }
    
    private fun showInvestmentDetails(investment: Investment) {
        lifecycleScope.launch {
            try {
                val performance = analyticsService.calculateInvestmentPerformance(investment.id)
                if (performance != null) {
                    showInvestmentDetailsDialog(investment, performance)
                }
            } catch (e: Exception) {
                com.example.budgetapp.utils.ErrorHandler.logError("InvestmentTracking", "Error loading investment details", e)
            }
        }
    }
    
    private fun showInvestmentDetailsDialog(
        investment: Investment, 
        performance: com.example.budgetapp.services.InvestmentPerformance
    ) {
        val message = """
            Typ: ${investment.type.name}
            Antal: ${investment.quantity}
            Genomsnittskostnad: ${numberFormat.format(investment.averageCost)}
            
            Nuvarande värde: ${numberFormat.format(performance.currentValue)}
            Total vinst/förlust: ${numberFormat.format(performance.absoluteGain)}
            Avkastning: ${String.format("%.2f", performance.percentageGain)}%
            Årlig avkastning: ${String.format("%.2f", performance.annualizedReturn)}%
            
            Utdelningsavkastning: ${String.format("%.2f", performance.dividendYield)}%
            Volatilitet: ${String.format("%.2f", performance.volatility)}%
            Sharpe-kvot: ${String.format("%.2f", performance.sharpeRatio)}
            
            Innehavsperiod: ${performance.holdingPeriodDays} dagar
        """.trimIndent()
        
        MaterialAlertDialogBuilder(this)
            .setTitle("${investment.name} - Detaljer")
            .setMessage(message)
            .setPositiveButton("Stäng", null)
            .setNeutralButton("Redigera") { _, _ ->
                editInvestment(investment)
            }
            .show()
    }
    
    private fun confirmDeleteInvestment(investment: Investment) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Ta bort investering")
            .setMessage("Är du säker på att du vill ta bort ${investment.name}?")
            .setPositiveButton("Ta bort") { _, _ ->
                deleteInvestment(investment)
            }
            .setNegativeButton("Avbryt", null)
            .show()
    }
    
    private fun deleteInvestment(investment: Investment) {
        lifecycleScope.launch {
            try {
                database.investmentDao().deactivateInvestment(investment.id)
                // Data will refresh automatically through Flow
            } catch (e: Exception) {
                com.example.budgetapp.utils.ErrorHandler.handleDatabaseError(this@InvestmentTrackingActivity, e)
            }
        }
    }
    
    private fun showPortfolioAnalytics() {
        val intent = Intent(this, PortfolioAnalyticsActivity::class.java)
        startActivity(intent)
    }
    
    private fun showInvestmentRecommendations() {
        lifecycleScope.launch {
            try {
                val recommendations = analyticsService.generateInvestmentRecommendations()
                if (recommendations.isNotEmpty()) {
                    showRecommendationsDialog(recommendations)
                } else {
                    showNoRecommendationsDialog()
                }
            } catch (e: Exception) {
                com.example.budgetapp.utils.ErrorHandler.logError("InvestmentTracking", "Error loading recommendations", e)
            }
        }
    }
    
    private fun showRecommendationsDialog(recommendations: List<com.example.budgetapp.services.InvestmentRecommendation>) {
        val message = recommendations.joinToString("\n\n") { recommendation ->
            "📊 ${recommendation.title}\n${recommendation.description}"
        }
        
        MaterialAlertDialogBuilder(this)
            .setTitle("Investeringsrekommendationer")
            .setMessage(message)
            .setPositiveButton("Stäng", null)
            .show()
    }
    
    private fun showNoRecommendationsDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Inga rekommendationer")
            .setMessage("Din portfölj ser bra ut för tillfället! Fortsätt med din nuvarande strategi.")
            .setPositiveButton("OK", null)
            .show()
    }
    
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.investment_tracking_menu, menu)
        return true
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.action_portfolio_analytics -> {
                showPortfolioAnalytics()
                true
            }
            R.id.action_export_data -> {
                exportInvestmentData()
                true
            }
            R.id.action_import_data -> {
                importInvestmentData()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    
    private fun exportInvestmentData() {
        // TODO: Implement data export functionality
        MaterialAlertDialogBuilder(this)
            .setTitle("Exportera data")
            .setMessage("Exportfunktionalitet kommer snart!")
            .setPositiveButton("OK", null)
            .show()
    }
    
    private fun importInvestmentData() {
        // TODO: Implement data import functionality
        MaterialAlertDialogBuilder(this)
            .setTitle("Importera data")
            .setMessage("Importfunktionalitet kommer snart!")
            .setPositiveButton("OK", null)
            .show()
    }
}*/
