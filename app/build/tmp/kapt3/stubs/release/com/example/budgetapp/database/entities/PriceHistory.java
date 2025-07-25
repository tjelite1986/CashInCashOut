package com.example.budgetapp.database.entities;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b%\b\u0087\b\u0018\u00002\u00020\u0001Bi\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u00a2\u0006\u0002\u0010\u0011J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010%\u001a\u0004\u0018\u00010\u000fH\u00c6\u0003J\t\u0010&\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\'\u001a\u00020\u0003H\u00c6\u0003J\t\u0010(\u001a\u00020\u0007H\u00c6\u0003J\u0010\u0010)\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0013J\u0010\u0010*\u001a\u0004\u0018\u00010\nH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0016J\t\u0010+\u001a\u00020\fH\u00c6\u0003J\t\u0010,\u001a\u00020\u0003H\u00c6\u0003J\t\u0010-\u001a\u00020\u000fH\u00c6\u0003Jx\u0010.\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u00c6\u0001\u00a2\u0006\u0002\u0010/J\u0013\u00100\u001a\u00020\f2\b\u00101\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00102\u001a\u00020\nH\u00d6\u0001J\t\u00103\u001a\u00020\u000fH\u00d6\u0001R\u0015\u0010\b\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0015\u0010\t\u001a\u0004\u0018\u00010\n\u00a2\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u001bR\u0011\u0010\r\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001bR\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001dR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001b\u00a8\u00064"}, d2 = {"Lcom/example/budgetapp/database/entities/PriceHistory;", "", "id", "", "productId", "storeId", "price", "", "campaignPrice", "campaignQuantity", "", "hasCampaignPrice", "", "recordedAt", "source", "", "notes", "(JJJDLjava/lang/Double;Ljava/lang/Integer;ZJLjava/lang/String;Ljava/lang/String;)V", "getCampaignPrice", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getCampaignQuantity", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getHasCampaignPrice", "()Z", "getId", "()J", "getNotes", "()Ljava/lang/String;", "getPrice", "()D", "getProductId", "getRecordedAt", "getSource", "getStoreId", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JJJDLjava/lang/Double;Ljava/lang/Integer;ZJLjava/lang/String;Ljava/lang/String;)Lcom/example/budgetapp/database/entities/PriceHistory;", "equals", "other", "hashCode", "toString", "CashInCashOut_release"})
@androidx.room.Entity(tableName = "price_history", foreignKeys = {@androidx.room.ForeignKey(entity = com.example.budgetapp.database.entities.Product.class, parentColumns = {"id"}, childColumns = {"productId"}, onDelete = 5), @androidx.room.ForeignKey(entity = com.example.budgetapp.database.entities.Store.class, parentColumns = {"id"}, childColumns = {"storeId"}, onDelete = 5)}, indices = {@androidx.room.Index(value = {"productId"}), @androidx.room.Index(value = {"storeId"}), @androidx.room.Index(value = {"recordedAt"}), @androidx.room.Index(value = {"productId", "storeId", "recordedAt"})})
public final class PriceHistory {
    @androidx.room.PrimaryKey(autoGenerate = true)
    private final long id = 0L;
    private final long productId = 0L;
    private final long storeId = 0L;
    private final double price = 0.0;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Double campaignPrice = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Integer campaignQuantity = null;
    private final boolean hasCampaignPrice = false;
    private final long recordedAt = 0L;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String source = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String notes = null;
    
    public PriceHistory(long id, long productId, long storeId, double price, @org.jetbrains.annotations.Nullable
    java.lang.Double campaignPrice, @org.jetbrains.annotations.Nullable
    java.lang.Integer campaignQuantity, boolean hasCampaignPrice, long recordedAt, @org.jetbrains.annotations.NotNull
    java.lang.String source, @org.jetbrains.annotations.Nullable
    java.lang.String notes) {
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
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double getCampaignPrice() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getCampaignQuantity() {
        return null;
    }
    
    public final boolean getHasCampaignPrice() {
        return false;
    }
    
    public final long getRecordedAt() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getSource() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getNotes() {
        return null;
    }
    
    public final long component1() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component10() {
        return null;
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
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component6() {
        return null;
    }
    
    public final boolean component7() {
        return false;
    }
    
    public final long component8() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.PriceHistory copy(long id, long productId, long storeId, double price, @org.jetbrains.annotations.Nullable
    java.lang.Double campaignPrice, @org.jetbrains.annotations.Nullable
    java.lang.Integer campaignQuantity, boolean hasCampaignPrice, long recordedAt, @org.jetbrains.annotations.NotNull
    java.lang.String source, @org.jetbrains.annotations.Nullable
    java.lang.String notes) {
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