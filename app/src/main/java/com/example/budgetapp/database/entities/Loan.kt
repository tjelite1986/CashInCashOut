package com.example.budgetapp.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "loans")
data class Loan(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val amount: Double,
    val description: String? = null,
    val personName: String, // Person som lånat till/från
    val type: LoanType, // BORROWED eller LENT
    val interestRate: Double = 0.0, // Ränta i procent
    val dueDate: Long? = null, // Förfallodatum
    val isPaidBack: Boolean = false, // Om lånet är återbetalt
    val paidBackDate: Long? = null, // När lånet återbetalades
    val paidBackAmount: Double = 0.0, // Återbetalt belopp
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

enum class LoanType {
    BORROWED, // Lånat från någon (skuld)
    LENT;     // Lånat ut till någon (fordran)
    
    companion object {
        fun fromString(value: String): LoanType {
            return when (value) {
                "BORROWED" -> BORROWED
                "LENT" -> LENT
                else -> BORROWED
            }
        }
    }
    
    override fun toString(): String {
        return name
    }
}

class LoanTypeConverter {
    @TypeConverter
    fun fromLoanType(type: LoanType): String {
        return type.name
    }
    
    @TypeConverter
    fun toLoanType(type: String): LoanType {
        return LoanType.fromString(type)
    }
}