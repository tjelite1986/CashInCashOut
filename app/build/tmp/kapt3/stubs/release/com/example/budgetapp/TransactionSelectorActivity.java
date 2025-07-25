package com.example.budgetapp;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001:\u0001\u001bB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\n\u0010\u0010\u001a\u0004\u0018\u00010\tH\u0002J\u0012\u0010\u0011\u001a\u00020\u000f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u000fH\u0002J\b\u0010\u0015\u001a\u00020\u000fH\u0002J\b\u0010\u0016\u001a\u00020\u000fH\u0002J\b\u0010\u0017\u001a\u00020\u000fH\u0002J\b\u0010\u0018\u001a\u00020\u000fH\u0002J\u0010\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\rH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00060\u000bR\u00020\u0000X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/example/budgetapp/TransactionSelectorActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/budgetapp/databinding/ActivityTransactionSelectorBinding;", "database", "Lcom/example/budgetapp/database/BudgetDatabase;", "fragments", "", "Lcom/example/budgetapp/fragments/TransactionListFragment;", "pagerAdapter", "Lcom/example/budgetapp/TransactionSelectorActivity$TransactionPagerAdapter;", "selectedTransactionsCount", "", "applyFilters", "", "getCurrentFragment", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "saveSelectedTransactions", "setupClickListeners", "setupSearchAndFilters", "setupToolbar", "setupViewPager", "updateSelectionCount", "count", "TransactionPagerAdapter", "CashInCashOut_release"})
public final class TransactionSelectorActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.budgetapp.databinding.ActivityTransactionSelectorBinding binding;
    private com.example.budgetapp.database.BudgetDatabase database;
    private com.example.budgetapp.TransactionSelectorActivity.TransactionPagerAdapter pagerAdapter;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.example.budgetapp.fragments.TransactionListFragment> fragments = null;
    private int selectedTransactionsCount = 0;
    
    public TransactionSelectorActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupToolbar() {
    }
    
    private final void setupViewPager() {
    }
    
    private final void setupSearchAndFilters() {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void applyFilters() {
    }
    
    private final com.example.budgetapp.fragments.TransactionListFragment getCurrentFragment() {
        return null;
    }
    
    private final void updateSelectionCount(int count) {
    }
    
    private final void saveSelectedTransactions() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0018\u0010\u0004\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0004\u0012\u00020\b0\u0005\u00a2\u0006\u0002\u0010\tJ\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\rH\u0016R \u0010\u0004\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0004\u0012\u00020\b0\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/example/budgetapp/TransactionSelectorActivity$TransactionPagerAdapter;", "Landroidx/viewpager2/adapter/FragmentStateAdapter;", "fragmentActivity", "Landroidx/fragment/app/FragmentActivity;", "onSelectionChanged", "Lkotlin/Function1;", "", "Lcom/example/budgetapp/adapters/SelectableTransactionAdapter$SelectableTransaction;", "", "(Lcom/example/budgetapp/TransactionSelectorActivity;Landroidx/fragment/app/FragmentActivity;Lkotlin/jvm/functions/Function1;)V", "createFragment", "Landroidx/fragment/app/Fragment;", "position", "", "getItemCount", "CashInCashOut_release"})
    final class TransactionPagerAdapter extends androidx.viewpager2.adapter.FragmentStateAdapter {
        @org.jetbrains.annotations.NotNull
        private final kotlin.jvm.functions.Function1<java.util.List<com.example.budgetapp.adapters.SelectableTransactionAdapter.SelectableTransaction>, kotlin.Unit> onSelectionChanged = null;
        
        public TransactionPagerAdapter(@org.jetbrains.annotations.NotNull
        androidx.fragment.app.FragmentActivity fragmentActivity, @org.jetbrains.annotations.NotNull
        kotlin.jvm.functions.Function1<? super java.util.List<com.example.budgetapp.adapters.SelectableTransactionAdapter.SelectableTransaction>, kotlin.Unit> onSelectionChanged) {
            super(null);
        }
        
        @java.lang.Override
        public int getItemCount() {
            return 0;
        }
        
        @java.lang.Override
        @org.jetbrains.annotations.NotNull
        public androidx.fragment.app.Fragment createFragment(int position) {
            return null;
        }
    }
}