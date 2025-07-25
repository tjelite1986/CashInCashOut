package com.example.budgetapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.Product
import com.example.budgetapp.database.entities.ProductStore
import com.example.budgetapp.database.entities.PriceHistory
import com.example.budgetapp.database.entities.Store
import com.example.budgetapp.databinding.ActivityProductPriceManagerBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ProductPriceManagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProductPriceManagerBinding
    private lateinit var database: BudgetDatabase
    private lateinit var priceAdapter: ProductPriceAdapter
    private var productId: Long = -1
    private var currentProduct: Product? = null
    private var stores: List<Store> = emptyList()
    private var productPrices = mutableListOf<ProductStoreWithStore>()
    
    data class ProductStoreWithStore(
        val productStore: ProductStore,
        val store: Store
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductPriceManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = BudgetDatabase.getDatabase(this)
        productId = intent.getLongExtra("product_id", -1)

        if (productId == -1L) {
            Toast.makeText(this, "Fel: Produkt-ID saknas", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        setupUI()
        setupClickListeners()
        loadData()
    }

    private fun setupUI() {
        supportActionBar?.title = "Hantera priser"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupRecyclerView() {
        priceAdapter = ProductPriceAdapter(
            prices = productPrices,
            product = currentProduct,
            onEditClick = { productStoreWithStore ->
                editPrice(productStoreWithStore)
            },
            onDeleteClick = { productStoreWithStore ->
                showDeletePriceDialog(productStoreWithStore)
            }
        )
        
        binding.recyclerViewPrices.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewPrices.adapter = priceAdapter
    }

    private fun setupClickListeners() {
        binding.fabAddPrice.setOnClickListener {
            showAddPriceDialog()
        }
        
        binding.buttonPriceHistory.setOnClickListener {
            val intent = Intent(this, PriceHistoryActivity::class.java)
            intent.putExtra("product_id", productId)
            startActivity(intent)
        }
    }

    private fun loadData() {
        lifecycleScope.launch {
            // Load product
            currentProduct = database.productDao().getProductById(productId)
            currentProduct?.let { product ->
                binding.textViewProductName.text = product.name
                binding.textViewProductBrand.text = product.productName ?: ""
            }

            // Load stores
            stores = database.storeDao().getAllStores().first()

            // Setup RecyclerView after product is loaded
            setupRecyclerView()

            // Load prices
            loadPrices()
        }
    }

    private fun loadPrices() {
        lifecycleScope.launch {
            val productStores = database.productStoreDao().getPricesForProduct(productId).first()
            productPrices.clear()
            
            productStores.forEach { productStore ->
                val store = database.storeDao().getStoreById(productStore.storeId)
                if (store != null) {
                    productPrices.add(ProductStoreWithStore(productStore, store))
                }
            }
            
            priceAdapter.notifyDataSetChanged()
            
            // Update empty state
            if (productPrices.isEmpty()) {
                binding.textViewEmptyPrices.visibility = android.view.View.VISIBLE
                binding.recyclerViewPrices.visibility = android.view.View.GONE
            } else {
                binding.textViewEmptyPrices.visibility = android.view.View.GONE
                binding.recyclerViewPrices.visibility = android.view.View.VISIBLE
            }
        }
    }

    private fun showAddPriceDialog() {
        val availableStores = stores.filter { store ->
            productPrices.none { it.store.id == store.id }
        }
        
        if (availableStores.isEmpty()) {
            Toast.makeText(this, "Alla butiker har redan priser registrerade", Toast.LENGTH_SHORT).show()
            return
        }
        
        val storeNames = availableStores.map { "${it.name} (${it.chain ?: ""})" }
        val dialogBinding = com.example.budgetapp.databinding.DialogAddPriceBinding.inflate(layoutInflater)
        
        val storeAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, storeNames)
        storeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dialogBinding.spinnerStore.adapter = storeAdapter
        
        // Setup campaign price visibility
        dialogBinding.switchCampaignPrice.setOnCheckedChangeListener { _, isChecked ->
            dialogBinding.layoutCampaignPrice.visibility = if (isChecked) {
                android.view.View.VISIBLE
            } else {
                android.view.View.GONE
            }
        }
        
        AlertDialog.Builder(this)
            .setTitle("Lägg till pris")
            .setView(dialogBinding.root)
            .setPositiveButton("Spara") { _, _ ->
                val selectedStoreIndex = dialogBinding.spinnerStore.selectedItemPosition
                val price = dialogBinding.editTextPrice.text.toString().toDoubleOrNull()
                val hasCampaignPrice = dialogBinding.switchCampaignPrice.isChecked
                val campaignQuantity = dialogBinding.editTextCampaignQuantity.text.toString().toIntOrNull()
                val campaignPrice = dialogBinding.editTextCampaignPrice.text.toString().toDoubleOrNull()
                
                if (price != null && price > 0) {
                    val selectedStore = availableStores[selectedStoreIndex]
                    val productStore = ProductStore(
                        productId = productId,
                        storeId = selectedStore.id,
                        price = price,
                        hasCampaignPrice = hasCampaignPrice,
                        campaignQuantity = if (hasCampaignPrice) campaignQuantity else null,
                        campaignPrice = if (hasCampaignPrice) campaignPrice else null
                    )
                    
                    lifecycleScope.launch {
                        try {
                            database.productStoreDao().insertProductStore(productStore)
                            
                            // Save price history for new price
                            val priceHistory = PriceHistory(
                                productId = productId,
                                storeId = selectedStore.id,
                                price = price,
                                campaignPrice = if (hasCampaignPrice) campaignPrice else null,
                                source = "manual_entry"
                            )
                            database.priceHistoryDao().insertPriceHistory(priceHistory)
                            
                            loadPrices()
                            Toast.makeText(this@ProductPriceManagerActivity, "Pris tillagt!", Toast.LENGTH_SHORT).show()
                        } catch (e: Exception) {
                            Toast.makeText(this@ProductPriceManagerActivity, "Fel: ${e.message}", Toast.LENGTH_LONG).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Ange ett giltigt pris", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Avbryt", null)
            .show()
    }

    private fun editPrice(productStoreWithStore: ProductStoreWithStore) {
        val dialogBinding = com.example.budgetapp.databinding.DialogEditPriceBinding.inflate(layoutInflater)
        
        // Pre-fill with current values
        dialogBinding.textViewStore.text = "${productStoreWithStore.store.name} (${productStoreWithStore.store.chain})"
        dialogBinding.editTextPrice.setText(productStoreWithStore.productStore.price.toString())
        dialogBinding.switchCampaignPrice.isChecked = productStoreWithStore.productStore.hasCampaignPrice
        
        if (productStoreWithStore.productStore.hasCampaignPrice) {
            dialogBinding.editTextCampaignQuantity.setText(productStoreWithStore.productStore.campaignQuantity?.toString() ?: "")
            dialogBinding.editTextCampaignPrice.setText(productStoreWithStore.productStore.campaignPrice?.toString() ?: "")
        }
        
        // Setup campaign price visibility
        dialogBinding.switchCampaignPrice.setOnCheckedChangeListener { _, isChecked ->
            dialogBinding.layoutCampaignPrice.visibility = if (isChecked) {
                android.view.View.VISIBLE
            } else {
                android.view.View.GONE
            }
        }
        
        AlertDialog.Builder(this)
            .setTitle("Redigera pris")
            .setView(dialogBinding.root)
            .setPositiveButton("Spara") { _, _ ->
                val price = dialogBinding.editTextPrice.text.toString().toDoubleOrNull()
                val hasCampaignPrice = dialogBinding.switchCampaignPrice.isChecked
                val campaignQuantity = dialogBinding.editTextCampaignQuantity.text.toString().toIntOrNull()
                val campaignPrice = dialogBinding.editTextCampaignPrice.text.toString().toDoubleOrNull()
                
                if (price != null && price > 0) {
                    val updatedProductStore = productStoreWithStore.productStore.copy(
                        price = price,
                        hasCampaignPrice = hasCampaignPrice,
                        campaignQuantity = if (hasCampaignPrice) campaignQuantity else null,
                        campaignPrice = if (hasCampaignPrice) campaignPrice else null,
                        updatedAt = System.currentTimeMillis()
                    )
                    
                    lifecycleScope.launch {
                        try {
                            // Check if price actually changed
                            val oldPrice = productStoreWithStore.productStore.price
                            val oldCampaignPrice = productStoreWithStore.productStore.campaignPrice
                            
                            database.productStoreDao().updateProductStore(updatedProductStore)
                            
                            // Save price history if price changed
                            if (price != oldPrice || campaignPrice != oldCampaignPrice) {
                                val priceHistory = PriceHistory(
                                    productId = productId,
                                    storeId = productStoreWithStore.store.id,
                                    price = price,
                                    campaignPrice = if (hasCampaignPrice) campaignPrice else null,
                                    source = "manual_update"
                                )
                                database.priceHistoryDao().insertPriceHistory(priceHistory)
                            }
                            
                            loadPrices()
                            Toast.makeText(this@ProductPriceManagerActivity, "Pris uppdaterat!", Toast.LENGTH_SHORT).show()
                        } catch (e: Exception) {
                            Toast.makeText(this@ProductPriceManagerActivity, "Fel: ${e.message}", Toast.LENGTH_LONG).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Ange ett giltigt pris", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Avbryt", null)
            .show()
    }

    private fun showDeletePriceDialog(productStoreWithStore: ProductStoreWithStore) {
        AlertDialog.Builder(this)
            .setTitle("Ta bort pris")
            .setMessage("Är du säker på att du vill ta bort priset för ${productStoreWithStore.store.name}?")
            .setPositiveButton("Ta bort") { _, _ ->
                lifecycleScope.launch {
                    try {
                        database.productStoreDao().deleteProductStore(productStoreWithStore.productStore)
                        loadPrices()
                        Toast.makeText(this@ProductPriceManagerActivity, "Pris borttaget!", Toast.LENGTH_SHORT).show()
                    } catch (e: Exception) {
                        Toast.makeText(this@ProductPriceManagerActivity, "Fel: ${e.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }
            .setNegativeButton("Avbryt", null)
            .show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}