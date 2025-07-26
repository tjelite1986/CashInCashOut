package com.example.budgetapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.Income
import com.example.budgetapp.databinding.ActivityAddIncomeBinding
import com.example.budgetapp.utils.CategoryConstants
import com.example.budgetapp.analytics.ai.NLPCategorizationService
import com.example.budgetapp.analytics.ai.CategorySuggestion
import com.example.budgetapp.database.entities.Category
import com.google.android.material.appbar.MaterialToolbar
import androidx.core.widget.doOnTextChanged
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddIncomeActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityAddIncomeBinding
    private lateinit var database: BudgetDatabase
    private lateinit var nlpService: NLPCategorizationService
    private var selectedDate: Long = System.currentTimeMillis()
    private var selectedCategory: String = "Lön"
    private var selectedRecurringType: String? = null
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    
    private var editingIncomeId: Long? = null
    private var isEditing: Boolean = false
    
    // NLP categorization variables
    private var currentSuggestion: CategorySuggestion? = null
    private var hasUserSelectedCategory = false
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            binding = ActivityAddIncomeBinding.inflate(layoutInflater)
            setContentView(binding.root)
            
            database = BudgetDatabase.getDatabase(this)
            nlpService = NLPCategorizationService(this)
            
            // Check if we're editing an existing income
            editingIncomeId = intent.getLongExtra("INCOME_ID", -1).takeIf { it != -1L }
            isEditing = editingIncomeId != null
            
            setupUI()
            setupSpinners()
            setupClickListeners()
            setupNLPCategorization()
            
            if (isEditing) {
                loadIncomeForEditing()
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
            supportActionBar?.title = if (isEditing) "Redigera inkomst" else "Lägg till inkomst"
            
            toolbar.setNavigationOnClickListener {
                finish()
            }
        } catch (e: Exception) {
            // Fallback if toolbar setup fails
            supportActionBar?.title = if (isEditing) "Redigera inkomst" else "Lägg till inkomst"
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
        val categories = CategoryConstants.INCOME_CATEGORIES
        val categoryAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCategory.adapter = categoryAdapter
        
        binding.spinnerCategory.setOnItemSelectedListener(object : android.widget.AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: android.widget.AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                selectedCategory = categories[position]
                hasUserSelectedCategory = true
                
                // Learn from user correction if different from suggestion
                currentSuggestion?.let { suggestion ->
                    if (suggestion.category?.name != selectedCategory) {
                        learnFromUserCorrection(suggestion, selectedCategory)
                    }
                }
            }
            
            override fun onNothingSelected(parent: android.widget.AdapterView<*>?) {
                selectedCategory = "Lön"
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
            saveIncome()
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
    
    private fun loadIncomeForEditing() {
        editingIncomeId?.let { incomeId ->
            lifecycleScope.launch {
                try {
                    val income = database.incomeDao().getIncomeById(incomeId)
                    income?.let {
                        runOnUiThread {
                            binding.etTitle.setText(it.title)
                            binding.etAmount.setText(it.amount.toString())
                            binding.etDescription.setText(it.description ?: "")
                            
                            selectedDate = it.date
                            binding.btnSelectDate.text = dateFormat.format(Date(selectedDate))
                            
                            selectedCategory = it.category ?: "Lön"
                            val categories = CategoryConstants.INCOME_CATEGORIES
                            val categoryIndex = categories.indexOf(selectedCategory).takeIf { it >= 0 } ?: 0
                            binding.spinnerCategory.setSelection(categoryIndex)
                            
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
                        }
                    }
                } catch (e: Exception) {
                    runOnUiThread {
                        Toast.makeText(this@AddIncomeActivity, "Fel vid laddning av inkomst: ${e.message}", Toast.LENGTH_LONG).show()
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    private fun saveIncome() {
        val title = binding.etTitle.text.toString().trim()
        val amountText = binding.etAmount.text.toString().trim()
        val description = binding.etDescription.text.toString().trim().takeIf { it.isNotEmpty() }
        
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
        
        val income = if (isEditing && editingIncomeId != null) {
            Income(
                id = editingIncomeId!!,
                title = title,
                amount = amount,
                description = description,
                category = selectedCategory,
                date = selectedDate,
                isRecurring = binding.switchRecurring.isChecked,
                recurringType = if (binding.switchRecurring.isChecked) selectedRecurringType else null,
                updatedAt = System.currentTimeMillis()
            )
        } else {
            Income(
                title = title,
                amount = amount,
                description = description,
                category = selectedCategory,
                date = selectedDate,
                isRecurring = binding.switchRecurring.isChecked,
                recurringType = if (binding.switchRecurring.isChecked) selectedRecurringType else null
            )
        }
        
        lifecycleScope.launch {
            try {
                if (isEditing) {
                    database.incomeDao().updateIncome(income)
                } else {
                    database.incomeDao().insertIncome(income)
                }
                runOnUiThread {
                    val message = if (isEditing) "Inkomst uppdaterad!" else "Inkomst sparad!"
                    Toast.makeText(this@AddIncomeActivity, message, Toast.LENGTH_SHORT).show()
                    setResult(RESULT_OK)
                    finish()
                }
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(this@AddIncomeActivity, "Fel vid sparande: ${e.message}", Toast.LENGTH_LONG).show()
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
    
    /**
     * Setup NLP-based auto-categorization for income
     */
    private fun setupNLPCategorization() {
        // Auto-categorize when title or description changes
        binding.etTitle.doOnTextChanged { _, _, _, _ ->
            if (!hasUserSelectedCategory && !isEditing) {
                performAutoCategorization()
            }
        }
        
        binding.etDescription.doOnTextChanged { _, _, _, _ ->
            if (!hasUserSelectedCategory && !isEditing) {
                performAutoCategorization()
            }
        }
    }
    
    /**
     * Perform automatic categorization for income based on transaction text
     */
    private fun performAutoCategorization() {
        val title = binding.etTitle.text.toString().trim()
        val description = binding.etDescription.text.toString().trim()
        val amountText = binding.etAmount.text.toString().trim()
        
        if (title.length < 3) return // Too short to categorize reliably
        
        val amount = amountText.toDoubleOrNull() ?: 0.0
        
        lifecycleScope.launch {
            try {
                val suggestion = nlpService.categorizeTransaction(
                    title = title,
                    description = description,
                    amount = amount,
                    isIncome = true
                )
                
                suggestion?.let { applyCategorySuggestion(it) }
            } catch (e: Exception) {
                // Fail silently for auto-categorization
                com.example.budgetapp.utils.ErrorHandler.logError("AddIncomeActivity", "Auto-categorization failed", e)
            }
        }
    }
    
    /**
     * Apply the category suggestion to the UI
     */
    private fun applyCategorySuggestion(suggestion: CategorySuggestion) {
        runOnUiThread {
            currentSuggestion = suggestion
            
            // Find the matching category in our spinner
            val categories = CategoryConstants.INCOME_CATEGORIES
            val matchingIndex = when {
                suggestion.category != null -> {
                    categories.indexOfFirst { it.equals(suggestion.category.name, ignoreCase = true) }
                }
                else -> -1
            }
            
            if (matchingIndex >= 0) {
                binding.spinnerCategory.setSelection(matchingIndex)
                selectedCategory = categories[matchingIndex]
                
                // Show suggestion confidence to user
                showCategorySuggestionFeedback(suggestion)
            }
        }
    }
    
    /**
     * Show feedback about the category suggestion
     */
    private fun showCategorySuggestionFeedback(suggestion: CategorySuggestion) {
        val confidencePercent = (suggestion.confidence * 100).toInt()
        val message = "Auto-kategoriserad som '${suggestion.category?.name}' ($confidencePercent% säkerhet)"
        
        // Show a subtle toast for high-confidence suggestions
        if (suggestion.confidence > 0.8f) {
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
    
    /**
     * Learn from user correction to improve future categorization
     */
    private fun learnFromUserCorrection(suggestion: CategorySuggestion, actualCategory: String) {
        lifecycleScope.launch {
            try {
                // Find the actual category ID
                val categories = mutableListOf<Category>()
                database.categoryDao().getAllCategories().collect { categories.addAll(it) }
                val actualCategoryEntity = categories.find { 
                    it.name.equals(actualCategory, ignoreCase = true) 
                }
                
                actualCategoryEntity?.let { category ->
                    val originalText = "${binding.etTitle.text} ${binding.etDescription.text}"
                    nlpService.learnFromCorrection(
                        originalText = originalText,
                        suggestedCategoryId = suggestion.category?.id,
                        actualCategoryId = category.id
                    )
                }
            } catch (e: Exception) {
                com.example.budgetapp.utils.ErrorHandler.logError("AddIncomeActivity", "Failed to learn from correction", e)
            }
        }
    }
}