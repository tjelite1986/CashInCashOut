package com.example.budgetapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.budgetapp.ProductListActivity
import com.example.budgetapp.ShoppingListActivity
import com.example.budgetapp.SmartRecommendationsActivity
import com.example.budgetapp.StoreManagerActivity
import com.example.budgetapp.databinding.FragmentMoreBinding

class MoreFragment : Fragment() {
    
    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupClickListeners()
    }

    private fun setupClickListeners() {
        // Statistics
        binding.cardStatistics.setOnClickListener {
            try {
                val statisticsFragment = SimpleStatisticsFragment()
                parentFragmentManager.beginTransaction()
                    .replace(com.example.budgetapp.R.id.nav_host_fragment, statisticsFragment)
                    .addToBackStack(null)
                    .commit()
            } catch (e: Exception) {
                e.printStackTrace()
                // Could show a toast here if statistics fails
            }
        }
        
        // Smart Recommendations
        binding.cardSmartRecommendations.setOnClickListener {
            val intent = Intent(requireContext(), SmartRecommendationsActivity::class.java)
            startActivity(intent)
        }
        
        // Store Management
        binding.cardStoreManagement.setOnClickListener {
            val intent = Intent(requireContext(), StoreManagerActivity::class.java)
            startActivity(intent)
        }
        
        // Shopping Lists
        binding.cardShoppingLists.setOnClickListener {
            val intent = Intent(requireContext(), ShoppingListActivity::class.java)
            startActivity(intent)
        }
        
        // Product Management
        binding.cardProductManagement.setOnClickListener {
            val intent = Intent(requireContext(), ProductListActivity::class.java)
            startActivity(intent)
        }
        
        // Settings
        binding.cardSettings.setOnClickListener {
            val settingsFragment = SettingsFragment()
            parentFragmentManager.beginTransaction()
                .replace(com.example.budgetapp.R.id.nav_host_fragment, settingsFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}