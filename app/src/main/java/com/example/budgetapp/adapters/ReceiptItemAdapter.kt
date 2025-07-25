package com.example.budgetapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.data.ReceiptItemInput
import com.example.budgetapp.databinding.ItemReceiptItemBinding
import java.text.NumberFormat
import java.util.*

class ReceiptItemAdapter(
    private val onEditClick: (ReceiptItemInput, Int) -> Unit,
    private val onDeleteClick: (ReceiptItemInput, Int) -> Unit
) : ListAdapter<ReceiptItemInput, ReceiptItemAdapter.ReceiptItemViewHolder>(ReceiptItemDiffCallback()) {

    private val numberFormat = NumberFormat.getCurrencyInstance(Locale("sv", "SE"))

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiptItemViewHolder {
        val binding = ItemReceiptItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ReceiptItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReceiptItemViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    inner class ReceiptItemViewHolder(
        private val binding: ItemReceiptItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ReceiptItemInput, position: Int) {
            binding.apply {
                // Visa produktnamn med ev. storlek och pant
                val nameText = buildString {
                    append(item.productName)
                    if (item.productSize != null) {
                        append(" (${item.productSize})")
                    }
                    if (item.hasDeposit) {
                        append(" [med pant]")
                    }
                }
                textProductName.text = nameText
                
                textQuantity.text = "${item.quantity} ${item.unit}"
                
                // Visa styckpris + pant om det finns
                val priceText = if (item.hasDeposit && item.depositAmount > 0) {
                    "${numberFormat.format(item.unitPrice)} + ${numberFormat.format(item.depositAmount)} pant"
                } else {
                    numberFormat.format(item.unitPrice)
                }
                textUnitPrice.text = priceText
                
                textTotalPrice.text = numberFormat.format(item.totalPrice)
                
                if (item.discount > 0) {
                    textDiscount.text = "- ${numberFormat.format(item.discount)}"
                    textDiscount.visibility = android.view.View.VISIBLE
                } else {
                    textDiscount.visibility = android.view.View.GONE
                }

                buttonEdit.setOnClickListener {
                    onEditClick(item, position)
                }

                buttonDelete.setOnClickListener {
                    onDeleteClick(item, position)
                }
            }
        }
    }
}

class ReceiptItemDiffCallback : DiffUtil.ItemCallback<ReceiptItemInput>() {
    override fun areItemsTheSame(oldItem: ReceiptItemInput, newItem: ReceiptItemInput): Boolean {
        return oldItem.productName == newItem.productName && 
               oldItem.unitPrice == newItem.unitPrice &&
               oldItem.quantity == newItem.quantity
    }

    override fun areContentsTheSame(oldItem: ReceiptItemInput, newItem: ReceiptItemInput): Boolean {
        return oldItem == newItem
    }
}