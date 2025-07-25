package com.example.budgetapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.data.ShoppingListItemOptimized
import com.example.budgetapp.databinding.ItemOptimizedShoppingItemBinding

class OptimizedShoppingItemAdapter : RecyclerView.Adapter<OptimizedShoppingItemAdapter.ViewHolder>() {
    
    private var items = listOf<ShoppingListItemOptimized>()
    
    fun updateItems(newItems: List<ShoppingListItemOptimized>) {
        items = newItems
        notifyDataSetChanged()
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemOptimizedShoppingItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
    
    override fun getItemCount() = items.size
    
    inner class ViewHolder(
        private val binding: ItemOptimizedShoppingItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(item: ShoppingListItemOptimized) {
            binding.apply {
                // Produktnamn
                textItemName.text = item.productName ?: "Okänd vara"
                
                // Kvantitet och enhet
                textQuantity.text = "${item.item.quantity} ${item.item.unit ?: "st"}"
                
                // Pris per enhet
                textUnitPrice.text = String.format("%.2f kr/st", item.cheapestStorePrice)
                
                // Totalpris
                textTotalPrice.text = String.format("%.2f kr", item.getTotalCost())
                
                // Kampanjtext om det finns
                if (item.hasCampaignPrice && !item.campaignText.isNullOrEmpty()) {
                    textCampaignInfo.text = "📢 ${item.campaignText}"
                    textCampaignInfo.visibility = android.view.View.VISIBLE
                } else {
                    textCampaignInfo.visibility = android.view.View.GONE
                }
                
                // Besparingar
                val savings = item.getTotalSavings()
                if (savings > 0) {
                    textSavings.text = "Sparar: ${String.format("%.2f kr", savings)}"
                    textSavings.visibility = android.view.View.VISIBLE
                    chipBestPrice.visibility = android.view.View.VISIBLE
                } else {
                    textSavings.visibility = android.view.View.GONE
                    chipBestPrice.visibility = android.view.View.GONE
                }
                
                // Prioritet (från originalvaran)
                when (item.item.priority) {
                    1 -> {
                        // Hög prioritet
                        root.alpha = 1.0f
                        textItemName.setTextColor(root.context.getColor(android.R.color.holo_red_dark))
                    }
                    -1 -> {
                        // Låg prioritet  
                        root.alpha = 0.7f
                        textItemName.setTextColor(root.context.getColor(android.R.color.darker_gray))
                    }
                    else -> {
                        // Normal prioritet
                        root.alpha = 1.0f
                        textItemName.setTextColor(root.context.getColor(android.R.color.black))
                    }
                }
            }
        }
    }
}