package com.example.budgetapp.analytics.investments.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0015\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J!\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0019\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u0019\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u0019\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0012H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J\u0019\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0016H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J\u0019\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u001aH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001bJ\u0014\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u001e0\u001dH\'J\u0014\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0\u001e0\u001dH\'J\u0014\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u001e0\u001dH\'J\u001c\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u001e0\u001d2\u0006\u0010\t\u001a\u00020\u0003H\'J\u001b\u0010#\u001a\u0004\u0018\u00010\u00162\u0006\u0010\t\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u0017\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u001eH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010&J\u001c\u0010\'\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u001e0\u001d2\u0006\u0010(\u001a\u00020)H\'J\u001c\u0010*\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u001e0\u001d2\u0006\u0010+\u001a\u00020,H\'J\u001c\u0010-\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u001e0\u001d2\u0006\u0010\u000f\u001a\u00020\u0003H\'J\u001b\u0010.\u001a\u0004\u0018\u00010/2\u0006\u0010\t\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u001b\u00100\u001a\u0004\u0018\u00010 2\u0006\u0010\u000f\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u0013\u00101\u001a\u0004\u0018\u000102H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010&J\u001c\u00103\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020/0\u001e0\u001d2\u0006\u0010\t\u001a\u00020\u0003H\'J\u001e\u00104\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u001e0\u001d2\b\b\u0002\u00105\u001a\u000206H\'J!\u00107\u001a\b\u0012\u0004\u0012\u00020\u00160\u001e2\b\b\u0002\u00105\u001a\u000206H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00108J#\u00109\u001a\u0004\u0018\u00010:2\u0006\u0010;\u001a\u00020\u00032\u0006\u0010<\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ+\u0010=\u001a\u0004\u0018\u00010:2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010;\u001a\u00020\u00032\u0006\u0010<\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010>J\u001c\u0010?\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u001e0\u001d2\u0006\u0010\t\u001a\u00020\u0003H\'J!\u0010@\u001a\b\u0012\u0004\u0012\u00020\u00160\u001e2\b\b\u0002\u00105\u001a\u000206H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u00108J\u0019\u0010A\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J\u0019\u0010B\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0016H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J\u0019\u0010C\u001a\u00020\u00032\u0006\u0010D\u001a\u00020 H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010EJ\u0019\u0010F\u001a\u00020\u00032\u0006\u0010G\u001a\u00020/H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010HJ\u0019\u0010I\u001a\u00020\u00032\u0006\u0010\u0019\u001a\u00020\u001aH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001bJ!\u0010J\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000bJ\u0019\u0010K\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0012H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0013J\u0019\u0010L\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0016H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0017J\u0019\u0010M\u001a\u00020\b2\u0006\u0010D\u001a\u00020 H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010EJ\u0019\u0010N\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u001aH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u001b\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006O"}, d2 = {"Lcom/example/budgetapp/analytics/investments/dao/InvestmentDao;", "", "addInvestmentToPortfolio", "", "portfolioInvestment", "Lcom/example/budgetapp/analytics/investments/entities/PortfolioInvestment;", "(Lcom/example/budgetapp/analytics/investments/entities/PortfolioInvestment;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cleanupOldPriceHistory", "", "investmentId", "cutoffDate", "(JJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deactivateInvestment", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deactivatePortfolio", "portfolioId", "deleteDividend", "dividend", "Lcom/example/budgetapp/analytics/investments/entities/InvestmentDividend;", "(Lcom/example/budgetapp/analytics/investments/entities/InvestmentDividend;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteInvestment", "investment", "Lcom/example/budgetapp/analytics/investments/entities/Investment;", "(Lcom/example/budgetapp/analytics/investments/entities/Investment;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteTransaction", "transaction", "Lcom/example/budgetapp/analytics/investments/entities/InvestmentTransaction;", "(Lcom/example/budgetapp/analytics/investments/entities/InvestmentTransaction;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllActiveInvestments", "Lkotlinx/coroutines/flow/Flow;", "", "getAllActivePortfolios", "Lcom/example/budgetapp/analytics/investments/entities/InvestmentPortfolio;", "getAllInvestments", "getDividendsForInvestment", "getInvestmentById", "getInvestmentTypeBreakdown", "Lcom/example/budgetapp/analytics/investments/dao/InvestmentTypeBreakdown;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getInvestmentsByCategory", "category", "Lcom/example/budgetapp/analytics/investments/entities/InvestmentCategory;", "getInvestmentsByType", "type", "Lcom/example/budgetapp/analytics/investments/entities/InvestmentType;", "getInvestmentsInPortfolio", "getLatestPriceForInvestment", "Lcom/example/budgetapp/analytics/investments/entities/InvestmentPriceHistory;", "getPortfolioById", "getPortfolioSummary", "Lcom/example/budgetapp/analytics/investments/dao/PortfolioSummary;", "getPriceHistoryForInvestment", "getRecentTransactions", "limit", "", "getTopPerformers", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTotalDividendIncomeForPeriod", "", "startDate", "endDate", "getTotalDividendsForPeriod", "(JJJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getTransactionsForInvestment", "getWorstPerformers", "insertDividend", "insertInvestment", "insertPortfolio", "portfolio", "(Lcom/example/budgetapp/analytics/investments/entities/InvestmentPortfolio;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertPriceHistoryEntry", "priceHistory", "(Lcom/example/budgetapp/analytics/investments/entities/InvestmentPriceHistory;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertTransaction", "removeInvestmentFromPortfolio", "updateDividend", "updateInvestment", "updatePortfolio", "updateTransaction", "CashInCashOut_debug"})
@androidx.room.Dao
public abstract interface InvestmentDao {
    
