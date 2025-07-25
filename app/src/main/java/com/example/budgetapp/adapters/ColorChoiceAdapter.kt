package com.example.budgetapp.adapters

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.data.AccentColor
import com.example.budgetapp.databinding.ItemColorChoiceBinding

class ColorChoiceAdapter(
    private val colors: List<AccentColor>,
    private var selectedColor: AccentColor,
    private val onColorSelected: (AccentColor) -> Unit
) : RecyclerView.Adapter<ColorChoiceAdapter.ColorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorViewHolder {
        val binding = ItemColorChoiceBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ColorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ColorViewHolder, position: Int) {
        holder.bind(colors[position])
    }

    override fun getItemCount(): Int = colors.size

    fun updateSelectedColor(color: AccentColor) {
        val oldSelectedIndex = colors.indexOf(selectedColor)
        val newSelectedIndex = colors.indexOf(color)
        
        selectedColor = color
        
        if (oldSelectedIndex != -1) {
            notifyItemChanged(oldSelectedIndex)
        }
        if (newSelectedIndex != -1) {
            notifyItemChanged(newSelectedIndex)
        }
    }

    inner class ColorViewHolder(
        private val binding: ItemColorChoiceBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(color: AccentColor) {
            binding.textColorName.text = color.displayName
            binding.radioColor.isChecked = color == selectedColor

            // Create color circle
            val drawable = GradientDrawable().apply {
                shape = GradientDrawable.OVAL
                setColor(Color.parseColor(color.colorValue))
                setSize(96, 96) // 32dp * 3 for density
            }
            binding.viewColorCircle.background = drawable

            binding.root.setOnClickListener {
                onColorSelected(color)
            }
        }
    }
}