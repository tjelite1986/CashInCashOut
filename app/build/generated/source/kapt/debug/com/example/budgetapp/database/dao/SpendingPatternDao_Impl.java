package com.example.budgetapp.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.budgetapp.database.entities.AnalyticsTypeConverters;
import com.example.budgetapp.database.entities.PatternTimeframe;
import com.example.budgetapp.database.entities.SeasonalityType;
import com.example.budgetapp.database.entities.SpendingFrequency;
import com.example.budgetapp.database.entities.SpendingPattern;
import com.example.budgetapp.database.entities.TrendDirection;
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
public final class SpendingPatternDao_Impl implements SpendingPatternDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<SpendingPattern> __insertionAdapterOfSpendingPattern;

  private final AnalyticsTypeConverters __analyticsTypeConverters = new AnalyticsTypeConverters();

  private final SharedSQLiteStatement __preparedStmtOfDeleteOldPatterns;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllPatterns;

  public SpendingPatternDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSpendingPattern = new EntityInsertionAdapter<SpendingPattern>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `spending_patterns` (`id`,`category`,`timeframe`,`averageAmount`,`totalAmount`,`transactionCount`,`trendDirection`,`trendPercentage`,`confidenceLevel`,`seasonality`,`anomalyScore`,`predictedNextAmount`,`volatility`,`frequency`,`analysisDate`,`dataPoints`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SpendingPattern entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getCategory() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getCategory());
        }
        final String _tmp = __analyticsTypeConverters.fromPatternTimeframe(entity.getTimeframe());
        if (_tmp == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, _tmp);
        }
        statement.bindDouble(4, entity.getAverageAmount());
        statement.bindDouble(5, entity.getTotalAmount());
        statement.bindLong(6, entity.getTransactionCount());
        final String _tmp_1 = __analyticsTypeConverters.fromTrendDirection(entity.getTrendDirection());
        if (_tmp_1 == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, _tmp_1);
        }
        statement.bindDouble(8, entity.getTrendPercentage());
        statement.bindDouble(9, entity.getConfidenceLevel());
        final String _tmp_2 = __analyticsTypeConverters.fromSeasonalityType(entity.getSeasonality());
        if (_tmp_2 == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, _tmp_2);
        }
        statement.bindDouble(11, entity.getAnomalyScore());
        if (entity.getPredictedNextAmount() == null) {
          statement.bindNull(12);
        } else {
          statement.bindDouble(12, entity.getPredictedNextAmount());
        }
        statement.bindDouble(13, entity.getVolatility());
        final String _tmp_3 = __analyticsTypeConverters.fromSpendingFrequency(entity.getFrequency());
        if (_tmp_3 == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, _tmp_3);
        }
        statement.bindLong(15, entity.getAnalysisDate());
        statement.bindLong(16, entity.getDataPoints());
        statement.bindLong(17, entity.getCreatedAt());
      }
    };
    this.__preparedStmtOfDeleteOldPatterns = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM spending_patterns WHERE analysisDate < ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllPatterns = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM spending_patterns";
        return _query;
      }
    };
  }

  @Override
  public Object insertPattern(final SpendingPattern pattern,
      final Continuation<? super Long> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfSpendingPattern.insertAndReturnId(pattern);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object insertPatterns(final List<SpendingPattern> patterns,
      final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfSpendingPattern.insert(patterns);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object deleteOldPatterns(final long cutoffTime, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteOldPatterns.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, cutoffTime);
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
          __preparedStmtOfDeleteOldPatterns.release(_stmt);
        }
      }
    }, arg1);
  }

  @Override
  public Object deleteAllPatterns(final Continuation<? super Unit> arg0) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllPatterns.acquire();
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
          __preparedStmtOfDeleteAllPatterns.release(_stmt);
        }
      }
    }, arg0);
  }

  @Override
  public Flow<List<SpendingPattern>> getAllPatterns() {
    final String _sql = "SELECT * FROM spending_patterns ORDER BY analysisDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"spending_patterns"}, new Callable<List<SpendingPattern>>() {
      @Override
      @NonNull
      public List<SpendingPattern> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfTimeframe = CursorUtil.getColumnIndexOrThrow(_cursor, "timeframe");
          final int _cursorIndexOfAverageAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "averageAmount");
          final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
          final int _cursorIndexOfTransactionCount = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionCount");
          final int _cursorIndexOfTrendDirection = CursorUtil.getColumnIndexOrThrow(_cursor, "trendDirection");
          final int _cursorIndexOfTrendPercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "trendPercentage");
          final int _cursorIndexOfConfidenceLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "confidenceLevel");
          final int _cursorIndexOfSeasonality = CursorUtil.getColumnIndexOrThrow(_cursor, "seasonality");
          final int _cursorIndexOfAnomalyScore = CursorUtil.getColumnIndexOrThrow(_cursor, "anomalyScore");
          final int _cursorIndexOfPredictedNextAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "predictedNextAmount");
          final int _cursorIndexOfVolatility = CursorUtil.getColumnIndexOrThrow(_cursor, "volatility");
          final int _cursorIndexOfFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "frequency");
          final int _cursorIndexOfAnalysisDate = CursorUtil.getColumnIndexOrThrow(_cursor, "analysisDate");
          final int _cursorIndexOfDataPoints = CursorUtil.getColumnIndexOrThrow(_cursor, "dataPoints");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<SpendingPattern> _result = new ArrayList<SpendingPattern>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SpendingPattern _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final PatternTimeframe _tmpTimeframe;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfTimeframe)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfTimeframe);
            }
            _tmpTimeframe = __analyticsTypeConverters.toPatternTimeframe(_tmp);
            final double _tmpAverageAmount;
            _tmpAverageAmount = _cursor.getDouble(_cursorIndexOfAverageAmount);
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            final int _tmpTransactionCount;
            _tmpTransactionCount = _cursor.getInt(_cursorIndexOfTransactionCount);
            final TrendDirection _tmpTrendDirection;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfTrendDirection)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfTrendDirection);
            }
            _tmpTrendDirection = __analyticsTypeConverters.toTrendDirection(_tmp_1);
            final double _tmpTrendPercentage;
            _tmpTrendPercentage = _cursor.getDouble(_cursorIndexOfTrendPercentage);
            final float _tmpConfidenceLevel;
            _tmpConfidenceLevel = _cursor.getFloat(_cursorIndexOfConfidenceLevel);
            final SeasonalityType _tmpSeasonality;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfSeasonality)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfSeasonality);
            }
            _tmpSeasonality = __analyticsTypeConverters.toSeasonalityType(_tmp_2);
            final float _tmpAnomalyScore;
            _tmpAnomalyScore = _cursor.getFloat(_cursorIndexOfAnomalyScore);
            final Double _tmpPredictedNextAmount;
            if (_cursor.isNull(_cursorIndexOfPredictedNextAmount)) {
              _tmpPredictedNextAmount = null;
            } else {
              _tmpPredictedNextAmount = _cursor.getDouble(_cursorIndexOfPredictedNextAmount);
            }
            final double _tmpVolatility;
            _tmpVolatility = _cursor.getDouble(_cursorIndexOfVolatility);
            final SpendingFrequency _tmpFrequency;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfFrequency)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfFrequency);
            }
            _tmpFrequency = __analyticsTypeConverters.toSpendingFrequency(_tmp_3);
            final long _tmpAnalysisDate;
            _tmpAnalysisDate = _cursor.getLong(_cursorIndexOfAnalysisDate);
            final int _tmpDataPoints;
            _tmpDataPoints = _cursor.getInt(_cursorIndexOfDataPoints);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new SpendingPattern(_tmpId,_tmpCategory,_tmpTimeframe,_tmpAverageAmount,_tmpTotalAmount,_tmpTransactionCount,_tmpTrendDirection,_tmpTrendPercentage,_tmpConfidenceLevel,_tmpSeasonality,_tmpAnomalyScore,_tmpPredictedNextAmount,_tmpVolatility,_tmpFrequency,_tmpAnalysisDate,_tmpDataPoints,_tmpCreatedAt);
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
  public Flow<List<SpendingPattern>> getPatternsByCategory(final String category) {
    final String _sql = "SELECT * FROM spending_patterns WHERE category = ? ORDER BY analysisDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (category == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, category);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"spending_patterns"}, new Callable<List<SpendingPattern>>() {
      @Override
      @NonNull
      public List<SpendingPattern> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfTimeframe = CursorUtil.getColumnIndexOrThrow(_cursor, "timeframe");
          final int _cursorIndexOfAverageAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "averageAmount");
          final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
          final int _cursorIndexOfTransactionCount = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionCount");
          final int _cursorIndexOfTrendDirection = CursorUtil.getColumnIndexOrThrow(_cursor, "trendDirection");
          final int _cursorIndexOfTrendPercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "trendPercentage");
          final int _cursorIndexOfConfidenceLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "confidenceLevel");
          final int _cursorIndexOfSeasonality = CursorUtil.getColumnIndexOrThrow(_cursor, "seasonality");
          final int _cursorIndexOfAnomalyScore = CursorUtil.getColumnIndexOrThrow(_cursor, "anomalyScore");
          final int _cursorIndexOfPredictedNextAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "predictedNextAmount");
          final int _cursorIndexOfVolatility = CursorUtil.getColumnIndexOrThrow(_cursor, "volatility");
          final int _cursorIndexOfFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "frequency");
          final int _cursorIndexOfAnalysisDate = CursorUtil.getColumnIndexOrThrow(_cursor, "analysisDate");
          final int _cursorIndexOfDataPoints = CursorUtil.getColumnIndexOrThrow(_cursor, "dataPoints");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<SpendingPattern> _result = new ArrayList<SpendingPattern>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SpendingPattern _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final PatternTimeframe _tmpTimeframe;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfTimeframe)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfTimeframe);
            }
            _tmpTimeframe = __analyticsTypeConverters.toPatternTimeframe(_tmp);
            final double _tmpAverageAmount;
            _tmpAverageAmount = _cursor.getDouble(_cursorIndexOfAverageAmount);
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            final int _tmpTransactionCount;
            _tmpTransactionCount = _cursor.getInt(_cursorIndexOfTransactionCount);
            final TrendDirection _tmpTrendDirection;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfTrendDirection)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfTrendDirection);
            }
            _tmpTrendDirection = __analyticsTypeConverters.toTrendDirection(_tmp_1);
            final double _tmpTrendPercentage;
            _tmpTrendPercentage = _cursor.getDouble(_cursorIndexOfTrendPercentage);
            final float _tmpConfidenceLevel;
            _tmpConfidenceLevel = _cursor.getFloat(_cursorIndexOfConfidenceLevel);
            final SeasonalityType _tmpSeasonality;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfSeasonality)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfSeasonality);
            }
            _tmpSeasonality = __analyticsTypeConverters.toSeasonalityType(_tmp_2);
            final float _tmpAnomalyScore;
            _tmpAnomalyScore = _cursor.getFloat(_cursorIndexOfAnomalyScore);
            final Double _tmpPredictedNextAmount;
            if (_cursor.isNull(_cursorIndexOfPredictedNextAmount)) {
              _tmpPredictedNextAmount = null;
            } else {
              _tmpPredictedNextAmount = _cursor.getDouble(_cursorIndexOfPredictedNextAmount);
            }
            final double _tmpVolatility;
            _tmpVolatility = _cursor.getDouble(_cursorIndexOfVolatility);
            final SpendingFrequency _tmpFrequency;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfFrequency)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfFrequency);
            }
            _tmpFrequency = __analyticsTypeConverters.toSpendingFrequency(_tmp_3);
            final long _tmpAnalysisDate;
            _tmpAnalysisDate = _cursor.getLong(_cursorIndexOfAnalysisDate);
            final int _tmpDataPoints;
            _tmpDataPoints = _cursor.getInt(_cursorIndexOfDataPoints);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new SpendingPattern(_tmpId,_tmpCategory,_tmpTimeframe,_tmpAverageAmount,_tmpTotalAmount,_tmpTransactionCount,_tmpTrendDirection,_tmpTrendPercentage,_tmpConfidenceLevel,_tmpSeasonality,_tmpAnomalyScore,_tmpPredictedNextAmount,_tmpVolatility,_tmpFrequency,_tmpAnalysisDate,_tmpDataPoints,_tmpCreatedAt);
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
  public Flow<List<SpendingPattern>> getPatternsByTimeframe(final PatternTimeframe timeframe) {
    final String _sql = "SELECT * FROM spending_patterns WHERE timeframe = ? ORDER BY analysisDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __analyticsTypeConverters.fromPatternTimeframe(timeframe);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"spending_patterns"}, new Callable<List<SpendingPattern>>() {
      @Override
      @NonNull
      public List<SpendingPattern> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfTimeframe = CursorUtil.getColumnIndexOrThrow(_cursor, "timeframe");
          final int _cursorIndexOfAverageAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "averageAmount");
          final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
          final int _cursorIndexOfTransactionCount = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionCount");
          final int _cursorIndexOfTrendDirection = CursorUtil.getColumnIndexOrThrow(_cursor, "trendDirection");
          final int _cursorIndexOfTrendPercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "trendPercentage");
          final int _cursorIndexOfConfidenceLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "confidenceLevel");
          final int _cursorIndexOfSeasonality = CursorUtil.getColumnIndexOrThrow(_cursor, "seasonality");
          final int _cursorIndexOfAnomalyScore = CursorUtil.getColumnIndexOrThrow(_cursor, "anomalyScore");
          final int _cursorIndexOfPredictedNextAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "predictedNextAmount");
          final int _cursorIndexOfVolatility = CursorUtil.getColumnIndexOrThrow(_cursor, "volatility");
          final int _cursorIndexOfFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "frequency");
          final int _cursorIndexOfAnalysisDate = CursorUtil.getColumnIndexOrThrow(_cursor, "analysisDate");
          final int _cursorIndexOfDataPoints = CursorUtil.getColumnIndexOrThrow(_cursor, "dataPoints");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<SpendingPattern> _result = new ArrayList<SpendingPattern>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SpendingPattern _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final PatternTimeframe _tmpTimeframe;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfTimeframe)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfTimeframe);
            }
            _tmpTimeframe = __analyticsTypeConverters.toPatternTimeframe(_tmp_1);
            final double _tmpAverageAmount;
            _tmpAverageAmount = _cursor.getDouble(_cursorIndexOfAverageAmount);
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            final int _tmpTransactionCount;
            _tmpTransactionCount = _cursor.getInt(_cursorIndexOfTransactionCount);
            final TrendDirection _tmpTrendDirection;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfTrendDirection)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfTrendDirection);
            }
            _tmpTrendDirection = __analyticsTypeConverters.toTrendDirection(_tmp_2);
            final double _tmpTrendPercentage;
            _tmpTrendPercentage = _cursor.getDouble(_cursorIndexOfTrendPercentage);
            final float _tmpConfidenceLevel;
            _tmpConfidenceLevel = _cursor.getFloat(_cursorIndexOfConfidenceLevel);
            final SeasonalityType _tmpSeasonality;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfSeasonality)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfSeasonality);
            }
            _tmpSeasonality = __analyticsTypeConverters.toSeasonalityType(_tmp_3);
            final float _tmpAnomalyScore;
            _tmpAnomalyScore = _cursor.getFloat(_cursorIndexOfAnomalyScore);
            final Double _tmpPredictedNextAmount;
            if (_cursor.isNull(_cursorIndexOfPredictedNextAmount)) {
              _tmpPredictedNextAmount = null;
            } else {
              _tmpPredictedNextAmount = _cursor.getDouble(_cursorIndexOfPredictedNextAmount);
            }
            final double _tmpVolatility;
            _tmpVolatility = _cursor.getDouble(_cursorIndexOfVolatility);
            final SpendingFrequency _tmpFrequency;
            final String _tmp_4;
            if (_cursor.isNull(_cursorIndexOfFrequency)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getString(_cursorIndexOfFrequency);
            }
            _tmpFrequency = __analyticsTypeConverters.toSpendingFrequency(_tmp_4);
            final long _tmpAnalysisDate;
            _tmpAnalysisDate = _cursor.getLong(_cursorIndexOfAnalysisDate);
            final int _tmpDataPoints;
            _tmpDataPoints = _cursor.getInt(_cursorIndexOfDataPoints);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new SpendingPattern(_tmpId,_tmpCategory,_tmpTimeframe,_tmpAverageAmount,_tmpTotalAmount,_tmpTransactionCount,_tmpTrendDirection,_tmpTrendPercentage,_tmpConfidenceLevel,_tmpSeasonality,_tmpAnomalyScore,_tmpPredictedNextAmount,_tmpVolatility,_tmpFrequency,_tmpAnalysisDate,_tmpDataPoints,_tmpCreatedAt);
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
  public Object getAnomalousPatterns(final float threshold,
      final Continuation<? super List<SpendingPattern>> arg1) {
    final String _sql = "SELECT * FROM spending_patterns WHERE anomalyScore >= ? ORDER BY anomalyScore DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindDouble(_argIndex, threshold);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<SpendingPattern>>() {
      @Override
      @NonNull
      public List<SpendingPattern> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfTimeframe = CursorUtil.getColumnIndexOrThrow(_cursor, "timeframe");
          final int _cursorIndexOfAverageAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "averageAmount");
          final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
          final int _cursorIndexOfTransactionCount = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionCount");
          final int _cursorIndexOfTrendDirection = CursorUtil.getColumnIndexOrThrow(_cursor, "trendDirection");
          final int _cursorIndexOfTrendPercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "trendPercentage");
          final int _cursorIndexOfConfidenceLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "confidenceLevel");
          final int _cursorIndexOfSeasonality = CursorUtil.getColumnIndexOrThrow(_cursor, "seasonality");
          final int _cursorIndexOfAnomalyScore = CursorUtil.getColumnIndexOrThrow(_cursor, "anomalyScore");
          final int _cursorIndexOfPredictedNextAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "predictedNextAmount");
          final int _cursorIndexOfVolatility = CursorUtil.getColumnIndexOrThrow(_cursor, "volatility");
          final int _cursorIndexOfFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "frequency");
          final int _cursorIndexOfAnalysisDate = CursorUtil.getColumnIndexOrThrow(_cursor, "analysisDate");
          final int _cursorIndexOfDataPoints = CursorUtil.getColumnIndexOrThrow(_cursor, "dataPoints");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<SpendingPattern> _result = new ArrayList<SpendingPattern>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SpendingPattern _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final PatternTimeframe _tmpTimeframe;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfTimeframe)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfTimeframe);
            }
            _tmpTimeframe = __analyticsTypeConverters.toPatternTimeframe(_tmp);
            final double _tmpAverageAmount;
            _tmpAverageAmount = _cursor.getDouble(_cursorIndexOfAverageAmount);
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            final int _tmpTransactionCount;
            _tmpTransactionCount = _cursor.getInt(_cursorIndexOfTransactionCount);
            final TrendDirection _tmpTrendDirection;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfTrendDirection)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfTrendDirection);
            }
            _tmpTrendDirection = __analyticsTypeConverters.toTrendDirection(_tmp_1);
            final double _tmpTrendPercentage;
            _tmpTrendPercentage = _cursor.getDouble(_cursorIndexOfTrendPercentage);
            final float _tmpConfidenceLevel;
            _tmpConfidenceLevel = _cursor.getFloat(_cursorIndexOfConfidenceLevel);
            final SeasonalityType _tmpSeasonality;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfSeasonality)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfSeasonality);
            }
            _tmpSeasonality = __analyticsTypeConverters.toSeasonalityType(_tmp_2);
            final float _tmpAnomalyScore;
            _tmpAnomalyScore = _cursor.getFloat(_cursorIndexOfAnomalyScore);
            final Double _tmpPredictedNextAmount;
            if (_cursor.isNull(_cursorIndexOfPredictedNextAmount)) {
              _tmpPredictedNextAmount = null;
            } else {
              _tmpPredictedNextAmount = _cursor.getDouble(_cursorIndexOfPredictedNextAmount);
            }
            final double _tmpVolatility;
            _tmpVolatility = _cursor.getDouble(_cursorIndexOfVolatility);
            final SpendingFrequency _tmpFrequency;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfFrequency)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfFrequency);
            }
            _tmpFrequency = __analyticsTypeConverters.toSpendingFrequency(_tmp_3);
            final long _tmpAnalysisDate;
            _tmpAnalysisDate = _cursor.getLong(_cursorIndexOfAnalysisDate);
            final int _tmpDataPoints;
            _tmpDataPoints = _cursor.getInt(_cursorIndexOfDataPoints);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new SpendingPattern(_tmpId,_tmpCategory,_tmpTimeframe,_tmpAverageAmount,_tmpTotalAmount,_tmpTransactionCount,_tmpTrendDirection,_tmpTrendPercentage,_tmpConfidenceLevel,_tmpSeasonality,_tmpAnomalyScore,_tmpPredictedNextAmount,_tmpVolatility,_tmpFrequency,_tmpAnalysisDate,_tmpDataPoints,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg1);
  }

  @Override
  public Object getPatternsByTrend(final TrendDirection direction,
      final Continuation<? super List<SpendingPattern>> arg1) {
    final String _sql = "SELECT * FROM spending_patterns WHERE trendDirection = ? ORDER BY analysisDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __analyticsTypeConverters.fromTrendDirection(direction);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<SpendingPattern>>() {
      @Override
      @NonNull
      public List<SpendingPattern> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfTimeframe = CursorUtil.getColumnIndexOrThrow(_cursor, "timeframe");
          final int _cursorIndexOfAverageAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "averageAmount");
          final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
          final int _cursorIndexOfTransactionCount = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionCount");
          final int _cursorIndexOfTrendDirection = CursorUtil.getColumnIndexOrThrow(_cursor, "trendDirection");
          final int _cursorIndexOfTrendPercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "trendPercentage");
          final int _cursorIndexOfConfidenceLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "confidenceLevel");
          final int _cursorIndexOfSeasonality = CursorUtil.getColumnIndexOrThrow(_cursor, "seasonality");
          final int _cursorIndexOfAnomalyScore = CursorUtil.getColumnIndexOrThrow(_cursor, "anomalyScore");
          final int _cursorIndexOfPredictedNextAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "predictedNextAmount");
          final int _cursorIndexOfVolatility = CursorUtil.getColumnIndexOrThrow(_cursor, "volatility");
          final int _cursorIndexOfFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "frequency");
          final int _cursorIndexOfAnalysisDate = CursorUtil.getColumnIndexOrThrow(_cursor, "analysisDate");
          final int _cursorIndexOfDataPoints = CursorUtil.getColumnIndexOrThrow(_cursor, "dataPoints");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<SpendingPattern> _result = new ArrayList<SpendingPattern>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SpendingPattern _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final PatternTimeframe _tmpTimeframe;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfTimeframe)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfTimeframe);
            }
            _tmpTimeframe = __analyticsTypeConverters.toPatternTimeframe(_tmp_1);
            final double _tmpAverageAmount;
            _tmpAverageAmount = _cursor.getDouble(_cursorIndexOfAverageAmount);
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            final int _tmpTransactionCount;
            _tmpTransactionCount = _cursor.getInt(_cursorIndexOfTransactionCount);
            final TrendDirection _tmpTrendDirection;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfTrendDirection)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfTrendDirection);
            }
            _tmpTrendDirection = __analyticsTypeConverters.toTrendDirection(_tmp_2);
            final double _tmpTrendPercentage;
            _tmpTrendPercentage = _cursor.getDouble(_cursorIndexOfTrendPercentage);
            final float _tmpConfidenceLevel;
            _tmpConfidenceLevel = _cursor.getFloat(_cursorIndexOfConfidenceLevel);
            final SeasonalityType _tmpSeasonality;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfSeasonality)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfSeasonality);
            }
            _tmpSeasonality = __analyticsTypeConverters.toSeasonalityType(_tmp_3);
            final float _tmpAnomalyScore;
            _tmpAnomalyScore = _cursor.getFloat(_cursorIndexOfAnomalyScore);
            final Double _tmpPredictedNextAmount;
            if (_cursor.isNull(_cursorIndexOfPredictedNextAmount)) {
              _tmpPredictedNextAmount = null;
            } else {
              _tmpPredictedNextAmount = _cursor.getDouble(_cursorIndexOfPredictedNextAmount);
            }
            final double _tmpVolatility;
            _tmpVolatility = _cursor.getDouble(_cursorIndexOfVolatility);
            final SpendingFrequency _tmpFrequency;
            final String _tmp_4;
            if (_cursor.isNull(_cursorIndexOfFrequency)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getString(_cursorIndexOfFrequency);
            }
            _tmpFrequency = __analyticsTypeConverters.toSpendingFrequency(_tmp_4);
            final long _tmpAnalysisDate;
            _tmpAnalysisDate = _cursor.getLong(_cursorIndexOfAnalysisDate);
            final int _tmpDataPoints;
            _tmpDataPoints = _cursor.getInt(_cursorIndexOfDataPoints);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new SpendingPattern(_tmpId,_tmpCategory,_tmpTimeframe,_tmpAverageAmount,_tmpTotalAmount,_tmpTransactionCount,_tmpTrendDirection,_tmpTrendPercentage,_tmpConfidenceLevel,_tmpSeasonality,_tmpAnomalyScore,_tmpPredictedNextAmount,_tmpVolatility,_tmpFrequency,_tmpAnalysisDate,_tmpDataPoints,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg1);
  }

  @Override
  public Object getSeasonalPatterns(final Continuation<? super List<SpendingPattern>> arg0) {
    final String _sql = "SELECT * FROM spending_patterns WHERE seasonality != 'NONE' ORDER BY analysisDate DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<SpendingPattern>>() {
      @Override
      @NonNull
      public List<SpendingPattern> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfTimeframe = CursorUtil.getColumnIndexOrThrow(_cursor, "timeframe");
          final int _cursorIndexOfAverageAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "averageAmount");
          final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
          final int _cursorIndexOfTransactionCount = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionCount");
          final int _cursorIndexOfTrendDirection = CursorUtil.getColumnIndexOrThrow(_cursor, "trendDirection");
          final int _cursorIndexOfTrendPercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "trendPercentage");
          final int _cursorIndexOfConfidenceLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "confidenceLevel");
          final int _cursorIndexOfSeasonality = CursorUtil.getColumnIndexOrThrow(_cursor, "seasonality");
          final int _cursorIndexOfAnomalyScore = CursorUtil.getColumnIndexOrThrow(_cursor, "anomalyScore");
          final int _cursorIndexOfPredictedNextAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "predictedNextAmount");
          final int _cursorIndexOfVolatility = CursorUtil.getColumnIndexOrThrow(_cursor, "volatility");
          final int _cursorIndexOfFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "frequency");
          final int _cursorIndexOfAnalysisDate = CursorUtil.getColumnIndexOrThrow(_cursor, "analysisDate");
          final int _cursorIndexOfDataPoints = CursorUtil.getColumnIndexOrThrow(_cursor, "dataPoints");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<SpendingPattern> _result = new ArrayList<SpendingPattern>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SpendingPattern _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final PatternTimeframe _tmpTimeframe;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfTimeframe)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfTimeframe);
            }
            _tmpTimeframe = __analyticsTypeConverters.toPatternTimeframe(_tmp);
            final double _tmpAverageAmount;
            _tmpAverageAmount = _cursor.getDouble(_cursorIndexOfAverageAmount);
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            final int _tmpTransactionCount;
            _tmpTransactionCount = _cursor.getInt(_cursorIndexOfTransactionCount);
            final TrendDirection _tmpTrendDirection;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfTrendDirection)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfTrendDirection);
            }
            _tmpTrendDirection = __analyticsTypeConverters.toTrendDirection(_tmp_1);
            final double _tmpTrendPercentage;
            _tmpTrendPercentage = _cursor.getDouble(_cursorIndexOfTrendPercentage);
            final float _tmpConfidenceLevel;
            _tmpConfidenceLevel = _cursor.getFloat(_cursorIndexOfConfidenceLevel);
            final SeasonalityType _tmpSeasonality;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfSeasonality)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfSeasonality);
            }
            _tmpSeasonality = __analyticsTypeConverters.toSeasonalityType(_tmp_2);
            final float _tmpAnomalyScore;
            _tmpAnomalyScore = _cursor.getFloat(_cursorIndexOfAnomalyScore);
            final Double _tmpPredictedNextAmount;
            if (_cursor.isNull(_cursorIndexOfPredictedNextAmount)) {
              _tmpPredictedNextAmount = null;
            } else {
              _tmpPredictedNextAmount = _cursor.getDouble(_cursorIndexOfPredictedNextAmount);
            }
            final double _tmpVolatility;
            _tmpVolatility = _cursor.getDouble(_cursorIndexOfVolatility);
            final SpendingFrequency _tmpFrequency;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfFrequency)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfFrequency);
            }
            _tmpFrequency = __analyticsTypeConverters.toSpendingFrequency(_tmp_3);
            final long _tmpAnalysisDate;
            _tmpAnalysisDate = _cursor.getLong(_cursorIndexOfAnalysisDate);
            final int _tmpDataPoints;
            _tmpDataPoints = _cursor.getInt(_cursorIndexOfDataPoints);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new SpendingPattern(_tmpId,_tmpCategory,_tmpTimeframe,_tmpAverageAmount,_tmpTotalAmount,_tmpTransactionCount,_tmpTrendDirection,_tmpTrendPercentage,_tmpConfidenceLevel,_tmpSeasonality,_tmpAnomalyScore,_tmpPredictedNextAmount,_tmpVolatility,_tmpFrequency,_tmpAnalysisDate,_tmpDataPoints,_tmpCreatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg0);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
