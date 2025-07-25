package com.example.budgetapp.data

import com.example.budgetapp.database.entities.Receipt
import com.example.budgetapp.database.entities.ReceiptItem

data class ReceiptWithItems(
    val receipt: Receipt,
    val items: List<ReceiptItem>
)

data class ReceiptItemInput(
    val productName: String,
    val quantity: Double,
    val unitPrice: Double,
    val unit: String = "st",
    val productSize: String? = null, // Produktstorlek/volym (t.ex. 500ml, 1kg)
    val discount: Double = 0.0,
    val productId: Long? = null, // Koppling till befintlig produkt om vald
    val storeName: String? = null,
    val storeChain: String? = null,
    val storeCity: String? = null,
    val hasDeposit: Boolean = false, // Om varan har pant
    val depositAmount: Double = 0.0 // Pantbelopp per vara
) {
    val totalPrice: Double
        get() = (quantity * (unitPrice + depositAmount)) - discount
}