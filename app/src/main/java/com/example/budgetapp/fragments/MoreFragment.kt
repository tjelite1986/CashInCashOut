package com.example.budgetapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.budgetapp.CategoryManagerActivity
import com.example.budgetapp.ProductCategoryManagerActivity
import com.example.budgetapp.StoreManagerActivity
import com.example.budgetapp.StoreChainManagerActivity
import com.example.budgetapp.ProductListActivity
import com.example.budgetapp.SmartRecommendationsActivity
import com.example.budgetapp.AutomationActivity
import com.example.budgetapp.ThemePreferencesActivity
import com.example.budgetapp.NotificationSettingsActivity
import com.example.budgetapp.BudgetRolloverSettingsActivity
// import com.example.budgetapp.analytics.investments.InvestmentTrackingActivity
// import com.example.budgetapp.analytics.tax.TaxReportingActivity
import com.example.budgetapp.adapters.MoreItemAdapter
import com.example.budgetapp.data.MoreMenuItem
import com.example.budgetapp.databinding.FragmentMoreBinding
import com.example.budgetapp.R

class MoreFragment : Fragment() {
    
    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var moreAdapter: MoreItemAdapter
    
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
        
        setupRecyclerView()
        loadMoreItems()
    }
    
    private fun setupRecyclerView() {
        moreAdapter = MoreItemAdapter { menuItem ->
            handleMenuItemClick(menuItem)
        }
        
        binding.recyclerViewMore.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = moreAdapter
        }
    }
    
    private fun loadMoreItems() {
        val menuItems = listOf(
            // Analytics Section
            MoreMenuItem(
                id = "analytics_header",
                title = "Analytics & Intelligens",
                subtitle = "Avancerade verktyg för finansanalys",
                icon = R.drawable.ic_statistics,
                isHeader = true
            ),
            MoreMenuItem(
                id = "investment_tracking",
                title = "Investeringsanalys",
                subtitle = "Spåra och analysera investeringar (Kommer snart)",
                icon = R.drawable.ic_investment
                // activityClass = InvestmentTrackingActivity::class.java
            ),
            MoreMenuItem(
                id = "tax_reporting",
                title = "Skattedeklaration",
                subtitle = "RUT/ROT avdrag och skatteoptimering (Kommer snart)",
                icon = R.drawable.ic_tax
                // activityClass = TaxReportingActivity::class.java
            ),
            MoreMenuItem(
                id = "smart_automation",
                title = "Smart Automation",
                subtitle = "AI-drivna automatiseringsregler och insikter",
                icon = R.drawable.ic_lightning_bolt,
                activityClass = AutomationActivity::class.java
            ),
            MoreMenuItem(
                id = "smart_recommendations",
                title = "Smarta rekommendationer",
                subtitle = "AI-drivna utgiftstips och förslag",
                icon = R.drawable.ic_lightbulb,
                activityClass = SmartRecommendationsActivity::class.java
            ),
            
            // Management Section
            MoreMenuItem(
                id = "management_header",
                title = "Hantering",
                subtitle = "Administrera kategorier, butiker och produkter",
                icon = R.drawable.ic_settings,
                isHeader = true
            ),
            MoreMenuItem(
                id = "category_manager",
                title = "Kategorier",
                subtitle = "Hantera utgiftskategorier",
                icon = R.drawable.ic_category,
                activityClass = CategoryManagerActivity::class.java
            ),
            MoreMenuItem(
                id = "store_manager",
                title = "Butiker",
                subtitle = "Hantera butiker och butiksinformation",
                icon = R.drawable.ic_store,
                activityClass = StoreManagerActivity::class.java
            ),
            MoreMenuItem(
                id = "store_chain_manager",
                title = "Butikskedjor",
                subtitle = "Hantera butikskedjor",
                icon = R.drawable.ic_business,
                activityClass = StoreChainManagerActivity::class.java
            ),
            MoreMenuItem(
                id = "product_category_manager",
                title = "Produktkategorier",
                subtitle = "Hantera produktkategorier",
                icon = R.drawable.ic_category_default,
                activityClass = ProductCategoryManagerActivity::class.java
            ),
            MoreMenuItem(
                id = "product_manager",
                title = "Produkter",
                subtitle = "Hantera produkter och priser",
                icon = R.drawable.ic_inventory,
                activityClass = ProductListActivity::class.java
            ),
            
            // Personalization Section
            MoreMenuItem(
                id = "personalization_header",
                title = "Personalisering",
                subtitle = "Anpassa utseende och inställningar",
                icon = R.drawable.ic_palette,
                isHeader = true
            ),
            MoreMenuItem(
                id = "theme_preferences",
                title = "Tema & Utseende",
                subtitle = "Anpassa färger, tema och design",
                icon = R.drawable.ic_palette,
                activityClass = ThemePreferencesActivity::class.java
            ),
            MoreMenuItem(
                id = "notification_settings",
                title = "Notifikationer",
                subtitle = "Hantera påminnelser och varningar",
                icon = R.drawable.ic_notifications,
                activityClass = NotificationSettingsActivity::class.java
            ),
            MoreMenuItem(
                id = "budget_rollover_settings",
                title = "Budgetöverföring",
                subtitle = "Inställningar för automatisk budgetöverföring",
                icon = R.drawable.ic_budget,
                activityClass = BudgetRolloverSettingsActivity::class.java
            ),
            
            // Tools & Settings Section
            MoreMenuItem(
                id = "tools_header",
                title = "Verktyg & Inställningar",
                subtitle = "Avancerade verktyg och systeminställningar",
                icon = R.drawable.ic_settings,
                isHeader = true
            ),
            MoreMenuItem(
                id = "backup_restore",
                title = "Säkerhetskopiering",
                subtitle = "Säkerhetskopiera och återställ data",
                icon = R.drawable.ic_backup
                // TODO: Add backup activity when created
            ),
            MoreMenuItem(
                id = "data_export",
                title = "Dataexport",
                subtitle = "Exportera data till olika format",
                icon = R.drawable.ic_export
                // TODO: Add export activity when created
            ),
            MoreMenuItem(
                id = "app_info",
                title = "Om Appen",
                subtitle = "Version, licenser och information",
                icon = R.drawable.ic_info
                // TODO: Add about activity when created
            )
        )
        
        moreAdapter.submitList(menuItems)
    }
    
    private fun handleMenuItemClick(menuItem: MoreMenuItem) {
        if (menuItem.isHeader) return
        
        menuItem.activityClass?.let { activityClass ->
            val intent = Intent(requireContext(), activityClass)
            startActivity(intent)
        }
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}