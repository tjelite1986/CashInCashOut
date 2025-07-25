package com.example.budgetapp.fragments;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J \u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0010H\u0002J\u0010\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u0017H\u0002J$\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\b\u0010%\u001a\u00020\u0017H\u0016J\b\u0010&\u001a\u00020\u0017H\u0016J\u001a\u0010\'\u001a\u00020\u00172\u0006\u0010(\u001a\u00020\u001e2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\b\u0010)\u001a\u00020\u0017H\u0002J\b\u0010*\u001a\u00020\u0017H\u0002J\b\u0010+\u001a\u00020\u0017H\u0002J\b\u0010,\u001a\u00020\u0017H\u0002J\b\u0010-\u001a\u00020\u0017H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006."}, d2 = {"Lcom/example/budgetapp/fragments/OverviewFragment;", "Lcom/example/budgetapp/fragments/BaseFragment;", "()V", "_binding", "Lcom/example/budgetapp/databinding/FragmentOverviewBinding;", "binding", "getBinding", "()Lcom/example/budgetapp/databinding/FragmentOverviewBinding;", "currencyFormat", "Ljava/text/NumberFormat;", "kotlin.jvm.PlatformType", "database", "Lcom/example/budgetapp/database/BudgetDatabase;", "prefs", "Landroid/content/SharedPreferences;", "adjustColorAlpha", "", "color", "alpha", "", "adjustColorHue", "hueShift", "applyColorfulCardBackgrounds", "", "accentColor", "applyCustomTheme", "settings", "Lcom/example/budgetapp/data/ThemeSettings;", "loadFinancialData", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onResume", "onViewCreated", "view", "setupClickListeners", "setupDefaultUI", "setupUI", "updateGreeting", "updateSectionVisibility", "CashInCashOut_release"})
public final class OverviewFragment extends com.example.budgetapp.fragments.BaseFragment {
    @org.jetbrains.annotations.Nullable
    private com.example.budgetapp.databinding.FragmentOverviewBinding _binding;
    private com.example.budgetapp.database.BudgetDatabase database;
    private android.content.SharedPreferences prefs;
    private final java.text.NumberFormat currencyFormat = null;
    
    public OverviewFragment() {
        super();
    }
    
    private final com.example.budgetapp.databinding.FragmentOverviewBinding getBinding() {
        return null;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override
    public void onViewCreated(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupUI() {
    }
    
    private final void updateGreeting() {
    }
    
    private final void updateSectionVisibility() {
    }
    
    private final void setupDefaultUI() {
    }
    
    private final void loadFinancialData() {
    }
    
    private final void setupClickListeners() {
    }
    
    @java.lang.Override
    public void onResume() {
    }
    
    @java.lang.Override
    public void applyCustomTheme(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.data.ThemeSettings settings) {
    }
    
    private final void applyColorfulCardBackgrounds(int accentColor) {
    }
    
    private final int adjustColorAlpha(int color, float alpha) {
        return 0;
    }
    
    private final int adjustColorHue(int color, float hueShift, float alpha) {
        return 0;
    }
    
    @java.lang.Override
    public void onDestroyView() {
    }
}