package com.example.budgetapp;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J+\u0010)\u001a\u0004\u0018\u00010*2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010+\u001a\u00020\f2\u0006\u0010,\u001a\u00020*H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010-J!\u0010.\u001a\u00020/2\u0006\u0010+\u001a\u00020\f2\u0006\u00100\u001a\u00020\u001fH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00101J\u0010\u00102\u001a\u00020\u001f2\u0006\u00103\u001a\u00020\u0006H\u0002J\u0018\u00104\u001a\u00020/2\u0006\u00105\u001a\u00020#2\u0006\u00103\u001a\u00020\u0006H\u0002J\'\u00106\u001a\u00020/2\b\u0010+\u001a\u0004\u0018\u00010\f2\u0006\u00107\u001a\u00020#2\u0006\u00103\u001a\u00020\u0006H\u0002\u00a2\u0006\u0002\u00108J\b\u00109\u001a\u00020/H\u0002J\b\u0010:\u001a\u00020/H\u0002J\b\u0010;\u001a\u00020/H\u0002J\u0019\u0010<\u001a\u00020/2\u0006\u0010=\u001a\u00020\fH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010>J\u0012\u0010?\u001a\u00020/2\b\u0010@\u001a\u0004\u0018\u00010AH\u0014J\b\u0010B\u001a\u00020\u0014H\u0016J\b\u0010C\u001a\u00020/H\u0002J\u0010\u0010D\u001a\u00020/2\u0006\u00100\u001a\u00020\u001fH\u0002J!\u0010E\u001a\u00020/2\u0006\u0010=\u001a\u00020\f2\u0006\u0010F\u001a\u00020*H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010GJ\u0010\u0010H\u001a\u00020/2\u0006\u00100\u001a\u00020\u001fH\u0002J\u0010\u0010I\u001a\u00020/2\u0006\u00103\u001a\u00020\u0006H\u0002J\b\u0010J\u001a\u00020/H\u0002J\b\u0010K\u001a\u00020/H\u0002J\b\u0010L\u001a\u00020/H\u0002J\u0010\u0010M\u001a\u00020/2\u0006\u00103\u001a\u00020\u0006H\u0002J\b\u0010N\u001a\u00020/H\u0002J\u001e\u0010O\u001a\u00020/2\n\b\u0002\u0010P\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010Q\u001a\u00020RH\u0002J\u0010\u0010S\u001a\u00020/2\u0006\u0010T\u001a\u00020\u0006H\u0002J\b\u0010U\u001a\u00020/H\u0002J!\u0010V\u001a\u00020/2\u0006\u0010+\u001a\u00020\f2\u0006\u00103\u001a\u00020\u0006H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010WJ\b\u0010X\u001a\u00020/H\u0002J\u0010\u0010Y\u001a\u00020/2\u0006\u00103\u001a\u00020\u0006H\u0002J\u0010\u0010Z\u001a\u00020\u00142\u0006\u00103\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\rR\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\n \u0017*\u0004\u0018\u00010\u00160\u0016X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0018\u001a\u0010\u0012\f\u0012\n \u0017*\u0004\u0018\u00010\u001a0\u001a0\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001f0\u001eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010 \u001a\u0010\u0012\f\u0012\n \u0017*\u0004\u0018\u00010\u001a0\u001a0\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010!\u001a\u0010\u0012\f\u0012\n \u0017*\u0004\u0018\u00010\u001a0\u001a0\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020#X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010&\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\rR\u0010\u0010\'\u001a\u0004\u0018\u00010#X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006["}, d2 = {"Lcom/example/budgetapp/AddExpenseActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/budgetapp/databinding/ActivityAddExpenseBinding;", "currentReceiptDialog", "Lcom/example/budgetapp/databinding/DialogAddReceiptItemBinding;", "database", "Lcom/example/budgetapp/database/BudgetDatabase;", "dateFormat", "Ljava/text/SimpleDateFormat;", "editingExpenseId", "", "Ljava/lang/Long;", "existingProducts", "", "Lcom/example/budgetapp/database/entities/Product;", "existingStores", "Lcom/example/budgetapp/database/entities/Store;", "isEditing", "", "numberFormat", "Ljava/text/NumberFormat;", "kotlin.jvm.PlatformType", "receiptBarcodeScannerLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "receiptItemAdapter", "Lcom/example/budgetapp/adapters/ReceiptItemAdapter;", "receiptItems", "", "Lcom/example/budgetapp/data/ReceiptItemInput;", "receiptProductSelectorLauncher", "selectProductLauncher", "selectedCategory", "", "selectedDate", "selectedPaymentMethod", "selectedProductId", "selectedRecurringType", "userEditingDiscount", "calculateBestPriceForQuantity", "", "productId", "quantity", "(Lcom/example/budgetapp/database/BudgetDatabase;JDLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createProductStoreConnection", "", "item", "(JLcom/example/budgetapp/data/ReceiptItemInput;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createReceiptItemFromInput", "dialogBinding", "handleScannedBarcodeInReceipt", "barcode", "handleSelectedProductInReceipt", "productName", "(Ljava/lang/Long;Ljava/lang/String;Lcom/example/budgetapp/databinding/DialogAddReceiptItemBinding;)V", "loadExistingProducts", "loadExistingStores", "loadExpenseForEditing", "loadReceiptDataForEditing", "expenseId", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSupportNavigateUp", "saveExpense", "saveProductIfNew", "saveReceiptData", "totalAmount", "(JDLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveStoreIfNew", "setupChainDropdownForReceipt", "setupClickListeners", "setupReceiptFunctionality", "setupSpinners", "setupStoreDropdowns", "setupUI", "showAddReceiptItemDialog", "editItem", "editPosition", "", "showAddStoreDialogFromReceipt", "receiptDialogBinding", "showDatePicker", "updateReceiptPriceWithCampaignLogic", "(JLcom/example/budgetapp/databinding/DialogAddReceiptItemBinding;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateReceiptTotal", "updateTotalPreview", "validateReceiptItemInput", "CashInCashOut_debug"})
public final class AddExpenseActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.budgetapp.databinding.ActivityAddExpenseBinding binding;
    private com.example.budgetapp.database.BudgetDatabase database;
    private long selectedDate;
    @org.jetbrains.annotations.NotNull
    private java.lang.String selectedCategory = "Mat";
    @org.jetbrains.annotations.Nullable
    private java.lang.String selectedRecurringType;
    @org.jetbrains.annotations.NotNull
    private java.lang.String selectedPaymentMethod = "Kort";
    @org.jetbrains.annotations.NotNull
    private final java.text.SimpleDateFormat dateFormat = null;
    @org.jetbrains.annotations.Nullable
    private java.lang.Long editingExpenseId;
    private boolean isEditing = false;
    @org.jetbrains.annotations.Nullable
    private java.lang.Long selectedProductId;
    @org.jetbrains.annotations.NotNull
    private final androidx.activity.result.ActivityResultLauncher<android.content.Intent> selectProductLauncher = null;
    private com.example.budgetapp.adapters.ReceiptItemAdapter receiptItemAdapter;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.example.budgetapp.data.ReceiptItemInput> receiptItems = null;
    private final java.text.NumberFormat numberFormat = null;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.example.budgetapp.database.entities.Product> existingProducts;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.example.budgetapp.database.entities.Store> existingStores;
    @org.jetbrains.annotations.Nullable
    private com.example.budgetapp.databinding.DialogAddReceiptItemBinding currentReceiptDialog;
    private boolean userEditingDiscount = false;
    @org.jetbrains.annotations.NotNull
    private final androidx.activity.result.ActivityResultLauncher<android.content.Intent> receiptBarcodeScannerLauncher = null;
    @org.jetbrains.annotations.NotNull
    private final androidx.activity.result.ActivityResultLauncher<android.content.Intent> receiptProductSelectorLauncher = null;
    
    public AddExpenseActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupUI() {
    }
    
    private final void setupSpinners() {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void showDatePicker() {
    }
    
    private final void loadExpenseForEditing() {
    }
    
    private final void saveExpense() {
    }
    
    private final void setupReceiptFunctionality() {
    }
    
    private final void loadExistingProducts() {
    }
    
    private final void loadExistingStores() {
    }
    
    private final void showAddReceiptItemDialog(com.example.budgetapp.data.ReceiptItemInput editItem, int editPosition) {
    }
    
    private final void handleSelectedProductInReceipt(java.lang.Long productId, java.lang.String productName, com.example.budgetapp.databinding.DialogAddReceiptItemBinding dialogBinding) {
    }
    
    private final void handleScannedBarcodeInReceipt(java.lang.String barcode, com.example.budgetapp.databinding.DialogAddReceiptItemBinding dialogBinding) {
    }
    
    private final java.lang.Object updateReceiptPriceWithCampaignLogic(long productId, com.example.budgetapp.databinding.DialogAddReceiptItemBinding dialogBinding, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final void updateTotalPreview(com.example.budgetapp.databinding.DialogAddReceiptItemBinding dialogBinding) {
    }
    
    private final boolean validateReceiptItemInput(com.example.budgetapp.databinding.DialogAddReceiptItemBinding dialogBinding) {
        return false;
    }
    
    private final com.example.budgetapp.data.ReceiptItemInput createReceiptItemFromInput(com.example.budgetapp.databinding.DialogAddReceiptItemBinding dialogBinding) {
        return null;
    }
    
    private final void saveProductIfNew(com.example.budgetapp.data.ReceiptItemInput item) {
    }
    
    private final void saveStoreIfNew(com.example.budgetapp.data.ReceiptItemInput item) {
    }
    
    private final void updateReceiptTotal() {
    }
    
    private final java.lang.Object saveReceiptData(long expenseId, double totalAmount, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final void setupStoreDropdowns(com.example.budgetapp.databinding.DialogAddReceiptItemBinding dialogBinding) {
    }
    
    private final void setupChainDropdownForReceipt(com.example.budgetapp.databinding.DialogAddReceiptItemBinding dialogBinding) {
    }
    
    private final void showAddStoreDialogFromReceipt(com.example.budgetapp.databinding.DialogAddReceiptItemBinding receiptDialogBinding) {
    }
    
    private final java.lang.Object createProductStoreConnection(long productId, com.example.budgetapp.data.ReceiptItemInput item, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Beräknar bästa pris för en given kvantitet av en produkt.
     * Tar hänsyn till om kampanjpris är fördelaktigt baserat på kvantitet.
     *
     * Exempel: Vara kostar 13,95 + 1kr pant, kampanj "2 för 20kr"
     * - 1 vara: 13,95 + 1 = 14,95 kr
     * - 2 varor: 20 + 2 = 22 kr (kampanj + pant per vara)
     * - 3 varor: 20 + 13,95 + 3 = 36,95 kr (kampanj för 2 + ordinarie för 1 + pant för alla)
     */
    private final java.lang.Object calculateBestPriceForQuantity(com.example.budgetapp.database.BudgetDatabase database, long productId, double quantity, kotlin.coroutines.Continuation<? super java.lang.Double> $completion) {
        return null;
    }
    
    private final java.lang.Object loadReceiptDataForEditing(long expenseId, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override
    public boolean onSupportNavigateUp() {
        return false;
    }
}