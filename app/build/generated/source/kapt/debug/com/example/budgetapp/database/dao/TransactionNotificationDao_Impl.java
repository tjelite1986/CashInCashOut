package com.example.budgetapp.database.dao;

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
import com.example.budgetapp.database.entities.TransactionNotification;
import com.example.budgetapp.database.entities.TransactionType;
import java.lang.Class;
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

@SuppressWarnings({"unchecked", "deprecation"})
public final class TransactionNotificationDao_Impl implements TransactionNotificationDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<TransactionNotification> __insertionAdapterOfTransactionNotification;

  private final EntityDeletionOrUpdateAdapter<TransactionNotification> __deletionAdapterOfTransactionNotification;

  private final EntityDeletionOrUpdateAdapter<TransactionNotification> __updateAdapterOfTransactionNotification;

  private final SharedSQLiteStatement __preparedStmtOfDeleteNotificationForTransaction;

  private final SharedSQLiteStatement __preparedStmtOfDeleteNotificationsByType;

  private final SharedSQLiteStatement __preparedStmtOfToggleNotification;

  public TransactionNotificationDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTransactionNotification = new EntityInsertionAdapter<TransactionNotification>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `transaction_notifications` (`id`,`transactionType`,`transactionId`,`transactionTitle`,`isEnabled`,`reminderDaysBefore`,`customMessage`,`createdAt`,`updatedAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TransactionNotification entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, __TransactionType_enumToString(entity.getTransactionType()));
        statement.bindLong(3, entity.getTransactionId());
        if (entity.getTransactionTitle() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getTransactionTitle());
        }
        final int _tmp = entity.isEnabled() ? 1 : 0;
        statement.bindLong(5, _tmp);
        statement.bindLong(6, entity.getReminderDaysBefore());
        if (entity.getCustomMessage() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getCustomMessage());
        }
        statement.bindLong(8, entity.getCreatedAt());
        statement.bindLong(9, entity.getUpdatedAt());
      }
    };
    this.__deletionAdapterOfTransactionNotification = new EntityDeletionOrUpdateAdapter<TransactionNotification>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `transaction_notifications` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TransactionNotification entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfTransactionNotification = new EntityDeletionOrUpdateAdapter<TransactionNotification>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `transaction_notifications` SET `id` = ?,`transactionType` = ?,`transactionId` = ?,`transactionTitle` = ?,`isEnabled` = ?,`reminderDaysBefore` = ?,`customMessage` = ?,`createdAt` = ?,`updatedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final TransactionNotification entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, __TransactionType_enumToString(entity.getTransactionType()));
        statement.bindLong(3, entity.getTransactionId());
        if (entity.getTransactionTitle() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getTransactionTitle());
        }
        final int _tmp = entity.isEnabled() ? 1 : 0;
        statement.bindLong(5, _tmp);
        statement.bindLong(6, entity.getReminderDaysBefore());
        if (entity.getCustomMessage() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getCustomMessage());
        }
        statement.bindLong(8, entity.getCreatedAt());
        statement.bindLong(9, entity.getUpdatedAt());
        statement.bindLong(10, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteNotificationForTransaction = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM transaction_notifications WHERE transactionId = ? AND transactionType = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteNotificationsByType = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM transaction_notifications WHERE transactionType = ?";
        return _query;
      }
    };
    this.__preparedStmtOfToggleNotification = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE transaction_notifications SET isEnabled = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertNotification(final TransactionNotification notification,
      final Continuation<? super Long> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfTransactionNotification.insertAndReturnId(notification);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object insertNotifications(final List<TransactionNotification> notifications,
      final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfTransactionNotification.insert(notifications);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object deleteNotification(final TransactionNotification notification,
      final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfTransactionNotification.handle(notification);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object updateNotification(final TransactionNotification notification,
      final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfTransactionNotification.handle(notification);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object deleteNotificationForTransaction(final long transactionId,
      final TransactionType type, final Continuation<? super Unit> arg2) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteNotificationForTransaction.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, transactionId);
        _argIndex = 2;
        _stmt.bindString(_argIndex, __TransactionType_enumToString(type));
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
          __preparedStmtOfDeleteNotificationForTransaction.release(_stmt);
        }
      }
    }, arg2);
  }

  @Override
  public Object deleteNotificationsByType(final TransactionType type,
      final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteNotificationsByType.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, __TransactionType_enumToString(type));
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
          __preparedStmtOfDeleteNotificationsByType.release(_stmt);
        }
      }
    }, arg1);
  }

  @Override
  public Object toggleNotification(final long id, final boolean enabled,
      final Continuation<? super Unit> arg2) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfToggleNotification.acquire();
        int _argIndex = 1;
        final int _tmp = enabled ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
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
          __preparedStmtOfToggleNotification.release(_stmt);
        }
      }
    }, arg2);
  }

  @Override
  public Object getActiveNotifications(
      final Continuation<? super List<TransactionNotification>> arg0) {
    final String _sql = "SELECT * FROM transaction_notifications WHERE isEnabled = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<TransactionNotification>>() {
      @Override
      @NonNull
      public List<TransactionNotification> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTransactionType = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionType");
          final int _cursorIndexOfTransactionId = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionId");
          final int _cursorIndexOfTransactionTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionTitle");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isEnabled");
          final int _cursorIndexOfReminderDaysBefore = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderDaysBefore");
          final int _cursorIndexOfCustomMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "customMessage");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<TransactionNotification> _result = new ArrayList<TransactionNotification>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TransactionNotification _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final TransactionType _tmpTransactionType;
            _tmpTransactionType = __TransactionType_stringToEnum(_cursor.getString(_cursorIndexOfTransactionType));
            final long _tmpTransactionId;
            _tmpTransactionId = _cursor.getLong(_cursorIndexOfTransactionId);
            final String _tmpTransactionTitle;
            if (_cursor.isNull(_cursorIndexOfTransactionTitle)) {
              _tmpTransactionTitle = null;
            } else {
              _tmpTransactionTitle = _cursor.getString(_cursorIndexOfTransactionTitle);
            }
            final boolean _tmpIsEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEnabled);
            _tmpIsEnabled = _tmp != 0;
            final int _tmpReminderDaysBefore;
            _tmpReminderDaysBefore = _cursor.getInt(_cursorIndexOfReminderDaysBefore);
            final String _tmpCustomMessage;
            if (_cursor.isNull(_cursorIndexOfCustomMessage)) {
              _tmpCustomMessage = null;
            } else {
              _tmpCustomMessage = _cursor.getString(_cursorIndexOfCustomMessage);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new TransactionNotification(_tmpId,_tmpTransactionType,_tmpTransactionId,_tmpTransactionTitle,_tmpIsEnabled,_tmpReminderDaysBefore,_tmpCustomMessage,_tmpCreatedAt,_tmpUpdatedAt);
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

  @Override
  public Object getAllNotifications(
      final Continuation<? super List<TransactionNotification>> arg0) {
    final String _sql = "SELECT * FROM transaction_notifications";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<TransactionNotification>>() {
      @Override
      @NonNull
      public List<TransactionNotification> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTransactionType = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionType");
          final int _cursorIndexOfTransactionId = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionId");
          final int _cursorIndexOfTransactionTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionTitle");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isEnabled");
          final int _cursorIndexOfReminderDaysBefore = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderDaysBefore");
          final int _cursorIndexOfCustomMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "customMessage");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<TransactionNotification> _result = new ArrayList<TransactionNotification>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TransactionNotification _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final TransactionType _tmpTransactionType;
            _tmpTransactionType = __TransactionType_stringToEnum(_cursor.getString(_cursorIndexOfTransactionType));
            final long _tmpTransactionId;
            _tmpTransactionId = _cursor.getLong(_cursorIndexOfTransactionId);
            final String _tmpTransactionTitle;
            if (_cursor.isNull(_cursorIndexOfTransactionTitle)) {
              _tmpTransactionTitle = null;
            } else {
              _tmpTransactionTitle = _cursor.getString(_cursorIndexOfTransactionTitle);
            }
            final boolean _tmpIsEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEnabled);
            _tmpIsEnabled = _tmp != 0;
            final int _tmpReminderDaysBefore;
            _tmpReminderDaysBefore = _cursor.getInt(_cursorIndexOfReminderDaysBefore);
            final String _tmpCustomMessage;
            if (_cursor.isNull(_cursorIndexOfCustomMessage)) {
              _tmpCustomMessage = null;
            } else {
              _tmpCustomMessage = _cursor.getString(_cursorIndexOfCustomMessage);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new TransactionNotification(_tmpId,_tmpTransactionType,_tmpTransactionId,_tmpTransactionTitle,_tmpIsEnabled,_tmpReminderDaysBefore,_tmpCustomMessage,_tmpCreatedAt,_tmpUpdatedAt);
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

  @Override
  public Object getNotificationForTransaction(final long transactionId, final TransactionType type,
      final Continuation<? super TransactionNotification> arg2) {
    final String _sql = "SELECT * FROM transaction_notifications WHERE transactionId = ? AND transactionType = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, transactionId);
    _argIndex = 2;
    _statement.bindString(_argIndex, __TransactionType_enumToString(type));
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<TransactionNotification>() {
      @Override
      @Nullable
      public TransactionNotification call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTransactionType = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionType");
          final int _cursorIndexOfTransactionId = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionId");
          final int _cursorIndexOfTransactionTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionTitle");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isEnabled");
          final int _cursorIndexOfReminderDaysBefore = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderDaysBefore");
          final int _cursorIndexOfCustomMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "customMessage");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final TransactionNotification _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final TransactionType _tmpTransactionType;
            _tmpTransactionType = __TransactionType_stringToEnum(_cursor.getString(_cursorIndexOfTransactionType));
            final long _tmpTransactionId;
            _tmpTransactionId = _cursor.getLong(_cursorIndexOfTransactionId);
            final String _tmpTransactionTitle;
            if (_cursor.isNull(_cursorIndexOfTransactionTitle)) {
              _tmpTransactionTitle = null;
            } else {
              _tmpTransactionTitle = _cursor.getString(_cursorIndexOfTransactionTitle);
            }
            final boolean _tmpIsEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEnabled);
            _tmpIsEnabled = _tmp != 0;
            final int _tmpReminderDaysBefore;
            _tmpReminderDaysBefore = _cursor.getInt(_cursorIndexOfReminderDaysBefore);
            final String _tmpCustomMessage;
            if (_cursor.isNull(_cursorIndexOfCustomMessage)) {
              _tmpCustomMessage = null;
            } else {
              _tmpCustomMessage = _cursor.getString(_cursorIndexOfCustomMessage);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new TransactionNotification(_tmpId,_tmpTransactionType,_tmpTransactionId,_tmpTransactionTitle,_tmpIsEnabled,_tmpReminderDaysBefore,_tmpCustomMessage,_tmpCreatedAt,_tmpUpdatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg2);
  }

  @Override
  public Object getNotificationsByType(final TransactionType type,
      final Continuation<? super List<TransactionNotification>> arg1) {
    final String _sql = "SELECT * FROM transaction_notifications WHERE transactionType = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, __TransactionType_enumToString(type));
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<TransactionNotification>>() {
      @Override
      @NonNull
      public List<TransactionNotification> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTransactionType = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionType");
          final int _cursorIndexOfTransactionId = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionId");
          final int _cursorIndexOfTransactionTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionTitle");
          final int _cursorIndexOfIsEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "isEnabled");
          final int _cursorIndexOfReminderDaysBefore = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderDaysBefore");
          final int _cursorIndexOfCustomMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "customMessage");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<TransactionNotification> _result = new ArrayList<TransactionNotification>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final TransactionNotification _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final TransactionType _tmpTransactionType;
            _tmpTransactionType = __TransactionType_stringToEnum(_cursor.getString(_cursorIndexOfTransactionType));
            final long _tmpTransactionId;
            _tmpTransactionId = _cursor.getLong(_cursorIndexOfTransactionId);
            final String _tmpTransactionTitle;
            if (_cursor.isNull(_cursorIndexOfTransactionTitle)) {
              _tmpTransactionTitle = null;
            } else {
              _tmpTransactionTitle = _cursor.getString(_cursorIndexOfTransactionTitle);
            }
            final boolean _tmpIsEnabled;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsEnabled);
            _tmpIsEnabled = _tmp != 0;
            final int _tmpReminderDaysBefore;
            _tmpReminderDaysBefore = _cursor.getInt(_cursorIndexOfReminderDaysBefore);
            final String _tmpCustomMessage;
            if (_cursor.isNull(_cursorIndexOfCustomMessage)) {
              _tmpCustomMessage = null;
            } else {
              _tmpCustomMessage = _cursor.getString(_cursorIndexOfCustomMessage);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new TransactionNotification(_tmpId,_tmpTransactionType,_tmpTransactionId,_tmpTransactionTitle,_tmpIsEnabled,_tmpReminderDaysBefore,_tmpCustomMessage,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Object getActiveNotificationCount(final Continuation<? super Integer> arg0) {
    final String _sql = "SELECT COUNT(*) FROM transaction_notifications WHERE isEnabled = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
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
          _statement.release();
        }
      }
    }, arg0);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private String __TransactionType_enumToString(@NonNull final TransactionType _value) {
    switch (_value) {
      case INCOME: return "INCOME";
      case EXPENSE: return "EXPENSE";
      case LOAN_DUE: return "LOAN_DUE";
      default: throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + _value);
    }
  }

  private TransactionType __TransactionType_stringToEnum(@NonNull final String _value) {
    switch (_value) {
      case "INCOME": return TransactionType.INCOME;
      case "EXPENSE": return TransactionType.EXPENSE;
      case "LOAN_DUE": return TransactionType.LOAN_DUE;
      default: throw new IllegalArgumentException("Can't convert value to enum, unknown value: " + _value);
    }
  }
}
