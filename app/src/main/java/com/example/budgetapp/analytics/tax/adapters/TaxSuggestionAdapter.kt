package com.example.budgetapp.analytics.tax.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
// import com.example.budgetapp.databinding.ItemTaxSuggestionBinding
import com.example.budgetapp.analytics.tax.TaxOptimizationSuggestion
import com.example.budgetapp.analytics.tax.OptimizationType
import com.example.budgetapp.analytics.tax.Priority
import java.text.NumberFormat
import java.util.*

// Temporarily commented out until layout files are created
/*
class TaxSuggestionAdapter(
    private val onSuggestionClick: (TaxOptimizationSuggestion) -> Unit
) : ListAdapter<TaxOptimizationSuggestion, TaxSuggestionAdapter.TaxSuggestionViewHolder>(TaxSuggestionDiffCallback()) {
    
    private val numberFormat = NumberFormat.getCurrencyInstance(Locale("sv", "SE"))
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaxSuggestionViewHolder {
        val binding = ItemTaxSuggestionBinding.inflate(
            LayoutInflater.from(parent.context), 
            parent, 
            false
        )
        return TaxSuggestionViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: TaxSuggestionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    inner class TaxSuggestionViewHolder(
        private val binding: ItemTaxSuggestionBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(suggestion: TaxOptimizationSuggestion) {
            binding.apply {
                textSuggestionTitle.text = suggestion.title
                textSuggestionDescription.text = suggestion.description
                textPotentialSaving.text = numberFormat.format(suggestion.potentialSaving)
                textPriority.text = formatPriority(suggestion.priority)
                
                // Set priority indicator color
                val context = root.context
                val priorityColor = when (suggestion.priority) {
                    Priority.HIGH -> context.getColor(android.R.color.holo_red_dark)
                    Priority.MEDIUM -> context.getColor(android.R.color.holo_orange_dark)
                    Priority.LOW -> context.getColor(android.R.color.holo_green_dark)
                }
                
                textPriority.setTextColor(priorityColor)
                viewPriorityIndicator.setBackgroundColor(priorityColor)
                
                // Set suggestion type icon
                imageSuggestionIcon.setImageResource(getSuggestionTypeIcon(suggestion.type))
                
                // Format potential saving with color
                val savingColor = when {
                    suggestion.potentialSaving > 5000 -> context.getColor(android.R.color.holo_green_dark)
                    suggestion.potentialSaving > 1000 -> context.getColor(android.R.color.holo_orange_dark)
                    else -> context.getColor(android.R.color.darker_gray)
                }
                textPotentialSaving.setTextColor(savingColor)
                
                // Click listener
                root.setOnClickListener {
                    onSuggestionClick(suggestion)
                }
            }
        }
        
        private fun formatPriority(priority: Priority): String {
            return when (priority) {
                Priority.HIGH -> "Hög prioritet"
                Priority.MEDIUM -> "Medel prioritet"
                Priority.LOW -> "Låg prioritet"
            }
        }
        
        private fun getSuggestionTypeIcon(type: OptimizationType): Int {
            return when (type) {
                OptimizationType.RUT_OPTIMIZATION -> com.example.budgetapp.R.drawable.ic_home
                OptimizationType.ROT_OPTIMIZATION -> com.example.budgetapp.R.drawable.ic_home
                OptimizationType.MEDICAL_EXPENSES -> com.example.budgetapp.R.drawable.ic_health
                OptimizationType.INVESTMENT_TIMING -> com.example.budgetapp.R.drawable.ic_investment
                OptimizationType.CHARITABLE_GIVING -> com.example.budgetapp.R.drawable.ic_gift
                OptimizationType.PROFESSIONAL_EXPENSES -> com.example.budgetapp.R.drawable.ic_education
            }
        }
    }
}

class TaxSuggestionDiffCallback : DiffUtil.ItemCallback<TaxOptimizationSuggestion>() {
    
    override fun areItemsTheSame(oldItem: TaxOptimizationSuggestion, newItem: TaxOptimizationSuggestion): Boolean {
        return oldItem.title == newItem.title && oldItem.type == newItem.type
    }
    
    override fun areContentsTheSame(oldItem: TaxOptimizationSuggestion, newItem: TaxOptimizationSuggestion): Boolean {
        return oldItem == newItem
    }
}*/
