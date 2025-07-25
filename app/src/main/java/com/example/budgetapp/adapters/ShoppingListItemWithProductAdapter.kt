package com.example.budgetapp.adapters

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.data.ShoppingListItemWithProduct
import com.example.budgetapp.database.entities.ShoppingListItem
import com.example.budgetapp.databinding.ItemShoppingListItemBinding

class ShoppingListItemWithProductAdapter(
    private val onItemClick: (ShoppingListItem) -> Unit,
    private val onDeleteClick: (ShoppingListItem) -> Unit,
    private val onCompleteToggle: (ShoppingListItem) -> Unit,
    private val onQuantityChange: (ShoppingListItem, Int) -> Unit,
    private val onActualPriceChange: (ShoppingListItem, Double) -> Unit
) : ListAdapter<ShoppingListItemWithProduct, ShoppingListItemWithProductAdapter.ViewHolder>(ShoppingListItemWithProductDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemShoppingListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemShoppingListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private var currentItemWithProduct: ShoppingListItemWithProduct? = null

        fun bind(itemWithProduct: ShoppingListItemWithProduct) {
            currentItemWithProduct = itemWithProduct
            val item = itemWithProduct.item
            
            binding.apply {
                // Visa namnet - använd produktnamn om det finns, annars custom namn
                val displayName = itemWithProduct.getDisplayName()
                val productInfo = if (itemWithProduct.product != null && itemWithProduct.product.hasDeposit && itemWithProduct.product.depositAmount != null) {
                    "$displayName (pant: ${String.format("%.2f", itemWithProduct.product.depositAmount)} kr)"
                } else {
                    displayName
                }
                textItemName.text = productInfo
                
                // Visa kvantitet
                editTextQuantity.setText(item.quantity.toString())
                textUnit.text = item.unit ?: "st"
                
                // Visa priser om de finns
                if (item.estimatedPrice != null && item.estimatedPrice > 0) {
                    textEstimatedPrice.text = String.format("~%.2f kr", item.estimatedPrice)
                } else {
                    textEstimatedPrice.text = "Inget pris"
                }
                
                // Sätt faktiskt pris i EditText
                if (item.actualPrice != null && item.actualPrice > 0) {
                    editTextActualPrice.setText(String.format("%.2f", item.actualPrice))
                } else {
                    editTextActualPrice.setText("")
                }
                
                // Visa anteckningar om de finns
                if (!item.notes.isNullOrEmpty()) {
                    textNotes.text = item.notes
                    textNotes.visibility = android.view.View.VISIBLE
                } else {
                    textNotes.visibility = android.view.View.GONE
                }
                
                // Checkbox för slutförd status
                checkboxCompleted.isChecked = item.isCompleted
                
                // Prioritet (visuell indikering)
                when (item.priority) {
                    1 -> {
                        // Hög prioritet - röd bakgrund
                        root.setCardBackgroundColor(
                            root.context.getColor(android.R.color.holo_red_light)
                        )
                        root.alpha = 0.9f
                    }
                    -1 -> {
                        // Låg prioritet - grå bakgrund
                        root.setCardBackgroundColor(
                            root.context.getColor(android.R.color.darker_gray)
                        )
                        root.alpha = 0.7f
                    }
                    else -> {
                        // Normal prioritet
                        root.setCardBackgroundColor(
                            root.context.getColor(android.R.color.white)
                        )
                        root.alpha = if (item.isCompleted) 0.6f else 1.0f
                    }
                }
                
                // Klick-lyssnare - skicka item, inte itemWithProduct
                root.setOnClickListener {
                    onItemClick(item)
                }
                
                buttonDelete.setOnClickListener {
                    onDeleteClick(item)
                }
                
                checkboxCompleted.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked != item.isCompleted) {
                        onCompleteToggle(item)
                    }
                }
                
                // Kvantitetsändring
                editTextQuantity.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                    override fun afterTextChanged(s: Editable?) {
                        val quantity = s?.toString()?.toIntOrNull()
                        if (quantity != null && quantity != item.quantity && quantity > 0) {
                            onQuantityChange(item, quantity)
                        }
                    }
                })
                
                // Faktiskt pris-ändring
                editTextActualPrice.addTextChangedListener(object : TextWatcher {
                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                    override fun afterTextChanged(s: Editable?) {
                        val price = s?.toString()?.toDoubleOrNull()
                        if (price != null && price != item.actualPrice && price >= 0) {
                            onActualPriceChange(item, price)
                        }
                    }
                })
            }
        }
    }

    class ShoppingListItemWithProductDiffCallback : DiffUtil.ItemCallback<ShoppingListItemWithProduct>() {
        override fun areItemsTheSame(oldItem: ShoppingListItemWithProduct, newItem: ShoppingListItemWithProduct): Boolean {
            return oldItem.item.id == newItem.item.id
        }

        override fun areContentsTheSame(oldItem: ShoppingListItemWithProduct, newItem: ShoppingListItemWithProduct): Boolean {
            return oldItem.item == newItem.item && oldItem.product == newItem.product
        }
    }
}