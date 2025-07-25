package com.example.budgetapp.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.budgetapp.database.entities.AnalyticsTypeConverters;
import com.example.budgetapp.database.entities.ForecastMethod;
import com.example.budgetapp.database.entities.SpendingForecast;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
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
public final class SpendingForecastDao_Impl implements SpendingForecastDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SpendingForecast> __insertionAdapterOfSpendingForecast;

  private final AnalyticsTypeConverters __analyticsTypeConverters = new AnalyticsTypeConverters();

  private final SharedSQLiteStatement __preparedStmtOfUpdateForecastActual;

  private final SharedSQLiteStatement __preparedStmtOfDeleteExpiredForecasts;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllForecasts;

  public SpendingForecastDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSpendingForecast = new EntityInsertionAdapter<SpendingForecast>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `spending_forecasts` (`id`,`category`,`forecastDate`,`predictedAmount`,`confidenceInterval`,`confidenceLevel`,`forecastMethod`,`basedOnDays`,`seasonalityFactor`,`trendFactor`,`actualAmount`,`accuracy`,`createdAt`,`validUntil`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SpendingForecast entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getCategory() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getCategory());
        }
        statement.bindLong(3, entity.getForecastDate());
        statement.bindDouble(4, entity.getPredictedAmount());
        statement.bindDouble(5, entity.getConfidenceInterval());
        statement.bindDouble(6, entity.getConfidenceLevel());
        final String _tmp = __analyticsTypeConverters.fromForecastMethod(entity.getForecastMethod());
        if (_tmp == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, _tmp);
        }
        statement.bindLong(8, entity.getBasedOnDays());
        statement.bindDouble(9, entity.getSeasonalityFactor());
        statement.bindDouble(10, entity.getTrendFactor());
        if (entity.getActualAmount() == null) {
          statement.bindNull(11);
        } else {
          statement.bindDouble(11, entity.getActualAmount());
        }
        if (entity.getAccuracy() == null) {
          statement.bindNull(12);
        } else {
          statement.bindDouble(12, entity.getAccuracy());
        }
        statement.bindLong(13, entity.getCreatedAt());
        statement.bindLong(14, entity.getValidUntil());
      }
    };
    this.__preparedStmtOfUpdateForecastActual = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE spending_forecasts SET actualAmount = ?, accuracy = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteExpiredForecasts = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM spending_forecasts WHERE validUntil < ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllForecasts = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM spending_forecasts";
        return _query;
      }
    };
  }

  @Override
  public Object insertForecast(final SpendingForecast forecast,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfSpendingForecast.insertAndReturnId(forecast);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertForecasts(final List<SpendingForecast> forecasts,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfSpendingForecast.insert(forecasts);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateForecastActual(final long id, final double actual, final double accuracy,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateForecastActual.acquire();
        int _argIndex = 1;
        _stmt.bindDouble(_argIndex, actual);
        _argIndex = 2;
        _stmt.bindDouble(_argIndex, accuracy);
        _argIndex = 3;
        _stmt.bindLong(_argIndex, id);
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
          __preparedStmtOfUpdateForecastActual.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteExpiredForecasts(final long currentTime,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteExpiredForecasts.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, currentTime);
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
          __preparedStmtOfDeleteExpiredForecasts.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllForecasts(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllForecasts.acquire();
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
          __preparedStmtOfDeleteAllForecasts.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<SpendingForecast>> getAllForecasts() {
    final String _sql = "SELECT * FROM spending_forecasts ORDER BY forecastDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"spending_forecasts"}, new Callable<List<SpendingForecast>>() {
      @Override
      @NonNull
      public List<SpendingForecast> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfForecastDate = CursorUtil.getColumnIndexOrThrow(_cursor, "forecastDate");
          final int _cursorIndexOfPredictedAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "predictedAmount");
          final int _cursorIndexOfConfidenceInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "confidenceInterval");
          final int _cursorIndexOfConfidenceLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "confidenceLevel");
          final int _cursorIndexOfForecastMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "forecastMethod");
          final int _cursorIndexOfBasedOnDays = CursorUtil.getColumnIndexOrThrow(_cursor, "basedOnDays");
          final int _cursorIndexOfSeasonalityFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "seasonalityFactor");
          final int _cursorIndexOfTrendFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "trendFactor");
          final int _cursorIndexOfActualAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "actualAmount");
          final int _cursorIndexOfAccuracy = CursorUtil.getColumnIndexOrThrow(_cursor, "accuracy");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfValidUntil = CursorUtil.getColumnIndexOrThrow(_cursor, "validUntil");
          final List<SpendingForecast> _result = new ArrayList<SpendingForecast>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SpendingForecast _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final long _tmpForecastDate;
            _tmpForecastDate = _cursor.getLong(_cursorIndexOfForecastDate);
            final double _tmpPredictedAmount;
            _tmpPredictedAmount = _cursor.getDouble(_cursorIndexOfPredictedAmount);
            final double _tmpConfidenceInterval;
            _tmpConfidenceInterval = _cursor.getDouble(_cursorIndexOfConfidenceInterval);
            final float _tmpConfidenceLevel;
            _tmpConfidenceLevel = _cursor.getFloat(_cursorIndexOfConfidenceLevel);
            final ForecastMethod _tmpForecastMethod;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfForecastMethod)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfForecastMethod);
            }
            _tmpForecastMethod = __analyticsTypeConverters.toForecastMethod(_tmp);
            final int _tmpBasedOnDays;
            _tmpBasedOnDays = _cursor.getInt(_cursorIndexOfBasedOnDays);
            final double _tmpSeasonalityFactor;
            _tmpSeasonalityFactor = _cursor.getDouble(_cursorIndexOfSeasonalityFactor);
            final double _tmpTrendFactor;
            _tmpTrendFactor = _cursor.getDouble(_cursorIndexOfTrendFactor);
            final Double _tmpActualAmount;
            if (_cursor.isNull(_cursorIndexOfActualAmount)) {
              _tmpActualAmount = null;
            } else {
              _tmpActualAmount = _cursor.getDouble(_cursorIndexOfActualAmount);
            }
            final Double _tmpAccuracy;
            if (_cursor.isNull(_cursorIndexOfAccuracy)) {
              _tmpAccuracy = null;
            } else {
              _tmpAccuracy = _cursor.getDouble(_cursorIndexOfAccuracy);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpValidUntil;
            _tmpValidUntil = _cursor.getLong(_cursorIndexOfValidUntil);
            _item = new SpendingForecast(_tmpId,_tmpCategory,_tmpForecastDate,_tmpPredictedAmount,_tmpConfidenceInterval,_tmpConfidenceLevel,_tmpForecastMethod,_tmpBasedOnDays,_tmpSeasonalityFactor,_tmpTrendFactor,_tmpActualAmount,_tmpAccuracy,_tmpCreatedAt,_tmpValidUntil);
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
  public Flow<List<SpendingForecast>> getForecastsByCategory(final String category) {
    final String _sql = "SELECT * FROM spending_forecasts WHERE category = ? ORDER BY forecastDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (category == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, category);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"spending_forecasts"}, new Callable<List<SpendingForecast>>() {
      @Override
      @NonNull
      public List<SpendingForecast> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfForecastDate = CursorUtil.getColumnIndexOrThrow(_cursor, "forecastDate");
          final int _cursorIndexOfPredictedAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "predictedAmount");
          final int _cursorIndexOfConfidenceInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "confidenceInterval");
          final int _cursorIndexOfConfidenceLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "confidenceLevel");
          final int _cursorIndexOfForecastMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "forecastMethod");
          final int _cursorIndexOfBasedOnDays = CursorUtil.getColumnIndexOrThrow(_cursor, "basedOnDays");
          final int _cursorIndexOfSeasonalityFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "seasonalityFactor");
          final int _cursorIndexOfTrendFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "trendFactor");
          final int _cursorIndexOfActualAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "actualAmount");
          final int _cursorIndexOfAccuracy = CursorUtil.getColumnIndexOrThrow(_cursor, "accuracy");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfValidUntil = CursorUtil.getColumnIndexOrThrow(_cursor, "validUntil");
          final List<SpendingForecast> _result = new ArrayList<SpendingForecast>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SpendingForecast _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final long _tmpForecastDate;
            _tmpForecastDate = _cursor.getLong(_cursorIndexOfForecastDate);
            final double _tmpPredictedAmount;
            _tmpPredictedAmount = _cursor.getDouble(_cursorIndexOfPredictedAmount);
            final double _tmpConfidenceInterval;
            _tmpConfidenceInterval = _cursor.getDouble(_cursorIndexOfConfidenceInterval);
            final float _tmpConfidenceLevel;
            _tmpConfidenceLevel = _cursor.getFloat(_cursorIndexOfConfidenceLevel);
            final ForecastMethod _tmpForecastMethod;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfForecastMethod)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfForecastMethod);
            }
            _tmpForecastMethod = __analyticsTypeConverters.toForecastMethod(_tmp);
            final int _tmpBasedOnDays;
            _tmpBasedOnDays = _cursor.getInt(_cursorIndexOfBasedOnDays);
            final double _tmpSeasonalityFactor;
            _tmpSeasonalityFactor = _cursor.getDouble(_cursorIndexOfSeasonalityFactor);
            final double _tmpTrendFactor;
            _tmpTrendFactor = _cursor.getDouble(_cursorIndexOfTrendFactor);
            final Double _tmpActualAmount;
            if (_cursor.isNull(_cursorIndexOfActualAmount)) {
              _tmpActualAmount = null;
            } else {
              _tmpActualAmount = _cursor.getDouble(_cursorIndexOfActualAmount);
            }
            final Double _tmpAccuracy;
            if (_cursor.isNull(_cursorIndexOfAccuracy)) {
              _tmpAccuracy = null;
            } else {
              _tmpAccuracy = _cursor.getDouble(_cursorIndexOfAccuracy);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpValidUntil;
            _tmpValidUntil = _cursor.getLong(_cursorIndexOfValidUntil);
            _item = new SpendingForecast(_tmpId,_tmpCategory,_tmpForecastDate,_tmpPredictedAmount,_tmpConfidenceInterval,_tmpConfidenceLevel,_tmpForecastMethod,_tmpBasedOnDays,_tmpSeasonalityFactor,_tmpTrendFactor,_tmpActualAmount,_tmpAccuracy,_tmpCreatedAt,_tmpValidUntil);
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
  public Object getForecastsInRange(final long startDate, final long endDate,
      final Continuation<? super List<SpendingForecast>> $completion) {
    final String _sql = "SELECT * FROM spending_forecasts WHERE forecastDate BETWEEN ? AND ? ORDER BY forecastDate ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endDate);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<SpendingForecast>>() {
      @Override
      @NonNull
      public List<SpendingForecast> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfForecastDate = CursorUtil.getColumnIndexOrThrow(_cursor, "forecastDate");
          final int _cursorIndexOfPredictedAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "predictedAmount");
          final int _cursorIndexOfConfidenceInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "confidenceInterval");
          final int _cursorIndexOfConfidenceLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "confidenceLevel");
          final int _cursorIndexOfForecastMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "forecastMethod");
          final int _cursorIndexOfBasedOnDays = CursorUtil.getColumnIndexOrThrow(_cursor, "basedOnDays");
          final int _cursorIndexOfSeasonalityFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "seasonalityFactor");
          final int _cursorIndexOfTrendFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "trendFactor");
          final int _cursorIndexOfActualAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "actualAmount");
          final int _cursorIndexOfAccuracy = CursorUtil.getColumnIndexOrThrow(_cursor, "accuracy");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfValidUntil = CursorUtil.getColumnIndexOrThrow(_cursor, "validUntil");
          final List<SpendingForecast> _result = new ArrayList<SpendingForecast>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SpendingForecast _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final long _tmpForecastDate;
            _tmpForecastDate = _cursor.getLong(_cursorIndexOfForecastDate);
            final double _tmpPredictedAmount;
            _tmpPredictedAmount = _cursor.getDouble(_cursorIndexOfPredictedAmount);
            final double _tmpConfidenceInterval;
            _tmpConfidenceInterval = _cursor.getDouble(_cursorIndexOfConfidenceInterval);
            final float _tmpConfidenceLevel;
            _tmpConfidenceLevel = _cursor.getFloat(_cursorIndexOfConfidenceLevel);
            final ForecastMethod _tmpForecastMethod;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfForecastMethod)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfForecastMethod);
            }
            _tmpForecastMethod = __analyticsTypeConverters.toForecastMethod(_tmp);
            final int _tmpBasedOnDays;
            _tmpBasedOnDays = _cursor.getInt(_cursorIndexOfBasedOnDays);
            final double _tmpSeasonalityFactor;
            _tmpSeasonalityFactor = _cursor.getDouble(_cursorIndexOfSeasonalityFactor);
            final double _tmpTrendFactor;
            _tmpTrendFactor = _cursor.getDouble(_cursorIndexOfTrendFactor);
            final Double _tmpActualAmount;
            if (_cursor.isNull(_cursorIndexOfActualAmount)) {
              _tmpActualAmount = null;
            } else {
              _tmpActualAmount = _cursor.getDouble(_cursorIndexOfActualAmount);
            }
            final Double _tmpAccuracy;
            if (_cursor.isNull(_cursorIndexOfAccuracy)) {
              _tmpAccuracy = null;
            } else {
              _tmpAccuracy = _cursor.getDouble(_cursorIndexOfAccuracy);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpValidUntil;
            _tmpValidUntil = _cursor.getLong(_cursorIndexOfValidUntil);
            _item = new SpendingForecast(_tmpId,_tmpCategory,_tmpForecastDate,_tmpPredictedAmount,_tmpConfidenceInterval,_tmpConfidenceLevel,_tmpForecastMethod,_tmpBasedOnDays,_tmpSeasonalityFactor,_tmpTrendFactor,_tmpActualAmount,_tmpAccuracy,_tmpCreatedAt,_tmpValidUntil);
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
  public Object getForecastsNeedingActuals(final long currentTime,
      final Continuation<? super List<SpendingForecast>> $completion) {
    final String _sql = "SELECT * FROM spending_forecasts WHERE actualAmount IS NULL AND forecastDate < ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, currentTime);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<SpendingForecast>>() {
      @Override
      @NonNull
      public List<SpendingForecast> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfForecastDate = CursorUtil.getColumnIndexOrThrow(_cursor, "forecastDate");
          final int _cursorIndexOfPredictedAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "predictedAmount");
          final int _cursorIndexOfConfidenceInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "confidenceInterval");
          final int _cursorIndexOfConfidenceLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "confidenceLevel");
          final int _cursorIndexOfForecastMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "forecastMethod");
          final int _cursorIndexOfBasedOnDays = CursorUtil.getColumnIndexOrThrow(_cursor, "basedOnDays");
          final int _cursorIndexOfSeasonalityFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "seasonalityFactor");
          final int _cursorIndexOfTrendFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "trendFactor");
          final int _cursorIndexOfActualAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "actualAmount");
          final int _cursorIndexOfAccuracy = CursorUtil.getColumnIndexOrThrow(_cursor, "accuracy");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfValidUntil = CursorUtil.getColumnIndexOrThrow(_cursor, "validUntil");
          final List<SpendingForecast> _result = new ArrayList<SpendingForecast>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SpendingForecast _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final long _tmpForecastDate;
            _tmpForecastDate = _cursor.getLong(_cursorIndexOfForecastDate);
            final double _tmpPredictedAmount;
            _tmpPredictedAmount = _cursor.getDouble(_cursorIndexOfPredictedAmount);
            final double _tmpConfidenceInterval;
            _tmpConfidenceInterval = _cursor.getDouble(_cursorIndexOfConfidenceInterval);
            final float _tmpConfidenceLevel;
            _tmpConfidenceLevel = _cursor.getFloat(_cursorIndexOfConfidenceLevel);
            final ForecastMethod _tmpForecastMethod;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfForecastMethod)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfForecastMethod);
            }
            _tmpForecastMethod = __analyticsTypeConverters.toForecastMethod(_tmp);
            final int _tmpBasedOnDays;
            _tmpBasedOnDays = _cursor.getInt(_cursorIndexOfBasedOnDays);
            final double _tmpSeasonalityFactor;
            _tmpSeasonalityFactor = _cursor.getDouble(_cursorIndexOfSeasonalityFactor);
            final double _tmpTrendFactor;
            _tmpTrendFactor = _cursor.getDouble(_cursorIndexOfTrendFactor);
            final Double _tmpActualAmount;
            if (_cursor.isNull(_cursorIndexOfActualAmount)) {
              _tmpActualAmount = null;
            } else {
              _tmpActualAmount = _cursor.getDouble(_cursorIndexOfActualAmount);
            }
            final Double _tmpAccuracy;
            if (_cursor.isNull(_cursorIndexOfAccuracy)) {
              _tmpAccuracy = null;
            } else {
              _tmpAccuracy = _cursor.getDouble(_cursorIndexOfAccuracy);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpValidUntil;
            _tmpValidUntil = _cursor.getLong(_cursorIndexOfValidUntil);
            _item = new SpendingForecast(_tmpId,_tmpCategory,_tmpForecastDate,_tmpPredictedAmount,_tmpConfidenceInterval,_tmpConfidenceLevel,_tmpForecastMethod,_tmpBasedOnDays,_tmpSeasonalityFactor,_tmpTrendFactor,_tmpActualAmount,_tmpAccuracy,_tmpCreatedAt,_tmpValidUntil);
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
  public Object getCompletedForecasts(
      final Continuation<? super List<SpendingForecast>> $completion) {
    final String _sql = "SELECT * FROM spending_forecasts WHERE actualAmount IS NOT NULL";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<SpendingForecast>>() {
      @Override
      @NonNull
      public List<SpendingForecast> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfForecastDate = CursorUtil.getColumnIndexOrThrow(_cursor, "forecastDate");
          final int _cursorIndexOfPredictedAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "predictedAmount");
          final int _cursorIndexOfConfidenceInterval = CursorUtil.getColumnIndexOrThrow(_cursor, "confidenceInterval");
          final int _cursorIndexOfConfidenceLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "confidenceLevel");
          final int _cursorIndexOfForecastMethod = CursorUtil.getColumnIndexOrThrow(_cursor, "forecastMethod");
          final int _cursorIndexOfBasedOnDays = CursorUtil.getColumnIndexOrThrow(_cursor, "basedOnDays");
          final int _cursorIndexOfSeasonalityFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "seasonalityFactor");
          final int _cursorIndexOfTrendFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "trendFactor");
          final int _cursorIndexOfActualAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "actualAmount");
          final int _cursorIndexOfAccuracy = CursorUtil.getColumnIndexOrThrow(_cursor, "accuracy");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfValidUntil = CursorUtil.getColumnIndexOrThrow(_cursor, "validUntil");
          final List<SpendingForecast> _result = new ArrayList<SpendingForecast>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SpendingForecast _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final long _tmpForecastDate;
            _tmpForecastDate = _cursor.getLong(_cursorIndexOfForecastDate);
            final double _tmpPredictedAmount;
            _tmpPredictedAmount = _cursor.getDouble(_cursorIndexOfPredictedAmount);
            final double _tmpConfidenceInterval;
            _tmpConfidenceInterval = _cursor.getDouble(_cursorIndexOfConfidenceInterval);
            final float _tmpConfidenceLevel;
            _tmpConfidenceLevel = _cursor.getFloat(_cursorIndexOfConfidenceLevel);
            final ForecastMethod _tmpForecastMethod;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfForecastMethod)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfForecastMethod);
            }
            _tmpForecastMethod = __analyticsTypeConverters.toForecastMethod(_tmp);
            final int _tmpBasedOnDays;
            _tmpBasedOnDays = _cursor.getInt(_cursorIndexOfBasedOnDays);
            final double _tmpSeasonalityFactor;
            _tmpSeasonalityFactor = _cursor.getDouble(_cursorIndexOfSeasonalityFactor);
            final double _tmpTrendFactor;
            _tmpTrendFactor = _cursor.getDouble(_cursorIndexOfTrendFactor);
            final Double _tmpActualAmount;
            if (_cursor.isNull(_cursorIndexOfActualAmount)) {
              _tmpActualAmount = null;
            } else {
              _tmpActualAmount = _cursor.getDouble(_cursorIndexOfActualAmount);
            }
            final Double _tmpAccuracy;
            if (_cursor.isNull(_cursorIndexOfAccuracy)) {
              _tmpAccuracy = null;
            } else {
              _tmpAccuracy = _cursor.getDouble(_cursorIndexOfAccuracy);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpValidUntil;
            _tmpValidUntil = _cursor.getLong(_cursorIndexOfValidUntil);
            _item = new SpendingForecast(_tmpId,_tmpCategory,_tmpForecastDate,_tmpPredictedAmount,_tmpConfidenceInterval,_tmpConfidenceLevel,_tmpForecastMethod,_tmpBasedOnDays,_tmpSeasonalityFactor,_tmpTrendFactor,_tmpActualAmount,_tmpAccuracy,_tmpCreatedAt,_tmpValidUntil);
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
  public Object getAverageAccuracyByMethod(final ForecastMethod method,
      final Continuation<? super Double> $completion) {
    final String _sql = "SELECT AVG(accuracy) FROM spending_forecasts WHERE accuracy IS NOT NULL AND forecastMethod = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __analyticsTypeConverters.fromForecastMethod(method);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Double>() {
      @Override
      @Nullable
      public Double call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Double _result;
          if (_cursor.moveToFirst()) {
            final Double _tmp_1;
            if (_cursor.isNull(0)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getDouble(0);
            }
            _result = _tmp_1;
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
}
