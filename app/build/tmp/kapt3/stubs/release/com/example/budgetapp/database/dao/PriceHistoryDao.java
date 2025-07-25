package com.example.budgetapp.database.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\f\bg\u0018\u00002\u00020\u0001:\u0001-J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0019\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u001b\u0010\u0011\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000e\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J#\u0010\u0012\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J\u001b\u0010\u0014\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000e\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\u0006\u0010\u000e\u001a\u00020\u0005H\'J\u001c\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00190\u00162\u0006\u0010\u000e\u001a\u00020\u0005H\'J$\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00190\u00162\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005H\'J4\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00190\u00162\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0005H\'J,\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00190\u00162\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0005H\'J\u001f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\t0\u00192\u0006\u0010\u000e\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00190\u00162\u0006\u0010\u0010\u001a\u00020\u0005H\'J\u001b\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010\u000e\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J#\u0010#\u001a\u0004\u0018\u00010\"2\u0006\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J\u001c\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00190\u00162\u0006\u0010%\u001a\u00020\u0005H\'J\u001f\u0010&\u001a\b\u0012\u0004\u0012\u00020\t0\u00192\u0006\u0010%\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\'\u001a\u00020\u00172\u0006\u0010\u000e\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010(\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ%\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00050\u00192\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\t0\u0019H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010+J\u0019\u0010,\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006."}, d2 = {"Lcom/example/budgetapp/database/dao/PriceHistoryDao;", "", "deleteOldPriceHistory", "", "cutoffDate", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletePriceHistory", "priceHistory", "Lcom/example/budgetapp/database/entities/PriceHistory;", "(Lcom/example/budgetapp/database/entities/PriceHistory;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deletePriceHistoryById", "id", "deletePriceHistoryForProduct", "productId", "deletePriceHistoryForStore", "storeId", "getHighestPriceForProduct", "getLatestPriceForProductAtStore", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getLowestPriceForProduct", "getPriceHistoryCountForProduct", "Landroidx/lifecycle/LiveData;", "", "getPriceHistoryForProduct", "", "getPriceHistoryForProductAtStore", "getPriceHistoryForProductAtStoreInDateRange", "startDate", "endDate", "getPriceHistoryForProductInDateRange", "getPriceHistoryForProductSuspend", "getPriceHistoryForStore", "getPriceStatisticsForProduct", "Lcom/example/budgetapp/database/dao/PriceHistoryDao$PriceStatistics;", "getPriceStatisticsForProductAtStore", "getRecentPriceHistory", "date", "getRecentPriceHistorySuspend", "getStoreCountWithPriceHistoryForProduct", "insertPriceHistory", "insertPriceHistoryList", "priceHistories", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updatePriceHistory", "PriceStatistics", "CashInCashOut_release"})
@androidx.room.Dao
public abstract interface PriceHistoryDao {
    
    @androidx.room.Query(value = "SELECT * FROM price_history WHERE productId = :productId ORDER BY recordedAt DESC")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<java.util.List<com.example.budgetapp.database.entities.PriceHistory>> getPriceHistoryForProduct(long productId);
    
    @androidx.room.Query(value = "SELECT * FROM price_history WHERE productId = :productId AND storeId = :storeId ORDER BY recordedAt DESC")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<java.util.List<com.example.budgetapp.database.entities.PriceHistory>> getPriceHistoryForProductAtStore(long productId, long storeId);
    
    @androidx.room.Query(value = "SELECT * FROM price_history WHERE storeId = :storeId ORDER BY recordedAt DESC")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<java.util.List<com.example.budgetapp.database.entities.PriceHistory>> getPriceHistoryForStore(long storeId);
    
    @androidx.room.Query(value = "SELECT * FROM price_history WHERE productId = :productId AND recordedAt BETWEEN :startDate AND :endDate ORDER BY recordedAt DESC")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<java.util.List<com.example.budgetapp.database.entities.PriceHistory>> getPriceHistoryForProductInDateRange(long productId, long startDate, long endDate);
    
