package com.example.budgetapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.budgetapp.AddProductActivity
import com.example.budgetapp.AddIncomeActivity
import com.example.budgetapp.AddExpenseActivity
import com.example.budgetapp.ProductListActivity
import com.example.budgetapp.StoreManagerActivity
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.databinding.FragmentOverviewBinding
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.*

class OverviewFragment : Fragment() {

    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var database: BudgetDatabase
    private val currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOverviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        try {
            database = BudgetDatabase.getDatabase(requireContext())
            setupClickListeners()
            setupUI()
            loadFinancialData()
        } catch (e: Exception) {
            e.printStackTrace()
            // Fallback to default values if database fails
            setupDefaultUI()
        }
    }

    private fun setupUI() {
        // Set default values initially
        binding.textTotalBalance.text = "0 kr"
        binding.textMonthlyExpenses.text = "0 kr"
        binding.textMonthlyIncome.text = "0 kr"
    }
    
    private fun setupDefaultUI() {
        // Fallback UI if database initialization fails
        binding.textTotalBalance.text = "0 kr"
        binding.textMonthlyExpenses.text = "0 kr"
        binding.textMonthlyIncome.text = "0 kr"
    }
    
    private fun loadFinancialData() {
        lifecycleScope.launch {
            try {
                // Get total income and expenses
                val totalIncome = database.incomeDao().getTotalIncome() ?: 0.0
                val totalExpenses = database.expenseDao().getTotalExpenses() ?: 0.0
                val balance = totalIncome - totalExpenses
                
                // Update UI on main thread
                binding.textMonthlyIncome.text = currencyFormat.format(totalIncome)
                binding.textMonthlyExpenses.text = currencyFormat.format(totalExpenses)
                binding.textTotalBalance.text = currencyFormat.format(balance)
            } catch (e: Exception) {
                e.printStackTrace()
                // Keep default values if database query fails
            }
        }
    }
    
    private fun setupClickListeners() {
        try {
            binding.fabAddProduct.setOnClickListener {
                val intent = Intent(requireContext(), AddProductActivity::class.java)
                startActivity(intent)
            }
            
            binding.buttonAddExpense.setOnClickListener {
                val intent = Intent(requireContext(), AddExpenseActivity::class.java)
                startActivity(intent)
            }
            
            binding.buttonAddIncome.setOnClickListener {
                val intent = Intent(requireContext(), AddIncomeActivity::class.java)
                startActivity(intent)
            }
            
            binding.buttonViewProducts.setOnClickListener {
                val intent = Intent(requireContext(), ProductListActivity::class.java)
                startActivity(intent)
            }
            
            binding.buttonManageStores.setOnClickListener {
                val intent = Intent(requireContext(), StoreManagerActivity::class.java)
                startActivity(intent)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    override fun onResume() {
        super.onResume()
        try {
            loadFinancialData()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}