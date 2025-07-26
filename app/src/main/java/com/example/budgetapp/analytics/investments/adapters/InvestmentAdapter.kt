package com.example.budgetapp.analytics.investments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.analytics.investments.entities.Investment
import com.example.budgetapp.analytics.investments.entities.InvestmentType
import java.text.NumberFormat
import java.util.*

class InvestmentAdapter(
    private val onInvestmentClick: (Investment) -> Unit,
    private val onEditClick: (Investment) -> Unit,
    private val onDeleteClick: (Investment) -> Unit
) : ListAdapter<Investment, InvestmentAdapter.InvestmentViewHolder>(InvestmentDiffCallback()) {
    
    private val numberFormat = NumberFormat.getCurrencyInstance(Locale("sv", "SE"))
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvestmentViewHolder {
        val textView = TextView(parent.context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setPadding(16, 16, 16, 16)
            textSize = 16f
        }
        return InvestmentViewHolder(textView)
    }
    
    override fun onBindViewHolder(holder: InvestmentViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    
    inner class InvestmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView = itemView as TextView
        
        fun bind(investment: Investment) {
            val currentValue = investment.currentValue * investment.quantity
            val gain = currentValue - (investment.averageCost * investment.quantity)
            val gainPercent = if (investment.averageCost > 0) {
                ((currentValue - investment.averageCost * investment.quantity) / (investment.averageCost * investment.quantity)) * 100
            } else 0.0

            textView.text = """
                ${investment.name} (${investment.symbol ?: "N/A"})
                Type: ${formatInvestmentType(investment.type)}
                Quantity: ${investment.quantity}
                Current Value: ${numberFormat.format(currentValue)}
                Gain/Loss: ${numberFormat.format(gain)} (${String.format("%.2f", gainPercent)}%)
            """.trimIndent()

            itemView.setOnClickListener { onInvestmentClick(investment) }
            itemView.setOnLongClickListener { 
                onEditClick(investment)
                true
            }
        }
        
        private fun formatInvestmentType(type: InvestmentType): String {
            return when (type) {
                InvestmentType.STOCK -> "Aktie"
                InvestmentType.FUND -> "Fond"
                InvestmentType.ETF -> "ETF"
                InvestmentType.BOND -> "Obligation"
                InvestmentType.CRYPTO -> "Krypto"
                InvestmentType.REAL_ESTATE -> "Fastighet"
                InvestmentType.COMMODITY -> "Råvara"
                InvestmentType.SAVINGS_ACCOUNT -> "Sparkonto"
                InvestmentType.PENSION -> "Pension"
                InvestmentType.OTHER -> "Annat"
            }
        }
        
        private fun formatQuantity(quantity: Double): String {
            return if (quantity == quantity.toInt().toDouble()) {
                quantity.toInt().toString()
            } else {
                String.format("%.2f", quantity)
            }
        }
        
    }
}

class InvestmentDiffCallback : DiffUtil.ItemCallback<Investment>() {
    
    override fun areItemsTheSame(oldItem: Investment, newItem: Investment): Boolean {
        return oldItem.id == newItem.id
    }
    
    override fun areContentsTheSame(oldItem: Investment, newItem: Investment): Boolean {
        return oldItem == newItem
    }
}
