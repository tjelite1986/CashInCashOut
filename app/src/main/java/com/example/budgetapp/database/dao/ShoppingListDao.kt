package com.example.budgetapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.budgetapp.database.entities.ShoppingList

@Dao
interface ShoppingListDao {
    @Query("SELECT * FROM shopping_lists ORDER BY createdAt DESC")
    fun getAllShoppingLists(): LiveData<List<ShoppingList>>

    @Query("SELECT * FROM shopping_lists WHERE isCompleted = :isCompleted ORDER BY createdAt DESC")
    fun getShoppingListsByStatus(isCompleted: Boolean): LiveData<List<ShoppingList>>

    @Query("SELECT * FROM shopping_lists WHERE id = :id")
    suspend fun getShoppingListById(id: Long): ShoppingList?

    @Query("SELECT * FROM shopping_lists WHERE id = :id")
    fun getShoppingListByIdLiveData(id: Long): LiveData<ShoppingList?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingList(shoppingList: ShoppingList): Long

    @Update
    suspend fun updateShoppingList(shoppingList: ShoppingList)

    @Delete
    suspend fun deleteShoppingList(shoppingList: ShoppingList)

    @Query("DELETE FROM shopping_lists WHERE id = :id")
    suspend fun deleteShoppingListById(id: Long)

    @Query("UPDATE shopping_lists SET isCompleted = :isCompleted, completedAt = :completedAt, updatedAt = :updatedAt WHERE id = :id")
    suspend fun updateShoppingListStatus(id: Long, isCompleted: Boolean, completedAt: Long?, updatedAt: Long = System.currentTimeMillis())

    @Query("UPDATE shopping_lists SET totalEstimatedCost = :totalCost, updatedAt = :updatedAt WHERE id = :id")
    suspend fun updateShoppingListTotalCost(id: Long, totalCost: Double, updatedAt: Long = System.currentTimeMillis())

    @Query("SELECT COUNT(*) FROM shopping_lists WHERE isCompleted = 0")
    fun getActiveShoppingListsCount(): LiveData<Int>

    @Query("SELECT COUNT(*) FROM shopping_lists WHERE isCompleted = 1")
    fun getCompletedShoppingListsCount(): LiveData<Int>
    
    // Suspend functions for background thread access
    @Query("SELECT * FROM shopping_lists WHERE isCompleted = :isCompleted ORDER BY createdAt DESC")
    suspend fun getShoppingListsByStatusSuspend(isCompleted: Boolean): List<ShoppingList>
}