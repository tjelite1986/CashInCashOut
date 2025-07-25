package com.example.budgetapp;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0010H\u0002J\u0011\u0010\u0014\u001a\u00020\u0010H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J\u0011\u0010\u0016\u001a\u00020\u0010H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J\u0012\u0010\u0017\u001a\u00020\u00102\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\b\u0010\u001a\u001a\u00020\u0010H\u0002J\b\u0010\u001b\u001a\u00020\u0010H\u0002J\b\u0010\u001c\u001a\u00020\u0010H\u0002J\u0010\u0010\u001d\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u0010 \u001a\u00020\u00102\u0006\u0010!\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\""}, d2 = {"Lcom/example/budgetapp/SmartRecommendationsActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/budgetapp/databinding/ActivitySmartRecommendationsBinding;", "currentRecommendations", "Lcom/example/budgetapp/data/ShoppingListRecommendations;", "recommendationAdapter", "Lcom/example/budgetapp/adapters/RecommendationAdapter;", "recommendationService", "Lcom/example/budgetapp/services/SmartRecommendationService;", "shoppingListId", "", "suggestionAdapter", "Lcom/example/budgetapp/adapters/SmartSuggestionAdapter;", "handleSuggestionClick", "", "suggestion", "Lcom/example/budgetapp/data/SmartSuggestion;", "loadData", "loadShoppingListRecommendations", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadSmartSuggestions", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupClickListeners", "setupRecyclerViews", "setupToolbar", "showErrorState", "message", "", "showOptimizedShoppingPlan", "recommendations", "CashInCashOut_debug"})
public final class SmartRecommendationsActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.budgetapp.databinding.ActivitySmartRecommendationsBinding binding;
    private com.example.budgetapp.services.SmartRecommendationService recommendationService;
    private com.example.budgetapp.adapters.SmartSuggestionAdapter suggestionAdapter;
    private com.example.budgetapp.adapters.RecommendationAdapter recommendationAdapter;
    private long shoppingListId = -1L;
    @org.jetbrains.annotations.Nullable
    private com.example.budgetapp.data.ShoppingListRecommendations currentRecommendations;
    
    public SmartRecommendationsActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupToolbar() {
    }
    
    private final void setupRecyclerViews() {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void loadData() {
    }
    
    private final java.lang.Object loadShoppingListRecommendations(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object loadSmartSuggestions(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final void handleSuggestionClick(com.example.budgetapp.data.SmartSuggestion suggestion) {
    }
    
    private final void showOptimizedShoppingPlan(com.example.budgetapp.data.ShoppingListRecommendations recommendations) {
    }
    
    private final void showErrorState(java.lang.String message) {
    }
}