package com.example.budgetapp.adapters;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u00012\u00020\u0004:\u0002#$BA\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\u0002\u0010\nJ\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0018\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0013H\u0002J\u0018\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u0013H\u0002J\u0010\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\fH\u0016J$\u0010\u001a\u001a\u00020\u00072\n\u0010\u001b\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u0002H\u0002J\u001c\u0010\u001d\u001a\u00020\u00072\n\u0010\u001b\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u0010H\u0016J\u001c\u0010\u001f\u001a\u00060\u0003R\u00020\u00002\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0010H\u0016R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lcom/example/budgetapp/adapters/ShoppingListAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/example/budgetapp/database/entities/ShoppingList;", "Lcom/example/budgetapp/adapters/ShoppingListAdapter$ShoppingListViewHolder;", "Lcom/example/budgetapp/utils/ThemeAware;", "onItemClick", "Lkotlin/Function1;", "", "onDeleteClick", "onCompleteToggle", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "currentThemeSettings", "Lcom/example/budgetapp/data/ThemeSettings;", "themeManager", "Lcom/example/budgetapp/utils/ThemeManager;", "adjustColorAlpha", "", "color", "alpha", "", "adjustColorBrightness", "factor", "adjustColorHue", "hueShift", "applyTheme", "settings", "applyThemeToItem", "holder", "shoppingList", "onBindViewHolder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ShoppingListDiffCallback", "ShoppingListViewHolder", "CashInCashOut_release"})
public final class ShoppingListAdapter extends androidx.recyclerview.widget.ListAdapter<com.example.budgetapp.database.entities.ShoppingList, com.example.budgetapp.adapters.ShoppingListAdapter.ShoppingListViewHolder> implements com.example.budgetapp.utils.ThemeAware {
    @org.jetbrains.annotations.NotNull
    private final kotlin.jvm.functions.Function1<com.example.budgetapp.database.entities.ShoppingList, kotlin.Unit> onItemClick = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.jvm.functions.Function1<com.example.budgetapp.database.entities.ShoppingList, kotlin.Unit> onDeleteClick = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.jvm.functions.Function1<com.example.budgetapp.database.entities.ShoppingList, kotlin.Unit> onCompleteToggle = null;
    @org.jetbrains.annotations.Nullable
    private com.example.budgetapp.data.ThemeSettings currentThemeSettings;
    private com.example.budgetapp.utils.ThemeManager themeManager;
    
    public ShoppingListAdapter(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.budgetapp.database.entities.ShoppingList, kotlin.Unit> onItemClick, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.budgetapp.database.entities.ShoppingList, kotlin.Unit> onDeleteClick, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super com.example.budgetapp.database.entities.ShoppingList, kotlin.Unit> onCompleteToggle) {
        super(null);
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.example.budgetapp.adapters.ShoppingListAdapter.ShoppingListViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.adapters.ShoppingListAdapter.ShoppingListViewHolder holder, int position) {
    }
    
    @java.lang.Override
    public void applyTheme(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.data.ThemeSettings settings) {
    }
    
    private final void applyThemeToItem(com.example.budgetapp.adapters.ShoppingListAdapter.ShoppingListViewHolder holder, com.example.budgetapp.data.ThemeSettings settings, com.example.budgetapp.database.entities.ShoppingList shoppingList) {
    }
    
    private final int adjustColorAlpha(int color, float alpha) {
        return 0;
    }
    
    private final int adjustColorHue(int color, float hueShift) {
        return 0;
    }
    
    private final int adjustColorBrightness(int color, float factor) {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016\u00a8\u0006\t"}, d2 = {"Lcom/example/budgetapp/adapters/ShoppingListAdapter$ShoppingListDiffCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/example/budgetapp/database/entities/ShoppingList;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "CashInCashOut_release"})
    public static final class ShoppingListDiffCallback extends androidx.recyclerview.widget.DiffUtil.ItemCallback<com.example.budgetapp.database.entities.ShoppingList> {
        
        public ShoppingListDiffCallback() {
            super();
        }
        
        @java.lang.Override
        public boolean areItemsTheSame(@org.jetbrains.annotations.NotNull
        com.example.budgetapp.database.entities.ShoppingList oldItem, @org.jetbrains.annotations.NotNull
        com.example.budgetapp.database.entities.ShoppingList newItem) {
            return false;
        }
        
        @java.lang.Override
        public boolean areContentsTheSame(@org.jetbrains.annotations.NotNull
        com.example.budgetapp.database.entities.ShoppingList oldItem, @org.jetbrains.annotations.NotNull
        com.example.budgetapp.database.entities.ShoppingList newItem) {
            return false;
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u000b"}, d2 = {"Lcom/example/budgetapp/adapters/ShoppingListAdapter$ShoppingListViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/example/budgetapp/databinding/ItemShoppingListBinding;", "(Lcom/example/budgetapp/adapters/ShoppingListAdapter;Lcom/example/budgetapp/databinding/ItemShoppingListBinding;)V", "getBinding", "()Lcom/example/budgetapp/databinding/ItemShoppingListBinding;", "bind", "", "shoppingList", "Lcom/example/budgetapp/database/entities/ShoppingList;", "CashInCashOut_release"})
    public final class ShoppingListViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull
        private final com.example.budgetapp.databinding.ItemShoppingListBinding binding = null;
        
        public ShoppingListViewHolder(@org.jetbrains.annotations.NotNull
        com.example.budgetapp.databinding.ItemShoppingListBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.budgetapp.databinding.ItemShoppingListBinding getBinding() {
            return null;
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull
        com.example.budgetapp.database.entities.ShoppingList shoppingList) {
        }
    }
}