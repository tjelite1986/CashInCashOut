package com.example.budgetapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.budgetapp.database.dao.ProductStoreDao
import com.example.budgetapp.database.dao.StoreDao
import com.example.budgetapp.database.entities.ProductStore
import com.example.budgetapp.database.entities.Store

class StoreRepository(
    private val storeDao: StoreDao,
    private val productStoreDao: ProductStoreDao
) {
    
    fun getAllStores(): LiveData<List<Store>> = storeDao.getAllStores().asLiveData()
    
    suspend fun getStoreById(storeId: Long): Store? = storeDao.getStoreById(storeId)
    
    fun getProductsForStore(storeId: Long): LiveData<List<ProductStore>> = 
        productStoreDao.getPricesForStore(storeId).asLiveData()
    
    suspend fun insertStore(store: Store): Long = storeDao.insertStore(store)
    
    suspend fun updateStore(store: Store) = storeDao.updateStore(store)
    
    suspend fun deleteStore(store: Store) = storeDao.deleteStore(store)
}