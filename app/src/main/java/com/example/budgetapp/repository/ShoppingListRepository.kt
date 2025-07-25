package com.example.budgetapp.repository

import androidx.lifecycle.LiveData
import com.example.budgetapp.database.dao.ShoppingListDao
import com.example.budgetapp.database.dao.ShoppingListItemDao
import com.example.budgetapp.database.dao.ProductDao
import com.example.budgetapp.database.dao.StoreDao
import com.example.budgetapp.database.entities.ShoppingList
import com.example.budgetapp.database.entities.ShoppingListItem
import com.example.budgetapp.data.ShoppingListItemWithProduct

class ShoppingListRepository(
    private val shoppingListDao: ShoppingListDao,
    private val shoppingListItemDao: ShoppingListItemDao,
    private val productDao: ProductDao,
    private val storeDao: StoreDao
) {
    
    // Shopping List operations
    fun getAllShoppingLists(): LiveData<List<ShoppingList>> = shoppingListDao.getAllShoppingLists()
    
    fun getActiveShoppingLists(): LiveData<List<ShoppingList>> = shoppingListDao.getShoppingListsByStatus(false)
    
    fun getCompletedShoppingLists(): LiveData<List<ShoppingList>> = shoppingListDao.getShoppingListsByStatus(true)
    
    suspend fun getShoppingListById(id: Long): ShoppingList? = shoppingListDao.getShoppingListById(id)
    
    fun getShoppingListByIdLiveData(id: Long): LiveData<ShoppingList?> = shoppingListDao.getShoppingListByIdLiveData(id)
    
    suspend fun insertShoppingList(shoppingList: ShoppingList): Long = shoppingListDao.insertShoppingList(shoppingList)
    
    suspend fun updateShoppingList(shoppingList: ShoppingList) = shoppingListDao.updateShoppingList(shoppingList)
    
    suspend fun deleteShoppingList(shoppingList: ShoppingList) = shoppingListDao.deleteShoppingList(shoppingList)
    
    suspend fun deleteShoppingListById(id: Long) = shoppingListDao.deleteShoppingListById(id)
    
    suspend fun updateShoppingListStatus(id: Long, isCompleted: Boolean, completedAt: Long?) {
        shoppingListDao.updateShoppingListStatus(id, isCompleted, completedAt)
    }
    
    suspend fun updateShoppingListTotalCost(id: Long, totalCost: Double) {
        shoppingListDao.updateShoppingListTotalCost(id, totalCost)
    }
    
    fun getActiveShoppingListsCount(): LiveData<Int> = shoppingListDao.getActiveShoppingListsCount()
    
    fun getCompletedShoppingListsCount(): LiveData<Int> = shoppingListDao.getCompletedShoppingListsCount()
    
    // Shopping List Item operations
    fun getItemsForShoppingList(shoppingListId: Long): LiveData<List<ShoppingListItem>> = 
        shoppingListItemDao.getItemsForShoppingList(shoppingListId)
    
    fun getItemsWithProductForShoppingList(shoppingListId: Long): LiveData<List<ShoppingListItemWithProduct>> =
        shoppingListItemDao.getItemsWithProductForShoppingList(shoppingListId)
    
    fun getActiveItemsForShoppingList(shoppingListId: Long): LiveData<List<ShoppingListItem>> = 
        shoppingListItemDao.getItemsForShoppingListByStatus(shoppingListId, false)
    
    fun getCompletedItemsForShoppingList(shoppingListId: Long): LiveData<List<ShoppingListItem>> = 
        shoppingListItemDao.getItemsForShoppingListByStatus(shoppingListId, true)
    
    suspend fun getShoppingListItemById(id: Long): ShoppingListItem? = shoppingListItemDao.getShoppingListItemById(id)
    
    suspend fun insertShoppingListItem(item: ShoppingListItem): Long = shoppingListItemDao.insertShoppingListItem(item)
    
    suspend fun insertShoppingListItems(items: List<ShoppingListItem>): List<Long> = 
        shoppingListItemDao.insertShoppingListItems(items)
    
    suspend fun updateShoppingListItem(item: ShoppingListItem) = shoppingListItemDao.updateShoppingListItem(item)
    
    suspend fun deleteShoppingListItem(item: ShoppingListItem) = shoppingListItemDao.deleteShoppingListItem(item)
    
    suspend fun deleteShoppingListItemById(id: Long) = shoppingListItemDao.deleteShoppingListItemById(id)
    
    suspend fun deleteAllItemsForShoppingList(shoppingListId: Long) = 
        shoppingListItemDao.deleteAllItemsForShoppingList(shoppingListId)
    
    suspend fun updateShoppingListItemStatus(id: Long, isCompleted: Boolean, completedAt: Long?) {
        shoppingListItemDao.updateShoppingListItemStatus(id, isCompleted, completedAt)
    }
    
    suspend fun updateShoppingListItemQuantity(id: Long, quantity: Int) {
        shoppingListItemDao.updateShoppingListItemQuantity(id, quantity)
    }
    
    suspend fun updateShoppingListItemActualPrice(id: Long, actualPrice: Double) {
        shoppingListItemDao.updateShoppingListItemActualPrice(id, actualPrice)
    }
    
    fun getItemCountForShoppingList(shoppingListId: Long): LiveData<Int> = 
        shoppingListItemDao.getItemCountForShoppingList(shoppingListId)
    
    fun getCompletedItemCountForShoppingList(shoppingListId: Long): LiveData<Int> = 
        shoppingListItemDao.getCompletedItemCountForShoppingList(shoppingListId)
    
    fun getTotalEstimatedCostForShoppingList(shoppingListId: Long): LiveData<Double?> = 
        shoppingListItemDao.getTotalEstimatedCostForShoppingList(shoppingListId)
    
    fun getTotalActualCostForShoppingList(shoppingListId: Long): LiveData<Double?> = 
        shoppingListItemDao.getTotalActualCostForShoppingList(shoppingListId)
}