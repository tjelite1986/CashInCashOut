package com.example.budgetapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.R
import com.example.budgetapp.database.entities.ProductCategory

class ProductCategoryAdapter(
    private val onEditClick: (ProductCategory) -> Unit,
    private val onDeleteClick: (ProductCategory) -> Unit
) : RecyclerView.Adapter<ProductCategoryAdapter.ProductCategoryViewHolder>() {
    
    private var categories: List<ProductCategory> = emptyList()
    
    fun updateCategories(newCategories: List<ProductCategory>) {
        categories = newCategories
        notifyDataSetChanged()
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductCategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product_category, parent, false)
        return ProductCategoryViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: ProductCategoryViewHolder, position: Int) {
        holder.bind(categories[position])
    }
    
    override fun getItemCount(): Int = categories.size
    
    inner class ProductCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textCategoryName: TextView = itemView.findViewById(R.id.textCategoryName)
        private val textCategoryType: TextView = itemView.findViewById(R.id.textCategoryType)
        private val buttonEdit: ImageButton = itemView.findViewById(R.id.buttonEdit)
        private val buttonDelete: ImageButton = itemView.findViewById(R.id.buttonDelete)
        
        fun bind(category: ProductCategory) {
            textCategoryName.text = category.name
            textCategoryType.text = if (category.isDefault) "Standard" else "Anpassad"
            
            // Default categories should have limited edit capabilities
            if (category.isDefault) {
                buttonDelete.visibility = View.GONE
                textCategoryType.setTextColor(itemView.context.getColor(android.R.color.holo_blue_dark))
            } else {
                buttonDelete.visibility = View.VISIBLE
                textCategoryType.setTextColor(itemView.context.getColor(android.R.color.holo_green_dark))
            }
            
            buttonEdit.setOnClickListener {
                onEditClick(category)
            }
            
            buttonDelete.setOnClickListener {
                onDeleteClick(category)
            }
        }
    }
}