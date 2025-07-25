package com.example.budgetapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.Loan
import com.example.budgetapp.database.entities.LoanType
import com.example.budgetapp.databinding.ActivityAddLoanBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddLoanActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityAddLoanBinding
    private lateinit var database: BudgetDatabase
    private var selectedDueDate: Long? = null
    private var selectedLoanType: LoanType = LoanType.BORROWED
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    
    private var editingLoanId: Long? = null
    private var isEditing: Boolean = false
    private var originalLoan: Loan? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            binding = ActivityAddLoanBinding.inflate(layoutInflater)
            setContentView(binding.root)
            
            database = BudgetDatabase.getDatabase(this)
            
            // Check if we're editing an existing loan
            editingLoanId = intent.getLongExtra("loan_id", -1).takeIf { it != -1L }
            isEditing = editingLoanId != null
            
            // Get loan type from intent if provided
            intent.getStringExtra("loan_type")?.let { type ->
                selectedLoanType = LoanType.valueOf(type)
            }
            
            setupUI()
            setupClickListeners()
            
            if (isEditing) {
                loadLoanForEditing()
            } else {
                setInitialLoanType()
            }
        } catch (e: Exception) {
            Toast.makeText(this, "Fel vid uppstart: ${e.message}", Toast.LENGTH_LONG).show()
            e.printStackTrace()
            finish()
        }
    }
    
    private fun setupUI() {
        // Setup toolbar
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
        
        // Update title based on editing mode
        if (isEditing) {
            binding.toolbar.title = "Redigera lån"
        } else {
            binding.toolbar.title = "Lägg till lån"
        }
    }
    
    private fun setInitialLoanType() {
        when (selectedLoanType) {
            LoanType.BORROWED -> {
                binding.chipBorrowed.isChecked = true
                binding.toolbar.title = "Lägg till lånat pengar"
            }
            LoanType.LENT -> {
                binding.chipLent.isChecked = true
                binding.toolbar.title = "Lägg till utlånade pengar"
            }
        }
    }
    
    private fun setupClickListeners() {
        // Loan type chips
        binding.chipGroupLoanType.setOnCheckedStateChangeListener { _, checkedIds ->
            when (checkedIds.firstOrNull()) {
                binding.chipBorrowed.id -> {
                    selectedLoanType = LoanType.BORROWED
                }
                binding.chipLent.id -> {
                    selectedLoanType = LoanType.LENT
                }
            }
        }
        
        // Due date picker
        binding.btnSelectDueDate.setOnClickListener {
            showDatePicker()
        }
        
        // Cancel button
        binding.btnCancel.setOnClickListener {
            finish()
        }
        
        // Save button
        binding.btnSave.setOnClickListener {
            saveLoan()
        }
    }
    
    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        if (selectedDueDate != null) {
            calendar.timeInMillis = selectedDueDate!!
        }
        
        DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val selectedCalendar = Calendar.getInstance()
                selectedCalendar.set(year, month, dayOfMonth)
                selectedDueDate = selectedCalendar.timeInMillis
                binding.btnSelectDueDate.text = dateFormat.format(Date(selectedDueDate!!))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }
    
    private fun saveLoan() {
        try {
            val title = binding.etTitle.text.toString().trim()
            val amountText = binding.etAmount.text.toString().trim()
            val personName = binding.etPersonName.text.toString().trim()
            val interestRateText = binding.etInterestRate.text.toString().trim()
            val description = binding.etDescription.text.toString().trim()
            
            // Validation
            if (title.isEmpty()) {
                binding.etTitle.error = "Titel krävs"
                return
            }
            
            if (amountText.isEmpty()) {
                binding.etAmount.error = "Belopp krävs"
                return
            }
            
            if (personName.isEmpty()) {
                binding.etPersonName.error = "Person krävs"
                return
            }
            
            val amount = try {
                amountText.toDouble()
            } catch (e: NumberFormatException) {
                binding.etAmount.error = "Ogiltigt belopp"
                return
            }
            
            if (amount <= 0) {
                binding.etAmount.error = "Belopp måste vara större än 0"
                return
            }
            
            val interestRate = if (interestRateText.isNotEmpty()) {
                try {
                    interestRateText.toDouble()
                } catch (e: NumberFormatException) {
                    binding.etInterestRate.error = "Ogiltig ränta"
                    return
                }
            } else {
                0.0
            }
            
            // Create loan object
            val loan = if (isEditing) {
                originalLoan!!.copy(
                    title = title,
                    amount = amount,
                    personName = personName,
                    type = selectedLoanType,
                    interestRate = interestRate,
                    dueDate = selectedDueDate,
                    description = description.takeIf { it.isNotEmpty() },
                    updatedAt = System.currentTimeMillis()
                )
            } else {
                Loan(
                    title = title,
                    amount = amount,
                    personName = personName,
                    type = selectedLoanType,
                    interestRate = interestRate,
                    dueDate = selectedDueDate,
                    description = description.takeIf { it.isNotEmpty() }
                )
            }
            
            // Save to database
            lifecycleScope.launch {
                try {
                    if (isEditing) {
                        database.loanDao().updateLoan(loan)
                        Toast.makeText(this@AddLoanActivity, "Lån uppdaterat", Toast.LENGTH_SHORT).show()
                    } else {
                        database.loanDao().insertLoan(loan)
                        Toast.makeText(this@AddLoanActivity, "Lån sparat", Toast.LENGTH_SHORT).show()
                    }
                    finish()
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(this@AddLoanActivity, "Fel vid sparande: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
            
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Fel vid sparande: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }
    
    private fun loadLoanForEditing() {
        lifecycleScope.launch {
            try {
                val loan = database.loanDao().getLoanById(editingLoanId!!)
                if (loan != null) {
                    originalLoan = loan
                    binding.etTitle.setText(loan.title)
                    binding.etAmount.setText(loan.amount.toString())
                    binding.etPersonName.setText(loan.personName)
                    binding.etInterestRate.setText(if (loan.interestRate > 0) loan.interestRate.toString() else "")
                    binding.etDescription.setText(loan.description ?: "")
                    
                    selectedLoanType = loan.type
                    when (loan.type) {
                        LoanType.BORROWED -> binding.chipBorrowed.isChecked = true
                        LoanType.LENT -> binding.chipLent.isChecked = true
                    }
                    
                    selectedDueDate = loan.dueDate
                    if (loan.dueDate != null) {
                        binding.btnSelectDueDate.text = dateFormat.format(Date(loan.dueDate))
                    }
                } else {
                    Toast.makeText(this@AddLoanActivity, "Kunde inte ladda lån", Toast.LENGTH_SHORT).show()
                    finish()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@AddLoanActivity, "Fel vid laddning: ${e.message}", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }
}