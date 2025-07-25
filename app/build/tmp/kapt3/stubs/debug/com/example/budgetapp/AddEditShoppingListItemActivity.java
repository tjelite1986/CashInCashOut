package com.example.budgetapp;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J+\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\u001bH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 J\b\u0010!\u001a\u00020\"H\u0002J\u0012\u0010#\u001a\u00020\"2\b\u0010$\u001a\u0004\u0018\u00010%H\u0014J\b\u0010&\u001a\u00020\"H\u0002J\b\u0010\'\u001a\u00020\"H\u0002J\b\u0010(\u001a\u00020\"H\u0002J\b\u0010)\u001a\u00020\"H\u0002J\b\u0010*\u001a\u00020\"H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000b0\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0013R\u001b\u0010\u0014\u001a\u00020\u00158BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u0017\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006+"}, d2 = {"Lcom/example/budgetapp/AddEditShoppingListItemActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/budgetapp/databinding/ActivityAddEditShoppingListItemBinding;", "isEditMode", "", "itemId", "", "selectProductLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "selectedProductId", "Ljava/lang/Long;", "shoppingListId", "units", "", "", "[Ljava/lang/String;", "viewModel", "Lcom/example/budgetapp/viewmodel/ShoppingListViewModel;", "getViewModel", "()Lcom/example/budgetapp/viewmodel/ShoppingListViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "calculateBestPriceForQuantity", "", "database", "Lcom/example/budgetapp/database/BudgetDatabase;", "productId", "quantity", "(Lcom/example/budgetapp/database/BudgetDatabase;JDLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadShoppingListItem", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "saveShoppingListItem", "setupClickListeners", "setupSpinners", "setupToolbar", "updatePriceBasedOnQuantity", "CashInCashOut_debug"})
public final class AddEditShoppingListItemActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.budgetapp.databinding.ActivityAddEditShoppingListItemBinding binding;
    private long shoppingListId = -1L;
    private long itemId = -1L;
    private boolean isEditMode = false;
    @org.jetbrains.annotations.Nullable
    private java.lang.Long selectedProductId;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String[] units = {"st", "kg", "g", "l", "ml", "cl", "f\u00f6rp", "burk", "p\u00e5se", "flaska"};
    @org.jetbrains.annotations.NotNull
    private final androidx.activity.result.ActivityResultLauncher<android.content.Intent> selectProductLauncher = null;
    
    public AddEditShoppingListItemActivity() {
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
    
    private final void setupSpinners() {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void loadShoppingListItem() {
    }
    
    private final void saveShoppingListItem() {
    }
    
    private final void updatePriceBasedOnQuantity() {
    }
    
    /**
     * Beräknar bästa pris för en given kvantitet av en produkt.
     * Tar hänsyn till om kampanjpris är fördelaktigt baserat på kvantitet.
     *
     * Exempel: Vara kostar 13,95 + 1kr pant, kampanj "2 för 20kr"
     * - 1 vara: 13,95 + 1 = 14,95 kr
     * - 2 varor: 20 + 2 = 22 kr (kampanj + pant per vara)
     * - 3 varor: 20 + 13,95 + 3 = 36,95 kr (kampanj för 2 + ordinarie för 1 + pant för alla)
     */
    private final java.lang.Object calculateBestPriceForQuantity(com.example.budgetapp.database.BudgetDatabase database, long productId, double quantity, kotlin.coroutines.Continuation<? super java.lang.Double> $completion) {
        return null;
    }
}