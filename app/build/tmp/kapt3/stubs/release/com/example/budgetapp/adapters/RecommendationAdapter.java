package com.example.budgetapp.adapters;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 \u001d2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0006\u001d\u001e\u001f !\"BA\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00060\u0004\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00060\u0004\u00a2\u0006\u0002\u0010\u000bJ\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0012H\u0016J\u0018\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0012H\u0016J\u0018\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0012H\u0016J\u000e\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0010J\b\u0010\u001c\u001a\u00020\u0006H\u0002R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/example/budgetapp/adapters/RecommendationAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "onStoreClick", "Lkotlin/Function1;", "Lcom/example/budgetapp/data/StoreRecommendation;", "", "onItemClick", "Lcom/example/budgetapp/data/ItemRecommendation;", "onPriceClick", "Lcom/example/budgetapp/data/PriceRecommendation;", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "displayItems", "", "Lcom/example/budgetapp/adapters/RecommendationAdapter$DisplayItem;", "recommendations", "Lcom/example/budgetapp/data/ShoppingListRecommendations;", "getItemCount", "", "getItemViewType", "position", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "submitRecommendations", "updateDisplayItems", "Companion", "DisplayItem", "DisplayItemDiffCallback", "HeaderViewHolder", "ItemViewHolder", "StoreViewHolder", "CashInCashOut_release"})
public final class RecommendationAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder> {
    @org.jetbrains.annotations.NotNull
    private final kotlin.jvm.functions.Function1<com.example.budgetapp.data.StoreRecommendation, kotlin.Unit> onStoreClick = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.jvm.functions.Function1<com.example.budgetapp.data.ItemRecommendation, kotlin.Unit> onItemClick = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.jvm.functions.Function1<com.example.budgetapp.data.PriceRecommendation, kotlin.Unit> onPriceClick = null;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_STORE = 1;
    private static final int TYPE_ITEM = 2;
    @org.jetbrains.annotations.Nullable
    private com.example.budgetapp.data.ShoppingListRecommendations recommendations;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.example.budgetapp.adapters.RecommendationAdapter.DisplayItem> displayItems = null;
    @org.jetbrains.annotations.NotNull
    public static final com.example.budgetapp.adapters.RecommendationAdapter.Companion Companion = null;
    
    public RecommendationAdapter(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.budgetapp.data.StoreRecommendation, kotlin.Unit> onStoreClick, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.budgetapp.data.ItemRecommendation, kotlin.Unit> onItemClick, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.budgetapp.data.PriceRecommendation, kotlin.Unit> onPriceClick) {
        super();
    }
    
    public final void submitRecommendations(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.data.ShoppingListRecommendations recommendations) {
    }
    
    private final void updateDisplayItems() {
    }
    
    @java.lang.Override
    public int getItemViewType(int position) {
        return 0;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public androidx.recyclerview.widget.RecyclerView.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    androidx.recyclerview.widget.RecyclerView.ViewHolder holder, int position) {
    }
    
    @java.lang.Override
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lcom/example/budgetapp/adapters/RecommendationAdapter$Companion;", "", "()V", "TYPE_HEADER", "", "TYPE_ITEM", "TYPE_STORE", "CashInCashOut_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n\u00a8\u0006\u000b"}, d2 = {"Lcom/example/budgetapp/adapters/RecommendationAdapter$DisplayItem;", "", "()V", "ItemHeader", "ItemItem", "StoreHeader", "StoreItem", "Lcom/example/budgetapp/adapters/RecommendationAdapter$DisplayItem$ItemHeader;", "Lcom/example/budgetapp/adapters/RecommendationAdapter$DisplayItem$ItemItem;", "Lcom/example/budgetapp/adapters/RecommendationAdapter$DisplayItem$StoreHeader;", "Lcom/example/budgetapp/adapters/RecommendationAdapter$DisplayItem$StoreItem;", "CashInCashOut_release"})
    public static abstract class DisplayItem {
        
        private DisplayItem() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/budgetapp/adapters/RecommendationAdapter$DisplayItem$ItemHeader;", "Lcom/example/budgetapp/adapters/RecommendationAdapter$DisplayItem;", "()V", "CashInCashOut_release"})
        public static final class ItemHeader extends com.example.budgetapp.adapters.RecommendationAdapter.DisplayItem {
            @org.jetbrains.annotations.NotNull
            public static final com.example.budgetapp.adapters.RecommendationAdapter.DisplayItem.ItemHeader INSTANCE = null;
            
            private ItemHeader() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/example/budgetapp/adapters/RecommendationAdapter$DisplayItem$ItemItem;", "Lcom/example/budgetapp/adapters/RecommendationAdapter$DisplayItem;", "item", "Lcom/example/budgetapp/data/ItemRecommendation;", "(Lcom/example/budgetapp/data/ItemRecommendation;)V", "getItem", "()Lcom/example/budgetapp/data/ItemRecommendation;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "CashInCashOut_release"})
        public static final class ItemItem extends com.example.budgetapp.adapters.RecommendationAdapter.DisplayItem {
            @org.jetbrains.annotations.NotNull
            private final com.example.budgetapp.data.ItemRecommendation item = null;
            
            public ItemItem(@org.jetbrains.annotations.NotNull
            com.example.budgetapp.data.ItemRecommendation item) {
            }
            
