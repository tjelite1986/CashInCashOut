package com.example.budgetapp.utils

import com.example.budgetapp.database.entities.Budget
import com.example.budgetapp.database.entities.BudgetPeriod
import com.example.budgetapp.repository.BudgetRepository
import java.util.*

object SampleDataUtils {
    
    suspend fun createSampleBudgets(budgetRepository: BudgetRepository) {
        try {
            // Kontrollera om det redan finns budgetar
            val existingBudgets = budgetRepository.getAllActiveBudgets()
            
            // Skapa några exempelbudgetar för aktuell månad
            val calendar = Calendar.getInstance()
            
            // Budget 1: Mat & Dryck
            val (foodStart, foodEnd) = budgetRepository.createBudgetPeriod(
                BudgetPeriod.MONTHLY, 
                calendar.time
            )
            
            val foodBudget = Budget(
                name = "Matbudget",
                categoryName = "Mat & Dryck",
                budgetAmount = 5000.0,
                period = BudgetPeriod.MONTHLY,
                startDate = foodStart,
                endDate = foodEnd,
                alertThreshold = 80.0
            )
            
            // Budget 2: Transport
            val transportBudget = Budget(
                name = "Transportkostnader",
                categoryName = "Transport",
                budgetAmount = 1500.0,
                period = BudgetPeriod.MONTHLY,
                startDate = foodStart,
                endDate = foodEnd,
                alertThreshold = 75.0
            )
            
            // Budget 3: Nöje
            val entertainmentBudget = Budget(
                name = "Nöje & Aktiviteter",
                categoryName = "Nöje",
                budgetAmount = 2000.0,
                period = BudgetPeriod.MONTHLY,
                startDate = foodStart,
                endDate = foodEnd,
                alertThreshold = 85.0
            )
            
            // Lägg till budgetarna
            budgetRepository.insertBudget(foodBudget)
            budgetRepository.insertBudget(transportBudget)
            budgetRepository.insertBudget(entertainmentBudget)
            
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}