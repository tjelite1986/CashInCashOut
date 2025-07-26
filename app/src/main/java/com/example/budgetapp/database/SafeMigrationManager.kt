package com.example.budgetapp.database

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object SafeMigrationManager {
    
    fun createDatabaseWithSafeMigration(context: Context): BudgetDatabase {
        return try {
            // Temporarily use destructive migration while fixing schema issues
            Room.databaseBuilder(
                context.applicationContext,
                BudgetDatabase::class.java,
                "budget_database"
            )
            .addMigrations(
                BudgetDatabase.MIGRATION_1_2,
                BudgetDatabase.MIGRATION_2_3,
                BudgetDatabase.MIGRATION_3_4,
                BudgetDatabase.MIGRATION_4_5,
                BudgetDatabase.MIGRATION_5_6,
                BudgetDatabase.MIGRATION_6_7,
                BudgetDatabase.MIGRATION_7_8,
                BudgetDatabase.MIGRATION_8_9,
                BudgetDatabase.MIGRATION_9_10,
                BudgetDatabase.MIGRATION_10_11,
                BudgetDatabase.MIGRATION_11_12,
                BudgetDatabase.MIGRATION_12_13,
                BudgetDatabase.MIGRATION_13_14,
                BudgetDatabase.MIGRATION_14_15,
                BudgetDatabase.MIGRATION_15_16,
                BudgetDatabase.MIGRATION_16_17,
                BudgetDatabase.MIGRATION_17_18,
                BudgetDatabase.MIGRATION_18_19,
                BudgetDatabase.MIGRATION_19_20,
                BudgetDatabase.MIGRATION_20_21,
                BudgetDatabase.MIGRATION_21_22,
                BudgetDatabase.MIGRATION_22_23
            )
            .addCallback(BudgetDatabase.Companion.DatabaseCallback())
            .fallbackToDestructiveMigration()
            .build()
        } catch (e: Exception) {
            e.printStackTrace()
            // Fallback: Create fresh database with backup attempt
            createFallbackDatabase(context)
        }
    }
    
    private fun createSafeMigration7to8(context: Context): Migration {
        return object : Migration(7, 8) {
            override fun migrate(database: SupportSQLiteDatabase) {
                try {
                    // Create backup before migration
                    DatabaseBackupHelper.createBackup(context, database)
                    
                    // Check current state
                    if (!DatabaseMigrationHelper.doesTableExist(database, "budgets")) {
                        // Safe migration to add budgets table
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
                            // Create indices
                            DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_budgets_categoryName ON budgets(categoryName)")
                            DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_budgets_isActive ON budgets(isActive)")
                            DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_budgets_period ON budgets(period)")
                            DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_budgets_startDate_endDate ON budgets(startDate, endDate)")
                            
                            // Mark migration as successful
                            DatabaseBackupHelper.setDataVersion(context, 8)
                        } else {
                            throw Exception("Failed to create budgets table")
                        }
                    } else {
                        // Table already exists, just update version
                        DatabaseBackupHelper.setDataVersion(context, 8)
                    }
                    
                } catch (e: Exception) {
                    e.printStackTrace()
                    // Try to restore from backup if migration fails
                    if (DatabaseBackupHelper.hasBackup(context)) {
                        throw Exception("Migration failed, backup available for restore: ${e.message}")
                    } else {
                        throw Exception("Migration failed: ${e.message}")
                    }
                }
            }
        }
    }
    
    private fun createCombinedMigration6to8(context: Context): Migration {
        return object : Migration(6, 8) {
            override fun migrate(database: SupportSQLiteDatabase) {
                try {
                    // Create backup before migration
                    DatabaseBackupHelper.createBackup(context, database)
                    
                    // Add loans table (from 6->7)
                    if (!DatabaseMigrationHelper.doesTableExist(database, "loans")) {
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
                    
                    // Add budgets table (from 7->8)
                    if (!DatabaseMigrationHelper.doesTableExist(database, "budgets")) {
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
                            DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_budgets_categoryName ON budgets(categoryName)")
                            DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_budgets_isActive ON budgets(isActive)")
                            DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_budgets_period ON budgets(period)")
                            DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_budgets_startDate_endDate ON budgets(startDate, endDate)")
                        }
                    }
                    
                    DatabaseBackupHelper.setDataVersion(context, 8)
                    
                } catch (e: Exception) {
                    e.printStackTrace()
                    throw Exception("Combined migration 6->8 failed: ${e.message}", e)
                }
            }
        }
    }
    
    private fun createCombinedMigration5to8(context: Context): Migration {
        return object : Migration(5, 8) {
            override fun migrate(database: SupportSQLiteDatabase) {
                try {
                    // Create backup before migration
                    DatabaseBackupHelper.createBackup(context, database)
                    
                    // Add expenses table (from 5->6)
                    if (!DatabaseMigrationHelper.doesTableExist(database, "expenses")) {
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
                    
                    // Add loans table (from 6->7)
                    if (!DatabaseMigrationHelper.doesTableExist(database, "loans")) {
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
                    
                    // Add budgets table (from 7->8)
                    if (!DatabaseMigrationHelper.doesTableExist(database, "budgets")) {
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
                            DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_budgets_categoryName ON budgets(categoryName)")
                            DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_budgets_isActive ON budgets(isActive)")
                            DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_budgets_period ON budgets(period)")
                            DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_budgets_startDate_endDate ON budgets(startDate, endDate)")
                        }
                    }
                    
                    DatabaseBackupHelper.setDataVersion(context, 8)
                    
                } catch (e: Exception) {
                    e.printStackTrace()
                    throw Exception("Combined migration 5->8 failed: ${e.message}", e)
                }
            }
        }
    }
    
    private fun createSafeMigration8to9(context: Context): Migration {
        return object : Migration(8, 9) {
            override fun migrate(database: SupportSQLiteDatabase) {
                try {
                    // Create backup before migration
                    DatabaseBackupHelper.createBackup(context, database)
                    
                    // Create receipts table if it doesn't exist
                    if (!DatabaseMigrationHelper.doesTableExist(database, "receipts")) {
                        val createReceiptsTableSQL = """
                            CREATE TABLE receipts (
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
                        
                        if (DatabaseMigrationHelper.safeExecuteSQL(database, createReceiptsTableSQL)) {
                            DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_receipts_expenseId ON receipts(expenseId)")
                            DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_receipts_createdAt ON receipts(createdAt)")
                        }
                    }
                    
                    // Create receipt_items table if it doesn't exist
                    if (!DatabaseMigrationHelper.doesTableExist(database, "receipt_items")) {
                        val createReceiptItemsTableSQL = """
                            CREATE TABLE receipt_items (
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
                        
                        if (DatabaseMigrationHelper.safeExecuteSQL(database, createReceiptItemsTableSQL)) {
                            DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_receipt_items_receiptId ON receipt_items(receiptId)")
                            DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_receipt_items_productId ON receipt_items(productId)")
                            DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_receipt_items_createdAt ON receipt_items(createdAt)")
                        }
                    }
                    
                    DatabaseBackupHelper.setDataVersion(context, 9)
                    
                } catch (e: Exception) {
                    e.printStackTrace()
                    throw Exception("Safe migration 8->9 failed: ${e.message}", e)
                }
            }
        }
    }
    
    private fun createSafeMigration9to10(context: Context): Migration {
        return object : Migration(9, 10) {
            override fun migrate(database: SupportSQLiteDatabase) {
                try {
                    // Create backup before migration
                    DatabaseBackupHelper.createBackup(context, database)
                    
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
                    
                    DatabaseBackupHelper.setDataVersion(context, 10)
                    
                } catch (e: Exception) {
                    e.printStackTrace()
                    throw Exception("Safe migration 9->10 failed: ${e.message}", e)
                }
            }
        }
    }
    
    private fun createSafeMigration10to11(context: Context): Migration {
        return object : Migration(10, 11) {
            override fun migrate(database: SupportSQLiteDatabase) {
                try {
                    // Create backup before migration
                    DatabaseBackupHelper.createBackup(context, database)
                    
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
                    
                    // Create shopping_lists table if it doesn't exist
                    if (!DatabaseMigrationHelper.doesTableExist(database, "shopping_lists")) {
                        val createShoppingListsTableSQL = """
                            CREATE TABLE shopping_lists (
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
                        
                        if (DatabaseMigrationHelper.safeExecuteSQL(database, createShoppingListsTableSQL)) {
                            DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_shopping_lists_isCompleted ON shopping_lists(isCompleted)")
                            DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_shopping_lists_targetStoreId ON shopping_lists(targetStoreId)")
                        }
                    }
                    
                    // Create shopping_list_items table if it doesn't exist
                    if (!DatabaseMigrationHelper.doesTableExist(database, "shopping_list_items")) {
                        val createShoppingListItemsTableSQL = """
                            CREATE TABLE shopping_list_items (
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
                        
                        if (DatabaseMigrationHelper.safeExecuteSQL(database, createShoppingListItemsTableSQL)) {
                            DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_shopping_list_items_shoppingListId ON shopping_list_items(shoppingListId)")
                            DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_shopping_list_items_productId ON shopping_list_items(productId)")
                        }
                    }
                    
                    // Create price_history table if it doesn't exist
                    if (!DatabaseMigrationHelper.doesTableExist(database, "price_history")) {
                        val createPriceHistoryTableSQL = """
                            CREATE TABLE price_history (
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
                        
                        if (DatabaseMigrationHelper.safeExecuteSQL(database, createPriceHistoryTableSQL)) {
                            DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_price_history_productId ON price_history(productId)")
                            DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_price_history_storeId ON price_history(storeId)")
                            DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_price_history_recordedAt ON price_history(recordedAt)")
                            DatabaseMigrationHelper.safeExecuteSQL(database, "CREATE INDEX IF NOT EXISTS index_price_history_productId_storeId_recordedAt ON price_history(productId, storeId, recordedAt)")
                        }
                    }
                    
                    DatabaseBackupHelper.setDataVersion(context, 11)
                    
                } catch (e: Exception) {
                    e.printStackTrace()
                    throw Exception("Safe migration 10->11 failed: ${e.message}", e)
                }
            }
        }
    }
    
    private fun createFallbackDatabase(context: Context): BudgetDatabase {
        return try {
            // Try to restore from backup first
            if (DatabaseBackupHelper.hasBackup(context)) {
                // Attempt backup restore
                if (DatabaseBackupHelper.restoreFromBackup(context)) {
                    // Try again with restored database
                    Room.databaseBuilder(
                        context.applicationContext,
                        BudgetDatabase::class.java,
                        "budget_database"
                    )
                    .addCallback(BudgetDatabase.Companion.DatabaseCallback())
                    .fallbackToDestructiveMigration()
                    .build()
                } else {
                    createCleanDatabase(context)
                }
            } else {
                createCleanDatabase(context)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            createCleanDatabase(context)
        }
    }
    
    private fun createCleanDatabase(context: Context): BudgetDatabase {
        // Last resort: Clean database creation
        return Room.databaseBuilder(
            context.applicationContext,
            BudgetDatabase::class.java,
            "budget_database"
        )
        .fallbackToDestructiveMigration()
        .build()
    }
}