            @org.jetbrains.annotations.NotNull
            public final com.example.budgetapp.data.ItemRecommendation getItem() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull
            public final com.example.budgetapp.data.ItemRecommendation component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull
            public final com.example.budgetapp.adapters.RecommendationAdapter.DisplayItem.ItemItem copy(@org.jetbrains.annotations.NotNull
            com.example.budgetapp.data.ItemRecommendation item) {
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
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/example/budgetapp/adapters/RecommendationAdapter$DisplayItem$StoreHeader;", "Lcom/example/budgetapp/adapters/RecommendationAdapter$DisplayItem;", "()V", "CashInCashOut_release"})
        public static final class StoreHeader extends com.example.budgetapp.adapters.RecommendationAdapter.DisplayItem {
            @org.jetbrains.annotations.NotNull
            public static final com.example.budgetapp.adapters.RecommendationAdapter.DisplayItem.StoreHeader INSTANCE = null;
            
            private StoreHeader() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/example/budgetapp/adapters/RecommendationAdapter$DisplayItem$StoreItem;", "Lcom/example/budgetapp/adapters/RecommendationAdapter$DisplayItem;", "store", "Lcom/example/budgetapp/data/StoreRecommendation;", "(Lcom/example/budgetapp/data/StoreRecommendation;)V", "getStore", "()Lcom/example/budgetapp/data/StoreRecommendation;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "CashInCashOut_release"})
        public static final class StoreItem extends com.example.budgetapp.adapters.RecommendationAdapter.DisplayItem {
            @org.jetbrains.annotations.NotNull
            private final com.example.budgetapp.data.StoreRecommendation store = null;
            
            public StoreItem(@org.jetbrains.annotations.NotNull
            com.example.budgetapp.data.StoreRecommendation store) {
            }
            
            @org.jetbrains.annotations.NotNull
            public final com.example.budgetapp.data.StoreRecommendation getStore() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull
            public final com.example.budgetapp.data.StoreRecommendation component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull
            public final com.example.budgetapp.adapters.RecommendationAdapter.DisplayItem.StoreItem copy(@org.jetbrains.annotations.NotNull
            com.example.budgetapp.data.StoreRecommendation store) {
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
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0018\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\b\u0010\r\u001a\u00020\nH\u0016J\b\u0010\u000e\u001a\u00020\nH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/example/budgetapp/adapters/RecommendationAdapter$DisplayItemDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$Callback;", "oldList", "", "Lcom/example/budgetapp/adapters/RecommendationAdapter$DisplayItem;", "newList", "(Ljava/util/List;Ljava/util/List;)V", "areContentsTheSame", "", "oldItemPosition", "", "newItemPosition", "areItemsTheSame", "getNewListSize", "getOldListSize", "CashInCashOut_release"})
    static final class DisplayItemDiffCallback extends androidx.recyclerview.widget.DiffUtil.Callback {
        @org.jetbrains.annotations.NotNull
        private final java.util.List<com.example.budgetapp.adapters.RecommendationAdapter.DisplayItem> oldList = null;
        @org.jetbrains.annotations.NotNull
        private final java.util.List<com.example.budgetapp.adapters.RecommendationAdapter.DisplayItem> newList = null;
        
        public DisplayItemDiffCallback(@org.jetbrains.annotations.NotNull
        java.util.List<? extends com.example.budgetapp.adapters.RecommendationAdapter.DisplayItem> oldList, @org.jetbrains.annotations.NotNull
        java.util.List<? extends com.example.budgetapp.adapters.RecommendationAdapter.DisplayItem> newList) {
            super();
        }
        
        @java.lang.Override
        public int getOldListSize() {
            return 0;
        }
        
        @java.lang.Override
        public int getNewListSize() {
            return 0;
        }
        
        @java.lang.Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return false;
        }
        
        @java.lang.Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/example/budgetapp/adapters/RecommendationAdapter$HeaderViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/example/budgetapp/databinding/ItemRecommendationHeaderBinding;", "(Lcom/example/budgetapp/adapters/RecommendationAdapter;Lcom/example/budgetapp/databinding/ItemRecommendationHeaderBinding;)V", "bind", "", "title", "", "CashInCashOut_release"})
    public final class HeaderViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final com.example.budgetapp.databinding.ItemRecommendationHeaderBinding binding = null;
        
        public HeaderViewHolder(@org.jetbrains.annotations.NotNull
        com.example.budgetapp.databinding.ItemRecommendationHeaderBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull
        java.lang.String title) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/example/budgetapp/adapters/RecommendationAdapter$ItemViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/example/budgetapp/databinding/ItemRecommendationBinding;", "(Lcom/example/budgetapp/adapters/RecommendationAdapter;Lcom/example/budgetapp/databinding/ItemRecommendationBinding;)V", "bind", "", "itemRecommendation", "Lcom/example/budgetapp/data/ItemRecommendation;", "CashInCashOut_release"})
    public final class ItemViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final com.example.budgetapp.databinding.ItemRecommendationBinding binding = null;
        
        public ItemViewHolder(@org.jetbrains.annotations.NotNull
        com.example.budgetapp.databinding.ItemRecommendationBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull
        com.example.budgetapp.data.ItemRecommendation itemRecommendation) {
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/example/budgetapp/adapters/RecommendationAdapter$StoreViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/example/budgetapp/databinding/ItemRecommendationBinding;", "(Lcom/example/budgetapp/adapters/RecommendationAdapter;Lcom/example/budgetapp/databinding/ItemRecommendationBinding;)V", "bind", "", "storeRecommendation", "Lcom/example/budgetapp/data/StoreRecommendation;", "CashInCashOut_release"})
    public final class StoreViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final com.example.budgetapp.databinding.ItemRecommendationBinding binding = null;
        
        public StoreViewHolder(@org.jetbrains.annotations.NotNull
        com.example.budgetapp.databinding.ItemRecommendationBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull
        com.example.budgetapp.data.StoreRecommendation storeRecommendation) {
        }
    }
}