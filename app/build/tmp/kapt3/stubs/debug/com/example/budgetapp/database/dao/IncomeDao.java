package com.example.budgetapp.database.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\n\bg\u0018\u00002\u00020\u0001J\u0011\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u0019\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ\u0014\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000f0\u000eH\'J\u001b\u0010\u0010\u001a\u0004\u0018\u00010\u00072\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ\u001c\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000f0\u000e2\u0006\u0010\u0012\u001a\u00020\u0013H\'J$\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000f0\u000e2\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u000bH\'J\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u001b\u0010\u0019\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0012\u001a\u00020\u0013H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001aJ#\u0010\u001b\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001cJ\u0019\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u001f\u0010\u001e\u001a\u00020\u00032\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00070\u000fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 J\u0019\u0010!\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\""}, d2 = {"Lcom/example/budgetapp/database/dao/IncomeDao;", "", "deleteAllIncomes", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteIncome", "income", "Lcom/example/budgetapp/database/entities/Income;", "(Lcom/example/budgetapp/database/entities/Income;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteIncomeById", "incomeId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllIncomes", "Lkotlinx/coroutines/flow/Flow;", "", "getIncomeById", "getIncomesByCategory", "category", "", "getIncomesByDateRange", "startDate", "endDate", "getTotalIncome", "", "getTotalIncomeByCategory", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTotalIncomeByDateRange", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertIncome", "insertIncomes", "incomes", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateIncome", "budget-debug_debug"})
@androidx.room.Dao
public abstract interface IncomeDao {
    
    @androidx.room.Query(value = "SELECT * FROM incomes ORDER BY date DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.Income>> getAllIncomes();
    
    @androidx.room.Query(value = "SELECT * FROM incomes WHERE id = :incomeId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getIncomeById(long incomeId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.database.entities.Income> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM incomes WHERE category = :category ORDER BY date DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.Income>> getIncomesByCategory(@org.jetbrains.annotations.NotNull
    java.lang.String category);
    
    @androidx.room.Query(value = "SELECT * FROM incomes WHERE date >= :startDate AND date <= :endDate ORDER BY date DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.Income>> getIncomesByDateRange(long startDate, long endDate);
    
    @androidx.room.Query(value = "SELECT SUM(amount) FROM incomes")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getTotalIncome(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    @androidx.room.Query(value = "SELECT SUM(amount) FROM incomes WHERE date >= :startDate AND date <= :endDate")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getTotalIncomeByDateRange(long startDate, long endDate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    @androidx.room.Query(value = "SELECT SUM(amount) FROM incomes WHERE category = :category")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getTotalIncomeByCategory(@org.jetbrains.annotations.NotNull
    java.lang.String category, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertIncome(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Income income, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertIncomes(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.database.entities.Income> incomes, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateIncome(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Income income, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteIncome(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Income income, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM incomes WHERE id = :incomeId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteIncomeById(long incomeId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM incomes")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteAllIncomes(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}