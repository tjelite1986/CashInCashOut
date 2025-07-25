package com.example.budgetapp.database.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\t\bg\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u0019\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u0019\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u0019\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J\u0014\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00150\u0014H\'J\u0014\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00150\u0014H\'J\u001c\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00150\u00142\u0006\u0010\u0006\u001a\u00020\u0007H\'J\u001b\u0010\u0018\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u000b\u001a\u00020\fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u001c\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00150\u00142\u0006\u0010\u001a\u001a\u00020\fH\'J\u0019\u0010\u001b\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u0019\u0010\u001c\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0011H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J\u0019\u0010\u001d\u001a\u00020\n2\u0006\u0010\u0010\u001a\u00020\u0011H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001e"}, d2 = {"Lcom/example/budgetapp/database/dao/CategoryDao;", "", "checkDuplicateCategory", "", "name", "", "type", "Lcom/example/budgetapp/database/entities/CategoryType;", "(Ljava/lang/String;Lcom/example/budgetapp/database/entities/CategoryType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deactivateCategory", "", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "decrementTransactionCount", "deleteCategory", "category", "Lcom/example/budgetapp/database/entities/Category;", "(Lcom/example/budgetapp/database/entities/Category;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllActiveCategories", "Lkotlinx/coroutines/flow/Flow;", "", "getAllCategories", "getCategoriesByType", "getCategoryById", "getSubcategories", "parentId", "incrementTransactionCount", "insertCategory", "updateCategory", "CashInCashOut_debug"})
@androidx.room.Dao
public abstract interface CategoryDao {
    
    @androidx.room.Query(value = "SELECT * FROM categories WHERE isActive = 1 ORDER BY sortOrder ASC, name ASC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.Category>> getAllActiveCategories();
    
    @androidx.room.Query(value = "SELECT * FROM categories WHERE type = :type AND isActive = 1 AND parentCategoryId IS NULL ORDER BY sortOrder ASC, name ASC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.Category>> getCategoriesByType(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.CategoryType type);
    
    @androidx.room.Query(value = "SELECT * FROM categories WHERE parentCategoryId = :parentId AND isActive = 1 ORDER BY sortOrder ASC, name ASC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.Category>> getSubcategories(long parentId);
    
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
    
    @androidx.room.Query(value = "UPDATE categories SET isActive = 0 WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deactivateCategory(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE categories SET transactionCount = transactionCount + 1 WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object incrementTransactionCount(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE categories SET transactionCount = transactionCount - 1 WHERE id = :id AND transactionCount > 0")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object decrementTransactionCount(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM categories WHERE name = :name AND type = :type AND isActive = 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object checkDuplicateCategory(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.CategoryType type, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM categories ORDER BY name ASC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.Category>> getAllCategories();
}