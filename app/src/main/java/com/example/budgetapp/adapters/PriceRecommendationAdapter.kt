package com.example.budgetapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetapp.R
import com.example.budgetapp.data.PriceRecommendation
import com.example.budgetapp.data.RecommendationType
import com.example.budgetapp.databinding.ItemPriceRecommendationBinding

class PriceRecommendationAdapter(
    private val onPriceClick: (PriceRecommendation) -> Unit
) : ListAdapter<PriceRecommendation, PriceRecommendationAdapter.PriceViewHolder>(PriceDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PriceViewHolder {
        val binding = ItemPriceRecommendationBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PriceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PriceViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PriceViewHolder(
        private val binding: ItemPriceRecommendationBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(recommendation: PriceRecommendation) {
            binding.textStoreName.text = recommendation.storeName
            binding.textPrice.text = "${String.format("%.0f", recommendation.currentPrice)} kr"
            
            // Set recommendation type indicator
            when (recommendation.recommendationType) {
                RecommendationType.BEST_PRICE -> {
                    binding.textRecommendationType.text = "Bästa pris"
                    binding.textRecommendationType.setTextColor(
                        binding.root.context.getColor(R.color.success)
                    )
                    binding.imageTypeIcon.setImageResource(R.drawable.ic_star)
                }
                RecommendationType.PRICE_DROP -> {
                    binding.textRecommendationType.text = "Prisfall!"
                    binding.textRecommendationType.setTextColor(
                        binding.root.context.getColor(R.color.success)
                    )
                    binding.imageTypeIcon.setImageResource(R.drawable.ic_trending_down)
                }
                RecommendationType.GOOD_DEAL -> {
                    binding.textRecommendationType.text = "Bra pris"
                    binding.textRecommendationType.setTextColor(
                        binding.root.context.getColor(R.color.success)
                    )
                    binding.imageTypeIcon.setImageResource(R.drawable.ic_thumb_up)
                }
                RecommendationType.AVOID_HIGH_PRICE -> {
                    binding.textRecommendationType.text = "Högt pris"
                    binding.textRecommendationType.setTextColor(
                        binding.root.context.getColor(R.color.warning)
                    )
                    binding.imageTypeIcon.setImageResource(R.drawable.ic_warning)
                }
                RecommendationType.PRICE_TREND_UP -> {
                    binding.textRecommendationType.text = "Stigande"
                    binding.textRecommendationType.setTextColor(
                        binding.root.context.getColor(R.color.warning)
                    )
                    binding.imageTypeIcon.setImageResource(R.drawable.ic_trending_up)
                }
                RecommendationType.PRICE_TREND_DOWN -> {
                    binding.textRecommendationType.text = "Fallande"
                    binding.textRecommendationType.setTextColor(
                        binding.root.context.getColor(R.color.success)
                    )
                    binding.imageTypeIcon.setImageResource(R.drawable.ic_trending_down)
                }
            }
            
            // Show savings if any
            if (recommendation.savingsAmount > 0) {
                binding.textSavings.text = "Spara ${String.format("%.0f", recommendation.savingsAmount)} kr"
                binding.textSavings.visibility = android.view.View.VISIBLE
            } else {
                binding.textSavings.visibility = android.view.View.GONE
            }
            
            // Show confidence level with alpha
            binding.root.alpha = if (recommendation.confidence < 0.5) 0.7f else 1.0f
            
            binding.root.setOnClickListener {
                onPriceClick(recommendation)
            }
        }
    }

    private class PriceDiffCallback : DiffUtil.ItemCallback<PriceRecommendation>() {
        override fun areItemsTheSame(oldItem: PriceRecommendation, newItem: PriceRecommendation): Boolean {
            return oldItem.productId == newItem.productId && oldItem.storeId == newItem.storeId
        }

        override fun areContentsTheSame(oldItem: PriceRecommendation, newItem: PriceRecommendation): Boolean {
            return oldItem == newItem
        }
    }
}