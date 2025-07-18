package com.example.budgetapp.database.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\bg\u0018\u00002\u00020\u0001:\u0001\u001fJ\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\fH\'J\u001b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u001b\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0012\u001a\u00020\u0013H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014J\u001b\u0010\u0015\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0011\u0010\u0016\u001a\u00020\u0017H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018J\u001c\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\f2\u0006\u0010\u001a\u001a\u00020\tH\'J\u0019\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\f2\u0006\u0010\u001d\u001a\u00020\u0013H\'J\u0019\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006 "}, d2 = {"Lcom/example/budgetapp/database/dao/ProductDao;", "", "deleteProduct", "", "product", "Lcom/example/budgetapp/database/entities/Product;", "(Lcom/example/budgetapp/database/entities/Product;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteProductById", "id", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllProducts", "Lkotlinx/coroutines/flow/Flow;", "", "getPriceStats", "Lcom/example/budgetapp/database/dao/ProductDao$PriceStats;", "productId", "getProductByBarcode", "barcode", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getProductById", "getProductCount", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getProductsByCategory", "categoryId", "insertProduct", "searchProducts", "searchQuery", "updateProduct", "PriceStats", "budget-debug_debug"})
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
    
    @androidx.room.Query(value = "SELECT * FROM products WHERE categoryId = :categoryId ORDER BY name ASC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.Product>> getProductsByCategory(long categoryId);
    
    @androidx.room.Query(value = "SELECT * FROM products WHERE name LIKE \'%\' || :searchQuery || \'%\' OR productName LIKE \'%\' || :searchQuery || \'%\' ORDER BY name ASC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.Product>> searchProducts(@org.jetbrains.annotations.NotNull
    java.lang.String searchQuery);
    
    @androidx.room.Query(value = "\n        SELECT MIN(ps.price) as minPrice, MAX(ps.price) as maxPrice, AVG(ps.price) as avgPrice\n        FROM product_stores ps\n        WHERE ps.productId = :productId\n    ")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getPriceStats(long productId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.database.dao.ProductDao.PriceStats> $completion);
    
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u0006J\u0010\u0010\f\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\r\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\bJ2\u0010\u000f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001R\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\t\u001a\u0004\b\n\u0010\bR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u000b\u0010\b\u00a8\u0006\u0018"}, d2 = {"Lcom/example/budgetapp/database/dao/ProductDao$PriceStats;", "", "minPrice", "", "maxPrice", "avgPrice", "(Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;)V", "getAvgPrice", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getMaxPrice", "getMinPrice", "component1", "component2", "component3", "copy", "(Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Double;)Lcom/example/budgetapp/database/dao/ProductDao$PriceStats;", "equals", "", "other", "hashCode", "", "toString", "", "budget-debug_debug"})
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
}