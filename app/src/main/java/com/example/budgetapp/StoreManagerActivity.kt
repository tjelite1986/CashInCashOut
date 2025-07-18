package com.example.budgetapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.Store
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class StoreManagerActivity : AppCompatActivity() {
    
    private lateinit var database: BudgetDatabase
    private lateinit var storeAdapter: StoreAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_manager)
        
        database = BudgetDatabase.getDatabase(this)
        
        setupViews()
        setupRecyclerView()
        setupFab()
        observeStores()
    }
    
    private fun setupViews() {
        try {
            val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "Hantera butiker"
            
            toolbar.setNavigationOnClickListener {
                finish()
            }
        } catch (e: Exception) {
            // Fallback if toolbar setup fails
            supportActionBar?.title = "Hantera butiker"
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }
    
    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.recyclerViewStores)
        storeAdapter = StoreAdapter(
            onEditClick = { store -> showEditStoreDialog(store) },
            onDeleteClick = { store -> showDeleteConfirmation(store) }
        )
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = storeAdapter
    }
    
    private fun setupFab() {
        fab = findViewById(R.id.fabAddStore)
        fab.setOnClickListener {
            showAddStoreDialog()
        }
    }
    
    private fun observeStores() {
        lifecycleScope.launch {
            database.storeDao().getAllStores().collectLatest { stores ->
                storeAdapter.submitList(stores)
            }
        }
    }
    
    private fun showAddStoreDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_store, null)
        val nameInput = dialogView.findViewById<TextInputEditText>(R.id.etStoreName)
        val addressInput = dialogView.findViewById<TextInputEditText>(R.id.etStoreAddress)
        val cityInput = dialogView.findViewById<TextInputEditText>(R.id.etStoreCity)
        val chainInput = dialogView.findViewById<TextInputEditText>(R.id.etStoreChain)
        
        val dialog = MaterialAlertDialogBuilder(this)
            .setView(dialogView)
            .create()
        
        // Set up custom buttons
        val saveButton = dialogView.findViewById<com.google.android.material.button.MaterialButton>(R.id.btnSave)
        val cancelButton = dialogView.findViewById<com.google.android.material.button.MaterialButton>(R.id.btnCancel)
        
        saveButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val address = addressInput.text.toString().trim().takeIf { it.isNotEmpty() }
            val city = cityInput.text.toString().trim().takeIf { it.isNotEmpty() }
            val chain = chainInput.text.toString().trim().takeIf { it.isNotEmpty() }
            
            if (name.isNotEmpty()) {
                val store = Store(
                    name = name,
                    address = address,
                    city = city,
                    chain = chain
                )
                
                lifecycleScope.launch {
                    database.storeDao().insertStore(store)
                    Toast.makeText(this@StoreManagerActivity, "Butik sparad", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Butiksnamn krävs", Toast.LENGTH_SHORT).show()
            }
        }
        
        cancelButton.setOnClickListener {
            dialog.dismiss()
        }
        
        dialog.show()
    }
    
    private fun showEditStoreDialog(store: Store) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_store, null)
        val nameInput = dialogView.findViewById<TextInputEditText>(R.id.etStoreName)
        val addressInput = dialogView.findViewById<TextInputEditText>(R.id.etStoreAddress)
        val cityInput = dialogView.findViewById<TextInputEditText>(R.id.etStoreCity)
        val chainInput = dialogView.findViewById<TextInputEditText>(R.id.etStoreChain)
        
        // Förifyll med befintlig data
        nameInput.setText(store.name)
        addressInput.setText(store.address)
        cityInput.setText(store.city)
        chainInput.setText(store.chain)
        
        val dialog = MaterialAlertDialogBuilder(this)
            .setView(dialogView)
            .create()
        
        // Set up custom buttons
        val saveButton = dialogView.findViewById<com.google.android.material.button.MaterialButton>(R.id.btnSave)
        val cancelButton = dialogView.findViewById<com.google.android.material.button.MaterialButton>(R.id.btnCancel)
        
        saveButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val address = addressInput.text.toString().trim().takeIf { it.isNotEmpty() }
            val city = cityInput.text.toString().trim().takeIf { it.isNotEmpty() }
            val chain = chainInput.text.toString().trim().takeIf { it.isNotEmpty() }
            
            if (name.isNotEmpty()) {
                val updatedStore = store.copy(
                    name = name,
                    address = address,
                    city = city,
                    chain = chain,
                    updatedAt = System.currentTimeMillis()
                )
                
                lifecycleScope.launch {
                    database.storeDao().updateStore(updatedStore)
                    Toast.makeText(this@StoreManagerActivity, "Butik uppdaterad", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Butiksnamn krävs", Toast.LENGTH_SHORT).show()
            }
        }
        
        cancelButton.setOnClickListener {
            dialog.dismiss()
        }
        
        dialog.show()
    }
    
    private fun showDeleteConfirmation(store: Store) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Ta bort butik")
            .setMessage("Är du säker på att du vill ta bort \"${store.name}\"? Detta kommer även ta bort alla priser för denna butik.")
            .setPositiveButton("Ta bort") { _, _ ->
                lifecycleScope.launch {
                    database.storeDao().deleteStore(store)
                    Toast.makeText(this@StoreManagerActivity, "Butik borttagen", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Avbryt", null)
            .show()
    }
}