package com.example.budgetapp.database.dao

import androidx.room.*
import com.example.budgetapp.database.entities.Product
import com.example.budgetapp.data.ProductWithPrices
import com.example.budgetapp.data.ProductStoreWithStore
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM products ORDER BY name ASC")
    fun getAllProducts(): Flow<List<Product>>
    
    @Query("SELECT * FROM products WHERE id = :id")
    suspend fun getProductById(id: Long): Product?
    
    @Query("SELECT * FROM products WHERE barcode = :barcode LIMIT 1")
    suspend fun getProductByBarcode(barcode: String): Product?
    
    @Query("SELECT * FROM products WHERE categoryId = :categoryId ORDER BY name ASC")
    fun getProductsByCategory(categoryId: Long): Flow<List<Product>>
    
    @Query("SELECT * FROM products WHERE name LIKE '%' || :searchQuery || '%' OR productName LIKE '%' || :searchQuery || '%' ORDER BY name ASC")
    fun searchProducts(searchQuery: String): Flow<List<Product>>
    
    @Query("""
        SELECT MIN(ps.price) as minPrice, MAX(ps.price) as maxPrice, AVG(ps.price) as avgPrice
        FROM product_stores ps
        WHERE ps.productId = :productId
    """)
    suspend fun getPriceStats(productId: Long): PriceStats?
    
    data class PriceStats(
        val minPrice: Double?,
        val maxPrice: Double?,
        val avgPrice: Double?
    )
    
    @Insert
    suspend fun insertProduct(product: Product): Long
    
    @Update
    suspend fun updateProduct(product: Product)
    
    @Delete
    suspend fun deleteProduct(product: Product)
    
    @Query("DELETE FROM products WHERE id = :id")
    suspend fun deleteProductById(id: Long)
    
    @Query("SELECT COUNT(*) FROM products")
    suspend fun getProductCount(): Int
}