package com.example.budgetapp.analytics.investments.banking;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\r\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002J%\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u000fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012J!\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0007H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J3\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u000f2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\f0\u000f2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\f0\u000fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001cJ\u001f\u0010\u001d\u001a\u00020\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u000fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0012JI\u0010!\u001a\b\u0012\u0004\u0012\u00020\f0\u000f2\u0006\u0010\"\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\u00072\n\b\u0002\u0010$\u001a\u0004\u0018\u00010%2\n\b\u0002\u0010&\u001a\u0004\u0018\u00010%2\b\b\u0002\u0010\'\u001a\u00020(H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010)J\u001f\u0010*\u001a\b\u0012\u0004\u0012\u00020 0\u000f2\u0006\u0010\"\u001a\u00020\u0007H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010+J\b\u0010,\u001a\u00020\u0007H\u0002J\b\u0010-\u001a\u00020\u0007H\u0002J\b\u0010.\u001a\u00020\u0007H\u0002J\u0017\u0010/\u001a\b\u0012\u0004\u0012\u00020\b0\u000fH\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00100J!\u00101\u001a\u00020\u00142\u0006\u00102\u001a\u00020\u00072\u0006\u00103\u001a\u00020\u0007H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J%\u00104\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\n0\u00062\u0006\u0010\"\u001a\u00020\u0007H\u0086@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010+R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u00065"}, d2 = {"Lcom/example/budgetapp/analytics/investments/banking/BankingIntegrationService;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "supportedBanks", "", "", "Lcom/example/budgetapp/analytics/investments/banking/BankInfo;", "calculateTransactionSimilarity", "", "tx1", "Lcom/example/budgetapp/analytics/investments/banking/BankTransaction;", "tx2", "categorizeTransactions", "", "Lcom/example/budgetapp/analytics/investments/banking/CategorizedTransaction;", "transactions", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "completeBankConnection", "Lcom/example/budgetapp/analytics/investments/banking/BankConnectionResult;", "connectionId", "authorizationCode", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "detectDuplicateTransactions", "Lcom/example/budgetapp/analytics/investments/banking/DuplicateTransactionPair;", "newTransactions", "existingTransactions", "(Ljava/util/List;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateBankingInsights", "Lcom/example/budgetapp/analytics/investments/banking/BankingInsights;", "accounts", "Lcom/example/budgetapp/analytics/investments/banking/BankAccount;", "getBankTransactions", "accessToken", "accountId", "fromDate", "", "toDate", "limit", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Long;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getConnectedAccounts", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRandomCategory", "getRandomCounterparty", "getRandomTransactionDescription", "getSupportedBanks", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "initiateBankConnection", "bankCode", "userId", "syncAccountBalances", "CashInCashOut_debug"})
public final class BankingIntegrationService {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.Map<java.lang.String, com.example.budgetapp.analytics.investments.banking.BankInfo> supportedBanks = null;
    
    public BankingIntegrationService(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getSupportedBanks(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.analytics.investments.banking.BankInfo>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object initiateBankConnection(@org.jetbrains.annotations.NotNull
    java.lang.String bankCode, @org.jetbrains.annotations.NotNull
    java.lang.String userId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.analytics.investments.banking.BankConnectionResult> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object completeBankConnection(@org.jetbrains.annotations.NotNull
    java.lang.String connectionId, @org.jetbrains.annotations.NotNull
    java.lang.String authorizationCode, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.analytics.investments.banking.BankConnectionResult> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getConnectedAccounts(@org.jetbrains.annotations.NotNull
    java.lang.String accessToken, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.analytics.investments.banking.BankAccount>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object getBankTransactions(@org.jetbrains.annotations.NotNull
    java.lang.String accessToken, @org.jetbrains.annotations.NotNull
    java.lang.String accountId, @org.jetbrains.annotations.Nullable
    java.lang.Long fromDate, @org.jetbrains.annotations.Nullable
    java.lang.Long toDate, int limit, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.analytics.investments.banking.BankTransaction>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object syncAccountBalances(@org.jetbrains.annotations.NotNull
    java.lang.String accessToken, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.Map<java.lang.String, java.lang.Double>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object categorizeTransactions(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.analytics.investments.banking.BankTransaction> transactions, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.analytics.investments.banking.CategorizedTransaction>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object detectDuplicateTransactions(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.analytics.investments.banking.BankTransaction> newTransactions, @org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.analytics.investments.banking.BankTransaction> existingTransactions, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.analytics.investments.banking.DuplicateTransactionPair>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.Object generateBankingInsights(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.budgetapp.analytics.investments.banking.BankAccount> accounts, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.analytics.investments.banking.BankingInsights> $completion) {
        return null;
    }
    
    private final double calculateTransactionSimilarity(com.example.budgetapp.analytics.investments.banking.BankTransaction tx1, com.example.budgetapp.analytics.investments.banking.BankTransaction tx2) {
        return 0.0;
    }
    
    private final java.lang.String getRandomTransactionDescription() {
        return null;
    }
    
    private final java.lang.String getRandomCategory() {
        return null;
    }
    
    private final java.lang.String getRandomCounterparty() {
        return null;
    }
}