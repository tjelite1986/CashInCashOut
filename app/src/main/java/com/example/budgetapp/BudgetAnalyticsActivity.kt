package com.example.budgetapp

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.databinding.ActivityBudgetAnalyticsBinding
import com.example.budgetapp.services.BudgetAnalyticsService
import com.example.budgetapp.services.BudgetStatus
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class BudgetAnalyticsActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityBudgetAnalyticsBinding
    private lateinit var analyticsService: BudgetAnalyticsService
    private val numberFormat = NumberFormat.getCurrencyInstance(Locale("sv", "SE"))
    private val dateFormat = SimpleDateFormat("MMM dd", Locale("sv", "SE"))
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBudgetAnalyticsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupToolbar()
        initializeService()
        loadAnalytics()
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.title = "Budgetanalys"
        
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
    
    private fun initializeService() {
        val database = BudgetDatabase.getDatabase(this)
        analyticsService = BudgetAnalyticsService(database)
    }
    
    private fun loadAnalytics() {
        lifecycleScope.launch {
            try {
                binding.progressBar.visibility = android.view.View.VISIBLE
                binding.scrollView.visibility = android.view.View.GONE
                
                val analytics = analyticsService.generateBudgetAnalytics()
                val performanceHistory = analyticsService.getBudgetPerformanceHistory(30)
                
                displayAnalytics(analytics)
                setupCharts(analytics, performanceHistory)
                
                binding.progressBar.visibility = android.view.View.GONE
                binding.scrollView.visibility = android.view.View.VISIBLE
                
            } catch (e: Exception) {
                e.printStackTrace()
                binding.progressBar.visibility = android.view.View.GONE
                binding.textErrorMessage.visibility = android.view.View.VISIBLE
                binding.textErrorMessage.text = "Fel vid laddning av analys: ${e.message}"
            }
        }
    }
    
    private fun displayAnalytics(analytics: com.example.budgetapp.services.BudgetAnalytics) {
        // Summary cards
        binding.textTotalBudgets.text = analytics.totalBudgets.toString()
        binding.textTotalBudgetAmount.text = numberFormat.format(analytics.totalBudgetAmount)
        binding.textTotalSpent.text = numberFormat.format(analytics.totalSpentAmount)
        binding.textTotalRemaining.text = numberFormat.format(analytics.totalRemainingAmount)
        
        // Progress indicators
        binding.textBudgetsOnTrack.text = analytics.budgetsOnTrack.toString()
        binding.textBudgetsNearLimit.text = analytics.budgetsNearLimit.toString()
        binding.textBudgetsOverLimit.text = analytics.budgetsOverLimit.toString()
        
        // Efficiency metrics
        binding.textUtilizationRate.text = "${analytics.budgetEfficiency.utilizationRate.toInt()}%"
        binding.textBudgetAccuracy.text = "${analytics.budgetEfficiency.accuracy.toInt()}%"
        binding.textEfficiencyScore.text = "${analytics.budgetEfficiency.efficiency.toInt()}%"
        binding.textEfficiencyRecommendation.text = analytics.budgetEfficiency.recommendation
        
        // Period analysis
        val periodText = when (analytics.periodAnalysis.mostCommonPeriod) {
            com.example.budgetapp.database.entities.BudgetPeriod.WEEKLY -> "Veckovis"
            com.example.budgetapp.database.entities.BudgetPeriod.MONTHLY -> "Månadsvis"
            com.example.budgetapp.database.entities.BudgetPeriod.QUARTERLY -> "Kvartalsvis"
            com.example.budgetapp.database.entities.BudgetPeriod.YEARLY -> "Årligen"
        }
        binding.textMostCommonPeriod.text = periodText
        binding.textPeriodRecommendation.text = analytics.periodAnalysis.recommendation
        
        // Color code efficiency indicators
        updateEfficiencyColors(analytics.budgetEfficiency.utilizationRate)
    }
    
    private fun updateEfficiencyColors(utilizationRate: Double) {
        val color = when {
            utilizationRate > 100 -> ContextCompat.getColor(this, R.color.error)
            utilizationRate > 90 -> ContextCompat.getColor(this, R.color.success)
            utilizationRate > 70 -> ContextCompat.getColor(this, R.color.primary)
            else -> ContextCompat.getColor(this, R.color.warning)
        }
        
        binding.textUtilizationRate.setTextColor(color)
    }
    
    private fun setupCharts(
        analytics: com.example.budgetapp.services.BudgetAnalytics,
        performanceHistory: List<com.example.budgetapp.services.BudgetPerformancePoint>
    ) {
        setupCategoryPieChart(analytics.categoryBreakdown)
        setupSpendingTrendChart(analytics.spendingTrend)
        setupBudgetStatusChart(analytics)
        setupPerformanceChart(performanceHistory)
    }
    
    private fun setupCategoryPieChart(categoryBreakdown: List<com.example.budgetapp.services.CategorySpending>) {
        val pieChart = binding.pieChartCategories
        
        if (categoryBreakdown.isEmpty()) {
            pieChart.setNoDataText("Inga budgetkategorier att visa")
            return
        }
        
        val entries = ArrayList<PieEntry>()
        val colors = ArrayList<Int>()
        
        categoryBreakdown.take(8).forEach { category -> // Limit to top 8 categories
            entries.add(PieEntry(category.spentAmount.toFloat(), category.categoryName))
            
            val color = when (category.status) {
                BudgetStatus.OVER_BUDGET -> ContextCompat.getColor(this, R.color.error)
                BudgetStatus.NEAR_LIMIT -> ContextCompat.getColor(this, R.color.warning)
                BudgetStatus.ON_TRACK -> ContextCompat.getColor(this, R.color.success)
            }
            colors.add(color)
        }
        
        val dataSet = PieDataSet(entries, "Utgifter per kategori")
        dataSet.colors = colors
        dataSet.valueTextSize = 12f
        dataSet.valueTextColor = Color.WHITE
        dataSet.valueFormatter = object : com.github.mikephil.charting.formatter.ValueFormatter() {
            override fun getPieLabel(value: Float, pieEntry: PieEntry?): String {
                return numberFormat.format(value.toDouble())
            }
        }
        
        val data = PieData(dataSet)
        pieChart.data = data
        
        // Styling
        pieChart.description.isEnabled = false
        pieChart.isDrawHoleEnabled = true
        pieChart.setHoleColor(Color.TRANSPARENT)
        pieChart.holeRadius = 40f
        pieChart.transparentCircleRadius = 45f
        pieChart.setDrawCenterText(true)
        pieChart.centerText = "Kategorier"
        pieChart.setCenterTextSize(16f)
        
        // Legend
        pieChart.legend.isEnabled = true
        pieChart.legend.textSize = 12f
        
        // Animation
        pieChart.animateY(1000, Easing.EaseInOutQuad)
        
        pieChart.invalidate()
    }
    
    private fun setupSpendingTrendChart(spendingTrend: List<com.example.budgetapp.services.SpendingTrendPoint>) {
        val lineChart = binding.lineChartSpendingTrend
        
        if (spendingTrend.isEmpty()) {
            lineChart.setNoDataText("Ingen utgiftstrend att visa")
            return
        }
        
        val entries = ArrayList<Entry>()
        val labels = ArrayList<String>()
        
        spendingTrend.forEachIndexed { index, point ->
            entries.add(Entry(index.toFloat(), point.amount.toFloat()))
            labels.add(dateFormat.format(Date(point.date)))
        }
        
        val dataSet = LineDataSet(entries, "Dagliga utgifter")
        dataSet.color = ContextCompat.getColor(this, R.color.primary)
        dataSet.setCircleColor(ContextCompat.getColor(this, R.color.primary))
        dataSet.lineWidth = 2f
        dataSet.circleRadius = 4f
        dataSet.setDrawFilled(true)
        dataSet.fillColor = ContextCompat.getColor(this, R.color.primary_variant)
        dataSet.fillAlpha = 100
        dataSet.valueTextSize = 10f
        
        val data = LineData(dataSet)
        lineChart.data = data
        
        // X-axis
        val xAxis = lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        xAxis.granularity = 1f
        xAxis.setDrawGridLines(false)
        xAxis.textSize = 10f
        
        // Y-axis
        lineChart.axisLeft.setDrawGridLines(true)
        lineChart.axisLeft.textSize = 10f
        lineChart.axisRight.isEnabled = false
        
        // Styling
        lineChart.description.isEnabled = false
        lineChart.legend.isEnabled = false
        lineChart.setTouchEnabled(true)
        lineChart.setDragEnabled(true)
        lineChart.setScaleEnabled(false)
        
        // Animation
        lineChart.animateX(1000)
        
        lineChart.invalidate()
    }
    
    private fun setupBudgetStatusChart(analytics: com.example.budgetapp.services.BudgetAnalytics) {
        val pieChart = binding.pieChartBudgetStatus
        
        val entries = ArrayList<PieEntry>()
        entries.add(PieEntry(analytics.budgetsOnTrack.toFloat(), "På rätt spår"))
        entries.add(PieEntry(analytics.budgetsNearLimit.toFloat(), "Nära gränsen"))
        entries.add(PieEntry(analytics.budgetsOverLimit.toFloat(), "Över budget"))
        
        val dataSet = PieDataSet(entries, "Budgetstatus")
        dataSet.colors = listOf(
            ContextCompat.getColor(this, R.color.success),
            ContextCompat.getColor(this, R.color.warning),
            ContextCompat.getColor(this, R.color.error)
        )
        dataSet.valueTextSize = 12f
        dataSet.valueTextColor = Color.WHITE
        dataSet.valueFormatter = PercentFormatter(pieChart)
        
        val data = PieData(dataSet)
        pieChart.data = data
        
        // Styling
        pieChart.description.isEnabled = false
        pieChart.isDrawHoleEnabled = true
        pieChart.setHoleColor(Color.TRANSPARENT)
        pieChart.holeRadius = 40f
        pieChart.setDrawCenterText(true)
        pieChart.centerText = "Status"
        pieChart.setCenterTextSize(16f)
        pieChart.setUsePercentValues(true)
        
        // Legend
        pieChart.legend.isEnabled = true
        pieChart.legend.textSize = 12f
        
        // Animation
        pieChart.animateY(1000, Easing.EaseInOutQuad)
        
        pieChart.invalidate()
    }
    
    private fun setupPerformanceChart(performanceHistory: List<com.example.budgetapp.services.BudgetPerformancePoint>) {
        val lineChart = binding.lineChartPerformance
        
        if (performanceHistory.isEmpty()) {
            lineChart.setNoDataText("Ingen prestationshistorik att visa")
            return
        }
        
        val entries = ArrayList<Entry>()
        val labels = ArrayList<String>()
        
        performanceHistory.forEachIndexed { index, point ->
            entries.add(Entry(index.toFloat(), point.averagePercentage.toFloat()))
            labels.add(dateFormat.format(Date(point.date)))
        }
        
        val dataSet = LineDataSet(entries, "Genomsnittlig budgetanvändning (%)")
        dataSet.color = ContextCompat.getColor(this, R.color.secondary)
        dataSet.setCircleColor(ContextCompat.getColor(this, R.color.secondary))
        dataSet.lineWidth = 2f
        dataSet.circleRadius = 3f
        dataSet.setDrawFilled(true)
        dataSet.fillColor = ContextCompat.getColor(this, R.color.secondary_variant)
        dataSet.fillAlpha = 80
        dataSet.valueTextSize = 0f // Hide values for cleaner look
        
        val data = LineData(dataSet)
        lineChart.data = data
        
        // X-axis
        val xAxis = lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        xAxis.granularity = 5f // Show every 5th label
        xAxis.setDrawGridLines(false)
        xAxis.textSize = 9f
        
        // Y-axis
        lineChart.axisLeft.setDrawGridLines(true)
        lineChart.axisLeft.textSize = 10f
        lineChart.axisLeft.axisMinimum = 0f
        lineChart.axisLeft.axisMaximum = 120f
        lineChart.axisRight.isEnabled = false
        
        // Add reference lines
        val limitLine = com.github.mikephil.charting.components.LimitLine(100f, "100% Budget")
        limitLine.lineColor = ContextCompat.getColor(this, R.color.error)
        limitLine.lineWidth = 2f
        lineChart.axisLeft.addLimitLine(limitLine)
        
        // Styling
        lineChart.description.isEnabled = false
        lineChart.legend.isEnabled = false
        lineChart.setTouchEnabled(true)
        lineChart.setDragEnabled(true)
        lineChart.setScaleEnabled(false)
        
        // Animation
        lineChart.animateX(1000)
        
        lineChart.invalidate()
    }
}