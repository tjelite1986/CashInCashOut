package com.example.budgetapp

import com.example.budgetapp.database.entities.Expense
import com.example.budgetapp.database.entities.Income
import java.util.*

sealed class Transaction {
    data class IncomeTransaction(val income: Income) : Transaction()
    data class ExpenseTransaction(val expense: Expense) : Transaction()
    
    fun getAmount(): Double = when (this) {
        is IncomeTransaction -> income.amount
        is ExpenseTransaction -> expense.amount
    }
    
    fun getDescription(): String = when (this) {
        is IncomeTransaction -> income.description ?: income.title
        is ExpenseTransaction -> expense.description ?: expense.title
    }
    
    fun getDate(): Date = when (this) {
        is IncomeTransaction -> Date(income.date)
        is ExpenseTransaction -> Date(expense.date)
    }
    
    fun getCategoryName(): String = when (this) {
        is IncomeTransaction -> income.category ?: "Inkomst"
        is ExpenseTransaction -> expense.category ?: "Utgift"
    }
    
    fun isIncome(): Boolean = this is IncomeTransaction
}