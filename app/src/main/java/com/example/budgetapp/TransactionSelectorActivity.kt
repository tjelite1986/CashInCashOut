package com.example.budgetapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.budgetapp.adapters.SelectableTransactionAdapter
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.TransactionNotification
import com.example.budgetapp.database.entities.TransactionType
import com.example.budgetapp.databinding.ActivityTransactionSelectorBinding
import com.example.budgetapp.fragments.TransactionListFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TransactionSelectorActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityTransactionSelectorBinding
    private lateinit var database: BudgetDatabase
    
    private lateinit var pagerAdapter: TransactionPagerAdapter
    private val fragments = mutableListOf<TransactionListFragment>()
    
    private var selectedTransactionsCount = 0
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionSelectorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        database = BudgetDatabase.getDatabase(this)
        
        setupToolbar()
        setupViewPager()
        setupSearchAndFilters()
        setupClickListeners()
    }
    
    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
    
    private fun setupViewPager() {
        pagerAdapter = TransactionPagerAdapter(this) { selectedTransactions ->
            updateSelectionCount(selectedTransactions.size)
        }
        
        binding.viewPager.adapter = pagerAdapter
        
        // Setup tabs
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Inkomster"
                1 -> "Utgifter"  
                2 -> "Lån"
                else -> ""
            }
        }.attach()
    }
    
    private fun setupSearchAndFilters() {
        // Search functionality
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                applyFilters()
            }
        })
        
        // Filter chips
        binding.chipRecurring.setOnCheckedChangeListener { _, _ -> applyFilters() }
        binding.chipThisMonth.setOnCheckedChangeListener { _, _ -> applyFilters() }
    }
    
    private fun setupClickListeners() {
        binding.btnSelectAll.setOnClickListener {
            getCurrentFragment()?.selectAll()
        }
        
        binding.btnClearAll.setOnClickListener {
            getCurrentFragment()?.clearSelection()
        }
        
        binding.fabSave.setOnClickListener {
            saveSelectedTransactions()
        }
    }
    
    private fun applyFilters() {
        val query = binding.etSearch.text.toString()
        val showRecurringOnly = binding.chipRecurring.isChecked
        val showThisMonthOnly = binding.chipThisMonth.isChecked
        
        getCurrentFragment()?.filterTransactions(query, showRecurringOnly, showThisMonthOnly)
    }
    
    private fun getCurrentFragment(): TransactionListFragment? {
        val currentItem = binding.viewPager.currentItem
        return if (currentItem < fragments.size) fragments[currentItem] else null
    }
    
    private fun updateSelectionCount(count: Int) {
        selectedTransactionsCount = count
        binding.tvSelectionCount.text = "$count transaktioner valda"
        
        // Show/hide FAB based on selection
        if (count > 0) {
            binding.fabSave.show()
        } else {
            binding.fabSave.hide()
        }
    }
    
    private fun saveSelectedTransactions() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                // Collect all selected transactions from all fragments
                val allSelectedTransactions = mutableListOf<SelectableTransactionAdapter.SelectableTransaction>()
                
                withContext(Dispatchers.Main) {
                    fragments.forEach { fragment ->
                        allSelectedTransactions.addAll(fragment.getSelectedTransactions())
                    }
                }
                
                // Clear existing notifications
                database.transactionNotificationDao().deleteNotificationsByType(TransactionType.INCOME)
                database.transactionNotificationDao().deleteNotificationsByType(TransactionType.EXPENSE)
                database.transactionNotificationDao().deleteNotificationsByType(TransactionType.LOAN_DUE)
                
                // Save new notifications
                val notifications = allSelectedTransactions.map { selectableTransaction ->
                    TransactionNotification(
                        transactionType = selectableTransaction.transactionType,
                        transactionId = selectableTransaction.id,
                        transactionTitle = selectableTransaction.title,
                        isEnabled = true,
                        reminderDaysBefore = 1, // Default to 1 day before
                        customMessage = null
                    )
                }
                
                database.transactionNotificationDao().insertNotifications(notifications)
                
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@TransactionSelectorActivity,
                        "Sparat ${notifications.size} transaktionspåminnelser",
                        Toast.LENGTH_SHORT
                    ).show()
                    
                    // Set result and finish
                    setResult(RESULT_OK)
                    finish()
                }
                
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        this@TransactionSelectorActivity,
                        "Fel vid sparande: ${e.message}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
    
    private inner class TransactionPagerAdapter(
        fragmentActivity: FragmentActivity,
        private val onSelectionChanged: (List<SelectableTransactionAdapter.SelectableTransaction>) -> Unit
    ) : FragmentStateAdapter(fragmentActivity) {
        
        override fun getItemCount(): Int = 3
        
        override fun createFragment(position: Int): Fragment {
            val transactionType = when (position) {
                0 -> TransactionType.INCOME
                1 -> TransactionType.EXPENSE
                2 -> TransactionType.LOAN_DUE
                else -> TransactionType.INCOME
            }
            
            val fragment = TransactionListFragment.newInstance(transactionType) { selectedTransactions ->
                onSelectionChanged(selectedTransactions)
            }
            
            fragments.add(fragment)
            return fragment
        }
    }
}