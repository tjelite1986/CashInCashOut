package com.example.budgetapp.adapters;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0001\u0010B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J\u001c\u0010\b\u001a\u00020\u00062\n\u0010\t\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u001c\u0010\f\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000bH\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/example/budgetapp/adapters/InsightAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/example/budgetapp/database/entities/FinancialInsight;", "Lcom/example/budgetapp/adapters/InsightAdapter$InsightViewHolder;", "onInsightClick", "Lkotlin/Function1;", "", "(Lkotlin/jvm/functions/Function1;)V", "onBindViewHolder", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "InsightViewHolder", "CashInCashOut_debug"})
public final class InsightAdapter extends androidx.recyclerview.widget.ListAdapter<com.example.budgetapp.database.entities.FinancialInsight, com.example.budgetapp.adapters.InsightAdapter.InsightViewHolder> {
    @org.jetbrains.annotations.NotNull
    private final kotlin.jvm.functions.Function1<com.example.budgetapp.database.entities.FinancialInsight, kotlin.Unit> onInsightClick = null;
    
    public InsightAdapter(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.budgetapp.database.entities.FinancialInsight, kotlin.Unit> onInsightClick) {
        super(null);
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.example.budgetapp.adapters.InsightAdapter.InsightViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.adapters.InsightAdapter.InsightViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J!\u0010\r\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\fH\u0002\u00a2\u0006\u0002\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u001a\u0010\u001c\u001a\u00020\u00062\b\u0010\u001d\u001a\u0004\u0018\u00010\n2\u0006\u0010\u001e\u001a\u00020\u0018H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/example/budgetapp/adapters/InsightAdapter$InsightViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/example/budgetapp/databinding/ItemInsightBinding;", "(Lcom/example/budgetapp/adapters/InsightAdapter;Lcom/example/budgetapp/databinding/ItemInsightBinding;)V", "bind", "", "insight", "Lcom/example/budgetapp/database/entities/FinancialInsight;", "formatCurrency", "", "amount", "", "setupAmountDisplay", "percentage", "(Ljava/lang/Double;Ljava/lang/Double;)V", "setupConfidenceScore", "confidence", "", "setupInsightTypeIcon", "type", "Lcom/example/budgetapp/database/entities/InsightType;", "setupReadStatus", "isRead", "", "setupSeverityIndicator", "severity", "Lcom/example/budgetapp/database/entities/InsightSeverity;", "setupSuggestedAction", "suggestedAction", "actionable", "CashInCashOut_debug"})
    public final class InsightViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final com.example.budgetapp.databinding.ItemInsightBinding binding = null;
        
        public InsightViewHolder(@org.jetbrains.annotations.NotNull
        com.example.budgetapp.databinding.ItemInsightBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull
        com.example.budgetapp.database.entities.FinancialInsight insight) {
        }
        
        private final void setupSeverityIndicator(com.example.budgetapp.database.entities.InsightSeverity severity) {
        }
        
        private final void setupInsightTypeIcon(com.example.budgetapp.database.entities.InsightType type) {
        }
        
        private final void setupConfidenceScore(float confidence) {
        }
        
        private final void setupAmountDisplay(java.lang.Double amount, java.lang.Double percentage) {
        }
        
        private final void setupSuggestedAction(java.lang.String suggestedAction, boolean actionable) {
        }
        
        private final void setupReadStatus(boolean isRead) {
        }
        
        private final java.lang.String formatCurrency(double amount) {
            return null;
        }
    }
}