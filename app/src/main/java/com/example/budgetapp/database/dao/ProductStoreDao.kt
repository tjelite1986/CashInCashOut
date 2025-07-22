package com.example.budgetapp.database.dao

import androidx.room.*
import com.example.budgetapp.database.entities.ProductStore
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductStoreDao {
    @Query("SELECT * FROM product_stores")
    fun getAllProductStores(): Flow<List<ProductStore>>

    @Query("SELECT * FROM product_stores WHERE productId = :productId ORDER BY price ASC")
    fun getPricesForProduct(productId: Long): Flow<List<ProductStore>>

    @Query("SELECT * FROM product_stores WHERE storeId = :storeId ORDER BY price ASC")
    fun getPricesForStore(storeId: Long): Flow<List<ProductStore>>

    @Query("SELECT * FROM product_stores WHERE productId = :productId AND storeId = :storeId")
    suspend fun getProductStorePrice(productId: Long, storeId: Long): ProductStore?

    @Query("""
        SELECT ps.* FROM product_stores ps
        INNER JOIN stores s ON ps.storeId = s.id
        WHERE ps.productId = :productId
        ORDER BY ps.price ASC
    """)
    fun getPricesForProductWithStore(productId: Long): Flow<List<ProductStore>>

    @Query("""
        SELECT MIN(price) FROM product_stores 
        WHERE productId = :productId
    """)
    suspend fun getLowestPrice(productId: Long): Double?

    @Query("""
        SELECT MAX(price) FROM product_stores 
        WHERE productId = :productId
    """)
    suspend fun getHighestPrice(productId: Long): Double?

    @Query("""
        SELECT AVG(price) FROM product_stores 
        WHERE productId = :productId
    """)
    suspend fun getAveragePrice(productId: Long): Double?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductStore(productStore: ProductStore): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductStores(productStores: List<ProductStore>)

    @Update
    suspend fun updateProductStore(productStore: ProductStore)

    @Delete
    suspend fun deleteProductStore(productStore: ProductStore)

    @Query("DELETE FROM product_stores WHERE productId = :productId")
    suspend fun deleteAllPricesForProduct(productId: Long)

    @Query("DELETE FROM product_stores WHERE storeId = :storeId")
    suspend fun deleteAllPricesForStore(storeId: Long)

    @Query("DELETE FROM product_stores WHERE productId = :productId AND storeId = :storeId")
    suspend fun deleteProductStorePrice(productId: Long, storeId: Long)
}