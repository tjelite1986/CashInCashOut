package com.example.budgetapp.database.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0011\u0010\u0002\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b0\nH\'J\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0004J\u001b\u0010\r\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000e\u001a\u00020\u000fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0010J\u0019\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\bJ!\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0016H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J\u0019\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0019"}, d2 = {"Lcom/example/budgetapp/database/dao/ReminderSettingsDao;", "", "deleteAllReminders", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteReminder", "reminder", "Lcom/example/budgetapp/database/entities/ReminderSettings;", "(Lcom/example/budgetapp/database/entities/ReminderSettings;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllReminders", "Landroidx/lifecycle/LiveData;", "", "getEnabledReminders", "getReminderByType", "type", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertReminder", "", "toggleReminder", "id", "enabled", "", "(JZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateReminder", "CashInCashOut_release"})
@androidx.room.Dao
public abstract interface ReminderSettingsDao {
    
    @androidx.room.Query(value = "SELECT * FROM reminder_settings ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull
    public abstract androidx.lifecycle.LiveData<java.util.List<com.example.budgetapp.database.entities.ReminderSettings>> getAllReminders();
    
    @androidx.room.Query(value = "SELECT * FROM reminder_settings WHERE isEnabled = 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getEnabledReminders(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.database.entities.ReminderSettings>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM reminder_settings WHERE reminderType = :type LIMIT 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getReminderByType(@org.jetbrains.annotations.NotNull
    java.lang.String type, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.database.entities.ReminderSettings> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertReminder(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.ReminderSettings reminder, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateReminder(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.ReminderSettings reminder, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteReminder(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.ReminderSettings reminder, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE reminder_settings SET isEnabled = :enabled WHERE id = :id")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object toggleReminder(long id, boolean enabled, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM reminder_settings")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteAllReminders(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}