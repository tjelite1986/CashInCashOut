package com.example.budgetapp.services;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u001e\b\u0086\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\t\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u001e\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\u0005H\u00c6\u0003J\t\u0010 \u001a\u00020\u0005H\u00c6\u0003J\t\u0010!\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\"\u001a\u00020\tH\u00c6\u0003J\t\u0010#\u001a\u00020\u000bH\u00c6\u0003J\t\u0010$\u001a\u00020\tH\u00c6\u0003J\u0010\u0010%\u001a\u0004\u0018\u00010\u000eH\u00c6\u0003\u00a2\u0006\u0002\u0010\u0013J`\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\t2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eH\u00c6\u0001\u00a2\u0006\u0002\u0010\'J\u0013\u0010(\u001a\u00020\t2\b\u0010)\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010*\u001a\u00020\u000eH\u00d6\u0001J\t\u0010+\u001a\u00020\u000bH\u00d6\u0001R\u0011\u0010\f\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0015\u0010\r\u001a\u0004\u0018\u00010\u000e\u00a2\u0006\n\n\u0002\u0010\u0014\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0018R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0011\u00a8\u0006,"}, d2 = {"Lcom/example/budgetapp/services/BudgetRolloverDetail;", "", "originalBudget", "Lcom/example/budgetapp/database/entities/Budget;", "spentAmount", "", "remainingAmount", "rolloverAmount", "wasRolledOver", "", "rolloverReason", "", "newBudgetCreated", "newBudgetId", "", "(Lcom/example/budgetapp/database/entities/Budget;DDDZLjava/lang/String;ZLjava/lang/Integer;)V", "getNewBudgetCreated", "()Z", "getNewBudgetId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getOriginalBudget", "()Lcom/example/budgetapp/database/entities/Budget;", "getRemainingAmount", "()D", "getRolloverAmount", "getRolloverReason", "()Ljava/lang/String;", "getSpentAmount", "getWasRolledOver", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Lcom/example/budgetapp/database/entities/Budget;DDDZLjava/lang/String;ZLjava/lang/Integer;)Lcom/example/budgetapp/services/BudgetRolloverDetail;", "equals", "other", "hashCode", "toString", "CashInCashOut_debug"})
public final class BudgetRolloverDetail {
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.entities.Budget originalBudget = null;
    private final double spentAmount = 0.0;
    private final double remainingAmount = 0.0;
    private final double rolloverAmount = 0.0;
    private final boolean wasRolledOver = false;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String rolloverReason = null;
    private final boolean newBudgetCreated = false;
    @org.jetbrains.annotations.Nullable
    private final java.lang.Integer newBudgetId = null;
    
    public BudgetRolloverDetail(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Budget originalBudget, double spentAmount, double remainingAmount, double rolloverAmount, boolean wasRolledOver, @org.jetbrains.annotations.NotNull
    java.lang.String rolloverReason, boolean newBudgetCreated, @org.jetbrains.annotations.Nullable
    java.lang.Integer newBudgetId) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.Budget getOriginalBudget() {
        return null;
    }
    
    public final double getSpentAmount() {
        return 0.0;
    }
    
    public final double getRemainingAmount() {
        return 0.0;
    }
    
    public final double getRolloverAmount() {
        return 0.0;
    }
    
    public final boolean getWasRolledOver() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getRolloverReason() {
        return null;
    }
    
    public final boolean getNewBudgetCreated() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer getNewBudgetId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.Budget component1() {
        return null;
    }
    
    public final double component2() {
        return 0.0;
    }
    
    public final double component3() {
        return 0.0;
    }
    
    public final double component4() {
        return 0.0;
    }
    
    public final boolean component5() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component6() {
        return null;
    }
    
    public final boolean component7() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Integer component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.services.BudgetRolloverDetail copy(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Budget originalBudget, double spentAmount, double remainingAmount, double rolloverAmount, boolean wasRolledOver, @org.jetbrains.annotations.NotNull
    java.lang.String rolloverReason, boolean newBudgetCreated, @org.jetbrains.annotations.Nullable
    java.lang.Integer newBudgetId) {
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