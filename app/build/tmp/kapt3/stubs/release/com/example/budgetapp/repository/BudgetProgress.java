package com.example.budgetapp.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u00a2\u0006\u0002\u0010\u000bJ\t\u0010\u0013\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0015\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0017\u001a\u00020\tH\u00c6\u0003J\t\u0010\u0018\u001a\u00020\tH\u00c6\u0003JE\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\tH\u00c6\u0001J\u0013\u0010\u001a\u001a\u00020\t2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001J\t\u0010\u001e\u001a\u00020\u001fH\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\n\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000eR\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010\u00a8\u0006 "}, d2 = {"Lcom/example/budgetapp/repository/BudgetProgress;", "", "budget", "Lcom/example/budgetapp/database/entities/Budget;", "spentAmount", "", "remainingAmount", "percentage", "isOverBudget", "", "isNearLimit", "(Lcom/example/budgetapp/database/entities/Budget;DDDZZ)V", "getBudget", "()Lcom/example/budgetapp/database/entities/Budget;", "()Z", "getPercentage", "()D", "getRemainingAmount", "getSpentAmount", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "other", "hashCode", "", "toString", "", "CashInCashOut_release"})
public final class BudgetProgress {
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.entities.Budget budget = null;
    private final double spentAmount = 0.0;
    private final double remainingAmount = 0.0;
    private final double percentage = 0.0;
    private final boolean isOverBudget = false;
    private final boolean isNearLimit = false;
    
    public BudgetProgress(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Budget budget, double spentAmount, double remainingAmount, double percentage, boolean isOverBudget, boolean isNearLimit) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.Budget getBudget() {
        return null;
    }
    
    public final double getSpentAmount() {
        return 0.0;
    }
    
    public final double getRemainingAmount() {
        return 0.0;
    }
    
    public final double getPercentage() {
        return 0.0;
    }
    
    public final boolean isOverBudget() {
        return false;
    }
    
    public final boolean isNearLimit() {
        return false;
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
    
    public final boolean component6() {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.repository.BudgetProgress copy(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Budget budget, double spentAmount, double remainingAmount, double percentage, boolean isOverBudget, boolean isNearLimit) {
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