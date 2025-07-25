package com.example.budgetapp.database.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\bg\u0018\u00002\u00020\u0001J\u0011\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u001b\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u0019\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ\u0019\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u0019\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u0014\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00130\u0012H\'J\u0014\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00130\u0012H\'J#\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001aJ!\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00132\b\b\u0002\u0010\u001c\u001a\u00020\u001dH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001eJ!\u0010\u001f\u001a\u00020 2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001aJ\u001f\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u00132\u0006\u0010\u0019\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u001f\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u00132\u0006\u0010\u0019\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u001c\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00130\u00122\u0006\u0010&\u001a\u00020\'H\'J\u001c\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00130\u00122\u0006\u0010)\u001a\u00020*H\'J\u001c\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00130\u00122\u0006\u0010\u0017\u001a\u00020\u0018H\'J\u000e\u0010,\u001a\b\u0012\u0004\u0012\u00020 0-H\'J\u0019\u0010.\u001a\u00020 2\u0006\u0010)\u001a\u00020*H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010/J\u0014\u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00130\u0012H\'J!\u00101\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00132\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u0019\u00102\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\fJ\u001f\u00103\u001a\u00020\u00032\f\u00104\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0013H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00105J#\u00106\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u00072\b\b\u0002\u00107\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00108J#\u00109\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u00072\b\b\u0002\u00107\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00108J)\u00109\u001a\u00020\u00032\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00070\u00132\b\b\u0002\u00107\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010;J\u0019\u0010<\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\f\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006="}, d2 = {"Lcom/example/budgetapp/database/dao/FinancialInsightDao;", "", "deleteAllInsights", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteExpiredInsights", "currentTime", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteInsight", "insight", "Lcom/example/budgetapp/database/entities/FinancialInsight;", "(Lcom/example/budgetapp/database/entities/FinancialInsight;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteInsightById", "id", "deleteOldInsights", "cutoffTime", "getActionableInsights", "Lkotlinx/coroutines/flow/Flow;", "", "getAllInsights", "getAverageConfidenceByType", "", "type", "Lcom/example/budgetapp/database/entities/InsightType;", "since", "(Lcom/example/budgetapp/database/entities/InsightType;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getHighConfidenceInsights", "minConfidence", "", "(FLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getInsightCountByTypeSince", "", "getInsightCountsByCategory", "Lcom/example/budgetapp/database/dao/CategoryCount;", "getInsightCountsByType", "Lcom/example/budgetapp/database/dao/InsightTypeCount;", "getInsightsByCategory", "category", "", "getInsightsBySeverity", "severity", "Lcom/example/budgetapp/database/entities/InsightSeverity;", "getInsightsByType", "getUnreadCount", "Landroidx/lifecycle/LiveData;", "getUnreadCountBySeverity", "(Lcom/example/budgetapp/database/entities/InsightSeverity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUnreadInsights", "getValidInsights", "insertInsight", "insertInsights", "insights", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markAsActedUpon", "timestamp", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markAsRead", "ids", "(Ljava/util/List;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateInsight", "CashInCashOut_debug"})
@androidx.room.Dao
public abstract interface FinancialInsightDao {
    
    @androidx.room.Query(value = "SELECT * FROM financial_insights ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.FinancialInsight>> getAllInsights();
    
    @androidx.room.Query(value = "SELECT * FROM financial_insights WHERE isRead = 0 ORDER BY severity DESC, createdAt DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.FinancialInsight>> getUnreadInsights();
    
    @androidx.room.Query(value = "SELECT * FROM financial_insights WHERE insightType = :type ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.FinancialInsight>> getInsightsByType(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.InsightType type);
    
    @androidx.room.Query(value = "SELECT * FROM financial_insights WHERE severity = :severity ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.FinancialInsight>> getInsightsBySeverity(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.InsightSeverity severity);
    
    @androidx.room.Query(value = "SELECT * FROM financial_insights WHERE category = :category ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.FinancialInsight>> getInsightsByCategory(@org.jetbrains.annotations.NotNull
    java.lang.String category);
    
    @androidx.room.Query(value = "SELECT * FROM financial_insights WHERE actionable = 1 AND isActedUpon = 0 ORDER BY severity DESC, createdAt DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.FinancialInsight>> getActionableInsights();
    
    @androidx.room.Query(value = "SELECT * FROM financial_insights WHERE validUntil IS NULL OR validUntil > :currentTime ORDER BY createdAt DESC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getValidInsights(long currentTime, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.database.entities.FinancialInsight>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM financial_insights WHERE confidenceScore >= :minConfidence ORDER BY confidenceScore DESC, createdAt DESC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getHighConfidenceInsights(float minConfidence, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.database.entities.FinancialInsight>> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM financial_insights WHERE isRead = 0")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<java.lang.Integer> getUnreadCount();
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM financial_insights WHERE severity = :severity AND isRead = 0")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getUnreadCountBySeverity(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.InsightSeverity severity, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertInsight(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.FinancialInsight insight, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertInsights(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.database.entities.FinancialInsight> insights, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateInsight(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.FinancialInsight insight, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE financial_insights SET isRead = 1, updatedAt = :timestamp WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object markAsRead(long id, long timestamp, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE financial_insights SET isRead = 1, updatedAt = :timestamp WHERE id IN (:ids)")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object markAsRead(@org.jetbrains.annotations.NotNull
    java.util.List<java.lang.Long> ids, long timestamp, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE financial_insights SET isActedUpon = 1, updatedAt = :timestamp WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object markAsActedUpon(long id, long timestamp, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteInsight(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.FinancialInsight insight, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM financial_insights WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteInsightById(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM financial_insights WHERE validUntil IS NOT NULL AND validUntil < :currentTime")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteExpiredInsights(long currentTime, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM financial_insights WHERE createdAt < :cutoffTime")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteOldInsights(long cutoffTime, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM financial_insights")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteAllInsights(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "\n        SELECT COUNT(*) FROM financial_insights \n        WHERE insightType = :type \n        AND createdAt > :since\n    ")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getInsightCountByTypeSince(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.InsightType type, long since, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "\n        SELECT AVG(confidenceScore) FROM financial_insights \n        WHERE insightType = :type \n        AND createdAt > :since\n    ")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getAverageConfidenceByType(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.InsightType type, long since, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    @androidx.room.Query(value = "\n        SELECT insightType, COUNT(*) as count \n        FROM financial_insights \n        WHERE createdAt > :since \n        GROUP BY insightType\n    ")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getInsightCountsByType(long since, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.database.dao.InsightTypeCount>> $completion);
    
    @androidx.room.Query(value = "\n        SELECT category, COUNT(*) as count \n        FROM financial_insights \n        WHERE category IS NOT NULL AND createdAt > :since \n        GROUP BY category\n    ")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getInsightCountsByCategory(long since, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.database.dao.CategoryCount>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}