package com.example.budgetapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.budgetapp.database.dao.CategoryDao
import com.example.budgetapp.database.dao.ProductDao
import com.example.budgetapp.database.dao.StoreDao
import com.example.budgetapp.database.dao.ProductStoreDao
import com.example.budgetapp.database.dao.IncomeDao
import com.example.budgetapp.database.dao.ExpenseDao
import com.example.budgetapp.database.dao.LoanDao
import com.example.budgetapp.database.dao.BudgetDao
import com.example.budgetapp.database.dao.ReceiptDao
import com.example.budgetapp.database.dao.ReceiptItemDao
import com.example.budgetapp.database.dao.ShoppingListDao
import com.example.budgetapp.database.dao.ShoppingListItemDao
import com.example.budgetapp.database.dao.PriceHistoryDao
import com.example.budgetapp.database.entities.Category
import com.example.budgetapp.database.entities.Product
import com.example.budgetapp.database.entities.Store
import com.example.budgetapp.database.entities.ProductStore
import com.example.budgetapp.database.entities.Income
import com.example.budgetapp.database.entities.Expense
import com.example.budgetapp.database.entities.Loan
import com.example.budgetapp.database.entities.Budget
import com.example.budgetapp.database.entities.Receipt
import com.example.budgetapp.database.entities.ReceiptItem
import com.example.budgetapp.database.entities.ShoppingList
import com.example.budgetapp.database.entities.ShoppingListItem
import com.example.budgetapp.database.entities.PriceHistory
import com.example.budgetapp.database.entities.LoanTypeConverter
import com.example.budgetapp.database.entities.BudgetTypeConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Product::class, Category::class, Store::class, ProductStore::class, Income::class, Expense::class, Loan::class, Budget::class, Receipt::class, ReceiptItem::class, ShoppingList::class, ShoppingListItem::class, PriceHistory::class],
    version = 11,
    exportSchema = false
)
@TypeConverters(LoanTypeConverter::class, BudgetTypeConverter::class)
abstract class BudgetDatabase : RoomDatabase() {
    
    abstract fun productDao(): ProductDao
    abstract fun categoryDao(): CategoryDao
    abstract fun storeDao(): StoreDao
    abstract fun productStoreDao(): ProductStoreDao
    abstract fun incomeDao(): IncomeDao
    abstract fun expenseDao(): ExpenseDao
    abstract fun loanDao(): LoanDao
    abstract fun budgetDao(): BudgetDao
    abstract fun receiptDao(): ReceiptDao
    abstract fun receiptItemDao(): ReceiptItemDao
    abstract fun shoppingListDao(): ShoppingListDao
    abstract fun shoppingListItemDao(): ShoppingListItemDao
    abstract fun priceHistoryDao(): PriceHistoryDao
    
