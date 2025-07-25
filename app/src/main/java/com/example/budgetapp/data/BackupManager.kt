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
            version = "1.0",
            exportDate = System.currentTimeMillis(),
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
                .format(Date(backupData.exportDate ?: System.currentTimeMillis()))
            
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
            
            // Try to detect and handle different backup formats
            val backupData = when {
                isLegacyBudgetAppBackup(jsonString) -> {
                    convertLegacyBackup(jsonString)
                }
                else -> {
                    gson.fromJson(jsonString, BackupData::class.java)
                }
            }
            
            // Importera data till databasen
            val importResult = importData(backupData)
            
            val sourceInfo = if (isLegacyBudgetAppBackup(jsonString)) {
                " (från Budget App backup)"
            } else {
                ""
            }
            
            Result.success("Import slutförd${sourceInfo}! ${importResult.itemsAdded} nya objekt lades till. ${importResult.duplicatesSkipped} dubbletter skippades.")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    private suspend fun importData(backupData: BackupData): ImportResult = withContext(Dispatchers.IO) {
        var duplicatesSkipped = 0
        var itemsAdded = 0
        
        // Importera kategorier först (andra tabeller refererar till dem)
        backupData.categories?.forEach { category ->
            val duplicateCount = database.categoryDao().checkDuplicateCategory(category.name, category.type)
            if (duplicateCount == 0) {
                database.categoryDao().insertCategory(category.copy(id = 0))
                itemsAdded++
            } else {
                duplicatesSkipped++
            }
        }
        
        // Importera butiker med dubblettdetektering
        backupData.stores?.forEach { store ->
            val duplicateCount = database.storeDao().checkDuplicateStore(store.name, store.chain ?: "")
            if (duplicateCount == 0) {
                database.storeDao().insertStore(store.copy(id = 0))
                itemsAdded++
            } else {
                duplicatesSkipped++
            }
        }
        
        // Importera produkter (för nu utan dubblettdetektering - kan läggas till senare)
        backupData.products?.forEach { product ->
            database.productDao().insertProduct(product.copy(id = 0))
            itemsAdded++
        }
        
        // Importera inkomster med dubblettdetektering
        backupData.incomes?.forEach { income ->
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
        backupData.expenses?.forEach { expense ->
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
        backupData.loans?.forEach { loan ->
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
        backupData.budgets?.forEach { budget ->
            database.budgetDao().insertBudget(budget.copy(id = 0))
            itemsAdded++
        }
        
        // Importera produktbutik-relationer sist (har redan unique constraint)
        backupData.productStores?.let { productStores ->
            if (productStores.isNotEmpty()) {
                val productStoresWithoutId = productStores.map { it.copy(id = 0) }
                database.productStoreDao().insertProductStores(productStoresWithoutId)
                itemsAdded += productStores.size
            }
        }
        
        return@withContext ImportResult(itemsAdded, duplicatesSkipped)
    }
    
    fun generateBackupFileName(): String {
        val timestamp = SimpleDateFormat("yyyy-MM-dd_HH-mm", Locale.getDefault())
            .format(Date())
        return "budget_backup_$timestamp.json"
    }
    
    private fun isLegacyBudgetAppBackup(jsonString: String): Boolean {
        return try {
            // Legacy backups might have different structure or file names like "budget_backup_*"
            // Check for patterns that indicate old Budget App format
            jsonString.contains("\"appName\":\"Budget App\"") ||
            jsonString.contains("\"version\":\"0.") ||
            (!jsonString.contains("\"version\":\"1.") && jsonString.contains("\"categories\"")) ||
            // If it has the basic structure but no version field, it's likely legacy
            (!jsonString.contains("\"version\":") && (jsonString.contains("\"incomes\"") || jsonString.contains("\"expenses\"")))
        } catch (e: Exception) {
            false
        }
    }
    
    private fun convertLegacyBackup(jsonString: String): BackupData {
        return try {
            // First, try to parse as current format in case it's compatible
            val directParse = try {
                gson.fromJson(jsonString, BackupData::class.java)
            } catch (e: Exception) {
                null
            }
            
            if (directParse != null) {
                // If direct parsing works, use it but ensure version is set
                directParse.copy(version = "1.0 (migrated from Budget App)")
            } else {
                // Handle other legacy formats or create empty backup with migration note
                BackupData(
                    version = "1.0 (legacy migration)",
                    exportDate = System.currentTimeMillis(),
                    categories = emptyList(),
                    incomes = emptyList(),
                    expenses = emptyList(),
                    loans = emptyList(),
                    budgets = emptyList(),
                    stores = emptyList(),
                    products = emptyList(),
                    productStores = emptyList()
                )
            }
        } catch (e: Exception) {
            // If all else fails, return empty backup
            BackupData(
                version = "1.0 (failed migration)",
                exportDate = System.currentTimeMillis(),
                categories = emptyList(),
                incomes = emptyList(),
                expenses = emptyList(),
                loans = emptyList(),
                budgets = emptyList(),
                stores = emptyList(),
                products = emptyList(),
                productStores = emptyList()
            )
        }
    }
}