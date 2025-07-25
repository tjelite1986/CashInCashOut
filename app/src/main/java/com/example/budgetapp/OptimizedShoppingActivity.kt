package com.example.budgetapp

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgetapp.adapters.OptimizedShoppingListAdapter
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.databinding.ActivityOptimizedShoppingBinding
import com.example.budgetapp.repository.ShoppingListRepository
import com.example.budgetapp.services.ShoppingListOptimizer
import com.example.budgetapp.viewmodel.ShoppingListViewModel
import com.example.budgetapp.viewmodel.ShoppingListViewModelFactory
import kotlinx.coroutines.launch

class OptimizedShoppingActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityOptimizedShoppingBinding
    private lateinit var adapter: OptimizedShoppingListAdapter
    private lateinit var optimizer: ShoppingListOptimizer
    private var shoppingListId: Long = -1
    
    private val viewModel: ShoppingListViewModel by viewModels {
        val database = BudgetDatabase.getDatabase(this)
        val repository = ShoppingListRepository(
            database.shoppingListDao(),
            database.shoppingListItemDao(),
            database.productDao(),
            database.storeDao()
        )
        ShoppingListViewModelFactory(repository)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOptimizedShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        shoppingListId = intent.getLongExtra("shopping_list_id", -1)
        if (shoppingListId == -1L) {
            finish()
            return
        }
        
        val database = BudgetDatabase.getDatabase(this)
        optimizer = ShoppingListOptimizer(database)
        
        setupToolbar()
        setupRecyclerView()
        loadOptimizedLists()
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Optimerad inköpslista"
            subtitle = "Handla smart och spara pengar"
        }
    }
    
    private fun setupRecyclerView() {
        adapter = OptimizedShoppingListAdapter()
        binding.recyclerViewOptimizedLists.apply {
            layoutManager = LinearLayoutManager(this@OptimizedShoppingActivity)
            adapter = this@OptimizedShoppingActivity.adapter
        }
    }
    
    private fun loadOptimizedLists() {
        lifecycleScope.launch {
            try {
                // Visa loading
                binding.progressBar.visibility = android.view.View.VISIBLE
                binding.recyclerViewOptimizedLists.visibility = android.view.View.GONE
                
                // Hämta items från shoppinglistan
                viewModel.getItemsWithProductForShoppingList(shoppingListId).observe(this@OptimizedShoppingActivity) { items ->
                    if (items.isNotEmpty()) {
                        lifecycleScope.launch {
                            try {
                                // Optimera listan
                                val optimizedLists = optimizer.optimizeShoppingList(items)
                                val totalSavings = optimizer.calculateTotalSavings(optimizedLists)
                                
                                // Uppdatera UI
                                adapter.updateLists(optimizedLists)
                                
                                // Visa statistik
                                binding.textTotalSavings.text = "Total besparing: ${String.format("%.2f kr", totalSavings)}"
                                binding.textStoreCount.text = "${optimizedLists.size} butiker"
                                binding.textItemCount.text = "${optimizedLists.sumOf { it.getItemCount() }} varor"
                                
                                // Dölj loading och visa innehåll
                                binding.progressBar.visibility = android.view.View.GONE
                                binding.recyclerViewOptimizedLists.visibility = android.view.View.VISIBLE
                                binding.statsLayout.visibility = android.view.View.VISIBLE
                                
                            } catch (e: Exception) {
                                showError("Fel vid optimering: ${e.message}")
                            }
                        }
                    } else {
                        showError("Inga varor att optimera")
                    }
                }
                
            } catch (e: Exception) {
                showError("Fel vid laddning: ${e.message}")
            }
        }
    }
    
    private fun showError(message: String) {
        binding.progressBar.visibility = android.view.View.GONE
        binding.textError.text = message
        binding.textError.visibility = android.view.View.VISIBLE
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}