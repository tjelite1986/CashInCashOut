package com.example.budgetapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgetapp.database.entities.ProductStore
import com.example.budgetapp.database.entities.Store
import com.example.budgetapp.repository.StoreRepository
import kotlinx.coroutines.launch

class StoreViewModel(private val repository: StoreRepository) : ViewModel() {
    
    private val _store = MutableLiveData<Store?>()
    val store: LiveData<Store?> = _store
    
    fun getStoreById(storeId: Long): LiveData<Store?> {
        viewModelScope.launch {
            _store.value = repository.getStoreById(storeId)
        }
        return _store
    }
    
    fun getProductsForStore(storeId: Long): LiveData<List<ProductStore>> = 
        repository.getProductsForStore(storeId)
    
    fun loadStoreDetails(storeId: Long) {
        viewModelScope.launch {
            _store.value = repository.getStoreById(storeId)
        }
    }
    
    fun insertStore(store: Store) {
        viewModelScope.launch {
            repository.insertStore(store)
        }
    }
    
    fun updateStore(store: Store) {
        viewModelScope.launch {
            repository.updateStore(store)
        }
    }
    
    fun deleteStore(store: Store) {
        viewModelScope.launch {
            repository.deleteStore(store)
        }
    }
}