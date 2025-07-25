package com.example.budgetapp.database.dao

import androidx.room.*
import com.example.budgetapp.database.entities.Product
import com.example.budgetapp.data.ProductWithPrices
import com.example.budgetapp.data.ProductStoreWithStore
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM products ORDER BY name ASC")
    fun getAllProducts(): Flow<List<Product>>
    
    @Query("SELECT * FROM products WHERE id = :id")
    suspend fun getProductById(id: Long): Product?
    
    @Query("SELECT * FROM products WHERE barcode = :barcode LIMIT 1")
    suspend fun getProductByBarcode(barcode: String): Product?
    
    @Query("SELECT * FROM products WHERE productCategoryId = :productCategoryId ORDER BY name ASC")
    fun getProductsByCategory(productCategoryId: Long): Flow<List<Product>>
    
    @Query("SELECT * FROM products WHERE name LIKE '%' || :searchQuery || '%' OR productName LIKE '%' || :searchQuery || '%' ORDER BY name ASC")
    fun searchProducts(searchQuery: String): Flow<List<Product>>
    
    @Query("""
        SELECT MIN(ps.price) as minPrice, MAX(ps.price) as maxPrice, AVG(ps.price) as avgPrice
        FROM product_stores ps
        WHERE ps.productId = :productId
    """)
    suspend fun getPriceStats(productId: Long): PriceStats?
    
    @Query("""
        SELECT 
            MIN(CASE 
                WHEN ps.hasCampaignPrice THEN COALESCE(ps.campaignPrice, ps.price)
                ELSE ps.price 
            END) as minPrice,
            MAX(CASE 
                WHEN ps.hasCampaignPrice THEN COALESCE(ps.campaignPrice, ps.price)
                ELSE ps.price 
            END) as maxPrice,
            AVG(CASE 
                WHEN ps.hasCampaignPrice THEN COALESCE(ps.campaignPrice, ps.price)
                ELSE ps.price 
            END) as avgPrice,
            p.hasDeposit,
            p.depositAmount
        FROM product_stores ps
        INNER JOIN products p ON ps.productId = p.id
        WHERE ps.productId = :productId
        GROUP BY p.hasDeposit, p.depositAmount
    """)
    suspend fun getPriceStatsWithCampaignAndDeposit(productId: Long): PriceStatsWithDeposit?
    
    @Query("""
        SELECT 
            ps.*,
            s.name as store_name,
            s.address as store_address,
            (CASE 
                WHEN ps.hasCampaignPrice THEN COALESCE(ps.campaignPrice, ps.price)
                ELSE ps.price 
            END) as effective_price,
            p.hasDeposit,
            p.depositAmount
        FROM product_stores ps
        INNER JOIN stores s ON ps.storeId = s.id
        INNER JOIN products p ON ps.productId = p.id
        WHERE ps.productId = :productId
        ORDER BY effective_price ASC
    """)
    suspend fun getProductStoresWithPrices(productId: Long): List<ProductStoreWithPrice>
    
    @Query("""
        SELECT 
            ps.*,
            s.name as store_name,
            s.address as store_address,
            (CASE 
                WHEN ps.hasCampaignPrice THEN COALESCE(ps.campaignPrice, ps.price)
                ELSE ps.price 
            END) as effective_price,
            p.hasDeposit,
            p.depositAmount
        FROM product_stores ps
        INNER JOIN stores s ON ps.storeId = s.id
        INNER JOIN products p ON ps.productId = p.id
        WHERE ps.productId = :productId
        ORDER BY effective_price ASC
        LIMIT 1
    """)
    suspend fun getCheapestStoreForProduct(productId: Long): ProductStoreWithPrice?
    
    data class PriceStats(
        val minPrice: Double?,
        val maxPrice: Double?,
        val avgPrice: Double?
    )
    
    data class PriceStatsWithDeposit(
        val minPrice: Double?,
        val maxPrice: Double?,
        val avgPrice: Double?,
        val hasDeposit: Boolean,
        val depositAmount: Double?
    ) {
        fun getTotalAvgPrice(): Double? {
            return if (avgPrice != null) {
                avgPrice + (if (hasDeposit && depositAmount != null) depositAmount else 0.0)
            } else null
        }
        
        fun getTotalMinPrice(): Double? {
            return if (minPrice != null) {
                minPrice + (if (hasDeposit && depositAmount != null) depositAmount else 0.0)
            } else null
        }
        
        fun getTotalMaxPrice(): Double? {
            return if (maxPrice != null) {
                maxPrice + (if (hasDeposit && depositAmount != null) depositAmount else 0.0)
            } else null
        }
    }
    
    data class ProductStoreWithPrice(
        val id: Long,
        val productId: Long,
        val storeId: Long,
        val price: Double,
        val hasCampaignPrice: Boolean,
        val campaignQuantity: Int?,
        val campaignPrice: Double?,
        val lastSeen: Long,
        val createdAt: Long,
        val updatedAt: Long,
        val store_name: String,
        val store_address: String?,
        val effective_price: Double,
        val hasDeposit: Boolean,
        val depositAmount: Double?
    ) {
        fun getTotalPrice(): Double {
            return effective_price + (if (hasDeposit && depositAmount != null) depositAmount else 0.0)
        }
        
        fun getPriceDisplayText(): String {
            val basePrice = String.format("%.2f kr", getTotalPrice())
            return if (hasCampaignPrice && campaignQuantity != null) {
                "$basePrice (kampanj: $campaignQuantity för ${String.format("%.2f", campaignPrice ?: price)} kr)"
            } else {
                basePrice
            }
        }
    }
    
    @Insert
    suspend fun insertProduct(product: Product): Long
    
    @Update
    suspend fun updateProduct(product: Product)
    
    @Delete
    suspend fun deleteProduct(product: Product)
    
    @Query("DELETE FROM products WHERE id = :id")
    suspend fun deleteProductById(id: Long)
    
    @Query("SELECT COUNT(*) FROM products")
    suspend fun getProductCount(): Int
}