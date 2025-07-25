package com.example.budgetapp.database;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\r\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u000b\u00a8\u0006\u0011"}, d2 = {"Lcom/example/budgetapp/database/DatabaseBackupHelper;", "", "()V", "createBackup", "", "context", "Landroid/content/Context;", "database", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "deleteBackup", "getDataVersion", "", "hasBackup", "restoreFromBackup", "setDataVersion", "", "version", "CashInCashOut_release"})
public final class DatabaseBackupHelper {
    @org.jetbrains.annotations.NotNull
    public static final com.example.budgetapp.database.DatabaseBackupHelper INSTANCE = null;
    
    private DatabaseBackupHelper() {
        super();
    }
    
    public final boolean createBackup(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    androidx.sqlite.db.SupportSQLiteDatabase database) {
        return false;
    }
    
    public final boolean restoreFromBackup(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        return false;
    }
    
    public final boolean hasBackup(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        return false;
    }
    
    public final boolean deleteBackup(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        return false;
    }
    
    public final int getDataVersion(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        return 0;
    }
    
    public final void setDataVersion(@org.jetbrains.annotations.NotNull
    android.content.Context context, int version) {
    }
}