package com.example.budgetapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.budgetapp.databinding.ActivityEditHomeBinding

class EditHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditHomeBinding
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        prefs = getSharedPreferences("home_sections", Context.MODE_PRIVATE)
        
        setupUI()
        loadSectionStates()
        setupClickListeners()
    }
    
    private fun setupUI() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
    
    private fun loadSectionStates() {
        binding.switchAccounts.isChecked = prefs.getBoolean("accounts_visible", true)
        binding.switchIncomeExpenses.isChecked = prefs.getBoolean("income_expenses_visible", true)
        binding.switchLoans.isChecked = prefs.getBoolean("loans_visible", true)
        binding.switchBudgets.isChecked = prefs.getBoolean("budgets_visible", false)
        binding.switchGoals.isChecked = prefs.getBoolean("goals_visible", false)
        binding.switchNetWorth.isChecked = prefs.getBoolean("net_worth_visible", false)
        binding.switchStatistics.isChecked = prefs.getBoolean("statistics_visible", true)
        binding.switchQuickActions.isChecked = prefs.getBoolean("quick_actions_visible", true)
        binding.switchSavingsGoals.isChecked = prefs.getBoolean("savings_goals_visible", false)
        binding.switchInvestmentOverview.isChecked = prefs.getBoolean("investment_overview_visible", false)
        binding.switchRecentActivity.isChecked = prefs.getBoolean("recent_activity_visible", true)
        binding.switchMonthlySummary.isChecked = prefs.getBoolean("monthly_summary_visible", true)
    }
    
    private fun setupClickListeners() {
        binding.switchAccounts.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("accounts_visible", isChecked).apply()
        }
        
        binding.switchIncomeExpenses.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("income_expenses_visible", isChecked).apply()
        }
        
        binding.switchLoans.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("loans_visible", isChecked).apply()
        }
        
        binding.switchBudgets.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("budgets_visible", isChecked).apply()
        }
        
        binding.switchGoals.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("goals_visible", isChecked).apply()
        }
        
        binding.switchNetWorth.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("net_worth_visible", isChecked).apply()
        }
        
        binding.switchStatistics.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("statistics_visible", isChecked).apply()
        }
        
        binding.switchQuickActions.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("quick_actions_visible", isChecked).apply()
        }
        
        binding.switchSavingsGoals.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("savings_goals_visible", isChecked).apply()
        }
        
        binding.switchInvestmentOverview.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("investment_overview_visible", isChecked).apply()
        }
        
        binding.switchRecentActivity.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("recent_activity_visible", isChecked).apply()
        }
        
        binding.switchMonthlySummary.setOnCheckedChangeListener { _, isChecked ->
            prefs.edit().putBoolean("monthly_summary_visible", isChecked).apply()
        }
    }
}