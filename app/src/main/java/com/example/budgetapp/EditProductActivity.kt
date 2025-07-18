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
import com.example.budgetapp.databinding.ActivityEditProductBinding
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProductBinding
    private lateinit var database: BudgetDatabase
    private var categories: List<Category> = emptyList()
    private var selectedCategoryId: Long? = null
    private var selectedUnit: String = "st"
    private var productId: Long = -1
    private var currentProduct: Product? = null
    
    private val barcodeScannerLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val barcode = result.data?.getStringExtra("barcode")
            if (barcode != null) {
                binding.editTextBarcode.setText(barcode)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = BudgetDatabase.getDatabase(this)
        productId = intent.getLongExtra("product_id", -1)

        if (productId == -1L) {
            Toast.makeText(this, "Fel: Produkt-ID saknas", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        setupUI()
        loadCategories()
        setupUnitSpinner()
        setupClickListeners()
        loadProduct()
    }

    private fun setupUI() {
        supportActionBar?.title = "Redigera produkt"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
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

    private fun setupUnitSpinner() {
        val units = listOf("st", "g", "kg", "cl", "ml", "l", "mb", "gb")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerUnit.adapter = adapter
        
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

    private fun loadProduct() {
        lifecycleScope.launch {
            currentProduct = database.productDao().getProductById(productId)
            currentProduct?.let { product ->
                populateForm(product)
            }
        }
    }

    private fun populateForm(product: Product) {
        binding.editTextProductName.setText(product.name)
        binding.editTextProductBrandName.setText(product.productName ?: "")
        binding.editTextAmount.setText(product.amount?.toString() ?: "")
        binding.editTextBarcode.setText(product.barcode ?: "")
        binding.editTextDescription.setText(product.description ?: "")
        
        // Set unit spinner
        product.unit?.let { unit ->
            val units = listOf("st", "g", "kg", "cl", "ml", "l", "mb", "gb")
            val unitIndex = units.indexOf(unit)
            if (unitIndex >= 0) {
                binding.spinnerUnit.setSelection(unitIndex)
            }
        }
        
        // Set category
        product.categoryId?.let { categoryId ->
            val categoryIndex = categories.indexOfFirst { it.id == categoryId }
            if (categoryIndex >= 0) {
                binding.spinnerCategory.setSelection(categoryIndex + 1)
            }
        }
        
        // Set deposit
        binding.switchDeposit.isChecked = product.hasDeposit
        if (product.hasDeposit) {
            binding.editTextDepositAmount.setText(product.depositAmount?.toString() ?: "")
        }
        
    }

    private fun saveProduct() {
        val name = binding.editTextProductName.text.toString().trim()
        val productName = binding.editTextProductBrandName.text.toString().trim().takeIf { it.isNotEmpty() }
        val amountText = binding.editTextAmount.text.toString().trim()
        val description = binding.editTextDescription.text.toString().trim().takeIf { it.isNotEmpty() }
        val barcode = binding.editTextBarcode.text.toString().trim().takeIf { it.isNotEmpty() }
        
        // Validation
        if (name.isEmpty()) {
            binding.editTextProductName.error = "Produktnamn krävs"
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
        
        
        // Handle amount
        val amount = if (amountText.isNotEmpty()) {
            amountText.toDoubleOrNull()
        } else null
        
        val updatedProduct = currentProduct?.copy(
            name = name,
            categoryId = selectedCategoryId,
            hasDeposit = hasDeposit,
            depositAmount = depositAmount,
            barcode = barcode,
            productName = productName,
            description = description,
            amount = amount,
            unit = selectedUnit,
            updatedAt = System.currentTimeMillis()
        )
        
        if (updatedProduct != null) {
            lifecycleScope.launch {
                try {
                    database.productDao().updateProduct(updatedProduct)
                    Toast.makeText(this@EditProductActivity, "Produkt uppdaterad!", Toast.LENGTH_SHORT).show()
                    finish()
                } catch (e: Exception) {
                    Toast.makeText(this@EditProductActivity, "Fel vid uppdatering: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}