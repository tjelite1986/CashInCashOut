package com.example.budgetapp.database.entities;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b=\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u00d3\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\n\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0012\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0012\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0018\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0019\u001a\u00020\u001a\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u001c\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u001dJ\t\u0010?\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010A\u001a\u00020\u0012H\u00c6\u0003J\t\u0010B\u001a\u00020\u0012H\u00c6\u0003J\u0010\u0010C\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010&J\t\u0010D\u001a\u00020\u0012H\u00c6\u0003J\u0010\u0010E\u001a\u0004\u0018\u00010\nH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001fJ\u000b\u0010F\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010G\u001a\u00020\u0012H\u00c6\u0003J\t\u0010H\u001a\u00020\u001aH\u00c6\u0003J\t\u0010I\u001a\u00020\u0003H\u00c6\u0003J\t\u0010J\u001a\u00020\u0005H\u00c6\u0003J\t\u0010K\u001a\u00020\u0003H\u00c6\u0003J\u000b\u0010L\u001a\u0004\u0018\u00010\u0005H\u00c6\u0003J\t\u0010M\u001a\u00020\bH\u00c6\u0003J\t\u0010N\u001a\u00020\nH\u00c6\u0003J\t\u0010O\u001a\u00020\nH\u00c6\u0003J\u0010\u0010P\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010&J\u0010\u0010Q\u001a\u0004\u0018\u00010\nH\u00c6\u0003\u00a2\u0006\u0002\u0010\u001fJ\t\u0010R\u001a\u00020\u000fH\u00c6\u0003J\u00e4\u0001\u0010S\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00122\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00122\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0018\u001a\u00020\u00122\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u00032\b\b\u0002\u0010\u001c\u001a\u00020\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010TJ\u0013\u0010U\u001a\u00020\u00122\b\u0010V\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010W\u001a\u00020XH\u00d6\u0001J\t\u0010Y\u001a\u00020\u0005H\u00d6\u0001R\u0015\u0010\u0016\u001a\u0004\u0018\u00010\n\u00a2\u0006\n\n\u0002\u0010 \u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0015\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0015\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\'\u001a\u0004\b%\u0010&R\u0011\u0010\u001b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\u000b\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010$R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010)R\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\"R\u0011\u0010\u0013\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\"R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b0\u0010$R\u0015\u0010\r\u001a\u0004\u0018\u00010\n\u00a2\u0006\n\n\u0002\u0010 \u001a\u0004\b1\u0010\u001fR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010$R\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u0011\u00105\u001a\u00020\n8F\u00a2\u0006\u0006\u001a\u0004\b6\u0010+R\u0011\u00107\u001a\u00020\n8F\u00a2\u0006\u0006\u001a\u0004\b8\u0010+R\u0011\u0010\u0018\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b9\u0010\"R\u0011\u0010\u0019\u001a\u00020\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b<\u0010+R\u0015\u0010\f\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\'\u001a\u0004\b=\u0010&R\u0011\u0010\u001c\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b>\u0010)\u00a8\u0006Z"}, d2 = {"Lcom/example/budgetapp/database/entities/FinancialGoal;", "", "id", "", "name", "", "description", "goalType", "Lcom/example/budgetapp/database/entities/GoalType;", "targetAmount", "", "currentAmount", "targetDate", "monthlyTarget", "priority", "Lcom/example/budgetapp/database/entities/GoalPriority;", "category", "isActive", "", "isCompleted", "completedAt", "autoTransferEnabled", "autoTransferAmount", "linkedAccountId", "reminderEnabled", "reminderFrequency", "Lcom/example/budgetapp/database/entities/ReminderFrequency;", "createdAt", "updatedAt", "(JLjava/lang/String;Ljava/lang/String;Lcom/example/budgetapp/database/entities/GoalType;DDLjava/lang/Long;Ljava/lang/Double;Lcom/example/budgetapp/database/entities/GoalPriority;Ljava/lang/String;ZZLjava/lang/Long;ZLjava/lang/Double;Ljava/lang/String;ZLcom/example/budgetapp/database/entities/ReminderFrequency;JJ)V", "getAutoTransferAmount", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getAutoTransferEnabled", "()Z", "getCategory", "()Ljava/lang/String;", "getCompletedAt", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getCreatedAt", "()J", "getCurrentAmount", "()D", "getDescription", "getGoalType", "()Lcom/example/budgetapp/database/entities/GoalType;", "getId", "getLinkedAccountId", "getMonthlyTarget", "getName", "getPriority", "()Lcom/example/budgetapp/database/entities/GoalPriority;", "progressPercentage", "getProgressPercentage", "remainingAmount", "getRemainingAmount", "getReminderEnabled", "getReminderFrequency", "()Lcom/example/budgetapp/database/entities/ReminderFrequency;", "getTargetAmount", "getTargetDate", "getUpdatedAt", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JLjava/lang/String;Ljava/lang/String;Lcom/example/budgetapp/database/entities/GoalType;DDLjava/lang/Long;Ljava/lang/Double;Lcom/example/budgetapp/database/entities/GoalPriority;Ljava/lang/String;ZZLjava/lang/Long;ZLjava/lang/Double;Ljava/lang/String;ZLcom/example/budgetapp/database/entities/ReminderFrequency;JJ)Lcom/example/budgetapp/database/entities/FinancialGoal;", "equals", "other", "hashCode", "", "toString", "CashInCashOut_debug"})
@androidx.room.Entity(tableName = "financial_goals")
public final class FinancialGoal {
    @androidx.room.PrimaryKey(autoGenerate = true)
    private final long id = 0L;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String name = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String description = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.entities.GoalType goalType = null;
    private final double targetAmount = 0.0;
    private final double currentAmount = 0.0;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Long targetDate = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Double monthlyTarget = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.entities.GoalPriority priority = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String category = null;
    private final boolean isActive = false;
    private final boolean isCompleted = false;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Long completedAt = null;
    private final boolean autoTransferEnabled = false;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Double autoTransferAmount = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String linkedAccountId = null;
    private final boolean reminderEnabled = false;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.entities.ReminderFrequency reminderFrequency = null;
    private final long createdAt = 0L;
    private final long updatedAt = 0L;
    
