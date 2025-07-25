package com.example.budgetapp.database.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0011\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0019\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ\u0019\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u001c\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00120\u000e2\u0006\u0010\u0004\u001a\u00020\u0005H\'J$\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00120\u000e2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0015H\'J\u001f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\u00122\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\u00120\u000e2\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u001b\u0010\u0019\u001a\u0004\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\u00120\u000e2\u0006\u0010\u001b\u001a\u00020\u0005H\'J\u0018\u0010\u001c\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001d0\u000e2\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0018\u0010\u001e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001d0\u000e2\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0019\u0010\u001f\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ%\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00050\u00122\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\t0\u0012H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\"J\u0019\u0010#\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\nJ+\u0010$\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010%\u001a\u00020\u001d2\b\b\u0002\u0010&\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\'J+\u0010(\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010)\u001a\u00020\u000f2\b\b\u0002\u0010&\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010*J5\u0010+\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010,\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010&\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010-\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006."}, d2 = {"Lcom/example/budgetapp/database/dao/ShoppingListItemDao;", "", "deleteAllItemsForShoppingList", "", "shoppingListId", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteShoppingListItem", "item", "Lcom/example/budgetapp/database/entities/ShoppingListItem;", "(Lcom/example/budgetapp/database/entities/ShoppingListItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteShoppingListItemById", "id", "getCompletedItemCountForShoppingList", "Landroidx/lifecycle/LiveData;", "", "getItemCountForShoppingList", "getItemsForShoppingList", "", "getItemsForShoppingListByStatus", "isCompleted", "", "getItemsForShoppingListSuspend", "getItemsWithProductForShoppingList", "Lcom/example/budgetapp/data/ShoppingListItemWithProduct;", "getShoppingListItemById", "getShoppingListItemsByProduct", "productId", "getTotalActualCostForShoppingList", "", "getTotalEstimatedCostForShoppingList", "insertShoppingListItem", "insertShoppingListItems", "items", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateShoppingListItem", "updateShoppingListItemActualPrice", "actualPrice", "updatedAt", "(JDJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateShoppingListItemQuantity", "quantity", "(JIJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateShoppingListItemStatus", "completedAt", "(JZLjava/lang/Long;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "CashInCashOut_debug"})
@androidx.room.Dao
public abstract interface ShoppingListItemDao {
    
    @androidx.room.Query(value = "SELECT * FROM shopping_list_items WHERE shoppingListId = :shoppingListId ORDER BY priority DESC, createdAt ASC")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<java.util.List<com.example.budgetapp.database.entities.ShoppingListItem>> getItemsForShoppingList(long shoppingListId);
    
    @androidx.room.Transaction
    @androidx.room.Query(value = "SELECT * FROM shopping_list_items WHERE shoppingListId = :shoppingListId ORDER BY priority DESC, createdAt ASC")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<java.util.List<com.example.budgetapp.data.ShoppingListItemWithProduct>> getItemsWithProductForShoppingList(long shoppingListId);
    
    @androidx.room.Query(value = "SELECT * FROM shopping_list_items WHERE shoppingListId = :shoppingListId AND isCompleted = :isCompleted ORDER BY priority DESC, createdAt ASC")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<java.util.List<com.example.budgetapp.database.entities.ShoppingListItem>> getItemsForShoppingListByStatus(long shoppingListId, boolean isCompleted);
    
    @androidx.room.Query(value = "SELECT * FROM shopping_list_items WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getShoppingListItemById(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.database.entities.ShoppingListItem> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM shopping_list_items WHERE productId = :productId")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<java.util.List<com.example.budgetapp.database.entities.ShoppingListItem>> getShoppingListItemsByProduct(long productId);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertShoppingListItem(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.ShoppingListItem item, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertShoppingListItems(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.database.entities.ShoppingListItem> items, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.Long>> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateShoppingListItem(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.ShoppingListItem item, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteShoppingListItem(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.ShoppingListItem item, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM shopping_list_items WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteShoppingListItemById(long id, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM shopping_list_items WHERE shoppingListId = :shoppingListId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteAllItemsForShoppingList(long shoppingListId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE shopping_list_items SET isCompleted = :isCompleted, completedAt = :completedAt, updatedAt = :updatedAt WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateShoppingListItemStatus(long id, boolean isCompleted, @org.jetbrains.annotations.Nullable
    java.lang.Long completedAt, long updatedAt, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE shopping_list_items SET quantity = :quantity, updatedAt = :updatedAt WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateShoppingListItemQuantity(long id, int quantity, long updatedAt, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE shopping_list_items SET actualPrice = :actualPrice, updatedAt = :updatedAt WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateShoppingListItemActualPrice(long id, double actualPrice, long updatedAt, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM shopping_list_items WHERE shoppingListId = :shoppingListId")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<java.lang.Integer> getItemCountForShoppingList(long shoppingListId);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM shopping_list_items WHERE shoppingListId = :shoppingListId AND isCompleted = 1")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<java.lang.Integer> getCompletedItemCountForShoppingList(long shoppingListId);
    
    @androidx.room.Query(value = "SELECT SUM(estimatedPrice * quantity) FROM shopping_list_items WHERE shoppingListId = :shoppingListId AND estimatedPrice IS NOT NULL")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<java.lang.Double> getTotalEstimatedCostForShoppingList(long shoppingListId);
    
    @androidx.room.Query(value = "SELECT SUM(actualPrice * quantity) FROM shopping_list_items WHERE shoppingListId = :shoppingListId AND actualPrice IS NOT NULL")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<java.lang.Double> getTotalActualCostForShoppingList(long shoppingListId);
    
    @androidx.room.Query(value = "SELECT * FROM shopping_list_items WHERE shoppingListId = :shoppingListId ORDER BY priority DESC, createdAt ASC")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getItemsForShoppingListSuspend(long shoppingListId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.database.entities.ShoppingListItem>> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}