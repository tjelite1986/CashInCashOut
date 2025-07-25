package com.example.budgetapp.adapters;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u00012\u00020\u0004:\u0002\u001f B-\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\u0002\u0010\tJ\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0018\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0012H\u0002J\u0010\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u000bH\u0016J\u001c\u0010\u0017\u001a\u00020\u00072\n\u0010\u0018\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u000bH\u0002J\u001c\u0010\u0019\u001a\u00020\u00072\n\u0010\u0018\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u000fH\u0016J\u001c\u0010\u001b\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u000fH\u0016R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/example/budgetapp/adapters/BudgetAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/example/budgetapp/repository/BudgetProgress;", "Lcom/example/budgetapp/adapters/BudgetAdapter$BudgetViewHolder;", "Lcom/example/budgetapp/utils/ThemeAware;", "onEditClick", "Lkotlin/Function1;", "", "onDeleteClick", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "currentThemeSettings", "Lcom/example/budgetapp/data/ThemeSettings;", "themeManager", "Lcom/example/budgetapp/utils/ThemeManager;", "adjustColorAlpha", "", "color", "alpha", "", "adjustColorBrightness", "factor", "applyTheme", "settings", "applyThemeToItem", "holder", "onBindViewHolder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "BudgetDiffCallback", "BudgetViewHolder", "CashInCashOut_debug"})
public final class BudgetAdapter extends androidx.recyclerview.widget.ListAdapter<com.example.budgetapp.repository.BudgetProgress, com.example.budgetapp.adapters.BudgetAdapter.BudgetViewHolder> implements com.example.budgetapp.utils.ThemeAware {
    @org.jetbrains.annotations.NotNull
    private final kotlin.jvm.functions.Function1<com.example.budgetapp.repository.BudgetProgress, kotlin.Unit> onEditClick = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.jvm.functions.Function1<com.example.budgetapp.repository.BudgetProgress, kotlin.Unit> onDeleteClick = null;
    @org.jetbrains.annotations.Nullable
    private com.example.budgetapp.data.ThemeSettings currentThemeSettings;
    private com.example.budgetapp.utils.ThemeManager themeManager;
    
    public BudgetAdapter(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.budgetapp.repository.BudgetProgress, kotlin.Unit> onEditClick, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.budgetapp.repository.BudgetProgress, kotlin.Unit> onDeleteClick) {
        super(null);
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.example.budgetapp.adapters.BudgetAdapter.BudgetViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.adapters.BudgetAdapter.BudgetViewHolder holder, int position) {
    }
    
    @java.lang.Override
    public void applyTheme(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.data.ThemeSettings settings) {
    }
    
    private final void applyThemeToItem(com.example.budgetapp.adapters.BudgetAdapter.BudgetViewHolder holder, com.example.budgetapp.data.ThemeSettings settings) {
    }
    
    private final int adjustColorAlpha(int color, float alpha) {
        return 0;
    }
    
    private final int adjustColorBrightness(int color, float factor) {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/example/budgetapp/adapters/BudgetAdapter$BudgetDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/example/budgetapp/repository/BudgetProgress;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "CashInCashOut_debug"})
    public static final class BudgetDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.example.budgetapp.repository.BudgetProgress> {
        
        public BudgetDiffCallback() {
            super();
        }
        
        @java.lang.Override
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull
        com.example.budgetapp.repository.BudgetProgress oldItem, @org.jetbrains.annotations.NotNull
        com.example.budgetapp.repository.BudgetProgress newItem) {
            return false;
        }
        
        @java.lang.Override
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull
        com.example.budgetapp.repository.BudgetProgress oldItem, @org.jetbrains.annotations.NotNull
        com.example.budgetapp.repository.BudgetProgress newItem) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000e"}, d2 = {"Lcom/example/budgetapp/adapters/BudgetAdapter$BudgetViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/example/budgetapp/databinding/ItemBudgetBinding;", "(Lcom/example/budgetapp/adapters/BudgetAdapter;Lcom/example/budgetapp/databinding/ItemBudgetBinding;)V", "getBinding", "()Lcom/example/budgetapp/databinding/ItemBudgetBinding;", "bind", "", "budgetProgress", "Lcom/example/budgetapp/repository/BudgetProgress;", "getPeriodText", "", "period", "CashInCashOut_debug"})
    public final class BudgetViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final com.example.budgetapp.databinding.ItemBudgetBinding binding = null;
        
        public BudgetViewHolder(@org.jetbrains.annotations.NotNull
        com.example.budgetapp.databinding.ItemBudgetBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.budgetapp.databinding.ItemBudgetBinding getBinding() {
            return null;
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull
        com.example.budgetapp.repository.BudgetProgress budgetProgress) {
        }
        
        private final java.lang.String getPeriodText(java.lang.String period) {
            return null;
        }
    }
}