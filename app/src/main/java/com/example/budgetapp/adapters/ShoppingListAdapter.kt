package com.example.budgetapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.database.entities.ShoppingList
import com.example.budgetapp.databinding.ItemShoppingListBinding
import java.text.SimpleDateFormat
import java.util.*

class ShoppingListAdapter(
    private val onItemClick: (ShoppingList) -> Unit,
    private val onDeleteClick: (ShoppingList) -> Unit,
    private val onCompleteToggle: (ShoppingList) -> Unit
) : ListAdapter<ShoppingList, ShoppingListAdapter.ShoppingListViewHolder>(ShoppingListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        val binding = ItemShoppingListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ShoppingListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ShoppingListViewHolder(
        private val binding: ItemShoppingListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(shoppingList: ShoppingList) {
            binding.apply {
                textShoppingListName.text = shoppingList.name
                textDescription.text = shoppingList.description ?: "Ingen beskrivning"
                
                val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale("sv", "SE"))
                textCreatedDate.text = "Skapad: ${dateFormat.format(Date(shoppingList.createdAt))}"
                
                if (shoppingList.totalEstimatedCost > 0) {
                    textEstimatedCost.text = String.format("%.2f kr", shoppingList.totalEstimatedCost)
                } else {
                    textEstimatedCost.text = "Ingen kostnad uppskattat"
                }
                
                checkboxCompleted.isChecked = shoppingList.isCompleted
                
                // Set up click listeners
                root.setOnClickListener {
                    onItemClick(shoppingList)
                }
                
                buttonDelete.setOnClickListener {
                    onDeleteClick(shoppingList)
                }
                
                checkboxCompleted.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked != shoppingList.isCompleted) {
                        onCompleteToggle(shoppingList)
                    }
                }
            }
        }
    }

    class ShoppingListDiffCallback : DiffUtil.ItemCallback<ShoppingList>() {
        override fun areItemsTheSame(oldItem: ShoppingList, newItem: ShoppingList): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ShoppingList, newItem: ShoppingList): Boolean {
            return oldItem == newItem
        }
    }
}