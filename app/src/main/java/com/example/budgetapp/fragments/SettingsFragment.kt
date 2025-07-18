package com.example.budgetapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.budgetapp.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            setupUI()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setupUI() {
        binding.textTitle.text = "Inställningar"
        binding.textDescription.text = "Här kan du anpassa appen efter dina behov"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}