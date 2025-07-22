package com.example.budgetapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgetapp.adapters.ShoppingListAdapter
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.databinding.ActivityShoppingListBinding
import com.example.budgetapp.repository.ShoppingListRepository
import com.example.budgetapp.viewmodel.ShoppingListViewModel
import com.example.budgetapp.viewmodel.ShoppingListViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ShoppingListActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityShoppingListBinding
    private lateinit var adapter: ShoppingListAdapter
    
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
        binding = ActivityShoppingListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupToolbar()
        setupRecyclerView()
        setupObservers()
        setupClickListeners()
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Inköpslistor"
        }
        
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
    
    private fun setupRecyclerView() {
        adapter = ShoppingListAdapter(
            onItemClick = { shoppingList ->
                val intent = Intent(this, ShoppingListDetailActivity::class.java)
                intent.putExtra("shopping_list_id", shoppingList.id)
                startActivity(intent)
            },
            onDeleteClick = { shoppingList ->
                showDeleteConfirmationDialog(shoppingList.id, shoppingList.name)
            },
            onCompleteToggle = { shoppingList ->
                viewModel.toggleShoppingListStatus(shoppingList.id, !shoppingList.isCompleted)
            }
        )
        
        binding.recyclerViewShoppingLists.apply {
            layoutManager = LinearLayoutManager(this@ShoppingListActivity)
            adapter = this@ShoppingListActivity.adapter
        }
    }
    
    private fun setupObservers() {
        viewModel.allShoppingLists.observe(this) { shoppingLists ->
            adapter.submitList(shoppingLists)
            
            if (shoppingLists.isEmpty()) {
                binding.emptyStateLayout.visibility = View.VISIBLE
                binding.recyclerViewShoppingLists.visibility = View.GONE
            } else {
                binding.emptyStateLayout.visibility = View.GONE
                binding.recyclerViewShoppingLists.visibility = View.VISIBLE
            }
        }
        
        viewModel.activeShoppingListsCount.observe(this) { count ->
            binding.textActiveListsCount.text = count.toString()
        }
        
        viewModel.completedShoppingListsCount.observe(this) { count ->
            binding.textCompletedListsCount.text = count.toString()
        }
    }
    
    private fun setupClickListeners() {
        binding.fabAddShoppingList.setOnClickListener {
            val intent = Intent(this, AddEditShoppingListActivity::class.java)
            startActivity(intent)
        }
        
        binding.buttonCreateFirstList.setOnClickListener {
            val intent = Intent(this, AddEditShoppingListActivity::class.java)
            startActivity(intent)
        }
    }
    
    private fun showDeleteConfirmationDialog(shoppingListId: Long, listName: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Ta bort inköpslista")
            .setMessage("Är du säker på att du vill ta bort \"$listName\"? Detta kommer att ta bort alla varor i listan också.")
            .setPositiveButton("Ta bort") { _, _ ->
                viewModel.deleteShoppingList(shoppingListId)
            }
            .setNegativeButton("Avbryt") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}