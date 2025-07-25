package com.example.budgetapp.repository

import com.example.budgetapp.database.dao.CategoryDao
import com.example.budgetapp.database.entities.Category
import com.example.budgetapp.database.entities.CategoryType
import kotlinx.coroutines.flow.Flow

class CategoryRepository(private val categoryDao: CategoryDao) {

    val allActiveCategories: Flow<List<Category>> = categoryDao.getAllActiveCategories()

    fun getCategoriesByType(type: CategoryType): Flow<List<Category>> {
        return categoryDao.getCategoriesByType(type)
    }

    fun getSubcategories(parentId: Long): Flow<List<Category>> {
        return categoryDao.getSubcategories(parentId)
    }

    suspend fun getCategoryById(id: Long): Category? {
        return categoryDao.getCategoryById(id)
    }

    suspend fun insertCategory(category: Category): Long {
        return categoryDao.insertCategory(category)
    }

    suspend fun updateCategory(category: Category) {
        categoryDao.updateCategory(category)
    }

    suspend fun deleteCategory(category: Category) {
        categoryDao.deleteCategory(category)
    }

    suspend fun deactivateCategory(id: Long) {
        categoryDao.deactivateCategory(id)
    }

    suspend fun incrementTransactionCount(categoryId: Long) {
        categoryDao.incrementTransactionCount(categoryId)
    }

    suspend fun decrementTransactionCount(categoryId: Long) {
        categoryDao.decrementTransactionCount(categoryId)
    }

    suspend fun checkDuplicateCategory(name: String, type: CategoryType): Boolean {
        return categoryDao.checkDuplicateCategory(name, type) > 0
    }
}