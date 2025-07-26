package com.example.budgetapp;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0006H\u0002J\u0018\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u0014H\u0002J\b\u0010\u001c\u001a\u00020\u0018H\u0002J\u0012\u0010\u001d\u001a\u00020\u00182\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J\b\u0010 \u001a\u00020\u000fH\u0016J\b\u0010!\u001a\u00020\u0018H\u0002J\b\u0010\"\u001a\u00020\u0018H\u0002J\b\u0010#\u001a\u00020\u0018H\u0002J\b\u0010$\u001a\u00020\u0018H\u0002J\b\u0010%\u001a\u00020\u0018H\u0002J\b\u0010&\u001a\u00020\u0018H\u0002J\u0010\u0010\'\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0006H\u0002J\b\u0010(\u001a\u00020\u0018H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2 = {"Lcom/example/budgetapp/AddIncomeActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/budgetapp/databinding/ActivityAddIncomeBinding;", "currentSuggestion", "Lcom/example/budgetapp/analytics/ai/CategorySuggestion;", "database", "Lcom/example/budgetapp/database/BudgetDatabase;", "dateFormat", "Ljava/text/SimpleDateFormat;", "editingIncomeId", "", "Ljava/lang/Long;", "hasUserSelectedCategory", "", "isEditing", "nlpService", "Lcom/example/budgetapp/analytics/ai/NLPCategorizationService;", "selectedCategory", "", "selectedDate", "selectedRecurringType", "applyCategorySuggestion", "", "suggestion", "learnFromUserCorrection", "actualCategory", "loadIncomeForEditing", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSupportNavigateUp", "performAutoCategorization", "saveIncome", "setupClickListeners", "setupNLPCategorization", "setupSpinners", "setupUI", "showCategorySuggestionFeedback", "showDatePicker", "CashInCashOut_debug"})
public final class AddIncomeActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.budgetapp.databinding.ActivityAddIncomeBinding binding;
    private com.example.budgetapp.database.BudgetDatabase database;
    private com.example.budgetapp.analytics.ai.NLPCategorizationService nlpService;
    private long selectedDate;
    @org.jetbrains.annotations.NotNull
    private java.lang.String selectedCategory = "L\u00f6n";
    @org.jetbrains.annotations.Nullable
    private java.lang.String selectedRecurringType;
    @org.jetbrains.annotations.NotNull
    private final java.text.SimpleDateFormat dateFormat = null;
    @org.jetbrains.annotations.Nullable
    private java.lang.Long editingIncomeId;
    private boolean isEditing = false;
    @org.jetbrains.annotations.Nullable
    private com.example.budgetapp.analytics.ai.CategorySuggestion currentSuggestion;
    private boolean hasUserSelectedCategory = false;
    
    public AddIncomeActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupUI() {
    }
    
    private final void setupSpinners() {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void showDatePicker() {
    }
    
    private final void loadIncomeForEditing() {
    }
    
    private final void saveIncome() {
    }
    
    @java.lang.Override
    public boolean onSupportNavigateUp() {
        return false;
    }
    
    /**
     * Setup NLP-based auto-categorization for income
     */
    private final void setupNLPCategorization() {
    }
    
    /**
     * Perform automatic categorization for income based on transaction text
     */
    private final void performAutoCategorization() {
    }
    
    /**
     * Apply the category suggestion to the UI
     */
    private final void applyCategorySuggestion(com.example.budgetapp.analytics.ai.CategorySuggestion suggestion) {
    }
    
    /**
     * Show feedback about the category suggestion
     */
    private final void showCategorySuggestionFeedback(com.example.budgetapp.analytics.ai.CategorySuggestion suggestion) {
    }
    
    /**
     * Learn from user correction to improve future categorization
     */
    private final void learnFromUserCorrection(com.example.budgetapp.analytics.ai.CategorySuggestion suggestion, java.lang.String actualCategory) {
    }
}