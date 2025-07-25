package com.example.budgetapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.budgetapp.data.ThemeSettings
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Job
import kotlinx.coroutines.CancellationException
import java.util.concurrent.CopyOnWriteArrayList
import com.example.budgetapp.BudgetAnalyticsActivity
import com.example.budgetapp.activities.AddEditBudgetActivity
import com.example.budgetapp.adapters.BudgetAdapter
import com.example.budgetapp.databinding.FragmentBudgetBinding
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.repository.BudgetRepository
import com.example.budgetapp.repository.BudgetProgress
import com.example.budgetapp.utils.SampleDataUtils
import com.example.budgetapp.utils.ErrorHandler
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.*

class BudgetFragment : BaseFragment() {

    private var _binding: FragmentBudgetBinding? = null
    private val binding: FragmentBudgetBinding
        get() = _binding ?: throw IllegalStateException("Binding is only valid between onCreateView and onDestroyView")
    
    private lateinit var budgetRepository: BudgetRepository
    private lateinit var budgetAdapter: BudgetAdapter
    private var budgetProgressList = CopyOnWriteArrayList<BudgetProgress>()
    private var observationJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBudgetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            initializeComponents()
            setupRecyclerView()
            setupClickListeners()
            observeBudgets()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    private fun initializeComponents() {
        val database = BudgetDatabase.getDatabase(requireContext())
        budgetRepository = BudgetRepository(database, requireContext())
    }

