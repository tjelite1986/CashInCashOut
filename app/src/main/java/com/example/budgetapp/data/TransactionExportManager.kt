package com.example.budgetapp.data

import android.content.Context
import android.net.Uri
import androidx.documentfile.provider.DocumentFile
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.Expense
import com.example.budgetapp.database.entities.Income
import com.example.budgetapp.database.entities.Loan
import com.example.budgetapp.database.entities.LoanType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.OutputStreamWriter
import java.text.SimpleDateFormat
import java.util.*

class TransactionExportManager(
    private val context: Context,
    private val database: BudgetDatabase
) {
    
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    private val dateOnlyFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    
    suspend fun exportToCsv(uri: Uri, includeExpenses: Boolean = true, includeIncomes: Boolean = true, includeLoans: Boolean = true): Result<String> {
        return withContext(Dispatchers.IO) {
            try {
                val outputStream = context.contentResolver.openOutputStream(uri)
                    ?: return@withContext Result.failure(Exception("Kunde inte öppna fil för skrivning"))
                
                val writer = OutputStreamWriter(outputStream)
                
                // Write CSV header
                writer.append("Typ,Titel,Belopp,Beskrivning,Kategori,Butik/Person,Datum,Återkommande,Återkommande_typ,Betalningsmetod,Skapat,Uppdaterat,Status\n")
                
                // Export expenses
                if (includeExpenses) {
                    val expenses = database.expenseDao().getAllExpenses()
                    // For simplicity, let's get a snapshot of current data
                    val expensesList = getExpensesSnapshot()
                    expensesList.forEach { expense ->
                        writer.append(formatExpenseAsCsv(expense))
                        writer.append("\n")
                    }
                }
                
                // Export incomes
                if (includeIncomes) {
                    val incomesList = getIncomesSnapshot()
                    incomesList.forEach { income ->
                        writer.append(formatIncomeAsCsv(income))
                        writer.append("\n")
                    }
                }
                
                // Export loans
                if (includeLoans) {
                    val loansList = getLoansSnapshot()
                    loansList.forEach { loan ->
                        writer.append(formatLoanAsCsv(loan))
                        writer.append("\n")
                    }
                }
                
                writer.close()
                outputStream.close()
                
                Result.success("CSV-export slutförd framgångsrikt")
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
    
    suspend fun exportToExcel(uri: Uri, includeExpenses: Boolean = true, includeIncomes: Boolean = true, includeLoans: Boolean = true): Result<String> {
        return withContext(Dispatchers.IO) {
            try {
                // For now, Excel export is not supported due to SDK requirements
                // Fall back to CSV export with .xlsx extension
                return@withContext exportToCsv(uri, includeExpenses, includeIncomes, includeLoans)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
    
    private suspend fun getExpensesSnapshot(): List<Expense> {
        return database.expenseDao().getExpensesBetweenDates(0, System.currentTimeMillis())
    }
    
    private suspend fun getIncomesSnapshot(): List<Income> {
        return getAllIncomesSnapshot()
    }
    
    private suspend fun getAllIncomesSnapshot(): List<Income> {
        return database.incomeDao().getAllIncomesSnapshot()
    }
    
    private suspend fun getLoansSnapshot(): List<Loan> {
        return getAllLoansSnapshot()
    }
    
    private suspend fun getAllLoansSnapshot(): List<Loan> {
        return database.loanDao().getAllLoansSnapshot()
    }
    
    private fun formatExpenseAsCsv(expense: Expense): String {
        return "Utgift,\"${escape(expense.title)}\",${expense.amount},\"${escape(expense.description ?: "")}\",\"${escape(expense.category ?: "")}\",\"${escape(expense.store ?: "")}\",\"${dateFormat.format(Date(expense.date))}\",${expense.isRecurring},\"${escape(expense.recurringType ?: "")}\",\"${escape(expense.paymentMethod ?: "")}\",\"${dateFormat.format(Date(expense.createdAt))}\",\"${dateFormat.format(Date(expense.updatedAt))}\",\"\""
    }
    
    private fun formatIncomeAsCsv(income: Income): String {
        return "Inkomst,\"${escape(income.title)}\",${income.amount},\"${escape(income.description ?: "")}\",\"${escape(income.category ?: "")}\",\"\",\"${dateFormat.format(Date(income.date))}\",${income.isRecurring},\"${escape(income.recurringType ?: "")}\",\"\",\"${dateFormat.format(Date(income.createdAt))}\",\"${dateFormat.format(Date(income.updatedAt))}\",\"\""
    }
    
    private fun formatLoanAsCsv(loan: Loan): String {
        val loanStatus = when {
            loan.isPaidBack -> "Återbetalt"
            loan.dueDate != null && loan.dueDate < System.currentTimeMillis() -> "Förfallet"
            else -> "Aktivt"
        }
        val loanTypeText = if (loan.type == LoanType.BORROWED) "Lånat från" else "Lånat ut till"
        return "Lån,\"${escape(loan.title)}\",${loan.amount},\"${escape(loan.description ?: "")}\",\"$loanTypeText\",\"${escape(loan.personName)}\",\"${dateFormat.format(Date(loan.createdAt))}\",false,\"\",\"\",\"${dateFormat.format(Date(loan.createdAt))}\",\"${dateFormat.format(Date(loan.updatedAt))}\",\"$loanStatus\""
    }
    
    private fun escape(value: String): String {
        return value.replace("\"", "\"\"")
    }
    
}