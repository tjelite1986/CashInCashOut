package com.example.budgetapp.automation.ml;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u001a\b\u0002\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003\u0012\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b0\u0003\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0004J\u0018\u0010\u000e\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0004H\u0002J\u001b\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003H\u00c2\u0003J\u0015\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b0\u0003H\u00c2\u0003J;\u0010\u0011\u001a\u00020\u00002\u001a\b\u0002\u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u00032\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b0\u0003H\u00c6\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00040\u00162\u0006\u0010\f\u001a\u00020\u0006J\t\u0010\u0017\u001a\u00020\u0018H\u00d6\u0001J\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00162\u0006\u0010\f\u001a\u00020\u0006J\t\u0010\u001b\u001a\u00020\u0004H\u00d6\u0001J\u0006\u0010\u001c\u001a\u00020\u000bR \u0010\u0002\u001a\u0014\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b0\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/example/budgetapp/automation/ml/CategorizationModel;", "", "categoryFeatures", "", "", "", "Lcom/example/budgetapp/automation/ml/TransactionFeatures;", "categoryProbabilities", "", "(Ljava/util/Map;Ljava/util/Map;)V", "addTrainingData", "", "features", "category", "calculateCategoryConfidence", "component1", "component2", "copy", "equals", "", "other", "getReasoningFactors", "", "hashCode", "", "predict", "Lcom/example/budgetapp/automation/ml/CategoryConfidence;", "toString", "train", "CashInCashOut_debug"})
public final class CategorizationModel {
    @org.jetbrains.annotations.NotNull
    private final java.util.Map<java.lang.String, java.util.List<com.example.budgetapp.automation.ml.TransactionFeatures>> categoryFeatures = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.Map<java.lang.String, java.lang.Double> categoryProbabilities = null;
    
    public CategorizationModel(@org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, java.util.List<com.example.budgetapp.automation.ml.TransactionFeatures>> categoryFeatures, @org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, java.lang.Double> categoryProbabilities) {
        super();
    }
    
    public final void addTrainingData(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.automation.ml.TransactionFeatures features, @org.jetbrains.annotations.NotNull
    java.lang.String category) {
    }
    
    public final void train() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.budgetapp.automation.ml.CategoryConfidence> predict(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.automation.ml.TransactionFeatures features) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<java.lang.String> getReasoningFactors(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.automation.ml.TransactionFeatures features) {
        return null;
    }
    
    private final double calculateCategoryConfidence(com.example.budgetapp.automation.ml.TransactionFeatures features, java.lang.String category) {
        return 0.0;
    }
    
    public CategorizationModel() {
        super();
    }
    
    private final java.util.Map<java.lang.String, java.util.List<com.example.budgetapp.automation.ml.TransactionFeatures>> component1() {
        return null;
    }
    
    private final java.util.Map<java.lang.String, java.lang.Double> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.automation.ml.CategorizationModel copy(@org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, java.util.List<com.example.budgetapp.automation.ml.TransactionFeatures>> categoryFeatures, @org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, java.lang.Double> categoryProbabilities) {
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