package com.example.budgetapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.database.entities.Product
import com.example.budgetapp.databinding.ItemProductBinding

class ProductAdapter(
    private val products: List<Product>,
    private val onEditClick: (Product) -> Unit,
    private val onDeleteClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
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
            
            // Price - not available in basic product view
            textViewPrice.text = "Se priser"
            
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
            
            // Campaign price - not available in basic product view
            textViewCampaignPrice.visibility = android.view.View.GONE
            
            // Deposit
            if (product.hasDeposit && product.depositAmount != null) {
                textViewDeposit.text = "Pant: ${product.depositAmount} kr"
                textViewDeposit.visibility = android.view.View.VISIBLE
            } else {
                textViewDeposit.visibility = android.view.View.GONE
            }
            
            // Description
            if (product.description.isNullOrEmpty()) {
                textViewDescription.visibility = android.view.View.GONE
            } else {
                textViewDescription.text = product.description
                textViewDescription.visibility = android.view.View.VISIBLE
            }
            
            // Click listeners
            buttonEdit.setOnClickListener {
                onEditClick(product)
            }
            
            buttonDelete.setOnClickListener {
                onDeleteClick(product)
            }
        }
    }

    override fun getItemCount(): Int = products.size
}