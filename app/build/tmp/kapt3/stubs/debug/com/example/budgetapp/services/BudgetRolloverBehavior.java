package com.example.budgetapp.services;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/example/budgetapp/services/BudgetRolloverBehavior;", "", "(Ljava/lang/String;I)V", "ADD_TO_ORIGINAL", "REPLACE_WITH_ROLLOVER", "WEIGHTED_AVERAGE", "CashInCashOut_debug"})
public enum BudgetRolloverBehavior {
    /*public static final*/ ADD_TO_ORIGINAL /* = new ADD_TO_ORIGINAL() */,
    /*public static final*/ REPLACE_WITH_ROLLOVER /* = new REPLACE_WITH_ROLLOVER() */,
    /*public static final*/ WEIGHTED_AVERAGE /* = new WEIGHTED_AVERAGE() */;
    
    BudgetRolloverBehavior() {
    }
    
    @org.jetbrains.annotations.NotNull
    public static kotlin.enums.EnumEntries<com.example.budgetapp.services.BudgetRolloverBehavior> getEntries() {
        return null;
    }
}