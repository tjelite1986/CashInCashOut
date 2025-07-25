package com.example.budgetapp.database.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0006\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\'J\u0014\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000f0\fH\'J\u000e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\fH\'J\u001b\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0018\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\f2\u0006\u0010\b\u001a\u00020\tH\'J\u001c\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000f0\f2\u0006\u0010\u0014\u001a\u00020\u0015H\'J\u001f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u000f2\u0006\u0010\u0014\u001a\u00020\u0015H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J\u0019\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J5\u0010\u001a\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u001b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u001c\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001dJ+\u0010\u001e\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010\u001c\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010!\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\""}, d2 = {"Lcom/example/budgetapp/database/dao/ShoppingListDao;", "", "deleteShoppingList", "", "shoppingList", "Lcom/example/budgetapp/database/entities/ShoppingList;", "(Lcom/example/budgetapp/database/entities/ShoppingList;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteShoppingListById", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveShoppingListsCount", "Landroidx/lifecycle/LiveData;", "", "getAllShoppingLists", "", "getCompletedShoppingListsCount", "getShoppingListById", "getShoppingListByIdLiveData", "getShoppingListsByStatus", "isCompleted", "", "getShoppingListsByStatusSuspend", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertShoppingList", "updateShoppingList", "updateShoppingListStatus", "completedAt", "updatedAt", "(JZLjava/lang/Long;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateShoppingListTotalCost", "totalCost", "", "(JDJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "CashInCashOut_release"})
@androidx.room.Dao
public abstract interface ShoppingListDao {
    
    @androidx.room.Query(value = "SELECT * FROM shopping_lists ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<java.util.List<com.example.budgetapp.database.entities.ShoppingList>> getAllShoppingLists();
    
    @androidx.room.Query(value = "SELECT * FROM shopping_lists WHERE isCompleted = :isCompleted ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<java.util.List<com.example.budgetapp.database.entities.ShoppingList>> getShoppingListsByStatus(boolean isCompleted);
    
    @androidx.room.Query(value = "SELECT * FROM shopping_lists WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getShoppingListById(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.database.entities.ShoppingList> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM shopping_lists WHERE id = :id")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<com.example.budgetapp.database.entities.ShoppingList> getShoppingListByIdLiveData(long id);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertShoppingList(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.ShoppingList shoppingList, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateShoppingList(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.ShoppingList shoppingList, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteShoppingList(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.ShoppingList shoppingList, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM shopping_lists WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteShoppingListById(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE shopping_lists SET isCompleted = :isCompleted, completedAt = :completedAt, updatedAt = :updatedAt WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateShoppingListStatus(long id, boolean isCompleted, @org.jetbrains.annotations.Nullable
    java.lang.Long completedAt, long updatedAt, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE shopping_lists SET totalEstimatedCost = :totalCost, updatedAt = :updatedAt WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateShoppingListTotalCost(long id, double totalCost, long updatedAt, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM shopping_lists WHERE isCompleted = 0")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<java.lang.Integer> getActiveShoppingListsCount();
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM shopping_lists WHERE isCompleted = 1")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<java.lang.Integer> getCompletedShoppingListsCount();
    
    @androidx.room.Query(value = "SELECT * FROM shopping_lists WHERE isCompleted = :isCompleted ORDER BY createdAt DESC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getShoppingListsByStatusSuspend(boolean isCompleted, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.database.entities.ShoppingList>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}