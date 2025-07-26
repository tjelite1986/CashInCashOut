package com.example.budgetapp.automation.ml;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u00a2\u0006\u0002\u0010\rJ\t\u0010\u0018\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001a\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001b\u001a\u00020\u0007H\u00c6\u0003J\u000f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u00c6\u0003J\t\u0010\u001d\u001a\u00020\fH\u00c6\u0003JK\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\b\b\u0002\u0010\u000b\u001a\u00020\fH\u00c6\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\"\u001a\u00020#H\u00d6\u0001J\t\u0010$\u001a\u00020%H\u00d6\u0001R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017\u00a8\u0006&"}, d2 = {"Lcom/example/budgetapp/automation/ml/UserProfile;", "", "monthlyIncome", "", "monthlyExpenses", "savingsRate", "riskTolerance", "Lcom/example/budgetapp/automation/ml/RiskTolerance;", "financialGoals", "", "Lcom/example/budgetapp/automation/ml/FinancialGoal;", "spendingPersonality", "Lcom/example/budgetapp/automation/ml/SpendingPersonality;", "(DDDLcom/example/budgetapp/automation/ml/RiskTolerance;Ljava/util/List;Lcom/example/budgetapp/automation/ml/SpendingPersonality;)V", "getFinancialGoals", "()Ljava/util/List;", "getMonthlyExpenses", "()D", "getMonthlyIncome", "getRiskTolerance", "()Lcom/example/budgetapp/automation/ml/RiskTolerance;", "getSavingsRate", "getSpendingPersonality", "()Lcom/example/budgetapp/automation/ml/SpendingPersonality;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "", "CashInCashOut_debug"})
public final class UserProfile {
    private final double monthlyIncome = 0.0;
    private final double monthlyExpenses = 0.0;
    private final double savingsRate = 0.0;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.automation.ml.RiskTolerance riskTolerance = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.example.budgetapp.automation.ml.FinancialGoal> financialGoals = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.automation.ml.SpendingPersonality spendingPersonality = null;
    
    public UserProfile(double monthlyIncome, double monthlyExpenses, double savingsRate, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.automation.ml.RiskTolerance riskTolerance, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.automation.ml.FinancialGoal> financialGoals, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.automation.ml.SpendingPersonality spendingPersonality) {
        super();
    }
    
    public final double getMonthlyIncome() {
        return 0.0;
    }
    
    public final double getMonthlyExpenses() {
        return 0.0;
    }
    
    public final double getSavingsRate() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.automation.ml.RiskTolerance getRiskTolerance() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.budgetapp.automation.ml.FinancialGoal> getFinancialGoals() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.automation.ml.SpendingPersonality getSpendingPersonality() {
        return null;
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
    public final com.example.budgetapp.automation.ml.RiskTolerance component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.budgetapp.automation.ml.FinancialGoal> component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.automation.ml.SpendingPersonality component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.automation.ml.UserProfile copy(double monthlyIncome, double monthlyExpenses, double savingsRate, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.automation.ml.RiskTolerance riskTolerance, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.automation.ml.FinancialGoal> financialGoals, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.automation.ml.SpendingPersonality spendingPersonality) {
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