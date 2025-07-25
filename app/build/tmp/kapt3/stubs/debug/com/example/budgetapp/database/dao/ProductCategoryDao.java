package com.example.budgetapp.database.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0019\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u0014\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00120\u0011H\'J\u001b\u0010\u0013\u001a\u0004\u0018\u00010\n2\u0006\u0010\r\u001a\u00020\u000eH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u0019\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u000eH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u0019\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0019\u0010\u0017\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0018"}, d2 = {"Lcom/example/budgetapp/database/dao/ProductCategoryDao;", "", "checkDuplicateProductCategory", "", "name", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteProductCategory", "", "category", "Lcom/example/budgetapp/database/entities/ProductCategory;", "(Lcom/example/budgetapp/database/entities/ProductCategory;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteProductCategoryById", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllProductCategories", "Lkotlinx/coroutines/flow/Flow;", "", "getProductCategoryById", "getProductCountForCategory", "productCategoryId", "insertProductCategory", "updateProductCategory", "CashInCashOut_debug"})
@androidx.room.Dao
public abstract interface ProductCategoryDao {
    
    @androidx.room.Query(value = "SELECT * FROM product_categories ORDER BY isDefault DESC, name ASC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.ProductCategory>> getAllProductCategories();
    
    @androidx.room.Query(value = "SELECT * FROM product_categories WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getProductCategoryById(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.database.entities.ProductCategory> $completion);
    
    @androidx.room.Insert
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertProductCategory(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.ProductCategory category, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateProductCategory(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.ProductCategory category, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteProductCategory(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.ProductCategory category, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM product_categories WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteProductCategoryById(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM product_categories WHERE name = :name")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object checkDuplicateProductCategory(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM products WHERE productCategoryId = :productCategoryId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getProductCountForCategory(long productCategoryId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
}