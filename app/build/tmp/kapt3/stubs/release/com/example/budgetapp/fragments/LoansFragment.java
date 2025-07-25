package com.example.budgetapp.fragments;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\u0018\u00002\u00020\u0001:\u0001GB\u0005\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0018\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u001cH\u0002J \u0010\u001f\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\u00192\u0006\u0010!\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0019H\u0002J\u0010\u0010%\u001a\u00020#2\u0006\u0010&\u001a\u00020\'H\u0016J\u0010\u0010(\u001a\u00020#2\u0006\u0010&\u001a\u00020\'H\u0002J\b\u0010)\u001a\u00020#H\u0002J\u0010\u0010*\u001a\u00020#2\u0006\u0010+\u001a\u00020\u0007H\u0002J\u0010\u0010,\u001a\u00020#2\u0006\u0010+\u001a\u00020\u0007H\u0002J\b\u0010-\u001a\u00020#H\u0002J\b\u0010.\u001a\u00020#H\u0002J\b\u0010/\u001a\u00020#H\u0002J\u0010\u00100\u001a\u00020#2\u0006\u0010+\u001a\u00020\u0007H\u0002J$\u00101\u001a\u0002022\u0006\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u0001062\b\u00107\u001a\u0004\u0018\u000108H\u0016J\b\u00109\u001a\u00020#H\u0016J\b\u0010:\u001a\u00020#H\u0016J\b\u0010;\u001a\u00020#H\u0016J\u001a\u0010<\u001a\u00020#2\u0006\u0010=\u001a\u0002022\b\u00107\u001a\u0004\u0018\u000108H\u0016J\b\u0010>\u001a\u00020#H\u0002J\b\u0010?\u001a\u00020#H\u0002J\b\u0010@\u001a\u00020#H\u0002J\u0010\u0010A\u001a\u00020#2\u0006\u0010+\u001a\u00020\u0007H\u0002J\u0010\u0010B\u001a\u00020#2\u0006\u0010+\u001a\u00020\u0007H\u0002J\u0010\u0010C\u001a\u00020#2\u0006\u0010+\u001a\u00020\u0007H\u0002J\b\u0010D\u001a\u00020#H\u0002J\u0010\u0010E\u001a\u00020#2\u0006\u0010+\u001a\u00020\u0007H\u0002J\b\u0010F\u001a\u00020#H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006H"}, d2 = {"Lcom/example/budgetapp/fragments/LoansFragment;", "Lcom/example/budgetapp/fragments/BaseFragment;", "()V", "_binding", "Lcom/example/budgetapp/databinding/FragmentLoansBinding;", "allLoans", "", "Lcom/example/budgetapp/database/entities/Loan;", "binding", "getBinding", "()Lcom/example/budgetapp/databinding/FragmentLoansBinding;", "currencyFormat", "Ljava/text/NumberFormat;", "kotlin.jvm.PlatformType", "currentFilter", "Lcom/example/budgetapp/fragments/LoansFragment$FilterType;", "dataLoadingJob", "Lkotlinx/coroutines/Job;", "database", "Lcom/example/budgetapp/database/BudgetDatabase;", "isFabExpanded", "", "loanAdapter", "Lcom/example/budgetapp/LoanAdapter;", "adjustColorAlpha", "", "color", "alpha", "", "adjustColorBrightness", "factor", "adjustColorHue", "baseColor", "hueShift", "applyColorfulLoanCards", "", "accentColor", "applyCustomTheme", "settings", "Lcom/example/budgetapp/data/ThemeSettings;", "applyThemeToChips", "collapseFabMenu", "deleteLoan", "loan", "editLoan", "expandFabMenu", "filterLoans", "loadLoans", "markLoanAsPaidBack", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onPause", "onResume", "onViewCreated", "view", "setupChipFilters", "setupClickListeners", "setupRecyclerView", "showDeleteDialog", "showPayBackDialog", "showUndoPaymentDialog", "toggleFabMenu", "undoLoanPayment", "updateSummary", "FilterType", "CashInCashOut_release"})
public final class LoansFragment extends com.example.budgetapp.fragments.BaseFragment {
    @org.jetbrains.annotations.Nullable
    private com.example.budgetapp.databinding.FragmentLoansBinding _binding;
    private com.example.budgetapp.database.BudgetDatabase database;
    private com.example.budgetapp.LoanAdapter loanAdapter;
    private final java.text.NumberFormat currencyFormat = null;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.example.budgetapp.database.entities.Loan> allLoans;
    private boolean isFabExpanded = false;
    @org.jetbrains.annotations.Nullable
    private kotlinx.coroutines.Job dataLoadingJob;
    @org.jetbrains.annotations.NotNull
    private com.example.budgetapp.fragments.LoansFragment.FilterType currentFilter = com.example.budgetapp.fragments.LoansFragment.FilterType.ALL;
    
    public LoansFragment() {
        super();
    }
    
    private final com.example.budgetapp.databinding.FragmentLoansBinding getBinding() {
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
    
    private final void setupRecyclerView() {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void setupChipFilters() {
    }
    
    private final void toggleFabMenu() {
    }
    
    private final void expandFabMenu() {
    }
    
    private final void collapseFabMenu() {
    }
    
    private final void loadLoans() {
    }
    
    private final void updateSummary() {
    }
    
    private final void filterLoans() {
    }
    
    private final void editLoan(com.example.budgetapp.database.entities.Loan loan) {
    }
    
    private final void showDeleteDialog(com.example.budgetapp.database.entities.Loan loan) {
    }
    
    private final void showPayBackDialog(com.example.budgetapp.database.entities.Loan loan) {
    }
    
    private final void deleteLoan(com.example.budgetapp.database.entities.Loan loan) {
    }
    
    private final void markLoanAsPaidBack(com.example.budgetapp.database.entities.Loan loan) {
    }
    
    private final void showUndoPaymentDialog(com.example.budgetapp.database.entities.Loan loan) {
    }
    
    private final void undoLoanPayment(com.example.budgetapp.database.entities.Loan loan) {
    }
    
    @java.lang.Override
    public void onResume() {
    }
    
    @java.lang.Override
    public void onPause() {
    }
    
    @java.lang.Override
    public void onDestroyView() {
    }
    
    @java.lang.Override
    public void applyCustomTheme(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.data.ThemeSettings settings) {
    }
    
    private final void applyColorfulLoanCards(int accentColor) {
    }
    
    private final int adjustColorAlpha(int color, float alpha) {
        return 0;
    }
    
    private final void applyThemeToChips(com.example.budgetapp.data.ThemeSettings settings) {
    }
    
    private final int adjustColorBrightness(int color, float factor) {
        return 0;
    }
    
    private final int adjustColorHue(int baseColor, float hueShift, float alpha) {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2 = {"Lcom/example/budgetapp/fragments/LoansFragment$FilterType;", "", "(Ljava/lang/String;I)V", "ALL", "BORROWED", "LENT", "ACTIVE", "PAID_BACK", "CashInCashOut_release"})
    public static enum FilterType {
        /*public static final*/ ALL /* = new ALL() */,
        /*public static final*/ BORROWED /* = new BORROWED() */,
        /*public static final*/ LENT /* = new LENT() */,
        /*public static final*/ ACTIVE /* = new ACTIVE() */,
        /*public static final*/ PAID_BACK /* = new PAID_BACK() */;
        
        FilterType() {
        }
        
        @org.jetbrains.annotations.NotNull
        public static kotlin.enums.EnumEntries<com.example.budgetapp.fragments.LoansFragment.FilterType> getEntries() {
            return null;
        }
    }
}