    companion object {
        @Volatile
        private var INSTANCE: BudgetDatabase? = null
        
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Add new columns to products table
                database.execSQL("ALTER TABLE products ADD COLUMN productName TEXT")
                database.execSQL("ALTER TABLE products ADD COLUMN amount REAL")
                
                // Rename brand column to productName (if exists)
                // Since we can't rename columns directly in SQLite, we'll just add the new column
                // and the old brand column will be ignored
            }
        }
        
        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Create stores table
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS stores (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        name TEXT NOT NULL,
                        address TEXT,
                        city TEXT,
                        chain TEXT,
                        createdAt INTEGER NOT NULL,
                        updatedAt INTEGER NOT NULL
                    )
                """)
                
                // Create product_stores table
                database.execSQL("""
                    CREATE TABLE IF NOT EXISTS product_stores (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        productId INTEGER NOT NULL,
                        storeId INTEGER NOT NULL,
                        price REAL NOT NULL,
                        hasCampaignPrice INTEGER NOT NULL DEFAULT 0,
                        campaignQuantity INTEGER,
                        campaignPrice REAL,
                        lastSeen INTEGER NOT NULL,
                        createdAt INTEGER NOT NULL,
                        updatedAt INTEGER NOT NULL,
                        FOREIGN KEY (productId) REFERENCES products(id) ON DELETE CASCADE,
                        FOREIGN KEY (storeId) REFERENCES stores(id) ON DELETE CASCADE
                    )
                """)
                
                // Create indices
                database.execSQL("CREATE INDEX IF NOT EXISTS index_product_stores_productId ON product_stores(productId)")
                database.execSQL("CREATE INDEX IF NOT EXISTS index_product_stores_storeId ON product_stores(storeId)")
                database.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS index_product_stores_productId_storeId ON product_stores(productId, storeId)")
                
                // Remove price and campaign price columns from products table
                database.execSQL("""
                    CREATE TABLE products_new (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        name TEXT NOT NULL,
                        categoryId INTEGER,
                        hasDeposit INTEGER NOT NULL DEFAULT 0,
                        depositAmount REAL,
                        barcode TEXT,
                        productName TEXT,
                        description TEXT,
                        amount REAL,
                        unit TEXT DEFAULT 'st',
                        createdAt INTEGER NOT NULL,
                        updatedAt INTEGER NOT NULL,
                        FOREIGN KEY (categoryId) REFERENCES categories(id) ON DELETE SET NULL
                    )
                """)
                
                // Copy data from old table to new table (excluding price and campaign price fields)
                database.execSQL("""
                    INSERT INTO products_new (id, name, categoryId, hasDeposit, depositAmount, barcode, productName, description, amount, unit, createdAt, updatedAt)
                    SELECT id, name, categoryId, hasDeposit, depositAmount, barcode, productName, description, amount, unit, createdAt, updatedAt
                    FROM products
                """)
                
                // Drop old table and rename new table
                database.execSQL("DROP TABLE products")
                database.execSQL("ALTER TABLE products_new RENAME TO products")
                
                // Add some default stores
                val currentTime = System.currentTimeMillis()
                database.execSQL("INSERT INTO stores (name, chain, createdAt, updatedAt) VALUES ('ICA Maxi', 'ICA', $currentTime, $currentTime)")
                database.execSQL("INSERT INTO stores (name, chain, createdAt, updatedAt) VALUES ('Coop Extra', 'Coop', $currentTime, $currentTime)")
                database.execSQL("INSERT INTO stores (name, chain, createdAt, updatedAt) VALUES ('Hemköp', 'Hemköp', $currentTime, $currentTime)")
                database.execSQL("INSERT INTO stores (name, chain, createdAt, updatedAt) VALUES ('Willys', 'Willys', $currentTime, $currentTime)")
                database.execSQL("INSERT INTO stores (name, chain, createdAt, updatedAt) VALUES ('Lidl', 'Lidl', $currentTime, $currentTime)")
            }
        }
        
        val MIGRATION_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Drop incomes table if it exists (in case of partial migration)
                database.execSQL("DROP TABLE IF EXISTS incomes")
                
                // Create incomes table
                database.execSQL("""
                    CREATE TABLE incomes (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        title TEXT NOT NULL,
                        amount REAL NOT NULL,
                        description TEXT,
                        category TEXT,
                        date INTEGER NOT NULL,
                        isRecurring INTEGER NOT NULL DEFAULT 0,
                        recurringType TEXT,
                        createdAt INTEGER NOT NULL,
                        updatedAt INTEGER NOT NULL
                    )
                """)
                
                // Create index on date for better performance
                database.execSQL("CREATE INDEX IF NOT EXISTS index_incomes_date ON incomes(date)")
                database.execSQL("CREATE INDEX IF NOT EXISTS index_incomes_category ON incomes(category)")
            }
        }
        
        val MIGRATION_4_5 = object : Migration(4, 5) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Ensure incomes table exists with correct structure
                database.execSQL("DROP TABLE IF EXISTS incomes")
                
                database.execSQL("""
                    CREATE TABLE incomes (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        title TEXT NOT NULL,
                        amount REAL NOT NULL,
                        description TEXT,
                        category TEXT,
                        date INTEGER NOT NULL,
                        isRecurring INTEGER NOT NULL DEFAULT 0,
                        recurringType TEXT,
                        createdAt INTEGER NOT NULL,
                        updatedAt INTEGER NOT NULL
                    )
                """)
                
                database.execSQL("CREATE INDEX IF NOT EXISTS index_incomes_date ON incomes(date)")
                database.execSQL("CREATE INDEX IF NOT EXISTS index_incomes_category ON incomes(category)")
            }
        }
        
        val MIGRATION_5_6 = object : Migration(5, 6) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Create expenses table
                database.execSQL("""
                    CREATE TABLE expenses (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        title TEXT NOT NULL,
                        amount REAL NOT NULL,
                        description TEXT,
                        category TEXT,
                        store TEXT,
                        date INTEGER NOT NULL,
                        isRecurring INTEGER NOT NULL DEFAULT 0,
                        recurringType TEXT,
                        paymentMethod TEXT,
                        createdAt INTEGER NOT NULL,
                        updatedAt INTEGER NOT NULL
                    )
                """)
                
                database.execSQL("CREATE INDEX IF NOT EXISTS index_expenses_date ON expenses(date)")
                database.execSQL("CREATE INDEX IF NOT EXISTS index_expenses_category ON expenses(category)")
                database.execSQL("CREATE INDEX IF NOT EXISTS index_expenses_store ON expenses(store)")
            }
        }
        
        val MIGRATION_6_7 = object : Migration(6, 7) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Create loans table
                database.execSQL("""
                    CREATE TABLE loans (
                        id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                        title TEXT NOT NULL,
                        amount REAL NOT NULL,
                        description TEXT,
                        personName TEXT NOT NULL,
                        type TEXT NOT NULL,
                        interestRate REAL NOT NULL DEFAULT 0.0,
                        dueDate INTEGER,
                        isPaidBack INTEGER NOT NULL DEFAULT 0,
                        paidBackDate INTEGER,
                        paidBackAmount REAL NOT NULL DEFAULT 0.0,
                        createdAt INTEGER NOT NULL,
                        updatedAt INTEGER NOT NULL
                    )
                """)
                
                database.execSQL("CREATE INDEX IF NOT EXISTS index_loans_type ON loans(type)")
                database.execSQL("CREATE INDEX IF NOT EXISTS index_loans_personName ON loans(personName)")
                database.execSQL("CREATE INDEX IF NOT EXISTS index_loans_dueDate ON loans(dueDate)")
                database.execSQL("CREATE INDEX IF NOT EXISTS index_loans_isPaidBack ON loans(isPaidBack)")
            }
        }
        
        private val MIGRATION_7_8 = object : Migration(7, 8) {
            override fun migrate(database: SupportSQLiteDatabase) {
                try {
                    // Use helper to check if budgets table already exists
                    if (!DatabaseMigrationHelper.doesTableExist(database, "budgets")) {
                        // Create budgets table
                        val createTableSQL = """
                            CREATE TABLE budgets (
                                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                                name TEXT NOT NULL,
                                categoryName TEXT NOT NULL,
                                budgetAmount REAL NOT NULL,
                                period TEXT NOT NULL,
                                startDate INTEGER NOT NULL,
                                endDate INTEGER NOT NULL,
                                isActive INTEGER NOT NULL DEFAULT 1,
                                alertThreshold REAL NOT NULL DEFAULT 80.0,
                                createdAt INTEGER NOT NULL,
                                updatedAt INTEGER NOT NULL
                            )
                        """
                        
                        if (DatabaseMigrationHelper.safeExecuteSQL(database, createTableSQL)) {
                            // Create indices only if table creation succeeded
                            DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_budgets_categoryName ON budgets(categoryName)")
                            DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_budgets_isActive ON budgets(isActive)")
                            DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_budgets_period ON budgets(period)")
                            DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_budgets_startDate_endDate ON budgets(startDate, endDate)")
                        } else {
                            throw Exception("Failed to create budgets table")
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    throw Exception("Migration 7->8 failed: ${e.message}", e)
                }
            }
        }
        
        private val MIGRATION_8_9 = object : Migration(8, 9) {
            override fun migrate(database: SupportSQLiteDatabase) {
                try {
                    // Create receipts table
                    val createReceiptsTableSQL = """
                        CREATE TABLE IF NOT EXISTS receipts (
                            id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            expenseId INTEGER NOT NULL,
                            receiptNumber TEXT,
                            totalAmount REAL NOT NULL,
                            tax REAL NOT NULL DEFAULT 0.0,
                            discount REAL NOT NULL DEFAULT 0.0,
                            createdAt INTEGER NOT NULL,
                            updatedAt INTEGER NOT NULL,
                            FOREIGN KEY(expenseId) REFERENCES expenses(id) ON DELETE CASCADE
                        )
                    """
                    
                    // Create receipt_items table
                    val createReceiptItemsTableSQL = """
                        CREATE TABLE IF NOT EXISTS receipt_items (
                            id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            receiptId INTEGER NOT NULL,
                            productId INTEGER,
                            productName TEXT NOT NULL,
                            quantity REAL NOT NULL,
                            unitPrice REAL NOT NULL,
                            totalPrice REAL NOT NULL,
                            unit TEXT NOT NULL DEFAULT 'st',
                            discount REAL NOT NULL DEFAULT 0.0,
                            createdAt INTEGER NOT NULL,
                            FOREIGN KEY(receiptId) REFERENCES receipts(id) ON DELETE CASCADE,
                            FOREIGN KEY(productId) REFERENCES products(id) ON DELETE SET NULL
                        )
                    """
                    
                    if (DatabaseMigrationHelper.safeExecuteSQL(database, createReceiptsTableSQL)) {
                        // Create indices for receipts
                        DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_receipts_expenseId ON receipts(expenseId)")
                        DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_receipts_createdAt ON receipts(createdAt)")
                    } else {
                        throw Exception("Failed to create receipts table")
                    }
                    
                    if (DatabaseMigrationHelper.safeExecuteSQL(database, createReceiptItemsTableSQL)) {
                        // Create indices for receipt_items
                        DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_receipt_items_receiptId ON receipt_items(receiptId)")
                        DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_receipt_items_productId ON receipt_items(productId)")
                        DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_receipt_items_createdAt ON receipt_items(createdAt)")
                    } else {
                        throw Exception("Failed to create receipt_items table")
                    }
                    
                } catch (e: Exception) {
                    e.printStackTrace()
                    throw Exception("Migration 8->9 failed: ${e.message}", e)
                }
            }
        }
        
        private val MIGRATION_9_10 = object : Migration(9, 10) {
            override fun migrate(database: SupportSQLiteDatabase) {
                try {
                    // Add new columns to receipt_items table for pant and store information
                    val alterTableSQL = listOf(
                        "ALTER TABLE receipt_items ADD COLUMN hasDeposit INTEGER NOT NULL DEFAULT 0",
                        "ALTER TABLE receipt_items ADD COLUMN depositAmount REAL NOT NULL DEFAULT 0.0",
                        "ALTER TABLE receipt_items ADD COLUMN storeName TEXT",
                        "ALTER TABLE receipt_items ADD COLUMN storeChain TEXT",
                        "ALTER TABLE receipt_items ADD COLUMN storeCity TEXT"
                    )
                    
                    alterTableSQL.forEach { sql ->
                        if (!DatabaseMigrationHelper.safeExecuteSQL(database, sql)) {
                            throw Exception("Failed to execute: $sql")
                        }
                    }
                    
                } catch (e: Exception) {
                    e.printStackTrace()
                    throw Exception("Migration 9->10 failed: ${e.message}", e)
                }
            }
        }
        
        private val MIGRATION_10_11 = object : Migration(10, 11) {
            override fun migrate(database: SupportSQLiteDatabase) {
                try {
                    // Add new location fields to stores table
                    val alterStoresTableSQL = listOf(
                        "ALTER TABLE stores ADD COLUMN latitude REAL",
                        "ALTER TABLE stores ADD COLUMN longitude REAL",
                        "ALTER TABLE stores ADD COLUMN phoneNumber TEXT",
                        "ALTER TABLE stores ADD COLUMN website TEXT",
                        "ALTER TABLE stores ADD COLUMN openingHours TEXT",
                        "ALTER TABLE stores ADD COLUMN isActive INTEGER NOT NULL DEFAULT 1"
                    )
                    
                    alterStoresTableSQL.forEach { sql ->
                        if (!DatabaseMigrationHelper.safeExecuteSQL(database, sql)) {
                            throw Exception("Failed to execute: $sql")
                        }
                    }
                    
                    // Create shopping_lists table
                    val createShoppingListsTableSQL = """
                        CREATE TABLE IF NOT EXISTS shopping_lists (
                            id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            name TEXT NOT NULL,
                            description TEXT,
                            isCompleted INTEGER NOT NULL DEFAULT 0,
                            totalEstimatedCost REAL NOT NULL DEFAULT 0.0,
                            targetStoreId INTEGER,
                            createdAt INTEGER NOT NULL,
                            updatedAt INTEGER NOT NULL,
                            completedAt INTEGER,
                            FOREIGN KEY(targetStoreId) REFERENCES stores(id) ON DELETE SET NULL
                        )
                    """
                    
                    if (!DatabaseMigrationHelper.safeExecuteSQL(database, createShoppingListsTableSQL)) {
                        throw Exception("Failed to create shopping_lists table")
                    }
                    
                    // Create shopping_list_items table
                    val createShoppingListItemsTableSQL = """
                        CREATE TABLE IF NOT EXISTS shopping_list_items (
                            id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            shoppingListId INTEGER NOT NULL,
                            productId INTEGER,
                            customItemName TEXT,
                            quantity INTEGER NOT NULL DEFAULT 1,
                            unit TEXT,
                            estimatedPrice REAL,
                            actualPrice REAL,
                            notes TEXT,
                            isCompleted INTEGER NOT NULL DEFAULT 0,
                            priority INTEGER NOT NULL DEFAULT 0,
                            createdAt INTEGER NOT NULL,
                            updatedAt INTEGER NOT NULL,
                            completedAt INTEGER,
                            FOREIGN KEY(shoppingListId) REFERENCES shopping_lists(id) ON DELETE CASCADE,
                            FOREIGN KEY(productId) REFERENCES products(id) ON DELETE SET NULL
                        )
                    """
                    
                    if (!DatabaseMigrationHelper.safeExecuteSQL(database, createShoppingListItemsTableSQL)) {
                        throw Exception("Failed to create shopping_list_items table")
                    }
                    
                    // Create price_history table
                    val createPriceHistoryTableSQL = """
                        CREATE TABLE IF NOT EXISTS price_history (
                            id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            productId INTEGER NOT NULL,
                            storeId INTEGER NOT NULL,
                            price REAL NOT NULL,
                            campaignPrice REAL,
                            campaignQuantity INTEGER,
                            hasCampaignPrice INTEGER NOT NULL DEFAULT 0,
                            recordedAt INTEGER NOT NULL,
                            source TEXT NOT NULL DEFAULT 'manual',
                            notes TEXT,
                            FOREIGN KEY(productId) REFERENCES products(id) ON DELETE CASCADE,
                            FOREIGN KEY(storeId) REFERENCES stores(id) ON DELETE CASCADE
                        )
                    """
                    
                    if (!DatabaseMigrationHelper.safeExecuteSQL(database, createPriceHistoryTableSQL)) {
                        throw Exception("Failed to create price_history table")
                    }
                    
                    // Create indices for better performance
                    val indices = listOf(
                        "CREATE INDEX IF NOT EXISTS index_shopping_lists_isCompleted ON shopping_lists(isCompleted)",
                        "CREATE INDEX IF NOT EXISTS index_shopping_lists_targetStoreId ON shopping_lists(targetStoreId)",
                        "CREATE INDEX IF NOT EXISTS index_shopping_list_items_shoppingListId ON shopping_list_items(shoppingListId)",
                        "CREATE INDEX IF NOT EXISTS index_shopping_list_items_productId ON shopping_list_items(productId)",
                        "CREATE INDEX IF NOT EXISTS index_price_history_productId ON price_history(productId)",
                        "CREATE INDEX IF NOT EXISTS index_price_history_storeId ON price_history(storeId)",
                        "CREATE INDEX IF NOT EXISTS index_price_history_recordedAt ON price_history(recordedAt)",
                        "CREATE INDEX IF NOT EXISTS index_price_history_productId_storeId_recordedAt ON price_history(productId, storeId, recordedAt)"
                    )
                    
                    indices.forEach { sql ->
                        DatabaseMigrationHelper.safeExecuteSQL(database, sql)
                    }
                    
                } catch (e: Exception) {
                    e.printStackTrace()
                    throw Exception("Migration 10->11 failed: ${e.message}", e)
                }
            }
        }
        
        fun getDatabase(context: Context): BudgetDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = SafeMigrationManager.createDatabaseWithSafeMigration(context)
                INSTANCE = instance
                instance
            }
        }
        
        class DatabaseCallback : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // Pre-populate database with default categories
                INSTANCE?.let { database ->
                    CoroutineScope(Dispatchers.IO).launch {
                        populateDatabase(database.categoryDao())
                    }
                }
            }
        }
        
        private suspend fun populateDatabase(categoryDao: CategoryDao) {
            // Default categories
            val defaultCategories = listOf(
                Category(name = "Mejeri", color = "#2196F3"),
                Category(name = "Kött & Fisk", color = "#F44336"),
                Category(name = "Frukt & Grönt", color = "#4CAF50"),
                Category(name = "Bröd & Bakverk", color = "#FF9800"),
                Category(name = "Konserver", color = "#9C27B0"),
                Category(name = "Frysta varor", color = "#00BCD4"),
                Category(name = "Drycker", color = "#CDDC39"),
                Category(name = "Snacks", color = "#FF5722"),
                Category(name = "Hygien & Hälsa", color = "#607D8B"),
                Category(name = "Hushåll", color = "#795548"),
                Category(name = "Övrigt", color = "#9E9E9E")
            )
            
            defaultCategories.forEach { category ->
                categoryDao.insertCategory(category)
            }
        }
    }
}