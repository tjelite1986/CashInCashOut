package com.example.budgetapp.database.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u000b\bg\u0018\u00002\u00020\u0001J!\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J)\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0019\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u0019\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J\u0014\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00160\u0015H\'J\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u0016H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018J\u001b\u0010\u0019\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0011\u001a\u00020\u0012H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J\u001c\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00160\u00152\u0006\u0010\u0006\u001a\u00020\u0005H\'J\u001c\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00160\u00152\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0019\u0010\u001c\u001a\u00020\u00122\u0006\u0010\r\u001a\u00020\u000eH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u001f\u0010\u001d\u001a\u00020\f2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0016H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001fJ\u0019\u0010 \u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006!"}, d2 = {"Lcom/example/budgetapp/database/dao/StoreDao;", "", "checkDuplicateStore", "", "name", "", "chain", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkDuplicateStoreWithCity", "city", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteStore", "", "store", "Lcom/example/budgetapp/database/entities/Store;", "(Lcom/example/budgetapp/database/entities/Store;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteStoreById", "storeId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllStores", "Lkotlinx/coroutines/flow/Flow;", "", "getDistinctChains", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getStoreById", "getStoresByChain", "getStoresByName", "insertStore", "insertStores", "stores", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateStore", "CashInCashOut_release"})
@androidx.room.Dao
public abstract interface StoreDao {
    
    @androidx.room.Query(value = "SELECT * FROM stores ORDER BY name ASC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.Store>> getAllStores();
    
    @androidx.room.Query(value = "SELECT * FROM stores WHERE id = :storeId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getStoreById(long storeId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.database.entities.Store> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM stores WHERE name LIKE :name")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.Store>> getStoresByName(@org.jetbrains.annotations.NotNull
    java.lang.String name);
    
    @androidx.room.Query(value = "SELECT * FROM stores WHERE chain = :chain ORDER BY name ASC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.Store>> getStoresByChain(@org.jetbrains.annotations.NotNull
    java.lang.String chain);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertStore(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Store store, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertStores(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.database.entities.Store> stores, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateStore(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Store store, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteStore(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Store store, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM stores WHERE id = :storeId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteStoreById(long storeId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM stores WHERE name = :name AND COALESCE(chain, \'\') = :chain")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object checkDuplicateStore(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.lang.String chain, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM stores WHERE name = :name AND COALESCE(chain, \'\') = :chain AND COALESCE(city, \'\') = :city")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object checkDuplicateStoreWithCity(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.lang.String chain, @org.jetbrains.annotations.NotNull
    java.lang.String city, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT DISTINCT chain FROM stores WHERE chain IS NOT NULL ORDER BY chain ASC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getDistinctChains(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.String>> $completion);
}