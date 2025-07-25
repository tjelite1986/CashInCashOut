package com.example.budgetapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.budgetapp.databinding.ActivityStoreAndProductManagerBinding
import com.google.android.material.appbar.MaterialToolbar

class StoreAndProductManagerActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityStoreAndProductManagerBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoreAndProductManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupUI()
        setupClickListeners()
    }
    
    private fun setupUI() {
        try {
            val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "Butik & Produkthantering"
            
            toolbar.setNavigationOnClickListener {
                finish()
            }
        } catch (e: Exception) {
            supportActionBar?.title = "Butik & Produkthantering"
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }
    
    private fun setupClickListeners() {
        // Butikshantering
        binding.cardStoreManagement.setOnClickListener {
            val intent = Intent(this, StoreManagerActivity::class.java)
            startActivity(intent)
        }
        
        // Butikkedjor
        binding.cardStoreChains.setOnClickListener {
            val intent = Intent(this, StoreChainManagerActivity::class.java)
            startActivity(intent)
        }
        
        // Produkthantering
        binding.cardProductManagement.setOnClickListener {
            val intent = Intent(this, ProductListActivity::class.java)
            startActivity(intent)
        }
        
        // Produktkategorier
        binding.cardProductCategories.setOnClickListener {
            val intent = Intent(this, ProductCategoryManagerActivity::class.java)
            startActivity(intent)
        }
    }
    
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}