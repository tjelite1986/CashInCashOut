package com.example.budgetapp.utils

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatDelegate
import com.example.budgetapp.data.AccentColor
import com.example.budgetapp.data.InterfaceStyle
import com.example.budgetapp.data.ThemeMode
import com.example.budgetapp.data.ThemeSettings

class ThemeManager(private val context: Context) {
    
    companion object {
        private const val THEME_PREFS = "theme_preferences"
        private const val KEY_ACCENT_COLOR = "accent_color"
        private const val KEY_THEME_MODE = "theme_mode"
        private const val KEY_INTERFACE_STYLE = "interface_style"
        
        @Volatile
        private var INSTANCE: ThemeManager? = null
        
        fun getInstance(context: Context): ThemeManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: ThemeManager(context.applicationContext).also { INSTANCE = it }
            }
        }
    }
    
    private val sharedPreferences: SharedPreferences = 
        context.getSharedPreferences(THEME_PREFS, Context.MODE_PRIVATE)
    
    fun getCurrentThemeSettings(): ThemeSettings {
        val accentColorName = sharedPreferences.getString(KEY_ACCENT_COLOR, AccentColor.SYSTEM.name)
        val themeModeString = sharedPreferences.getString(KEY_THEME_MODE, ThemeMode.SYSTEM.name)
        val interfaceStyleString = sharedPreferences.getString(KEY_INTERFACE_STYLE, InterfaceStyle.MATERIAL.name)
        
        val accentColor = try {
            AccentColor.valueOf(accentColorName ?: AccentColor.SYSTEM.name)
        } catch (e: IllegalArgumentException) {
            AccentColor.SYSTEM
        }
        
        val themeMode = try {
            ThemeMode.valueOf(themeModeString ?: ThemeMode.SYSTEM.name)
        } catch (e: IllegalArgumentException) {
            ThemeMode.SYSTEM
        }
        
        val interfaceStyle = try {
            InterfaceStyle.valueOf(interfaceStyleString ?: InterfaceStyle.MATERIAL.name)
        } catch (e: IllegalArgumentException) {
            InterfaceStyle.MATERIAL
        }
        
        return ThemeSettings(accentColor, themeMode, interfaceStyle)
    }
    
    fun saveThemeSettings(settings: ThemeSettings) {
        sharedPreferences.edit()
            .putString(KEY_ACCENT_COLOR, settings.accentColor.name)
            .putString(KEY_THEME_MODE, settings.themeMode.name)
            .putString(KEY_INTERFACE_STYLE, settings.interfaceStyle.name)
            .apply()
    }
    
    fun applyTheme(settings: ThemeSettings? = null) {
        val currentSettings = settings ?: getCurrentThemeSettings()
        
        // Apply theme mode (Light/Dark/System)
        when (currentSettings.themeMode) {
            ThemeMode.LIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            ThemeMode.DARK -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            ThemeMode.SYSTEM -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }
    
    fun isDarkMode(): Boolean {
        val currentSettings = getCurrentThemeSettings()
        return when (currentSettings.themeMode) {
            ThemeMode.DARK -> true
            ThemeMode.LIGHT -> false
            ThemeMode.SYSTEM -> {
                val nightModeFlags = context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
                nightModeFlags == Configuration.UI_MODE_NIGHT_YES
            }
        }
    }
    
    fun getAccentColorInt(): Int {
        val settings = getCurrentThemeSettings()
        return when (settings.accentColor) {
            AccentColor.SYSTEM -> {
                // Use app's default green for system
                android.graphics.Color.parseColor("#4CAF50")
            }
            else -> android.graphics.Color.parseColor(settings.accentColor.colorValue)
        }
    }
    
    fun isColorfulInterface(): Boolean {
        return getCurrentThemeSettings().interfaceStyle == InterfaceStyle.COLORFUL
    }
    
    // Callback interface for theme changes
    interface ThemeChangeListener {
        fun onThemeChanged(settings: ThemeSettings)
    }
    
    private val listeners = mutableSetOf<ThemeChangeListener>()
    
    fun addThemeChangeListener(listener: ThemeChangeListener) {
        listeners.add(listener)
    }
    
    fun removeThemeChangeListener(listener: ThemeChangeListener) {
        listeners.remove(listener)
    }
    
    fun notifyThemeChanged(settings: ThemeSettings) {
        // Post to main thread to avoid crashes
        Handler(Looper.getMainLooper()).post {
            // Create a copy to avoid concurrent modification
            val listenersCopy = listeners.toList()
            listenersCopy.forEach { 
                try {
                    it.onThemeChanged(settings)
                } catch (e: Exception) {
                    // Log error but don't crash the app
                    e.printStackTrace()
                }
            }
        }
    }
}