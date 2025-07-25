package com.example.budgetapp

import android.app.TimePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import androidx.activity.result.contract.ActivityResultContracts
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.ReminderSettings
import com.example.budgetapp.receivers.AlarmManagerHelper
import com.example.budgetapp.services.NotificationService
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers

class NotificationSettingsActivity : AppCompatActivity() {
    
    private val database by lazy { BudgetDatabase.getDatabase(this) }
    private val alarmManagerHelper by lazy { AlarmManagerHelper(this) }
    private val notificationService by lazy { NotificationService(this) }
    
    private lateinit var switchDailyIfNotOpened: Switch
    private lateinit var switch24Hours: Switch
    private lateinit var switchDailyAlways: Switch
    private lateinit var layoutDailyIfNotOpenedTime: LinearLayout
    private lateinit var layoutDailyAlwaysTime: LinearLayout
    private lateinit var tvDailyIfNotOpenedTime: TextView
    private lateinit var tvDailyAlwaysTime: TextView
    private lateinit var btnTestNotification: Button
    private lateinit var btnSelectTransactions: Button
    private lateinit var tvSelectedCount: TextView
    
    private var dailyIfNotOpenedSettings: ReminderSettings? = null
    private var hours24Settings: ReminderSettings? = null
    private var dailyAlwaysSettings: ReminderSettings? = null
    
    companion object {
        private const val NOTIFICATION_PERMISSION_REQUEST_CODE = 1001
        private const val TRANSACTION_SELECTOR_REQUEST_CODE = 1002
    }
    
