package com.example.budgetapp.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0019\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u0019\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J\u0019\u0010\u0014\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u000eH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u0019\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019J\u0019\u0010\u001a\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u000eH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u001a\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u001d0\u001c2\u0006\u0010\r\u001a\u00020\u000eJ\u0012\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u001d0\u001cJ\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u001cJ\u0012\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u001d0\u001cJ\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020 0\u001c2\u0006\u0010\r\u001a\u00020\u000eJ\u001a\u0010#\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u001d0\u001c2\u0006\u0010\r\u001a\u00020\u000eJ\u0012\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u001d0\u001cJ\f\u0010%\u001a\b\u0012\u0004\u0012\u00020 0\u001cJ\u0014\u0010&\u001a\b\u0012\u0004\u0012\u00020 0\u001c2\u0006\u0010\r\u001a\u00020\u000eJ\u001a\u0010\'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u001d0\u001c2\u0006\u0010\r\u001a\u00020\u000eJ\u001a\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0\u001d0\u001c2\u0006\u0010\r\u001a\u00020\u000eJ\u001b\u0010*\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0015\u001a\u00020\u000eH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010+\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u001c2\u0006\u0010\u0015\u001a\u00020\u000eJ\u001b\u0010,\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0015\u001a\u00020\u000eH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010-\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010.0\u001c2\u0006\u0010\r\u001a\u00020\u000eJ\u0016\u0010/\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010.0\u001c2\u0006\u0010\r\u001a\u00020\u000eJ\u0019\u00100\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J\u0019\u00101\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0018H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019J%\u00102\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001d2\f\u00103\u001a\b\u0012\u0004\u0012\u00020\u00180\u001dH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00104J\u0019\u00105\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J\u0019\u00106\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0019J!\u00107\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u00108\u001a\u00020.H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00109J!\u0010:\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010;\u001a\u00020 H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010<J+\u0010=\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010>\u001a\u00020?2\b\u0010@\u001a\u0004\u0018\u00010\u000eH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010AJ+\u0010B\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010>\u001a\u00020?2\b\u0010@\u001a\u0004\u0018\u00010\u000eH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010AJ!\u0010C\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010D\u001a\u00020.H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00109R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006E"}, d2 = {"Lcom/example/budgetapp/repository/ShoppingListRepository;", "", "shoppingListDao", "Lcom/example/budgetapp/database/dao/ShoppingListDao;", "shoppingListItemDao", "Lcom/example/budgetapp/database/dao/ShoppingListItemDao;", "productDao", "Lcom/example/budgetapp/database/dao/ProductDao;", "storeDao", "Lcom/example/budgetapp/database/dao/StoreDao;", "(Lcom/example/budgetapp/database/dao/ShoppingListDao;Lcom/example/budgetapp/database/dao/ShoppingListItemDao;Lcom/example/budgetapp/database/dao/ProductDao;Lcom/example/budgetapp/database/dao/StoreDao;)V", "deleteAllItemsForShoppingList", "", "shoppingListId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteShoppingList", "shoppingList", "Lcom/example/budgetapp/database/entities/ShoppingList;", "(Lcom/example/budgetapp/database/entities/ShoppingList;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteShoppingListById", "id", "deleteShoppingListItem", "item", "Lcom/example/budgetapp/database/entities/ShoppingListItem;", "(Lcom/example/budgetapp/database/entities/ShoppingListItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteShoppingListItemById", "getActiveItemsForShoppingList", "Landroidx/lifecycle/LiveData;", "", "getActiveShoppingLists", "getActiveShoppingListsCount", "", "getAllShoppingLists", "getCompletedItemCountForShoppingList", "getCompletedItemsForShoppingList", "getCompletedShoppingLists", "getCompletedShoppingListsCount", "getItemCountForShoppingList", "getItemsForShoppingList", "getItemsWithProductForShoppingList", "Lcom/example/budgetapp/data/ShoppingListItemWithProduct;", "getShoppingListById", "getShoppingListByIdLiveData", "getShoppingListItemById", "getTotalActualCostForShoppingList", "", "getTotalEstimatedCostForShoppingList", "insertShoppingList", "insertShoppingListItem", "insertShoppingListItems", "items", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateShoppingList", "updateShoppingListItem", "updateShoppingListItemActualPrice", "actualPrice", "(JDLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateShoppingListItemQuantity", "quantity", "(JILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateShoppingListItemStatus", "isCompleted", "", "completedAt", "(JZLjava/lang/Long;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateShoppingListStatus", "updateShoppingListTotalCost", "totalCost", "CashInCashOut_release"})
public final class ShoppingListRepository {
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.dao.ShoppingListDao shoppingListDao = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.dao.ShoppingListItemDao shoppingListItemDao = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.dao.ProductDao productDao = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.dao.StoreDao storeDao = null;
    
    public ShoppingListRepository(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.dao.ShoppingListDao shoppingListDao, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.dao.ShoppingListItemDao shoppingListItemDao, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.dao.ProductDao productDao, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.dao.StoreDao storeDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.example.budgetapp.database.entities.ShoppingList>> getAllShoppingLists() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.example.budgetapp.database.entities.ShoppingList>> getActiveShoppingLists() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.example.budgetapp.database.entities.ShoppingList>> getCompletedShoppingLists() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getShoppingListById(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.database.entities.ShoppingList> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<com.example.budgetapp.database.entities.ShoppingList> getShoppingListByIdLiveData(long id) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object insertShoppingList(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.ShoppingList shoppingList, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object updateShoppingList(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.ShoppingList shoppingList, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteShoppingList(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.ShoppingList shoppingList, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteShoppingListById(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object updateShoppingListStatus(long id, boolean isCompleted, @org.jetbrains.annotations.Nullable
    java.lang.Long completedAt, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object updateShoppingListTotalCost(long id, double totalCost, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.Integer> getActiveShoppingListsCount() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.Integer> getCompletedShoppingListsCount() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.example.budgetapp.database.entities.ShoppingListItem>> getItemsForShoppingList(long shoppingListId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.example.budgetapp.data.ShoppingListItemWithProduct>> getItemsWithProductForShoppingList(long shoppingListId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.example.budgetapp.database.entities.ShoppingListItem>> getActiveItemsForShoppingList(long shoppingListId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.util.List<com.example.budgetapp.database.entities.ShoppingListItem>> getCompletedItemsForShoppingList(long shoppingListId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getShoppingListItemById(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.database.entities.ShoppingListItem> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object insertShoppingListItem(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.ShoppingListItem item, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object insertShoppingListItems(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.database.entities.ShoppingListItem> items, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.Long>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object updateShoppingListItem(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.ShoppingListItem item, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteShoppingListItem(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.ShoppingListItem item, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteShoppingListItemById(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object deleteAllItemsForShoppingList(long shoppingListId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object updateShoppingListItemStatus(long id, boolean isCompleted, @org.jetbrains.annotations.Nullable
    java.lang.Long completedAt, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object updateShoppingListItemQuantity(long id, int quantity, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object updateShoppingListItemActualPrice(long id, double actualPrice, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.Integer> getItemCountForShoppingList(long shoppingListId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.Integer> getCompletedItemCountForShoppingList(long shoppingListId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.Double> getTotalEstimatedCostForShoppingList(long shoppingListId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.LiveData<java.lang.Double> getTotalActualCostForShoppingList(long shoppingListId) {
        return null;
    }
}