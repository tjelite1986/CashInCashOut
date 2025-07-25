package com.example.budgetapp.database.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001J\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ#\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000e\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000f"}, d2 = {"Lcom/example/budgetapp/database/dao/AppUsageDao;", "", "getAppUsage", "Lcom/example/budgetapp/database/entities/AppUsage;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLastOpenedTime", "", "insertOrUpdateAppUsage", "", "appUsage", "(Lcom/example/budgetapp/database/entities/AppUsage;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateLastOpened", "lastOpened", "updatedAt", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "CashInCashOut_debug"})
@androidx.room.Dao
public abstract interface AppUsageDao {
    
    @androidx.room.Query(value = "SELECT * FROM app_usage WHERE id = 1 LIMIT 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getAppUsage(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.database.entities.AppUsage> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertOrUpdateAppUsage(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.AppUsage appUsage, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE app_usage SET lastOpenedAt = :lastOpened, updatedAt = :updatedAt WHERE id = 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateLastOpened(long lastOpened, long updatedAt, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT lastOpenedAt FROM app_usage WHERE id = 1 LIMIT 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getLastOpenedTime(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}