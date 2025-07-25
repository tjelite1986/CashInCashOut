package com.example.budgetapp.database.entities;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b\u00a8\u0006\t"}, d2 = {"Lcom/example/budgetapp/database/entities/ForecastMethod;", "", "(Ljava/lang/String;I)V", "LINEAR_REGRESSION", "MOVING_AVERAGE", "EXPONENTIAL_SMOOTHING", "SEASONAL_DECOMPOSITION", "MACHINE_LEARNING", "HYBRID", "CashInCashOut_release"})
public enum ForecastMethod {
    /*public static final*/ LINEAR_REGRESSION /* = new LINEAR_REGRESSION() */,
    /*public static final*/ MOVING_AVERAGE /* = new MOVING_AVERAGE() */,
    /*public static final*/ EXPONENTIAL_SMOOTHING /* = new EXPONENTIAL_SMOOTHING() */,
    /*public static final*/ SEASONAL_DECOMPOSITION /* = new SEASONAL_DECOMPOSITION() */,
    /*public static final*/ MACHINE_LEARNING /* = new MACHINE_LEARNING() */,
    /*public static final*/ HYBRID /* = new HYBRID() */;
    
    ForecastMethod() {
    }
    
    @org.jetbrains.annotations.NotNull
    public static kotlin.enums.EnumEntries<com.example.budgetapp.database.entities.ForecastMethod> getEntries() {
        return null;
    }
}