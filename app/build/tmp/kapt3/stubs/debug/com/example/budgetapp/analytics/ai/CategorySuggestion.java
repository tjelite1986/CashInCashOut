package com.example.budgetapp.analytics.ai;

/**
 * Data class representing a categorization suggestion
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\b\u00a2\u0006\u0002\u0010\nJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003H\u00c6\u0003J\t\u0010\u0014\u001a\u00020\u0005H\u00c6\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J\t\u0010\u0016\u001a\u00020\bH\u00c6\u0003J9\u0010\u0017\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\bH\u00c6\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001b\u001a\u00020\u001cH\u00d6\u0001J\t\u0010\u001d\u001a\u00020\bH\u00d6\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\t\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\u00a8\u0006\u001e"}, d2 = {"Lcom/example/budgetapp/analytics/ai/CategorySuggestion;", "", "category", "Lcom/example/budgetapp/database/entities/Category;", "confidence", "", "matchedKeywords", "", "", "reasoning", "(Lcom/example/budgetapp/database/entities/Category;FLjava/util/List;Ljava/lang/String;)V", "getCategory", "()Lcom/example/budgetapp/database/entities/Category;", "getConfidence", "()F", "getMatchedKeywords", "()Ljava/util/List;", "getReasoning", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "CashInCashOut_debug"})
public final class CategorySuggestion {
    @org.jetbrains.annotations.Nullable
    private final com.example.budgetapp.database.entities.Category category = null;
    private final float confidence = 0.0F;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<java.lang.String> matchedKeywords = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String reasoning = null;
    
    public CategorySuggestion(@org.jetbrains.annotations.Nullable
    com.example.budgetapp.database.entities.Category category, float confidence, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> matchedKeywords, @org.jetbrains.annotations.NotNull
    java.lang.String reasoning) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.example.budgetapp.database.entities.Category getCategory() {
        return null;
    }
    
    public final float getConfidence() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.String> getMatchedKeywords() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getReasoning() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.example.budgetapp.database.entities.Category component1() {
        return null;
    }
    
    public final float component2() {
        return 0.0F;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.String> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.analytics.ai.CategorySuggestion copy(@org.jetbrains.annotations.Nullable
    com.example.budgetapp.database.entities.Category category, float confidence, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> matchedKeywords, @org.jetbrains.annotations.NotNull
    java.lang.String reasoning) {
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