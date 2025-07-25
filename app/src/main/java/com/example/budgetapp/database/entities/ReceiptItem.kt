package com.example.budgetapp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(
    tableName = "receipt_items",
    foreignKeys = [
        ForeignKey(
            entity = Receipt::class,
            parentColumns = ["id"],
            childColumns = ["receiptId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Product::class,
            parentColumns = ["id"],
            childColumns = ["productId"],
            onDelete = ForeignKey.SET_NULL
        )
    ]
)
data class ReceiptItem(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val receiptId: Long,
    val productId: Long? = null, // Koppling till befintlig produkt (kan vara null för nya produkter)
    val productName: String, // Produktnamn som visades på kvittot
    val quantity: Double,
    val unitPrice: Double,
    val totalPrice: Double,
    val unit: String = "st", // st, kg, g, l, ml, etc.
    val productSize: String? = null, // Produktstorlek/volym (t.ex. 500ml, 1kg)
    val discount: Double = 0.0,
    val hasDeposit: Boolean = false, // Om produkten har pant
    val depositAmount: Double = 0.0, // Pantsumma per enhet
    val storeName: String? = null, // Butiksnamn från kvittot
    val storeChain: String? = null, // Butikskedja (ICA, Coop, etc.)
    val storeCity: String? = null, // Stad där butiken finns
    val createdAt: Long = System.currentTimeMillis()
)