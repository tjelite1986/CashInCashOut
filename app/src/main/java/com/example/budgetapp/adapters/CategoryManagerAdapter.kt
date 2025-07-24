package com.example.budgetapp.adapters

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.R
import com.example.budgetapp.database.entities.Category
import com.example.budgetapp.database.entities.CategoryType
import com.example.budgetapp.databinding.ItemCategoryManagerBinding

class CategoryManagerAdapter(
    private val onCategoryClick: (Category) -> Unit,
    private val onDeleteClick: (Category) -> Unit
) : ListAdapter<Category, CategoryManagerAdapter.CategoryViewHolder>(CategoryDiffCallback()) {

    private var filteredList = mutableListOf<Category>()
    private var originalList = mutableListOf<Category>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryManagerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun submitList(list: List<Category>?) {
        originalList.clear()
        list?.let { originalList.addAll(it) }
        filteredList.clear()
        filteredList.addAll(originalList)
        super.submitList(filteredList.toList())
    }

    fun filter(query: String) {
        filteredList.clear()
        if (query.isEmpty()) {
            filteredList.addAll(originalList)
        } else {
            filteredList.addAll(
                originalList.filter { 
                    it.name.contains(query, ignoreCase = true) ||
                    it.type.name.contains(query, ignoreCase = true)
                }
            )
        }
        super.submitList(filteredList.toList())
    }

    inner class CategoryViewHolder(private val binding: ItemCategoryManagerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            binding.apply {
                textCategoryName.text = category.name
                textCategoryType.text = when(category.type) {
                    CategoryType.EXPENSE -> "Expense"
                    CategoryType.INCOME -> "Income"
                }
                textTransactionCount.text = "${category.transactionCount} transactions"

                // Set category color for icon background
                val iconBackground = GradientDrawable().apply {
                    shape = GradientDrawable.OVAL
                    try {
                        setColor(Color.parseColor(category.color))
                    } catch (e: IllegalArgumentException) {
                        setColor(Color.parseColor("#FF6B6B"))
                    }
                }
                iconCategory.background = iconBackground

                // Set category icon
                val iconRes = getCategoryIconResource(category.iconResName)
                iconCategory.setImageResource(iconRes)

                // Click listeners
                root.setOnClickListener { onCategoryClick(category) }
                buttonDelete.setOnClickListener { onDeleteClick(category) }
                buttonMenu.setOnClickListener { onCategoryClick(category) }
            }
        }

        private fun getCategoryIconResource(iconName: String): Int {
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
                "ic_salary" -> R.drawable.ic_salary
                "ic_bonus" -> R.drawable.ic_bonus
                "ic_freelance" -> R.drawable.ic_freelance
                "ic_investment" -> R.drawable.ic_investment
                "ic_sale" -> R.drawable.ic_sale
                "ic_other" -> R.drawable.ic_other
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
                "ic_tobacco" -> R.drawable.ic_tobacco
                "ic_drinks" -> R.drawable.ic_drinks
                else -> R.drawable.ic_category_default
            }
        }
    }

    class CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem == newItem
        }
    }
}