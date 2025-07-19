package com.example.budgetapp.database

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object SafeMigrationManager {
    
    fun createDatabaseWithSafeMigration(context: Context): BudgetDatabase {
        return try {
            // First attempt: Try with all migrations
            Room.databaseBuilder(
                context.applicationContext,
                BudgetDatabase::class.java,
                "budget_database"
            )
            .addCallback(BudgetDatabase.Companion.DatabaseCallback())
            .addMigrations(
                BudgetDatabase.MIGRATION_1_2,
                BudgetDatabase.MIGRATION_2_3, 
                BudgetDatabase.MIGRATION_3_4,
                BudgetDatabase.MIGRATION_4_5,
                BudgetDatabase.MIGRATION_5_6,
                BudgetDatabase.MIGRATION_6_7,
                createSafeMigration7to8(context),
                createCombinedMigration6to8(context),
                createCombinedMigration5to8(context)
            )
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
        .addCallback(BudgetDatabase.Companion.DatabaseCallback())
        .fallbackToDestructiveMigration()
        .build()
    }
}