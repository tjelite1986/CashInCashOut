package com.example.budgetapp.database.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\f\bg\u0018\u00002\u00020\u0001:\u0003$%&J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\fH\'J\u001b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u001b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0010\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u001b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0010\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u001b\u0010\u0015\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0016\u001a\u00020\u0017H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018J\u001b\u0010\u0019\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0011\u0010\u001a\u001a\u00020\u001bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001cJ\u001f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000f0\r2\u0006\u0010\u0010\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u001c\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\f2\u0006\u0010\u001f\u001a\u00020\tH\'J\u0019\u0010 \u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\f2\u0006\u0010\"\u001a\u00020\u0017H\'J\u0019\u0010#\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\'"}, d2 = {"Lcom/example/budgetapp/database/dao/ProductDao;", "", "deleteProduct", "", "product", "Lcom/example/budgetapp/database/entities/Product;", "(Lcom/example/budgetapp/database/entities/Product;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteProductById", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllProducts", "Lkotlinx/coroutines/flow/Flow;", "", "getCheapestStoreForProduct", "Lcom/example/budgetapp/database/dao/ProductDao$ProductStoreWithPrice;", "productId", "getPriceStats", "Lcom/example/budgetapp/database/dao/ProductDao$PriceStats;", "getPriceStatsWithCampaignAndDeposit", "Lcom/example/budgetapp/database/dao/ProductDao$PriceStatsWithDeposit;", "getProductByBarcode", "barcode", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getProductById", "getProductCount", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getProductStoresWithPrices", "getProductsByCategory", "productCategoryId", "insertProduct", "searchProducts", "searchQuery", "updateProduct", "PriceStats", "PriceStatsWithDeposit", "ProductStoreWithPrice", "CashInCashOut_debug"})
@androidx.room.Dao
public abstract interface ProductDao {
    
