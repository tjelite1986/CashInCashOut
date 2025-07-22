package com.example.budgetapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.budgetapp.R
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.LoanType
import com.example.budgetapp.databinding.FragmentSimpleStatisticsBinding
import kotlinx.coroutines.launch
import kotlin.math.abs

class SimpleStatisticsFragment : Fragment() {

    private var _binding: FragmentSimpleStatisticsBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var database: BudgetDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSimpleStatisticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        try {
            database = BudgetDatabase.getDatabase(requireContext())
            loadStatistics()
        } catch (e: Exception) {
            e.printStackTrace()
            binding.apply {
                textTotalExpenses.text = "Fel vid laddning: ${e.message}"
                textTotalIncome.text = "Fel vid laddning"
                textNetResult.text = "Fel vid laddning"
                textTotalLoans.text = "Fel vid laddning"
                textNetWorth.text = "Fel vid laddning"
            }
        }
    }

    private fun loadStatistics() {
        lifecycleScope.launch {
            try {
                loadSummaryStatistics()
            } catch (e: Exception) {
                e.printStackTrace()
                binding.apply {
                    textTotalExpenses.text = "Databasfel: ${e.message}"
                    textTotalIncome.text = "Databasfel"
                    textNetResult.text = "Databasfel"
                    textTotalLoans.text = "Databasfel"
                    textNetWorth.text = "Databasfel"
                }
            }
        }
    }

    private suspend fun loadSummaryStatistics() {
        val totalExpenses = database.expenseDao().getTotalExpenses() ?: 0.0
        val totalIncome = database.incomeDao().getTotalIncome() ?: 0.0
        val totalBorrowed = database.loanDao().getActiveAmountByType(LoanType.BORROWED) ?: 0.0
        val totalLent = database.loanDao().getActiveAmountByType(LoanType.LENT) ?: 0.0
        
        val netWorth = totalIncome - totalExpenses + totalLent - totalBorrowed

        binding.apply {
            textTotalExpenses.text = "${String.format("%.0f", totalExpenses)} kr"
            textTotalIncome.text = "${String.format("%.0f", totalIncome)} kr"
            textNetResult.text = "${String.format("%.0f", totalIncome - totalExpenses)} kr"
            textTotalLoans.text = "${String.format("%.0f", abs(totalBorrowed - totalLent))} kr"
            textNetWorth.text = "${String.format("%.0f", netWorth)} kr"
            
            // Set colors based on positive/negative values
            textNetResult.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    if (totalIncome - totalExpenses >= 0) R.color.chart_income_color else R.color.chart_expense_color
                )
            )
            
            textNetWorth.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    if (netWorth >= 0) R.color.chart_income_color else R.color.chart_expense_color
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}