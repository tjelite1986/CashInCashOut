package com.example.budgetapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.data.ProductWithPrices
import com.example.budgetapp.databinding.ItemProductWithPricesBinding
import java.text.DecimalFormat

class ProductWithPricesAdapter(
    private val products: List<ProductWithPrices>,
    private val onEditClick: (ProductWithPrices) -> Unit,
    private val onDeleteClick: (ProductWithPrices) -> Unit,
    private val onViewPricesClick: (ProductWithPrices) -> Unit
) : RecyclerView.Adapter<ProductWithPricesAdapter.ProductViewHolder>() {

    class ProductViewHolder(val binding: ItemProductWithPricesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductWithPricesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val productWithPrices = products[position]
        val product = productWithPrices.product
        val priceFormatter = DecimalFormat("#0.00")
        
        with(holder.binding) {
            // Product name
            textViewProductName.text = product.name
            
            // Product brand name
            if (product.productName.isNullOrEmpty()) {
                textViewProductBrandName.visibility = android.view.View.GONE
            } else {
                textViewProductBrandName.text = product.productName
                textViewProductBrandName.visibility = android.view.View.VISIBLE
            }
            
            // Price information
            when {
                productWithPrices.prices.isEmpty() -> {
                    textViewPrice.text = "Inget pris registrerat"
                    textViewPriceRange.visibility = android.view.View.GONE
                }
                productWithPrices.prices.size == 1 -> {
                    val price = productWithPrices.prices.first().productStore.price
                    textViewPrice.text = "${priceFormatter.format(price)} kr"
                    textViewPriceRange.visibility = android.view.View.GONE
                }
                else -> {
                    val lowestPrice = productWithPrices.lowestPrice!!
                    val highestPrice = productWithPrices.highestPrice!!
                    textViewPrice.text = "${priceFormatter.format(lowestPrice)} - ${priceFormatter.format(highestPrice)} kr"
                    textViewPriceRange.text = "${productWithPrices.prices.size} butiker"
                    textViewPriceRange.visibility = android.view.View.VISIBLE
                }
            }
            
            // Amount and unit
            if (product.amount != null && product.unit != null) {
                textViewAmountUnit.text = "${product.amount} ${product.unit}"
                textViewAmountUnit.visibility = android.view.View.VISIBLE
            } else if (product.unit != null) {
                textViewAmountUnit.text = product.unit
                textViewAmountUnit.visibility = android.view.View.VISIBLE
            } else {
                textViewAmountUnit.visibility = android.view.View.GONE
            }
            
            // Barcode
            if (product.barcode.isNullOrEmpty()) {
                textViewBarcode.visibility = android.view.View.GONE
            } else {
                textViewBarcode.text = "Streckkod: ${product.barcode}"
                textViewBarcode.visibility = android.view.View.VISIBLE
            }
            
            // Description
            if (product.description.isNullOrEmpty()) {
                textViewDescription.visibility = android.view.View.GONE
            } else {
                textViewDescription.text = product.description
                textViewDescription.visibility = android.view.View.VISIBLE
            }
            
            // Deposit
            if (product.hasDeposit && product.depositAmount != null) {
                textViewDeposit.text = "Pant: ${priceFormatter.format(product.depositAmount)} kr"
                textViewDeposit.visibility = android.view.View.VISIBLE
            } else {
                textViewDeposit.visibility = android.view.View.GONE
            }
            
            // Button actions
            buttonEdit.setOnClickListener { onEditClick(productWithPrices) }
            buttonDelete.setOnClickListener { onDeleteClick(productWithPrices) }
            buttonViewPrices.setOnClickListener { onViewPricesClick(productWithPrices) }
        }
    }

    override fun getItemCount(): Int = products.size
}