package com.example.budgetapp.database.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\b\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\fH\'J\u001b\u0010\u000e\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\f2\u0006\u0010\u0010\u001a\u00020\u0011H\'J\u001c\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\f2\u0006\u0010\u0013\u001a\u00020\u0011H\'J\u0019\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u001f\u0010\u0015\u001a\u00020\u00032\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\rH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J\u0019\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0019"}, d2 = {"Lcom/example/budgetapp/database/dao/StoreDao;", "", "deleteStore", "", "store", "Lcom/example/budgetapp/database/entities/Store;", "(Lcom/example/budgetapp/database/entities/Store;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteStoreById", "storeId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllStores", "Lkotlinx/coroutines/flow/Flow;", "", "getStoreById", "getStoresByChain", "chain", "", "getStoresByName", "name", "insertStore", "insertStores", "stores", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateStore", "budget-debug_debug"})
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
}