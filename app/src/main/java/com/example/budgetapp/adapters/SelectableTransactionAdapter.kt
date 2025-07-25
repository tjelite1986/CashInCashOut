package com.example.budgetapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.R
import com.example.budgetapp.Transaction
import com.example.budgetapp.databinding.ItemSelectableTransactionBinding
import com.example.budgetapp.database.entities.TransactionType
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class SelectableTransactionAdapter(
    private val onSelectionChanged: (List<SelectableTransaction>) -> Unit
) : ListAdapter<SelectableTransactionAdapter.SelectableTransaction, SelectableTransactionAdapter.ViewHolder>(DiffCallback()) {

    private val selectedTransactions = mutableSetOf<SelectableTransaction>()

    data class SelectableTransaction(
        val transaction: Transaction,
        val transactionType: TransactionType,
        var isSelected: Boolean = false
    ) {
        val id: Long get() = transaction.getId()
        val title: String get() = transaction.getTitle()
        val amount: Double get() = transaction.getAmount()
        val date: Long get() = transaction.getDate()
        val category: String get() = transaction.getCategoryName()
        val description: String get() = transaction.getDescription()
        val isRecurring: Boolean get() = transaction.isRecurring()
        val recurringType: String? get() = transaction.getRecurringType()
    }

    class ViewHolder(
        private val binding: ItemSelectableTransactionBinding,
        private val onItemClick: (SelectableTransaction) -> Unit,
        private val onCheckboxClick: (SelectableTransaction) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        private val numberFormat = NumberFormat.getCurrencyInstance(Locale("sv", "SE")).apply {
            currency = Currency.getInstance("SEK")
        }
        
        private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        fun bind(selectableTransaction: SelectableTransaction) {
            val transaction = selectableTransaction.transaction
            
            binding.apply {
                // Set transaction details
                tvTitle.text = selectableTransaction.title
                tvAmount.text = numberFormat.format(selectableTransaction.amount)
                tvCategory.text = selectableTransaction.category.ifEmpty { "Ingen kategori" }
                tvDate.text = dateFormat.format(Date(selectableTransaction.date))
                
                // Set checkbox state
                checkbox.isChecked = selectableTransaction.isSelected
                
                // Show recurring info if applicable
                if (selectableTransaction.isRecurring) {
                    tvRecurringInfo.visibility = android.view.View.VISIBLE
                    tvRecurringInfo.text = when (selectableTransaction.recurringType) {
                        "monthly" -> "Återkommer månadsvis"
                        "weekly" -> "Återkommer veckovis"
                        "yearly" -> "Återkommer årligen"
                        else -> "Återkommande"
                    }
                } else {
                    tvRecurringInfo.visibility = android.view.View.GONE
                }
                
                // Set type icon and amount color
                when (selectableTransaction.transactionType) {
                    TransactionType.INCOME -> {
                        ivTypeIcon.setImageResource(R.drawable.ic_income)
                        tvAmount.setTextColor(android.graphics.Color.parseColor("#4CAF50"))
                    }
                    TransactionType.EXPENSE -> {
                        ivTypeIcon.setImageResource(R.drawable.ic_expenses)
                        tvAmount.setTextColor(android.graphics.Color.parseColor("#F44336"))
                    }
                    TransactionType.LOAN_DUE -> {
                        ivTypeIcon.setImageResource(R.drawable.ic_loan)
                        tvAmount.setTextColor(android.graphics.Color.parseColor("#FF9800"))
                    }
                }
                
                // Set click listeners
                root.setOnClickListener { onItemClick(selectableTransaction) }
                checkbox.setOnClickListener { onCheckboxClick(selectableTransaction) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSelectableTransactionBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(
            binding = binding,
            onItemClick = { toggleSelection(it) },
            onCheckboxClick = { toggleSelection(it) }
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private fun toggleSelection(selectableTransaction: SelectableTransaction) {
        if (selectableTransaction.isSelected) {
            selectedTransactions.remove(selectableTransaction)
            selectableTransaction.isSelected = false
        } else {
            selectedTransactions.add(selectableTransaction)
            selectableTransaction.isSelected = true
        }
        
        // Update the item
        val position = currentList.indexOf(selectableTransaction)
        if (position != -1) {
            notifyItemChanged(position)
        }
        
        onSelectionChanged(selectedTransactions.toList())
    }

    fun selectAll() {
        currentList.forEach { selectableTransaction ->
            if (!selectableTransaction.isSelected) {
                selectableTransaction.isSelected = true
                selectedTransactions.add(selectableTransaction)
            }
        }
        notifyDataSetChanged()
        onSelectionChanged(selectedTransactions.toList())
    }

    fun clearSelection() {
        currentList.forEach { it.isSelected = false }
        selectedTransactions.clear()
        notifyDataSetChanged()
        onSelectionChanged(selectedTransactions.toList())
    }

    fun getSelectedTransactions(): List<SelectableTransaction> = selectedTransactions.toList()

    fun setInitialSelection(selectedIds: Set<Long>) {
        currentList.forEach { selectableTransaction ->
            if (selectableTransaction.id in selectedIds) {
                selectableTransaction.isSelected = true
                selectedTransactions.add(selectableTransaction)
            }
        }
        notifyDataSetChanged()
        onSelectionChanged(selectedTransactions.toList())
    }

    private class DiffCallback : DiffUtil.ItemCallback<SelectableTransaction>() {
        override fun areItemsTheSame(oldItem: SelectableTransaction, newItem: SelectableTransaction): Boolean {
            return oldItem.id == newItem.id && oldItem.transactionType == newItem.transactionType
        }

        override fun areContentsTheSame(oldItem: SelectableTransaction, newItem: SelectableTransaction): Boolean {
            return oldItem == newItem
        }
    }
}