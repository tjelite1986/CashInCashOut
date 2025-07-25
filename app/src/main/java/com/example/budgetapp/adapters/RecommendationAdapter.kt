package com.example.budgetapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.R
import com.example.budgetapp.data.*
import com.example.budgetapp.databinding.ItemRecommendationBinding
import com.example.budgetapp.databinding.ItemRecommendationHeaderBinding

class RecommendationAdapter(
    private val onStoreClick: (StoreRecommendation) -> Unit,
    private val onItemClick: (ItemRecommendation) -> Unit,
    private val onPriceClick: (PriceRecommendation) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_HEADER = 0
        private const val TYPE_STORE = 1
        private const val TYPE_ITEM = 2
    }

    private var recommendations: ShoppingListRecommendations? = null
    private val displayItems = mutableListOf<DisplayItem>()

    sealed class DisplayItem {
        object StoreHeader : DisplayItem()
        data class StoreItem(val store: StoreRecommendation) : DisplayItem()
        object ItemHeader : DisplayItem()
        data class ItemItem(val item: ItemRecommendation) : DisplayItem()
    }

    fun submitRecommendations(recommendations: ShoppingListRecommendations) {
        val oldItems = displayItems.toList()
        this.recommendations = recommendations
        updateDisplayItems()
        
        // Use DiffUtil for better performance
        val diffCallback = DisplayItemDiffCallback(oldItems, displayItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)
    }

    private fun updateDisplayItems() {
        displayItems.clear()
        val recs = recommendations ?: return

        // Add store recommendations if any
        if (recs.recommendedStores.isNotEmpty()) {
            displayItems.add(DisplayItem.StoreHeader)
            recs.recommendedStores.take(3).forEach { store ->
                displayItems.add(DisplayItem.StoreItem(store))
            }
        }

        // Add item recommendations if any
        if (recs.itemRecommendations.isNotEmpty()) {
            displayItems.add(DisplayItem.ItemHeader)
            recs.itemRecommendations.forEach { item ->
                displayItems.add(DisplayItem.ItemItem(item))
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (displayItems[position]) {
            is DisplayItem.StoreHeader, is DisplayItem.ItemHeader -> TYPE_HEADER
            is DisplayItem.StoreItem -> TYPE_STORE
            is DisplayItem.ItemItem -> TYPE_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_HEADER -> {
                val binding = ItemRecommendationHeaderBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                HeaderViewHolder(binding)
            }
            TYPE_STORE -> {
                val binding = ItemRecommendationBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                StoreViewHolder(binding)
            }
            TYPE_ITEM -> {
                val binding = ItemRecommendationBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                ItemViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = displayItems[position]) {
            is DisplayItem.StoreHeader -> {
                (holder as HeaderViewHolder).bind("Rekommenderade butiker")
            }
            is DisplayItem.ItemHeader -> {
                (holder as HeaderViewHolder).bind("Produktrekommendationer")
            }
            is DisplayItem.StoreItem -> {
                (holder as StoreViewHolder).bind(item.store)
            }
            is DisplayItem.ItemItem -> {
                (holder as ItemViewHolder).bind(item.item)
            }
        }
    }

    override fun getItemCount(): Int = displayItems.size

    inner class HeaderViewHolder(
        private val binding: ItemRecommendationHeaderBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(title: String) {
            binding.textHeaderTitle.text = title
        }
    }

    inner class StoreViewHolder(
        private val binding: ItemRecommendationBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(storeRecommendation: StoreRecommendation) {
            binding.textTitle.text = storeRecommendation.store.name
            binding.textSubtitle.text = storeRecommendation.store.chain ?: "Butik"
            binding.textDetails.text = "${storeRecommendation.itemCount} produkter • Spara ${String.format("%.0f", storeRecommendation.totalSavings)} kr"
            
            binding.imageIcon.setImageResource(R.drawable.ic_store)
            binding.cardBackground.setCardBackgroundColor(
                binding.root.context.getColor(R.color.light_blue_background)
            )
            
            binding.recyclerSubItems.visibility = View.GONE
            
            binding.root.setOnClickListener {
                onStoreClick(storeRecommendation)
            }
        }
    }

    inner class ItemViewHolder(
        private val binding: ItemRecommendationBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(itemRecommendation: ItemRecommendation) {
            binding.textTitle.text = itemRecommendation.customItemName ?: itemRecommendation.productName
            binding.textSubtitle.text = "${itemRecommendation.quantity} ${itemRecommendation.unit}"
            binding.textDetails.text = "Spara upp till ${String.format("%.0f", itemRecommendation.potentialSavings)} kr"
            
            binding.imageIcon.setImageResource(R.drawable.ic_add_product)
            binding.cardBackground.setCardBackgroundColor(
                binding.root.context.getColor(R.color.light_green_background)
            )
            
            // Show price recommendations in nested RecyclerView
            if (itemRecommendation.priceRecommendations.isNotEmpty()) {
                binding.recyclerSubItems.visibility = View.VISIBLE
                val priceAdapter = PriceRecommendationAdapter { priceRec ->
                    onPriceClick(priceRec)
                }
                binding.recyclerSubItems.layoutManager = LinearLayoutManager(binding.root.context)
                binding.recyclerSubItems.adapter = priceAdapter
                priceAdapter.submitList(itemRecommendation.priceRecommendations.take(3))
            } else {
                binding.recyclerSubItems.visibility = View.GONE
            }
            
            binding.root.setOnClickListener {
                onItemClick(itemRecommendation)
            }
        }
    }
    
    private class DisplayItemDiffCallback(
        private val oldList: List<DisplayItem>,
        private val newList: List<DisplayItem>
    ) : DiffUtil.Callback() {
        
        override fun getOldListSize(): Int = oldList.size
        
        override fun getNewListSize(): Int = newList.size
        
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            val oldItem = oldList[oldItemPosition]
            val newItem = newList[newItemPosition]
            
            return when {
                oldItem is DisplayItem.StoreHeader && newItem is DisplayItem.StoreHeader -> true
                oldItem is DisplayItem.ItemHeader && newItem is DisplayItem.ItemHeader -> true
                oldItem is DisplayItem.StoreItem && newItem is DisplayItem.StoreItem -> 
                    oldItem.store.store.id == newItem.store.store.id
                oldItem is DisplayItem.ItemItem && newItem is DisplayItem.ItemItem -> 
                    oldItem.item.itemId == newItem.item.itemId
                else -> false
            }
        }
        
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }
}