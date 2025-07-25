package com.example.budgetapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.budgetapp.database.BudgetDatabase
import kotlinx.coroutines.launch
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
    private var selectedProductId: Long? = null
    
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
    
    // Activity result launcher för produktval
    private val selectProductLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.let { data ->
                selectedProductId = data.getLongExtra("selected_product_id", -1L).takeIf { it != -1L }
                val productName = data.getStringExtra("selected_product_name") ?: ""
                
                // Fyll i produktinformation
                binding.editTextItemName.setText(productName)
                
                // Hämta bästa pris för produkten för kvantitet 1
                selectedProductId?.let { productId ->
                    lifecycleScope.launch {
                        try {
                            val database = BudgetDatabase.getDatabase(this@AddEditShoppingListItemActivity)
                            val bestPrice = calculateBestPriceForQuantity(database, productId, 1.0)
                            if (bestPrice != null && bestPrice > 0) {
                                binding.editTextEstimatedPrice.setText(String.format("%.2f", bestPrice))
                            }
                        } catch (e: Exception) {
                            // Ignorera fel vid prshämtning
                        }
                    }
                }
                
                // Visa att en produkt är vald
                Snackbar.make(binding.root, "Produkt vald: $productName", Snackbar.LENGTH_SHORT).show()
                
                // Fokusera på antal-fältet
                binding.editTextQuantity.requestFocus()
            }
        }
    }
    
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
            val intent = Intent(this, SelectProductActivity::class.java)
            selectProductLauncher.launch(intent)
        }
        
        // Lyssna på kvantitetsändringar för att uppdatera pris
        binding.editTextQuantity.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus && selectedProductId != null) {
                updatePriceBasedOnQuantity()
            }
        }
    }
    
    private fun loadShoppingListItem() {
        if (itemId != -1L) {
            lifecycleScope.launch {
                try {
                    val item = viewModel.getShoppingListItemById(itemId)
                    if (item != null) {
                        selectedProductId = item.productId
                        
                        // Sätt namn - använd produktnamn eller custom namn
                        val displayName = if (item.productId != null) {
                            // Hämta produktnamn från databasen
                            val database = BudgetDatabase.getDatabase(this@AddEditShoppingListItemActivity)
                            database.productDao().getProductById(item.productId)?.name ?: item.customItemName ?: ""
                        } else {
                            item.customItemName ?: ""
                        }
                        
                        binding.editTextItemName.setText(displayName)
                        binding.editTextQuantity.setText(item.quantity.toString())
                        
                        // Sätt enhet
                        val unitPosition = units.indexOf(item.unit)
                        if (unitPosition >= 0) {
                            binding.spinnerUnit.setSelection(unitPosition)
                        }
                        
                        // Sätt pris
                        item.estimatedPrice?.let { price ->
                            binding.editTextEstimatedPrice.setText(price.toString())
                        }
                        
                        // Sätt anteckningar
                        binding.editTextNotes.setText(item.notes ?: "")
                        
                        // Sätt prioritet
                        val priorityPosition = when (item.priority) {
                            1 -> 1   // Hög prioritet
                            -1 -> 2  // Låg prioritet
                            else -> 0 // Normal prioritet
                        }
                        binding.spinnerPriority.setSelection(priorityPosition)
                    }
                } catch (e: Exception) {
                    Snackbar.make(binding.root, "Fel vid laddning av vara: ${e.message}", Snackbar.LENGTH_LONG).show()
                }
            }
        }
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
                productId = selectedProductId,
                customItemName = if (selectedProductId != null) null else itemName,
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
                productId = selectedProductId,
                customItemName = if (selectedProductId != null) null else itemName,
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
    
    private fun updatePriceBasedOnQuantity() {
        val quantityStr = binding.editTextQuantity.text.toString().trim()
        val quantity = quantityStr.toDoubleOrNull()
        
        if (quantity != null && quantity > 0 && selectedProductId != null) {
            lifecycleScope.launch {
                try {
                    val database = BudgetDatabase.getDatabase(this@AddEditShoppingListItemActivity)
                    val bestPrice = calculateBestPriceForQuantity(database, selectedProductId!!, quantity)
                    if (bestPrice != null && bestPrice > 0) {
                        binding.editTextEstimatedPrice.setText(String.format("%.2f", bestPrice))
                    }
                } catch (e: Exception) {
                    // Ignorera fel vid prisberäkning
                }
            }
        }
    }
    
    /**
     * Beräknar bästa pris för en given kvantitet av en produkt.
     * Tar hänsyn till om kampanjpris är fördelaktigt baserat på kvantitet.
     * 
     * Exempel: Vara kostar 13,95 + 1kr pant, kampanj "2 för 20kr"
     * - 1 vara: 13,95 + 1 = 14,95 kr
     * - 2 varor: 20 + 2 = 22 kr (kampanj + pant per vara)
     * - 3 varor: 20 + 13,95 + 3 = 36,95 kr (kampanj för 2 + ordinarie för 1 + pant för alla)
     */
    private suspend fun calculateBestPriceForQuantity(database: BudgetDatabase, productId: Long, quantity: Double): Double? {
        val productStores = database.productDao().getProductStoresWithPrices(productId)
        if (productStores.isEmpty()) return null
        
        val product = database.productDao().getProductById(productId) ?: return null
        val depositAmount = if (product.hasDeposit) product.depositAmount ?: 0.0 else 0.0
        
        // Hitta det bästa totalpriset för den angivna kvantiteten
        var bestTotalPrice = Double.MAX_VALUE
        
        for (store in productStores) {
            val basePrice = store.price  // Baspris per vara (utan pant)
            val campaignPrice = store.campaignPrice
            val campaignQuantity = store.campaignQuantity
            
            // Beräkna totalpris för ordinarie köp
            val regularTotalPrice = (basePrice * quantity) + (depositAmount * quantity)
            
            // Beräkna totalpris för kampanjköp (om kampanj finns och är fördelaktig)
            val campaignTotalPrice = if (store.hasCampaignPrice && campaignPrice != null && campaignQuantity != null && campaignQuantity > 0) {
                // Kontrollera om kampanjen är fördelaktig per enhet
                val campaignPricePerUnit = campaignPrice / campaignQuantity
                
                if (campaignPricePerUnit < basePrice) {
                    // Kampanjen är fördelaktig, beräkna blandad pricing
                    val fullCampaignSets = (quantity / campaignQuantity).toInt()
                    val remainingItems = quantity % campaignQuantity
                    
                    // Totalkostnad = (antal kampanjset × kampanjpris) + (resterande × ordinarie pris) + (total pant)
                    (fullCampaignSets * campaignPrice) + (remainingItems * basePrice) + (depositAmount * quantity)
                } else {
                    // Kampanjen är inte fördelaktig, använd ordinarie pris
                    regularTotalPrice
                }
            } else {
                // Ingen kampanj tillgänglig
                regularTotalPrice
            }
            
            // Välj det billigaste alternativet för denna butik
            val bestPriceForThisStore = minOf(regularTotalPrice, campaignTotalPrice)
            
            if (bestPriceForThisStore < bestTotalPrice) {
                bestTotalPrice = bestPriceForThisStore
            }
        }
        
        return if (bestTotalPrice != Double.MAX_VALUE) {
            bestTotalPrice
        } else {
            null
        }
    }
}