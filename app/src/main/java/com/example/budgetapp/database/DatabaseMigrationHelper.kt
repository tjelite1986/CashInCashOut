package com.example.budgetapp.database

import androidx.sqlite.db.SupportSQLiteDatabase

object DatabaseMigrationHelper {
    
    fun doesTableExist(database: SupportSQLiteDatabase, tableName: String): Boolean {
        return try {
            database.query("SELECT name FROM sqlite_master WHERE type='table' AND name='$tableName'").use { cursor ->
                cursor.count > 0
            }
        } catch (e: Exception) {
            false
        }
    }
    
    fun doesColumnExist(database: SupportSQLiteDatabase, tableName: String, columnName: String): Boolean {
        return try {
            database.query("PRAGMA table_info($tableName)").use { cursor ->
                var exists = false
                val nameColumnIndex = cursor.getColumnIndex("name")
                if (nameColumnIndex >= 0) {
                    while (cursor.moveToNext()) {
                        val name = cursor.getString(nameColumnIndex)
                        if (name == columnName) {
                            exists = true
                            break
                        }
                    }
                }
                exists
            }
        } catch (e: Exception) {
            false
        }
    }
    
    fun getCurrentVersion(database: SupportSQLiteDatabase): Int {
        return try {
            database.version
        } catch (e: Exception) {
            1
        }
    }
    
    fun safeExecuteSQL(database: SupportSQLiteDatabase, sql: String): Boolean {
        return try {
            database.execSQL(sql)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}