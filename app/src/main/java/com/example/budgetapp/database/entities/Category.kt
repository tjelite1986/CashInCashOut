package com.example.budgetapp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val color: String = "#FF6B6B",
    val iconResName: String = "ic_category_default",
    val type: CategoryType = CategoryType.EXPENSE,
    val parentCategoryId: Long? = null,
    val isSubcategory: Boolean = false,
    val associatedKeywords: String = "",
    val transactionCount: Int = 0,
    val isActive: Boolean = true,
    val sortOrder: Int = 0
)

enum class CategoryType {
    EXPENSE, INCOME
}