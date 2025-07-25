package com.example.budgetapp.fragments;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0012H\u0002J\u0010\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u001c\u0010\u0019\u001a\u00020\u00122\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u001c0\u001bH\u0002J\b\u0010\u001d\u001a\u00020\u0012H\u0002J\b\u0010\u001e\u001a\u00020\u0012H\u0002J$\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\b\u0010\'\u001a\u00020\u0012H\u0016J\u001a\u0010(\u001a\u00020\u00122\u0006\u0010)\u001a\u00020 2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\u0019\u0010*\u001a\u00020\u00122\n\b\u0002\u0010+\u001a\u0004\u0018\u00010,H\u0002\u00a2\u0006\u0002\u0010-J\b\u0010.\u001a\u00020\u0012H\u0002J\b\u0010/\u001a\u00020\u0012H\u0002J\b\u00100\u001a\u00020\u0012H\u0002J\b\u00101\u001a\u00020\u0012H\u0002J\u0010\u00102\u001a\u00020\u00122\u0006\u00103\u001a\u00020\fH\u0002J\b\u00104\u001a\u00020\u0012H\u0002J\u001f\u00105\u001a\u00020\u00122\f\u00106\u001a\b\u0012\u0004\u0012\u00020\u001807H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00108J\b\u00109\u001a\u00020\u0012H\u0002J\b\u0010:\u001a\u00020\u0012H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006;"}, d2 = {"Lcom/example/budgetapp/fragments/BudgetFragment;", "Lcom/example/budgetapp/fragments/BaseFragment;", "()V", "_binding", "Lcom/example/budgetapp/databinding/FragmentBudgetBinding;", "binding", "getBinding", "()Lcom/example/budgetapp/databinding/FragmentBudgetBinding;", "budgetAdapter", "Lcom/example/budgetapp/adapters/BudgetAdapter;", "budgetProgressList", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lcom/example/budgetapp/repository/BudgetProgress;", "budgetRepository", "Lcom/example/budgetapp/repository/BudgetRepository;", "observationJob", "Lkotlinx/coroutines/Job;", "applyCustomTheme", "", "settings", "Lcom/example/budgetapp/data/ThemeSettings;", "createSampleData", "deleteBudget", "budget", "Lcom/example/budgetapp/database/entities/Budget;", "filterBudgets", "predicate", "Lkotlin/Function1;", "", "initializeComponents", "observeBudgets", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "openAddEditBudgetActivity", "budgetId", "", "(Ljava/lang/Integer;)V", "openBudgetAnalytics", "resetFilter", "setupClickListeners", "setupRecyclerView", "showDeleteConfirmationDialog", "budgetProgress", "showFilterDialog", "updateBudgetProgressList", "budgets", "", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateEmptyState", "updateSummaryCard", "CashInCashOut_debug"})
public final class BudgetFragment extends com.example.budgetapp.fragments.BaseFragment {
    @org.jetbrains.annotations.Nullable
    private com.example.budgetapp.databinding.FragmentBudgetBinding _binding;
    private com.example.budgetapp.repository.BudgetRepository budgetRepository;
    private com.example.budgetapp.adapters.BudgetAdapter budgetAdapter;
    @org.jetbrains.annotations.NotNull
    private java.util.concurrent.CopyOnWriteArrayList<com.example.budgetapp.repository.BudgetProgress> budgetProgressList;
    @org.jetbrains.annotations.Nullable
    private kotlinx.coroutines.Job observationJob;
    
    public BudgetFragment() {
        super();
    }
    
    private final com.example.budgetapp.databinding.FragmentBudgetBinding getBinding() {
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
    
    private final void initializeComponents() {
    }
    
    private final void setupRecyclerView() {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void observeBudgets() {
    }
    
    private final java.lang.Object updateBudgetProgressList(java.util.List<com.example.budgetapp.database.entities.Budget> budgets, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final void updateSummaryCard() {
    }
    
    private final void updateEmptyState() {
    }
    
    private final void openAddEditBudgetActivity(java.lang.Integer budgetId) {
    }
    
    private final void openBudgetAnalytics() {
    }
    
    private final void showDeleteConfirmationDialog(com.example.budgetapp.repository.BudgetProgress budgetProgress) {
    }
    
    private final void deleteBudget(com.example.budgetapp.database.entities.Budget budget) {
    }
    
    private final void showFilterDialog() {
    }
    
    private final void filterBudgets(kotlin.jvm.functions.Function1<? super com.example.budgetapp.repository.BudgetProgress, java.lang.Boolean> predicate) {
    }
    
    private final void resetFilter() {
    }
    
    private final void createSampleData() {
    }
    
    @java.lang.Override
    public void onDestroyView() {
    }
    
    @java.lang.Override
    public void applyCustomTheme(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.data.ThemeSettings settings) {
    }
}