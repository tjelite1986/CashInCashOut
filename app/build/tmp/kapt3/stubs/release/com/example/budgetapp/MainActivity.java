package com.example.budgetapp;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\u0012H\u0002J\b\u0010\u0014\u001a\u00020\u0012H\u0002J\b\u0010\u0015\u001a\u00020\u0012H\u0002J\u0012\u0010\u0016\u001a\u00020\u00122\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\b\u0010\u0019\u001a\u00020\u0012H\u0014J\b\u0010\u001a\u001a\u00020\u0012H\u0014J\b\u0010\u001b\u001a\u00020\u0012H\u0002J\b\u0010\u001c\u001a\u00020\u0012H\u0002J\u0010\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\b\u0010 \u001a\u00020\u0012H\u0002J\b\u0010!\u001a\u00020\u0012H\u0002J\b\u0010\"\u001a\u00020\u0012H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0010\u0012\f\u0012\n \n*\u0004\u0018\u00010\t0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/example/budgetapp/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "alarmManagerHelper", "Lcom/example/budgetapp/receivers/AlarmManagerHelper;", "binding", "Lcom/example/budgetapp/databinding/ActivityMainBinding;", "notificationPermissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "kotlin.jvm.PlatformType", "notificationService", "Lcom/example/budgetapp/services/NotificationService;", "notificationSettingsManager", "Lcom/example/budgetapp/utils/NotificationSettingsManager;", "themeManager", "Lcom/example/budgetapp/utils/ThemeManager;", "handleNavigationIntent", "", "initializeNotifications", "initializeServices", "initializeTheme", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onResume", "requestNotificationPermissionIfNeeded", "setupBottomNavigation", "showFragment", "fragment", "Landroidx/fragment/app/Fragment;", "showNotificationDeniedSnackbar", "showNotificationGrantedSnackbar", "updateThemeColors", "CashInCashOut_release"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.budgetapp.databinding.ActivityMainBinding binding;
    private com.example.budgetapp.utils.NotificationSettingsManager notificationSettingsManager;
    private com.example.budgetapp.utils.ThemeManager themeManager;
    private com.example.budgetapp.services.NotificationService notificationService;
    private com.example.budgetapp.receivers.AlarmManagerHelper alarmManagerHelper;
    @org.jetbrains.annotations.NotNull
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String> notificationPermissionLauncher = null;
    
    public MainActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void initializeServices() {
    }
    
    @java.lang.Override
    protected void onResume() {
    }
    
    @java.lang.Override
    protected void onDestroy() {
    }
    
    private final void initializeTheme() {
    }
    
    private final void updateThemeColors() {
    }
    
    private final void initializeNotifications() {
    }
    
    private final void requestNotificationPermissionIfNeeded() {
    }
    
    private final void handleNavigationIntent() {
    }
    
    private final void showNotificationGrantedSnackbar() {
    }
    
    private final void showNotificationDeniedSnackbar() {
    }
    
    private final void setupBottomNavigation() {
    }
    
    private final void showFragment(androidx.fragment.app.Fragment fragment) {
    }
}