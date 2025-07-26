package com.example.budgetapp.automation.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0011\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u0011\u0010\u0005\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u0011\u0010\u0006\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u001b\u0010\f\u001a\u00020\b2\b\b\u0002\u0010\r\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0019\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u0019\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0014H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J\u0019\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0017H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018J\u0019\u0010\u0019\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0019\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u001cH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001dJ!\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00100\u001f2\b\b\u0002\u0010\r\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0011\u0010 \u001a\u00020\bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u0017\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00140\u001fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u0017\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00170\u001fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00100\u001fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u001f0%H\'J\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00140\u001fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u001f0%H\'J\u0017\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00170\u001fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u0011\u0010)\u001a\u00020*H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u0011\u0010+\u001a\u00020,H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010-\u001a\u00020\b2\u0006\u0010.\u001a\u00020\u001cH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001dJ\u0017\u0010/\u001a\b\u0012\u0004\u0012\u0002000\u001fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u001f\u00101\u001a\b\u0012\u0004\u0012\u00020\u00100\u001f2\u0006\u00102\u001a\u00020\u001cH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001dJ\u001f\u00103\u001a\b\u0012\u0004\u0012\u00020\u00100\u001f2\u0006\u0010.\u001a\u00020\u001cH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001dJ\u001f\u00104\u001a\b\u0012\u0004\u0012\u00020\u00100\u001f2\u0006\u00105\u001a\u00020\u001cH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001dJ!\u00106\u001a\b\u0012\u0004\u0012\u00020\u00140\u001f2\b\b\u0002\u00107\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u001b\u00108\u001a\u0004\u0018\u00010\u00142\u0006\u00109\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u001f\u0010:\u001a\b\u0012\u0004\u0012\u00020;0\u001f2\u0006\u0010<\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u001c\u0010=\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u001f0%2\u0006\u0010>\u001a\u00020\u001cH\'J\u001f\u0010?\u001a\b\u0012\u0004\u0012\u00020@0\u001f2\u0006\u0010A\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u001b\u0010B\u001a\u0004\u0018\u00010\u00172\u0006\u00109\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u001f\u0010C\u001a\b\u0012\u0004\u0012\u00020\u00170\u001f2\u0006\u0010D\u001a\u00020\u001cH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001dJ\u0011\u0010E\u001a\u00020\bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u0017\u0010F\u001a\b\u0012\u0004\u0012\u00020\u00100\u001fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J#\u0010G\u001a\u00020\u00032\u0006\u00109\u001a\u00020\n2\b\b\u0002\u0010H\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010IJ\u0019\u0010J\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0010H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J%\u0010K\u001a\b\u0012\u0004\u0012\u00020\n0\u001f2\f\u0010L\u001a\b\u0012\u0004\u0012\u00020\u00100\u001fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010MJ\u0019\u0010N\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0014H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J%\u0010O\u001a\b\u0012\u0004\u0012\u00020\n0\u001f2\f\u0010P\u001a\b\u0012\u0004\u0012\u00020\u00140\u001fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010MJ\u0019\u0010Q\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0017H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018J\u0011\u0010R\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010S\u001a\u00020\u00032\u0006\u0010\u001b\u001a\u00020\u001cH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001dJ+\u0010T\u001a\u00020\u00032\u0006\u00109\u001a\u00020\n2\u0006\u0010U\u001a\u00020,2\b\b\u0002\u0010H\u001a\u00020\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010VJ!\u0010W\u001a\u00020\u00032\u0006\u00109\u001a\u00020\n2\u0006\u0010X\u001a\u00020YH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010ZJ!\u0010[\u001a\u00020\u00032\u0006\u00109\u001a\u00020\n2\u0006\u0010X\u001a\u00020YH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010ZJ\u0019\u0010\\\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u0019\u0010]\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0014H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J\u0019\u0010^\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0017H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006_"}, d2 = {"Lcom/example/budgetapp/automation/dao/AutomationRuleDao;", "", "deleteAllInsights", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteAllRules", "deleteAllTransferRules", "deleteDismissedInsights", "", "cutoffTime", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteExpiredInsights", "currentTime", "deleteInsight", "insight", "Lcom/example/budgetapp/automation/ml/SmartInsight;", "(Lcom/example/budgetapp/automation/ml/SmartInsight;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteRule", "rule", "Lcom/example/budgetapp/automation/AutomationRule;", "(Lcom/example/budgetapp/automation/AutomationRule;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteTransferRule", "Lcom/example/budgetapp/automation/AutoTransferRule;", "(Lcom/example/budgetapp/automation/AutoTransferRule;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteUnusedRules", "dismissInsight", "insightId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getActiveInsights", "", "getActiveRuleCount", "getActiveRules", "getActiveTransferRules", "getAllActiveInsightsForBackup", "getAllRules", "Lkotlinx/coroutines/flow/Flow;", "getAllRulesForBackup", "getAllTransferRules", "getAllTransferRulesForBackup", "getAutomationStatistics", "Lcom/example/budgetapp/automation/dao/AutomationStatistics;", "getAverageRuleUsage", "", "getInsightCountByPriority", "priority", "getInsightStatistics", "Lcom/example/budgetapp/automation/dao/InsightTypeStatistics;", "getInsightsByCategory", "category", "getInsightsByPriority", "getInsightsByType", "type", "getPopularRules", "minUsage", "getRuleById", "ruleId", "getRuleCreationHistory", "Lcom/example/budgetapp/automation/dao/RuleCreationHistory;", "startTime", "getRulesByCreator", "createdBy", "getTopUsedRules", "Lcom/example/budgetapp/automation/dao/RuleUsageStatistics;", "totalUsage", "getTransferRuleById", "getTransferRulesByAccount", "accountId", "getUnreadInsightCount", "getUnreadInsights", "incrementRuleUsage", "timestamp", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertInsight", "insertInsights", "insights", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertRule", "insertRules", "rules", "insertTransferRule", "markAllInsightsAsRead", "markInsightAsRead", "recordTransferExecution", "amount", "(JDJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toggleRuleStatus", "isActive", "", "(JZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toggleTransferRuleStatus", "updateInsight", "updateRule", "updateTransferRule", "CashInCashOut_debug"})
@androidx.room.Dao
public abstract interface AutomationRuleDao {
    
