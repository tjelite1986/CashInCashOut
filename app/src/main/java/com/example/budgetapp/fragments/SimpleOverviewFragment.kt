package com.example.budgetapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.budgetapp.databinding.FragmentOverviewBinding

class SimpleOverviewFragment : Fragment() {

    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("SimpleOverviewFragment", "onCreateView started")
        
        try {
            _binding = FragmentOverviewBinding.inflate(inflater, container, false)
            Log.d("SimpleOverviewFragment", "Binding inflated successfully")
            return binding.root
        } catch (e: Exception) {
            Log.e("SimpleOverviewFragment", "Error in onCreateView", e)
            throw e
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        try {
            Log.d("SimpleOverviewFragment", "onViewCreated started")
            
            // Set static text to test basic functionality without database
            binding.apply {
                textGreeting.text = "Välkommen till Budget App"
                textTotalBalance.text = "Test-läge - ingen databasanslutning"
                textMonthlyIncome.text = "0 kr"
                textMonthlyExpenses.text = "0 kr"
                textNetWorth.text = "0 kr"
            }
            
            Log.d("SimpleOverviewFragment", "onViewCreated completed successfully")
            
        } catch (e: Exception) {
            Log.e("SimpleOverviewFragment", "Error in onViewCreated", e)
            e.printStackTrace()
            
            // Try to show error message instead of crashing
            try {
                binding.textGreeting.text = "FEL: ${e.message}"
                binding.textTotalBalance.text = "App-fel upptäckt"
            } catch (fallbackError: Exception) {
                Log.e("SimpleOverviewFragment", "Fallback also failed", fallbackError)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("SimpleOverviewFragment", "onDestroyView")
        _binding = null
    }
}