    private fun setupRecyclerView() {
        budgetAdapter = BudgetAdapter(
            onEditClick = { budgetProgress ->
                openAddEditBudgetActivity(budgetProgress.budget.id)
            },
            onDeleteClick = { budgetProgress ->
                showDeleteConfirmationDialog(budgetProgress)
            }
        )
        
        binding.recyclerBudgets.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = budgetAdapter
        }
    }

    private fun setupClickListeners() {
        binding.fabAddBudget.setOnClickListener {
            openAddEditBudgetActivity()
        }
        
        binding.buttonCreateFirstBudget.setOnClickListener {
            openAddEditBudgetActivity()
        }
        
        // Dold funktion för att skapa exempeldata (för utveckling)
        binding.textTitle.setOnLongClickListener {
            createSampleData()
            true
        }
        
        binding.buttonAnalytics.setOnClickListener {
            openBudgetAnalytics()
        }
        
        binding.buttonFilter.setOnClickListener {
            showFilterDialog()
        }
    }

    private fun observeBudgets() {
        observationJob?.cancel()
        observationJob = lifecycleScope.launch {
            try {
                budgetRepository.getAllActiveBudgets().collect { budgets ->
                    updateBudgetProgressList(budgets)
                    updateSummaryCard()
                    updateEmptyState()
                }
            } catch (e: CancellationException) {
                // Expected when cancelled, do nothing
            } catch (e: Exception) {
                ErrorHandler.logError("BudgetFragment", "Error observing budgets", e)
                ErrorHandler.showErrorSnackbar(
                    binding.root,
                    "Fel vid laddning av budgetar. Försök igen.",
                    "Försök igen"
                ) { observeBudgets() }
            }
        }
    }
    
    private suspend fun updateBudgetProgressList(budgets: List<com.example.budgetapp.database.entities.Budget>) {
        try {
            // Use optimized batch method to avoid N+1 queries
            val progressList = budgetRepository.getBudgetProgressList(budgets)
            budgetProgressList.clear()
            budgetProgressList.addAll(progressList)
            budgetAdapter.submitList(budgetProgressList.toList())
        } catch (e: Exception) {
            ErrorHandler.logError("BudgetFragment", "Error updating budget progress list", e)
        }
    }
    
    private fun updateSummaryCard() {
        try {
            val activeBudgetsCount = budgetProgressList.size
            val totalBudget = budgetProgressList.sumOf { it.budget.budgetAmount }
            val totalSpent = budgetProgressList.sumOf { it.spentAmount }
            
            val numberFormat = NumberFormat.getCurrencyInstance(Locale("sv", "SE"))
            
            binding.textActiveBudgetsCount.text = activeBudgetsCount.toString()
            binding.textTotalBudget.text = numberFormat.format(totalBudget)
            binding.textTotalSpent.text = numberFormat.format(totalSpent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    private fun updateEmptyState() {
        val isEmpty = budgetProgressList.isEmpty()
        binding.layoutEmptyState.visibility = if (isEmpty) View.VISIBLE else View.GONE
        binding.recyclerBudgets.visibility = if (isEmpty) View.GONE else View.VISIBLE
    }

    private fun openAddEditBudgetActivity(budgetId: Int? = null) {
        try {
            val intent = Intent(requireContext(), AddEditBudgetActivity::class.java)
            budgetId?.let { intent.putExtra("BUDGET_ID", it) }
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    private fun openBudgetAnalytics() {
        try {
            val intent = Intent(requireContext(), BudgetAnalyticsActivity::class.java)
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    private fun showDeleteConfirmationDialog(budgetProgress: BudgetProgress) {
        try {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Ta bort budget")
                .setMessage("Är du säker på att du vill ta bort budgeten \"${budgetProgress.budget.name}\"?")
                .setPositiveButton("Ta bort") { _, _ ->
                    deleteBudget(budgetProgress.budget)
                }
                .setNegativeButton("Avbryt", null)
                .show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    private fun deleteBudget(budget: com.example.budgetapp.database.entities.Budget) {
        lifecycleScope.launch {
            try {
                budgetRepository.deleteBudget(budget)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    
    private fun showFilterDialog() {
        try {
            val filterOptions = arrayOf(
                "Alla budgetar",
                "Aktiva (inom budget)", 
                "Över budget",
                "Nära gränsen (>80%)",
                "Veckobudgetar",
                "Månadsbudgetar",
                "Kvartal/Årsbudgetar"
            )
            
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Filtrera budgetar")
                .setItems(filterOptions) { dialog, which ->
                    when (which) {
                        0 -> filterBudgets { true } // Alla
                        1 -> filterBudgets { !it.isOverBudget && !it.isNearLimit } // Aktiva inom budget
                        2 -> filterBudgets { it.isOverBudget } // Över budget
                        3 -> filterBudgets { it.isNearLimit && !it.isOverBudget } // Nära gränsen
                        4 -> filterBudgets { it.budget.period == com.example.budgetapp.database.entities.BudgetPeriod.WEEKLY } // Veckobudgetar
                        5 -> filterBudgets { it.budget.period == com.example.budgetapp.database.entities.BudgetPeriod.MONTHLY } // Månadsbudgetar
                        6 -> filterBudgets { 
                            it.budget.period == com.example.budgetapp.database.entities.BudgetPeriod.QUARTERLY || 
                            it.budget.period == com.example.budgetapp.database.entities.BudgetPeriod.YEARLY 
                        } // Kvartal/År
                    }
                    dialog.dismiss()
                }
                .setNegativeButton("Avbryt", null)
                .show()
        } catch (e: Exception) {
            ErrorHandler.logError("BudgetFragment", "Error showing filter dialog", e)
        }
    }
    
    private fun filterBudgets(predicate: (BudgetProgress) -> Boolean) {
        val filteredList = budgetProgressList.filter(predicate)
        budgetAdapter.submitList(filteredList)
        
        // Show feedback for empty results
        if (filteredList.isEmpty() && budgetProgressList.isNotEmpty()) {
            ErrorHandler.showErrorSnackbar(
                binding.root,
                "Inga budgetar matchar filtret",
                "Rensa filter"
            ) { resetFilter() }
        }
    }
    
    private fun resetFilter() {
        budgetAdapter.submitList(budgetProgressList.toList())
    }
    
    private fun createSampleData() {
        lifecycleScope.launch {
            try {
                SampleDataUtils.createSampleBudgets(budgetRepository)
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Exempeldata skapad")
                    .setMessage("Tre exempelbudgetar har lagts till för att demonstrera funktionaliteten.")
                    .setPositiveButton("OK", null)
                    .show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        observationJob?.cancel()
        _binding = null
    }
    
    override fun applyCustomTheme(settings: ThemeSettings) {
        // Apply custom theme styling specific to BudgetFragment
        val accentColor = themeManager.getAccentColorInt()
        
        // Update FAB color
        binding.fabAddBudget.backgroundTintList = android.content.res.ColorStateList.valueOf(accentColor)
        
        // Update adapter theme
        if (::budgetAdapter.isInitialized) {
            budgetAdapter.applyTheme(settings)
        }
    }
}