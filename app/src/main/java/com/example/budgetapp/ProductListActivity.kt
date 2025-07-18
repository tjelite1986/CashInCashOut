package com.example.budgetapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.Product
import com.example.budgetapp.databinding.ActivityProductListBinding
import com.example.budgetapp.data.ProductWithPrices
import com.example.budgetapp.data.ProductStoreWithStore
import com.google.android.material.appbar.MaterialToolbar
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ProductListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductListBinding
    private lateinit var database: BudgetDatabase
    private lateinit var productAdapter: ProductWithPricesAdapter
    private var products = mutableListOf<ProductWithPrices>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            binding = ActivityProductListBinding.inflate(layoutInflater)
            setContentView(binding.root)

            database = BudgetDatabase.getDatabase(this)
            
            setupUI()
            setupRecyclerView()
            setupClickListeners()
            loadProducts()
        } catch (e: Exception) {
            Toast.makeText(this, "Fel vid uppstart: ${e.message}", Toast.LENGTH_LONG).show()
            e.printStackTrace()
            finish()
        }
    }

    private fun setupUI() {
        try {
            val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "Mina produkter"
            
            toolbar.setNavigationOnClickListener {
                finish()
            }
        } catch (e: Exception) {
            // Fallback if toolbar setup fails
            supportActionBar?.title = "Mina produkter"
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupRecyclerView() {
        productAdapter = ProductWithPricesAdapter(
            products = products,
            onEditClick = { productWithPrices ->
                editProduct(productWithPrices.product)
            },
            onDeleteClick = { productWithPrices ->
                showDeleteDialog(productWithPrices.product)
            },
            onViewPricesClick = { productWithPrices ->
                val intent = Intent(this@ProductListActivity, ProductPriceManagerActivity::class.java)
                intent.putExtra("product_id", productWithPrices.product.id)
                startActivity(intent)
            }
        )
        
        binding.recyclerViewProducts.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewProducts.adapter = productAdapter
    }

    private fun setupClickListeners() {
        try {
            binding.fabAddProduct.setOnClickListener {
                val intent = Intent(this, AddProductActivity::class.java)
                startActivity(intent)
            }
            
            binding.btnManageStores.setOnClickListener {
                val intent = Intent(this, StoreManagerActivity::class.java)
                startActivity(intent)
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Fel vid setup av knappar: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadProducts() {
        lifecycleScope.launch {
            database.productDao().getAllProducts().collect { productList ->
                products.clear()
                
                // For each product, get its prices from different stores
                val productsWithPrices = productList.map { product ->
                    val productStores = database.productStoreDao().getPricesForProduct(product.id).first()
                    val productStoreWithStores = productStores.map { productStore ->
                        val store = database.storeDao().getStoreById(productStore.storeId)
                        ProductStoreWithStore(productStore, store!!)
                    }
                    ProductWithPrices(product, productStoreWithStores)
                }
                
                products.addAll(productsWithPrices)
                productAdapter.notifyDataSetChanged()
                
                // Update empty state
                if (products.isEmpty()) {
                    binding.textViewEmpty.visibility = android.view.View.VISIBLE
                    binding.recyclerViewProducts.visibility = android.view.View.GONE
                } else {
                    binding.textViewEmpty.visibility = android.view.View.GONE
                    binding.recyclerViewProducts.visibility = android.view.View.VISIBLE
                }
            }
        }
    }

    private fun editProduct(product: Product) {
        val intent = Intent(this, EditProductActivity::class.java)
        intent.putExtra("product_id", product.id)
        startActivity(intent)
    }
    
    private fun viewPrices(productWithPrices: ProductWithPrices) {
        if (productWithPrices.prices.isEmpty()) {
            Toast.makeText(this, "Inga priser registrerade för denna produkt", Toast.LENGTH_SHORT).show()
            return
        }
        
        val priceInfo = productWithPrices.prices.joinToString("\n") { priceData ->
            val price = priceData.productStore.price
            val store = priceData.store
            val campaignInfo = if (priceData.productStore.hasCampaignPrice) {
                val quantity = priceData.productStore.campaignQuantity ?: 1
                val campaignPrice = priceData.productStore.campaignPrice ?: 0.0
                " (Kampanj: $quantity för $campaignPrice kr)"
            } else ""
            "${store.name} (${store.chain}): $price kr$campaignInfo"
        }
        
        AlertDialog.Builder(this)
            .setTitle("Priser för ${productWithPrices.product.name}")
            .setMessage(priceInfo)
            .setPositiveButton("OK", null)
            .show()
    }

    private fun showDeleteDialog(product: Product) {
        AlertDialog.Builder(this)
            .setTitle("Ta bort produkt")
            .setMessage("Är du säker på att du vill ta bort \"${product.name}\"?")
            .setPositiveButton("Ta bort") { _, _ ->
                deleteProduct(product)
            }
            .setNegativeButton("Avbryt", null)
            .show()
    }

    private fun deleteProduct(product: Product) {
        lifecycleScope.launch {
            try {
                database.productDao().deleteProduct(product)
                Toast.makeText(this@ProductListActivity, "Produkt borttagen", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this@ProductListActivity, "Fel vid borttagning: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        loadProducts()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.product_list_menu, menu)
        return true
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_manage_stores -> {
                val intent = Intent(this, StoreManagerActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}