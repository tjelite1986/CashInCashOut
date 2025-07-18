package com.example.budgetapp.database;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u000eH&\u00a8\u0006\u0010"}, d2 = {"Lcom/example/budgetapp/database/BudgetDatabase;", "Landroidx/room/RoomDatabase;", "()V", "categoryDao", "Lcom/example/budgetapp/database/dao/CategoryDao;", "expenseDao", "Lcom/example/budgetapp/database/dao/ExpenseDao;", "incomeDao", "Lcom/example/budgetapp/database/dao/IncomeDao;", "productDao", "Lcom/example/budgetapp/database/dao/ProductDao;", "productStoreDao", "Lcom/example/budgetapp/database/dao/ProductStoreDao;", "storeDao", "Lcom/example/budgetapp/database/dao/StoreDao;", "Companion", "budget-debug_debug"})
@androidx.room.Database(entities = {com.example.budgetapp.database.entities.Product.class, com.example.budgetapp.database.entities.Category.class, com.example.budgetapp.database.entities.Store.class, com.example.budgetapp.database.entities.ProductStore.class, com.example.budgetapp.database.entities.Income.class, com.example.budgetapp.database.entities.Expense.class}, version = 6, exportSchema = false)
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
    public static final com.example.budgetapp.database.BudgetDatabase.Companion Companion = null;
    
    public BudgetDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.budgetapp.database.dao.ProductDao productDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.budgetapp.database.dao.CategoryDao categoryDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.budgetapp.database.dao.StoreDao storeDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.budgetapp.database.dao.ProductStoreDao productStoreDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.budgetapp.database.dao.IncomeDao incomeDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.budgetapp.database.dao.ExpenseDao expenseDao();
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001:\u0001\u0013B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rJ\u0019\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0014"}, d2 = {"Lcom/example/budgetapp/database/BudgetDatabase$Companion;", "", "()V", "INSTANCE", "Lcom/example/budgetapp/database/BudgetDatabase;", "MIGRATION_1_2", "Landroidx/room/migration/Migration;", "MIGRATION_2_3", "MIGRATION_3_4", "MIGRATION_4_5", "MIGRATION_5_6", "getDatabase", "context", "Landroid/content/Context;", "populateDatabase", "", "categoryDao", "Lcom/example/budgetapp/database/dao/CategoryDao;", "(Lcom/example/budgetapp/database/dao/CategoryDao;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "DatabaseCallback", "budget-debug_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.budgetapp.database.BudgetDatabase getDatabase(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
            return null;
        }
        
        private final java.lang.Object populateDatabase(com.example.budgetapp.database.dao.CategoryDao categoryDao, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
            return null;
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016\u00a8\u0006\u0007"}, d2 = {"Lcom/example/budgetapp/database/BudgetDatabase$Companion$DatabaseCallback;", "Landroidx/room/RoomDatabase$Callback;", "()V", "onCreate", "", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "budget-debug_debug"})
        static final class DatabaseCallback extends androidx.room.RoomDatabase.Callback {
            
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