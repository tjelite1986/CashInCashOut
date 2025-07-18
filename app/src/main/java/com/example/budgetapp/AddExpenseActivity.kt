package com.example.budgetapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.Expense
import com.example.budgetapp.databinding.ActivityAddExpenseBinding
import com.google.android.material.appbar.MaterialToolbar
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
            setupClickListeners()
            
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
        val categories = listOf("Mat", "Transport", "Hygien", "Nöje", "Räkningar", "Kläder", "Hälsa", "Boende", "Övrigt")
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
        val paymentMethods = listOf("Kort", "Kontant", "Swish", "Faktura")
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
        val recurringTypes = listOf("Månadsvis", "Veckovis", "Årligen")
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
                            val categories = listOf("Mat", "Transport", "Hygien", "Nöje", "Räkningar", "Kläder", "Hälsa", "Boende", "Övrigt")
                            val categoryIndex = categories.indexOf(selectedCategory).takeIf { idx -> idx >= 0 } ?: 0
                            binding.spinnerCategory.setSelection(categoryIndex)
                            
                            selectedPaymentMethod = it.paymentMethod ?: "Kort"
                            val paymentMethods = listOf("Kort", "Kontant", "Swish", "Faktura")
                            val paymentIndex = paymentMethods.indexOf(selectedPaymentMethod).takeIf { idx -> idx >= 0 } ?: 0
                            binding.spinnerPaymentMethod.setSelection(paymentIndex)
                            
                            binding.switchRecurring.isChecked = it.isRecurring
                            
                            if (it.isRecurring && it.recurringType != null) {
                                selectedRecurringType = it.recurringType
                                val recurringTypes = listOf("monthly", "weekly", "yearly")
                                val recurringIndex = recurringTypes.indexOf(it.recurringType).takeIf { idx -> idx >= 0 } ?: 0
                                binding.spinnerRecurringType.setSelection(recurringIndex)
                                binding.spinnerRecurringType.visibility = android.view.View.VISIBLE
                            } else {
                                binding.spinnerRecurringType.visibility = android.view.View.GONE
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
            return
        }
        
        val amount = amountText.toDoubleOrNull()
        if (amount == null || amount <= 0) {
            binding.etAmount.error = "Giltigt belopp krävs"
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
                if (isEditing) {
                    database.expenseDao().updateExpense(expense)
                } else {
                    database.expenseDao().insertExpense(expense)
                }
                runOnUiThread {
                    val message = if (isEditing) "Utgift uppdaterad!" else "Utgift sparad!"
                    Toast.makeText(this@AddExpenseActivity, message, Toast.LENGTH_SHORT).show()
                    setResult(RESULT_OK)
                    finish()
                }
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(this@AddExpenseActivity, "Fel vid sparande: ${e.message}", Toast.LENGTH_LONG).show()
                    e.printStackTrace()
                }
            }
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