package com.example.budgetapp.utils

import com.example.budgetapp.database.entities.Category
import com.example.budgetapp.database.entities.CategoryType

object CategoryConstants {
    
    // Predefined expense categories matching the design
    val DEFAULT_EXPENSE_CATEGORIES = listOf(
        Category(name = "Dining", color = "#607D8B", iconResName = "ic_dining", type = CategoryType.EXPENSE, sortOrder = 1),
        Category(name = "Groceries", color = "#4CAF50", iconResName = "ic_groceries", type = CategoryType.EXPENSE, sortOrder = 2),
        Category(name = "Shopping", color = "#E91E63", iconResName = "ic_shopping", type = CategoryType.EXPENSE, sortOrder = 3),
        Category(name = "Transit", color = "#FF9800", iconResName = "ic_transit", type = CategoryType.EXPENSE, sortOrder = 4),
        Category(name = "Entertainment", color = "#2196F3", iconResName = "ic_entertainment", type = CategoryType.EXPENSE, sortOrder = 5),
        Category(name = "Bills & Fees", color = "#4CAF50", iconResName = "ic_bills", type = CategoryType.EXPENSE, sortOrder = 6),
        Category(name = "Boende", color = "#795548", iconResName = "ic_home", type = CategoryType.EXPENSE, sortOrder = 7),
        Category(name = "Hälsa", color = "#F44336", iconResName = "ic_health", type = CategoryType.EXPENSE, sortOrder = 8),
        Category(name = "Teknik", color = "#9C27B0", iconResName = "ic_tech", type = CategoryType.EXPENSE, sortOrder = 9),
        Category(name = "Sport", color = "#009688", iconResName = "ic_sport", type = CategoryType.EXPENSE, sortOrder = 10)
    )
    
    // Predefined income categories
    val DEFAULT_INCOME_CATEGORIES = listOf(
        Category(name = "Salary", color = "#4CAF50", iconResName = "ic_salary", type = CategoryType.INCOME, sortOrder = 1),
        Category(name = "Bonus", color = "#FF9800", iconResName = "ic_bonus", type = CategoryType.INCOME, sortOrder = 2),
        Category(name = "Freelance", color = "#2196F3", iconResName = "ic_freelance", type = CategoryType.INCOME, sortOrder = 3),
        Category(name = "Investment", color = "#9C27B0", iconResName = "ic_investment", type = CategoryType.INCOME, sortOrder = 4),
        Category(name = "Sale", color = "#E91E63", iconResName = "ic_sale", type = CategoryType.INCOME, sortOrder = 5),
        Category(name = "Other Income", color = "#607D8B", iconResName = "ic_other", type = CategoryType.INCOME, sortOrder = 6)
    )
    
    // Available colors for categories - more vibrant and diverse
    val CATEGORY_COLORS = listOf(
        "#FF6B6B", // Coral Red
        "#4ECDC4", // Turquoise  
        "#45B7D1", // Sky Blue
        "#96CEB4", // Mint Green
        "#FFEAA7", // Warm Yellow
        "#DDA0DD", // Plum
        "#98D8C8", // Mint
        "#F7DC6F", // Banana Yellow
        "#BB8FCE", // Lavender
        "#85C1E9", // Light Blue
        "#F8C471", // Peach
        "#82E0AA", // Light Green
        "#F1948A", // Salmon
        "#85C1E9", // Powder Blue
        "#D7DBDD", // Light Gray
        "#F39C12", // Orange
        "#8E44AD", // Purple
        "#E74C3C", // Red
        "#2ECC71", // Emerald
        "#3498DB", // Blue
    )
    
    // Available icons for categories
    val CATEGORY_ICONS = mapOf(
        "ic_dining" to "Dining",
        "ic_groceries" to "Groceries", 
        "ic_shopping" to "Shopping",
        "ic_transit" to "Transit",
        "ic_entertainment" to "Entertainment",
        "ic_bills" to "Bills",
        "ic_home" to "Home",
        "ic_health" to "Health",
        "ic_tech" to "Technology",
        "ic_sport" to "Sports",
        "ic_salary" to "Salary",
        "ic_bonus" to "Bonus",
        "ic_freelance" to "Freelance",
        "ic_investment" to "Investment",
        "ic_sale" to "Sale",
        "ic_other" to "Other",
        "ic_car" to "Car",
        "ic_gas" to "Gas",
        "ic_clothes" to "Clothes",
        "ic_education" to "Education",
        "ic_coffee" to "Coffee",
        "ic_gift" to "Gifts",
        "ic_pets" to "Pets",
        "ic_travel" to "Travel",
        "ic_beauty" to "Beauty",
        "ic_pharmacy" to "Pharmacy",
        "ic_store" to "Store",
        "ic_calendar" to "Calendar",
        "ic_money" to "Money",
        "ic_tobacco" to "Tobacco",
        "ic_drinks" to "Drinks"
    )
    
    // Legacy support for existing activities
    val EXPENSE_CATEGORIES = listOf(
        "Mat", 
        "Transport", 
        "Boende", 
        "Kläder", 
        "Hälsa", 
        "Nöje", 
        "Räkningar",
        "Hygien",
        "Teknik",
        "Sport", 
        "Resor", 
        "Övrigt"
    )
    
    val INCOME_CATEGORIES = listOf(
        "Lön",
        "Bonus",
        "Freelance",
        "Investering",
        "Försäljning",
        "Övrigt"
    )
    
    val PAYMENT_METHODS = listOf(
        "Kort",
        "Kontant", 
        "Swish",
        "Faktura"
    )
    
    val RECURRING_TYPES = listOf(
        "Månadsvis",
        "Veckovis", 
        "Årligen"
    )
}