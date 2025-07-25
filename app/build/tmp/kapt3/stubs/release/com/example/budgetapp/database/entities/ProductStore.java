package com.example.budgetapp.database.entities;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b&\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001Bg\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0010J\t\u0010\"\u001a\u00020\u0003H\u00c6\u0003J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\t\u0010%\u001a\u00020\u0003H\u00c6\u0003J\t\u0010&\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\'\u001a\u00020\tH\u00c6\u0003J\u0010\u0010(\u001a\u0004\u0018\u00010\u000bH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0015J\u0010\u0010)\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0012J\t\u0010*\u001a\u00020\u0003H\u00c6\u0003J\t\u0010+\u001a\u00020\u0003H\u00c6\u0003Jv\u0010,\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010-J\u0013\u0010.\u001a\u00020\t2\b\u0010/\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00100\u001a\u00020\u000bH\u00d6\u0001J\t\u00101\u001a\u000202H\u00d6\u0001R\u0015\u0010\f\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0015\u0010\n\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\n\n\u0002\u0010\u0016\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u0011\u0010\r\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0018R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0018R\u0011\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0018\u00a8\u00063"}, d2 = {"Lcom/example/budgetapp/database/entities/ProductStore;", "", "id", "", "productId", "storeId", "price", "", "hasCampaignPrice", "", "campaignQuantity", "", "campaignPrice", "lastSeen", "createdAt", "updatedAt", "(JJJDZLjava/lang/Integer;Ljava/lang/Double;JJJ)V", "getCampaignPrice", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getCampaignQuantity", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getCreatedAt", "()J", "getHasCampaignPrice", "()Z", "getId", "getLastSeen", "getPrice", "()D", "getProductId", "getStoreId", "getUpdatedAt", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JJJDZLjava/lang/Integer;Ljava/lang/Double;JJJ)Lcom/example/budgetapp/database/entities/ProductStore;", "equals", "other", "hashCode", "toString", "", "CashInCashOut_release"})
@androidx.room.Entity(tableName = "product_stores", foreignKeys = {@androidx.room.ForeignKey(entity = com.example.budgetapp.database.entities.Product.class, parentColumns = {"id"}, childColumns = {"productId"}, onDelete = 5), @androidx.room.ForeignKey(entity = com.example.budgetapp.database.entities.Store.class, parentColumns = {"id"}, childColumns = {"storeId"}, onDelete = 5)}, indices = {@androidx.room.Index(value = {"productId"}), @androidx.room.Index(value = {"storeId"}), @androidx.room.Index(value = {"productId", "storeId"}, unique = true)})
public final class ProductStore {
    @androidx.room.PrimaryKey(autoGenerate = true)
    private final long id = 0L;
    private final long productId = 0L;
    private final long storeId = 0L;
    private final double price = 0.0;
    private final boolean hasCampaignPrice = false;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Integer campaignQuantity = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Double campaignPrice = null;
    private final long lastSeen = 0L;
    private final long createdAt = 0L;
    private final long updatedAt = 0L;
    
    public ProductStore(long id, long productId, long storeId, double price, boolean hasCampaignPrice, @org.jetbrains.annotations.Nullable
    java.lang.Integer campaignQuantity, @org.jetbrains.annotations.Nullable
    java.lang.Double campaignPrice, long lastSeen, long createdAt, long updatedAt) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    public final long getProductId() {
        return 0L;
    }
    
    public final long getStoreId() {
        return 0L;
    }
    
    public final double getPrice() {
        return 0.0;
    }
    
    public final boolean getHasCampaignPrice() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getCampaignQuantity() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double getCampaignPrice() {
        return null;
    }
    
    public final long getLastSeen() {
        return 0L;
    }
    
    public final long getCreatedAt() {
        return 0L;
    }
    
    public final long getUpdatedAt() {
        return 0L;
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final long component10() {
        return 0L;
    }
    
    public final long component2() {
        return 0L;
    }
    
    public final long component3() {
        return 0L;
    }
    
    public final double component4() {
        return 0.0;
    }
    
    public final boolean component5() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double component7() {
        return null;
    }
    
    public final long component8() {
        return 0L;
    }
    
    public final long component9() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.ProductStore copy(long id, long productId, long storeId, double price, boolean hasCampaignPrice, @org.jetbrains.annotations.Nullable
    java.lang.Integer campaignQuantity, @org.jetbrains.annotations.Nullable
    java.lang.Double campaignPrice, long lastSeen, long createdAt, long updatedAt) {
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