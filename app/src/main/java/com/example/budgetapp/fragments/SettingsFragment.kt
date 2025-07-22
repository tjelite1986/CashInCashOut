package com.example.budgetapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
// BuildConfig import will be available at runtime
import com.example.budgetapp.data.BackupManager
import com.example.budgetapp.data.TransactionExportManager
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.databinding.FragmentSettingsBinding
import kotlinx.coroutines.launch

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var backupManager: BackupManager
    private lateinit var exportManager: TransactionExportManager
    
    // Launcher for data export
    private val exportLauncher = registerForActivityResult(ActivityResultContracts.CreateDocument("application/json")) { uri ->
        uri?.let { 
            lifecycleScope.launch {
                backupManager.exportToFile(it).fold(
                    onSuccess = { message ->
                        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
                    },
                    onFailure = { error ->
                        Toast.makeText(requireContext(), "Export misslyckades: ${error.message}", Toast.LENGTH_LONG).show()
                    }
                )
            }
        }
    }
    
    // Launcher for data import
    private val importLauncher = registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri ->
        uri?.let { 
            showImportConfirmationDialog {
                lifecycleScope.launch {
                    backupManager.importFromFile(it).fold(
                        onSuccess = { message ->
                            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
                        },
                        onFailure = { error ->
                            Toast.makeText(requireContext(), "Import misslyckades: ${error.message}", Toast.LENGTH_LONG).show()
                        }
                    )
                }
            }
        }
    }

    // Launcher for CSV export
    private val csvExportLauncher = registerForActivityResult(ActivityResultContracts.CreateDocument("text/csv")) { uri ->
        uri?.let { 
            showExportOptionsDialog { includeExpenses, includeIncomes, includeLoans ->
                lifecycleScope.launch {
                    // CSV export is temporarily disabled - show message
                    Toast.makeText(requireContext(), "CSV-export kommer snart!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

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
            initializeManagers()
            setupUI()
            setupClickListeners()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(requireContext(), "Fel vid initialisering av inställningar", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun initializeManagers() {
        val database = BudgetDatabase.getDatabase(requireContext())
        backupManager = BackupManager(requireContext(), database)
        // Export manager temporarily disabled for stability
        // exportManager = TransactionExportManager(requireContext(), database)
    }

    private fun setupUI() {
        // Set app version
        binding.textAppVersion.text = "1.1-debug"
        
        // Set default values
        binding.textCurrency.text = "SEK"
        binding.textTheme.text = "Systemets val"
        binding.textLanguage.text = "Svenska"
        binding.textBudgetPeriod.text = "Månadsvis"
        binding.textWarningThreshold.text = "80%"
        
        // Set switch states
        binding.switchNotifications.isChecked = true
        binding.switchBudgetAlerts.isChecked = true
        binding.switchMonthlySummary.isChecked = false
        binding.switchRoundNumbers.isChecked = false
    }
    
    private fun setupClickListeners() {
        // General Settings
        binding.layoutCurrency.setOnClickListener { showCurrencyDialog() }
        binding.layoutTheme.setOnClickListener { showThemeDialog() }
        binding.layoutLanguage.setOnClickListener { showLanguageDialog() }
        
        // Budget Settings
        binding.layoutBudgetPeriod.setOnClickListener { showBudgetPeriodDialog() }
        binding.layoutWarningThreshold.setOnClickListener { showWarningThresholdDialog() }
        
        // Switch listeners
        binding.switchNotifications.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "Aviseringar aktiverade" else "Aviseringar inaktiverade"
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
        
        binding.switchBudgetAlerts.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "Budgetvarningar aktiverade" else "Budgetvarningar inaktiverade"
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
        
        binding.switchMonthlySummary.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "Månadssammanfattning aktiverad" else "Månadssammanfattning inaktiverad"
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
        
        binding.switchRoundNumbers.setOnCheckedChangeListener { _, isChecked ->
            val message = if (isChecked) "Avrundning aktiverad" else "Avrundning inaktiverad"
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        }
        
        // Data & Security
        binding.layoutExportData.setOnClickListener { exportData() }
        binding.layoutImportData.setOnClickListener { importData() }
        binding.layoutExportCsv.setOnClickListener { exportCsv() }
        binding.layoutClearData.setOnClickListener { showClearDataDialog() }
        
        // About
        binding.layoutPrivacyPolicy.setOnClickListener { showPrivacyPolicy() }
    }
    
    private fun showCurrencyDialog() {
        val currencies = arrayOf("SEK", "EUR", "USD", "NOK", "DKK", "GBP", "CAD", "AUD", "JPY")
        val currentCurrency = binding.textCurrency.text.toString()
        val currentIndex = currencies.indexOf(currentCurrency)
        
        AlertDialog.Builder(requireContext())
            .setTitle("Välj valuta")
            .setSingleChoiceItems(currencies, currentIndex) { dialog, which ->
                binding.textCurrency.text = currencies[which]
                Toast.makeText(requireContext(), "Valuta ändrad till ${currencies[which]}", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .setNegativeButton("Avbryt", null)
            .show()
    }
    
    private fun showThemeDialog() {
        val themes = arrayOf("Ljust tema", "Mörkt tema", "Systemets val")
        val currentTheme = binding.textTheme.text.toString()
        val currentIndex = themes.indexOf(currentTheme)
        
        AlertDialog.Builder(requireContext())
            .setTitle("Välj tema")
            .setSingleChoiceItems(themes, currentIndex) { dialog, which ->
                binding.textTheme.text = themes[which]
                Toast.makeText(requireContext(), "Tema ändrat till ${themes[which]}", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .setNegativeButton("Avbryt", null)
            .show()
    }
    
    private fun showLanguageDialog() {
        val languages = arrayOf("Svenska", "English", "Norsk", "Dansk")
        val currentLanguage = binding.textLanguage.text.toString()
        val currentIndex = languages.indexOf(currentLanguage)
        
        AlertDialog.Builder(requireContext())
            .setTitle("Välj språk")
            .setSingleChoiceItems(languages, currentIndex) { dialog, which ->
                binding.textLanguage.text = languages[which]
                Toast.makeText(requireContext(), "Språk ändrat till ${languages[which]}", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .setNegativeButton("Avbryt", null)
            .show()
    }
    
    private fun showBudgetPeriodDialog() {
        val periods = arrayOf("Veckovis", "Månadsvis", "Kvartalsvis", "Årligen")
        val currentPeriod = binding.textBudgetPeriod.text.toString()
        val currentIndex = periods.indexOf(currentPeriod)
        
        AlertDialog.Builder(requireContext())
            .setTitle("Välj budgetperiod")
            .setSingleChoiceItems(periods, currentIndex) { dialog, which ->
                binding.textBudgetPeriod.text = periods[which]
                Toast.makeText(requireContext(), "Budgetperiod ändrad till ${periods[which]}", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .setNegativeButton("Avbryt", null)
            .show()
    }
    
    private fun showWarningThresholdDialog() {
        val thresholds = arrayOf("50%", "60%", "70%", "80%", "90%", "95%")
        val currentThreshold = binding.textWarningThreshold.text.toString()
        val currentIndex = thresholds.indexOf(currentThreshold)
        
        AlertDialog.Builder(requireContext())
            .setTitle("Välj varningsgräns")
            .setMessage("Varna när denna procent av budgeten är använd")
            .setSingleChoiceItems(thresholds, currentIndex) { dialog, which ->
                binding.textWarningThreshold.text = thresholds[which]
                Toast.makeText(requireContext(), "Varningsgräns ändrad till ${thresholds[which]}", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }
            .setNegativeButton("Avbryt", null)
            .show()
    }
    
    private fun exportData() {
        val fileName = backupManager.generateBackupFileName()
        exportLauncher.launch(fileName)
    }
    
    private fun importData() {
        importLauncher.launch(arrayOf("application/json", "text/plain"))
    }

    private fun exportCsv() {
        csvExportLauncher.launch("budget_export.csv")
    }
    
    private fun showClearDataDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("⚠️ Rensa all data")
            .setMessage("Detta kommer att radera ALLA dina sparade transaktioner, budgetar, lån och inställningar permanent.\n\nDenna åtgärd kan INTE ångras!")
            .setPositiveButton("Ja, radera allt") { _, _ ->
                showFinalClearDataConfirmation()
            }
            .setNegativeButton("Avbryt", null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }
    
    private fun showFinalClearDataConfirmation() {
        AlertDialog.Builder(requireContext())
            .setTitle("Sista varningen!")
            .setMessage("Är du helt säker? All data kommer att raderas permanent.")
            .setPositiveButton("JA, RADERA ALLT") { _, _ ->
                clearAllData()
            }
            .setNegativeButton("Nej, behåll data", null)
            .show()
    }
    
    private fun clearAllData() {
        // Clear data functionality temporarily disabled for safety
        Toast.makeText(requireContext(), "Rensa data-funktionen är tillfälligt inaktiverad för säkerhet", Toast.LENGTH_LONG).show()
    }
    
    private fun showPrivacyPolicy() {
        AlertDialog.Builder(requireContext())
            .setTitle("Integritetspolicy")
            .setMessage("Denna app sparar all data lokalt på din enhet. Ingen data skickas till externa servrar.\n\nAll information som du anger (transaktioner, budgetar, etc.) förblir privat och lagras endast på din enhet.\n\nVi samlar inte in, delar eller säljer din personliga information.")
            .setPositiveButton("OK", null)
            .show()
    }
    
    private fun showExportOptionsDialog(onOptionsSelected: (Boolean, Boolean, Boolean) -> Unit) {
        val options = arrayOf("Utgifter", "Inkomster", "Lån")
        val checkedItems = booleanArrayOf(true, true, true)

        AlertDialog.Builder(requireContext())
            .setTitle("Välj data att exportera")
            .setMultiChoiceItems(options, checkedItems) { _, which, isChecked ->
                checkedItems[which] = isChecked
            }
            .setPositiveButton("Exportera") { _, _ ->
                onOptionsSelected(checkedItems[0], checkedItems[1], checkedItems[2])
            }
            .setNegativeButton("Avbryt", null)
            .show()
    }
    
    private fun showImportConfirmationDialog(onConfirmed: () -> Unit) {
        AlertDialog.Builder(requireContext())
            .setTitle("Importera data")
            .setMessage("Detta kommer att lägga till all data från backup-filen till din befintliga data.\n\nVill du fortsätta?")
            .setPositiveButton("Ja, importera") { _, _ -> onConfirmed() }
            .setNegativeButton("Avbryt", null)
            .setIcon(android.R.drawable.ic_dialog_info)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}