package com.example.budgetapp.database;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 /2\u00020\u0001:\u0001/B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u000eH&J\b\u0010\u000f\u001a\u00020\u0010H&J\b\u0010\u0011\u001a\u00020\u0012H&J\b\u0010\u0013\u001a\u00020\u0014H&J\b\u0010\u0015\u001a\u00020\u0016H&J\b\u0010\u0017\u001a\u00020\u0018H&J\b\u0010\u0019\u001a\u00020\u001aH&J\b\u0010\u001b\u001a\u00020\u001cH&J\b\u0010\u001d\u001a\u00020\u001eH&J\b\u0010\u001f\u001a\u00020 H&J\b\u0010!\u001a\u00020\"H&J\b\u0010#\u001a\u00020$H&J\b\u0010%\u001a\u00020&H&J\b\u0010\'\u001a\u00020(H&J\b\u0010)\u001a\u00020*H&J\b\u0010+\u001a\u00020,H&J\b\u0010-\u001a\u00020.H&\u00a8\u00060"}, d2 = {"Lcom/example/budgetapp/database/BudgetDatabase;", "Landroidx/room/RoomDatabase;", "()V", "appUsageDao", "Lcom/example/budgetapp/database/dao/AppUsageDao;", "budgetDao", "Lcom/example/budgetapp/database/dao/BudgetDao;", "categoryDao", "Lcom/example/budgetapp/database/dao/CategoryDao;", "expenseDao", "Lcom/example/budgetapp/database/dao/ExpenseDao;", "financialGoalDao", "Lcom/example/budgetapp/database/dao/FinancialGoalDao;", "financialInsightDao", "Lcom/example/budgetapp/database/dao/FinancialInsightDao;", "incomeDao", "Lcom/example/budgetapp/database/dao/IncomeDao;", "loanDao", "Lcom/example/budgetapp/database/dao/LoanDao;", "priceHistoryDao", "Lcom/example/budgetapp/database/dao/PriceHistoryDao;", "productCategoryDao", "Lcom/example/budgetapp/database/dao/ProductCategoryDao;", "productDao", "Lcom/example/budgetapp/database/dao/ProductDao;", "productStoreDao", "Lcom/example/budgetapp/database/dao/ProductStoreDao;", "receiptDao", "Lcom/example/budgetapp/database/dao/ReceiptDao;", "receiptItemDao", "Lcom/example/budgetapp/database/dao/ReceiptItemDao;", "reminderSettingsDao", "Lcom/example/budgetapp/database/dao/ReminderSettingsDao;", "shoppingListDao", "Lcom/example/budgetapp/database/dao/ShoppingListDao;", "shoppingListItemDao", "Lcom/example/budgetapp/database/dao/ShoppingListItemDao;", "spendingForecastDao", "Lcom/example/budgetapp/database/dao/SpendingForecastDao;", "spendingPatternDao", "Lcom/example/budgetapp/database/dao/SpendingPatternDao;", "storeChainDao", "Lcom/example/budgetapp/database/dao/StoreChainDao;", "storeDao", "Lcom/example/budgetapp/database/dao/StoreDao;", "transactionNotificationDao", "Lcom/example/budgetapp/database/dao/TransactionNotificationDao;", "Companion", "CashInCashOut_release"})
@androidx.room.Database(entities = {com.example.budgetapp.database.entities.Product.class, com.example.budgetapp.database.entities.ProductCategory.class, com.example.budgetapp.database.entities.Category.class, com.example.budgetapp.database.entities.Store.class, com.example.budgetapp.database.entities.StoreChain.class, com.example.budgetapp.database.entities.ProductStore.class, com.example.budgetapp.database.entities.Income.class, com.example.budgetapp.database.entities.Expense.class, com.example.budgetapp.database.entities.Loan.class, com.example.budgetapp.database.entities.Budget.class, com.example.budgetapp.database.entities.Receipt.class, com.example.budgetapp.database.entities.ReceiptItem.class, com.example.budgetapp.database.entities.ShoppingList.class, com.example.budgetapp.database.entities.ShoppingListItem.class, com.example.budgetapp.database.entities.PriceHistory.class, com.example.budgetapp.database.entities.ReminderSettings.class, com.example.budgetapp.database.entities.AppUsage.class, com.example.budgetapp.database.entities.TransactionNotification.class, com.example.budgetapp.database.entities.FinancialInsight.class, com.example.budgetapp.database.entities.FinancialGoal.class, com.example.budgetapp.database.entities.SpendingPattern.class, com.example.budgetapp.database.entities.SpendingForecast.class}, version = 18, exportSchema = false)
@androidx.room.TypeConverters(value = {com.example.budgetapp.database.entities.LoanTypeConverter.class, com.example.budgetapp.database.entities.BudgetTypeConverter.class, com.example.budgetapp.database.entities.AnalyticsTypeConverters.class})
public abstract class BudgetDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile
    @org.jetbrains.annotations.Nullable
    private static volatile com.example.budgetapp.database.BudgetDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull
    private static final androidx.room.migration.Migration MIGRATION_1_2 = null;
    @org.jetbrains.annotations.NotNull
    private static final androidx.room.migration.Migration MIGRATION_2_3 = null;
    @org.jetbrains.annotations.NotNull
    private static final androidx.room.migration.Migration MIGRATION_3_4 = null;
    @org.jetbrains.annotations.NotNull
    private static final androidx.room.migration.Migration MIGRATION_4_5 = null;
    @org.jetbrains.annotations.NotNull
    private static final androidx.room.migration.Migration MIGRATION_5_6 = null;
    @org.jetbrains.annotations.NotNull
    private static final androidx.room.migration.Migration MIGRATION_6_7 = null;
    @org.jetbrains.annotations.NotNull
    private static final androidx.room.migration.Migration MIGRATION_7_8 = null;
    @org.jetbrains.annotations.NotNull
    private static final androidx.room.migration.Migration MIGRATION_8_9 = null;
    @org.jetbrains.annotations.NotNull
    private static final androidx.room.migration.Migration MIGRATION_9_10 = null;
    @org.jetbrains.annotations.NotNull
    private static final androidx.room.migration.Migration MIGRATION_10_11 = null;
    @org.jetbrains.annotations.NotNull
    private static final androidx.room.migration.Migration MIGRATION_11_12 = null;
    @org.jetbrains.annotations.NotNull
    private static final androidx.room.migration.Migration MIGRATION_12_13 = null;
    @org.jetbrains.annotations.NotNull
    private static final androidx.room.migration.Migration MIGRATION_13_14 = null;
    @org.jetbrains.annotations.NotNull
    private static final androidx.room.migration.Migration MIGRATION_14_15 = null;
    @org.jetbrains.annotations.NotNull
    private static final androidx.room.migration.Migration MIGRATION_15_16 = null;
    @org.jetbrains.annotations.NotNull
    private static final androidx.room.migration.Migration MIGRATION_16_17 = null;
    @org.jetbrains.annotations.NotNull
    private static final androidx.room.migration.Migration MIGRATION_17_18 = null;
    @org.jetbrains.annotations.NotNull
    public static final com.example.budgetapp.database.BudgetDatabase.Companion Companion = null;
    
    public BudgetDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.budgetapp.database.dao.ProductDao productDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.budgetapp.database.dao.ProductCategoryDao productCategoryDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.budgetapp.database.dao.CategoryDao categoryDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.budgetapp.database.dao.StoreDao storeDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.budgetapp.database.dao.StoreChainDao storeChainDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.budgetapp.database.dao.ProductStoreDao productStoreDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.budgetapp.database.dao.IncomeDao incomeDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.budgetapp.database.dao.ExpenseDao expenseDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.budgetapp.database.dao.LoanDao loanDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.budgetapp.database.dao.BudgetDao budgetDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.budgetapp.database.dao.ReceiptDao receiptDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.budgetapp.database.dao.ReceiptItemDao receiptItemDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.budgetapp.database.dao.ShoppingListDao shoppingListDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.budgetapp.database.dao.ShoppingListItemDao shoppingListItemDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.budgetapp.database.dao.PriceHistoryDao priceHistoryDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.budgetapp.database.dao.ReminderSettingsDao reminderSettingsDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.budgetapp.database.dao.AppUsageDao appUsageDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.budgetapp.database.dao.TransactionNotificationDao transactionNotificationDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.budgetapp.database.dao.FinancialInsightDao financialInsightDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.budgetapp.database.dao.FinancialGoalDao financialGoalDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.budgetapp.database.dao.SpendingPatternDao spendingPatternDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.budgetapp.database.dao.SpendingForecastDao spendingForecastDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001:\u00011B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010)\u001a\u00020\u00042\u0006\u0010*\u001a\u00020+J\u0019\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00100R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\u0011\u0010\r\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0011\u0010\u000f\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\bR\u0011\u0010\u0011\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\bR\u0011\u0010\u0013\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\bR\u0011\u0010\u0015\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\bR\u0011\u0010\u0017\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\bR\u0011\u0010\u0019\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\bR\u0011\u0010\u001b\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\bR\u0011\u0010\u001d\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\bR\u0011\u0010\u001f\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\bR\u0011\u0010!\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\bR\u0011\u0010#\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\bR\u0011\u0010%\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\bR\u0011\u0010\'\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00062"}, d2 = {"Lcom/example/budgetapp/database/BudgetDatabase$Companion;", "", "()V", "INSTANCE", "Lcom/example/budgetapp/database/BudgetDatabase;", "MIGRATION_10_11", "Landroidx/room/migration/Migration;", "getMIGRATION_10_11", "()Landroidx/room/migration/Migration;", "MIGRATION_11_12", "getMIGRATION_11_12", "MIGRATION_12_13", "getMIGRATION_12_13", "MIGRATION_13_14", "getMIGRATION_13_14", "MIGRATION_14_15", "getMIGRATION_14_15", "MIGRATION_15_16", "getMIGRATION_15_16", "MIGRATION_16_17", "getMIGRATION_16_17", "MIGRATION_17_18", "getMIGRATION_17_18", "MIGRATION_1_2", "getMIGRATION_1_2", "MIGRATION_2_3", "getMIGRATION_2_3", "MIGRATION_3_4", "getMIGRATION_3_4", "MIGRATION_4_5", "getMIGRATION_4_5", "MIGRATION_5_6", "getMIGRATION_5_6", "MIGRATION_6_7", "getMIGRATION_6_7", "MIGRATION_7_8", "getMIGRATION_7_8", "MIGRATION_8_9", "getMIGRATION_8_9", "MIGRATION_9_10", "getMIGRATION_9_10", "getDatabase", "context", "Landroid/content/Context;", "populateDatabase", "", "categoryDao", "Lcom/example/budgetapp/database/dao/CategoryDao;", "(Lcom/example/budgetapp/database/dao/CategoryDao;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "DatabaseCallback", "CashInCashOut_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final androidx.room.migration.Migration getMIGRATION_1_2() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final androidx.room.migration.Migration getMIGRATION_2_3() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final androidx.room.migration.Migration getMIGRATION_3_4() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final androidx.room.migration.Migration getMIGRATION_4_5() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final androidx.room.migration.Migration getMIGRATION_5_6() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final androidx.room.migration.Migration getMIGRATION_6_7() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final androidx.room.migration.Migration getMIGRATION_7_8() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final androidx.room.migration.Migration getMIGRATION_8_9() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final androidx.room.migration.Migration getMIGRATION_9_10() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final androidx.room.migration.Migration getMIGRATION_10_11() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final androidx.room.migration.Migration getMIGRATION_11_12() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final androidx.room.migration.Migration getMIGRATION_12_13() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final androidx.room.migration.Migration getMIGRATION_13_14() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final androidx.room.migration.Migration getMIGRATION_14_15() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final androidx.room.migration.Migration getMIGRATION_15_16() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final androidx.room.migration.Migration getMIGRATION_16_17() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final androidx.room.migration.Migration getMIGRATION_17_18() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.budgetapp.database.BudgetDatabase getDatabase(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
            return null;
        }
        
        private final java.lang.Object populateDatabase(com.example.budgetapp.database.dao.CategoryDao categoryDao, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016\u00a8\u0006\u0007"}, d2 = {"Lcom/example/budgetapp/database/BudgetDatabase$Companion$DatabaseCallback;", "Landroidx/room/RoomDatabase$Callback;", "()V", "onCreate", "", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "CashInCashOut_release"})
        public static final class DatabaseCallback extends androidx.room.RoomDatabase.Callback {
            
            public DatabaseCallback() {
                super();
            }
            
            @java.lang.Override
            public void onCreate(@org.jetbrains.annotations.NotNull
            androidx.sqlite.db.SupportSQLiteDatabase db) {
            }
        }
    }
}