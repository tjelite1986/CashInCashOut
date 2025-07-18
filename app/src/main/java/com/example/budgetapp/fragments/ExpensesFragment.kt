package com.example.budgetapp.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgetapp.AddExpenseActivity
import com.example.budgetapp.ExpenseAdapter
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.Expense
import com.example.budgetapp.databinding.FragmentExpensesBinding
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.*

class ExpensesFragment : Fragment() {

    private var _binding: FragmentExpensesBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var database: BudgetDatabase
    private lateinit var expenseAdapter: ExpenseAdapter
    private val expenses = mutableListOf<Expense>()
    private val currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExpensesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        database = BudgetDatabase.getDatabase(requireContext())
        setupUI()
        setupRecyclerView()
        setupClickListeners()
        observeExpenses()
    }

    private fun setupUI() {
        binding.textTitle.text = "Dina utgifter"
        binding.textDescription.text = "Här kan du se och hantera alla dina utgifter"
    }

    private fun setupRecyclerView() {
        expenseAdapter = ExpenseAdapter(
            expenses = expenses,
            onEditClick = { expense -> editExpense(expense) },
            onDeleteClick = { expense -> deleteExpense(expense) }
        )
        
        binding.recyclerViewExpenses.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = expenseAdapter
        }
    }

    private fun setupClickListeners() {
        binding.fabAddExpense.setOnClickListener {
            val intent = Intent(requireContext(), AddExpenseActivity::class.java)
            startActivity(intent)
        }
    }

    private fun observeExpenses() {
        lifecycleScope.launch {
            database.expenseDao().getAllExpenses().collect { expenseList ->
                expenses.clear()
                expenses.addAll(expenseList)
                expenseAdapter.notifyDataSetChanged()
                
                updateUI(expenseList)
            }
        }
    }

    private fun updateUI(expenseList: List<Expense>) {
        if (expenseList.isEmpty()) {
            binding.textViewEmpty.visibility = View.VISIBLE
            binding.recyclerViewExpenses.visibility = View.GONE
            binding.textViewEmpty.text = "Inga utgifter ännu\n\nKlicka på + för att lägga till din första utgift"
            binding.textTotalExpenses.text = "0 kr"
        } else {
            binding.textViewEmpty.visibility = View.GONE
            binding.recyclerViewExpenses.visibility = View.VISIBLE
            
            // Calculate total expenses
            val totalExpenses = expenseList.sumOf { it.amount }
            binding.textTotalExpenses.text = currencyFormat.format(totalExpenses)
        }
    }

    private fun editExpense(expense: Expense) {
        val intent = Intent(requireContext(), AddExpenseActivity::class.java)
        intent.putExtra("EXPENSE_ID", expense.id)
        startActivity(intent)
    }

    private fun deleteExpense(expense: Expense) {
        AlertDialog.Builder(requireContext())
            .setTitle("Ta bort utgift")
            .setMessage("Är du säker på att du vill ta bort \"${expense.title}\"?")
            .setPositiveButton("Ta bort") { _, _ ->
                lifecycleScope.launch {
                    database.expenseDao().deleteExpense(expense)
                }
            }
            .setNegativeButton("Avbryt", null)
            .show()
    }

    override fun onResume() {
        super.onResume()
        // Refresh data when returning to fragment
        observeExpenses()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}