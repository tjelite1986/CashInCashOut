package com.example.budgetapp.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0012\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000e0\rJ\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000e0\r2\u0006\u0010\u0011\u001a\u00020\u0012J\u001b\u0010\u0013\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014J\u0019\u0010\u0015\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\nH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0019\u0010\u0016\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0017"}, d2 = {"Lcom/example/budgetapp/repository/StoreRepository;", "", "storeDao", "Lcom/example/budgetapp/database/dao/StoreDao;", "productStoreDao", "Lcom/example/budgetapp/database/dao/ProductStoreDao;", "(Lcom/example/budgetapp/database/dao/StoreDao;Lcom/example/budgetapp/database/dao/ProductStoreDao;)V", "deleteStore", "", "store", "Lcom/example/budgetapp/database/entities/Store;", "(Lcom/example/budgetapp/database/entities/Store;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllStores", "Landroidx/lifecycle/LiveData;", "", "getProductsForStore", "Lcom/example/budgetapp/database/entities/ProductStore;", "storeId", "", "getStoreById", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertStore", "updateStore", "CashInCashOut_release"})
public final class StoreRepository {
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.dao.StoreDao storeDao = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.dao.ProductStoreDao productStoreDao = null;
    
    public StoreRepository(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.dao.StoreDao storeDao, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.dao.ProductStoreDao productStoreDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.example.budgetapp.database.entities.Store>> getAllStores() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getStoreById(long storeId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.database.entities.Store> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.example.budgetapp.database.entities.ProductStore>> getProductsForStore(long storeId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object insertStore(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Store store, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object updateStore(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Store store, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteStore(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Store store, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}