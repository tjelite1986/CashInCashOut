package com.example.budgetapp;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\"B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000fH\u0002J\b\u0010\u0016\u001a\u00020\u0014H\u0002J\b\u0010\u0017\u001a\u00020\u0014H\u0002J\u0012\u0010\u0018\u001a\u00020\u00142\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u0014H\u0002J\b\u0010\u001e\u001a\u00020\u0014H\u0002J\b\u0010\u001f\u001a\u00020\u0014H\u0002J\b\u0010 \u001a\u00020\u0014H\u0002J\u0010\u0010!\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/example/budgetapp/ProductPriceManagerActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/budgetapp/databinding/ActivityProductPriceManagerBinding;", "currentProduct", "Lcom/example/budgetapp/database/entities/Product;", "database", "Lcom/example/budgetapp/database/BudgetDatabase;", "priceAdapter", "Lcom/example/budgetapp/ProductPriceAdapter;", "productId", "", "productPrices", "", "Lcom/example/budgetapp/ProductPriceManagerActivity$ProductStoreWithStore;", "stores", "", "Lcom/example/budgetapp/database/entities/Store;", "editPrice", "", "productStoreWithStore", "loadData", "loadPrices", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSupportNavigateUp", "", "setupClickListeners", "setupRecyclerView", "setupUI", "showAddPriceDialog", "showDeletePriceDialog", "ProductStoreWithStore", "budget-debug_debug"})
public final class ProductPriceManagerActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.budgetapp.databinding.ActivityProductPriceManagerBinding binding;
    private com.example.budgetapp.database.BudgetDatabase database;
    private com.example.budgetapp.ProductPriceAdapter priceAdapter;
    private long productId = -1L;
    @org.jetbrains.annotations.Nullable
    private com.example.budgetapp.database.entities.Product currentProduct;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.example.budgetapp.database.entities.Store> stores;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.example.budgetapp.ProductPriceManagerActivity.ProductStoreWithStore> productPrices;
    
    public ProductPriceManagerActivity() {
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
    
    private final void setupClickListeners() {
    }
    
    private final void loadData() {
    }
    
    private final void loadPrices() {
    }
    
    private final void showAddPriceDialog() {
    }
    
    private final void editPrice(com.example.budgetapp.ProductPriceManagerActivity.ProductStoreWithStore productStoreWithStore) {
    }
    
    private final void showDeletePriceDialog(com.example.budgetapp.ProductPriceManagerActivity.ProductStoreWithStore productStoreWithStore) {
    }
    
    @java.lang.Override
    public boolean onSupportNavigateUp() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0005H\u00c6\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005H\u00c6\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0011\u001a\u00020\u0012H\u00d6\u0001J\t\u0010\u0013\u001a\u00020\u0014H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lcom/example/budgetapp/ProductPriceManagerActivity$ProductStoreWithStore;", "", "productStore", "Lcom/example/budgetapp/database/entities/ProductStore;", "store", "Lcom/example/budgetapp/database/entities/Store;", "(Lcom/example/budgetapp/database/entities/ProductStore;Lcom/example/budgetapp/database/entities/Store;)V", "getProductStore", "()Lcom/example/budgetapp/database/entities/ProductStore;", "getStore", "()Lcom/example/budgetapp/database/entities/Store;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "budget-debug_debug"})
    public static final class ProductStoreWithStore {
        @org.jetbrains.annotations.NotNull
        private final com.example.budgetapp.database.entities.ProductStore productStore = null;
        @org.jetbrains.annotations.NotNull
        private final com.example.budgetapp.database.entities.Store store = null;
        
        public ProductStoreWithStore(@org.jetbrains.annotations.NotNull
        com.example.budgetapp.database.entities.ProductStore productStore, @org.jetbrains.annotations.NotNull
        com.example.budgetapp.database.entities.Store store) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.budgetapp.database.entities.ProductStore getProductStore() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.budgetapp.database.entities.Store getStore() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.budgetapp.database.entities.ProductStore component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.budgetapp.database.entities.Store component2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.budgetapp.ProductPriceManagerActivity.ProductStoreWithStore copy(@org.jetbrains.annotations.NotNull
        com.example.budgetapp.database.entities.ProductStore productStore, @org.jetbrains.annotations.NotNull
        com.example.budgetapp.database.entities.Store store) {
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
}