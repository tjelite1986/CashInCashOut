package com.example.budgetapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.StoreChain
import com.example.budgetapp.adapters.StoreChainAdapter
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class StoreChainManagerActivity : AppCompatActivity() {
    
    private lateinit var database: BudgetDatabase
    private lateinit var storeChainAdapter: StoreChainAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var fab: FloatingActionButton
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_store_chain_manager)
        
        database = BudgetDatabase.getDatabase(this)
        
        setupViews()
        setupRecyclerView()
        setupFab()
        observeStoreChains()
    }
    
    private fun setupViews() {
        try {
            val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "Hantera butikskedjor"
            
            toolbar.setNavigationOnClickListener {
                finish()
            }
        } catch (e: Exception) {
            supportActionBar?.title = "Hantera butikskedjor"
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }
    
    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.recyclerViewStoreChains)
        storeChainAdapter = StoreChainAdapter(
            onEditClick = { chain -> showEditChainDialog(chain) },
            onDeleteClick = { chain -> showDeleteConfirmation(chain) },
            onToggleActiveClick = { chain -> toggleChainActive(chain) }
        )
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = storeChainAdapter
    }
    
    private fun setupFab() {
        fab = findViewById(R.id.fabAddStoreChain)
        fab.setOnClickListener {
            showAddChainDialog()
        }
    }
    
    private fun observeStoreChains() {
        lifecycleScope.launch {
            database.storeChainDao().getAllChains().collectLatest { chains ->
                storeChainAdapter.submitList(chains)
            }
        }
    }
    
    private fun showAddChainDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_store_chain, null)
        val nameInput = dialogView.findViewById<TextInputEditText>(R.id.etChainName)
        val websiteInput = dialogView.findViewById<TextInputEditText>(R.id.etChainWebsite)
        val descriptionInput = dialogView.findViewById<TextInputEditText>(R.id.etChainDescription)
        
        val dialog = MaterialAlertDialogBuilder(this)
            .setView(dialogView)
            .create()
        
        val saveButton = dialogView.findViewById<com.google.android.material.button.MaterialButton>(R.id.btnSave)
        val cancelButton = dialogView.findViewById<com.google.android.material.button.MaterialButton>(R.id.btnCancel)
        
        saveButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val website = websiteInput.text.toString().trim().takeIf { it.isNotEmpty() }
            val description = descriptionInput.text.toString().trim().takeIf { it.isNotEmpty() }
            
            if (name.isNotEmpty()) {
                lifecycleScope.launch {
                    // Check if chain already exists
                    val existingChain = database.storeChainDao().getChainByName(name)
                    if (existingChain != null) {
                        Toast.makeText(this@StoreChainManagerActivity, "En kedja med detta namn finns redan", Toast.LENGTH_SHORT).show()
                        return@launch
                    }
                    
                    val chain = StoreChain(
                        name = name,
                        website = website,
                        description = description,
                        isDefault = false
                    )
                    
                    database.storeChainDao().insertChain(chain)
                    Toast.makeText(this@StoreChainManagerActivity, "Butikskedja sparad", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Kedjenamn krävs", Toast.LENGTH_SHORT).show()
            }
        }
        
        cancelButton.setOnClickListener {
            dialog.dismiss()
        }
        
        dialog.show()
    }
    
    private fun showEditChainDialog(chain: StoreChain) {
        // Don't allow editing default chains
        if (chain.isDefault) {
            Toast.makeText(this, "Standardkedjor kan inte redigeras", Toast.LENGTH_SHORT).show()
            return
        }
        
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_store_chain, null)
        val nameInput = dialogView.findViewById<TextInputEditText>(R.id.etChainName)
        val websiteInput = dialogView.findViewById<TextInputEditText>(R.id.etChainWebsite)
        val descriptionInput = dialogView.findViewById<TextInputEditText>(R.id.etChainDescription)
        
        // Pre-fill with existing data
        nameInput.setText(chain.name)
        websiteInput.setText(chain.website)
        descriptionInput.setText(chain.description)
        
        val dialog = MaterialAlertDialogBuilder(this)
            .setView(dialogView)
            .create()
        
        val saveButton = dialogView.findViewById<com.google.android.material.button.MaterialButton>(R.id.btnSave)
        val cancelButton = dialogView.findViewById<com.google.android.material.button.MaterialButton>(R.id.btnCancel)
        
        saveButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val website = websiteInput.text.toString().trim().takeIf { it.isNotEmpty() }
            val description = descriptionInput.text.toString().trim().takeIf { it.isNotEmpty() }
            
            if (name.isNotEmpty()) {
                lifecycleScope.launch {
                    // Check if another chain with this name exists (excluding current chain)
                    val existingChain = database.storeChainDao().getChainByName(name)
                    if (existingChain != null && existingChain.id != chain.id) {
                        Toast.makeText(this@StoreChainManagerActivity, "En kedja med detta namn finns redan", Toast.LENGTH_SHORT).show()
                        return@launch
                    }
                    
                    val updatedChain = chain.copy(
                        name = name,
                        website = website,
                        description = description,
                        updatedAt = System.currentTimeMillis()
                    )
                    
                    database.storeChainDao().updateChain(updatedChain)
                    Toast.makeText(this@StoreChainManagerActivity, "Butikskedja uppdaterad", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Kedjenamn krävs", Toast.LENGTH_SHORT).show()
            }
        }
        
        cancelButton.setOnClickListener {
            dialog.dismiss()
        }
        
        dialog.show()
    }
    
    private fun showDeleteConfirmation(chain: StoreChain) {
        // Don't allow deleting default chains
        if (chain.isDefault) {
            Toast.makeText(this, "Standardkedjor kan inte tas bort", Toast.LENGTH_SHORT).show()
            return
        }
        
        MaterialAlertDialogBuilder(this)
            .setTitle("Ta bort butikskedja")
            .setMessage("Är du säker på att du vill ta bort \"${chain.name}\"? Detta kommer påverka alla butiker som tillhör denna kedja.")
            .setPositiveButton("Ta bort") { _, _ ->
                lifecycleScope.launch {
                    database.storeChainDao().deleteChain(chain)
                    Toast.makeText(this@StoreChainManagerActivity, "Butikskedja borttagen", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Avbryt", null)
            .show()
    }
    
    private fun toggleChainActive(chain: StoreChain) {
        // Don't allow deactivating default chains
        if (chain.isDefault && chain.isActive) {
            Toast.makeText(this, "Standardkedjor kan inte inaktiveras", Toast.LENGTH_SHORT).show()
            return
        }
        
        lifecycleScope.launch {
            if (chain.isActive) {
                database.storeChainDao().deactivateChain(chain.id)
                Toast.makeText(this@StoreChainManagerActivity, "Kedja inaktiverad", Toast.LENGTH_SHORT).show()
            } else {
                database.storeChainDao().activateChain(chain.id)
                Toast.makeText(this@StoreChainManagerActivity, "Kedja aktiverad", Toast.LENGTH_SHORT).show()
            }
        }
    }
}