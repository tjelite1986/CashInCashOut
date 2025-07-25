package com.example.budgetapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Job
import com.example.budgetapp.AddExpenseActivity
import com.example.budgetapp.AddIncomeActivity
import com.example.budgetapp.Transaction
import com.example.budgetapp.TransactionAdapter
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.databinding.FragmentTransactionsBinding
import com.example.budgetapp.data.ThemeSettings
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.CancellationException
import java.text.NumberFormat
import java.util.*

class TransactionsFragment : BaseFragment() {

    private var _binding: FragmentTransactionsBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var database: BudgetDatabase
    private lateinit var transactionAdapter: TransactionAdapter
    private val currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
    
    private var allTransactions = listOf<Transaction>()
    private var isFabExpanded = false
    private var dataLoadingJob: Job? = null

    enum class FilterType {
        ALL, INCOME, EXPENSES
    }
    
    private var currentFilter = FilterType.ALL

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        try {
            _binding = FragmentTransactionsBinding.inflate(inflater, container, false)
            return binding.root
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        try {
            database = BudgetDatabase.getDatabase(requireContext())
            setupRecyclerView()
            setupClickListeners()
            setupChipFilters()
            loadTransactions()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(requireContext(), "Fel vid uppstart: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun setupRecyclerView() {
        try {
            transactionAdapter = TransactionAdapter(
                transactions = emptyList(),
                onEditClick = { transaction -> editTransaction(transaction) },
                onDeleteClick = { transaction -> showDeleteDialog(transaction) }
            )
            
            binding.recyclerTransactions.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = transactionAdapter
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(requireContext(), "Fel vid setup av RecyclerView: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun setupClickListeners() {
        binding.fabMain.setOnClickListener {
            if (_binding != null) {
                toggleFabMenu()
            }
        }
        
        binding.fabIncome.setOnClickListener {
            if (_binding != null) {
                collapseFabMenu()
                val intent = Intent(requireContext(), AddIncomeActivity::class.java)
                startActivity(intent)
            }
        }
        
        binding.fabExpense.setOnClickListener {
            if (_binding != null) {
                collapseFabMenu()
                val intent = Intent(requireContext(), AddExpenseActivity::class.java)
                startActivity(intent)
            }
        }
        
        binding.btnAddIncomeEmpty.setOnClickListener {
            if (_binding != null) {
                val intent = Intent(requireContext(), AddIncomeActivity::class.java)
                startActivity(intent)
            }
        }
        
        binding.btnAddExpenseEmpty.setOnClickListener {
            if (_binding != null) {
                val intent = Intent(requireContext(), AddExpenseActivity::class.java)
                startActivity(intent)
            }
        }
    }
    
    private fun setupChipFilters() {
        binding.chipGroupFilter.setOnCheckedStateChangeListener { _, checkedIds ->
            when (checkedIds.firstOrNull()) {
                binding.chipAll.id -> {
                    currentFilter = FilterType.ALL
                    filterTransactions()
                }
                binding.chipIncome.id -> {
                    currentFilter = FilterType.INCOME
                    filterTransactions()
                }
                binding.chipExpenses.id -> {
                    currentFilter = FilterType.EXPENSES
                    filterTransactions()
                }
            }
        }
    }

    private fun toggleFabMenu() {
        if (isFabExpanded) {
            collapseFabMenu()
        } else {
            expandFabMenu()
        }
    }
    
    private fun expandFabMenu() {
        if (_binding == null) return
        
        try {
            isFabExpanded = true
            binding.fabIncome.visibility = View.VISIBLE
            binding.fabExpense.visibility = View.VISIBLE
            
            binding.fabIncome.animate()
                .translationY(-140f)
                .alpha(1f)
                .setDuration(300)
                .start()
                
            binding.fabExpense.animate()
                .translationY(-210f)
                .alpha(1f)
                .setDuration(300)
                .start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    private fun collapseFabMenu() {
        if (_binding == null) return
        
        try {
            isFabExpanded = false
            
            binding.fabIncome.animate()
                .translationY(0f)
                .alpha(0f)
                .setDuration(300)
                .withEndAction {
                    try {
                        if (_binding != null) {
                            binding.fabIncome.visibility = View.GONE
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                .start()
                
            binding.fabExpense.animate()
                .translationY(0f)
                .alpha(0f)
                .setDuration(300)
                .withEndAction {
                    try {
                        if (_binding != null) {
                            binding.fabExpense.visibility = View.GONE
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                .start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun loadTransactions() {
        dataLoadingJob?.cancel()
        dataLoadingJob = lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                try {
                    combine(
                        database.incomeDao().getAllIncomes(),
                        database.expenseDao().getAllExpenses()
                    ) { incomes, expenses ->
                        val transactions = mutableListOf<Transaction>()
                        
                        incomes.forEach { income ->
                            transactions.add(Transaction.IncomeTransaction(income))
                        }
                        
                        expenses.forEach { expense ->
                            transactions.add(Transaction.ExpenseTransaction(expense))
                        }
                        
                        // Sort by date (newest first)
                        transactions.sortedByDescending { it.getDate() }
                    }.collect { transactions ->
                        if (_binding != null) {
                            allTransactions = transactions
                            updateSummary()
                            filterTransactions()
                        }
                    }
                } catch (e: CancellationException) {
                    // Fragment lifecycle cancelled the job - this is normal, don't show error
                } catch (e: Exception) {
                    e.printStackTrace()
                    if (_binding != null) {
                        Toast.makeText(requireContext(), "Fel vid laddning av transaktioner: ${e.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
    
    private fun updateSummary() {
        if (_binding == null) return
        
        val totalIncome = allTransactions.filterIsInstance<Transaction.IncomeTransaction>()
            .sumOf { it.income.amount }
        val totalExpenses = allTransactions.filterIsInstance<Transaction.ExpenseTransaction>()
            .sumOf { it.expense.amount }
            
        binding.textIncomeTotal.text = currencyFormat.format(totalIncome)
        binding.textExpensesTotal.text = currencyFormat.format(totalExpenses)
    }
    
    private fun filterTransactions() {
        if (_binding == null) return
        
        val filteredTransactions = when (currentFilter) {
            FilterType.ALL -> allTransactions
            FilterType.INCOME -> allTransactions.filterIsInstance<Transaction.IncomeTransaction>()
            FilterType.EXPENSES -> allTransactions.filterIsInstance<Transaction.ExpenseTransaction>()
        }
        
        transactionAdapter.updateTransactions(filteredTransactions)
        
        // Show empty state if no transactions
        if (filteredTransactions.isEmpty()) {
            binding.recyclerTransactions.visibility = View.GONE
            binding.layoutEmptyState.visibility = View.VISIBLE
            
            when (currentFilter) {
                FilterType.ALL -> {
                    binding.textEmptyTitle.text = "Inga transaktioner"
                    binding.textEmptyMessage.text = "Lägg till din första transaktion genom att trycka på + knappen"
                }
                FilterType.INCOME -> {
                    binding.textEmptyTitle.text = "Inga inkomster"
                    binding.textEmptyMessage.text = "Lägg till din första inkomst"
                }
                FilterType.EXPENSES -> {
                    binding.textEmptyTitle.text = "Inga utgifter"
                    binding.textEmptyMessage.text = "Lägg till din första utgift"
                }
            }
        } else {
            binding.recyclerTransactions.visibility = View.VISIBLE
            binding.layoutEmptyState.visibility = View.GONE
        }
    }

    private fun editTransaction(transaction: Transaction) {
        when (transaction) {
            is Transaction.IncomeTransaction -> {
                val intent = Intent(requireContext(), AddIncomeActivity::class.java)
                intent.putExtra("income_id", transaction.income.id)
                startActivity(intent)
            }
            is Transaction.ExpenseTransaction -> {
                val intent = Intent(requireContext(), AddExpenseActivity::class.java)
                intent.putExtra("expense_id", transaction.expense.id)
                startActivity(intent)
            }
            is Transaction.LoanTransaction -> {
                val intent = Intent(requireContext(), com.example.budgetapp.AddLoanActivity::class.java)
                intent.putExtra("loan_id", transaction.loan.id)
                startActivity(intent)
            }
        }
    }

    private fun showDeleteDialog(transaction: Transaction) {
        val title = if (transaction.isIncome()) "Ta bort inkomst" else "Ta bort utgift"
        val message = "Är du säker på att du vill ta bort \"${transaction.getDescription()}\"?"
        
        AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Ta bort") { _, _ ->
                deleteTransaction(transaction)
            }
            .setNegativeButton("Avbryt", null)
            .show()
    }

    private fun deleteTransaction(transaction: Transaction) {
        lifecycleScope.launch {
            try {
                when (transaction) {
                    is Transaction.IncomeTransaction -> {
                        database.incomeDao().deleteIncome(transaction.income)
                    }
                    is Transaction.ExpenseTransaction -> {
                        database.expenseDao().deleteExpense(transaction.expense)
                    }
                    is Transaction.LoanTransaction -> {
                        database.loanDao().deleteLoan(transaction.loan)
                    }
                }
                if (_binding != null) {
                    Toast.makeText(requireContext(), "Transaktion borttagen", Toast.LENGTH_SHORT).show()
                }
            } catch (e: CancellationException) {
                // Fragment lifecycle cancelled the job - this is normal
            } catch (e: Exception) {
                e.printStackTrace()
                if (_binding != null) {
                    Toast.makeText(requireContext(), "Fel vid borttagning: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Data loading is handled by the Flow in onViewCreated
    }
    
    override fun onPause() {
        super.onPause()
        // Collapse FAB menu when leaving fragment
        if (_binding != null && isFabExpanded) {
            try {
                binding.fabIncome.animate().cancel()
                binding.fabExpense.animate().cancel()
                binding.fabIncome.visibility = View.GONE
                binding.fabExpense.visibility = View.GONE
                isFabExpanded = false
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onDestroyView() {
        try {
            // Cancel any ongoing data loading
            dataLoadingJob?.cancel()
            
            // Cancel any ongoing animations
            _binding?.let { binding ->
                binding.fabIncome.animate().cancel()
                binding.fabExpense.animate().cancel()
                binding.fabMain.animate().cancel()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        super.onDestroyView()
        _binding = null
    }
    
    override fun applyCustomTheme(settings: ThemeSettings) {
        // Apply custom theme styling specific to TransactionsFragment
        val accentColor = themeManager.getAccentColorInt()
        
        // Update FAB colors
        binding.fabMain.backgroundTintList = android.content.res.ColorStateList.valueOf(accentColor)
        binding.fabIncome.backgroundTintList = android.content.res.ColorStateList.valueOf(accentColor)
        binding.fabExpense.backgroundTintList = android.content.res.ColorStateList.valueOf(accentColor)
        
        // Apply theme to filter chips
        applyThemeToChips(settings)
        
        // Apply colorful styling to summary cards if enabled
        if (settings.interfaceStyle == com.example.budgetapp.data.InterfaceStyle.COLORFUL) {
            applyColorfulSummaryCards(accentColor)
        }
        
        // Update adapter theme
        if (::transactionAdapter.isInitialized) {
            transactionAdapter.applyTheme(settings)
        }
    }
    
    private fun applyColorfulSummaryCards(accentColor: Int) {
        // Apply very subtle colorful backgrounds to summary cards
        
        // Income card with very subtle green tint
        binding.cardIncomeSummary.setCardBackgroundColor(
            adjustColorHue(accentColor, 0.33f, 0.04f) // Much more subtle green
        )
        
        // Expense card with very subtle red tint
        binding.cardExpensesSummary.setCardBackgroundColor(
            adjustColorHue(accentColor, 0.0f, 0.04f) // Much more subtle red
        )
    }
    
    private fun adjustColorAlpha(color: Int, alpha: Float): Int {
        val a = (255 * alpha).toInt()
        val r = android.graphics.Color.red(color)
        val g = android.graphics.Color.green(color)
        val b = android.graphics.Color.blue(color)
        return android.graphics.Color.argb(a, r, g, b)
    }
    
    private fun applyThemeToChips(settings: ThemeSettings) {
        val accentColor = themeManager.getAccentColorInt()
        val isColorful = settings.interfaceStyle == com.example.budgetapp.data.InterfaceStyle.COLORFUL
        
        val chips = listOf(binding.chipAll, binding.chipIncome, binding.chipExpenses)
        
        chips.forEach { chip ->
            val context = requireContext()
            
            if (isColorful) {
                // Subtle colorful styling
                val subtleAccent = adjustColorAlpha(accentColor, 0.12f)
                val textColor = adjustColorBrightness(accentColor, 0.6f) // Darker for better readability
                
                chip.chipBackgroundColor = android.content.res.ColorStateList.valueOf(subtleAccent)
                chip.setTextColor(android.content.res.ColorStateList.valueOf(textColor))
                chip.chipStrokeColor = android.content.res.ColorStateList.valueOf(adjustColorAlpha(accentColor, 0.2f))
                chip.checkedIconTint = android.content.res.ColorStateList.valueOf(textColor)
            } else {
                // Clean material design colors
                val isDark = themeManager.isDarkMode()
                
                if (isDark) {
                    chip.chipBackgroundColor = android.content.res.ColorStateList.valueOf(
                        context.getColor(com.google.android.material.R.color.material_dynamic_neutral20)
                    )
                    chip.setTextColor(android.content.res.ColorStateList.valueOf(
                        context.getColor(com.google.android.material.R.color.material_dynamic_neutral90)
                    ))
                } else {
                    chip.chipBackgroundColor = android.content.res.ColorStateList.valueOf(
                        context.getColor(com.google.android.material.R.color.material_dynamic_neutral95)
                    )
                    chip.setTextColor(android.content.res.ColorStateList.valueOf(
                        context.getColor(com.google.android.material.R.color.material_dynamic_neutral10)
                    ))
                }
                
                chip.chipStrokeColor = android.content.res.ColorStateList.valueOf(
                    adjustColorAlpha(accentColor, 0.3f)
                )
                chip.checkedIconTint = android.content.res.ColorStateList.valueOf(accentColor)
            }
        }
    }
    
    private fun adjustColorBrightness(color: Int, factor: Float): Int {
        val red = (android.graphics.Color.red(color) * factor).toInt().coerceIn(0, 255)
        val green = (android.graphics.Color.green(color) * factor).toInt().coerceIn(0, 255)
        val blue = (android.graphics.Color.blue(color) * factor).toInt().coerceIn(0, 255)
        return android.graphics.Color.rgb(red, green, blue)
    }
    
    private fun adjustColorHue(baseColor: Int, hueShift: Float, alpha: Float): Int {
        val hsv = FloatArray(3)
        android.graphics.Color.colorToHSV(baseColor, hsv)
        hsv[0] = (hsv[0] + hueShift * 360) % 360
        hsv[1] = hsv[1] * 0.3f // Much more subtle saturation
        hsv[2] = hsv[2] * 0.9f // Slightly reduce brightness
        val adjustedColor = android.graphics.Color.HSVToColor(hsv)
        return adjustColorAlpha(adjustedColor, alpha)
    }
}