package com.example.budgetapp.adapters

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.R
import com.example.budgetapp.databinding.ItemCategoryIconBinding
import com.example.budgetapp.utils.CategoryConstants

class CategoryIconAdapter(
    private val icons: List<String>,
    private val onIconSelected: (String) -> Unit
) : RecyclerView.Adapter<CategoryIconAdapter.IconViewHolder>() {

    private var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IconViewHolder {
        val binding = ItemCategoryIconBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return IconViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IconViewHolder, position: Int) {
        holder.bind(icons[position], position == selectedPosition)
    }

    override fun getItemCount() = icons.size

    fun selectIcon(iconName: String) {
        val newPosition = icons.indexOf(iconName)
        if (newPosition != -1) {
            val oldPosition = selectedPosition
            selectedPosition = newPosition
            notifyItemChanged(oldPosition)
            notifyItemChanged(selectedPosition)
        }
    }

    inner class IconViewHolder(private val binding: ItemCategoryIconBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(iconName: String, isSelected: Boolean) {
            // Set icon background with gradient for selected state
            val iconBackground = if (isSelected) {
                GradientDrawable().apply {
                    shape = GradientDrawable.RECTANGLE
                    cornerRadius = 16f
                    colors = intArrayOf(
                        Color.parseColor("#FF6B6B"),
                        Color.parseColor("#FF8A80")
                    )
                    gradientType = GradientDrawable.LINEAR_GRADIENT
                    orientation = GradientDrawable.Orientation.TOP_BOTTOM
                }
            } else {
                GradientDrawable().apply {
                    shape = GradientDrawable.RECTANGLE
                    cornerRadius = 16f
                    colors = intArrayOf(
                        Color.parseColor("#424242"),
                        Color.parseColor("#303030")
                    )
                    gradientType = GradientDrawable.LINEAR_GRADIENT
                    orientation = GradientDrawable.Orientation.TOP_BOTTOM
                }
            }

            binding.iconContainer.background = iconBackground

            // Set icon resource with enhanced tinting
            val iconRes = getIconResource(iconName)
            binding.iconImage.setImageResource(iconRes)
            binding.iconImage.setColorFilter(
                if (isSelected) Color.WHITE else Color.parseColor("#E0E0E0"),
                android.graphics.PorterDuff.Mode.SRC_IN
            )

            // Set icon name with enhanced styling
            binding.iconName.text = getIconDisplayName(iconName)
            binding.iconName.setTextColor(
                if (isSelected) Color.parseColor("#FF6B6B") else Color.parseColor("#B0B0B0")
            )
            binding.iconName.typeface = if (isSelected) 
                android.graphics.Typeface.DEFAULT_BOLD else 
                android.graphics.Typeface.DEFAULT

            binding.root.setOnClickListener {
                val oldPosition = selectedPosition
                selectedPosition = adapterPosition
                notifyItemChanged(oldPosition)
                notifyItemChanged(selectedPosition)
                onIconSelected(iconName)
            }
        }

        private fun getIconResource(iconName: String): Int {
            return when (iconName) {
                "ic_dining" -> R.drawable.ic_dining
                "ic_groceries" -> R.drawable.ic_groceries
                "ic_shopping" -> R.drawable.ic_shopping
                "ic_transit" -> R.drawable.ic_transit
                "ic_entertainment" -> R.drawable.ic_entertainment
                "ic_bills" -> R.drawable.ic_bills
                "ic_home" -> R.drawable.ic_home
                "ic_health" -> R.drawable.ic_health
                "ic_tech" -> R.drawable.ic_tech
                "ic_sport" -> R.drawable.ic_sport
                "ic_car" -> R.drawable.ic_car
                "ic_gas" -> R.drawable.ic_gas
                "ic_clothes" -> R.drawable.ic_clothes
                "ic_education" -> R.drawable.ic_education
                "ic_coffee" -> R.drawable.ic_coffee
                "ic_gift" -> R.drawable.ic_gift
                "ic_pets" -> R.drawable.ic_pets
                "ic_travel" -> R.drawable.ic_travel
                "ic_beauty" -> R.drawable.ic_beauty
                "ic_pharmacy" -> R.drawable.ic_pharmacy
                "ic_store" -> R.drawable.ic_store
                "ic_calendar" -> R.drawable.ic_calendar
                "ic_money" -> R.drawable.ic_money
                "ic_salary" -> R.drawable.ic_salary
                "ic_bonus" -> R.drawable.ic_bonus
                "ic_freelance" -> R.drawable.ic_freelance
                "ic_investment" -> R.drawable.ic_investment
                "ic_sale" -> R.drawable.ic_sale
                "ic_other" -> R.drawable.ic_other
                "ic_tobacco" -> R.drawable.ic_tobacco
                "ic_drinks" -> R.drawable.ic_drinks
                else -> R.drawable.ic_category_default
            }
        }

        private fun getIconDisplayName(iconName: String): String {
            return CategoryConstants.CATEGORY_ICONS[iconName] ?: "Other"
        }
    }
}