    @androidx.room.Query(value = "SELECT * FROM price_history WHERE productId = :productId AND storeId = :storeId AND recordedAt BETWEEN :startDate AND :endDate ORDER BY recordedAt DESC")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<java.util.List<com.example.budgetapp.database.entities.PriceHistory>> getPriceHistoryForProductAtStoreInDateRange(long productId, long storeId, long startDate, long endDate);
    
    @androidx.room.Query(value = "SELECT * FROM price_history WHERE recordedAt >= :date ORDER BY recordedAt DESC")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<java.util.List<com.example.budgetapp.database.entities.PriceHistory>> getRecentPriceHistory(long date);
    
    @androidx.room.Query(value = "SELECT MIN(price) as minPrice, MAX(price) as maxPrice, AVG(price) as avgPrice FROM price_history WHERE productId = :productId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getPriceStatisticsForProduct(long productId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.database.dao.PriceHistoryDao.PriceStatistics> $completion);
    
    @androidx.room.Query(value = "SELECT MIN(price) as minPrice, MAX(price) as maxPrice, AVG(price) as avgPrice FROM price_history WHERE productId = :productId AND storeId = :storeId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getPriceStatisticsForProductAtStore(long productId, long storeId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.database.dao.PriceHistoryDao.PriceStatistics> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM price_history WHERE productId = :productId AND storeId = :storeId ORDER BY recordedAt DESC LIMIT 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getLatestPriceForProductAtStore(long productId, long storeId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.database.entities.PriceHistory> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM price_history WHERE productId = :productId ORDER BY price ASC LIMIT 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getLowestPriceForProduct(long productId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.database.entities.PriceHistory> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM price_history WHERE productId = :productId ORDER BY price DESC LIMIT 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getHighestPriceForProduct(long productId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.database.entities.PriceHistory> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertPriceHistory(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.PriceHistory priceHistory, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertPriceHistoryList(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.database.entities.PriceHistory> priceHistories, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.Long>> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updatePriceHistory(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.PriceHistory priceHistory, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deletePriceHistory(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.PriceHistory priceHistory, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM price_history WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deletePriceHistoryById(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM price_history WHERE productId = :productId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deletePriceHistoryForProduct(long productId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM price_history WHERE storeId = :storeId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deletePriceHistoryForStore(long storeId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM price_history WHERE recordedAt < :cutoffDate")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteOldPriceHistory(long cutoffDate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM price_history WHERE productId = :productId")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<java.lang.Integer> getPriceHistoryCountForProduct(long productId);
    
    @androidx.room.Query(value = "SELECT COUNT(DISTINCT storeId) FROM price_history WHERE productId = :productId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getStoreCountWithPriceHistoryForProduct(long productId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM price_history WHERE productId = :productId ORDER BY recordedAt DESC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getPriceHistoryForProductSuspend(long productId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.database.entities.PriceHistory>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM price_history WHERE recordedAt >= :date ORDER BY recordedAt DESC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getRecentPriceHistorySuspend(long date, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.database.entities.PriceHistory>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b\u00a8\u0006\u0016"}, d2 = {"Lcom/example/budgetapp/database/dao/PriceHistoryDao$PriceStatistics;", "", "minPrice", "", "maxPrice", "avgPrice", "(DDD)V", "getAvgPrice", "()D", "getMaxPrice", "getMinPrice", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "CashInCashOut_release"})
    public static final class PriceStatistics {
        private final double minPrice = 0.0;
        private final double maxPrice = 0.0;
        private final double avgPrice = 0.0;
        
        public PriceStatistics(double minPrice, double maxPrice, double avgPrice) {
            super();
        }
        
        public final double getMinPrice() {
            return 0.0;
        }
        
        public final double getMaxPrice() {
            return 0.0;
        }
        
        public final double getAvgPrice() {
            return 0.0;
        }
        
        public final double component1() {
            return 0.0;
        }
        
        public final double component2() {
            return 0.0;
        }
        
        public final double component3() {
            return 0.0;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.budgetapp.database.dao.PriceHistoryDao.PriceStatistics copy(double minPrice, double maxPrice, double avgPrice) {
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