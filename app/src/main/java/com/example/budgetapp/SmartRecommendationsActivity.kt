package com.example.budgetapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgetapp.adapters.RecommendationAdapter
import com.example.budgetapp.adapters.SmartSuggestionAdapter
import com.example.budgetapp.data.ShoppingListRecommendations
import com.example.budgetapp.data.SmartSuggestion
import com.example.budgetapp.data.SuggestionType
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.databinding.ActivitySmartRecommendationsBinding
import com.example.budgetapp.services.SmartRecommendationService
import kotlinx.coroutines.launch

class SmartRecommendationsActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivitySmartRecommendationsBinding
    private lateinit var recommendationService: SmartRecommendationService
    private lateinit var suggestionAdapter: SmartSuggestionAdapter
    private lateinit var recommendationAdapter: RecommendationAdapter
    
    private var shoppingListId: Long = -1
    private var currentRecommendations: ShoppingListRecommendations? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySmartRecommendationsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        val database = BudgetDatabase.getDatabase(this)
        recommendationService = SmartRecommendationService(database)
        
        shoppingListId = intent.getLongExtra("shopping_list_id", -1)
        
        setupToolbar()
        setupRecyclerViews()
        setupClickListeners()
        loadData()
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = if (shoppingListId != -1L) "Smarta rekommendationer" else "Smarta tips"
        }
        
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
    
    private fun setupRecyclerViews() {
        // Smart suggestions adapter
        suggestionAdapter = SmartSuggestionAdapter { suggestion ->
            handleSuggestionClick(suggestion)
        }
        
        binding.recyclerViewSuggestions.apply {
            layoutManager = LinearLayoutManager(this@SmartRecommendationsActivity)
            adapter = suggestionAdapter
        }
        
        // Recommendations adapter (for specific shopping list)
        recommendationAdapter = RecommendationAdapter(
            onStoreClick = { storeRecommendation ->
                val intent = Intent(this, StoreDetailActivity::class.java)
                intent.putExtra("store_id", storeRecommendation.store.id)
                intent.putExtra("total_savings", storeRecommendation.totalSavings)
                intent.putExtra("item_count", storeRecommendation.itemCount)
                startActivity(intent)
            },
            onItemClick = { itemRecommendation ->
                // Show item price details
                val intent = Intent(this, PriceHistoryActivity::class.java)
                intent.putExtra("product_id", itemRecommendation.priceRecommendations.firstOrNull()?.productId ?: -1)
                startActivity(intent)
            },
            onPriceClick = { priceRecommendation ->
                val intent = Intent(this, PriceHistoryActivity::class.java)
                intent.putExtra("product_id", priceRecommendation.productId)
                intent.putExtra("store_id", priceRecommendation.storeId)
                startActivity(intent)
            }
        )
        
        binding.recyclerViewRecommendations.apply {
            layoutManager = LinearLayoutManager(this@SmartRecommendationsActivity)
            adapter = recommendationAdapter
        }
    }
    
    private fun setupClickListeners() {
        binding.buttonOptimizeList.setOnClickListener {
            currentRecommendations?.let { recommendations ->
                showOptimizedShoppingPlan(recommendations)
            }
        }
        
        // Removed SwipeRefreshLayout for now
    }
    
    private fun loadData() {
        lifecycleScope.launch {
            try {
                if (shoppingListId != -1L) {
                    // Load specific shopping list recommendations
                    loadShoppingListRecommendations()
                } else {
                    // Load general smart suggestions
                    loadSmartSuggestions()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                // Show error state to user
                showErrorState("Ett fel uppstod vid laddning av rekommendationer: ${e.message}")
            }
        }
    }
    
    private suspend fun loadShoppingListRecommendations() {
        val recommendations = recommendationService.getShoppingListRecommendations(shoppingListId)
        
        if (recommendations != null) {
            currentRecommendations = recommendations
            
            // Update summary
            binding.textTotalSavings.text = "Potentiella besparingar: ${String.format("%.0f", recommendations.totalEstimatedSavings)} kr"
            binding.textItemCount.text = "${recommendations.itemRecommendations.size} produkter analyserade"
            
            // Show recommendations
            recommendationAdapter.submitRecommendations(recommendations)
            
            // Show optimize button if there are savings
            binding.buttonOptimizeList.visibility = if (recommendations.totalEstimatedSavings > 5.0) {
                View.VISIBLE
            } else {
                View.GONE
            }
            
            // Show appropriate sections
            binding.layoutShoppingListRecommendations.visibility = View.VISIBLE
            binding.layoutSmartSuggestions.visibility = View.GONE
            
        } else {
            // No recommendations available
            binding.layoutShoppingListRecommendations.visibility = View.GONE
            binding.textNoRecommendations.visibility = View.VISIBLE
        }
    }
    
    private suspend fun loadSmartSuggestions() {
        val suggestions = recommendationService.getSmartSuggestions()
        
        suggestionAdapter.submitList(suggestions)
        
        if (suggestions.isNotEmpty()) {
            binding.layoutSmartSuggestions.visibility = View.VISIBLE
            binding.textNoSuggestions.visibility = View.GONE
        } else {
            binding.layoutSmartSuggestions.visibility = View.GONE
            binding.textNoSuggestions.visibility = View.VISIBLE
        }
    }
    
    private fun handleSuggestionClick(suggestion: SmartSuggestion) {
        when (suggestion.type) {
            SuggestionType.SHOPPING_OPTIMIZATION -> {
                val shoppingListId = suggestion.data["shopping_list_id"] as? Long ?: return
                val intent = Intent(this, SmartRecommendationsActivity::class.java)
                intent.putExtra("shopping_list_id", shoppingListId)
                startActivity(intent)
            }
            
            SuggestionType.PRICE_ALERT -> {
                val productId = suggestion.data["product_id"] as? Long ?: return
                val storeId = suggestion.data["store_id"] as? Long
                val intent = Intent(this, PriceHistoryActivity::class.java)
                intent.putExtra("product_id", productId)
                if (storeId != null) {
                    intent.putExtra("store_id", storeId)
                }
                startActivity(intent)
            }
            
            SuggestionType.STORE_RECOMMENDATION -> {
                val storeId = suggestion.data["store_id"] as? Long ?: return
                val intent = Intent(this, StoreDetailActivity::class.java)
                intent.putExtra("store_id", storeId)
                startActivity(intent)
            }
            
            else -> {
                // Handle other suggestion types
            }
        }
    }
    
    private fun showOptimizedShoppingPlan(recommendations: ShoppingListRecommendations) {
        // TODO: Implement optimized shopping plan activity
        // For now, just show the recommendations
    }
    
    private fun showErrorState(message: String) {
        binding.layoutSmartSuggestions.visibility = View.GONE
        binding.layoutShoppingListRecommendations.visibility = View.GONE
        binding.textNoSuggestions.text = message
        binding.textNoSuggestions.visibility = View.VISIBLE
    }
}