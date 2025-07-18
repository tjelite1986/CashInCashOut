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
import com.example.budgetapp.AddIncomeActivity
import com.example.budgetapp.IncomeAdapter
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.Income
import com.example.budgetapp.databinding.FragmentIncomeBinding
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.*

class IncomeFragment : Fragment() {

    private var _binding: FragmentIncomeBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var database: BudgetDatabase
    private lateinit var incomeAdapter: IncomeAdapter
    private val incomes = mutableListOf<Income>()
    private val currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIncomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        database = BudgetDatabase.getDatabase(requireContext())
        setupUI()
        setupRecyclerView()
        setupClickListeners()
        observeIncomes()
    }

    private fun setupUI() {
        binding.textTitle.text = "Dina inkomster"
        binding.textDescription.text = "Här kan du se och hantera alla dina inkomster"
    }

    private fun setupRecyclerView() {
        incomeAdapter = IncomeAdapter(
            incomes = incomes,
            onEditClick = { income -> editIncome(income) },
            onDeleteClick = { income -> deleteIncome(income) }
        )
        
        binding.recyclerViewIncomes.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = incomeAdapter
        }
    }

    private fun setupClickListeners() {
        binding.fabAddIncome.setOnClickListener {
            val intent = Intent(requireContext(), AddIncomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun observeIncomes() {
        lifecycleScope.launch {
            database.incomeDao().getAllIncomes().collect { incomeList ->
                incomes.clear()
                incomes.addAll(incomeList)
                incomeAdapter.notifyDataSetChanged()
                
                updateUI(incomeList)
            }
        }
    }

    private fun updateUI(incomeList: List<Income>) {
        if (incomeList.isEmpty()) {
            binding.textViewEmpty.visibility = View.VISIBLE
            binding.recyclerViewIncomes.visibility = View.GONE
            binding.textViewEmpty.text = "Inga inkomster ännu\n\nKlicka på + för att lägga till din första inkomst"
            binding.textTotalIncome.text = "0 kr"
        } else {
            binding.textViewEmpty.visibility = View.GONE
            binding.recyclerViewIncomes.visibility = View.VISIBLE
            
            // Calculate total income
            val totalIncome = incomeList.sumOf { it.amount }
            binding.textTotalIncome.text = currencyFormat.format(totalIncome)
        }
    }

    private fun editIncome(income: Income) {
        val intent = Intent(requireContext(), AddIncomeActivity::class.java)
        intent.putExtra("INCOME_ID", income.id)
        startActivity(intent)
    }

    private fun deleteIncome(income: Income) {
        AlertDialog.Builder(requireContext())
            .setTitle("Ta bort inkomst")
            .setMessage("Är du säker på att du vill ta bort \"${income.title}\"?")
            .setPositiveButton("Ta bort") { _, _ ->
                lifecycleScope.launch {
                    database.incomeDao().deleteIncome(income)
                }
            }
            .setNegativeButton("Avbryt", null)
            .show()
    }

    override fun onResume() {
        super.onResume()
        // Refresh data when returning to fragment
        observeIncomes()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}