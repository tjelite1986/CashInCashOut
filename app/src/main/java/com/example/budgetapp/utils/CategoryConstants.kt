package com.example.budgetapp.utils

object CategoryConstants {
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