package com.example.budgetapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.R
import com.example.budgetapp.database.entities.StoreChain
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView

class StoreChainAdapter(
    private val onEditClick: (StoreChain) -> Unit,
    private val onDeleteClick: (StoreChain) -> Unit,
    private val onToggleActiveClick: (StoreChain) -> Unit
) : ListAdapter<StoreChain, StoreChainAdapter.StoreChainViewHolder>(StoreChainDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreChainViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_store_chain, parent, false)
        return StoreChainViewHolder(view)
    }

    override fun onBindViewHolder(holder: StoreChainViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class StoreChainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardView: MaterialCardView = itemView.findViewById(R.id.cardView)
        private val tvChainName: TextView = itemView.findViewById(R.id.tvChainName)
        private val tvChainWebsite: TextView = itemView.findViewById(R.id.tvChainWebsite)
        private val tvChainDescription: TextView = itemView.findViewById(R.id.tvChainDescription)
        private val tvChainType: TextView = itemView.findViewById(R.id.tvChainType)
        private val btnEdit: MaterialButton = itemView.findViewById(R.id.btnEdit)
        private val btnDelete: MaterialButton = itemView.findViewById(R.id.btnDelete)
        private val btnToggleActive: MaterialButton = itemView.findViewById(R.id.btnToggleActive)

        fun bind(chain: StoreChain) {
            tvChainName.text = chain.name
            
            // Show website if available
            if (!chain.website.isNullOrEmpty()) {
                tvChainWebsite.text = chain.website
                tvChainWebsite.visibility = View.VISIBLE
            } else {
                tvChainWebsite.visibility = View.GONE
            }
            
            // Show description if available
            if (!chain.description.isNullOrEmpty()) {
                tvChainDescription.text = chain.description
                tvChainDescription.visibility = View.VISIBLE
            } else {
                tvChainDescription.visibility = View.GONE
            }
            
            // Show chain type (default or custom)
            tvChainType.text = if (chain.isDefault) "Standardkedja" else "Egen kedja"
            tvChainType.setTextColor(
                if (chain.isDefault) 
                    itemView.context.getColor(R.color.primary)
                else 
                    itemView.context.getColor(R.color.accent_teal)
            )
            
            // Update active/inactive state
            val alpha = if (chain.isActive) 1.0f else 0.6f
            cardView.alpha = alpha
            
            // Update toggle button text
            btnToggleActive.text = if (chain.isActive) "Inaktivera" else "Aktivera"
            
            // Hide edit/delete buttons for default chains or show based on status
            if (chain.isDefault) {
                btnEdit.visibility = View.GONE
                btnDelete.visibility = View.GONE
                // Only show toggle button if chain is active (can't deactivate default chains)
                btnToggleActive.visibility = if (chain.isActive) View.GONE else View.VISIBLE
            } else {
                btnEdit.visibility = View.VISIBLE
                btnDelete.visibility = View.VISIBLE
                btnToggleActive.visibility = View.VISIBLE
            }
            
            // Set click listeners
            btnEdit.setOnClickListener { onEditClick(chain) }
            btnDelete.setOnClickListener { onDeleteClick(chain) }
            btnToggleActive.setOnClickListener { onToggleActiveClick(chain) }
        }
    }

    private class StoreChainDiffCallback : DiffUtil.ItemCallback<StoreChain>() {
        override fun areItemsTheSame(oldItem: StoreChain, newItem: StoreChain): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: StoreChain, newItem: StoreChain): Boolean {
            return oldItem == newItem
        }
    }
}