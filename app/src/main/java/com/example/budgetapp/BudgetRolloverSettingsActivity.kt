package com.example.budgetapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.databinding.ActivityBudgetRolloverSettingsBinding
import com.example.budgetapp.services.BudgetRolloverBehavior
import com.example.budgetapp.services.BudgetRolloverService
import com.example.budgetapp.services.BudgetRolloverStrategy
import com.example.budgetapp.services.BudgetRolloverWorker
import com.example.budgetapp.utils.BudgetRolloverSettings
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.*

class BudgetRolloverSettingsActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityBudgetRolloverSettingsBinding
    private lateinit var rolloverSettings: BudgetRolloverSettings
    private lateinit var rolloverService: BudgetRolloverService
    private val numberFormat = NumberFormat.getCurrencyInstance(Locale("sv", "SE"))
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBudgetRolloverSettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        initializeComponents()
        setupToolbar()
        setupUI()
        setupClickListeners()
        loadSettings()
    }
    
    private fun initializeComponents() {
        rolloverSettings = BudgetRolloverSettings(this)
        val database = BudgetDatabase.getDatabase(this)
        rolloverService = BudgetRolloverService(database, this)
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.title = "Budgetrullning"
        
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
    
    private fun setupUI() {
        // Setup strategy dropdown
        val strategies = listOf("Smart rullning", "Hela beloppet", "Procent av kvarvarande", "Fast belopp")
        val strategyAdapter = ArrayAdapter(this, R.layout.dropdown_item_light, strategies)
        binding.dropdownStrategy.setAdapter(strategyAdapter)
        
        // Setup behavior dropdown
        val behaviors = listOf("Lägg till originalbudget", "Ersätt med rullat belopp", "Viktad genomsnitt")
        val behaviorAdapter = ArrayAdapter(this, R.layout.dropdown_item_light, behaviors)
        binding.dropdownBehavior.setAdapter(behaviorAdapter)
    }
    
    private fun setupClickListeners() {
        // Main rollover toggle
        binding.switchRolloverEnabled.setOnCheckedChangeListener { _, isChecked ->
            rolloverSettings.rolloverEnabled = isChecked
            updateUIState()
            updateWorkScheduling()
        }
        
        // Auto rollover toggle
        binding.switchAutoRollover.setOnCheckedChangeListener { _, isChecked ->
            rolloverSettings.autoRolloverEnabled = isChecked
            updateWorkScheduling()
        }
        
        // Rollover notifications toggle
        binding.switchRolloverNotifications.setOnCheckedChangeListener { _, isChecked ->
            rolloverSettings.rolloverNotificationsEnabled = isChecked
        }
        
        // Period toggles
        binding.switchWeekly.setOnCheckedChangeListener { _, isChecked ->
            rolloverSettings.rolloverWeeklyBudgets = isChecked
        }
        
        binding.switchMonthly.setOnCheckedChangeListener { _, isChecked ->
            rolloverSettings.rolloverMonthlyBudgets = isChecked
        }
        
        binding.switchQuarterly.setOnCheckedChangeListener { _, isChecked ->
            rolloverSettings.rolloverQuarterlyBudgets = isChecked
        }
        
        binding.switchYearly.setOnCheckedChangeListener { _, isChecked ->
            rolloverSettings.rolloverYearlyBudgets = isChecked
        }
        
        // Strategy dropdown
        binding.dropdownStrategy.setOnItemClickListener { _, _, position, _ ->
            val strategy = when (position) {
                0 -> BudgetRolloverStrategy.SMART
                1 -> BudgetRolloverStrategy.FULL_AMOUNT
                2 -> BudgetRolloverStrategy.PERCENTAGE
                3 -> BudgetRolloverStrategy.FIXED_AMOUNT
                else -> BudgetRolloverStrategy.SMART
            }
            rolloverSettings.rolloverStrategy = strategy
            updateStrategySettings()
        }
        
        // Behavior dropdown
        binding.dropdownBehavior.setOnItemClickListener { _, _, position, _ ->
            val behavior = when (position) {
                0 -> BudgetRolloverBehavior.ADD_TO_ORIGINAL
                1 -> BudgetRolloverBehavior.REPLACE_WITH_ROLLOVER
                2 -> BudgetRolloverBehavior.WEIGHTED_AVERAGE
                else -> BudgetRolloverBehavior.ADD_TO_ORIGINAL
            }
            rolloverSettings.rolloverBehavior = behavior
            updateBehaviorSettings()
        }
        
        // Amount input listeners
        binding.editMinimumAmount.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val amount = binding.editMinimumAmount.text.toString().toDoubleOrNull() ?: 0.0
                rolloverSettings.minimumRolloverAmount = amount
            }
        }
        
        binding.editPercentage.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val percentage = binding.editPercentage.text.toString().toDoubleOrNull() ?: 50.0
                rolloverSettings.rolloverPercentage = percentage.coerceIn(0.0, 100.0)
                binding.editPercentage.setText(rolloverSettings.rolloverPercentage.toInt().toString())
            }
        }
        
        binding.editFixedAmount.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                val amount = binding.editFixedAmount.text.toString().toDoubleOrNull() ?: 100.0
                rolloverSettings.fixedRolloverAmount = amount
            }
        }
        
        // Action buttons
        binding.buttonPreviewRollover.setOnClickListener {
            previewRollover()
        }
        
        binding.buttonTriggerRollover.setOnClickListener {
            showTriggerRolloverConfirmation()
        }
        
        binding.buttonResetSettings.setOnClickListener {
            showResetSettingsConfirmation()
        }
    }
    
    private fun loadSettings() {
        // Load current settings into UI
        binding.switchRolloverEnabled.isChecked = rolloverSettings.rolloverEnabled
        binding.switchAutoRollover.isChecked = rolloverSettings.autoRolloverEnabled
        binding.switchRolloverNotifications.isChecked = rolloverSettings.rolloverNotificationsEnabled
        
        // Period settings
        binding.switchWeekly.isChecked = rolloverSettings.rolloverWeeklyBudgets
        binding.switchMonthly.isChecked = rolloverSettings.rolloverMonthlyBudgets
        binding.switchQuarterly.isChecked = rolloverSettings.rolloverQuarterlyBudgets
        binding.switchYearly.isChecked = rolloverSettings.rolloverYearlyBudgets
        
        // Strategy and behavior
        val strategyIndex = when (rolloverSettings.rolloverStrategy) {
            BudgetRolloverStrategy.SMART -> 0
            BudgetRolloverStrategy.FULL_AMOUNT -> 1
            BudgetRolloverStrategy.PERCENTAGE -> 2
            BudgetRolloverStrategy.FIXED_AMOUNT -> 3
        }
        binding.dropdownStrategy.setText(binding.dropdownStrategy.adapter.getItem(strategyIndex).toString(), false)
        
        val behaviorIndex = when (rolloverSettings.rolloverBehavior) {
            BudgetRolloverBehavior.ADD_TO_ORIGINAL -> 0
            BudgetRolloverBehavior.REPLACE_WITH_ROLLOVER -> 1
            BudgetRolloverBehavior.WEIGHTED_AVERAGE -> 2
        }
        binding.dropdownBehavior.setText(binding.dropdownBehavior.adapter.getItem(behaviorIndex).toString(), false)
        
        // Amount settings
        binding.editMinimumAmount.setText(rolloverSettings.minimumRolloverAmount.toInt().toString())
        binding.editPercentage.setText(rolloverSettings.rolloverPercentage.toInt().toString())
        binding.editFixedAmount.setText(rolloverSettings.fixedRolloverAmount.toInt().toString())
        
        updateUIState()
        updateStrategySettings()
        updateBehaviorSettings()
    }
    
    private fun updateUIState() {
        val enabled = binding.switchRolloverEnabled.isChecked
        
        // Enable/disable all settings based on main toggle
        binding.layoutPeriodSettings.alpha = if (enabled) 1.0f else 0.5f
        binding.layoutStrategySettings.alpha = if (enabled) 1.0f else 0.5f
        binding.layoutBehaviorSettings.alpha = if (enabled) 1.0f else 0.5f
        binding.layoutAmountSettings.alpha = if (enabled) 1.0f else 0.5f
        binding.layoutActions.alpha = if (enabled) 1.0f else 0.5f
        
        binding.switchWeekly.isEnabled = enabled
        binding.switchMonthly.isEnabled = enabled
        binding.switchQuarterly.isEnabled = enabled
        binding.switchYearly.isEnabled = enabled
        binding.dropdownStrategy.isEnabled = enabled
        binding.dropdownBehavior.isEnabled = enabled
        binding.editMinimumAmount.isEnabled = enabled
        binding.editPercentage.isEnabled = enabled
        binding.editFixedAmount.isEnabled = enabled
    }
    
    private fun updateStrategySettings() {
        val strategy = rolloverSettings.rolloverStrategy
        
        // Show/hide strategy-specific settings
        binding.layoutPercentageSettings.visibility = 
            if (strategy == BudgetRolloverStrategy.PERCENTAGE) android.view.View.VISIBLE else android.view.View.GONE
        
        binding.layoutFixedAmountSettings.visibility = 
            if (strategy == BudgetRolloverStrategy.FIXED_AMOUNT) android.view.View.VISIBLE else android.view.View.GONE
        
        // Update strategy description
        val description = when (strategy) {
            BudgetRolloverStrategy.SMART -> "Analyserar tidigare utgiftsmönster för optimal rullning"
            BudgetRolloverStrategy.FULL_AMOUNT -> "Rullar över hela det kvarvarande beloppet"
            BudgetRolloverStrategy.PERCENTAGE -> "Rullar över en viss procent av kvarvarande belopp"
            BudgetRolloverStrategy.FIXED_AMOUNT -> "Rullar över ett fast belopp (max kvarvarande belopp)"
        }
        binding.textStrategyDescription.text = description
    }
    
    private fun updateBehaviorSettings() {
        val behavior = rolloverSettings.rolloverBehavior
        
        // Show/hide behavior-specific settings
        binding.layoutWeightSettings.visibility = 
            if (behavior == BudgetRolloverBehavior.WEIGHTED_AVERAGE) android.view.View.VISIBLE else android.view.View.GONE
        
        // Update behavior description
        val description = when (behavior) {
            BudgetRolloverBehavior.ADD_TO_ORIGINAL -> "Det rullade beloppet läggs till originalbudgeten för nästa period"
            BudgetRolloverBehavior.REPLACE_WITH_ROLLOVER -> "Endast det rullade beloppet används som ny budget"
            BudgetRolloverBehavior.WEIGHTED_AVERAGE -> "Skapar en viktad genomsnitt mellan original och rullat belopp"
        }
        binding.textBehaviorDescription.text = description
    }
    
    private fun updateWorkScheduling() {
        if (rolloverSettings.autoRolloverEnabled && rolloverSettings.rolloverEnabled) {
            BudgetRolloverWorker.scheduleRolloverCheck(this)
        } else {
            BudgetRolloverWorker.cancelRolloverWork(this)
        }
    }
    
    private fun previewRollover() {
        lifecycleScope.launch {
            try {
                binding.progressBar.visibility = android.view.View.VISIBLE
                
                val preview = rolloverService.previewRollover()
                
                binding.progressBar.visibility = android.view.View.GONE
                
                showRolloverPreview(preview)
            } catch (e: Exception) {
                binding.progressBar.visibility = android.view.View.GONE
                Toast.makeText(this@BudgetRolloverSettingsActivity, 
                    "Fel vid förhandsgranskning: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
    
    private fun showRolloverPreview(preview: com.example.budgetapp.services.BudgetRolloverPreview) {
        val message = buildString {
            appendLine("Förhandsgranskning av budgetrullning:")
            appendLine()
            appendLine("📊 Statistik:")
            appendLine("• Utgångna budgetar: ${preview.totalExpiredBudgets}")
            appendLine("• Kommer att rullas över: ${preview.budgetsToRollover}")
            appendLine("• Total rullning: ${numberFormat.format(preview.totalRolloverAmount)}")
            appendLine()
            
            if (preview.previews.isNotEmpty()) {
                appendLine("📋 Detaljer:")
                preview.previews.forEach { item ->
                    appendLine("• ${item.budgetName}")
                    appendLine("  Kvarvarande: ${numberFormat.format(item.remainingAmount)}")
                    if (item.wouldRollover) {
                        appendLine("  Rullning: ${numberFormat.format(item.rolloverAmount)}")
                    } else {
                        appendLine("  Status: ${item.reason}")
                    }
                    appendLine()
                }
            }
        }
        
        MaterialAlertDialogBuilder(this)
            .setTitle("Förhandsgranskning")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }
    
    private fun showTriggerRolloverConfirmation() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Kör budgetrullning nu")
            .setMessage("Detta kommer att rulla över alla utgångna budgetar enligt dina inställningar. Är du säker?")
            .setPositiveButton("Kör rullning") { _, _ ->
                triggerImmediateRollover()
            }
            .setNegativeButton("Avbryt", null)
            .show()
    }
    
    private fun triggerImmediateRollover() {
        BudgetRolloverWorker.triggerImmediateRollover(this)
        Toast.makeText(this, "Budgetrullning startad i bakgrunden", Toast.LENGTH_SHORT).show()
    }
    
    private fun showResetSettingsConfirmation() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Återställ inställningar")
            .setMessage("Detta kommer att återställa alla rullningsinställningar till standardvärden. Är du säker?")
            .setPositiveButton("Återställ") { _, _ ->
                resetSettings()
            }
            .setNegativeButton("Avbryt", null)
            .show()
    }
    
    private fun resetSettings() {
        rolloverSettings.resetToDefaults()
        loadSettings()
        updateWorkScheduling()
        Toast.makeText(this, "Inställningar återställda", Toast.LENGTH_SHORT).show()
    }
    
    override fun onPause() {
        super.onPause()
        // Save any pending input changes
        saveCurrentInputs()
    }
    
    private fun saveCurrentInputs() {
        try {
            val minimumAmount = binding.editMinimumAmount.text.toString().toDoubleOrNull()
            if (minimumAmount != null) {
                rolloverSettings.minimumRolloverAmount = minimumAmount
            }
            
            val percentage = binding.editPercentage.text.toString().toDoubleOrNull()
            if (percentage != null) {
                rolloverSettings.rolloverPercentage = percentage.coerceIn(0.0, 100.0)
            }
            
            val fixedAmount = binding.editFixedAmount.text.toString().toDoubleOrNull()
            if (fixedAmount != null) {
                rolloverSettings.fixedRolloverAmount = fixedAmount
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}