package com.example.budgetapp;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016H\u0002J\b\u0010\u0018\u001a\u00020\u0014H\u0002J\u0012\u0010\u0019\u001a\u00020\u00142\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u0014H\u0002J\b\u0010\u001f\u001a\u00020\u0014H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\fR\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010\u00a8\u0006 "}, d2 = {"Lcom/example/budgetapp/PriceHistoryActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/budgetapp/databinding/ActivityPriceHistoryBinding;", "currentProduct", "Lcom/example/budgetapp/database/entities/Product;", "database", "Lcom/example/budgetapp/database/BudgetDatabase;", "productId", "", "storeId", "Ljava/lang/Long;", "viewModel", "Lcom/example/budgetapp/viewmodel/ShoppingListViewModel;", "getViewModel", "()Lcom/example/budgetapp/viewmodel/ShoppingListViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "displayPriceChart", "", "priceHistory", "", "Lcom/example/budgetapp/database/entities/PriceHistory;", "loadData", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSupportNavigateUp", "", "setupChart", "setupToolbar", "CashInCashOut_debug"})
public final class PriceHistoryActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.budgetapp.databinding.ActivityPriceHistoryBinding binding;
    private com.example.budgetapp.database.BudgetDatabase database;
    private long productId = -1L;
    @org.jetbrains.annotations.Nullable
    private java.lang.Long storeId;
    @org.jetbrains.annotations.Nullable
    private com.example.budgetapp.database.entities.Product currentProduct;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy viewModel$delegate = null;
    
    public PriceHistoryActivity() {
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
    
    private final void setupChart() {
    }
    
    private final void loadData() {
    }
    
    private final void displayPriceChart(java.util.List<com.example.budgetapp.database.entities.PriceHistory> priceHistory) {
    }
    
    @java.lang.Override
    public boolean onSupportNavigateUp() {
        return false;
    }
}