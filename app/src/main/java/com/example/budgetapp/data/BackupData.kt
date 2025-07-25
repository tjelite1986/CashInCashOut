package com.example.budgetapp.data

import com.example.budgetapp.database.entities.*
import com.google.gson.annotations.SerializedName

data class BackupData(
    @SerializedName("version")
    val version: String = "1.0",
    @SerializedName("exportDate")
    val exportDate: Long? = null,
    @SerializedName("categories")
    val categories: List<Category>? = null,
    @SerializedName("incomes")
    val incomes: List<Income>? = null,
    @SerializedName("expenses")
    val expenses: List<Expense>? = null,
    @SerializedName("loans")
    val loans: List<Loan>? = null,
    @SerializedName("budgets")
    val budgets: List<Budget>? = null,
    @SerializedName("stores")
    val stores: List<Store>? = null,
    @SerializedName("products")
    val products: List<Product>? = null,
    @SerializedName("productStores")
    val productStores: List<ProductStore>? = null
)