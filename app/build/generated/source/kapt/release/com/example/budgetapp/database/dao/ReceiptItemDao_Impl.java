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
import com.example.budgetapp.database.entities.ReceiptItem;
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
public final class ReceiptItemDao_Impl implements ReceiptItemDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ReceiptItem> __insertionAdapterOfReceiptItem;

  private final EntityDeletionOrUpdateAdapter<ReceiptItem> __deletionAdapterOfReceiptItem;

  private final EntityDeletionOrUpdateAdapter<ReceiptItem> __updateAdapterOfReceiptItem;

  private final SharedSQLiteStatement __preparedStmtOfDeleteReceiptItemById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteReceiptItemsByReceiptId;

  public ReceiptItemDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfReceiptItem = new EntityInsertionAdapter<ReceiptItem>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `receipt_items` (`id`,`receiptId`,`productId`,`productName`,`quantity`,`unitPrice`,`totalPrice`,`unit`,`productSize`,`discount`,`hasDeposit`,`depositAmount`,`storeName`,`storeChain`,`storeCity`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ReceiptItem entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getReceiptId());
        if (entity.getProductId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, entity.getProductId());
        }
        if (entity.getProductName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getProductName());
        }
        statement.bindDouble(5, entity.getQuantity());
        statement.bindDouble(6, entity.getUnitPrice());
        statement.bindDouble(7, entity.getTotalPrice());
        if (entity.getUnit() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getUnit());
        }
        if (entity.getProductSize() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getProductSize());
        }
        statement.bindDouble(10, entity.getDiscount());
        final int _tmp = entity.getHasDeposit() ? 1 : 0;
        statement.bindLong(11, _tmp);
        statement.bindDouble(12, entity.getDepositAmount());
        if (entity.getStoreName() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getStoreName());
        }
        if (entity.getStoreChain() == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, entity.getStoreChain());
        }
        if (entity.getStoreCity() == null) {
          statement.bindNull(15);
        } else {
          statement.bindString(15, entity.getStoreCity());
        }
        statement.bindLong(16, entity.getCreatedAt());
      }
    };
    this.__deletionAdapterOfReceiptItem = new EntityDeletionOrUpdateAdapter<ReceiptItem>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `receipt_items` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ReceiptItem entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfReceiptItem = new EntityDeletionOrUpdateAdapter<ReceiptItem>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `receipt_items` SET `id` = ?,`receiptId` = ?,`productId` = ?,`productName` = ?,`quantity` = ?,`unitPrice` = ?,`totalPrice` = ?,`unit` = ?,`productSize` = ?,`discount` = ?,`hasDeposit` = ?,`depositAmount` = ?,`storeName` = ?,`storeChain` = ?,`storeCity` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ReceiptItem entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getReceiptId());
        if (entity.getProductId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, entity.getProductId());
        }
        if (entity.getProductName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getProductName());
        }
        statement.bindDouble(5, entity.getQuantity());
        statement.bindDouble(6, entity.getUnitPrice());
        statement.bindDouble(7, entity.getTotalPrice());
        if (entity.getUnit() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getUnit());
        }
        if (entity.getProductSize() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getProductSize());
        }
        statement.bindDouble(10, entity.getDiscount());
        final int _tmp = entity.getHasDeposit() ? 1 : 0;
        statement.bindLong(11, _tmp);
        statement.bindDouble(12, entity.getDepositAmount());
        if (entity.getStoreName() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getStoreName());
        }
        if (entity.getStoreChain() == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, entity.getStoreChain());
        }
        if (entity.getStoreCity() == null) {
          statement.bindNull(15);
        } else {
          statement.bindString(15, entity.getStoreCity());
        }
        statement.bindLong(16, entity.getCreatedAt());
        statement.bindLong(17, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteReceiptItemById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM receipt_items WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteReceiptItemsByReceiptId = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM receipt_items WHERE receiptId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertReceiptItem(final ReceiptItem receiptItem,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfReceiptItem.insertAndReturnId(receiptItem);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertReceiptItems(final List<ReceiptItem> receiptItems,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfReceiptItem.insert(receiptItems);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteReceiptItem(final ReceiptItem receiptItem,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfReceiptItem.handle(receiptItem);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateReceiptItem(final ReceiptItem receiptItem,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfReceiptItem.handle(receiptItem);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteReceiptItemById(final long itemId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteReceiptItemById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, itemId);
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
          __preparedStmtOfDeleteReceiptItemById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteReceiptItemsByReceiptId(final long receiptId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteReceiptItemsByReceiptId.acquire();
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
          __preparedStmtOfDeleteReceiptItemsByReceiptId.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ReceiptItem>> getAllReceiptItems() {
    final String _sql = "SELECT * FROM receipt_items ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"receipt_items"}, new Callable<List<ReceiptItem>>() {
      @Override
      @NonNull
      public List<ReceiptItem> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfReceiptId = CursorUtil.getColumnIndexOrThrow(_cursor, "receiptId");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final int _cursorIndexOfProductName = CursorUtil.getColumnIndexOrThrow(_cursor, "productName");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfUnitPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "unitPrice");
          final int _cursorIndexOfTotalPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "totalPrice");
          final int _cursorIndexOfUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "unit");
          final int _cursorIndexOfProductSize = CursorUtil.getColumnIndexOrThrow(_cursor, "productSize");
          final int _cursorIndexOfDiscount = CursorUtil.getColumnIndexOrThrow(_cursor, "discount");
          final int _cursorIndexOfHasDeposit = CursorUtil.getColumnIndexOrThrow(_cursor, "hasDeposit");
          final int _cursorIndexOfDepositAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "depositAmount");
          final int _cursorIndexOfStoreName = CursorUtil.getColumnIndexOrThrow(_cursor, "storeName");
          final int _cursorIndexOfStoreChain = CursorUtil.getColumnIndexOrThrow(_cursor, "storeChain");
          final int _cursorIndexOfStoreCity = CursorUtil.getColumnIndexOrThrow(_cursor, "storeCity");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<ReceiptItem> _result = new ArrayList<ReceiptItem>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ReceiptItem _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpReceiptId;
            _tmpReceiptId = _cursor.getLong(_cursorIndexOfReceiptId);
            final Long _tmpProductId;
            if (_cursor.isNull(_cursorIndexOfProductId)) {
              _tmpProductId = null;
            } else {
              _tmpProductId = _cursor.getLong(_cursorIndexOfProductId);
            }
            final String _tmpProductName;
            if (_cursor.isNull(_cursorIndexOfProductName)) {
              _tmpProductName = null;
            } else {
              _tmpProductName = _cursor.getString(_cursorIndexOfProductName);
            }
            final double _tmpQuantity;
            _tmpQuantity = _cursor.getDouble(_cursorIndexOfQuantity);
            final double _tmpUnitPrice;
            _tmpUnitPrice = _cursor.getDouble(_cursorIndexOfUnitPrice);
            final double _tmpTotalPrice;
            _tmpTotalPrice = _cursor.getDouble(_cursorIndexOfTotalPrice);
            final String _tmpUnit;
            if (_cursor.isNull(_cursorIndexOfUnit)) {
              _tmpUnit = null;
            } else {
              _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
            }
            final String _tmpProductSize;
            if (_cursor.isNull(_cursorIndexOfProductSize)) {
              _tmpProductSize = null;
            } else {
              _tmpProductSize = _cursor.getString(_cursorIndexOfProductSize);
            }
            final double _tmpDiscount;
            _tmpDiscount = _cursor.getDouble(_cursorIndexOfDiscount);
            final boolean _tmpHasDeposit;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHasDeposit);
            _tmpHasDeposit = _tmp != 0;
            final double _tmpDepositAmount;
            _tmpDepositAmount = _cursor.getDouble(_cursorIndexOfDepositAmount);
            final String _tmpStoreName;
            if (_cursor.isNull(_cursorIndexOfStoreName)) {
              _tmpStoreName = null;
            } else {
              _tmpStoreName = _cursor.getString(_cursorIndexOfStoreName);
            }
            final String _tmpStoreChain;
            if (_cursor.isNull(_cursorIndexOfStoreChain)) {
              _tmpStoreChain = null;
            } else {
              _tmpStoreChain = _cursor.getString(_cursorIndexOfStoreChain);
            }
            final String _tmpStoreCity;
            if (_cursor.isNull(_cursorIndexOfStoreCity)) {
              _tmpStoreCity = null;
            } else {
              _tmpStoreCity = _cursor.getString(_cursorIndexOfStoreCity);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new ReceiptItem(_tmpId,_tmpReceiptId,_tmpProductId,_tmpProductName,_tmpQuantity,_tmpUnitPrice,_tmpTotalPrice,_tmpUnit,_tmpProductSize,_tmpDiscount,_tmpHasDeposit,_tmpDepositAmount,_tmpStoreName,_tmpStoreChain,_tmpStoreCity,_tmpCreatedAt);
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
  public Object getReceiptItemById(final long itemId,
      final Continuation<? super ReceiptItem> $completion) {
    final String _sql = "SELECT * FROM receipt_items WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, itemId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ReceiptItem>() {
      @Override
      @Nullable
      public ReceiptItem call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfReceiptId = CursorUtil.getColumnIndexOrThrow(_cursor, "receiptId");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final int _cursorIndexOfProductName = CursorUtil.getColumnIndexOrThrow(_cursor, "productName");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfUnitPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "unitPrice");
          final int _cursorIndexOfTotalPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "totalPrice");
          final int _cursorIndexOfUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "unit");
          final int _cursorIndexOfProductSize = CursorUtil.getColumnIndexOrThrow(_cursor, "productSize");
          final int _cursorIndexOfDiscount = CursorUtil.getColumnIndexOrThrow(_cursor, "discount");
          final int _cursorIndexOfHasDeposit = CursorUtil.getColumnIndexOrThrow(_cursor, "hasDeposit");
          final int _cursorIndexOfDepositAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "depositAmount");
          final int _cursorIndexOfStoreName = CursorUtil.getColumnIndexOrThrow(_cursor, "storeName");
          final int _cursorIndexOfStoreChain = CursorUtil.getColumnIndexOrThrow(_cursor, "storeChain");
          final int _cursorIndexOfStoreCity = CursorUtil.getColumnIndexOrThrow(_cursor, "storeCity");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final ReceiptItem _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpReceiptId;
            _tmpReceiptId = _cursor.getLong(_cursorIndexOfReceiptId);
            final Long _tmpProductId;
            if (_cursor.isNull(_cursorIndexOfProductId)) {
              _tmpProductId = null;
            } else {
              _tmpProductId = _cursor.getLong(_cursorIndexOfProductId);
            }
            final String _tmpProductName;
            if (_cursor.isNull(_cursorIndexOfProductName)) {
              _tmpProductName = null;
            } else {
              _tmpProductName = _cursor.getString(_cursorIndexOfProductName);
            }
            final double _tmpQuantity;
            _tmpQuantity = _cursor.getDouble(_cursorIndexOfQuantity);
            final double _tmpUnitPrice;
            _tmpUnitPrice = _cursor.getDouble(_cursorIndexOfUnitPrice);
            final double _tmpTotalPrice;
            _tmpTotalPrice = _cursor.getDouble(_cursorIndexOfTotalPrice);
            final String _tmpUnit;
            if (_cursor.isNull(_cursorIndexOfUnit)) {
              _tmpUnit = null;
            } else {
              _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
            }
            final String _tmpProductSize;
            if (_cursor.isNull(_cursorIndexOfProductSize)) {
              _tmpProductSize = null;
            } else {
              _tmpProductSize = _cursor.getString(_cursorIndexOfProductSize);
            }
            final double _tmpDiscount;
            _tmpDiscount = _cursor.getDouble(_cursorIndexOfDiscount);
            final boolean _tmpHasDeposit;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHasDeposit);
            _tmpHasDeposit = _tmp != 0;
            final double _tmpDepositAmount;
            _tmpDepositAmount = _cursor.getDouble(_cursorIndexOfDepositAmount);
            final String _tmpStoreName;
            if (_cursor.isNull(_cursorIndexOfStoreName)) {
              _tmpStoreName = null;
            } else {
              _tmpStoreName = _cursor.getString(_cursorIndexOfStoreName);
            }
            final String _tmpStoreChain;
            if (_cursor.isNull(_cursorIndexOfStoreChain)) {
              _tmpStoreChain = null;
            } else {
              _tmpStoreChain = _cursor.getString(_cursorIndexOfStoreChain);
            }
            final String _tmpStoreCity;
            if (_cursor.isNull(_cursorIndexOfStoreCity)) {
              _tmpStoreCity = null;
            } else {
              _tmpStoreCity = _cursor.getString(_cursorIndexOfStoreCity);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new ReceiptItem(_tmpId,_tmpReceiptId,_tmpProductId,_tmpProductName,_tmpQuantity,_tmpUnitPrice,_tmpTotalPrice,_tmpUnit,_tmpProductSize,_tmpDiscount,_tmpHasDeposit,_tmpDepositAmount,_tmpStoreName,_tmpStoreChain,_tmpStoreCity,_tmpCreatedAt);
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
  public Object getReceiptItemsByReceiptId(final long receiptId,
      final Continuation<? super List<ReceiptItem>> $completion) {
    final String _sql = "SELECT * FROM receipt_items WHERE receiptId = ? ORDER BY createdAt ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, receiptId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ReceiptItem>>() {
      @Override
      @NonNull
      public List<ReceiptItem> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfReceiptId = CursorUtil.getColumnIndexOrThrow(_cursor, "receiptId");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final int _cursorIndexOfProductName = CursorUtil.getColumnIndexOrThrow(_cursor, "productName");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfUnitPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "unitPrice");
          final int _cursorIndexOfTotalPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "totalPrice");
          final int _cursorIndexOfUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "unit");
          final int _cursorIndexOfProductSize = CursorUtil.getColumnIndexOrThrow(_cursor, "productSize");
          final int _cursorIndexOfDiscount = CursorUtil.getColumnIndexOrThrow(_cursor, "discount");
          final int _cursorIndexOfHasDeposit = CursorUtil.getColumnIndexOrThrow(_cursor, "hasDeposit");
          final int _cursorIndexOfDepositAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "depositAmount");
          final int _cursorIndexOfStoreName = CursorUtil.getColumnIndexOrThrow(_cursor, "storeName");
          final int _cursorIndexOfStoreChain = CursorUtil.getColumnIndexOrThrow(_cursor, "storeChain");
          final int _cursorIndexOfStoreCity = CursorUtil.getColumnIndexOrThrow(_cursor, "storeCity");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<ReceiptItem> _result = new ArrayList<ReceiptItem>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ReceiptItem _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpReceiptId;
            _tmpReceiptId = _cursor.getLong(_cursorIndexOfReceiptId);
            final Long _tmpProductId;
            if (_cursor.isNull(_cursorIndexOfProductId)) {
              _tmpProductId = null;
            } else {
              _tmpProductId = _cursor.getLong(_cursorIndexOfProductId);
            }
            final String _tmpProductName;
            if (_cursor.isNull(_cursorIndexOfProductName)) {
              _tmpProductName = null;
            } else {
              _tmpProductName = _cursor.getString(_cursorIndexOfProductName);
            }
            final double _tmpQuantity;
            _tmpQuantity = _cursor.getDouble(_cursorIndexOfQuantity);
            final double _tmpUnitPrice;
            _tmpUnitPrice = _cursor.getDouble(_cursorIndexOfUnitPrice);
            final double _tmpTotalPrice;
            _tmpTotalPrice = _cursor.getDouble(_cursorIndexOfTotalPrice);
            final String _tmpUnit;
            if (_cursor.isNull(_cursorIndexOfUnit)) {
              _tmpUnit = null;
            } else {
              _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
            }
            final String _tmpProductSize;
            if (_cursor.isNull(_cursorIndexOfProductSize)) {
              _tmpProductSize = null;
            } else {
              _tmpProductSize = _cursor.getString(_cursorIndexOfProductSize);
            }
            final double _tmpDiscount;
            _tmpDiscount = _cursor.getDouble(_cursorIndexOfDiscount);
            final boolean _tmpHasDeposit;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHasDeposit);
            _tmpHasDeposit = _tmp != 0;
            final double _tmpDepositAmount;
            _tmpDepositAmount = _cursor.getDouble(_cursorIndexOfDepositAmount);
            final String _tmpStoreName;
            if (_cursor.isNull(_cursorIndexOfStoreName)) {
              _tmpStoreName = null;
            } else {
              _tmpStoreName = _cursor.getString(_cursorIndexOfStoreName);
            }
            final String _tmpStoreChain;
            if (_cursor.isNull(_cursorIndexOfStoreChain)) {
              _tmpStoreChain = null;
            } else {
              _tmpStoreChain = _cursor.getString(_cursorIndexOfStoreChain);
            }
            final String _tmpStoreCity;
            if (_cursor.isNull(_cursorIndexOfStoreCity)) {
              _tmpStoreCity = null;
            } else {
              _tmpStoreCity = _cursor.getString(_cursorIndexOfStoreCity);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new ReceiptItem(_tmpId,_tmpReceiptId,_tmpProductId,_tmpProductName,_tmpQuantity,_tmpUnitPrice,_tmpTotalPrice,_tmpUnit,_tmpProductSize,_tmpDiscount,_tmpHasDeposit,_tmpDepositAmount,_tmpStoreName,_tmpStoreChain,_tmpStoreCity,_tmpCreatedAt);
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
  public Flow<List<ReceiptItem>> getReceiptItemsByReceiptIdFlow(final long receiptId) {
    final String _sql = "SELECT * FROM receipt_items WHERE receiptId = ? ORDER BY createdAt ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, receiptId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"receipt_items"}, new Callable<List<ReceiptItem>>() {
      @Override
      @NonNull
      public List<ReceiptItem> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfReceiptId = CursorUtil.getColumnIndexOrThrow(_cursor, "receiptId");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final int _cursorIndexOfProductName = CursorUtil.getColumnIndexOrThrow(_cursor, "productName");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfUnitPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "unitPrice");
          final int _cursorIndexOfTotalPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "totalPrice");
          final int _cursorIndexOfUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "unit");
          final int _cursorIndexOfProductSize = CursorUtil.getColumnIndexOrThrow(_cursor, "productSize");
          final int _cursorIndexOfDiscount = CursorUtil.getColumnIndexOrThrow(_cursor, "discount");
          final int _cursorIndexOfHasDeposit = CursorUtil.getColumnIndexOrThrow(_cursor, "hasDeposit");
          final int _cursorIndexOfDepositAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "depositAmount");
          final int _cursorIndexOfStoreName = CursorUtil.getColumnIndexOrThrow(_cursor, "storeName");
          final int _cursorIndexOfStoreChain = CursorUtil.getColumnIndexOrThrow(_cursor, "storeChain");
          final int _cursorIndexOfStoreCity = CursorUtil.getColumnIndexOrThrow(_cursor, "storeCity");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<ReceiptItem> _result = new ArrayList<ReceiptItem>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ReceiptItem _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpReceiptId;
            _tmpReceiptId = _cursor.getLong(_cursorIndexOfReceiptId);
            final Long _tmpProductId;
            if (_cursor.isNull(_cursorIndexOfProductId)) {
              _tmpProductId = null;
            } else {
              _tmpProductId = _cursor.getLong(_cursorIndexOfProductId);
            }
            final String _tmpProductName;
            if (_cursor.isNull(_cursorIndexOfProductName)) {
              _tmpProductName = null;
            } else {
              _tmpProductName = _cursor.getString(_cursorIndexOfProductName);
            }
            final double _tmpQuantity;
            _tmpQuantity = _cursor.getDouble(_cursorIndexOfQuantity);
            final double _tmpUnitPrice;
            _tmpUnitPrice = _cursor.getDouble(_cursorIndexOfUnitPrice);
            final double _tmpTotalPrice;
            _tmpTotalPrice = _cursor.getDouble(_cursorIndexOfTotalPrice);
            final String _tmpUnit;
            if (_cursor.isNull(_cursorIndexOfUnit)) {
              _tmpUnit = null;
            } else {
              _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
            }
            final String _tmpProductSize;
            if (_cursor.isNull(_cursorIndexOfProductSize)) {
              _tmpProductSize = null;
            } else {
              _tmpProductSize = _cursor.getString(_cursorIndexOfProductSize);
            }
            final double _tmpDiscount;
            _tmpDiscount = _cursor.getDouble(_cursorIndexOfDiscount);
            final boolean _tmpHasDeposit;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHasDeposit);
            _tmpHasDeposit = _tmp != 0;
            final double _tmpDepositAmount;
            _tmpDepositAmount = _cursor.getDouble(_cursorIndexOfDepositAmount);
            final String _tmpStoreName;
            if (_cursor.isNull(_cursorIndexOfStoreName)) {
              _tmpStoreName = null;
            } else {
              _tmpStoreName = _cursor.getString(_cursorIndexOfStoreName);
            }
            final String _tmpStoreChain;
            if (_cursor.isNull(_cursorIndexOfStoreChain)) {
              _tmpStoreChain = null;
            } else {
              _tmpStoreChain = _cursor.getString(_cursorIndexOfStoreChain);
            }
            final String _tmpStoreCity;
            if (_cursor.isNull(_cursorIndexOfStoreCity)) {
              _tmpStoreCity = null;
            } else {
              _tmpStoreCity = _cursor.getString(_cursorIndexOfStoreCity);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new ReceiptItem(_tmpId,_tmpReceiptId,_tmpProductId,_tmpProductName,_tmpQuantity,_tmpUnitPrice,_tmpTotalPrice,_tmpUnit,_tmpProductSize,_tmpDiscount,_tmpHasDeposit,_tmpDepositAmount,_tmpStoreName,_tmpStoreChain,_tmpStoreCity,_tmpCreatedAt);
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
  public Flow<List<ReceiptItem>> getReceiptItemsByProductId(final long productId) {
    final String _sql = "SELECT * FROM receipt_items WHERE productId = ? ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, productId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"receipt_items"}, new Callable<List<ReceiptItem>>() {
      @Override
      @NonNull
      public List<ReceiptItem> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfReceiptId = CursorUtil.getColumnIndexOrThrow(_cursor, "receiptId");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final int _cursorIndexOfProductName = CursorUtil.getColumnIndexOrThrow(_cursor, "productName");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfUnitPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "unitPrice");
          final int _cursorIndexOfTotalPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "totalPrice");
          final int _cursorIndexOfUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "unit");
          final int _cursorIndexOfProductSize = CursorUtil.getColumnIndexOrThrow(_cursor, "productSize");
          final int _cursorIndexOfDiscount = CursorUtil.getColumnIndexOrThrow(_cursor, "discount");
          final int _cursorIndexOfHasDeposit = CursorUtil.getColumnIndexOrThrow(_cursor, "hasDeposit");
          final int _cursorIndexOfDepositAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "depositAmount");
          final int _cursorIndexOfStoreName = CursorUtil.getColumnIndexOrThrow(_cursor, "storeName");
          final int _cursorIndexOfStoreChain = CursorUtil.getColumnIndexOrThrow(_cursor, "storeChain");
          final int _cursorIndexOfStoreCity = CursorUtil.getColumnIndexOrThrow(_cursor, "storeCity");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<ReceiptItem> _result = new ArrayList<ReceiptItem>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ReceiptItem _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpReceiptId;
            _tmpReceiptId = _cursor.getLong(_cursorIndexOfReceiptId);
            final Long _tmpProductId;
            if (_cursor.isNull(_cursorIndexOfProductId)) {
              _tmpProductId = null;
            } else {
              _tmpProductId = _cursor.getLong(_cursorIndexOfProductId);
            }
            final String _tmpProductName;
            if (_cursor.isNull(_cursorIndexOfProductName)) {
              _tmpProductName = null;
            } else {
              _tmpProductName = _cursor.getString(_cursorIndexOfProductName);
            }
            final double _tmpQuantity;
            _tmpQuantity = _cursor.getDouble(_cursorIndexOfQuantity);
            final double _tmpUnitPrice;
            _tmpUnitPrice = _cursor.getDouble(_cursorIndexOfUnitPrice);
            final double _tmpTotalPrice;
            _tmpTotalPrice = _cursor.getDouble(_cursorIndexOfTotalPrice);
            final String _tmpUnit;
            if (_cursor.isNull(_cursorIndexOfUnit)) {
              _tmpUnit = null;
            } else {
              _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
            }
            final String _tmpProductSize;
            if (_cursor.isNull(_cursorIndexOfProductSize)) {
              _tmpProductSize = null;
            } else {
              _tmpProductSize = _cursor.getString(_cursorIndexOfProductSize);
            }
            final double _tmpDiscount;
            _tmpDiscount = _cursor.getDouble(_cursorIndexOfDiscount);
            final boolean _tmpHasDeposit;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHasDeposit);
            _tmpHasDeposit = _tmp != 0;
            final double _tmpDepositAmount;
            _tmpDepositAmount = _cursor.getDouble(_cursorIndexOfDepositAmount);
            final String _tmpStoreName;
            if (_cursor.isNull(_cursorIndexOfStoreName)) {
              _tmpStoreName = null;
            } else {
              _tmpStoreName = _cursor.getString(_cursorIndexOfStoreName);
            }
            final String _tmpStoreChain;
            if (_cursor.isNull(_cursorIndexOfStoreChain)) {
              _tmpStoreChain = null;
            } else {
              _tmpStoreChain = _cursor.getString(_cursorIndexOfStoreChain);
            }
            final String _tmpStoreCity;
            if (_cursor.isNull(_cursorIndexOfStoreCity)) {
              _tmpStoreCity = null;
            } else {
              _tmpStoreCity = _cursor.getString(_cursorIndexOfStoreCity);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new ReceiptItem(_tmpId,_tmpReceiptId,_tmpProductId,_tmpProductName,_tmpQuantity,_tmpUnitPrice,_tmpTotalPrice,_tmpUnit,_tmpProductSize,_tmpDiscount,_tmpHasDeposit,_tmpDepositAmount,_tmpStoreName,_tmpStoreChain,_tmpStoreCity,_tmpCreatedAt);
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
