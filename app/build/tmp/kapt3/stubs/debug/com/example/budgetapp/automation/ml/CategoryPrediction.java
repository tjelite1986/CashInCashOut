package com.example.budgetapp.automation.ml;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007\u00a2\u0006\u0002\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0013\u001a\u00020\u0005H\u00c6\u0003J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u00c6\u0003J\u000f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007H\u00c6\u0003J=\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007H\u00c6\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001a\u001a\u00020\u001bH\u00d6\u0001J\t\u0010\u001c\u001a\u00020\u0003H\u00d6\u0001R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\u001d"}, d2 = {"Lcom/example/budgetapp/automation/ml/CategoryPrediction;", "", "suggestedCategory", "", "confidence", "", "alternativeCategories", "", "Lcom/example/budgetapp/automation/ml/CategoryConfidence;", "reasoningFactors", "(Ljava/lang/String;DLjava/util/List;Ljava/util/List;)V", "getAlternativeCategories", "()Ljava/util/List;", "getConfidence", "()D", "getReasoningFactors", "getSuggestedCategory", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "CashInCashOut_debug"})
public final class CategoryPrediction {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String suggestedCategory = null;
    private final double confidence = 0.0;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.example.budgetapp.automation.ml.CategoryConfidence> alternativeCategories = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<java.lang.String> reasoningFactors = null;
    
    public CategoryPrediction(@org.jetbrains.annotations.NotNull
    java.lang.String suggestedCategory, double confidence, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.automation.ml.CategoryConfidence> alternativeCategories, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> reasoningFactors) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getSuggestedCategory() {
        return null;
    }
    
    public final double getConfidence() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.budgetapp.automation.ml.CategoryConfidence> getAlternativeCategories() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.String> getReasoningFactors() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String component1() {
        return null;
    }
    
    public final double component2() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.budgetapp.automation.ml.CategoryConfidence> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.String> component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.automation.ml.CategoryPrediction copy(@org.jetbrains.annotations.NotNull
    java.lang.String suggestedCategory, double confidence, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.automation.ml.CategoryConfidence> alternativeCategories, @org.jetbrains.annotations.NotNull
    java.util.List<java.lang.String> reasoningFactors) {
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