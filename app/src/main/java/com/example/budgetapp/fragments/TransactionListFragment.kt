package com.example.budgetapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgetapp.Transaction
import com.example.budgetapp.adapters.SelectableTransactionAdapter
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.TransactionType
import com.example.budgetapp.database.entities.Income
import com.example.budgetapp.database.entities.Expense
import com.example.budgetapp.database.entities.Loan
import com.example.budgetapp.databinding.FragmentTransactionListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TransactionListFragment : Fragment() {
    
    private var _binding: FragmentTransactionListBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var adapter: SelectableTransactionAdapter
    private lateinit var database: BudgetDatabase
    
    private var transactionType: TransactionType = TransactionType.INCOME
    private var onSelectionChanged: ((List<SelectableTransactionAdapter.SelectableTransaction>) -> Unit)? = null
    
    companion object {
        private const val ARG_TRANSACTION_TYPE = "transaction_type"
        
        fun newInstance(
            transactionType: TransactionType,
            onSelectionChanged: (List<SelectableTransactionAdapter.SelectableTransaction>) -> Unit
        ): TransactionListFragment {
            return TransactionListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TRANSACTION_TYPE, transactionType.name)
                }
                this.onSelectionChanged = onSelectionChanged
            }
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = BudgetDatabase.getDatabase(requireContext())
        
        arguments?.let {
            transactionType = TransactionType.valueOf(
                it.getString(ARG_TRANSACTION_TYPE, TransactionType.INCOME.name)
            )
        }
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransactionListBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupRecyclerView()
        setupSwipeRefresh()
        loadTransactions()
    }
    
    private fun setupRecyclerView() {
        adapter = SelectableTransactionAdapter { selectedTransactions ->
            onSelectionChanged?.invoke(selectedTransactions)
        }
        
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@TransactionListFragment.adapter
        }
    }
    
    private fun setupSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            loadTransactions()
        }
    }
    
    private fun loadTransactions() {
        binding.progressBar.visibility = View.VISIBLE
        binding.swipeRefresh.isRefreshing = true
        
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val transactions = when (transactionType) {
                    TransactionType.INCOME -> {
                        database.incomeDao().getAllIncomesSnapshot().map { income: Income ->
                            SelectableTransactionAdapter.SelectableTransaction(
                                transaction = Transaction.IncomeTransaction(income),
                                transactionType = TransactionType.INCOME
                            )
                        }
                    }
                    TransactionType.EXPENSE -> {
                        database.expenseDao().getAllExpensesSnapshot().map { expense: Expense ->
                            SelectableTransactionAdapter.SelectableTransaction(
                                transaction = Transaction.ExpenseTransaction(expense),
                                transactionType = TransactionType.EXPENSE
                            )
                        }
                    }
                    TransactionType.LOAN_DUE -> {
                        // For loans, we'll show unpaid loans with due dates
                        database.loanDao().getUnpaidLoans().filter { it.dueDate != null }.map { loan: Loan ->
                            SelectableTransactionAdapter.SelectableTransaction(
                                transaction = Transaction.LoanTransaction(loan),
                                transactionType = TransactionType.LOAN_DUE
                            )
                        }
                    }
                }
                
                // Load existing notification selections
                val existingNotifications = database.transactionNotificationDao()
                    .getNotificationsByType(transactionType)
                val selectedIds = existingNotifications.map { it.transactionId }.toSet()
                
                withContext(Dispatchers.Main) {
                    adapter.submitList(transactions)
                    adapter.setInitialSelection(selectedIds)
                    
                    updateEmptyState(transactions.isEmpty())
                    binding.progressBar.visibility = View.GONE
                    binding.swipeRefresh.isRefreshing = false
                }
                
            } catch (e: Exception) {
                e.printStackTrace()
                withContext(Dispatchers.Main) {
                    updateEmptyState(true)
                    binding.progressBar.visibility = View.GONE
                    binding.swipeRefresh.isRefreshing = false
                }
            }
        }
    }
    
    private fun updateEmptyState(isEmpty: Boolean) {
        if (isEmpty) {
            binding.layoutEmptyState.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        } else {
            binding.layoutEmptyState.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        }
    }
    
    fun selectAll() {
        adapter.selectAll()
    }
    
    fun clearSelection() {
        adapter.clearSelection()
    }
    
    fun getSelectedTransactions(): List<SelectableTransactionAdapter.SelectableTransaction> {
        return adapter.getSelectedTransactions()
    }
    
    fun filterTransactions(query: String, showRecurringOnly: Boolean, showThisMonthOnly: Boolean) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val allTransactions = when (transactionType) {
                    TransactionType.INCOME -> {
                        database.incomeDao().getAllIncomesSnapshot().map { income: Income ->
                            SelectableTransactionAdapter.SelectableTransaction(
                                transaction = Transaction.IncomeTransaction(income),
                                transactionType = TransactionType.INCOME
                            )
                        }
                    }
                    TransactionType.EXPENSE -> {
                        database.expenseDao().getAllExpensesSnapshot().map { expense: Expense ->
                            SelectableTransactionAdapter.SelectableTransaction(
                                transaction = Transaction.ExpenseTransaction(expense),
                                transactionType = TransactionType.EXPENSE
                            )
                        }
                    }
                    TransactionType.LOAN_DUE -> {
                        database.loanDao().getUnpaidLoans().filter { it.dueDate != null }.map { loan: Loan ->
                            SelectableTransactionAdapter.SelectableTransaction(
                                transaction = Transaction.LoanTransaction(loan),
                                transactionType = TransactionType.LOAN_DUE
                            )
                        }
                    }
                }
                
                val filteredTransactions = allTransactions.filter { selectableTransaction ->
                    var matches = true
                    
                    // Text search filter
                    if (query.isNotBlank()) {
                        matches = matches && (
                            selectableTransaction.title.contains(query, ignoreCase = true) ||
                            selectableTransaction.category.contains(query, ignoreCase = true) ||
                            selectableTransaction.description.contains(query, ignoreCase = true)
                        )
                    }
                    
                    // Recurring filter
                    if (showRecurringOnly) {
                        matches = matches && selectableTransaction.isRecurring
                    }
                    
                    // This month filter
                    if (showThisMonthOnly) {
                        val calendar = java.util.Calendar.getInstance()
                        val currentMonth = calendar.get(java.util.Calendar.MONTH)
                        val currentYear = calendar.get(java.util.Calendar.YEAR)
                        
                        calendar.timeInMillis = selectableTransaction.date
                        val transactionMonth = calendar.get(java.util.Calendar.MONTH)
                        val transactionYear = calendar.get(java.util.Calendar.YEAR)
                        
                        matches = matches && (transactionMonth == currentMonth && transactionYear == currentYear)
                    }
                    
                    matches
                }
                
                withContext(Dispatchers.Main) {
                    adapter.submitList(filteredTransactions)
                    updateEmptyState(filteredTransactions.isEmpty())
                }
                
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}