package com.example.budgetapp.services;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001f\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ%\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ%\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\bH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000f"}, d2 = {"Lcom/example/budgetapp/services/ShoppingListOptimizer;", "", "database", "Lcom/example/budgetapp/database/BudgetDatabase;", "(Lcom/example/budgetapp/database/BudgetDatabase;)V", "calculateTotalSavings", "", "optimizedLists", "", "Lcom/example/budgetapp/data/OptimizedShoppingList;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findOptimalStoreCombination", "items", "Lcom/example/budgetapp/data/ShoppingListItemWithProduct;", "optimizeShoppingList", "CashInCashOut_release"})
public final class ShoppingListOptimizer {
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.BudgetDatabase database = null;
    
    public ShoppingListOptimizer(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.BudgetDatabase database) {
        super();
    }
    
    /**
     * Optimerar en shoppinglista baserat på billigaste priser per butik
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object optimizeShoppingList(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.data.ShoppingListItemWithProduct> items, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.data.OptimizedShoppingList>> $completion) {
        return null;
    }
    
    /**
     * Beräknar total besparing jämfört med att handla allt på genomsnittspriser
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object calculateTotalSavings(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.data.OptimizedShoppingList> optimizedLists, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion) {
        return null;
    }
    
    /**
     * Hittar den mest ekonomiska kombinationen av butiker
     * (för framtida implementering - för nu returnerar vi alla butiker)
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object findOptimalStoreCombination(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.data.ShoppingListItemWithProduct> items, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.data.OptimizedShoppingList>> $completion) {
        return null;
    }
}