    private val transactionSelectorLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            updateSelectedTransactionCount()
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_settings)
        
        initViews()
        setupClickListeners()
        loadSettings()
        checkNotificationPermission()
    }
    
    private fun initViews() {
        switchDailyIfNotOpened = findViewById(R.id.switchDailyIfNotOpened)
        switch24Hours = findViewById(R.id.switch24Hours)
        switchDailyAlways = findViewById(R.id.switchDailyAlways)
        layoutDailyIfNotOpenedTime = findViewById(R.id.layoutDailyIfNotOpenedTime)
        layoutDailyAlwaysTime = findViewById(R.id.layoutDailyAlwaysTime)
        tvDailyIfNotOpenedTime = findViewById(R.id.tvDailyIfNotOpenedTime)
        tvDailyAlwaysTime = findViewById(R.id.tvDailyAlwaysTime)
        btnTestNotification = findViewById(R.id.btnTestNotification)
        btnSelectTransactions = findViewById(R.id.btnSelectTransactions)
        tvSelectedCount = findViewById(R.id.tvSelectedCount)
    }
    
    private fun setupClickListeners() {
        findViewById<android.widget.ImageView>(R.id.btnBack)?.setOnClickListener {
            finish()
        }
        
        switchDailyIfNotOpened.setOnCheckedChangeListener { _, isChecked ->
            layoutDailyIfNotOpenedTime.visibility = if (isChecked) {
                android.view.View.VISIBLE
            } else {
                android.view.View.GONE
            }
            
            updateReminderSetting("DAILY_IF_NOT_OPENED", isChecked)
        }
        
        switch24Hours.setOnCheckedChangeListener { _, isChecked ->
            updateReminderSetting("HOURS_24_FROM_LAST_OPEN", isChecked)
        }
        
        switchDailyAlways.setOnCheckedChangeListener { _, isChecked ->
            layoutDailyAlwaysTime.visibility = if (isChecked) {
                android.view.View.VISIBLE
            } else {
                android.view.View.GONE
            }
            
            updateReminderSetting("DAILY_ALWAYS", isChecked)
        }
        
        tvDailyIfNotOpenedTime.setOnClickListener {
            showTimePicker(tvDailyIfNotOpenedTime.text.toString()) { time ->
                tvDailyIfNotOpenedTime.text = time
                updateReminderTime("DAILY_IF_NOT_OPENED", time)
            }
        }
        
        tvDailyAlwaysTime.setOnClickListener {
            showTimePicker(tvDailyAlwaysTime.text.toString()) { time ->
                tvDailyAlwaysTime.text = time
                updateReminderTime("DAILY_ALWAYS", time)
            }
        }
        
        btnTestNotification.setOnClickListener {
            notificationService.showReminderNotification(
                9999,
                "Test-notifikation",
                "Detta är en test för att se att notifikationer fungerar korrekt!"
            )
            Toast.makeText(this, "Test-notifikation skickad!", Toast.LENGTH_SHORT).show()
        }
        
        btnSelectTransactions.setOnClickListener {
            val intent = Intent(this, TransactionSelectorActivity::class.java)
            transactionSelectorLauncher.launch(intent)
        }
    }
    
    private fun loadSettings() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                dailyIfNotOpenedSettings = database.reminderSettingsDao()
                    .getReminderByType("DAILY_IF_NOT_OPENED")
                hours24Settings = database.reminderSettingsDao()
                    .getReminderByType("HOURS_24_FROM_LAST_OPEN")
                dailyAlwaysSettings = database.reminderSettingsDao()
                    .getReminderByType("DAILY_ALWAYS")
                
                runOnUiThread {
                    updateUI()
                    updateSelectedTransactionCount()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                runOnUiThread {
                    Toast.makeText(this@NotificationSettingsActivity, 
                        "Fel vid laddning av inställningar", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    
    private fun updateUI() {
        dailyIfNotOpenedSettings?.let { settings ->
            switchDailyIfNotOpened.isChecked = settings.isEnabled
            tvDailyIfNotOpenedTime.text = settings.alertTime
            layoutDailyIfNotOpenedTime.visibility = if (settings.isEnabled) {
                android.view.View.VISIBLE
            } else {
                android.view.View.GONE
            }
        }
        
        hours24Settings?.let { settings ->
            switch24Hours.isChecked = settings.isEnabled
        }
        
        dailyAlwaysSettings?.let { settings ->
            switchDailyAlways.isChecked = settings.isEnabled
            tvDailyAlwaysTime.text = settings.alertTime
            layoutDailyAlwaysTime.visibility = if (settings.isEnabled) {
                android.view.View.VISIBLE
            } else {
                android.view.View.GONE
            }
        }
    }
    
    private fun updateReminderSetting(type: String, isEnabled: Boolean) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val existingSetting = database.reminderSettingsDao().getReminderByType(type)
                
                if (existingSetting != null) {
                    val updatedSetting = existingSetting.copy(
                        isEnabled = isEnabled,
                        updatedAt = System.currentTimeMillis()
                    )
                    database.reminderSettingsDao().updateReminder(updatedSetting)
                } else {
                    // Skapa ny inställning om den inte finns
                    val newSetting = ReminderSettings(
                        reminderType = type,
                        isEnabled = isEnabled,
                        title = getDefaultTitle(type),
                        message = getDefaultMessage(type)
                    )
                    database.reminderSettingsDao().insertReminder(newSetting)
                }
                
                // Uppdatera alarm
                if (isEnabled) {
                    alarmManagerHelper.scheduleAllReminders()
                } else {
                    alarmManagerHelper.cancelReminder(type)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                runOnUiThread {
                    Toast.makeText(this@NotificationSettingsActivity, 
                        "Fel vid uppdatering av inställning", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    
    private fun updateReminderTime(type: String, time: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val existingSetting = database.reminderSettingsDao().getReminderByType(type)
                
                if (existingSetting != null) {
                    val updatedSetting = existingSetting.copy(
                        alertTime = time,
                        updatedAt = System.currentTimeMillis()
                    )
                    database.reminderSettingsDao().updateReminder(updatedSetting)
                    
                    // Uppdatera alarm med ny tid
                    if (existingSetting.isEnabled) {
                        alarmManagerHelper.scheduleAllReminders()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                runOnUiThread {
                    Toast.makeText(this@NotificationSettingsActivity, 
                        "Fel vid uppdatering av tid", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    
    private fun showTimePicker(currentTime: String, onTimeSelected: (String) -> Unit) {
        val timeParts = currentTime.split(":")
        val hour = timeParts[0].toInt()
        val minute = timeParts[1].toInt()
        
        TimePickerDialog(this, { _, selectedHour, selectedMinute ->
            val formattedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
            onTimeSelected(formattedTime)
        }, hour, minute, true).show()
    }
    
    private fun getDefaultTitle(type: String): String {
        return when (type) {
            "DAILY_IF_NOT_OPENED" -> "Daglig påminnelse"
            "HOURS_24_FROM_LAST_OPEN" -> "24-timmars påminnelse"
            "DAILY_ALWAYS" -> "Daglig påminnelse (alltid)"
            else -> "Påminnelse"
        }
    }
    
    private fun getDefaultMessage(type: String): String {
        return when (type) {
            "DAILY_IF_NOT_OPENED" -> "Glöm inte att registrera dina utgifter idag!"
            "HOURS_24_FROM_LAST_OPEN" -> "Det har gått 24 timmar sedan du senast använde appen"
            "DAILY_ALWAYS" -> "Dags att kolla din budget!"
            else -> "Påminnelse från din budget-app"
        }
    }
    
    private fun checkNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                    NOTIFICATION_PERMISSION_REQUEST_CODE
                )
            }
        }
    }
    
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        
        when (requestCode) {
            NOTIFICATION_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Notifikationstillstånd beviljat!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Notifikationstillstånd krävs för påminnelser", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    
    private fun updateSelectedTransactionCount() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val count = database.transactionNotificationDao().getActiveNotificationCount()
                runOnUiThread {
                    tvSelectedCount.text = "$count transaktioner valda"
                }
            } catch (e: Exception) {
                e.printStackTrace()
                runOnUiThread {
                    tvSelectedCount.text = "0 transaktioner valda"
                }
            }
        }
    }
}