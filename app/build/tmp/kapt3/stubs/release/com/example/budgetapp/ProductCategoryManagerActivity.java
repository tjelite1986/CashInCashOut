package com.example.budgetapp;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\tH\u0002J\u0010\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\tH\u0002J\b\u0010\u0013\u001a\u00020\rH\u0002J\u0012\u0010\u0014\u001a\u00020\r2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\rH\u0002J\b\u0010\u001c\u001a\u00020\rH\u0002J\b\u0010\u001d\u001a\u00020\rH\u0002J\b\u0010\u001e\u001a\u00020\rH\u0002J\b\u0010\u001f\u001a\u00020\rH\u0002J\u0010\u0010 \u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\tH\u0002J\b\u0010!\u001a\u00020\rH\u0002J\u0018\u0010\"\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010#\u001a\u00020\u000fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Lcom/example/budgetapp/ProductCategoryManagerActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adapter", "Lcom/example/budgetapp/adapters/ProductCategoryAdapter;", "binding", "Lcom/example/budgetapp/databinding/ActivityProductCategoryManagerBinding;", "categories", "", "Lcom/example/budgetapp/database/entities/ProductCategory;", "database", "Lcom/example/budgetapp/database/BudgetDatabase;", "addCategory", "", "name", "", "confirmDeleteCategory", "category", "deleteCategory", "loadCategories", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "resetToDefaults", "setupClickListeners", "setupRecyclerView", "setupUI", "showAddCategoryDialog", "showEditCategoryDialog", "showResetToDefaultsDialog", "updateCategory", "newName", "CashInCashOut_release"})
public final class ProductCategoryManagerActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.budgetapp.databinding.ActivityProductCategoryManagerBinding binding;
    private com.example.budgetapp.database.BudgetDatabase database;
    private com.example.budgetapp.adapters.ProductCategoryAdapter adapter;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.example.budgetapp.database.entities.ProductCategory> categories;
    
    public ProductCategoryManagerActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupUI() {
    }
    
    private final void setupRecyclerView() {
    }
    
    private final void loadCategories() {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void showAddCategoryDialog() {
    }
    
    private final void showEditCategoryDialog(com.example.budgetapp.database.entities.ProductCategory category) {
    }
    
    private final void confirmDeleteCategory(com.example.budgetapp.database.entities.ProductCategory category) {
    }
    
    private final void showResetToDefaultsDialog() {
    }
    
    private final void addCategory(java.lang.String name) {
    }
    
    private final void updateCategory(com.example.budgetapp.database.entities.ProductCategory category, java.lang.String newName) {
    }
    
    private final void deleteCategory(com.example.budgetapp.database.entities.ProductCategory category) {
    }
    
    private final void resetToDefaults() {
    }
    
    @java.lang.Override
    public boolean onOptionsItemSelected(@org.jetbrains.annotations.NotNull
    android.view.MenuItem item) {
        return false;
    }
}