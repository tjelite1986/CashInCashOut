package com.example.budgetapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.budgetapp.database.dao.CategoryDao
import com.example.budgetapp.database.dao.ProductDao
import com.example.budgetapp.database.dao.StoreDao
import com.example.budgetapp.database.dao.ProductStoreDao
import com.example.budgetapp.database.dao.IncomeDao
import com.example.budgetapp.database.dao.ExpenseDao
import com.example.budgetapp.database.entities.Category
import com.example.budgetapp.database.entities.Product
import com.example.budgetapp.database.entities.Store
import com.example.budgetapp.database.entities.ProductStore
import com.example.budgetapp.database.entities.Income
import com.example.budgetapp.database.entities.Expense
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Product::class, Category::class, Store::class, ProductStore::class, Income::class, Expense::class],
    version = 6,
    exportSchema = false
)
abstract class BudgetDatabase : RoomDatabase() {
    
    abstract fun productDao(): ProductDao
    abstract fun categoryDao(): CategoryDao
    abstract fun storeDao(): StoreDao
    abstract fun productStoreDao(): ProductStoreDao
    abstract fun incomeDao(): IncomeDao
    abstract fun expenseDao(): ExpenseDao
    
    companion object {
        @Volatile
        private var INSTANCE: BudgetDatabase? = null
        
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Add new columns to products table
                database.execSQL("ALTER TABLE products ADD COLUMN productName TEXT")
                database.execSQL("ALTER TABLE products ADD COLUMN amount REAL")
                
                // Rename brand column to productName (if exists)
                // Since we can't rename columns directly in SQLite, we'll just add the new column
                // and the old brand column will be ignored
            }
        }
        
        private val MIGRATION_2_3 = object : Migration(2, 3) {
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
        
        private val MIGRATION_3_4 = object : Migration(3, 4) {
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
        
        private val MIGRATION_4_5 = object : Migration(4, 5) {
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
        
        private val MIGRATION_5_6 = object : Migration(5, 6) {
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
        
        fun getDatabase(context: Context): BudgetDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BudgetDatabase::class.java,
                    "budget_database"
                )
                .addCallback(DatabaseCallback())
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_6)
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
        
        private class DatabaseCallback : Callback() {
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