package com.example.budgetapp.adapters;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0010\t\n\u0002\b\u0006\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0003\u001c\u001d\u001eB\u001f\u0012\u0018\u0010\u0004\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u00a2\u0006\u0002\u0010\bJ\u0006\u0010\u000b\u001a\u00020\u0007J\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006J\u0018\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0010H\u0016J\u0006\u0010\u0015\u001a\u00020\u0007J\u0014\u0010\u0016\u001a\u00020\u00072\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018J\u0010\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u0002H\u0002R \u0010\u0004\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/example/budgetapp/adapters/SelectableTransactionAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/example/budgetapp/adapters/SelectableTransactionAdapter$SelectableTransaction;", "Lcom/example/budgetapp/adapters/SelectableTransactionAdapter$ViewHolder;", "onSelectionChanged", "Lkotlin/Function1;", "", "", "(Lkotlin/jvm/functions/Function1;)V", "selectedTransactions", "", "clearSelection", "getSelectedTransactions", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "selectAll", "setInitialSelection", "selectedIds", "", "", "toggleSelection", "selectableTransaction", "DiffCallback", "SelectableTransaction", "ViewHolder", "CashInCashOut_debug"})
public final class SelectableTransactionAdapter extends androidx.recyclerview.widget.ListAdapter<com.example.budgetapp.adapters.SelectableTransactionAdapter.SelectableTransaction, com.example.budgetapp.adapters.SelectableTransactionAdapter.ViewHolder> {
    @org.jetbrains.annotations.NotNull
    private final kotlin.jvm.functions.Function1<java.util.List<com.example.budgetapp.adapters.SelectableTransactionAdapter.SelectableTransaction>, kotlin.Unit> onSelectionChanged = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.Set<com.example.budgetapp.adapters.SelectableTransactionAdapter.SelectableTransaction> selectedTransactions = null;
    
    public SelectableTransactionAdapter(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.util.List<com.example.budgetapp.adapters.SelectableTransactionAdapter.SelectableTransaction>, kotlin.Unit> onSelectionChanged) {
        super(null);
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.example.budgetapp.adapters.SelectableTransactionAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.adapters.SelectableTransactionAdapter.ViewHolder holder, int position) {
    }
    
    private final void toggleSelection(com.example.budgetapp.adapters.SelectableTransactionAdapter.SelectableTransaction selectableTransaction) {
    }
    
    public final void selectAll() {
    }
    
