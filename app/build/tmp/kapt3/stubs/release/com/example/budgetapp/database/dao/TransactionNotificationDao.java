package com.example.budgetapp.database.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J!\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ\u0019\u0010\r\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0011\u0010\u000f\u001a\u00020\u0010H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u0013H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u0013H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J#\u0010\u0015\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ\u001f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u00132\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0019\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u001f\u0010\u0018\u001a\u00020\u00032\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u0013H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001aJ!\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\u001eH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001fJ\u0019\u0010 \u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006!"}, d2 = {"Lcom/example/budgetapp/database/dao/TransactionNotificationDao;", "", "deleteNotification", "", "notification", "Lcom/example/budgetapp/database/entities/TransactionNotification;", "(Lcom/example/budgetapp/database/entities/TransactionNotification;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteNotificationForTransaction", "transactionId", "", "type", "Lcom/example/budgetapp/database/entities/TransactionType;", "(JLcom/example/budgetapp/database/entities/TransactionType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteNotificationsByType", "(Lcom/example/budgetapp/database/entities/TransactionType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveNotificationCount", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveNotifications", "", "getAllNotifications", "getNotificationForTransaction", "getNotificationsByType", "insertNotification", "insertNotifications", "notifications", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toggleNotification", "id", "enabled", "", "(JZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateNotification", "CashInCashOut_release"})
@androidx.room.Dao
public abstract interface TransactionNotificationDao {
    
    @androidx.room.Query(value = "SELECT * FROM transaction_notifications WHERE isEnabled = 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getActiveNotifications(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.database.entities.TransactionNotification>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM transaction_notifications")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getAllNotifications(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.database.entities.TransactionNotification>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM transaction_notifications WHERE transactionId = :transactionId AND transactionType = :type")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getNotificationForTransaction(long transactionId, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.TransactionType type, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.database.entities.TransactionNotification> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM transaction_notifications WHERE transactionType = :type")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getNotificationsByType(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.TransactionType type, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.database.entities.TransactionNotification>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertNotification(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.TransactionNotification notification, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertNotifications(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.database.entities.TransactionNotification> notifications, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateNotification(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.TransactionNotification notification, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteNotification(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.TransactionNotification notification, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM transaction_notifications WHERE transactionId = :transactionId AND transactionType = :type")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteNotificationForTransaction(long transactionId, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.TransactionType type, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM transaction_notifications WHERE transactionType = :type")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteNotificationsByType(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.TransactionType type, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE transaction_notifications SET isEnabled = :enabled WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object toggleNotification(long id, boolean enabled, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM transaction_notifications WHERE isEnabled = 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getActiveNotificationCount(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
}