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
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.CancellationException
import java.text.NumberFormat
import java.util.*

class TransactionsFragment : Fragment() {

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
}