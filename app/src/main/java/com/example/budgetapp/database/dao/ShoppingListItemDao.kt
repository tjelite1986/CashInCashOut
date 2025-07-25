package com.example.budgetapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.budgetapp.database.entities.ShoppingListItem
import com.example.budgetapp.data.ShoppingListItemWithProduct

@Dao
interface ShoppingListItemDao {
    @Query("SELECT * FROM shopping_list_items WHERE shoppingListId = :shoppingListId ORDER BY priority DESC, createdAt ASC")
    fun getItemsForShoppingList(shoppingListId: Long): LiveData<List<ShoppingListItem>>
    
    @Transaction
    @Query("SELECT * FROM shopping_list_items WHERE shoppingListId = :shoppingListId ORDER BY priority DESC, createdAt ASC")
    fun getItemsWithProductForShoppingList(shoppingListId: Long): LiveData<List<ShoppingListItemWithProduct>>

    @Query("SELECT * FROM shopping_list_items WHERE shoppingListId = :shoppingListId AND isCompleted = :isCompleted ORDER BY priority DESC, createdAt ASC")
    fun getItemsForShoppingListByStatus(shoppingListId: Long, isCompleted: Boolean): LiveData<List<ShoppingListItem>>

    @Query("SELECT * FROM shopping_list_items WHERE id = :id")
    suspend fun getShoppingListItemById(id: Long): ShoppingListItem?

    @Query("SELECT * FROM shopping_list_items WHERE productId = :productId")
    fun getShoppingListItemsByProduct(productId: Long): LiveData<List<ShoppingListItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingListItem(item: ShoppingListItem): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingListItems(items: List<ShoppingListItem>): List<Long>

    @Update
    suspend fun updateShoppingListItem(item: ShoppingListItem)

    @Delete
    suspend fun deleteShoppingListItem(item: ShoppingListItem)

    @Query("DELETE FROM shopping_list_items WHERE id = :id")
    suspend fun deleteShoppingListItemById(id: Long)

    @Query("DELETE FROM shopping_list_items WHERE shoppingListId = :shoppingListId")
    suspend fun deleteAllItemsForShoppingList(shoppingListId: Long)

    @Query("UPDATE shopping_list_items SET isCompleted = :isCompleted, completedAt = :completedAt, updatedAt = :updatedAt WHERE id = :id")
    suspend fun updateShoppingListItemStatus(id: Long, isCompleted: Boolean, completedAt: Long?, updatedAt: Long = System.currentTimeMillis())

    @Query("UPDATE shopping_list_items SET quantity = :quantity, updatedAt = :updatedAt WHERE id = :id")
    suspend fun updateShoppingListItemQuantity(id: Long, quantity: Int, updatedAt: Long = System.currentTimeMillis())

    @Query("UPDATE shopping_list_items SET actualPrice = :actualPrice, updatedAt = :updatedAt WHERE id = :id")
    suspend fun updateShoppingListItemActualPrice(id: Long, actualPrice: Double, updatedAt: Long = System.currentTimeMillis())

    @Query("SELECT COUNT(*) FROM shopping_list_items WHERE shoppingListId = :shoppingListId")
    fun getItemCountForShoppingList(shoppingListId: Long): LiveData<Int>

    @Query("SELECT COUNT(*) FROM shopping_list_items WHERE shoppingListId = :shoppingListId AND isCompleted = 1")
    fun getCompletedItemCountForShoppingList(shoppingListId: Long): LiveData<Int>

    @Query("SELECT SUM(estimatedPrice * quantity) FROM shopping_list_items WHERE shoppingListId = :shoppingListId AND estimatedPrice IS NOT NULL")
    fun getTotalEstimatedCostForShoppingList(shoppingListId: Long): LiveData<Double?>

    @Query("SELECT SUM(actualPrice * quantity) FROM shopping_list_items WHERE shoppingListId = :shoppingListId AND actualPrice IS NOT NULL")
    fun getTotalActualCostForShoppingList(shoppingListId: Long): LiveData<Double?>
    
    // Suspend functions for background thread access
    @Query("SELECT * FROM shopping_list_items WHERE shoppingListId = :shoppingListId ORDER BY priority DESC, createdAt ASC")
    suspend fun getItemsForShoppingListSuspend(shoppingListId: Long): List<ShoppingListItem>
}