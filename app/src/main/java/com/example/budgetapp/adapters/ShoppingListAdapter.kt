package com.example.budgetapp.adapters

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.database.entities.ShoppingList
import com.example.budgetapp.databinding.ItemShoppingListBinding
import com.example.budgetapp.data.ThemeSettings
import com.example.budgetapp.data.InterfaceStyle
import com.example.budgetapp.utils.ThemeAware
import com.example.budgetapp.utils.ThemeManager
import java.text.SimpleDateFormat
import java.util.*

class ShoppingListAdapter(
    private val onItemClick: (ShoppingList) -> Unit,
    private val onDeleteClick: (ShoppingList) -> Unit,
    private val onCompleteToggle: (ShoppingList) -> Unit
) : ListAdapter<ShoppingList, ShoppingListAdapter.ShoppingListViewHolder>(ShoppingListDiffCallback()), ThemeAware {
    
    private var currentThemeSettings: ThemeSettings? = null
    private lateinit var themeManager: ThemeManager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        if (!::themeManager.isInitialized) {
            themeManager = ThemeManager.getInstance(parent.context)
        }
        val binding = ItemShoppingListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ShoppingListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        holder.bind(getItem(position))
        
        // Apply current theme to this item
        currentThemeSettings?.let { settings ->
            applyThemeToItem(holder, settings, getItem(position))
        }
    }

    inner class ShoppingListViewHolder(
        val binding: ItemShoppingListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(shoppingList: ShoppingList) {
            binding.apply {
                textShoppingListName.text = shoppingList.name
                textDescription.text = shoppingList.description ?: "Ingen beskrivning"
                
                val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale("sv", "SE"))
                textCreatedDate.text = "Skapad: ${dateFormat.format(Date(shoppingList.createdAt))}"
                
                if (shoppingList.totalEstimatedCost > 0) {
                    textEstimatedCost.text = String.format("%.2f kr", shoppingList.totalEstimatedCost)
                } else {
                    textEstimatedCost.text = "Ingen kostnad uppskattat"
                }
                
                checkboxCompleted.isChecked = shoppingList.isCompleted
                
                // Set up click listeners
                root.setOnClickListener {
                    onItemClick(shoppingList)
                }
                
                buttonDelete.setOnClickListener {
                    onDeleteClick(shoppingList)
                }
                
                checkboxCompleted.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked != shoppingList.isCompleted) {
                        onCompleteToggle(shoppingList)
                    }
                }
            }
        }
    }

    override fun applyTheme(settings: ThemeSettings) {
        currentThemeSettings = settings
        // Only notify if we have a valid context and the adapter is attached
        if (::themeManager.isInitialized) {
            notifyDataSetChanged() // Refresh all items with new theme
        }
    }
    
    private fun applyThemeToItem(holder: ShoppingListViewHolder, settings: ThemeSettings, shoppingList: ShoppingList) {
        if (!::themeManager.isInitialized) return
        
        val binding = holder.binding
        val accentColor = themeManager.getAccentColorInt()
        val isColorful = settings.interfaceStyle == InterfaceStyle.COLORFUL
        
        // Apply theme to the card background
        if (isColorful) {
            val statusColor = if (shoppingList.isCompleted) {
                adjustColorHue(accentColor, 120f) // Green for completed
            } else {
                adjustColorHue(accentColor, 30f) // Orange for pending
            }
            
            // Very subtle background tint
            val verySubtleColor = adjustColorAlpha(statusColor, 0.03f)
            binding.root.setBackgroundColor(verySubtleColor)
        } else {
            // Reset to default background for material mode
            binding.root.setBackgroundResource(android.R.color.transparent)
        }
        
        // Apply subtle colors to checkbox and buttons
        if (isColorful) {
            binding.checkboxCompleted.buttonTintList = android.content.res.ColorStateList.valueOf(
                adjustColorBrightness(accentColor, 0.8f)
            )
            binding.buttonDelete.setColorFilter(adjustColorBrightness(Color.RED, 0.7f))
        } else {
            binding.checkboxCompleted.buttonTintList = android.content.res.ColorStateList.valueOf(accentColor)
            binding.buttonDelete.clearColorFilter()
        }
    }
    
    private fun adjustColorAlpha(color: Int, alpha: Float): Int {
        val alphaInt = (255 * alpha).toInt().coerceIn(0, 255)
        return Color.argb(alphaInt, Color.red(color), Color.green(color), Color.blue(color))
    }
    
    private fun adjustColorHue(color: Int, hueShift: Float): Int {
        val hsv = FloatArray(3)
        Color.colorToHSV(color, hsv)
        hsv[0] = (hsv[0] + hueShift) % 360f
        hsv[1] = hsv[1] * 0.4f // Reduce saturation for subtlety
        hsv[2] = hsv[2] * 0.9f // Slightly reduce brightness
        return Color.HSVToColor(hsv)
    }
    
    private fun adjustColorBrightness(color: Int, factor: Float): Int {
        val red = (Color.red(color) * factor).toInt().coerceIn(0, 255)
        val green = (Color.green(color) * factor).toInt().coerceIn(0, 255)
        val blue = (Color.blue(color) * factor).toInt().coerceIn(0, 255)
        return Color.rgb(red, green, blue)
    }

    class ShoppingListDiffCallback : DiffUtil.ItemCallback<ShoppingList>() {
        override fun areItemsTheSame(oldItem: ShoppingList, newItem: ShoppingList): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ShoppingList, newItem: ShoppingList): Boolean {
            return oldItem == newItem
        }
    }
}