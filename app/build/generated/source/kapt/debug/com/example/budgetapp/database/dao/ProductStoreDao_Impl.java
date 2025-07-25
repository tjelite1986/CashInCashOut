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
import com.example.budgetapp.database.entities.ProductStore;
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
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ProductStoreDao_Impl implements ProductStoreDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ProductStore> __insertionAdapterOfProductStore;

  private final EntityDeletionOrUpdateAdapter<ProductStore> __deletionAdapterOfProductStore;

  private final EntityDeletionOrUpdateAdapter<ProductStore> __updateAdapterOfProductStore;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllPricesForProduct;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllPricesForStore;

  private final SharedSQLiteStatement __preparedStmtOfDeleteProductStorePrice;

  public ProductStoreDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfProductStore = new EntityInsertionAdapter<ProductStore>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `product_stores` (`id`,`productId`,`storeId`,`price`,`hasCampaignPrice`,`campaignQuantity`,`campaignPrice`,`lastSeen`,`createdAt`,`updatedAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ProductStore entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getProductId());
        statement.bindLong(3, entity.getStoreId());
        statement.bindDouble(4, entity.getPrice());
        final int _tmp = entity.getHasCampaignPrice() ? 1 : 0;
        statement.bindLong(5, _tmp);
        if (entity.getCampaignQuantity() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getCampaignQuantity());
        }
        if (entity.getCampaignPrice() == null) {
          statement.bindNull(7);
        } else {
          statement.bindDouble(7, entity.getCampaignPrice());
        }
        statement.bindLong(8, entity.getLastSeen());
        statement.bindLong(9, entity.getCreatedAt());
        statement.bindLong(10, entity.getUpdatedAt());
      }
    };
    this.__deletionAdapterOfProductStore = new EntityDeletionOrUpdateAdapter<ProductStore>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `product_stores` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ProductStore entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfProductStore = new EntityDeletionOrUpdateAdapter<ProductStore>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `product_stores` SET `id` = ?,`productId` = ?,`storeId` = ?,`price` = ?,`hasCampaignPrice` = ?,`campaignQuantity` = ?,`campaignPrice` = ?,`lastSeen` = ?,`createdAt` = ?,`updatedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ProductStore entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getProductId());
        statement.bindLong(3, entity.getStoreId());
        statement.bindDouble(4, entity.getPrice());
        final int _tmp = entity.getHasCampaignPrice() ? 1 : 0;
        statement.bindLong(5, _tmp);
        if (entity.getCampaignQuantity() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getCampaignQuantity());
        }
        if (entity.getCampaignPrice() == null) {
          statement.bindNull(7);
        } else {
          statement.bindDouble(7, entity.getCampaignPrice());
        }
        statement.bindLong(8, entity.getLastSeen());
        statement.bindLong(9, entity.getCreatedAt());
        statement.bindLong(10, entity.getUpdatedAt());
        statement.bindLong(11, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteAllPricesForProduct = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM product_stores WHERE productId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllPricesForStore = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM product_stores WHERE storeId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteProductStorePrice = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM product_stores WHERE productId = ? AND storeId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertProductStore(final ProductStore productStore,
      final Continuation<? super Long> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfProductStore.insertAndReturnId(productStore);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object insertProductStores(final List<ProductStore> productStores,
      final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfProductStore.insert(productStores);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object deleteProductStore(final ProductStore productStore,
      final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfProductStore.handle(productStore);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object updateProductStore(final ProductStore productStore,
      final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfProductStore.handle(productStore);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object deleteAllPricesForProduct(final long productId,
      final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllPricesForProduct.acquire();
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
          __preparedStmtOfDeleteAllPricesForProduct.release(_stmt);
        }
      }
    }, arg1);
  }

  @Override
  public Object deleteAllPricesForStore(final long storeId, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllPricesForStore.acquire();
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
          __preparedStmtOfDeleteAllPricesForStore.release(_stmt);
        }
      }
    }, arg1);
  }

  @Override
  public Object deleteProductStorePrice(final long productId, final long storeId,
      final Continuation<? super Unit> arg2) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteProductStorePrice.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, productId);
        _argIndex = 2;
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
          __preparedStmtOfDeleteProductStorePrice.release(_stmt);
        }
      }
    }, arg2);
  }

  @Override
  public Flow<List<ProductStore>> getAllProductStores() {
    final String _sql = "SELECT * FROM product_stores";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"product_stores"}, new Callable<List<ProductStore>>() {
      @Override
      @NonNull
      public List<ProductStore> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final int _cursorIndexOfStoreId = CursorUtil.getColumnIndexOrThrow(_cursor, "storeId");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfHasCampaignPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "hasCampaignPrice");
          final int _cursorIndexOfCampaignQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "campaignQuantity");
          final int _cursorIndexOfCampaignPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "campaignPrice");
          final int _cursorIndexOfLastSeen = CursorUtil.getColumnIndexOrThrow(_cursor, "lastSeen");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<ProductStore> _result = new ArrayList<ProductStore>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ProductStore _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpProductId;
            _tmpProductId = _cursor.getLong(_cursorIndexOfProductId);
            final long _tmpStoreId;
            _tmpStoreId = _cursor.getLong(_cursorIndexOfStoreId);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final boolean _tmpHasCampaignPrice;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHasCampaignPrice);
            _tmpHasCampaignPrice = _tmp != 0;
            final Integer _tmpCampaignQuantity;
            if (_cursor.isNull(_cursorIndexOfCampaignQuantity)) {
              _tmpCampaignQuantity = null;
            } else {
              _tmpCampaignQuantity = _cursor.getInt(_cursorIndexOfCampaignQuantity);
            }
            final Double _tmpCampaignPrice;
            if (_cursor.isNull(_cursorIndexOfCampaignPrice)) {
              _tmpCampaignPrice = null;
            } else {
              _tmpCampaignPrice = _cursor.getDouble(_cursorIndexOfCampaignPrice);
            }
            final long _tmpLastSeen;
            _tmpLastSeen = _cursor.getLong(_cursorIndexOfLastSeen);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new ProductStore(_tmpId,_tmpProductId,_tmpStoreId,_tmpPrice,_tmpHasCampaignPrice,_tmpCampaignQuantity,_tmpCampaignPrice,_tmpLastSeen,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<ProductStore>> getPricesForProduct(final long productId) {
    final String _sql = "SELECT * FROM product_stores WHERE productId = ? ORDER BY price ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, productId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"product_stores"}, new Callable<List<ProductStore>>() {
      @Override
      @NonNull
      public List<ProductStore> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final int _cursorIndexOfStoreId = CursorUtil.getColumnIndexOrThrow(_cursor, "storeId");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfHasCampaignPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "hasCampaignPrice");
          final int _cursorIndexOfCampaignQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "campaignQuantity");
          final int _cursorIndexOfCampaignPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "campaignPrice");
          final int _cursorIndexOfLastSeen = CursorUtil.getColumnIndexOrThrow(_cursor, "lastSeen");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<ProductStore> _result = new ArrayList<ProductStore>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ProductStore _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpProductId;
            _tmpProductId = _cursor.getLong(_cursorIndexOfProductId);
            final long _tmpStoreId;
            _tmpStoreId = _cursor.getLong(_cursorIndexOfStoreId);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final boolean _tmpHasCampaignPrice;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHasCampaignPrice);
            _tmpHasCampaignPrice = _tmp != 0;
            final Integer _tmpCampaignQuantity;
            if (_cursor.isNull(_cursorIndexOfCampaignQuantity)) {
              _tmpCampaignQuantity = null;
            } else {
              _tmpCampaignQuantity = _cursor.getInt(_cursorIndexOfCampaignQuantity);
            }
            final Double _tmpCampaignPrice;
            if (_cursor.isNull(_cursorIndexOfCampaignPrice)) {
              _tmpCampaignPrice = null;
            } else {
              _tmpCampaignPrice = _cursor.getDouble(_cursorIndexOfCampaignPrice);
            }
            final long _tmpLastSeen;
            _tmpLastSeen = _cursor.getLong(_cursorIndexOfLastSeen);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new ProductStore(_tmpId,_tmpProductId,_tmpStoreId,_tmpPrice,_tmpHasCampaignPrice,_tmpCampaignQuantity,_tmpCampaignPrice,_tmpLastSeen,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<ProductStore>> getPricesForStore(final long storeId) {
    final String _sql = "SELECT * FROM product_stores WHERE storeId = ? ORDER BY price ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, storeId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"product_stores"}, new Callable<List<ProductStore>>() {
      @Override
      @NonNull
      public List<ProductStore> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final int _cursorIndexOfStoreId = CursorUtil.getColumnIndexOrThrow(_cursor, "storeId");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfHasCampaignPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "hasCampaignPrice");
          final int _cursorIndexOfCampaignQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "campaignQuantity");
          final int _cursorIndexOfCampaignPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "campaignPrice");
          final int _cursorIndexOfLastSeen = CursorUtil.getColumnIndexOrThrow(_cursor, "lastSeen");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<ProductStore> _result = new ArrayList<ProductStore>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ProductStore _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpProductId;
            _tmpProductId = _cursor.getLong(_cursorIndexOfProductId);
            final long _tmpStoreId;
            _tmpStoreId = _cursor.getLong(_cursorIndexOfStoreId);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final boolean _tmpHasCampaignPrice;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHasCampaignPrice);
            _tmpHasCampaignPrice = _tmp != 0;
            final Integer _tmpCampaignQuantity;
            if (_cursor.isNull(_cursorIndexOfCampaignQuantity)) {
              _tmpCampaignQuantity = null;
            } else {
              _tmpCampaignQuantity = _cursor.getInt(_cursorIndexOfCampaignQuantity);
            }
            final Double _tmpCampaignPrice;
            if (_cursor.isNull(_cursorIndexOfCampaignPrice)) {
              _tmpCampaignPrice = null;
            } else {
              _tmpCampaignPrice = _cursor.getDouble(_cursorIndexOfCampaignPrice);
            }
            final long _tmpLastSeen;
            _tmpLastSeen = _cursor.getLong(_cursorIndexOfLastSeen);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new ProductStore(_tmpId,_tmpProductId,_tmpStoreId,_tmpPrice,_tmpHasCampaignPrice,_tmpCampaignQuantity,_tmpCampaignPrice,_tmpLastSeen,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Object getProductStorePrice(final long productId, final long storeId,
      final Continuation<? super ProductStore> arg2) {
    final String _sql = "SELECT * FROM product_stores WHERE productId = ? AND storeId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, productId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, storeId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ProductStore>() {
      @Override
      @Nullable
      public ProductStore call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final int _cursorIndexOfStoreId = CursorUtil.getColumnIndexOrThrow(_cursor, "storeId");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfHasCampaignPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "hasCampaignPrice");
          final int _cursorIndexOfCampaignQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "campaignQuantity");
          final int _cursorIndexOfCampaignPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "campaignPrice");
          final int _cursorIndexOfLastSeen = CursorUtil.getColumnIndexOrThrow(_cursor, "lastSeen");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final ProductStore _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpProductId;
            _tmpProductId = _cursor.getLong(_cursorIndexOfProductId);
            final long _tmpStoreId;
            _tmpStoreId = _cursor.getLong(_cursorIndexOfStoreId);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final boolean _tmpHasCampaignPrice;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHasCampaignPrice);
            _tmpHasCampaignPrice = _tmp != 0;
            final Integer _tmpCampaignQuantity;
            if (_cursor.isNull(_cursorIndexOfCampaignQuantity)) {
              _tmpCampaignQuantity = null;
            } else {
              _tmpCampaignQuantity = _cursor.getInt(_cursorIndexOfCampaignQuantity);
            }
            final Double _tmpCampaignPrice;
            if (_cursor.isNull(_cursorIndexOfCampaignPrice)) {
              _tmpCampaignPrice = null;
            } else {
              _tmpCampaignPrice = _cursor.getDouble(_cursorIndexOfCampaignPrice);
            }
            final long _tmpLastSeen;
            _tmpLastSeen = _cursor.getLong(_cursorIndexOfLastSeen);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new ProductStore(_tmpId,_tmpProductId,_tmpStoreId,_tmpPrice,_tmpHasCampaignPrice,_tmpCampaignQuantity,_tmpCampaignPrice,_tmpLastSeen,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<ProductStore>> getPricesForProductWithStore(final long productId) {
    final String _sql = "\n"
            + "        SELECT ps.* FROM product_stores ps\n"
            + "        INNER JOIN stores s ON ps.storeId = s.id\n"
            + "        WHERE ps.productId = ?\n"
            + "        ORDER BY ps.price ASC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, productId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"product_stores",
        "stores"}, new Callable<List<ProductStore>>() {
      @Override
      @NonNull
      public List<ProductStore> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final int _cursorIndexOfStoreId = CursorUtil.getColumnIndexOrThrow(_cursor, "storeId");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfHasCampaignPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "hasCampaignPrice");
          final int _cursorIndexOfCampaignQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "campaignQuantity");
          final int _cursorIndexOfCampaignPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "campaignPrice");
          final int _cursorIndexOfLastSeen = CursorUtil.getColumnIndexOrThrow(_cursor, "lastSeen");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<ProductStore> _result = new ArrayList<ProductStore>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ProductStore _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpProductId;
            _tmpProductId = _cursor.getLong(_cursorIndexOfProductId);
            final long _tmpStoreId;
            _tmpStoreId = _cursor.getLong(_cursorIndexOfStoreId);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final boolean _tmpHasCampaignPrice;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHasCampaignPrice);
            _tmpHasCampaignPrice = _tmp != 0;
            final Integer _tmpCampaignQuantity;
            if (_cursor.isNull(_cursorIndexOfCampaignQuantity)) {
              _tmpCampaignQuantity = null;
            } else {
              _tmpCampaignQuantity = _cursor.getInt(_cursorIndexOfCampaignQuantity);
            }
            final Double _tmpCampaignPrice;
            if (_cursor.isNull(_cursorIndexOfCampaignPrice)) {
              _tmpCampaignPrice = null;
            } else {
              _tmpCampaignPrice = _cursor.getDouble(_cursorIndexOfCampaignPrice);
            }
            final long _tmpLastSeen;
            _tmpLastSeen = _cursor.getLong(_cursorIndexOfLastSeen);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new ProductStore(_tmpId,_tmpProductId,_tmpStoreId,_tmpPrice,_tmpHasCampaignPrice,_tmpCampaignQuantity,_tmpCampaignPrice,_tmpLastSeen,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Object getLowestPrice(final long productId, final Continuation<? super Double> arg1) {
    final String _sql = "\n"
            + "        SELECT MIN(price) FROM product_stores \n"
            + "        WHERE productId = ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, productId);
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
    }, arg1);
  }

  @Override
  public Object getHighestPrice(final long productId, final Continuation<? super Double> arg1) {
    final String _sql = "\n"
            + "        SELECT MAX(price) FROM product_stores \n"
            + "        WHERE productId = ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, productId);
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
    }, arg1);
  }

  @Override
  public Object getAveragePrice(final long productId, final Continuation<? super Double> arg1) {
    final String _sql = "\n"
            + "        SELECT AVG(price) FROM product_stores \n"
            + "        WHERE productId = ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, productId);
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
    }, arg1);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
