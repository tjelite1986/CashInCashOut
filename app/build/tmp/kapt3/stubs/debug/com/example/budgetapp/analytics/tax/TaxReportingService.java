package com.example.budgetapp.analytics.tax;

/**
 * Service for automatic tax category reporting and deduction identification
 * Helps users categorize expenses for Swedish tax purposes
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u00a2\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 F2\u00020\u0001:\u0001FB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u000fH\u0002J.\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00130\u000e2\u0018\u0010\u0018\u001a\u0014\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00100\u000eH\u0002J/\u0010\u0019\u001a\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00102\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001eH\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010 J\u001e\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00112\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002J\u001b\u0010%\u001a\u0004\u0018\u00010&2\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\'J1\u0010(\u001a\u0014\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00100\u000e2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00150\u0010H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010*J!\u0010+\u001a\u00020\u00112\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00100J\u0010\u00101\u001a\u00020\u00112\u0006\u0010,\u001a\u00020-H\u0002J\u0010\u00102\u001a\u00020\u00112\u0006\u0010,\u001a\u00020-H\u0002J\u0010\u00103\u001a\u00020\u00112\u0006\u0010,\u001a\u00020-H\u0002J\u0010\u00104\u001a\u00020\u00112\u0006\u0010,\u001a\u00020-H\u0002J\u0016\u00105\u001a\u0002062\f\u00107\u001a\b\u0012\u0004\u0012\u0002080\u0010H\u0002J\u0018\u00109\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J<\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0018\u0010\u0018\u001a\u0014\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00100\u000e2\u0012\u0010;\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00130\u000eH\u0002J\u0019\u0010<\u001a\u00020-2\u0006\u0010=\u001a\u00020>H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010?J\u0017\u0010@\u001a\b\u0012\u0004\u0012\u00020A0\u0010H\u0082@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010BJ\u0017\u0010C\u001a\b\u0012\u0004\u0012\u00020A0\u0010H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010BJ\u001c\u0010D\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u001e0E2\u0006\u0010=\u001a\u00020>H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\r\u001a\u0014\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006G"}, d2 = {"Lcom/example/budgetapp/analytics/tax/TaxReportingService;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "database", "Lcom/example/budgetapp/database/BudgetDatabase;", "expenseDao", "Lcom/example/budgetapp/database/dao/ExpenseDao;", "incomeDao", "Lcom/example/budgetapp/database/dao/IncomeDao;", "investmentDao", "Lcom/example/budgetapp/analytics/investments/dao/InvestmentDao;", "taxDeductibleCategories", "", "Lcom/example/budgetapp/analytics/tax/TaxCategory;", "", "", "calculateDeductibleAmount", "", "expense", "Lcom/example/budgetapp/database/entities/Expense;", "category", "calculateDeductibleAmounts", "categorizedExpenses", "calculateInvestmentTaxInfo", "Lcom/example/budgetapp/analytics/tax/InvestmentTaxSummary;", "investments", "Lcom/example/budgetapp/analytics/investments/entities/Investment;", "startDate", "", "endDate", "(Ljava/util/List;JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "calculateKeywordMatchScore", "", "text", "keywords", "categorizeExpenseForTax", "Lcom/example/budgetapp/analytics/tax/TaxCategorization;", "(Lcom/example/budgetapp/database/entities/Expense;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "categorizeExpensesForTax", "expenses", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "exportTaxReport", "taxReport", "Lcom/example/budgetapp/analytics/tax/TaxReport;", "format", "Lcom/example/budgetapp/analytics/tax/ExportFormat;", "(Lcom/example/budgetapp/analytics/tax/TaxReport;Lcom/example/budgetapp/analytics/tax/ExportFormat;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "exportTaxReportToCsv", "exportTaxReportToExcel", "exportTaxReportToPdf", "exportTaxReportToSie", "generateIncomeSummary", "Lcom/example/budgetapp/analytics/tax/IncomeSummary;", "incomes", "Lcom/example/budgetapp/database/entities/Income;", "generateTaxNotes", "generateTaxOptimizationSuggestions", "deductibleAmounts", "generateTaxReport", "taxYear", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getInvestmentTaxSuggestions", "Lcom/example/budgetapp/analytics/tax/TaxOptimizationSuggestion;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTaxOptimizationSuggestions", "getTaxYearPeriod", "Lkotlin/Pair;", "Companion", "CashInCashOut_debug"})
public final class TaxReportingService {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.BudgetDatabase database = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.dao.ExpenseDao expenseDao = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.database.dao.IncomeDao incomeDao = null;
    @org.jetbrains.annotations.NotNull
    private final com.example.budgetapp.analytics.investments.dao.InvestmentDao investmentDao = null;
    public static final int TAX_YEAR_START_MONTH = 1;
    public static final int TAX_YEAR_START_DAY = 1;
    public static final int TAX_YEAR_END_MONTH = 12;
    public static final int TAX_YEAR_END_DAY = 31;
    @org.jetbrains.annotations.NotNull
    private final java.util.Map<com.example.budgetapp.analytics.tax.TaxCategory, java.util.List<java.lang.String>> taxDeductibleCategories = null;
    @org.jetbrains.annotations.NotNull
    public static final com.example.budgetapp.analytics.tax.TaxReportingService.Companion Companion = null;
    
    public TaxReportingService(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    /**
     * Generate comprehensive tax report for a given tax year
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object generateTaxReport(int taxYear, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.analytics.tax.TaxReport> $completion) {
        return null;
    }
    
    /**
     * Automatically categorize an expense for tax purposes
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object categorizeExpenseForTax(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.database.entities.Expense expense, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.analytics.tax.TaxCategorization> $completion) {
        return null;
    }
    
    /**
     * Get tax optimization suggestions
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getTaxOptimizationSuggestions(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.analytics.tax.TaxOptimizationSuggestion>> $completion) {
        return null;
    }
    
    /**
     * Export tax report to different formats
     */
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object exportTaxReport(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.tax.TaxReport taxReport, @org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.tax.ExportFormat format, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    private final kotlin.Pair<java.lang.Long, java.lang.Long> getTaxYearPeriod(int taxYear) {
        return null;
    }
    
    private final java.lang.Object categorizeExpensesForTax(java.util.List<com.example.budgetapp.database.entities.Expense> expenses, kotlin.coroutines.Continuation<? super java.util.Map<com.example.budgetapp.analytics.tax.TaxCategory, ? extends java.util.List<com.example.budgetapp.database.entities.Expense>>> $completion) {
        return null;
    }
    
    private final java.util.Map<com.example.budgetapp.analytics.tax.TaxCategory, java.lang.Double> calculateDeductibleAmounts(java.util.Map<com.example.budgetapp.analytics.tax.TaxCategory, ? extends java.util.List<com.example.budgetapp.database.entities.Expense>> categorizedExpenses) {
        return null;
    }
    
    private final double calculateDeductibleAmount(com.example.budgetapp.database.entities.Expense expense, com.example.budgetapp.analytics.tax.TaxCategory category) {
        return 0.0;
    }
    
    private final float calculateKeywordMatchScore(java.lang.String text, java.util.List<java.lang.String> keywords) {
        return 0.0F;
    }
    
    private final com.example.budgetapp.analytics.tax.IncomeSummary generateIncomeSummary(java.util.List<com.example.budgetapp.database.entities.Income> incomes) {
        return null;
    }
    
    private final java.lang.Object calculateInvestmentTaxInfo(java.util.List<com.example.budgetapp.analytics.investments.entities.Investment> investments, long startDate, long endDate, kotlin.coroutines.Continuation<? super com.example.budgetapp.analytics.tax.InvestmentTaxSummary> $completion) {
        return null;
    }
    
    private final java.util.List<java.lang.String> generateTaxOptimizationSuggestions(java.util.Map<com.example.budgetapp.analytics.tax.TaxCategory, ? extends java.util.List<com.example.budgetapp.database.entities.Expense>> categorizedExpenses, java.util.Map<com.example.budgetapp.analytics.tax.TaxCategory, java.lang.Double> deductibleAmounts) {
        return null;
    }
    
    private final java.lang.String generateTaxNotes(com.example.budgetapp.analytics.tax.TaxCategory category, com.example.budgetapp.database.entities.Expense expense) {
        return null;
    }
    
    private final java.lang.Object getInvestmentTaxSuggestions(kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.analytics.tax.TaxOptimizationSuggestion>> $completion) {
        return null;
    }
    
    private final java.lang.String exportTaxReportToCsv(com.example.budgetapp.analytics.tax.TaxReport taxReport) {
        return null;
    }
    
    private final java.lang.String exportTaxReportToPdf(com.example.budgetapp.analytics.tax.TaxReport taxReport) {
        return null;
    }
    
    private final java.lang.String exportTaxReportToExcel(com.example.budgetapp.analytics.tax.TaxReport taxReport) {
        return null;
    }
    
    private final java.lang.String exportTaxReportToSie(com.example.budgetapp.analytics.tax.TaxReport taxReport) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\b"}, d2 = {"Lcom/example/budgetapp/analytics/tax/TaxReportingService$Companion;", "", "()V", "TAX_YEAR_END_DAY", "", "TAX_YEAR_END_MONTH", "TAX_YEAR_START_DAY", "TAX_YEAR_START_MONTH", "CashInCashOut_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}