package com.example.budgetapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.databinding.ItemTransactionBinding
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class TransactionAdapter(
    private var transactions: List<Transaction>,
    private val onEditClick: (Transaction) -> Unit,
    private val onDeleteClick: (Transaction) -> Unit
) : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    private val currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
    private val dateFormat = SimpleDateFormat("MMM dd", Locale.getDefault())

    class TransactionViewHolder(val binding: ItemTransactionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val binding = ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactions[position]
        
        with(holder.binding) {
            textDescription.text = transaction.getDescription()
            textCategory.text = transaction.getCategoryName()
            textDate.text = dateFormat.format(transaction.getDate())
            
            if (transaction.isIncome()) {
                // Income styling
                textAmount.text = "+${currencyFormat.format(transaction.getAmount()).replace("kr", "").trim()} kr"
                textAmount.setTextColor(0xFF4CAF50.toInt())
                indicatorType.setBackgroundColor(0xFF4CAF50.toInt())
                iconCategory.setImageResource(R.drawable.ic_income)
            } else {
                // Expense styling
                textAmount.text = "-${currencyFormat.format(transaction.getAmount()).replace("kr", "").trim()} kr"
                textAmount.setTextColor(0xFFF44336.toInt())
                indicatorType.setBackgroundColor(0xFFF44336.toInt())
                iconCategory.setImageResource(R.drawable.ic_expenses)
            }
            
            btnMenu.setOnClickListener {
                showContextMenu(holder, transaction)
            }
        }
    }

    private fun showContextMenu(holder: TransactionViewHolder, transaction: Transaction) {
        val popup = androidx.appcompat.widget.PopupMenu(holder.itemView.context, holder.binding.btnMenu)
        popup.menuInflater.inflate(R.menu.transaction_context_menu, popup.menu)
        
        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_edit -> {
                    onEditClick(transaction)
                    true
                }
                R.id.action_delete -> {
                    onDeleteClick(transaction)
                    true
                }
                else -> false
            }
        }
        popup.show()
    }

    override fun getItemCount(): Int = transactions.size

    fun updateTransactions(newTransactions: List<Transaction>) {
        transactions = newTransactions
        notifyDataSetChanged()
    }
}