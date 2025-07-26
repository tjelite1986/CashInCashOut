package com.example.budgetapp.automation.dao;

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
import com.example.budgetapp.automation.AutoTransferRule;
import com.example.budgetapp.automation.AutomationAction;
import com.example.budgetapp.automation.AutomationCreatedBy;
import com.example.budgetapp.automation.AutomationRule;
import com.example.budgetapp.automation.AutomationTypeConverters;
import com.example.budgetapp.automation.RuleCondition;
import com.example.budgetapp.automation.TransferCondition;
import com.example.budgetapp.automation.TransferFrequency;
import com.example.budgetapp.automation.TransferType;
import com.example.budgetapp.automation.ml.ImpactLevel;
import com.example.budgetapp.automation.ml.InsightPriority;
import com.example.budgetapp.automation.ml.InsightType;
import com.example.budgetapp.automation.ml.MLTypeConverters;
import com.example.budgetapp.automation.ml.SmartInsight;
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
public final class AutomationRuleDao_Impl implements AutomationRuleDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AutomationRule> __insertionAdapterOfAutomationRule;

  private final AutomationTypeConverters __automationTypeConverters = new AutomationTypeConverters();

  private final EntityInsertionAdapter<AutoTransferRule> __insertionAdapterOfAutoTransferRule;

  private final EntityInsertionAdapter<SmartInsight> __insertionAdapterOfSmartInsight;

  private final MLTypeConverters __mLTypeConverters = new MLTypeConverters();

  private final EntityDeletionOrUpdateAdapter<AutomationRule> __deletionAdapterOfAutomationRule;

  private final EntityDeletionOrUpdateAdapter<AutoTransferRule> __deletionAdapterOfAutoTransferRule;

  private final EntityDeletionOrUpdateAdapter<SmartInsight> __deletionAdapterOfSmartInsight;

  private final EntityDeletionOrUpdateAdapter<AutomationRule> __updateAdapterOfAutomationRule;

  private final EntityDeletionOrUpdateAdapter<AutoTransferRule> __updateAdapterOfAutoTransferRule;

  private final EntityDeletionOrUpdateAdapter<SmartInsight> __updateAdapterOfSmartInsight;

  private final SharedSQLiteStatement __preparedStmtOfToggleRuleStatus;

  private final SharedSQLiteStatement __preparedStmtOfIncrementRuleUsage;

  private final SharedSQLiteStatement __preparedStmtOfDeleteUnusedRules;

  private final SharedSQLiteStatement __preparedStmtOfToggleTransferRuleStatus;

  private final SharedSQLiteStatement __preparedStmtOfRecordTransferExecution;

  private final SharedSQLiteStatement __preparedStmtOfMarkInsightAsRead;

  private final SharedSQLiteStatement __preparedStmtOfDismissInsight;

  private final SharedSQLiteStatement __preparedStmtOfMarkAllInsightsAsRead;

  private final SharedSQLiteStatement __preparedStmtOfDeleteDismissedInsights;

  private final SharedSQLiteStatement __preparedStmtOfDeleteExpiredInsights;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllRules;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllTransferRules;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllInsights;

  public AutomationRuleDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAutomationRule = new EntityInsertionAdapter<AutomationRule>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `automation_rules` (`id`,`name`,`description`,`conditions`,`actions`,`isActive`,`priority`,`usageCount`,`createdBy`,`confidence`,`createdAt`,`lastModified`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AutomationRule entity) {
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
        final String _tmp = __automationTypeConverters.fromRuleConditionList(entity.getConditions());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, _tmp);
        }
        final String _tmp_1 = __automationTypeConverters.fromAutomationActionList(entity.getActions());
        if (_tmp_1 == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, _tmp_1);
        }
        final int _tmp_2 = entity.isActive() ? 1 : 0;
        statement.bindLong(6, _tmp_2);
        statement.bindLong(7, entity.getPriority());
        statement.bindLong(8, entity.getUsageCount());
        final String _tmp_3 = __automationTypeConverters.fromAutomationCreatedBy(entity.getCreatedBy());
        if (_tmp_3 == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, _tmp_3);
        }
        statement.bindDouble(10, entity.getConfidence());
        statement.bindLong(11, entity.getCreatedAt());
        statement.bindLong(12, entity.getLastModified());
      }
    };
    this.__insertionAdapterOfAutoTransferRule = new EntityInsertionAdapter<AutoTransferRule>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `auto_transfer_rules` (`id`,`name`,`description`,`sourceAccountId`,`targetAccountId`,`transferType`,`amount`,`frequency`,`conditions`,`isActive`,`lastExecuted`,`totalTransferred`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AutoTransferRule entity) {
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
        if (entity.getSourceAccountId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getSourceAccountId());
        }
        if (entity.getTargetAccountId() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getTargetAccountId());
        }
        final String _tmp = __automationTypeConverters.fromTransferType(entity.getTransferType());
        if (_tmp == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, _tmp);
        }
        statement.bindDouble(7, entity.getAmount());
        final String _tmp_1 = __automationTypeConverters.fromTransferFrequency(entity.getFrequency());
        if (_tmp_1 == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, _tmp_1);
        }
        final String _tmp_2 = __automationTypeConverters.fromTransferConditionList(entity.getConditions());
        if (_tmp_2 == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, _tmp_2);
        }
        final int _tmp_3 = entity.isActive() ? 1 : 0;
        statement.bindLong(10, _tmp_3);
        if (entity.getLastExecuted() == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, entity.getLastExecuted());
        }
        statement.bindDouble(12, entity.getTotalTransferred());
        statement.bindLong(13, entity.getCreatedAt());
      }
    };
    this.__insertionAdapterOfSmartInsight = new EntityInsertionAdapter<SmartInsight>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `smart_insights` (`id`,`type`,`title`,`description`,`actionable`,`actions`,`priority`,`confidence`,`category`,`impact`,`isRead`,`isDismissed`,`createdAt`,`expiresAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SmartInsight entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        final String _tmp = __mLTypeConverters.fromInsightType(entity.getType());
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
        final int _tmp_1 = entity.getActionable() ? 1 : 0;
        statement.bindLong(5, _tmp_1);
        final String _tmp_2 = __automationTypeConverters.fromStringList(entity.getActions());
        if (_tmp_2 == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, _tmp_2);
        }
        final String _tmp_3 = __mLTypeConverters.fromInsightPriority(entity.getPriority());
        if (_tmp_3 == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, _tmp_3);
        }
        statement.bindDouble(8, entity.getConfidence());
        if (entity.getCategory() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getCategory());
        }
        final String _tmp_4 = __mLTypeConverters.fromImpactLevel(entity.getImpact());
        if (_tmp_4 == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, _tmp_4);
        }
        final int _tmp_5 = entity.isRead() ? 1 : 0;
        statement.bindLong(11, _tmp_5);
        final int _tmp_6 = entity.isDismissed() ? 1 : 0;
        statement.bindLong(12, _tmp_6);
        statement.bindLong(13, entity.getCreatedAt());
        if (entity.getExpiresAt() == null) {
          statement.bindNull(14);
        } else {
          statement.bindLong(14, entity.getExpiresAt());
        }
      }
    };
    this.__deletionAdapterOfAutomationRule = new EntityDeletionOrUpdateAdapter<AutomationRule>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `automation_rules` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AutomationRule entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__deletionAdapterOfAutoTransferRule = new EntityDeletionOrUpdateAdapter<AutoTransferRule>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `auto_transfer_rules` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AutoTransferRule entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__deletionAdapterOfSmartInsight = new EntityDeletionOrUpdateAdapter<SmartInsight>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `smart_insights` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SmartInsight entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
      }
    };
    this.__updateAdapterOfAutomationRule = new EntityDeletionOrUpdateAdapter<AutomationRule>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `automation_rules` SET `id` = ?,`name` = ?,`description` = ?,`conditions` = ?,`actions` = ?,`isActive` = ?,`priority` = ?,`usageCount` = ?,`createdBy` = ?,`confidence` = ?,`createdAt` = ?,`lastModified` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AutomationRule entity) {
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
        final String _tmp = __automationTypeConverters.fromRuleConditionList(entity.getConditions());
        if (_tmp == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, _tmp);
        }
        final String _tmp_1 = __automationTypeConverters.fromAutomationActionList(entity.getActions());
        if (_tmp_1 == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, _tmp_1);
        }
        final int _tmp_2 = entity.isActive() ? 1 : 0;
        statement.bindLong(6, _tmp_2);
        statement.bindLong(7, entity.getPriority());
        statement.bindLong(8, entity.getUsageCount());
        final String _tmp_3 = __automationTypeConverters.fromAutomationCreatedBy(entity.getCreatedBy());
        if (_tmp_3 == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, _tmp_3);
        }
        statement.bindDouble(10, entity.getConfidence());
        statement.bindLong(11, entity.getCreatedAt());
        statement.bindLong(12, entity.getLastModified());
        statement.bindLong(13, entity.getId());
      }
    };
    this.__updateAdapterOfAutoTransferRule = new EntityDeletionOrUpdateAdapter<AutoTransferRule>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `auto_transfer_rules` SET `id` = ?,`name` = ?,`description` = ?,`sourceAccountId` = ?,`targetAccountId` = ?,`transferType` = ?,`amount` = ?,`frequency` = ?,`conditions` = ?,`isActive` = ?,`lastExecuted` = ?,`totalTransferred` = ?,`createdAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AutoTransferRule entity) {
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
        if (entity.getSourceAccountId() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getSourceAccountId());
        }
        if (entity.getTargetAccountId() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getTargetAccountId());
        }
        final String _tmp = __automationTypeConverters.fromTransferType(entity.getTransferType());
        if (_tmp == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, _tmp);
        }
        statement.bindDouble(7, entity.getAmount());
        final String _tmp_1 = __automationTypeConverters.fromTransferFrequency(entity.getFrequency());
        if (_tmp_1 == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, _tmp_1);
        }
        final String _tmp_2 = __automationTypeConverters.fromTransferConditionList(entity.getConditions());
        if (_tmp_2 == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, _tmp_2);
        }
        final int _tmp_3 = entity.isActive() ? 1 : 0;
        statement.bindLong(10, _tmp_3);
        if (entity.getLastExecuted() == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, entity.getLastExecuted());
        }
        statement.bindDouble(12, entity.getTotalTransferred());
        statement.bindLong(13, entity.getCreatedAt());
        statement.bindLong(14, entity.getId());
      }
    };
    this.__updateAdapterOfSmartInsight = new EntityDeletionOrUpdateAdapter<SmartInsight>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `smart_insights` SET `id` = ?,`type` = ?,`title` = ?,`description` = ?,`actionable` = ?,`actions` = ?,`priority` = ?,`confidence` = ?,`category` = ?,`impact` = ?,`isRead` = ?,`isDismissed` = ?,`createdAt` = ?,`expiresAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SmartInsight entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        final String _tmp = __mLTypeConverters.fromInsightType(entity.getType());
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
        final int _tmp_1 = entity.getActionable() ? 1 : 0;
        statement.bindLong(5, _tmp_1);
        final String _tmp_2 = __automationTypeConverters.fromStringList(entity.getActions());
        if (_tmp_2 == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, _tmp_2);
        }
        final String _tmp_3 = __mLTypeConverters.fromInsightPriority(entity.getPriority());
        if (_tmp_3 == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, _tmp_3);
        }
        statement.bindDouble(8, entity.getConfidence());
        if (entity.getCategory() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getCategory());
        }
        final String _tmp_4 = __mLTypeConverters.fromImpactLevel(entity.getImpact());
        if (_tmp_4 == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, _tmp_4);
        }
        final int _tmp_5 = entity.isRead() ? 1 : 0;
        statement.bindLong(11, _tmp_5);
        final int _tmp_6 = entity.isDismissed() ? 1 : 0;
        statement.bindLong(12, _tmp_6);
        statement.bindLong(13, entity.getCreatedAt());
        if (entity.getExpiresAt() == null) {
          statement.bindNull(14);
        } else {
          statement.bindLong(14, entity.getExpiresAt());
        }
        if (entity.getId() == null) {
          statement.bindNull(15);
        } else {
          statement.bindString(15, entity.getId());
        }
      }
    };
    this.__preparedStmtOfToggleRuleStatus = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE automation_rules SET isActive = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfIncrementRuleUsage = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE automation_rules SET usageCount = usageCount + 1, lastModified = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteUnusedRules = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM automation_rules WHERE isActive = 0 AND usageCount = 0 AND createdAt < ?";
        return _query;
      }
    };
    this.__preparedStmtOfToggleTransferRuleStatus = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE auto_transfer_rules SET isActive = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfRecordTransferExecution = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE auto_transfer_rules SET lastExecuted = ?, totalTransferred = totalTransferred + ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfMarkInsightAsRead = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE smart_insights SET isRead = 1 WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDismissInsight = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE smart_insights SET isDismissed = 1 WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfMarkAllInsightsAsRead = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE smart_insights SET isRead = 1 WHERE isDismissed = 0";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteDismissedInsights = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM smart_insights WHERE isDismissed = 1 AND createdAt < ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteExpiredInsights = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM smart_insights WHERE expiresAt IS NOT NULL AND expiresAt < ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllRules = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM automation_rules";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllTransferRules = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM auto_transfer_rules";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllInsights = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM smart_insights";
        return _query;
      }
    };
  }

  @Override
  public Object insertRule(final AutomationRule rule,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfAutomationRule.insertAndReturnId(rule);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertRules(final List<AutomationRule> rules,
      final Continuation<? super List<Long>> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<List<Long>>() {
      @Override
      @NonNull
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          final List<Long> _result = __insertionAdapterOfAutomationRule.insertAndReturnIdsList(rules);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertTransferRule(final AutoTransferRule rule,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfAutoTransferRule.insertAndReturnId(rule);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertInsight(final SmartInsight insight,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfSmartInsight.insertAndReturnId(insight);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertInsights(final List<SmartInsight> insights,
      final Continuation<? super List<Long>> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<List<Long>>() {
      @Override
      @NonNull
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          final List<Long> _result = __insertionAdapterOfSmartInsight.insertAndReturnIdsList(insights);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteRule(final AutomationRule rule,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfAutomationRule.handle(rule);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteTransferRule(final AutoTransferRule rule,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfAutoTransferRule.handle(rule);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteInsight(final SmartInsight insight,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfSmartInsight.handle(insight);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateRule(final AutomationRule rule,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfAutomationRule.handle(rule);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateTransferRule(final AutoTransferRule rule,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfAutoTransferRule.handle(rule);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateInsight(final SmartInsight insight,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfSmartInsight.handle(insight);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object toggleRuleStatus(final long ruleId, final boolean isActive,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfToggleRuleStatus.acquire();
        int _argIndex = 1;
        final int _tmp = isActive ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, ruleId);
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
          __preparedStmtOfToggleRuleStatus.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object incrementRuleUsage(final long ruleId, final long timestamp,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfIncrementRuleUsage.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, timestamp);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, ruleId);
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
          __preparedStmtOfIncrementRuleUsage.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteUnusedRules(final long cutoffTime,
      final Continuation<? super Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteUnusedRules.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, cutoffTime);
        try {
          __db.beginTransaction();
          try {
            final Integer _result = _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteUnusedRules.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object toggleTransferRuleStatus(final long ruleId, final boolean isActive,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfToggleTransferRuleStatus.acquire();
        int _argIndex = 1;
        final int _tmp = isActive ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, ruleId);
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
          __preparedStmtOfToggleTransferRuleStatus.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object recordTransferExecution(final long ruleId, final double amount,
      final long timestamp, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfRecordTransferExecution.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, timestamp);
        _argIndex = 2;
        _stmt.bindDouble(_argIndex, amount);
        _argIndex = 3;
        _stmt.bindLong(_argIndex, ruleId);
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
          __preparedStmtOfRecordTransferExecution.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object markInsightAsRead(final String insightId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfMarkInsightAsRead.acquire();
        int _argIndex = 1;
        if (insightId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, insightId);
        }
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
          __preparedStmtOfMarkInsightAsRead.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object dismissInsight(final String insightId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDismissInsight.acquire();
        int _argIndex = 1;
        if (insightId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, insightId);
        }
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
          __preparedStmtOfDismissInsight.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object markAllInsightsAsRead(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfMarkAllInsightsAsRead.acquire();
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
          __preparedStmtOfMarkAllInsightsAsRead.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteDismissedInsights(final long cutoffTime,
      final Continuation<? super Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteDismissedInsights.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, cutoffTime);
        try {
          __db.beginTransaction();
          try {
            final Integer _result = _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteDismissedInsights.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteExpiredInsights(final long currentTime,
      final Continuation<? super Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteExpiredInsights.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, currentTime);
        try {
          __db.beginTransaction();
          try {
            final Integer _result = _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return _result;
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
  public Object deleteAllRules(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllRules.acquire();
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
          __preparedStmtOfDeleteAllRules.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAllTransferRules(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllTransferRules.acquire();
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
          __preparedStmtOfDeleteAllTransferRules.release(_stmt);
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
  public Object getActiveRules(final Continuation<? super List<AutomationRule>> $completion) {
    final String _sql = "SELECT * FROM automation_rules WHERE isActive = 1 ORDER BY priority ASC, usageCount DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AutomationRule>>() {
      @Override
      @NonNull
      public List<AutomationRule> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfConditions = CursorUtil.getColumnIndexOrThrow(_cursor, "conditions");
          final int _cursorIndexOfActions = CursorUtil.getColumnIndexOrThrow(_cursor, "actions");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfUsageCount = CursorUtil.getColumnIndexOrThrow(_cursor, "usageCount");
          final int _cursorIndexOfCreatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "createdBy");
          final int _cursorIndexOfConfidence = CursorUtil.getColumnIndexOrThrow(_cursor, "confidence");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfLastModified = CursorUtil.getColumnIndexOrThrow(_cursor, "lastModified");
          final List<AutomationRule> _result = new ArrayList<AutomationRule>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AutomationRule _item;
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
            final List<RuleCondition> _tmpConditions;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfConditions)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfConditions);
            }
            _tmpConditions = __automationTypeConverters.toRuleConditionList(_tmp);
            final List<AutomationAction> _tmpActions;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfActions)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfActions);
            }
            _tmpActions = __automationTypeConverters.toAutomationActionList(_tmp_1);
            final boolean _tmpIsActive;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_2 != 0;
            final int _tmpPriority;
            _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
            final long _tmpUsageCount;
            _tmpUsageCount = _cursor.getLong(_cursorIndexOfUsageCount);
            final AutomationCreatedBy _tmpCreatedBy;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfCreatedBy)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfCreatedBy);
            }
            _tmpCreatedBy = __automationTypeConverters.toAutomationCreatedBy(_tmp_3);
            final double _tmpConfidence;
            _tmpConfidence = _cursor.getDouble(_cursorIndexOfConfidence);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpLastModified;
            _tmpLastModified = _cursor.getLong(_cursorIndexOfLastModified);
            _item = new AutomationRule(_tmpId,_tmpName,_tmpDescription,_tmpConditions,_tmpActions,_tmpIsActive,_tmpPriority,_tmpUsageCount,_tmpCreatedBy,_tmpConfidence,_tmpCreatedAt,_tmpLastModified);
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
  public Flow<List<AutomationRule>> getAllRules() {
    final String _sql = "SELECT * FROM automation_rules ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"automation_rules"}, new Callable<List<AutomationRule>>() {
      @Override
      @NonNull
      public List<AutomationRule> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfConditions = CursorUtil.getColumnIndexOrThrow(_cursor, "conditions");
          final int _cursorIndexOfActions = CursorUtil.getColumnIndexOrThrow(_cursor, "actions");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfUsageCount = CursorUtil.getColumnIndexOrThrow(_cursor, "usageCount");
          final int _cursorIndexOfCreatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "createdBy");
          final int _cursorIndexOfConfidence = CursorUtil.getColumnIndexOrThrow(_cursor, "confidence");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfLastModified = CursorUtil.getColumnIndexOrThrow(_cursor, "lastModified");
          final List<AutomationRule> _result = new ArrayList<AutomationRule>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AutomationRule _item;
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
            final List<RuleCondition> _tmpConditions;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfConditions)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfConditions);
            }
            _tmpConditions = __automationTypeConverters.toRuleConditionList(_tmp);
            final List<AutomationAction> _tmpActions;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfActions)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfActions);
            }
            _tmpActions = __automationTypeConverters.toAutomationActionList(_tmp_1);
            final boolean _tmpIsActive;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_2 != 0;
            final int _tmpPriority;
            _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
            final long _tmpUsageCount;
            _tmpUsageCount = _cursor.getLong(_cursorIndexOfUsageCount);
            final AutomationCreatedBy _tmpCreatedBy;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfCreatedBy)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfCreatedBy);
            }
            _tmpCreatedBy = __automationTypeConverters.toAutomationCreatedBy(_tmp_3);
            final double _tmpConfidence;
            _tmpConfidence = _cursor.getDouble(_cursorIndexOfConfidence);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpLastModified;
            _tmpLastModified = _cursor.getLong(_cursorIndexOfLastModified);
            _item = new AutomationRule(_tmpId,_tmpName,_tmpDescription,_tmpConditions,_tmpActions,_tmpIsActive,_tmpPriority,_tmpUsageCount,_tmpCreatedBy,_tmpConfidence,_tmpCreatedAt,_tmpLastModified);
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
  public Object getRuleById(final long ruleId,
      final Continuation<? super AutomationRule> $completion) {
    final String _sql = "SELECT * FROM automation_rules WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, ruleId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<AutomationRule>() {
      @Override
      @Nullable
      public AutomationRule call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfConditions = CursorUtil.getColumnIndexOrThrow(_cursor, "conditions");
          final int _cursorIndexOfActions = CursorUtil.getColumnIndexOrThrow(_cursor, "actions");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfUsageCount = CursorUtil.getColumnIndexOrThrow(_cursor, "usageCount");
          final int _cursorIndexOfCreatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "createdBy");
          final int _cursorIndexOfConfidence = CursorUtil.getColumnIndexOrThrow(_cursor, "confidence");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfLastModified = CursorUtil.getColumnIndexOrThrow(_cursor, "lastModified");
          final AutomationRule _result;
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
            final List<RuleCondition> _tmpConditions;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfConditions)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfConditions);
            }
            _tmpConditions = __automationTypeConverters.toRuleConditionList(_tmp);
            final List<AutomationAction> _tmpActions;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfActions)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfActions);
            }
            _tmpActions = __automationTypeConverters.toAutomationActionList(_tmp_1);
            final boolean _tmpIsActive;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_2 != 0;
            final int _tmpPriority;
            _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
            final long _tmpUsageCount;
            _tmpUsageCount = _cursor.getLong(_cursorIndexOfUsageCount);
            final AutomationCreatedBy _tmpCreatedBy;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfCreatedBy)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfCreatedBy);
            }
            _tmpCreatedBy = __automationTypeConverters.toAutomationCreatedBy(_tmp_3);
            final double _tmpConfidence;
            _tmpConfidence = _cursor.getDouble(_cursorIndexOfConfidence);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpLastModified;
            _tmpLastModified = _cursor.getLong(_cursorIndexOfLastModified);
            _result = new AutomationRule(_tmpId,_tmpName,_tmpDescription,_tmpConditions,_tmpActions,_tmpIsActive,_tmpPriority,_tmpUsageCount,_tmpCreatedBy,_tmpConfidence,_tmpCreatedAt,_tmpLastModified);
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
  public Flow<List<AutomationRule>> getRulesByCreator(final String createdBy) {
    final String _sql = "SELECT * FROM automation_rules WHERE createdBy = ? ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (createdBy == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, createdBy);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"automation_rules"}, new Callable<List<AutomationRule>>() {
      @Override
      @NonNull
      public List<AutomationRule> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfConditions = CursorUtil.getColumnIndexOrThrow(_cursor, "conditions");
          final int _cursorIndexOfActions = CursorUtil.getColumnIndexOrThrow(_cursor, "actions");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfUsageCount = CursorUtil.getColumnIndexOrThrow(_cursor, "usageCount");
          final int _cursorIndexOfCreatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "createdBy");
          final int _cursorIndexOfConfidence = CursorUtil.getColumnIndexOrThrow(_cursor, "confidence");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfLastModified = CursorUtil.getColumnIndexOrThrow(_cursor, "lastModified");
          final List<AutomationRule> _result = new ArrayList<AutomationRule>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AutomationRule _item;
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
            final List<RuleCondition> _tmpConditions;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfConditions)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfConditions);
            }
            _tmpConditions = __automationTypeConverters.toRuleConditionList(_tmp);
            final List<AutomationAction> _tmpActions;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfActions)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfActions);
            }
            _tmpActions = __automationTypeConverters.toAutomationActionList(_tmp_1);
            final boolean _tmpIsActive;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_2 != 0;
            final int _tmpPriority;
            _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
            final long _tmpUsageCount;
            _tmpUsageCount = _cursor.getLong(_cursorIndexOfUsageCount);
            final AutomationCreatedBy _tmpCreatedBy;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfCreatedBy)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfCreatedBy);
            }
            _tmpCreatedBy = __automationTypeConverters.toAutomationCreatedBy(_tmp_3);
            final double _tmpConfidence;
            _tmpConfidence = _cursor.getDouble(_cursorIndexOfConfidence);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpLastModified;
            _tmpLastModified = _cursor.getLong(_cursorIndexOfLastModified);
            _item = new AutomationRule(_tmpId,_tmpName,_tmpDescription,_tmpConditions,_tmpActions,_tmpIsActive,_tmpPriority,_tmpUsageCount,_tmpCreatedBy,_tmpConfidence,_tmpCreatedAt,_tmpLastModified);
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
  public Object getPopularRules(final long minUsage,
      final Continuation<? super List<AutomationRule>> $completion) {
    final String _sql = "SELECT * FROM automation_rules WHERE usageCount > ? ORDER BY usageCount DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, minUsage);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AutomationRule>>() {
      @Override
      @NonNull
      public List<AutomationRule> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfConditions = CursorUtil.getColumnIndexOrThrow(_cursor, "conditions");
          final int _cursorIndexOfActions = CursorUtil.getColumnIndexOrThrow(_cursor, "actions");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfUsageCount = CursorUtil.getColumnIndexOrThrow(_cursor, "usageCount");
          final int _cursorIndexOfCreatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "createdBy");
          final int _cursorIndexOfConfidence = CursorUtil.getColumnIndexOrThrow(_cursor, "confidence");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfLastModified = CursorUtil.getColumnIndexOrThrow(_cursor, "lastModified");
          final List<AutomationRule> _result = new ArrayList<AutomationRule>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AutomationRule _item;
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
            final List<RuleCondition> _tmpConditions;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfConditions)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfConditions);
            }
            _tmpConditions = __automationTypeConverters.toRuleConditionList(_tmp);
            final List<AutomationAction> _tmpActions;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfActions)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfActions);
            }
            _tmpActions = __automationTypeConverters.toAutomationActionList(_tmp_1);
            final boolean _tmpIsActive;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_2 != 0;
            final int _tmpPriority;
            _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
            final long _tmpUsageCount;
            _tmpUsageCount = _cursor.getLong(_cursorIndexOfUsageCount);
            final AutomationCreatedBy _tmpCreatedBy;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfCreatedBy)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfCreatedBy);
            }
            _tmpCreatedBy = __automationTypeConverters.toAutomationCreatedBy(_tmp_3);
            final double _tmpConfidence;
            _tmpConfidence = _cursor.getDouble(_cursorIndexOfConfidence);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpLastModified;
            _tmpLastModified = _cursor.getLong(_cursorIndexOfLastModified);
            _item = new AutomationRule(_tmpId,_tmpName,_tmpDescription,_tmpConditions,_tmpActions,_tmpIsActive,_tmpPriority,_tmpUsageCount,_tmpCreatedBy,_tmpConfidence,_tmpCreatedAt,_tmpLastModified);
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
  public Object getActiveRuleCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM automation_rules WHERE isActive = 1";
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

  @Override
  public Object getAverageRuleUsage(final Continuation<? super Double> $completion) {
    final String _sql = "SELECT AVG(usageCount) FROM automation_rules WHERE isActive = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Double>() {
      @Override
      @NonNull
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
    }, $completion);
  }

  @Override
  public Object getActiveTransferRules(
      final Continuation<? super List<AutoTransferRule>> $completion) {
    final String _sql = "SELECT * FROM auto_transfer_rules WHERE isActive = 1 ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AutoTransferRule>>() {
      @Override
      @NonNull
      public List<AutoTransferRule> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfSourceAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceAccountId");
          final int _cursorIndexOfTargetAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "targetAccountId");
          final int _cursorIndexOfTransferType = CursorUtil.getColumnIndexOrThrow(_cursor, "transferType");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "frequency");
          final int _cursorIndexOfConditions = CursorUtil.getColumnIndexOrThrow(_cursor, "conditions");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfLastExecuted = CursorUtil.getColumnIndexOrThrow(_cursor, "lastExecuted");
          final int _cursorIndexOfTotalTransferred = CursorUtil.getColumnIndexOrThrow(_cursor, "totalTransferred");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<AutoTransferRule> _result = new ArrayList<AutoTransferRule>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AutoTransferRule _item;
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
            final String _tmpSourceAccountId;
            if (_cursor.isNull(_cursorIndexOfSourceAccountId)) {
              _tmpSourceAccountId = null;
            } else {
              _tmpSourceAccountId = _cursor.getString(_cursorIndexOfSourceAccountId);
            }
            final String _tmpTargetAccountId;
            if (_cursor.isNull(_cursorIndexOfTargetAccountId)) {
              _tmpTargetAccountId = null;
            } else {
              _tmpTargetAccountId = _cursor.getString(_cursorIndexOfTargetAccountId);
            }
            final TransferType _tmpTransferType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfTransferType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfTransferType);
            }
            _tmpTransferType = __automationTypeConverters.toTransferType(_tmp);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final TransferFrequency _tmpFrequency;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfFrequency)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfFrequency);
            }
            _tmpFrequency = __automationTypeConverters.toTransferFrequency(_tmp_1);
            final List<TransferCondition> _tmpConditions;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfConditions)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfConditions);
            }
            _tmpConditions = __automationTypeConverters.toTransferConditionList(_tmp_2);
            final boolean _tmpIsActive;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_3 != 0;
            final Long _tmpLastExecuted;
            if (_cursor.isNull(_cursorIndexOfLastExecuted)) {
              _tmpLastExecuted = null;
            } else {
              _tmpLastExecuted = _cursor.getLong(_cursorIndexOfLastExecuted);
            }
            final double _tmpTotalTransferred;
            _tmpTotalTransferred = _cursor.getDouble(_cursorIndexOfTotalTransferred);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new AutoTransferRule(_tmpId,_tmpName,_tmpDescription,_tmpSourceAccountId,_tmpTargetAccountId,_tmpTransferType,_tmpAmount,_tmpFrequency,_tmpConditions,_tmpIsActive,_tmpLastExecuted,_tmpTotalTransferred,_tmpCreatedAt);
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
  public Flow<List<AutoTransferRule>> getAllTransferRules() {
    final String _sql = "SELECT * FROM auto_transfer_rules ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"auto_transfer_rules"}, new Callable<List<AutoTransferRule>>() {
      @Override
      @NonNull
      public List<AutoTransferRule> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfSourceAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceAccountId");
          final int _cursorIndexOfTargetAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "targetAccountId");
          final int _cursorIndexOfTransferType = CursorUtil.getColumnIndexOrThrow(_cursor, "transferType");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "frequency");
          final int _cursorIndexOfConditions = CursorUtil.getColumnIndexOrThrow(_cursor, "conditions");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfLastExecuted = CursorUtil.getColumnIndexOrThrow(_cursor, "lastExecuted");
          final int _cursorIndexOfTotalTransferred = CursorUtil.getColumnIndexOrThrow(_cursor, "totalTransferred");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<AutoTransferRule> _result = new ArrayList<AutoTransferRule>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AutoTransferRule _item;
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
            final String _tmpSourceAccountId;
            if (_cursor.isNull(_cursorIndexOfSourceAccountId)) {
              _tmpSourceAccountId = null;
            } else {
              _tmpSourceAccountId = _cursor.getString(_cursorIndexOfSourceAccountId);
            }
            final String _tmpTargetAccountId;
            if (_cursor.isNull(_cursorIndexOfTargetAccountId)) {
              _tmpTargetAccountId = null;
            } else {
              _tmpTargetAccountId = _cursor.getString(_cursorIndexOfTargetAccountId);
            }
            final TransferType _tmpTransferType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfTransferType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfTransferType);
            }
            _tmpTransferType = __automationTypeConverters.toTransferType(_tmp);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final TransferFrequency _tmpFrequency;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfFrequency)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfFrequency);
            }
            _tmpFrequency = __automationTypeConverters.toTransferFrequency(_tmp_1);
            final List<TransferCondition> _tmpConditions;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfConditions)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfConditions);
            }
            _tmpConditions = __automationTypeConverters.toTransferConditionList(_tmp_2);
            final boolean _tmpIsActive;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_3 != 0;
            final Long _tmpLastExecuted;
            if (_cursor.isNull(_cursorIndexOfLastExecuted)) {
              _tmpLastExecuted = null;
            } else {
              _tmpLastExecuted = _cursor.getLong(_cursorIndexOfLastExecuted);
            }
            final double _tmpTotalTransferred;
            _tmpTotalTransferred = _cursor.getDouble(_cursorIndexOfTotalTransferred);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new AutoTransferRule(_tmpId,_tmpName,_tmpDescription,_tmpSourceAccountId,_tmpTargetAccountId,_tmpTransferType,_tmpAmount,_tmpFrequency,_tmpConditions,_tmpIsActive,_tmpLastExecuted,_tmpTotalTransferred,_tmpCreatedAt);
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
  public Object getTransferRuleById(final long ruleId,
      final Continuation<? super AutoTransferRule> $completion) {
    final String _sql = "SELECT * FROM auto_transfer_rules WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, ruleId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<AutoTransferRule>() {
      @Override
      @Nullable
      public AutoTransferRule call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfSourceAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceAccountId");
          final int _cursorIndexOfTargetAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "targetAccountId");
          final int _cursorIndexOfTransferType = CursorUtil.getColumnIndexOrThrow(_cursor, "transferType");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "frequency");
          final int _cursorIndexOfConditions = CursorUtil.getColumnIndexOrThrow(_cursor, "conditions");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfLastExecuted = CursorUtil.getColumnIndexOrThrow(_cursor, "lastExecuted");
          final int _cursorIndexOfTotalTransferred = CursorUtil.getColumnIndexOrThrow(_cursor, "totalTransferred");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final AutoTransferRule _result;
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
            final String _tmpSourceAccountId;
            if (_cursor.isNull(_cursorIndexOfSourceAccountId)) {
              _tmpSourceAccountId = null;
            } else {
              _tmpSourceAccountId = _cursor.getString(_cursorIndexOfSourceAccountId);
            }
            final String _tmpTargetAccountId;
            if (_cursor.isNull(_cursorIndexOfTargetAccountId)) {
              _tmpTargetAccountId = null;
            } else {
              _tmpTargetAccountId = _cursor.getString(_cursorIndexOfTargetAccountId);
            }
            final TransferType _tmpTransferType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfTransferType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfTransferType);
            }
            _tmpTransferType = __automationTypeConverters.toTransferType(_tmp);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final TransferFrequency _tmpFrequency;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfFrequency)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfFrequency);
            }
            _tmpFrequency = __automationTypeConverters.toTransferFrequency(_tmp_1);
            final List<TransferCondition> _tmpConditions;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfConditions)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfConditions);
            }
            _tmpConditions = __automationTypeConverters.toTransferConditionList(_tmp_2);
            final boolean _tmpIsActive;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_3 != 0;
            final Long _tmpLastExecuted;
            if (_cursor.isNull(_cursorIndexOfLastExecuted)) {
              _tmpLastExecuted = null;
            } else {
              _tmpLastExecuted = _cursor.getLong(_cursorIndexOfLastExecuted);
            }
            final double _tmpTotalTransferred;
            _tmpTotalTransferred = _cursor.getDouble(_cursorIndexOfTotalTransferred);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _result = new AutoTransferRule(_tmpId,_tmpName,_tmpDescription,_tmpSourceAccountId,_tmpTargetAccountId,_tmpTransferType,_tmpAmount,_tmpFrequency,_tmpConditions,_tmpIsActive,_tmpLastExecuted,_tmpTotalTransferred,_tmpCreatedAt);
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
  public Object getTransferRulesByAccount(final String accountId,
      final Continuation<? super List<AutoTransferRule>> $completion) {
    final String _sql = "SELECT * FROM auto_transfer_rules WHERE sourceAccountId = ? OR targetAccountId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (accountId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, accountId);
    }
    _argIndex = 2;
    if (accountId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, accountId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AutoTransferRule>>() {
      @Override
      @NonNull
      public List<AutoTransferRule> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfSourceAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceAccountId");
          final int _cursorIndexOfTargetAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "targetAccountId");
          final int _cursorIndexOfTransferType = CursorUtil.getColumnIndexOrThrow(_cursor, "transferType");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "frequency");
          final int _cursorIndexOfConditions = CursorUtil.getColumnIndexOrThrow(_cursor, "conditions");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfLastExecuted = CursorUtil.getColumnIndexOrThrow(_cursor, "lastExecuted");
          final int _cursorIndexOfTotalTransferred = CursorUtil.getColumnIndexOrThrow(_cursor, "totalTransferred");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<AutoTransferRule> _result = new ArrayList<AutoTransferRule>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AutoTransferRule _item;
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
            final String _tmpSourceAccountId;
            if (_cursor.isNull(_cursorIndexOfSourceAccountId)) {
              _tmpSourceAccountId = null;
            } else {
              _tmpSourceAccountId = _cursor.getString(_cursorIndexOfSourceAccountId);
            }
            final String _tmpTargetAccountId;
            if (_cursor.isNull(_cursorIndexOfTargetAccountId)) {
              _tmpTargetAccountId = null;
            } else {
              _tmpTargetAccountId = _cursor.getString(_cursorIndexOfTargetAccountId);
            }
            final TransferType _tmpTransferType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfTransferType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfTransferType);
            }
            _tmpTransferType = __automationTypeConverters.toTransferType(_tmp);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final TransferFrequency _tmpFrequency;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfFrequency)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfFrequency);
            }
            _tmpFrequency = __automationTypeConverters.toTransferFrequency(_tmp_1);
            final List<TransferCondition> _tmpConditions;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfConditions)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfConditions);
            }
            _tmpConditions = __automationTypeConverters.toTransferConditionList(_tmp_2);
            final boolean _tmpIsActive;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_3 != 0;
            final Long _tmpLastExecuted;
            if (_cursor.isNull(_cursorIndexOfLastExecuted)) {
              _tmpLastExecuted = null;
            } else {
              _tmpLastExecuted = _cursor.getLong(_cursorIndexOfLastExecuted);
            }
            final double _tmpTotalTransferred;
            _tmpTotalTransferred = _cursor.getDouble(_cursorIndexOfTotalTransferred);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new AutoTransferRule(_tmpId,_tmpName,_tmpDescription,_tmpSourceAccountId,_tmpTargetAccountId,_tmpTransferType,_tmpAmount,_tmpFrequency,_tmpConditions,_tmpIsActive,_tmpLastExecuted,_tmpTotalTransferred,_tmpCreatedAt);
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
  public Object getActiveInsights(final long currentTime,
      final Continuation<? super List<SmartInsight>> $completion) {
    final String _sql = "SELECT * FROM smart_insights WHERE isDismissed = 0 AND (expiresAt IS NULL OR expiresAt > ?) ORDER BY priority DESC, createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, currentTime);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<SmartInsight>>() {
      @Override
      @NonNull
      public List<SmartInsight> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfActionable = CursorUtil.getColumnIndexOrThrow(_cursor, "actionable");
          final int _cursorIndexOfActions = CursorUtil.getColumnIndexOrThrow(_cursor, "actions");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfConfidence = CursorUtil.getColumnIndexOrThrow(_cursor, "confidence");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfImpact = CursorUtil.getColumnIndexOrThrow(_cursor, "impact");
          final int _cursorIndexOfIsRead = CursorUtil.getColumnIndexOrThrow(_cursor, "isRead");
          final int _cursorIndexOfIsDismissed = CursorUtil.getColumnIndexOrThrow(_cursor, "isDismissed");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfExpiresAt = CursorUtil.getColumnIndexOrThrow(_cursor, "expiresAt");
          final List<SmartInsight> _result = new ArrayList<SmartInsight>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SmartInsight _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final InsightType _tmpType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfType);
            }
            _tmpType = __mLTypeConverters.toInsightType(_tmp);
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
            final boolean _tmpActionable;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfActionable);
            _tmpActionable = _tmp_1 != 0;
            final List<String> _tmpActions;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfActions)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfActions);
            }
            _tmpActions = __automationTypeConverters.toStringList(_tmp_2);
            final InsightPriority _tmpPriority;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfPriority)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfPriority);
            }
            _tmpPriority = __mLTypeConverters.toInsightPriority(_tmp_3);
            final double _tmpConfidence;
            _tmpConfidence = _cursor.getDouble(_cursorIndexOfConfidence);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final ImpactLevel _tmpImpact;
            final String _tmp_4;
            if (_cursor.isNull(_cursorIndexOfImpact)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getString(_cursorIndexOfImpact);
            }
            _tmpImpact = __mLTypeConverters.toImpactLevel(_tmp_4);
            final boolean _tmpIsRead;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfIsRead);
            _tmpIsRead = _tmp_5 != 0;
            final boolean _tmpIsDismissed;
            final int _tmp_6;
            _tmp_6 = _cursor.getInt(_cursorIndexOfIsDismissed);
            _tmpIsDismissed = _tmp_6 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpExpiresAt;
            if (_cursor.isNull(_cursorIndexOfExpiresAt)) {
              _tmpExpiresAt = null;
            } else {
              _tmpExpiresAt = _cursor.getLong(_cursorIndexOfExpiresAt);
            }
            _item = new SmartInsight(_tmpId,_tmpType,_tmpTitle,_tmpDescription,_tmpActionable,_tmpActions,_tmpPriority,_tmpConfidence,_tmpCategory,_tmpImpact,_tmpIsRead,_tmpIsDismissed,_tmpCreatedAt,_tmpExpiresAt);
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
  public Object getInsightsByType(final String type,
      final Continuation<? super List<SmartInsight>> $completion) {
    final String _sql = "SELECT * FROM smart_insights WHERE type = ? AND isDismissed = 0 ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (type == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, type);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<SmartInsight>>() {
      @Override
      @NonNull
      public List<SmartInsight> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfActionable = CursorUtil.getColumnIndexOrThrow(_cursor, "actionable");
          final int _cursorIndexOfActions = CursorUtil.getColumnIndexOrThrow(_cursor, "actions");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfConfidence = CursorUtil.getColumnIndexOrThrow(_cursor, "confidence");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfImpact = CursorUtil.getColumnIndexOrThrow(_cursor, "impact");
          final int _cursorIndexOfIsRead = CursorUtil.getColumnIndexOrThrow(_cursor, "isRead");
          final int _cursorIndexOfIsDismissed = CursorUtil.getColumnIndexOrThrow(_cursor, "isDismissed");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfExpiresAt = CursorUtil.getColumnIndexOrThrow(_cursor, "expiresAt");
          final List<SmartInsight> _result = new ArrayList<SmartInsight>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SmartInsight _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final InsightType _tmpType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfType);
            }
            _tmpType = __mLTypeConverters.toInsightType(_tmp);
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
            final boolean _tmpActionable;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfActionable);
            _tmpActionable = _tmp_1 != 0;
            final List<String> _tmpActions;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfActions)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfActions);
            }
            _tmpActions = __automationTypeConverters.toStringList(_tmp_2);
            final InsightPriority _tmpPriority;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfPriority)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfPriority);
            }
            _tmpPriority = __mLTypeConverters.toInsightPriority(_tmp_3);
            final double _tmpConfidence;
            _tmpConfidence = _cursor.getDouble(_cursorIndexOfConfidence);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final ImpactLevel _tmpImpact;
            final String _tmp_4;
            if (_cursor.isNull(_cursorIndexOfImpact)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getString(_cursorIndexOfImpact);
            }
            _tmpImpact = __mLTypeConverters.toImpactLevel(_tmp_4);
            final boolean _tmpIsRead;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfIsRead);
            _tmpIsRead = _tmp_5 != 0;
            final boolean _tmpIsDismissed;
            final int _tmp_6;
            _tmp_6 = _cursor.getInt(_cursorIndexOfIsDismissed);
            _tmpIsDismissed = _tmp_6 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpExpiresAt;
            if (_cursor.isNull(_cursorIndexOfExpiresAt)) {
              _tmpExpiresAt = null;
            } else {
              _tmpExpiresAt = _cursor.getLong(_cursorIndexOfExpiresAt);
            }
            _item = new SmartInsight(_tmpId,_tmpType,_tmpTitle,_tmpDescription,_tmpActionable,_tmpActions,_tmpPriority,_tmpConfidence,_tmpCategory,_tmpImpact,_tmpIsRead,_tmpIsDismissed,_tmpCreatedAt,_tmpExpiresAt);
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
  public Object getInsightsByCategory(final String category,
      final Continuation<? super List<SmartInsight>> $completion) {
    final String _sql = "SELECT * FROM smart_insights WHERE category = ? AND isDismissed = 0 ORDER BY priority DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (category == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, category);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<SmartInsight>>() {
      @Override
      @NonNull
      public List<SmartInsight> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfActionable = CursorUtil.getColumnIndexOrThrow(_cursor, "actionable");
          final int _cursorIndexOfActions = CursorUtil.getColumnIndexOrThrow(_cursor, "actions");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfConfidence = CursorUtil.getColumnIndexOrThrow(_cursor, "confidence");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfImpact = CursorUtil.getColumnIndexOrThrow(_cursor, "impact");
          final int _cursorIndexOfIsRead = CursorUtil.getColumnIndexOrThrow(_cursor, "isRead");
          final int _cursorIndexOfIsDismissed = CursorUtil.getColumnIndexOrThrow(_cursor, "isDismissed");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfExpiresAt = CursorUtil.getColumnIndexOrThrow(_cursor, "expiresAt");
          final List<SmartInsight> _result = new ArrayList<SmartInsight>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SmartInsight _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final InsightType _tmpType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfType);
            }
            _tmpType = __mLTypeConverters.toInsightType(_tmp);
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
            final boolean _tmpActionable;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfActionable);
            _tmpActionable = _tmp_1 != 0;
            final List<String> _tmpActions;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfActions)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfActions);
            }
            _tmpActions = __automationTypeConverters.toStringList(_tmp_2);
            final InsightPriority _tmpPriority;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfPriority)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfPriority);
            }
            _tmpPriority = __mLTypeConverters.toInsightPriority(_tmp_3);
            final double _tmpConfidence;
            _tmpConfidence = _cursor.getDouble(_cursorIndexOfConfidence);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final ImpactLevel _tmpImpact;
            final String _tmp_4;
            if (_cursor.isNull(_cursorIndexOfImpact)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getString(_cursorIndexOfImpact);
            }
            _tmpImpact = __mLTypeConverters.toImpactLevel(_tmp_4);
            final boolean _tmpIsRead;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfIsRead);
            _tmpIsRead = _tmp_5 != 0;
            final boolean _tmpIsDismissed;
            final int _tmp_6;
            _tmp_6 = _cursor.getInt(_cursorIndexOfIsDismissed);
            _tmpIsDismissed = _tmp_6 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpExpiresAt;
            if (_cursor.isNull(_cursorIndexOfExpiresAt)) {
              _tmpExpiresAt = null;
            } else {
              _tmpExpiresAt = _cursor.getLong(_cursorIndexOfExpiresAt);
            }
            _item = new SmartInsight(_tmpId,_tmpType,_tmpTitle,_tmpDescription,_tmpActionable,_tmpActions,_tmpPriority,_tmpConfidence,_tmpCategory,_tmpImpact,_tmpIsRead,_tmpIsDismissed,_tmpCreatedAt,_tmpExpiresAt);
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
  public Object getInsightsByPriority(final String priority,
      final Continuation<? super List<SmartInsight>> $completion) {
    final String _sql = "SELECT * FROM smart_insights WHERE priority = ? AND isDismissed = 0 ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (priority == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, priority);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<SmartInsight>>() {
      @Override
      @NonNull
      public List<SmartInsight> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfActionable = CursorUtil.getColumnIndexOrThrow(_cursor, "actionable");
          final int _cursorIndexOfActions = CursorUtil.getColumnIndexOrThrow(_cursor, "actions");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfConfidence = CursorUtil.getColumnIndexOrThrow(_cursor, "confidence");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfImpact = CursorUtil.getColumnIndexOrThrow(_cursor, "impact");
          final int _cursorIndexOfIsRead = CursorUtil.getColumnIndexOrThrow(_cursor, "isRead");
          final int _cursorIndexOfIsDismissed = CursorUtil.getColumnIndexOrThrow(_cursor, "isDismissed");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfExpiresAt = CursorUtil.getColumnIndexOrThrow(_cursor, "expiresAt");
          final List<SmartInsight> _result = new ArrayList<SmartInsight>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SmartInsight _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final InsightType _tmpType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfType);
            }
            _tmpType = __mLTypeConverters.toInsightType(_tmp);
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
            final boolean _tmpActionable;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfActionable);
            _tmpActionable = _tmp_1 != 0;
            final List<String> _tmpActions;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfActions)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfActions);
            }
            _tmpActions = __automationTypeConverters.toStringList(_tmp_2);
            final InsightPriority _tmpPriority;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfPriority)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfPriority);
            }
            _tmpPriority = __mLTypeConverters.toInsightPriority(_tmp_3);
            final double _tmpConfidence;
            _tmpConfidence = _cursor.getDouble(_cursorIndexOfConfidence);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final ImpactLevel _tmpImpact;
            final String _tmp_4;
            if (_cursor.isNull(_cursorIndexOfImpact)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getString(_cursorIndexOfImpact);
            }
            _tmpImpact = __mLTypeConverters.toImpactLevel(_tmp_4);
            final boolean _tmpIsRead;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfIsRead);
            _tmpIsRead = _tmp_5 != 0;
            final boolean _tmpIsDismissed;
            final int _tmp_6;
            _tmp_6 = _cursor.getInt(_cursorIndexOfIsDismissed);
            _tmpIsDismissed = _tmp_6 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpExpiresAt;
            if (_cursor.isNull(_cursorIndexOfExpiresAt)) {
              _tmpExpiresAt = null;
            } else {
              _tmpExpiresAt = _cursor.getLong(_cursorIndexOfExpiresAt);
            }
            _item = new SmartInsight(_tmpId,_tmpType,_tmpTitle,_tmpDescription,_tmpActionable,_tmpActions,_tmpPriority,_tmpConfidence,_tmpCategory,_tmpImpact,_tmpIsRead,_tmpIsDismissed,_tmpCreatedAt,_tmpExpiresAt);
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
  public Object getUnreadInsights(final Continuation<? super List<SmartInsight>> $completion) {
    final String _sql = "SELECT * FROM smart_insights WHERE isRead = 0 AND isDismissed = 0 ORDER BY priority DESC, createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<SmartInsight>>() {
      @Override
      @NonNull
      public List<SmartInsight> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfActionable = CursorUtil.getColumnIndexOrThrow(_cursor, "actionable");
          final int _cursorIndexOfActions = CursorUtil.getColumnIndexOrThrow(_cursor, "actions");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfConfidence = CursorUtil.getColumnIndexOrThrow(_cursor, "confidence");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfImpact = CursorUtil.getColumnIndexOrThrow(_cursor, "impact");
          final int _cursorIndexOfIsRead = CursorUtil.getColumnIndexOrThrow(_cursor, "isRead");
          final int _cursorIndexOfIsDismissed = CursorUtil.getColumnIndexOrThrow(_cursor, "isDismissed");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfExpiresAt = CursorUtil.getColumnIndexOrThrow(_cursor, "expiresAt");
          final List<SmartInsight> _result = new ArrayList<SmartInsight>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SmartInsight _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final InsightType _tmpType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfType);
            }
            _tmpType = __mLTypeConverters.toInsightType(_tmp);
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
            final boolean _tmpActionable;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfActionable);
            _tmpActionable = _tmp_1 != 0;
            final List<String> _tmpActions;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfActions)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfActions);
            }
            _tmpActions = __automationTypeConverters.toStringList(_tmp_2);
            final InsightPriority _tmpPriority;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfPriority)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfPriority);
            }
            _tmpPriority = __mLTypeConverters.toInsightPriority(_tmp_3);
            final double _tmpConfidence;
            _tmpConfidence = _cursor.getDouble(_cursorIndexOfConfidence);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final ImpactLevel _tmpImpact;
            final String _tmp_4;
            if (_cursor.isNull(_cursorIndexOfImpact)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getString(_cursorIndexOfImpact);
            }
            _tmpImpact = __mLTypeConverters.toImpactLevel(_tmp_4);
            final boolean _tmpIsRead;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfIsRead);
            _tmpIsRead = _tmp_5 != 0;
            final boolean _tmpIsDismissed;
            final int _tmp_6;
            _tmp_6 = _cursor.getInt(_cursorIndexOfIsDismissed);
            _tmpIsDismissed = _tmp_6 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpExpiresAt;
            if (_cursor.isNull(_cursorIndexOfExpiresAt)) {
              _tmpExpiresAt = null;
            } else {
              _tmpExpiresAt = _cursor.getLong(_cursorIndexOfExpiresAt);
            }
            _item = new SmartInsight(_tmpId,_tmpType,_tmpTitle,_tmpDescription,_tmpActionable,_tmpActions,_tmpPriority,_tmpConfidence,_tmpCategory,_tmpImpact,_tmpIsRead,_tmpIsDismissed,_tmpCreatedAt,_tmpExpiresAt);
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
  public Object getUnreadInsightCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM smart_insights WHERE isRead = 0 AND isDismissed = 0";
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

  @Override
  public Object getInsightCountByPriority(final String priority,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM smart_insights WHERE priority = ? AND isDismissed = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (priority == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, priority);
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
  public Object getAutomationStatistics(
      final Continuation<? super AutomationStatistics> $completion) {
    final String _sql = "\n"
            + "        SELECT \n"
            + "            COUNT(*) as totalRules,\n"
            + "            SUM(CASE WHEN isActive = 1 THEN 1 ELSE 0 END) as activeRules,\n"
            + "            SUM(usageCount) as totalUsage,\n"
            + "            AVG(usageCount) as averageUsage\n"
            + "        FROM automation_rules\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<AutomationStatistics>() {
      @Override
      @NonNull
      public AutomationStatistics call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTotalRules = 0;
          final int _cursorIndexOfActiveRules = 1;
          final int _cursorIndexOfTotalUsage = 2;
          final int _cursorIndexOfAverageUsage = 3;
          final AutomationStatistics _result;
          if (_cursor.moveToFirst()) {
            final int _tmpTotalRules;
            _tmpTotalRules = _cursor.getInt(_cursorIndexOfTotalRules);
            final int _tmpActiveRules;
            _tmpActiveRules = _cursor.getInt(_cursorIndexOfActiveRules);
            final long _tmpTotalUsage;
            _tmpTotalUsage = _cursor.getLong(_cursorIndexOfTotalUsage);
            final double _tmpAverageUsage;
            _tmpAverageUsage = _cursor.getDouble(_cursorIndexOfAverageUsage);
            _result = new AutomationStatistics(_tmpTotalRules,_tmpActiveRules,_tmpTotalUsage,_tmpAverageUsage);
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
  public Object getInsightStatistics(
      final Continuation<? super List<InsightTypeStatistics>> $completion) {
    final String _sql = "\n"
            + "        SELECT \n"
            + "            type,\n"
            + "            COUNT(*) as count,\n"
            + "            AVG(confidence) as averageConfidence\n"
            + "        FROM smart_insights \n"
            + "        WHERE isDismissed = 0 \n"
            + "        GROUP BY type\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<InsightTypeStatistics>>() {
      @Override
      @NonNull
      public List<InsightTypeStatistics> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfType = 0;
          final int _cursorIndexOfCount = 1;
          final int _cursorIndexOfAverageConfidence = 2;
          final List<InsightTypeStatistics> _result = new ArrayList<InsightTypeStatistics>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final InsightTypeStatistics _item;
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final int _tmpCount;
            _tmpCount = _cursor.getInt(_cursorIndexOfCount);
            final double _tmpAverageConfidence;
            _tmpAverageConfidence = _cursor.getDouble(_cursorIndexOfAverageConfidence);
            _item = new InsightTypeStatistics(_tmpType,_tmpCount,_tmpAverageConfidence);
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
  public Object getRuleCreationHistory(final long startTime,
      final Continuation<? super List<RuleCreationHistory>> $completion) {
    final String _sql = "\n"
            + "        SELECT \n"
            + "            DATE(createdAt/1000, 'unixepoch') as date,\n"
            + "            COUNT(*) as count\n"
            + "        FROM automation_rules \n"
            + "        WHERE createdAt > ?\n"
            + "        GROUP BY DATE(createdAt/1000, 'unixepoch')\n"
            + "        ORDER BY date DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, startTime);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<RuleCreationHistory>>() {
      @Override
      @NonNull
      public List<RuleCreationHistory> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDate = 0;
          final int _cursorIndexOfCount = 1;
          final List<RuleCreationHistory> _result = new ArrayList<RuleCreationHistory>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RuleCreationHistory _item;
            final String _tmpDate;
            if (_cursor.isNull(_cursorIndexOfDate)) {
              _tmpDate = null;
            } else {
              _tmpDate = _cursor.getString(_cursorIndexOfDate);
            }
            final int _tmpCount;
            _tmpCount = _cursor.getInt(_cursorIndexOfCount);
            _item = new RuleCreationHistory(_tmpDate,_tmpCount);
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
  public Object getTopUsedRules(final long totalUsage,
      final Continuation<? super List<RuleUsageStatistics>> $completion) {
    final String _sql = "\n"
            + "        SELECT \n"
            + "            name,\n"
            + "            usageCount,\n"
            + "            (usageCount * 1.0 / ?) * 100 as usagePercentage\n"
            + "        FROM automation_rules \n"
            + "        WHERE isActive = 1 AND usageCount > 0\n"
            + "        ORDER BY usageCount DESC\n"
            + "        LIMIT 10\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, totalUsage);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<RuleUsageStatistics>>() {
      @Override
      @NonNull
      public List<RuleUsageStatistics> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfName = 0;
          final int _cursorIndexOfUsageCount = 1;
          final int _cursorIndexOfUsagePercentage = 2;
          final List<RuleUsageStatistics> _result = new ArrayList<RuleUsageStatistics>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RuleUsageStatistics _item;
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final long _tmpUsageCount;
            _tmpUsageCount = _cursor.getLong(_cursorIndexOfUsageCount);
            final double _tmpUsagePercentage;
            _tmpUsagePercentage = _cursor.getDouble(_cursorIndexOfUsagePercentage);
            _item = new RuleUsageStatistics(_tmpName,_tmpUsageCount,_tmpUsagePercentage);
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
  public Object getAllRulesForBackup(final Continuation<? super List<AutomationRule>> $completion) {
    final String _sql = "SELECT * FROM automation_rules";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AutomationRule>>() {
      @Override
      @NonNull
      public List<AutomationRule> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfConditions = CursorUtil.getColumnIndexOrThrow(_cursor, "conditions");
          final int _cursorIndexOfActions = CursorUtil.getColumnIndexOrThrow(_cursor, "actions");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfUsageCount = CursorUtil.getColumnIndexOrThrow(_cursor, "usageCount");
          final int _cursorIndexOfCreatedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "createdBy");
          final int _cursorIndexOfConfidence = CursorUtil.getColumnIndexOrThrow(_cursor, "confidence");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfLastModified = CursorUtil.getColumnIndexOrThrow(_cursor, "lastModified");
          final List<AutomationRule> _result = new ArrayList<AutomationRule>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AutomationRule _item;
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
            final List<RuleCondition> _tmpConditions;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfConditions)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfConditions);
            }
            _tmpConditions = __automationTypeConverters.toRuleConditionList(_tmp);
            final List<AutomationAction> _tmpActions;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfActions)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfActions);
            }
            _tmpActions = __automationTypeConverters.toAutomationActionList(_tmp_1);
            final boolean _tmpIsActive;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_2 != 0;
            final int _tmpPriority;
            _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
            final long _tmpUsageCount;
            _tmpUsageCount = _cursor.getLong(_cursorIndexOfUsageCount);
            final AutomationCreatedBy _tmpCreatedBy;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfCreatedBy)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfCreatedBy);
            }
            _tmpCreatedBy = __automationTypeConverters.toAutomationCreatedBy(_tmp_3);
            final double _tmpConfidence;
            _tmpConfidence = _cursor.getDouble(_cursorIndexOfConfidence);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpLastModified;
            _tmpLastModified = _cursor.getLong(_cursorIndexOfLastModified);
            _item = new AutomationRule(_tmpId,_tmpName,_tmpDescription,_tmpConditions,_tmpActions,_tmpIsActive,_tmpPriority,_tmpUsageCount,_tmpCreatedBy,_tmpConfidence,_tmpCreatedAt,_tmpLastModified);
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
  public Object getAllTransferRulesForBackup(
      final Continuation<? super List<AutoTransferRule>> $completion) {
    final String _sql = "SELECT * FROM auto_transfer_rules";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AutoTransferRule>>() {
      @Override
      @NonNull
      public List<AutoTransferRule> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfSourceAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "sourceAccountId");
          final int _cursorIndexOfTargetAccountId = CursorUtil.getColumnIndexOrThrow(_cursor, "targetAccountId");
          final int _cursorIndexOfTransferType = CursorUtil.getColumnIndexOrThrow(_cursor, "transferType");
          final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
          final int _cursorIndexOfFrequency = CursorUtil.getColumnIndexOrThrow(_cursor, "frequency");
          final int _cursorIndexOfConditions = CursorUtil.getColumnIndexOrThrow(_cursor, "conditions");
          final int _cursorIndexOfIsActive = CursorUtil.getColumnIndexOrThrow(_cursor, "isActive");
          final int _cursorIndexOfLastExecuted = CursorUtil.getColumnIndexOrThrow(_cursor, "lastExecuted");
          final int _cursorIndexOfTotalTransferred = CursorUtil.getColumnIndexOrThrow(_cursor, "totalTransferred");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<AutoTransferRule> _result = new ArrayList<AutoTransferRule>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AutoTransferRule _item;
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
            final String _tmpSourceAccountId;
            if (_cursor.isNull(_cursorIndexOfSourceAccountId)) {
              _tmpSourceAccountId = null;
            } else {
              _tmpSourceAccountId = _cursor.getString(_cursorIndexOfSourceAccountId);
            }
            final String _tmpTargetAccountId;
            if (_cursor.isNull(_cursorIndexOfTargetAccountId)) {
              _tmpTargetAccountId = null;
            } else {
              _tmpTargetAccountId = _cursor.getString(_cursorIndexOfTargetAccountId);
            }
            final TransferType _tmpTransferType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfTransferType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfTransferType);
            }
            _tmpTransferType = __automationTypeConverters.toTransferType(_tmp);
            final double _tmpAmount;
            _tmpAmount = _cursor.getDouble(_cursorIndexOfAmount);
            final TransferFrequency _tmpFrequency;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfFrequency)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfFrequency);
            }
            _tmpFrequency = __automationTypeConverters.toTransferFrequency(_tmp_1);
            final List<TransferCondition> _tmpConditions;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfConditions)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfConditions);
            }
            _tmpConditions = __automationTypeConverters.toTransferConditionList(_tmp_2);
            final boolean _tmpIsActive;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsActive);
            _tmpIsActive = _tmp_3 != 0;
            final Long _tmpLastExecuted;
            if (_cursor.isNull(_cursorIndexOfLastExecuted)) {
              _tmpLastExecuted = null;
            } else {
              _tmpLastExecuted = _cursor.getLong(_cursorIndexOfLastExecuted);
            }
            final double _tmpTotalTransferred;
            _tmpTotalTransferred = _cursor.getDouble(_cursorIndexOfTotalTransferred);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new AutoTransferRule(_tmpId,_tmpName,_tmpDescription,_tmpSourceAccountId,_tmpTargetAccountId,_tmpTransferType,_tmpAmount,_tmpFrequency,_tmpConditions,_tmpIsActive,_tmpLastExecuted,_tmpTotalTransferred,_tmpCreatedAt);
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
  public Object getAllActiveInsightsForBackup(
      final Continuation<? super List<SmartInsight>> $completion) {
    final String _sql = "SELECT * FROM smart_insights WHERE isDismissed = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<SmartInsight>>() {
      @Override
      @NonNull
      public List<SmartInsight> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfActionable = CursorUtil.getColumnIndexOrThrow(_cursor, "actionable");
          final int _cursorIndexOfActions = CursorUtil.getColumnIndexOrThrow(_cursor, "actions");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfConfidence = CursorUtil.getColumnIndexOrThrow(_cursor, "confidence");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfImpact = CursorUtil.getColumnIndexOrThrow(_cursor, "impact");
          final int _cursorIndexOfIsRead = CursorUtil.getColumnIndexOrThrow(_cursor, "isRead");
          final int _cursorIndexOfIsDismissed = CursorUtil.getColumnIndexOrThrow(_cursor, "isDismissed");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfExpiresAt = CursorUtil.getColumnIndexOrThrow(_cursor, "expiresAt");
          final List<SmartInsight> _result = new ArrayList<SmartInsight>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SmartInsight _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final InsightType _tmpType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfType);
            }
            _tmpType = __mLTypeConverters.toInsightType(_tmp);
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
            final boolean _tmpActionable;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfActionable);
            _tmpActionable = _tmp_1 != 0;
            final List<String> _tmpActions;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfActions)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfActions);
            }
            _tmpActions = __automationTypeConverters.toStringList(_tmp_2);
            final InsightPriority _tmpPriority;
            final String _tmp_3;
            if (_cursor.isNull(_cursorIndexOfPriority)) {
              _tmp_3 = null;
            } else {
              _tmp_3 = _cursor.getString(_cursorIndexOfPriority);
            }
            _tmpPriority = __mLTypeConverters.toInsightPriority(_tmp_3);
            final double _tmpConfidence;
            _tmpConfidence = _cursor.getDouble(_cursorIndexOfConfidence);
            final String _tmpCategory;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmpCategory = null;
            } else {
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            }
            final ImpactLevel _tmpImpact;
            final String _tmp_4;
            if (_cursor.isNull(_cursorIndexOfImpact)) {
              _tmp_4 = null;
            } else {
              _tmp_4 = _cursor.getString(_cursorIndexOfImpact);
            }
            _tmpImpact = __mLTypeConverters.toImpactLevel(_tmp_4);
            final boolean _tmpIsRead;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfIsRead);
            _tmpIsRead = _tmp_5 != 0;
            final boolean _tmpIsDismissed;
            final int _tmp_6;
            _tmp_6 = _cursor.getInt(_cursorIndexOfIsDismissed);
            _tmpIsDismissed = _tmp_6 != 0;
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpExpiresAt;
            if (_cursor.isNull(_cursorIndexOfExpiresAt)) {
              _tmpExpiresAt = null;
            } else {
              _tmpExpiresAt = _cursor.getLong(_cursorIndexOfExpiresAt);
            }
            _item = new SmartInsight(_tmpId,_tmpType,_tmpTitle,_tmpDescription,_tmpActionable,_tmpActions,_tmpPriority,_tmpConfidence,_tmpCategory,_tmpImpact,_tmpIsRead,_tmpIsDismissed,_tmpCreatedAt,_tmpExpiresAt);
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
