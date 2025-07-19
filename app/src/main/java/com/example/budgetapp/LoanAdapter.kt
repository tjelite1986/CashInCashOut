package com.example.budgetapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.database.entities.Loan
import com.example.budgetapp.database.entities.LoanType
import com.example.budgetapp.databinding.ItemLoanBinding
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class LoanAdapter(
    private var loans: List<Loan>,
    private val onEditClick: (Loan) -> Unit,
    private val onDeleteClick: (Loan) -> Unit,
    private val onPayBackClick: (Loan) -> Unit,
    private val onUndoPaymentClick: (Loan) -> Unit
) : RecyclerView.Adapter<LoanAdapter.LoanViewHolder>() {

    private val currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
    private val dateFormat = SimpleDateFormat("MMM dd", Locale.getDefault())

    class LoanViewHolder(val binding: ItemLoanBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoanViewHolder {
        val binding = ItemLoanBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LoanViewHolder, position: Int) {
        val loan = loans[position]
        
        with(holder.binding) {
            textTitle.text = loan.title
            textPersonName.text = loan.personName
            textDescription.text = loan.description ?: ""
            
            if (loan.type == LoanType.BORROWED) {
                // Borrowed styling (debt - red)
                textAmount.text = "-${currencyFormat.format(loan.amount).replace("kr", "").trim()} kr"
                textAmount.setTextColor(0xFFF44336.toInt())
                indicatorType.setBackgroundColor(0xFFF44336.toInt())
                textType.text = "Lånat från"
            } else {
                // Lent styling (claim - green)
                textAmount.text = "+${currencyFormat.format(loan.amount).replace("kr", "").trim()} kr"
                textAmount.setTextColor(0xFF4CAF50.toInt())
                indicatorType.setBackgroundColor(0xFF4CAF50.toInt())
                textType.text = "Lånat ut till"
            }
            
            // Status
            if (loan.isPaidBack) {
                textStatus.text = "Återbetalt"
                textStatus.setTextColor(0xFF9E9E9E.toInt())
            } else {
                textStatus.text = "Aktivt"
                textStatus.setTextColor(0xFFFF9800.toInt())
            }
            
            // Due date
            if (loan.dueDate != null) {
                textDueDate.text = "Förfaller: ${dateFormat.format(Date(loan.dueDate))}"
                textDueDate.visibility = android.view.View.VISIBLE
                
                // Check if overdue
                if (loan.dueDate < System.currentTimeMillis() && !loan.isPaidBack) {
                    textDueDate.setTextColor(0xFFF44336.toInt())
                } else {
                    textDueDate.setTextColor(0xFFB0B0B0.toInt())
                }
            } else {
                textDueDate.visibility = android.view.View.GONE
            }
            
            btnMenu.setOnClickListener {
                showContextMenu(holder, loan)
            }
        }
    }

    private fun showContextMenu(holder: LoanViewHolder, loan: Loan) {
        val popup = androidx.appcompat.widget.PopupMenu(holder.itemView.context, holder.binding.btnMenu)
        popup.menuInflater.inflate(R.menu.loan_context_menu, popup.menu)
        
        // Show/hide options based on payment status
        popup.menu.findItem(R.id.action_pay_back)?.isVisible = !loan.isPaidBack
        popup.menu.findItem(R.id.action_undo_payment)?.isVisible = loan.isPaidBack
        
        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_edit -> {
                    onEditClick(loan)
                    true
                }
                R.id.action_pay_back -> {
                    onPayBackClick(loan)
                    true
                }
                R.id.action_undo_payment -> {
                    onUndoPaymentClick(loan)
                    true
                }
                R.id.action_delete -> {
                    onDeleteClick(loan)
                    true
                }
                else -> false
            }
        }
        popup.show()
    }

    override fun getItemCount(): Int = loans.size

    fun updateLoans(newLoans: List<Loan>) {
        loans = newLoans
        notifyDataSetChanged()
    }
}