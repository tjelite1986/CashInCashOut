package com.example.budgetapp.activities;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0017\u001a\u00020\rH\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\b\u0010\u001c\u001a\u00020\u0019H\u0002J\b\u0010\u001d\u001a\u00020\u0019H\u0002J\u0012\u0010\u001e\u001a\u00020\u00192\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014J\u0010\u0010!\u001a\u00020\u00192\u0006\u0010\"\u001a\u00020\rH\u0002J\b\u0010#\u001a\u00020\u0019H\u0002J\b\u0010$\u001a\u00020\u0019H\u0002J\b\u0010%\u001a\u00020\u0019H\u0002J\b\u0010&\u001a\u00020\u0019H\u0002J\b\u0010\'\u001a\u00020\u0019H\u0002J\b\u0010(\u001a\u00020\u0019H\u0002J\u0010\u0010)\u001a\u00020\u00192\u0006\u0010*\u001a\u00020\u0010H\u0002J\b\u0010+\u001a\u00020\u0019H\u0002J\b\u0010,\u001a\u00020\u0019H\u0002J\b\u0010-\u001a\u00020\u0019H\u0002J\b\u0010.\u001a\u00020\u001bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\n \u0016*\u0004\u0018\u00010\u00150\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006/"}, d2 = {"Lcom/example/budgetapp/activities/AddEditBudgetActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/budgetapp/databinding/ActivityAddEditBudgetBinding;", "budgetId", "", "Ljava/lang/Integer;", "budgetRepository", "Lcom/example/budgetapp/repository/BudgetRepository;", "dateFormat", "Ljava/text/SimpleDateFormat;", "existingBudget", "Lcom/example/budgetapp/database/entities/Budget;", "expenseCategories", "", "", "periodOptions", "", "Lcom/example/budgetapp/database/entities/BudgetPeriod;", "selectedStartDate", "Ljava/util/Calendar;", "kotlin.jvm.PlatformType", "createBudgetFromInput", "handleBackPress", "", "hasUnsavedChanges", "", "initializeComponents", "loadBudgetData", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "populateFields", "budget", "saveBudget", "setupBackPressedHandler", "setupClickListeners", "setupDropdowns", "setupToolbar", "showDatePicker", "showSuccess", "message", "updateBudgetInfo", "updatePeriodDates", "updateStartDate", "validateInput", "CashInCashOut_debug"})
public final class AddEditBudgetActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.budgetapp.databinding.ActivityAddEditBudgetBinding binding;
    private com.example.budgetapp.repository.BudgetRepository budgetRepository;
    @org.jetbrains.annotations.Nullable
    private java.lang.Integer budgetId;
    @org.jetbrains.annotations.Nullable
    private com.example.budgetapp.database.entities.Budget existingBudget;
    private java.util.Calendar selectedStartDate;
    @org.jetbrains.annotations.NotNull
    private final java.text.SimpleDateFormat dateFormat = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<java.lang.String> expenseCategories = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.Map<java.lang.String, com.example.budgetapp.database.entities.BudgetPeriod> periodOptions = null;
    
    public AddEditBudgetActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initializeComponents() {
    }
    
    private final void setupToolbar() {
    }
    
    private final void setupDropdowns() {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void setupBackPressedHandler() {
    }
    
    private final void handleBackPress() {
    }
    
    private final void loadBudgetData() {
    }
    
    private final void populateFields(com.example.budgetapp.database.entities.Budget budget) {
    }
    
    private final void showDatePicker() {
    }
    
    private final void updateStartDate() {
    }
    
    private final void updatePeriodDates() {
    }
    
    private final void updateBudgetInfo() {
    }
    
    private final void saveBudget() {
    }
    
    private final boolean validateInput() {
        return false;
    }
    
    private final com.example.budgetapp.database.entities.Budget createBudgetFromInput() {
        return null;
    }
    
    private final void showSuccess(java.lang.String message) {
    }
    
    private final boolean hasUnsavedChanges() {
        return false;
    }
}