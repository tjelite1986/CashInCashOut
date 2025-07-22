package com.example.budgetapp.activities

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.budgetapp.R
import com.example.budgetapp.databinding.ActivityAddEditBudgetBinding
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.Budget
import com.example.budgetapp.database.entities.BudgetPeriod
import com.example.budgetapp.repository.BudgetRepository
import com.example.budgetapp.utils.ErrorHandler
import com.example.budgetapp.utils.CategoryConstants
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddEditBudgetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEditBudgetBinding
    private lateinit var budgetRepository: BudgetRepository
    
    private var budgetId: Int? = null
    private var existingBudget: Budget? = null
    private var selectedStartDate = Calendar.getInstance()
    
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    
    // Kategorier baserat på befintliga utgiftskategorier
    private val expenseCategories = CategoryConstants.EXPENSE_CATEGORIES
    
    private val periodOptions = mapOf(
        "Vecka" to BudgetPeriod.WEEKLY,
        "Månad" to BudgetPeriod.MONTHLY,
        "Kvartal" to BudgetPeriod.QUARTERLY,
        "År" to BudgetPeriod.YEARLY
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditBudgetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        try {
            initializeComponents()
            setupToolbar()
            setupDropdowns()
            setupClickListeners()
            setupBackPressedHandler()
            loadBudgetData()
        } catch (e: Exception) {
            ErrorHandler.logError("AddEditBudgetActivity", "Error during initialization", e)
            ErrorHandler.handleDatabaseError(this, e)
            finish()
        }
    }
    
    private fun initializeComponents() {
        val database = BudgetDatabase.getDatabase(this)
        budgetRepository = BudgetRepository(database)
        
        budgetId = intent.getIntExtra("BUDGET_ID", -1).takeIf { it != -1 }
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        
        val title = if (budgetId != null) "Redigera budget" else "Lägg till budget"
        binding.toolbar.title = title
        
        binding.toolbar.setNavigationOnClickListener {
            handleBackPress()
        }
    }
    
    private fun setupDropdowns() {
        // Kategori dropdown
        val categoryAdapter = ArrayAdapter(
            this,
            R.layout.dropdown_item_light,
            expenseCategories
        )
        binding.dropdownCategory.setAdapter(categoryAdapter)
        
        // Period dropdown
        val periodAdapter = ArrayAdapter(
            this,
            R.layout.dropdown_item_light,
            periodOptions.keys.toList()
        )
        binding.dropdownPeriod.setAdapter(periodAdapter)
        
        // Sätt ljus bakgrund för dropdown-popups
        binding.dropdownCategory.setDropDownBackgroundResource(android.R.color.background_light)
        binding.dropdownPeriod.setDropDownBackgroundResource(android.R.color.background_light)
        
        // Sätt standardvärden
        binding.dropdownPeriod.setText("Månad", false)
        binding.editAlertThreshold.setText("80")
        updateStartDate()
    }
    
    private fun setupClickListeners() {
        binding.editStartDate.setOnClickListener {
            showDatePicker()
        }
        
        binding.buttonCancel.setOnClickListener {
            handleBackPress()
        }
        
        binding.buttonSave.setOnClickListener {
            saveBudget()
        }
        
        // Uppdatera information när användaren ändrar värden
        binding.dropdownCategory.setOnItemClickListener { _, _, _, _ ->
            updateBudgetInfo()
        }
        
        binding.dropdownPeriod.setOnItemClickListener { _, _, _, _ ->
            updatePeriodDates()
            updateBudgetInfo()
        }
    }
    
    private fun setupBackPressedHandler() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                handleBackPress()
            }
        })
    }
    
    private fun handleBackPress() {
        if (hasUnsavedChanges()) {
            MaterialAlertDialogBuilder(this)
                .setTitle("Osparade ändringar")
                .setMessage("Du har osparade ändringar. Vill du verkligen lämna?")
                .setPositiveButton("Lämna") { _, _ -> finish() }
                .setNegativeButton("Stanna kvar", null)
                .show()
        } else {
            finish()
        }
    }
    
    private fun loadBudgetData() {
        budgetId?.let { id ->
            lifecycleScope.launch {
                try {
                    existingBudget = budgetRepository.getBudgetById(id)
                    existingBudget?.let { budget ->
                        populateFields(budget)
                    }
                } catch (e: Exception) {
                    ErrorHandler.logError("AddEditBudgetActivity", "Error loading budget data", e)
                    ErrorHandler.handleDatabaseError(this@AddEditBudgetActivity, e)
                }
            }
        }
    }
    
    private fun populateFields(budget: Budget) {
        binding.editBudgetName.setText(budget.name)
        binding.editBudgetAmount.setText(budget.budgetAmount.toString())
        binding.dropdownCategory.setText(budget.categoryName, false)
        
        val periodText = periodOptions.entries.find { it.value == budget.period }?.key ?: "Månad"
        binding.dropdownPeriod.setText(periodText, false)
        
        selectedStartDate.timeInMillis = budget.startDate
        updateStartDate()
        
        binding.editAlertThreshold.setText(budget.alertThreshold.toInt().toString())
        
        updateBudgetInfo()
    }
    
    private fun showDatePicker() {
        val calendar = selectedStartDate
        
        DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                updateStartDate()
                updatePeriodDates()
                updateBudgetInfo()
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }
    
    private fun updateStartDate() {
        binding.editStartDate.setText(dateFormat.format(selectedStartDate.time))
    }
    
    private fun updatePeriodDates() {
        val selectedPeriodText = binding.dropdownPeriod.text.toString()
        val selectedPeriod = periodOptions[selectedPeriodText] ?: BudgetPeriod.MONTHLY
        
        val (startDate, _) = budgetRepository.createBudgetPeriod(
            selectedPeriod, 
            selectedStartDate.time
        )
        
        selectedStartDate.timeInMillis = startDate
        updateStartDate()
    }
    
    private fun updateBudgetInfo() {
        val categoryName = binding.dropdownCategory.text.toString()
        val periodText = binding.dropdownPeriod.text.toString()
        val startDateText = binding.editStartDate.text.toString()
        
        if (categoryName.isNotEmpty() && periodText.isNotEmpty() && startDateText.isNotEmpty()) {
            val selectedPeriod = periodOptions[periodText] ?: BudgetPeriod.MONTHLY
            val (_, endDate) = budgetRepository.createBudgetPeriod(
                selectedPeriod, 
                selectedStartDate.time
            )
            
            val endDateText = dateFormat.format(Date(endDate))
            
            val info = "Denna budget kommer att spåra dina utgifter för kategorin " +
                    "$categoryName under perioden $startDateText - $endDateText."
            
            binding.textBudgetInfo.text = info
        }
    }
    
    private fun saveBudget() {
        try {
            if (!validateInput()) return
            
            lifecycleScope.launch {
                try {
                    val budget = createBudgetFromInput()
                    
                    if (existingBudget != null) {
                        budgetRepository.updateBudget(budget)
                        showSuccess("Budget uppdaterad!")
                    } else {
                        budgetRepository.insertBudget(budget)
                        showSuccess("Budget skapad!")
                    }
                    
                    finish()
                } catch (e: Exception) {
                    ErrorHandler.logError("AddEditBudgetActivity", "Error saving budget", e)
                    ErrorHandler.handleDatabaseError(this@AddEditBudgetActivity, e)
                }
            }
        } catch (e: Exception) {
            ErrorHandler.logError("AddEditBudgetActivity", "Error during validation", e)
            ErrorHandler.showError(this, "Valideringsfel", "Ett fel uppstod vid validering av formuläret")
        }
    }
    
    private fun validateInput(): Boolean {
        var isValid = true
        
        if (binding.editBudgetName.text.isNullOrBlank()) {
            binding.textInputBudgetName.error = "Budget namn krävs"
            isValid = false
        } else {
            binding.textInputBudgetName.error = null
        }
        
        val amountText = binding.editBudgetAmount.text.toString()
        if (amountText.isBlank() || amountText.toDoubleOrNull() == null || amountText.toDouble() <= 0) {
            binding.textInputBudgetAmount.error = "Ange ett giltigt belopp"
            isValid = false
        } else {
            binding.textInputBudgetAmount.error = null
        }
        
        if (binding.dropdownCategory.text.isNullOrBlank()) {
            binding.textInputCategory.error = "Välj en kategori"
            isValid = false
        } else {
            binding.textInputCategory.error = null
        }
        
        if (binding.dropdownPeriod.text.isNullOrBlank()) {
            binding.textInputPeriod.error = "Välj en period"
            isValid = false
        } else {
            binding.textInputPeriod.error = null
        }
        
        val thresholdText = binding.editAlertThreshold.text.toString()
        val threshold = thresholdText.toIntOrNull()
        if (threshold == null || threshold < 1 || threshold > 100) {
            binding.textInputAlertThreshold.error = "Ange ett värde mellan 1-100"
            isValid = false
        } else {
            binding.textInputAlertThreshold.error = null
        }
        
        return isValid
    }
    
    private fun createBudgetFromInput(): Budget {
        val name = binding.editBudgetName.text.toString().trim()
        val amount = binding.editBudgetAmount.text.toString().toDouble()
        val categoryName = binding.dropdownCategory.text.toString()
        val periodText = binding.dropdownPeriod.text.toString()
        val period = periodOptions[periodText] ?: BudgetPeriod.MONTHLY
        val alertThreshold = binding.editAlertThreshold.text.toString().toDouble()
        
        val (startDate, endDate) = budgetRepository.createBudgetPeriod(
            period, 
            selectedStartDate.time
        )
        
        return if (existingBudget != null) {
            existingBudget!!.copy(
                name = name,
                categoryName = categoryName,
                budgetAmount = amount,
                period = period,
                startDate = startDate,
                endDate = endDate,
                alertThreshold = alertThreshold
            )
        } else {
            Budget(
                name = name,
                categoryName = categoryName,
                budgetAmount = amount,
                period = period,
                startDate = startDate,
                endDate = endDate,
                alertThreshold = alertThreshold
            )
        }
    }
    
    private fun showSuccess(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
    
    
    private fun hasUnsavedChanges(): Boolean {
        val currentName = binding.editBudgetName.text.toString().trim()
        val currentAmount = binding.editBudgetAmount.text.toString()
        val currentCategory = binding.dropdownCategory.text.toString()
        
        return currentName.isNotEmpty() || currentAmount.isNotEmpty() || currentCategory.isNotEmpty()
    }
}