    @androidx.room.Query(value = "SELECT * FROM products ORDER BY name ASC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.Product>> getAllProducts();
    
    @androidx.room.Query(value = "SELECT * FROM products WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getProductById(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.database.entities.Product> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM products WHERE barcode = :barcode LIMIT 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getProductByBarcode(@org.jetbrains.annotations.NotNull
    java.lang.String barcode, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.database.entities.Product> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM products WHERE productCategoryId = :productCategoryId ORDER BY name ASC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.Product>> getProductsByCategory(long productCategoryId);
    
    @androidx.room.Query(value = "SELECT * FROM products WHERE name LIKE \'%\' || :searchQuery || \'%\' OR productName LIKE \'%\' || :searchQuery || \'%\' ORDER BY name ASC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.Product>> searchProducts(@org.jetbrains.annotations.NotNull
    java.lang.String searchQuery);
    
    @androidx.room.Query(value = "\n        SELECT MIN(ps.price) as minPrice, MAX(ps.price) as maxPrice, AVG(ps.price) as avgPrice\n        FROM product_stores ps\n        WHERE ps.productId = :productId\n    ")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getPriceStats(long productId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.database.dao.ProductDao.PriceStats> $completion);
    
    @androidx.room.Query(value = "\n        SELECT \n            MIN(CASE \n                WHEN ps.hasCampaignPrice THEN COALESCE(ps.campaignPrice, ps.price)\n                ELSE ps.price \n            END) as minPrice,\n            MAX(CASE \n                WHEN ps.hasCampaignPrice THEN COALESCE(ps.campaignPrice, ps.price)\n                ELSE ps.price \n            END) as maxPrice,\n            AVG(CASE \n                WHEN ps.hasCampaignPrice THEN COALESCE(ps.campaignPrice, ps.price)\n                ELSE ps.price \n            END) as avgPrice,\n            p.hasDeposit,\n            p.depositAmount\n        FROM product_stores ps\n        INNER JOIN products p ON ps.productId = p.id\n        WHERE ps.productId = :productId\n        GROUP BY p.hasDeposit, p.depositAmount\n    ")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getPriceStatsWithCampaignAndDeposit(long productId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.database.dao.ProductDao.PriceStatsWithDeposit> $completion);
    
    @androidx.room.Query(value = "\n        SELECT \n            ps.*,\n            s.name as store_name,\n            s.address as store_address,\n            (CASE \n                WHEN ps.hasCampaignPrice THEN COALESCE(ps.campaignPrice, ps.price)\n                ELSE ps.price \n            END) as effective_price,\n            p.hasDeposit,\n            p.depositAmount\n        FROM product_stores ps\n        INNER JOIN stores s ON ps.storeId = s.id\n        INNER JOIN products p ON ps.productId = p.id\n        WHERE ps.productId = :productId\n        ORDER BY effective_price ASC\n    ")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getProductStoresWithPrices(long productId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.database.dao.ProductDao.ProductStoreWithPrice>> $completion);
    
    @androidx.room.Query(value = "\n        SELECT \n            ps.*,\n            s.name as store_name,\n            s.address as store_address,\n            (CASE \n                WHEN ps.hasCampaignPrice THEN COALESCE(ps.campaignPrice, ps.price)\n                ELSE ps.price \n            END) as effective_price,\n            p.hasDeposit,\n            p.depositAmount\n        FROM product_stores ps\n        INNER JOIN stores s ON ps.storeId = s.id\n        INNER JOIN products p ON ps.productId = p.id\n        WHERE ps.productId = :productId\n        ORDER BY effective_price ASC\n        LIMIT 1\n    ")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getCheapestStoreForProduct(long productId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.database.dao.ProductDao.ProductStoreWithPrice> $completion);
    
    @androidx.room.Insert
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertProduct(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Product product, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateProduct(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Product product, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteProduct(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Product product, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM products WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteProductById(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM products")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getProductCount(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\f\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\r\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\bJ2\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\t\u001a\u0004\b\n\u0010\bR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u000b\u0010\b\u00a8\u0006\u0018"}, d2 = {"Lcom/example/budgetapp/database/dao/ProductDao$PriceStats;", "", "minPrice", "", "maxPrice", "avgPrice", "(Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;)V", "getAvgPrice", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getMaxPrice", "getMinPrice", "component1", "component2", "component3", "copy", "(Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;)Lcom/example/budgetapp/database/dao/ProductDao$PriceStats;", "equals", "", "other", "hashCode", "", "toString", "", "CashInCashOut_debug"})
    public static final class PriceStats {
        @org.jetbrains.annotations.Nullable
        private final java.lang.Double minPrice = null;
        @org.jetbrains.annotations.Nullable
        private final java.lang.Double maxPrice = null;
        @org.jetbrains.annotations.Nullable
        private final java.lang.Double avgPrice = null;
        
        public PriceStats(@org.jetbrains.annotations.Nullable
        java.lang.Double minPrice, @org.jetbrains.annotations.Nullable
        java.lang.Double maxPrice, @org.jetbrains.annotations.Nullable
        java.lang.Double avgPrice) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.Double getMinPrice() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.Double getMaxPrice() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.Double getAvgPrice() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.Double component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.Double component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.Double component3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.budgetapp.database.dao.ProductDao.PriceStats copy(@org.jetbrains.annotations.Nullable
        java.lang.Double minPrice, @org.jetbrains.annotations.Nullable
        java.lang.Double maxPrice, @org.jetbrains.annotations.Nullable
        java.lang.Double avgPrice) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\tJ\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000bJ\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000bJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0007H\u00c6\u0003J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u000bJH\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u00072\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\r\u0010\u001b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u000bJ\r\u0010\u001c\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u000bJ\r\u0010\u001d\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u001e\u001a\u00020\u001fH\u00d6\u0001J\t\u0010 \u001a\u00020!H\u00d6\u0001R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0015\u0010\b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\f\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u0010\u0010\u000bR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u0011\u0010\u000b\u00a8\u0006\""}, d2 = {"Lcom/example/budgetapp/database/dao/ProductDao$PriceStatsWithDeposit;", "", "minPrice", "", "maxPrice", "avgPrice", "hasDeposit", "", "depositAmount", "(Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;ZLjava/lang/Double;)V", "getAvgPrice", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getDepositAmount", "getHasDeposit", "()Z", "getMaxPrice", "getMinPrice", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;ZLjava/lang/Double;)Lcom/example/budgetapp/database/dao/ProductDao$PriceStatsWithDeposit;", "equals", "other", "getTotalAvgPrice", "getTotalMaxPrice", "getTotalMinPrice", "hashCode", "", "toString", "", "CashInCashOut_debug"})
    public static final class PriceStatsWithDeposit {
        @org.jetbrains.annotations.Nullable
        private final java.lang.Double minPrice = null;
        @org.jetbrains.annotations.Nullable
        private final java.lang.Double maxPrice = null;
        @org.jetbrains.annotations.Nullable
        private final java.lang.Double avgPrice = null;
        private final boolean hasDeposit = false;
        @org.jetbrains.annotations.Nullable
        private final java.lang.Double depositAmount = null;
        
        public PriceStatsWithDeposit(@org.jetbrains.annotations.Nullable
        java.lang.Double minPrice, @org.jetbrains.annotations.Nullable
        java.lang.Double maxPrice, @org.jetbrains.annotations.Nullable
        java.lang.Double avgPrice, boolean hasDeposit, @org.jetbrains.annotations.Nullable
        java.lang.Double depositAmount) {
            super();
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.Double getMinPrice() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.Double getMaxPrice() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.Double getAvgPrice() {
            return null;
        }
        
        public final boolean getHasDeposit() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.Double getDepositAmount() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.Double getTotalAvgPrice() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.Double getTotalMinPrice() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.Double getTotalMaxPrice() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.Double component1() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.Double component2() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.Double component3() {
            return null;
        }
        
        public final boolean component4() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.Double component5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.budgetapp.database.dao.ProductDao.PriceStatsWithDeposit copy(@org.jetbrains.annotations.Nullable
        java.lang.Double minPrice, @org.jetbrains.annotations.Nullable
        java.lang.Double maxPrice, @org.jetbrains.annotations.Nullable
        java.lang.Double avgPrice, boolean hasDeposit, @org.jetbrains.annotations.Nullable
        java.lang.Double depositAmount) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b4\b\u0086\b\u0018\u00002\u00020\u0001B\u0085\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\u0006\u0010\u0013\u001a\u00020\u0007\u0012\u0006\u0010\u0014\u001a\u00020\t\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\u0002\u0010\u0016J\t\u0010.\u001a\u00020\u0003H\u00c6\u0003J\t\u0010/\u001a\u00020\u0003H\u00c6\u0003J\t\u00100\u001a\u00020\u0011H\u00c6\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0011H\u00c6\u0003J\t\u00102\u001a\u00020\u0007H\u00c6\u0003J\t\u00103\u001a\u00020\tH\u00c6\u0003J\u0010\u00104\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0018J\t\u00105\u001a\u00020\u0003H\u00c6\u0003J\t\u00106\u001a\u00020\u0003H\u00c6\u0003J\t\u00107\u001a\u00020\u0007H\u00c6\u0003J\t\u00108\u001a\u00020\tH\u00c6\u0003J\u0010\u00109\u001a\u0004\u0018\u00010\u000bH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001bJ\u0010\u0010:\u001a\u0004\u0018\u00010\u0007H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0018J\t\u0010;\u001a\u00020\u0003H\u00c6\u0003J\t\u0010<\u001a\u00020\u0003H\u00c6\u0003J\u00ac\u0001\u0010=\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00072\b\b\u0002\u0010\u0014\u001a\u00020\t2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0007H\u00c6\u0001\u00a2\u0006\u0002\u0010>J\u0013\u0010?\u001a\u00020\t2\b\u0010@\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\u0006\u0010A\u001a\u00020\u0011J\u0006\u0010B\u001a\u00020\u0007J\t\u0010C\u001a\u00020\u000bH\u00d6\u0001J\t\u0010D\u001a\u00020\u0011H\u00d6\u0001R\u0015\u0010\f\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u0015\u0010\n\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0015\u0010\u0015\u001a\u0004\u0018\u00010\u0007\u00a2\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u001f\u0010\u0018R\u0011\u0010\u0013\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u0014\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010#R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001eR\u0011\u0010\r\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001eR\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010!R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001eR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001eR\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010+R\u0011\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001e\u00a8\u0006E"}, d2 = {"Lcom/example/budgetapp/database/dao/ProductDao$ProductStoreWithPrice;", "", "id", "", "productId", "storeId", "price", "", "hasCampaignPrice", "", "campaignQuantity", "", "campaignPrice", "lastSeen", "createdAt", "updatedAt", "store_name", "", "store_address", "effective_price", "hasDeposit", "depositAmount", "(JJJDZLjava/lang/Integer;Ljava/lang/Double;JJJLjava/lang/String;Ljava/lang/String;DZLjava/lang/Double;)V", "getCampaignPrice", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getCampaignQuantity", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getCreatedAt", "()J", "getDepositAmount", "getEffective_price", "()D", "getHasCampaignPrice", "()Z", "getHasDeposit", "getId", "getLastSeen", "getPrice", "getProductId", "getStoreId", "getStore_address", "()Ljava/lang/String;", "getStore_name", "getUpdatedAt", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JJJDZLjava/lang/Integer;Ljava/lang/Double;JJJLjava/lang/String;Ljava/lang/String;DZLjava/lang/Double;)Lcom/example/budgetapp/database/dao/ProductDao$ProductStoreWithPrice;", "equals", "other", "getPriceDisplayText", "getTotalPrice", "hashCode", "toString", "CashInCashOut_debug"})
    public static final class ProductStoreWithPrice {
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
        @org.jetbrains.annotations.NotNull
        private final java.lang.String store_name = null;
        @org.jetbrains.annotations.Nullable
        private final java.lang.String store_address = null;
        private final double effective_price = 0.0;
        private final boolean hasDeposit = false;
        @org.jetbrains.annotations.Nullable
        private final java.lang.Double depositAmount = null;
        
        public ProductStoreWithPrice(long id, long productId, long storeId, double price, boolean hasCampaignPrice, @org.jetbrains.annotations.Nullable
        java.lang.Integer campaignQuantity, @org.jetbrains.annotations.Nullable
        java.lang.Double campaignPrice, long lastSeen, long createdAt, long updatedAt, @org.jetbrains.annotations.NotNull
        java.lang.String store_name, @org.jetbrains.annotations.Nullable
        java.lang.String store_address, double effective_price, boolean hasDeposit, @org.jetbrains.annotations.Nullable
        java.lang.Double depositAmount) {
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
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getStore_name() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getStore_address() {
            return null;
        }
        
        public final double getEffective_price() {
            return 0.0;
        }
        
        public final boolean getHasDeposit() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.Double getDepositAmount() {
            return null;
        }
        
        public final double getTotalPrice() {
            return 0.0;
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getPriceDisplayText() {
            return null;
        }
        
        public final long component1() {
            return 0L;
        }
        
        public final long component10() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String component11() {
            return null;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String component12() {
            return null;
        }
        
        public final double component13() {
            return 0.0;
        }
        
        public final boolean component14() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.Double component15() {
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
        public final com.example.budgetapp.database.dao.ProductDao.ProductStoreWithPrice copy(long id, long productId, long storeId, double price, boolean hasCampaignPrice, @org.jetbrains.annotations.Nullable
        java.lang.Integer campaignQuantity, @org.jetbrains.annotations.Nullable
        java.lang.Double campaignPrice, long lastSeen, long createdAt, long updatedAt, @org.jetbrains.annotations.NotNull
        java.lang.String store_name, @org.jetbrains.annotations.Nullable
        java.lang.String store_address, double effective_price, boolean hasDeposit, @org.jetbrains.annotations.Nullable
        java.lang.Double depositAmount) {
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
}