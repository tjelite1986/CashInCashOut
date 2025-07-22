package com.example.budgetapp.data

import com.example.budgetapp.database.entities.*
import com.google.gson.annotations.SerializedName

data class BackupData(
    @SerializedName("version")
    val version: String = "1.0",
    @SerializedName("exportDate")
    val exportDate: Long = System.currentTimeMillis(),
    @SerializedName("categories")
    val categories: List<Category> = emptyList(),
    @SerializedName("incomes")
    val incomes: List<Income> = emptyList(),
    @SerializedName("expenses")
    val expenses: List<Expense> = emptyList(),
    @SerializedName("loans")
    val loans: List<Loan> = emptyList(),
    @SerializedName("budgets")
    val budgets: List<Budget> = emptyList(),
    @SerializedName("stores")
    val stores: List<Store> = emptyList(),
    @SerializedName("products")
    val products: List<Product> = emptyList(),
    @SerializedName("productStores")
    val productStores: List<ProductStore> = emptyList()
)