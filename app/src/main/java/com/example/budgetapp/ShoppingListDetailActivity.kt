package com.example.budgetapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgetapp.adapters.ShoppingListItemAdapter
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.databinding.ActivityShoppingListDetailBinding
import com.example.budgetapp.repository.ShoppingListRepository
import com.example.budgetapp.viewmodel.ShoppingListViewModel
import com.example.budgetapp.viewmodel.ShoppingListViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat
import java.util.*

class ShoppingListDetailActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityShoppingListDetailBinding
    private lateinit var adapter: ShoppingListItemAdapter
    private var shoppingListId: Long = -1
    
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
        binding = ActivityShoppingListDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        shoppingListId = intent.getLongExtra("shopping_list_id", -1)
        if (shoppingListId == -1L) {
            finish()
            return
        }
        
        setupToolbar()
        setupRecyclerView()
        setupObservers()
        setupClickListeners()
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Inköpslista"
        }
        
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
    
    private fun setupRecyclerView() {
        adapter = ShoppingListItemAdapter(
            onItemClick = { item ->
                // Öppna redigering av vara
                val intent = Intent(this, AddEditShoppingListItemActivity::class.java)
                intent.putExtra("shopping_list_id", shoppingListId)
                intent.putExtra("item_id", item.id)
                startActivity(intent)
            },
            onDeleteClick = { item ->
                showDeleteItemConfirmationDialog(item.id, item.customItemName ?: "Vara")
            },
            onCompleteToggle = { item ->
                viewModel.toggleShoppingListItemStatus(item.id, !item.isCompleted)
            },
            onQuantityChange = { item, newQuantity ->
                if (newQuantity > 0) {
                    viewModel.updateShoppingListItemQuantity(item.id, newQuantity)
                }
            }
        )
        
        binding.recyclerViewItems.apply {
            layoutManager = LinearLayoutManager(this@ShoppingListDetailActivity)
            adapter = this@ShoppingListDetailActivity.adapter
        }
    }
    
    private fun setupObservers() {
        // Observera inköpslistan
        viewModel.getShoppingListById(shoppingListId).observe(this) { shoppingList ->
            shoppingList?.let {
                supportActionBar?.title = it.name
                binding.textListDescription.text = it.description ?: "Ingen beskrivning"
                
                val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale("sv", "SE"))
                binding.textCreatedDate.text = "Skapad: ${dateFormat.format(Date(it.createdAt))}"
                
                if (it.isCompleted) {
                    binding.chipStatus.text = "Slutförd"
                    binding.chipStatus.setChipBackgroundColorResource(R.color.success)
                } else {
                    binding.chipStatus.text = "Aktiv"
                    binding.chipStatus.setChipBackgroundColorResource(R.color.primary)
                }
            }
        }
        
        // Observera varor i listan
        viewModel.getItemsForShoppingList(shoppingListId).observe(this) { items ->
            adapter.submitList(items)
            
            if (items.isEmpty()) {
                binding.emptyStateLayout.visibility = View.VISIBLE
                binding.recyclerViewItems.visibility = View.GONE
            } else {
                binding.emptyStateLayout.visibility = View.GONE
                binding.recyclerViewItems.visibility = View.VISIBLE
            }
        }
        
        // Statistik
        viewModel.getItemCountForShoppingList(shoppingListId).observe(this) { count ->
            binding.textTotalItems.text = count.toString()
        }
        
        viewModel.getCompletedItemCountForShoppingList(shoppingListId).observe(this) { completedCount ->
            binding.textCompletedItems.text = completedCount.toString()
        }
        
        viewModel.getTotalEstimatedCostForShoppingList(shoppingListId).observe(this) { cost ->
            binding.textEstimatedCost.text = if (cost != null && cost > 0) {
                String.format("%.2f kr", cost)
            } else {
                "Ingen kostnad uppskattat"
            }
        }
        
        viewModel.getTotalActualCostForShoppingList(shoppingListId).observe(this) { cost ->
            binding.textActualCost.text = if (cost != null && cost > 0) {
                String.format("%.2f kr", cost)
            } else {
                "Ingen faktisk kostnad"
            }
        }
    }
    
    private fun setupClickListeners() {
        binding.fabAddItem.setOnClickListener {
            val intent = Intent(this, AddEditShoppingListItemActivity::class.java)
            intent.putExtra("shopping_list_id", shoppingListId)
            startActivity(intent)
        }
        
        binding.buttonAddFirstItem.setOnClickListener {
            val intent = Intent(this, AddEditShoppingListItemActivity::class.java)
            intent.putExtra("shopping_list_id", shoppingListId)
            startActivity(intent)
        }
    }
    
    private fun showDeleteItemConfirmationDialog(itemId: Long, itemName: String) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Ta bort vara")
            .setMessage("Är du säker på att du vill ta bort \"$itemName\" från listan?")
            .setPositiveButton("Ta bort") { _, _ ->
                viewModel.deleteShoppingListItem(itemId)
            }
            .setNegativeButton("Avbryt") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
    
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.shopping_list_detail_menu, menu)
        return true
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit_list -> {
                val intent = Intent(this, AddEditShoppingListActivity::class.java)
                intent.putExtra("shopping_list_id", shoppingListId)
                startActivity(intent)
                true
            }
            R.id.action_mark_completed -> {
                viewModel.toggleShoppingListStatus(shoppingListId, true)
                true
            }
            R.id.action_share_list -> {
                shareShoppingList()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    
    private fun shareShoppingList() {
        viewModel.getShoppingListById(shoppingListId).observe(this) { shoppingList ->
            viewModel.getItemsForShoppingList(shoppingListId).observe(this) { items ->
                if (shoppingList != null) {
                    val shareText = buildString {
                        appendLine("🛒 ${shoppingList.name}")
                        if (!shoppingList.description.isNullOrEmpty()) {
                            appendLine("${shoppingList.description}")
                            appendLine()
                        }
                        
                        items.forEach { item ->
                            val checkbox = if (item.isCompleted) "☑️" else "☐"
                            val name = item.customItemName ?: "Vara"
                            val quantity = if (item.quantity > 1) " (${item.quantity})" else ""
                            appendLine("$checkbox $name$quantity")
                        }
                        
                        appendLine()
                        appendLine("Skickat från Budget App")
                    }
                    
                    val shareIntent = Intent().apply {
                        action = Intent.ACTION_SEND
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, shareText)
                        putExtra(Intent.EXTRA_SUBJECT, "Inköpslista: ${shoppingList.name}")
                    }
                    
                    startActivity(Intent.createChooser(shareIntent, "Dela inköpslista"))
                }
            }
        }
    }
}