    public FinancialGoal(long id, @org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.Nullable
    java.lang.String description, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.GoalType goalType, double targetAmount, double currentAmount, @org.jetbrains.annotations.Nullable
    java.lang.Long targetDate, @org.jetbrains.annotations.Nullable
    java.lang.Double monthlyTarget, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.GoalPriority priority, @org.jetbrains.annotations.Nullable
    java.lang.String category, boolean isActive, boolean isCompleted, @org.jetbrains.annotations.Nullable
    java.lang.Long completedAt, boolean autoTransferEnabled, @org.jetbrains.annotations.Nullable
    java.lang.Double autoTransferAmount, @org.jetbrains.annotations.Nullable
    java.lang.String linkedAccountId, boolean reminderEnabled, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.ReminderFrequency reminderFrequency, long createdAt, long updatedAt) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getDescription() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.GoalType getGoalType() {
        return null;
    }
    
    public final double getTargetAmount() {
        return 0.0;
    }
    
    public final double getCurrentAmount() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long getTargetDate() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double getMonthlyTarget() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.GoalPriority getPriority() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getCategory() {
        return null;
    }
    
    public final boolean isActive() {
        return false;
    }
    
    public final boolean isCompleted() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long getCompletedAt() {
        return null;
    }
    
    public final boolean getAutoTransferEnabled() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double getAutoTransferAmount() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getLinkedAccountId() {
        return null;
    }
    
    public final boolean getReminderEnabled() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.ReminderFrequency getReminderFrequency() {
        return null;
    }
    
    public final long getCreatedAt() {
        return 0L;
    }
    
    public final long getUpdatedAt() {
        return 0L;
    }
    
    public final double getProgressPercentage() {
        return 0.0;
    }
    
    public final double getRemainingAmount() {
        return 0.0;
    }
    
    public final long component1() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component10() {
        return null;
    }
    
    public final boolean component11() {
        return false;
    }
    
    public final boolean component12() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long component13() {
        return null;
    }
    
    public final boolean component14() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double component15() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component16() {
        return null;
    }
    
    public final boolean component17() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.ReminderFrequency component18() {
        return null;
    }
    
    public final long component19() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component2() {
        return null;
    }
    
    public final long component20() {
        return 0L;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.GoalType component4() {
        return null;
    }
    
    public final double component5() {
        return 0.0;
    }
    
    public final double component6() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Double component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.GoalPriority component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.FinancialGoal copy(long id, @org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.Nullable
    java.lang.String description, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.GoalType goalType, double targetAmount, double currentAmount, @org.jetbrains.annotations.Nullable
    java.lang.Long targetDate, @org.jetbrains.annotations.Nullable
    java.lang.Double monthlyTarget, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.GoalPriority priority, @org.jetbrains.annotations.Nullable
    java.lang.String category, boolean isActive, boolean isCompleted, @org.jetbrains.annotations.Nullable
    java.lang.Long completedAt, boolean autoTransferEnabled, @org.jetbrains.annotations.Nullable
    java.lang.Double autoTransferAmount, @org.jetbrains.annotations.Nullable
    java.lang.String linkedAccountId, boolean reminderEnabled, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.ReminderFrequency reminderFrequency, long createdAt, long updatedAt) {
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