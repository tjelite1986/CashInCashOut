package com.example.budgetapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.Category
import com.example.budgetapp.database.entities.Product
import com.example.budgetapp.database.entities.Store
import com.example.budgetapp.database.entities.ProductStore
import com.example.budgetapp.databinding.ActivityAddProductBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class AddProductActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityAddProductBinding
    private lateinit var database: BudgetDatabase
    private var categories: List<Category> = emptyList()
    private var stores: List<Store> = emptyList()
    private var selectedCategoryId: Long? = null
    private var selectedStoreId: Long? = null
    private var scannedBarcode: String? = null
    private var selectedUnit: String = "st"
    
    private val barcodeScannerLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val barcode = result.data?.getStringExtra("barcode")
            if (barcode != null) {
                scannedBarcode = barcode
                binding.editTextBarcode.setText(barcode)
                // Try to find existing product with this barcode
                checkExistingProduct(barcode)
            }
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            binding = ActivityAddProductBinding.inflate(layoutInflater)
            setContentView(binding.root)
            
            database = BudgetDatabase.getDatabase(this)
            
            setupUI()
            loadCategories()
            loadStores()
            setupUnitSpinner()
            setupStoreSpinner()
            setupClickListeners()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Fel vid uppstart: ${e.message}", Toast.LENGTH_LONG).show()
            finish()
        }
    }
    
    private fun setupUI() {
        try {
            val toolbar = findViewById<com.google.android.material.appbar.MaterialToolbar>(R.id.toolbar)
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "Lägg till produkt"
            
            toolbar.setNavigationOnClickListener {
                finish()
            }
        } catch (e: Exception) {
            // Fallback if toolbar setup fails
            supportActionBar?.title = "Lägg till produkt"
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        
        // Setup campaign price visibility
        binding.switchCampaignPrice.setOnCheckedChangeListener { _, isChecked ->
            binding.layoutCampaignPrice.visibility = if (isChecked) {
                android.view.View.VISIBLE
            } else {
                android.view.View.GONE
            }
        }
        
        // Setup deposit visibility
        binding.switchDeposit.setOnCheckedChangeListener { _, isChecked ->
            binding.layoutDeposit.visibility = if (isChecked) {
                android.view.View.VISIBLE
            } else {
                android.view.View.GONE
            }
        }
    }
    
    private fun loadCategories() {
        lifecycleScope.launch {
            categories = database.categoryDao().getAllCategories().first()
            setupCategorySpinner()
        }
    }
    
    private fun loadStores() {
        lifecycleScope.launch {
            stores = database.storeDao().getAllStores().first()
            setupStoreSpinner()
        }
    }
    
    private fun setupCategorySpinner() {
        val categoryNames = listOf("Välj kategori") + categories.map { it.name }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categoryNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCategory.adapter = adapter
        
        binding.spinnerCategory.setOnItemSelectedListener(object : android.widget.AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: android.widget.AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                selectedCategoryId = if (position > 0) {
                    categories[position - 1].id
                } else {
                    null
                }
            }
            
            override fun onNothingSelected(parent: android.widget.AdapterView<*>?) {
                selectedCategoryId = null
            }
        })
    }
    
    private fun setupStoreSpinner() {
        val storeNames = listOf("Välj butik *") + stores.map { "${it.name} (${it.chain ?: ""})" }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, storeNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerStore.adapter = adapter
        
        binding.spinnerStore.setOnItemSelectedListener(object : android.widget.AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: android.widget.AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                selectedStoreId = if (position > 0) {
                    stores[position - 1].id
                } else {
                    null
                }
            }
            
            override fun onNothingSelected(parent: android.widget.AdapterView<*>?) {
                selectedStoreId = null
            }
        })
    }
    
    private fun setupUnitSpinner() {
        val units = listOf("st", "g", "kg", "cl", "ml", "l", "mb", "gb")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerUnit.adapter = adapter
        
        // Set default selection to "st"
        binding.spinnerUnit.setSelection(0)
        
        binding.spinnerUnit.setOnItemSelectedListener(object : android.widget.AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: android.widget.AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                selectedUnit = units[position]
            }
            
            override fun onNothingSelected(parent: android.widget.AdapterView<*>?) {
                selectedUnit = "st"
            }
        })
    }
    
    private fun setupClickListeners() {
        binding.buttonScanBarcode.setOnClickListener {
            val intent = Intent(this, BarcodeScannerActivity::class.java)
            barcodeScannerLauncher.launch(intent)
        }
        
        binding.buttonSave.setOnClickListener {
            saveProduct()
        }
        
        binding.buttonCancel.setOnClickListener {
            finish()
        }
    }
    
    private fun checkExistingProduct(barcode: String) {
        lifecycleScope.launch {
            val existingProduct = database.productDao().getProductByBarcode(barcode)
            if (existingProduct != null) {
                // Pre-fill form with existing product data
                binding.editTextProductName.setText(existingProduct.name)
                binding.editTextProductBrandName.setText(existingProduct.productName ?: "")
                binding.editTextAmount.setText(existingProduct.amount?.toString() ?: "")
                binding.editTextDescription.setText(existingProduct.description ?: "")
                
                // Set unit spinner
                existingProduct.unit?.let { unit ->
                    val units = listOf("st", "g", "kg", "cl", "ml", "l", "mb", "gb")
                    val unitIndex = units.indexOf(unit)
                    if (unitIndex >= 0) {
                        binding.spinnerUnit.setSelection(unitIndex)
                    }
                }
                
                // Set category
                existingProduct.categoryId?.let { categoryId ->
                    val categoryIndex = categories.indexOfFirst { it.id == categoryId }
                    if (categoryIndex >= 0) {
                        binding.spinnerCategory.setSelection(categoryIndex + 1)
                    }
                }
                
                // Set deposit
                binding.switchDeposit.isChecked = existingProduct.hasDeposit
                if (existingProduct.hasDeposit) {
                    binding.editTextDepositAmount.setText(existingProduct.depositAmount?.toString() ?: "")
                }
                
                Toast.makeText(this@AddProductActivity, "Produkt hittades! Uppgifter ifyllda.", Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private fun saveProduct() {
        val name = binding.editTextProductName.text.toString().trim()
        val priceText = binding.editTextPrice.text.toString().trim()
        val productName = binding.editTextProductBrandName.text.toString().trim().takeIf { it.isNotEmpty() }
        val amountText = binding.editTextAmount.text.toString().trim()
        val description = binding.editTextDescription.text.toString().trim().takeIf { it.isNotEmpty() }
        val barcode = binding.editTextBarcode.text.toString().trim().takeIf { it.isNotEmpty() }
        
        // Validation
        if (name.isEmpty()) {
            binding.editTextProductName.error = "Produktnamn krävs"
            return
        }
        
        val price = priceText.toDoubleOrNull()
        if (price == null || price <= 0) {
            binding.editTextPrice.error = "Giltigt pris krävs"
            return
        }
        
        // Validate store selection
        if (selectedStoreId == null) {
            Toast.makeText(this, "Välj en butik", Toast.LENGTH_SHORT).show()
            return
        }
        
        // Handle deposit
        val hasDeposit = binding.switchDeposit.isChecked
        val depositAmount = if (hasDeposit) {
            val depositText = binding.editTextDepositAmount.text.toString().trim()
            if (depositText.isEmpty()) {
                binding.editTextDepositAmount.error = "Pantbelopp krävs"
                return
            }
            depositText.toDoubleOrNull() ?: 0.0
        } else null
        
        // Handle campaign price
        val hasCampaignPrice = binding.switchCampaignPrice.isChecked
        val campaignQuantity = if (hasCampaignPrice) {
            val quantityText = binding.editTextCampaignQuantity.text.toString().trim()
            if (quantityText.isEmpty()) {
                binding.editTextCampaignQuantity.error = "Kampanjantal krävs"
                return
            }
            quantityText.toIntOrNull() ?: 1
        } else null
        
        val campaignPrice = if (hasCampaignPrice) {
            val campaignPriceText = binding.editTextCampaignPrice.text.toString().trim()
            if (campaignPriceText.isEmpty()) {
                binding.editTextCampaignPrice.error = "Kampanjpris krävs"
                return
            }
            campaignPriceText.toDoubleOrNull() ?: 0.0
        } else null
        
        // Handle amount
        val amount = if (amountText.isNotEmpty()) {
            amountText.toDoubleOrNull()
        } else null
        
        val product = Product(
            name = name,
            categoryId = selectedCategoryId,
            hasDeposit = hasDeposit,
            depositAmount = depositAmount,
            barcode = barcode,
            productName = productName,
            description = description,
            amount = amount,
            unit = selectedUnit
        )
        
        lifecycleScope.launch {
            try {
                // First, check if product already exists
                var existingProduct = if (barcode != null) {
                    database.productDao().getProductByBarcode(barcode)
                } else {
                    null
                }
                
                val productId = if (existingProduct != null) {
                    // Update existing product
                    val updatedProduct = existingProduct.copy(
                        name = name,
                        categoryId = selectedCategoryId,
                        hasDeposit = hasDeposit,
                        depositAmount = depositAmount,
                        productName = productName,
                        description = description,
                        amount = amount,
                        unit = selectedUnit,
                        updatedAt = System.currentTimeMillis()
                    )
                    database.productDao().updateProduct(updatedProduct)
                    existingProduct.id
                } else {
                    // Insert new product
                    database.productDao().insertProduct(product)
                }
                
                // Now save the price for the selected store
                val productStore = ProductStore(
                    productId = productId,
                    storeId = selectedStoreId!!,
                    price = price,
                    hasCampaignPrice = hasCampaignPrice,
                    campaignQuantity = campaignQuantity,
                    campaignPrice = campaignPrice
                )
                
                database.productStoreDao().insertProductStore(productStore)
                
                Toast.makeText(this@AddProductActivity, "Produkt och pris sparad!", Toast.LENGTH_SHORT).show()
                finish()
            } catch (e: Exception) {
                Toast.makeText(this@AddProductActivity, "Fel vid sparande: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
    
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}