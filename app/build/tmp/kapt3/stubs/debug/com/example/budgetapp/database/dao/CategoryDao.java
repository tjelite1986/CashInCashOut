package com.example.budgetapp.database.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\fH\'J\u001b\u0010\u000e\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0019\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0011"}, d2 = {"Lcom/example/budgetapp/database/dao/CategoryDao;", "", "deleteCategory", "", "category", "Lcom/example/budgetapp/database/entities/Category;", "(Lcom/example/budgetapp/database/entities/Category;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteCategoryById", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllCategories", "Lkotlinx/coroutines/flow/Flow;", "", "getCategoryById", "insertCategory", "updateCategory", "budget-debug_debug"})
@androidx.room.Dao
public abstract interface CategoryDao {
    
    @androidx.room.Query(value = "SELECT * FROM categories ORDER BY name ASC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.Category>> getAllCategories();
    
    @androidx.room.Query(value = "SELECT * FROM categories WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getCategoryById(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.database.entities.Category> $completion);
    
    @androidx.room.Insert
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertCategory(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Category category, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateCategory(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Category category, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteCategory(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Category category, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM categories WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteCategoryById(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}