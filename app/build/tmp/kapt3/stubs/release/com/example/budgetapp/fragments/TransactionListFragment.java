package com.example.budgetapp.fragments;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 -2\u00020\u0001:\u0001-B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0013\u001a\u00020\u0010J\u001e\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018J\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eJ\b\u0010\u001b\u001a\u00020\u0010H\u0002J\u0012\u0010\u001c\u001a\u00020\u00102\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J$\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\b\u0010%\u001a\u00020\u0010H\u0016J\u001a\u0010&\u001a\u00020\u00102\u0006\u0010\'\u001a\u00020 2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0006\u0010(\u001a\u00020\u0010J\b\u0010)\u001a\u00020\u0010H\u0002J\b\u0010*\u001a\u00020\u0010H\u0002J\u0010\u0010+\u001a\u00020\u00102\u0006\u0010,\u001a\u00020\u0018H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\"\u0010\f\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\u0004\u0012\u00020\u0010\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006."}, d2 = {"Lcom/example/budgetapp/fragments/TransactionListFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/example/budgetapp/databinding/FragmentTransactionListBinding;", "adapter", "Lcom/example/budgetapp/adapters/SelectableTransactionAdapter;", "binding", "getBinding", "()Lcom/example/budgetapp/databinding/FragmentTransactionListBinding;", "database", "Lcom/example/budgetapp/database/BudgetDatabase;", "onSelectionChanged", "Lkotlin/Function1;", "", "Lcom/example/budgetapp/adapters/SelectableTransactionAdapter$SelectableTransaction;", "", "transactionType", "Lcom/example/budgetapp/database/entities/TransactionType;", "clearSelection", "filterTransactions", "query", "", "showRecurringOnly", "", "showThisMonthOnly", "getSelectedTransactions", "loadTransactions", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onViewCreated", "view", "selectAll", "setupRecyclerView", "setupSwipeRefresh", "updateEmptyState", "isEmpty", "Companion", "CashInCashOut_release"})
public final class TransactionListFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable
    private com.example.budgetapp.databinding.FragmentTransactionListBinding _binding;
    private com.example.budgetapp.adapters.SelectableTransactionAdapter adapter;
    private com.example.budgetapp.database.BudgetDatabase database;
    @org.jetbrains.annotations.NotNull
    private com.example.budgetapp.database.entities.TransactionType transactionType = com.example.budgetapp.database.entities.TransactionType.INCOME;
    @org.jetbrains.annotations.Nullable
    private kotlin.jvm.functions.Function1<? super java.util.List<com.example.budgetapp.adapters.SelectableTransactionAdapter.SelectableTransaction>, kotlin.Unit> onSelectionChanged;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String ARG_TRANSACTION_TYPE = "transaction_type";
    @org.jetbrains.annotations.NotNull
    public static final com.example.budgetapp.fragments.TransactionListFragment.Companion Companion = null;
    
    public TransactionListFragment() {
        super();
    }
    
    private final com.example.budgetapp.databinding.FragmentTransactionListBinding getBinding() {
        return null;
    }
    
    @java.lang.Override
    public void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
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
    
    private final void setupRecyclerView() {
    }
    
    private final void setupSwipeRefresh() {
    }
    
    private final void loadTransactions() {
    }
    
    private final void updateEmptyState(boolean isEmpty) {
    }
    
    public final void selectAll() {
    }
    
    public final void clearSelection() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.budgetapp.adapters.SelectableTransactionAdapter.SelectableTransaction> getSelectedTransactions() {
        return null;
    }
    
    public final void filterTransactions(@org.jetbrains.annotations.NotNull
    java.lang.String query, boolean showRecurringOnly, boolean showThisMonthOnly) {
    }
    
    @java.lang.Override
    public void onDestroyView() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J(\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0018\u0010\t\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0004\u0012\u00020\r0\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/example/budgetapp/fragments/TransactionListFragment$Companion;", "", "()V", "ARG_TRANSACTION_TYPE", "", "newInstance", "Lcom/example/budgetapp/fragments/TransactionListFragment;", "transactionType", "Lcom/example/budgetapp/database/entities/TransactionType;", "onSelectionChanged", "Lkotlin/Function1;", "", "Lcom/example/budgetapp/adapters/SelectableTransactionAdapter$SelectableTransaction;", "", "CashInCashOut_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.budgetapp.fragments.TransactionListFragment newInstance(@org.jetbrains.annotations.NotNull
        com.example.budgetapp.database.entities.TransactionType transactionType, @org.jetbrains.annotations.NotNull
        kotlin.jvm.functions.Function1<? super java.util.List<com.example.budgetapp.adapters.SelectableTransactionAdapter.SelectableTransaction>, kotlin.Unit> onSelectionChanged) {
            return null;
        }
    }
}