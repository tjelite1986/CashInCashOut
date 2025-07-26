package com.example.budgetapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.budgetapp.database.dao.CategoryDao
import com.example.budgetapp.database.dao.ProductCategoryDao
import com.example.budgetapp.database.dao.ProductDao
import com.example.budgetapp.database.dao.StoreDao
import com.example.budgetapp.database.dao.StoreChainDao
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
import com.example.budgetapp.database.dao.ReminderSettingsDao
import com.example.budgetapp.database.dao.AppUsageDao
import com.example.budgetapp.database.dao.TransactionNotificationDao
import com.example.budgetapp.database.dao.FinancialInsightDao
import com.example.budgetapp.database.dao.FinancialGoalDao
import com.example.budgetapp.database.dao.SpendingPatternDao
import com.example.budgetapp.database.dao.SpendingForecastDao
import com.example.budgetapp.analytics.investments.dao.InvestmentDao
import com.example.budgetapp.database.entities.Category
import com.example.budgetapp.database.entities.ProductCategory
import com.example.budgetapp.database.entities.Product
import com.example.budgetapp.database.entities.Store
import com.example.budgetapp.database.entities.StoreChain
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
import com.example.budgetapp.database.entities.ReminderSettings
import com.example.budgetapp.database.entities.AppUsage
import com.example.budgetapp.database.entities.TransactionNotification
import com.example.budgetapp.database.entities.FinancialInsight
import com.example.budgetapp.database.entities.FinancialGoal
import com.example.budgetapp.database.entities.SpendingPattern
import com.example.budgetapp.database.entities.SpendingForecast
import com.example.budgetapp.analytics.investments.entities.Investment
import com.example.budgetapp.analytics.investments.entities.InvestmentTransaction
import com.example.budgetapp.analytics.investments.entities.InvestmentPriceHistory
import com.example.budgetapp.analytics.investments.entities.InvestmentDividend
import com.example.budgetapp.analytics.investments.entities.InvestmentPortfolio
import com.example.budgetapp.analytics.investments.entities.PortfolioInvestment
import com.example.budgetapp.analytics.investments.multicurrency.ExchangeRate
import com.example.budgetapp.analytics.investments.multicurrency.CurrencyAlert
import com.example.budgetapp.analytics.investments.multicurrency.MultiCurrencyTransaction
import com.example.budgetapp.database.entities.LoanTypeConverter
import com.example.budgetapp.database.entities.BudgetTypeConverter
import com.example.budgetapp.database.entities.AnalyticsTypeConverters
import com.example.budgetapp.automation.AutomationTypeConverters
import com.example.budgetapp.automation.ml.MLTypeConverters
import com.example.budgetapp.automation.dao.AutomationRuleDao
import com.example.budgetapp.automation.AutomationRule
import com.example.budgetapp.automation.AutoTransferRule
import com.example.budgetapp.automation.ml.SmartInsight
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [Product::class, ProductCategory::class, Category::class, Store::class, StoreChain::class, ProductStore::class, Income::class, Expense::class, Loan::class, Budget::class, Receipt::class, ReceiptItem::class, ShoppingList::class, ShoppingListItem::class, PriceHistory::class, ReminderSettings::class, AppUsage::class, TransactionNotification::class, FinancialInsight::class, FinancialGoal::class, SpendingPattern::class, SpendingForecast::class, Investment::class, InvestmentTransaction::class, InvestmentPriceHistory::class, InvestmentDividend::class, InvestmentPortfolio::class, PortfolioInvestment::class, ExchangeRate::class, CurrencyAlert::class, MultiCurrencyTransaction::class, AutomationRule::class, AutoTransferRule::class, SmartInsight::class],
    version = 23,
    exportSchema = false
)
@TypeConverters(LoanTypeConverter::class, BudgetTypeConverter::class, AnalyticsTypeConverters::class, AutomationTypeConverters::class, MLTypeConverters::class)
abstract class BudgetDatabase : RoomDatabase() {
    
    abstract fun productDao(): ProductDao
    abstract fun productCategoryDao(): ProductCategoryDao
    abstract fun categoryDao(): CategoryDao
    abstract fun storeDao(): StoreDao
    abstract fun storeChainDao(): StoreChainDao
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
    abstract fun reminderSettingsDao(): ReminderSettingsDao
    abstract fun appUsageDao(): AppUsageDao
    abstract fun transactionNotificationDao(): TransactionNotificationDao
    abstract fun financialInsightDao(): FinancialInsightDao
    abstract fun financialGoalDao(): FinancialGoalDao
    abstract fun spendingPatternDao(): SpendingPatternDao
    abstract fun spendingForecastDao(): SpendingForecastDao
    abstract fun investmentDao(): InvestmentDao
    abstract fun exchangeRateDao(): com.example.budgetapp.analytics.investments.dao.ExchangeRateDao
    abstract fun automationRuleDao(): AutomationRuleDao
    
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
                database.execSQL("CREATE INDEX IF NOT EXISTS index_expenses_isRecurring ON expenses(isRecurring)")
                database.execSQL("CREATE INDEX IF NOT EXISTS index_expenses_amount ON expenses(amount)")
                database.execSQL("CREATE INDEX IF NOT EXISTS index_expenses_paymentMethod ON expenses(paymentMethod)")
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
        