    public final void clearSelection() {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.example.budgetapp.adapters.SelectableTransactionAdapter.SelectableTransaction> getSelectedTransactions() {
        return null;
    }
    
    public final void setInitialSelection(@org.jetbrains.annotations.NotNull
    java.util.Set<java.lang.Long> selectedIds) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/example/budgetapp/adapters/SelectableTransactionAdapter$DiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/example/budgetapp/adapters/SelectableTransactionAdapter$SelectableTransaction;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "CashInCashOut_debug"})
    static final class DiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.example.budgetapp.adapters.SelectableTransactionAdapter.SelectableTransaction> {
        
        public DiffCallback() {
            super();
        }
        
        @java.lang.Override
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull
        com.example.budgetapp.adapters.SelectableTransactionAdapter.SelectableTransaction oldItem, @org.jetbrains.annotations.NotNull
        com.example.budgetapp.adapters.SelectableTransactionAdapter.SelectableTransaction newItem) {
            return false;
        }
        
        @java.lang.Override
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull
        com.example.budgetapp.adapters.SelectableTransactionAdapter.SelectableTransaction oldItem, @org.jetbrains.annotations.NotNull
        com.example.budgetapp.adapters.SelectableTransactionAdapter.SelectableTransaction newItem) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0019\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010%\u001a\u00020\u0003H\u00c6\u0003J\t\u0010&\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\'\u001a\u00020\u0007H\u00c6\u0003J\'\u0010(\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010)\u001a\u00020\u00072\b\u0010*\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010+\u001a\u00020,H\u00d6\u0001J\t\u0010-\u001a\u00020\u000eH\u00d6\u0001R\u0011\u0010\t\u001a\u00020\n8F\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e8F\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u00128F\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u000e8F\u00a2\u0006\u0006\u001a\u0004\b\u0016\u0010\u0010R\u0011\u0010\u0017\u001a\u00020\u00128F\u00a2\u0006\u0006\u001a\u0004\b\u0018\u0010\u0014R\u0011\u0010\u0019\u001a\u00020\u00078F\u00a2\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u000e8F\u00a2\u0006\u0006\u001a\u0004\b\u001e\u0010\u0010R\u0011\u0010\u001f\u001a\u00020\u000e8F\u00a2\u0006\u0006\u001a\u0004\b \u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$\u00a8\u0006."}, d2 = {"Lcom/example/budgetapp/adapters/SelectableTransactionAdapter$SelectableTransaction;", "", "transaction", "Lcom/example/budgetapp/Transaction;", "transactionType", "Lcom/example/budgetapp/database/entities/TransactionType;", "isSelected", "", "(Lcom/example/budgetapp/Transaction;Lcom/example/budgetapp/database/entities/TransactionType;Z)V", "amount", "", "getAmount", "()D", "category", "", "getCategory", "()Ljava/lang/String;", "date", "", "getDate", "()J", "description", "getDescription", "id", "getId", "isRecurring", "()Z", "setSelected", "(Z)V", "recurringType", "getRecurringType", "title", "getTitle", "getTransaction", "()Lcom/example/budgetapp/Transaction;", "getTransactionType", "()Lcom/example/budgetapp/database/entities/TransactionType;", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "CashInCashOut_debug"})
    public static final class SelectableTransaction {
        @org.jetbrains.annotations.NotNull
        private final com.example.budgetapp.Transaction transaction = null;
        @org.jetbrains.annotations.NotNull
        private final com.example.budgetapp.database.entities.TransactionType transactionType = null;
        private boolean isSelected;
        
        public SelectableTransaction(@org.jetbrains.annotations.NotNull
        com.example.budgetapp.Transaction transaction, @org.jetbrains.annotations.NotNull
        com.example.budgetapp.database.entities.TransactionType transactionType, boolean isSelected) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.budgetapp.Transaction getTransaction() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.budgetapp.database.entities.TransactionType getTransactionType() {
            return null;
        }
        
        public final boolean isSelected() {
            return false;
        }
        
        public final void setSelected(boolean p0) {
        }
        
        public final long getId() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getTitle() {
            return null;
        }
        
        public final double getAmount() {
            return 0.0;
        }
        
        public final long getDate() {
            return 0L;
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getCategory() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getDescription() {
            return null;
        }
        
        public final boolean isRecurring() {
            return false;
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.String getRecurringType() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.budgetapp.Transaction component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.budgetapp.database.entities.TransactionType component2() {
            return null;
        }
        
        public final boolean component3() {
            return false;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.budgetapp.adapters.SelectableTransactionAdapter.SelectableTransaction copy(@org.jetbrains.annotations.NotNull
        com.example.budgetapp.Transaction transaction, @org.jetbrains.annotations.NotNull
        com.example.budgetapp.database.entities.TransactionType transactionType, boolean isSelected) {
            return null;
        }
        
        @java.lang.Override
        public boolean equals(@org.jetbrains.annotations.Nullable
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override
        @org.jetbrains.annotations.NotNull
        public java.lang.String toString() {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u00a2\u0006\u0002\u0010\tJ\u000e\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/example/budgetapp/adapters/SelectableTransactionAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/example/budgetapp/databinding/ItemSelectableTransactionBinding;", "onItemClick", "Lkotlin/Function1;", "Lcom/example/budgetapp/adapters/SelectableTransactionAdapter$SelectableTransaction;", "", "onCheckboxClick", "(Lcom/example/budgetapp/databinding/ItemSelectableTransactionBinding;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "dateFormat", "Ljava/text/SimpleDateFormat;", "numberFormat", "Ljava/text/NumberFormat;", "kotlin.jvm.PlatformType", "bind", "selectableTransaction", "CashInCashOut_debug"})
    public static final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final com.example.budgetapp.databinding.ItemSelectableTransactionBinding binding = null;
        @org.jetbrains.annotations.NotNull
        private final kotlin.jvm.functions.Function1<com.example.budgetapp.adapters.SelectableTransactionAdapter.SelectableTransaction, kotlin.Unit> onItemClick = null;
        @org.jetbrains.annotations.NotNull
        private final kotlin.jvm.functions.Function1<com.example.budgetapp.adapters.SelectableTransactionAdapter.SelectableTransaction, kotlin.Unit> onCheckboxClick = null;
        private final java.text.NumberFormat numberFormat = null;
        @org.jetbrains.annotations.NotNull
        private final java.text.SimpleDateFormat dateFormat = null;
        
        public ViewHolder(@org.jetbrains.annotations.NotNull
        com.example.budgetapp.databinding.ItemSelectableTransactionBinding binding, @org.jetbrains.annotations.NotNull
        kotlin.jvm.functions.Function1<? super com.example.budgetapp.adapters.SelectableTransactionAdapter.SelectableTransaction, kotlin.Unit> onItemClick, @org.jetbrains.annotations.NotNull
        kotlin.jvm.functions.Function1<? super com.example.budgetapp.adapters.SelectableTransactionAdapter.SelectableTransaction, kotlin.Unit> onCheckboxClick) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull
        com.example.budgetapp.adapters.SelectableTransactionAdapter.SelectableTransaction selectableTransaction) {
        }
    }
}