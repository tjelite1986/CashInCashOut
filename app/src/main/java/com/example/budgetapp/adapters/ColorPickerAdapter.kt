package com.example.budgetapp.adapters

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.R
import com.example.budgetapp.databinding.ItemColorPickerBinding

class ColorPickerAdapter(
    private val colors: List<String>,
    private val onColorSelected: (String) -> Unit
) : RecyclerView.Adapter<ColorPickerAdapter.ColorViewHolder>() {

    private var selectedPosition = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val binding = ItemColorPickerBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ColorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bind(colors[position], position == selectedPosition)
    }

    override fun getItemCount() = colors.size

    fun selectColor(color: String) {
        val newPosition = colors.indexOf(color)
        if (newPosition != -1) {
            val oldPosition = selectedPosition
            selectedPosition = newPosition
            notifyItemChanged(oldPosition)
            notifyItemChanged(selectedPosition)
        }
    }

    inner class ColorViewHolder(private val binding: ItemColorPickerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(color: String, isSelected: Boolean) {
            val colorDrawable = GradientDrawable().apply {
                shape = GradientDrawable.OVAL
                try {
                    val baseColor = Color.parseColor(color)
                    if (isSelected) {
                        // Create gradient for selected color
                        colors = intArrayOf(
                            baseColor,
                            adjustBrightness(baseColor, 0.8f)
                        )
                        gradientType = GradientDrawable.RADIAL_GRADIENT
                        gradientRadius = 20f
                    } else {
                        setColor(baseColor)
                    }
                    
                    // Add subtle shadow effect
                    setStroke(if (isSelected) 4 else 2, Color.WHITE)
                } catch (e: IllegalArgumentException) {
                    setColor(Color.parseColor("#FF6B6B"))
                }
            }

            binding.colorCircle.background = colorDrawable
            binding.selectedIndicator.isVisible = isSelected
            
            // Add subtle animation for selection
            val scale = if (isSelected) 1.1f else 1.0f
            binding.colorCircle.scaleX = scale
            binding.colorCircle.scaleY = scale

            binding.root.setOnClickListener {
                val oldPosition = selectedPosition
                selectedPosition = adapterPosition
                notifyItemChanged(oldPosition)
                notifyItemChanged(selectedPosition)
                onColorSelected(color)
            }
        }
        
        private fun adjustBrightness(color: Int, factor: Float): Int {
            val r = (Color.red(color) * factor).toInt().coerceIn(0, 255)
            val g = (Color.green(color) * factor).toInt().coerceIn(0, 255)
            val b = (Color.blue(color) * factor).toInt().coerceIn(0, 255)
            return Color.rgb(r, g, b)
        }
    }
}