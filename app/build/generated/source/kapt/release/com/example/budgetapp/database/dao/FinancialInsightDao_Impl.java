package com.example.budgetapp.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.budgetapp.database.entities.AnalyticsTypeConverters;
import com.example.budgetapp.database.entities.FinancialInsight;
import com.example.budgetapp.database.entities.InsightSeverity;
import com.example.budgetapp.database.entities.InsightType;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class FinancialInsightDao_Impl implements FinancialInsightDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<FinancialInsight> __insertionAdapterOfFinancialInsight;

  private final AnalyticsTypeConverters __analyticsTypeConverters = new AnalyticsTypeConverters();

  private final EntityDeletionOrUpdateAdapter<FinancialInsight> __deletionAdapterOfFinancialInsight;

  private final EntityDeletionOrUpdateAdapter<FinancialInsight> __updateAdapterOfFinancialInsight;

  private final SharedSQLiteStatement __preparedStmtOfMarkAsRead;

  private final SharedSQLiteStatement __preparedStmtOfMarkAsActedUpon;

  private final SharedSQLiteStatement __preparedStmtOfDeleteInsightById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteExpiredInsights;

  private final SharedSQLiteStatement __preparedStmtOfDeleteOldInsights;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllInsights;

  public FinancialInsightDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFinancialInsight = new EntityInsertionAdapter<FinancialInsight>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `financial_insights` (`id`,`insightType`,`title`,`description`,`severity`,`category`,`amount`,`percentage`,`confidenceScore`,`actionable`,`suggestedAction`,`isRead`,`isActedUpon`,`validUntil`,`data`,`createdAt`,`updatedAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FinancialInsight entity) {
        statement.bindLong(1, entity.getId());
        final String _tmp = __analyticsTypeConverters.fromInsightType(entity.getInsightType());
        if (_tmp == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, _tmp);
        }
        if (entity.getTitle() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getTitle());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDescription());
        }
        final String _tmp_1 = __analyticsTypeConverters.fromInsightSeverity(entity.getSeverity());
        if (_tmp_1 == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, _tmp_1);
        }
        if (entity.getCategory() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getCategory());
        }
        if (entity.getAmount() == null) {
          statement.bindNull(7);
        } else {
          statement.bindDouble(7, entity.getAmount());
        }
        if (entity.getPercentage() == null) {
          statement.bindNull(8);
        } else {
          statement.bindDouble(8, entity.getPercentage());
        }
        statement.bindDouble(9, entity.getConfidenceScore());
        final int _tmp_2 = entity.getActionable() ? 1 : 0;
        statement.bindLong(10, _tmp_2);
        if (entity.getSuggestedAction() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getSuggestedAction());
        }
        final int _tmp_3 = entity.isRead() ? 1 : 0;
        statement.bindLong(12, _tmp_3);
        final int _tmp_4 = entity.isActedUpon() ? 1 : 0;
        statement.bindLong(13, _tmp_4);
        if (entity.getValidUntil() == null) {
          statement.bindNull(14);
        } else {
          statement.bindLong(14, entity.getValidUntil());
        }
        if (entity.getData() == null) {
          statement.bindNull(15);
        } else {
          statement.bindString(15, entity.getData());
        }
        statement.bindLong(16, entity.getCreatedAt());
        statement.bindLong(17, entity.getUpdatedAt());
      }
    };
    this.__deletionAdapterOfFinancialInsight = new EntityDeletionOrUpdateAdapter<FinancialInsight>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `financial_insights` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FinancialInsight entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfFinancialInsight = new EntityDeletionOrUpdateAdapter<FinancialInsight>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `financial_insights` SET `id` = ?,`insightType` = ?,`title` = ?,`description` = ?,`severity` = ?,`category` = ?,`amount` = ?,`percentage` = ?,`confidenceScore` = ?,`actionable` = ?,`suggestedAction` = ?,`isRead` = ?,`isActedUpon` = ?,`validUntil` = ?,`data` = ?,`createdAt` = ?,`updatedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FinancialInsight entity) {
        statement.bindLong(1, entity.getId());
        final String _tmp = __analyticsTypeConverters.fromInsightType(entity.getInsightType());
        if (_tmp == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, _tmp);
        }
        if (entity.getTitle() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getTitle());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getDescription());
        }
        final String _tmp_1 = __analyticsTypeConverters.fromInsightSeverity(entity.getSeverity());
        if (_tmp_1 == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, _tmp_1);
        }
        if (entity.getCategory() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getCategory());
        }
        if (entity.getAmount() == null) {
          statement.bindNull(7);
        } else {
          statement.bindDouble(7, entity.getAmount());
        }
        if (entity.getPercentage() == null) {
          statement.bindNull(8);
        } else {
          statement.bindDouble(8, entity.getPercentage());
        }
        statement.bindDouble(9, entity.getConfidenceScore());
        final int _tmp_2 = entity.getActionable() ? 1 : 0;
        statement.bindLong(10, _tmp_2);
        if (entity.getSuggestedAction() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getSuggestedAction());
        }
        final int _tmp_3 = entity.isRead() ? 1 : 0;
        statement.bindLong(12, _tmp_3);
        final int _tmp_4 = entity.isActedUpon() ? 1 : 0;
        statement.bindLong(13, _tmp_4);
        if (entity.getValidUntil() == null) {
          statement.bindNull(14);
        } else {
          statement.bindLong(14, entity.getValidUntil());
        }
        if (entity.getData() == null) {
          statement.bindNull(15);
        } else {
          statement.bindString(15, entity.getData());
        }
        statement.bindLong(16, entity.getCreatedAt());
        statement.bindLong(17, entity.getUpdatedAt());
        statement.bindLong(18, entity.getId());
      }
    };
    this.__preparedStmtOfMarkAsRead = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE financial_insights SET isRead = 1, updatedAt = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfMarkAsActedUpon = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE financial_insights SET isActedUpon = 1, updatedAt = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteInsightById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM financial_insights WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteExpiredInsights = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM financial_insights WHERE validUntil IS NOT NULL AND validUntil < ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteOldInsights = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM financial_insights WHERE createdAt < ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllInsights = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM financial_insights";
        return _query;
      }
    };
  }

  @Override
  public Object insertInsight(final FinancialInsight insight,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfFinancialInsight.insertAndReturnId(insight);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertInsights(final List<FinancialInsight> insights,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfFinancialInsight.insert(insights);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteInsight(final FinancialInsight insight,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfFinancialInsight.handle(insight);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateInsight(final FinancialInsight insight,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfFinancialInsight.handle(insight);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object markAsRead(final long id, final long timestamp,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfMarkAsRead.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, timestamp);
        _argIndex = 2;
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
          __preparedStmtOfMarkAsRead.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object markAsActedUpon(final long id, final long timestamp,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfMarkAsActedUpon.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, timestamp);
        _argIndex = 2;
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
          __preparedStmtOfMarkAsActedUpon.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteInsightById(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteInsightById.acquire();
        int _argIndex = 1;
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
          __preparedStmtOfDeleteInsightById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteExpiredInsights(final long currentTime,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteExpiredInsights.acquire();
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
          __preparedStmtOfDeleteExpiredInsights.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteOldInsights(final long cutoffTime,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteOldInsights.acquire();
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
          __preparedStmtOfDeleteOldInsights.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllInsights(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllInsights.acquire();
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
          __preparedStmtOfDeleteAllInsights.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<FinancialInsight>> getAllInsights() {
    final String _sql = "SELECT * FROM financial_insights ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"financial_insights"}, new Callable<List<FinancialInsight>>() {
      @Override
      @NonNull
      public List<FinancialInsight> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfInsightType = CursorUtil.getColumnIndexOrThrow(_cursor, "insightType");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfSeverity = CursorUtil.getColumnIndexOrThrow(_cursor, "severity");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfPercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "percentage");
          final int _cursorIndexOfConfidenceScore = CursorUtil.getColumnIndexOrThrow(_cursor, "confidenceScore");
          final int _cursorIndexOfActionable = CursorUtil.getColumnIndexOrThrow(_cursor, "actionable");
          final int _cursorIndexOfSuggestedAction = CursorUtil.getColumnIndexOrThrow(_cursor, "suggestedAction");
          final int _cursorIndexOfIsRead = CursorUtil.getColumnIndexOrThrow(_cursor, "isRead");
          final int _cursorIndexOfIsActedUpon = CursorUtil.getColumnIndexOrThrow(_cursor, "isActedUpon");
          final int _cursorIndexOfValidUntil = CursorUtil.getColumnIndexOrThrow(_cursor, "validUntil");
          final int _cursorIndexOfData = CursorUtil.getColumnIndexOrThrow(_cursor, "data");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<FinancialInsight> _result = new ArrayList<FinancialInsight>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FinancialInsight _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final InsightType _tmpInsightType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfInsightType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfInsightType);
            }
            _tmpInsightType = __analyticsTypeConverters.toInsightType(_tmp);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final InsightSeverity _tmpSeverity;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfSeverity)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfSeverity);
            }
            _tmpSeverity = __analyticsTypeConverters.toInsightSeverity(_tmp_1);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final Double _tmpAmount;
            if (_cursor.isNull(_cursorIndexOfAmount)) {
              _tmpAmount = null;
            } else {
              _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            }
            final Double _tmpPercentage;
            if (_cursor.isNull(_cursorIndexOfPercentage)) {
              _tmpPercentage = null;
            } else {
              _tmpPercentage = _cursor.getDouble(_cursorIndexOfPercentage);
            }
            final float _tmpConfidenceScore;
            _tmpConfidenceScore = _cursor.getFloat(_cursorIndexOfConfidenceScore);
            final boolean _tmpActionable;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfActionable);
            _tmpActionable = _tmp_2 != 0;
            final String _tmpSuggestedAction;
            if (_cursor.isNull(_cursorIndexOfSuggestedAction)) {
              _tmpSuggestedAction = null;
            } else {
              _tmpSuggestedAction = _cursor.getString(_cursorIndexOfSuggestedAction);
            }
            final boolean _tmpIsRead;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsRead);
            _tmpIsRead = _tmp_3 != 0;
            final boolean _tmpIsActedUpon;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsActedUpon);
            _tmpIsActedUpon = _tmp_4 != 0;
            final Long _tmpValidUntil;
            if (_cursor.isNull(_cursorIndexOfValidUntil)) {
              _tmpValidUntil = null;
            } else {
              _tmpValidUntil = _cursor.getLong(_cursorIndexOfValidUntil);
            }
            final String _tmpData;
            if (_cursor.isNull(_cursorIndexOfData)) {
              _tmpData = null;
            } else {
              _tmpData = _cursor.getString(_cursorIndexOfData);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new FinancialInsight(_tmpId,_tmpInsightType,_tmpTitle,_tmpDescription,_tmpSeverity,_tmpCategory,_tmpAmount,_tmpPercentage,_tmpConfidenceScore,_tmpActionable,_tmpSuggestedAction,_tmpIsRead,_tmpIsActedUpon,_tmpValidUntil,_tmpData,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<FinancialInsight>> getUnreadInsights() {
    final String _sql = "SELECT * FROM financial_insights WHERE isRead = 0 ORDER BY severity DESC, createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"financial_insights"}, new Callable<List<FinancialInsight>>() {
      @Override
      @NonNull
      public List<FinancialInsight> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfInsightType = CursorUtil.getColumnIndexOrThrow(_cursor, "insightType");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfSeverity = CursorUtil.getColumnIndexOrThrow(_cursor, "severity");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfPercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "percentage");
          final int _cursorIndexOfConfidenceScore = CursorUtil.getColumnIndexOrThrow(_cursor, "confidenceScore");
          final int _cursorIndexOfActionable = CursorUtil.getColumnIndexOrThrow(_cursor, "actionable");
          final int _cursorIndexOfSuggestedAction = CursorUtil.getColumnIndexOrThrow(_cursor, "suggestedAction");
          final int _cursorIndexOfIsRead = CursorUtil.getColumnIndexOrThrow(_cursor, "isRead");
          final int _cursorIndexOfIsActedUpon = CursorUtil.getColumnIndexOrThrow(_cursor, "isActedUpon");
          final int _cursorIndexOfValidUntil = CursorUtil.getColumnIndexOrThrow(_cursor, "validUntil");
          final int _cursorIndexOfData = CursorUtil.getColumnIndexOrThrow(_cursor, "data");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<FinancialInsight> _result = new ArrayList<FinancialInsight>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FinancialInsight _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final InsightType _tmpInsightType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfInsightType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfInsightType);
            }
            _tmpInsightType = __analyticsTypeConverters.toInsightType(_tmp);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final InsightSeverity _tmpSeverity;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfSeverity)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfSeverity);
            }
            _tmpSeverity = __analyticsTypeConverters.toInsightSeverity(_tmp_1);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final Double _tmpAmount;
            if (_cursor.isNull(_cursorIndexOfAmount)) {
              _tmpAmount = null;
            } else {
              _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            }
            final Double _tmpPercentage;
            if (_cursor.isNull(_cursorIndexOfPercentage)) {
              _tmpPercentage = null;
            } else {
              _tmpPercentage = _cursor.getDouble(_cursorIndexOfPercentage);
            }
            final float _tmpConfidenceScore;
            _tmpConfidenceScore = _cursor.getFloat(_cursorIndexOfConfidenceScore);
            final boolean _tmpActionable;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfActionable);
            _tmpActionable = _tmp_2 != 0;
            final String _tmpSuggestedAction;
            if (_cursor.isNull(_cursorIndexOfSuggestedAction)) {
              _tmpSuggestedAction = null;
            } else {
              _tmpSuggestedAction = _cursor.getString(_cursorIndexOfSuggestedAction);
            }
            final boolean _tmpIsRead;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsRead);
            _tmpIsRead = _tmp_3 != 0;
            final boolean _tmpIsActedUpon;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsActedUpon);
            _tmpIsActedUpon = _tmp_4 != 0;
            final Long _tmpValidUntil;
            if (_cursor.isNull(_cursorIndexOfValidUntil)) {
              _tmpValidUntil = null;
            } else {
              _tmpValidUntil = _cursor.getLong(_cursorIndexOfValidUntil);
            }
            final String _tmpData;
            if (_cursor.isNull(_cursorIndexOfData)) {
              _tmpData = null;
            } else {
              _tmpData = _cursor.getString(_cursorIndexOfData);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new FinancialInsight(_tmpId,_tmpInsightType,_tmpTitle,_tmpDescription,_tmpSeverity,_tmpCategory,_tmpAmount,_tmpPercentage,_tmpConfidenceScore,_tmpActionable,_tmpSuggestedAction,_tmpIsRead,_tmpIsActedUpon,_tmpValidUntil,_tmpData,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<FinancialInsight>> getInsightsByType(final InsightType type) {
    final String _sql = "SELECT * FROM financial_insights WHERE insightType = ? ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __analyticsTypeConverters.fromInsightType(type);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"financial_insights"}, new Callable<List<FinancialInsight>>() {
      @Override
      @NonNull
      public List<FinancialInsight> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfInsightType = CursorUtil.getColumnIndexOrThrow(_cursor, "insightType");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfSeverity = CursorUtil.getColumnIndexOrThrow(_cursor, "severity");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfPercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "percentage");
          final int _cursorIndexOfConfidenceScore = CursorUtil.getColumnIndexOrThrow(_cursor, "confidenceScore");
          final int _cursorIndexOfActionable = CursorUtil.getColumnIndexOrThrow(_cursor, "actionable");
          final int _cursorIndexOfSuggestedAction = CursorUtil.getColumnIndexOrThrow(_cursor, "suggestedAction");
          final int _cursorIndexOfIsRead = CursorUtil.getColumnIndexOrThrow(_cursor, "isRead");
          final int _cursorIndexOfIsActedUpon = CursorUtil.getColumnIndexOrThrow(_cursor, "isActedUpon");
          final int _cursorIndexOfValidUntil = CursorUtil.getColumnIndexOrThrow(_cursor, "validUntil");
          final int _cursorIndexOfData = CursorUtil.getColumnIndexOrThrow(_cursor, "data");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<FinancialInsight> _result = new ArrayList<FinancialInsight>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FinancialInsight _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final InsightType _tmpInsightType;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfInsightType)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfInsightType);
            }
            _tmpInsightType = __analyticsTypeConverters.toInsightType(_tmp_1);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final InsightSeverity _tmpSeverity;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfSeverity)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfSeverity);
            }
            _tmpSeverity = __analyticsTypeConverters.toInsightSeverity(_tmp_2);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final Double _tmpAmount;
            if (_cursor.isNull(_cursorIndexOfAmount)) {
              _tmpAmount = null;
            } else {
              _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            }
            final Double _tmpPercentage;
            if (_cursor.isNull(_cursorIndexOfPercentage)) {
              _tmpPercentage = null;
            } else {
              _tmpPercentage = _cursor.getDouble(_cursorIndexOfPercentage);
            }
            final float _tmpConfidenceScore;
            _tmpConfidenceScore = _cursor.getFloat(_cursorIndexOfConfidenceScore);
            final boolean _tmpActionable;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfActionable);
            _tmpActionable = _tmp_3 != 0;
            final String _tmpSuggestedAction;
            if (_cursor.isNull(_cursorIndexOfSuggestedAction)) {
              _tmpSuggestedAction = null;
            } else {
              _tmpSuggestedAction = _cursor.getString(_cursorIndexOfSuggestedAction);
            }
            final boolean _tmpIsRead;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsRead);
            _tmpIsRead = _tmp_4 != 0;
            final boolean _tmpIsActedUpon;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfIsActedUpon);
            _tmpIsActedUpon = _tmp_5 != 0;
            final Long _tmpValidUntil;
            if (_cursor.isNull(_cursorIndexOfValidUntil)) {
              _tmpValidUntil = null;
            } else {
              _tmpValidUntil = _cursor.getLong(_cursorIndexOfValidUntil);
            }
            final String _tmpData;
            if (_cursor.isNull(_cursorIndexOfData)) {
              _tmpData = null;
            } else {
              _tmpData = _cursor.getString(_cursorIndexOfData);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new FinancialInsight(_tmpId,_tmpInsightType,_tmpTitle,_tmpDescription,_tmpSeverity,_tmpCategory,_tmpAmount,_tmpPercentage,_tmpConfidenceScore,_tmpActionable,_tmpSuggestedAction,_tmpIsRead,_tmpIsActedUpon,_tmpValidUntil,_tmpData,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<FinancialInsight>> getInsightsBySeverity(final InsightSeverity severity) {
    final String _sql = "SELECT * FROM financial_insights WHERE severity = ? ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __analyticsTypeConverters.fromInsightSeverity(severity);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"financial_insights"}, new Callable<List<FinancialInsight>>() {
      @Override
      @NonNull
      public List<FinancialInsight> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfInsightType = CursorUtil.getColumnIndexOrThrow(_cursor, "insightType");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfSeverity = CursorUtil.getColumnIndexOrThrow(_cursor, "severity");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfPercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "percentage");
          final int _cursorIndexOfConfidenceScore = CursorUtil.getColumnIndexOrThrow(_cursor, "confidenceScore");
          final int _cursorIndexOfActionable = CursorUtil.getColumnIndexOrThrow(_cursor, "actionable");
          final int _cursorIndexOfSuggestedAction = CursorUtil.getColumnIndexOrThrow(_cursor, "suggestedAction");
          final int _cursorIndexOfIsRead = CursorUtil.getColumnIndexOrThrow(_cursor, "isRead");
          final int _cursorIndexOfIsActedUpon = CursorUtil.getColumnIndexOrThrow(_cursor, "isActedUpon");
          final int _cursorIndexOfValidUntil = CursorUtil.getColumnIndexOrThrow(_cursor, "validUntil");
          final int _cursorIndexOfData = CursorUtil.getColumnIndexOrThrow(_cursor, "data");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<FinancialInsight> _result = new ArrayList<FinancialInsight>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FinancialInsight _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final InsightType _tmpInsightType;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfInsightType)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfInsightType);
            }
            _tmpInsightType = __analyticsTypeConverters.toInsightType(_tmp_1);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final InsightSeverity _tmpSeverity;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfSeverity)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfSeverity);
            }
            _tmpSeverity = __analyticsTypeConverters.toInsightSeverity(_tmp_2);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final Double _tmpAmount;
            if (_cursor.isNull(_cursorIndexOfAmount)) {
              _tmpAmount = null;
            } else {
              _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            }
            final Double _tmpPercentage;
            if (_cursor.isNull(_cursorIndexOfPercentage)) {
              _tmpPercentage = null;
            } else {
              _tmpPercentage = _cursor.getDouble(_cursorIndexOfPercentage);
            }
            final float _tmpConfidenceScore;
            _tmpConfidenceScore = _cursor.getFloat(_cursorIndexOfConfidenceScore);
            final boolean _tmpActionable;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfActionable);
            _tmpActionable = _tmp_3 != 0;
            final String _tmpSuggestedAction;
            if (_cursor.isNull(_cursorIndexOfSuggestedAction)) {
              _tmpSuggestedAction = null;
            } else {
              _tmpSuggestedAction = _cursor.getString(_cursorIndexOfSuggestedAction);
            }
            final boolean _tmpIsRead;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsRead);
            _tmpIsRead = _tmp_4 != 0;
            final boolean _tmpIsActedUpon;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfIsActedUpon);
            _tmpIsActedUpon = _tmp_5 != 0;
            final Long _tmpValidUntil;
            if (_cursor.isNull(_cursorIndexOfValidUntil)) {
              _tmpValidUntil = null;
            } else {
              _tmpValidUntil = _cursor.getLong(_cursorIndexOfValidUntil);
            }
            final String _tmpData;
            if (_cursor.isNull(_cursorIndexOfData)) {
              _tmpData = null;
            } else {
              _tmpData = _cursor.getString(_cursorIndexOfData);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new FinancialInsight(_tmpId,_tmpInsightType,_tmpTitle,_tmpDescription,_tmpSeverity,_tmpCategory,_tmpAmount,_tmpPercentage,_tmpConfidenceScore,_tmpActionable,_tmpSuggestedAction,_tmpIsRead,_tmpIsActedUpon,_tmpValidUntil,_tmpData,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<FinancialInsight>> getInsightsByCategory(final String category) {
    final String _sql = "SELECT * FROM financial_insights WHERE category = ? ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (category == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, category);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"financial_insights"}, new Callable<List<FinancialInsight>>() {
      @Override
      @NonNull
      public List<FinancialInsight> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfInsightType = CursorUtil.getColumnIndexOrThrow(_cursor, "insightType");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfSeverity = CursorUtil.getColumnIndexOrThrow(_cursor, "severity");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfPercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "percentage");
          final int _cursorIndexOfConfidenceScore = CursorUtil.getColumnIndexOrThrow(_cursor, "confidenceScore");
          final int _cursorIndexOfActionable = CursorUtil.getColumnIndexOrThrow(_cursor, "actionable");
          final int _cursorIndexOfSuggestedAction = CursorUtil.getColumnIndexOrThrow(_cursor, "suggestedAction");
          final int _cursorIndexOfIsRead = CursorUtil.getColumnIndexOrThrow(_cursor, "isRead");
          final int _cursorIndexOfIsActedUpon = CursorUtil.getColumnIndexOrThrow(_cursor, "isActedUpon");
          final int _cursorIndexOfValidUntil = CursorUtil.getColumnIndexOrThrow(_cursor, "validUntil");
          final int _cursorIndexOfData = CursorUtil.getColumnIndexOrThrow(_cursor, "data");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<FinancialInsight> _result = new ArrayList<FinancialInsight>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FinancialInsight _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final InsightType _tmpInsightType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfInsightType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfInsightType);
            }
            _tmpInsightType = __analyticsTypeConverters.toInsightType(_tmp);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final InsightSeverity _tmpSeverity;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfSeverity)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfSeverity);
            }
            _tmpSeverity = __analyticsTypeConverters.toInsightSeverity(_tmp_1);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final Double _tmpAmount;
            if (_cursor.isNull(_cursorIndexOfAmount)) {
              _tmpAmount = null;
            } else {
              _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            }
            final Double _tmpPercentage;
            if (_cursor.isNull(_cursorIndexOfPercentage)) {
              _tmpPercentage = null;
            } else {
              _tmpPercentage = _cursor.getDouble(_cursorIndexOfPercentage);
            }
            final float _tmpConfidenceScore;
            _tmpConfidenceScore = _cursor.getFloat(_cursorIndexOfConfidenceScore);
            final boolean _tmpActionable;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfActionable);
            _tmpActionable = _tmp_2 != 0;
            final String _tmpSuggestedAction;
            if (_cursor.isNull(_cursorIndexOfSuggestedAction)) {
              _tmpSuggestedAction = null;
            } else {
              _tmpSuggestedAction = _cursor.getString(_cursorIndexOfSuggestedAction);
            }
            final boolean _tmpIsRead;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsRead);
            _tmpIsRead = _tmp_3 != 0;
            final boolean _tmpIsActedUpon;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsActedUpon);
            _tmpIsActedUpon = _tmp_4 != 0;
            final Long _tmpValidUntil;
            if (_cursor.isNull(_cursorIndexOfValidUntil)) {
              _tmpValidUntil = null;
            } else {
              _tmpValidUntil = _cursor.getLong(_cursorIndexOfValidUntil);
            }
            final String _tmpData;
            if (_cursor.isNull(_cursorIndexOfData)) {
              _tmpData = null;
            } else {
              _tmpData = _cursor.getString(_cursorIndexOfData);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new FinancialInsight(_tmpId,_tmpInsightType,_tmpTitle,_tmpDescription,_tmpSeverity,_tmpCategory,_tmpAmount,_tmpPercentage,_tmpConfidenceScore,_tmpActionable,_tmpSuggestedAction,_tmpIsRead,_tmpIsActedUpon,_tmpValidUntil,_tmpData,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<FinancialInsight>> getActionableInsights() {
    final String _sql = "SELECT * FROM financial_insights WHERE actionable = 1 AND isActedUpon = 0 ORDER BY severity DESC, createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"financial_insights"}, new Callable<List<FinancialInsight>>() {
      @Override
      @NonNull
      public List<FinancialInsight> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfInsightType = CursorUtil.getColumnIndexOrThrow(_cursor, "insightType");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfSeverity = CursorUtil.getColumnIndexOrThrow(_cursor, "severity");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfPercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "percentage");
          final int _cursorIndexOfConfidenceScore = CursorUtil.getColumnIndexOrThrow(_cursor, "confidenceScore");
          final int _cursorIndexOfActionable = CursorUtil.getColumnIndexOrThrow(_cursor, "actionable");
          final int _cursorIndexOfSuggestedAction = CursorUtil.getColumnIndexOrThrow(_cursor, "suggestedAction");
          final int _cursorIndexOfIsRead = CursorUtil.getColumnIndexOrThrow(_cursor, "isRead");
          final int _cursorIndexOfIsActedUpon = CursorUtil.getColumnIndexOrThrow(_cursor, "isActedUpon");
          final int _cursorIndexOfValidUntil = CursorUtil.getColumnIndexOrThrow(_cursor, "validUntil");
          final int _cursorIndexOfData = CursorUtil.getColumnIndexOrThrow(_cursor, "data");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<FinancialInsight> _result = new ArrayList<FinancialInsight>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FinancialInsight _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final InsightType _tmpInsightType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfInsightType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfInsightType);
            }
            _tmpInsightType = __analyticsTypeConverters.toInsightType(_tmp);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final InsightSeverity _tmpSeverity;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfSeverity)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfSeverity);
            }
            _tmpSeverity = __analyticsTypeConverters.toInsightSeverity(_tmp_1);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final Double _tmpAmount;
            if (_cursor.isNull(_cursorIndexOfAmount)) {
              _tmpAmount = null;
            } else {
              _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            }
            final Double _tmpPercentage;
            if (_cursor.isNull(_cursorIndexOfPercentage)) {
              _tmpPercentage = null;
            } else {
              _tmpPercentage = _cursor.getDouble(_cursorIndexOfPercentage);
            }
            final float _tmpConfidenceScore;
            _tmpConfidenceScore = _cursor.getFloat(_cursorIndexOfConfidenceScore);
            final boolean _tmpActionable;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfActionable);
            _tmpActionable = _tmp_2 != 0;
            final String _tmpSuggestedAction;
            if (_cursor.isNull(_cursorIndexOfSuggestedAction)) {
              _tmpSuggestedAction = null;
            } else {
              _tmpSuggestedAction = _cursor.getString(_cursorIndexOfSuggestedAction);
            }
            final boolean _tmpIsRead;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsRead);
            _tmpIsRead = _tmp_3 != 0;
            final boolean _tmpIsActedUpon;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsActedUpon);
            _tmpIsActedUpon = _tmp_4 != 0;
            final Long _tmpValidUntil;
            if (_cursor.isNull(_cursorIndexOfValidUntil)) {
              _tmpValidUntil = null;
            } else {
              _tmpValidUntil = _cursor.getLong(_cursorIndexOfValidUntil);
            }
            final String _tmpData;
            if (_cursor.isNull(_cursorIndexOfData)) {
              _tmpData = null;
            } else {
              _tmpData = _cursor.getString(_cursorIndexOfData);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new FinancialInsight(_tmpId,_tmpInsightType,_tmpTitle,_tmpDescription,_tmpSeverity,_tmpCategory,_tmpAmount,_tmpPercentage,_tmpConfidenceScore,_tmpActionable,_tmpSuggestedAction,_tmpIsRead,_tmpIsActedUpon,_tmpValidUntil,_tmpData,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Object getValidInsights(final long currentTime,
      final Continuation<? super List<FinancialInsight>> $completion) {
    final String _sql = "SELECT * FROM financial_insights WHERE validUntil IS NULL OR validUntil > ? ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, currentTime);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<FinancialInsight>>() {
      @Override
      @NonNull
      public List<FinancialInsight> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfInsightType = CursorUtil.getColumnIndexOrThrow(_cursor, "insightType");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfSeverity = CursorUtil.getColumnIndexOrThrow(_cursor, "severity");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfPercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "percentage");
          final int _cursorIndexOfConfidenceScore = CursorUtil.getColumnIndexOrThrow(_cursor, "confidenceScore");
          final int _cursorIndexOfActionable = CursorUtil.getColumnIndexOrThrow(_cursor, "actionable");
          final int _cursorIndexOfSuggestedAction = CursorUtil.getColumnIndexOrThrow(_cursor, "suggestedAction");
          final int _cursorIndexOfIsRead = CursorUtil.getColumnIndexOrThrow(_cursor, "isRead");
          final int _cursorIndexOfIsActedUpon = CursorUtil.getColumnIndexOrThrow(_cursor, "isActedUpon");
          final int _cursorIndexOfValidUntil = CursorUtil.getColumnIndexOrThrow(_cursor, "validUntil");
          final int _cursorIndexOfData = CursorUtil.getColumnIndexOrThrow(_cursor, "data");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<FinancialInsight> _result = new ArrayList<FinancialInsight>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FinancialInsight _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final InsightType _tmpInsightType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfInsightType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfInsightType);
            }
            _tmpInsightType = __analyticsTypeConverters.toInsightType(_tmp);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final InsightSeverity _tmpSeverity;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfSeverity)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfSeverity);
            }
            _tmpSeverity = __analyticsTypeConverters.toInsightSeverity(_tmp_1);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final Double _tmpAmount;
            if (_cursor.isNull(_cursorIndexOfAmount)) {
              _tmpAmount = null;
            } else {
              _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            }
            final Double _tmpPercentage;
            if (_cursor.isNull(_cursorIndexOfPercentage)) {
              _tmpPercentage = null;
            } else {
              _tmpPercentage = _cursor.getDouble(_cursorIndexOfPercentage);
            }
            final float _tmpConfidenceScore;
            _tmpConfidenceScore = _cursor.getFloat(_cursorIndexOfConfidenceScore);
            final boolean _tmpActionable;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfActionable);
            _tmpActionable = _tmp_2 != 0;
            final String _tmpSuggestedAction;
            if (_cursor.isNull(_cursorIndexOfSuggestedAction)) {
              _tmpSuggestedAction = null;
            } else {
              _tmpSuggestedAction = _cursor.getString(_cursorIndexOfSuggestedAction);
            }
            final boolean _tmpIsRead;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsRead);
            _tmpIsRead = _tmp_3 != 0;
            final boolean _tmpIsActedUpon;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsActedUpon);
            _tmpIsActedUpon = _tmp_4 != 0;
            final Long _tmpValidUntil;
            if (_cursor.isNull(_cursorIndexOfValidUntil)) {
              _tmpValidUntil = null;
            } else {
              _tmpValidUntil = _cursor.getLong(_cursorIndexOfValidUntil);
            }
            final String _tmpData;
            if (_cursor.isNull(_cursorIndexOfData)) {
              _tmpData = null;
            } else {
              _tmpData = _cursor.getString(_cursorIndexOfData);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new FinancialInsight(_tmpId,_tmpInsightType,_tmpTitle,_tmpDescription,_tmpSeverity,_tmpCategory,_tmpAmount,_tmpPercentage,_tmpConfidenceScore,_tmpActionable,_tmpSuggestedAction,_tmpIsRead,_tmpIsActedUpon,_tmpValidUntil,_tmpData,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Object getHighConfidenceInsights(final float minConfidence,
      final Continuation<? super List<FinancialInsight>> $completion) {
    final String _sql = "SELECT * FROM financial_insights WHERE confidenceScore >= ? ORDER BY confidenceScore DESC, createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindDouble(_argIndex, minConfidence);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<FinancialInsight>>() {
      @Override
      @NonNull
      public List<FinancialInsight> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfInsightType = CursorUtil.getColumnIndexOrThrow(_cursor, "insightType");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfSeverity = CursorUtil.getColumnIndexOrThrow(_cursor, "severity");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfPercentage = CursorUtil.getColumnIndexOrThrow(_cursor, "percentage");
          final int _cursorIndexOfConfidenceScore = CursorUtil.getColumnIndexOrThrow(_cursor, "confidenceScore");
          final int _cursorIndexOfActionable = CursorUtil.getColumnIndexOrThrow(_cursor, "actionable");
          final int _cursorIndexOfSuggestedAction = CursorUtil.getColumnIndexOrThrow(_cursor, "suggestedAction");
          final int _cursorIndexOfIsRead = CursorUtil.getColumnIndexOrThrow(_cursor, "isRead");
          final int _cursorIndexOfIsActedUpon = CursorUtil.getColumnIndexOrThrow(_cursor, "isActedUpon");
          final int _cursorIndexOfValidUntil = CursorUtil.getColumnIndexOrThrow(_cursor, "validUntil");
          final int _cursorIndexOfData = CursorUtil.getColumnIndexOrThrow(_cursor, "data");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<FinancialInsight> _result = new ArrayList<FinancialInsight>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FinancialInsight _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final InsightType _tmpInsightType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfInsightType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfInsightType);
            }
            _tmpInsightType = __analyticsTypeConverters.toInsightType(_tmp);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final InsightSeverity _tmpSeverity;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfSeverity)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfSeverity);
            }
            _tmpSeverity = __analyticsTypeConverters.toInsightSeverity(_tmp_1);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final Double _tmpAmount;
            if (_cursor.isNull(_cursorIndexOfAmount)) {
              _tmpAmount = null;
            } else {
              _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            }
            final Double _tmpPercentage;
            if (_cursor.isNull(_cursorIndexOfPercentage)) {
              _tmpPercentage = null;
            } else {
              _tmpPercentage = _cursor.getDouble(_cursorIndexOfPercentage);
            }
            final float _tmpConfidenceScore;
            _tmpConfidenceScore = _cursor.getFloat(_cursorIndexOfConfidenceScore);
            final boolean _tmpActionable;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfActionable);
            _tmpActionable = _tmp_2 != 0;
            final String _tmpSuggestedAction;
            if (_cursor.isNull(_cursorIndexOfSuggestedAction)) {
              _tmpSuggestedAction = null;
            } else {
              _tmpSuggestedAction = _cursor.getString(_cursorIndexOfSuggestedAction);
            }
            final boolean _tmpIsRead;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsRead);
            _tmpIsRead = _tmp_3 != 0;
            final boolean _tmpIsActedUpon;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsActedUpon);
            _tmpIsActedUpon = _tmp_4 != 0;
            final Long _tmpValidUntil;
            if (_cursor.isNull(_cursorIndexOfValidUntil)) {
              _tmpValidUntil = null;
            } else {
              _tmpValidUntil = _cursor.getLong(_cursorIndexOfValidUntil);
            }
            final String _tmpData;
            if (_cursor.isNull(_cursorIndexOfData)) {
              _tmpData = null;
            } else {
              _tmpData = _cursor.getString(_cursorIndexOfData);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new FinancialInsight(_tmpId,_tmpInsightType,_tmpTitle,_tmpDescription,_tmpSeverity,_tmpCategory,_tmpAmount,_tmpPercentage,_tmpConfidenceScore,_tmpActionable,_tmpSuggestedAction,_tmpIsRead,_tmpIsActedUpon,_tmpValidUntil,_tmpData,_tmpCreatedAt,_tmpUpdatedAt);
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
  public LiveData<Integer> getUnreadCount() {
    final String _sql = "SELECT COUNT(*) FROM financial_insights WHERE isRead = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"financial_insights"}, false, new Callable<Integer>() {
      @Override
      @Nullable
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
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
  public Object getUnreadCountBySeverity(final InsightSeverity severity,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM financial_insights WHERE severity = ? AND isRead = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __analyticsTypeConverters.fromInsightSeverity(severity);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp_1;
            if (_cursor.isNull(0)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getInt(0);
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

  @Override
  public Object getInsightCountByTypeSince(final InsightType type, final long since,
      final Continuation<? super Integer> $completion) {
    final String _sql = "\n"
            + "        SELECT COUNT(*) FROM financial_insights \n"
            + "        WHERE insightType = ? \n"
            + "        AND createdAt > ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    final String _tmp = __analyticsTypeConverters.fromInsightType(type);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, since);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp_1;
            if (_cursor.isNull(0)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getInt(0);
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

  @Override
  public Object getAverageConfidenceByType(final InsightType type, final long since,
      final Continuation<? super Double> $completion) {
    final String _sql = "\n"
            + "        SELECT AVG(confidenceScore) FROM financial_insights \n"
            + "        WHERE insightType = ? \n"
            + "        AND createdAt > ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    final String _tmp = __analyticsTypeConverters.fromInsightType(type);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, since);
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

  @Override
  public Object getInsightCountsByType(final long since,
      final Continuation<? super List<InsightTypeCount>> $completion) {
    final String _sql = "\n"
            + "        SELECT insightType, COUNT(*) as count \n"
            + "        FROM financial_insights \n"
            + "        WHERE createdAt > ? \n"
            + "        GROUP BY insightType\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, since);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<InsightTypeCount>>() {
      @Override
      @NonNull
      public List<InsightTypeCount> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfInsightType = 0;
          final int _cursorIndexOfCount = 1;
          final List<InsightTypeCount> _result = new ArrayList<InsightTypeCount>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final InsightTypeCount _item;
            final String _tmpInsightType;
            if (_cursor.isNull(_cursorIndexOfInsightType)) {
              _tmpInsightType = null;
            } else {
              _tmpInsightType = _cursor.getString(_cursorIndexOfInsightType);
            }
            final int _tmpCount;
            _tmpCount = _cursor.getInt(_cursorIndexOfCount);
            _item = new InsightTypeCount(_tmpInsightType,_tmpCount);
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
  public Object getInsightCountsByCategory(final long since,
      final Continuation<? super List<CategoryCount>> $completion) {
    final String _sql = "\n"
            + "        SELECT category, COUNT(*) as count \n"
            + "        FROM financial_insights \n"
            + "        WHERE category IS NOT NULL AND createdAt > ? \n"
            + "        GROUP BY category\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, since);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<CategoryCount>>() {
      @Override
      @NonNull
      public List<CategoryCount> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfCategory = 0;
          final int _cursorIndexOfCount = 1;
          final List<CategoryCount> _result = new ArrayList<CategoryCount>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CategoryCount _item;
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final int _tmpCount;
            _tmpCount = _cursor.getInt(_cursorIndexOfCount);
            _item = new CategoryCount(_tmpCategory,_tmpCount);
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
  public Object markAsRead(final List<Long> ids, final long timestamp,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
        _stringBuilder.append("UPDATE financial_insights SET isRead = 1, updatedAt = ");
        _stringBuilder.append("?");
        _stringBuilder.append(" WHERE id IN (");
        final int _inputSize = ids.size();
        StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
        _stringBuilder.append(")");
        final String _sql = _stringBuilder.toString();
        final SupportSQLiteStatement _stmt = __db.compileStatement(_sql);
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, timestamp);
        _argIndex = 2;
        for (Long _item : ids) {
          if (_item == null) {
            _stmt.bindNull(_argIndex);
          } else {
            _stmt.bindLong(_argIndex, _item);
          }
          _argIndex++;
        }
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
