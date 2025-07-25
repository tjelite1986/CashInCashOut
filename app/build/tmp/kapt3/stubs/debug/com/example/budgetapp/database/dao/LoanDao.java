package com.example.budgetapp.database.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0017\bg\u0018\u00002\u00020\u0001J1\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0011\u0010\f\u001a\u00020\rH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0019\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J\u0019\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0015H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J\u001b\u0010\u0017\u001a\u0004\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018J\u0014\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u001b0\u001aH\'J\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00110\u001bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u001b\u0010\u001d\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0014\u001a\u00020\u0015H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J\u001c\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u001b0\u001a2\u0006\u0010\b\u001a\u00020\u0005H\'J\u001c\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u001b0\u001a2\u0006\u0010 \u001a\u00020!H\'J\u001c\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u001b0\u001a2\u0006\u0010\t\u001a\u00020\nH\'J$\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u001b0\u001a2\u0006\u0010$\u001a\u00020\u00152\u0006\u0010%\u001a\u00020\u0015H\'J\u001c\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u001b0\u001a2\u0006\u0010\'\u001a\u00020\u0015H\'J\u001b\u0010(\u001a\u0004\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018J\u001b\u0010)\u001a\u0004\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018J\u0017\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00110\u001bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0019\u0010+\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u0011H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J\u001f\u0010,\u001a\u00020\r2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00110\u001bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010.J3\u0010/\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u00100\u001a\u00020\u00152\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u00101\u001a\u00020\u0015H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00102J#\u00103\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u00101\u001a\u00020\u0015H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00104J\u0019\u00105\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J+\u00106\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u00101\u001a\u00020\u0015H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00107\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00068"}, d2 = {"Lcom/example/budgetapp/database/dao/LoanDao;", "", "checkDuplicateLoan", "", "title", "", "amount", "", "personName", "type", "Lcom/example/budgetapp/database/entities/LoanType;", "(Ljava/lang/String;DLjava/lang/String;Lcom/example/budgetapp/database/entities/LoanType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllLoans", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteLoan", "loan", "Lcom/example/budgetapp/database/entities/Loan;", "(Lcom/example/budgetapp/database/entities/Loan;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteLoanById", "loanId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveAmountByType", "(Lcom/example/budgetapp/database/entities/LoanType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllLoans", "Lkotlinx/coroutines/flow/Flow;", "", "getAllLoansSnapshot", "getLoanById", "getLoansByPerson", "getLoansByStatus", "isPaidBack", "", "getLoansByType", "getLoansWithDueDateRange", "startDate", "endDate", "getOverdueLoans", "currentDate", "getPaidBackAmountByType", "getTotalAmountByType", "getUnpaidLoans", "insertLoan", "insertLoans", "loans", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markLoanAsPaidBack", "paidDate", "updatedAt", "(JJDJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "undoLoanPayment", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateLoan", "updatePaidBackAmount", "(JDJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "CashInCashOut_debug"})
@androidx.room.Dao
public abstract interface LoanDao {
    
    @androidx.room.Query(value = "SELECT * FROM loans ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.Loan>> getAllLoans();
    
    @androidx.room.Query(value = "SELECT * FROM loans ORDER BY createdAt DESC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getAllLoansSnapshot(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.database.entities.Loan>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM loans WHERE id = :loanId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getLoanById(long loanId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.database.entities.Loan> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM loans WHERE type = :type ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.Loan>> getLoansByType(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.LoanType type);
    
    @androidx.room.Query(value = "SELECT * FROM loans WHERE isPaidBack = :isPaidBack ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.Loan>> getLoansByStatus(boolean isPaidBack);
    
    @androidx.room.Query(value = "SELECT * FROM loans WHERE isPaidBack = 0 ORDER BY createdAt DESC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getUnpaidLoans(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.database.entities.Loan>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM loans WHERE personName LIKE \'%\' || :personName || \'%\' ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.Loan>> getLoansByPerson(@org.jetbrains.annotations.NotNull
    java.lang.String personName);
    
    @androidx.room.Query(value = "SELECT * FROM loans WHERE dueDate IS NOT NULL AND dueDate < :currentDate AND isPaidBack = 0 ORDER BY dueDate ASC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.Loan>> getOverdueLoans(long currentDate);
    
    @androidx.room.Query(value = "SELECT * FROM loans WHERE dueDate IS NOT NULL AND dueDate BETWEEN :startDate AND :endDate ORDER BY dueDate ASC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.Loan>> getLoansWithDueDateRange(long startDate, long endDate);
    
    @androidx.room.Query(value = "SELECT SUM(amount) FROM loans WHERE type = :type")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getTotalAmountByType(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.LoanType type, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    @androidx.room.Query(value = "SELECT SUM(amount) FROM loans WHERE type = :type AND isPaidBack = 0")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getActiveAmountByType(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.LoanType type, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    @androidx.room.Query(value = "SELECT SUM(paidBackAmount) FROM loans WHERE type = :type AND isPaidBack = 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getPaidBackAmountByType(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.LoanType type, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertLoan(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Loan loan, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertLoans(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.database.entities.Loan> loans, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateLoan(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Loan loan, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteLoan(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Loan loan, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM loans WHERE id = :loanId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteLoanById(long loanId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM loans")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteAllLoans(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE loans SET isPaidBack = 1, paidBackDate = :paidDate, paidBackAmount = :amount, updatedAt = :updatedAt WHERE id = :loanId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object markLoanAsPaidBack(long loanId, long paidDate, double amount, long updatedAt, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE loans SET isPaidBack = 0, paidBackDate = NULL, paidBackAmount = 0.0, updatedAt = :updatedAt WHERE id = :loanId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object undoLoanPayment(long loanId, long updatedAt, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE loans SET paidBackAmount = :amount, updatedAt = :updatedAt WHERE id = :loanId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updatePaidBackAmount(long loanId, double amount, long updatedAt, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM loans WHERE title = :title AND amount = :amount AND personName = :personName AND type = :type")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object checkDuplicateLoan(@org.jetbrains.annotations.NotNull
    java.lang.String title, double amount, @org.jetbrains.annotations.NotNull
    java.lang.String personName, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.LoanType type, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}