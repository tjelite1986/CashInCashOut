package com.example.budgetapp.fragments

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.budgetapp.data.InterfaceStyle
import com.example.budgetapp.data.ThemeSettings
import com.example.budgetapp.utils.ThemeManager
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.floatingactionbutton.FloatingActionButton

abstract class BaseFragment : Fragment(), ThemeManager.ThemeChangeListener {

    protected lateinit var themeManager: ThemeManager
    private var isThemeInitialized = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        themeManager = ThemeManager.getInstance(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Register theme change listener
        themeManager.addThemeChangeListener(this)
        isThemeInitialized = true
        
        // Apply initial theme
        onThemeChanged(themeManager.getCurrentThemeSettings())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (isThemeInitialized) {
            themeManager.removeThemeChangeListener(this)
            isThemeInitialized = false
        }
    }

    override fun onThemeChanged(settings: ThemeSettings) {
        if (!isThemeInitialized || !isAdded) return
        
        view?.let { rootView ->
            applyThemeToViews(rootView, settings)
            // Also call the custom theme application
            applyCustomTheme(settings)
        }
    }

    private fun applyThemeToViews(rootView: View, settings: ThemeSettings) {
        val accentColor = themeManager.getAccentColorInt()
        val isColorful = settings.interfaceStyle == InterfaceStyle.COLORFUL
        
        // Apply theme to all relevant views recursively
        applyThemeRecursively(rootView, accentColor, isColorful)
    }

    private fun applyThemeRecursively(view: View, accentColor: Int, isColorful: Boolean) {
        when (view) {
            is FloatingActionButton -> {
                view.backgroundTintList = android.content.res.ColorStateList.valueOf(accentColor)
            }
            is MaterialButton -> {
                if (view.tag == "accent_button" || view.text.toString().contains("Lägg till") || 
                    view.text.toString().contains("Add") || view.text.toString().contains("Spara")) {
                    view.backgroundTintList = android.content.res.ColorStateList.valueOf(accentColor)
                }
            }
            is MaterialCardView -> {
                if (isColorful) {
                    applyColorfulCardStyle(view, accentColor)
                } else {
                    // Reset to default material card style
                    view.setCardBackgroundColor(getColor(android.R.color.background_light))
                }
            }
            is android.view.ViewGroup -> {
                // Recursively apply to child views
                for (i in 0 until view.childCount) {
                    applyThemeRecursively(view.getChildAt(i), accentColor, isColorful)
                }
            }
        }
    }

    private fun applyColorfulCardStyle(cardView: MaterialCardView, accentColor: Int) {
        // Create subtle gradient background for colorful mode
        val gradient = GradientDrawable().apply {
            orientation = GradientDrawable.Orientation.TL_BR
            colors = intArrayOf(
                adjustColorBrightness(accentColor, 0.95f),
                adjustColorBrightness(accentColor, 0.98f)
            )
            cornerRadius = 24f
        }
        cardView.background = gradient
    }

    private fun adjustColorBrightness(color: Int, factor: Float): Int {
        val red = (Color.red(color) * factor).toInt().coerceIn(0, 255)
        val green = (Color.green(color) * factor).toInt().coerceIn(0, 255)
        val blue = (Color.blue(color) * factor).toInt().coerceIn(0, 255)
        return Color.rgb(red, green, blue)
    }

    private fun getColor(colorRes: Int): Int {
        return requireContext().getColor(colorRes)
    }

    // Abstract methods for subclasses to implement
    abstract fun applyCustomTheme(settings: ThemeSettings)
}