package com.example.budgetapp.database;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bJ\u0016\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\b\u00a8\u0006\u000f"}, d2 = {"Lcom/example/budgetapp/database/DatabaseMigrationHelper;", "", "()V", "doesColumnExist", "", "database", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "tableName", "", "columnName", "doesTableExist", "getCurrentVersion", "", "safeExecuteSQL", "sql", "CashInCashOut_release"})
public final class DatabaseMigrationHelper {
    @org.jetbrains.annotations.NotNull
    public static final com.example.budgetapp.database.DatabaseMigrationHelper INSTANCE = null;
    
    private DatabaseMigrationHelper() {
        super();
    }
    
    public final boolean doesTableExist(@org.jetbrains.annotations.NotNull
    androidx.sqlite.db.SupportSQLiteDatabase database, @org.jetbrains.annotations.NotNull
    java.lang.String tableName) {
        return false;
    }
    
    public final boolean doesColumnExist(@org.jetbrains.annotations.NotNull
    androidx.sqlite.db.SupportSQLiteDatabase database, @org.jetbrains.annotations.NotNull
    java.lang.String tableName, @org.jetbrains.annotations.NotNull
    java.lang.String columnName) {
        return false;
    }
    
    public final int getCurrentVersion(@org.jetbrains.annotations.NotNull
    androidx.sqlite.db.SupportSQLiteDatabase database) {
        return 0;
    }
    
    public final boolean safeExecuteSQL(@org.jetbrains.annotations.NotNull
    androidx.sqlite.db.SupportSQLiteDatabase database, @org.jetbrains.annotations.NotNull
    java.lang.String sql) {
        return false;
    }
}