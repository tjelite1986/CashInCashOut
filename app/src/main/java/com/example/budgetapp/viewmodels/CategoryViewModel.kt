package com.example.budgetapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.Category
import com.example.budgetapp.database.entities.CategoryType
import com.example.budgetapp.repository.CategoryRepository
import kotlinx.coroutines.launch

class CategoryViewModel(application: Application) : AndroidViewModel(application) {
    
    private val repository: CategoryRepository
    
    val allActiveCategories: LiveData<List<Category>>
    val expenseCategories: LiveData<List<Category>>
    val incomeCategories: LiveData<List<Category>>
    
    init {
        val categoryDao = BudgetDatabase.getDatabase(application).categoryDao()
        repository = CategoryRepository(categoryDao)
        
        allActiveCategories = repository.allActiveCategories.asLiveData()
        expenseCategories = repository.getCategoriesByType(CategoryType.EXPENSE).asLiveData()
        incomeCategories = repository.getCategoriesByType(CategoryType.INCOME).asLiveData()
    }
    
    fun insertCategory(category: Category) = viewModelScope.launch {
        repository.insertCategory(category)
    }
    
    fun updateCategory(category: Category) = viewModelScope.launch {
        repository.updateCategory(category)
    }
    
    fun deleteCategory(category: Category) = viewModelScope.launch {
        repository.deleteCategory(category)
    }
    
    suspend fun getCategoryById(id: Long) = repository.getCategoryById(id)
    
    fun getSubcategories(parentId: Long) = repository.getSubcategories(parentId).asLiveData()
    
    fun incrementTransactionCount(categoryId: Long) = viewModelScope.launch {
        repository.incrementTransactionCount(categoryId)
    }
    
    fun decrementTransactionCount(categoryId: Long) = viewModelScope.launch {
        repository.decrementTransactionCount(categoryId)
    }
}