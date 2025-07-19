package com.example.budgetapp.utils

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import android.database.sqlite.SQLiteConstraintException
import com.google.android.material.snackbar.Snackbar
import java.sql.SQLException

object ErrorHandler {
    
    fun logError(tag: String, message: String, throwable: Throwable) {
        Log.e(tag, message, throwable)
    }
    
    fun showError(context: Context, title: String, message: String) {
        Toast.makeText(context, "$title: $message", Toast.LENGTH_LONG).show()
    }
    
    fun handleDatabaseError(context: Context, throwable: Throwable) {
        val message = when (throwable) {
            is SQLiteConstraintException -> "Databasfel: Ogiltig data eller constraint violation"
            is SQLException -> "Databasfel: SQL-problem - ${throwable.message}"
            else -> "Databasfel: ${throwable.javaClass.simpleName} - ${throwable.message}"
        }
        
        Log.e("DatabaseError", "Database operation failed", throwable)
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
    
    fun showErrorSnackbar(view: View, message: String, actionText: String? = null, action: (() -> Unit)? = null) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        if (actionText != null && action != null) {
            snackbar.setAction(actionText) { action() }
        }
        snackbar.show()
    }
}