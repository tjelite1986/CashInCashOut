package com.example.budgetapp.database.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\t\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\fH\'J\u001b\u0010\u000e\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000f\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u001b\u0010\u0010\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ$\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\f2\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\tH\'J\u0019\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0016"}, d2 = {"Lcom/example/budgetapp/database/dao/ReceiptDao;", "", "deleteReceipt", "", "receipt", "Lcom/example/budgetapp/database/entities/Receipt;", "(Lcom/example/budgetapp/database/entities/Receipt;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteReceiptById", "receiptId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllReceipts", "Lkotlinx/coroutines/flow/Flow;", "", "getReceiptByExpenseId", "expenseId", "getReceiptById", "getReceiptsByDateRange", "startDate", "endDate", "insertReceipt", "updateReceipt", "CashInCashOut_release"})
@androidx.room.Dao
public abstract interface ReceiptDao {
    
    @androidx.room.Query(value = "SELECT * FROM receipts ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.Receipt>> getAllReceipts();
    
    @androidx.room.Query(value = "SELECT * FROM receipts WHERE id = :receiptId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getReceiptById(long receiptId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.database.entities.Receipt> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM receipts WHERE expenseId = :expenseId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getReceiptByExpenseId(long expenseId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.database.entities.Receipt> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM receipts WHERE createdAt >= :startDate AND createdAt <= :endDate ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.Receipt>> getReceiptsByDateRange(long startDate, long endDate);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertReceipt(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Receipt receipt, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateReceipt(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Receipt receipt, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteReceipt(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Receipt receipt, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM receipts WHERE id = :receiptId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteReceiptById(long receiptId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}