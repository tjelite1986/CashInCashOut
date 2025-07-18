package com.example.budgetapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.databinding.ItemProductPriceBinding
import java.text.DecimalFormat

class ProductPriceAdapter(
    private val prices: List<ProductPriceManagerActivity.ProductStoreWithStore>,
    private val onEditClick: (ProductPriceManagerActivity.ProductStoreWithStore) -> Unit,
    private val onDeleteClick: (ProductPriceManagerActivity.ProductStoreWithStore) -> Unit
) : RecyclerView.Adapter<ProductPriceAdapter.PriceViewHolder>() {

    class PriceViewHolder(val binding: ItemProductPriceBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PriceViewHolder {
        val binding = ItemProductPriceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PriceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PriceViewHolder, position: Int) {
        val priceData = prices[position]
        val priceFormatter = DecimalFormat("#0.00")
        
        with(holder.binding) {
            // Store name
            textViewStoreName.text = priceData.store.name
            textViewStoreChain.text = priceData.store.chain ?: ""
            
            // Price
            textViewPrice.text = "${priceFormatter.format(priceData.productStore.price)} kr"
            
            // Campaign price
            if (priceData.productStore.hasCampaignPrice) {
                val quantity = priceData.productStore.campaignQuantity ?: 1
                val campaignPrice = priceData.productStore.campaignPrice ?: 0.0
                textViewCampaignPrice.text = "Kampanj: $quantity för ${priceFormatter.format(campaignPrice)} kr"
                textViewCampaignPrice.visibility = android.view.View.VISIBLE
            } else {
                textViewCampaignPrice.visibility = android.view.View.GONE
            }
            
            // Last seen
            val lastSeen = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
                .format(java.util.Date(priceData.productStore.lastSeen))
            textViewLastSeen.text = "Senast sett: $lastSeen"
            
            // Button actions
            buttonEdit.setOnClickListener { onEditClick(priceData) }
            buttonDelete.setOnClickListener { onDeleteClick(priceData) }
        }
    }

    override fun getItemCount(): Int = prices.size
}