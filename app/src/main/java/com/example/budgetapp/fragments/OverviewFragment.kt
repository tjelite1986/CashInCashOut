package com.example.budgetapp.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.budgetapp.AddIncomeActivity
import com.example.budgetapp.AddExpenseActivity
import com.example.budgetapp.AddLoanActivity
import com.example.budgetapp.EditHomeActivity
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.LoanType
import com.example.budgetapp.databinding.FragmentOverviewBinding
import com.example.budgetapp.data.ThemeSettings
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class OverviewFragment : BaseFragment() {

    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var database: BudgetDatabase
    private lateinit var prefs: SharedPreferences
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
            prefs = requireContext().getSharedPreferences("home_sections", Context.MODE_PRIVATE)
            
            setupUI()
            setupClickListeners()
            updateSectionVisibility()
            loadFinancialData()
        } catch (e: Exception) {
            e.printStackTrace()
            // Fallback to default values if database fails
            setupDefaultUI()
        }
    }

    private fun setupUI() {
        // Set greeting based on time of day
        updateGreeting()
        
        // Set default values initially
        binding.textTotalBalance.text = "0 kr"
        binding.textMonthlyExpenses.text = "0 kr"
        binding.textMonthlyIncome.text = "0 kr"
        binding.textNetWorth.text = "0 kr"
        binding.textBorrowedAmount.text = "0 kr"
        binding.textLentAmount.text = "0 kr"
    }
    
    private fun updateGreeting() {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val greeting = when (hour) {
            in 0..11 -> "Good morning"
            in 12..17 -> "Good afternoon"
            else -> "Good evening"
        }
        binding.textGreeting.text = greeting
    }
    
    private fun updateSectionVisibility() {
        val accountsVisible = prefs.getBoolean("accounts_visible", true)
        val incomeExpensesVisible = prefs.getBoolean("income_expenses_visible", true)
        val loansVisible = prefs.getBoolean("loans_visible", true)
        val budgetsVisible = prefs.getBoolean("budgets_visible", false)
        val goalsVisible = prefs.getBoolean("goals_visible", false)
        val netWorthVisible = prefs.getBoolean("net_worth_visible", false)
        
        // Apply visibility settings
        binding.cardAccounts.visibility = if (accountsVisible) View.VISIBLE else View.GONE
        binding.cardIncomeExpenses.visibility = if (incomeExpensesVisible) View.VISIBLE else View.GONE
        binding.cardLoans.visibility = if (loansVisible) View.VISIBLE else View.GONE
        binding.cardBudgets.visibility = if (budgetsVisible) View.VISIBLE else View.GONE
        binding.cardGoals.visibility = if (goalsVisible) View.VISIBLE else View.GONE
        binding.cardNetWorth.visibility = if (netWorthVisible) View.VISIBLE else View.GONE
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
                
                // Get loan amounts
                val borrowedAmount = database.loanDao().getActiveAmountByType(LoanType.BORROWED) ?: 0.0
                val lentAmount = database.loanDao().getActiveAmountByType(LoanType.LENT) ?: 0.0
                
                // Net worth is only based on income/expenses, loans are separate
                val netWorth = balance
                
                // Update UI on main thread (check if binding is still valid)
                _binding?.let { binding ->
                    binding.textMonthlyIncome.text = currencyFormat.format(totalIncome)
                    binding.textMonthlyExpenses.text = currencyFormat.format(totalExpenses)
                    binding.textTotalBalance.text = currencyFormat.format(balance)
                    binding.textBorrowedAmount.text = currencyFormat.format(borrowedAmount)
                    binding.textLentAmount.text = currencyFormat.format(lentAmount)
                    binding.textNetWorth.text = currencyFormat.format(netWorth)
                }
            } catch (e: Exception) {
                com.example.budgetapp.utils.ErrorHandler.logError("OverviewFragment", "Failed to load financial data", e)
                // Keep default values if database query fails (check if binding is still valid)
                _binding?.let { binding ->
                    binding.textMonthlyIncome.text = "0 kr"
                    binding.textMonthlyExpenses.text = "0 kr"
                    binding.textTotalBalance.text = "0 kr"
                    binding.textBorrowedAmount.text = "0 kr"
                    binding.textLentAmount.text = "0 kr"
                    binding.textNetWorth.text = "0 kr"
                }
            }
        }
    }
    
    private fun setupClickListeners() {
        try {
            binding.buttonAddExpense.setOnClickListener {
                val intent = Intent(requireContext(), AddExpenseActivity::class.java)
                startActivity(intent)
            }
            
            binding.buttonAddIncome.setOnClickListener {
                val intent = Intent(requireContext(), AddIncomeActivity::class.java)
                startActivity(intent)
            }
            
            binding.buttonAddBorrowed.setOnClickListener {
                val intent = Intent(requireContext(), AddLoanActivity::class.java)
                intent.putExtra("loan_type", LoanType.BORROWED.name)
                startActivity(intent)
            }
            
            binding.buttonAddLent.setOnClickListener {
                val intent = Intent(requireContext(), AddLoanActivity::class.java)
                intent.putExtra("loan_type", LoanType.LENT.name)
                startActivity(intent)
            }
            
            binding.menuOptions.setOnClickListener {
                val intent = Intent(requireContext(), EditHomeActivity::class.java)
                startActivity(intent)
            }
            
            binding.fabAdd.setOnClickListener {
                // Quick action menu - can be expanded later
                val intent = Intent(requireContext(), AddExpenseActivity::class.java)
                startActivity(intent)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    override fun onResume() {
        super.onResume()
        try {
            updateSectionVisibility()
            loadFinancialData()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun applyCustomTheme(settings: ThemeSettings) {
        // Apply custom theme styling specific to OverviewFragment
        val accentColor = themeManager.getAccentColorInt()
        
        // Tag buttons for accent color application
        binding.buttonAddIncome.tag = "accent_button"
        binding.buttonAddExpense.tag = "accent_button"
        binding.buttonAddBorrowed.tag = "accent_button"
        binding.buttonAddLent.tag = "accent_button"
        
        // Apply colorful backgrounds to cards if enabled
        if (settings.interfaceStyle == com.example.budgetapp.data.InterfaceStyle.COLORFUL) {
            applyColorfulCardBackgrounds(accentColor)
        }
    }
    
    private fun applyColorfulCardBackgrounds(accentColor: Int) {
        // Apply subtle colorful backgrounds to overview cards
        binding.cardAccounts.setCardBackgroundColor(adjustColorHue(accentColor, 0.0f, 0.08f))
        binding.cardIncomeExpenses.setCardBackgroundColor(adjustColorHue(accentColor, 0.3f, 0.08f))
        binding.cardLoans.setCardBackgroundColor(adjustColorHue(accentColor, 0.6f, 0.08f))
        binding.cardBudgets?.setCardBackgroundColor(adjustColorHue(accentColor, 0.1f, 0.08f))
        binding.cardGoals?.setCardBackgroundColor(adjustColorHue(accentColor, 0.8f, 0.08f))
        binding.cardNetWorth?.setCardBackgroundColor(adjustColorHue(accentColor, 0.5f, 0.08f))
    }
    
    private fun adjustColorAlpha(color: Int, alpha: Float): Int {
        val a = (255 * alpha).toInt()
        val r = android.graphics.Color.red(color)
        val g = android.graphics.Color.green(color)
        val b = android.graphics.Color.blue(color)
        return android.graphics.Color.argb(a, r, g, b)
    }
    
    private fun adjustColorHue(color: Int, hueShift: Float, alpha: Float): Int {
        val hsv = FloatArray(3)
        android.graphics.Color.colorToHSV(color, hsv)
        hsv[0] = (hsv[0] + hueShift * 360) % 360
        hsv[1] = hsv[1] * 0.3f // Reduce saturation
        hsv[2] = hsv[2] * 0.95f // Slightly reduce brightness
        val adjustedColor = android.graphics.Color.HSVToColor(hsv)
        return adjustColorAlpha(adjustedColor, alpha)
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}