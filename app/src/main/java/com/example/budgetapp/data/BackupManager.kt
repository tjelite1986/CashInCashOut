package com.example.budgetapp.data

import android.content.Context
import android.net.Uri
import com.example.budgetapp.database.BudgetDatabase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.flow.first
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.text.SimpleDateFormat
import java.util.*

class BackupManager(private val context: Context, private val database: BudgetDatabase) {
    
    data class ImportResult(
        val itemsAdded: Int,
        val duplicatesSkipped: Int
    )
    
    private val gson: Gson = GsonBuilder()
        .setPrettyPrinting()
        .create()
    
    suspend fun exportData(): BackupData = withContext(Dispatchers.IO) {
        val categories = database.categoryDao().getAllCategories().first()
        val incomes = database.incomeDao().getAllIncomes().first()
        val expenses = database.expenseDao().getAllExpenses().first()
        val loans = database.loanDao().getAllLoans().first()
        val budgets = database.budgetDao().getAllBudgets().first()
        val stores = database.storeDao().getAllStores().first()
        val products = database.productDao().getAllProducts().first()
        val productStores = database.productStoreDao().getAllProductStores().first()
        
        BackupData(
            categories = categories,
            incomes = incomes,
            expenses = expenses,
            loans = loans,
            budgets = budgets,
            stores = stores,
            products = products,
            productStores = productStores
        )
    }
    
    suspend fun exportToFile(uri: Uri): Result<String> = withContext(Dispatchers.IO) {
        try {
            val backupData = exportData()
            val jsonString = gson.toJson(backupData)
            
            context.contentResolver.openOutputStream(uri)?.use { outputStream ->
                OutputStreamWriter(outputStream).use { writer ->
                    writer.write(jsonString)
                }
            } ?: return@withContext Result.failure(Exception("Kunde inte öppna fil för skrivning"))
            
            val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
                .format(Date(backupData.exportDate))
            
            Result.success("Backup exporterad: $timestamp")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun importFromFile(uri: Uri): Result<String> = withContext(Dispatchers.IO) {
        try {
            val jsonString = context.contentResolver.openInputStream(uri)?.use { inputStream ->
                BufferedReader(InputStreamReader(inputStream)).use { reader ->
                    reader.readText()
                }
            } ?: return@withContext Result.failure(Exception("Kunde inte läsa fil"))
            
            val backupData = gson.fromJson(jsonString, BackupData::class.java)
            
            // Importera data till databasen
            val importResult = importData(backupData)
            
            Result.success("Import slutförd! ${importResult.itemsAdded} nya objekt lades till. ${importResult.duplicatesSkipped} dubbletter skippades.")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    private suspend fun importData(backupData: BackupData): ImportResult = withContext(Dispatchers.IO) {
        var duplicatesSkipped = 0
        var itemsAdded = 0
        
        // Importera kategorier först (andra tabeller refererar till dem)
        backupData.categories.forEach { category ->
            val duplicateCount = database.categoryDao().checkDuplicateCategory(category.name)
            if (duplicateCount == 0) {
                database.categoryDao().insertCategory(category.copy(id = 0))
                itemsAdded++
            } else {
                duplicatesSkipped++
            }
        }
        
        // Importera butiker med dubblettdetektering
        backupData.stores.forEach { store ->
            val duplicateCount = database.storeDao().checkDuplicateStore(store.name, store.chain ?: "")
            if (duplicateCount == 0) {
                database.storeDao().insertStore(store.copy(id = 0))
                itemsAdded++
            } else {
                duplicatesSkipped++
            }
        }
        
        // Importera produkter (för nu utan dubblettdetektering - kan läggas till senare)
        backupData.products.forEach { product ->
            database.productDao().insertProduct(product.copy(id = 0))
            itemsAdded++
        }
        
        // Importera inkomster med dubblettdetektering
        backupData.incomes.forEach { income ->
            val duplicateCount = database.incomeDao().checkDuplicateIncome(
                income.title, income.amount, income.category ?: "", income.date
            )
            if (duplicateCount == 0) {
                database.incomeDao().insertIncome(income.copy(id = 0))
                itemsAdded++
            } else {
                duplicatesSkipped++
            }
        }
        
        // Importera utgifter med dubblettdetektering
        backupData.expenses.forEach { expense ->
            val duplicateCount = database.expenseDao().checkDuplicateExpense(
                expense.title, expense.amount, expense.category ?: "", expense.date, expense.store
            )
            if (duplicateCount == 0) {
                database.expenseDao().insertExpense(expense.copy(id = 0))
                itemsAdded++
            } else {
                duplicatesSkipped++
            }
        }
        
        // Importera lån med dubblettdetektering
        backupData.loans.forEach { loan ->
            val duplicateCount = database.loanDao().checkDuplicateLoan(
                loan.title, loan.amount, loan.personName, loan.type
            )
            if (duplicateCount == 0) {
                database.loanDao().insertLoan(loan.copy(id = 0))
                itemsAdded++
            } else {
                duplicatesSkipped++
            }
        }
        
        // Importera budgetar (för nu utan dubblettdetektering)
        backupData.budgets.forEach { budget ->
            database.budgetDao().insertBudget(budget.copy(id = 0))
            itemsAdded++
        }
        
        // Importera produktbutik-relationer sist (har redan unique constraint)
        if (backupData.productStores.isNotEmpty()) {
            val productStoresWithoutId = backupData.productStores.map { it.copy(id = 0) }
            database.productStoreDao().insertProductStores(productStoresWithoutId)
            itemsAdded += backupData.productStores.size
        }
        
        return@withContext ImportResult(itemsAdded, duplicatesSkipped)
    }
    
    fun generateBackupFileName(): String {
        val timestamp = SimpleDateFormat("yyyy-MM-dd_HH-mm", Locale.getDefault())
            .format(Date())
        return "budget_backup_$timestamp.json"
    }
}