package com.example.budgetapp.database.dao

import androidx.room.*
import com.example.budgetapp.database.entities.Category
import com.example.budgetapp.database.entities.CategoryType
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories WHERE isActive = 1 ORDER BY sortOrder ASC, name ASC")
    fun getAllActiveCategories(): Flow<List<Category>>
    
    @Query("SELECT * FROM categories WHERE type = :type AND isActive = 1 AND parentCategoryId IS NULL ORDER BY sortOrder ASC, name ASC")
    fun getCategoriesByType(type: CategoryType): Flow<List<Category>>
    
    @Query("SELECT * FROM categories WHERE parentCategoryId = :parentId AND isActive = 1 ORDER BY sortOrder ASC, name ASC")
    fun getSubcategories(parentId: Long): Flow<List<Category>>
    
    @Query("SELECT * FROM categories WHERE id = :id")
    suspend fun getCategoryById(id: Long): Category?
    
    @Insert
    suspend fun insertCategory(category: Category): Long
    
    @Update
    suspend fun updateCategory(category: Category)
    
    @Delete
    suspend fun deleteCategory(category: Category)
    
    @Query("UPDATE categories SET isActive = 0 WHERE id = :id")
    suspend fun deactivateCategory(id: Long)
    
    @Query("UPDATE categories SET transactionCount = transactionCount + 1 WHERE id = :id")
    suspend fun incrementTransactionCount(id: Long)
    
    @Query("UPDATE categories SET transactionCount = transactionCount - 1 WHERE id = :id AND transactionCount > 0")
    suspend fun decrementTransactionCount(id: Long)

    @Query("SELECT COUNT(*) FROM categories WHERE name = :name AND type = :type AND isActive = 1")
    suspend fun checkDuplicateCategory(name: String, type: CategoryType): Int
    
    @Query("SELECT * FROM categories ORDER BY name ASC")
    fun getAllCategories(): Flow<List<Category>>
}