package com.example.budgetapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgetapp.AddLoanActivity
import com.example.budgetapp.LoanAdapter
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.Loan
import com.example.budgetapp.database.entities.LoanType
import com.example.budgetapp.databinding.FragmentLoansBinding
import com.example.budgetapp.data.ThemeSettings
import kotlinx.coroutines.Job
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.*

class LoansFragment : BaseFragment() {

    private var _binding: FragmentLoansBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var database: BudgetDatabase
    private lateinit var loanAdapter: LoanAdapter
    private val currencyFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
    
    private var allLoans = listOf<Loan>()
    private var isFabExpanded = false
    private var dataLoadingJob: Job? = null

    enum class FilterType {
        ALL, BORROWED, LENT, ACTIVE, PAID_BACK
    }
    
    private var currentFilter = FilterType.ALL

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        try {
            _binding = FragmentLoansBinding.inflate(inflater, container, false)
            return binding.root
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        try {
            database = BudgetDatabase.getDatabase(requireContext())
            setupRecyclerView()
            setupClickListeners()
            setupChipFilters()
            loadLoans()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(requireContext(), "Fel vid uppstart: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun setupRecyclerView() {
        try {
            loanAdapter = LoanAdapter(
                loans = emptyList(),
                onEditClick = { loan -> editLoan(loan) },
                onDeleteClick = { loan -> showDeleteDialog(loan) },
                onPayBackClick = { loan -> showPayBackDialog(loan) },
                onUndoPaymentClick = { loan -> showUndoPaymentDialog(loan) }
            )
            
            binding.recyclerLoans.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = loanAdapter
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(requireContext(), "Fel vid setup av RecyclerView: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    private fun setupClickListeners() {
        binding.fabMain.setOnClickListener {
            if (_binding != null) {
                toggleFabMenu()
            }
        }
        
        binding.fabBorrowed.setOnClickListener {
            if (_binding != null) {
                collapseFabMenu()
                val intent = Intent(requireContext(), AddLoanActivity::class.java)
                intent.putExtra("loan_type", LoanType.BORROWED.name)
                startActivity(intent)
            }
        }
        
        binding.fabLent.setOnClickListener {
            if (_binding != null) {
                collapseFabMenu()
                val intent = Intent(requireContext(), AddLoanActivity::class.java)
                intent.putExtra("loan_type", LoanType.LENT.name)
                startActivity(intent)
            }
        }
        
        binding.btnAddBorrowedEmpty.setOnClickListener {
            if (_binding != null) {
                val intent = Intent(requireContext(), AddLoanActivity::class.java)
                intent.putExtra("loan_type", LoanType.BORROWED.name)
                startActivity(intent)
            }
        }
        
        binding.btnAddLentEmpty.setOnClickListener {
            if (_binding != null) {
                val intent = Intent(requireContext(), AddLoanActivity::class.java)
                intent.putExtra("loan_type", LoanType.LENT.name)
                startActivity(intent)
            }
        }
    }
    
    private fun setupChipFilters() {
        binding.chipGroupFilter.setOnCheckedStateChangeListener { _, checkedIds ->
            when (checkedIds.firstOrNull()) {
                binding.chipAll.id -> {
                    currentFilter = FilterType.ALL
                    filterLoans()
                }
                binding.chipBorrowed.id -> {
                    currentFilter = FilterType.BORROWED
                    filterLoans()
                }
                binding.chipLent.id -> {
                    currentFilter = FilterType.LENT
                    filterLoans()
                }
                binding.chipActive.id -> {
                    currentFilter = FilterType.ACTIVE
                    filterLoans()
                }
                binding.chipPaidBack.id -> {
                    currentFilter = FilterType.PAID_BACK
                    filterLoans()
                }
            }
        }
    }

    private fun toggleFabMenu() {
        if (isFabExpanded) {
            collapseFabMenu()
        } else {
            expandFabMenu()
        }
    }
    
    private fun expandFabMenu() {
        if (_binding == null) return
        
        try {
            isFabExpanded = true
            binding.fabBorrowed.visibility = View.VISIBLE
            binding.fabLent.visibility = View.VISIBLE
            
            binding.fabBorrowed.animate()
                .translationY(-140f)
                .alpha(1f)
                .setDuration(300)
                .start()
                
            binding.fabLent.animate()
                .translationY(-210f)
                .alpha(1f)
                .setDuration(300)
                .start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    
    private fun collapseFabMenu() {
        if (_binding == null) return
        
        try {
            isFabExpanded = false
            
            binding.fabBorrowed.animate()
                .translationY(0f)
                .alpha(0f)
                .setDuration(300)
                .withEndAction {
                    try {
                        if (_binding != null) {
                            binding.fabBorrowed.visibility = View.GONE
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                .start()
                
            binding.fabLent.animate()
                .translationY(0f)
                .alpha(0f)
                .setDuration(300)
                .withEndAction {
                    try {
                        if (_binding != null) {
                            binding.fabLent.visibility = View.GONE
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                .start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun loadLoans() {
        dataLoadingJob?.cancel()
        dataLoadingJob = lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                try {
                    database.loanDao().getAllLoans().collect { loans ->
                        if (_binding != null) {
                            allLoans = loans
                            updateSummary()
                            filterLoans()
                        }
                    }
                } catch (e: CancellationException) {
                    // Fragment lifecycle cancelled the job - this is normal
                } catch (e: Exception) {
                    e.printStackTrace()
                    if (_binding != null) {
                        Toast.makeText(requireContext(), "Fel vid laddning av lån: ${e.message}", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
    
    private fun updateSummary() {
        if (_binding == null) return
        
        val borrowedAmount = allLoans.filter { it.type == LoanType.BORROWED && !it.isPaidBack }
            .sumOf { it.amount }
        val lentAmount = allLoans.filter { it.type == LoanType.LENT && !it.isPaidBack }
            .sumOf { it.amount }
            
        binding.textBorrowedTotal.text = currencyFormat.format(borrowedAmount)
        binding.textLentTotal.text = currencyFormat.format(lentAmount)
    }
    
    private fun filterLoans() {
        if (_binding == null) return
        
        val filteredLoans = when (currentFilter) {
            FilterType.ALL -> allLoans
            FilterType.BORROWED -> allLoans.filter { it.type == LoanType.BORROWED }
            FilterType.LENT -> allLoans.filter { it.type == LoanType.LENT }
            FilterType.ACTIVE -> allLoans.filter { !it.isPaidBack }
            FilterType.PAID_BACK -> allLoans.filter { it.isPaidBack }
        }
        
        loanAdapter.updateLoans(filteredLoans)
        
        // Show empty state if no loans
        if (filteredLoans.isEmpty()) {
            binding.recyclerLoans.visibility = View.GONE
            binding.layoutEmptyState.visibility = View.VISIBLE
            
            when (currentFilter) {
                FilterType.ALL -> {
                    binding.textEmptyTitle.text = "Inga lån"
                    binding.textEmptyMessage.text = "Lägg till ditt första lån genom att trycka på + knappen"
                }
                FilterType.BORROWED -> {
                    binding.textEmptyTitle.text = "Inga lånade pengar"
                    binding.textEmptyMessage.text = "Lägg till pengar du lånat från någon"
                }
                FilterType.LENT -> {
                    binding.textEmptyTitle.text = "Inga utlånade pengar"
                    binding.textEmptyMessage.text = "Lägg till pengar du lånat ut till någon"
                }
                FilterType.ACTIVE -> {
                    binding.textEmptyTitle.text = "Inga aktiva lån"
                    binding.textEmptyMessage.text = "Alla lån är återbetalda"
                }
                FilterType.PAID_BACK -> {
                    binding.textEmptyTitle.text = "Inga återbetalda lån"
                    binding.textEmptyMessage.text = "Inga lån har markerats som återbetalda än"
                }
            }
        } else {
            binding.recyclerLoans.visibility = View.VISIBLE
            binding.layoutEmptyState.visibility = View.GONE
        }
    }

    private fun editLoan(loan: Loan) {
        val intent = Intent(requireContext(), AddLoanActivity::class.java)
        intent.putExtra("loan_id", loan.id)
        startActivity(intent)
    }

    private fun showDeleteDialog(loan: Loan) {
        val title = "Ta bort lån"
        val message = "Är du säker på att du vill ta bort \"${loan.title}\"?"
        
        AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Ta bort") { _, _ ->
                deleteLoan(loan)
            }
            .setNegativeButton("Avbryt", null)
            .show()
    }

    private fun showPayBackDialog(loan: Loan) {
        if (loan.isPaidBack) {
            Toast.makeText(requireContext(), "Lånet är redan återbetalt", Toast.LENGTH_SHORT).show()
            return
        }
        
        val title = if (loan.type == LoanType.BORROWED) "Markera som återbetalt" else "Markera som mottaget"
        val message = "Markera \"${loan.title}\" som återbetalt?"
        
        AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Ja") { _, _ ->
                markLoanAsPaidBack(loan)
            }
            .setNegativeButton("Avbryt", null)
            .show()
    }

    private fun deleteLoan(loan: Loan) {
        lifecycleScope.launch {
            try {
                database.loanDao().deleteLoan(loan)
                if (_binding != null) {
                    Toast.makeText(requireContext(), "Lån borttaget", Toast.LENGTH_SHORT).show()
                }
            } catch (e: CancellationException) {
                // Fragment lifecycle cancelled the job - this is normal
            } catch (e: Exception) {
                e.printStackTrace()
                if (_binding != null) {
                    Toast.makeText(requireContext(), "Fel vid borttagning: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun markLoanAsPaidBack(loan: Loan) {
        lifecycleScope.launch {
            try {
                database.loanDao().markLoanAsPaidBack(
                    loanId = loan.id,
                    paidDate = System.currentTimeMillis(),
                    amount = loan.amount
                )
                if (_binding != null) {
                    Toast.makeText(requireContext(), "Lån markerat som återbetalt", Toast.LENGTH_SHORT).show()
                }
            } catch (e: CancellationException) {
                // Fragment lifecycle cancelled the job - this is normal
            } catch (e: Exception) {
                e.printStackTrace()
                if (_binding != null) {
                    Toast.makeText(requireContext(), "Fel vid uppdatering: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun showUndoPaymentDialog(loan: Loan) {
        if (!loan.isPaidBack) {
            Toast.makeText(requireContext(), "Lånet är inte markerat som återbetalt", Toast.LENGTH_SHORT).show()
            return
        }
        
        val title = "Ångra återbetalning"
        val message = "Markera \"${loan.title}\" som aktivt lån igen?"
        
        AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Ja") { _, _ ->
                undoLoanPayment(loan)
            }
            .setNegativeButton("Avbryt", null)
            .show()
    }

    private fun undoLoanPayment(loan: Loan) {
        lifecycleScope.launch {
            try {
                database.loanDao().undoLoanPayment(loan.id)
                if (_binding != null) {
                    Toast.makeText(requireContext(), "Återbetalning ångrad", Toast.LENGTH_SHORT).show()
                }
            } catch (e: CancellationException) {
                // Fragment lifecycle cancelled the job - this is normal
            } catch (e: Exception) {
                e.printStackTrace()
                if (_binding != null) {
                    Toast.makeText(requireContext(), "Fel vid ångring: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Data loading is handled by the Flow in onViewCreated
    }
    
    override fun onPause() {
        super.onPause()
        // Collapse FAB menu when leaving fragment
        if (_binding != null && isFabExpanded) {
            try {
                binding.fabBorrowed.animate().cancel()
                binding.fabLent.animate().cancel()
                binding.fabBorrowed.visibility = View.GONE
                binding.fabLent.visibility = View.GONE
                isFabExpanded = false
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onDestroyView() {
        try {
            // Cancel any ongoing data loading
            dataLoadingJob?.cancel()
            
            // Cancel any ongoing animations
            _binding?.let { binding ->
                binding.fabBorrowed.animate().cancel()
                binding.fabLent.animate().cancel()
                binding.fabMain.animate().cancel()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        super.onDestroyView()
        _binding = null
    }
    
    override fun applyCustomTheme(settings: ThemeSettings) {
        // Apply custom theme styling specific to LoansFragment
        val accentColor = themeManager.getAccentColorInt()
        
        // Update FAB colors
        binding.fabMain.backgroundTintList = android.content.res.ColorStateList.valueOf(accentColor)
        binding.fabBorrowed.backgroundTintList = android.content.res.ColorStateList.valueOf(accentColor)
        binding.fabLent.backgroundTintList = android.content.res.ColorStateList.valueOf(accentColor)
        
        // Apply theme to filter chips
        applyThemeToChips(settings)
        
        // Apply colorful styling to summary cards if enabled
        if (settings.interfaceStyle == com.example.budgetapp.data.InterfaceStyle.COLORFUL) {
            applyColorfulLoanCards(accentColor)
        }
        
        // Update adapter theme
        if (::loanAdapter.isInitialized) {
            loanAdapter.applyTheme(settings)
        }
    }
    
    private fun applyColorfulLoanCards(accentColor: Int) {
        // Apply very subtle colorful backgrounds to loan summary cards
        
        // Borrowed loans card with very subtle orange tint
        binding.cardBorrowedSummary.setCardBackgroundColor(
            adjustColorHue(accentColor, 0.08f, 0.04f) // Much more subtle orange
        )
        
        // Lent loans card with very subtle blue tint
        binding.cardLentSummary.setCardBackgroundColor(
            adjustColorHue(accentColor, 0.55f, 0.04f) // Much more subtle blue
        )
    }
    
    private fun adjustColorAlpha(color: Int, alpha: Float): Int {
        val a = (255 * alpha).toInt()
        val r = android.graphics.Color.red(color)
        val g = android.graphics.Color.green(color)
        val b = android.graphics.Color.blue(color)
        return android.graphics.Color.argb(a, r, g, b)
    }
    
    private fun applyThemeToChips(settings: ThemeSettings) {
        val accentColor = themeManager.getAccentColorInt()
        val isColorful = settings.interfaceStyle == com.example.budgetapp.data.InterfaceStyle.COLORFUL
        
        val chips = listOf(
            binding.chipAll, 
            binding.chipBorrowed, 
            binding.chipLent, 
            binding.chipActive, 
            binding.chipPaidBack
        )
        
        chips.forEach { chip ->
            val context = requireContext()
            
            if (isColorful) {
                // Subtle colorful styling
                val subtleAccent = adjustColorAlpha(accentColor, 0.12f)
                val textColor = adjustColorBrightness(accentColor, 0.6f) // Darker for better readability
                
                chip.chipBackgroundColor = android.content.res.ColorStateList.valueOf(subtleAccent)
                chip.setTextColor(android.content.res.ColorStateList.valueOf(textColor))
                chip.chipStrokeColor = android.content.res.ColorStateList.valueOf(adjustColorAlpha(accentColor, 0.2f))
                chip.checkedIconTint = android.content.res.ColorStateList.valueOf(textColor)
            } else {
                // Clean material design colors
                val isDark = themeManager.isDarkMode()
                
                if (isDark) {
                    chip.chipBackgroundColor = android.content.res.ColorStateList.valueOf(
                        context.getColor(com.google.android.material.R.color.material_dynamic_neutral20)
                    )
                    chip.setTextColor(android.content.res.ColorStateList.valueOf(
                        context.getColor(com.google.android.material.R.color.material_dynamic_neutral90)
                    ))
                } else {
                    chip.chipBackgroundColor = android.content.res.ColorStateList.valueOf(
                        context.getColor(com.google.android.material.R.color.material_dynamic_neutral95)
                    )
                    chip.setTextColor(android.content.res.ColorStateList.valueOf(
                        context.getColor(com.google.android.material.R.color.material_dynamic_neutral10)
                    ))
                }
                
                chip.chipStrokeColor = android.content.res.ColorStateList.valueOf(
                    adjustColorAlpha(accentColor, 0.3f)
                )
                chip.checkedIconTint = android.content.res.ColorStateList.valueOf(accentColor)
            }
        }
    }
    
    private fun adjustColorBrightness(color: Int, factor: Float): Int {
        val red = (android.graphics.Color.red(color) * factor).toInt().coerceIn(0, 255)
        val green = (android.graphics.Color.green(color) * factor).toInt().coerceIn(0, 255)
        val blue = (android.graphics.Color.blue(color) * factor).toInt().coerceIn(0, 255)
        return android.graphics.Color.rgb(red, green, blue)
    }
    
    private fun adjustColorHue(baseColor: Int, hueShift: Float, alpha: Float): Int {
        val hsv = FloatArray(3)
        android.graphics.Color.colorToHSV(baseColor, hsv)
        hsv[0] = (hsv[0] + hueShift * 360) % 360
        hsv[1] = hsv[1] * 0.3f // Much more subtle saturation
        hsv[2] = hsv[2] * 0.9f // Slightly reduce brightness
        val adjustedColor = android.graphics.Color.HSVToColor(hsv)
        return adjustColorAlpha(adjustedColor, alpha)
    }
}