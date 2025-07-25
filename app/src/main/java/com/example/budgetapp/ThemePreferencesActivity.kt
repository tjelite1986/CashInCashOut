package com.example.budgetapp

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgetapp.adapters.ColorChoiceAdapter
import com.example.budgetapp.data.AccentColor
import com.example.budgetapp.data.InterfaceStyle
import com.example.budgetapp.data.ThemeMode
import com.example.budgetapp.data.ThemeSettings
import com.example.budgetapp.databinding.ActivityThemePreferencesBinding
import com.example.budgetapp.utils.ThemeManager

class ThemePreferencesActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityThemePreferencesBinding
    private lateinit var themeManager: ThemeManager
    private lateinit var colorAdapter: ColorChoiceAdapter
    
    private var currentSettings = ThemeSettings()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        themeManager = ThemeManager.getInstance(this)
        
        // Apply current theme before setting content view
        themeManager.applyTheme()
        
        super.onCreate(savedInstanceState)
        binding = ActivityThemePreferencesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Load current settings
        currentSettings = themeManager.getCurrentThemeSettings()
        
        setupToolbar()
        setupThemeModeRadioButtons()
        setupAccentColorSection()
        setupInterfaceStyleRadioButtons()
        setupPreview()
        setupClickListeners()
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Theme Settings"
        }
        
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
    
    private fun setupThemeModeRadioButtons() {
        when (currentSettings.themeMode) {
            ThemeMode.LIGHT -> binding.radioLight.isChecked = true
            ThemeMode.DARK -> binding.radioDark.isChecked = true
            ThemeMode.SYSTEM -> binding.radioSystem.isChecked = true
        }
        
        binding.radioGroupThemeMode.setOnCheckedChangeListener { _, checkedId ->
            currentSettings = currentSettings.copy(
                themeMode = when (checkedId) {
                    R.id.radio_light -> ThemeMode.LIGHT
                    R.id.radio_dark -> ThemeMode.DARK
                    R.id.radio_system -> ThemeMode.SYSTEM
                    else -> ThemeMode.SYSTEM
                }
            )
            updatePreview()
        }
    }
    
    private fun setupAccentColorSection() {
        // System color option
        binding.radioSystemColor.isChecked = currentSettings.accentColor == AccentColor.SYSTEM
        binding.layoutSystemColor.setOnClickListener {
            selectAccentColor(AccentColor.SYSTEM)
        }
        
        // Setup color grid
        val colors = AccentColor.values().filter { it != AccentColor.SYSTEM }
        colorAdapter = ColorChoiceAdapter(colors, currentSettings.accentColor) { color ->
            selectAccentColor(color)
        }
        
        binding.recyclerViewColors.apply {
            layoutManager = LinearLayoutManager(this@ThemePreferencesActivity)
            adapter = colorAdapter
        }
    }
    
    private fun selectAccentColor(color: AccentColor) {
        currentSettings = currentSettings.copy(accentColor = color)
        
        // Update UI
        binding.radioSystemColor.isChecked = color == AccentColor.SYSTEM
        if (color != AccentColor.SYSTEM) {
            colorAdapter.updateSelectedColor(color)
        }
        
        updatePreview()
    }
    
    private fun setupInterfaceStyleRadioButtons() {
        when (currentSettings.interfaceStyle) {
            InterfaceStyle.MATERIAL -> binding.radioMaterial.isChecked = true
            InterfaceStyle.COLORFUL -> binding.radioColorful.isChecked = true
        }
        
        binding.radioGroupInterfaceStyle.setOnCheckedChangeListener { _, checkedId ->
            currentSettings = currentSettings.copy(
                interfaceStyle = when (checkedId) {
                    R.id.radio_material -> InterfaceStyle.MATERIAL
                    R.id.radio_colorful -> InterfaceStyle.COLORFUL
                    else -> InterfaceStyle.MATERIAL
                }
            )
            updatePreview()
        }
    }
    
    private fun setupPreview() {
        updatePreview()
    }
    
    private fun updatePreview() {
        // Update FAB color based on selected accent color
        val accentColor = if (currentSettings.accentColor == AccentColor.SYSTEM) {
            Color.parseColor("#4CAF50") // Default green
        } else {
            Color.parseColor(currentSettings.accentColor.colorValue)
        }
        
        binding.fabApply.backgroundTintList = android.content.res.ColorStateList.valueOf(accentColor)
        
        // Update preview section styling based on interface style
        if (currentSettings.interfaceStyle == InterfaceStyle.COLORFUL) {
            // Apply colorful styling to preview
            val gradient = GradientDrawable().apply {
                orientation = GradientDrawable.Orientation.TL_BR
                colors = intArrayOf(
                    Color.parseColor("#FFE082"),
                    Color.parseColor("#FFCC02")
                )
                cornerRadius = 12f
            }
            binding.layoutPreview.background = gradient
        } else {
            // Apply material styling to preview
            binding.layoutPreview.setBackgroundColor(
                getColor(R.color.surface)
            )
        }
    }
    
    private fun setupClickListeners() {
        binding.fabApply.setOnClickListener {
            applyTheme()
        }
    }
    
    private fun applyTheme() {
        // Save the new theme settings
        themeManager.saveThemeSettings(currentSettings)
        
        // Apply the theme
        themeManager.applyTheme(currentSettings)
        
        // Notify listeners (this will update all fragments)
        themeManager.notifyThemeChanged(currentSettings)
        
        // Just finish this activity, let MainActivity handle the theme change
        finish()
    }
}