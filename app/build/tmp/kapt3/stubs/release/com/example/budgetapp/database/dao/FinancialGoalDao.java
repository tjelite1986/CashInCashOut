package com.example.budgetapp.database.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\bg\u0018\u00002\u00020\u0001J#\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J+\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ#\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\u0011\u0010\r\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0019\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0011H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J\u0019\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014J\u0019\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\'J\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0017\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0014\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u001b0 H\'J\u0014\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u001b0 H\'J\u0013\u0010\"\u001a\u0004\u0018\u00010\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0017\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u001bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u000e\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018H\'J\u0014\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u001b0 H\'J\u001b\u0010\'\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014J\u001c\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u001b0 2\u0006\u0010)\u001a\u00020*H\'J\u001c\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u001b0 2\u0006\u0010,\u001a\u00020-H\'J\'\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00110\u001b2\u0006\u0010/\u001a\u00020\u00052\u0006\u00100\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\u0017\u00101\u001a\b\u0012\u0004\u0012\u00020\u00110\u001bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0017\u00102\u001a\b\u0012\u0004\u0012\u00020\u00110\u001bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0017\u00103\u001a\b\u0012\u0004\u0012\u00020\u00110\u001bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ!\u00104\u001a\b\u0012\u0004\u0012\u00020\u00110\u001b2\b\b\u0002\u00105\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0014J\u0013\u00106\u001a\u0004\u0018\u00010\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0013\u00107\u001a\u0004\u0018\u00010\nH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0017\u00108\u001a\b\u0012\u0004\u0012\u0002090\u001bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\u0019\u0010:\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J\u001f\u0010;\u001a\u00020\u00032\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00110\u001bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010=J#\u0010>\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\u0019\u0010?\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0011H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J+\u0010@\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000b\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006A"}, d2 = {"Lcom/example/budgetapp/database/dao/FinancialGoalDao;", "", "activateGoal", "", "goalId", "", "timestamp", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addToGoalProgress", "amount", "", "(JDJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deactivateGoal", "deleteAllGoals", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteGoal", "goal", "Lcom/example/budgetapp/database/entities/FinancialGoal;", "(Lcom/example/budgetapp/database/entities/FinancialGoal;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteGoalById", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteOldCompletedGoals", "cutoffTime", "getActiveGoalCount", "Landroidx/lifecycle/LiveData;", "", "getActiveGoalCountsByPriority", "", "Lcom/example/budgetapp/database/dao/GoalPriorityCount;", "getActiveGoalCountsByType", "Lcom/example/budgetapp/database/dao/GoalTypeCount;", "getActiveGoals", "Lkotlinx/coroutines/flow/Flow;", "getAllGoals", "getAverageProgress", "getAverageProgressByType", "Lcom/example/budgetapp/database/dao/GoalTypeProgress;", "getCompletedGoalCount", "getCompletedGoals", "getGoalById", "getGoalsByPriority", "priority", "Lcom/example/budgetapp/database/entities/GoalPriority;", "getGoalsByType", "type", "Lcom/example/budgetapp/database/entities/GoalType;", "getGoalsDueInRange", "startDate", "endDate", "getGoalsReadyForCompletion", "getGoalsWithAutoTransfer", "getGoalsWithReminders", "getOverdueGoals", "date", "getTotalCurrentAmount", "getTotalTargetAmount", "getTotalTargetByType", "Lcom/example/budgetapp/database/dao/GoalTypeTarget;", "insertGoal", "insertGoals", "goals", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "markGoalCompleted", "updateGoal", "updateGoalProgress", "CashInCashOut_release"})
@androidx.room.Dao
public abstract interface FinancialGoalDao {
    
