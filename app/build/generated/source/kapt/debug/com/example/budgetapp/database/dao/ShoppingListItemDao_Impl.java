package com.example.budgetapp.database.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.LongSparseArray;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.RelationUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.budgetapp.data.ShoppingListItemWithProduct;
import com.example.budgetapp.database.entities.Product;
import com.example.budgetapp.database.entities.ShoppingListItem;
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

@SuppressWarnings({"unchecked", "deprecation"})
public final class ShoppingListItemDao_Impl implements ShoppingListItemDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ShoppingListItem> __insertionAdapterOfShoppingListItem;

  private final EntityDeletionOrUpdateAdapter<ShoppingListItem> __deletionAdapterOfShoppingListItem;

  private final EntityDeletionOrUpdateAdapter<ShoppingListItem> __updateAdapterOfShoppingListItem;

  private final SharedSQLiteStatement __preparedStmtOfDeleteShoppingListItemById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllItemsForShoppingList;

  private final SharedSQLiteStatement __preparedStmtOfUpdateShoppingListItemStatus;

  private final SharedSQLiteStatement __preparedStmtOfUpdateShoppingListItemQuantity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateShoppingListItemActualPrice;

  public ShoppingListItemDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfShoppingListItem = new EntityInsertionAdapter<ShoppingListItem>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `shopping_list_items` (`id`,`shoppingListId`,`productId`,`customItemName`,`quantity`,`unit`,`estimatedPrice`,`actualPrice`,`notes`,`isCompleted`,`priority`,`createdAt`,`updatedAt`,`completedAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ShoppingListItem entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getShoppingListId());
        if (entity.getProductId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, entity.getProductId());
        }
        if (entity.getCustomItemName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCustomItemName());
        }
        statement.bindLong(5, entity.getQuantity());
        if (entity.getUnit() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getUnit());
        }
        if (entity.getEstimatedPrice() == null) {
          statement.bindNull(7);
        } else {
          statement.bindDouble(7, entity.getEstimatedPrice());
        }
        if (entity.getActualPrice() == null) {
          statement.bindNull(8);
        } else {
          statement.bindDouble(8, entity.getActualPrice());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getNotes());
        }
        final int _tmp = entity.isCompleted() ? 1 : 0;
        statement.bindLong(10, _tmp);
        statement.bindLong(11, entity.getPriority());
        statement.bindLong(12, entity.getCreatedAt());
        statement.bindLong(13, entity.getUpdatedAt());
        if (entity.getCompletedAt() == null) {
          statement.bindNull(14);
        } else {
          statement.bindLong(14, entity.getCompletedAt());
        }
      }
    };
    this.__deletionAdapterOfShoppingListItem = new EntityDeletionOrUpdateAdapter<ShoppingListItem>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `shopping_list_items` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ShoppingListItem entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfShoppingListItem = new EntityDeletionOrUpdateAdapter<ShoppingListItem>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `shopping_list_items` SET `id` = ?,`shoppingListId` = ?,`productId` = ?,`customItemName` = ?,`quantity` = ?,`unit` = ?,`estimatedPrice` = ?,`actualPrice` = ?,`notes` = ?,`isCompleted` = ?,`priority` = ?,`createdAt` = ?,`updatedAt` = ?,`completedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ShoppingListItem entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getShoppingListId());
        if (entity.getProductId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindLong(3, entity.getProductId());
        }
        if (entity.getCustomItemName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCustomItemName());
        }
        statement.bindLong(5, entity.getQuantity());
        if (entity.getUnit() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getUnit());
        }
        if (entity.getEstimatedPrice() == null) {
          statement.bindNull(7);
        } else {
          statement.bindDouble(7, entity.getEstimatedPrice());
        }
        if (entity.getActualPrice() == null) {
          statement.bindNull(8);
        } else {
          statement.bindDouble(8, entity.getActualPrice());
        }
        if (entity.getNotes() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getNotes());
        }
        final int _tmp = entity.isCompleted() ? 1 : 0;
        statement.bindLong(10, _tmp);
        statement.bindLong(11, entity.getPriority());
        statement.bindLong(12, entity.getCreatedAt());
        statement.bindLong(13, entity.getUpdatedAt());
        if (entity.getCompletedAt() == null) {
          statement.bindNull(14);
        } else {
          statement.bindLong(14, entity.getCompletedAt());
        }
        statement.bindLong(15, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteShoppingListItemById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM shopping_list_items WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllItemsForShoppingList = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM shopping_list_items WHERE shoppingListId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateShoppingListItemStatus = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE shopping_list_items SET isCompleted = ?, completedAt = ?, updatedAt = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateShoppingListItemQuantity = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE shopping_list_items SET quantity = ?, updatedAt = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateShoppingListItemActualPrice = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE shopping_list_items SET actualPrice = ?, updatedAt = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertShoppingListItem(final ShoppingListItem item,
      final Continuation<? super Long> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfShoppingListItem.insertAndReturnId(item);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object insertShoppingListItems(final List<ShoppingListItem> items,
      final Continuation<? super List<Long>> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<List<Long>>() {
      @Override
      @NonNull
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          final List<Long> _result = __insertionAdapterOfShoppingListItem.insertAndReturnIdsList(items);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object deleteShoppingListItem(final ShoppingListItem item,
      final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfShoppingListItem.handle(item);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object updateShoppingListItem(final ShoppingListItem item,
      final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfShoppingListItem.handle(item);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object deleteShoppingListItemById(final long id, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteShoppingListItemById.acquire();
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
          __preparedStmtOfDeleteShoppingListItemById.release(_stmt);
        }
      }
    }, arg1);
  }

  @Override
  public Object deleteAllItemsForShoppingList(final long shoppingListId,
      final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllItemsForShoppingList.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, shoppingListId);
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
          __preparedStmtOfDeleteAllItemsForShoppingList.release(_stmt);
        }
      }
    }, arg1);
  }

  @Override
  public Object updateShoppingListItemStatus(final long id, final boolean isCompleted,
      final Long completedAt, final long updatedAt, final Continuation<? super Unit> arg4) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateShoppingListItemStatus.acquire();
        int _argIndex = 1;
        final int _tmp = isCompleted ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
        _argIndex = 2;
        if (completedAt == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindLong(_argIndex, completedAt);
        }
        _argIndex = 3;
        _stmt.bindLong(_argIndex, updatedAt);
        _argIndex = 4;
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
          __preparedStmtOfUpdateShoppingListItemStatus.release(_stmt);
        }
      }
    }, arg4);
  }

  @Override
  public Object updateShoppingListItemQuantity(final long id, final int quantity,
      final long updatedAt, final Continuation<? super Unit> arg3) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateShoppingListItemQuantity.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, quantity);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, updatedAt);
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
          __preparedStmtOfUpdateShoppingListItemQuantity.release(_stmt);
        }
      }
    }, arg3);
  }

  @Override
  public Object updateShoppingListItemActualPrice(final long id, final double actualPrice,
      final long updatedAt, final Continuation<? super Unit> arg3) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateShoppingListItemActualPrice.acquire();
        int _argIndex = 1;
        _stmt.bindDouble(_argIndex, actualPrice);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, updatedAt);
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
          __preparedStmtOfUpdateShoppingListItemActualPrice.release(_stmt);
        }
      }
    }, arg3);
  }

  @Override
  public LiveData<List<ShoppingListItem>> getItemsForShoppingList(final long shoppingListId) {
    final String _sql = "SELECT * FROM shopping_list_items WHERE shoppingListId = ? ORDER BY priority DESC, createdAt ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, shoppingListId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"shopping_list_items"}, false, new Callable<List<ShoppingListItem>>() {
      @Override
      @Nullable
      public List<ShoppingListItem> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfShoppingListId = CursorUtil.getColumnIndexOrThrow(_cursor, "shoppingListId");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final int _cursorIndexOfCustomItemName = CursorUtil.getColumnIndexOrThrow(_cursor, "customItemName");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "unit");
          final int _cursorIndexOfEstimatedPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedPrice");
          final int _cursorIndexOfActualPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "actualPrice");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final List<ShoppingListItem> _result = new ArrayList<ShoppingListItem>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ShoppingListItem _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpShoppingListId;
            _tmpShoppingListId = _cursor.getLong(_cursorIndexOfShoppingListId);
            final Long _tmpProductId;
            if (_cursor.isNull(_cursorIndexOfProductId)) {
              _tmpProductId = null;
            } else {
              _tmpProductId = _cursor.getLong(_cursorIndexOfProductId);
            }
            final String _tmpCustomItemName;
            if (_cursor.isNull(_cursorIndexOfCustomItemName)) {
              _tmpCustomItemName = null;
            } else {
              _tmpCustomItemName = _cursor.getString(_cursorIndexOfCustomItemName);
            }
            final int _tmpQuantity;
            _tmpQuantity = _cursor.getInt(_cursorIndexOfQuantity);
            final String _tmpUnit;
            if (_cursor.isNull(_cursorIndexOfUnit)) {
              _tmpUnit = null;
            } else {
              _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
            }
            final Double _tmpEstimatedPrice;
            if (_cursor.isNull(_cursorIndexOfEstimatedPrice)) {
              _tmpEstimatedPrice = null;
            } else {
              _tmpEstimatedPrice = _cursor.getDouble(_cursorIndexOfEstimatedPrice);
            }
            final Double _tmpActualPrice;
            if (_cursor.isNull(_cursorIndexOfActualPrice)) {
              _tmpActualPrice = null;
            } else {
              _tmpActualPrice = _cursor.getDouble(_cursorIndexOfActualPrice);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final boolean _tmpIsCompleted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp != 0;
            final int _tmpPriority;
            _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            _item = new ShoppingListItem(_tmpId,_tmpShoppingListId,_tmpProductId,_tmpCustomItemName,_tmpQuantity,_tmpUnit,_tmpEstimatedPrice,_tmpActualPrice,_tmpNotes,_tmpIsCompleted,_tmpPriority,_tmpCreatedAt,_tmpUpdatedAt,_tmpCompletedAt);
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
  public LiveData<List<ShoppingListItemWithProduct>> getItemsWithProductForShoppingList(
      final long shoppingListId) {
    final String _sql = "SELECT * FROM shopping_list_items WHERE shoppingListId = ? ORDER BY priority DESC, createdAt ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, shoppingListId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"products",
        "shopping_list_items"}, true, new Callable<List<ShoppingListItemWithProduct>>() {
      @Override
      @Nullable
      public List<ShoppingListItemWithProduct> call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfShoppingListId = CursorUtil.getColumnIndexOrThrow(_cursor, "shoppingListId");
            final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
            final int _cursorIndexOfCustomItemName = CursorUtil.getColumnIndexOrThrow(_cursor, "customItemName");
            final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
            final int _cursorIndexOfUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "unit");
            final int _cursorIndexOfEstimatedPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedPrice");
            final int _cursorIndexOfActualPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "actualPrice");
            final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
            final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
            final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
            final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
            final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
            final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
            final LongSparseArray<Product> _collectionProduct = new LongSparseArray<Product>();
            while (_cursor.moveToNext()) {
              final Long _tmpKey;
              if (_cursor.isNull(_cursorIndexOfProductId)) {
                _tmpKey = null;
              } else {
                _tmpKey = _cursor.getLong(_cursorIndexOfProductId);
              }
              if (_tmpKey != null) {
                _collectionProduct.put(_tmpKey, null);
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshipproductsAscomExampleBudgetappDatabaseEntitiesProduct(_collectionProduct);
            final List<ShoppingListItemWithProduct> _result = new ArrayList<ShoppingListItemWithProduct>(_cursor.getCount());
            while (_cursor.moveToNext()) {
              final ShoppingListItemWithProduct _item;
              final ShoppingListItem _tmpItem;
              final long _tmpId;
              _tmpId = _cursor.getLong(_cursorIndexOfId);
              final long _tmpShoppingListId;
              _tmpShoppingListId = _cursor.getLong(_cursorIndexOfShoppingListId);
              final Long _tmpProductId;
              if (_cursor.isNull(_cursorIndexOfProductId)) {
                _tmpProductId = null;
              } else {
                _tmpProductId = _cursor.getLong(_cursorIndexOfProductId);
              }
              final String _tmpCustomItemName;
              if (_cursor.isNull(_cursorIndexOfCustomItemName)) {
                _tmpCustomItemName = null;
              } else {
                _tmpCustomItemName = _cursor.getString(_cursorIndexOfCustomItemName);
              }
              final int _tmpQuantity;
              _tmpQuantity = _cursor.getInt(_cursorIndexOfQuantity);
              final String _tmpUnit;
              if (_cursor.isNull(_cursorIndexOfUnit)) {
                _tmpUnit = null;
              } else {
                _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
              }
              final Double _tmpEstimatedPrice;
              if (_cursor.isNull(_cursorIndexOfEstimatedPrice)) {
                _tmpEstimatedPrice = null;
              } else {
                _tmpEstimatedPrice = _cursor.getDouble(_cursorIndexOfEstimatedPrice);
              }
              final Double _tmpActualPrice;
              if (_cursor.isNull(_cursorIndexOfActualPrice)) {
                _tmpActualPrice = null;
              } else {
                _tmpActualPrice = _cursor.getDouble(_cursorIndexOfActualPrice);
              }
              final String _tmpNotes;
              if (_cursor.isNull(_cursorIndexOfNotes)) {
                _tmpNotes = null;
              } else {
                _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
              }
              final boolean _tmpIsCompleted;
              final int _tmp;
              _tmp = _cursor.getInt(_cursorIndexOfIsCompleted);
              _tmpIsCompleted = _tmp != 0;
              final int _tmpPriority;
              _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
              final long _tmpCreatedAt;
              _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
              final long _tmpUpdatedAt;
              _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
              final Long _tmpCompletedAt;
              if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
                _tmpCompletedAt = null;
              } else {
                _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
              }
              _tmpItem = new ShoppingListItem(_tmpId,_tmpShoppingListId,_tmpProductId,_tmpCustomItemName,_tmpQuantity,_tmpUnit,_tmpEstimatedPrice,_tmpActualPrice,_tmpNotes,_tmpIsCompleted,_tmpPriority,_tmpCreatedAt,_tmpUpdatedAt,_tmpCompletedAt);
              final Product _tmpProduct;
              final Long _tmpKey_1;
              if (_cursor.isNull(_cursorIndexOfProductId)) {
                _tmpKey_1 = null;
              } else {
                _tmpKey_1 = _cursor.getLong(_cursorIndexOfProductId);
              }
              if (_tmpKey_1 != null) {
                _tmpProduct = _collectionProduct.get(_tmpKey_1);
              } else {
                _tmpProduct = null;
              }
              _item = new ShoppingListItemWithProduct(_tmpItem,_tmpProduct);
              _result.add(_item);
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<ShoppingListItem>> getItemsForShoppingListByStatus(final long shoppingListId,
      final boolean isCompleted) {
    final String _sql = "SELECT * FROM shopping_list_items WHERE shoppingListId = ? AND isCompleted = ? ORDER BY priority DESC, createdAt ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, shoppingListId);
    _argIndex = 2;
    final int _tmp = isCompleted ? 1 : 0;
    _statement.bindLong(_argIndex, _tmp);
    return __db.getInvalidationTracker().createLiveData(new String[] {"shopping_list_items"}, false, new Callable<List<ShoppingListItem>>() {
      @Override
      @Nullable
      public List<ShoppingListItem> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfShoppingListId = CursorUtil.getColumnIndexOrThrow(_cursor, "shoppingListId");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final int _cursorIndexOfCustomItemName = CursorUtil.getColumnIndexOrThrow(_cursor, "customItemName");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "unit");
          final int _cursorIndexOfEstimatedPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedPrice");
          final int _cursorIndexOfActualPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "actualPrice");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final List<ShoppingListItem> _result = new ArrayList<ShoppingListItem>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ShoppingListItem _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpShoppingListId;
            _tmpShoppingListId = _cursor.getLong(_cursorIndexOfShoppingListId);
            final Long _tmpProductId;
            if (_cursor.isNull(_cursorIndexOfProductId)) {
              _tmpProductId = null;
            } else {
              _tmpProductId = _cursor.getLong(_cursorIndexOfProductId);
            }
            final String _tmpCustomItemName;
            if (_cursor.isNull(_cursorIndexOfCustomItemName)) {
              _tmpCustomItemName = null;
            } else {
              _tmpCustomItemName = _cursor.getString(_cursorIndexOfCustomItemName);
            }
            final int _tmpQuantity;
            _tmpQuantity = _cursor.getInt(_cursorIndexOfQuantity);
            final String _tmpUnit;
            if (_cursor.isNull(_cursorIndexOfUnit)) {
              _tmpUnit = null;
            } else {
              _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
            }
            final Double _tmpEstimatedPrice;
            if (_cursor.isNull(_cursorIndexOfEstimatedPrice)) {
              _tmpEstimatedPrice = null;
            } else {
              _tmpEstimatedPrice = _cursor.getDouble(_cursorIndexOfEstimatedPrice);
            }
            final Double _tmpActualPrice;
            if (_cursor.isNull(_cursorIndexOfActualPrice)) {
              _tmpActualPrice = null;
            } else {
              _tmpActualPrice = _cursor.getDouble(_cursorIndexOfActualPrice);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final boolean _tmpIsCompleted;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_1 != 0;
            final int _tmpPriority;
            _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            _item = new ShoppingListItem(_tmpId,_tmpShoppingListId,_tmpProductId,_tmpCustomItemName,_tmpQuantity,_tmpUnit,_tmpEstimatedPrice,_tmpActualPrice,_tmpNotes,_tmpIsCompleted,_tmpPriority,_tmpCreatedAt,_tmpUpdatedAt,_tmpCompletedAt);
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
  public Object getShoppingListItemById(final long id,
      final Continuation<? super ShoppingListItem> arg1) {
    final String _sql = "SELECT * FROM shopping_list_items WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ShoppingListItem>() {
      @Override
      @Nullable
      public ShoppingListItem call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfShoppingListId = CursorUtil.getColumnIndexOrThrow(_cursor, "shoppingListId");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final int _cursorIndexOfCustomItemName = CursorUtil.getColumnIndexOrThrow(_cursor, "customItemName");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "unit");
          final int _cursorIndexOfEstimatedPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedPrice");
          final int _cursorIndexOfActualPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "actualPrice");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final ShoppingListItem _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpShoppingListId;
            _tmpShoppingListId = _cursor.getLong(_cursorIndexOfShoppingListId);
            final Long _tmpProductId;
            if (_cursor.isNull(_cursorIndexOfProductId)) {
              _tmpProductId = null;
            } else {
              _tmpProductId = _cursor.getLong(_cursorIndexOfProductId);
            }
            final String _tmpCustomItemName;
            if (_cursor.isNull(_cursorIndexOfCustomItemName)) {
              _tmpCustomItemName = null;
            } else {
              _tmpCustomItemName = _cursor.getString(_cursorIndexOfCustomItemName);
            }
            final int _tmpQuantity;
            _tmpQuantity = _cursor.getInt(_cursorIndexOfQuantity);
            final String _tmpUnit;
            if (_cursor.isNull(_cursorIndexOfUnit)) {
              _tmpUnit = null;
            } else {
              _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
            }
            final Double _tmpEstimatedPrice;
            if (_cursor.isNull(_cursorIndexOfEstimatedPrice)) {
              _tmpEstimatedPrice = null;
            } else {
              _tmpEstimatedPrice = _cursor.getDouble(_cursorIndexOfEstimatedPrice);
            }
            final Double _tmpActualPrice;
            if (_cursor.isNull(_cursorIndexOfActualPrice)) {
              _tmpActualPrice = null;
            } else {
              _tmpActualPrice = _cursor.getDouble(_cursorIndexOfActualPrice);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final boolean _tmpIsCompleted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp != 0;
            final int _tmpPriority;
            _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            _result = new ShoppingListItem(_tmpId,_tmpShoppingListId,_tmpProductId,_tmpCustomItemName,_tmpQuantity,_tmpUnit,_tmpEstimatedPrice,_tmpActualPrice,_tmpNotes,_tmpIsCompleted,_tmpPriority,_tmpCreatedAt,_tmpUpdatedAt,_tmpCompletedAt);
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
  public LiveData<List<ShoppingListItem>> getShoppingListItemsByProduct(final long productId) {
    final String _sql = "SELECT * FROM shopping_list_items WHERE productId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, productId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"shopping_list_items"}, false, new Callable<List<ShoppingListItem>>() {
      @Override
      @Nullable
      public List<ShoppingListItem> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfShoppingListId = CursorUtil.getColumnIndexOrThrow(_cursor, "shoppingListId");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final int _cursorIndexOfCustomItemName = CursorUtil.getColumnIndexOrThrow(_cursor, "customItemName");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "unit");
          final int _cursorIndexOfEstimatedPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedPrice");
          final int _cursorIndexOfActualPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "actualPrice");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final List<ShoppingListItem> _result = new ArrayList<ShoppingListItem>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ShoppingListItem _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpShoppingListId;
            _tmpShoppingListId = _cursor.getLong(_cursorIndexOfShoppingListId);
            final Long _tmpProductId;
            if (_cursor.isNull(_cursorIndexOfProductId)) {
              _tmpProductId = null;
            } else {
              _tmpProductId = _cursor.getLong(_cursorIndexOfProductId);
            }
            final String _tmpCustomItemName;
            if (_cursor.isNull(_cursorIndexOfCustomItemName)) {
              _tmpCustomItemName = null;
            } else {
              _tmpCustomItemName = _cursor.getString(_cursorIndexOfCustomItemName);
            }
            final int _tmpQuantity;
            _tmpQuantity = _cursor.getInt(_cursorIndexOfQuantity);
            final String _tmpUnit;
            if (_cursor.isNull(_cursorIndexOfUnit)) {
              _tmpUnit = null;
            } else {
              _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
            }
            final Double _tmpEstimatedPrice;
            if (_cursor.isNull(_cursorIndexOfEstimatedPrice)) {
              _tmpEstimatedPrice = null;
            } else {
              _tmpEstimatedPrice = _cursor.getDouble(_cursorIndexOfEstimatedPrice);
            }
            final Double _tmpActualPrice;
            if (_cursor.isNull(_cursorIndexOfActualPrice)) {
              _tmpActualPrice = null;
            } else {
              _tmpActualPrice = _cursor.getDouble(_cursorIndexOfActualPrice);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final boolean _tmpIsCompleted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp != 0;
            final int _tmpPriority;
            _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            _item = new ShoppingListItem(_tmpId,_tmpShoppingListId,_tmpProductId,_tmpCustomItemName,_tmpQuantity,_tmpUnit,_tmpEstimatedPrice,_tmpActualPrice,_tmpNotes,_tmpIsCompleted,_tmpPriority,_tmpCreatedAt,_tmpUpdatedAt,_tmpCompletedAt);
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
  public LiveData<Integer> getItemCountForShoppingList(final long shoppingListId) {
    final String _sql = "SELECT COUNT(*) FROM shopping_list_items WHERE shoppingListId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, shoppingListId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"shopping_list_items"}, false, new Callable<Integer>() {
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
  public LiveData<Integer> getCompletedItemCountForShoppingList(final long shoppingListId) {
    final String _sql = "SELECT COUNT(*) FROM shopping_list_items WHERE shoppingListId = ? AND isCompleted = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, shoppingListId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"shopping_list_items"}, false, new Callable<Integer>() {
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
  public LiveData<Double> getTotalEstimatedCostForShoppingList(final long shoppingListId) {
    final String _sql = "SELECT SUM(estimatedPrice * quantity) FROM shopping_list_items WHERE shoppingListId = ? AND estimatedPrice IS NOT NULL";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, shoppingListId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"shopping_list_items"}, false, new Callable<Double>() {
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
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<Double> getTotalActualCostForShoppingList(final long shoppingListId) {
    final String _sql = "SELECT SUM(actualPrice * quantity) FROM shopping_list_items WHERE shoppingListId = ? AND actualPrice IS NOT NULL";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, shoppingListId);
    return __db.getInvalidationTracker().createLiveData(new String[] {"shopping_list_items"}, false, new Callable<Double>() {
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
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getItemsForShoppingListSuspend(final long shoppingListId,
      final Continuation<? super List<ShoppingListItem>> arg1) {
    final String _sql = "SELECT * FROM shopping_list_items WHERE shoppingListId = ? ORDER BY priority DESC, createdAt ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, shoppingListId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ShoppingListItem>>() {
      @Override
      @NonNull
      public List<ShoppingListItem> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfShoppingListId = CursorUtil.getColumnIndexOrThrow(_cursor, "shoppingListId");
          final int _cursorIndexOfProductId = CursorUtil.getColumnIndexOrThrow(_cursor, "productId");
          final int _cursorIndexOfCustomItemName = CursorUtil.getColumnIndexOrThrow(_cursor, "customItemName");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfUnit = CursorUtil.getColumnIndexOrThrow(_cursor, "unit");
          final int _cursorIndexOfEstimatedPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "estimatedPrice");
          final int _cursorIndexOfActualPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "actualPrice");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final List<ShoppingListItem> _result = new ArrayList<ShoppingListItem>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ShoppingListItem _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpShoppingListId;
            _tmpShoppingListId = _cursor.getLong(_cursorIndexOfShoppingListId);
            final Long _tmpProductId;
            if (_cursor.isNull(_cursorIndexOfProductId)) {
              _tmpProductId = null;
            } else {
              _tmpProductId = _cursor.getLong(_cursorIndexOfProductId);
            }
            final String _tmpCustomItemName;
            if (_cursor.isNull(_cursorIndexOfCustomItemName)) {
              _tmpCustomItemName = null;
            } else {
              _tmpCustomItemName = _cursor.getString(_cursorIndexOfCustomItemName);
            }
            final int _tmpQuantity;
            _tmpQuantity = _cursor.getInt(_cursorIndexOfQuantity);
            final String _tmpUnit;
            if (_cursor.isNull(_cursorIndexOfUnit)) {
              _tmpUnit = null;
            } else {
              _tmpUnit = _cursor.getString(_cursorIndexOfUnit);
            }
            final Double _tmpEstimatedPrice;
            if (_cursor.isNull(_cursorIndexOfEstimatedPrice)) {
              _tmpEstimatedPrice = null;
            } else {
              _tmpEstimatedPrice = _cursor.getDouble(_cursorIndexOfEstimatedPrice);
            }
            final Double _tmpActualPrice;
            if (_cursor.isNull(_cursorIndexOfActualPrice)) {
              _tmpActualPrice = null;
            } else {
              _tmpActualPrice = _cursor.getDouble(_cursorIndexOfActualPrice);
            }
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final boolean _tmpIsCompleted;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp != 0;
            final int _tmpPriority;
            _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            _item = new ShoppingListItem(_tmpId,_tmpShoppingListId,_tmpProductId,_tmpCustomItemName,_tmpQuantity,_tmpUnit,_tmpEstimatedPrice,_tmpActualPrice,_tmpNotes,_tmpIsCompleted,_tmpPriority,_tmpCreatedAt,_tmpUpdatedAt,_tmpCompletedAt);
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private void __fetchRelationshipproductsAscomExampleBudgetappDatabaseEntitiesProduct(
      @NonNull final LongSparseArray<Product> _map) {
    if (_map.isEmpty()) {
      return;
    }
    if (_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      RelationUtil.recursiveFetchLongSparseArray(_map, false, (map) -> {
        __fetchRelationshipproductsAscomExampleBudgetappDatabaseEntitiesProduct(map);
        return Unit.INSTANCE;
      });
      return;
    }
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `id`,`name`,`productCategoryId`,`hasDeposit`,`depositAmount`,`barcode`,`productName`,`description`,`amount`,`unit`,`createdAt`,`updatedAt` FROM `products` WHERE `id` IN (");
    final int _inputSize = _map.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int i = 0; i < _map.size(); i++) {
      final long _item = _map.keyAt(i);
      _stmt.bindLong(_argIndex, _item);
      _argIndex++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "id");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfId = 0;
      final int _cursorIndexOfName = 1;
      final int _cursorIndexOfProductCategoryId = 2;
      final int _cursorIndexOfHasDeposit = 3;
      final int _cursorIndexOfDepositAmount = 4;
      final int _cursorIndexOfBarcode = 5;
      final int _cursorIndexOfProductName = 6;
      final int _cursorIndexOfDescription = 7;
      final int _cursorIndexOfAmount = 8;
      final int _cursorIndexOfUnit = 9;
      final int _cursorIndexOfCreatedAt = 10;
      final int _cursorIndexOfUpdatedAt = 11;
      while (_cursor.moveToNext()) {
        final long _tmpKey;
        _tmpKey = _cursor.getLong(_itemKeyIndex);
        if (_map.containsKey(_tmpKey)) {
          final Product _item_1;
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
          _item_1 = new Product(_tmpId,_tmpName,_tmpProductCategoryId,_tmpHasDeposit,_tmpDepositAmount,_tmpBarcode,_tmpProductName,_tmpDescription,_tmpAmount,_tmpUnit,_tmpCreatedAt,_tmpUpdatedAt);
          _map.put(_tmpKey, _item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
