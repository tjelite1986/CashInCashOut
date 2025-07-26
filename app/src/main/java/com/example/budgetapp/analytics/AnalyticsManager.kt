package com.example.budgetapp.analytics

import android.content.Context
import com.example.budgetapp.analytics.ai.NLPCategorizationService
import com.example.budgetapp.analytics.investments.InvestmentAnalyticsService
import com.example.budgetapp.analytics.tax.TaxReportingService

/**
 * Central coordinator for all Stage 1 Analytics & Intelligence features
 * Provides unified access to AI, Investment, and Tax analytics services
 */
class AnalyticsManager private constructor(private val context: Context) {
    
    // Lazy initialization of services
    val nlpService: NLPCategorizationService by lazy { 
        NLPCategorizationService(context) 
    }
    
    val investmentService: InvestmentAnalyticsService by lazy { 
        InvestmentAnalyticsService(context) 
    }
    
    val taxService: TaxReportingService by lazy { 
        TaxReportingService(context) 
    }
    
    /**
     * Initialize all analytics services
     * Should be called once during app startup
     */
    suspend fun initialize() {
        // Pre-warm critical services by accessing them
        nlpService
        investmentService
        taxService
    }
    
    /**
     * Check if all Stage 1 features are available
     */
    fun isStage1Available(): Boolean {
        return try {
            // Quick validation that services can be created
            nlpService
            investmentService
            taxService
            true
        } catch (e: Exception) {
            false
        }
    }
    
    companion object {
        @Volatile
        private var INSTANCE: AnalyticsManager? = null
        
        fun getInstance(context: Context): AnalyticsManager {
            return INSTANCE ?: synchronized(this) {
                val instance = AnalyticsManager(context.applicationContext)
                INSTANCE = instance
                instance
            }
        }
    }
}

/**
 * Extension functions for easy access to analytics features
 */
object Analytics {
    fun with(context: Context): AnalyticsManager = AnalyticsManager.getInstance(context)
}