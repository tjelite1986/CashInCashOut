package com.example.budgetapp.database.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u000b\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0019\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000f0\u000eH\'J\u001b\u0010\u0010\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000f0\u000e2\u0006\u0010\u0012\u001a\u00020\tH\'J\u001f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u000f2\u0006\u0010\f\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000f0\u000e2\u0006\u0010\f\u001a\u00020\tH\'J\u0019\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u001f\u0010\u0016\u001a\u00020\u00032\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u000fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018J\u0019\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u001a"}, d2 = {"Lcom/example/budgetapp/database/dao/ReceiptItemDao;", "", "deleteReceiptItem", "", "receiptItem", "Lcom/example/budgetapp/database/entities/ReceiptItem;", "(Lcom/example/budgetapp/database/entities/ReceiptItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteReceiptItemById", "itemId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteReceiptItemsByReceiptId", "receiptId", "getAllReceiptItems", "Lkotlinx/coroutines/flow/Flow;", "", "getReceiptItemById", "getReceiptItemsByProductId", "productId", "getReceiptItemsByReceiptId", "getReceiptItemsByReceiptIdFlow", "insertReceiptItem", "insertReceiptItems", "receiptItems", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateReceiptItem", "CashInCashOut_debug"})
@androidx.room.Dao
public abstract interface ReceiptItemDao {
    
    @androidx.room.Query(value = "SELECT * FROM receipt_items ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.ReceiptItem>> getAllReceiptItems();
    
    @androidx.room.Query(value = "SELECT * FROM receipt_items WHERE id = :itemId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getReceiptItemById(long itemId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.database.entities.ReceiptItem> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM receipt_items WHERE receiptId = :receiptId ORDER BY createdAt ASC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getReceiptItemsByReceiptId(long receiptId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.database.entities.ReceiptItem>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM receipt_items WHERE receiptId = :receiptId ORDER BY createdAt ASC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.ReceiptItem>> getReceiptItemsByReceiptIdFlow(long receiptId);
    
    @androidx.room.Query(value = "SELECT * FROM receipt_items WHERE productId = :productId ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.ReceiptItem>> getReceiptItemsByProductId(long productId);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertReceiptItem(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.ReceiptItem receiptItem, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertReceiptItems(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.database.entities.ReceiptItem> receiptItems, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateReceiptItem(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.ReceiptItem receiptItem, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteReceiptItem(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.ReceiptItem receiptItem, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM receipt_items WHERE id = :itemId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteReceiptItemById(long itemId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM receipt_items WHERE receiptId = :receiptId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteReceiptItemsByReceiptId(long receiptId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}