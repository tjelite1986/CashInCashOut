import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.File

data class BackupData(
    val version: String = "1.0",
    val exportDate: Long? = null,
    val categories: List<Any>? = null,
    val incomes: List<Any>? = null,
    val expenses: List<Any>? = null,
    val loans: List<Any>? = null,
    val budgets: List<Any>? = null,
    val stores: List<Any>? = null,
    val products: List<Any>? = null,
    val productStores: List<Any>? = null
)

fun main() {
    val gson = GsonBuilder().setPrettyPrinting().create()
    val jsonString = File("/home/thomas/budget/photo/budget_backup_2025-07-25_05-17.json").readText()
    
    try {
        val backupData = gson.fromJson(jsonString, BackupData::class.java)
        println("✅ Backup parsed successfully!")
        println("Version: ${backupData.version}")
        println("Export date: ${backupData.exportDate}")
        println("Categories: ${backupData.categories?.size ?: 0}")
        println("Incomes: ${backupData.incomes?.size ?: 0}")
        println("Expenses: ${backupData.expenses?.size ?: 0}")
        println("Loans: ${backupData.loans?.size ?: 0}")
        println("Budgets: ${backupData.budgets?.size ?: 0}")
        println("Stores: ${backupData.stores?.size ?: 0}")
        println("Products: ${backupData.products?.size ?: 0}")
        println("Product stores: ${backupData.productStores?.size ?: 0}")
    } catch (e: Exception) {
        println("❌ Error parsing backup: ${e.message}")
        e.printStackTrace()
    }
}