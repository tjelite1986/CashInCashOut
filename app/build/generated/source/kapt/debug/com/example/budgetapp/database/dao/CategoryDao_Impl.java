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
import com.example.budgetapp.database.entities.Category;
import com.example.budgetapp.database.entities.CategoryType;
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
import kotlinx.coroutines.flow.Flow;

@SuppressWarnings({"unchecked", "deprecation"})
public final class CategoryDao_Impl implements CategoryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Category> __insertionAdapterOfCategory;

  private final EntityDeletionOrUpdateAdapter<Category> __deletionAdapterOfCategory;

  private final EntityDeletionOrUpdateAdapter<Category> __updateAdapterOfCategory;

  private final SharedSQLiteStatement __preparedStmtOfDeactivateCategory;

  private final SharedSQLiteStatement __preparedStmtOfIncrementTransactionCount;

  private final SharedSQLiteStatement __preparedStmtOfDecrementTransactionCount;

  public CategoryDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCategory = new EntityInsertionAdapter<Category>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `categories` (`id`,`name`,`color`,`iconResName`,`type`,`parentCategoryId`,`isSubcategory`,`associatedKeywords`,`transactionCount`,`isActive`,`sortOrder`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Category entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getColor() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getColor());
        }
        if (entity.getIconResName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getIconResName());
        }
        statement.bindString(5, __CategoryType_enumToString(entity.getType()));
        if (entity.getParentCategoryId() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getParentCategoryId());
        }
        final int _tmp = entity.isSubcategory() ? 1 : 0;
        statement.bindLong(7, _tmp);
        if (entity.getAssociatedKeywords() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getAssociatedKeywords());
        }
        statement.bindLong(9, entity.getTransactionCount());
        final int _tmp_1 = entity.isActive() ? 1 : 0;
        statement.bindLong(10, _tmp_1);
        statement.bindLong(11, entity.getSortOrder());
      }
    };
    this.__deletionAdapterOfCategory = new EntityDeletionOrUpdateAdapter<Category>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `categories` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Category entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfCategory = new EntityDeletionOrUpdateAdapter<Category>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `categories` SET `id` = ?,`name` = ?,`color` = ?,`iconResName` = ?,`type` = ?,`parentCategoryId` = ?,`isSubcategory` = ?,`associatedKeywords` = ?,`transactionCount` = ?,`isActive` = ?,`sortOrder` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Category entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getColor() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getColor());
        }
        if (entity.getIconResName() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getIconResName());
        }
        statement.bindString(5, __CategoryType_enumToString(entity.getType()));
        if (entity.getParentCategoryId() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getParentCategoryId());
        }
        final int _tmp = entity.isSubcategory() ? 1 : 0;
        statement.bindLong(7, _tmp);
        if (entity.getAssociatedKeywords() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getAssociatedKeywords());
        }
        statement.bindLong(9, entity.getTransactionCount());
        final int _tmp_1 = entity.isActive() ? 1 : 0;
        statement.bindLong(10, _tmp_1);
        statement.bindLong(11, entity.getSortOrder());
        statement.bindLong(12, entity.getId());
      }
    };
    this.__preparedStmtOfDeactivateCategory = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE categories SET isActive = 0 WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfIncrementTransactionCount = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE categories SET transactionCount = transactionCount + 1 WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDecrementTransactionCount = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE categories SET transactionCount = transactionCount - 1 WHERE id = ? AND transactionCount > 0";
        return _query;
      }
    };
  }

  @Override
  public Object insertCategory(final Category category,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfCategory.insertAndReturnId(category);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteCategory(final Category category,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfCategory.handle(category);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateCategory(final Category category,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfCategory.handle(category);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deactivateCategory(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeactivateCategory.acquire();
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
          __preparedStmtOfDeactivateCategory.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object incrementTransactionCount(final long id,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfIncrementTransactionCount.acquire();
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
          __preparedStmtOfIncrementTransactionCount.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object decrementTransactionCount(final long id,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDecrementTransactionCount.acquire();
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
          __preparedStmtOfDecrementTransactionCount.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Category>> getAllActiveCategories() {
    final String _sql = "SELECT * FROM categories WHERE isActive = 1 ORDER BY sortOrder ASC, name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"categories"}, new Callable<List<Category>>() {
      @Override
      @NonNull
      public List<Category> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfIconResName = CursorUtil.getColumnIndexOrThrow(_cursor, "iconResName");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfParentCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "parentCategoryId");
          final int _cursorIndexOfIsSubcategory = CursorUtil.getColumnIndexOrThrow(_cursor, "isSubcategory");
          final int _cursorIndexOfAssociatedKeywords = CursorUtil.getColumnIndexOrThrow(_cursor, "associatedKeywords");
          final int _cursorIndexOfTransactionCount = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionCount");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfSortOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "sortOrder");
          final List<Category> _result = new ArrayList<Category>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Category _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpColor;
            if (_cursor.isNull(_cursorIndexOfColor)) {
              _tmpColor = null;
            } else {
              _tmpColor = _cursor.getString(_cursorIndexOfColor);
            }
            final String _tmpIconResName;
            if (_cursor.isNull(_cursorIndexOfIconResName)) {
              _tmpIconResName = null;
            } else {
              _tmpIconResName = _cursor.getString(_cursorIndexOfIconResName);
            }
            final CategoryType _tmpType;
            _tmpType = __CategoryType_stringToEnum(_cursor.getString(_cursorIndexOfType));
            final Long _tmpParentCategoryId;
            if (_cursor.isNull(_cursorIndexOfParentCategoryId)) {
              _tmpParentCategoryId = null;
            } else {
              _tmpParentCategoryId = _cursor.getLong(_cursorIndexOfParentCategoryId);
            }
            final boolean _tmpIsSubcategory;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSubcategory);
            _tmpIsSubcategory = _tmp != 0;
            final String _tmpAssociatedKeywords;
            if (_cursor.isNull(_cursorIndexOfAssociatedKeywords)) {
              _tmpAssociatedKeywords = null;
            } else {
              _tmpAssociatedKeywords = _cursor.getString(_cursorIndexOfAssociatedKeywords);
            }
            final int _tmpTransactionCount;
            _tmpTransactionCount = _cursor.getInt(_cursorIndexOfTransactionCount);
            final boolean _tmpIsActive;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_1 != 0;
            final int _tmpSortOrder;
            _tmpSortOrder = _cursor.getInt(_cursorIndexOfSortOrder);
            _item = new Category(_tmpId,_tmpName,_tmpColor,_tmpIconResName,_tmpType,_tmpParentCategoryId,_tmpIsSubcategory,_tmpAssociatedKeywords,_tmpTransactionCount,_tmpIsActive,_tmpSortOrder);
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
  public Flow<List<Category>> getCategoriesByType(final CategoryType type) {
    final String _sql = "SELECT * FROM categories WHERE type = ? AND isActive = 1 AND parentCategoryId IS NULL ORDER BY sortOrder ASC, name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, __CategoryType_enumToString(type));
    return CoroutinesRoom.createFlow(__db, false, new String[] {"categories"}, new Callable<List<Category>>() {
      @Override
      @NonNull
      public List<Category> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfIconResName = CursorUtil.getColumnIndexOrThrow(_cursor, "iconResName");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfParentCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "parentCategoryId");
          final int _cursorIndexOfIsSubcategory = CursorUtil.getColumnIndexOrThrow(_cursor, "isSubcategory");
          final int _cursorIndexOfAssociatedKeywords = CursorUtil.getColumnIndexOrThrow(_cursor, "associatedKeywords");
          final int _cursorIndexOfTransactionCount = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionCount");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfSortOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "sortOrder");
          final List<Category> _result = new ArrayList<Category>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Category _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpColor;
            if (_cursor.isNull(_cursorIndexOfColor)) {
              _tmpColor = null;
            } else {
              _tmpColor = _cursor.getString(_cursorIndexOfColor);
            }
            final String _tmpIconResName;
            if (_cursor.isNull(_cursorIndexOfIconResName)) {
              _tmpIconResName = null;
            } else {
              _tmpIconResName = _cursor.getString(_cursorIndexOfIconResName);
            }
            final CategoryType _tmpType;
            _tmpType = __CategoryType_stringToEnum(_cursor.getString(_cursorIndexOfType));
            final Long _tmpParentCategoryId;
            if (_cursor.isNull(_cursorIndexOfParentCategoryId)) {
              _tmpParentCategoryId = null;
            } else {
              _tmpParentCategoryId = _cursor.getLong(_cursorIndexOfParentCategoryId);
            }
            final boolean _tmpIsSubcategory;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSubcategory);
            _tmpIsSubcategory = _tmp != 0;
            final String _tmpAssociatedKeywords;
            if (_cursor.isNull(_cursorIndexOfAssociatedKeywords)) {
              _tmpAssociatedKeywords = null;
            } else {
              _tmpAssociatedKeywords = _cursor.getString(_cursorIndexOfAssociatedKeywords);
            }
            final int _tmpTransactionCount;
            _tmpTransactionCount = _cursor.getInt(_cursorIndexOfTransactionCount);
            final boolean _tmpIsActive;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_1 != 0;
            final int _tmpSortOrder;
            _tmpSortOrder = _cursor.getInt(_cursorIndexOfSortOrder);
            _item = new Category(_tmpId,_tmpName,_tmpColor,_tmpIconResName,_tmpType,_tmpParentCategoryId,_tmpIsSubcategory,_tmpAssociatedKeywords,_tmpTransactionCount,_tmpIsActive,_tmpSortOrder);
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
  public Flow<List<Category>> getSubcategories(final long parentId) {
    final String _sql = "SELECT * FROM categories WHERE parentCategoryId = ? AND isActive = 1 ORDER BY sortOrder ASC, name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, parentId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"categories"}, new Callable<List<Category>>() {
      @Override
      @NonNull
      public List<Category> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfIconResName = CursorUtil.getColumnIndexOrThrow(_cursor, "iconResName");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfParentCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "parentCategoryId");
          final int _cursorIndexOfIsSubcategory = CursorUtil.getColumnIndexOrThrow(_cursor, "isSubcategory");
          final int _cursorIndexOfAssociatedKeywords = CursorUtil.getColumnIndexOrThrow(_cursor, "associatedKeywords");
          final int _cursorIndexOfTransactionCount = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionCount");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfSortOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "sortOrder");
          final List<Category> _result = new ArrayList<Category>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Category _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpColor;
            if (_cursor.isNull(_cursorIndexOfColor)) {
              _tmpColor = null;
            } else {
              _tmpColor = _cursor.getString(_cursorIndexOfColor);
            }
            final String _tmpIconResName;
            if (_cursor.isNull(_cursorIndexOfIconResName)) {
              _tmpIconResName = null;
            } else {
              _tmpIconResName = _cursor.getString(_cursorIndexOfIconResName);
            }
            final CategoryType _tmpType;
            _tmpType = __CategoryType_stringToEnum(_cursor.getString(_cursorIndexOfType));
            final Long _tmpParentCategoryId;
            if (_cursor.isNull(_cursorIndexOfParentCategoryId)) {
              _tmpParentCategoryId = null;
            } else {
              _tmpParentCategoryId = _cursor.getLong(_cursorIndexOfParentCategoryId);
            }
            final boolean _tmpIsSubcategory;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSubcategory);
            _tmpIsSubcategory = _tmp != 0;
            final String _tmpAssociatedKeywords;
            if (_cursor.isNull(_cursorIndexOfAssociatedKeywords)) {
              _tmpAssociatedKeywords = null;
            } else {
              _tmpAssociatedKeywords = _cursor.getString(_cursorIndexOfAssociatedKeywords);
            }
            final int _tmpTransactionCount;
            _tmpTransactionCount = _cursor.getInt(_cursorIndexOfTransactionCount);
            final boolean _tmpIsActive;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_1 != 0;
            final int _tmpSortOrder;
            _tmpSortOrder = _cursor.getInt(_cursorIndexOfSortOrder);
            _item = new Category(_tmpId,_tmpName,_tmpColor,_tmpIconResName,_tmpType,_tmpParentCategoryId,_tmpIsSubcategory,_tmpAssociatedKeywords,_tmpTransactionCount,_tmpIsActive,_tmpSortOrder);
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
  public Object getCategoryById(final long id, final Continuation<? super Category> $completion) {
    final String _sql = "SELECT * FROM categories WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Category>() {
      @Override
      @Nullable
      public Category call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfIconResName = CursorUtil.getColumnIndexOrThrow(_cursor, "iconResName");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfParentCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "parentCategoryId");
          final int _cursorIndexOfIsSubcategory = CursorUtil.getColumnIndexOrThrow(_cursor, "isSubcategory");
          final int _cursorIndexOfAssociatedKeywords = CursorUtil.getColumnIndexOrThrow(_cursor, "associatedKeywords");
          final int _cursorIndexOfTransactionCount = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionCount");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfSortOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "sortOrder");
          final Category _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpColor;
            if (_cursor.isNull(_cursorIndexOfColor)) {
              _tmpColor = null;
            } else {
              _tmpColor = _cursor.getString(_cursorIndexOfColor);
            }
            final String _tmpIconResName;
            if (_cursor.isNull(_cursorIndexOfIconResName)) {
              _tmpIconResName = null;
            } else {
              _tmpIconResName = _cursor.getString(_cursorIndexOfIconResName);
            }
            final CategoryType _tmpType;
            _tmpType = __CategoryType_stringToEnum(_cursor.getString(_cursorIndexOfType));
            final Long _tmpParentCategoryId;
            if (_cursor.isNull(_cursorIndexOfParentCategoryId)) {
              _tmpParentCategoryId = null;
            } else {
              _tmpParentCategoryId = _cursor.getLong(_cursorIndexOfParentCategoryId);
            }
            final boolean _tmpIsSubcategory;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSubcategory);
            _tmpIsSubcategory = _tmp != 0;
            final String _tmpAssociatedKeywords;
            if (_cursor.isNull(_cursorIndexOfAssociatedKeywords)) {
              _tmpAssociatedKeywords = null;
            } else {
              _tmpAssociatedKeywords = _cursor.getString(_cursorIndexOfAssociatedKeywords);
            }
            final int _tmpTransactionCount;
            _tmpTransactionCount = _cursor.getInt(_cursorIndexOfTransactionCount);
            final boolean _tmpIsActive;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_1 != 0;
            final int _tmpSortOrder;
            _tmpSortOrder = _cursor.getInt(_cursorIndexOfSortOrder);
            _result = new Category(_tmpId,_tmpName,_tmpColor,_tmpIconResName,_tmpType,_tmpParentCategoryId,_tmpIsSubcategory,_tmpAssociatedKeywords,_tmpTransactionCount,_tmpIsActive,_tmpSortOrder);
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
  public Object checkDuplicateCategory(final String name, final CategoryType type,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM categories WHERE name = ? AND type = ? AND isActive = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (name == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, name);
    }
    _argIndex = 2;
    _statement.bindString(_argIndex, __CategoryType_enumToString(type));
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
  public Flow<List<Category>> getAllCategories() {
    final String _sql = "SELECT * FROM categories ORDER BY name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"categories"}, new Callable<List<Category>>() {
      @Override
      @NonNull
      public List<Category> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfIconResName = CursorUtil.getColumnIndexOrThrow(_cursor, "iconResName");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfParentCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "parentCategoryId");
          final int _cursorIndexOfIsSubcategory = CursorUtil.getColumnIndexOrThrow(_cursor, "isSubcategory");
          final int _cursorIndexOfAssociatedKeywords = CursorUtil.getColumnIndexOrThrow(_cursor, "associatedKeywords");
          final int _cursorIndexOfTransactionCount = CursorUtil.getColumnIndexOrThrow(_cursor, "transactionCount");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfSortOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "sortOrder");
          final List<Category> _result = new ArrayList<Category>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Category _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpColor;
            if (_cursor.isNull(_cursorIndexOfColor)) {
              _tmpColor = null;
            } else {
              _tmpColor = _cursor.getString(_cursorIndexOfColor);
            }
            final String _tmpIconResName;
            if (_cursor.isNull(_cursorIndexOfIconResName)) {
              _tmpIconResName = null;
            } else {
              _tmpIconResName = _cursor.getString(_cursorIndexOfIconResName);
            }
            final CategoryType _tmpType;
            _tmpType = __CategoryType_stringToEnum(_cursor.getString(_cursorIndexOfType));
            final Long _tmpParentCategoryId;
            if (_cursor.isNull(_cursorIndexOfParentCategoryId)) {
              _tmpParentCategoryId = null;
            } else {
              _tmpParentCategoryId = _cursor.getLong(_cursorIndexOfParentCategoryId);
            }
            final boolean _tmpIsSubcategory;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSubcategory);
            _tmpIsSubcategory = _tmp != 0;
            final String _tmpAssociatedKeywords;
            if (_cursor.isNull(_cursorIndexOfAssociatedKeywords)) {
              _tmpAssociatedKeywords = null;
            } else {
              _tmpAssociatedKeywords = _cursor.getString(_cursorIndexOfAssociatedKeywords);
            }
            final int _tmpTransactionCount;
            _tmpTransactionCount = _cursor.getInt(_cursorIndexOfTransactionCount);
            final boolean _tmpIsActive;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_1 != 0;
            final int _tmpSortOrder;
            _tmpSortOrder = _cursor.getInt(_cursorIndexOfSortOrder);
            _item = new Category(_tmpId,_tmpName,_tmpColor,_tmpIconResName,_tmpType,_tmpParentCategoryId,_tmpIsSubcategory,_tmpAssociatedKeywords,_tmpTransactionCount,_tmpIsActive,_tmpSortOrder);
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

  private String __CategoryType_enumToString(@NonNull final CategoryType _value) {
    switch (_value) {
      case EXPENSE: return "EXPENSE";
      case INCOME: return "INCOME";
      default: throw new IllegalArgumentException("Can't convert enum to string, unknown enum value: " + _value);
    }
  }

  private CategoryType __CategoryType_stringToEnum(@NonNull final String _value) {
    switch (_value) {
      case "EXPENSE": return CategoryType.EXPENSE;
      case "INCOME": return CategoryType.INCOME;
      default: throw new IllegalArgumentException("Can't convert value to enum, unknown value: " + _value);
    }
  }
}
