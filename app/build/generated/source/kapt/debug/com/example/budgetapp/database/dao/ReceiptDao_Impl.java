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
import com.example.budgetapp.database.entities.Receipt;
import java.lang.Class;
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
public final class ReceiptDao_Impl implements ReceiptDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Receipt> __insertionAdapterOfReceipt;

  private final EntityDeletionOrUpdateAdapter<Receipt> __deletionAdapterOfReceipt;

  private final EntityDeletionOrUpdateAdapter<Receipt> __updateAdapterOfReceipt;

  private final SharedSQLiteStatement __preparedStmtOfDeleteReceiptById;

  public ReceiptDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfReceipt = new EntityInsertionAdapter<Receipt>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `receipts` (`id`,`expenseId`,`receiptNumber`,`totalAmount`,`tax`,`discount`,`createdAt`,`updatedAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Receipt entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getExpenseId());
        if (entity.getReceiptNumber() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getReceiptNumber());
        }
        statement.bindDouble(4, entity.getTotalAmount());
        statement.bindDouble(5, entity.getTax());
        statement.bindDouble(6, entity.getDiscount());
        statement.bindLong(7, entity.getCreatedAt());
        statement.bindLong(8, entity.getUpdatedAt());
      }
    };
    this.__deletionAdapterOfReceipt = new EntityDeletionOrUpdateAdapter<Receipt>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `receipts` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Receipt entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfReceipt = new EntityDeletionOrUpdateAdapter<Receipt>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `receipts` SET `id` = ?,`expenseId` = ?,`receiptNumber` = ?,`totalAmount` = ?,`tax` = ?,`discount` = ?,`createdAt` = ?,`updatedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Receipt entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getExpenseId());
        if (entity.getReceiptNumber() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getReceiptNumber());
        }
        statement.bindDouble(4, entity.getTotalAmount());
        statement.bindDouble(5, entity.getTax());
        statement.bindDouble(6, entity.getDiscount());
        statement.bindLong(7, entity.getCreatedAt());
        statement.bindLong(8, entity.getUpdatedAt());
        statement.bindLong(9, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteReceiptById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM receipts WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertReceipt(final Receipt receipt, final Continuation<? super Long> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfReceipt.insertAndReturnId(receipt);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object deleteReceipt(final Receipt receipt, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfReceipt.handle(receipt);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object updateReceipt(final Receipt receipt, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfReceipt.handle(receipt);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object deleteReceiptById(final long receiptId, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteReceiptById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, receiptId);
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
          __preparedStmtOfDeleteReceiptById.release(_stmt);
        }
      }
    }, arg1);
  }

  @Override
  public Flow<List<Receipt>> getAllReceipts() {
    final String _sql = "SELECT * FROM receipts ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"receipts"}, new Callable<List<Receipt>>() {
      @Override
      @NonNull
      public List<Receipt> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfExpenseId = CursorUtil.getColumnIndexOrThrow(_cursor, "expenseId");
          final int _cursorIndexOfReceiptNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "receiptNumber");
          final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
          final int _cursorIndexOfTax = CursorUtil.getColumnIndexOrThrow(_cursor, "tax");
          final int _cursorIndexOfDiscount = CursorUtil.getColumnIndexOrThrow(_cursor, "discount");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<Receipt> _result = new ArrayList<Receipt>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Receipt _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpExpenseId;
            _tmpExpenseId = _cursor.getLong(_cursorIndexOfExpenseId);
            final String _tmpReceiptNumber;
            if (_cursor.isNull(_cursorIndexOfReceiptNumber)) {
              _tmpReceiptNumber = null;
            } else {
              _tmpReceiptNumber = _cursor.getString(_cursorIndexOfReceiptNumber);
            }
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            final double _tmpTax;
            _tmpTax = _cursor.getDouble(_cursorIndexOfTax);
            final double _tmpDiscount;
            _tmpDiscount = _cursor.getDouble(_cursorIndexOfDiscount);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new Receipt(_tmpId,_tmpExpenseId,_tmpReceiptNumber,_tmpTotalAmount,_tmpTax,_tmpDiscount,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Object getReceiptById(final long receiptId, final Continuation<? super Receipt> arg1) {
    final String _sql = "SELECT * FROM receipts WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, receiptId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Receipt>() {
      @Override
      @Nullable
      public Receipt call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfExpenseId = CursorUtil.getColumnIndexOrThrow(_cursor, "expenseId");
          final int _cursorIndexOfReceiptNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "receiptNumber");
          final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
          final int _cursorIndexOfTax = CursorUtil.getColumnIndexOrThrow(_cursor, "tax");
          final int _cursorIndexOfDiscount = CursorUtil.getColumnIndexOrThrow(_cursor, "discount");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final Receipt _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpExpenseId;
            _tmpExpenseId = _cursor.getLong(_cursorIndexOfExpenseId);
            final String _tmpReceiptNumber;
            if (_cursor.isNull(_cursorIndexOfReceiptNumber)) {
              _tmpReceiptNumber = null;
            } else {
              _tmpReceiptNumber = _cursor.getString(_cursorIndexOfReceiptNumber);
            }
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            final double _tmpTax;
            _tmpTax = _cursor.getDouble(_cursorIndexOfTax);
            final double _tmpDiscount;
            _tmpDiscount = _cursor.getDouble(_cursorIndexOfDiscount);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new Receipt(_tmpId,_tmpExpenseId,_tmpReceiptNumber,_tmpTotalAmount,_tmpTax,_tmpDiscount,_tmpCreatedAt,_tmpUpdatedAt);
          } else {
            _result = null;
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
  public Object getReceiptByExpenseId(final long expenseId,
      final Continuation<? super Receipt> arg1) {
    final String _sql = "SELECT * FROM receipts WHERE expenseId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, expenseId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Receipt>() {
      @Override
      @Nullable
      public Receipt call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfExpenseId = CursorUtil.getColumnIndexOrThrow(_cursor, "expenseId");
          final int _cursorIndexOfReceiptNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "receiptNumber");
          final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
          final int _cursorIndexOfTax = CursorUtil.getColumnIndexOrThrow(_cursor, "tax");
          final int _cursorIndexOfDiscount = CursorUtil.getColumnIndexOrThrow(_cursor, "discount");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final Receipt _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpExpenseId;
            _tmpExpenseId = _cursor.getLong(_cursorIndexOfExpenseId);
            final String _tmpReceiptNumber;
            if (_cursor.isNull(_cursorIndexOfReceiptNumber)) {
              _tmpReceiptNumber = null;
            } else {
              _tmpReceiptNumber = _cursor.getString(_cursorIndexOfReceiptNumber);
            }
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            final double _tmpTax;
            _tmpTax = _cursor.getDouble(_cursorIndexOfTax);
            final double _tmpDiscount;
            _tmpDiscount = _cursor.getDouble(_cursorIndexOfDiscount);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new Receipt(_tmpId,_tmpExpenseId,_tmpReceiptNumber,_tmpTotalAmount,_tmpTax,_tmpDiscount,_tmpCreatedAt,_tmpUpdatedAt);
          } else {
            _result = null;
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
  public Flow<List<Receipt>> getReceiptsByDateRange(final long startDate, final long endDate) {
    final String _sql = "SELECT * FROM receipts WHERE createdAt >= ? AND createdAt <= ? ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endDate);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"receipts"}, new Callable<List<Receipt>>() {
      @Override
      @NonNull
      public List<Receipt> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfExpenseId = CursorUtil.getColumnIndexOrThrow(_cursor, "expenseId");
          final int _cursorIndexOfReceiptNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "receiptNumber");
          final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
          final int _cursorIndexOfTax = CursorUtil.getColumnIndexOrThrow(_cursor, "tax");
          final int _cursorIndexOfDiscount = CursorUtil.getColumnIndexOrThrow(_cursor, "discount");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<Receipt> _result = new ArrayList<Receipt>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Receipt _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpExpenseId;
            _tmpExpenseId = _cursor.getLong(_cursorIndexOfExpenseId);
            final String _tmpReceiptNumber;
            if (_cursor.isNull(_cursorIndexOfReceiptNumber)) {
              _tmpReceiptNumber = null;
            } else {
              _tmpReceiptNumber = _cursor.getString(_cursorIndexOfReceiptNumber);
            }
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            final double _tmpTax;
            _tmpTax = _cursor.getDouble(_cursorIndexOfTax);
            final double _tmpDiscount;
            _tmpDiscount = _cursor.getDouble(_cursorIndexOfDiscount);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new Receipt(_tmpId,_tmpExpenseId,_tmpReceiptNumber,_tmpTotalAmount,_tmpTax,_tmpDiscount,_tmpCreatedAt,_tmpUpdatedAt);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
