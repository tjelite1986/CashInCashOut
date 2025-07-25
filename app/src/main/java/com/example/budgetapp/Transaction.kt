package com.example.budgetapp

import com.example.budgetapp.database.entities.Expense
import com.example.budgetapp.database.entities.Income
import com.example.budgetapp.database.entities.Loan
import java.util.*

sealed class Transaction {
    data class IncomeTransaction(val income: Income) : Transaction()
    data class ExpenseTransaction(val expense: Expense) : Transaction()
    data class LoanTransaction(val loan: Loan) : Transaction()
    
    fun getId(): Long = when (this) {
        is IncomeTransaction -> income.id
        is ExpenseTransaction -> expense.id
        is LoanTransaction -> loan.id
    }
    
    fun getTitle(): String = when (this) {
        is IncomeTransaction -> income.title
        is ExpenseTransaction -> expense.title
        is LoanTransaction -> loan.title
    }
    
    fun getAmount(): Double = when (this) {
        is IncomeTransaction -> income.amount
        is ExpenseTransaction -> expense.amount
        is LoanTransaction -> loan.amount
    }
    
    fun getDescription(): String = when (this) {
        is IncomeTransaction -> income.description ?: income.title
        is ExpenseTransaction -> expense.description ?: expense.title
        is LoanTransaction -> loan.description ?: loan.title
    }
    
    fun getDate(): Long = when (this) {
        is IncomeTransaction -> income.date
        is ExpenseTransaction -> expense.date
        is LoanTransaction -> loan.dueDate ?: loan.createdAt
    }
    
    fun getCategoryName(): String = when (this) {
        is IncomeTransaction -> income.category ?: "Inkomst"
        is ExpenseTransaction -> expense.category ?: "Utgift"
        is LoanTransaction -> when (loan.type.toString()) {
            "BORROWED" -> "Lånat"
            "LENT" -> "Utlånat"
            else -> "Lån"
        }
    }
    
    fun isRecurring(): Boolean = when (this) {
        is IncomeTransaction -> income.isRecurring
        is ExpenseTransaction -> expense.isRecurring
        is LoanTransaction -> false // Loans are typically not recurring
    }
    
    fun getRecurringType(): String? = when (this) {
        is IncomeTransaction -> income.recurringType
        is ExpenseTransaction -> expense.recurringType
        is LoanTransaction -> null
    }
    
    fun isIncome(): Boolean = this is IncomeTransaction
}