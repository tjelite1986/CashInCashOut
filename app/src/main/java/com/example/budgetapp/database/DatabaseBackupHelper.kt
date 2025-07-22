package com.example.budgetapp.database

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

object DatabaseBackupHelper {
    
    fun createBackup(context: Context, database: SupportSQLiteDatabase): Boolean {
        return try {
            val dbFile = context.getDatabasePath("budget_database")
            val backupFile = File(context.filesDir, "budget_database_backup.db")
            
            if (dbFile.exists()) {
                dbFile.copyTo(backupFile, overwrite = true)
                true
            } else {
                false
            }
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
    
    fun restoreFromBackup(context: Context): Boolean {
        return try {
            val backupFile = File(context.filesDir, "budget_database_backup.db")
            val dbFile = context.getDatabasePath("budget_database")
            
            if (backupFile.exists()) {
                backupFile.copyTo(dbFile, overwrite = true)
                true
            } else {
                false
            }
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
    
    fun hasBackup(context: Context): Boolean {
        val backupFile = File(context.filesDir, "budget_database_backup.db")
        return backupFile.exists()
    }
    
    fun deleteBackup(context: Context): Boolean {
        return try {
            val backupFile = File(context.filesDir, "budget_database_backup.db")
            if (backupFile.exists()) {
                backupFile.delete()
            } else {
                true
            }
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
    
    fun getDataVersion(context: Context): Int {
        return try {
            val prefs = context.getSharedPreferences("budget_db_prefs", Context.MODE_PRIVATE)
            prefs.getInt("db_version", 1)
        } catch (e: Exception) {
            1
        }
    }
    
    fun setDataVersion(context: Context, version: Int) {
        try {
            val prefs = context.getSharedPreferences("budget_db_prefs", Context.MODE_PRIVATE)
            prefs.edit().putInt("db_version", version).apply()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}