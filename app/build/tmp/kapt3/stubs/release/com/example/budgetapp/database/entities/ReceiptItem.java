package com.example.budgetapp.database.entities;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b1\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u00a5\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\b\b\u0002\u0010\f\u001a\u00020\u0007\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\u000e\u001a\u00020\t\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\t\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0016J\t\u0010-\u001a\u00020\u0003H\u00c6\u0003J\t\u0010.\u001a\u00020\tH\u00c6\u0003J\t\u0010/\u001a\u00020\u0010H\u00c6\u0003J\t\u00100\u001a\u00020\tH\u00c6\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u000b\u00102\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u000b\u00103\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\t\u00104\u001a\u00020\u0003H\u00c6\u0003J\t\u00105\u001a\u00020\u0003H\u00c6\u0003J\u0010\u00106\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010 J\t\u00107\u001a\u00020\u0007H\u00c6\u0003J\t\u00108\u001a\u00020\tH\u00c6\u0003J\t\u00109\u001a\u00020\tH\u00c6\u0003J\t\u0010:\u001a\u00020\tH\u00c6\u0003J\t\u0010;\u001a\u00020\u0007H\u00c6\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003J\u00b8\u0001\u0010=\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\u00072\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\t2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\u0015\u001a\u00020\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010>J\u0013\u0010?\u001a\u00020\u00102\b\u0010@\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010A\u001a\u00020BH\u00d6\u0001J\t\u0010C\u001a\u00020\u0007H\u00d6\u0001R\u0011\u0010\u0015\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0011\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u000e\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0011\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010!\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010#R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001aR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0018R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010#R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010#R\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010#R\u0011\u0010\u000b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001aR\u0011\u0010\f\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010#R\u0011\u0010\n\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001a\u00a8\u0006D"}, d2 = {"Lcom/example/budgetapp/database/entities/ReceiptItem;", "", "id", "", "receiptId", "productId", "productName", "", "quantity", "", "unitPrice", "totalPrice", "unit", "productSize", "discount", "hasDeposit", "", "depositAmount", "storeName", "storeChain", "storeCity", "createdAt", "(JJLjava/lang/Long;Ljava/lang/String;DDDLjava/lang/String;Ljava/lang/String;DZDLjava/lang/String;Ljava/lang/String;Ljava/lang/String;J)V", "getCreatedAt", "()J", "getDepositAmount", "()D", "getDiscount", "getHasDeposit", "()Z", "getId", "getProductId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getProductName", "()Ljava/lang/String;", "getProductSize", "getQuantity", "getReceiptId", "getStoreChain", "getStoreCity", "getStoreName", "getTotalPrice", "getUnit", "getUnitPrice", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JJLjava/lang/Long;Ljava/lang/String;DDDLjava/lang/String;Ljava/lang/String;DZDLjava/lang/String;Ljava/lang/String;Ljava/lang/String;J)Lcom/example/budgetapp/database/entities/ReceiptItem;", "equals", "other", "hashCode", "", "toString", "CashInCashOut_release"})
@androidx.room.Entity(tableName = "receipt_items", foreignKeys = {@androidx.room.ForeignKey(entity = com.example.budgetapp.database.entities.Receipt.class, parentColumns = {"id"}, childColumns = {"receiptId"}, onDelete = 5), @androidx.room.ForeignKey(entity = com.example.budgetapp.database.entities.Product.class, parentColumns = {"id"}, childColumns = {"productId"}, onDelete = 3)})
public final class ReceiptItem {
    @androidx.room.PrimaryKey(autoGenerate = true)
    private final long id = 0L;
    private final long receiptId = 0L;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Long productId = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String productName = null;
    private final double quantity = 0.0;
    private final double unitPrice = 0.0;
    private final double totalPrice = 0.0;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String unit = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String productSize = null;
    private final double discount = 0.0;
    private final boolean hasDeposit = false;
    private final double depositAmount = 0.0;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String storeName = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String storeChain = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String storeCity = null;
    private final long createdAt = 0L;
    
    public ReceiptItem(long id, long receiptId, @org.jetbrains.annotations.Nullable
    java.lang.Long productId, @org.jetbrains.annotations.NotNull
    java.lang.String productName, double quantity, double unitPrice, double totalPrice, @org.jetbrains.annotations.NotNull
    java.lang.String unit, @org.jetbrains.annotations.Nullable
    java.lang.String productSize, double discount, boolean hasDeposit, double depositAmount, @org.jetbrains.annotations.Nullable
    java.lang.String storeName, @org.jetbrains.annotations.Nullable
    java.lang.String storeChain, @org.jetbrains.annotations.Nullable
    java.lang.String storeCity, long createdAt) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    public final long getReceiptId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long getProductId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getProductName() {
        return null;
    }
    
    public final double getQuantity() {
        return 0.0;
    }
    
    public final double getUnitPrice() {
        return 0.0;
    }
    
    public final double getTotalPrice() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getUnit() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getProductSize() {
        return null;
    }
    
    public final double getDiscount() {
        return 0.0;
    }
    
    public final boolean getHasDeposit() {
        return false;
    }
    
    public final double getDepositAmount() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getStoreName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getStoreChain() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getStoreCity() {
        return null;
    }
    
    public final long getCreatedAt() {
        return 0L;
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final double component10() {
        return 0.0;
    }
    
    public final boolean component11() {
        return false;
    }
    
    public final double component12() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component13() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component14() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component15() {
        return null;
    }
    
    public final long component16() {
        return 0L;
    }
    
    public final long component2() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component4() {
        return null;
    }
    
    public final double component5() {
        return 0.0;
    }
    
    public final double component6() {
        return 0.0;
    }
    
    public final double component7() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component8() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.ReceiptItem copy(long id, long receiptId, @org.jetbrains.annotations.Nullable
    java.lang.Long productId, @org.jetbrains.annotations.NotNull
    java.lang.String productName, double quantity, double unitPrice, double totalPrice, @org.jetbrains.annotations.NotNull
    java.lang.String unit, @org.jetbrains.annotations.Nullable
    java.lang.String productSize, double discount, boolean hasDeposit, double depositAmount, @org.jetbrains.annotations.Nullable
    java.lang.String storeName, @org.jetbrains.annotations.Nullable
    java.lang.String storeChain, @org.jetbrains.annotations.Nullable
    java.lang.String storeCity, long createdAt) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public java.lang.String toString() {
        return null;
    }
}