    @androidx.room.Query(value = "SELECT * FROM automation_rules WHERE isActive = 1 ORDER BY priority ASC, usageCount DESC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getActiveRules(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.automation.AutomationRule>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM automation_rules ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.automation.AutomationRule>> getAllRules();
    
    @androidx.room.Query(value = "SELECT * FROM automation_rules WHERE id = :ruleId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getRuleById(long ruleId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.automation.AutomationRule> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM automation_rules WHERE createdBy = :createdBy ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.automation.AutomationRule>> getRulesByCreator(@org.jetbrains.annotations.NotNull
    java.lang.String createdBy);
    
    @androidx.room.Query(value = "SELECT * FROM automation_rules WHERE usageCount > :minUsage ORDER BY usageCount DESC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getPopularRules(long minUsage, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.automation.AutomationRule>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertRule(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.automation.AutomationRule rule, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertRules(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.automation.AutomationRule> rules, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.Long>> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateRule(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.automation.AutomationRule rule, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteRule(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.automation.AutomationRule rule, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE automation_rules SET isActive = :isActive WHERE id = :ruleId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object toggleRuleStatus(long ruleId, boolean isActive, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE automation_rules SET usageCount = usageCount + 1, lastModified = :timestamp WHERE id = :ruleId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object incrementRuleUsage(long ruleId, long timestamp, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM automation_rules WHERE isActive = 0 AND usageCount = 0 AND createdAt < :cutoffTime")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteUnusedRules(long cutoffTime, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM automation_rules WHERE isActive = 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getActiveRuleCount(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT AVG(usageCount) FROM automation_rules WHERE isActive = 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getAverageRuleUsage(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM auto_transfer_rules WHERE isActive = 1 ORDER BY createdAt DESC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getActiveTransferRules(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.automation.AutoTransferRule>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM auto_transfer_rules ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.automation.AutoTransferRule>> getAllTransferRules();
    
    @androidx.room.Query(value = "SELECT * FROM auto_transfer_rules WHERE id = :ruleId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getTransferRuleById(long ruleId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.automation.AutoTransferRule> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM auto_transfer_rules WHERE sourceAccountId = :accountId OR targetAccountId = :accountId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getTransferRulesByAccount(@org.jetbrains.annotations.NotNull
    java.lang.String accountId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.automation.AutoTransferRule>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertTransferRule(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.automation.AutoTransferRule rule, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateTransferRule(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.automation.AutoTransferRule rule, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteTransferRule(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.automation.AutoTransferRule rule, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE auto_transfer_rules SET isActive = :isActive WHERE id = :ruleId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object toggleTransferRuleStatus(long ruleId, boolean isActive, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE auto_transfer_rules SET lastExecuted = :timestamp, totalTransferred = totalTransferred + :amount WHERE id = :ruleId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object recordTransferExecution(long ruleId, double amount, long timestamp, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM smart_insights WHERE isDismissed = 0 AND (expiresAt IS NULL OR expiresAt > :currentTime) ORDER BY priority DESC, createdAt DESC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getActiveInsights(long currentTime, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.automation.ml.SmartInsight>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM smart_insights WHERE type = :type AND isDismissed = 0 ORDER BY createdAt DESC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getInsightsByType(@org.jetbrains.annotations.NotNull
    java.lang.String type, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.automation.ml.SmartInsight>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM smart_insights WHERE category = :category AND isDismissed = 0 ORDER BY priority DESC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getInsightsByCategory(@org.jetbrains.annotations.NotNull
    java.lang.String category, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.automation.ml.SmartInsight>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM smart_insights WHERE priority = :priority AND isDismissed = 0 ORDER BY createdAt DESC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getInsightsByPriority(@org.jetbrains.annotations.NotNull
    java.lang.String priority, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.automation.ml.SmartInsight>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM smart_insights WHERE isRead = 0 AND isDismissed = 0 ORDER BY priority DESC, createdAt DESC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getUnreadInsights(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.automation.ml.SmartInsight>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertInsight(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.automation.ml.SmartInsight insight, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertInsights(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.automation.ml.SmartInsight> insights, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.Long>> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateInsight(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.automation.ml.SmartInsight insight, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteInsight(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.automation.ml.SmartInsight insight, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE smart_insights SET isRead = 1 WHERE id = :insightId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object markInsightAsRead(@org.jetbrains.annotations.NotNull
    java.lang.String insightId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE smart_insights SET isDismissed = 1 WHERE id = :insightId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object dismissInsight(@org.jetbrains.annotations.NotNull
    java.lang.String insightId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE smart_insights SET isRead = 1 WHERE isDismissed = 0")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object markAllInsightsAsRead(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM smart_insights WHERE isDismissed = 1 AND createdAt < :cutoffTime")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteDismissedInsights(long cutoffTime, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "DELETE FROM smart_insights WHERE expiresAt IS NOT NULL AND expiresAt < :currentTime")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteExpiredInsights(long currentTime, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM smart_insights WHERE isRead = 0 AND isDismissed = 0")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getUnreadInsightCount(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM smart_insights WHERE priority = :priority AND isDismissed = 0")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getInsightCountByPriority(@org.jetbrains.annotations.NotNull
    java.lang.String priority, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
    
    @androidx.room.Query(value = "\n        SELECT \n            COUNT(*) as totalRules,\n            SUM(CASE WHEN isActive = 1 THEN 1 ELSE 0 END) as activeRules,\n            SUM(usageCount) as totalUsage,\n            AVG(usageCount) as averageUsage\n        FROM automation_rules\n    ")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getAutomationStatistics(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.automation.dao.AutomationStatistics> $completion);
    
    @androidx.room.Query(value = "\n        SELECT \n            type,\n            COUNT(*) as count,\n            AVG(confidence) as averageConfidence\n        FROM smart_insights \n        WHERE isDismissed = 0 \n        GROUP BY type\n    ")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getInsightStatistics(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.automation.dao.InsightTypeStatistics>> $completion);
    
    @androidx.room.Query(value = "\n        SELECT \n            DATE(createdAt/1000, \'unixepoch\') as date,\n            COUNT(*) as count\n        FROM automation_rules \n        WHERE createdAt > :startTime\n        GROUP BY DATE(createdAt/1000, \'unixepoch\')\n        ORDER BY date DESC\n    ")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getRuleCreationHistory(long startTime, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.automation.dao.RuleCreationHistory>> $completion);
    
    @androidx.room.Query(value = "\n        SELECT \n            name,\n            usageCount,\n            (usageCount * 1.0 / :totalUsage) * 100 as usagePercentage\n        FROM automation_rules \n        WHERE isActive = 1 AND usageCount > 0\n        ORDER BY usageCount DESC\n        LIMIT 10\n    ")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getTopUsedRules(long totalUsage, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.automation.dao.RuleUsageStatistics>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM automation_rules")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getAllRulesForBackup(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.automation.AutomationRule>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM auto_transfer_rules")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getAllTransferRulesForBackup(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.automation.AutoTransferRule>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM smart_insights WHERE isDismissed = 0")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getAllActiveInsightsForBackup(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.automation.ml.SmartInsight>> $completion);
    
    @androidx.room.Query(value = "DELETE FROM automation_rules")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteAllRules(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM auto_transfer_rules")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteAllTransferRules(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM smart_insights")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteAllInsights(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}