    @androidx.room.Query(value = "SELECT * FROM investments WHERE isActive = 1 ORDER BY name ASC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.analytics.investments.entities.Investment>> getAllActiveInvestments();
    
    @androidx.room.Query(value = "SELECT * FROM investments ORDER BY createdAt DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.analytics.investments.entities.Investment>> getAllInvestments();
    
    @androidx.room.Query(value = "SELECT * FROM investments WHERE id = :investmentId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getInvestmentById(long investmentId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.analytics.investments.entities.Investment> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM investments WHERE type = :type AND isActive = 1")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.analytics.investments.entities.Investment>> getInvestmentsByType(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.entities.InvestmentType type);
    
    @androidx.room.Query(value = "SELECT * FROM investments WHERE category = :category AND isActive = 1")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.analytics.investments.entities.Investment>> getInvestmentsByCategory(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.entities.InvestmentCategory category);
    
    @androidx.room.Insert
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertInvestment(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.entities.Investment investment, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateInvestment(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.entities.Investment investment, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteInvestment(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.entities.Investment investment, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE investments SET isActive = 0 WHERE id = :investmentId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deactivateInvestment(long investmentId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM investment_transactions WHERE investmentId = :investmentId ORDER BY transactionDate DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.analytics.investments.entities.InvestmentTransaction>> getTransactionsForInvestment(long investmentId);
    
    @androidx.room.Query(value = "SELECT * FROM investment_transactions ORDER BY transactionDate DESC LIMIT :limit")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.analytics.investments.entities.InvestmentTransaction>> getRecentTransactions(int limit);
    
    @androidx.room.Insert
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertTransaction(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.entities.InvestmentTransaction transaction, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateTransaction(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.entities.InvestmentTransaction transaction, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteTransaction(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.entities.InvestmentTransaction transaction, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM investment_price_history WHERE investmentId = :investmentId ORDER BY recordedAt DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.analytics.investments.entities.InvestmentPriceHistory>> getPriceHistoryForInvestment(long investmentId);
    
    @androidx.room.Query(value = "SELECT * FROM investment_price_history WHERE investmentId = :investmentId ORDER BY recordedAt DESC LIMIT 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getLatestPriceForInvestment(long investmentId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.analytics.investments.entities.InvestmentPriceHistory> $completion);
    
    @androidx.room.Insert
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertPriceHistoryEntry(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.entities.InvestmentPriceHistory priceHistory, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Query(value = "DELETE FROM investment_price_history WHERE investmentId = :investmentId AND recordedAt < :cutoffDate")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object cleanupOldPriceHistory(long investmentId, long cutoffDate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM investment_dividends WHERE investmentId = :investmentId ORDER BY paymentDate DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.analytics.investments.entities.InvestmentDividend>> getDividendsForInvestment(long investmentId);
    
    @androidx.room.Query(value = "SELECT SUM(totalAmount) FROM investment_dividends WHERE investmentId = :investmentId AND paymentDate >= :startDate AND paymentDate <= :endDate")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getTotalDividendsForPeriod(long investmentId, long startDate, long endDate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    @androidx.room.Insert
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertDividend(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.entities.InvestmentDividend dividend, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updateDividend(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.entities.InvestmentDividend dividend, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deleteDividend(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.entities.InvestmentDividend dividend, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM investment_portfolios WHERE isActive = 1 ORDER BY name ASC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.analytics.investments.entities.InvestmentPortfolio>> getAllActivePortfolios();
    
    @androidx.room.Query(value = "SELECT * FROM investment_portfolios WHERE id = :portfolioId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getPortfolioById(long portfolioId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.analytics.investments.entities.InvestmentPortfolio> $completion);
    
    @androidx.room.Insert
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertPortfolio(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.entities.InvestmentPortfolio portfolio, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Update
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object updatePortfolio(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.entities.InvestmentPortfolio portfolio, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "UPDATE investment_portfolios SET isActive = 0 WHERE id = :portfolioId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object deactivatePortfolio(long portfolioId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "\n        SELECT i.* FROM investments i \n        INNER JOIN portfolio_investments pi ON i.id = pi.investmentId \n        WHERE pi.portfolioId = :portfolioId AND i.isActive = 1\n        ORDER BY i.name ASC\n    ")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.example.budgetapp.analytics.investments.entities.Investment>> getInvestmentsInPortfolio(long portfolioId);
    
    @androidx.room.Insert
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object addInvestmentToPortfolio(@org.jetbrains.annotations.NotNull
    com.example.budgetapp.analytics.investments.entities.PortfolioInvestment portfolioInvestment, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion);
    
    @androidx.room.Query(value = "DELETE FROM portfolio_investments WHERE portfolioId = :portfolioId AND investmentId = :investmentId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object removeInvestmentFromPortfolio(long portfolioId, long investmentId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "\n        SELECT \n            type,\n            COUNT(*) as count,\n            SUM(currentValue * quantity) as totalValue,\n            SUM((currentValue - averageCost) * quantity) as totalGainLoss\n        FROM investments \n        WHERE isActive = 1 \n        GROUP BY type\n    ")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getInvestmentTypeBreakdown(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.analytics.investments.dao.InvestmentTypeBreakdown>> $completion);
    
    @androidx.room.Query(value = "\n        SELECT \n            SUM(currentValue * quantity) as totalValue,\n            SUM(initialValue * quantity) as totalCost,\n            SUM((currentValue - averageCost) * quantity) as totalGainLoss,\n            COUNT(*) as totalInvestments\n        FROM investments \n        WHERE isActive = 1\n    ")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getPortfolioSummary(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.budgetapp.analytics.investments.dao.PortfolioSummary> $completion);
    
    @androidx.room.Query(value = "\n        SELECT * FROM investments \n        WHERE isActive = 1 \n        ORDER BY ((currentValue - averageCost) / averageCost) DESC \n        LIMIT :limit\n    ")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getTopPerformers(int limit, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.analytics.investments.entities.Investment>> $completion);
    
    @androidx.room.Query(value = "\n        SELECT * FROM investments \n        WHERE isActive = 1 \n        ORDER BY ((currentValue - averageCost) / averageCost) ASC \n        LIMIT :limit\n    ")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getWorstPerformers(int limit, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.budgetapp.analytics.investments.entities.Investment>> $completion);
    
    @androidx.room.Query(value = "\n        SELECT SUM(totalAmount) FROM investment_dividends \n        WHERE paymentDate >= :startDate AND paymentDate <= :endDate\n    ")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getTotalDividendIncomeForPeriod(long startDate, long endDate, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.Double> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}