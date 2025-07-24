package com.example.budgetapp

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.budgetapp.adapters.CategoryIconAdapter
import com.example.budgetapp.adapters.ColorPickerAdapter
import com.example.budgetapp.database.entities.Category
import com.example.budgetapp.database.entities.CategoryType
import com.example.budgetapp.databinding.ActivityAddEditCategoryBinding
import com.example.budgetapp.utils.CategoryConstants
import com.example.budgetapp.viewmodels.CategoryViewModel
import kotlinx.coroutines.launch

class AddEditCategoryActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityAddEditCategoryBinding
    private val categoryViewModel: CategoryViewModel by viewModels()
    
    private var categoryId: Long = -1L
    private var isEditMode = false
    private var currentCategory: Category? = null
    
    private var selectedColor = CategoryConstants.CATEGORY_COLORS[0]
    private var selectedIcon = "ic_category_default"
    private var selectedType = CategoryType.EXPENSE
    private var isSubcategory = false
    private var parentCategoryId: Long? = null
    
    private lateinit var colorAdapter: ColorPickerAdapter
    private lateinit var iconAdapter: CategoryIconAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEditCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        categoryId = intent.getLongExtra("category_id", -1L)
        isEditMode = categoryId != -1L
        
        setupToolbar()
        setupTypeToggle()
        setupColorPicker()
        setupIconPicker()
        setupButtons()
        
        if (isEditMode) {
            loadCategoryData()
        } else {
            updatePreview()
        }
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = if (isEditMode) "Edit Category" else "Add Category"
        }
    }
    
    private fun setupTypeToggle() {
        binding.toggleGroupType.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                selectedType = when (checkedId) {
                    R.id.buttonExpense -> CategoryType.EXPENSE
                    R.id.buttonIncome -> CategoryType.INCOME
                    else -> CategoryType.EXPENSE
                }
                updateTypeButtonStyles()
                updatePreview()
            }
        }
        
        // Set default selection
        binding.buttonExpense.isChecked = true
        updateTypeButtonStyles()
    }
    
    private fun updateTypeButtonStyles() {
        // Update expense button
        if (binding.buttonExpense.isChecked) {
            binding.buttonExpense.setBackgroundColor(getColor(R.color.expense_red))
            binding.buttonExpense.setTextColor(getColor(android.R.color.white))
        } else {
            binding.buttonExpense.setBackgroundColor(getColor(R.color.card_background))
            binding.buttonExpense.setTextColor(getColor(R.color.text_primary))
        }
        
        // Update income button
        if (binding.buttonIncome.isChecked) {
            binding.buttonIncome.setBackgroundColor(getColor(R.color.income_green))
            binding.buttonIncome.setTextColor(getColor(android.R.color.white))
        } else {
            binding.buttonIncome.setBackgroundColor(getColor(R.color.card_background))
            binding.buttonIncome.setTextColor(getColor(R.color.text_primary))
        }
    }
    
    private fun setupColorPicker() {
        colorAdapter = ColorPickerAdapter(
            colors = CategoryConstants.CATEGORY_COLORS,
            onColorSelected = { color ->
                selectedColor = color
                updatePreview()
            }
        )
        
        binding.recyclerViewColors.layoutManager = GridLayoutManager(this, 6)
        binding.recyclerViewColors.adapter = colorAdapter
        
        // Select first color by default
        colorAdapter.selectColor(selectedColor)
    }
    
    private fun setupIconPicker() {
        iconAdapter = CategoryIconAdapter(
            icons = CategoryConstants.CATEGORY_ICONS.keys.toList(),
            onIconSelected = { iconName ->
                selectedIcon = iconName
                updatePreview()
            }
        )
        
        binding.recyclerViewSubcategories.layoutManager = GridLayoutManager(this, 4)
        binding.recyclerViewSubcategories.adapter = iconAdapter
    }
    
    private fun setupButtons() {
        binding.buttonSave.setOnClickListener {
            saveCategory()
        }
        
        binding.buttonMainCategory.setOnClickListener {
            isSubcategory = false
            parentCategoryId = null
            updateSubcategoryUI()
        }
        
        binding.buttonSubcategory.setOnClickListener {
            isSubcategory = true
            updateSubcategoryUI()
        }
    }
    
    private fun updateSubcategoryUI() {
        if (isSubcategory) {
            binding.textSubcategoryLabel.text = "Select Parent Category"
            // Show parent category selection
        } else {
            binding.textSubcategoryLabel.text = "Subcategory"
        }
    }
    
    private fun updatePreview() {
        // Update the preview icon with selected color and icon
        val iconBackground = GradientDrawable().apply {
            shape = GradientDrawable.OVAL
            try {
                setColor(Color.parseColor(selectedColor))
            } catch (e: IllegalArgumentException) {
                setColor(Color.parseColor("#FF6B6B"))
            }
        }
        
        binding.previewIcon.background = iconBackground
        
        // Set the icon resource
        val iconRes = getIconResource(selectedIcon)
        binding.previewIcon.setImageResource(iconRes)
    }
    
    private fun getIconResource(iconName: String): Int {
        return when (iconName) {
            "ic_dining" -> R.drawable.ic_dining
            "ic_groceries" -> R.drawable.ic_groceries
            "ic_shopping" -> R.drawable.ic_shopping
            "ic_transit" -> R.drawable.ic_transit
            "ic_entertainment" -> R.drawable.ic_entertainment
            "ic_bills" -> R.drawable.ic_bills
            "ic_home" -> R.drawable.ic_home
            "ic_health" -> R.drawable.ic_health
            "ic_tech" -> R.drawable.ic_tech
            "ic_sport" -> R.drawable.ic_sport
            "ic_car" -> R.drawable.ic_car
            "ic_gas" -> R.drawable.ic_gas
            "ic_clothes" -> R.drawable.ic_clothes
            "ic_education" -> R.drawable.ic_education
            "ic_coffee" -> R.drawable.ic_coffee
            "ic_gift" -> R.drawable.ic_gift
            "ic_pets" -> R.drawable.ic_pets
            "ic_travel" -> R.drawable.ic_travel
            "ic_beauty" -> R.drawable.ic_beauty
            "ic_pharmacy" -> R.drawable.ic_pharmacy
            "ic_store" -> R.drawable.ic_store
            "ic_calendar" -> R.drawable.ic_calendar
            "ic_money" -> R.drawable.ic_money
            "ic_salary" -> R.drawable.ic_salary
            "ic_bonus" -> R.drawable.ic_bonus
            "ic_freelance" -> R.drawable.ic_freelance
            "ic_investment" -> R.drawable.ic_investment
            "ic_sale" -> R.drawable.ic_sale
            "ic_other" -> R.drawable.ic_other
            "ic_tobacco" -> R.drawable.ic_tobacco
            "ic_drinks" -> R.drawable.ic_drinks
            else -> R.drawable.ic_category_default
        }
    }
    
    private fun loadCategoryData() {
        lifecycleScope.launch {
            currentCategory = categoryViewModel.getCategoryById(categoryId)
            currentCategory?.let { category ->
                binding.editTextName.setText(category.name)
                selectedColor = category.color
                selectedIcon = category.iconResName
                selectedType = category.type
                isSubcategory = category.isSubcategory
                parentCategoryId = category.parentCategoryId
                
                // Update UI
                when (selectedType) {
                    CategoryType.EXPENSE -> binding.buttonExpense.isChecked = true
                    CategoryType.INCOME -> binding.buttonIncome.isChecked = true
                }
                
                colorAdapter.selectColor(selectedColor)
                iconAdapter.selectIcon(selectedIcon)
                updatePreview()
                updateSubcategoryUI()
            }
        }
    }
    
    private fun saveCategory() {
        val name = binding.editTextName.text.toString().trim()
        
        if (name.isEmpty()) {
            Toast.makeText(this, "Please enter a category name", Toast.LENGTH_SHORT).show()
            return
        }
        
        lifecycleScope.launch {
            val category = if (isEditMode) {
                currentCategory?.copy(
                    name = name,
                    color = selectedColor,
                    iconResName = selectedIcon,
                    type = selectedType,
                    isSubcategory = isSubcategory,
                    parentCategoryId = parentCategoryId
                ) ?: return@launch
            } else {
                Category(
                    name = name,
                    color = selectedColor,
                    iconResName = selectedIcon,
                    type = selectedType,
                    isSubcategory = isSubcategory,
                    parentCategoryId = parentCategoryId
                )
            }
            
            if (isEditMode) {
                categoryViewModel.updateCategory(category)
            } else {
                categoryViewModel.insertCategory(category)
            }
            
            Toast.makeText(this@AddEditCategoryActivity, 
                if (isEditMode) "Category updated" else "Category created", 
                Toast.LENGTH_SHORT).show()
            
            finish()
        }
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}