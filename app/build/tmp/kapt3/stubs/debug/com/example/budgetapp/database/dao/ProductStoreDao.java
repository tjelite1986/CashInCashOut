package com.example.budgetapp.database.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0006\n\u0002\b\f\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ!\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0014\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00110\u0010H\'J\u001b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u001b\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u001b\u0010\u0015\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00110\u00102\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u001c\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00110\u00102\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u001c\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00110\u00102\u0006\u0010\b\u001a\u00020\u0005H\'J#\u0010\u0019\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0019\u0010\u001a\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ\u001f\u0010\u001b\u001a\u00020\u00032\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0011H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001dJ\u0019\u0010\u001e\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001f"}, d2 = {"Lcom/example/budgetapp/database/dao/ProductStoreDao;", "", "deleteAllPricesForProduct", "", "productId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllPricesForStore", "storeId", "deleteProductStore", "productStore", "Lcom/example/budgetapp/database/entities/ProductStore;", "(Lcom/example/budgetapp/database/entities/ProductStore;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteProductStorePrice", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllProductStores", "Lkotlinx/coroutines/flow/Flow;", "", "getAveragePrice", "", "getHighestPrice", "getLowestPrice", "getPricesForProduct", "getPricesForProductWithStore", "getPricesForStore", "getProductStorePrice", "insertProductStore", "insertProductStores", "productStores", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateProductStore", "CashInCashOut_debug"})
@androidx.room.Dao
public abstract interface ProductStoreDao {
    
    @androidx.room.Query(value = "SELECT * FROM product_stores")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.ProductStore>> getAllProductStores();
    
    @androidx.room.Query(value = "SELECT * FROM product_stores WHERE productId = :productId ORDER BY price ASC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.ProductStore>> getPricesForProduct(long productId);
    
    @androidx.room.Query(value = "SELECT * FROM product_stores WHERE storeId = :storeId ORDER BY price ASC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.ProductStore>> getPricesForStore(long storeId);
    
    @androidx.room.Query(value = "SELECT * FROM product_stores WHERE productId = :productId AND storeId = :storeId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getProductStorePrice(long productId, long storeId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.database.entities.ProductStore> $completion);
    
    @androidx.room.Query(value = "\n        SELECT ps.* FROM product_stores ps\n        INNER JOIN stores s ON ps.storeId = s.id\n        WHERE ps.productId = :productId\n        ORDER BY ps.price ASC\n    ")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.ProductStore>> getPricesForProductWithStore(long productId);
    
    @androidx.room.Query(value = "\n        SELECT MIN(price) FROM product_stores \n        WHERE productId = :productId\n    ")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getLowestPrice(long productId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    @androidx.room.Query(value = "\n        SELECT MAX(price) FROM product_stores \n        WHERE productId = :productId\n    ")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getHighestPrice(long productId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    @androidx.room.Query(value = "\n        SELECT AVG(price) FROM product_stores \n        WHERE productId = :productId\n    ")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getAveragePrice(long productId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertProductStore(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.ProductStore productStore, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertProductStores(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.database.entities.ProductStore> productStores, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateProductStore(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.ProductStore productStore, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteProductStore(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.ProductStore productStore, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM product_stores WHERE productId = :productId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteAllPricesForProduct(long productId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM product_stores WHERE storeId = :storeId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteAllPricesForStore(long storeId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM product_stores WHERE productId = :productId AND storeId = :storeId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteProductStorePrice(long productId, long storeId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}