package com.example.budgetapp.database.entities

import androidx.room.TypeConverter

class BudgetTypeConverter {
    @TypeConverter
    fun fromBudgetPeriod(period: BudgetPeriod): String {
        return period.name
    }
    
    @TypeConverter
    fun toBudgetPeriod(periodString: String): BudgetPeriod {
        return BudgetPeriod.valueOf(periodString)
    }
}