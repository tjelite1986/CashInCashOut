package com.example.budgetapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.budgetapp.databinding.ActivityMainBinding
import com.example.budgetapp.fragments.BudgetFragment
import com.example.budgetapp.fragments.ExpensesFragment
import com.example.budgetapp.fragments.IncomeFragment
import com.example.budgetapp.fragments.OverviewFragment
import com.example.budgetapp.fragments.SettingsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigation()
        
        // Show overview fragment by default
        if (savedInstanceState == null) {
            showFragment(OverviewFragment())
        }
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            try {
                when (item.itemId) {
                    R.id.nav_overview -> {
                        showFragment(OverviewFragment())
                        true
                    }
                    R.id.nav_expenses -> {
                        showFragment(ExpensesFragment())
                        true
                    }
                    R.id.nav_income -> {
                        showFragment(IncomeFragment())
                        true
                    }
                    R.id.nav_budget -> {
                        showFragment(BudgetFragment())
                        true
                    }
                    R.id.nav_settings -> {
                        showFragment(SettingsFragment())
                        true
                    }
                    else -> false
                }
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
        
        // Set default selection
        binding.bottomNavigation.selectedItemId = R.id.nav_overview
    }

    private fun showFragment(fragment: androidx.fragment.app.Fragment) {
        try {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .commitAllowingStateLoss()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}