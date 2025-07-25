package com.example.budgetapp.database.dao

import androidx.room.*
import com.example.budgetapp.database.entities.ProductCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductCategoryDao {
    @Query("SELECT * FROM product_categories ORDER BY isDefault DESC, name ASC")
    fun getAllProductCategories(): Flow<List<ProductCategory>>
    
    @Query("SELECT * FROM product_categories WHERE id = :id")
    suspend fun getProductCategoryById(id: Long): ProductCategory?
    
    @Insert
    suspend fun insertProductCategory(category: ProductCategory): Long
    
    @Update
    suspend fun updateProductCategory(category: ProductCategory)
    
    @Delete
    suspend fun deleteProductCategory(category: ProductCategory)
    
    @Query("DELETE FROM product_categories WHERE id = :id")
    suspend fun deleteProductCategoryById(id: Long)

    @Query("SELECT COUNT(*) FROM product_categories WHERE name = :name")
    suspend fun checkDuplicateProductCategory(name: String): Int
    
    @Query("SELECT COUNT(*) FROM products WHERE productCategoryId = :productCategoryId")
    suspend fun getProductCountForCategory(productCategoryId: Long): Int
}