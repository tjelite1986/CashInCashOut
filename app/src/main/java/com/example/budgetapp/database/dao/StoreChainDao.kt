package com.example.budgetapp.database.dao

import androidx.room.*
import com.example.budgetapp.database.entities.StoreChain
import kotlinx.coroutines.flow.Flow

@Dao
interface StoreChainDao {
    
    @Query("SELECT * FROM store_chains WHERE isActive = 1 ORDER BY isDefault DESC, name ASC")
    fun getAllActiveChains(): Flow<List<StoreChain>>
    
    @Query("SELECT * FROM store_chains ORDER BY isDefault DESC, name ASC")
    fun getAllChains(): Flow<List<StoreChain>>
    
    @Query("SELECT * FROM store_chains WHERE id = :id")
    suspend fun getChainById(id: Long): StoreChain?
    
    @Query("SELECT * FROM store_chains WHERE name = :name AND isActive = 1")
    suspend fun getChainByName(name: String): StoreChain?
    
    @Query("SELECT name FROM store_chains WHERE isActive = 1 ORDER BY isDefault DESC, name ASC")
    suspend fun getAllChainNames(): List<String>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChain(chain: StoreChain): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertChains(chains: List<StoreChain>)
    
    @Update
    suspend fun updateChain(chain: StoreChain)
    
    @Delete
    suspend fun deleteChain(chain: StoreChain)
    
    @Query("UPDATE store_chains SET isActive = 0, updatedAt = :timestamp WHERE id = :id")
    suspend fun deactivateChain(id: Long, timestamp: Long = System.currentTimeMillis())
    
    @Query("UPDATE store_chains SET isActive = 1, updatedAt = :timestamp WHERE id = :id")
    suspend fun activateChain(id: Long, timestamp: Long = System.currentTimeMillis())
    
    @Query("SELECT COUNT(*) FROM store_chains WHERE isDefault = 1")
    suspend fun getDefaultChainsCount(): Int
    
    @Query("DELETE FROM store_chains WHERE isDefault = 0")
    suspend fun deleteCustomChains()
}