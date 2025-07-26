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
import com.example.budgetapp.analytics.investments.multicurrency.CurrencyAlert;
import com.example.budgetapp.analytics.investments.multicurrency.CurrencyAlertType;
import com.example.budgetapp.analytics.investments.multicurrency.ExchangeRate;
import com.example.budgetapp.analytics.investments.multicurrency.MultiCurrencyTransaction;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
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
public final class ExchangeRateDao_Impl implements ExchangeRateDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ExchangeRate> __insertionAdapterOfExchangeRate;

  private final EntityInsertionAdapter<CurrencyAlert> __insertionAdapterOfCurrencyAlert;

  private final EntityInsertionAdapter<MultiCurrencyTransaction> __insertionAdapterOfMultiCurrencyTransaction;

  private final EntityDeletionOrUpdateAdapter<ExchangeRate> __deletionAdapterOfExchangeRate;

  private final EntityDeletionOrUpdateAdapter<CurrencyAlert> __deletionAdapterOfCurrencyAlert;

  private final EntityDeletionOrUpdateAdapter<MultiCurrencyTransaction> __deletionAdapterOfMultiCurrencyTransaction;

  private final EntityDeletionOrUpdateAdapter<ExchangeRate> __updateAdapterOfExchangeRate;

  private final EntityDeletionOrUpdateAdapter<CurrencyAlert> __updateAdapterOfCurrencyAlert;

  private final EntityDeletionOrUpdateAdapter<MultiCurrencyTransaction> __updateAdapterOfMultiCurrencyTransaction;

  private final SharedSQLiteStatement __preparedStmtOfDeleteOldExchangeRates;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllExchangeRates;

  private final SharedSQLiteStatement __preparedStmtOfDeactivateCurrencyAlert;

  private final SharedSQLiteStatement __preparedStmtOfTriggerCurrencyAlert;

  private final SharedSQLiteStatement __preparedStmtOfDeleteMultiCurrencyTransactionById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllMultiCurrencyTransactions;

  public ExchangeRateDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfExchangeRate = new EntityInsertionAdapter<ExchangeRate>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `exchange_rates` (`id`,`fromCurrency`,`toCurrency`,`rate`,`lastUpdated`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ExchangeRate entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getFromCurrency() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getFromCurrency());
        }
        if (entity.getToCurrency() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getToCurrency());
        }
        statement.bindDouble(4, entity.getRate());
        statement.bindLong(5, entity.getLastUpdated());
      }
    };
    this.__insertionAdapterOfCurrencyAlert = new EntityInsertionAdapter<CurrencyAlert>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `currency_alerts` (`id`,`fromCurrency`,`toCurrency`,`targetRate`,`alertType`,`isActive`,`createdAt`,`triggeredAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CurrencyAlert entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getFromCurrency() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getFromCurrency());
        }
        if (entity.getToCurrency() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getToCurrency());
        }
        statement.bindDouble(4, entity.getTargetRate());
        statement.bindString(5, __CurrencyAlertType_enumToString(entity.getAlertType()));
        final int _tmp = entity.isActive() ? 1 : 0;
        statement.bindLong(6, _tmp);
        statement.bindLong(7, entity.getCreatedAt());
        if (entity.getTriggeredAt() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getTriggeredAt());
        }
      }
    };
    this.__insertionAdapterOfMultiCurrencyTransaction = new EntityInsertionAdapter<MultiCurrencyTransaction>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `multi_currency_transactions` (`id`,`amount`,`currency`,`convertedAmount`,`baseCurrency`,`exchangeRate`,`category`,`description`,`transactionDate`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MultiCurrencyTransaction entity) {
        statement.bindLong(1, entity.getId());
        statement.bindDouble(2, entity.getAmount());
        if (entity.getCurrency() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getCurrency());
        }
        statement.bindDouble(4, entity.getConvertedAmount());
        if (entity.getBaseCurrency() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getBaseCurrency());
        }
        statement.bindDouble(6, entity.getExchangeRate());
        if (entity.getCategory() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getCategory());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getDescription());
        }
        statement.bindLong(9, entity.getTransactionDate());
        statement.bindLong(10, entity.getCreatedAt());
      }
    };
    this.__deletionAdapterOfExchangeRate = new EntityDeletionOrUpdateAdapter<ExchangeRate>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `exchange_rates` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ExchangeRate entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__deletionAdapterOfCurrencyAlert = new EntityDeletionOrUpdateAdapter<CurrencyAlert>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `currency_alerts` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CurrencyAlert entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__deletionAdapterOfMultiCurrencyTransaction = new EntityDeletionOrUpdateAdapter<MultiCurrencyTransaction>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `multi_currency_transactions` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MultiCurrencyTransaction entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfExchangeRate = new EntityDeletionOrUpdateAdapter<ExchangeRate>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `exchange_rates` SET `id` = ?,`fromCurrency` = ?,`toCurrency` = ?,`rate` = ?,`lastUpdated` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ExchangeRate entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getFromCurrency() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getFromCurrency());
        }
        if (entity.getToCurrency() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getToCurrency());
        }
        statement.bindDouble(4, entity.getRate());
        statement.bindLong(5, entity.getLastUpdated());
        statement.bindLong(6, entity.getId());
      }
    };
    this.__updateAdapterOfCurrencyAlert = new EntityDeletionOrUpdateAdapter<CurrencyAlert>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `currency_alerts` SET `id` = ?,`fromCurrency` = ?,`toCurrency` = ?,`targetRate` = ?,`alertType` = ?,`isActive` = ?,`createdAt` = ?,`triggeredAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CurrencyAlert entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getFromCurrency() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getFromCurrency());
        }
        if (entity.getToCurrency() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getToCurrency());
        }
        statement.bindDouble(4, entity.getTargetRate());
        statement.bindString(5, __CurrencyAlertType_enumToString(entity.getAlertType()));
        final int _tmp = entity.isActive() ? 1 : 0;
        statement.bindLong(6, _tmp);
        statement.bindLong(7, entity.getCreatedAt());
        if (entity.getTriggeredAt() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getTriggeredAt());
        }
        statement.bindLong(9, entity.getId());
      }
    };
    this.__updateAdapterOfMultiCurrencyTransaction = new EntityDeletionOrUpdateAdapter<MultiCurrencyTransaction>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `multi_currency_transactions` SET `id` = ?,`amount` = ?,`currency` = ?,`convertedAmount` = ?,`baseCurrency` = ?,`exchangeRate` = ?,`category` = ?,`description` = ?,`transactionDate` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MultiCurrencyTransaction entity) {
        statement.bindLong(1, entity.getId());
        statement.bindDouble(2, entity.getAmount());
        if (entity.getCurrency() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getCurrency());
        }
        statement.bindDouble(4, entity.getConvertedAmount());
        if (entity.getBaseCurrency() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getBaseCurrency());
        }
        statement.bindDouble(6, entity.getExchangeRate());
        if (entity.getCategory() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getCategory());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getDescription());
        }
        statement.bindLong(9, entity.getTransactionDate());
        statement.bindLong(10, entity.getCreatedAt());
        statement.bindLong(11, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteOldExchangeRates = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM exchange_rates WHERE lastUpdated < ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllExchangeRates = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM exchange_rates";
        return _query;
      }
    };
    this.__preparedStmtOfDeactivateCurrencyAlert = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE currency_alerts SET isActive = 0 WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfTriggerCurrencyAlert = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE currency_alerts SET isActive = 0, triggeredAt = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteMultiCurrencyTransactionById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM multi_currency_transactions WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllMultiCurrencyTransactions = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM multi_currency_transactions";
        return _query;
      }
    };
  }

  @Override
  public Object insertExchangeRate(final ExchangeRate exchangeRate,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfExchangeRate.insertAndReturnId(exchangeRate);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertExchangeRates(final List<ExchangeRate> exchangeRates,
      final Continuation<? super List<Long>> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<List<Long>>() {
      @Override
      @NonNull
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          final List<Long> _result = __insertionAdapterOfExchangeRate.insertAndReturnIdsList(exchangeRates);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertCurrencyAlert(final CurrencyAlert alert,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfCurrencyAlert.insertAndReturnId(alert);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertMultiCurrencyTransaction(final MultiCurrencyTransaction transaction,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfMultiCurrencyTransaction.insertAndReturnId(transaction);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertMultiCurrencyTransactions(final List<MultiCurrencyTransaction> transactions,
      final Continuation<? super List<Long>> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<List<Long>>() {
      @Override
      @NonNull
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          final List<Long> _result = __insertionAdapterOfMultiCurrencyTransaction.insertAndReturnIdsList(transactions);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteExchangeRate(final ExchangeRate exchangeRate,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfExchangeRate.handle(exchangeRate);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteCurrencyAlert(final CurrencyAlert alert,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfCurrencyAlert.handle(alert);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteMultiCurrencyTransaction(final MultiCurrencyTransaction transaction,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfMultiCurrencyTransaction.handle(transaction);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateExchangeRate(final ExchangeRate exchangeRate,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfExchangeRate.handle(exchangeRate);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateCurrencyAlert(final CurrencyAlert alert,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfCurrencyAlert.handle(alert);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateMultiCurrencyTransaction(final MultiCurrencyTransaction transaction,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfMultiCurrencyTransaction.handle(transaction);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteOldExchangeRates(final long cutoffTime,
      final Continuation<? super Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteOldExchangeRates.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, cutoffTime);
        try {
          __db.beginTransaction();
          try {
            final Integer _result = _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteOldExchangeRates.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllExchangeRates(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllExchangeRates.acquire();
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
          __preparedStmtOfDeleteAllExchangeRates.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deactivateCurrencyAlert(final long alertId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeactivateCurrencyAlert.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, alertId);
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
          __preparedStmtOfDeactivateCurrencyAlert.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object triggerCurrencyAlert(final long alertId, final long triggeredAt,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfTriggerCurrencyAlert.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, triggeredAt);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, alertId);
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
          __preparedStmtOfTriggerCurrencyAlert.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteMultiCurrencyTransactionById(final long transactionId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteMultiCurrencyTransactionById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, transactionId);
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
          __preparedStmtOfDeleteMultiCurrencyTransactionById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllMultiCurrencyTransactions(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllMultiCurrencyTransactions.acquire();
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
          __preparedStmtOfDeleteAllMultiCurrencyTransactions.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ExchangeRate>> getAllExchangeRates() {
    final String _sql = "SELECT * FROM exchange_rates ORDER BY lastUpdated DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"exchange_rates"}, new Callable<List<ExchangeRate>>() {
      @Override
      @NonNull
      public List<ExchangeRate> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFromCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "fromCurrency");
          final int _cursorIndexOfToCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "toCurrency");
          final int _cursorIndexOfRate = CursorUtil.getColumnIndexOrThrow(_cursor, "rate");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final List<ExchangeRate> _result = new ArrayList<ExchangeRate>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ExchangeRate _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpFromCurrency;
            if (_cursor.isNull(_cursorIndexOfFromCurrency)) {
              _tmpFromCurrency = null;
            } else {
              _tmpFromCurrency = _cursor.getString(_cursorIndexOfFromCurrency);
            }
            final String _tmpToCurrency;
            if (_cursor.isNull(_cursorIndexOfToCurrency)) {
              _tmpToCurrency = null;
            } else {
              _tmpToCurrency = _cursor.getString(_cursorIndexOfToCurrency);
            }
            final double _tmpRate;
            _tmpRate = _cursor.getDouble(_cursorIndexOfRate);
            final long _tmpLastUpdated;
            _tmpLastUpdated = _cursor.getLong(_cursorIndexOfLastUpdated);
            _item = new ExchangeRate(_tmpId,_tmpFromCurrency,_tmpToCurrency,_tmpRate,_tmpLastUpdated);
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
  public Object getLatestExchangeRate(final String fromCurrency, final String toCurrency,
      final Continuation<? super ExchangeRate> $completion) {
    final String _sql = "SELECT * FROM exchange_rates WHERE fromCurrency = ? AND toCurrency = ? ORDER BY lastUpdated DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (fromCurrency == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, fromCurrency);
    }
    _argIndex = 2;
    if (toCurrency == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, toCurrency);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ExchangeRate>() {
      @Override
      @Nullable
      public ExchangeRate call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFromCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "fromCurrency");
          final int _cursorIndexOfToCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "toCurrency");
          final int _cursorIndexOfRate = CursorUtil.getColumnIndexOrThrow(_cursor, "rate");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final ExchangeRate _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpFromCurrency;
            if (_cursor.isNull(_cursorIndexOfFromCurrency)) {
              _tmpFromCurrency = null;
            } else {
              _tmpFromCurrency = _cursor.getString(_cursorIndexOfFromCurrency);
            }
            final String _tmpToCurrency;
            if (_cursor.isNull(_cursorIndexOfToCurrency)) {
              _tmpToCurrency = null;
            } else {
              _tmpToCurrency = _cursor.getString(_cursorIndexOfToCurrency);
            }
            final double _tmpRate;
            _tmpRate = _cursor.getDouble(_cursorIndexOfRate);
            final long _tmpLastUpdated;
            _tmpLastUpdated = _cursor.getLong(_cursorIndexOfLastUpdated);
            _result = new ExchangeRate(_tmpId,_tmpFromCurrency,_tmpToCurrency,_tmpRate,_tmpLastUpdated);
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
  public Flow<List<ExchangeRate>> getExchangeRatesFromCurrency(final String fromCurrency) {
    final String _sql = "SELECT * FROM exchange_rates WHERE fromCurrency = ? ORDER BY lastUpdated DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (fromCurrency == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, fromCurrency);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"exchange_rates"}, new Callable<List<ExchangeRate>>() {
      @Override
      @NonNull
      public List<ExchangeRate> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFromCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "fromCurrency");
          final int _cursorIndexOfToCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "toCurrency");
          final int _cursorIndexOfRate = CursorUtil.getColumnIndexOrThrow(_cursor, "rate");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final List<ExchangeRate> _result = new ArrayList<ExchangeRate>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ExchangeRate _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpFromCurrency;
            if (_cursor.isNull(_cursorIndexOfFromCurrency)) {
              _tmpFromCurrency = null;
            } else {
              _tmpFromCurrency = _cursor.getString(_cursorIndexOfFromCurrency);
            }
            final String _tmpToCurrency;
            if (_cursor.isNull(_cursorIndexOfToCurrency)) {
              _tmpToCurrency = null;
            } else {
              _tmpToCurrency = _cursor.getString(_cursorIndexOfToCurrency);
            }
            final double _tmpRate;
            _tmpRate = _cursor.getDouble(_cursorIndexOfRate);
            final long _tmpLastUpdated;
            _tmpLastUpdated = _cursor.getLong(_cursorIndexOfLastUpdated);
            _item = new ExchangeRate(_tmpId,_tmpFromCurrency,_tmpToCurrency,_tmpRate,_tmpLastUpdated);
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
  public Flow<List<ExchangeRate>> getExchangeRatesToCurrency(final String toCurrency) {
    final String _sql = "SELECT * FROM exchange_rates WHERE toCurrency = ? ORDER BY lastUpdated DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (toCurrency == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, toCurrency);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"exchange_rates"}, new Callable<List<ExchangeRate>>() {
      @Override
      @NonNull
      public List<ExchangeRate> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFromCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "fromCurrency");
          final int _cursorIndexOfToCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "toCurrency");
          final int _cursorIndexOfRate = CursorUtil.getColumnIndexOrThrow(_cursor, "rate");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final List<ExchangeRate> _result = new ArrayList<ExchangeRate>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ExchangeRate _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpFromCurrency;
            if (_cursor.isNull(_cursorIndexOfFromCurrency)) {
              _tmpFromCurrency = null;
            } else {
              _tmpFromCurrency = _cursor.getString(_cursorIndexOfFromCurrency);
            }
            final String _tmpToCurrency;
            if (_cursor.isNull(_cursorIndexOfToCurrency)) {
              _tmpToCurrency = null;
            } else {
              _tmpToCurrency = _cursor.getString(_cursorIndexOfToCurrency);
            }
            final double _tmpRate;
            _tmpRate = _cursor.getDouble(_cursorIndexOfRate);
            final long _tmpLastUpdated;
            _tmpLastUpdated = _cursor.getLong(_cursorIndexOfLastUpdated);
            _item = new ExchangeRate(_tmpId,_tmpFromCurrency,_tmpToCurrency,_tmpRate,_tmpLastUpdated);
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
  public Flow<List<ExchangeRate>> getRecentExchangeRates(final long since) {
    final String _sql = "SELECT * FROM exchange_rates WHERE lastUpdated >= ? ORDER BY lastUpdated DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, since);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"exchange_rates"}, new Callable<List<ExchangeRate>>() {
      @Override
      @NonNull
      public List<ExchangeRate> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFromCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "fromCurrency");
          final int _cursorIndexOfToCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "toCurrency");
          final int _cursorIndexOfRate = CursorUtil.getColumnIndexOrThrow(_cursor, "rate");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final List<ExchangeRate> _result = new ArrayList<ExchangeRate>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ExchangeRate _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpFromCurrency;
            if (_cursor.isNull(_cursorIndexOfFromCurrency)) {
              _tmpFromCurrency = null;
            } else {
              _tmpFromCurrency = _cursor.getString(_cursorIndexOfFromCurrency);
            }
            final String _tmpToCurrency;
            if (_cursor.isNull(_cursorIndexOfToCurrency)) {
              _tmpToCurrency = null;
            } else {
              _tmpToCurrency = _cursor.getString(_cursorIndexOfToCurrency);
            }
            final double _tmpRate;
            _tmpRate = _cursor.getDouble(_cursorIndexOfRate);
            final long _tmpLastUpdated;
            _tmpLastUpdated = _cursor.getLong(_cursorIndexOfLastUpdated);
            _item = new ExchangeRate(_tmpId,_tmpFromCurrency,_tmpToCurrency,_tmpRate,_tmpLastUpdated);
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
  public Flow<List<CurrencyAlert>> getActiveCurrencyAlerts() {
    final String _sql = "SELECT * FROM currency_alerts WHERE isActive = 1 ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"currency_alerts"}, new Callable<List<CurrencyAlert>>() {
      @Override
      @NonNull
      public List<CurrencyAlert> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFromCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "fromCurrency");
          final int _cursorIndexOfToCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "toCurrency");
          final int _cursorIndexOfTargetRate = CursorUtil.getColumnIndexOrThrow(_cursor, "targetRate");
          final int _cursorIndexOfAlertType = CursorUtil.getColumnIndexOrThrow(_cursor, "alertType");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfTriggeredAt = CursorUtil.getColumnIndexOrThrow(_cursor, "triggeredAt");
          final List<CurrencyAlert> _result = new ArrayList<CurrencyAlert>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CurrencyAlert _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpFromCurrency;
            if (_cursor.isNull(_cursorIndexOfFromCurrency)) {
              _tmpFromCurrency = null;
            } else {
              _tmpFromCurrency = _cursor.getString(_cursorIndexOfFromCurrency);
            }
            final String _tmpToCurrency;
            if (_cursor.isNull(_cursorIndexOfToCurrency)) {
              _tmpToCurrency = null;
            } else {
              _tmpToCurrency = _cursor.getString(_cursorIndexOfToCurrency);
            }
            final double _tmpTargetRate;
            _tmpTargetRate = _cursor.getDouble(_cursorIndexOfTargetRate);
            final CurrencyAlertType _tmpAlertType;
            _tmpAlertType = __CurrencyAlertType_stringToEnum(_cursor.getString(_cursorIndexOfAlertType));
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpTriggeredAt;
            if (_cursor.isNull(_cursorIndexOfTriggeredAt)) {
              _tmpTriggeredAt = null;
            } else {
              _tmpTriggeredAt = _cursor.getLong(_cursorIndexOfTriggeredAt);
            }
            _item = new CurrencyAlert(_tmpId,_tmpFromCurrency,_tmpToCurrency,_tmpTargetRate,_tmpAlertType,_tmpIsActive,_tmpCreatedAt,_tmpTriggeredAt);
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
  public Flow<List<CurrencyAlert>> getAllCurrencyAlerts() {
    final String _sql = "SELECT * FROM currency_alerts ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"currency_alerts"}, new Callable<List<CurrencyAlert>>() {
      @Override
      @NonNull
      public List<CurrencyAlert> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFromCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "fromCurrency");
          final int _cursorIndexOfToCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "toCurrency");
          final int _cursorIndexOfTargetRate = CursorUtil.getColumnIndexOrThrow(_cursor, "targetRate");
          final int _cursorIndexOfAlertType = CursorUtil.getColumnIndexOrThrow(_cursor, "alertType");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfTriggeredAt = CursorUtil.getColumnIndexOrThrow(_cursor, "triggeredAt");
          final List<CurrencyAlert> _result = new ArrayList<CurrencyAlert>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CurrencyAlert _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpFromCurrency;
            if (_cursor.isNull(_cursorIndexOfFromCurrency)) {
              _tmpFromCurrency = null;
            } else {
              _tmpFromCurrency = _cursor.getString(_cursorIndexOfFromCurrency);
            }
            final String _tmpToCurrency;
            if (_cursor.isNull(_cursorIndexOfToCurrency)) {
              _tmpToCurrency = null;
            } else {
              _tmpToCurrency = _cursor.getString(_cursorIndexOfToCurrency);
            }
            final double _tmpTargetRate;
            _tmpTargetRate = _cursor.getDouble(_cursorIndexOfTargetRate);
            final CurrencyAlertType _tmpAlertType;
            _tmpAlertType = __CurrencyAlertType_stringToEnum(_cursor.getString(_cursorIndexOfAlertType));
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpTriggeredAt;
            if (_cursor.isNull(_cursorIndexOfTriggeredAt)) {
              _tmpTriggeredAt = null;
            } else {
              _tmpTriggeredAt = _cursor.getLong(_cursorIndexOfTriggeredAt);
            }
            _item = new CurrencyAlert(_tmpId,_tmpFromCurrency,_tmpToCurrency,_tmpTargetRate,_tmpAlertType,_tmpIsActive,_tmpCreatedAt,_tmpTriggeredAt);
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
  public Flow<List<CurrencyAlert>> getCurrencyAlertsForPair(final String fromCurrency,
      final String toCurrency) {
    final String _sql = "SELECT * FROM currency_alerts WHERE fromCurrency = ? AND toCurrency = ? AND isActive = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (fromCurrency == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, fromCurrency);
    }
    _argIndex = 2;
    if (toCurrency == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, toCurrency);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"currency_alerts"}, new Callable<List<CurrencyAlert>>() {
      @Override
      @NonNull
      public List<CurrencyAlert> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFromCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "fromCurrency");
          final int _cursorIndexOfToCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "toCurrency");
          final int _cursorIndexOfTargetRate = CursorUtil.getColumnIndexOrThrow(_cursor, "targetRate");
          final int _cursorIndexOfAlertType = CursorUtil.getColumnIndexOrThrow(_cursor, "alertType");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfTriggeredAt = CursorUtil.getColumnIndexOrThrow(_cursor, "triggeredAt");
          final List<CurrencyAlert> _result = new ArrayList<CurrencyAlert>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CurrencyAlert _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpFromCurrency;
            if (_cursor.isNull(_cursorIndexOfFromCurrency)) {
              _tmpFromCurrency = null;
            } else {
              _tmpFromCurrency = _cursor.getString(_cursorIndexOfFromCurrency);
            }
            final String _tmpToCurrency;
            if (_cursor.isNull(_cursorIndexOfToCurrency)) {
              _tmpToCurrency = null;
            } else {
              _tmpToCurrency = _cursor.getString(_cursorIndexOfToCurrency);
            }
            final double _tmpTargetRate;
            _tmpTargetRate = _cursor.getDouble(_cursorIndexOfTargetRate);
            final CurrencyAlertType _tmpAlertType;
            _tmpAlertType = __CurrencyAlertType_stringToEnum(_cursor.getString(_cursorIndexOfAlertType));
            final boolean _tmpIsActive;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpTriggeredAt;
            if (_cursor.isNull(_cursorIndexOfTriggeredAt)) {
              _tmpTriggeredAt = null;
            } else {
              _tmpTriggeredAt = _cursor.getLong(_cursorIndexOfTriggeredAt);
            }
            _item = new CurrencyAlert(_tmpId,_tmpFromCurrency,_tmpToCurrency,_tmpTargetRate,_tmpAlertType,_tmpIsActive,_tmpCreatedAt,_tmpTriggeredAt);
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
  public Flow<List<MultiCurrencyTransaction>> getAllMultiCurrencyTransactions() {
    final String _sql = "SELECT * FROM multi_currency_transactions ORDER BY transactionDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"multi_currency_transactions"}, new Callable<List<MultiCurrencyTransaction>>() {
      @Override
      @NonNull
      public List<MultiCurrencyTransaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "currency");
          final int _cursorIndexOfConvertedAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "convertedAmount");
          final int _cursorIndexOfBaseCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "baseCurrency");
          final int _cursorIndexOfExchangeRate = CursorUtil.getColumnIndexOrThrow(_cursor, "exchangeRate");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTransactionDate = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionDate");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<MultiCurrencyTransaction> _result = new ArrayList<MultiCurrencyTransaction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MultiCurrencyTransaction _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpCurrency;
            if (_cursor.isNull(_cursorIndexOfCurrency)) {
              _tmpCurrency = null;
            } else {
              _tmpCurrency = _cursor.getString(_cursorIndexOfCurrency);
            }
            final double _tmpConvertedAmount;
            _tmpConvertedAmount = _cursor.getDouble(_cursorIndexOfConvertedAmount);
            final String _tmpBaseCurrency;
            if (_cursor.isNull(_cursorIndexOfBaseCurrency)) {
              _tmpBaseCurrency = null;
            } else {
              _tmpBaseCurrency = _cursor.getString(_cursorIndexOfBaseCurrency);
            }
            final double _tmpExchangeRate;
            _tmpExchangeRate = _cursor.getDouble(_cursorIndexOfExchangeRate);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final long _tmpTransactionDate;
            _tmpTransactionDate = _cursor.getLong(_cursorIndexOfTransactionDate);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new MultiCurrencyTransaction(_tmpId,_tmpAmount,_tmpCurrency,_tmpConvertedAmount,_tmpBaseCurrency,_tmpExchangeRate,_tmpCategory,_tmpDescription,_tmpTransactionDate,_tmpCreatedAt);
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
  public Flow<List<MultiCurrencyTransaction>> getTransactionsByCurrency(final String currency) {
    final String _sql = "SELECT * FROM multi_currency_transactions WHERE currency = ? ORDER BY transactionDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (currency == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, currency);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"multi_currency_transactions"}, new Callable<List<MultiCurrencyTransaction>>() {
      @Override
      @NonNull
      public List<MultiCurrencyTransaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "currency");
          final int _cursorIndexOfConvertedAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "convertedAmount");
          final int _cursorIndexOfBaseCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "baseCurrency");
          final int _cursorIndexOfExchangeRate = CursorUtil.getColumnIndexOrThrow(_cursor, "exchangeRate");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTransactionDate = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionDate");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<MultiCurrencyTransaction> _result = new ArrayList<MultiCurrencyTransaction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MultiCurrencyTransaction _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpCurrency;
            if (_cursor.isNull(_cursorIndexOfCurrency)) {
              _tmpCurrency = null;
            } else {
              _tmpCurrency = _cursor.getString(_cursorIndexOfCurrency);
            }
            final double _tmpConvertedAmount;
            _tmpConvertedAmount = _cursor.getDouble(_cursorIndexOfConvertedAmount);
            final String _tmpBaseCurrency;
            if (_cursor.isNull(_cursorIndexOfBaseCurrency)) {
              _tmpBaseCurrency = null;
            } else {
              _tmpBaseCurrency = _cursor.getString(_cursorIndexOfBaseCurrency);
            }
            final double _tmpExchangeRate;
            _tmpExchangeRate = _cursor.getDouble(_cursorIndexOfExchangeRate);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final long _tmpTransactionDate;
            _tmpTransactionDate = _cursor.getLong(_cursorIndexOfTransactionDate);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new MultiCurrencyTransaction(_tmpId,_tmpAmount,_tmpCurrency,_tmpConvertedAmount,_tmpBaseCurrency,_tmpExchangeRate,_tmpCategory,_tmpDescription,_tmpTransactionDate,_tmpCreatedAt);
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
  public Flow<List<MultiCurrencyTransaction>> getTransactionsByBaseCurrency(
      final String baseCurrency) {
    final String _sql = "SELECT * FROM multi_currency_transactions WHERE baseCurrency = ? ORDER BY transactionDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (baseCurrency == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, baseCurrency);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"multi_currency_transactions"}, new Callable<List<MultiCurrencyTransaction>>() {
      @Override
      @NonNull
      public List<MultiCurrencyTransaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "currency");
          final int _cursorIndexOfConvertedAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "convertedAmount");
          final int _cursorIndexOfBaseCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "baseCurrency");
          final int _cursorIndexOfExchangeRate = CursorUtil.getColumnIndexOrThrow(_cursor, "exchangeRate");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTransactionDate = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionDate");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<MultiCurrencyTransaction> _result = new ArrayList<MultiCurrencyTransaction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MultiCurrencyTransaction _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpCurrency;
            if (_cursor.isNull(_cursorIndexOfCurrency)) {
              _tmpCurrency = null;
            } else {
              _tmpCurrency = _cursor.getString(_cursorIndexOfCurrency);
            }
            final double _tmpConvertedAmount;
            _tmpConvertedAmount = _cursor.getDouble(_cursorIndexOfConvertedAmount);
            final String _tmpBaseCurrency;
            if (_cursor.isNull(_cursorIndexOfBaseCurrency)) {
              _tmpBaseCurrency = null;
            } else {
              _tmpBaseCurrency = _cursor.getString(_cursorIndexOfBaseCurrency);
            }
            final double _tmpExchangeRate;
            _tmpExchangeRate = _cursor.getDouble(_cursorIndexOfExchangeRate);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final long _tmpTransactionDate;
            _tmpTransactionDate = _cursor.getLong(_cursorIndexOfTransactionDate);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new MultiCurrencyTransaction(_tmpId,_tmpAmount,_tmpCurrency,_tmpConvertedAmount,_tmpBaseCurrency,_tmpExchangeRate,_tmpCategory,_tmpDescription,_tmpTransactionDate,_tmpCreatedAt);
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
  public Flow<List<MultiCurrencyTransaction>> getTransactionsByCategory(final String category) {
    final String _sql = "SELECT * FROM multi_currency_transactions WHERE category = ? ORDER BY transactionDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (category == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, category);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"multi_currency_transactions"}, new Callable<List<MultiCurrencyTransaction>>() {
      @Override
      @NonNull
      public List<MultiCurrencyTransaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "currency");
          final int _cursorIndexOfConvertedAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "convertedAmount");
          final int _cursorIndexOfBaseCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "baseCurrency");
          final int _cursorIndexOfExchangeRate = CursorUtil.getColumnIndexOrThrow(_cursor, "exchangeRate");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTransactionDate = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionDate");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<MultiCurrencyTransaction> _result = new ArrayList<MultiCurrencyTransaction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MultiCurrencyTransaction _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpCurrency;
            if (_cursor.isNull(_cursorIndexOfCurrency)) {
              _tmpCurrency = null;
            } else {
              _tmpCurrency = _cursor.getString(_cursorIndexOfCurrency);
            }
            final double _tmpConvertedAmount;
            _tmpConvertedAmount = _cursor.getDouble(_cursorIndexOfConvertedAmount);
            final String _tmpBaseCurrency;
            if (_cursor.isNull(_cursorIndexOfBaseCurrency)) {
              _tmpBaseCurrency = null;
            } else {
              _tmpBaseCurrency = _cursor.getString(_cursorIndexOfBaseCurrency);
            }
            final double _tmpExchangeRate;
            _tmpExchangeRate = _cursor.getDouble(_cursorIndexOfExchangeRate);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final long _tmpTransactionDate;
            _tmpTransactionDate = _cursor.getLong(_cursorIndexOfTransactionDate);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new MultiCurrencyTransaction(_tmpId,_tmpAmount,_tmpCurrency,_tmpConvertedAmount,_tmpBaseCurrency,_tmpExchangeRate,_tmpCategory,_tmpDescription,_tmpTransactionDate,_tmpCreatedAt);
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
  public Flow<List<MultiCurrencyTransaction>> getTransactionsByDateRange(final long startDate,
      final long endDate) {
    final String _sql = "SELECT * FROM multi_currency_transactions WHERE transactionDate BETWEEN ? AND ? ORDER BY transactionDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endDate);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"multi_currency_transactions"}, new Callable<List<MultiCurrencyTransaction>>() {
      @Override
      @NonNull
      public List<MultiCurrencyTransaction> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "currency");
          final int _cursorIndexOfConvertedAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "convertedAmount");
          final int _cursorIndexOfBaseCurrency = CursorUtil.getColumnIndexOrThrow(_cursor, "baseCurrency");
          final int _cursorIndexOfExchangeRate = CursorUtil.getColumnIndexOrThrow(_cursor, "exchangeRate");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTransactionDate = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionDate");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<MultiCurrencyTransaction> _result = new ArrayList<MultiCurrencyTransaction>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MultiCurrencyTransaction _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final String _tmpCurrency;
            if (_cursor.isNull(_cursorIndexOfCurrency)) {
              _tmpCurrency = null;
            } else {
              _tmpCurrency = _cursor.getString(_cursorIndexOfCurrency);
            }
            final double _tmpConvertedAmount;
            _tmpConvertedAmount = _cursor.getDouble(_cursorIndexOfConvertedAmount);
            final String _tmpBaseCurrency;
            if (_cursor.isNull(_cursorIndexOfBaseCurrency)) {
              _tmpBaseCurrency = null;
            } else {
              _tmpBaseCurrency = _cursor.getString(_cursorIndexOfBaseCurrency);
            }
            final double _tmpExchangeRate;
            _tmpExchangeRate = _cursor.getDouble(_cursorIndexOfExchangeRate);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final long _tmpTransactionDate;
            _tmpTransactionDate = _cursor.getLong(_cursorIndexOfTransactionDate);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new MultiCurrencyTransaction(_tmpId,_tmpAmount,_tmpCurrency,_tmpConvertedAmount,_tmpBaseCurrency,_tmpExchangeRate,_tmpCategory,_tmpDescription,_tmpTransactionDate,_tmpCreatedAt);
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
  public Object getUsedCurrencies(final Continuation<? super List<String>> $completion) {
    final String _sql = "SELECT DISTINCT currency FROM multi_currency_transactions ORDER BY currency";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<String>>() {
      @Override
      @NonNull
      public List<String> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<String> _result = new ArrayList<String>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final String _item;
            if (_cursor.isNull(0)) {
              _item = null;
            } else {
              _item = _cursor.getString(0);
            }
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
  public Object getUsedBaseCurrencies(final Continuation<? super List<String>> $completion) {
    final String _sql = "SELECT DISTINCT baseCurrency FROM multi_currency_transactions ORDER BY baseCurrency";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<String>>() {
      @Override
      @NonNull
      public List<String> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<String> _result = new ArrayList<String>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final String _item;
            if (_cursor.isNull(0)) {
              _item = null;
            } else {
              _item = _cursor.getString(0);
            }
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
  public Object getUsedCategories(final Continuation<? super List<String>> $completion) {
    final String _sql = "SELECT DISTINCT category FROM multi_currency_transactions ORDER BY category";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<String>>() {
      @Override
      @NonNull
      public List<String> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<String> _result = new ArrayList<String>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final String _item;
            if (_cursor.isNull(0)) {
              _item = null;
            } else {
              _item = _cursor.getString(0);
            }
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
  public Object getTotalAmountInBaseCurrency(final String baseCurrency, final long startDate,
      final long endDate, final Continuation<? super Double> $completion) {
    final String _sql = "SELECT SUM(convertedAmount) FROM multi_currency_transactions WHERE baseCurrency = ? AND transactionDate BETWEEN ? AND ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    if (baseCurrency == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, baseCurrency);
    }
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
  public Object getTotalAmountInCurrency(final String currency, final long startDate,
      final long endDate, final Continuation<? super Double> $completion) {
    final String _sql = "SELECT SUM(amount) FROM multi_currency_transactions WHERE currency = ? AND transactionDate BETWEEN ? AND ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    if (currency == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, currency);
    }
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private String __CurrencyAlertType_enumToString(@NonNull final CurrencyAlertType _value) {
    switch (_value) {
      case ABOVE: return "ABOVE";
      case BELOW: return "BELOW";
      case CHANGE_PERCENT: return "CHANGE_PERCENT";
      default: throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + _value);
    }
  }

  private CurrencyAlertType __CurrencyAlertType_stringToEnum(@NonNull final String _value) {
    switch (_value) {
      case "ABOVE": return CurrencyAlertType.ABOVE;
      case "BELOW": return CurrencyAlertType.BELOW;
      case "CHANGE_PERCENT": return CurrencyAlertType.CHANGE_PERCENT;
      default: throw new IllegalArgumentException("Can't convert value to enum, unknown value: " + _value);
    }
  }
}
