package com.example.budgetapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgetapp.database.entities.ShoppingList
import com.example.budgetapp.database.entities.ShoppingListItem
import com.example.budgetapp.repository.ShoppingListRepository
import kotlinx.coroutines.launch

class ShoppingListViewModel(private val repository: ShoppingListRepository) : ViewModel() {
    
    // Shopping List LiveData
    val allShoppingLists: LiveData<List<ShoppingList>> = repository.getAllShoppingLists()
    val activeShoppingLists: LiveData<List<ShoppingList>> = repository.getActiveShoppingLists()
    val completedShoppingLists: LiveData<List<ShoppingList>> = repository.getCompletedShoppingLists()
    val activeShoppingListsCount: LiveData<Int> = repository.getActiveShoppingListsCount()
    val completedShoppingListsCount: LiveData<Int> = repository.getCompletedShoppingListsCount()
    
    // Shopping List operations
    fun insertShoppingList(shoppingList: ShoppingList) {
        viewModelScope.launch {
            repository.insertShoppingList(shoppingList)
        }
    }
    
    fun updateShoppingList(shoppingList: ShoppingList) {
        viewModelScope.launch {
            repository.updateShoppingList(shoppingList)
        }
    }
    
    fun deleteShoppingList(id: Long) {
        viewModelScope.launch {
            repository.deleteShoppingListById(id)
        }
    }
    
    fun toggleShoppingListStatus(id: Long, isCompleted: Boolean) {
        viewModelScope.launch {
            val completedAt = if (isCompleted) System.currentTimeMillis() else null
            repository.updateShoppingListStatus(id, isCompleted, completedAt)
        }
    }
    
    fun updateShoppingListTotalCost(id: Long, totalCost: Double) {
        viewModelScope.launch {
            repository.updateShoppingListTotalCost(id, totalCost)
        }
    }
    
    fun getShoppingListById(id: Long): LiveData<ShoppingList?> {
        return repository.getShoppingListByIdLiveData(id)
    }
    
    // Shopping List Item operations
    fun getItemsForShoppingList(shoppingListId: Long): LiveData<List<ShoppingListItem>> {
        return repository.getItemsForShoppingList(shoppingListId)
    }
    
    fun getActiveItemsForShoppingList(shoppingListId: Long): LiveData<List<ShoppingListItem>> {
        return repository.getActiveItemsForShoppingList(shoppingListId)
    }
    
    fun getCompletedItemsForShoppingList(shoppingListId: Long): LiveData<List<ShoppingListItem>> {
        return repository.getCompletedItemsForShoppingList(shoppingListId)
    }
    
    fun insertShoppingListItem(item: ShoppingListItem) {
        viewModelScope.launch {
            repository.insertShoppingListItem(item)
        }
    }
    
    fun updateShoppingListItem(item: ShoppingListItem) {
        viewModelScope.launch {
            repository.updateShoppingListItem(item)
        }
    }
    
    fun deleteShoppingListItem(id: Long) {
        viewModelScope.launch {
            repository.deleteShoppingListItemById(id)
        }
    }
    
    fun toggleShoppingListItemStatus(id: Long, isCompleted: Boolean) {
        viewModelScope.launch {
            val completedAt = if (isCompleted) System.currentTimeMillis() else null
            repository.updateShoppingListItemStatus(id, isCompleted, completedAt)
        }
    }
    
    fun updateShoppingListItemQuantity(id: Long, quantity: Int) {
        viewModelScope.launch {
            repository.updateShoppingListItemQuantity(id, quantity)
        }
    }
    
    fun updateShoppingListItemActualPrice(id: Long, actualPrice: Double) {
        viewModelScope.launch {
            repository.updateShoppingListItemActualPrice(id, actualPrice)
        }
    }
    
    fun getItemCountForShoppingList(shoppingListId: Long): LiveData<Int> {
        return repository.getItemCountForShoppingList(shoppingListId)
    }
    
    fun getCompletedItemCountForShoppingList(shoppingListId: Long): LiveData<Int> {
        return repository.getCompletedItemCountForShoppingList(shoppingListId)
    }
    
    fun getTotalEstimatedCostForShoppingList(shoppingListId: Long): LiveData<Double?> {
        return repository.getTotalEstimatedCostForShoppingList(shoppingListId)
    }
    
    fun getTotalActualCostForShoppingList(shoppingListId: Long): LiveData<Double?> {
        return repository.getTotalActualCostForShoppingList(shoppingListId)
    }
}