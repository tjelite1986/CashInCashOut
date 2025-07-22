package com.example.budgetapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.ShoppingListItem
import com.example.budgetapp.databinding.ActivityAddEditShoppingListItemBinding
import com.example.budgetapp.repository.ShoppingListRepository
import com.example.budgetapp.viewmodel.ShoppingListViewModel
import com.example.budgetapp.viewmodel.ShoppingListViewModelFactory
import com.google.android.material.snackbar.Snackbar

class AddEditShoppingListItemActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityAddEditShoppingListItemBinding
    private var shoppingListId: Long = -1
    private var itemId: Long = -1
    private var isEditMode = false
    
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
    
    // Fördefinierade enheter
    private val units = arrayOf("st", "kg", "g", "l", "ml", "cl", "förp", "burk", "påse", "flaska")
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditShoppingListItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        shoppingListId = intent.getLongExtra("shopping_list_id", -1)
        itemId = intent.getLongExtra("item_id", -1)
        isEditMode = itemId != -1L
        
        if (shoppingListId == -1L) {
            finish()
            return
        }
        
        setupToolbar()
        setupSpinners()
        setupClickListeners()
        
        if (isEditMode) {
            loadShoppingListItem()
        }
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = if (isEditMode) "Redigera vara" else "Lägg till vara"
        }
        
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
    
    private fun setupSpinners() {
        // Enhet spinner
        val unitAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, units)
        unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerUnit.adapter = unitAdapter
        
        // Prioritet spinner
        val priorities = arrayOf("Normal", "Hög prioritet", "Låg prioritet")
        val priorityAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, priorities)
        priorityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerPriority.adapter = priorityAdapter
    }
    
    private fun setupClickListeners() {
        binding.buttonSave.setOnClickListener {
            saveShoppingListItem()
        }
        
        binding.buttonCancel.setOnClickListener {
            finish()
        }
        
        // Lägg till från befintliga produkter knapp
        binding.buttonSelectFromProducts.setOnClickListener {
            // TODO: Implementera val från befintliga produkter
            Snackbar.make(binding.root, "Välj från produkter kommer snart!", Snackbar.LENGTH_SHORT).show()
        }
    }
    
    private fun loadShoppingListItem() {
        // Implementera laddning av befintlig vara för redigering
        // För nu sätter vi bara placeholder-data
        binding.editTextItemName.setText("Exempelprodukt")
        binding.editTextQuantity.setText("1")
        binding.spinnerUnit.setSelection(0)
        binding.editTextEstimatedPrice.setText("25.00")
        binding.editTextNotes.setText("Anteckningar...")
    }
    
    private fun saveShoppingListItem() {
        val itemName = binding.editTextItemName.text.toString().trim()
        val quantityStr = binding.editTextQuantity.text.toString().trim()
        val estimatedPriceStr = binding.editTextEstimatedPrice.text.toString().trim()
        val notes = binding.editTextNotes.text.toString().trim()
        
        // Validering
        if (itemName.isEmpty()) {
            binding.editTextItemName.error = "Produktnamn krävs"
            return
        }
        
        val quantity = quantityStr.toIntOrNull()
        if (quantity == null || quantity <= 0) {
            binding.editTextQuantity.error = "Giltigt antal krävs"
            return
        }
        
        val estimatedPrice = if (estimatedPriceStr.isNotEmpty()) {
            estimatedPriceStr.toDoubleOrNull()
        } else {
            null
        }
        
        val selectedUnit = binding.spinnerUnit.selectedItem.toString()
        val selectedPriorityIndex = binding.spinnerPriority.selectedItemPosition
        val priority = when (selectedPriorityIndex) {
            1 -> 1  // Hög prioritet
            2 -> -1 // Låg prioritet
            else -> 0 // Normal prioritet
        }
        
        val shoppingListItem = if (isEditMode) {
            ShoppingListItem(
                id = itemId,
                shoppingListId = shoppingListId,
                productId = null, // TODO: Hantera koppling till befintliga produkter
                customItemName = itemName,
                quantity = quantity,
                unit = selectedUnit,
                estimatedPrice = estimatedPrice,
                notes = notes.ifEmpty { null },
                priority = priority,
                updatedAt = System.currentTimeMillis()
            )
        } else {
            ShoppingListItem(
                shoppingListId = shoppingListId,
                productId = null, // TODO: Hantera koppling till befintliga produkter
                customItemName = itemName,
                quantity = quantity,
                unit = selectedUnit,
                estimatedPrice = estimatedPrice,
                notes = notes.ifEmpty { null },
                priority = priority
            )
        }
        
        if (isEditMode) {
            viewModel.updateShoppingListItem(shoppingListItem)
            Snackbar.make(binding.root, "Vara uppdaterad", Snackbar.LENGTH_SHORT).show()
        } else {
            viewModel.insertShoppingListItem(shoppingListItem)
            Snackbar.make(binding.root, "Vara tillagd", Snackbar.LENGTH_SHORT).show()
        }
        
        finish()
    }
}