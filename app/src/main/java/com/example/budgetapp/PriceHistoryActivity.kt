package com.example.budgetapp

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.budgetapp.database.BudgetDatabase
import com.example.budgetapp.database.entities.Product
import com.example.budgetapp.databinding.ActivityPriceHistoryBinding
import com.example.budgetapp.repository.ShoppingListRepository
import com.example.budgetapp.viewmodel.ShoppingListViewModel
import com.example.budgetapp.viewmodel.ShoppingListViewModelFactory
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PriceHistoryActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityPriceHistoryBinding
    private lateinit var database: BudgetDatabase
    private var productId: Long = -1
    private var storeId: Long? = null
    private var currentProduct: Product? = null
    
    private val viewModel: ShoppingListViewModel by viewModels {
        val database = BudgetDatabase.getDatabase(this)
        val repository = ShoppingListRepository(
            database.shoppingListDao(),
            database.shoppingListItemDao(),
            database.productDao(),
            database.storeDao()
        )
        ShoppingListViewModelFactory(repository)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPriceHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        database = BudgetDatabase.getDatabase(this)
        productId = intent.getLongExtra("product_id", -1)
        storeId = intent.getLongExtra("store_id", -1).let { if (it == -1L) null else it }
        
        if (productId == -1L) {
            finish()
            return
        }
        
        setupToolbar()
        setupChart()
        loadData()
    }
    
    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "Prishistorik"
        }
        
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }
    
    private fun setupChart() {
        with(binding.priceChart) {
            description.isEnabled = false
            setTouchEnabled(true)
            isDragEnabled = true
            setScaleEnabled(true)
            setPinchZoom(true)
            setDrawGridBackground(false)
            
            // X-axis setup
            xAxis.apply {
                position = XAxis.XAxisPosition.BOTTOM
                setDrawGridLines(true)
                granularity = 1f
                valueFormatter = object : ValueFormatter() {
                    private val dateFormat = SimpleDateFormat("MMM dd", Locale("sv", "SE"))
                    override fun getFormattedValue(value: Float): String {
                        return dateFormat.format(Date(value.toLong()))
                    }
                }
            }
            
            // Y-axis setup
            axisLeft.apply {
                setDrawGridLines(true)
                valueFormatter = object : ValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        return "${value.toInt()} kr"
                    }
                }
            }
            
            axisRight.isEnabled = false
            
            legend.isEnabled = true
            
            setNoDataText("Ingen prishistorik tillgänglig")
        }
    }
    
    private fun loadData() {
        lifecycleScope.launch {
            // Load product info
            currentProduct = database.productDao().getProductById(productId)
            currentProduct?.let { product ->
                binding.textProductName.text = product.name
                binding.textProductBrand.text = product.productName ?: ""
                supportActionBar?.title = "Prishistorik - ${product.name}"
            }
        }
        
        // Load price history data using LiveData (outside the coroutine)
        val priceHistoryLiveData = if (storeId != null) {
            database.priceHistoryDao().getPriceHistoryForProductAtStore(productId, storeId!!)
        } else {
            database.priceHistoryDao().getPriceHistoryForProduct(productId)
        }
        
        priceHistoryLiveData.observe(this) { priceHistoryData ->
            if (priceHistoryData.isNotEmpty()) {
                displayPriceChart(priceHistoryData)
                
                // Show statistics
                val minPrice = priceHistoryData.minOf { it.price }
                val maxPrice = priceHistoryData.maxOf { it.price }
                val avgPrice = priceHistoryData.map { it.price }.average()
                val latestPrice = priceHistoryData.maxByOrNull { it.recordedAt }?.price ?: 0.0
                
                binding.textMinPrice.text = "%.2f kr".format(minPrice)
                binding.textMaxPrice.text = "%.2f kr".format(maxPrice)
                binding.textAvgPrice.text = "%.2f kr".format(avgPrice)
                binding.textLatestPrice.text = "%.2f kr".format(latestPrice)
                
                binding.layoutStatistics.visibility = android.view.View.VISIBLE
            } else {
                binding.layoutStatistics.visibility = android.view.View.GONE
            }
        }
    }
    
    private fun displayPriceChart(priceHistory: List<com.example.budgetapp.database.entities.PriceHistory>) {
        if (priceHistory.isEmpty()) return
        
        // Group by store if showing all stores
        val storeGroups = if (storeId == null) {
            priceHistory.groupBy { it.storeId }
        } else {
            mapOf(storeId!! to priceHistory)
        }
        
        val dataSets = mutableListOf<LineDataSet>()
        val colors = listOf(
            Color.BLUE, Color.RED, Color.GREEN, Color.MAGENTA, 
            Color.CYAN, Color.YELLOW, Color.GRAY, Color.BLACK
        )
        
        storeGroups.entries.forEachIndexed { index, (currentStoreId, historyForStore) ->
            val entries = historyForStore.map { history ->
                Entry(history.recordedAt.toFloat(), history.price.toFloat())
            }.sortedBy { it.x }
            
            if (entries.isNotEmpty()) {
                lifecycleScope.launch {
                    val storeName = database.storeDao().getStoreById(currentStoreId)?.name ?: "Okänd butik"
                    
                    val dataSet = LineDataSet(entries, storeName).apply {
                        color = colors[index % colors.size]
                        setCircleColor(colors[index % colors.size])
                        lineWidth = 2f
                        circleRadius = 4f
                        setDrawValues(false)
                        mode = LineDataSet.Mode.CUBIC_BEZIER
                        cubicIntensity = 0.2f
                    }
                    
                    dataSets.add(dataSet)
                    
                    // Update chart when all datasets are ready
                    if (dataSets.size == storeGroups.size) {
                        val lineData = LineData(dataSets as List<com.github.mikephil.charting.interfaces.datasets.ILineDataSet>)
                        binding.priceChart.data = lineData
                        binding.priceChart.invalidate()
                    }
                }
            }
        }
    }
    
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}