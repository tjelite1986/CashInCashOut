package com.example.budgetapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.budgetapp.databinding.ActivityMainBinding
import com.example.budgetapp.fragments.BudgetFragment
import com.example.budgetapp.fragments.TransactionsFragment
import com.example.budgetapp.fragments.OverviewFragment
import com.example.budgetapp.fragments.SettingsFragment
import com.example.budgetapp.fragments.LoansFragment
import com.example.budgetapp.fragments.SimpleStatisticsFragment
import com.example.budgetapp.fragments.MoreFragment
import com.example.budgetapp.services.BudgetRolloverWorker
import android.view.MenuItem
import com.example.budgetapp.services.NotificationService
import com.example.budgetapp.receivers.AlarmManagerHelper
import com.example.budgetapp.utils.NotificationSettingsManager
import com.example.budgetapp.utils.ThemeManager
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var notificationSettingsManager: NotificationSettingsManager
    private lateinit var themeManager: ThemeManager
    private lateinit var notificationService: NotificationService
    private lateinit var alarmManagerHelper: AlarmManagerHelper
    
    private val notificationPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            notificationSettingsManager.enableDefaultNotifications()
            showNotificationGrantedSnackbar()
        } else {
            showNotificationDeniedSnackbar()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Initialize theme manager and apply theme before calling super.onCreate
        themeManager = ThemeManager.getInstance(this)
        themeManager.applyTheme()
        
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeServices()
        initializeTheme()
        initializeNotifications()
        setupToolbar()
        setupBottomNavigation()
        handleNavigationIntent()
        
        // Show overview fragment by default
        if (savedInstanceState == null) {
            showFragment(OverviewFragment())
        }
    }
    
    private fun initializeServices() {
        notificationService = NotificationService(this)
        alarmManagerHelper = AlarmManagerHelper(this)
        
        // Uppdatera app-användningstid när appen öppnas
        notificationService.updateAppUsage()
        
        // Schemalägg alla påminnelser (säkert att göra varje gång)
        alarmManagerHelper.scheduleAllReminders()
    }
    
    override fun onResume() {
        super.onResume()
        // Uppdatera app-användningstid när appen kommer i förgrunden
        if (::notificationService.isInitialized) {
            notificationService.updateAppUsage()
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        // Clean up resources to prevent memory leaks
        if (::notificationService.isInitialized) {
            // Any cleanup needed for notification service
        }
    }
    
    private fun initializeTheme() {
        // Apply accent color to bottom navigation and other UI elements
        updateThemeColors()
        
        // Set up theme change listener
        themeManager.addThemeChangeListener(object : ThemeManager.ThemeChangeListener {
            override fun onThemeChanged(settings: com.example.budgetapp.data.ThemeSettings) {
                // Update theme colors immediately
                runOnUiThread {
                    updateThemeColors()
                }
            }
        })
    }
    
    private fun updateThemeColors() {
        val accentColor = themeManager.getAccentColorInt()
        
        // Update bottom navigation colors
        binding.bottomNavigation.itemIconTintList = android.content.res.ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_checked),
                intArrayOf()
            ),
            intArrayOf(
                accentColor,
                getColor(android.R.color.darker_gray)
            )
        )
        
        binding.bottomNavigation.itemTextColor = android.content.res.ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_checked),
                intArrayOf()
            ),
            intArrayOf(
                accentColor,
                getColor(android.R.color.darker_gray)
            )
        )
    }
    
    private fun setupToolbar() {
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            handleToolbarMenuClick(menuItem)
        }
    }
    
    private fun handleToolbarMenuClick(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                showFragment(SettingsFragment())
                true
            }
            R.id.action_statistics -> {
                showFragment(SimpleStatisticsFragment())
                true
            }
            R.id.action_store_management -> {
                showFragment(MoreFragment())
                true
            }
            R.id.action_export -> {
                showExportOptions()
                true
            }
            R.id.action_backup -> {
                showBackupOptions()
                true
            }
            R.id.action_about -> {
                showAboutDialog()
                true
            }
            else -> false
        }
    }
    
    private fun showExportOptions() {
        Snackbar.make(
            binding.root,
            "Export functionality - coming soon!",
            Snackbar.LENGTH_SHORT
        ).show()
    }
    
    private fun showBackupOptions() {
        Snackbar.make(
            binding.root,
            "Backup functionality - coming soon!",
            Snackbar.LENGTH_SHORT
        ).show()
    }
    
    private fun showAboutDialog() {
        val dialog = androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("About CashInCashOut")
            .setMessage("CashInCashOut v1.1\n\nAdvanced Budget Management App\nwith AI-powered Analytics\n\n© 2024")
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .create()
        dialog.show()
    }
    
    
    private fun initializeNotifications() {
        notificationSettingsManager = NotificationSettingsManager(this)
        requestNotificationPermissionIfNeeded()
        
        // Initialize budget rollover scheduling
        BudgetRolloverWorker.scheduleRolloverCheck(this)
    }
    
    private fun requestNotificationPermissionIfNeeded() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED -> {
                    // Permission already granted, initialize notifications
                    notificationSettingsManager.initializeNotifications()
                }
                else -> {
                    // Request permission
                    notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        } else {
            // For Android 12 and below, permission is granted by default
            notificationSettingsManager.initializeNotifications()
        }
    }
    
    private fun handleNavigationIntent() {
        // Check if we were launched by a notification
        intent?.getStringExtra("navigate_to")?.let { destination ->
            when (destination) {
                "budget" -> {
                    binding.bottomNavigation.selectedItemId = R.id.nav_budget
                    showFragment(BudgetFragment())
                }
                "overview" -> {
                    binding.bottomNavigation.selectedItemId = R.id.nav_overview
                    showFragment(OverviewFragment())
                }
            }
        }
    }
    
    private fun showNotificationGrantedSnackbar() {
        Snackbar.make(
            binding.root,
            "Notifikationer aktiverade! Du kommer att få budgetvarningar och sammanfattningar.",
            Snackbar.LENGTH_LONG
        ).show()
    }
    
    private fun showNotificationDeniedSnackbar() {
        Snackbar.make(
            binding.root,
            "Notifikationer inaktiverade. Du kan aktivera dem senare i inställningarna.",
            Snackbar.LENGTH_LONG
        ).setAction("Inställningar") {
            // Navigate to app settings
            val intent = android.content.Intent().apply {
                action = android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                data = android.net.Uri.fromParts("package", packageName, null)
            }
            startActivity(intent)
        }.show()
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            try {
                when (item.itemId) {
                    R.id.nav_overview -> {
                        showFragment(OverviewFragment())
                        true
                    }
                    R.id.nav_transactions -> {
                        showFragment(TransactionsFragment())
                        true
                    }
                    R.id.nav_loans -> {
                        showFragment(LoansFragment())
                        true
                    }
                    R.id.nav_budget -> {
                        showFragment(BudgetFragment())
                        true
                    }
                    R.id.nav_more -> {
                        showFragment(MoreFragment())
                        true
                    }
                    else -> false
                }
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }
        
        // Set default selection
        binding.bottomNavigation.selectedItemId = R.id.nav_overview
    }

    private fun showFragment(fragment: androidx.fragment.app.Fragment) {
        try {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, fragment)
                .commitAllowingStateLoss()
        } catch (e: Exception) {
            e.printStackTrace()
            // Fallback to overview fragment if there's an error
            if (fragment !is OverviewFragment) {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment, OverviewFragment())
                    .commitAllowingStateLoss()
            }
        }
    }
}