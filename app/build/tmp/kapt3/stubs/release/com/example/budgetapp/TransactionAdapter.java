package com.example.budgetapp;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001+B;\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\b\u0012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\u0002\u0010\u000bJ\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u0010H\u0016J\u0018\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\u0010H\u0002J\b\u0010\u001f\u001a\u00020\u0016H\u0016J\u0018\u0010 \u001a\u00020\t2\u0006\u0010!\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020\u0016H\u0016J\u0018\u0010#\u001a\u00020\u00022\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0016H\u0016J\u0018\u0010\'\u001a\u00020\t2\u0006\u0010!\u001a\u00020\u00022\u0006\u0010(\u001a\u00020\u0006H\u0002J\u0014\u0010)\u001a\u00020\t2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005R\u0016\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006,"}, d2 = {"Lcom/example/budgetapp/TransactionAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/budgetapp/TransactionAdapter$TransactionViewHolder;", "Lcom/example/budgetapp/utils/ThemeAware;", "transactions", "", "Lcom/example/budgetapp/Transaction;", "onEditClick", "Lkotlin/Function1;", "", "onDeleteClick", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "currencyFormat", "Ljava/text/NumberFormat;", "kotlin.jvm.PlatformType", "currentThemeSettings", "Lcom/example/budgetapp/data/ThemeSettings;", "dateFormat", "Ljava/text/SimpleDateFormat;", "themeManager", "Lcom/example/budgetapp/utils/ThemeManager;", "adjustColorAlpha", "", "color", "alpha", "", "applyTheme", "settings", "applyThemeToItem", "binding", "Lcom/example/budgetapp/databinding/ItemTransactionBinding;", "getItemCount", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "showContextMenu", "transaction", "updateTransactions", "newTransactions", "TransactionViewHolder", "CashInCashOut_release"})
public final class TransactionAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.budgetapp.TransactionAdapter.TransactionViewHolder> implements com.example.budgetapp.utils.ThemeAware {
    @org.jetbrains.annotations.NotNull
    private java.util.List<? extends com.example.budgetapp.Transaction> transactions;
    @org.jetbrains.annotations.NotNull
    private final kotlin.jvm.functions.Function1<com.example.budgetapp.Transaction, kotlin.Unit> onEditClick = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.jvm.functions.Function1<com.example.budgetapp.Transaction, kotlin.Unit> onDeleteClick = null;
    @org.jetbrains.annotations.Nullable
    private com.example.budgetapp.data.ThemeSettings currentThemeSettings;
    private com.example.budgetapp.utils.ThemeManager themeManager;
    private final java.text.NumberFormat currencyFormat = null;
    @org.jetbrains.annotations.NotNull
    private final java.text.SimpleDateFormat dateFormat = null;
    
    public TransactionAdapter(@org.jetbrains.annotations.NotNull
    java.util.List<? extends com.example.budgetapp.Transaction> transactions, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.budgetapp.Transaction, kotlin.Unit> onEditClick, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.budgetapp.Transaction, kotlin.Unit> onDeleteClick) {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.example.budgetapp.TransactionAdapter.TransactionViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.TransactionAdapter.TransactionViewHolder holder, int position) {
    }
    
    private final void showContextMenu(com.example.budgetapp.TransactionAdapter.TransactionViewHolder holder, com.example.budgetapp.Transaction transaction) {
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    public final void updateTransactions(@org.jetbrains.annotations.NotNull
    java.util.List<? extends com.example.budgetapp.Transaction> newTransactions) {
    }
    
    @java.lang.Override
    public void applyTheme(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.data.ThemeSettings settings) {
    }
    
    private final void applyThemeToItem(com.example.budgetapp.databinding.ItemTransactionBinding binding, com.example.budgetapp.data.ThemeSettings settings) {
    }
    
    private final int adjustColorAlpha(int color, float alpha) {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/example/budgetapp/TransactionAdapter$TransactionViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/example/budgetapp/databinding/ItemTransactionBinding;", "(Lcom/example/budgetapp/databinding/ItemTransactionBinding;)V", "getBinding", "()Lcom/example/budgetapp/databinding/ItemTransactionBinding;", "CashInCashOut_release"})
    public static final class TransactionViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final com.example.budgetapp.databinding.ItemTransactionBinding binding = null;
        
        public TransactionViewHolder(@org.jetbrains.annotations.NotNull
        com.example.budgetapp.databinding.ItemTransactionBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.budgetapp.databinding.ItemTransactionBinding getBinding() {
            return null;
        }
    }
}