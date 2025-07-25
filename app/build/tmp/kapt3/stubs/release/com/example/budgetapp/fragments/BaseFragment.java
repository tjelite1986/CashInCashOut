package com.example.budgetapp.fragments;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0018\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\rH\u0002J\u0010\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0018H&J \u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0015\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\u0005H\u0002J\u0018\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\rH\u0002J\u0012\u0010!\u001a\u00020\u00122\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010$\u001a\u00020\u0012H\u0016J\u0010\u0010%\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u001a\u0010&\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0084.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b\u00a8\u0006\'"}, d2 = {"Lcom/example/budgetapp/fragments/BaseFragment;", "Landroidx/fragment/app/Fragment;", "Lcom/example/budgetapp/utils/ThemeManager$ThemeChangeListener;", "()V", "isThemeInitialized", "", "themeManager", "Lcom/example/budgetapp/utils/ThemeManager;", "getThemeManager", "()Lcom/example/budgetapp/utils/ThemeManager;", "setThemeManager", "(Lcom/example/budgetapp/utils/ThemeManager;)V", "adjustColorBrightness", "", "color", "factor", "", "applyColorfulCardStyle", "", "cardView", "Lcom/google/android/material/card/MaterialCardView;", "accentColor", "applyCustomTheme", "settings", "Lcom/example/budgetapp/data/ThemeSettings;", "applyThemeRecursively", "view", "Landroid/view/View;", "isColorful", "applyThemeToViews", "rootView", "getColor", "colorRes", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onThemeChanged", "onViewCreated", "CashInCashOut_release"})
public abstract class BaseFragment extends androidx.fragment.app.Fragment implements com.example.budgetapp.utils.ThemeManager.ThemeChangeListener {
    protected com.example.budgetapp.utils.ThemeManager themeManager;
    private boolean isThemeInitialized = false;
    
    public BaseFragment() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    protected final com.example.budgetapp.utils.ThemeManager getThemeManager() {
        return null;
    }
    
    protected final void setThemeManager(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.utils.ThemeManager p0) {
    }
    
    @java.lang.Override
    public void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
    public void onDestroyView() {
    }
    
    @java.lang.Override
    public void onThemeChanged(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.data.ThemeSettings settings) {
    }
    
    private final void applyThemeToViews(android.view.View rootView, com.example.budgetapp.data.ThemeSettings settings) {
    }
    
    private final void applyThemeRecursively(android.view.View view, int accentColor, boolean isColorful) {
    }
    
    private final void applyColorfulCardStyle(com.google.android.material.card.MaterialCardView cardView, int accentColor) {
    }
    
    private final int adjustColorBrightness(int color, float factor) {
        return 0;
    }
    
    private final int getColor(int colorRes) {
        return 0;
    }
    
    public abstract void applyCustomTheme(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.data.ThemeSettings settings);
}