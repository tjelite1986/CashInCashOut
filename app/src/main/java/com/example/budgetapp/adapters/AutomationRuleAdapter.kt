package com.example.budgetapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.automation.AutomationRule
import com.example.budgetapp.databinding.ItemAutomationRuleBinding
import java.text.SimpleDateFormat
import java.util.*

class AutomationRuleAdapter(
    private val onRuleClick: (AutomationRule) -> Unit
) : ListAdapter<AutomationRule, AutomationRuleAdapter.AutomationRuleViewHolder>(AutomationRuleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AutomationRuleViewHolder {
        val binding = ItemAutomationRuleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AutomationRuleViewHolder(binding, onRuleClick)
    }

    override fun onBindViewHolder(holder: AutomationRuleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class AutomationRuleViewHolder(
        private val binding: ItemAutomationRuleBinding,
        private val onRuleClick: (AutomationRule) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(rule: AutomationRule) {
            binding.apply {
                textRuleName.text = rule.name
                textRuleDescription.text = rule.description
                
                // Show rule status
                switchRuleActive.isChecked = rule.isActive
                switchRuleActive.setOnCheckedChangeListener { _, isChecked ->
                    // Handle rule activation/deactivation
                }
                
                // Show usage count
                textUsageCount.text = "Used ${rule.usageCount} times"
                
                // Show creation info
                val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
                textCreatedAt.text = "Created ${dateFormat.format(Date(rule.createdAt))}"
                
                // Show creator
                textCreatedBy.text = "By ${rule.createdBy.name.lowercase().capitalize()}"
                
                // Show priority
                val priorityText = when (rule.priority) {
                    1 -> "High Priority"
                    2 -> "Medium Priority"
                    else -> "Low Priority"
                }
                textPriority.text = priorityText
                
                // Show confidence if created by system/AI
                if (rule.createdBy != com.example.budgetapp.automation.AutomationCreatedBy.USER) {
                    textConfidence.text = "${(rule.confidence * 100).toInt()}% confidence"
                    textConfidence.visibility = android.view.View.VISIBLE
                } else {
                    textConfidence.visibility = android.view.View.GONE
                }
                
                // Set colors based on rule status
                val context = itemView.context
                if (rule.isActive) {
                    cardRule.strokeColor = context.getColor(android.R.color.holo_green_light)
                } else {
                    cardRule.strokeColor = context.getColor(android.R.color.darker_gray)
                }
                
                root.setOnClickListener {
                    onRuleClick(rule)
                }
            }
        }
    }

    private class AutomationRuleDiffCallback : DiffUtil.ItemCallback<AutomationRule>() {
        override fun areItemsTheSame(oldItem: AutomationRule, newItem: AutomationRule): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AutomationRule, newItem: AutomationRule): Boolean {
            return oldItem == newItem
        }
    }
}