package com.example.budgetapp.database.entities;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007\u00a8\u0006\b"}, d2 = {"Lcom/example/budgetapp/database/entities/LoanTypeConverter;", "", "()V", "fromLoanType", "", "type", "Lcom/example/budgetapp/database/entities/LoanType;", "toLoanType", "CashInCashOut_release"})
public final class LoanTypeConverter {
    
    public LoanTypeConverter() {
        super();
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final java.lang.String fromLoanType(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.LoanType type) {
        return null;
    }
    
    @androidx.room.TypeConverter
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.database.entities.LoanType toLoanType(@org.jetbrains.annotations.NotNull
    java.lang.String type) {
        return null;
    }
}