        val MIGRATION_7_8 = object : Migration(7, 8) {
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
        
        val MIGRATION_8_9 = object : Migration(8, 9) {
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
        
        val MIGRATION_9_10 = object : Migration(9, 10) {
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
        
        val MIGRATION_10_11 = object : Migration(10, 11) {
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
        
        val MIGRATION_11_12 = object : Migration(11, 12) {
            override fun migrate(database: SupportSQLiteDatabase) {
                try {
                    // Create product_categories table
                    val createProductCategoriesTableSQL = """
                        CREATE TABLE IF NOT EXISTS product_categories (
                            id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            name TEXT NOT NULL,
                            color TEXT,
                            iconName TEXT,
                            isDefault INTEGER NOT NULL DEFAULT 0,
                            createdAt INTEGER NOT NULL
                        )
                    """
                    
                    if (!DatabaseMigrationHelper.safeExecuteSQL(database, createProductCategoriesTableSQL)) {
                        throw Exception("Failed to create product_categories table")
                    }
                    
                    // Add productCategoryId column to products table
                    if (!DatabaseMigrationHelper.safeExecuteSQL(database, "ALTER TABLE products ADD COLUMN productCategoryId INTEGER")) {
                        throw Exception("Failed to add productCategoryId column to products table")
                    }
                    
                    // Create index for productCategoryId
                    DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_products_productCategoryId ON products(productCategoryId)")
                    
                    // Populate with default product categories
                    val defaultCategories = listOf(
                        "Mat & Dryck", "Kött & Chark", "Mejeri", "Frukt & Grönt", "Bröd & Bakverk",
                        "Konserver", "Fryst", "Godis & Snacks", "Drycker", "Hygien & Skönhet",
                        "Hushållsprodukter", "Elektronik", "Kläder", "Hem & Trädgård", "Sport & Fritid",
                        "Böcker & Media", "Leksaker", "Husdjur", "Övrigt"
                    )
                    
                    val currentTime = System.currentTimeMillis()
                    defaultCategories.forEach { categoryName ->
                        DatabaseMigrationHelper.safeExecuteSQL(database, 
                            "INSERT INTO product_categories (name, isDefault, createdAt) VALUES ('$categoryName', 1, $currentTime)")
                    }
                    
                } catch (e: Exception) {
                    e.printStackTrace()
                    throw Exception("Migration 11->12 failed: ${e.message}", e)
                }
            }
        }
        
        val MIGRATION_12_13 = object : Migration(12, 13) {
            override fun migrate(database: SupportSQLiteDatabase) {
                try {
                    // Add productSize column to receipt_items table
                    if (!DatabaseMigrationHelper.safeExecuteSQL(database, "ALTER TABLE receipt_items ADD COLUMN productSize TEXT")) {
                        throw Exception("Failed to add productSize column to receipt_items table")
                    }
                    
                } catch (e: Exception) {
                    e.printStackTrace()
                    throw Exception("Migration 12->13 failed: ${e.message}", e)
                }
            }
        }
        
        val MIGRATION_13_14 = object : Migration(13, 14) {
            override fun migrate(database: SupportSQLiteDatabase) {
                try {
                    // Create reminder_settings table
                    val createReminderSettingsTableSQL = """
                        CREATE TABLE IF NOT EXISTS reminder_settings (
                            id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            reminderType TEXT NOT NULL,
                            isEnabled INTEGER NOT NULL DEFAULT 1,
                            alertTime TEXT NOT NULL DEFAULT '20:00',
                            title TEXT NOT NULL,
                            message TEXT NOT NULL,
                            createdAt INTEGER NOT NULL,
                            updatedAt INTEGER NOT NULL
                        )
                    """
                    
                    if (!DatabaseMigrationHelper.safeExecuteSQL(database, createReminderSettingsTableSQL)) {
                        throw Exception("Failed to create reminder_settings table")
                    }
                    
                    // Create app_usage table
                    val createAppUsageTableSQL = """
                        CREATE TABLE IF NOT EXISTS app_usage (
                            id INTEGER PRIMARY KEY NOT NULL DEFAULT 1,
                            lastOpenedAt INTEGER NOT NULL,
                            updatedAt INTEGER NOT NULL
                        )
                    """
                    
                    if (!DatabaseMigrationHelper.safeExecuteSQL(database, createAppUsageTableSQL)) {
                        throw Exception("Failed to create app_usage table")
                    }
                    
                    // Create indices
                    DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_reminder_settings_reminderType ON reminder_settings(reminderType)")
                    DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_reminder_settings_isEnabled ON reminder_settings(isEnabled)")
                    
                    // Insert default reminder settings
                    val currentTime = System.currentTimeMillis()
                    val defaultReminders = listOf(
                        Triple("DAILY_IF_NOT_OPENED", "Daglig påminnelse", "Glöm inte att registrera dina utgifter idag!"),
                        Triple("HOURS_24_FROM_LAST_OPEN", "24-timmars påminnelse", "Det har gått 24 timmar sedan du senast använde appen"),
                        Triple("DAILY_ALWAYS", "Daglig påminnelse (alltid)", "Dags att kolla din budget!")
                    )
                    
                    defaultReminders.forEach { (type, title, message) ->
                        DatabaseMigrationHelper.safeExecuteSQL(database, 
                            "INSERT INTO reminder_settings (reminderType, title, message, createdAt, updatedAt) VALUES ('$type', '$title', '$message', $currentTime, $currentTime)")
                    }
                    
                    // Initialize app_usage with current time
                    DatabaseMigrationHelper.safeExecuteSQL(database, 
                        "INSERT INTO app_usage (id, lastOpenedAt, updatedAt) VALUES (1, $currentTime, $currentTime)")
                    
                } catch (e: Exception) {
                    e.printStackTrace()
                    throw Exception("Migration 13->14 failed: ${e.message}", e)
                }
            }
        }
        
        val MIGRATION_14_15 = object : Migration(14, 15) {
            override fun migrate(database: SupportSQLiteDatabase) {
                try {
                    // Create transaction_notifications table
                    val createTransactionNotificationsTableSQL = """
                        CREATE TABLE IF NOT EXISTS transaction_notifications (
                            id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            transactionType TEXT NOT NULL,
                            transactionId INTEGER NOT NULL,
                            transactionTitle TEXT NOT NULL,
                            isEnabled INTEGER NOT NULL DEFAULT 1,
                            reminderDaysBefore INTEGER NOT NULL DEFAULT 1,
                            customMessage TEXT,
                            createdAt INTEGER NOT NULL,
                            updatedAt INTEGER NOT NULL
                        )
                    """
                    
                    if (!DatabaseMigrationHelper.safeExecuteSQL(database, createTransactionNotificationsTableSQL)) {
                        throw Exception("Failed to create transaction_notifications table")
                    }
                    
                    // Create indices for better performance
                    DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_transaction_notifications_transactionId ON transaction_notifications(transactionId)")
                    DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_transaction_notifications_transactionType ON transaction_notifications(transactionType)")
                    DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_transaction_notifications_isEnabled ON transaction_notifications(isEnabled)")
                    
                } catch (e: Exception) {
                    e.printStackTrace()
                    throw Exception("Migration 14->15 failed: ${e.message}", e)
                }
            }
        }
        
        val MIGRATION_15_16 = object : Migration(15, 16) {
            override fun migrate(database: SupportSQLiteDatabase) {
                try {
                    // Add new columns to categories table
                    DatabaseMigrationHelper.safeExecuteSQL(database, "ALTER TABLE categories ADD COLUMN iconResName TEXT NOT NULL DEFAULT 'ic_category_default'")
                    DatabaseMigrationHelper.safeExecuteSQL(database, "ALTER TABLE categories ADD COLUMN type TEXT NOT NULL DEFAULT 'EXPENSE'")
                    DatabaseMigrationHelper.safeExecuteSQL(database, "ALTER TABLE categories ADD COLUMN parentCategoryId INTEGER")
                    DatabaseMigrationHelper.safeExecuteSQL(database, "ALTER TABLE categories ADD COLUMN isSubcategory INTEGER NOT NULL DEFAULT 0")
                    DatabaseMigrationHelper.safeExecuteSQL(database, "ALTER TABLE categories ADD COLUMN associatedKeywords TEXT NOT NULL DEFAULT ''")
                    DatabaseMigrationHelper.safeExecuteSQL(database, "ALTER TABLE categories ADD COLUMN transactionCount INTEGER NOT NULL DEFAULT 0")
                    DatabaseMigrationHelper.safeExecuteSQL(database, "ALTER TABLE categories ADD COLUMN isActive INTEGER NOT NULL DEFAULT 1")
                    DatabaseMigrationHelper.safeExecuteSQL(database, "ALTER TABLE categories ADD COLUMN sortOrder INTEGER NOT NULL DEFAULT 0")
                    
                    // Update existing categories with default color if null
                    DatabaseMigrationHelper.safeExecuteSQL(database, "UPDATE categories SET color = '#FF6B6B' WHERE color IS NULL")
                    
                    // Clear old categories and insert new default categories with proper structure
                    DatabaseMigrationHelper.safeExecuteSQL(database, "DELETE FROM categories")
                    
                    // Insert new default expense categories
                    val currentTime = System.currentTimeMillis()
                    val defaultExpenseCategories = listOf(
                        "('Dining', '#607D8B', 'ic_dining', 'EXPENSE', NULL, 0, '', 0, 1, 1)",
                        "('Groceries', '#4CAF50', 'ic_groceries', 'EXPENSE', NULL, 0, '', 0, 1, 2)",
                        "('Shopping', '#E91E63', 'ic_shopping', 'EXPENSE', NULL, 0, '', 0, 1, 3)",
                        "('Transit', '#FF9800', 'ic_transit', 'EXPENSE', NULL, 0, '', 0, 1, 4)",
                        "('Entertainment', '#2196F3', 'ic_entertainment', 'EXPENSE', NULL, 0, '', 0, 1, 5)",
                        "('Bills & Fees', '#4CAF50', 'ic_bills', 'EXPENSE', NULL, 0, '', 0, 1, 6)",
                        "('Boende', '#795548', 'ic_home', 'EXPENSE', NULL, 0, '', 0, 1, 7)",
                        "('Hälsa', '#F44336', 'ic_health', 'EXPENSE', NULL, 0, '', 0, 1, 8)",
                        "('Teknik', '#9C27B0', 'ic_tech', 'EXPENSE', NULL, 0, '', 0, 1, 9)",
                        "('Sport', '#009688', 'ic_sport', 'EXPENSE', NULL, 0, '', 0, 1, 10)"
                    )
                    
                    // Insert new default income categories
                    val defaultIncomeCategories = listOf(
                        "('Salary', '#4CAF50', 'ic_income', 'INCOME', NULL, 0, '', 0, 1, 1)",
                        "('Bonus', '#FF9800', 'ic_bonus', 'INCOME', NULL, 0, '', 0, 1, 2)",
                        "('Freelance', '#2196F3', 'ic_freelance', 'INCOME', NULL, 0, '', 0, 1, 3)",
                        "('Investment', '#9C27B0', 'ic_investment', 'INCOME', NULL, 0, '', 0, 1, 4)",
                        "('Sale', '#E91E63', 'ic_sale', 'INCOME', NULL, 0, '', 0, 1, 5)",
                        "('Other Income', '#607D8B', 'ic_other', 'INCOME', NULL, 0, '', 0, 1, 6)"
                    )
                    
                    (defaultExpenseCategories + defaultIncomeCategories).forEach { values ->
                        DatabaseMigrationHelper.safeExecuteSQL(database, 
                            "INSERT INTO categories (name, color, iconResName, type, parentCategoryId, isSubcategory, associatedKeywords, transactionCount, isActive, sortOrder) VALUES $values"
                        )
                    }
                    
                    // Create indices for better performance
                    DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_categories_type ON categories(type)")
                    DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_categories_parentCategoryId ON categories(parentCategoryId)")
                    DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_categories_isActive ON categories(isActive)")
                    DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_categories_sortOrder ON categories(sortOrder)")
                    
                } catch (e: Exception) {
                    e.printStackTrace()
                    throw Exception("Migration 15->16 failed: ${e.message}", e)
                }
            }
        }
        
        val MIGRATION_16_17 = object : Migration(16, 17) {
            override fun migrate(database: SupportSQLiteDatabase) {
                try {
                    // Create store_chains table
                    val createStoreChainsTableSQL = """
                        CREATE TABLE IF NOT EXISTS store_chains (
                            id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            name TEXT NOT NULL,
                            logoUrl TEXT,
                            website TEXT,
                            description TEXT,
                            isDefault INTEGER NOT NULL DEFAULT 0,
                            isActive INTEGER NOT NULL DEFAULT 1,
                            createdAt INTEGER NOT NULL,
                            updatedAt INTEGER NOT NULL
                        )
                    """
                    
                    if (!DatabaseMigrationHelper.safeExecuteSQL(database, createStoreChainsTableSQL)) {
                        throw Exception("Failed to create store_chains table")
                    }
                    
                    // Create indices for better performance
                    DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_store_chains_name ON store_chains(name)")
                    DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_store_chains_isActive ON store_chains(isActive)")
                    DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_store_chains_isDefault ON store_chains(isDefault)")
                    
                    // Insert default store chains
                    val currentTime = System.currentTimeMillis()
                    val defaultChains = listOf(
                        "ICA", "Coop", "Hemköp", "Willys", "Lidl", 
                        "Tempo", "Ica Nära", "City Gross", "Bergendahls", "Axfood"
                    )
                    
                    defaultChains.forEach { chainName ->
                        DatabaseMigrationHelper.safeExecuteSQL(database, 
                            "INSERT INTO store_chains (name, isDefault, isActive, createdAt, updatedAt) VALUES ('$chainName', 1, 1, $currentTime, $currentTime)")
                    }
                    
                } catch (e: Exception) {
                    e.printStackTrace()
                    throw Exception("Migration 16->17 failed: ${e.message}", e)
                }
            }
        }
        
        val MIGRATION_17_18 = object : Migration(17, 18) {
            override fun migrate(database: SupportSQLiteDatabase) {
                try {
                    // Create financial_insights table
                    val createFinancialInsightsTableSQL = """
                        CREATE TABLE IF NOT EXISTS financial_insights (
                            id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            insightType TEXT NOT NULL,
                            title TEXT NOT NULL,
                            description TEXT NOT NULL,
                            severity TEXT NOT NULL,
                            category TEXT,
                            amount REAL,
                            percentage REAL,
                            confidenceScore REAL NOT NULL,
                            actionable INTEGER NOT NULL DEFAULT 1,
                            suggestedAction TEXT,
                            isRead INTEGER NOT NULL DEFAULT 0,
                            isActedUpon INTEGER NOT NULL DEFAULT 0,
                            validUntil INTEGER,
                            data TEXT,
                            createdAt INTEGER NOT NULL,
                            updatedAt INTEGER NOT NULL
                        )
                    """
                    
                    if (!DatabaseMigrationHelper.safeExecuteSQL(database, createFinancialInsightsTableSQL)) {
                        throw Exception("Failed to create financial_insights table")
                    }
                    
                    // Create financial_goals table
                    val createFinancialGoalsTableSQL = """
                        CREATE TABLE IF NOT EXISTS financial_goals (
                            id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            name TEXT NOT NULL,
                            description TEXT,
                            goalType TEXT NOT NULL,
                            targetAmount REAL NOT NULL,
                            currentAmount REAL NOT NULL DEFAULT 0.0,
                            targetDate INTEGER,
                            priority TEXT NOT NULL,
                            isActive INTEGER NOT NULL DEFAULT 1,
                            isCompleted INTEGER NOT NULL DEFAULT 0,
                            completedAt INTEGER,
                            reminderEnabled INTEGER NOT NULL DEFAULT 0,
                            reminderFrequency TEXT,
                            autoTransferEnabled INTEGER NOT NULL DEFAULT 0,
                            autoTransferAmount REAL,
                            linkedAccountId TEXT,
                            tags TEXT,
                            createdAt INTEGER NOT NULL,
                            updatedAt INTEGER NOT NULL
                        )
                    """
                    
                    if (!DatabaseMigrationHelper.safeExecuteSQL(database, createFinancialGoalsTableSQL)) {
                        throw Exception("Failed to create financial_goals table")
                    }
                    
                    // Create spending_patterns table
                    val createSpendingPatternsTableSQL = """
                        CREATE TABLE IF NOT EXISTS spending_patterns (
                            id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            category TEXT NOT NULL,
                            timeframe TEXT NOT NULL,
                            averageAmount REAL NOT NULL,
                            totalAmount REAL NOT NULL,
                            transactionCount INTEGER NOT NULL,
                            trendDirection TEXT NOT NULL,
                            trendStrength REAL NOT NULL,
                            seasonality TEXT NOT NULL,
                            anomalyScore REAL NOT NULL,
                            frequency TEXT NOT NULL,
                            analysisDate INTEGER NOT NULL,
                            confidence REAL NOT NULL,
                            insights TEXT
                        )
                    """
                    
                    if (!DatabaseMigrationHelper.safeExecuteSQL(database, createSpendingPatternsTableSQL)) {
                        throw Exception("Failed to create spending_patterns table")
                    }
                    
                    // Create spending_forecasts table
                    val createSpendingForecastsTableSQL = """
                        CREATE TABLE IF NOT EXISTS spending_forecasts (
                            id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            category TEXT NOT NULL,
                            forecastDate INTEGER NOT NULL,
                            predictedAmount REAL NOT NULL,
                            actualAmount REAL,
                            accuracy REAL,
                            forecastMethod TEXT NOT NULL,
                            confidence REAL NOT NULL,
                            validUntil INTEGER NOT NULL,
                            factors TEXT,
                            createdAt INTEGER NOT NULL
                        )
                    """
                    
                    if (!DatabaseMigrationHelper.safeExecuteSQL(database, createSpendingForecastsTableSQL)) {
                        throw Exception("Failed to create spending_forecasts table")
                    }
                    
                    // Create indices for better performance
                    val indices = listOf(
                        "CREATE INDEX IF NOT EXISTS index_financial_insights_insightType ON financial_insights(insightType)",
                        "CREATE INDEX IF NOT EXISTS index_financial_insights_severity ON financial_insights(severity)",
                        "CREATE INDEX IF NOT EXISTS index_financial_insights_category ON financial_insights(category)",
                        "CREATE INDEX IF NOT EXISTS index_financial_insights_isRead ON financial_insights(isRead)",
                        "CREATE INDEX IF NOT EXISTS index_financial_insights_createdAt ON financial_insights(createdAt)",
                        "CREATE INDEX IF NOT EXISTS index_financial_goals_goalType ON financial_goals(goalType)",
                        "CREATE INDEX IF NOT EXISTS index_financial_goals_priority ON financial_goals(priority)",
                        "CREATE INDEX IF NOT EXISTS index_financial_goals_isActive ON financial_goals(isActive)",
                        "CREATE INDEX IF NOT EXISTS index_financial_goals_targetDate ON financial_goals(targetDate)",
                        "CREATE INDEX IF NOT EXISTS index_spending_patterns_category ON spending_patterns(category)",
                        "CREATE INDEX IF NOT EXISTS index_spending_patterns_timeframe ON spending_patterns(timeframe)",
                        "CREATE INDEX IF NOT EXISTS index_spending_patterns_analysisDate ON spending_patterns(analysisDate)",
                        "CREATE INDEX IF NOT EXISTS index_spending_forecasts_category ON spending_forecasts(category)",
                        "CREATE INDEX IF NOT EXISTS index_spending_forecasts_forecastDate ON spending_forecasts(forecastDate)",
                        "CREATE INDEX IF NOT EXISTS index_spending_forecasts_validUntil ON spending_forecasts(validUntil)"
                    )
                    
                    indices.forEach { sql ->
                        DatabaseMigrationHelper.safeExecuteSQL(database, sql)
                    }
                    
                } catch (e: Exception) {
                    e.printStackTrace()
                    throw Exception("Migration 17->18 failed: ${e.message}", e)
                }
            }
        }
        
        val MIGRATION_18_19 = object : Migration(18, 19) {
            override fun migrate(database: SupportSQLiteDatabase) {
                try {
                    // Create investments table
                    database.execSQL("""
                        CREATE TABLE IF NOT EXISTS investments (
                            id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            name TEXT NOT NULL,
                            symbol TEXT,
                            type TEXT NOT NULL,
                            initialValue REAL NOT NULL,
                            currentValue REAL NOT NULL,
                            quantity REAL NOT NULL DEFAULT 1.0,
                            averageCost REAL NOT NULL,
                            purchaseDate INTEGER NOT NULL,
                            currency TEXT NOT NULL DEFAULT 'SEK',
                            broker TEXT,
                            category TEXT NOT NULL DEFAULT 'EQUITY',
                            notes TEXT,
                            isActive INTEGER NOT NULL DEFAULT 1,
                            lastUpdated INTEGER NOT NULL,
                            createdAt INTEGER NOT NULL
                        )
                    """)
                    
                    // Create investment_transactions table
                    database.execSQL("""
                        CREATE TABLE IF NOT EXISTS investment_transactions (
                            id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            investmentId INTEGER NOT NULL,
                            type TEXT NOT NULL,
                            quantity REAL NOT NULL,
                            pricePerUnit REAL NOT NULL,
                            totalAmount REAL NOT NULL,
                            fees REAL NOT NULL DEFAULT 0.0,
                            transactionDate INTEGER NOT NULL,
                            notes TEXT,
                            createdAt INTEGER NOT NULL,
                            FOREIGN KEY(investmentId) REFERENCES investments(id) ON DELETE CASCADE
                        )
                    """)
                    
                    // Create investment_price_history table
                    database.execSQL("""
                        CREATE TABLE IF NOT EXISTS investment_price_history (
                            id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            investmentId INTEGER NOT NULL,
                            price REAL NOT NULL,
                            currency TEXT NOT NULL DEFAULT 'SEK',
                            recordedAt INTEGER NOT NULL,
                            source TEXT,
                            FOREIGN KEY(investmentId) REFERENCES investments(id) ON DELETE CASCADE
                        )
                    """)
                    
                    // Create investment_dividends table
                    database.execSQL("""
                        CREATE TABLE IF NOT EXISTS investment_dividends (
                            id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            investmentId INTEGER NOT NULL,
                            amountPerShare REAL NOT NULL,
                            totalAmount REAL NOT NULL,
                            currency TEXT NOT NULL DEFAULT 'SEK',
                            paymentDate INTEGER NOT NULL,
                            exDividendDate INTEGER,
                            taxWithheld REAL NOT NULL DEFAULT 0.0,
                            notes TEXT,
                            createdAt INTEGER NOT NULL,
                            FOREIGN KEY(investmentId) REFERENCES investments(id) ON DELETE CASCADE
                        )
                    """)
                    
                    // Create investment_portfolios table
                    database.execSQL("""
                        CREATE TABLE IF NOT EXISTS investment_portfolios (
                            id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            name TEXT NOT NULL,
                            description TEXT,
                            targetAllocation TEXT,
                            riskProfile TEXT NOT NULL DEFAULT 'MODERATE',
                            isActive INTEGER NOT NULL DEFAULT 1,
                            createdAt INTEGER NOT NULL
                        )
                    """)
                    
                    // Create portfolio_investments table
                    database.execSQL("""
                        CREATE TABLE IF NOT EXISTS portfolio_investments (
                            id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            portfolioId INTEGER NOT NULL,
                            investmentId INTEGER NOT NULL,
                            targetPercentage REAL,
                            addedAt INTEGER NOT NULL,
                            FOREIGN KEY(portfolioId) REFERENCES investment_portfolios(id) ON DELETE CASCADE,
                            FOREIGN KEY(investmentId) REFERENCES investments(id) ON DELETE CASCADE
                        )
                    """)
                    
                    // Create indices for better performance
                    database.execSQL("CREATE INDEX IF NOT EXISTS index_investments_type ON investments(type)")
                    database.execSQL("CREATE INDEX IF NOT EXISTS index_investments_category ON investments(category)")
                    database.execSQL("CREATE INDEX IF NOT EXISTS index_investments_isActive ON investments(isActive)")
                    database.execSQL("CREATE INDEX IF NOT EXISTS index_investment_transactions_investmentId ON investment_transactions(investmentId)")
                    database.execSQL("CREATE INDEX IF NOT EXISTS index_investment_transactions_transactionDate ON investment_transactions(transactionDate)")
                    database.execSQL("CREATE INDEX IF NOT EXISTS index_investment_price_history_investmentId ON investment_price_history(investmentId)")
                    database.execSQL("CREATE INDEX IF NOT EXISTS index_investment_price_history_recordedAt ON investment_price_history(recordedAt)")
                    database.execSQL("CREATE INDEX IF NOT EXISTS index_investment_dividends_investmentId ON investment_dividends(investmentId)")
                    database.execSQL("CREATE INDEX IF NOT EXISTS index_investment_dividends_paymentDate ON investment_dividends(paymentDate)")
                    database.execSQL("CREATE INDEX IF NOT EXISTS index_portfolio_investments_portfolioId ON portfolio_investments(portfolioId)")
                    database.execSQL("CREATE INDEX IF NOT EXISTS index_portfolio_investments_investmentId ON portfolio_investments(investmentId)")
                    
                } catch (e: Exception) {
                    e.printStackTrace()
                    throw Exception("Migration 18->19 failed: ${e.message}", e)
                }
            }
        }
        
        val MIGRATION_19_20 = object : Migration(19, 20) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // This migration might already be applied, just ensuring it exists
                try {
                    // Any additional migrations for version 20 if needed
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        
        val MIGRATION_20_21 = object : Migration(20, 21) {
            override fun migrate(database: SupportSQLiteDatabase) {
                try {
                    // Create automation_rules table
                    database.execSQL("""
                        CREATE TABLE IF NOT EXISTS automation_rules (
                            id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            name TEXT NOT NULL,
                            description TEXT NOT NULL,
                            conditions TEXT NOT NULL,
                            actions TEXT NOT NULL,
                            isActive INTEGER NOT NULL DEFAULT 1,
                            priority INTEGER NOT NULL DEFAULT 1,
                            usageCount INTEGER NOT NULL DEFAULT 0,
                            createdBy TEXT NOT NULL DEFAULT 'USER',
                            confidence REAL NOT NULL DEFAULT 1.0,
                            createdAt INTEGER NOT NULL,
                            lastModified INTEGER NOT NULL
                        )
                    """)
                    
                    // Create auto_transfer_rules table
                    database.execSQL("""
                        CREATE TABLE IF NOT EXISTS auto_transfer_rules (
                            id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            name TEXT NOT NULL,
                            description TEXT NOT NULL,
                            sourceAccountId TEXT NOT NULL,
                            targetAccountId TEXT NOT NULL,
                            transferType TEXT NOT NULL,
                            amount REAL NOT NULL,
                            frequency TEXT NOT NULL,
                            conditions TEXT NOT NULL,
                            isActive INTEGER NOT NULL DEFAULT 1,
                            lastExecuted INTEGER,
                            totalTransferred REAL NOT NULL DEFAULT 0.0,
                            createdAt INTEGER NOT NULL
                        )
                    """)
                    
                    // Create smart_insights table
                    database.execSQL("""
                        CREATE TABLE IF NOT EXISTS smart_insights (
                            id TEXT PRIMARY KEY NOT NULL,
                            type TEXT NOT NULL,
                            title TEXT NOT NULL,
                            description TEXT NOT NULL,
                            actionable INTEGER NOT NULL DEFAULT 0,
                            actions TEXT NOT NULL,
                            priority TEXT NOT NULL,
                            confidence REAL NOT NULL,
                            category TEXT,
                            impact TEXT NOT NULL,
                            isRead INTEGER NOT NULL DEFAULT 0,
                            isDismissed INTEGER NOT NULL DEFAULT 0,
                            expiresAt INTEGER,
                            createdAt INTEGER NOT NULL,
                            updatedAt INTEGER NOT NULL
                        )
                    """)
                    
                    // Create indices for better performance
                    database.execSQL("CREATE INDEX IF NOT EXISTS index_automation_rules_isActive ON automation_rules(isActive)")
                    database.execSQL("CREATE INDEX IF NOT EXISTS index_automation_rules_priority ON automation_rules(priority)")
                    database.execSQL("CREATE INDEX IF NOT EXISTS index_automation_rules_usageCount ON automation_rules(usageCount)")
                    database.execSQL("CREATE INDEX IF NOT EXISTS index_auto_transfer_rules_isActive ON auto_transfer_rules(isActive)")
                    database.execSQL("CREATE INDEX IF NOT EXISTS index_smart_insights_type ON smart_insights(type)")
                    database.execSQL("CREATE INDEX IF NOT EXISTS index_smart_insights_isRead ON smart_insights(isRead)")
                    database.execSQL("CREATE INDEX IF NOT EXISTS index_smart_insights_isDismissed ON smart_insights(isDismissed)")
                    
                } catch (e: Exception) {
                    e.printStackTrace()
                    throw Exception("Migration 20->21 failed: ${e.message}", e)
                }
            }
        }
        
        val MIGRATION_21_22 = object : Migration(21, 22) {
            override fun migrate(database: SupportSQLiteDatabase) {
                try {
                    // Add missing updatedAt column to smart_insights table if it doesn't exist
                    database.execSQL("ALTER TABLE smart_insights ADD COLUMN updatedAt INTEGER DEFAULT ${System.currentTimeMillis()}")
                } catch (e: Exception) {
                    // Column might already exist, ignore error
                    e.printStackTrace()
                }
            }
        }
        
        val MIGRATION_22_23 = object : Migration(22, 23) {
            override fun migrate(database: SupportSQLiteDatabase) {
                try {
                    // Create automation_rules table if it doesn't exist
                    database.execSQL("""
                        CREATE TABLE IF NOT EXISTS automation_rules (
                            id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            name TEXT NOT NULL,
                            description TEXT NOT NULL,
                            conditions TEXT NOT NULL,
                            actions TEXT NOT NULL,
                            isActive INTEGER NOT NULL DEFAULT 1,
                            priority INTEGER NOT NULL DEFAULT 1,
                            usageCount INTEGER NOT NULL DEFAULT 0,
                            createdBy TEXT NOT NULL,
                            confidence REAL NOT NULL DEFAULT 1.0,
                            createdAt INTEGER NOT NULL,
                            lastModified INTEGER NOT NULL
                        )
                    """)
                    
                    // Create auto_transfer_rules table if it doesn't exist
                    database.execSQL("""
                        CREATE TABLE IF NOT EXISTS auto_transfer_rules (
                            id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                            name TEXT NOT NULL,
                            description TEXT NOT NULL,
                            sourceAccountId TEXT NOT NULL,
                            targetAccountId TEXT NOT NULL,
                            transferType TEXT NOT NULL,
                            amount REAL NOT NULL,
                            frequency TEXT NOT NULL,
                            conditions TEXT NOT NULL,
                            isActive INTEGER NOT NULL DEFAULT 1,
                            lastExecuted INTEGER,
                            totalTransferred REAL NOT NULL DEFAULT 0.0,
                            createdAt INTEGER NOT NULL
                        )
                    """)
                    
                    // Create smart_insights table if it doesn't exist
                    database.execSQL("""
                        CREATE TABLE IF NOT EXISTS smart_insights (
                            id TEXT PRIMARY KEY NOT NULL,
                            type TEXT NOT NULL,
                            title TEXT NOT NULL,
                            description TEXT NOT NULL,
                            actionable INTEGER NOT NULL,
                            actions TEXT NOT NULL,
                            priority TEXT NOT NULL,
                            confidence REAL NOT NULL,
                            category TEXT,
                            impact TEXT NOT NULL,
                            isRead INTEGER NOT NULL DEFAULT 0,
                            isDismissed INTEGER NOT NULL DEFAULT 0,
                            expiresAt INTEGER,
                            createdAt INTEGER NOT NULL,
                            updatedAt INTEGER NOT NULL DEFAULT ${System.currentTimeMillis()}
                        )
                    """)
                    
                } catch (e: Exception) {
                    // Tables might already exist, ignore error
                    e.printStackTrace()
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