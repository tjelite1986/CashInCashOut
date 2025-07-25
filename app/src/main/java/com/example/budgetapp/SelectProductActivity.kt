package com.example.budgetapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgetapp.adapters.SelectProductAdapter
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.Product
import com.example.budgetapp.databinding.ActivitySelectProductBinding
import kotlinx.coroutines.launch

class SelectProductActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivitySelectProductBinding
    private lateinit var adapter: SelectProductAdapter
    private lateinit var database: BudgetDatabase
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        database = BudgetDatabase.getDatabase(this)
        
        setupToolbar()
        setupRecyclerView()
        loadProducts()
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Välj produkt"
        }
        
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
    
    private fun setupRecyclerView() {
        adapter = SelectProductAdapter { product ->
            onProductSelected(product)
        }
        
        binding.recyclerViewProducts.apply {
            this.adapter = this@SelectProductActivity.adapter
            layoutManager = LinearLayoutManager(this@SelectProductActivity)
            addItemDecoration(DividerItemDecoration(this@SelectProductActivity, DividerItemDecoration.VERTICAL))
        }
    }
    
    private fun loadProducts() {
        lifecycleScope.launch {
            try {
                database.productDao().getAllProducts().collect { products ->
                    adapter.updateProducts(products)
                    
                    // Visa meddelande om inga produkter finns
                    if (products.isEmpty()) {
                        binding.textViewEmptyState.text = "Inga produkter hittades. Lägg till produkter först i Produkthantering."
                        binding.textViewEmptyState.visibility = android.view.View.VISIBLE
                        binding.recyclerViewProducts.visibility = android.view.View.GONE
                    } else {
                        binding.textViewEmptyState.visibility = android.view.View.GONE
                        binding.recyclerViewProducts.visibility = android.view.View.VISIBLE
                    }
                }
            } catch (e: Exception) {
                binding.textViewEmptyState.text = "Fel vid laddning av produkter: ${e.message}"
                binding.textViewEmptyState.visibility = android.view.View.VISIBLE
                binding.recyclerViewProducts.visibility = android.view.View.GONE
            }
        }
    }
    
    private fun onProductSelected(product: Product) {
        val resultIntent = Intent().apply {
            putExtra("selected_product_id", product.id)
            putExtra("selected_product_name", product.name)
            putExtra("selected_product_barcode", product.barcode)
        }
        setResult(Activity.RESULT_OK, resultIntent)
        finish()
    }
    
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_select_product, menu)
        
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as? SearchView
        
        searchView?.apply {
            queryHint = "Sök produkter..."
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?) = false
                
                override fun onQueryTextChange(newText: String?): Boolean {
                    adapter.filter(newText ?: "")
                    return true
                }
            })
        }
        
        return true
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