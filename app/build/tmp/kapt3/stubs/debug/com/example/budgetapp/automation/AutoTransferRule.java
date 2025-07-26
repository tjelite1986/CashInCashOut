package com.example.budgetapp.automation;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b+\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B}\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0015\u001a\u00020\f\u0012\u0006\u0010\u0016\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0017J\t\u0010-\u001a\u00020\u0003H\u00c6\u0003J\t\u0010.\u001a\u00020\u0013H\u00c6\u0003J\u0010\u0010/\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010%J\t\u00100\u001a\u00020\fH\u00c6\u0003J\t\u00101\u001a\u00020\u0003H\u00c6\u0003J\t\u00102\u001a\u00020\u0005H\u00c6\u0003J\t\u00103\u001a\u00020\u0005H\u00c6\u0003J\t\u00104\u001a\u00020\u0005H\u00c6\u0003J\t\u00105\u001a\u00020\u0005H\u00c6\u0003J\t\u00106\u001a\u00020\nH\u00c6\u0003J\t\u00107\u001a\u00020\fH\u00c6\u0003J\t\u00108\u001a\u00020\u000eH\u00c6\u0003J\u000f\u00109\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u00c6\u0003J\u0098\u0001\u0010:\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\b\b\u0002\u0010\u0012\u001a\u00020\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0015\u001a\u00020\f2\b\b\u0002\u0010\u0016\u001a\u00020\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010;J\u0013\u0010<\u001a\u00020\u00132\b\u0010=\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010>\u001a\u00020?H\u00d6\u0001J\t\u0010@\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0016\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\r\u001a\u00020\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001dR\u0011\u0010\u0012\u001a\u00020\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010#R\u0015\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010&\u001a\u0004\b$\u0010%R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u001fR\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\u001fR\u0011\u0010\b\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u001fR\u0011\u0010\u0015\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0019R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b+\u0010,\u00a8\u0006A"}, d2 = {"Lcom/example/budgetapp/automation/AutoTransferRule;", "", "id", "", "name", "", "description", "sourceAccountId", "targetAccountId", "transferType", "Lcom/example/budgetapp/automation/TransferType;", "amount", "", "frequency", "Lcom/example/budgetapp/automation/TransferFrequency;", "conditions", "", "Lcom/example/budgetapp/automation/TransferCondition;", "isActive", "", "lastExecuted", "totalTransferred", "createdAt", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/example/budgetapp/automation/TransferType;DLcom/example/budgetapp/automation/TransferFrequency;Ljava/util/List;ZLjava/lang/Long;DJ)V", "getAmount", "()D", "getConditions", "()Ljava/util/List;", "getCreatedAt", "()J", "getDescription", "()Ljava/lang/String;", "getFrequency", "()Lcom/example/budgetapp/automation/TransferFrequency;", "getId", "()Z", "getLastExecuted", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getName", "getSourceAccountId", "getTargetAccountId", "getTotalTransferred", "getTransferType", "()Lcom/example/budgetapp/automation/TransferType;", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/example/budgetapp/automation/TransferType;DLcom/example/budgetapp/automation/TransferFrequency;Ljava/util/List;ZLjava/lang/Long;DJ)Lcom/example/budgetapp/automation/AutoTransferRule;", "equals", "other", "hashCode", "", "toString", "CashInCashOut_debug"})
@androidx.room.Entity(tableName = "auto_transfer_rules")
public final class AutoTransferRule {
    @androidx.room.PrimaryKey(autoGenerate = true)
    private final long id = 0L;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String name = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String description = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String sourceAccountId = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String targetAccountId = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.automation.TransferType transferType = null;
    private final double amount = 0.0;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.automation.TransferFrequency frequency = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.example.budgetapp.automation.TransferCondition> conditions = null;
    private final boolean isActive = false;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Long lastExecuted = null;
    private final double totalTransferred = 0.0;
    private final long createdAt = 0L;
    
    public AutoTransferRule(long id, @org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.lang.String description, @org.jetbrains.annotations.NotNull
    java.lang.String sourceAccountId, @org.jetbrains.annotations.NotNull
    java.lang.String targetAccountId, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.automation.TransferType transferType, double amount, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.automation.TransferFrequency frequency, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.automation.TransferCondition> conditions, boolean isActive, @org.jetbrains.annotations.Nullable
    java.lang.Long lastExecuted, double totalTransferred, long createdAt) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getDescription() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getSourceAccountId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getTargetAccountId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.automation.TransferType getTransferType() {
        return null;
    }
    
    public final double getAmount() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.automation.TransferFrequency getFrequency() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.budgetapp.automation.TransferCondition> getConditions() {
        return null;
    }
    
    public final boolean isActive() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long getLastExecuted() {
        return null;
    }
    
    public final double getTotalTransferred() {
        return 0.0;
    }
    
    public final long getCreatedAt() {
        return 0L;
    }
    
    public final long component1() {
        return 0L;
    }
    
    public final boolean component10() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long component11() {
        return null;
    }
    
    public final double component12() {
        return 0.0;
    }
    
    public final long component13() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.automation.TransferType component6() {
        return null;
    }
    
    public final double component7() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.automation.TransferFrequency component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.budgetapp.automation.TransferCondition> component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.automation.AutoTransferRule copy(long id, @org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.lang.String description, @org.jetbrains.annotations.NotNull
    java.lang.String sourceAccountId, @org.jetbrains.annotations.NotNull
    java.lang.String targetAccountId, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.automation.TransferType transferType, double amount, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.automation.TransferFrequency frequency, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.automation.TransferCondition> conditions, boolean isActive, @org.jetbrains.annotations.Nullable
    java.lang.Long lastExecuted, double totalTransferred, long createdAt) {
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