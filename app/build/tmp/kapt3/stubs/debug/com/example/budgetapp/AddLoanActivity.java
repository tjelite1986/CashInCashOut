package com.example.budgetapp;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001a\u00020\u0014H\u0002J\u0012\u0010\u0015\u001a\u00020\u00142\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u0014H\u0002J\b\u0010\u0019\u001a\u00020\u0014H\u0002J\b\u0010\u001a\u001a\u00020\u0014H\u0002J\b\u0010\u001b\u001a\u00020\u0014H\u0002J\b\u0010\u001c\u001a\u00020\u0014H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\u0010\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/example/budgetapp/AddLoanActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/example/budgetapp/databinding/ActivityAddLoanBinding;", "database", "Lcom/example/budgetapp/database/BudgetDatabase;", "dateFormat", "Ljava/text/SimpleDateFormat;", "editingLoanId", "", "Ljava/lang/Long;", "isEditing", "", "originalLoan", "Lcom/example/budgetapp/database/entities/Loan;", "selectedDueDate", "selectedLoanType", "Lcom/example/budgetapp/database/entities/LoanType;", "loadLoanForEditing", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "saveLoan", "setInitialLoanType", "setupClickListeners", "setupUI", "showDatePicker", "CashInCashOut_debug"})
public final class AddLoanActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.example.budgetapp.databinding.ActivityAddLoanBinding binding;
    private com.example.budgetapp.database.BudgetDatabase database;
    @org.jetbrains.annotations.Nullable
    private java.lang.Long selectedDueDate;
    @org.jetbrains.annotations.NotNull
    private com.example.budgetapp.database.entities.LoanType selectedLoanType = com.example.budgetapp.database.entities.LoanType.BORROWED;
    @org.jetbrains.annotations.NotNull
    private final java.text.SimpleDateFormat dateFormat = null;
    @org.jetbrains.annotations.Nullable
    private java.lang.Long editingLoanId;
    private boolean isEditing = false;
    @org.jetbrains.annotations.Nullable
    private com.example.budgetapp.database.entities.Loan originalLoan;
    
    public AddLoanActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupUI() {
    }
    
    private final void setInitialLoanType() {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void showDatePicker() {
    }
    
    private final void saveLoan() {
    }
    
    private final void loadLoanForEditing() {
    }
}