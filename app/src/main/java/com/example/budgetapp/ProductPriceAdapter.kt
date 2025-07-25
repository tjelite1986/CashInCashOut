package com.example.budgetapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.database.entities.Product
import com.example.budgetapp.databinding.ItemProductPriceBinding
import java.text.DecimalFormat

class ProductPriceAdapter(
    private val prices: List<ProductPriceManagerActivity.ProductStoreWithStore>,
    private val product: Product?,
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
            
            // Calculate total price including deposit
            val basePrice = priceData.productStore.price
            val depositAmount = if (product?.hasDeposit == true) product.depositAmount ?: 0.0 else 0.0
            val totalPrice = basePrice + depositAmount
            
            // Price (show both base price and total if there's deposit)
            if (depositAmount > 0.0) {
                textViewPrice.text = "${priceFormatter.format(totalPrice)} kr (inkl. pant ${priceFormatter.format(depositAmount)} kr)"
            } else {
                textViewPrice.text = "${priceFormatter.format(totalPrice)} kr"
            }
            
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