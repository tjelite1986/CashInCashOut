package com.example.budgetapp.database.entities;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/example/budgetapp/database/entities/ReminderType;", "", "(Ljava/lang/String;I)V", "DAILY_IF_NOT_OPENED", "HOURS_24_FROM_LAST_OPEN", "DAILY_ALWAYS", "CashInCashOut_release"})
public enum ReminderType {
    /*public static final*/ DAILY_IF_NOT_OPENED /* = new DAILY_IF_NOT_OPENED() */,
    /*public static final*/ HOURS_24_FROM_LAST_OPEN /* = new HOURS_24_FROM_LAST_OPEN() */,
    /*public static final*/ DAILY_ALWAYS /* = new DAILY_ALWAYS() */;
    
    ReminderType() {
    }
    
    @org.jetbrains.annotations.NotNull
    public static kotlin.enums.EnumEntries<com.example.budgetapp.database.entities.ReminderType> getEntries() {
        return null;
    }
}