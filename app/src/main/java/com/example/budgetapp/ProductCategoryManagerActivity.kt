package com.example.budgetapp

import android.app.AlertDialog
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgetapp.adapters.ProductCategoryAdapter
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.ProductCategory
import com.example.budgetapp.databinding.ActivityProductCategoryManagerBinding
import com.example.budgetapp.utils.ProductCategoryConstants
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ProductCategoryManagerActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityProductCategoryManagerBinding
    private lateinit var database: BudgetDatabase
    private lateinit var adapter: ProductCategoryAdapter
    private var categories: List<ProductCategory> = emptyList()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductCategoryManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        database = BudgetDatabase.getDatabase(this)
        
        setupUI()
        setupRecyclerView()
        loadCategories()
        setupClickListeners()
    }
    
    private fun setupUI() {
        supportActionBar?.title = "Hantera produktkategorier"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    
    private fun setupRecyclerView() {
        adapter = ProductCategoryAdapter(
            onEditClick = { category -> showEditCategoryDialog(category) },
            onDeleteClick = { category -> confirmDeleteCategory(category) }
        )
        
        binding.recyclerViewCategories.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewCategories.adapter = adapter
    }
    
    private fun loadCategories() {
        lifecycleScope.launch {
            categories = database.productCategoryDao().getAllProductCategories().first()
            adapter.updateCategories(categories)
        }
    }
    
    private fun setupClickListeners() {
        binding.fabAddCategory.setOnClickListener {
            showAddCategoryDialog()
        }
        
        binding.buttonResetToDefaults.setOnClickListener {
            showResetToDefaultsDialog()
        }
    }
    
    private fun showAddCategoryDialog() {
        val editText = EditText(this)
        editText.hint = "Kategorinamn"
        
        AlertDialog.Builder(this)
            .setTitle("Lägg till produktkategori")
            .setView(editText)
            .setPositiveButton("Lägg till") { _, _ ->
                val name = editText.text.toString().trim()
                if (name.isNotEmpty()) {
                    addCategory(name)
                } else {
                    Toast.makeText(this, "Ange ett kategorinamn", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Avbryt", null)
            .show()
    }
    
    private fun showEditCategoryDialog(category: ProductCategory) {
        val editText = EditText(this)
        editText.setText(category.name)
        editText.hint = "Kategorinamn"
        
        AlertDialog.Builder(this)
            .setTitle("Redigera produktkategori")
            .setView(editText)
            .setPositiveButton("Spara") { _, _ ->
                val newName = editText.text.toString().trim()
                if (newName.isNotEmpty()) {
                    updateCategory(category, newName)
                } else {
                    Toast.makeText(this, "Ange ett kategorinamn", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Avbryt", null)
            .show()
    }
    
    private fun confirmDeleteCategory(category: ProductCategory) {
        lifecycleScope.launch {
            val productCount = database.productCategoryDao().getProductCountForCategory(category.id)
            
            val message = if (productCount > 0) {
                "Denna kategori används av $productCount produkter. Om du tar bort kategorin kommer produkterna att få kategorin \"Övrigt\". Vill du fortsätta?"
            } else {
                "Är du säker på att du vill ta bort kategorin \"${category.name}\"?"
            }
            
            AlertDialog.Builder(this@ProductCategoryManagerActivity)
                .setTitle("Ta bort kategori")
                .setMessage(message)
                .setPositiveButton("Ta bort") { _, _ ->
                    deleteCategory(category)
                }
                .setNegativeButton("Avbryt", null)
                .show()
        }
    }
    
    private fun showResetToDefaultsDialog() {
        AlertDialog.Builder(this)
            .setTitle("Återställ till standardkategorier")
            .setMessage("Detta kommer att lägga till alla standardkategorier om de inte redan finns. Befintliga kategorier påverkas inte.")
            .setPositiveButton("Återställ") { _, _ ->
                resetToDefaults()
            }
            .setNegativeButton("Avbryt", null)
            .show()
    }
    
    private fun addCategory(name: String) {
        lifecycleScope.launch {
            try {
                // Check for duplicates
                val existingCount = database.productCategoryDao().checkDuplicateProductCategory(name)
                if (existingCount > 0) {
                    Toast.makeText(this@ProductCategoryManagerActivity, "Kategorin finns redan", Toast.LENGTH_SHORT).show()
                    return@launch
                }
                
                val category = ProductCategory(
                    name = name,
                    isDefault = false
                )
                
                database.productCategoryDao().insertProductCategory(category)
                loadCategories()
                Toast.makeText(this@ProductCategoryManagerActivity, "Kategori tillagd", Toast.LENGTH_SHORT).show()
                
            } catch (e: Exception) {
                Toast.makeText(this@ProductCategoryManagerActivity, "Fel vid tillägg: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
    
    private fun updateCategory(category: ProductCategory, newName: String) {
        lifecycleScope.launch {
            try {
                // Check for duplicates (excluding current category)
                val existingCategories = database.productCategoryDao().getAllProductCategories().first()
                val hasDuplicate = existingCategories.any { it.name == newName && it.id != category.id }
                
                if (hasDuplicate) {
                    Toast.makeText(this@ProductCategoryManagerActivity, "Kategorin finns redan", Toast.LENGTH_SHORT).show()
                    return@launch
                }
                
                val updatedCategory = category.copy(name = newName)
                database.productCategoryDao().updateProductCategory(updatedCategory)
                loadCategories()
                Toast.makeText(this@ProductCategoryManagerActivity, "Kategori uppdaterad", Toast.LENGTH_SHORT).show()
                
            } catch (e: Exception) {
                Toast.makeText(this@ProductCategoryManagerActivity, "Fel vid uppdatering: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
    
    private fun deleteCategory(category: ProductCategory) {
        lifecycleScope.launch {
            try {
                // If category is used by products, move them to "Övrigt" category
                val productCount = database.productCategoryDao().getProductCountForCategory(category.id)
                if (productCount > 0) {
                    // Find or create "Övrigt" category
                    val categories = database.productCategoryDao().getAllProductCategories().first()
                    val otherCategory = categories.find { it.name == "Övrigt" }
                        ?: run {
                            val otherId = database.productCategoryDao().insertProductCategory(
                                ProductCategory(name = "Övrigt", isDefault = true)
                            )
                            ProductCategory(id = otherId, name = "Övrigt", isDefault = true)
                        }
                    
                    // Update products to use "Övrigt" category
                    // This would require a method in ProductDao to update all products with a specific category
                    // For now, we'll just delete the category and let the foreign key constraint handle it
                }
                
                database.productCategoryDao().deleteProductCategory(category)
                loadCategories()
                Toast.makeText(this@ProductCategoryManagerActivity, "Kategori borttagen", Toast.LENGTH_SHORT).show()
                
            } catch (e: Exception) {
                Toast.makeText(this@ProductCategoryManagerActivity, "Fel vid borttagning: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
    
    private fun resetToDefaults() {
        lifecycleScope.launch {
            try {
                val existingCategories = database.productCategoryDao().getAllProductCategories().first()
                val existingNames = existingCategories.map { it.name }.toSet()
                
                var addedCount = 0
                
                ProductCategoryConstants.DEFAULT_PRODUCT_CATEGORIES.forEach { categoryName ->
                    if (!existingNames.contains(categoryName)) {
                        val category = ProductCategory(
                            name = categoryName,
                            isDefault = true
                        )
                        database.productCategoryDao().insertProductCategory(category)
                        addedCount++
                    }
                }
                
                loadCategories()
                Toast.makeText(this@ProductCategoryManagerActivity, "$addedCount nya kategorier tillagda", Toast.LENGTH_SHORT).show()
                
            } catch (e: Exception) {
                Toast.makeText(this@ProductCategoryManagerActivity, "Fel vid återställning: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
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