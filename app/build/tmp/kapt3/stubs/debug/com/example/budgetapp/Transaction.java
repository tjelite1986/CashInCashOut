package com.example.budgetapp;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0010\u0011\u0012B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\u0006J\u0006\u0010\n\u001a\u00020\bJ\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006J\u0006\u0010\f\u001a\u00020\u0006J\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u000e\u0082\u0001\u0003\u0013\u0014\u0015\u00a8\u0006\u0016"}, d2 = {"Lcom/example/budgetapp/Transaction;", "", "()V", "getAmount", "", "getCategoryName", "", "getDate", "", "getDescription", "getId", "getRecurringType", "getTitle", "isIncome", "", "isRecurring", "ExpenseTransaction", "IncomeTransaction", "LoanTransaction", "Lcom/example/budgetapp/Transaction$ExpenseTransaction;", "Lcom/example/budgetapp/Transaction$IncomeTransaction;", "Lcom/example/budgetapp/Transaction$LoanTransaction;", "CashInCashOut_debug"})
public abstract class Transaction {
    
    private Transaction() {
        super();
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
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getDescription() {
        return null;
    }
    
    public final long getDate() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getCategoryName() {
        return null;
    }
    
    public final boolean isRecurring() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getRecurringType() {
        return null;
    }
    
    public final boolean isIncome() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/example/budgetapp/Transaction$ExpenseTransaction;", "Lcom/example/budgetapp/Transaction;", "expense", "Lcom/example/budgetapp/database/entities/Expense;", "(Lcom/example/budgetapp/database/entities/Expense;)V", "getExpense", "()Lcom/example/budgetapp/database/entities/Expense;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "CashInCashOut_debug"})
    public static final class ExpenseTransaction extends com.example.budgetapp.Transaction {
        @org.jetbrains.annotations.NotNull
        private final com.example.budgetapp.database.entities.Expense expense = null;
        
        public ExpenseTransaction(@org.jetbrains.annotations.NotNull
        com.example.budgetapp.database.entities.Expense expense) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.budgetapp.database.entities.Expense getExpense() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.budgetapp.database.entities.Expense component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.budgetapp.Transaction.ExpenseTransaction copy(@org.jetbrains.annotations.NotNull
        com.example.budgetapp.database.entities.Expense expense) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/example/budgetapp/Transaction$IncomeTransaction;", "Lcom/example/budgetapp/Transaction;", "income", "Lcom/example/budgetapp/database/entities/Income;", "(Lcom/example/budgetapp/database/entities/Income;)V", "getIncome", "()Lcom/example/budgetapp/database/entities/Income;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "CashInCashOut_debug"})
    public static final class IncomeTransaction extends com.example.budgetapp.Transaction {
        @org.jetbrains.annotations.NotNull
        private final com.example.budgetapp.database.entities.Income income = null;
        
        public IncomeTransaction(@org.jetbrains.annotations.NotNull
        com.example.budgetapp.database.entities.Income income) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.budgetapp.database.entities.Income getIncome() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.budgetapp.database.entities.Income component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.budgetapp.Transaction.IncomeTransaction copy(@org.jetbrains.annotations.NotNull
        com.example.budgetapp.database.entities.Income income) {
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
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0010H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0011"}, d2 = {"Lcom/example/budgetapp/Transaction$LoanTransaction;", "Lcom/example/budgetapp/Transaction;", "loan", "Lcom/example/budgetapp/database/entities/Loan;", "(Lcom/example/budgetapp/database/entities/Loan;)V", "getLoan", "()Lcom/example/budgetapp/database/entities/Loan;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "CashInCashOut_debug"})
    public static final class LoanTransaction extends com.example.budgetapp.Transaction {
        @org.jetbrains.annotations.NotNull
        private final com.example.budgetapp.database.entities.Loan loan = null;
        
        public LoanTransaction(@org.jetbrains.annotations.NotNull
        com.example.budgetapp.database.entities.Loan loan) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.budgetapp.database.entities.Loan getLoan() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.budgetapp.database.entities.Loan component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.example.budgetapp.Transaction.LoanTransaction copy(@org.jetbrains.annotations.NotNull
        com.example.budgetapp.database.entities.Loan loan) {
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