package com.example.budgetapp.utils

import com.example.budgetapp.data.ThemeSettings

interface ThemeAware {
    fun applyTheme(settings: ThemeSettings)
}