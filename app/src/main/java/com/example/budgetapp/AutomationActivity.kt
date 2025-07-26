package com.example.budgetapp

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgetapp.automation.SmartAutomationEngine
import com.example.budgetapp.automation.ml.MachineLearningInsightEngine
// import com.example.budgetapp.automation.prediction.PredictiveFinancialPlanner
import com.example.budgetapp.adapters.AutomationRuleAdapter
import com.example.budgetapp.adapters.SmartInsightAdapter
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.databinding.ActivityAutomationBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class AutomationActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityAutomationBinding
    private lateinit var database: BudgetDatabase
    private lateinit var automationEngine: SmartAutomationEngine
    private lateinit var mlEngine: MachineLearningInsightEngine
    // private lateinit var financialPlanner: PredictiveFinancialPlanner
    private lateinit var ruleAdapter: AutomationRuleAdapter
    private lateinit var insightAdapter: SmartInsightAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAutomationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupToolbar()
        initializeServices()
        setupRecyclerViews()
        setupClickListeners()
        loadData()
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Smart Automation"
        }
    }
    
    private fun initializeServices() {
        database = BudgetDatabase.getDatabase(this)
        automationEngine = SmartAutomationEngine(this)
        mlEngine = MachineLearningInsightEngine(this)
        // financialPlanner = PredictiveFinancialPlanner(this)
    }
    
    private fun setupRecyclerViews() {
        // Automation Rules RecyclerView
        ruleAdapter = AutomationRuleAdapter { rule ->
            // Handle rule click - show details or edit
            showRuleDetails(rule)
        }
        
        binding.recyclerViewRules.apply {
            layoutManager = LinearLayoutManager(this@AutomationActivity)
            adapter = ruleAdapter
        }
        
        // Smart Insights RecyclerView
        insightAdapter = SmartInsightAdapter { insight ->
            // Handle insight click
            markInsightAsRead(insight.id)
        }
        
        binding.recyclerViewInsights.apply {
            layoutManager = LinearLayoutManager(this@AutomationActivity)
            adapter = insightAdapter
        }
    }
    
    private fun setupClickListeners() {
        binding.fabAddRule.setOnClickListener {
            // TODO: Open activity to create new automation rule
            // startActivity(Intent(this, AddAutomationRuleActivity::class.java))
            showError("Add automation rule feature coming soon!")
        }
        
        binding.buttonGenerateInsights.setOnClickListener {
            generateSmartInsights()
        }
        
        binding.buttonOptimizeBudget.setOnClickListener {
            optimizeBudgets()
        }
        
        binding.buttonCreateEmergencyPlan.setOnClickListener {
            createEmergencyFundPlan()
        }
        
        binding.buttonDetectPatterns.setOnClickListener {
            detectSpendingPatterns()
        }
        
        binding.switchAutoInsights.setOnCheckedChangeListener { _, isChecked ->
            saveAutoInsightsPreference(isChecked)
        }
    }
    
    private fun loadData() {
        lifecycleScope.launch {
            try {
                binding.progressBar.visibility = android.view.View.VISIBLE
                
                // Load automation rules (placeholder - will be implemented later)
                val emptyRules = emptyList<com.example.budgetapp.automation.AutomationRule>()
                ruleAdapter.submitList(emptyRules)
                updateRuleStatistics(emptyRules)
                
                // Load smart insights
                val insights = database.automationRuleDao().getActiveInsights()
                insightAdapter.submitList(insights)
                updateInsightStatistics(insights)
                
                binding.progressBar.visibility = android.view.View.GONE
                
            } catch (e: Exception) {
                binding.progressBar.visibility = android.view.View.GONE
                showError("Failed to load automation data: ${e.message}")
            }
        }
    }
    
    private fun generateSmartInsights() {
        lifecycleScope.launch {
            try {
                binding.progressBar.visibility = android.view.View.VISIBLE
                
                val insights = mlEngine.generateSmartInsights()
                
                // Save insights to database
                insights.forEach { insight ->
                    database.automationRuleDao().insertInsight(insight)
                }
                
                // Refresh the insights list
                val activeInsights = database.automationRuleDao().getActiveInsights()
                insightAdapter.submitList(activeInsights)
                
                binding.progressBar.visibility = android.view.View.GONE
                
                Snackbar.make(binding.root, "Generated ${insights.size} smart insights", Snackbar.LENGTH_LONG)
                    .setAction("View") {
                        binding.viewPager.currentItem = 1 // Switch to insights tab
                    }
                    .show()
                
            } catch (e: Exception) {
                binding.progressBar.visibility = android.view.View.GONE
                showError("Failed to generate insights: ${e.message}")
            }
        }
    }
    
    private fun optimizeBudgets() {
        lifecycleScope.launch {
            try {
                binding.progressBar.visibility = android.view.View.VISIBLE
                
                val optimization = mlEngine.optimizeBudgetAllocations()
                
                // Show optimization results in a dialog
                showBudgetOptimizationDialog(optimization)
                
                binding.progressBar.visibility = android.view.View.GONE
                
            } catch (e: Exception) {
                binding.progressBar.visibility = android.view.View.GONE
                showError("Failed to optimize budgets: ${e.message}")
            }
        }
    }
    
    private fun createEmergencyFundPlan() {
        // TODO: Implement emergency fund planning when prediction module is ready
        showError("Emergency fund planning coming soon - prediction module being developed!")
    }
    
    private fun detectSpendingPatterns() {
        lifecycleScope.launch {
            try {
                binding.progressBar.visibility = android.view.View.VISIBLE
                
                val changes = mlEngine.detectSpendingBehaviorChanges()
                
                // Show behavior changes in insights
                val insights = changes.map { change ->
                    com.example.budgetapp.automation.ml.SmartInsight(
                        id = java.util.UUID.randomUUID().toString(),
                        type = com.example.budgetapp.automation.ml.InsightType.SPENDING_PATTERN,
                        title = "Beteendeförändring i ${change.category}",
                        description = "Dina utgifter i ${change.category} har ${if (change.changeType == com.example.budgetapp.automation.ml.ChangeType.INCREASE) "ökat" else "minskat"} med ${change.changePercent.toInt()}%",
                        actionable = true,
                        actions = listOf("Granska ${change.category} utgifter", "Justera budget för ${change.category}"),
                        priority = when (change.significance) {
                            com.example.budgetapp.automation.ml.ChangeSignificance.HIGH -> com.example.budgetapp.automation.ml.InsightPriority.HIGH
                            com.example.budgetapp.automation.ml.ChangeSignificance.MEDIUM -> com.example.budgetapp.automation.ml.InsightPriority.MEDIUM
                            else -> com.example.budgetapp.automation.ml.InsightPriority.LOW
                        },
                        confidence = 0.85,
                        category = change.category,
                        impact = com.example.budgetapp.automation.ml.ImpactLevel.MEDIUM,
                        createdAt = System.currentTimeMillis()
                    )
                }
                
                // Save insights to database
                insights.forEach { insight ->
                    database.automationRuleDao().insertInsight(insight)
                }
                
                // Refresh insights
                val activeInsights = database.automationRuleDao().getActiveInsights()
                insightAdapter.submitList(activeInsights)
                
                binding.progressBar.visibility = android.view.View.GONE
                
                Snackbar.make(binding.root, "Detected ${changes.size} spending pattern changes", Snackbar.LENGTH_LONG)
                    .show()
                
            } catch (e: Exception) {
                binding.progressBar.visibility = android.view.View.GONE
                showError("Failed to detect patterns: ${e.message}")
            }
        }
    }
    
    private fun showRuleDetails(rule: com.example.budgetapp.automation.AutomationRule) {
        // TODO: Implement rule details dialog or activity
        // val intent = Intent(this, AutomationRuleDetailsActivity::class.java)
        // intent.putExtra("rule_id", rule.id)
        // startActivity(intent)
        showError("Rule details feature coming soon!")
    }
    
    private fun markInsightAsRead(insightId: String) {
        lifecycleScope.launch {
            database.automationRuleDao().markInsightAsRead(insightId)
        }
    }
    
    private fun updateRuleStatistics(rules: List<com.example.budgetapp.automation.AutomationRule>) {
        val activeRules = rules.count { it.isActive }
        val totalUsage = rules.sumOf { it.usageCount }
        
        binding.textActiveRules.text = "Active Rules: $activeRules"
        binding.textTotalUsage.text = "Total Usage: $totalUsage"
    }
    
    private fun updateInsightStatistics(insights: List<com.example.budgetapp.automation.ml.SmartInsight>) {
        val unreadCount = insights.count { !it.isRead }
        val highPriorityCount = insights.count { it.priority == com.example.budgetapp.automation.ml.InsightPriority.HIGH }
        
        binding.textUnreadInsights.text = "Unread: $unreadCount"
        binding.textHighPriorityInsights.text = "High Priority: $highPriorityCount"
    }
    
    private fun showBudgetOptimizationDialog(optimization: com.example.budgetapp.automation.ml.BudgetOptimizationResult) {
        // TODO: Implement budget optimization dialog
        // val intent = Intent(this, BudgetOptimizationActivity::class.java)
        // startActivity(intent)
        showError("Budget optimization dialog coming soon!")
    }
    
    private fun showEmergencyFundDialog(recommendation: Any) {
        // TODO: Implement emergency fund dialog
        // val intent = Intent(this, EmergencyFundPlanningActivity::class.java)
        // startActivity(intent)
        showError("Emergency fund planning coming soon!")
    }
    
    private fun saveAutoInsightsPreference(enabled: Boolean) {
        val sharedPrefs = getSharedPreferences("automation_settings", MODE_PRIVATE)
        sharedPrefs.edit().putBoolean("auto_insights", enabled).apply()
    }
    
    private fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    
    override fun onResume() {
        super.onResume()
        // Refresh data when returning to activity
        loadData()
    }
}