package com.example.budgetapp

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.Expense
import com.example.budgetapp.database.entities.Receipt
import com.example.budgetapp.database.entities.ReceiptItem
import com.example.budgetapp.database.entities.Product
import com.example.budgetapp.database.entities.PriceHistory
import com.example.budgetapp.utils.CategoryConstants
import com.example.budgetapp.utils.ErrorHandler
import com.example.budgetapp.databinding.ActivityAddExpenseBinding
import com.example.budgetapp.databinding.DialogAddReceiptItemBinding
import com.example.budgetapp.data.ReceiptItemInput
import com.example.budgetapp.adapters.ReceiptItemAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import androidx.lifecycle.lifecycleScope
import androidx.core.widget.doOnTextChanged
import java.text.NumberFormat
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.button.MaterialButton
import android.widget.AutoCompleteTextView
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddExpenseActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityAddExpenseBinding
    private lateinit var database: BudgetDatabase
    private var selectedDate: Long = System.currentTimeMillis()
    private var selectedCategory: String = "Mat"
    private var selectedRecurringType: String? = null
    private var selectedPaymentMethod: String = "Kort"
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    
    private var editingExpenseId: Long? = null
    private var isEditing: Boolean = false
    
    // Receipt functionality
    private lateinit var receiptItemAdapter: ReceiptItemAdapter
    private val receiptItems = mutableListOf<ReceiptItemInput>()
    private val numberFormat = NumberFormat.getCurrencyInstance(Locale("sv", "SE"))
    private var existingProducts = listOf<Product>()
    private var existingStores = listOf<com.example.budgetapp.database.entities.Store>()
    private var currentReceiptDialog: DialogAddReceiptItemBinding? = null
    
    // Barcode scanner for receipt items
    private val receiptBarcodeScannerLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val barcode = result.data?.getStringExtra("barcode")
            if (barcode != null && currentReceiptDialog != null) {
                handleScannedBarcodeInReceipt(barcode, currentReceiptDialog!!)
            }
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            binding = ActivityAddExpenseBinding.inflate(layoutInflater)
            setContentView(binding.root)
            
            database = BudgetDatabase.getDatabase(this)
            
            // Check if we're editing an existing expense
            editingExpenseId = intent.getLongExtra("EXPENSE_ID", -1).takeIf { it != -1L }
            isEditing = editingExpenseId != null
            
            setupUI()
            setupSpinners()
            setupReceiptFunctionality()
            setupClickListeners()
            loadExistingProducts()
            loadExistingStores()
            
            if (isEditing) {
                loadExpenseForEditing()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Fel vid uppstart: ${e.message}", Toast.LENGTH_LONG).show()
            e.printStackTrace()
            finish()
        }
    }
    
    private fun setupUI() {
        try {
            val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = if (isEditing) "Redigera utgift" else "Lägg till utgift"
            
            toolbar.setNavigationOnClickListener {
                finish()
            }
        } catch (e: Exception) {
            // Fallback if toolbar setup fails
            supportActionBar?.title = if (isEditing) "Redigera utgift" else "Lägg till utgift"
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        
        // Set default date
        binding.btnSelectDate.text = dateFormat.format(Date(selectedDate))
        
        // Setup recurring visibility
        binding.switchRecurring.setOnCheckedChangeListener { _, isChecked ->
            binding.spinnerRecurringType.visibility = if (isChecked) {
                android.view.View.VISIBLE
            } else {
                android.view.View.GONE
            }
        }
    }
    
    private fun setupSpinners() {
        // Category spinner
        val categories = CategoryConstants.EXPENSE_CATEGORIES
        val categoryAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCategory.adapter = categoryAdapter
        
        binding.spinnerCategory.setOnItemSelectedListener(object : android.widget.AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: android.widget.AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                selectedCategory = categories[position]
            }
            
            override fun onNothingSelected(parent: android.widget.AdapterView<*>?) {
                selectedCategory = "Mat"
            }
        })
        
        // Payment method spinner
        val paymentMethods = CategoryConstants.PAYMENT_METHODS
        val paymentAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, paymentMethods)
        paymentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerPaymentMethod.adapter = paymentAdapter
        
        binding.spinnerPaymentMethod.setOnItemSelectedListener(object : android.widget.AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: android.widget.AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                selectedPaymentMethod = paymentMethods[position]
            }
            
            override fun onNothingSelected(parent: android.widget.AdapterView<*>?) {
                selectedPaymentMethod = "Kort"
            }
        })
        
        // Recurring type spinner
        val recurringTypes = CategoryConstants.RECURRING_TYPES
        val recurringAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, recurringTypes)
        recurringAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerRecurringType.adapter = recurringAdapter
        
        binding.spinnerRecurringType.setOnItemSelectedListener(object : android.widget.AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: android.widget.AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                selectedRecurringType = when (position) {
                    0 -> "monthly"
                    1 -> "weekly"
                    2 -> "yearly"
                    else -> null
                }
            }
            
            override fun onNothingSelected(parent: android.widget.AdapterView<*>?) {
                selectedRecurringType = null
            }
        })
    }
    
    private fun setupClickListeners() {
        binding.btnSelectDate.setOnClickListener {
            showDatePicker()
        }
        
        binding.btnSave.setOnClickListener {
            saveExpense()
        }
        
        binding.btnCancel.setOnClickListener {
            finish()
        }
    }
    
    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = selectedDate
        
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                selectedDate = calendar.timeInMillis
                binding.btnSelectDate.text = dateFormat.format(Date(selectedDate))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        
        datePickerDialog.show()
    }
    
    private fun loadExpenseForEditing() {
        editingExpenseId?.let { expenseId ->
            lifecycleScope.launch {
                try {
                    val expense = database.expenseDao().getExpenseById(expenseId)
                    expense?.let {
                        runOnUiThread {
                            binding.etTitle.setText(it.title)
                            binding.etAmount.setText(it.amount.toString())
                            binding.etDescription.setText(it.description ?: "")
                            binding.etStore.setText(it.store ?: "")
                            
                            selectedDate = it.date
                            binding.btnSelectDate.text = dateFormat.format(Date(selectedDate))
                            
                            selectedCategory = it.category ?: "Mat"
                            val categories = CategoryConstants.EXPENSE_CATEGORIES
                            val categoryIndex = categories.indexOf(selectedCategory).takeIf { idx -> idx >= 0 } ?: 0
                            binding.spinnerCategory.setSelection(categoryIndex)
                            
                            selectedPaymentMethod = it.paymentMethod ?: "Kort"
                            val paymentMethods = CategoryConstants.PAYMENT_METHODS
                            val paymentIndex = paymentMethods.indexOf(selectedPaymentMethod).takeIf { idx -> idx >= 0 } ?: 0
                            binding.spinnerPaymentMethod.setSelection(paymentIndex)
                            
                            binding.switchRecurring.isChecked = it.isRecurring
                            
                            if (it.isRecurring && it.recurringType != null) {
                                selectedRecurringType = it.recurringType
                                val recurringTypes = listOf("Månadsvis", "Veckovis", "Årligen")
                                val recurringIndex = recurringTypes.indexOf(it.recurringType).takeIf { idx -> idx >= 0 } ?: 0
                                binding.spinnerRecurringType.setSelection(recurringIndex)
                                binding.spinnerRecurringType.visibility = android.view.View.VISIBLE
                            } else {
                                binding.spinnerRecurringType.visibility = android.view.View.GONE
                            }
                            
                            // Load receipt data if it exists
                            lifecycleScope.launch {
                                loadReceiptDataForEditing(expenseId)
                            }
                        }
                    }
                } catch (e: Exception) {
                    runOnUiThread {
                        Toast.makeText(this@AddExpenseActivity, "Fel vid laddning av utgift: ${e.message}", Toast.LENGTH_LONG).show()
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    private fun saveExpense() {
        val title = binding.etTitle.text.toString().trim()
        val amountText = binding.etAmount.text.toString().trim()
        val description = binding.etDescription.text.toString().trim().takeIf { it.isNotEmpty() }
        val store = binding.etStore.text.toString().trim().takeIf { it.isNotEmpty() }
        
        // Validation
        if (title.isEmpty()) {
            binding.etTitle.error = "Titel krävs"
            binding.etTitle.requestFocus()
            return
        }
        
        val amount = amountText.toDoubleOrNull()
        if (amount == null || amount <= 0) {
            binding.etAmount.error = "Giltigt belopp krävs"
            binding.etAmount.requestFocus()
            return
        }
        
        if (amount > 1000000) {
            binding.etAmount.error = "Beloppet är för stort"
            binding.etAmount.requestFocus()
            return
        }
        
        val expense = if (isEditing && editingExpenseId != null) {
            Expense(
                id = editingExpenseId!!,
                title = title,
                amount = amount,
                description = description,
                category = selectedCategory,
                store = store,
                date = selectedDate,
                isRecurring = binding.switchRecurring.isChecked,
                recurringType = if (binding.switchRecurring.isChecked) selectedRecurringType else null,
                paymentMethod = selectedPaymentMethod,
                updatedAt = System.currentTimeMillis()
            )
        } else {
            Expense(
                title = title,
                amount = amount,
                description = description,
                category = selectedCategory,
                store = store,
                date = selectedDate,
                isRecurring = binding.switchRecurring.isChecked,
                recurringType = if (binding.switchRecurring.isChecked) selectedRecurringType else null,
                paymentMethod = selectedPaymentMethod
            )
        }
        
        lifecycleScope.launch {
            try {
                val expenseId = if (isEditing) {
                    database.expenseDao().updateExpense(expense)
                    expense.id
                } else {
                    database.expenseDao().insertExpense(expense)
                }
                
                // Save receipt data if there are items
                if (receiptItems.isNotEmpty()) {
                    saveReceiptData(expenseId, amount)
                }
                
                runOnUiThread {
                    val message = if (isEditing) "Utgift uppdaterad!" else "Utgift sparad!"
                    Toast.makeText(this@AddExpenseActivity, message, Toast.LENGTH_SHORT).show()
                    setResult(RESULT_OK)
                    finish()
                }
            } catch (e: Exception) {
                runOnUiThread {
                    ErrorHandler.logError("AddExpenseActivity", "Error saving expense", e)
                    ErrorHandler.handleDatabaseError(this@AddExpenseActivity, e)
                }
            }
        }
    }
    
    private fun setupReceiptFunctionality() {
        receiptItemAdapter = ReceiptItemAdapter(
            onEditClick = { item, position -> 
                showAddReceiptItemDialog(item, position)
            },
            onDeleteClick = { item, position ->
                receiptItems.removeAt(position)
                receiptItemAdapter.submitList(receiptItems.toList())
                updateReceiptTotal()
            }
        )
        
        binding.recyclerReceiptItems.apply {
            layoutManager = LinearLayoutManager(this@AddExpenseActivity)
            adapter = receiptItemAdapter
        }
        
        binding.buttonAddReceiptItem.setOnClickListener {
            showAddReceiptItemDialog()
        }
    }
    
    private fun loadExistingProducts() {
        lifecycleScope.launch {
            try {
                database.productDao().getAllProducts().collect { products ->
                    existingProducts = products
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    
    private fun loadExistingStores() {
        lifecycleScope.launch {
            try {
                database.storeDao().getAllStores().collect { stores ->
                    existingStores = stores
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    
    private fun showAddReceiptItemDialog(editItem: ReceiptItemInput? = null, editPosition: Int = -1) {
        val dialogBinding = DialogAddReceiptItemBinding.inflate(layoutInflater)
        currentReceiptDialog = dialogBinding
        
        // Setup store chain dropdown with better functionality
        setupChainDropdownForReceipt(dialogBinding)
        
        // Setup store name dropdown with more comprehensive store list
        setupStoreDropdowns(dialogBinding)
        
        // Setup store city autocomplete
        val storeCities = existingStores.mapNotNull { it.city }.distinct()
        val storeCityAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, storeCities)
        dialogBinding.autocompleteStoreCity.setAdapter(storeCityAdapter)
        
        // Setup units dropdown
        val units = listOf("st", "kg", "g", "l", "ml", "cl", "m", "cm")
        val unitAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, units)
        dialogBinding.dropdownUnit.setAdapter(unitAdapter)
        dialogBinding.dropdownUnit.keyListener = null // Förhindra manual inmatning
        
        // Setup product autocomplete
        val productNames = existingProducts.map { it.name }.distinct()
        val productAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, productNames)
        dialogBinding.autocompleteProductName.setAdapter(productAdapter)
        
        // Auto-fill existing product data when selected
        dialogBinding.autocompleteProductName.setOnItemClickListener { _, _, position, _ ->
            val selectedProductName = productNames[position]
            val existingProduct = existingProducts.find { it.name == selectedProductName }
            existingProduct?.let {
                dialogBinding.dropdownUnit.setText(it.unit, false)
                // Set deposit information if product has deposit
                if (it.hasDeposit && it.depositAmount != null) {
                    dialogBinding.switchHasDeposit.isChecked = true
                    dialogBinding.editDepositAmount.setText(it.depositAmount.toString())
                }
            }
        }
        
        // Setup deposit visibility toggle
        dialogBinding.switchHasDeposit.setOnCheckedChangeListener { _, isChecked ->
            dialogBinding.textInputDepositAmount.visibility = if (isChecked) {
                android.view.View.VISIBLE
            } else {
                android.view.View.GONE
            }
            // Update total when deposit toggle changes
            updateTotalPreview(dialogBinding)
        }
        
        // Pre-fill if editing
        editItem?.let {
            dialogBinding.autocompleteStoreName.setText(it.storeName ?: "")
            dialogBinding.dropdownStoreChain.setText(it.storeChain ?: "", false)
            dialogBinding.autocompleteStoreCity.setText(it.storeCity ?: "")
            dialogBinding.autocompleteProductName.setText(it.productName)
            dialogBinding.editQuantity.setText(it.quantity.toString())
            dialogBinding.dropdownUnit.setText(it.unit, false)
            dialogBinding.editUnitPrice.setText(it.unitPrice.toString())
            dialogBinding.editDiscount.setText(it.discount.toString())
            // Set deposit information
            dialogBinding.switchHasDeposit.isChecked = it.hasDeposit
            if (it.hasDeposit) {
                dialogBinding.editDepositAmount.setText(it.depositAmount.toString())
            }
            updateTotalPreview(dialogBinding)
        }
        
        // Setup text watchers for live total preview
        listOf(
            dialogBinding.editQuantity,
            dialogBinding.editUnitPrice,
            dialogBinding.editDiscount,
            dialogBinding.editDepositAmount
        ).forEach { editText ->
            editText.doOnTextChanged { _, _, _, _ ->
                updateTotalPreview(dialogBinding)
            }
        }
        
        val dialog = MaterialAlertDialogBuilder(this)
            .setView(dialogBinding.root)
            .create()
        
        // Setup barcode scanning
        dialogBinding.buttonScanBarcode.setOnClickListener {
            val intent = Intent(this, BarcodeScannerActivity::class.java)
            receiptBarcodeScannerLauncher.launch(intent)
        }
        
        // Setup "Add new store" button
        dialogBinding.buttonAddStore.setOnClickListener {
            showAddStoreDialogFromReceipt(dialogBinding)
        }
        
        dialogBinding.buttonCancel.setOnClickListener {
            currentReceiptDialog = null
            dialog.dismiss()
        }
        
        dialogBinding.buttonAdd.setOnClickListener {
            if (validateReceiptItemInput(dialogBinding)) {
                val item = createReceiptItemFromInput(dialogBinding)
                
                if (editPosition >= 0) {
                    receiptItems[editPosition] = item
                } else {
                    receiptItems.add(item)
                }
                
                receiptItemAdapter.submitList(receiptItems.toList())
                updateReceiptTotal()
                currentReceiptDialog = null
                dialog.dismiss()
                
                // Auto-save product and store if they don't exist
                saveProductIfNew(item)
                saveStoreIfNew(item)
            }
        }
        
        dialog.show()
    }
    
    private fun handleScannedBarcodeInReceipt(barcode: String, dialogBinding: DialogAddReceiptItemBinding) {
        // Hitta produkt med denna streckkod
        val existingProduct = existingProducts.find { it.barcode == barcode }
        
        if (existingProduct != null) {
            // Fyll i all produktinformation automatiskt
            dialogBinding.autocompleteProductName.setText(existingProduct.name)
            dialogBinding.dropdownUnit.setText(existingProduct.unit ?: "st", false)
            
            // Sätt pant-information om den finns
            if (existingProduct.hasDeposit && existingProduct.depositAmount != null) {
                dialogBinding.switchHasDeposit.isChecked = true
                dialogBinding.editDepositAmount.setText(existingProduct.depositAmount.toString())
            } else {
                dialogBinding.switchHasDeposit.isChecked = false
            }
            
            // Sätt mängd om den finns
            existingProduct.amount?.let { amount ->
                dialogBinding.editQuantity.setText(amount.toString())
            }
            
            // Hitta senaste pris för denna produkt från ProductStore
            lifecycleScope.launch {
                try {
                    // Ta endast första värdet från Flow, inte collect kontinuerligt
                    val productStores = database.productStoreDao().getPricesForProduct(existingProduct.id)
                    productStores.collect { stores ->
                        if (stores.isNotEmpty()) {
                            // Hitta senaste pris baserat på createdAt
                            val latestPrice = stores.maxByOrNull { it.createdAt }
                            
                            latestPrice?.let { ps ->
                                var finalPrice = ps.price
                                
                                // Debug: visa vad som finns i databasen
                                android.util.Log.d("BARCODE_SCAN", "Produkt pris: ${ps.price}, har kampanj: ${ps.hasCampaignPrice}")
                                if (ps.hasCampaignPrice) {
                                    android.util.Log.d("BARCODE_SCAN", "Kampanjpris: ${ps.campaignPrice}, antal: ${ps.campaignQuantity}")
                                }
                                
                                // För säkerhets skull: använd ALDRIG kampanjpris för scannning
                                // Detta förhindrar problem med felaktig data i databasen
                                // Använd bara det normala priset
                                finalPrice = ps.price
                                
                                runOnUiThread {
                                    // Avrunda till 2 decimaler för att undvika långa decimaltal
                                    val roundedPrice = String.format("%.2f", finalPrice).toDouble()
                                    dialogBinding.editUnitPrice.setText(roundedPrice.toString())
                                    updateTotalPreview(dialogBinding)
                                }
                            }
                        } else {
                            // Inget pris hittat, låt användaren mata in manuellt
                            runOnUiThread {
                                updateTotalPreview(dialogBinding)
                            }
                        }
                        // Avbryt collect efter första hämtningen
                        return@collect
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    runOnUiThread {
                        updateTotalPreview(dialogBinding)
                    }
                }
            }
            Toast.makeText(this, "Produkt hittad: ${existingProduct.name}", Toast.LENGTH_SHORT).show()
        } else {
            // Produkten finns inte, bara sätt streckkoden som produktnamn
            dialogBinding.autocompleteProductName.setText(barcode)
            Toast.makeText(this, "Okänd produkt. Ange produktnamn manuellt.", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun updateTotalPreview(dialogBinding: DialogAddReceiptItemBinding) {
        try {
            val quantity = dialogBinding.editQuantity.text.toString().toDoubleOrNull() ?: 0.0
            val unitPrice = dialogBinding.editUnitPrice.text.toString().toDoubleOrNull() ?: 0.0
            val discount = dialogBinding.editDiscount.text.toString().toDoubleOrNull() ?: 0.0
            val hasDeposit = dialogBinding.switchHasDeposit.isChecked
            val depositAmount = if (hasDeposit) {
                dialogBinding.editDepositAmount.text.toString().toDoubleOrNull() ?: 0.0
            } else 0.0
            
            // Total = (antal × (styckpris + pant)) - rabatt
            val total = (quantity * (unitPrice + depositAmount)) - discount
            dialogBinding.textTotalPreview.text = numberFormat.format(total)
        } catch (e: Exception) {
            dialogBinding.textTotalPreview.text = "0,00 kr"
        }
    }
    
    private fun validateReceiptItemInput(dialogBinding: DialogAddReceiptItemBinding): Boolean {
        var isValid = true
        
        if (dialogBinding.autocompleteProductName.text.isNullOrBlank()) {
            dialogBinding.textInputProductName.error = "Produktnamn krävs"
            isValid = false
        } else {
            dialogBinding.textInputProductName.error = null
        }
        
        val quantity = dialogBinding.editQuantity.text.toString().toDoubleOrNull()
        if (quantity == null || quantity <= 0) {
            dialogBinding.textInputQuantity.error = "Ange giltigt antal"
            isValid = false
        } else {
            dialogBinding.textInputQuantity.error = null
        }
        
        val unitPrice = dialogBinding.editUnitPrice.text.toString().toDoubleOrNull()
        if (unitPrice == null || unitPrice < 0) {
            dialogBinding.textInputUnitPrice.error = "Ange giltigt pris"
            isValid = false
        } else {
            dialogBinding.textInputUnitPrice.error = null
        }
        
        return isValid
    }
    
    private fun createReceiptItemFromInput(dialogBinding: DialogAddReceiptItemBinding): ReceiptItemInput {
        val productName = dialogBinding.autocompleteProductName.text.toString()
        val quantity = dialogBinding.editQuantity.text.toString().toDouble()
        val unit = dialogBinding.dropdownUnit.text.toString()
        val unitPrice = dialogBinding.editUnitPrice.text.toString().toDouble()
        val discount = dialogBinding.editDiscount.text.toString().toDoubleOrNull() ?: 0.0
        val storeName = dialogBinding.autocompleteStoreName.text.toString().takeIf { it.isNotBlank() }
        val storeChain = dialogBinding.dropdownStoreChain.text.toString().takeIf { it.isNotBlank() }
        val storeCity = dialogBinding.autocompleteStoreCity.text.toString().takeIf { it.isNotBlank() }
        val hasDeposit = dialogBinding.switchHasDeposit.isChecked
        val depositAmount = if (hasDeposit) {
            dialogBinding.editDepositAmount.text.toString().toDoubleOrNull() ?: 0.0
        } else 0.0
        
        // Find existing product ID if it exists
        val existingProduct = existingProducts.find { it.name.equals(productName, ignoreCase = true) }
        
        return ReceiptItemInput(
            productName = productName,
            quantity = quantity,
            unitPrice = unitPrice,
            unit = unit,
            discount = discount,
            productId = existingProduct?.id,
            storeName = storeName,
            storeChain = storeChain,
            storeCity = storeCity,
            hasDeposit = hasDeposit,
            depositAmount = depositAmount
        )
    }
    
    private fun saveProductIfNew(item: ReceiptItemInput) {
        lifecycleScope.launch {
            try {
                val productId = if (item.productId == null) {
                    // Product doesn't exist, save it
                    val newProduct = Product(
                        name = item.productName,
                        categoryId = 1, // Default category, could be made configurable
                        amount = item.quantity,
                        unit = item.unit,
                        hasDeposit = item.hasDeposit,
                        depositAmount = if (item.hasDeposit) item.depositAmount else null
                    )
                    database.productDao().insertProduct(newProduct)
                } else {
                    item.productId
                }
                
                // Create ProductStore connection if store information exists
                item.storeName?.let { storeName ->
                    createProductStoreConnection(productId, item)
                }
                
            } catch (e: Exception) {
                ErrorHandler.logError("AddExpenseActivity", "Error saving new product", e)
                e.printStackTrace()
            }
        }
    }
    
    private fun saveStoreIfNew(item: ReceiptItemInput) {
        val storeName = item.storeName
        if (!storeName.isNullOrBlank()) {
            lifecycleScope.launch {
                try {
                    // Use database to check for duplicates with name, chain, and city
                    val duplicateCount = database.storeDao().checkDuplicateStoreWithCity(
                        name = storeName,
                        chain = item.storeChain ?: "",
                        city = item.storeCity ?: ""
                    )
                    
                    if (duplicateCount == 0) {
                        // Store doesn't exist, save it
                        val newStore = com.example.budgetapp.database.entities.Store(
                            name = storeName,
                            chain = item.storeChain,
                            city = item.storeCity
                        )
                        database.storeDao().insertStore(newStore)
                        
                        // Reload stores to update the cached list
                        loadExistingStores()
                    }
                } catch (e: Exception) {
                    ErrorHandler.logError("AddExpenseActivity", "Error checking/saving store", e)
                    e.printStackTrace()
                }
            }
        }
    }
    
    private fun updateReceiptTotal() {
        val total = receiptItems.sumOf { it.totalPrice }
        binding.textReceiptTotal.text = numberFormat.format(total)
        
        // Update main amount field to match receipt total
        if (receiptItems.isNotEmpty()) {
            binding.etAmount.setText(total.toString())
        }
        
        // Show/hide receipt summary
        binding.layoutReceiptSummary.visibility = if (receiptItems.isNotEmpty()) {
            android.view.View.VISIBLE
        } else {
            android.view.View.GONE
        }
    }
    
    private suspend fun saveReceiptData(expenseId: Long, totalAmount: Double) {
        try {
            // Create receipt
            val receipt = Receipt(
                expenseId = expenseId,
                totalAmount = totalAmount,
                tax = 0.0, // Could be calculated or entered separately
                discount = receiptItems.sumOf { it.discount }
            )
            val receiptId = database.receiptDao().insertReceipt(receipt)
            
            // Create receipt items
            val receiptItemEntities = receiptItems.map { item ->
                ReceiptItem(
                    receiptId = receiptId,
                    productId = item.productId,
                    productName = item.productName,
                    quantity = item.quantity,
                    unitPrice = item.unitPrice,
                    totalPrice = item.totalPrice,
                    unit = item.unit,
                    discount = item.discount,
                    hasDeposit = item.hasDeposit,
                    depositAmount = item.depositAmount,
                    storeName = item.storeName,
                    storeChain = item.storeChain,
                    storeCity = item.storeCity
                )
            }
            
            database.receiptItemDao().insertReceiptItems(receiptItemEntities)
            
        } catch (e: Exception) {
            ErrorHandler.logError("AddExpenseActivity", "Error saving receipt data", e)
            e.printStackTrace()
            throw e
        }
    }
    
    private fun setupStoreDropdowns(dialogBinding: DialogAddReceiptItemBinding) {
        // Setup store name dropdown
        val storeNames = existingStores.map { "${it.name}${if (it.city != null) " (${it.city})" else ""}" }.distinct().sorted()
        val storeNameAdapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, storeNames)
        dialogBinding.autocompleteStoreName.setAdapter(storeNameAdapter)
        dialogBinding.autocompleteStoreName.threshold = 1
        
        // Auto-fill chain and city when store is selected
        dialogBinding.autocompleteStoreName.setOnItemClickListener { _, _, position, _ ->
            val selectedStoreName = storeNameAdapter.getItem(position) ?: ""
            val storeName = selectedStoreName.split(" (").first()
            val matchingStore = existingStores.find { it.name == storeName }
            
            matchingStore?.let {
                dialogBinding.dropdownStoreChain.setText(it.chain ?: "", false)
                dialogBinding.autocompleteStoreCity.setText(it.city ?: "")
            }
        }
    }
    
    private fun setupChainDropdownForReceipt(dialogBinding: DialogAddReceiptItemBinding) {
        val storeChains = mutableListOf("ICA", "Coop", "Hemköp", "Willys", "Lidl", "Tempo", "Ica Nära", "City Gross", "Bergendahls", "Axfood")
        
        lifecycleScope.launch {
            try {
                val existingChains = database.storeDao().getDistinctChains()
                val allChains = (storeChains + existingChains).distinct().sorted()
                
                runOnUiThread {
                    val chainAdapter = ArrayAdapter(this@AddExpenseActivity, android.R.layout.simple_dropdown_item_1line, allChains)
                    dialogBinding.dropdownStoreChain.setAdapter(chainAdapter)
                    dialogBinding.dropdownStoreChain.threshold = 1
                    
                    // Enable dropdown show on click/focus
                    dialogBinding.dropdownStoreChain.setOnClickListener {
                        dialogBinding.dropdownStoreChain.showDropDown()
                    }
                    
                    dialogBinding.dropdownStoreChain.setOnFocusChangeListener { _, hasFocus ->
                        if (hasFocus) {
                            dialogBinding.dropdownStoreChain.showDropDown()
                        }
                    }
                }
            } catch (e: Exception) {
                runOnUiThread {
                    val chainAdapter = ArrayAdapter(this@AddExpenseActivity, android.R.layout.simple_dropdown_item_1line, storeChains)
                    dialogBinding.dropdownStoreChain.setAdapter(chainAdapter)
                    dialogBinding.dropdownStoreChain.threshold = 1
                }
            }
        }
    }
    
    private fun showAddStoreDialogFromReceipt(receiptDialogBinding: DialogAddReceiptItemBinding) {
        val storeDialogView = layoutInflater.inflate(R.layout.dialog_add_store, null)
        val nameInput = storeDialogView.findViewById<TextInputEditText>(R.id.etStoreName)
        val addressInput = storeDialogView.findViewById<TextInputEditText>(R.id.etStoreAddress)
        val cityInput = storeDialogView.findViewById<TextInputEditText>(R.id.etStoreCity)
        val chainInput = storeDialogView.findViewById<AutoCompleteTextView>(R.id.acStoreChain)
        
        // Setup chain dropdown for the store dialog
        val storeChains = mutableListOf("ICA", "Coop", "Hemköp", "Willys", "Lidl", "Tempo", "Ica Nära", "City Gross", "Bergendahls", "Axfood")
        lifecycleScope.launch {
            try {
                val existingChains = database.storeDao().getDistinctChains()
                val allChains = (storeChains + existingChains).distinct().sorted()
                runOnUiThread {
                    val chainAdapter = ArrayAdapter(this@AddExpenseActivity, android.R.layout.simple_dropdown_item_1line, allChains)
                    chainInput.setAdapter(chainAdapter)
                    chainInput.threshold = 1
                }
            } catch (e: Exception) {
                runOnUiThread {
                    val chainAdapter = ArrayAdapter(this@AddExpenseActivity, android.R.layout.simple_dropdown_item_1line, storeChains)
                    chainInput.setAdapter(chainAdapter)
                    chainInput.threshold = 1
                }
            }
        }
        
        val storeDialog = MaterialAlertDialogBuilder(this)
            .setView(storeDialogView)
            .create()
        
        // Setup save button
        val saveButton = storeDialogView.findViewById<MaterialButton>(R.id.btnSave)
        val cancelButton = storeDialogView.findViewById<MaterialButton>(R.id.btnCancel)
        
        saveButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val address = addressInput.text.toString().trim().takeIf { it.isNotEmpty() }
            val city = cityInput.text.toString().trim().takeIf { it.isNotEmpty() }
            val chain = chainInput.text.toString().trim().takeIf { it.isNotEmpty() }
            
            if (name.isNotEmpty()) {
                val newStore = com.example.budgetapp.database.entities.Store(
                    name = name,
                    address = address,
                    city = city,
                    chain = chain
                )
                
                lifecycleScope.launch {
                    try {
                        database.storeDao().insertStore(newStore)
                        
                        // Reload stores and update dropdown
                        loadExistingStores()
                        runOnUiThread {
                            setupStoreDropdowns(receiptDialogBinding)
                            
                            // Auto-select the newly created store
                            val displayName = "$name${if (city != null) " ($city)" else ""}"
                            receiptDialogBinding.autocompleteStoreName.setText(displayName)
                            receiptDialogBinding.dropdownStoreChain.setText(chain ?: "", false)
                            receiptDialogBinding.autocompleteStoreCity.setText(city ?: "")
                            
                            Toast.makeText(this@AddExpenseActivity, "Butik tillagd och vald", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        runOnUiThread {
                            Toast.makeText(this@AddExpenseActivity, "Fel vid sparning: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                storeDialog.dismiss()
            } else {
                Toast.makeText(this, "Butiksnamn krävs", Toast.LENGTH_SHORT).show()
            }
        }
        
        cancelButton.setOnClickListener {
            storeDialog.dismiss()
        }
        
        storeDialog.show()
    }
    
    private suspend fun createProductStoreConnection(productId: Long, item: ReceiptItemInput) {
        try {
            val storeName = item.storeName ?: return
            
            // Find or create store
            val existingStore = existingStores.find { store ->
                store.name.equals(storeName, ignoreCase = true) &&
                (store.chain?.equals(item.storeChain, ignoreCase = true) ?: (item.storeChain == null)) &&
                (store.city?.equals(item.storeCity, ignoreCase = true) ?: (item.storeCity == null))
            }
            
            val storeId = if (existingStore != null) {
                existingStore.id
            } else {
                // Create new store
                val newStore = com.example.budgetapp.database.entities.Store(
                    name = storeName,
                    chain = item.storeChain,
                    city = item.storeCity
                )
                database.storeDao().insertStore(newStore)
            }
            
            // Check if ProductStore connection already exists
            val existingProductStore = database.productStoreDao()
                .getProductStorePrice(productId, storeId)
            
            if (existingProductStore == null) {
                // Create new ProductStore connection
                val productStore = com.example.budgetapp.database.entities.ProductStore(
                    productId = productId,
                    storeId = storeId,
                    price = item.unitPrice,
                    lastSeen = System.currentTimeMillis()
                )
                database.productStoreDao().insertProductStore(productStore)
                
                // Save initial price to history
                val priceHistory = PriceHistory(
                    productId = productId,
                    storeId = storeId,
                    price = item.unitPrice,
                    source = "receipt_scan"
                )
                database.priceHistoryDao().insertPriceHistory(priceHistory)
            } else {
                // Update existing ProductStore with new price only if different
                if (existingProductStore.price != item.unitPrice) {
                    val updatedProductStore = existingProductStore.copy(
                        price = item.unitPrice,
                        lastSeen = System.currentTimeMillis()
                    )
                    database.productStoreDao().updateProductStore(updatedProductStore)
                    
                    // Save price change to history
                    val priceHistory = PriceHistory(
                        productId = productId,
                        storeId = storeId,
                        price = item.unitPrice,
                        source = "receipt_scan"
                    )
                    database.priceHistoryDao().insertPriceHistory(priceHistory)
                }
            }
            
        } catch (e: Exception) {
            ErrorHandler.logError("AddExpenseActivity", "Error creating ProductStore connection", e)
            e.printStackTrace()
        }
    }
    
    private suspend fun loadReceiptDataForEditing(expenseId: Long) {
        try {
            // Check if there's a receipt for this expense
            val receipt = database.receiptDao().getReceiptByExpenseId(expenseId)
            receipt?.let { existingReceipt ->
                // Load receipt items
                val receiptItems = database.receiptItemDao().getReceiptItemsByReceiptId(existingReceipt.id)
                
                // Convert ReceiptItem entities to ReceiptItemInput for UI
                val receiptInputItems = receiptItems.map { item ->
                    ReceiptItemInput(
                        productName = item.productName,
                        quantity = item.quantity,
                        unitPrice = item.unitPrice,
                        unit = item.unit,
                        discount = item.discount,
                        productId = item.productId,
                        storeName = item.storeName,
                        storeChain = item.storeChain,
                        storeCity = item.storeCity,
                        hasDeposit = item.hasDeposit,
                        depositAmount = item.depositAmount
                    )
                }
                
                runOnUiThread {
                    // Update receipt items list and adapter
                    this.receiptItems.clear()
                    this.receiptItems.addAll(receiptInputItems)
                    receiptItemAdapter.submitList(this.receiptItems.toList())
                    
                    // Show receipt section if there are items
                    if (receiptInputItems.isNotEmpty()) {
                        binding.recyclerReceiptItems.visibility = android.view.View.VISIBLE
                        updateReceiptTotal()
                    }
                }
            }
        } catch (e: Exception) {
            ErrorHandler.logError("AddExpenseActivity", "Error loading receipt data for editing", e)
            e.printStackTrace()
        }
    }
    
    override fun onSupportNavigateUp(): Boolean {
        try {
            finish()
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return super.onSupportNavigateUp()
        }
    }
}