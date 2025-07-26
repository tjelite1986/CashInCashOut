package com.example.budgetapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.automation.ml.SmartInsight
import com.example.budgetapp.automation.ml.InsightPriority
import com.example.budgetapp.automation.ml.ImpactLevel
import com.example.budgetapp.databinding.ItemSmartInsightBinding
import java.text.SimpleDateFormat
import java.util.*

class SmartInsightAdapter(
    private val onInsightClick: (SmartInsight) -> Unit
) : ListAdapter<SmartInsight, SmartInsightAdapter.SmartInsightViewHolder>(SmartInsightDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SmartInsightViewHolder {
        val binding = ItemSmartInsightBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SmartInsightViewHolder(binding, onInsightClick)
    }

    override fun onBindViewHolder(holder: SmartInsightViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class SmartInsightViewHolder(
        private val binding: ItemSmartInsightBinding,
        private val onInsightClick: (SmartInsight) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(insight: SmartInsight) {
            binding.apply {
                textInsightTitle.text = insight.title
                textInsightDescription.text = insight.description
                
                // Show priority
                val priorityText = when (insight.priority) {
                    InsightPriority.CRITICAL -> "🔴 Critical"
                    InsightPriority.HIGH -> "🟠 High"
                    InsightPriority.MEDIUM -> "🟡 Medium"
                    InsightPriority.LOW -> "🟢 Low"
                }
                textPriority.text = priorityText
                
                // Show impact
                val impactText = when (insight.impact) {
                    ImpactLevel.HIGH -> "High Impact"
                    ImpactLevel.MEDIUM -> "Medium Impact"
                    ImpactLevel.LOW -> "Low Impact"
                }
                textImpact.text = impactText
                
                // Show confidence
                textConfidence.text = "${(insight.confidence * 100).toInt()}% confidence"
                
                // Show category if available
                if (insight.category != null) {
                    textCategory.text = insight.category
                    textCategory.visibility = android.view.View.VISIBLE
                } else {
                    textCategory.visibility = android.view.View.GONE
                }
                
                // Show creation date
                val dateFormat = SimpleDateFormat("MMM dd, HH:mm", Locale.getDefault())
                textCreatedAt.text = dateFormat.format(Date(insight.createdAt))
                
                // Show actions if actionable
                if (insight.actionable && insight.actions.isNotEmpty()) {
                    val actionsText = insight.actions.joinToString(" • ")
                    textActions.text = actionsText
                    textActions.visibility = android.view.View.VISIBLE
                } else {
                    textActions.visibility = android.view.View.GONE
                }
                
                // Show read status
                if (insight.isRead) {
                    cardInsight.alpha = 0.7f
                    indicatorUnread.visibility = android.view.View.GONE
                } else {
                    cardInsight.alpha = 1.0f
                    indicatorUnread.visibility = android.view.View.VISIBLE
                }
                
                // Set card colors based on priority
                val context = itemView.context
                val strokeColor = when (insight.priority) {
                    InsightPriority.CRITICAL -> context.getColor(android.R.color.holo_red_light)
                    InsightPriority.HIGH -> context.getColor(android.R.color.holo_orange_light)
                    InsightPriority.MEDIUM -> context.getColor(android.R.color.holo_blue_light)
                    InsightPriority.LOW -> context.getColor(android.R.color.holo_green_light)
                }
                cardInsight.strokeColor = strokeColor
                
                root.setOnClickListener {
                    onInsightClick(insight)
                }
            }
        }
    }

    private class SmartInsightDiffCallback : DiffUtil.ItemCallback<SmartInsight>() {
        override fun areItemsTheSame(oldItem: SmartInsight, newItem: SmartInsight): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SmartInsight, newItem: SmartInsight): Boolean {
            return oldItem == newItem
        }
    }
}