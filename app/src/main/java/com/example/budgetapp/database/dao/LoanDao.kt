package com.example.budgetapp.database.dao

import androidx.room.*
import com.example.budgetapp.database.entities.Loan
import com.example.budgetapp.database.entities.LoanType
import kotlinx.coroutines.flow.Flow

@Dao
interface LoanDao {
    @Query("SELECT * FROM loans ORDER BY createdAt DESC")
    fun getAllLoans(): Flow<List<Loan>>

    @Query("SELECT * FROM loans ORDER BY createdAt DESC")
    suspend fun getAllLoansSnapshot(): List<Loan>

    @Query("SELECT * FROM loans WHERE id = :loanId")
    suspend fun getLoanById(loanId: Long): Loan?

    @Query("SELECT * FROM loans WHERE type = :type ORDER BY createdAt DESC")
    fun getLoansByType(type: LoanType): Flow<List<Loan>>

    @Query("SELECT * FROM loans WHERE isPaidBack = :isPaidBack ORDER BY createdAt DESC")
    fun getLoansByStatus(isPaidBack: Boolean): Flow<List<Loan>>

    @Query("SELECT * FROM loans WHERE personName LIKE '%' || :personName || '%' ORDER BY createdAt DESC")
    fun getLoansByPerson(personName: String): Flow<List<Loan>>

    @Query("SELECT * FROM loans WHERE dueDate IS NOT NULL AND dueDate < :currentDate AND isPaidBack = 0 ORDER BY dueDate ASC")
    fun getOverdueLoans(currentDate: Long): Flow<List<Loan>>

    @Query("SELECT * FROM loans WHERE dueDate IS NOT NULL AND dueDate BETWEEN :startDate AND :endDate ORDER BY dueDate ASC")
    fun getLoansWithDueDateRange(startDate: Long, endDate: Long): Flow<List<Loan>>

    @Query("SELECT SUM(amount) FROM loans WHERE type = :type")
    suspend fun getTotalAmountByType(type: LoanType): Double?

    @Query("SELECT SUM(amount) FROM loans WHERE type = :type AND isPaidBack = 0")
    suspend fun getActiveAmountByType(type: LoanType): Double?

    @Query("SELECT SUM(paidBackAmount) FROM loans WHERE type = :type AND isPaidBack = 1")
    suspend fun getPaidBackAmountByType(type: LoanType): Double?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLoan(loan: Loan): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLoans(loans: List<Loan>)

    @Update
    suspend fun updateLoan(loan: Loan)

    @Delete
    suspend fun deleteLoan(loan: Loan)

    @Query("DELETE FROM loans WHERE id = :loanId")
    suspend fun deleteLoanById(loanId: Long)

    @Query("DELETE FROM loans")
    suspend fun deleteAllLoans()

    // Mark loan as paid back
    @Query("UPDATE loans SET isPaidBack = 1, paidBackDate = :paidDate, paidBackAmount = :amount, updatedAt = :updatedAt WHERE id = :loanId")
    suspend fun markLoanAsPaidBack(loanId: Long, paidDate: Long, amount: Double, updatedAt: Long = System.currentTimeMillis())

    // Undo payment - mark loan as active again
    @Query("UPDATE loans SET isPaidBack = 0, paidBackDate = NULL, paidBackAmount = 0.0, updatedAt = :updatedAt WHERE id = :loanId")
    suspend fun undoLoanPayment(loanId: Long, updatedAt: Long = System.currentTimeMillis())

    // Partial payment
    @Query("UPDATE loans SET paidBackAmount = :amount, updatedAt = :updatedAt WHERE id = :loanId")
    suspend fun updatePaidBackAmount(loanId: Long, amount: Double, updatedAt: Long = System.currentTimeMillis())

    @Query("SELECT COUNT(*) FROM loans WHERE title = :title AND amount = :amount AND personName = :personName AND type = :type")
    suspend fun checkDuplicateLoan(title: String, amount: Double, personName: String, type: LoanType): Int
}