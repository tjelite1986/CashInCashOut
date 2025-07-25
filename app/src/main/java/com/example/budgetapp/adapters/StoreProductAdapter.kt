package com.example.budgetapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.database.entities.ProductStore
import com.example.budgetapp.databinding.ItemStoreProductBinding

class StoreProductAdapter(
    private val onProductClick: (ProductStore) -> Unit
) : ListAdapter<ProductStore, StoreProductAdapter.ViewHolder>(ProductStoreDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemStoreProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    inner class ViewHolder(
        private val binding: ItemStoreProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(productStore: ProductStore) {
            binding.apply {
                // Product name would need to be loaded from Product table
                textProductName.text = "Produkt ID: ${productStore.productId}"
                
                // Current price
                textCurrentPrice.text = String.format("%.2f kr", productStore.price)
                
                // Campaign price if available
                if (productStore.campaignPrice != null && productStore.campaignPrice!! > 0) {
                    textCampaignPrice.text = String.format("Kampanj: %.2f kr", productStore.campaignPrice)
                    textCampaignPrice.visibility = View.VISIBLE
                } else {
                    textCampaignPrice.visibility = View.GONE
                }
                
                // Campaign info
                if (productStore.hasCampaignPrice && productStore.campaignQuantity != null) {
                    textCampaignInfo.text = "${productStore.campaignQuantity} för ${String.format("%.2f kr", productStore.campaignPrice)}"
                    textCampaignInfo.visibility = View.VISIBLE
                } else {
                    textCampaignInfo.visibility = View.GONE
                }
                
                // Last updated
                val lastUpdated = java.text.SimpleDateFormat("dd MMM yyyy", java.util.Locale("sv", "SE"))
                    .format(java.util.Date(productStore.updatedAt))
                textLastUpdated.text = "Uppdaterad: $lastUpdated"
                
                root.setOnClickListener {
                    onProductClick(productStore)
                }
            }
        }
    }
    
    private class ProductStoreDiffCallback : DiffUtil.ItemCallback<ProductStore>() {
        override fun areItemsTheSame(oldItem: ProductStore, newItem: ProductStore): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: ProductStore, newItem: ProductStore): Boolean {
            return oldItem == newItem
        }
    }
}