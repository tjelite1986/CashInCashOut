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
import com.example.budgetapp.database.entities.Product;
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
public final class ProductDao_Impl implements ProductDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Product> __insertionAdapterOfProduct;

  private final EntityDeletionOrUpdateAdapter<Product> __deletionAdapterOfProduct;

  private final EntityDeletionOrUpdateAdapter<Product> __updateAdapterOfProduct;

  private final SharedSQLiteStatement __preparedStmtOfDeleteProductById;

  public ProductDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfProduct = new EntityInsertionAdapter<Product>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `products` (`id`,`name`,`productCategoryId`,`hasDeposit`,`depositAmount`,`barcode`,`productName`,`description`,`amount`,`unit`,`createdAt`,`updatedAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Product entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getProductCategoryId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, entity.getProductCategoryId());
        }
        final int _tmp = entity.getHasDeposit() ? 1 : 0;
        statement.bindLong(4, _tmp);
        if (entity.getDepositAmount() == null) {
          statement.bindNull(5);
        } else {
          statement.bindDouble(5, entity.getDepositAmount());
        }
        if (entity.getBarcode() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getBarcode());
        }
        if (entity.getProductName() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getProductName());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getDescription());
        }
        if (entity.getAmount() == null) {
          statement.bindNull(9);
        } else {
          statement.bindDouble(9, entity.getAmount());
        }
        if (entity.getUnit() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getUnit());
        }
        statement.bindLong(11, entity.getCreatedAt());
        statement.bindLong(12, entity.getUpdatedAt());
      }
    };
    this.__deletionAdapterOfProduct = new EntityDeletionOrUpdateAdapter<Product>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `products` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Product entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfProduct = new EntityDeletionOrUpdateAdapter<Product>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `products` SET `id` = ?,`name` = ?,`productCategoryId` = ?,`hasDeposit` = ?,`depositAmount` = ?,`barcode` = ?,`productName` = ?,`description` = ?,`amount` = ?,`unit` = ?,`createdAt` = ?,`updatedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Product entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getProductCategoryId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, entity.getProductCategoryId());
        }
        final int _tmp = entity.getHasDeposit() ? 1 : 0;
        statement.bindLong(4, _tmp);
        if (entity.getDepositAmount() == null) {
          statement.bindNull(5);
        } else {
          statement.bindDouble(5, entity.getDepositAmount());
        }
        if (entity.getBarcode() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getBarcode());
        }
        if (entity.getProductName() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getProductName());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getDescription());
        }
        if (entity.getAmount() == null) {
          statement.bindNull(9);
        } else {
          statement.bindDouble(9, entity.getAmount());
        }
        if (entity.getUnit() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getUnit());
        }
        statement.bindLong(11, entity.getCreatedAt());
        statement.bindLong(12, entity.getUpdatedAt());
        statement.bindLong(13, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteProductById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM products WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertProduct(final Product product, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfProduct.insertAndReturnId(product);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteProduct(final Product product, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfProduct.handle(product);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateProduct(final Product product, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfProduct.handle(product);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteProductById(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteProductById.acquire();
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
          __preparedStmtOfDeleteProductById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Product>> getAllProducts() {
    final String _sql = "SELECT * FROM products ORDER BY name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"products"}, new Callable<List<Product>>() {
      @Override
      @NonNull
      public List<Product> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfProductCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "productCategoryId");
          final int _cursorIndexOfHasDeposit = CursorUtil.getColumnIndexOrThrow(_cursor, "hasDeposit");
          final int _cursorIndexOfDepositAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "depositAmount");
          final int _cursorIndexOfBarcode = CursorUtil.getColumnIndexOrThrow(_cursor, "barcode");
          final int _cursorIndexOfProductName = CursorUtil.getColumnIndexOrThrow(_cursor, "productName");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "unit");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<Product> _result = new ArrayList<Product>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Product _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final Long _tmpProductCategoryId;
            if (_cursor.isNull(_cursorIndexOfProductCategoryId)) {
              _tmpProductCategoryId = null;
            } else {
              _tmpProductCategoryId = _cursor.getLong(_cursorIndexOfProductCategoryId);
            }
            final boolean _tmpHasDeposit;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHasDeposit);
            _tmpHasDeposit = _tmp != 0;
            final Double _tmpDepositAmount;
            if (_cursor.isNull(_cursorIndexOfDepositAmount)) {
              _tmpDepositAmount = null;
            } else {
              _tmpDepositAmount = _cursor.getDouble(_cursorIndexOfDepositAmount);
            }
            final String _tmpBarcode;
            if (_cursor.isNull(_cursorIndexOfBarcode)) {
              _tmpBarcode = null;
            } else {
              _tmpBarcode = _cursor.getString(_cursorIndexOfBarcode);
            }
            final String _tmpProductName;
            if (_cursor.isNull(_cursorIndexOfProductName)) {
              _tmpProductName = null;
            } else {
              _tmpProductName = _cursor.getString(_cursorIndexOfProductName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final Double _tmpAmount;
            if (_cursor.isNull(_cursorIndexOfAmount)) {
              _tmpAmount = null;
            } else {
              _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            }
            final String _tmpUnit;
            if (_cursor.isNull(_cursorIndexOfUnit)) {
              _tmpUnit = null;
            } else {
              _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new Product(_tmpId,_tmpName,_tmpProductCategoryId,_tmpHasDeposit,_tmpDepositAmount,_tmpBarcode,_tmpProductName,_tmpDescription,_tmpAmount,_tmpUnit,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Object getProductById(final long id, final Continuation<? super Product> $completion) {
    final String _sql = "SELECT * FROM products WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Product>() {
      @Override
      @Nullable
      public Product call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfProductCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "productCategoryId");
          final int _cursorIndexOfHasDeposit = CursorUtil.getColumnIndexOrThrow(_cursor, "hasDeposit");
          final int _cursorIndexOfDepositAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "depositAmount");
          final int _cursorIndexOfBarcode = CursorUtil.getColumnIndexOrThrow(_cursor, "barcode");
          final int _cursorIndexOfProductName = CursorUtil.getColumnIndexOrThrow(_cursor, "productName");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "unit");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final Product _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final Long _tmpProductCategoryId;
            if (_cursor.isNull(_cursorIndexOfProductCategoryId)) {
              _tmpProductCategoryId = null;
            } else {
              _tmpProductCategoryId = _cursor.getLong(_cursorIndexOfProductCategoryId);
            }
            final boolean _tmpHasDeposit;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHasDeposit);
            _tmpHasDeposit = _tmp != 0;
            final Double _tmpDepositAmount;
            if (_cursor.isNull(_cursorIndexOfDepositAmount)) {
              _tmpDepositAmount = null;
            } else {
              _tmpDepositAmount = _cursor.getDouble(_cursorIndexOfDepositAmount);
            }
            final String _tmpBarcode;
            if (_cursor.isNull(_cursorIndexOfBarcode)) {
              _tmpBarcode = null;
            } else {
              _tmpBarcode = _cursor.getString(_cursorIndexOfBarcode);
            }
            final String _tmpProductName;
            if (_cursor.isNull(_cursorIndexOfProductName)) {
              _tmpProductName = null;
            } else {
              _tmpProductName = _cursor.getString(_cursorIndexOfProductName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final Double _tmpAmount;
            if (_cursor.isNull(_cursorIndexOfAmount)) {
              _tmpAmount = null;
            } else {
              _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            }
            final String _tmpUnit;
            if (_cursor.isNull(_cursorIndexOfUnit)) {
              _tmpUnit = null;
            } else {
              _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new Product(_tmpId,_tmpName,_tmpProductCategoryId,_tmpHasDeposit,_tmpDepositAmount,_tmpBarcode,_tmpProductName,_tmpDescription,_tmpAmount,_tmpUnit,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Object getProductByBarcode(final String barcode,
      final Continuation<? super Product> $completion) {
    final String _sql = "SELECT * FROM products WHERE barcode = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (barcode == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, barcode);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Product>() {
      @Override
      @Nullable
      public Product call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfProductCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "productCategoryId");
          final int _cursorIndexOfHasDeposit = CursorUtil.getColumnIndexOrThrow(_cursor, "hasDeposit");
          final int _cursorIndexOfDepositAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "depositAmount");
          final int _cursorIndexOfBarcode = CursorUtil.getColumnIndexOrThrow(_cursor, "barcode");
          final int _cursorIndexOfProductName = CursorUtil.getColumnIndexOrThrow(_cursor, "productName");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "unit");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final Product _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final Long _tmpProductCategoryId;
            if (_cursor.isNull(_cursorIndexOfProductCategoryId)) {
              _tmpProductCategoryId = null;
            } else {
              _tmpProductCategoryId = _cursor.getLong(_cursorIndexOfProductCategoryId);
            }
            final boolean _tmpHasDeposit;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHasDeposit);
            _tmpHasDeposit = _tmp != 0;
            final Double _tmpDepositAmount;
            if (_cursor.isNull(_cursorIndexOfDepositAmount)) {
              _tmpDepositAmount = null;
            } else {
              _tmpDepositAmount = _cursor.getDouble(_cursorIndexOfDepositAmount);
            }
            final String _tmpBarcode;
            if (_cursor.isNull(_cursorIndexOfBarcode)) {
              _tmpBarcode = null;
            } else {
              _tmpBarcode = _cursor.getString(_cursorIndexOfBarcode);
            }
            final String _tmpProductName;
            if (_cursor.isNull(_cursorIndexOfProductName)) {
              _tmpProductName = null;
            } else {
              _tmpProductName = _cursor.getString(_cursorIndexOfProductName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final Double _tmpAmount;
            if (_cursor.isNull(_cursorIndexOfAmount)) {
              _tmpAmount = null;
            } else {
              _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            }
            final String _tmpUnit;
            if (_cursor.isNull(_cursorIndexOfUnit)) {
              _tmpUnit = null;
            } else {
              _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new Product(_tmpId,_tmpName,_tmpProductCategoryId,_tmpHasDeposit,_tmpDepositAmount,_tmpBarcode,_tmpProductName,_tmpDescription,_tmpAmount,_tmpUnit,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<Product>> getProductsByCategory(final long productCategoryId) {
    final String _sql = "SELECT * FROM products WHERE productCategoryId = ? ORDER BY name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, productCategoryId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"products"}, new Callable<List<Product>>() {
      @Override
      @NonNull
      public List<Product> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfProductCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "productCategoryId");
          final int _cursorIndexOfHasDeposit = CursorUtil.getColumnIndexOrThrow(_cursor, "hasDeposit");
          final int _cursorIndexOfDepositAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "depositAmount");
          final int _cursorIndexOfBarcode = CursorUtil.getColumnIndexOrThrow(_cursor, "barcode");
          final int _cursorIndexOfProductName = CursorUtil.getColumnIndexOrThrow(_cursor, "productName");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "unit");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<Product> _result = new ArrayList<Product>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Product _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final Long _tmpProductCategoryId;
            if (_cursor.isNull(_cursorIndexOfProductCategoryId)) {
              _tmpProductCategoryId = null;
            } else {
              _tmpProductCategoryId = _cursor.getLong(_cursorIndexOfProductCategoryId);
            }
            final boolean _tmpHasDeposit;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHasDeposit);
            _tmpHasDeposit = _tmp != 0;
            final Double _tmpDepositAmount;
            if (_cursor.isNull(_cursorIndexOfDepositAmount)) {
              _tmpDepositAmount = null;
            } else {
              _tmpDepositAmount = _cursor.getDouble(_cursorIndexOfDepositAmount);
            }
            final String _tmpBarcode;
            if (_cursor.isNull(_cursorIndexOfBarcode)) {
              _tmpBarcode = null;
            } else {
              _tmpBarcode = _cursor.getString(_cursorIndexOfBarcode);
            }
            final String _tmpProductName;
            if (_cursor.isNull(_cursorIndexOfProductName)) {
              _tmpProductName = null;
            } else {
              _tmpProductName = _cursor.getString(_cursorIndexOfProductName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final Double _tmpAmount;
            if (_cursor.isNull(_cursorIndexOfAmount)) {
              _tmpAmount = null;
            } else {
              _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            }
            final String _tmpUnit;
            if (_cursor.isNull(_cursorIndexOfUnit)) {
              _tmpUnit = null;
            } else {
              _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new Product(_tmpId,_tmpName,_tmpProductCategoryId,_tmpHasDeposit,_tmpDepositAmount,_tmpBarcode,_tmpProductName,_tmpDescription,_tmpAmount,_tmpUnit,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<Product>> searchProducts(final String searchQuery) {
    final String _sql = "SELECT * FROM products WHERE name LIKE '%' || ? || '%' OR productName LIKE '%' || ? || '%' ORDER BY name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (searchQuery == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, searchQuery);
    }
    _argIndex = 2;
    if (searchQuery == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, searchQuery);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"products"}, new Callable<List<Product>>() {
      @Override
      @NonNull
      public List<Product> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfProductCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "productCategoryId");
          final int _cursorIndexOfHasDeposit = CursorUtil.getColumnIndexOrThrow(_cursor, "hasDeposit");
          final int _cursorIndexOfDepositAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "depositAmount");
          final int _cursorIndexOfBarcode = CursorUtil.getColumnIndexOrThrow(_cursor, "barcode");
          final int _cursorIndexOfProductName = CursorUtil.getColumnIndexOrThrow(_cursor, "productName");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "unit");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<Product> _result = new ArrayList<Product>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Product _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final Long _tmpProductCategoryId;
            if (_cursor.isNull(_cursorIndexOfProductCategoryId)) {
              _tmpProductCategoryId = null;
            } else {
              _tmpProductCategoryId = _cursor.getLong(_cursorIndexOfProductCategoryId);
            }
            final boolean _tmpHasDeposit;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHasDeposit);
            _tmpHasDeposit = _tmp != 0;
            final Double _tmpDepositAmount;
            if (_cursor.isNull(_cursorIndexOfDepositAmount)) {
              _tmpDepositAmount = null;
            } else {
              _tmpDepositAmount = _cursor.getDouble(_cursorIndexOfDepositAmount);
            }
            final String _tmpBarcode;
            if (_cursor.isNull(_cursorIndexOfBarcode)) {
              _tmpBarcode = null;
            } else {
              _tmpBarcode = _cursor.getString(_cursorIndexOfBarcode);
            }
            final String _tmpProductName;
            if (_cursor.isNull(_cursorIndexOfProductName)) {
              _tmpProductName = null;
            } else {
              _tmpProductName = _cursor.getString(_cursorIndexOfProductName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final Double _tmpAmount;
            if (_cursor.isNull(_cursorIndexOfAmount)) {
              _tmpAmount = null;
            } else {
              _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            }
            final String _tmpUnit;
            if (_cursor.isNull(_cursorIndexOfUnit)) {
              _tmpUnit = null;
            } else {
              _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new Product(_tmpId,_tmpName,_tmpProductCategoryId,_tmpHasDeposit,_tmpDepositAmount,_tmpBarcode,_tmpProductName,_tmpDescription,_tmpAmount,_tmpUnit,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Object getPriceStats(final long productId,
      final Continuation<? super ProductDao.PriceStats> $completion) {
    final String _sql = "\n"
            + "        SELECT MIN(ps.price) as minPrice, MAX(ps.price) as maxPrice, AVG(ps.price) as avgPrice\n"
            + "        FROM product_stores ps\n"
            + "        WHERE ps.productId = ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, productId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ProductDao.PriceStats>() {
      @Override
      @Nullable
      public ProductDao.PriceStats call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfMinPrice = 0;
          final int _cursorIndexOfMaxPrice = 1;
          final int _cursorIndexOfAvgPrice = 2;
          final ProductDao.PriceStats _result;
          if (_cursor.moveToFirst()) {
            final Double _tmpMinPrice;
            if (_cursor.isNull(_cursorIndexOfMinPrice)) {
              _tmpMinPrice = null;
            } else {
              _tmpMinPrice = _cursor.getDouble(_cursorIndexOfMinPrice);
            }
            final Double _tmpMaxPrice;
            if (_cursor.isNull(_cursorIndexOfMaxPrice)) {
              _tmpMaxPrice = null;
            } else {
              _tmpMaxPrice = _cursor.getDouble(_cursorIndexOfMaxPrice);
            }
            final Double _tmpAvgPrice;
            if (_cursor.isNull(_cursorIndexOfAvgPrice)) {
              _tmpAvgPrice = null;
            } else {
              _tmpAvgPrice = _cursor.getDouble(_cursorIndexOfAvgPrice);
            }
            _result = new ProductDao.PriceStats(_tmpMinPrice,_tmpMaxPrice,_tmpAvgPrice);
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
  public Object getPriceStatsWithCampaignAndDeposit(final long productId,
      final Continuation<? super ProductDao.PriceStatsWithDeposit> $completion) {
    final String _sql = "\n"
            + "        SELECT \n"
            + "            MIN(CASE \n"
            + "                WHEN ps.hasCampaignPrice THEN COALESCE(ps.campaignPrice, ps.price)\n"
            + "                ELSE ps.price \n"
            + "            END) as minPrice,\n"
            + "            MAX(CASE \n"
            + "                WHEN ps.hasCampaignPrice THEN COALESCE(ps.campaignPrice, ps.price)\n"
            + "                ELSE ps.price \n"
            + "            END) as maxPrice,\n"
            + "            AVG(CASE \n"
            + "                WHEN ps.hasCampaignPrice THEN COALESCE(ps.campaignPrice, ps.price)\n"
            + "                ELSE ps.price \n"
            + "            END) as avgPrice,\n"
            + "            p.hasDeposit,\n"
            + "            p.depositAmount\n"
            + "        FROM product_stores ps\n"
            + "        INNER JOIN products p ON ps.productId = p.id\n"
            + "        WHERE ps.productId = ?\n"
            + "        GROUP BY p.hasDeposit, p.depositAmount\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, productId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ProductDao.PriceStatsWithDeposit>() {
      @Override
      @Nullable
      public ProductDao.PriceStatsWithDeposit call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfMinPrice = 0;
          final int _cursorIndexOfMaxPrice = 1;
          final int _cursorIndexOfAvgPrice = 2;
          final int _cursorIndexOfHasDeposit = 3;
          final int _cursorIndexOfDepositAmount = 4;
          final ProductDao.PriceStatsWithDeposit _result;
          if (_cursor.moveToFirst()) {
            final Double _tmpMinPrice;
            if (_cursor.isNull(_cursorIndexOfMinPrice)) {
              _tmpMinPrice = null;
            } else {
              _tmpMinPrice = _cursor.getDouble(_cursorIndexOfMinPrice);
            }
            final Double _tmpMaxPrice;
            if (_cursor.isNull(_cursorIndexOfMaxPrice)) {
              _tmpMaxPrice = null;
            } else {
              _tmpMaxPrice = _cursor.getDouble(_cursorIndexOfMaxPrice);
            }
            final Double _tmpAvgPrice;
            if (_cursor.isNull(_cursorIndexOfAvgPrice)) {
              _tmpAvgPrice = null;
            } else {
              _tmpAvgPrice = _cursor.getDouble(_cursorIndexOfAvgPrice);
            }
            final boolean _tmpHasDeposit;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfHasDeposit);
            _tmpHasDeposit = _tmp != 0;
            final Double _tmpDepositAmount;
            if (_cursor.isNull(_cursorIndexOfDepositAmount)) {
              _tmpDepositAmount = null;
            } else {
              _tmpDepositAmount = _cursor.getDouble(_cursorIndexOfDepositAmount);
            }
            _result = new ProductDao.PriceStatsWithDeposit(_tmpMinPrice,_tmpMaxPrice,_tmpAvgPrice,_tmpHasDeposit,_tmpDepositAmount);
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
  public Object getProductStoresWithPrices(final long productId,
      final Continuation<? super List<ProductDao.ProductStoreWithPrice>> $completion) {
    final String _sql = "\n"
            + "        SELECT \n"
            + "            ps.*,\n"
            + "            s.name as store_name,\n"
            + "            s.address as store_address,\n"
            + "            (CASE \n"
            + "                WHEN ps.hasCampaignPrice THEN COALESCE(ps.campaignPrice, ps.price)\n"
            + "                ELSE ps.price \n"
            + "            END) as effective_price,\n"
            + "            p.hasDeposit,\n"
            + "            p.depositAmount\n"
            + "        FROM product_stores ps\n"
            + "        INNER JOIN stores s ON ps.storeId = s.id\n"
            + "        INNER JOIN products p ON ps.productId = p.id\n"
            + "        WHERE ps.productId = ?\n"
            + "        ORDER BY effective_price ASC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, productId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ProductDao.ProductStoreWithPrice>>() {
      @Override
      @NonNull
      public List<ProductDao.ProductStoreWithPrice> call() throws Exception {
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
          final int _cursorIndexOfStoreName = CursorUtil.getColumnIndexOrThrow(_cursor, "store_name");
          final int _cursorIndexOfStoreAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "store_address");
          final int _cursorIndexOfEffectivePrice = CursorUtil.getColumnIndexOrThrow(_cursor, "effective_price");
          final int _cursorIndexOfHasDeposit = CursorUtil.getColumnIndexOrThrow(_cursor, "hasDeposit");
          final int _cursorIndexOfDepositAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "depositAmount");
          final List<ProductDao.ProductStoreWithPrice> _result = new ArrayList<ProductDao.ProductStoreWithPrice>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ProductDao.ProductStoreWithPrice _item;
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
            final String _tmpStore_name;
            if (_cursor.isNull(_cursorIndexOfStoreName)) {
              _tmpStore_name = null;
            } else {
              _tmpStore_name = _cursor.getString(_cursorIndexOfStoreName);
            }
            final String _tmpStore_address;
            if (_cursor.isNull(_cursorIndexOfStoreAddress)) {
              _tmpStore_address = null;
            } else {
              _tmpStore_address = _cursor.getString(_cursorIndexOfStoreAddress);
            }
            final double _tmpEffective_price;
            _tmpEffective_price = _cursor.getDouble(_cursorIndexOfEffectivePrice);
            final boolean _tmpHasDeposit;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfHasDeposit);
            _tmpHasDeposit = _tmp_1 != 0;
            final Double _tmpDepositAmount;
            if (_cursor.isNull(_cursorIndexOfDepositAmount)) {
              _tmpDepositAmount = null;
            } else {
              _tmpDepositAmount = _cursor.getDouble(_cursorIndexOfDepositAmount);
            }
            _item = new ProductDao.ProductStoreWithPrice(_tmpId,_tmpProductId,_tmpStoreId,_tmpPrice,_tmpHasCampaignPrice,_tmpCampaignQuantity,_tmpCampaignPrice,_tmpLastSeen,_tmpCreatedAt,_tmpUpdatedAt,_tmpStore_name,_tmpStore_address,_tmpEffective_price,_tmpHasDeposit,_tmpDepositAmount);
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
  public Object getCheapestStoreForProduct(final long productId,
      final Continuation<? super ProductDao.ProductStoreWithPrice> $completion) {
    final String _sql = "\n"
            + "        SELECT \n"
            + "            ps.*,\n"
            + "            s.name as store_name,\n"
            + "            s.address as store_address,\n"
            + "            (CASE \n"
            + "                WHEN ps.hasCampaignPrice THEN COALESCE(ps.campaignPrice, ps.price)\n"
            + "                ELSE ps.price \n"
            + "            END) as effective_price,\n"
            + "            p.hasDeposit,\n"
            + "            p.depositAmount\n"
            + "        FROM product_stores ps\n"
            + "        INNER JOIN stores s ON ps.storeId = s.id\n"
            + "        INNER JOIN products p ON ps.productId = p.id\n"
            + "        WHERE ps.productId = ?\n"
            + "        ORDER BY effective_price ASC\n"
            + "        LIMIT 1\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, productId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ProductDao.ProductStoreWithPrice>() {
      @Override
      @Nullable
      public ProductDao.ProductStoreWithPrice call() throws Exception {
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
          final int _cursorIndexOfStoreName = CursorUtil.getColumnIndexOrThrow(_cursor, "store_name");
          final int _cursorIndexOfStoreAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "store_address");
          final int _cursorIndexOfEffectivePrice = CursorUtil.getColumnIndexOrThrow(_cursor, "effective_price");
          final int _cursorIndexOfHasDeposit = CursorUtil.getColumnIndexOrThrow(_cursor, "hasDeposit");
          final int _cursorIndexOfDepositAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "depositAmount");
          final ProductDao.ProductStoreWithPrice _result;
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
            final String _tmpStore_name;
            if (_cursor.isNull(_cursorIndexOfStoreName)) {
              _tmpStore_name = null;
            } else {
              _tmpStore_name = _cursor.getString(_cursorIndexOfStoreName);
            }
            final String _tmpStore_address;
            if (_cursor.isNull(_cursorIndexOfStoreAddress)) {
              _tmpStore_address = null;
            } else {
              _tmpStore_address = _cursor.getString(_cursorIndexOfStoreAddress);
            }
            final double _tmpEffective_price;
            _tmpEffective_price = _cursor.getDouble(_cursorIndexOfEffectivePrice);
            final boolean _tmpHasDeposit;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfHasDeposit);
            _tmpHasDeposit = _tmp_1 != 0;
            final Double _tmpDepositAmount;
            if (_cursor.isNull(_cursorIndexOfDepositAmount)) {
              _tmpDepositAmount = null;
            } else {
              _tmpDepositAmount = _cursor.getDouble(_cursorIndexOfDepositAmount);
            }
            _result = new ProductDao.ProductStoreWithPrice(_tmpId,_tmpProductId,_tmpStoreId,_tmpPrice,_tmpHasCampaignPrice,_tmpCampaignQuantity,_tmpCampaignPrice,_tmpLastSeen,_tmpCreatedAt,_tmpUpdatedAt,_tmpStore_name,_tmpStore_address,_tmpEffective_price,_tmpHasDeposit,_tmpDepositAmount);
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
  public Object getProductCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM products";
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
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
