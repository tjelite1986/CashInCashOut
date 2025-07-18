package com.example.budgetapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.database.entities.Income
import com.google.android.material.card.MaterialCardView
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class IncomeAdapter(
    private val incomes: MutableList<Income>,
    private val onEditClick: (Income) -> Unit,
    private val onDeleteClick: (Income) -> Unit
) : RecyclerView.Adapter<IncomeAdapter.IncomeViewHolder>() {

    private val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    private val currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_income, parent, false)
        return IncomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: IncomeViewHolder, position: Int) {
        val income = incomes[position]
        holder.bind(income)
    }

    override fun getItemCount(): Int = incomes.size

    inner class IncomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardView: MaterialCardView = itemView.findViewById(R.id.cardIncome)
        private val tvTitle: TextView = itemView.findViewById(R.id.tvIncomeTitle)
        private val tvAmount: TextView = itemView.findViewById(R.id.tvIncomeAmount)
        private val tvCategory: TextView = itemView.findViewById(R.id.tvIncomeCategory)
        private val tvDate: TextView = itemView.findViewById(R.id.tvIncomeDate)
        private val tvDescription: TextView = itemView.findViewById(R.id.tvIncomeDescription)
        private val tvRecurring: TextView = itemView.findViewById(R.id.tvIncomeRecurring)
        private val btnEdit: ImageButton = itemView.findViewById(R.id.btnEditIncome)
        private val btnDelete: ImageButton = itemView.findViewById(R.id.btnDeleteIncome)

        fun bind(income: Income) {
            tvTitle.text = income.title
            tvAmount.text = currencyFormat.format(income.amount)
            tvCategory.text = income.category
            tvDate.text = dateFormat.format(Date(income.date))

            // Show description if available
            if (!income.description.isNullOrEmpty()) {
                tvDescription.text = income.description
                tvDescription.visibility = View.VISIBLE
            } else {
                tvDescription.visibility = View.GONE
            }

            // Show recurring info if applicable
            if (income.isRecurring) {
                val recurringText = when (income.recurringType) {
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
                onEditClick(income)
            }

            btnDelete.setOnClickListener {
                onDeleteClick(income)
            }
        }
    }
}