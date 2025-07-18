package com.example.budgetapp.database.dao

import androidx.room.*
import com.example.budgetapp.database.entities.Income
import kotlinx.coroutines.flow.Flow

@Dao
interface IncomeDao {
    @Query("SELECT * FROM incomes ORDER BY date DESC")
    fun getAllIncomes(): Flow<List<Income>>

    @Query("SELECT * FROM incomes WHERE id = :incomeId")
    suspend fun getIncomeById(incomeId: Long): Income?

    @Query("SELECT * FROM incomes WHERE category = :category ORDER BY date DESC")
    fun getIncomesByCategory(category: String): Flow<List<Income>>

    @Query("SELECT * FROM incomes WHERE date >= :startDate AND date <= :endDate ORDER BY date DESC")
    fun getIncomesByDateRange(startDate: Long, endDate: Long): Flow<List<Income>>

    @Query("SELECT SUM(amount) FROM incomes")
    suspend fun getTotalIncome(): Double?

    @Query("SELECT SUM(amount) FROM incomes WHERE date >= :startDate AND date <= :endDate")
    suspend fun getTotalIncomeByDateRange(startDate: Long, endDate: Long): Double?

    @Query("SELECT SUM(amount) FROM incomes WHERE category = :category")
    suspend fun getTotalIncomeByCategory(category: String): Double?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIncome(income: Income): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIncomes(incomes: List<Income>)

    @Update
    suspend fun updateIncome(income: Income)

    @Delete
    suspend fun deleteIncome(income: Income)

    @Query("DELETE FROM incomes WHERE id = :incomeId")
    suspend fun deleteIncomeById(incomeId: Long)

    @Query("DELETE FROM incomes")
    suspend fun deleteAllIncomes()
}