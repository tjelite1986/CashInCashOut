package com.example.budgetapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.ShoppingList
import com.example.budgetapp.databinding.ActivityAddEditShoppingListBinding
import com.example.budgetapp.repository.ShoppingListRepository
import com.example.budgetapp.viewmodel.ShoppingListViewModel
import com.example.budgetapp.viewmodel.ShoppingListViewModelFactory
import com.google.android.material.snackbar.Snackbar

class AddEditShoppingListActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityAddEditShoppingListBinding
    private var shoppingListId: Long = -1
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
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditShoppingListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        shoppingListId = intent.getLongExtra("shopping_list_id", -1)
        isEditMode = shoppingListId != -1L
        
        setupToolbar()
        setupClickListeners()
        
        if (isEditMode) {
            loadShoppingList()
        }
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = if (isEditMode) "Redigera inköpslista" else "Ny inköpslista"
        }
        
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
    
    private fun setupClickListeners() {
        binding.buttonSave.setOnClickListener {
            saveShoppingList()
        }
        
        binding.buttonCancel.setOnClickListener {
            finish()
        }
    }
    
    private fun loadShoppingList() {
        viewModel.getShoppingListById(shoppingListId).observe(this) { shoppingList ->
            shoppingList?.let {
                binding.editTextName.setText(it.name)
                binding.editTextDescription.setText(it.description)
            }
        }
    }
    
    private fun saveShoppingList() {
        val name = binding.editTextName.text.toString().trim()
        val description = binding.editTextDescription.text.toString().trim()
        
        if (name.isEmpty()) {
            binding.editTextName.error = "Namn krävs"
            return
        }
        
        val shoppingList = if (isEditMode) {
            ShoppingList(
                id = shoppingListId,
                name = name,
                description = description.ifEmpty { null },
                updatedAt = System.currentTimeMillis()
            )
        } else {
            ShoppingList(
                name = name,
                description = description.ifEmpty { null }
            )
        }
        
        if (isEditMode) {
            viewModel.updateShoppingList(shoppingList)
            Snackbar.make(binding.root, "Inköpslista uppdaterad", Snackbar.LENGTH_SHORT).show()
        } else {
            viewModel.insertShoppingList(shoppingList)
            Snackbar.make(binding.root, "Inköpslista skapad", Snackbar.LENGTH_SHORT).show()
        }
        
        finish()
    }
}