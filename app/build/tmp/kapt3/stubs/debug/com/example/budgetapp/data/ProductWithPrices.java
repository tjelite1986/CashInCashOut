package com.example.budgetapp.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\t\u0010\u0014\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0003J#\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00c6\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\u0010\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\t\u0010\u001d\u001a\u00020\u001eH\u00d6\u0001J\t\u0010\u001f\u001a\u00020 H\u00d6\u0001R\u0013\u0010\b\u001a\u0004\u0018\u00010\t8F\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\f\u001a\u0004\u0018\u00010\t8F\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\t8F\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u000bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\u00a8\u0006!"}, d2 = {"Lcom/example/budgetapp/data/ProductWithPrices;", "", "product", "Lcom/example/budgetapp/database/entities/Product;", "prices", "", "Lcom/example/budgetapp/data/ProductStoreWithStore;", "(Lcom/example/budgetapp/database/entities/Product;Ljava/util/List;)V", "averagePrice", "", "getAveragePrice", "()Ljava/lang/Double;", "highestPrice", "getHighestPrice", "lowestPrice", "getLowestPrice", "getPrices", "()Ljava/util/List;", "getProduct", "()Lcom/example/budgetapp/database/entities/Product;", "component1", "component2", "copy", "equals", "", "other", "getTotalPrice", "productStore", "Lcom/example/budgetapp/database/entities/ProductStore;", "hashCode", "", "toString", "", "CashInCashOut_debug"})
public final class ProductWithPrices {
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.entities.Product product = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.example.budgetapp.data.ProductStoreWithStore> prices = null;
    
    public ProductWithPrices(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Product product, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.data.ProductStoreWithStore> prices) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.Product getProduct() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.budgetapp.data.ProductStoreWithStore> getPrices() {
        return null;
    }
    
    private final double getTotalPrice(com.example.budgetapp.database.entities.ProductStore productStore) {
        return 0.0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double getLowestPrice() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double getHighestPrice() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double getAveragePrice() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.Product component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.budgetapp.data.ProductStoreWithStore> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.data.ProductWithPrices copy(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Product product, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.data.ProductStoreWithStore> prices) {
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