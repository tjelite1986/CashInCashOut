package com.example.budgetapp;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0019B;\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007\u00a2\u0006\u0002\u0010\nJ\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u001c\u0010\u0012\u001a\u00020\b2\n\u0010\u0013\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0011H\u0016J\u001c\u0010\u0015\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0011H\u0016R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/example/budgetapp/IncomeAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/budgetapp/IncomeAdapter$IncomeViewHolder;", "incomes", "", "Lcom/example/budgetapp/database/entities/Income;", "onEditClick", "Lkotlin/Function1;", "", "onDeleteClick", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "currencyFormat", "Ljava/text/NumberFormat;", "kotlin.jvm.PlatformType", "dateFormat", "Ljava/text/SimpleDateFormat;", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "IncomeViewHolder", "budget-debug_debug"})
public final class IncomeAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.budgetapp.IncomeAdapter.IncomeViewHolder> {
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.example.budgetapp.database.entities.Income> incomes = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.jvm.functions.Function1<com.example.budgetapp.database.entities.Income, kotlin.Unit> onEditClick = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.jvm.functions.Function1<com.example.budgetapp.database.entities.Income, kotlin.Unit> onDeleteClick = null;
    @org.jetbrains.annotations.NotNull
    private final java.text.SimpleDateFormat dateFormat = null;
    private final java.text.NumberFormat currencyFormat = null;
    
    public IncomeAdapter(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.database.entities.Income> incomes, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.budgetapp.database.entities.Income, kotlin.Unit> onEditClick, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.budgetapp.database.entities.Income, kotlin.Unit> onDeleteClick) {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.example.budgetapp.IncomeAdapter.IncomeViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.IncomeAdapter.IncomeViewHolder holder, int position) {
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/example/budgetapp/IncomeAdapter$IncomeViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/example/budgetapp/IncomeAdapter;Landroid/view/View;)V", "btnDelete", "Landroid/widget/ImageButton;", "btnEdit", "cardView", "Lcom/google/android/material/card/MaterialCardView;", "tvAmount", "Landroid/widget/TextView;", "tvCategory", "tvDate", "tvDescription", "tvRecurring", "tvTitle", "bind", "", "income", "Lcom/example/budgetapp/database/entities/Income;", "budget-debug_debug"})
    public final class IncomeViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final com.google.android.material.card.MaterialCardView cardView = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView tvTitle = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView tvAmount = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView tvCategory = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView tvDate = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView tvDescription = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.TextView tvRecurring = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageButton btnEdit = null;
        @org.jetbrains.annotations.NotNull
        private final android.widget.ImageButton btnDelete = null;
        
        public IncomeViewHolder(@org.jetbrains.annotations.NotNull
        android.view.View itemView) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull
        com.example.budgetapp.database.entities.Income income) {
        }
    }
}