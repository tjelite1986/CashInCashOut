package com.example.budgetapp.analytics.tax.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
// import com.example.budgetapp.TaxCategoryBreakdown
import com.example.budgetapp.database.entities.Expense
// import com.example.budgetapp.databinding.ItemTaxCategoryBinding
import com.example.budgetapp.analytics.tax.TaxCategory
import java.text.NumberFormat
import java.util.*

// Temporarily commented out until layout files are created
/*
class TaxCategoryAdapter(
    private val onCategoryClick: (TaxCategory, List<Expense>) -> Unit
) : ListAdapter<TaxCategoryBreakdown, TaxCategoryAdapter.TaxCategoryViewHolder>(TaxCategoryDiffCallback()) {
    
    private val numberFormat = NumberFormat.getCurrencyInstance(Locale("sv", "SE"))
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaxCategoryViewHolder {
        val binding = ItemTaxCategoryBinding.inflate(
            LayoutInflater.from(parent.context), 
            parent, 
            false
        )
        return TaxCategoryViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: TaxCategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    inner class TaxCategoryViewHolder(
        private val binding: ItemTaxCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(breakdown: TaxCategoryBreakdown) {
            binding.apply {
                textCategoryName.text = formatTaxCategoryName(breakdown.category)
                textExpenseCount.text = "${breakdown.expenses.size} utgifter"
                textTotalAmount.text = numberFormat.format(breakdown.totalAmount)
                textDeductibleAmount.text = numberFormat.format(breakdown.deductibleAmount)
                
                // Set category icon
                imageCategoryIcon.setImageResource(getTaxCategoryIcon(breakdown.category))
                
                // Calculate deduction percentage
                val deductionPercentage = if (breakdown.totalAmount > 0) {
                    (breakdown.deductibleAmount / breakdown.totalAmount * 100).toInt()
                } else 0
                
                textDeductionPercentage.text = "$deductionPercentage%"
                
                // Set colors based on deduction value
                val context = root.context
                val color = when {
                    breakdown.deductibleAmount > 10000 -> context.getColor(android.R.color.holo_green_dark)
                    breakdown.deductibleAmount > 5000 -> context.getColor(android.R.color.holo_orange_dark)
                    else -> context.getColor(android.R.color.darker_gray)
                }
                
                textDeductibleAmount.setTextColor(color)
                textDeductionPercentage.setTextColor(color)
                
                // Click listener
                root.setOnClickListener {
                    onCategoryClick(breakdown.category, breakdown.expenses)
                }
            }
        }
        
        private fun formatTaxCategoryName(category: TaxCategory): String {
            return when (category) {
                TaxCategory.RUT_SERVICES -> "RUT-tjänster"
                TaxCategory.ROT_SERVICES -> "ROT-arbeten"
                TaxCategory.MEDICAL_EXPENSES -> "Sjukvård"
                TaxCategory.TRAVEL_EXPENSES -> "Resor"
                TaxCategory.WORK_EQUIPMENT -> "Utrustning"
                TaxCategory.PROFESSIONAL_DEVELOPMENT -> "Utbildning"
                TaxCategory.CHARITABLE_DONATIONS -> "Välgörenhet"
                TaxCategory.PROFESSIONAL_FEES -> "Avgifter"
                TaxCategory.OTHER -> "Övrigt"
            }
        }
        
        private fun getTaxCategoryIcon(category: TaxCategory): Int {
            return when (category) {
                TaxCategory.RUT_SERVICES -> com.example.budgetapp.R.drawable.ic_home
                TaxCategory.ROT_SERVICES -> com.example.budgetapp.R.drawable.ic_home
                TaxCategory.MEDICAL_EXPENSES -> com.example.budgetapp.R.drawable.ic_health
                TaxCategory.TRAVEL_EXPENSES -> com.example.budgetapp.R.drawable.ic_transit
                TaxCategory.WORK_EQUIPMENT -> com.example.budgetapp.R.drawable.ic_tech
                TaxCategory.PROFESSIONAL_DEVELOPMENT -> com.example.budgetapp.R.drawable.ic_education
                TaxCategory.CHARITABLE_DONATIONS -> com.example.budgetapp.R.drawable.ic_gift
                TaxCategory.PROFESSIONAL_FEES -> com.example.budgetapp.R.drawable.ic_bills
                TaxCategory.OTHER -> com.example.budgetapp.R.drawable.ic_other
            }
        }
    }
}

class TaxCategoryDiffCallback : DiffUtil.ItemCallback<TaxCategoryBreakdown>() {
    
    override fun areItemsTheSame(oldItem: TaxCategoryBreakdown, newItem: TaxCategoryBreakdown): Boolean {
        return oldItem.category == newItem.category
    }
    
    override fun areContentsTheSame(oldItem: TaxCategoryBreakdown, newItem: TaxCategoryBreakdown): Boolean {
        return oldItem == newItem
    }
}*/
