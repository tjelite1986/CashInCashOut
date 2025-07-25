package com.example.budgetapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.data.OptimizedShoppingList
import com.example.budgetapp.databinding.ItemOptimizedShoppingListBinding

class OptimizedShoppingListAdapter : RecyclerView.Adapter<OptimizedShoppingListAdapter.ViewHolder>() {
    
    private var optimizedLists = listOf<OptimizedShoppingList>()
    
    fun updateLists(newLists: List<OptimizedShoppingList>) {
        optimizedLists = newLists
        notifyDataSetChanged()
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemOptimizedShoppingListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(optimizedLists[position])
    }
    
    override fun getItemCount() = optimizedLists.size
    
    inner class ViewHolder(
        private val binding: ItemOptimizedShoppingListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(optimizedList: OptimizedShoppingList) {
            binding.apply {
                // Butiksinformation
                textStoreName.text = optimizedList.storeName
                textStoreAddress.text = optimizedList.storeAddress ?: ""
                if (optimizedList.storeAddress.isNullOrEmpty()) {
                    textStoreAddress.visibility = android.view.View.GONE
                } else {
                    textStoreAddress.visibility = android.view.View.VISIBLE
                }
                
                // Statistik
                textItemCount.text = "${optimizedList.getItemCount()} varor"
                textTotalCost.text = String.format("%.2f kr", optimizedList.totalEstimatedCost)
                
                // Besparingar
                if (optimizedList.totalSavings > 0) {
                    textSavings.text = "Sparar: ${String.format("%.2f kr", optimizedList.totalSavings)}"
                    textSavings.visibility = android.view.View.VISIBLE
                    chipSavings.visibility = android.view.View.VISIBLE
                } else {
                    textSavings.visibility = android.view.View.GONE
                    chipSavings.visibility = android.view.View.GONE
                }
                
                // Setup nested RecyclerView för varor
                val itemsAdapter = OptimizedShoppingItemAdapter()
                recyclerViewItems.apply {
                    layoutManager = LinearLayoutManager(binding.root.context)
                    adapter = itemsAdapter
                    isNestedScrollingEnabled = false
                }
                itemsAdapter.updateItems(optimizedList.items)
            }
        }
    }
}