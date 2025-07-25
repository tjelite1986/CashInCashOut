package com.example.budgetapp.adapters

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.R
import com.example.budgetapp.databinding.ItemBudgetBinding
import com.example.budgetapp.repository.BudgetProgress
import com.example.budgetapp.data.ThemeSettings
import com.example.budgetapp.data.InterfaceStyle
import com.example.budgetapp.utils.ThemeAware
import com.example.budgetapp.utils.ThemeManager
import java.text.NumberFormat
import java.util.*

class BudgetAdapter(
    private val onEditClick: (BudgetProgress) -> Unit,
    private val onDeleteClick: (BudgetProgress) -> Unit
) : ListAdapter<BudgetProgress, BudgetAdapter.BudgetViewHolder>(BudgetDiffCallback()), ThemeAware {
    
    private var currentThemeSettings: ThemeSettings? = null
    private lateinit var themeManager: ThemeManager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BudgetViewHolder {
        if (!::themeManager.isInitialized) {
            themeManager = ThemeManager.getInstance(parent.context)
        }
        val binding = ItemBudgetBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BudgetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BudgetViewHolder, position: Int) {
        holder.bind(getItem(position))
        
        // Apply current theme to this item
        currentThemeSettings?.let { settings ->
            applyThemeToItem(holder, settings)
        }
    }

    inner class BudgetViewHolder(val binding: ItemBudgetBinding) : 
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

    override fun applyTheme(settings: ThemeSettings) {
        currentThemeSettings = settings
        // Only notify if we have a valid context and the adapter is attached
        if (::themeManager.isInitialized) {
            notifyDataSetChanged() // Refresh all items with new theme
        }
    }
    
    private fun applyThemeToItem(holder: BudgetViewHolder, settings: ThemeSettings) {
        if (!::themeManager.isInitialized) return
        
        val binding = holder.binding
        val accentColor = themeManager.getAccentColorInt()
        val isColorful = settings.interfaceStyle == InterfaceStyle.COLORFUL
        
        // Apply theme to the card background
        if (isColorful) {
            // Very subtle background tint
            val verySubtleColor = adjustColorAlpha(accentColor, 0.02f)
            binding.root.setBackgroundColor(verySubtleColor)
        } else {
            // Reset to default background for material mode
            binding.root.setBackgroundResource(android.R.color.transparent)
        }
        
        // Apply subtle colors to buttons
        val context = binding.root.context
        
        if (isColorful) {
            binding.buttonEdit.setTextColor(adjustColorBrightness(accentColor, 0.7f))
            binding.buttonDelete.setTextColor(adjustColorBrightness(Color.RED, 0.8f))
        } else {
            // Simple material design colors
            binding.buttonEdit.setTextColor(context.getColor(android.R.color.darker_gray))
            binding.buttonDelete.setTextColor(context.getColor(android.R.color.holo_red_dark))
        }
    }
    
    private fun adjustColorAlpha(color: Int, alpha: Float): Int {
        val alphaInt = (255 * alpha).toInt().coerceIn(0, 255)
        return Color.argb(alphaInt, Color.red(color), Color.green(color), Color.blue(color))
    }
    
    private fun adjustColorBrightness(color: Int, factor: Float): Int {
        val red = (Color.red(color) * factor).toInt().coerceIn(0, 255)
        val green = (Color.green(color) * factor).toInt().coerceIn(0, 255)
        val blue = (Color.blue(color) * factor).toInt().coerceIn(0, 255)
        return Color.rgb(red, green, blue)
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