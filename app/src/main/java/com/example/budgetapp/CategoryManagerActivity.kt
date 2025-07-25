package com.example.budgetapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgetapp.adapters.CategoryManagerAdapter
import com.example.budgetapp.database.entities.CategoryType
import com.example.budgetapp.databinding.ActivityCategoryManagerBinding
import com.example.budgetapp.viewmodels.CategoryViewModel
import kotlinx.coroutines.launch

class CategoryManagerActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityCategoryManagerBinding
    private val categoryViewModel: CategoryViewModel by viewModels()
    private lateinit var adapter: CategoryManagerAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupToolbar()
        setupRecyclerView()
        setupSearchBar()
        setupFab()
        observeCategories()
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "Edit Categories"
        }
    }
    
    private fun setupRecyclerView() {
        adapter = CategoryManagerAdapter(
            onCategoryClick = { category ->
                val intent = Intent(this, AddEditCategoryActivity::class.java)
                intent.putExtra("category_id", category.id)
                startActivity(intent)
            },
            onDeleteClick = { category ->
                lifecycleScope.launch {
                    categoryViewModel.deleteCategory(category)
                }
            }
        )
        
        binding.recyclerViewCategories.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewCategories.adapter = adapter
    }
    
    private fun setupSearchBar() {
        binding.searchView.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                adapter.filter(s?.toString() ?: "")
            }
        })
    }
    
    private fun setupFab() {
        binding.fabAddCategory.setOnClickListener {
            val intent = Intent(this, AddEditCategoryActivity::class.java)
            startActivity(intent)
        }
    }
    
    private fun observeCategories() {
        categoryViewModel.allActiveCategories.observe(this) { categories ->
            adapter.submitList(categories)
        }
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}