    @androidx.room.Query(value = "SELECT * FROM financial_goals ORDER BY priority DESC, targetDate ASC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.FinancialGoal>> getAllGoals();
    
    @androidx.room.Query(value = "SELECT * FROM financial_goals WHERE isActive = 1 ORDER BY priority DESC, targetDate ASC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.FinancialGoal>> getActiveGoals();
    
    @androidx.room.Query(value = "SELECT * FROM financial_goals WHERE isCompleted = 1 ORDER BY completedAt DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.FinancialGoal>> getCompletedGoals();
    
    @androidx.room.Query(value = "SELECT * FROM financial_goals WHERE goalType = :type ORDER BY priority DESC, targetDate ASC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.FinancialGoal>> getGoalsByType(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.GoalType type);
    
    @androidx.room.Query(value = "SELECT * FROM financial_goals WHERE priority = :priority ORDER BY targetDate ASC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.database.entities.FinancialGoal>> getGoalsByPriority(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.GoalPriority priority);
    
    @androidx.room.Query(value = "SELECT * FROM financial_goals WHERE id = :goalId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getGoalById(long goalId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.database.entities.FinancialGoal> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM financial_goals WHERE targetDate IS NOT NULL AND targetDate < :date AND isCompleted = 0")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getOverdueGoals(long date, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.database.entities.FinancialGoal>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM financial_goals WHERE targetDate IS NOT NULL AND targetDate BETWEEN :startDate AND :endDate")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getGoalsDueInRange(long startDate, long endDate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.database.entities.FinancialGoal>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM financial_goals WHERE (currentAmount / targetAmount * 100) >= 100 AND isCompleted = 0")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getGoalsReadyForCompletion(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.database.entities.FinancialGoal>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM financial_goals WHERE reminderEnabled = 1 AND isActive = 1 AND isCompleted = 0")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getGoalsWithReminders(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.database.entities.FinancialGoal>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM financial_goals WHERE autoTransferEnabled = 1 AND isActive = 1 AND isCompleted = 0")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getGoalsWithAutoTransfer(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.database.entities.FinancialGoal>> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM financial_goals WHERE isActive = 1")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<java.lang.Integer> getActiveGoalCount();
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM financial_goals WHERE isCompleted = 1")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<java.lang.Integer> getCompletedGoalCount();
    
    @androidx.room.Query(value = "SELECT SUM(targetAmount) FROM financial_goals WHERE isActive = 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getTotalTargetAmount(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    @androidx.room.Query(value = "SELECT SUM(currentAmount) FROM financial_goals WHERE isActive = 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getTotalCurrentAmount(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    @androidx.room.Query(value = "SELECT AVG(currentAmount / targetAmount * 100) FROM financial_goals WHERE isActive = 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getAverageProgress(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertGoal(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.FinancialGoal goal, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertGoals(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.database.entities.FinancialGoal> goals, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateGoal(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.FinancialGoal goal, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE financial_goals SET currentAmount = :amount, updatedAt = :timestamp WHERE id = :goalId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateGoalProgress(long goalId, double amount, long timestamp, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE financial_goals SET currentAmount = currentAmount + :amount, updatedAt = :timestamp WHERE id = :goalId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object addToGoalProgress(long goalId, double amount, long timestamp, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "\n        UPDATE financial_goals \n        SET isCompleted = 1, \n            completedAt = :timestamp, \n            updatedAt = :timestamp \n        WHERE id = :goalId\n    ")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object markGoalCompleted(long goalId, long timestamp, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE financial_goals SET isActive = 0, updatedAt = :timestamp WHERE id = :goalId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deactivateGoal(long goalId, long timestamp, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE financial_goals SET isActive = 1, updatedAt = :timestamp WHERE id = :goalId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object activateGoal(long goalId, long timestamp, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteGoal(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.FinancialGoal goal, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM financial_goals WHERE id = :goalId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteGoalById(long goalId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM financial_goals WHERE isCompleted = 1 AND completedAt < :cutoffTime")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteOldCompletedGoals(long cutoffTime, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM financial_goals")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteAllGoals(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "\n        SELECT goalType, COUNT(*) as count \n        FROM financial_goals \n        WHERE isActive = 1 \n        GROUP BY goalType\n    ")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getActiveGoalCountsByType(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.database.dao.GoalTypeCount>> $completion);
    
    @androidx.room.Query(value = "\n        SELECT goalType, AVG(currentAmount / targetAmount * 100) as avgProgress \n        FROM financial_goals \n        WHERE isActive = 1 \n        GROUP BY goalType\n    ")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getAverageProgressByType(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.database.dao.GoalTypeProgress>> $completion);
    
    @androidx.room.Query(value = "\n        SELECT goalType, SUM(targetAmount) as totalTarget \n        FROM financial_goals \n        WHERE isActive = 1 \n        GROUP BY goalType\n    ")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getTotalTargetByType(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.database.dao.GoalTypeTarget>> $completion);
    
    @androidx.room.Query(value = "\n        SELECT priority, COUNT(*) as count \n        FROM financial_goals \n        WHERE isActive = 1 \n        GROUP BY priority\n    ")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getActiveGoalCountsByPriority(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.database.dao.GoalPriorityCount>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}