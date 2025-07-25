package com.example.budgetapp;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u0011H\u0014J\u0010\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0011H\u0002J\b\u0010\u0019\u001a\u00020\u0011H\u0002J\b\u0010\u001a\u001a\u00020\u0011H\u0002J\b\u0010\u001b\u001a\u00020\u0011H\u0002J\u0018\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\r\u00a8\u0006!"}, d2 = {"Lcom/example/budgetapp/ShoppingListActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/example/budgetapp/utils/ThemeManager$ThemeChangeListener;", "()V", "adapter", "Lcom/example/budgetapp/adapters/ShoppingListAdapter;", "binding", "Lcom/example/budgetapp/databinding/ActivityShoppingListBinding;", "themeManager", "Lcom/example/budgetapp/utils/ThemeManager;", "viewModel", "Lcom/example/budgetapp/viewmodel/ShoppingListViewModel;", "getViewModel", "()Lcom/example/budgetapp/viewmodel/ShoppingListViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onThemeChanged", "settings", "Lcom/example/budgetapp/data/ThemeSettings;", "setupClickListeners", "setupObservers", "setupRecyclerView", "setupToolbar", "showDeleteConfirmationDialog", "shoppingListId", "", "listName", "", "CashInCashOut_release"})
public final class ShoppingListActivity extends androidx.appcompat.app.AppCompatActivity implements com.example.budgetapp.utils.ThemeManager.ThemeChangeListener {
    private com.example.budgetapp.databinding.ActivityShoppingListBinding binding;
    private com.example.budgetapp.adapters.ShoppingListAdapter adapter;
    private com.example.budgetapp.utils.ThemeManager themeManager;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy viewModel$delegate = null;
    
    public ShoppingListActivity() {
        super();
    }
    
    private final com.example.budgetapp.viewmodel.ShoppingListViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupToolbar() {
    }
    
    private final void setupRecyclerView() {
    }
    
    private final void setupObservers() {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void showDeleteConfirmationDialog(long shoppingListId, java.lang.String listName) {
    }
    
    @java.lang.Override
    protected void onDestroy() {
    }
    
    @java.lang.Override
    public void onThemeChanged(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.data.ThemeSettings settings) {
    }
}