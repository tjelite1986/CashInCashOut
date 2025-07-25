package com.example.budgetapp;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0019H\u0002J\b\u0010 \u001a\u00020!H\u0002J\u0012\u0010\"\u001a\u00020!2\b\u0010#\u001a\u0004\u0018\u00010$H\u0014J\b\u0010%\u001a\u00020\u0014H\u0016J\b\u0010&\u001a\u00020!H\u0002J\b\u0010\'\u001a\u00020!H\u0002J\b\u0010(\u001a\u00020!H\u0002J\b\u0010)\u001a\u00020!H\u0002J\b\u0010*\u001a\u00020!H\u0002J\b\u0010+\u001a\u00020!H\u0002J\b\u0010,\u001a\u00020!H\u0002J\b\u0010-\u001a\u00020!H\u0002J\b\u0010.\u001a\u00020!H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006/"}, d2 = {"Lcom/example/budgetapp/AddEditCategoryActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/budgetapp/databinding/ActivityAddEditCategoryBinding;", "categoryId", "", "categoryViewModel", "Lcom/example/budgetapp/viewmodels/CategoryViewModel;", "getCategoryViewModel", "()Lcom/example/budgetapp/viewmodels/CategoryViewModel;", "categoryViewModel$delegate", "Lkotlin/Lazy;", "colorAdapter", "Lcom/example/budgetapp/adapters/ColorPickerAdapter;", "currentCategory", "Lcom/example/budgetapp/database/entities/Category;", "iconAdapter", "Lcom/example/budgetapp/adapters/CategoryIconAdapter;", "isEditMode", "", "isSubcategory", "parentCategoryId", "Ljava/lang/Long;", "selectedColor", "", "selectedIcon", "selectedType", "Lcom/example/budgetapp/database/entities/CategoryType;", "getIconResource", "", "iconName", "loadCategoryData", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSupportNavigateUp", "saveCategory", "setupButtons", "setupColorPicker", "setupIconPicker", "setupToolbar", "setupTypeToggle", "updatePreview", "updateSubcategoryUI", "updateTypeButtonStyles", "CashInCashOut_debug"})
public final class AddEditCategoryActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.budgetapp.databinding.ActivityAddEditCategoryBinding binding;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy categoryViewModel$delegate = null;
    private long categoryId = -1L;
    private boolean isEditMode = false;
    @org.jetbrains.annotations.Nullable
    private com.example.budgetapp.database.entities.Category currentCategory;
    @org.jetbrains.annotations.NotNull
    private java.lang.String selectedColor;
    @org.jetbrains.annotations.NotNull
    private java.lang.String selectedIcon = "ic_category_default";
    @org.jetbrains.annotations.NotNull
    private com.example.budgetapp.database.entities.CategoryType selectedType = com.example.budgetapp.database.entities.CategoryType.EXPENSE;
    private boolean isSubcategory = false;
    @org.jetbrains.annotations.Nullable
    private java.lang.Long parentCategoryId;
    private com.example.budgetapp.adapters.ColorPickerAdapter colorAdapter;
    private com.example.budgetapp.adapters.CategoryIconAdapter iconAdapter;
    
    public AddEditCategoryActivity() {
        super();
    }
    
    private final com.example.budgetapp.viewmodels.CategoryViewModel getCategoryViewModel() {
        return null;
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupToolbar() {
    }
    
    private final void setupTypeToggle() {
    }
    
    private final void updateTypeButtonStyles() {
    }
    
    private final void setupColorPicker() {
    }
    
    private final void setupIconPicker() {
    }
    
    private final void setupButtons() {
    }
    
    private final void updateSubcategoryUI() {
    }
    
    private final void updatePreview() {
    }
    
    private final int getIconResource(java.lang.String iconName) {
        return 0;
    }
    
    private final void loadCategoryData() {
    }
    
    private final void saveCategory() {
    }
    
    @java.lang.Override
    public boolean onSupportNavigateUp() {
        return false;
    }
}