package com.example.budgetapp.analytics.investments.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.budgetapp.analytics.investments.entities.Investment;
import com.example.budgetapp.analytics.investments.entities.InvestmentCategory;
import com.example.budgetapp.analytics.investments.entities.InvestmentDividend;
import com.example.budgetapp.analytics.investments.entities.InvestmentPortfolio;
import com.example.budgetapp.analytics.investments.entities.InvestmentPriceHistory;
import com.example.budgetapp.analytics.investments.entities.InvestmentTransaction;
import com.example.budgetapp.analytics.investments.entities.InvestmentTransactionType;
import com.example.budgetapp.analytics.investments.entities.InvestmentType;
import com.example.budgetapp.analytics.investments.entities.PortfolioInvestment;
import com.example.budgetapp.analytics.investments.entities.RiskProfile;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class InvestmentDao_Impl implements InvestmentDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Investment> __insertionAdapterOfInvestment;

  private final EntityInsertionAdapter<InvestmentTransaction> __insertionAdapterOfInvestmentTransaction;

  private final EntityInsertionAdapter<InvestmentPriceHistory> __insertionAdapterOfInvestmentPriceHistory;

  private final EntityInsertionAdapter<InvestmentDividend> __insertionAdapterOfInvestmentDividend;

  private final EntityInsertionAdapter<InvestmentPortfolio> __insertionAdapterOfInvestmentPortfolio;

  private final EntityInsertionAdapter<PortfolioInvestment> __insertionAdapterOfPortfolioInvestment;

  private final EntityDeletionOrUpdateAdapter<Investment> __deletionAdapterOfInvestment;

  private final EntityDeletionOrUpdateAdapter<InvestmentTransaction> __deletionAdapterOfInvestmentTransaction;

  private final EntityDeletionOrUpdateAdapter<InvestmentDividend> __deletionAdapterOfInvestmentDividend;

  private final EntityDeletionOrUpdateAdapter<Investment> __updateAdapterOfInvestment;

  private final EntityDeletionOrUpdateAdapter<InvestmentTransaction> __updateAdapterOfInvestmentTransaction;

  private final EntityDeletionOrUpdateAdapter<InvestmentDividend> __updateAdapterOfInvestmentDividend;

  private final EntityDeletionOrUpdateAdapter<InvestmentPortfolio> __updateAdapterOfInvestmentPortfolio;

  private final SharedSQLiteStatement __preparedStmtOfDeactivateInvestment;

  private final SharedSQLiteStatement __preparedStmtOfCleanupOldPriceHistory;

  private final SharedSQLiteStatement __preparedStmtOfDeactivatePortfolio;

  private final SharedSQLiteStatement __preparedStmtOfRemoveInvestmentFromPortfolio;

  public InvestmentDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfInvestment = new EntityInsertionAdapter<Investment>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `investments` (`id`,`name`,`symbol`,`type`,`initialValue`,`currentValue`,`quantity`,`averageCost`,`purchaseDate`,`currency`,`broker`,`category`,`notes`,`isActive`,`lastUpdated`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Investment entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getSymbol() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getSymbol());
        }
        statement.bindString(4, __InvestmentType_enumToString(entity.getType()));
        statement.bindDouble(5, entity.getInitialValue());
        statement.bindDouble(6, entity.getCurrentValue());
        statement.bindDouble(7, entity.getQuantity());
        statement.bindDouble(8, entity.getAverageCost());
        statement.bindLong(9, entity.getPurchaseDate());
        if (entity.getCurrency() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getCurrency());
        }
        if (entity.getBroker() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getBroker());
        }
        statement.bindString(12, __InvestmentCategory_enumToString(entity.getCategory()));
        if (entity.getNotes() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getNotes());
        }
        final int _tmp = entity.isActive() ? 1 : 0;
        statement.bindLong(14, _tmp);
        statement.bindLong(15, entity.getLastUpdated());
        statement.bindLong(16, entity.getCreatedAt());
      }
    };
    this.__insertionAdapterOfInvestmentTransaction = new EntityInsertionAdapter<InvestmentTransaction>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `investment_transactions` (`id`,`investmentId`,`type`,`quantity`,`pricePerUnit`,`totalAmount`,`fees`,`transactionDate`,`notes`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final InvestmentTransaction entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getInvestmentId());
        statement.bindString(3, __InvestmentTransactionType_enumToString(entity.getType()));
        statement.bindDouble(4, entity.getQuantity());
        statement.bindDouble(5, entity.getPricePerUnit());
        statement.bindDouble(6, entity.getTotalAmount());
        statement.bindDouble(7, entity.getFees());
        statement.bindLong(8, entity.getTransactionDate());
        if (entity.getNotes() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getNotes());
        }
        statement.bindLong(10, entity.getCreatedAt());
      }
    };
    this.__insertionAdapterOfInvestmentPriceHistory = new EntityInsertionAdapter<InvestmentPriceHistory>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `investment_price_history` (`id`,`investmentId`,`price`,`currency`,`recordedAt`,`source`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final InvestmentPriceHistory entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getInvestmentId());
        statement.bindDouble(3, entity.getPrice());
        if (entity.getCurrency() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCurrency());
        }
        statement.bindLong(5, entity.getRecordedAt());
        if (entity.getSource() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getSource());
        }
      }
    };
    this.__insertionAdapterOfInvestmentDividend = new EntityInsertionAdapter<InvestmentDividend>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `investment_dividends` (`id`,`investmentId`,`amountPerShare`,`totalAmount`,`currency`,`paymentDate`,`exDividendDate`,`taxWithheld`,`notes`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final InvestmentDividend entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getInvestmentId());
        statement.bindDouble(3, entity.getAmountPerShare());
        statement.bindDouble(4, entity.getTotalAmount());
        if (entity.getCurrency() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getCurrency());
        }
        statement.bindLong(6, entity.getPaymentDate());
        if (entity.getExDividendDate() == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.getExDividendDate());
        }
        statement.bindDouble(8, entity.getTaxWithheld());
        if (entity.getNotes() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getNotes());
        }
        statement.bindLong(10, entity.getCreatedAt());
      }
    };
    this.__insertionAdapterOfInvestmentPortfolio = new EntityInsertionAdapter<InvestmentPortfolio>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `investment_portfolios` (`id`,`name`,`description`,`targetAllocation`,`riskProfile`,`isActive`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final InvestmentPortfolio entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDescription());
        }
        if (entity.getTargetAllocation() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getTargetAllocation());
        }
        statement.bindString(5, __RiskProfile_enumToString(entity.getRiskProfile()));
        final int _tmp = entity.isActive() ? 1 : 0;
        statement.bindLong(6, _tmp);
        statement.bindLong(7, entity.getCreatedAt());
      }
    };
    this.__insertionAdapterOfPortfolioInvestment = new EntityInsertionAdapter<PortfolioInvestment>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `portfolio_investments` (`id`,`portfolioId`,`investmentId`,`targetPercentage`,`addedAt`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PortfolioInvestment entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getPortfolioId());
        statement.bindLong(3, entity.getInvestmentId());
        if (entity.getTargetPercentage() == null) {
          statement.bindNull(4);
        } else {
          statement.bindDouble(4, entity.getTargetPercentage());
        }
        statement.bindLong(5, entity.getAddedAt());
      }
    };
    this.__deletionAdapterOfInvestment = new EntityDeletionOrUpdateAdapter<Investment>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `investments` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Investment entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__deletionAdapterOfInvestmentTransaction = new EntityDeletionOrUpdateAdapter<InvestmentTransaction>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `investment_transactions` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final InvestmentTransaction entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__deletionAdapterOfInvestmentDividend = new EntityDeletionOrUpdateAdapter<InvestmentDividend>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `investment_dividends` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final InvestmentDividend entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfInvestment = new EntityDeletionOrUpdateAdapter<Investment>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `investments` SET `id` = ?,`name` = ?,`symbol` = ?,`type` = ?,`initialValue` = ?,`currentValue` = ?,`quantity` = ?,`averageCost` = ?,`purchaseDate` = ?,`currency` = ?,`broker` = ?,`category` = ?,`notes` = ?,`isActive` = ?,`lastUpdated` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Investment entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getSymbol() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getSymbol());
        }
        statement.bindString(4, __InvestmentType_enumToString(entity.getType()));
        statement.bindDouble(5, entity.getInitialValue());
        statement.bindDouble(6, entity.getCurrentValue());
        statement.bindDouble(7, entity.getQuantity());
        statement.bindDouble(8, entity.getAverageCost());
        statement.bindLong(9, entity.getPurchaseDate());
        if (entity.getCurrency() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getCurrency());
        }
        if (entity.getBroker() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getBroker());
        }
        statement.bindString(12, __InvestmentCategory_enumToString(entity.getCategory()));
        if (entity.getNotes() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getNotes());
        }
        final int _tmp = entity.isActive() ? 1 : 0;
        statement.bindLong(14, _tmp);
        statement.bindLong(15, entity.getLastUpdated());
        statement.bindLong(16, entity.getCreatedAt());
        statement.bindLong(17, entity.getId());
      }
    };
    this.__updateAdapterOfInvestmentTransaction = new EntityDeletionOrUpdateAdapter<InvestmentTransaction>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `investment_transactions` SET `id` = ?,`investmentId` = ?,`type` = ?,`quantity` = ?,`pricePerUnit` = ?,`totalAmount` = ?,`fees` = ?,`transactionDate` = ?,`notes` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final InvestmentTransaction entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getInvestmentId());
        statement.bindString(3, __InvestmentTransactionType_enumToString(entity.getType()));
        statement.bindDouble(4, entity.getQuantity());
        statement.bindDouble(5, entity.getPricePerUnit());
        statement.bindDouble(6, entity.getTotalAmount());
        statement.bindDouble(7, entity.getFees());
        statement.bindLong(8, entity.getTransactionDate());
        if (entity.getNotes() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getNotes());
        }
        statement.bindLong(10, entity.getCreatedAt());
        statement.bindLong(11, entity.getId());
      }
    };
    this.__updateAdapterOfInvestmentDividend = new EntityDeletionOrUpdateAdapter<InvestmentDividend>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `investment_dividends` SET `id` = ?,`investmentId` = ?,`amountPerShare` = ?,`totalAmount` = ?,`currency` = ?,`paymentDate` = ?,`exDividendDate` = ?,`taxWithheld` = ?,`notes` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final InvestmentDividend entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getInvestmentId());
        statement.bindDouble(3, entity.getAmountPerShare());
        statement.bindDouble(4, entity.getTotalAmount());
        if (entity.getCurrency() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getCurrency());
        }
        statement.bindLong(6, entity.getPaymentDate());
        if (entity.getExDividendDate() == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.getExDividendDate());
        }
        statement.bindDouble(8, entity.getTaxWithheld());
        if (entity.getNotes() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getNotes());
        }
        statement.bindLong(10, entity.getCreatedAt());
        statement.bindLong(11, entity.getId());
      }
    };
    this.__updateAdapterOfInvestmentPortfolio = new EntityDeletionOrUpdateAdapter<InvestmentPortfolio>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `investment_portfolios` SET `id` = ?,`name` = ?,`description` = ?,`targetAllocation` = ?,`riskProfile` = ?,`isActive` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final InvestmentPortfolio entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDescription());
        }
        if (entity.getTargetAllocation() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getTargetAllocation());
        }
        statement.bindString(5, __RiskProfile_enumToString(entity.getRiskProfile()));
        final int _tmp = entity.isActive() ? 1 : 0;
        statement.bindLong(6, _tmp);
        statement.bindLong(7, entity.getCreatedAt());
        statement.bindLong(8, entity.getId());
      }
    };
    this.__preparedStmtOfDeactivateInvestment = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE investments SET isActive = 0 WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfCleanupOldPriceHistory = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM investment_price_history WHERE investmentId = ? AND recordedAt < ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeactivatePortfolio = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE investment_portfolios SET isActive = 0 WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfRemoveInvestmentFromPortfolio = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM portfolio_investments WHERE portfolioId = ? AND investmentId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertInvestment(final Investment investment,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfInvestment.insertAndReturnId(investment);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertTransaction(final InvestmentTransaction transaction,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfInvestmentTransaction.insertAndReturnId(transaction);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertPriceHistoryEntry(final InvestmentPriceHistory priceHistory,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfInvestmentPriceHistory.insertAndReturnId(priceHistory);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertDividend(final InvestmentDividend dividend,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfInvestmentDividend.insertAndReturnId(dividend);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertPortfolio(final InvestmentPortfolio portfolio,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfInvestmentPortfolio.insertAndReturnId(portfolio);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object addInvestmentToPortfolio(final PortfolioInvestment portfolioInvestment,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfPortfolioInvestment.insertAndReturnId(portfolioInvestment);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteInvestment(final Investment investment,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfInvestment.handle(investment);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteTransaction(final InvestmentTransaction transaction,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfInvestmentTransaction.handle(transaction);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteDividend(final InvestmentDividend dividend,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfInvestmentDividend.handle(dividend);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateInvestment(final Investment investment,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfInvestment.handle(investment);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateTransaction(final InvestmentTransaction transaction,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfInvestmentTransaction.handle(transaction);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateDividend(final InvestmentDividend dividend,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfInvestmentDividend.handle(dividend);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updatePortfolio(final InvestmentPortfolio portfolio,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfInvestmentPortfolio.handle(portfolio);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deactivateInvestment(final long investmentId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeactivateInvestment.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, investmentId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeactivateInvestment.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object cleanupOldPriceHistory(final long investmentId, final long cutoffDate,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfCleanupOldPriceHistory.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, investmentId);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, cutoffDate);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfCleanupOldPriceHistory.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deactivatePortfolio(final long portfolioId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeactivatePortfolio.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, portfolioId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeactivatePortfolio.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object removeInvestmentFromPortfolio(final long portfolioId, final long investmentId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfRemoveInvestmentFromPortfolio.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, portfolioId);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, investmentId);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfRemoveInvestmentFromPortfolio.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Investment>> getAllActiveInvestments() {
    final String _sql = "SELECT * FROM investments WHERE isActive = 1 ORDER BY name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"investments"}, new Callable<List<Investment>>() {
      @Override
      @NonNull
      public List<Investment> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfSymbol = CursorUtil.getColumnIndexOrThrow(_cursor, "symbol");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfInitialValue = CursorUtil.getColumnIndexOrThrow(_cursor, "initialValue");
          final int _cursorIndexOfCurrentValue = CursorUtil.getColumnIndexOrThrow(_cursor, "currentValue");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfAverageCost = CursorUtil.getColumnIndexOrThrow(_cursor, "averageCost");
          final int _cursorIndexOfPurchaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "purchaseDate");
          final int _cursorIndexOfCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "currency");
          final int _cursorIndexOfBroker = CursorUtil.getColumnIndexOrThrow(_cursor, "broker");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<Investment> _result = new ArrayList<Investment>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Investment _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpSymbol;
            if (_cursor.isNull(_cursorIndexOfSymbol)) {
              _tmpSymbol = null;
            } else {
              _tmpSymbol = _cursor.getString(_cursorIndexOfSymbol);
            }
            final InvestmentType _tmpType;
            _tmpType = __InvestmentType_stringToEnum(_cursor.getString(_cursorIndexOfType));
            final double _tmpInitialValue;
            _tmpInitialValue = _cursor.getDouble(_cursorIndexOfInitialValue);
            final double _tmpCurrentValue;
            _tmpCurrentValue = _cursor.getDouble(_cursorIndexOfCurrentValue);
            final double _tmpQuantity;
            _tmpQuantity = _cursor.getDouble(_cursorIndexOfQuantity);
            final double _tmpAverageCost;
            _tmpAverageCost = _cursor.getDouble(_cursorIndexOfAverageCost);
            final long _tmpPurchaseDate;
            _tmpPurchaseDate = _cursor.getLong(_cursorIndexOfPurchaseDate);
            final String _tmpCurrency;
            if (_cursor.isNull(_cursorIndexOfCurrency)) {
              _tmpCurrency = null;
            } else {
              _tmpCurrency = _cursor.getString(_cursorIndexOfCurrency);
            }
            final String _tmpBroker;
            if (_cursor.isNull(_cursorIndexOfBroker)) {
              _tmpBroker = null;
            } else {
              _tmpBroker = _cursor.getString(_cursorIndexOfBroker);
            }
            final InvestmentCategory _tmpCategory;
            _tmpCategory = __InvestmentCategory_stringToEnum(_cursor.getString(_cursorIndexOfCategory));
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final long _tmpLastUpdated;
            _tmpLastUpdated = _cursor.getLong(_cursorIndexOfLastUpdated);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new Investment(_tmpId,_tmpName,_tmpSymbol,_tmpType,_tmpInitialValue,_tmpCurrentValue,_tmpQuantity,_tmpAverageCost,_tmpPurchaseDate,_tmpCurrency,_tmpBroker,_tmpCategory,_tmpNotes,_tmpIsActive,_tmpLastUpdated,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Investment>> getAllInvestments() {
    final String _sql = "SELECT * FROM investments ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"investments"}, new Callable<List<Investment>>() {
      @Override
      @NonNull
      public List<Investment> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfSymbol = CursorUtil.getColumnIndexOrThrow(_cursor, "symbol");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfInitialValue = CursorUtil.getColumnIndexOrThrow(_cursor, "initialValue");
          final int _cursorIndexOfCurrentValue = CursorUtil.getColumnIndexOrThrow(_cursor, "currentValue");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfAverageCost = CursorUtil.getColumnIndexOrThrow(_cursor, "averageCost");
          final int _cursorIndexOfPurchaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "purchaseDate");
          final int _cursorIndexOfCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "currency");
          final int _cursorIndexOfBroker = CursorUtil.getColumnIndexOrThrow(_cursor, "broker");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<Investment> _result = new ArrayList<Investment>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Investment _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpSymbol;
            if (_cursor.isNull(_cursorIndexOfSymbol)) {
              _tmpSymbol = null;
            } else {
              _tmpSymbol = _cursor.getString(_cursorIndexOfSymbol);
            }
            final InvestmentType _tmpType;
            _tmpType = __InvestmentType_stringToEnum(_cursor.getString(_cursorIndexOfType));
            final double _tmpInitialValue;
            _tmpInitialValue = _cursor.getDouble(_cursorIndexOfInitialValue);
            final double _tmpCurrentValue;
            _tmpCurrentValue = _cursor.getDouble(_cursorIndexOfCurrentValue);
            final double _tmpQuantity;
            _tmpQuantity = _cursor.getDouble(_cursorIndexOfQuantity);
            final double _tmpAverageCost;
            _tmpAverageCost = _cursor.getDouble(_cursorIndexOfAverageCost);
            final long _tmpPurchaseDate;
            _tmpPurchaseDate = _cursor.getLong(_cursorIndexOfPurchaseDate);
            final String _tmpCurrency;
            if (_cursor.isNull(_cursorIndexOfCurrency)) {
              _tmpCurrency = null;
            } else {
              _tmpCurrency = _cursor.getString(_cursorIndexOfCurrency);
            }
            final String _tmpBroker;
            if (_cursor.isNull(_cursorIndexOfBroker)) {
              _tmpBroker = null;
            } else {
              _tmpBroker = _cursor.getString(_cursorIndexOfBroker);
            }
            final InvestmentCategory _tmpCategory;
            _tmpCategory = __InvestmentCategory_stringToEnum(_cursor.getString(_cursorIndexOfCategory));
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final long _tmpLastUpdated;
            _tmpLastUpdated = _cursor.getLong(_cursorIndexOfLastUpdated);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new Investment(_tmpId,_tmpName,_tmpSymbol,_tmpType,_tmpInitialValue,_tmpCurrentValue,_tmpQuantity,_tmpAverageCost,_tmpPurchaseDate,_tmpCurrency,_tmpBroker,_tmpCategory,_tmpNotes,_tmpIsActive,_tmpLastUpdated,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getInvestmentById(final long investmentId,
      final Continuation<? super Investment> $completion) {
    final String _sql = "SELECT * FROM investments WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, investmentId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Investment>() {
      @Override
      @Nullable
      public Investment call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfSymbol = CursorUtil.getColumnIndexOrThrow(_cursor, "symbol");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfInitialValue = CursorUtil.getColumnIndexOrThrow(_cursor, "initialValue");
          final int _cursorIndexOfCurrentValue = CursorUtil.getColumnIndexOrThrow(_cursor, "currentValue");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfAverageCost = CursorUtil.getColumnIndexOrThrow(_cursor, "averageCost");
          final int _cursorIndexOfPurchaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "purchaseDate");
          final int _cursorIndexOfCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "currency");
          final int _cursorIndexOfBroker = CursorUtil.getColumnIndexOrThrow(_cursor, "broker");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final Investment _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpSymbol;
            if (_cursor.isNull(_cursorIndexOfSymbol)) {
              _tmpSymbol = null;
            } else {
              _tmpSymbol = _cursor.getString(_cursorIndexOfSymbol);
            }
            final InvestmentType _tmpType;
            _tmpType = __InvestmentType_stringToEnum(_cursor.getString(_cursorIndexOfType));
            final double _tmpInitialValue;
            _tmpInitialValue = _cursor.getDouble(_cursorIndexOfInitialValue);
            final double _tmpCurrentValue;
            _tmpCurrentValue = _cursor.getDouble(_cursorIndexOfCurrentValue);
            final double _tmpQuantity;
            _tmpQuantity = _cursor.getDouble(_cursorIndexOfQuantity);
            final double _tmpAverageCost;
            _tmpAverageCost = _cursor.getDouble(_cursorIndexOfAverageCost);
            final long _tmpPurchaseDate;
            _tmpPurchaseDate = _cursor.getLong(_cursorIndexOfPurchaseDate);
            final String _tmpCurrency;
            if (_cursor.isNull(_cursorIndexOfCurrency)) {
              _tmpCurrency = null;
            } else {
              _tmpCurrency = _cursor.getString(_cursorIndexOfCurrency);
            }
            final String _tmpBroker;
            if (_cursor.isNull(_cursorIndexOfBroker)) {
              _tmpBroker = null;
            } else {
              _tmpBroker = _cursor.getString(_cursorIndexOfBroker);
            }
            final InvestmentCategory _tmpCategory;
            _tmpCategory = __InvestmentCategory_stringToEnum(_cursor.getString(_cursorIndexOfCategory));
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final long _tmpLastUpdated;
            _tmpLastUpdated = _cursor.getLong(_cursorIndexOfLastUpdated);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new Investment(_tmpId,_tmpName,_tmpSymbol,_tmpType,_tmpInitialValue,_tmpCurrentValue,_tmpQuantity,_tmpAverageCost,_tmpPurchaseDate,_tmpCurrency,_tmpBroker,_tmpCategory,_tmpNotes,_tmpIsActive,_tmpLastUpdated,_tmpCreatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Investment>> getInvestmentsByType(final InvestmentType type) {
    final String _sql = "SELECT * FROM investments WHERE type = ? AND isActive = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, __InvestmentType_enumToString(type));
    return CoroutinesRoom.createFlow(__db, false, new String[] {"investments"}, new Callable<List<Investment>>() {
      @Override
      @NonNull
      public List<Investment> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfSymbol = CursorUtil.getColumnIndexOrThrow(_cursor, "symbol");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfInitialValue = CursorUtil.getColumnIndexOrThrow(_cursor, "initialValue");
          final int _cursorIndexOfCurrentValue = CursorUtil.getColumnIndexOrThrow(_cursor, "currentValue");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfAverageCost = CursorUtil.getColumnIndexOrThrow(_cursor, "averageCost");
          final int _cursorIndexOfPurchaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "purchaseDate");
          final int _cursorIndexOfCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "currency");
          final int _cursorIndexOfBroker = CursorUtil.getColumnIndexOrThrow(_cursor, "broker");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<Investment> _result = new ArrayList<Investment>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Investment _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpSymbol;
            if (_cursor.isNull(_cursorIndexOfSymbol)) {
              _tmpSymbol = null;
            } else {
              _tmpSymbol = _cursor.getString(_cursorIndexOfSymbol);
            }
            final InvestmentType _tmpType;
            _tmpType = __InvestmentType_stringToEnum(_cursor.getString(_cursorIndexOfType));
            final double _tmpInitialValue;
            _tmpInitialValue = _cursor.getDouble(_cursorIndexOfInitialValue);
            final double _tmpCurrentValue;
            _tmpCurrentValue = _cursor.getDouble(_cursorIndexOfCurrentValue);
            final double _tmpQuantity;
            _tmpQuantity = _cursor.getDouble(_cursorIndexOfQuantity);
            final double _tmpAverageCost;
            _tmpAverageCost = _cursor.getDouble(_cursorIndexOfAverageCost);
            final long _tmpPurchaseDate;
            _tmpPurchaseDate = _cursor.getLong(_cursorIndexOfPurchaseDate);
            final String _tmpCurrency;
            if (_cursor.isNull(_cursorIndexOfCurrency)) {
              _tmpCurrency = null;
            } else {
              _tmpCurrency = _cursor.getString(_cursorIndexOfCurrency);
            }
            final String _tmpBroker;
            if (_cursor.isNull(_cursorIndexOfBroker)) {
              _tmpBroker = null;
            } else {
              _tmpBroker = _cursor.getString(_cursorIndexOfBroker);
            }
            final InvestmentCategory _tmpCategory;
            _tmpCategory = __InvestmentCategory_stringToEnum(_cursor.getString(_cursorIndexOfCategory));
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final long _tmpLastUpdated;
            _tmpLastUpdated = _cursor.getLong(_cursorIndexOfLastUpdated);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new Investment(_tmpId,_tmpName,_tmpSymbol,_tmpType,_tmpInitialValue,_tmpCurrentValue,_tmpQuantity,_tmpAverageCost,_tmpPurchaseDate,_tmpCurrency,_tmpBroker,_tmpCategory,_tmpNotes,_tmpIsActive,_tmpLastUpdated,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Investment>> getInvestmentsByCategory(final InvestmentCategory category) {
    final String _sql = "SELECT * FROM investments WHERE category = ? AND isActive = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, __InvestmentCategory_enumToString(category));
    return CoroutinesRoom.createFlow(__db, false, new String[] {"investments"}, new Callable<List<Investment>>() {
      @Override
      @NonNull
      public List<Investment> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfSymbol = CursorUtil.getColumnIndexOrThrow(_cursor, "symbol");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfInitialValue = CursorUtil.getColumnIndexOrThrow(_cursor, "initialValue");
          final int _cursorIndexOfCurrentValue = CursorUtil.getColumnIndexOrThrow(_cursor, "currentValue");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfAverageCost = CursorUtil.getColumnIndexOrThrow(_cursor, "averageCost");
          final int _cursorIndexOfPurchaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "purchaseDate");
          final int _cursorIndexOfCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "currency");
          final int _cursorIndexOfBroker = CursorUtil.getColumnIndexOrThrow(_cursor, "broker");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<Investment> _result = new ArrayList<Investment>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Investment _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpSymbol;
            if (_cursor.isNull(_cursorIndexOfSymbol)) {
              _tmpSymbol = null;
            } else {
              _tmpSymbol = _cursor.getString(_cursorIndexOfSymbol);
            }
            final InvestmentType _tmpType;
            _tmpType = __InvestmentType_stringToEnum(_cursor.getString(_cursorIndexOfType));
            final double _tmpInitialValue;
            _tmpInitialValue = _cursor.getDouble(_cursorIndexOfInitialValue);
            final double _tmpCurrentValue;
            _tmpCurrentValue = _cursor.getDouble(_cursorIndexOfCurrentValue);
            final double _tmpQuantity;
            _tmpQuantity = _cursor.getDouble(_cursorIndexOfQuantity);
            final double _tmpAverageCost;
            _tmpAverageCost = _cursor.getDouble(_cursorIndexOfAverageCost);
            final long _tmpPurchaseDate;
            _tmpPurchaseDate = _cursor.getLong(_cursorIndexOfPurchaseDate);
            final String _tmpCurrency;
            if (_cursor.isNull(_cursorIndexOfCurrency)) {
              _tmpCurrency = null;
            } else {
              _tmpCurrency = _cursor.getString(_cursorIndexOfCurrency);
            }
            final String _tmpBroker;
            if (_cursor.isNull(_cursorIndexOfBroker)) {
              _tmpBroker = null;
            } else {
              _tmpBroker = _cursor.getString(_cursorIndexOfBroker);
            }
            final InvestmentCategory _tmpCategory;
            _tmpCategory = __InvestmentCategory_stringToEnum(_cursor.getString(_cursorIndexOfCategory));
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final long _tmpLastUpdated;
            _tmpLastUpdated = _cursor.getLong(_cursorIndexOfLastUpdated);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new Investment(_tmpId,_tmpName,_tmpSymbol,_tmpType,_tmpInitialValue,_tmpCurrentValue,_tmpQuantity,_tmpAverageCost,_tmpPurchaseDate,_tmpCurrency,_tmpBroker,_tmpCategory,_tmpNotes,_tmpIsActive,_tmpLastUpdated,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<InvestmentTransaction>> getTransactionsForInvestment(final long investmentId) {
    final String _sql = "SELECT * FROM investment_transactions WHERE investmentId = ? ORDER BY transactionDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, investmentId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"investment_transactions"}, new Callable<List<InvestmentTransaction>>() {
      @Override
      @NonNull
      public List<InvestmentTransaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfInvestmentId = CursorUtil.getColumnIndexOrThrow(_cursor, "investmentId");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfPricePerUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "pricePerUnit");
          final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
          final int _cursorIndexOfFees = CursorUtil.getColumnIndexOrThrow(_cursor, "fees");
          final int _cursorIndexOfTransactionDate = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionDate");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<InvestmentTransaction> _result = new ArrayList<InvestmentTransaction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final InvestmentTransaction _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpInvestmentId;
            _tmpInvestmentId = _cursor.getLong(_cursorIndexOfInvestmentId);
            final InvestmentTransactionType _tmpType;
            _tmpType = __InvestmentTransactionType_stringToEnum(_cursor.getString(_cursorIndexOfType));
            final double _tmpQuantity;
            _tmpQuantity = _cursor.getDouble(_cursorIndexOfQuantity);
            final double _tmpPricePerUnit;
            _tmpPricePerUnit = _cursor.getDouble(_cursorIndexOfPricePerUnit);
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            final double _tmpFees;
            _tmpFees = _cursor.getDouble(_cursorIndexOfFees);
            final long _tmpTransactionDate;
            _tmpTransactionDate = _cursor.getLong(_cursorIndexOfTransactionDate);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new InvestmentTransaction(_tmpId,_tmpInvestmentId,_tmpType,_tmpQuantity,_tmpPricePerUnit,_tmpTotalAmount,_tmpFees,_tmpTransactionDate,_tmpNotes,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<InvestmentTransaction>> getRecentTransactions(final int limit) {
    final String _sql = "SELECT * FROM investment_transactions ORDER BY transactionDate DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"investment_transactions"}, new Callable<List<InvestmentTransaction>>() {
      @Override
      @NonNull
      public List<InvestmentTransaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfInvestmentId = CursorUtil.getColumnIndexOrThrow(_cursor, "investmentId");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfPricePerUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "pricePerUnit");
          final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
          final int _cursorIndexOfFees = CursorUtil.getColumnIndexOrThrow(_cursor, "fees");
          final int _cursorIndexOfTransactionDate = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionDate");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<InvestmentTransaction> _result = new ArrayList<InvestmentTransaction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final InvestmentTransaction _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpInvestmentId;
            _tmpInvestmentId = _cursor.getLong(_cursorIndexOfInvestmentId);
            final InvestmentTransactionType _tmpType;
            _tmpType = __InvestmentTransactionType_stringToEnum(_cursor.getString(_cursorIndexOfType));
            final double _tmpQuantity;
            _tmpQuantity = _cursor.getDouble(_cursorIndexOfQuantity);
            final double _tmpPricePerUnit;
            _tmpPricePerUnit = _cursor.getDouble(_cursorIndexOfPricePerUnit);
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            final double _tmpFees;
            _tmpFees = _cursor.getDouble(_cursorIndexOfFees);
            final long _tmpTransactionDate;
            _tmpTransactionDate = _cursor.getLong(_cursorIndexOfTransactionDate);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new InvestmentTransaction(_tmpId,_tmpInvestmentId,_tmpType,_tmpQuantity,_tmpPricePerUnit,_tmpTotalAmount,_tmpFees,_tmpTransactionDate,_tmpNotes,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<InvestmentPriceHistory>> getPriceHistoryForInvestment(final long investmentId) {
    final String _sql = "SELECT * FROM investment_price_history WHERE investmentId = ? ORDER BY recordedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, investmentId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"investment_price_history"}, new Callable<List<InvestmentPriceHistory>>() {
      @Override
      @NonNull
      public List<InvestmentPriceHistory> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfInvestmentId = CursorUtil.getColumnIndexOrThrow(_cursor, "investmentId");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "currency");
          final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "recordedAt");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final List<InvestmentPriceHistory> _result = new ArrayList<InvestmentPriceHistory>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final InvestmentPriceHistory _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpInvestmentId;
            _tmpInvestmentId = _cursor.getLong(_cursorIndexOfInvestmentId);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final String _tmpCurrency;
            if (_cursor.isNull(_cursorIndexOfCurrency)) {
              _tmpCurrency = null;
            } else {
              _tmpCurrency = _cursor.getString(_cursorIndexOfCurrency);
            }
            final long _tmpRecordedAt;
            _tmpRecordedAt = _cursor.getLong(_cursorIndexOfRecordedAt);
            final String _tmpSource;
            if (_cursor.isNull(_cursorIndexOfSource)) {
              _tmpSource = null;
            } else {
              _tmpSource = _cursor.getString(_cursorIndexOfSource);
            }
            _item = new InvestmentPriceHistory(_tmpId,_tmpInvestmentId,_tmpPrice,_tmpCurrency,_tmpRecordedAt,_tmpSource);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getLatestPriceForInvestment(final long investmentId,
      final Continuation<? super InvestmentPriceHistory> $completion) {
    final String _sql = "SELECT * FROM investment_price_history WHERE investmentId = ? ORDER BY recordedAt DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, investmentId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<InvestmentPriceHistory>() {
      @Override
      @Nullable
      public InvestmentPriceHistory call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfInvestmentId = CursorUtil.getColumnIndexOrThrow(_cursor, "investmentId");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "currency");
          final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "recordedAt");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final InvestmentPriceHistory _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpInvestmentId;
            _tmpInvestmentId = _cursor.getLong(_cursorIndexOfInvestmentId);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final String _tmpCurrency;
            if (_cursor.isNull(_cursorIndexOfCurrency)) {
              _tmpCurrency = null;
            } else {
              _tmpCurrency = _cursor.getString(_cursorIndexOfCurrency);
            }
            final long _tmpRecordedAt;
            _tmpRecordedAt = _cursor.getLong(_cursorIndexOfRecordedAt);
            final String _tmpSource;
            if (_cursor.isNull(_cursorIndexOfSource)) {
              _tmpSource = null;
            } else {
              _tmpSource = _cursor.getString(_cursorIndexOfSource);
            }
            _result = new InvestmentPriceHistory(_tmpId,_tmpInvestmentId,_tmpPrice,_tmpCurrency,_tmpRecordedAt,_tmpSource);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<InvestmentDividend>> getDividendsForInvestment(final long investmentId) {
    final String _sql = "SELECT * FROM investment_dividends WHERE investmentId = ? ORDER BY paymentDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, investmentId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"investment_dividends"}, new Callable<List<InvestmentDividend>>() {
      @Override
      @NonNull
      public List<InvestmentDividend> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfInvestmentId = CursorUtil.getColumnIndexOrThrow(_cursor, "investmentId");
          final int _cursorIndexOfAmountPerShare = CursorUtil.getColumnIndexOrThrow(_cursor, "amountPerShare");
          final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
          final int _cursorIndexOfCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "currency");
          final int _cursorIndexOfPaymentDate = CursorUtil.getColumnIndexOrThrow(_cursor, "paymentDate");
          final int _cursorIndexOfExDividendDate = CursorUtil.getColumnIndexOrThrow(_cursor, "exDividendDate");
          final int _cursorIndexOfTaxWithheld = CursorUtil.getColumnIndexOrThrow(_cursor, "taxWithheld");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<InvestmentDividend> _result = new ArrayList<InvestmentDividend>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final InvestmentDividend _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpInvestmentId;
            _tmpInvestmentId = _cursor.getLong(_cursorIndexOfInvestmentId);
            final double _tmpAmountPerShare;
            _tmpAmountPerShare = _cursor.getDouble(_cursorIndexOfAmountPerShare);
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            final String _tmpCurrency;
            if (_cursor.isNull(_cursorIndexOfCurrency)) {
              _tmpCurrency = null;
            } else {
              _tmpCurrency = _cursor.getString(_cursorIndexOfCurrency);
            }
            final long _tmpPaymentDate;
            _tmpPaymentDate = _cursor.getLong(_cursorIndexOfPaymentDate);
            final Long _tmpExDividendDate;
            if (_cursor.isNull(_cursorIndexOfExDividendDate)) {
              _tmpExDividendDate = null;
            } else {
              _tmpExDividendDate = _cursor.getLong(_cursorIndexOfExDividendDate);
            }
            final double _tmpTaxWithheld;
            _tmpTaxWithheld = _cursor.getDouble(_cursorIndexOfTaxWithheld);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new InvestmentDividend(_tmpId,_tmpInvestmentId,_tmpAmountPerShare,_tmpTotalAmount,_tmpCurrency,_tmpPaymentDate,_tmpExDividendDate,_tmpTaxWithheld,_tmpNotes,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getTotalDividendsForPeriod(final long investmentId, final long startDate,
      final long endDate, final Continuation<? super Double> $completion) {
    final String _sql = "SELECT SUM(totalAmount) FROM investment_dividends WHERE investmentId = ? AND paymentDate >= ? AND paymentDate <= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, investmentId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 3;
    _statement.bindLong(_argIndex, endDate);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final Double _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getDouble(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<InvestmentPortfolio>> getAllActivePortfolios() {
    final String _sql = "SELECT * FROM investment_portfolios WHERE isActive = 1 ORDER BY name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"investment_portfolios"}, new Callable<List<InvestmentPortfolio>>() {
      @Override
      @NonNull
      public List<InvestmentPortfolio> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTargetAllocation = CursorUtil.getColumnIndexOrThrow(_cursor, "targetAllocation");
          final int _cursorIndexOfRiskProfile = CursorUtil.getColumnIndexOrThrow(_cursor, "riskProfile");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<InvestmentPortfolio> _result = new ArrayList<InvestmentPortfolio>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final InvestmentPortfolio _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpTargetAllocation;
            if (_cursor.isNull(_cursorIndexOfTargetAllocation)) {
              _tmpTargetAllocation = null;
            } else {
              _tmpTargetAllocation = _cursor.getString(_cursorIndexOfTargetAllocation);
            }
            final RiskProfile _tmpRiskProfile;
            _tmpRiskProfile = __RiskProfile_stringToEnum(_cursor.getString(_cursorIndexOfRiskProfile));
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new InvestmentPortfolio(_tmpId,_tmpName,_tmpDescription,_tmpTargetAllocation,_tmpRiskProfile,_tmpIsActive,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getPortfolioById(final long portfolioId,
      final Continuation<? super InvestmentPortfolio> $completion) {
    final String _sql = "SELECT * FROM investment_portfolios WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, portfolioId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<InvestmentPortfolio>() {
      @Override
      @Nullable
      public InvestmentPortfolio call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTargetAllocation = CursorUtil.getColumnIndexOrThrow(_cursor, "targetAllocation");
          final int _cursorIndexOfRiskProfile = CursorUtil.getColumnIndexOrThrow(_cursor, "riskProfile");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final InvestmentPortfolio _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpTargetAllocation;
            if (_cursor.isNull(_cursorIndexOfTargetAllocation)) {
              _tmpTargetAllocation = null;
            } else {
              _tmpTargetAllocation = _cursor.getString(_cursorIndexOfTargetAllocation);
            }
            final RiskProfile _tmpRiskProfile;
            _tmpRiskProfile = __RiskProfile_stringToEnum(_cursor.getString(_cursorIndexOfRiskProfile));
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new InvestmentPortfolio(_tmpId,_tmpName,_tmpDescription,_tmpTargetAllocation,_tmpRiskProfile,_tmpIsActive,_tmpCreatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Investment>> getInvestmentsInPortfolio(final long portfolioId) {
    final String _sql = "\n"
            + "        SELECT i.* FROM investments i \n"
            + "        INNER JOIN portfolio_investments pi ON i.id = pi.investmentId \n"
            + "        WHERE pi.portfolioId = ? AND i.isActive = 1\n"
            + "        ORDER BY i.name ASC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, portfolioId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"investments",
        "portfolio_investments"}, new Callable<List<Investment>>() {
      @Override
      @NonNull
      public List<Investment> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfSymbol = CursorUtil.getColumnIndexOrThrow(_cursor, "symbol");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfInitialValue = CursorUtil.getColumnIndexOrThrow(_cursor, "initialValue");
          final int _cursorIndexOfCurrentValue = CursorUtil.getColumnIndexOrThrow(_cursor, "currentValue");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfAverageCost = CursorUtil.getColumnIndexOrThrow(_cursor, "averageCost");
          final int _cursorIndexOfPurchaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "purchaseDate");
          final int _cursorIndexOfCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "currency");
          final int _cursorIndexOfBroker = CursorUtil.getColumnIndexOrThrow(_cursor, "broker");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<Investment> _result = new ArrayList<Investment>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Investment _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpSymbol;
            if (_cursor.isNull(_cursorIndexOfSymbol)) {
              _tmpSymbol = null;
            } else {
              _tmpSymbol = _cursor.getString(_cursorIndexOfSymbol);
            }
            final InvestmentType _tmpType;
            _tmpType = __InvestmentType_stringToEnum(_cursor.getString(_cursorIndexOfType));
            final double _tmpInitialValue;
            _tmpInitialValue = _cursor.getDouble(_cursorIndexOfInitialValue);
            final double _tmpCurrentValue;
            _tmpCurrentValue = _cursor.getDouble(_cursorIndexOfCurrentValue);
            final double _tmpQuantity;
            _tmpQuantity = _cursor.getDouble(_cursorIndexOfQuantity);
            final double _tmpAverageCost;
            _tmpAverageCost = _cursor.getDouble(_cursorIndexOfAverageCost);
            final long _tmpPurchaseDate;
            _tmpPurchaseDate = _cursor.getLong(_cursorIndexOfPurchaseDate);
            final String _tmpCurrency;
            if (_cursor.isNull(_cursorIndexOfCurrency)) {
              _tmpCurrency = null;
            } else {
              _tmpCurrency = _cursor.getString(_cursorIndexOfCurrency);
            }
            final String _tmpBroker;
            if (_cursor.isNull(_cursorIndexOfBroker)) {
              _tmpBroker = null;
            } else {
              _tmpBroker = _cursor.getString(_cursorIndexOfBroker);
            }
            final InvestmentCategory _tmpCategory;
            _tmpCategory = __InvestmentCategory_stringToEnum(_cursor.getString(_cursorIndexOfCategory));
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final long _tmpLastUpdated;
            _tmpLastUpdated = _cursor.getLong(_cursorIndexOfLastUpdated);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new Investment(_tmpId,_tmpName,_tmpSymbol,_tmpType,_tmpInitialValue,_tmpCurrentValue,_tmpQuantity,_tmpAverageCost,_tmpPurchaseDate,_tmpCurrency,_tmpBroker,_tmpCategory,_tmpNotes,_tmpIsActive,_tmpLastUpdated,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getInvestmentTypeBreakdown(
      final Continuation<? super List<InvestmentTypeBreakdown>> $completion) {
    final String _sql = "\n"
            + "        SELECT \n"
            + "            type,\n"
            + "            COUNT(*) as count,\n"
            + "            SUM(currentValue * quantity) as totalValue,\n"
            + "            SUM((currentValue - averageCost) * quantity) as totalGainLoss\n"
            + "        FROM investments \n"
            + "        WHERE isActive = 1 \n"
            + "        GROUP BY type\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<InvestmentTypeBreakdown>>() {
      @Override
      @NonNull
      public List<InvestmentTypeBreakdown> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfType = 0;
          final int _cursorIndexOfCount = 1;
          final int _cursorIndexOfTotalValue = 2;
          final int _cursorIndexOfTotalGainLoss = 3;
          final List<InvestmentTypeBreakdown> _result = new ArrayList<InvestmentTypeBreakdown>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final InvestmentTypeBreakdown _item;
            final InvestmentType _tmpType;
            _tmpType = __InvestmentType_stringToEnum(_cursor.getString(_cursorIndexOfType));
            final int _tmpCount;
            _tmpCount = _cursor.getInt(_cursorIndexOfCount);
            final double _tmpTotalValue;
            _tmpTotalValue = _cursor.getDouble(_cursorIndexOfTotalValue);
            final double _tmpTotalGainLoss;
            _tmpTotalGainLoss = _cursor.getDouble(_cursorIndexOfTotalGainLoss);
            _item = new InvestmentTypeBreakdown(_tmpType,_tmpCount,_tmpTotalValue,_tmpTotalGainLoss);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getPortfolioSummary(final Continuation<? super PortfolioSummary> $completion) {
    final String _sql = "\n"
            + "        SELECT \n"
            + "            SUM(currentValue * quantity) as totalValue,\n"
            + "            SUM(initialValue * quantity) as totalCost,\n"
            + "            SUM((currentValue - averageCost) * quantity) as totalGainLoss,\n"
            + "            COUNT(*) as totalInvestments\n"
            + "        FROM investments \n"
            + "        WHERE isActive = 1\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<PortfolioSummary>() {
      @Override
      @Nullable
      public PortfolioSummary call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTotalValue = 0;
          final int _cursorIndexOfTotalCost = 1;
          final int _cursorIndexOfTotalGainLoss = 2;
          final int _cursorIndexOfTotalInvestments = 3;
          final PortfolioSummary _result;
          if (_cursor.moveToFirst()) {
            final double _tmpTotalValue;
            _tmpTotalValue = _cursor.getDouble(_cursorIndexOfTotalValue);
            final double _tmpTotalCost;
            _tmpTotalCost = _cursor.getDouble(_cursorIndexOfTotalCost);
            final double _tmpTotalGainLoss;
            _tmpTotalGainLoss = _cursor.getDouble(_cursorIndexOfTotalGainLoss);
            final int _tmpTotalInvestments;
            _tmpTotalInvestments = _cursor.getInt(_cursorIndexOfTotalInvestments);
            _result = new PortfolioSummary(_tmpTotalValue,_tmpTotalCost,_tmpTotalGainLoss,_tmpTotalInvestments);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getTopPerformers(final int limit,
      final Continuation<? super List<Investment>> $completion) {
    final String _sql = "\n"
            + "        SELECT * FROM investments \n"
            + "        WHERE isActive = 1 \n"
            + "        ORDER BY ((currentValue - averageCost) / averageCost) DESC \n"
            + "        LIMIT ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Investment>>() {
      @Override
      @NonNull
      public List<Investment> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfSymbol = CursorUtil.getColumnIndexOrThrow(_cursor, "symbol");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfInitialValue = CursorUtil.getColumnIndexOrThrow(_cursor, "initialValue");
          final int _cursorIndexOfCurrentValue = CursorUtil.getColumnIndexOrThrow(_cursor, "currentValue");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfAverageCost = CursorUtil.getColumnIndexOrThrow(_cursor, "averageCost");
          final int _cursorIndexOfPurchaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "purchaseDate");
          final int _cursorIndexOfCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "currency");
          final int _cursorIndexOfBroker = CursorUtil.getColumnIndexOrThrow(_cursor, "broker");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<Investment> _result = new ArrayList<Investment>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Investment _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpSymbol;
            if (_cursor.isNull(_cursorIndexOfSymbol)) {
              _tmpSymbol = null;
            } else {
              _tmpSymbol = _cursor.getString(_cursorIndexOfSymbol);
            }
            final InvestmentType _tmpType;
            _tmpType = __InvestmentType_stringToEnum(_cursor.getString(_cursorIndexOfType));
            final double _tmpInitialValue;
            _tmpInitialValue = _cursor.getDouble(_cursorIndexOfInitialValue);
            final double _tmpCurrentValue;
            _tmpCurrentValue = _cursor.getDouble(_cursorIndexOfCurrentValue);
            final double _tmpQuantity;
            _tmpQuantity = _cursor.getDouble(_cursorIndexOfQuantity);
            final double _tmpAverageCost;
            _tmpAverageCost = _cursor.getDouble(_cursorIndexOfAverageCost);
            final long _tmpPurchaseDate;
            _tmpPurchaseDate = _cursor.getLong(_cursorIndexOfPurchaseDate);
            final String _tmpCurrency;
            if (_cursor.isNull(_cursorIndexOfCurrency)) {
              _tmpCurrency = null;
            } else {
              _tmpCurrency = _cursor.getString(_cursorIndexOfCurrency);
            }
            final String _tmpBroker;
            if (_cursor.isNull(_cursorIndexOfBroker)) {
              _tmpBroker = null;
            } else {
              _tmpBroker = _cursor.getString(_cursorIndexOfBroker);
            }
            final InvestmentCategory _tmpCategory;
            _tmpCategory = __InvestmentCategory_stringToEnum(_cursor.getString(_cursorIndexOfCategory));
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final long _tmpLastUpdated;
            _tmpLastUpdated = _cursor.getLong(_cursorIndexOfLastUpdated);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new Investment(_tmpId,_tmpName,_tmpSymbol,_tmpType,_tmpInitialValue,_tmpCurrentValue,_tmpQuantity,_tmpAverageCost,_tmpPurchaseDate,_tmpCurrency,_tmpBroker,_tmpCategory,_tmpNotes,_tmpIsActive,_tmpLastUpdated,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getWorstPerformers(final int limit,
      final Continuation<? super List<Investment>> $completion) {
    final String _sql = "\n"
            + "        SELECT * FROM investments \n"
            + "        WHERE isActive = 1 \n"
            + "        ORDER BY ((currentValue - averageCost) / averageCost) ASC \n"
            + "        LIMIT ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Investment>>() {
      @Override
      @NonNull
      public List<Investment> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfSymbol = CursorUtil.getColumnIndexOrThrow(_cursor, "symbol");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfInitialValue = CursorUtil.getColumnIndexOrThrow(_cursor, "initialValue");
          final int _cursorIndexOfCurrentValue = CursorUtil.getColumnIndexOrThrow(_cursor, "currentValue");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfAverageCost = CursorUtil.getColumnIndexOrThrow(_cursor, "averageCost");
          final int _cursorIndexOfPurchaseDate = CursorUtil.getColumnIndexOrThrow(_cursor, "purchaseDate");
          final int _cursorIndexOfCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "currency");
          final int _cursorIndexOfBroker = CursorUtil.getColumnIndexOrThrow(_cursor, "broker");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<Investment> _result = new ArrayList<Investment>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Investment _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpSymbol;
            if (_cursor.isNull(_cursorIndexOfSymbol)) {
              _tmpSymbol = null;
            } else {
              _tmpSymbol = _cursor.getString(_cursorIndexOfSymbol);
            }
            final InvestmentType _tmpType;
            _tmpType = __InvestmentType_stringToEnum(_cursor.getString(_cursorIndexOfType));
            final double _tmpInitialValue;
            _tmpInitialValue = _cursor.getDouble(_cursorIndexOfInitialValue);
            final double _tmpCurrentValue;
            _tmpCurrentValue = _cursor.getDouble(_cursorIndexOfCurrentValue);
            final double _tmpQuantity;
            _tmpQuantity = _cursor.getDouble(_cursorIndexOfQuantity);
            final double _tmpAverageCost;
            _tmpAverageCost = _cursor.getDouble(_cursorIndexOfAverageCost);
            final long _tmpPurchaseDate;
            _tmpPurchaseDate = _cursor.getLong(_cursorIndexOfPurchaseDate);
            final String _tmpCurrency;
            if (_cursor.isNull(_cursorIndexOfCurrency)) {
              _tmpCurrency = null;
            } else {
              _tmpCurrency = _cursor.getString(_cursorIndexOfCurrency);
            }
            final String _tmpBroker;
            if (_cursor.isNull(_cursorIndexOfBroker)) {
              _tmpBroker = null;
            } else {
              _tmpBroker = _cursor.getString(_cursorIndexOfBroker);
            }
            final InvestmentCategory _tmpCategory;
            _tmpCategory = __InvestmentCategory_stringToEnum(_cursor.getString(_cursorIndexOfCategory));
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final long _tmpLastUpdated;
            _tmpLastUpdated = _cursor.getLong(_cursorIndexOfLastUpdated);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new Investment(_tmpId,_tmpName,_tmpSymbol,_tmpType,_tmpInitialValue,_tmpCurrentValue,_tmpQuantity,_tmpAverageCost,_tmpPurchaseDate,_tmpCurrency,_tmpBroker,_tmpCategory,_tmpNotes,_tmpIsActive,_tmpLastUpdated,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getTotalDividendIncomeForPeriod(final long startDate, final long endDate,
      final Continuation<? super Double> $completion) {
    final String _sql = "\n"
            + "        SELECT SUM(totalAmount) FROM investment_dividends \n"
            + "        WHERE paymentDate >= ? AND paymentDate <= ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endDate);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final Double _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getDouble(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private String __InvestmentType_enumToString(@NonNull final InvestmentType _value) {
    switch (_value) {
      case STOCK: return "STOCK";
      case FUND: return "FUND";
      case ETF: return "ETF";
      case BOND: return "BOND";
      case CRYPTO: return "CRYPTO";
      case REAL_ESTATE: return "REAL_ESTATE";
      case COMMODITY: return "COMMODITY";
      case SAVINGS_ACCOUNT: return "SAVINGS_ACCOUNT";
      case PENSION: return "PENSION";
      case OTHER: return "OTHER";
      default: throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + _value);
    }
  }

  private String __InvestmentCategory_enumToString(@NonNull final InvestmentCategory _value) {
    switch (_value) {
      case EQUITY: return "EQUITY";
      case FIXED_INCOME: return "FIXED_INCOME";
      case MIXED: return "MIXED";
      case ALTERNATIVE: return "ALTERNATIVE";
      case CASH_EQUIVALENT: return "CASH_EQUIVALENT";
      default: throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + _value);
    }
  }

  private String __InvestmentTransactionType_enumToString(
      @NonNull final InvestmentTransactionType _value) {
    switch (_value) {
      case BUY: return "BUY";
      case SELL: return "SELL";
      case DIVIDEND: return "DIVIDEND";
      case SPLIT: return "SPLIT";
      case MERGER: return "MERGER";
      case SPIN_OFF: return "SPIN_OFF";
      case RIGHTS_ISSUE: return "RIGHTS_ISSUE";
      case BONUS_SHARES: return "BONUS_SHARES";
      default: throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + _value);
    }
  }

  private String __RiskProfile_enumToString(@NonNull final RiskProfile _value) {
    switch (_value) {
      case CONSERVATIVE: return "CONSERVATIVE";
      case MODERATE: return "MODERATE";
      case AGGRESSIVE: return "AGGRESSIVE";
      case SPECULATIVE: return "SPECULATIVE";
      default: throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + _value);
    }
  }

  private InvestmentType __InvestmentType_stringToEnum(@NonNull final String _value) {
    switch (_value) {
      case "STOCK": return InvestmentType.STOCK;
      case "FUND": return InvestmentType.FUND;
      case "ETF": return InvestmentType.ETF;
      case "BOND": return InvestmentType.BOND;
      case "CRYPTO": return InvestmentType.CRYPTO;
      case "REAL_ESTATE": return InvestmentType.REAL_ESTATE;
      case "COMMODITY": return InvestmentType.COMMODITY;
      case "SAVINGS_ACCOUNT": return InvestmentType.SAVINGS_ACCOUNT;
      case "PENSION": return InvestmentType.PENSION;
      case "OTHER": return InvestmentType.OTHER;
      default: throw new IllegalArgumentException("Can't convert value to enum, unknown value: " + _value);
    }
  }

  private InvestmentCategory __InvestmentCategory_stringToEnum(@NonNull final String _value) {
    switch (_value) {
      case "EQUITY": return InvestmentCategory.EQUITY;
      case "FIXED_INCOME": return InvestmentCategory.FIXED_INCOME;
      case "MIXED": return InvestmentCategory.MIXED;
      case "ALTERNATIVE": return InvestmentCategory.ALTERNATIVE;
      case "CASH_EQUIVALENT": return InvestmentCategory.CASH_EQUIVALENT;
      default: throw new IllegalArgumentException("Can't convert value to enum, unknown value: " + _value);
    }
  }

  private InvestmentTransactionType __InvestmentTransactionType_stringToEnum(
      @NonNull final String _value) {
    switch (_value) {
      case "BUY": return InvestmentTransactionType.BUY;
      case "SELL": return InvestmentTransactionType.SELL;
      case "DIVIDEND": return InvestmentTransactionType.DIVIDEND;
      case "SPLIT": return InvestmentTransactionType.SPLIT;
      case "MERGER": return InvestmentTransactionType.MERGER;
      case "SPIN_OFF": return InvestmentTransactionType.SPIN_OFF;
      case "RIGHTS_ISSUE": return InvestmentTransactionType.RIGHTS_ISSUE;
      case "BONUS_SHARES": return InvestmentTransactionType.BONUS_SHARES;
      default: throw new IllegalArgumentException("Can't convert value to enum, unknown value: " + _value);
    }
  }

  private RiskProfile __RiskProfile_stringToEnum(@NonNull final String _value) {
    switch (_value) {
      case "CONSERVATIVE": return RiskProfile.CONSERVATIVE;
      case "MODERATE": return RiskProfile.MODERATE;
      case "AGGRESSIVE": return RiskProfile.AGGRESSIVE;
      case "SPECULATIVE": return RiskProfile.SPECULATIVE;
      default: throw new IllegalArgumentException("Can't convert value to enum, unknown value: " + _value);
    }
  }
}
