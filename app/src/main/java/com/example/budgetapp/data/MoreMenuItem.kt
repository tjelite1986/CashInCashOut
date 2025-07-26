package com.example.budgetapp.data

data class MoreMenuItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val icon: Int,
    val isHeader: Boolean = false,
    val activityClass: Class<*>? = null
)