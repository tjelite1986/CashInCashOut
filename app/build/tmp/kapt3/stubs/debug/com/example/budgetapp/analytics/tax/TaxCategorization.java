package com.example.budgetapp.analytics.tax;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\n\u00a2\u0006\u0002\u0010\u000eJ\t\u0010\u001b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u001c\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0007H\u00c6\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u00c6\u0003J\t\u0010\u001f\u001a\u00020\fH\u00c6\u0003J\t\u0010 \u001a\u00020\nH\u00c6\u0003JK\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\nH\u00c6\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010%\u001a\u00020&H\u00d6\u0001J\t\u0010\'\u001a\u00020\nH\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u000b\u001a\u00020\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\r\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a\u00a8\u0006("}, d2 = {"Lcom/example/budgetapp/analytics/tax/TaxCategorization;", "", "expense", "Lcom/example/budgetapp/database/entities/Expense;", "suggestedCategory", "Lcom/example/budgetapp/analytics/tax/TaxCategory;", "confidence", "", "matchedKeywords", "", "", "deductibleAmount", "", "notes", "(Lcom/example/budgetapp/database/entities/Expense;Lcom/example/budgetapp/analytics/tax/TaxCategory;FLjava/util/List;DLjava/lang/String;)V", "getConfidence", "()F", "getDeductibleAmount", "()D", "getExpense", "()Lcom/example/budgetapp/database/entities/Expense;", "getMatchedKeywords", "()Ljava/util/List;", "getNotes", "()Ljava/lang/String;", "getSuggestedCategory", "()Lcom/example/budgetapp/analytics/tax/TaxCategory;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "CashInCashOut_debug"})
public final class TaxCategorization {
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.entities.Expense expense = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.analytics.tax.TaxCategory suggestedCategory = null;
    private final float confidence = 0.0F;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<java.lang.String> matchedKeywords = null;
    private final double deductibleAmount = 0.0;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String notes = null;
    
    public TaxCategorization(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Expense expense, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.tax.TaxCategory suggestedCategory, float confidence, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> matchedKeywords, double deductibleAmount, @org.jetbrains.annotations.NotNull
    java.lang.String notes) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.Expense getExpense() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.analytics.tax.TaxCategory getSuggestedCategory() {
        return null;
    }
    
    public final float getConfidence() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.String> getMatchedKeywords() {
        return null;
    }
    
    public final double getDeductibleAmount() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getNotes() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.Expense component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.analytics.tax.TaxCategory component2() {
        return null;
    }
    
    public final float component3() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.String> component4() {
        return null;
    }
    
    public final double component5() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.analytics.tax.TaxCategorization copy(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Expense expense, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.tax.TaxCategory suggestedCategory, float confidence, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> matchedKeywords, double deductibleAmount, @org.jetbrains.annotations.NotNull
    java.lang.String notes) {
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