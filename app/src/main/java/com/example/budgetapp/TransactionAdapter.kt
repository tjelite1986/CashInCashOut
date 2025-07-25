package com.example.budgetapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.databinding.ItemTransactionBinding
import com.example.budgetapp.data.ThemeSettings
import com.example.budgetapp.data.InterfaceStyle
import com.example.budgetapp.utils.ThemeAware
import com.example.budgetapp.utils.ThemeManager
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class TransactionAdapter(
    private var transactions: List<Transaction>,
    private val onEditClick: (Transaction) -> Unit,
    private val onDeleteClick: (Transaction) -> Unit
) : RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>(), ThemeAware {
    
    private var currentThemeSettings: ThemeSettings? = null
    private lateinit var themeManager: ThemeManager

    private val currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
    private val dateFormat = SimpleDateFormat("MMM dd", Locale.getDefault())

    class TransactionViewHolder(val binding: ItemTransactionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        if (!::themeManager.isInitialized) {
            themeManager = ThemeManager.getInstance(parent.context.applicationContext)
        }
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
            
            // Apply current theme to this item
            currentThemeSettings?.let { settings ->
                applyThemeToItem(holder.binding, settings)
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
    
    override fun applyTheme(settings: ThemeSettings) {
        currentThemeSettings = settings
        // Only notify if we have a valid context and the adapter is attached
        if (::themeManager.isInitialized) {
            notifyDataSetChanged() // Refresh all items with new theme
        }
    }
    
    private fun applyThemeToItem(binding: ItemTransactionBinding, settings: ThemeSettings) {
        if (!::themeManager.isInitialized) return
        
        val accentColor = themeManager.getAccentColorInt()
        val isColorful = settings.interfaceStyle == InterfaceStyle.COLORFUL
        
        // Apply theme to the card background
        if (isColorful) {
            // Very subtle background tint
            val verySubtleColor = adjustColorAlpha(accentColor, 0.03f)
            binding.root.setBackgroundColor(verySubtleColor)
        } else {
            // Reset to default background for material mode
            binding.root.setBackgroundResource(android.R.color.transparent)
        }
        
        // Apply subtle accent color to menu button if colorful
        if (isColorful) {
            binding.btnMenu.setColorFilter(adjustColorAlpha(accentColor, 0.4f))
        } else {
            binding.btnMenu.clearColorFilter()
        }
    }
    
    private fun adjustColorAlpha(color: Int, alpha: Float): Int {
        val alphaInt = (255 * alpha).toInt().coerceIn(0, 255)
        return Color.argb(alphaInt, Color.red(color), Color.green(color), Color.blue(color))
    }
}