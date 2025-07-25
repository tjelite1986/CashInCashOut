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
import com.example.budgetapp.database.entities.AnalyticsTypeConverters;
import com.example.budgetapp.database.entities.FinancialGoal;
import com.example.budgetapp.database.entities.GoalPriority;
import com.example.budgetapp.database.entities.GoalType;
import com.example.budgetapp.database.entities.ReminderFrequency;
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
public final class FinancialGoalDao_Impl implements FinancialGoalDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<FinancialGoal> __insertionAdapterOfFinancialGoal;

  private final AnalyticsTypeConverters __analyticsTypeConverters = new AnalyticsTypeConverters();

  private final EntityDeletionOrUpdateAdapter<FinancialGoal> __deletionAdapterOfFinancialGoal;

  private final EntityDeletionOrUpdateAdapter<FinancialGoal> __updateAdapterOfFinancialGoal;

  private final SharedSQLiteStatement __preparedStmtOfUpdateGoalProgress;

  private final SharedSQLiteStatement __preparedStmtOfAddToGoalProgress;

  private final SharedSQLiteStatement __preparedStmtOfMarkGoalCompleted;

  private final SharedSQLiteStatement __preparedStmtOfDeactivateGoal;

  private final SharedSQLiteStatement __preparedStmtOfActivateGoal;

  private final SharedSQLiteStatement __preparedStmtOfDeleteGoalById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteOldCompletedGoals;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllGoals;

  public FinancialGoalDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFinancialGoal = new EntityInsertionAdapter<FinancialGoal>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `financial_goals` (`id`,`name`,`description`,`goalType`,`targetAmount`,`currentAmount`,`targetDate`,`monthlyTarget`,`priority`,`category`,`isActive`,`isCompleted`,`completedAt`,`autoTransferEnabled`,`autoTransferAmount`,`linkedAccountId`,`reminderEnabled`,`reminderFrequency`,`createdAt`,`updatedAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FinancialGoal entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDescription());
        }
        final String _tmp = __analyticsTypeConverters.fromGoalType(entity.getGoalType());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, _tmp);
        }
        statement.bindDouble(5, entity.getTargetAmount());
        statement.bindDouble(6, entity.getCurrentAmount());
        if (entity.getTargetDate() == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.getTargetDate());
        }
        if (entity.getMonthlyTarget() == null) {
          statement.bindNull(8);
        } else {
          statement.bindDouble(8, entity.getMonthlyTarget());
        }
        final String _tmp_1 = __analyticsTypeConverters.fromGoalPriority(entity.getPriority());
        if (_tmp_1 == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, _tmp_1);
        }
        if (entity.getCategory() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getCategory());
        }
        final int _tmp_2 = entity.isActive() ? 1 : 0;
        statement.bindLong(11, _tmp_2);
        final int _tmp_3 = entity.isCompleted() ? 1 : 0;
        statement.bindLong(12, _tmp_3);
        if (entity.getCompletedAt() == null) {
          statement.bindNull(13);
        } else {
          statement.bindLong(13, entity.getCompletedAt());
        }
        final int _tmp_4 = entity.getAutoTransferEnabled() ? 1 : 0;
        statement.bindLong(14, _tmp_4);
        if (entity.getAutoTransferAmount() == null) {
          statement.bindNull(15);
        } else {
          statement.bindDouble(15, entity.getAutoTransferAmount());
        }
        if (entity.getLinkedAccountId() == null) {
          statement.bindNull(16);
        } else {
          statement.bindString(16, entity.getLinkedAccountId());
        }
        final int _tmp_5 = entity.getReminderEnabled() ? 1 : 0;
        statement.bindLong(17, _tmp_5);
        final String _tmp_6 = __analyticsTypeConverters.fromReminderFrequency(entity.getReminderFrequency());
        if (_tmp_6 == null) {
          statement.bindNull(18);
        } else {
          statement.bindString(18, _tmp_6);
        }
        statement.bindLong(19, entity.getCreatedAt());
        statement.bindLong(20, entity.getUpdatedAt());
      }
    };
    this.__deletionAdapterOfFinancialGoal = new EntityDeletionOrUpdateAdapter<FinancialGoal>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `financial_goals` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FinancialGoal entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfFinancialGoal = new EntityDeletionOrUpdateAdapter<FinancialGoal>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `financial_goals` SET `id` = ?,`name` = ?,`description` = ?,`goalType` = ?,`targetAmount` = ?,`currentAmount` = ?,`targetDate` = ?,`monthlyTarget` = ?,`priority` = ?,`category` = ?,`isActive` = ?,`isCompleted` = ?,`completedAt` = ?,`autoTransferEnabled` = ?,`autoTransferAmount` = ?,`linkedAccountId` = ?,`reminderEnabled` = ?,`reminderFrequency` = ?,`createdAt` = ?,`updatedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FinancialGoal entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getDescription());
        }
        final String _tmp = __analyticsTypeConverters.fromGoalType(entity.getGoalType());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, _tmp);
        }
        statement.bindDouble(5, entity.getTargetAmount());
        statement.bindDouble(6, entity.getCurrentAmount());
        if (entity.getTargetDate() == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.getTargetDate());
        }
        if (entity.getMonthlyTarget() == null) {
          statement.bindNull(8);
        } else {
          statement.bindDouble(8, entity.getMonthlyTarget());
        }
        final String _tmp_1 = __analyticsTypeConverters.fromGoalPriority(entity.getPriority());
        if (_tmp_1 == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, _tmp_1);
        }
        if (entity.getCategory() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getCategory());
        }
        final int _tmp_2 = entity.isActive() ? 1 : 0;
        statement.bindLong(11, _tmp_2);
        final int _tmp_3 = entity.isCompleted() ? 1 : 0;
        statement.bindLong(12, _tmp_3);
        if (entity.getCompletedAt() == null) {
          statement.bindNull(13);
        } else {
          statement.bindLong(13, entity.getCompletedAt());
        }
        final int _tmp_4 = entity.getAutoTransferEnabled() ? 1 : 0;
        statement.bindLong(14, _tmp_4);
        if (entity.getAutoTransferAmount() == null) {
          statement.bindNull(15);
        } else {
          statement.bindDouble(15, entity.getAutoTransferAmount());
        }
        if (entity.getLinkedAccountId() == null) {
          statement.bindNull(16);
        } else {
          statement.bindString(16, entity.getLinkedAccountId());
        }
        final int _tmp_5 = entity.getReminderEnabled() ? 1 : 0;
        statement.bindLong(17, _tmp_5);
        final String _tmp_6 = __analyticsTypeConverters.fromReminderFrequency(entity.getReminderFrequency());
        if (_tmp_6 == null) {
          statement.bindNull(18);
        } else {
          statement.bindString(18, _tmp_6);
        }
        statement.bindLong(19, entity.getCreatedAt());
        statement.bindLong(20, entity.getUpdatedAt());
        statement.bindLong(21, entity.getId());
      }
    };
    this.__preparedStmtOfUpdateGoalProgress = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE financial_goals SET currentAmount = ?, updatedAt = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfAddToGoalProgress = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE financial_goals SET currentAmount = currentAmount + ?, updatedAt = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfMarkGoalCompleted = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "\n"
                + "        UPDATE financial_goals \n"
                + "        SET isCompleted = 1, \n"
                + "            completedAt = ?, \n"
                + "            updatedAt = ? \n"
                + "        WHERE id = ?\n"
                + "    ";
        return _query;
      }
    };
    this.__preparedStmtOfDeactivateGoal = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE financial_goals SET isActive = 0, updatedAt = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfActivateGoal = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE financial_goals SET isActive = 1, updatedAt = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteGoalById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM financial_goals WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteOldCompletedGoals = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM financial_goals WHERE isCompleted = 1 AND completedAt < ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllGoals = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM financial_goals";
        return _query;
      }
    };
  }

  @Override
  public Object insertGoal(final FinancialGoal goal, final Continuation<? super Long> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfFinancialGoal.insertAndReturnId(goal);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object insertGoals(final List<FinancialGoal> goals,
      final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfFinancialGoal.insert(goals);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object deleteGoal(final FinancialGoal goal, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfFinancialGoal.handle(goal);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object updateGoal(final FinancialGoal goal, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfFinancialGoal.handle(goal);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object updateGoalProgress(final long goalId, final double amount, final long timestamp,
      final Continuation<? super Unit> arg3) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateGoalProgress.acquire();
        int _argIndex = 1;
        _stmt.bindDouble(_argIndex, amount);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, timestamp);
        _argIndex = 3;
        _stmt.bindLong(_argIndex, goalId);
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
          __preparedStmtOfUpdateGoalProgress.release(_stmt);
        }
      }
    }, arg3);
  }

  @Override
  public Object addToGoalProgress(final long goalId, final double amount, final long timestamp,
      final Continuation<? super Unit> arg3) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfAddToGoalProgress.acquire();
        int _argIndex = 1;
        _stmt.bindDouble(_argIndex, amount);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, timestamp);
        _argIndex = 3;
        _stmt.bindLong(_argIndex, goalId);
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
          __preparedStmtOfAddToGoalProgress.release(_stmt);
        }
      }
    }, arg3);
  }

  @Override
  public Object markGoalCompleted(final long goalId, final long timestamp,
      final Continuation<? super Unit> arg2) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfMarkGoalCompleted.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, timestamp);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, timestamp);
        _argIndex = 3;
        _stmt.bindLong(_argIndex, goalId);
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
          __preparedStmtOfMarkGoalCompleted.release(_stmt);
        }
      }
    }, arg2);
  }

  @Override
  public Object deactivateGoal(final long goalId, final long timestamp,
      final Continuation<? super Unit> arg2) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeactivateGoal.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, timestamp);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, goalId);
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
          __preparedStmtOfDeactivateGoal.release(_stmt);
        }
      }
    }, arg2);
  }

  @Override
  public Object activateGoal(final long goalId, final long timestamp,
      final Continuation<? super Unit> arg2) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfActivateGoal.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, timestamp);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, goalId);
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
          __preparedStmtOfActivateGoal.release(_stmt);
        }
      }
    }, arg2);
  }

  @Override
  public Object deleteGoalById(final long goalId, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteGoalById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, goalId);
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
          __preparedStmtOfDeleteGoalById.release(_stmt);
        }
      }
    }, arg1);
  }

  @Override
  public Object deleteOldCompletedGoals(final long cutoffTime,
      final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteOldCompletedGoals.acquire();
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
          __preparedStmtOfDeleteOldCompletedGoals.release(_stmt);
        }
      }
    }, arg1);
  }

  @Override
  public Object deleteAllGoals(final Continuation<? super Unit> arg0) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllGoals.acquire();
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
          __preparedStmtOfDeleteAllGoals.release(_stmt);
        }
      }
    }, arg0);
  }

  @Override
  public Flow<List<FinancialGoal>> getAllGoals() {
    final String _sql = "SELECT * FROM financial_goals ORDER BY priority DESC, targetDate ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"financial_goals"}, new Callable<List<FinancialGoal>>() {
      @Override
      @NonNull
      public List<FinancialGoal> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfGoalType = CursorUtil.getColumnIndexOrThrow(_cursor, "goalType");
          final int _cursorIndexOfTargetAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "targetAmount");
          final int _cursorIndexOfCurrentAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "currentAmount");
          final int _cursorIndexOfTargetDate = CursorUtil.getColumnIndexOrThrow(_cursor, "targetDate");
          final int _cursorIndexOfMonthlyTarget = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyTarget");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfAutoTransferEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "autoTransferEnabled");
          final int _cursorIndexOfAutoTransferAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "autoTransferAmount");
          final int _cursorIndexOfLinkedAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "linkedAccountId");
          final int _cursorIndexOfReminderEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderEnabled");
          final int _cursorIndexOfReminderFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderFrequency");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<FinancialGoal> _result = new ArrayList<FinancialGoal>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FinancialGoal _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final GoalType _tmpGoalType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfGoalType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfGoalType);
            }
            _tmpGoalType = __analyticsTypeConverters.toGoalType(_tmp);
            final double _tmpTargetAmount;
            _tmpTargetAmount = _cursor.getDouble(_cursorIndexOfTargetAmount);
            final double _tmpCurrentAmount;
            _tmpCurrentAmount = _cursor.getDouble(_cursorIndexOfCurrentAmount);
            final Long _tmpTargetDate;
            if (_cursor.isNull(_cursorIndexOfTargetDate)) {
              _tmpTargetDate = null;
            } else {
              _tmpTargetDate = _cursor.getLong(_cursorIndexOfTargetDate);
            }
            final Double _tmpMonthlyTarget;
            if (_cursor.isNull(_cursorIndexOfMonthlyTarget)) {
              _tmpMonthlyTarget = null;
            } else {
              _tmpMonthlyTarget = _cursor.getDouble(_cursorIndexOfMonthlyTarget);
            }
            final GoalPriority _tmpPriority;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfPriority)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfPriority);
            }
            _tmpPriority = __analyticsTypeConverters.toGoalPriority(_tmp_1);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final boolean _tmpIsActive;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_2 != 0;
            final boolean _tmpIsCompleted;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_3 != 0;
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            final boolean _tmpAutoTransferEnabled;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfAutoTransferEnabled);
            _tmpAutoTransferEnabled = _tmp_4 != 0;
            final Double _tmpAutoTransferAmount;
            if (_cursor.isNull(_cursorIndexOfAutoTransferAmount)) {
              _tmpAutoTransferAmount = null;
            } else {
              _tmpAutoTransferAmount = _cursor.getDouble(_cursorIndexOfAutoTransferAmount);
            }
            final String _tmpLinkedAccountId;
            if (_cursor.isNull(_cursorIndexOfLinkedAccountId)) {
              _tmpLinkedAccountId = null;
            } else {
              _tmpLinkedAccountId = _cursor.getString(_cursorIndexOfLinkedAccountId);
            }
            final boolean _tmpReminderEnabled;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfReminderEnabled);
            _tmpReminderEnabled = _tmp_5 != 0;
            final ReminderFrequency _tmpReminderFrequency;
            final String _tmp_6;
            if (_cursor.isNull(_cursorIndexOfReminderFrequency)) {
              _tmp_6 = null;
            } else {
              _tmp_6 = _cursor.getString(_cursorIndexOfReminderFrequency);
            }
            _tmpReminderFrequency = __analyticsTypeConverters.toReminderFrequency(_tmp_6);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new FinancialGoal(_tmpId,_tmpName,_tmpDescription,_tmpGoalType,_tmpTargetAmount,_tmpCurrentAmount,_tmpTargetDate,_tmpMonthlyTarget,_tmpPriority,_tmpCategory,_tmpIsActive,_tmpIsCompleted,_tmpCompletedAt,_tmpAutoTransferEnabled,_tmpAutoTransferAmount,_tmpLinkedAccountId,_tmpReminderEnabled,_tmpReminderFrequency,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<FinancialGoal>> getActiveGoals() {
    final String _sql = "SELECT * FROM financial_goals WHERE isActive = 1 ORDER BY priority DESC, targetDate ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"financial_goals"}, new Callable<List<FinancialGoal>>() {
      @Override
      @NonNull
      public List<FinancialGoal> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfGoalType = CursorUtil.getColumnIndexOrThrow(_cursor, "goalType");
          final int _cursorIndexOfTargetAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "targetAmount");
          final int _cursorIndexOfCurrentAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "currentAmount");
          final int _cursorIndexOfTargetDate = CursorUtil.getColumnIndexOrThrow(_cursor, "targetDate");
          final int _cursorIndexOfMonthlyTarget = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyTarget");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfAutoTransferEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "autoTransferEnabled");
          final int _cursorIndexOfAutoTransferAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "autoTransferAmount");
          final int _cursorIndexOfLinkedAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "linkedAccountId");
          final int _cursorIndexOfReminderEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderEnabled");
          final int _cursorIndexOfReminderFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderFrequency");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<FinancialGoal> _result = new ArrayList<FinancialGoal>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FinancialGoal _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final GoalType _tmpGoalType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfGoalType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfGoalType);
            }
            _tmpGoalType = __analyticsTypeConverters.toGoalType(_tmp);
            final double _tmpTargetAmount;
            _tmpTargetAmount = _cursor.getDouble(_cursorIndexOfTargetAmount);
            final double _tmpCurrentAmount;
            _tmpCurrentAmount = _cursor.getDouble(_cursorIndexOfCurrentAmount);
            final Long _tmpTargetDate;
            if (_cursor.isNull(_cursorIndexOfTargetDate)) {
              _tmpTargetDate = null;
            } else {
              _tmpTargetDate = _cursor.getLong(_cursorIndexOfTargetDate);
            }
            final Double _tmpMonthlyTarget;
            if (_cursor.isNull(_cursorIndexOfMonthlyTarget)) {
              _tmpMonthlyTarget = null;
            } else {
              _tmpMonthlyTarget = _cursor.getDouble(_cursorIndexOfMonthlyTarget);
            }
            final GoalPriority _tmpPriority;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfPriority)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfPriority);
            }
            _tmpPriority = __analyticsTypeConverters.toGoalPriority(_tmp_1);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final boolean _tmpIsActive;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_2 != 0;
            final boolean _tmpIsCompleted;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_3 != 0;
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            final boolean _tmpAutoTransferEnabled;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfAutoTransferEnabled);
            _tmpAutoTransferEnabled = _tmp_4 != 0;
            final Double _tmpAutoTransferAmount;
            if (_cursor.isNull(_cursorIndexOfAutoTransferAmount)) {
              _tmpAutoTransferAmount = null;
            } else {
              _tmpAutoTransferAmount = _cursor.getDouble(_cursorIndexOfAutoTransferAmount);
            }
            final String _tmpLinkedAccountId;
            if (_cursor.isNull(_cursorIndexOfLinkedAccountId)) {
              _tmpLinkedAccountId = null;
            } else {
              _tmpLinkedAccountId = _cursor.getString(_cursorIndexOfLinkedAccountId);
            }
            final boolean _tmpReminderEnabled;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfReminderEnabled);
            _tmpReminderEnabled = _tmp_5 != 0;
            final ReminderFrequency _tmpReminderFrequency;
            final String _tmp_6;
            if (_cursor.isNull(_cursorIndexOfReminderFrequency)) {
              _tmp_6 = null;
            } else {
              _tmp_6 = _cursor.getString(_cursorIndexOfReminderFrequency);
            }
            _tmpReminderFrequency = __analyticsTypeConverters.toReminderFrequency(_tmp_6);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new FinancialGoal(_tmpId,_tmpName,_tmpDescription,_tmpGoalType,_tmpTargetAmount,_tmpCurrentAmount,_tmpTargetDate,_tmpMonthlyTarget,_tmpPriority,_tmpCategory,_tmpIsActive,_tmpIsCompleted,_tmpCompletedAt,_tmpAutoTransferEnabled,_tmpAutoTransferAmount,_tmpLinkedAccountId,_tmpReminderEnabled,_tmpReminderFrequency,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<FinancialGoal>> getCompletedGoals() {
    final String _sql = "SELECT * FROM financial_goals WHERE isCompleted = 1 ORDER BY completedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"financial_goals"}, new Callable<List<FinancialGoal>>() {
      @Override
      @NonNull
      public List<FinancialGoal> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfGoalType = CursorUtil.getColumnIndexOrThrow(_cursor, "goalType");
          final int _cursorIndexOfTargetAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "targetAmount");
          final int _cursorIndexOfCurrentAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "currentAmount");
          final int _cursorIndexOfTargetDate = CursorUtil.getColumnIndexOrThrow(_cursor, "targetDate");
          final int _cursorIndexOfMonthlyTarget = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyTarget");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfAutoTransferEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "autoTransferEnabled");
          final int _cursorIndexOfAutoTransferAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "autoTransferAmount");
          final int _cursorIndexOfLinkedAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "linkedAccountId");
          final int _cursorIndexOfReminderEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderEnabled");
          final int _cursorIndexOfReminderFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderFrequency");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<FinancialGoal> _result = new ArrayList<FinancialGoal>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FinancialGoal _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final GoalType _tmpGoalType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfGoalType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfGoalType);
            }
            _tmpGoalType = __analyticsTypeConverters.toGoalType(_tmp);
            final double _tmpTargetAmount;
            _tmpTargetAmount = _cursor.getDouble(_cursorIndexOfTargetAmount);
            final double _tmpCurrentAmount;
            _tmpCurrentAmount = _cursor.getDouble(_cursorIndexOfCurrentAmount);
            final Long _tmpTargetDate;
            if (_cursor.isNull(_cursorIndexOfTargetDate)) {
              _tmpTargetDate = null;
            } else {
              _tmpTargetDate = _cursor.getLong(_cursorIndexOfTargetDate);
            }
            final Double _tmpMonthlyTarget;
            if (_cursor.isNull(_cursorIndexOfMonthlyTarget)) {
              _tmpMonthlyTarget = null;
            } else {
              _tmpMonthlyTarget = _cursor.getDouble(_cursorIndexOfMonthlyTarget);
            }
            final GoalPriority _tmpPriority;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfPriority)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfPriority);
            }
            _tmpPriority = __analyticsTypeConverters.toGoalPriority(_tmp_1);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final boolean _tmpIsActive;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_2 != 0;
            final boolean _tmpIsCompleted;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_3 != 0;
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            final boolean _tmpAutoTransferEnabled;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfAutoTransferEnabled);
            _tmpAutoTransferEnabled = _tmp_4 != 0;
            final Double _tmpAutoTransferAmount;
            if (_cursor.isNull(_cursorIndexOfAutoTransferAmount)) {
              _tmpAutoTransferAmount = null;
            } else {
              _tmpAutoTransferAmount = _cursor.getDouble(_cursorIndexOfAutoTransferAmount);
            }
            final String _tmpLinkedAccountId;
            if (_cursor.isNull(_cursorIndexOfLinkedAccountId)) {
              _tmpLinkedAccountId = null;
            } else {
              _tmpLinkedAccountId = _cursor.getString(_cursorIndexOfLinkedAccountId);
            }
            final boolean _tmpReminderEnabled;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfReminderEnabled);
            _tmpReminderEnabled = _tmp_5 != 0;
            final ReminderFrequency _tmpReminderFrequency;
            final String _tmp_6;
            if (_cursor.isNull(_cursorIndexOfReminderFrequency)) {
              _tmp_6 = null;
            } else {
              _tmp_6 = _cursor.getString(_cursorIndexOfReminderFrequency);
            }
            _tmpReminderFrequency = __analyticsTypeConverters.toReminderFrequency(_tmp_6);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new FinancialGoal(_tmpId,_tmpName,_tmpDescription,_tmpGoalType,_tmpTargetAmount,_tmpCurrentAmount,_tmpTargetDate,_tmpMonthlyTarget,_tmpPriority,_tmpCategory,_tmpIsActive,_tmpIsCompleted,_tmpCompletedAt,_tmpAutoTransferEnabled,_tmpAutoTransferAmount,_tmpLinkedAccountId,_tmpReminderEnabled,_tmpReminderFrequency,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<FinancialGoal>> getGoalsByType(final GoalType type) {
    final String _sql = "SELECT * FROM financial_goals WHERE goalType = ? ORDER BY priority DESC, targetDate ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __analyticsTypeConverters.fromGoalType(type);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"financial_goals"}, new Callable<List<FinancialGoal>>() {
      @Override
      @NonNull
      public List<FinancialGoal> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfGoalType = CursorUtil.getColumnIndexOrThrow(_cursor, "goalType");
          final int _cursorIndexOfTargetAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "targetAmount");
          final int _cursorIndexOfCurrentAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "currentAmount");
          final int _cursorIndexOfTargetDate = CursorUtil.getColumnIndexOrThrow(_cursor, "targetDate");
          final int _cursorIndexOfMonthlyTarget = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyTarget");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfAutoTransferEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "autoTransferEnabled");
          final int _cursorIndexOfAutoTransferAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "autoTransferAmount");
          final int _cursorIndexOfLinkedAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "linkedAccountId");
          final int _cursorIndexOfReminderEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderEnabled");
          final int _cursorIndexOfReminderFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderFrequency");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<FinancialGoal> _result = new ArrayList<FinancialGoal>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FinancialGoal _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final GoalType _tmpGoalType;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfGoalType)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfGoalType);
            }
            _tmpGoalType = __analyticsTypeConverters.toGoalType(_tmp_1);
            final double _tmpTargetAmount;
            _tmpTargetAmount = _cursor.getDouble(_cursorIndexOfTargetAmount);
            final double _tmpCurrentAmount;
            _tmpCurrentAmount = _cursor.getDouble(_cursorIndexOfCurrentAmount);
            final Long _tmpTargetDate;
            if (_cursor.isNull(_cursorIndexOfTargetDate)) {
              _tmpTargetDate = null;
            } else {
              _tmpTargetDate = _cursor.getLong(_cursorIndexOfTargetDate);
            }
            final Double _tmpMonthlyTarget;
            if (_cursor.isNull(_cursorIndexOfMonthlyTarget)) {
              _tmpMonthlyTarget = null;
            } else {
              _tmpMonthlyTarget = _cursor.getDouble(_cursorIndexOfMonthlyTarget);
            }
            final GoalPriority _tmpPriority;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfPriority)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfPriority);
            }
            _tmpPriority = __analyticsTypeConverters.toGoalPriority(_tmp_2);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final boolean _tmpIsActive;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_3 != 0;
            final boolean _tmpIsCompleted;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_4 != 0;
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            final boolean _tmpAutoTransferEnabled;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfAutoTransferEnabled);
            _tmpAutoTransferEnabled = _tmp_5 != 0;
            final Double _tmpAutoTransferAmount;
            if (_cursor.isNull(_cursorIndexOfAutoTransferAmount)) {
              _tmpAutoTransferAmount = null;
            } else {
              _tmpAutoTransferAmount = _cursor.getDouble(_cursorIndexOfAutoTransferAmount);
            }
            final String _tmpLinkedAccountId;
            if (_cursor.isNull(_cursorIndexOfLinkedAccountId)) {
              _tmpLinkedAccountId = null;
            } else {
              _tmpLinkedAccountId = _cursor.getString(_cursorIndexOfLinkedAccountId);
            }
            final boolean _tmpReminderEnabled;
            final int _tmp_6;
            _tmp_6 = _cursor.getInt(_cursorIndexOfReminderEnabled);
            _tmpReminderEnabled = _tmp_6 != 0;
            final ReminderFrequency _tmpReminderFrequency;
            final String _tmp_7;
            if (_cursor.isNull(_cursorIndexOfReminderFrequency)) {
              _tmp_7 = null;
            } else {
              _tmp_7 = _cursor.getString(_cursorIndexOfReminderFrequency);
            }
            _tmpReminderFrequency = __analyticsTypeConverters.toReminderFrequency(_tmp_7);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new FinancialGoal(_tmpId,_tmpName,_tmpDescription,_tmpGoalType,_tmpTargetAmount,_tmpCurrentAmount,_tmpTargetDate,_tmpMonthlyTarget,_tmpPriority,_tmpCategory,_tmpIsActive,_tmpIsCompleted,_tmpCompletedAt,_tmpAutoTransferEnabled,_tmpAutoTransferAmount,_tmpLinkedAccountId,_tmpReminderEnabled,_tmpReminderFrequency,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Flow<List<FinancialGoal>> getGoalsByPriority(final GoalPriority priority) {
    final String _sql = "SELECT * FROM financial_goals WHERE priority = ? ORDER BY targetDate ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final String _tmp = __analyticsTypeConverters.fromGoalPriority(priority);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"financial_goals"}, new Callable<List<FinancialGoal>>() {
      @Override
      @NonNull
      public List<FinancialGoal> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfGoalType = CursorUtil.getColumnIndexOrThrow(_cursor, "goalType");
          final int _cursorIndexOfTargetAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "targetAmount");
          final int _cursorIndexOfCurrentAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "currentAmount");
          final int _cursorIndexOfTargetDate = CursorUtil.getColumnIndexOrThrow(_cursor, "targetDate");
          final int _cursorIndexOfMonthlyTarget = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyTarget");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfAutoTransferEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "autoTransferEnabled");
          final int _cursorIndexOfAutoTransferAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "autoTransferAmount");
          final int _cursorIndexOfLinkedAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "linkedAccountId");
          final int _cursorIndexOfReminderEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderEnabled");
          final int _cursorIndexOfReminderFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderFrequency");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<FinancialGoal> _result = new ArrayList<FinancialGoal>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FinancialGoal _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final GoalType _tmpGoalType;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfGoalType)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfGoalType);
            }
            _tmpGoalType = __analyticsTypeConverters.toGoalType(_tmp_1);
            final double _tmpTargetAmount;
            _tmpTargetAmount = _cursor.getDouble(_cursorIndexOfTargetAmount);
            final double _tmpCurrentAmount;
            _tmpCurrentAmount = _cursor.getDouble(_cursorIndexOfCurrentAmount);
            final Long _tmpTargetDate;
            if (_cursor.isNull(_cursorIndexOfTargetDate)) {
              _tmpTargetDate = null;
            } else {
              _tmpTargetDate = _cursor.getLong(_cursorIndexOfTargetDate);
            }
            final Double _tmpMonthlyTarget;
            if (_cursor.isNull(_cursorIndexOfMonthlyTarget)) {
              _tmpMonthlyTarget = null;
            } else {
              _tmpMonthlyTarget = _cursor.getDouble(_cursorIndexOfMonthlyTarget);
            }
            final GoalPriority _tmpPriority;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfPriority)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfPriority);
            }
            _tmpPriority = __analyticsTypeConverters.toGoalPriority(_tmp_2);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final boolean _tmpIsActive;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_3 != 0;
            final boolean _tmpIsCompleted;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_4 != 0;
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            final boolean _tmpAutoTransferEnabled;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfAutoTransferEnabled);
            _tmpAutoTransferEnabled = _tmp_5 != 0;
            final Double _tmpAutoTransferAmount;
            if (_cursor.isNull(_cursorIndexOfAutoTransferAmount)) {
              _tmpAutoTransferAmount = null;
            } else {
              _tmpAutoTransferAmount = _cursor.getDouble(_cursorIndexOfAutoTransferAmount);
            }
            final String _tmpLinkedAccountId;
            if (_cursor.isNull(_cursorIndexOfLinkedAccountId)) {
              _tmpLinkedAccountId = null;
            } else {
              _tmpLinkedAccountId = _cursor.getString(_cursorIndexOfLinkedAccountId);
            }
            final boolean _tmpReminderEnabled;
            final int _tmp_6;
            _tmp_6 = _cursor.getInt(_cursorIndexOfReminderEnabled);
            _tmpReminderEnabled = _tmp_6 != 0;
            final ReminderFrequency _tmpReminderFrequency;
            final String _tmp_7;
            if (_cursor.isNull(_cursorIndexOfReminderFrequency)) {
              _tmp_7 = null;
            } else {
              _tmp_7 = _cursor.getString(_cursorIndexOfReminderFrequency);
            }
            _tmpReminderFrequency = __analyticsTypeConverters.toReminderFrequency(_tmp_7);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new FinancialGoal(_tmpId,_tmpName,_tmpDescription,_tmpGoalType,_tmpTargetAmount,_tmpCurrentAmount,_tmpTargetDate,_tmpMonthlyTarget,_tmpPriority,_tmpCategory,_tmpIsActive,_tmpIsCompleted,_tmpCompletedAt,_tmpAutoTransferEnabled,_tmpAutoTransferAmount,_tmpLinkedAccountId,_tmpReminderEnabled,_tmpReminderFrequency,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Object getGoalById(final long goalId, final Continuation<? super FinancialGoal> arg1) {
    final String _sql = "SELECT * FROM financial_goals WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, goalId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<FinancialGoal>() {
      @Override
      @Nullable
      public FinancialGoal call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfGoalType = CursorUtil.getColumnIndexOrThrow(_cursor, "goalType");
          final int _cursorIndexOfTargetAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "targetAmount");
          final int _cursorIndexOfCurrentAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "currentAmount");
          final int _cursorIndexOfTargetDate = CursorUtil.getColumnIndexOrThrow(_cursor, "targetDate");
          final int _cursorIndexOfMonthlyTarget = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyTarget");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfAutoTransferEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "autoTransferEnabled");
          final int _cursorIndexOfAutoTransferAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "autoTransferAmount");
          final int _cursorIndexOfLinkedAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "linkedAccountId");
          final int _cursorIndexOfReminderEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderEnabled");
          final int _cursorIndexOfReminderFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderFrequency");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final FinancialGoal _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final GoalType _tmpGoalType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfGoalType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfGoalType);
            }
            _tmpGoalType = __analyticsTypeConverters.toGoalType(_tmp);
            final double _tmpTargetAmount;
            _tmpTargetAmount = _cursor.getDouble(_cursorIndexOfTargetAmount);
            final double _tmpCurrentAmount;
            _tmpCurrentAmount = _cursor.getDouble(_cursorIndexOfCurrentAmount);
            final Long _tmpTargetDate;
            if (_cursor.isNull(_cursorIndexOfTargetDate)) {
              _tmpTargetDate = null;
            } else {
              _tmpTargetDate = _cursor.getLong(_cursorIndexOfTargetDate);
            }
            final Double _tmpMonthlyTarget;
            if (_cursor.isNull(_cursorIndexOfMonthlyTarget)) {
              _tmpMonthlyTarget = null;
            } else {
              _tmpMonthlyTarget = _cursor.getDouble(_cursorIndexOfMonthlyTarget);
            }
            final GoalPriority _tmpPriority;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfPriority)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfPriority);
            }
            _tmpPriority = __analyticsTypeConverters.toGoalPriority(_tmp_1);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final boolean _tmpIsActive;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_2 != 0;
            final boolean _tmpIsCompleted;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_3 != 0;
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            final boolean _tmpAutoTransferEnabled;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfAutoTransferEnabled);
            _tmpAutoTransferEnabled = _tmp_4 != 0;
            final Double _tmpAutoTransferAmount;
            if (_cursor.isNull(_cursorIndexOfAutoTransferAmount)) {
              _tmpAutoTransferAmount = null;
            } else {
              _tmpAutoTransferAmount = _cursor.getDouble(_cursorIndexOfAutoTransferAmount);
            }
            final String _tmpLinkedAccountId;
            if (_cursor.isNull(_cursorIndexOfLinkedAccountId)) {
              _tmpLinkedAccountId = null;
            } else {
              _tmpLinkedAccountId = _cursor.getString(_cursorIndexOfLinkedAccountId);
            }
            final boolean _tmpReminderEnabled;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfReminderEnabled);
            _tmpReminderEnabled = _tmp_5 != 0;
            final ReminderFrequency _tmpReminderFrequency;
            final String _tmp_6;
            if (_cursor.isNull(_cursorIndexOfReminderFrequency)) {
              _tmp_6 = null;
            } else {
              _tmp_6 = _cursor.getString(_cursorIndexOfReminderFrequency);
            }
            _tmpReminderFrequency = __analyticsTypeConverters.toReminderFrequency(_tmp_6);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new FinancialGoal(_tmpId,_tmpName,_tmpDescription,_tmpGoalType,_tmpTargetAmount,_tmpCurrentAmount,_tmpTargetDate,_tmpMonthlyTarget,_tmpPriority,_tmpCategory,_tmpIsActive,_tmpIsCompleted,_tmpCompletedAt,_tmpAutoTransferEnabled,_tmpAutoTransferAmount,_tmpLinkedAccountId,_tmpReminderEnabled,_tmpReminderFrequency,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Object getOverdueGoals(final long date,
      final Continuation<? super List<FinancialGoal>> arg1) {
    final String _sql = "SELECT * FROM financial_goals WHERE targetDate IS NOT NULL AND targetDate < ? AND isCompleted = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, date);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<FinancialGoal>>() {
      @Override
      @NonNull
      public List<FinancialGoal> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfGoalType = CursorUtil.getColumnIndexOrThrow(_cursor, "goalType");
          final int _cursorIndexOfTargetAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "targetAmount");
          final int _cursorIndexOfCurrentAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "currentAmount");
          final int _cursorIndexOfTargetDate = CursorUtil.getColumnIndexOrThrow(_cursor, "targetDate");
          final int _cursorIndexOfMonthlyTarget = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyTarget");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfAutoTransferEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "autoTransferEnabled");
          final int _cursorIndexOfAutoTransferAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "autoTransferAmount");
          final int _cursorIndexOfLinkedAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "linkedAccountId");
          final int _cursorIndexOfReminderEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderEnabled");
          final int _cursorIndexOfReminderFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderFrequency");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<FinancialGoal> _result = new ArrayList<FinancialGoal>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FinancialGoal _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final GoalType _tmpGoalType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfGoalType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfGoalType);
            }
            _tmpGoalType = __analyticsTypeConverters.toGoalType(_tmp);
            final double _tmpTargetAmount;
            _tmpTargetAmount = _cursor.getDouble(_cursorIndexOfTargetAmount);
            final double _tmpCurrentAmount;
            _tmpCurrentAmount = _cursor.getDouble(_cursorIndexOfCurrentAmount);
            final Long _tmpTargetDate;
            if (_cursor.isNull(_cursorIndexOfTargetDate)) {
              _tmpTargetDate = null;
            } else {
              _tmpTargetDate = _cursor.getLong(_cursorIndexOfTargetDate);
            }
            final Double _tmpMonthlyTarget;
            if (_cursor.isNull(_cursorIndexOfMonthlyTarget)) {
              _tmpMonthlyTarget = null;
            } else {
              _tmpMonthlyTarget = _cursor.getDouble(_cursorIndexOfMonthlyTarget);
            }
            final GoalPriority _tmpPriority;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfPriority)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfPriority);
            }
            _tmpPriority = __analyticsTypeConverters.toGoalPriority(_tmp_1);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final boolean _tmpIsActive;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_2 != 0;
            final boolean _tmpIsCompleted;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_3 != 0;
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            final boolean _tmpAutoTransferEnabled;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfAutoTransferEnabled);
            _tmpAutoTransferEnabled = _tmp_4 != 0;
            final Double _tmpAutoTransferAmount;
            if (_cursor.isNull(_cursorIndexOfAutoTransferAmount)) {
              _tmpAutoTransferAmount = null;
            } else {
              _tmpAutoTransferAmount = _cursor.getDouble(_cursorIndexOfAutoTransferAmount);
            }
            final String _tmpLinkedAccountId;
            if (_cursor.isNull(_cursorIndexOfLinkedAccountId)) {
              _tmpLinkedAccountId = null;
            } else {
              _tmpLinkedAccountId = _cursor.getString(_cursorIndexOfLinkedAccountId);
            }
            final boolean _tmpReminderEnabled;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfReminderEnabled);
            _tmpReminderEnabled = _tmp_5 != 0;
            final ReminderFrequency _tmpReminderFrequency;
            final String _tmp_6;
            if (_cursor.isNull(_cursorIndexOfReminderFrequency)) {
              _tmp_6 = null;
            } else {
              _tmp_6 = _cursor.getString(_cursorIndexOfReminderFrequency);
            }
            _tmpReminderFrequency = __analyticsTypeConverters.toReminderFrequency(_tmp_6);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new FinancialGoal(_tmpId,_tmpName,_tmpDescription,_tmpGoalType,_tmpTargetAmount,_tmpCurrentAmount,_tmpTargetDate,_tmpMonthlyTarget,_tmpPriority,_tmpCategory,_tmpIsActive,_tmpIsCompleted,_tmpCompletedAt,_tmpAutoTransferEnabled,_tmpAutoTransferAmount,_tmpLinkedAccountId,_tmpReminderEnabled,_tmpReminderFrequency,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Object getGoalsDueInRange(final long startDate, final long endDate,
      final Continuation<? super List<FinancialGoal>> arg2) {
    final String _sql = "SELECT * FROM financial_goals WHERE targetDate IS NOT NULL AND targetDate BETWEEN ? AND ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startDate);
    _argIndex = 2;
    _statement.bindLong(_argIndex, endDate);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<FinancialGoal>>() {
      @Override
      @NonNull
      public List<FinancialGoal> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfGoalType = CursorUtil.getColumnIndexOrThrow(_cursor, "goalType");
          final int _cursorIndexOfTargetAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "targetAmount");
          final int _cursorIndexOfCurrentAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "currentAmount");
          final int _cursorIndexOfTargetDate = CursorUtil.getColumnIndexOrThrow(_cursor, "targetDate");
          final int _cursorIndexOfMonthlyTarget = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyTarget");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfAutoTransferEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "autoTransferEnabled");
          final int _cursorIndexOfAutoTransferAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "autoTransferAmount");
          final int _cursorIndexOfLinkedAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "linkedAccountId");
          final int _cursorIndexOfReminderEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderEnabled");
          final int _cursorIndexOfReminderFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderFrequency");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<FinancialGoal> _result = new ArrayList<FinancialGoal>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FinancialGoal _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final GoalType _tmpGoalType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfGoalType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfGoalType);
            }
            _tmpGoalType = __analyticsTypeConverters.toGoalType(_tmp);
            final double _tmpTargetAmount;
            _tmpTargetAmount = _cursor.getDouble(_cursorIndexOfTargetAmount);
            final double _tmpCurrentAmount;
            _tmpCurrentAmount = _cursor.getDouble(_cursorIndexOfCurrentAmount);
            final Long _tmpTargetDate;
            if (_cursor.isNull(_cursorIndexOfTargetDate)) {
              _tmpTargetDate = null;
            } else {
              _tmpTargetDate = _cursor.getLong(_cursorIndexOfTargetDate);
            }
            final Double _tmpMonthlyTarget;
            if (_cursor.isNull(_cursorIndexOfMonthlyTarget)) {
              _tmpMonthlyTarget = null;
            } else {
              _tmpMonthlyTarget = _cursor.getDouble(_cursorIndexOfMonthlyTarget);
            }
            final GoalPriority _tmpPriority;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfPriority)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfPriority);
            }
            _tmpPriority = __analyticsTypeConverters.toGoalPriority(_tmp_1);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final boolean _tmpIsActive;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_2 != 0;
            final boolean _tmpIsCompleted;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_3 != 0;
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            final boolean _tmpAutoTransferEnabled;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfAutoTransferEnabled);
            _tmpAutoTransferEnabled = _tmp_4 != 0;
            final Double _tmpAutoTransferAmount;
            if (_cursor.isNull(_cursorIndexOfAutoTransferAmount)) {
              _tmpAutoTransferAmount = null;
            } else {
              _tmpAutoTransferAmount = _cursor.getDouble(_cursorIndexOfAutoTransferAmount);
            }
            final String _tmpLinkedAccountId;
            if (_cursor.isNull(_cursorIndexOfLinkedAccountId)) {
              _tmpLinkedAccountId = null;
            } else {
              _tmpLinkedAccountId = _cursor.getString(_cursorIndexOfLinkedAccountId);
            }
            final boolean _tmpReminderEnabled;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfReminderEnabled);
            _tmpReminderEnabled = _tmp_5 != 0;
            final ReminderFrequency _tmpReminderFrequency;
            final String _tmp_6;
            if (_cursor.isNull(_cursorIndexOfReminderFrequency)) {
              _tmp_6 = null;
            } else {
              _tmp_6 = _cursor.getString(_cursorIndexOfReminderFrequency);
            }
            _tmpReminderFrequency = __analyticsTypeConverters.toReminderFrequency(_tmp_6);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new FinancialGoal(_tmpId,_tmpName,_tmpDescription,_tmpGoalType,_tmpTargetAmount,_tmpCurrentAmount,_tmpTargetDate,_tmpMonthlyTarget,_tmpPriority,_tmpCategory,_tmpIsActive,_tmpIsCompleted,_tmpCompletedAt,_tmpAutoTransferEnabled,_tmpAutoTransferAmount,_tmpLinkedAccountId,_tmpReminderEnabled,_tmpReminderFrequency,_tmpCreatedAt,_tmpUpdatedAt);
            _result.add(_item);
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
  public Object getGoalsReadyForCompletion(final Continuation<? super List<FinancialGoal>> arg0) {
    final String _sql = "SELECT * FROM financial_goals WHERE (currentAmount / targetAmount * 100) >= 100 AND isCompleted = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<FinancialGoal>>() {
      @Override
      @NonNull
      public List<FinancialGoal> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfGoalType = CursorUtil.getColumnIndexOrThrow(_cursor, "goalType");
          final int _cursorIndexOfTargetAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "targetAmount");
          final int _cursorIndexOfCurrentAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "currentAmount");
          final int _cursorIndexOfTargetDate = CursorUtil.getColumnIndexOrThrow(_cursor, "targetDate");
          final int _cursorIndexOfMonthlyTarget = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyTarget");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfAutoTransferEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "autoTransferEnabled");
          final int _cursorIndexOfAutoTransferAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "autoTransferAmount");
          final int _cursorIndexOfLinkedAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "linkedAccountId");
          final int _cursorIndexOfReminderEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderEnabled");
          final int _cursorIndexOfReminderFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderFrequency");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<FinancialGoal> _result = new ArrayList<FinancialGoal>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FinancialGoal _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final GoalType _tmpGoalType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfGoalType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfGoalType);
            }
            _tmpGoalType = __analyticsTypeConverters.toGoalType(_tmp);
            final double _tmpTargetAmount;
            _tmpTargetAmount = _cursor.getDouble(_cursorIndexOfTargetAmount);
            final double _tmpCurrentAmount;
            _tmpCurrentAmount = _cursor.getDouble(_cursorIndexOfCurrentAmount);
            final Long _tmpTargetDate;
            if (_cursor.isNull(_cursorIndexOfTargetDate)) {
              _tmpTargetDate = null;
            } else {
              _tmpTargetDate = _cursor.getLong(_cursorIndexOfTargetDate);
            }
            final Double _tmpMonthlyTarget;
            if (_cursor.isNull(_cursorIndexOfMonthlyTarget)) {
              _tmpMonthlyTarget = null;
            } else {
              _tmpMonthlyTarget = _cursor.getDouble(_cursorIndexOfMonthlyTarget);
            }
            final GoalPriority _tmpPriority;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfPriority)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfPriority);
            }
            _tmpPriority = __analyticsTypeConverters.toGoalPriority(_tmp_1);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final boolean _tmpIsActive;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_2 != 0;
            final boolean _tmpIsCompleted;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_3 != 0;
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            final boolean _tmpAutoTransferEnabled;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfAutoTransferEnabled);
            _tmpAutoTransferEnabled = _tmp_4 != 0;
            final Double _tmpAutoTransferAmount;
            if (_cursor.isNull(_cursorIndexOfAutoTransferAmount)) {
              _tmpAutoTransferAmount = null;
            } else {
              _tmpAutoTransferAmount = _cursor.getDouble(_cursorIndexOfAutoTransferAmount);
            }
            final String _tmpLinkedAccountId;
            if (_cursor.isNull(_cursorIndexOfLinkedAccountId)) {
              _tmpLinkedAccountId = null;
            } else {
              _tmpLinkedAccountId = _cursor.getString(_cursorIndexOfLinkedAccountId);
            }
            final boolean _tmpReminderEnabled;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfReminderEnabled);
            _tmpReminderEnabled = _tmp_5 != 0;
            final ReminderFrequency _tmpReminderFrequency;
            final String _tmp_6;
            if (_cursor.isNull(_cursorIndexOfReminderFrequency)) {
              _tmp_6 = null;
            } else {
              _tmp_6 = _cursor.getString(_cursorIndexOfReminderFrequency);
            }
            _tmpReminderFrequency = __analyticsTypeConverters.toReminderFrequency(_tmp_6);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new FinancialGoal(_tmpId,_tmpName,_tmpDescription,_tmpGoalType,_tmpTargetAmount,_tmpCurrentAmount,_tmpTargetDate,_tmpMonthlyTarget,_tmpPriority,_tmpCategory,_tmpIsActive,_tmpIsCompleted,_tmpCompletedAt,_tmpAutoTransferEnabled,_tmpAutoTransferAmount,_tmpLinkedAccountId,_tmpReminderEnabled,_tmpReminderFrequency,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Object getGoalsWithReminders(final Continuation<? super List<FinancialGoal>> arg0) {
    final String _sql = "SELECT * FROM financial_goals WHERE reminderEnabled = 1 AND isActive = 1 AND isCompleted = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<FinancialGoal>>() {
      @Override
      @NonNull
      public List<FinancialGoal> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfGoalType = CursorUtil.getColumnIndexOrThrow(_cursor, "goalType");
          final int _cursorIndexOfTargetAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "targetAmount");
          final int _cursorIndexOfCurrentAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "currentAmount");
          final int _cursorIndexOfTargetDate = CursorUtil.getColumnIndexOrThrow(_cursor, "targetDate");
          final int _cursorIndexOfMonthlyTarget = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyTarget");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfAutoTransferEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "autoTransferEnabled");
          final int _cursorIndexOfAutoTransferAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "autoTransferAmount");
          final int _cursorIndexOfLinkedAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "linkedAccountId");
          final int _cursorIndexOfReminderEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderEnabled");
          final int _cursorIndexOfReminderFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderFrequency");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<FinancialGoal> _result = new ArrayList<FinancialGoal>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FinancialGoal _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final GoalType _tmpGoalType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfGoalType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfGoalType);
            }
            _tmpGoalType = __analyticsTypeConverters.toGoalType(_tmp);
            final double _tmpTargetAmount;
            _tmpTargetAmount = _cursor.getDouble(_cursorIndexOfTargetAmount);
            final double _tmpCurrentAmount;
            _tmpCurrentAmount = _cursor.getDouble(_cursorIndexOfCurrentAmount);
            final Long _tmpTargetDate;
            if (_cursor.isNull(_cursorIndexOfTargetDate)) {
              _tmpTargetDate = null;
            } else {
              _tmpTargetDate = _cursor.getLong(_cursorIndexOfTargetDate);
            }
            final Double _tmpMonthlyTarget;
            if (_cursor.isNull(_cursorIndexOfMonthlyTarget)) {
              _tmpMonthlyTarget = null;
            } else {
              _tmpMonthlyTarget = _cursor.getDouble(_cursorIndexOfMonthlyTarget);
            }
            final GoalPriority _tmpPriority;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfPriority)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfPriority);
            }
            _tmpPriority = __analyticsTypeConverters.toGoalPriority(_tmp_1);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final boolean _tmpIsActive;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_2 != 0;
            final boolean _tmpIsCompleted;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_3 != 0;
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            final boolean _tmpAutoTransferEnabled;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfAutoTransferEnabled);
            _tmpAutoTransferEnabled = _tmp_4 != 0;
            final Double _tmpAutoTransferAmount;
            if (_cursor.isNull(_cursorIndexOfAutoTransferAmount)) {
              _tmpAutoTransferAmount = null;
            } else {
              _tmpAutoTransferAmount = _cursor.getDouble(_cursorIndexOfAutoTransferAmount);
            }
            final String _tmpLinkedAccountId;
            if (_cursor.isNull(_cursorIndexOfLinkedAccountId)) {
              _tmpLinkedAccountId = null;
            } else {
              _tmpLinkedAccountId = _cursor.getString(_cursorIndexOfLinkedAccountId);
            }
            final boolean _tmpReminderEnabled;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfReminderEnabled);
            _tmpReminderEnabled = _tmp_5 != 0;
            final ReminderFrequency _tmpReminderFrequency;
            final String _tmp_6;
            if (_cursor.isNull(_cursorIndexOfReminderFrequency)) {
              _tmp_6 = null;
            } else {
              _tmp_6 = _cursor.getString(_cursorIndexOfReminderFrequency);
            }
            _tmpReminderFrequency = __analyticsTypeConverters.toReminderFrequency(_tmp_6);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new FinancialGoal(_tmpId,_tmpName,_tmpDescription,_tmpGoalType,_tmpTargetAmount,_tmpCurrentAmount,_tmpTargetDate,_tmpMonthlyTarget,_tmpPriority,_tmpCategory,_tmpIsActive,_tmpIsCompleted,_tmpCompletedAt,_tmpAutoTransferEnabled,_tmpAutoTransferAmount,_tmpLinkedAccountId,_tmpReminderEnabled,_tmpReminderFrequency,_tmpCreatedAt,_tmpUpdatedAt);
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
  public Object getGoalsWithAutoTransfer(final Continuation<? super List<FinancialGoal>> arg0) {
    final String _sql = "SELECT * FROM financial_goals WHERE autoTransferEnabled = 1 AND isActive = 1 AND isCompleted = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<FinancialGoal>>() {
      @Override
      @NonNull
      public List<FinancialGoal> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfGoalType = CursorUtil.getColumnIndexOrThrow(_cursor, "goalType");
          final int _cursorIndexOfTargetAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "targetAmount");
          final int _cursorIndexOfCurrentAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "currentAmount");
          final int _cursorIndexOfTargetDate = CursorUtil.getColumnIndexOrThrow(_cursor, "targetDate");
          final int _cursorIndexOfMonthlyTarget = CursorUtil.getColumnIndexOrThrow(_cursor, "monthlyTarget");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfAutoTransferEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "autoTransferEnabled");
          final int _cursorIndexOfAutoTransferAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "autoTransferAmount");
          final int _cursorIndexOfLinkedAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "linkedAccountId");
          final int _cursorIndexOfReminderEnabled = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderEnabled");
          final int _cursorIndexOfReminderFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "reminderFrequency");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<FinancialGoal> _result = new ArrayList<FinancialGoal>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FinancialGoal _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final GoalType _tmpGoalType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfGoalType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfGoalType);
            }
            _tmpGoalType = __analyticsTypeConverters.toGoalType(_tmp);
            final double _tmpTargetAmount;
            _tmpTargetAmount = _cursor.getDouble(_cursorIndexOfTargetAmount);
            final double _tmpCurrentAmount;
            _tmpCurrentAmount = _cursor.getDouble(_cursorIndexOfCurrentAmount);
            final Long _tmpTargetDate;
            if (_cursor.isNull(_cursorIndexOfTargetDate)) {
              _tmpTargetDate = null;
            } else {
              _tmpTargetDate = _cursor.getLong(_cursorIndexOfTargetDate);
            }
            final Double _tmpMonthlyTarget;
            if (_cursor.isNull(_cursorIndexOfMonthlyTarget)) {
              _tmpMonthlyTarget = null;
            } else {
              _tmpMonthlyTarget = _cursor.getDouble(_cursorIndexOfMonthlyTarget);
            }
            final GoalPriority _tmpPriority;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfPriority)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfPriority);
            }
            _tmpPriority = __analyticsTypeConverters.toGoalPriority(_tmp_1);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final boolean _tmpIsActive;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_2 != 0;
            final boolean _tmpIsCompleted;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_3 != 0;
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            final boolean _tmpAutoTransferEnabled;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfAutoTransferEnabled);
            _tmpAutoTransferEnabled = _tmp_4 != 0;
            final Double _tmpAutoTransferAmount;
            if (_cursor.isNull(_cursorIndexOfAutoTransferAmount)) {
              _tmpAutoTransferAmount = null;
            } else {
              _tmpAutoTransferAmount = _cursor.getDouble(_cursorIndexOfAutoTransferAmount);
            }
            final String _tmpLinkedAccountId;
            if (_cursor.isNull(_cursorIndexOfLinkedAccountId)) {
              _tmpLinkedAccountId = null;
            } else {
              _tmpLinkedAccountId = _cursor.getString(_cursorIndexOfLinkedAccountId);
            }
            final boolean _tmpReminderEnabled;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfReminderEnabled);
            _tmpReminderEnabled = _tmp_5 != 0;
            final ReminderFrequency _tmpReminderFrequency;
            final String _tmp_6;
            if (_cursor.isNull(_cursorIndexOfReminderFrequency)) {
              _tmp_6 = null;
            } else {
              _tmp_6 = _cursor.getString(_cursorIndexOfReminderFrequency);
            }
            _tmpReminderFrequency = __analyticsTypeConverters.toReminderFrequency(_tmp_6);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new FinancialGoal(_tmpId,_tmpName,_tmpDescription,_tmpGoalType,_tmpTargetAmount,_tmpCurrentAmount,_tmpTargetDate,_tmpMonthlyTarget,_tmpPriority,_tmpCategory,_tmpIsActive,_tmpIsCompleted,_tmpCompletedAt,_tmpAutoTransferEnabled,_tmpAutoTransferAmount,_tmpLinkedAccountId,_tmpReminderEnabled,_tmpReminderFrequency,_tmpCreatedAt,_tmpUpdatedAt);
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
  public LiveData<Integer> getActiveGoalCount() {
    final String _sql = "SELECT COUNT(*) FROM financial_goals WHERE isActive = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"financial_goals"}, false, new Callable<Integer>() {
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
  public LiveData<Integer> getCompletedGoalCount() {
    final String _sql = "SELECT COUNT(*) FROM financial_goals WHERE isCompleted = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"financial_goals"}, false, new Callable<Integer>() {
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
  public Object getTotalTargetAmount(final Continuation<? super Double> arg0) {
    final String _sql = "SELECT SUM(targetAmount) FROM financial_goals WHERE isActive = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
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
    }, arg0);
  }

  @Override
  public Object getTotalCurrentAmount(final Continuation<? super Double> arg0) {
    final String _sql = "SELECT SUM(currentAmount) FROM financial_goals WHERE isActive = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
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
    }, arg0);
  }

  @Override
  public Object getAverageProgress(final Continuation<? super Double> arg0) {
    final String _sql = "SELECT AVG(currentAmount / targetAmount * 100) FROM financial_goals WHERE isActive = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
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
    }, arg0);
  }

  @Override
  public Object getActiveGoalCountsByType(final Continuation<? super List<GoalTypeCount>> arg0) {
    final String _sql = "\n"
            + "        SELECT goalType, COUNT(*) as count \n"
            + "        FROM financial_goals \n"
            + "        WHERE isActive = 1 \n"
            + "        GROUP BY goalType\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<GoalTypeCount>>() {
      @Override
      @NonNull
      public List<GoalTypeCount> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfGoalType = 0;
          final int _cursorIndexOfCount = 1;
          final List<GoalTypeCount> _result = new ArrayList<GoalTypeCount>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final GoalTypeCount _item;
            final String _tmpGoalType;
            if (_cursor.isNull(_cursorIndexOfGoalType)) {
              _tmpGoalType = null;
            } else {
              _tmpGoalType = _cursor.getString(_cursorIndexOfGoalType);
            }
            final int _tmpCount;
            _tmpCount = _cursor.getInt(_cursorIndexOfCount);
            _item = new GoalTypeCount(_tmpGoalType,_tmpCount);
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
  public Object getAverageProgressByType(final Continuation<? super List<GoalTypeProgress>> arg0) {
    final String _sql = "\n"
            + "        SELECT goalType, AVG(currentAmount / targetAmount * 100) as avgProgress \n"
            + "        FROM financial_goals \n"
            + "        WHERE isActive = 1 \n"
            + "        GROUP BY goalType\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<GoalTypeProgress>>() {
      @Override
      @NonNull
      public List<GoalTypeProgress> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfGoalType = 0;
          final int _cursorIndexOfAvgProgress = 1;
          final List<GoalTypeProgress> _result = new ArrayList<GoalTypeProgress>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final GoalTypeProgress _item;
            final String _tmpGoalType;
            if (_cursor.isNull(_cursorIndexOfGoalType)) {
              _tmpGoalType = null;
            } else {
              _tmpGoalType = _cursor.getString(_cursorIndexOfGoalType);
            }
            final double _tmpAvgProgress;
            _tmpAvgProgress = _cursor.getDouble(_cursorIndexOfAvgProgress);
            _item = new GoalTypeProgress(_tmpGoalType,_tmpAvgProgress);
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
  public Object getTotalTargetByType(final Continuation<? super List<GoalTypeTarget>> arg0) {
    final String _sql = "\n"
            + "        SELECT goalType, SUM(targetAmount) as totalTarget \n"
            + "        FROM financial_goals \n"
            + "        WHERE isActive = 1 \n"
            + "        GROUP BY goalType\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<GoalTypeTarget>>() {
      @Override
      @NonNull
      public List<GoalTypeTarget> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfGoalType = 0;
          final int _cursorIndexOfTotalTarget = 1;
          final List<GoalTypeTarget> _result = new ArrayList<GoalTypeTarget>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final GoalTypeTarget _item;
            final String _tmpGoalType;
            if (_cursor.isNull(_cursorIndexOfGoalType)) {
              _tmpGoalType = null;
            } else {
              _tmpGoalType = _cursor.getString(_cursorIndexOfGoalType);
            }
            final double _tmpTotalTarget;
            _tmpTotalTarget = _cursor.getDouble(_cursorIndexOfTotalTarget);
            _item = new GoalTypeTarget(_tmpGoalType,_tmpTotalTarget);
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
  public Object getActiveGoalCountsByPriority(
      final Continuation<? super List<GoalPriorityCount>> arg0) {
    final String _sql = "\n"
            + "        SELECT priority, COUNT(*) as count \n"
            + "        FROM financial_goals \n"
            + "        WHERE isActive = 1 \n"
            + "        GROUP BY priority\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<GoalPriorityCount>>() {
      @Override
      @NonNull
      public List<GoalPriorityCount> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPriority = 0;
          final int _cursorIndexOfCount = 1;
          final List<GoalPriorityCount> _result = new ArrayList<GoalPriorityCount>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final GoalPriorityCount _item;
            final String _tmpPriority;
            if (_cursor.isNull(_cursorIndexOfPriority)) {
              _tmpPriority = null;
            } else {
              _tmpPriority = _cursor.getString(_cursorIndexOfPriority);
            }
            final int _tmpCount;
            _tmpCount = _cursor.getInt(_cursorIndexOfCount);
            _item = new GoalPriorityCount(_tmpPriority,_tmpCount);
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
