package com.example.budgetapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.database.entities.Store
import com.google.android.material.card.MaterialCardView

class StoreAdapter(
    private val onEditClick: (Store) -> Unit,
    private val onDeleteClick: (Store) -> Unit
) : ListAdapter<Store, StoreAdapter.StoreViewHolder>(StoreDiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_store, parent, false)
        return StoreViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
        val store = getItem(position)
        holder.bind(store)
    }
    
    inner class StoreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cardView: MaterialCardView = itemView.findViewById(R.id.cardStore)
        private val tvStoreName: TextView = itemView.findViewById(R.id.tvStoreName)
        private val tvStoreChain: TextView = itemView.findViewById(R.id.tvStoreChain)
        private val tvStoreAddress: TextView = itemView.findViewById(R.id.tvStoreAddress)
        private val tvStoreCity: TextView = itemView.findViewById(R.id.tvStoreCity)
        private val btnEdit: ImageButton = itemView.findViewById(R.id.btnEditStore)
        private val btnDelete: ImageButton = itemView.findViewById(R.id.btnDeleteStore)
        
        fun bind(store: Store) {
            tvStoreName.text = store.name
            
            // Visa kedja om den finns
            if (!store.chain.isNullOrEmpty()) {
                tvStoreChain.text = store.chain
                tvStoreChain.visibility = View.VISIBLE
            } else {
                tvStoreChain.visibility = View.GONE
            }
            
            // Visa adress om den finns
            if (!store.address.isNullOrEmpty()) {
                tvStoreAddress.text = store.address
                tvStoreAddress.visibility = View.VISIBLE
            } else {
                tvStoreAddress.visibility = View.GONE
            }
            
            // Visa stad om den finns
            if (!store.city.isNullOrEmpty()) {
                tvStoreCity.text = store.city
                tvStoreCity.visibility = View.VISIBLE
            } else {
                tvStoreCity.visibility = View.GONE
            }
            
            btnEdit.setOnClickListener {
                onEditClick(store)
            }
            
            btnDelete.setOnClickListener {
                onDeleteClick(store)
            }
        }
    }
    
    class StoreDiffCallback : DiffUtil.ItemCallback<Store>() {
        override fun areItemsTheSame(oldItem: Store, newItem: Store): Boolean {
            return oldItem.id == newItem.id
        }
        
        override fun areContentsTheSame(oldItem: Store, newItem: Store): Boolean {
            return oldItem == newItem
        }
    }
}