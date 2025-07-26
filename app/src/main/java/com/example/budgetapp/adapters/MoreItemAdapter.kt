package com.example.budgetapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.data.MoreMenuItem
import com.example.budgetapp.databinding.ItemMoreHeaderBinding
import com.example.budgetapp.databinding.ItemMoreMenuBinding

class MoreItemAdapter(
    private val onItemClick: (MoreMenuItem) -> Unit
) : ListAdapter<MoreMenuItem, RecyclerView.ViewHolder>(MoreItemDiffCallback()) {
    
    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_ITEM = 1
    }
    
    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).isHeader) TYPE_HEADER else TYPE_ITEM
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                val binding = ItemMoreHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                HeaderViewHolder(binding)
            }
            else -> {
                val binding = ItemMoreMenuBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                ItemViewHolder(binding)
            }
        }
    }
    
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is HeaderViewHolder -> holder.bind(item)
            is ItemViewHolder -> holder.bind(item, onItemClick)
        }
    }
    
    class HeaderViewHolder(
        private val binding: ItemMoreHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(item: MoreMenuItem) {
            binding.apply {
                textTitle.text = item.title
                textSubtitle.text = item.subtitle
                imageIcon.setImageResource(item.icon)
            }
        }
    }
    
    class ItemViewHolder(
        private val binding: ItemMoreMenuBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(item: MoreMenuItem, onItemClick: (MoreMenuItem) -> Unit) {
            binding.apply {
                textTitle.text = item.title
                textSubtitle.text = item.subtitle
                imageIcon.setImageResource(item.icon)
                
                root.setOnClickListener {
                    onItemClick(item)
                }
            }
        }
    }
}

class MoreItemDiffCallback : DiffUtil.ItemCallback<MoreMenuItem>() {
    override fun areItemsTheSame(oldItem: MoreMenuItem, newItem: MoreMenuItem): Boolean {
        return oldItem.id == newItem.id
    }
    
    override fun areContentsTheSame(oldItem: MoreMenuItem, newItem: MoreMenuItem): Boolean {
        return oldItem == newItem
    }
}