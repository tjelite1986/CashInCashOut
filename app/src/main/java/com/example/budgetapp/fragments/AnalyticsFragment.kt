package com.example.budgetapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgetapp.adapters.InsightAdapter
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.databinding.FragmentAnalyticsBinding
import com.example.budgetapp.services.AdvancedAnalyticsService
import com.example.budgetapp.viewmodels.AnalyticsViewModel
import com.example.budgetapp.viewmodels.AnalyticsViewModelFactory
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale

class AnalyticsFragment : Fragment() {
    
    private var _binding: FragmentAnalyticsBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var analyticsViewModel: AnalyticsViewModel
    private lateinit var insightAdapter: InsightAdapter
    private lateinit var analyticsService: AdvancedAnalyticsService
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnalyticsBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupViewModel()
        setupRecyclerView()
        setupAnalyticsService()
        setupUI()
        loadAnalyticsData()
    }
    
    private fun setupViewModel() {
        val database = BudgetDatabase.getDatabase(requireContext())
        val factory = AnalyticsViewModelFactory(database)
        analyticsViewModel = ViewModelProvider(this, factory)[AnalyticsViewModel::class.java]
    }
    
    private fun setupRecyclerView() {
        insightAdapter = InsightAdapter { insight ->
            // Handle insight click - mark as read and show details
            analyticsViewModel.markInsightAsRead(insight.id)
            showInsightDetails(insight)
        }
        
        binding.recyclerViewInsights.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = insightAdapter
        }
    }
    
    private fun setupAnalyticsService() {
        analyticsService = AdvancedAnalyticsService(requireContext())
    }
    
    private fun setupUI() {
        // Setup refresh functionality
        binding.swipeRefreshLayout.setOnRefreshListener {
            refreshAnalytics()
        }
        
        // Setup financial health card click
        binding.cardFinancialHealth.setOnClickListener {
            showFinancialHealthDetails()
        }
        
        // Setup insights header click to show all insights
        binding.textViewInsightsHeader.setOnClickListener {
            showAllInsights()
        }
        
        // Setup generate insights button
        binding.buttonGenerateInsights.setOnClickListener {
            generateNewInsights()
        }
    }
    
    private fun loadAnalyticsData() {
        // Observe financial insights
        analyticsViewModel.unreadInsights.observe(viewLifecycleOwner) { insights ->
            insightAdapter.submitList(insights.take(5)) // Show top 5 insights
            
            binding.textViewNoInsights.visibility = if (insights.isEmpty()) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
        
        // Observe financial health score
        analyticsViewModel.financialHealthScore.observe(viewLifecycleOwner) { score ->
            updateFinancialHealthUI(score)
        }
        
        // Observe insight counts
        analyticsViewModel.insightCounts.observe(viewLifecycleOwner) { counts ->
            updateInsightCountsUI(counts)
        }
        
        // Observe active goals
        analyticsViewModel.activeGoals.observe(viewLifecycleOwner) { goals ->
            updateGoalsProgressUI(goals)
        }
    }
    
    private fun updateFinancialHealthUI(score: Double) {
        val percentage = (score * 100).toInt()
        binding.textViewHealthScore.text = "$percentage/100"
        
        // Update progress bar
        binding.progressBarHealthScore.progress = percentage
        
        // Update health status text and color
        val (statusText, colorRes) = when {
            score >= 0.8 -> Pair("Excellent", android.R.color.holo_green_dark)
            score >= 0.6 -> Pair("Good", android.R.color.holo_blue_dark)
            score >= 0.4 -> Pair("Fair", android.R.color.holo_orange_dark)
            else -> Pair("Needs Attention", android.R.color.holo_red_dark)
        }
        
        binding.textViewHealthStatus.text = statusText
        binding.textViewHealthStatus.setTextColor(resources.getColor(colorRes, null))
        binding.progressBarHealthScore.progressTintList = 
            resources.getColorStateList(colorRes, null)
    }
    
    private fun updateInsightCountsUI(counts: Map<String, Int>) {
        val totalInsights = counts.values.sum()
        binding.textViewTotalInsights.text = totalInsights.toString()
        
        // Show breakdown of insight types
        val criticalCount = counts["CRITICAL"] ?: 0
        val highCount = counts["HIGH"] ?: 0
        val mediumCount = counts["MEDIUM"] ?: 0
        
        binding.textViewCriticalInsights.text = criticalCount.toString()
        binding.textViewHighInsights.text = highCount.toString()
        binding.textViewMediumInsights.text = mediumCount.toString()
        
        // Show/hide warning indicators
        binding.iconCriticalWarning.visibility = if (criticalCount > 0) View.VISIBLE else View.GONE
        binding.iconHighWarning.visibility = if (highCount > 0) View.VISIBLE else View.GONE
    }
    
    private fun updateGoalsProgressUI(goals: List<com.example.budgetapp.database.entities.FinancialGoal>) {
        if (goals.isEmpty()) {
            binding.layoutGoalsProgress.visibility = View.GONE
            return
        }
        
        binding.layoutGoalsProgress.visibility = View.VISIBLE
        
        val totalProgress = goals.map { it.progressPercentage }.average()
        val activeGoalsCount = goals.size
        val completedGoals = goals.count { it.progressPercentage >= 100.0 }
        
        binding.textViewActiveGoals.text = activeGoalsCount.toString()
        binding.textViewCompletedGoals.text = completedGoals.toString()
        binding.textViewAverageProgress.text = "${totalProgress.toInt()}%"
        
        binding.progressBarGoalsOverall.progress = totalProgress.toInt()
    }
    
    private fun refreshAnalytics() {
        lifecycleScope.launch {
            try {
                binding.swipeRefreshLayout.isRefreshing = true
                
                // Generate fresh insights
                analyticsService.generateAllInsights()
                
                // Refresh ViewModels
                analyticsViewModel.refreshData()
                
            } catch (e: Exception) {
                e.printStackTrace()
                // Show error message
                showError("Failed to refresh analytics: ${e.message}")
            } finally {
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }
    }
    
    private fun generateNewInsights() {
        lifecycleScope.launch {
            try {
                binding.buttonGenerateInsights.isEnabled = false
                binding.buttonGenerateInsights.text = "Generating..."
                
                analyticsService.generateAllInsights()
                analyticsViewModel.refreshData()
                
                showSuccess("New insights generated successfully!")
                
            } catch (e: Exception) {
                e.printStackTrace()
                showError("Failed to generate insights: ${e.message}")
            } finally {
                binding.buttonGenerateInsights.isEnabled = true
                binding.buttonGenerateInsights.text = "Generate Insights"
            }
        }
    }
    
    private fun showInsightDetails(insight: com.example.budgetapp.database.entities.FinancialInsight) {
        // Create and show insight details dialog
        val dialog = InsightDetailsDialogFragment.newInstance(insight)
        dialog.show(parentFragmentManager, "insight_details")
    }
    
    private fun showFinancialHealthDetails() {
        // TODO: Navigate to detailed financial health screen
        showSuccess("Financial health details - coming soon!")
    }
    
    private fun showAllInsights() {
        // TODO: Navigate to all insights screen
        showSuccess("All insights view - coming soon!")
    }
    
    private fun showError(message: String) {
        com.google.android.material.snackbar.Snackbar.make(
            binding.root,
            message,
            com.google.android.material.snackbar.Snackbar.LENGTH_LONG
        ).show()
    }
    
    private fun showSuccess(message: String) {
        com.google.android.material.snackbar.Snackbar.make(
            binding.root,
            message,
            com.google.android.material.snackbar.Snackbar.LENGTH_SHORT
        ).show()
    }
    
    private fun findNavController(): androidx.navigation.NavController {
        return androidx.navigation.Navigation.findNavController(requireView())
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}