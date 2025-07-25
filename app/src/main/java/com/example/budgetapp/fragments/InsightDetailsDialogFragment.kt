package com.example.budgetapp.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.budgetapp.R
import com.example.budgetapp.database.entities.FinancialInsight
import com.example.budgetapp.database.entities.InsightSeverity
import com.example.budgetapp.database.entities.InsightType
import com.example.budgetapp.databinding.DialogInsightDetailsBinding
import java.text.SimpleDateFormat
import java.util.*

class InsightDetailsDialogFragment : DialogFragment() {
    
    private var _binding: DialogInsightDetailsBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var insight: FinancialInsight
    
    companion object {
        private const val ARG_INSIGHT = "insight"
        
        fun newInstance(insight: FinancialInsight): InsightDetailsDialogFragment {
            val fragment = InsightDetailsDialogFragment()
            val args = Bundle()
            args.putSerializable(ARG_INSIGHT, insight)
            fragment.arguments = args
            return fragment
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        insight = arguments?.getSerializable(ARG_INSIGHT) as FinancialInsight
        setStyle(STYLE_NORMAL, R.style.Theme_BudgetApp_Dialog)
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogInsightDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupClickListeners()
    }
    
    private fun setupUI() {
        binding.apply {
            // Basic info
            textViewTitle.text = insight.title
            textViewDescription.text = insight.description
            
            // Date
            val dateFormat = SimpleDateFormat("EEEE, MMM dd, yyyy 'at' HH:mm", Locale.getDefault())
            textViewDate.text = dateFormat.format(Date(insight.createdAt))
            
            // Severity
            setupSeverityDisplay()
            
            // Insight type
            setupInsightTypeDisplay()
            
            // Confidence score
            val confidencePercentage = (insight.confidenceScore * 100).toInt()
            textViewConfidence.text = "${confidencePercentage}%"
            progressBarConfidence.progress = confidencePercentage
            
            // Amount and percentage
            setupAmountDisplay()
            
            // Category
            if (!insight.category.isNullOrBlank()) {
                textViewCategory.text = insight.category
                layoutCategory.visibility = View.VISIBLE
            } else {
                layoutCategory.visibility = View.GONE
            }
            
            // Suggested action
            if (insight.actionable && !insight.suggestedAction.isNullOrBlank()) {
                textViewSuggestedAction.text = insight.suggestedAction
                layoutSuggestedAction.visibility = View.VISIBLE
            } else {
                layoutSuggestedAction.visibility = View.GONE
            }
            
            // Additional data
            if (!insight.data.isNullOrBlank()) {
                textViewAdditionalData.text = insight.data
                layoutAdditionalData.visibility = View.VISIBLE
            } else {
                layoutAdditionalData.visibility = View.GONE
            }
        }
    }
    
    private fun setupSeverityDisplay() {
        val (colorRes, textRes, backgroundRes) = when (insight.severity) {
            InsightSeverity.CRITICAL -> Triple(
                R.color.severity_critical,
                "CRITICAL",
                R.color.severity_critical_background
            )
            InsightSeverity.HIGH -> Triple(
                R.color.severity_high,
                "HIGH PRIORITY",
                R.color.severity_high_background
            )
            InsightSeverity.MEDIUM -> Triple(
                R.color.severity_medium,
                "MEDIUM",
                R.color.severity_medium_background
            )
            InsightSeverity.LOW -> Triple(
                R.color.severity_low,
                "LOW",
                R.color.severity_low_background
            )
            InsightSeverity.INFO -> Triple(
                R.color.severity_info,
                "INFORMATION",
                R.color.severity_info_background
            )
        }
        
        binding.textViewSeverity.apply {
            text = textRes
            setTextColor(ContextCompat.getColor(requireContext(), colorRes))
            setBackgroundColor(ContextCompat.getColor(requireContext(), backgroundRes))
        }
    }
    
    private fun setupInsightTypeDisplay() {
        val (iconRes, typeText) = when (insight.insightType) {
            InsightType.SPENDING_ANOMALY -> Pair(R.drawable.ic_warning, "Spending Anomaly")
            InsightType.BUDGET_WARNING -> Pair(R.drawable.ic_budget, "Budget Warning")
            InsightType.SAVING_OPPORTUNITY -> Pair(R.drawable.ic_savings, "Saving Opportunity")
            InsightType.INCOME_PATTERN -> Pair(R.drawable.ic_income, "Income Pattern")
            InsightType.EXPENSE_TREND -> Pair(R.drawable.ic_expenses, "Expense Trend")
            InsightType.GOAL_PROGRESS -> Pair(R.drawable.ic_goal, "Goal Progress")
            InsightType.SEASONAL_PATTERN -> Pair(R.drawable.ic_calendar, "Seasonal Pattern")
            InsightType.CATEGORY_ANALYSIS -> Pair(R.drawable.ic_category, "Category Analysis")
            InsightType.CASH_FLOW_PREDICTION -> Pair(R.drawable.ic_prediction, "Cash Flow Prediction")
            InsightType.INVESTMENT_OPPORTUNITY -> Pair(R.drawable.ic_investment, "Investment Opportunity")
            InsightType.DEBT_OPTIMIZATION -> Pair(R.drawable.ic_debt, "Debt Optimization")
            InsightType.TAX_OPTIMIZATION -> Pair(R.drawable.ic_tax, "Tax Optimization")
            InsightType.BILL_OPTIMIZATION -> Pair(R.drawable.ic_bills, "Bill Optimization")
            InsightType.FINANCIAL_HEALTH -> Pair(R.drawable.ic_health, "Financial Health")
            InsightType.FRAUD_ALERT -> Pair(R.drawable.ic_security, "Fraud Alert")
        }
        
        binding.imageViewInsightType.setImageResource(iconRes)
        binding.textViewInsightType.text = typeText
    }
    
    private fun setupAmountDisplay() {
        val hasAmount = insight.amount != null
        val hasPercentage = insight.percentage != null
        
        when {
            hasAmount && hasPercentage -> {
                binding.textViewAmount.text = formatCurrency(insight.amount!!)
                binding.textViewPercentage.text = "${String.format("%.1f", insight.percentage)}%"
                binding.layoutAmountInfo.visibility = View.VISIBLE
            }
            hasAmount -> {
                binding.textViewAmount.text = formatCurrency(insight.amount!!)
                binding.textViewPercentage.visibility = View.GONE
                binding.layoutAmountInfo.visibility = View.VISIBLE
            }
            hasPercentage -> {
                binding.textViewAmount.visibility = View.GONE
                binding.textViewPercentage.text = "${String.format("%.1f", insight.percentage)}%"
                binding.layoutAmountInfo.visibility = View.VISIBLE
            }
            else -> {
                binding.layoutAmountInfo.visibility = View.GONE
            }
        }
    }
    
    private fun setupClickListeners() {
        binding.buttonClose.setOnClickListener {
            dismiss()
        }
        
        binding.buttonMarkAsRead.setOnClickListener {
            // Mark insight as read and dismiss
            markAsReadAndDismiss()
        }
        
        binding.buttonTakeAction.setOnClickListener {
            // Handle take action - could navigate to relevant screen
            takeAction()
        }
    }
    
    private fun markAsReadAndDismiss() {
        // This would be handled by the parent fragment/activity
        dismiss()
    }
    
    private fun takeAction() {
        // Based on insight type, navigate to appropriate action
        when (insight.insightType) {
            InsightType.BUDGET_WARNING -> {
                // Navigate to budget management
            }
            InsightType.GOAL_PROGRESS -> {
                // Navigate to goals
            }
            InsightType.SAVING_OPPORTUNITY -> {
                // Navigate to recommendations
            }
            else -> {
                // General action or navigation
            }
        }
        
        dismiss()
    }
    
    private fun formatCurrency(amount: Double): String {
        return String.format("%.0f kr", amount)
    }
    
    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}