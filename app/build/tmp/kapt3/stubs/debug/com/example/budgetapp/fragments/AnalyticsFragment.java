package com.example.budgetapp.fragments;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0011H\u0002J$\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u0011H\u0016J\u001a\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u00142\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010\u001e\u001a\u00020\u0011H\u0002J\b\u0010\u001f\u001a\u00020\u0011H\u0002J\b\u0010 \u001a\u00020\u0011H\u0002J\b\u0010!\u001a\u00020\u0011H\u0002J\b\u0010\"\u001a\u00020\u0011H\u0002J\b\u0010#\u001a\u00020\u0011H\u0002J\u0010\u0010$\u001a\u00020\u00112\u0006\u0010%\u001a\u00020&H\u0002J\b\u0010\'\u001a\u00020\u0011H\u0002J\u0010\u0010(\u001a\u00020\u00112\u0006\u0010)\u001a\u00020*H\u0002J\u0010\u0010+\u001a\u00020\u00112\u0006\u0010%\u001a\u00020&H\u0002J\u0010\u0010,\u001a\u00020\u00112\u0006\u0010-\u001a\u00020.H\u0002J\u0016\u0010/\u001a\u00020\u00112\f\u00100\u001a\b\u0012\u0004\u0012\u00020201H\u0002J\u001c\u00103\u001a\u00020\u00112\u0012\u00104\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020605H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u00067"}, d2 = {"Lcom/example/budgetapp/fragments/AnalyticsFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/example/budgetapp/databinding/FragmentAnalyticsBinding;", "analyticsService", "Lcom/example/budgetapp/services/AdvancedAnalyticsService;", "analyticsViewModel", "Lcom/example/budgetapp/viewmodels/AnalyticsViewModel;", "binding", "getBinding", "()Lcom/example/budgetapp/databinding/FragmentAnalyticsBinding;", "insightAdapter", "Lcom/example/budgetapp/adapters/InsightAdapter;", "findNavController", "Landroidx/navigation/NavController;", "generateNewInsights", "", "loadAnalyticsData", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "refreshAnalytics", "setupAnalyticsService", "setupRecyclerView", "setupUI", "setupViewModel", "showAllInsights", "showError", "message", "", "showFinancialHealthDetails", "showInsightDetails", "insight", "Lcom/example/budgetapp/database/entities/FinancialInsight;", "showSuccess", "updateFinancialHealthUI", "score", "", "updateGoalsProgressUI", "goals", "", "Lcom/example/budgetapp/database/entities/FinancialGoal;", "updateInsightCountsUI", "counts", "", "", "CashInCashOut_debug"})
public final class AnalyticsFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable
    private com.example.budgetapp.databinding.FragmentAnalyticsBinding _binding;
    private com.example.budgetapp.viewmodels.AnalyticsViewModel analyticsViewModel;
    private com.example.budgetapp.adapters.InsightAdapter insightAdapter;
    private com.example.budgetapp.services.AdvancedAnalyticsService analyticsService;
    
    public AnalyticsFragment() {
        super();
    }
    
    private final com.example.budgetapp.databinding.FragmentAnalyticsBinding getBinding() {
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
    
    private final void setupViewModel() {
    }
    
    private final void setupRecyclerView() {
    }
    
    private final void setupAnalyticsService() {
    }
    
    private final void setupUI() {
    }
    
    private final void loadAnalyticsData() {
    }
    
    private final void updateFinancialHealthUI(double score) {
    }
    
    private final void updateInsightCountsUI(java.util.Map<java.lang.String, java.lang.Integer> counts) {
    }
    
    private final void updateGoalsProgressUI(java.util.List<com.example.budgetapp.database.entities.FinancialGoal> goals) {
    }
    
    private final void refreshAnalytics() {
    }
    
    private final void generateNewInsights() {
    }
    
    private final void showInsightDetails(com.example.budgetapp.database.entities.FinancialInsight insight) {
    }
    
    private final void showFinancialHealthDetails() {
    }
    
    private final void showAllInsights() {
    }
    
    private final void showError(java.lang.String message) {
    }
    
    private final void showSuccess(java.lang.String message) {
    }
    
    private final androidx.navigation.NavController findNavController() {
        return null;
    }
    
    @java.lang.Override
    public void onDestroyView() {
    }
}