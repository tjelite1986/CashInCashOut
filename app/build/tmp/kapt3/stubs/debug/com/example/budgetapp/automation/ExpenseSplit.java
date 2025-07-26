package com.example.budgetapp.automation;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B;\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\fJ\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0007H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\nH\u00c6\u0003J\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003\u00a2\u0006\u0002\u0010\u0012JL\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003H\u00c6\u0001\u00a2\u0006\u0002\u0010 J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010$\u001a\u00020%H\u00d6\u0001J\t\u0010&\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010\u0013\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\b\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000eR\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018\u00a8\u0006\'"}, d2 = {"Lcom/example/budgetapp/automation/ExpenseSplit;", "", "id", "", "participantId", "", "amount", "", "percentage", "status", "Lcom/example/budgetapp/automation/SplitStatus;", "paidAt", "(JLjava/lang/String;DDLcom/example/budgetapp/automation/SplitStatus;Ljava/lang/Long;)V", "getAmount", "()D", "getId", "()J", "getPaidAt", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getParticipantId", "()Ljava/lang/String;", "getPercentage", "getStatus", "()Lcom/example/budgetapp/automation/SplitStatus;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(JLjava/lang/String;DDLcom/example/budgetapp/automation/SplitStatus;Ljava/lang/Long;)Lcom/example/budgetapp/automation/ExpenseSplit;", "equals", "", "other", "hashCode", "", "toString", "CashInCashOut_debug"})
public final class ExpenseSplit {
    private final long id = 0L;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String participantId = null;
    private final double amount = 0.0;
    private final double percentage = 0.0;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.automation.SplitStatus status = null;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Long paidAt = null;
    
    public ExpenseSplit(long id, @org.jetbrains.annotations.NotNull
    java.lang.String participantId, double amount, double percentage, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.automation.SplitStatus status, @org.jetbrains.annotations.Nullable
    java.lang.Long paidAt) {
        super();
    }
    
    public final long getId() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getParticipantId() {
        return null;
    }
    
    public final double getAmount() {
        return 0.0;
    }
    
    public final double getPercentage() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.automation.SplitStatus getStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long getPaidAt() {
        return null;
    }
    
    public final long component1() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component2() {
        return null;
    }
    
    public final double component3() {
        return 0.0;
    }
    
    public final double component4() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.automation.SplitStatus component5() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Long component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.automation.ExpenseSplit copy(long id, @org.jetbrains.annotations.NotNull
    java.lang.String participantId, double amount, double percentage, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.automation.SplitStatus status, @org.jetbrains.annotations.Nullable
    java.lang.Long paidAt) {
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