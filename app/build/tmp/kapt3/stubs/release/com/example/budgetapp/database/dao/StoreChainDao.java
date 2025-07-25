package com.example.budgetapp.database.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J#\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J#\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\u0019\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ\u0011\u0010\r\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0014\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00110\u0010H\'J\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u0011H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0014\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00110\u0010H\'J\u001b\u0010\u0015\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0016J\u001b\u0010\u0017\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0018\u001a\u00020\u0013H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019J\u0011\u0010\u001a\u001a\u00020\u001bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0019\u0010\u001c\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ\u001f\u0010\u001d\u001a\u00020\u00032\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0011H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001fJ\u0019\u0010 \u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006!"}, d2 = {"Lcom/example/budgetapp/database/dao/StoreChainDao;", "", "activateChain", "", "id", "", "timestamp", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deactivateChain", "deleteChain", "chain", "Lcom/example/budgetapp/database/entities/StoreChain;", "(Lcom/example/budgetapp/database/entities/StoreChain;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteCustomChains", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllActiveChains", "Lkotlinx/coroutines/flow/Flow;", "", "getAllChainNames", "", "getAllChains", "getChainById", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getChainByName", "name", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDefaultChainsCount", "", "insertChain", "insertChains", "chains", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateChain", "CashInCashOut_release"})
@androidx.room.Dao
public abstract interface StoreChainDao {
    
    @androidx.room.Query(value = "SELECT * FROM store_chains WHERE isActive = 1 ORDER BY isDefault DESC, name ASC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.StoreChain>> getAllActiveChains();
    
    @androidx.room.Query(value = "SELECT * FROM store_chains ORDER BY isDefault DESC, name ASC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.StoreChain>> getAllChains();
    
    @androidx.room.Query(value = "SELECT * FROM store_chains WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getChainById(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.database.entities.StoreChain> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM store_chains WHERE name = :name AND isActive = 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getChainByName(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.database.entities.StoreChain> $completion);
    
    @androidx.room.Query(value = "SELECT name FROM store_chains WHERE isActive = 1 ORDER BY isDefault DESC, name ASC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getAllChainNames(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.String>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertChain(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.StoreChain chain, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertChains(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.database.entities.StoreChain> chains, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateChain(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.StoreChain chain, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteChain(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.StoreChain chain, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE store_chains SET isActive = 0, updatedAt = :timestamp WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deactivateChain(long id, long timestamp, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE store_chains SET isActive = 1, updatedAt = :timestamp WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object activateChain(long id, long timestamp, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM store_chains WHERE isDefault = 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getDefaultChainsCount(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "DELETE FROM store_chains WHERE isDefault = 0")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteCustomChains(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}