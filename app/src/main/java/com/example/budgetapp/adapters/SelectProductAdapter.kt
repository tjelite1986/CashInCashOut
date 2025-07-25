package com.example.budgetapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.database.entities.Product
import com.example.budgetapp.databinding.ItemSelectProductBinding

class SelectProductAdapter(
    private val onProductSelected: (Product) -> Unit
) : RecyclerView.Adapter<SelectProductAdapter.ProductViewHolder>() {
    
    private var products = listOf<Product>()
    private var filteredProducts = listOf<Product>()
    
    fun updateProducts(newProducts: List<Product>) {
        products = newProducts
        filteredProducts = newProducts
        notifyDataSetChanged()
    }
    
    fun filter(query: String) {
        filteredProducts = if (query.isEmpty()) {
            products
        } else {
            products.filter { product ->
                product.name.contains(query, ignoreCase = true) ||
                (product.barcode != null && product.barcode.contains(query, ignoreCase = true))
            }
        }
        notifyDataSetChanged()
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemSelectProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ProductViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(filteredProducts[position])
    }
    
    override fun getItemCount() = filteredProducts.size
    
    inner class ProductViewHolder(
        private val binding: ItemSelectProductBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(product: Product) {
            binding.apply {
                textViewProductName.text = product.name
                
                // Bygg kategoritext med pant-info
                val categoryText = buildString {
                    append("Produkt")
                    if (product.hasDeposit && product.depositAmount != null) {
                        append(" • Pant: ${String.format("%.2f", product.depositAmount)} kr")
                    }
                }
                textViewProductCategory.text = categoryText
                
                if (product.barcode != null) {
                    textViewProductBarcode.text = "Streckkod: ${product.barcode}"
                    textViewProductBarcode.visibility = android.view.View.VISIBLE
                } else {
                    textViewProductBarcode.visibility = android.view.View.GONE
                }
                
                root.setOnClickListener {
                    onProductSelected(product)
                }
            }
        }
    }
}