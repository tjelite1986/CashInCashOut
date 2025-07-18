package com.example.budgetapp;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010\u0018\u001a\u00020\u0017H\u0002J\u0012\u0010\u0019\u001a\u00020\u00172\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\rH\u0002J\b\u0010 \u001a\u00020\u0017H\u0002J\b\u0010!\u001a\u00020\u0017H\u0002J\b\u0010\"\u001a\u00020\u0017H\u0002J\b\u0010#\u001a\u00020\u0017H\u0002J\b\u0010$\u001a\u00020\u0017H\u0002R\u001c\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lcom/example/budgetapp/EditProductActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "barcodeScannerLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "binding", "Lcom/example/budgetapp/databinding/ActivityEditProductBinding;", "categories", "", "Lcom/example/budgetapp/database/entities/Category;", "currentProduct", "Lcom/example/budgetapp/database/entities/Product;", "database", "Lcom/example/budgetapp/database/BudgetDatabase;", "productId", "", "selectedCategoryId", "Ljava/lang/Long;", "selectedUnit", "", "loadCategories", "", "loadProduct", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSupportNavigateUp", "", "populateForm", "product", "saveProduct", "setupCategorySpinner", "setupClickListeners", "setupUI", "setupUnitSpinner", "budget-debug_debug"})
public final class EditProductActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.budgetapp.databinding.ActivityEditProductBinding binding;
    private com.example.budgetapp.database.BudgetDatabase database;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.example.budgetapp.database.entities.Category> categories;
    @org.jetbrains.annotations.Nullable
    private java.lang.Long selectedCategoryId;
    @org.jetbrains.annotations.NotNull
    private java.lang.String selectedUnit = "st";
    private long productId = -1L;
    @org.jetbrains.annotations.Nullable
    private com.example.budgetapp.database.entities.Product currentProduct;
    @org.jetbrains.annotations.NotNull
    private final androidx.activity.result.ActivityResultLauncher<android.content.Intent> barcodeScannerLauncher = null;
    
    public EditProductActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupUI() {
    }
    
    private final void loadCategories() {
    }
    
    private final void setupCategorySpinner() {
    }
    
    private final void setupUnitSpinner() {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void loadProduct() {
    }
    
    private final void populateForm(com.example.budgetapp.database.entities.Product product) {
    }
    
    private final void saveProduct() {
    }
    
    @java.lang.Override
    public boolean onSupportNavigateUp() {
        return false;
    }
}