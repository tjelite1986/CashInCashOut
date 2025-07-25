package com.example.budgetapp.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u0007J\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\t2\u0006\u0010\u0011\u001a\u00020\u0012J\u0016\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\t2\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0014\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u0007J\u000e\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0016\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\u0007R\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0017"}, d2 = {"Lcom/example/budgetapp/viewmodel/StoreViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/example/budgetapp/repository/StoreRepository;", "(Lcom/example/budgetapp/repository/StoreRepository;)V", "_store", "Landroidx/lifecycle/MutableLiveData;", "Lcom/example/budgetapp/database/entities/Store;", "store", "Landroidx/lifecycle/LiveData;", "getStore", "()Landroidx/lifecycle/LiveData;", "deleteStore", "", "getProductsForStore", "", "Lcom/example/budgetapp/database/entities/ProductStore;", "storeId", "", "getStoreById", "insertStore", "loadStoreDetails", "updateStore", "CashInCashOut_debug"})
public final class StoreViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.repository.StoreRepository repository = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.MutableLiveData<com.example.budgetapp.database.entities.Store> _store = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.lifecycle.LiveData<com.example.budgetapp.database.entities.Store> store = null;
    
    public StoreViewModel(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.repository.StoreRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.example.budgetapp.database.entities.Store> getStore() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.example.budgetapp.database.entities.Store> getStoreById(long storeId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.example.budgetapp.database.entities.ProductStore>> getProductsForStore(long storeId) {
        return null;
    }
    
    public final void loadStoreDetails(long storeId) {
    }
    
    public final void insertStore(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Store store) {
    }
    
    public final void updateStore(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Store store) {
    }
    
    public final void deleteStore(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Store store) {
    }
}