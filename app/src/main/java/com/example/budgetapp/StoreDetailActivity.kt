package com.example.budgetapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgetapp.adapters.StoreProductAdapter
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.databinding.ActivityStoreDetailBinding
import com.example.budgetapp.repository.StoreRepository
import com.example.budgetapp.viewmodel.StoreViewModel
import com.example.budgetapp.viewmodel.StoreViewModelFactory
import kotlinx.coroutines.launch

class StoreDetailActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityStoreDetailBinding
    private lateinit var adapter: StoreProductAdapter
    private var storeId: Long = -1
    private var totalSavings: Double = 0.0
    private var itemCount: Int = 0
    
    private val viewModel: StoreViewModel by viewModels {
        val database = BudgetDatabase.getDatabase(this)
        val repository = StoreRepository(database.storeDao(), database.productStoreDao())
        StoreViewModelFactory(repository)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoreDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        storeId = intent.getLongExtra("store_id", -1)
        totalSavings = intent.getDoubleExtra("total_savings", 0.0)
        itemCount = intent.getIntExtra("item_count", 0)
        
        if (storeId == -1L) {
            finish()
            return
        }
        
        setupToolbar()
        setupRecyclerView()
        setupObservers()
        loadStoreDetails()
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Butiksinformation"
        }
        
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
    
    private fun setupRecyclerView() {
        adapter = StoreProductAdapter(
            onProductClick = { productStore ->
                val intent = Intent(this, ProductPriceManagerActivity::class.java)
                intent.putExtra("product_id", productStore.productId)
                startActivity(intent)
            }
        )
        
        binding.recyclerViewProducts.apply {
            layoutManager = LinearLayoutManager(this@StoreDetailActivity)
            adapter = this@StoreDetailActivity.adapter
        }
    }
    
    private fun setupObservers() {
        viewModel.getStoreById(storeId).observe(this) { store ->
            store?.let {
                supportActionBar?.title = it.name
                binding.textStoreName.text = it.name
                binding.textStoreChain.text = it.chain ?: "Ingen kedja"
                
                if (!it.address.isNullOrEmpty()) {
                    binding.textStoreAddress.text = "${it.address}"
                    if (!it.city.isNullOrEmpty()) {
                        binding.textStoreAddress.append(", ${it.city}")
                    }
                    binding.layoutStoreAddress.visibility = View.VISIBLE
                } else {
                    binding.layoutStoreAddress.visibility = View.GONE
                }
                
                if (!it.phoneNumber.isNullOrEmpty()) {
                    binding.textStorePhone.text = it.phoneNumber
                    binding.layoutStorePhone.visibility = View.VISIBLE
                } else {
                    binding.layoutStorePhone.visibility = View.GONE
                }
                
                if (!it.website.isNullOrEmpty()) {
                    binding.textStoreWebsite.text = it.website
                    binding.layoutStoreWebsite.visibility = View.VISIBLE
                } else {
                    binding.layoutStoreWebsite.visibility = View.GONE
                }
                
                if (!it.openingHours.isNullOrEmpty()) {
                    binding.textOpeningHours.text = it.openingHours
                    binding.layoutOpeningHours.visibility = View.VISIBLE
                } else {
                    binding.layoutOpeningHours.visibility = View.GONE
                }
                
                setupClickListeners(it)
            }
        }
        
        viewModel.getProductsForStore(storeId).observe(this) { products ->
            adapter.submitList(products)
            
            if (products.isEmpty()) {
                binding.emptyStateLayout.visibility = View.VISIBLE
                binding.recyclerViewProducts.visibility = View.GONE
            } else {
                binding.emptyStateLayout.visibility = View.GONE
                binding.recyclerViewProducts.visibility = View.VISIBLE
            }
            
            binding.textProductCount.text = "${products.size} produkter"
        }
        
        if (totalSavings > 0) {
            binding.textTotalSavings.text = String.format("%.2f kr", totalSavings)
            binding.layoutSavings.visibility = View.VISIBLE
        } else {
            binding.layoutSavings.visibility = View.GONE
        }
        
        if (itemCount > 0) {
            binding.textRecommendedItems.text = "$itemCount rekommenderade varor"
            binding.layoutSavings.visibility = View.VISIBLE
        }
    }
    
    private fun setupClickListeners(store: com.example.budgetapp.database.entities.Store) {
        binding.layoutStoreAddress.setOnClickListener {
            if (!store.address.isNullOrEmpty()) {
                val uri = if (store.latitude != null && store.longitude != null) {
                    Uri.parse("geo:${store.latitude},${store.longitude}?q=${Uri.encode(store.address)}")
                } else {
                    Uri.parse("geo:0,0?q=${Uri.encode(store.address + if (!store.city.isNullOrEmpty()) ", ${store.city}" else "")}")
                }
                val intent = Intent(Intent.ACTION_VIEW, uri)
                intent.setPackage("com.google.android.apps.maps")
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                } else {
                    val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.google.com/maps?q=${Uri.encode(store.address)}"))
                    startActivity(webIntent)
                }
            }
        }
        
        binding.layoutStorePhone.setOnClickListener {
            if (!store.phoneNumber.isNullOrEmpty()) {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${store.phoneNumber}"))
                startActivity(intent)
            }
        }
        
        binding.layoutStoreWebsite.setOnClickListener {
            if (!store.website.isNullOrEmpty()) {
                var url = store.website
                if (!url.startsWith("http://") && !url.startsWith("https://")) {
                    url = "https://$url"
                }
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }
        }
    }
    
    private fun loadStoreDetails() {
        lifecycleScope.launch {
            viewModel.loadStoreDetails(storeId)
        }
    }
    
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.store_detail_menu, menu)
        return true
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit_store -> {
                val intent = Intent(this, StoreManagerActivity::class.java)
                intent.putExtra("store_id", storeId)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}