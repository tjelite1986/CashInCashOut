package com.example.budgetapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.R
import com.example.budgetapp.data.SmartSuggestion
import com.example.budgetapp.data.SuggestionType
import com.example.budgetapp.databinding.ItemSmartSuggestionBinding

class SmartSuggestionAdapter(
    private val onSuggestionClick: (SmartSuggestion) -> Unit
) : ListAdapter<SmartSuggestion, SmartSuggestionAdapter.SuggestionViewHolder>(SuggestionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestionViewHolder {
        val binding = ItemSmartSuggestionBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return SuggestionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SuggestionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class SuggestionViewHolder(
        private val binding: ItemSmartSuggestionBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(suggestion: SmartSuggestion) {
            binding.textTitle.text = suggestion.title
            binding.textDescription.text = suggestion.description
            binding.buttonAction.text = suggestion.actionText
            
            // Set icon and colors based on suggestion type
            when (suggestion.type) {
                SuggestionType.PRICE_ALERT -> {
                    binding.imageIcon.setImageResource(R.drawable.ic_price_alert)
                    binding.cardSuggestion.setCardBackgroundColor(
                        binding.root.context.getColor(R.color.light_orange_background)
                    )
                }
                SuggestionType.SHOPPING_OPTIMIZATION -> {
                    binding.imageIcon.setImageResource(R.drawable.ic_optimization)
                    binding.cardSuggestion.setCardBackgroundColor(
                        binding.root.context.getColor(R.color.light_green_background)
                    )
                }
                SuggestionType.STORE_RECOMMENDATION -> {
                    binding.imageIcon.setImageResource(R.drawable.ic_store)
                    binding.cardSuggestion.setCardBackgroundColor(
                        binding.root.context.getColor(R.color.light_blue_background)
                    )
                }
                SuggestionType.BUDGET_TIP -> {
                    binding.imageIcon.setImageResource(R.drawable.ic_tips)
                    binding.cardSuggestion.setCardBackgroundColor(
                        binding.root.context.getColor(R.color.light_blue_background)
                    )
                }
                SuggestionType.SEASONAL_DEAL -> {
                    binding.imageIcon.setImageResource(R.drawable.ic_seasonal)
                    binding.cardSuggestion.setCardBackgroundColor(
                        binding.root.context.getColor(R.color.light_green_background)
                    )
                }
            }
            
            // Set priority indicator
            val priorityColor = when (suggestion.priority) {
                1 -> R.color.error // High priority
                2 -> R.color.warning // Medium priority
                else -> R.color.primary // Low priority
            }
            binding.viewPriorityIndicator.setBackgroundColor(
                binding.root.context.getColor(priorityColor)
            )
            
            binding.buttonAction.setOnClickListener {
                onSuggestionClick(suggestion)
            }
            
            binding.root.setOnClickListener {
                onSuggestionClick(suggestion)
            }
        }
    }

    private class SuggestionDiffCallback : DiffUtil.ItemCallback<SmartSuggestion>() {
        override fun areItemsTheSame(oldItem: SmartSuggestion, newItem: SmartSuggestion): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SmartSuggestion, newItem: SmartSuggestion): Boolean {
            return oldItem == newItem
        }
    }
}