package com.example.budgetapp.database.dao

import androidx.room.*
import com.example.budgetapp.database.entities.Store
import kotlinx.coroutines.flow.Flow

@Dao
interface StoreDao {
    @Query("SELECT * FROM stores ORDER BY name ASC")
    fun getAllStores(): Flow<List<Store>>

    @Query("SELECT * FROM stores WHERE id = :storeId")
    suspend fun getStoreById(storeId: Long): Store?

    @Query("SELECT * FROM stores WHERE name LIKE :name")
    fun getStoresByName(name: String): Flow<List<Store>>

    @Query("SELECT * FROM stores WHERE chain = :chain ORDER BY name ASC")
    fun getStoresByChain(chain: String): Flow<List<Store>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStore(store: Store): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStores(stores: List<Store>)

    @Update
    suspend fun updateStore(store: Store)

    @Delete
    suspend fun deleteStore(store: Store)

    @Query("DELETE FROM stores WHERE id = :storeId")
    suspend fun deleteStoreById(storeId: Long)
}