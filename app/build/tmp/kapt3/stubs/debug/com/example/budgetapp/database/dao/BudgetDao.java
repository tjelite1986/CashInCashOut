package com.example.budgetapp.database.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J#\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u0019\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ\u0019\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ#\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J\u0014\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00160\u0015H\'J\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0016H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018J\u0014\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00160\u0015H\'J\u001b\u0010\u001a\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0014\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00160\u0015H\'J\u001b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u0012\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001eJ\u0019\u0010\u001f\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ\u0019\u0010 \u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006!"}, d2 = {"Lcom/example/budgetapp/database/dao/BudgetDao;", "", "deactivateBudget", "", "id", "", "timestamp", "", "(IJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteBudget", "budget", "Lcom/example/budgetapp/database/entities/Budget;", "(Lcom/example/budgetapp/database/entities/Budget;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteBudgetById", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveBudgetForCategory", "categoryName", "", "currentDate", "(Ljava/lang/String;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllActiveBudgets", "Lkotlinx/coroutines/flow/Flow;", "", "getAllActiveBudgetsSnapshot", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllBudgets", "getBudgetById", "getBudgetCategories", "getTotalActiveBudgetAmount", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertBudget", "updateBudget", "CashInCashOut_debug"})
@androidx.room.Dao
public abstract interface BudgetDao {
    
    @androidx.room.Query(value = "SELECT * FROM budgets WHERE isActive = 1 ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.Budget>> getAllActiveBudgets();
    
    @androidx.room.Query(value = "SELECT * FROM budgets ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.Budget>> getAllBudgets();
    
    @androidx.room.Query(value = "SELECT * FROM budgets WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getBudgetById(int id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.database.entities.Budget> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM budgets WHERE categoryName = :categoryName AND isActive = 1 AND startDate <= :currentDate AND endDate >= :currentDate")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getActiveBudgetForCategory(@org.jetbrains.annotations.NotNull
    java.lang.String categoryName, long currentDate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.database.entities.Budget> $completion);
    
    @androidx.room.Query(value = "SELECT DISTINCT categoryName FROM budgets WHERE isActive = 1")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<java.lang.String>> getBudgetCategories();
    
    @androidx.room.Insert
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertBudget(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Budget budget, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateBudget(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Budget budget, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteBudget(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Budget budget, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE budgets SET isActive = 0, updatedAt = :timestamp WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deactivateBudget(int id, long timestamp, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM budgets WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteBudgetById(int id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT SUM(budgetAmount) FROM budgets WHERE isActive = 1 AND startDate <= :currentDate AND endDate >= :currentDate")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getTotalActiveBudgetAmount(long currentDate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM budgets WHERE isActive = 1 ORDER BY createdAt DESC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getAllActiveBudgetsSnapshot(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.database.entities.Budget>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}