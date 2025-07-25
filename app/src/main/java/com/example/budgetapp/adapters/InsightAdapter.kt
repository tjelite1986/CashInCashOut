package com.example.budgetapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.R
import com.example.budgetapp.database.entities.FinancialInsight
import com.example.budgetapp.database.entities.InsightSeverity
import com.example.budgetapp.database.entities.InsightType
import com.example.budgetapp.databinding.ItemInsightBinding
import java.text.SimpleDateFormat
import java.util.*

class InsightAdapter(
    private val onInsightClick: (FinancialInsight) -> Unit
) : ListAdapter<FinancialInsight, InsightAdapter.InsightViewHolder>(InsightDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InsightViewHolder {
        val binding = ItemInsightBinding.inflate(
            LayoutInflater.from(parent.context), 
            parent, 
            false
        )
        return InsightViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: InsightViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    inner class InsightViewHolder(
        private val binding: ItemInsightBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(insight: FinancialInsight) {
            binding.apply {
                // Basic insight info
                textViewInsightTitle.text = insight.title
                textViewInsightDescription.text = insight.description
                
                // Format timestamp
                val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
                textViewInsightDate.text = dateFormat.format(Date(insight.createdAt))
                
                // Set severity indicator
                setupSeverityIndicator(insight.severity)
                
                // Set insight type icon
                setupInsightTypeIcon(insight.insightType)
                
                // Set confidence score
                setupConfidenceScore(insight.confidenceScore)
                
                // Set amount if available
                setupAmountDisplay(insight.amount, insight.percentage)
                
                // Set suggested action
                setupSuggestedAction(insight.suggestedAction, insight.actionable)
                
                // Set read status
                setupReadStatus(insight.isRead)
                
                // Handle click
                root.setOnClickListener {
                    onInsightClick(insight)
                }
            }
        }
        
        private fun setupSeverityIndicator(severity: InsightSeverity) {
            val (colorRes, textRes) = when (severity) {
                InsightSeverity.CRITICAL -> Pair(R.color.severity_critical, "CRITICAL")
                InsightSeverity.HIGH -> Pair(R.color.severity_high, "HIGH")
                InsightSeverity.MEDIUM -> Pair(R.color.severity_medium, "MEDIUM")
                InsightSeverity.LOW -> Pair(R.color.severity_low, "LOW")
                InsightSeverity.INFO -> Pair(R.color.severity_info, "INFO")
            }
            
            binding.viewSeverityIndicator.setBackgroundColor(
                ContextCompat.getColor(binding.root.context, colorRes)
            )
            binding.textViewSeverity.text = textRes
            binding.textViewSeverity.setTextColor(
                ContextCompat.getColor(binding.root.context, colorRes)
            )
        }
        
        private fun setupInsightTypeIcon(type: InsightType) {
            val iconRes = when (type) {
                InsightType.SPENDING_ANOMALY -> R.drawable.ic_warning
                InsightType.BUDGET_WARNING -> R.drawable.ic_budget
                InsightType.SAVING_OPPORTUNITY -> R.drawable.ic_savings
                InsightType.INCOME_PATTERN -> R.drawable.ic_income
                InsightType.EXPENSE_TREND -> R.drawable.ic_expenses
                InsightType.GOAL_PROGRESS -> R.drawable.ic_goal
                InsightType.SEASONAL_PATTERN -> R.drawable.ic_calendar
                InsightType.CATEGORY_ANALYSIS -> R.drawable.ic_category
                InsightType.CASH_FLOW_PREDICTION -> R.drawable.ic_prediction
                InsightType.INVESTMENT_OPPORTUNITY -> R.drawable.ic_investment
                InsightType.DEBT_OPTIMIZATION -> R.drawable.ic_debt
                InsightType.TAX_OPTIMIZATION -> R.drawable.ic_tax
                InsightType.BILL_OPTIMIZATION -> R.drawable.ic_bills
                InsightType.FINANCIAL_HEALTH -> R.drawable.ic_health
                InsightType.FRAUD_ALERT -> R.drawable.ic_security
            }
            
            binding.imageViewInsightIcon.setImageResource(iconRes)
        }
        
        private fun setupConfidenceScore(confidence: Float) {
            val confidencePercentage = (confidence * 100).toInt()
            binding.textViewConfidence.text = "${confidencePercentage}%"
            
            // Set progress bar
            binding.progressBarConfidence.progress = confidencePercentage
            
            // Set color based on confidence level
            val colorRes = when {
                confidence >= 0.8f -> R.color.confidence_high
                confidence >= 0.6f -> R.color.confidence_medium
                else -> R.color.confidence_low
            }
            
            binding.progressBarConfidence.progressTintList = 
                ContextCompat.getColorStateList(binding.root.context, colorRes)
        }
        
        private fun setupAmountDisplay(amount: Double?, percentage: Double?) {
            when {
                amount != null && percentage != null -> {
                    binding.textViewAmount.text = formatCurrency(amount)
                    binding.textViewPercentage.text = "${String.format("%.1f", percentage)}%"
                    binding.layoutAmountInfo.visibility = View.VISIBLE
                }
                amount != null -> {
                    binding.textViewAmount.text = formatCurrency(amount)
                    binding.textViewPercentage.visibility = View.GONE
                    binding.layoutAmountInfo.visibility = View.VISIBLE
                }
                percentage != null -> {
                    binding.textViewAmount.visibility = View.GONE
                    binding.textViewPercentage.text = "${String.format("%.1f", percentage)}%"
                    binding.layoutAmountInfo.visibility = View.VISIBLE
                }
                else -> {
                    binding.layoutAmountInfo.visibility = View.GONE
                }
            }
        }
        
        private fun setupSuggestedAction(suggestedAction: String?, actionable: Boolean) {
            if (actionable && !suggestedAction.isNullOrBlank()) {
                binding.textViewSuggestedAction.text = suggestedAction
                binding.layoutSuggestedAction.visibility = View.VISIBLE
            } else {
                binding.layoutSuggestedAction.visibility = View.GONE
            }
        }
        
        private fun setupReadStatus(isRead: Boolean) {
            if (isRead) {
                binding.root.alpha = 0.7f
                binding.viewNewIndicator.visibility = View.GONE
            } else {
                binding.root.alpha = 1.0f
                binding.viewNewIndicator.visibility = View.VISIBLE
            }
        }
        
        private fun formatCurrency(amount: Double): String {
            return String.format("%.0f kr", amount)
        }
    }
}

class InsightDiffCallback : DiffUtil.ItemCallback<FinancialInsight>() {
    override fun areItemsTheSame(oldItem: FinancialInsight, newItem: FinancialInsight): Boolean {
        return oldItem.id == newItem.id
    }
    
    override fun areContentsTheSame(oldItem: FinancialInsight, newItem: FinancialInsight): Boolean {
        return oldItem == newItem
    }
}