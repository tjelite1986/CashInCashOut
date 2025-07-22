package com.example.budgetapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.R
import com.example.budgetapp.databinding.ItemBudgetBinding
import com.example.budgetapp.repository.BudgetProgress
import java.text.NumberFormat
import java.util.*

class BudgetAdapter(
    private val onEditClick: (BudgetProgress) -> Unit,
    private val onDeleteClick: (BudgetProgress) -> Unit
) : ListAdapter<BudgetProgress, BudgetAdapter.BudgetViewHolder>(BudgetDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BudgetViewHolder {
        val binding = ItemBudgetBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BudgetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BudgetViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BudgetViewHolder(private val binding: ItemBudgetBinding) : 
        RecyclerView.ViewHolder(binding.root) {

        fun bind(budgetProgress: BudgetProgress) {
            val budget = budgetProgress.budget
            val context = binding.root.context
            val numberFormat = NumberFormat.getCurrencyInstance(Locale("sv", "SE"))

            binding.apply {
                textBudgetName.text = budget.name
                textCategory.text = budget.categoryName
                textPeriod.text = getPeriodText(budget.period.name)
                
                textBudgetAmount.text = numberFormat.format(budget.budgetAmount)
                textSpentAmount.text = numberFormat.format(budgetProgress.spentAmount)
                textRemainingAmount.text = numberFormat.format(budgetProgress.remainingAmount)
                
                progressBudget.progress = budgetProgress.percentage.toInt()
                textProgressPercentage.text = "${budgetProgress.percentage.toInt()}%"
                
                // Färgkodning baserat på status
                when {
                    budgetProgress.isOverBudget -> {
                        progressBudget.setIndicatorColor(ContextCompat.getColor(context, R.color.error))
                        textProgressPercentage.setTextColor(ContextCompat.getColor(context, R.color.error))
                        textRemainingAmount.setTextColor(ContextCompat.getColor(context, R.color.error))
                    }
                    budgetProgress.isNearLimit -> {
                        progressBudget.setIndicatorColor(ContextCompat.getColor(context, R.color.warning))
                        textProgressPercentage.setTextColor(ContextCompat.getColor(context, R.color.warning))
                        textRemainingAmount.setTextColor(ContextCompat.getColor(context, R.color.warning))
                    }
                    else -> {
                        progressBudget.setIndicatorColor(ContextCompat.getColor(context, R.color.success))
                        textProgressPercentage.setTextColor(ContextCompat.getColor(context, R.color.success))
                        textRemainingAmount.setTextColor(ContextCompat.getColor(context, R.color.on_surface))
                    }
                }
                
                // Status indikator
                textStatusIndicator.text = when {
                    budgetProgress.isOverBudget -> "Över budget!"
                    budgetProgress.isNearLimit -> "Nära gränsen"
                    else -> "På rätt spår"
                }
                
                textStatusIndicator.setTextColor(when {
                    budgetProgress.isOverBudget -> ContextCompat.getColor(context, R.color.error)
                    budgetProgress.isNearLimit -> ContextCompat.getColor(context, R.color.warning)
                    else -> ContextCompat.getColor(context, R.color.success)
                })

                // Click listeners
                buttonEdit.setOnClickListener { onEditClick(budgetProgress) }
                buttonDelete.setOnClickListener { onDeleteClick(budgetProgress) }
                
                root.setOnClickListener { onEditClick(budgetProgress) }
            }
        }
        
        private fun getPeriodText(period: String): String {
            return when (period) {
                "WEEKLY" -> "Vecka"
                "MONTHLY" -> "Månad"
                "QUARTERLY" -> "Kvartal"
                "YEARLY" -> "År"
                else -> period
            }
        }
    }

    class BudgetDiffCallback : DiffUtil.ItemCallback<BudgetProgress>() {
        override fun areItemsTheSame(oldItem: BudgetProgress, newItem: BudgetProgress): Boolean {
            return oldItem.budget.id == newItem.budget.id
        }

        override fun areContentsTheSame(oldItem: BudgetProgress, newItem: BudgetProgress): Boolean {
            return oldItem == newItem
        }
    }
}