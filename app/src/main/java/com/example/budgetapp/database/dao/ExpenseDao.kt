package com.example.budgetapp.database.dao

import androidx.room.*
import com.example.budgetapp.database.entities.Expense
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM expenses ORDER BY date DESC")
    fun getAllExpenses(): Flow<List<Expense>>

    @Query("SELECT * FROM expenses WHERE id = :expenseId")
    suspend fun getExpenseById(expenseId: Long): Expense?

    @Query("SELECT * FROM expenses WHERE category = :category ORDER BY date DESC")
    fun getExpensesByCategory(category: String): Flow<List<Expense>>

    @Query("SELECT * FROM expenses WHERE date >= :startDate AND date <= :endDate ORDER BY date DESC")
    fun getExpensesByDateRange(startDate: Long, endDate: Long): Flow<List<Expense>>
    
    @Query("SELECT * FROM expenses WHERE date >= :startDate AND date <= :endDate")
    suspend fun getExpensesBetweenDates(startDate: Long, endDate: Long): List<Expense>

    @Query("SELECT * FROM expenses ORDER BY date DESC")
    suspend fun getAllExpensesSnapshot(): List<Expense>

    @Query("SELECT * FROM expenses WHERE store = :store ORDER BY date DESC")
    fun getExpensesByStore(store: String): Flow<List<Expense>>

    @Query("SELECT SUM(amount) FROM expenses")
    suspend fun getTotalExpenses(): Double?

    @Query("SELECT SUM(amount) FROM expenses WHERE date >= :startDate AND date <= :endDate")
    suspend fun getTotalExpensesByDateRange(startDate: Long, endDate: Long): Double?

    @Query("SELECT SUM(amount) FROM expenses WHERE category = :category")
    suspend fun getTotalExpensesByCategory(category: String): Double?

    @Query("SELECT SUM(amount) FROM expenses WHERE store = :store")
    suspend fun getTotalExpensesByStore(store: String): Double?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: Expense): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpenses(expenses: List<Expense>)

    @Update
    suspend fun updateExpense(expense: Expense)

    @Delete
    suspend fun deleteExpense(expense: Expense)

    @Query("DELETE FROM expenses WHERE id = :expenseId")
    suspend fun deleteExpenseById(expenseId: Long)

    @Query("DELETE FROM expenses")
    suspend fun deleteAllExpenses()

    @Query("SELECT COUNT(*) FROM expenses WHERE title = :title AND amount = :amount AND COALESCE(category, '') = :category AND date = :date AND COALESCE(store, '') = COALESCE(:store, '')")
    suspend fun checkDuplicateExpense(title: String, amount: Double, category: String, date: Long, store: String?): Int
    
    @Query("SELECT * FROM expenses WHERE isRecurring = 1 ORDER BY date DESC")
    suspend fun getRecurringExpenses(): List<Expense>
}