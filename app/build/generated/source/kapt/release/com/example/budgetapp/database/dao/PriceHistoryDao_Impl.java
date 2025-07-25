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
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.budgetapp.database.entities.PriceHistory;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
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
public final class PriceHistoryDao_Impl implements PriceHistoryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PriceHistory> __insertionAdapterOfPriceHistory;

  private final EntityDeletionOrUpdateAdapter<PriceHistory> __deletionAdapterOfPriceHistory;

  private final EntityDeletionOrUpdateAdapter<PriceHistory> __updateAdapterOfPriceHistory;

  private final SharedSQLiteStatement __preparedStmtOfDeletePriceHistoryById;

  private final SharedSQLiteStatement __preparedStmtOfDeletePriceHistoryForProduct;

  private final SharedSQLiteStatement __preparedStmtOfDeletePriceHistoryForStore;

  private final SharedSQLiteStatement __preparedStmtOfDeleteOldPriceHistory;

  public PriceHistoryDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPriceHistory = new EntityInsertionAdapter<PriceHistory>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `price_history` (`id`,`productId`,`storeId`,`price`,`campaignPrice`,`campaignQuantity`,`hasCampaignPrice`,`recordedAt`,`source`,`notes`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PriceHistory entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getProductId());
        statement.bindLong(3, entity.getStoreId());
        statement.bindDouble(4, entity.getPrice());
        if (entity.getCampaignPrice() == null) {
          statement.bindNull(5);
        } else {
          statement.bindDouble(5, entity.getCampaignPrice());
        }
        if (entity.getCampaignQuantity() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getCampaignQuantity());
        }
        final int _tmp = entity.getHasCampaignPrice() ? 1 : 0;
        statement.bindLong(7, _tmp);
        statement.bindLong(8, entity.getRecordedAt());
        if (entity.getSource() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getSource());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getNotes());
        }
      }
    };
    this.__deletionAdapterOfPriceHistory = new EntityDeletionOrUpdateAdapter<PriceHistory>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `price_history` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PriceHistory entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfPriceHistory = new EntityDeletionOrUpdateAdapter<PriceHistory>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `price_history` SET `id` = ?,`productId` = ?,`storeId` = ?,`price` = ?,`campaignPrice` = ?,`campaignQuantity` = ?,`hasCampaignPrice` = ?,`recordedAt` = ?,`source` = ?,`notes` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PriceHistory entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getProductId());
        statement.bindLong(3, entity.getStoreId());
        statement.bindDouble(4, entity.getPrice());
        if (entity.getCampaignPrice() == null) {
          statement.bindNull(5);
        } else {
          statement.bindDouble(5, entity.getCampaignPrice());
        }
        if (entity.getCampaignQuantity() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getCampaignQuantity());
        }
        final int _tmp = entity.getHasCampaignPrice() ? 1 : 0;
        statement.bindLong(7, _tmp);
        statement.bindLong(8, entity.getRecordedAt());
        if (entity.getSource() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getSource());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getNotes());
        }
        statement.bindLong(11, entity.getId());
      }
    };
    this.__preparedStmtOfDeletePriceHistoryById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM price_history WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeletePriceHistoryForProduct = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM price_history WHERE productId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeletePriceHistoryForStore = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM price_history WHERE storeId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteOldPriceHistory = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM price_history WHERE recordedAt < ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertPriceHistory(final PriceHistory priceHistory,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfPriceHistory.insertAndReturnId(priceHistory);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertPriceHistoryList(final List<PriceHistory> priceHistories,
      final Continuation<? super List<Long>> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<List<Long>>() {
      @Override
      @NonNull
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          final List<Long> _result = __insertionAdapterOfPriceHistory.insertAndReturnIdsList(priceHistories);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deletePriceHistory(final PriceHistory priceHistory,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfPriceHistory.handle(priceHistory);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updatePriceHistory(final PriceHistory priceHistory,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfPriceHistory.handle(priceHistory);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deletePriceHistoryById(final long id,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeletePriceHistoryById.acquire();
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
          __preparedStmtOfDeletePriceHistoryById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deletePriceHistoryForProduct(final long productId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeletePriceHistoryForProduct.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, productId);
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
          __preparedStmtOfDeletePriceHistoryForProduct.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deletePriceHistoryForStore(final long storeId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeletePriceHistoryForStore.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, storeId);
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
          __preparedStmtOfDeletePriceHistoryForStore.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteOldPriceHistory(final long cutoffDate,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteOldPriceHistory.acquire();
        int _argIndex = 1;
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
          __preparedStmtOfDeleteOldPriceHistory.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<PriceHistory>> getPriceHistoryForProduct(final long productId) {
    final String _sql = "SELECT * FROM price_history WHERE productId = ? ORDER BY recordedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, productId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"price_history"}, false, new Callable<List<PriceHistory>>() {
      @Override
      @Nullable
      public List<PriceHistory> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final int _cursorIndexOfStoreId = CursorUtil.getColumnIndexOrThrow(_cursor, "storeId");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfCampaignPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "campaignPrice");
          final int _cursorIndexOfCampaignQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "campaignQuantity");
          final int _cursorIndexOfHasCampaignPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "hasCampaignPrice");
          final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "recordedAt");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final List<PriceHistory> _result = new ArrayList<PriceHistory>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PriceHistory _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpProductId;
            _tmpProductId = _cursor.getLong(_cursorIndexOfProductId);
            final long _tmpStoreId;
            _tmpStoreId = _cursor.getLong(_cursorIndexOfStoreId);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final Double _tmpCampaignPrice;
            if (_cursor.isNull(_cursorIndexOfCampaignPrice)) {
              _tmpCampaignPrice = null;
            } else {
              _tmpCampaignPrice = _cursor.getDouble(_cursorIndexOfCampaignPrice);
            }
            final Integer _tmpCampaignQuantity;
            if (_cursor.isNull(_cursorIndexOfCampaignQuantity)) {
              _tmpCampaignQuantity = null;
            } else {
              _tmpCampaignQuantity = _cursor.getInt(_cursorIndexOfCampaignQuantity);
            }
            final boolean _tmpHasCampaignPrice;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHasCampaignPrice);
            _tmpHasCampaignPrice = _tmp != 0;
            final long _tmpRecordedAt;
            _tmpRecordedAt = _cursor.getLong(_cursorIndexOfRecordedAt);
            final String _tmpSource;
            if (_cursor.isNull(_cursorIndexOfSource)) {
              _tmpSource = null;
            } else {
              _tmpSource = _cursor.getString(_cursorIndexOfSource);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _item = new PriceHistory(_tmpId,_tmpProductId,_tmpStoreId,_tmpPrice,_tmpCampaignPrice,_tmpCampaignQuantity,_tmpHasCampaignPrice,_tmpRecordedAt,_tmpSource,_tmpNotes);
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
  public LiveData<List<PriceHistory>> getPriceHistoryForProductAtStore(final long productId,
      final long storeId) {
    final String _sql = "SELECT * FROM price_history WHERE productId = ? AND storeId = ? ORDER BY recordedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, productId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, storeId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"price_history"}, false, new Callable<List<PriceHistory>>() {
      @Override
      @Nullable
      public List<PriceHistory> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final int _cursorIndexOfStoreId = CursorUtil.getColumnIndexOrThrow(_cursor, "storeId");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfCampaignPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "campaignPrice");
          final int _cursorIndexOfCampaignQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "campaignQuantity");
          final int _cursorIndexOfHasCampaignPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "hasCampaignPrice");
          final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "recordedAt");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final List<PriceHistory> _result = new ArrayList<PriceHistory>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PriceHistory _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpProductId;
            _tmpProductId = _cursor.getLong(_cursorIndexOfProductId);
            final long _tmpStoreId;
            _tmpStoreId = _cursor.getLong(_cursorIndexOfStoreId);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final Double _tmpCampaignPrice;
            if (_cursor.isNull(_cursorIndexOfCampaignPrice)) {
              _tmpCampaignPrice = null;
            } else {
              _tmpCampaignPrice = _cursor.getDouble(_cursorIndexOfCampaignPrice);
            }
            final Integer _tmpCampaignQuantity;
            if (_cursor.isNull(_cursorIndexOfCampaignQuantity)) {
              _tmpCampaignQuantity = null;
            } else {
              _tmpCampaignQuantity = _cursor.getInt(_cursorIndexOfCampaignQuantity);
            }
            final boolean _tmpHasCampaignPrice;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHasCampaignPrice);
            _tmpHasCampaignPrice = _tmp != 0;
            final long _tmpRecordedAt;
            _tmpRecordedAt = _cursor.getLong(_cursorIndexOfRecordedAt);
            final String _tmpSource;
            if (_cursor.isNull(_cursorIndexOfSource)) {
              _tmpSource = null;
            } else {
              _tmpSource = _cursor.getString(_cursorIndexOfSource);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _item = new PriceHistory(_tmpId,_tmpProductId,_tmpStoreId,_tmpPrice,_tmpCampaignPrice,_tmpCampaignQuantity,_tmpHasCampaignPrice,_tmpRecordedAt,_tmpSource,_tmpNotes);
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
  public LiveData<List<PriceHistory>> getPriceHistoryForStore(final long storeId) {
    final String _sql = "SELECT * FROM price_history WHERE storeId = ? ORDER BY recordedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, storeId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"price_history"}, false, new Callable<List<PriceHistory>>() {
      @Override
      @Nullable
      public List<PriceHistory> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final int _cursorIndexOfStoreId = CursorUtil.getColumnIndexOrThrow(_cursor, "storeId");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfCampaignPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "campaignPrice");
          final int _cursorIndexOfCampaignQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "campaignQuantity");
          final int _cursorIndexOfHasCampaignPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "hasCampaignPrice");
          final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "recordedAt");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final List<PriceHistory> _result = new ArrayList<PriceHistory>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PriceHistory _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpProductId;
            _tmpProductId = _cursor.getLong(_cursorIndexOfProductId);
            final long _tmpStoreId;
            _tmpStoreId = _cursor.getLong(_cursorIndexOfStoreId);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final Double _tmpCampaignPrice;
            if (_cursor.isNull(_cursorIndexOfCampaignPrice)) {
              _tmpCampaignPrice = null;
            } else {
              _tmpCampaignPrice = _cursor.getDouble(_cursorIndexOfCampaignPrice);
            }
            final Integer _tmpCampaignQuantity;
            if (_cursor.isNull(_cursorIndexOfCampaignQuantity)) {
              _tmpCampaignQuantity = null;
            } else {
              _tmpCampaignQuantity = _cursor.getInt(_cursorIndexOfCampaignQuantity);
            }
            final boolean _tmpHasCampaignPrice;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHasCampaignPrice);
            _tmpHasCampaignPrice = _tmp != 0;
            final long _tmpRecordedAt;
            _tmpRecordedAt = _cursor.getLong(_cursorIndexOfRecordedAt);
            final String _tmpSource;
            if (_cursor.isNull(_cursorIndexOfSource)) {
              _tmpSource = null;
            } else {
              _tmpSource = _cursor.getString(_cursorIndexOfSource);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _item = new PriceHistory(_tmpId,_tmpProductId,_tmpStoreId,_tmpPrice,_tmpCampaignPrice,_tmpCampaignQuantity,_tmpHasCampaignPrice,_tmpRecordedAt,_tmpSource,_tmpNotes);
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
  public LiveData<List<PriceHistory>> getPriceHistoryForProductInDateRange(final long productId,
      final long startDate, final long endDate) {
    final String _sql = "SELECT * FROM price_history WHERE productId = ? AND recordedAt BETWEEN ? AND ? ORDER BY recordedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, productId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 3;
    _statement.bindLong(_argIndex, endDate);
    return __db.getInvalidationTracker().createLiveData(new String[] {"price_history"}, false, new Callable<List<PriceHistory>>() {
      @Override
      @Nullable
      public List<PriceHistory> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final int _cursorIndexOfStoreId = CursorUtil.getColumnIndexOrThrow(_cursor, "storeId");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfCampaignPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "campaignPrice");
          final int _cursorIndexOfCampaignQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "campaignQuantity");
          final int _cursorIndexOfHasCampaignPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "hasCampaignPrice");
          final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "recordedAt");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final List<PriceHistory> _result = new ArrayList<PriceHistory>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PriceHistory _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpProductId;
            _tmpProductId = _cursor.getLong(_cursorIndexOfProductId);
            final long _tmpStoreId;
            _tmpStoreId = _cursor.getLong(_cursorIndexOfStoreId);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final Double _tmpCampaignPrice;
            if (_cursor.isNull(_cursorIndexOfCampaignPrice)) {
              _tmpCampaignPrice = null;
            } else {
              _tmpCampaignPrice = _cursor.getDouble(_cursorIndexOfCampaignPrice);
            }
            final Integer _tmpCampaignQuantity;
            if (_cursor.isNull(_cursorIndexOfCampaignQuantity)) {
              _tmpCampaignQuantity = null;
            } else {
              _tmpCampaignQuantity = _cursor.getInt(_cursorIndexOfCampaignQuantity);
            }
            final boolean _tmpHasCampaignPrice;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHasCampaignPrice);
            _tmpHasCampaignPrice = _tmp != 0;
            final long _tmpRecordedAt;
            _tmpRecordedAt = _cursor.getLong(_cursorIndexOfRecordedAt);
            final String _tmpSource;
            if (_cursor.isNull(_cursorIndexOfSource)) {
              _tmpSource = null;
            } else {
              _tmpSource = _cursor.getString(_cursorIndexOfSource);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _item = new PriceHistory(_tmpId,_tmpProductId,_tmpStoreId,_tmpPrice,_tmpCampaignPrice,_tmpCampaignQuantity,_tmpHasCampaignPrice,_tmpRecordedAt,_tmpSource,_tmpNotes);
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
  public LiveData<List<PriceHistory>> getPriceHistoryForProductAtStoreInDateRange(
      final long productId, final long storeId, final long startDate, final long endDate) {
    final String _sql = "SELECT * FROM price_history WHERE productId = ? AND storeId = ? AND recordedAt BETWEEN ? AND ? ORDER BY recordedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 4);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, productId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, storeId);
    _argIndex = 3;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 4;
    _statement.bindLong(_argIndex, endDate);
    return __db.getInvalidationTracker().createLiveData(new String[] {"price_history"}, false, new Callable<List<PriceHistory>>() {
      @Override
      @Nullable
      public List<PriceHistory> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final int _cursorIndexOfStoreId = CursorUtil.getColumnIndexOrThrow(_cursor, "storeId");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfCampaignPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "campaignPrice");
          final int _cursorIndexOfCampaignQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "campaignQuantity");
          final int _cursorIndexOfHasCampaignPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "hasCampaignPrice");
          final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "recordedAt");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final List<PriceHistory> _result = new ArrayList<PriceHistory>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PriceHistory _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpProductId;
            _tmpProductId = _cursor.getLong(_cursorIndexOfProductId);
            final long _tmpStoreId;
            _tmpStoreId = _cursor.getLong(_cursorIndexOfStoreId);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final Double _tmpCampaignPrice;
            if (_cursor.isNull(_cursorIndexOfCampaignPrice)) {
              _tmpCampaignPrice = null;
            } else {
              _tmpCampaignPrice = _cursor.getDouble(_cursorIndexOfCampaignPrice);
            }
            final Integer _tmpCampaignQuantity;
            if (_cursor.isNull(_cursorIndexOfCampaignQuantity)) {
              _tmpCampaignQuantity = null;
            } else {
              _tmpCampaignQuantity = _cursor.getInt(_cursorIndexOfCampaignQuantity);
            }
            final boolean _tmpHasCampaignPrice;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHasCampaignPrice);
            _tmpHasCampaignPrice = _tmp != 0;
            final long _tmpRecordedAt;
            _tmpRecordedAt = _cursor.getLong(_cursorIndexOfRecordedAt);
            final String _tmpSource;
            if (_cursor.isNull(_cursorIndexOfSource)) {
              _tmpSource = null;
            } else {
              _tmpSource = _cursor.getString(_cursorIndexOfSource);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _item = new PriceHistory(_tmpId,_tmpProductId,_tmpStoreId,_tmpPrice,_tmpCampaignPrice,_tmpCampaignQuantity,_tmpHasCampaignPrice,_tmpRecordedAt,_tmpSource,_tmpNotes);
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
  public LiveData<List<PriceHistory>> getRecentPriceHistory(final long date) {
    final String _sql = "SELECT * FROM price_history WHERE recordedAt >= ? ORDER BY recordedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, date);
    return __db.getInvalidationTracker().createLiveData(new String[] {"price_history"}, false, new Callable<List<PriceHistory>>() {
      @Override
      @Nullable
      public List<PriceHistory> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final int _cursorIndexOfStoreId = CursorUtil.getColumnIndexOrThrow(_cursor, "storeId");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfCampaignPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "campaignPrice");
          final int _cursorIndexOfCampaignQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "campaignQuantity");
          final int _cursorIndexOfHasCampaignPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "hasCampaignPrice");
          final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "recordedAt");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final List<PriceHistory> _result = new ArrayList<PriceHistory>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PriceHistory _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpProductId;
            _tmpProductId = _cursor.getLong(_cursorIndexOfProductId);
            final long _tmpStoreId;
            _tmpStoreId = _cursor.getLong(_cursorIndexOfStoreId);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final Double _tmpCampaignPrice;
            if (_cursor.isNull(_cursorIndexOfCampaignPrice)) {
              _tmpCampaignPrice = null;
            } else {
              _tmpCampaignPrice = _cursor.getDouble(_cursorIndexOfCampaignPrice);
            }
            final Integer _tmpCampaignQuantity;
            if (_cursor.isNull(_cursorIndexOfCampaignQuantity)) {
              _tmpCampaignQuantity = null;
            } else {
              _tmpCampaignQuantity = _cursor.getInt(_cursorIndexOfCampaignQuantity);
            }
            final boolean _tmpHasCampaignPrice;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHasCampaignPrice);
            _tmpHasCampaignPrice = _tmp != 0;
            final long _tmpRecordedAt;
            _tmpRecordedAt = _cursor.getLong(_cursorIndexOfRecordedAt);
            final String _tmpSource;
            if (_cursor.isNull(_cursorIndexOfSource)) {
              _tmpSource = null;
            } else {
              _tmpSource = _cursor.getString(_cursorIndexOfSource);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _item = new PriceHistory(_tmpId,_tmpProductId,_tmpStoreId,_tmpPrice,_tmpCampaignPrice,_tmpCampaignQuantity,_tmpHasCampaignPrice,_tmpRecordedAt,_tmpSource,_tmpNotes);
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
  public Object getPriceStatisticsForProduct(final long productId,
      final Continuation<? super PriceHistoryDao.PriceStatistics> $completion) {
    final String _sql = "SELECT MIN(price) as minPrice, MAX(price) as maxPrice, AVG(price) as avgPrice FROM price_history WHERE productId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, productId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<PriceHistoryDao.PriceStatistics>() {
      @Override
      @Nullable
      public PriceHistoryDao.PriceStatistics call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfMinPrice = 0;
          final int _cursorIndexOfMaxPrice = 1;
          final int _cursorIndexOfAvgPrice = 2;
          final PriceHistoryDao.PriceStatistics _result;
          if (_cursor.moveToFirst()) {
            final double _tmpMinPrice;
            _tmpMinPrice = _cursor.getDouble(_cursorIndexOfMinPrice);
            final double _tmpMaxPrice;
            _tmpMaxPrice = _cursor.getDouble(_cursorIndexOfMaxPrice);
            final double _tmpAvgPrice;
            _tmpAvgPrice = _cursor.getDouble(_cursorIndexOfAvgPrice);
            _result = new PriceHistoryDao.PriceStatistics(_tmpMinPrice,_tmpMaxPrice,_tmpAvgPrice);
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
  public Object getPriceStatisticsForProductAtStore(final long productId, final long storeId,
      final Continuation<? super PriceHistoryDao.PriceStatistics> $completion) {
    final String _sql = "SELECT MIN(price) as minPrice, MAX(price) as maxPrice, AVG(price) as avgPrice FROM price_history WHERE productId = ? AND storeId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, productId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, storeId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<PriceHistoryDao.PriceStatistics>() {
      @Override
      @Nullable
      public PriceHistoryDao.PriceStatistics call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfMinPrice = 0;
          final int _cursorIndexOfMaxPrice = 1;
          final int _cursorIndexOfAvgPrice = 2;
          final PriceHistoryDao.PriceStatistics _result;
          if (_cursor.moveToFirst()) {
            final double _tmpMinPrice;
            _tmpMinPrice = _cursor.getDouble(_cursorIndexOfMinPrice);
            final double _tmpMaxPrice;
            _tmpMaxPrice = _cursor.getDouble(_cursorIndexOfMaxPrice);
            final double _tmpAvgPrice;
            _tmpAvgPrice = _cursor.getDouble(_cursorIndexOfAvgPrice);
            _result = new PriceHistoryDao.PriceStatistics(_tmpMinPrice,_tmpMaxPrice,_tmpAvgPrice);
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
  public Object getLatestPriceForProductAtStore(final long productId, final long storeId,
      final Continuation<? super PriceHistory> $completion) {
    final String _sql = "SELECT * FROM price_history WHERE productId = ? AND storeId = ? ORDER BY recordedAt DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, productId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, storeId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<PriceHistory>() {
      @Override
      @Nullable
      public PriceHistory call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final int _cursorIndexOfStoreId = CursorUtil.getColumnIndexOrThrow(_cursor, "storeId");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfCampaignPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "campaignPrice");
          final int _cursorIndexOfCampaignQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "campaignQuantity");
          final int _cursorIndexOfHasCampaignPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "hasCampaignPrice");
          final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "recordedAt");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final PriceHistory _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpProductId;
            _tmpProductId = _cursor.getLong(_cursorIndexOfProductId);
            final long _tmpStoreId;
            _tmpStoreId = _cursor.getLong(_cursorIndexOfStoreId);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final Double _tmpCampaignPrice;
            if (_cursor.isNull(_cursorIndexOfCampaignPrice)) {
              _tmpCampaignPrice = null;
            } else {
              _tmpCampaignPrice = _cursor.getDouble(_cursorIndexOfCampaignPrice);
            }
            final Integer _tmpCampaignQuantity;
            if (_cursor.isNull(_cursorIndexOfCampaignQuantity)) {
              _tmpCampaignQuantity = null;
            } else {
              _tmpCampaignQuantity = _cursor.getInt(_cursorIndexOfCampaignQuantity);
            }
            final boolean _tmpHasCampaignPrice;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHasCampaignPrice);
            _tmpHasCampaignPrice = _tmp != 0;
            final long _tmpRecordedAt;
            _tmpRecordedAt = _cursor.getLong(_cursorIndexOfRecordedAt);
            final String _tmpSource;
            if (_cursor.isNull(_cursorIndexOfSource)) {
              _tmpSource = null;
            } else {
              _tmpSource = _cursor.getString(_cursorIndexOfSource);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _result = new PriceHistory(_tmpId,_tmpProductId,_tmpStoreId,_tmpPrice,_tmpCampaignPrice,_tmpCampaignQuantity,_tmpHasCampaignPrice,_tmpRecordedAt,_tmpSource,_tmpNotes);
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
  public Object getLowestPriceForProduct(final long productId,
      final Continuation<? super PriceHistory> $completion) {
    final String _sql = "SELECT * FROM price_history WHERE productId = ? ORDER BY price ASC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, productId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<PriceHistory>() {
      @Override
      @Nullable
      public PriceHistory call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final int _cursorIndexOfStoreId = CursorUtil.getColumnIndexOrThrow(_cursor, "storeId");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfCampaignPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "campaignPrice");
          final int _cursorIndexOfCampaignQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "campaignQuantity");
          final int _cursorIndexOfHasCampaignPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "hasCampaignPrice");
          final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "recordedAt");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final PriceHistory _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpProductId;
            _tmpProductId = _cursor.getLong(_cursorIndexOfProductId);
            final long _tmpStoreId;
            _tmpStoreId = _cursor.getLong(_cursorIndexOfStoreId);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final Double _tmpCampaignPrice;
            if (_cursor.isNull(_cursorIndexOfCampaignPrice)) {
              _tmpCampaignPrice = null;
            } else {
              _tmpCampaignPrice = _cursor.getDouble(_cursorIndexOfCampaignPrice);
            }
            final Integer _tmpCampaignQuantity;
            if (_cursor.isNull(_cursorIndexOfCampaignQuantity)) {
              _tmpCampaignQuantity = null;
            } else {
              _tmpCampaignQuantity = _cursor.getInt(_cursorIndexOfCampaignQuantity);
            }
            final boolean _tmpHasCampaignPrice;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHasCampaignPrice);
            _tmpHasCampaignPrice = _tmp != 0;
            final long _tmpRecordedAt;
            _tmpRecordedAt = _cursor.getLong(_cursorIndexOfRecordedAt);
            final String _tmpSource;
            if (_cursor.isNull(_cursorIndexOfSource)) {
              _tmpSource = null;
            } else {
              _tmpSource = _cursor.getString(_cursorIndexOfSource);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _result = new PriceHistory(_tmpId,_tmpProductId,_tmpStoreId,_tmpPrice,_tmpCampaignPrice,_tmpCampaignQuantity,_tmpHasCampaignPrice,_tmpRecordedAt,_tmpSource,_tmpNotes);
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
  public Object getHighestPriceForProduct(final long productId,
      final Continuation<? super PriceHistory> $completion) {
    final String _sql = "SELECT * FROM price_history WHERE productId = ? ORDER BY price DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, productId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<PriceHistory>() {
      @Override
      @Nullable
      public PriceHistory call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final int _cursorIndexOfStoreId = CursorUtil.getColumnIndexOrThrow(_cursor, "storeId");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfCampaignPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "campaignPrice");
          final int _cursorIndexOfCampaignQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "campaignQuantity");
          final int _cursorIndexOfHasCampaignPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "hasCampaignPrice");
          final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "recordedAt");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final PriceHistory _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpProductId;
            _tmpProductId = _cursor.getLong(_cursorIndexOfProductId);
            final long _tmpStoreId;
            _tmpStoreId = _cursor.getLong(_cursorIndexOfStoreId);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final Double _tmpCampaignPrice;
            if (_cursor.isNull(_cursorIndexOfCampaignPrice)) {
              _tmpCampaignPrice = null;
            } else {
              _tmpCampaignPrice = _cursor.getDouble(_cursorIndexOfCampaignPrice);
            }
            final Integer _tmpCampaignQuantity;
            if (_cursor.isNull(_cursorIndexOfCampaignQuantity)) {
              _tmpCampaignQuantity = null;
            } else {
              _tmpCampaignQuantity = _cursor.getInt(_cursorIndexOfCampaignQuantity);
            }
            final boolean _tmpHasCampaignPrice;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHasCampaignPrice);
            _tmpHasCampaignPrice = _tmp != 0;
            final long _tmpRecordedAt;
            _tmpRecordedAt = _cursor.getLong(_cursorIndexOfRecordedAt);
            final String _tmpSource;
            if (_cursor.isNull(_cursorIndexOfSource)) {
              _tmpSource = null;
            } else {
              _tmpSource = _cursor.getString(_cursorIndexOfSource);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _result = new PriceHistory(_tmpId,_tmpProductId,_tmpStoreId,_tmpPrice,_tmpCampaignPrice,_tmpCampaignQuantity,_tmpHasCampaignPrice,_tmpRecordedAt,_tmpSource,_tmpNotes);
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
  public LiveData<Integer> getPriceHistoryCountForProduct(final long productId) {
    final String _sql = "SELECT COUNT(*) FROM price_history WHERE productId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, productId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"price_history"}, false, new Callable<Integer>() {
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
  public Object getStoreCountWithPriceHistoryForProduct(final long productId,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(DISTINCT storeId) FROM price_history WHERE productId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, productId);
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
    }, $completion);
  }

  @Override
  public Object getPriceHistoryForProductSuspend(final long productId,
      final Continuation<? super List<PriceHistory>> $completion) {
    final String _sql = "SELECT * FROM price_history WHERE productId = ? ORDER BY recordedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, productId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<PriceHistory>>() {
      @Override
      @NonNull
      public List<PriceHistory> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final int _cursorIndexOfStoreId = CursorUtil.getColumnIndexOrThrow(_cursor, "storeId");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfCampaignPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "campaignPrice");
          final int _cursorIndexOfCampaignQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "campaignQuantity");
          final int _cursorIndexOfHasCampaignPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "hasCampaignPrice");
          final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "recordedAt");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final List<PriceHistory> _result = new ArrayList<PriceHistory>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PriceHistory _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpProductId;
            _tmpProductId = _cursor.getLong(_cursorIndexOfProductId);
            final long _tmpStoreId;
            _tmpStoreId = _cursor.getLong(_cursorIndexOfStoreId);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final Double _tmpCampaignPrice;
            if (_cursor.isNull(_cursorIndexOfCampaignPrice)) {
              _tmpCampaignPrice = null;
            } else {
              _tmpCampaignPrice = _cursor.getDouble(_cursorIndexOfCampaignPrice);
            }
            final Integer _tmpCampaignQuantity;
            if (_cursor.isNull(_cursorIndexOfCampaignQuantity)) {
              _tmpCampaignQuantity = null;
            } else {
              _tmpCampaignQuantity = _cursor.getInt(_cursorIndexOfCampaignQuantity);
            }
            final boolean _tmpHasCampaignPrice;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHasCampaignPrice);
            _tmpHasCampaignPrice = _tmp != 0;
            final long _tmpRecordedAt;
            _tmpRecordedAt = _cursor.getLong(_cursorIndexOfRecordedAt);
            final String _tmpSource;
            if (_cursor.isNull(_cursorIndexOfSource)) {
              _tmpSource = null;
            } else {
              _tmpSource = _cursor.getString(_cursorIndexOfSource);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _item = new PriceHistory(_tmpId,_tmpProductId,_tmpStoreId,_tmpPrice,_tmpCampaignPrice,_tmpCampaignQuantity,_tmpHasCampaignPrice,_tmpRecordedAt,_tmpSource,_tmpNotes);
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
  public Object getRecentPriceHistorySuspend(final long date,
      final Continuation<? super List<PriceHistory>> $completion) {
    final String _sql = "SELECT * FROM price_history WHERE recordedAt >= ? ORDER BY recordedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, date);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<PriceHistory>>() {
      @Override
      @NonNull
      public List<PriceHistory> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final int _cursorIndexOfStoreId = CursorUtil.getColumnIndexOrThrow(_cursor, "storeId");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfCampaignPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "campaignPrice");
          final int _cursorIndexOfCampaignQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "campaignQuantity");
          final int _cursorIndexOfHasCampaignPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "hasCampaignPrice");
          final int _cursorIndexOfRecordedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "recordedAt");
          final int _cursorIndexOfSource = CursorUtil.getColumnIndexOrThrow(_cursor, "source");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final List<PriceHistory> _result = new ArrayList<PriceHistory>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PriceHistory _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpProductId;
            _tmpProductId = _cursor.getLong(_cursorIndexOfProductId);
            final long _tmpStoreId;
            _tmpStoreId = _cursor.getLong(_cursorIndexOfStoreId);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final Double _tmpCampaignPrice;
            if (_cursor.isNull(_cursorIndexOfCampaignPrice)) {
              _tmpCampaignPrice = null;
            } else {
              _tmpCampaignPrice = _cursor.getDouble(_cursorIndexOfCampaignPrice);
            }
            final Integer _tmpCampaignQuantity;
            if (_cursor.isNull(_cursorIndexOfCampaignQuantity)) {
              _tmpCampaignQuantity = null;
            } else {
              _tmpCampaignQuantity = _cursor.getInt(_cursorIndexOfCampaignQuantity);
            }
            final boolean _tmpHasCampaignPrice;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHasCampaignPrice);
            _tmpHasCampaignPrice = _tmp != 0;
            final long _tmpRecordedAt;
            _tmpRecordedAt = _cursor.getLong(_cursorIndexOfRecordedAt);
            final String _tmpSource;
            if (_cursor.isNull(_cursorIndexOfSource)) {
              _tmpSource = null;
            } else {
              _tmpSource = _cursor.getString(_cursorIndexOfSource);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            _item = new PriceHistory(_tmpId,_tmpProductId,_tmpStoreId,_tmpPrice,_tmpCampaignPrice,_tmpCampaignQuantity,_tmpHasCampaignPrice,_tmpRecordedAt,_tmpSource,_tmpNotes);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
