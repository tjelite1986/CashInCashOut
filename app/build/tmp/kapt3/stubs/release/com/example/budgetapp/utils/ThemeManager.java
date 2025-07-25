package com.example.budgetapp.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u0000 \u00192\u00020\u0001:\u0002\u0019\u001aB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007J\u0012\u0010\r\u001a\u00020\u000b2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fJ\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u000fJ\u0006\u0010\u0013\u001a\u00020\u0014J\u0006\u0010\u0015\u001a\u00020\u0014J\u000e\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007J\u000e\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/example/budgetapp/utils/ThemeManager;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "listeners", "", "Lcom/example/budgetapp/utils/ThemeManager$ThemeChangeListener;", "sharedPreferences", "Landroid/content/SharedPreferences;", "addThemeChangeListener", "", "listener", "applyTheme", "settings", "Lcom/example/budgetapp/data/ThemeSettings;", "getAccentColorInt", "", "getCurrentThemeSettings", "isColorfulInterface", "", "isDarkMode", "notifyThemeChanged", "removeThemeChangeListener", "saveThemeSettings", "Companion", "ThemeChangeListener", "CashInCashOut_release"})
public final class ThemeManager {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String THEME_PREFS = "theme_preferences";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_ACCENT_COLOR = "accent_color";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_THEME_MODE = "theme_mode";
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String KEY_INTERFACE_STYLE = "interface_style";
    @kotlin.jvm.Volatile
    @org.jetbrains.annotations.Nullable
    private static volatile com.example.budgetapp.utils.ThemeManager INSTANCE;
    @org.jetbrains.annotations.NotNull
    private final android.content.SharedPreferences sharedPreferences = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.Set<com.example.budgetapp.utils.ThemeManager.ThemeChangeListener> listeners = null;
    @org.jetbrains.annotations.NotNull
    public static final com.example.budgetapp.utils.ThemeManager.Companion Companion = null;
    
    public ThemeManager(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.example.budgetapp.data.ThemeSettings getCurrentThemeSettings() {
        return null;
    }
    
    public final void saveThemeSettings(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.data.ThemeSettings settings) {
    }
    
    public final void applyTheme(@org.jetbrains.annotations.Nullable
    com.example.budgetapp.data.ThemeSettings settings) {
    }
    
    public final boolean isDarkMode() {
        return false;
    }
    
    public final int getAccentColorInt() {
        return 0;
    }
    
    public final boolean isColorfulInterface() {
        return false;
    }
    
    public final void addThemeChangeListener(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.utils.ThemeManager.ThemeChangeListener listener) {
    }
    
    public final void removeThemeChangeListener(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.utils.ThemeManager.ThemeChangeListener listener) {
    }
    
    public final void notifyThemeChanged(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.data.ThemeSettings settings) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/example/budgetapp/utils/ThemeManager$Companion;", "", "()V", "INSTANCE", "Lcom/example/budgetapp/utils/ThemeManager;", "KEY_ACCENT_COLOR", "", "KEY_INTERFACE_STYLE", "KEY_THEME_MODE", "THEME_PREFS", "getInstance", "context", "Landroid/content/Context;", "CashInCashOut_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.budgetapp.utils.ThemeManager getInstance(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lcom/example/budgetapp/utils/ThemeManager$ThemeChangeListener;", "", "onThemeChanged", "", "settings", "Lcom/example/budgetapp/data/ThemeSettings;", "CashInCashOut_release"})
    public static abstract interface ThemeChangeListener {
        
        public abstract void onThemeChanged(@org.jetbrains.annotations.NotNull
        com.example.budgetapp.data.ThemeSettings settings);
    }
}