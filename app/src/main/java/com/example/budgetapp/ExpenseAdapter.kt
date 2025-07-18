package com.example.budgetapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.database.entities.Expense
import com.google.android.material.card.MaterialCardView
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class ExpenseAdapter(
    private val expenses: MutableList<Expense>,
    private val onEditClick: (Expense) -> Unit,
    private val onDeleteClick: (Expense) -> Unit
) : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    private val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    private val currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_expense, parent, false)
        return ExpenseViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val expense = expenses[position]
        holder.bind(expense)
    }

    override fun getItemCount(): Int = expenses.size

    inner class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardView: MaterialCardView = itemView.findViewById(R.id.cardExpense)
        private val tvTitle: TextView = itemView.findViewById(R.id.tvExpenseTitle)
        private val tvAmount: TextView = itemView.findViewById(R.id.tvExpenseAmount)
        private val tvCategory: TextView = itemView.findViewById(R.id.tvExpenseCategory)
        private val tvStore: TextView = itemView.findViewById(R.id.tvExpenseStore)
        private val tvPaymentMethod: TextView = itemView.findViewById(R.id.tvExpensePaymentMethod)
        private val tvDate: TextView = itemView.findViewById(R.id.tvExpenseDate)
        private val tvDescription: TextView = itemView.findViewById(R.id.tvExpenseDescription)
        private val tvRecurring: TextView = itemView.findViewById(R.id.tvExpenseRecurring)
        private val btnEdit: ImageButton = itemView.findViewById(R.id.btnEditExpense)
        private val btnDelete: ImageButton = itemView.findViewById(R.id.btnDeleteExpense)

        fun bind(expense: Expense) {
            tvTitle.text = expense.title
            tvAmount.text = currencyFormat.format(expense.amount)
            tvCategory.text = expense.category
            tvDate.text = dateFormat.format(Date(expense.date))

            // Show store if available
            if (!expense.store.isNullOrEmpty()) {
                tvStore.text = expense.store
                tvStore.visibility = View.VISIBLE
            } else {
                tvStore.visibility = View.GONE
            }

            // Show payment method if available
            if (!expense.paymentMethod.isNullOrEmpty()) {
                tvPaymentMethod.text = expense.paymentMethod
                tvPaymentMethod.visibility = View.VISIBLE
            } else {
                tvPaymentMethod.visibility = View.GONE
            }

            // Show description if available
            if (!expense.description.isNullOrEmpty()) {
                tvDescription.text = expense.description
                tvDescription.visibility = View.VISIBLE
            } else {
                tvDescription.visibility = View.GONE
            }

            // Show recurring info if applicable
            if (expense.isRecurring) {
                val recurringText = when (expense.recurringType) {
                    "monthly" -> "Återkommande månadsvis"
                    "weekly" -> "Återkommande veckovis"
                    "yearly" -> "Återkommande årligen"
                    else -> "Återkommande"
                }
                tvRecurring.text = recurringText
                tvRecurring.visibility = View.VISIBLE
            } else {
                tvRecurring.visibility = View.GONE
            }

            btnEdit.setOnClickListener {
                onEditClick(expense)
            }

            btnDelete.setOnClickListener {
                onDeleteClick(expense)
            }
        }
    }
}