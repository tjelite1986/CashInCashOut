package com.example.budgetapp.database;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.example.budgetapp.analytics.investments.dao.ExchangeRateDao;
import com.example.budgetapp.analytics.investments.dao.ExchangeRateDao_Impl;
import com.example.budgetapp.analytics.investments.dao.InvestmentDao;
import com.example.budgetapp.analytics.investments.dao.InvestmentDao_Impl;
import com.example.budgetapp.automation.dao.AutomationRuleDao;
import com.example.budgetapp.automation.dao.AutomationRuleDao_Impl;
import com.example.budgetapp.database.dao.AppUsageDao;
import com.example.budgetapp.database.dao.AppUsageDao_Impl;
import com.example.budgetapp.database.dao.BudgetDao;
import com.example.budgetapp.database.dao.BudgetDao_Impl;
import com.example.budgetapp.database.dao.CategoryDao;
import com.example.budgetapp.database.dao.CategoryDao_Impl;
import com.example.budgetapp.database.dao.ExpenseDao;
import com.example.budgetapp.database.dao.ExpenseDao_Impl;
import com.example.budgetapp.database.dao.FinancialGoalDao;
import com.example.budgetapp.database.dao.FinancialGoalDao_Impl;
import com.example.budgetapp.database.dao.FinancialInsightDao;
import com.example.budgetapp.database.dao.FinancialInsightDao_Impl;
import com.example.budgetapp.database.dao.IncomeDao;
import com.example.budgetapp.database.dao.IncomeDao_Impl;
import com.example.budgetapp.database.dao.LoanDao;
import com.example.budgetapp.database.dao.LoanDao_Impl;
import com.example.budgetapp.database.dao.PriceHistoryDao;
import com.example.budgetapp.database.dao.PriceHistoryDao_Impl;
import com.example.budgetapp.database.dao.ProductCategoryDao;
import com.example.budgetapp.database.dao.ProductCategoryDao_Impl;
import com.example.budgetapp.database.dao.ProductDao;
import com.example.budgetapp.database.dao.ProductDao_Impl;
import com.example.budgetapp.database.dao.ProductStoreDao;
import com.example.budgetapp.database.dao.ProductStoreDao_Impl;
import com.example.budgetapp.database.dao.ReceiptDao;
import com.example.budgetapp.database.dao.ReceiptDao_Impl;
import com.example.budgetapp.database.dao.ReceiptItemDao;
import com.example.budgetapp.database.dao.ReceiptItemDao_Impl;
import com.example.budgetapp.database.dao.ReminderSettingsDao;
import com.example.budgetapp.database.dao.ReminderSettingsDao_Impl;
import com.example.budgetapp.database.dao.ShoppingListDao;
import com.example.budgetapp.database.dao.ShoppingListDao_Impl;
import com.example.budgetapp.database.dao.ShoppingListItemDao;
import com.example.budgetapp.database.dao.ShoppingListItemDao_Impl;
import com.example.budgetapp.database.dao.SpendingForecastDao;
import com.example.budgetapp.database.dao.SpendingForecastDao_Impl;
import com.example.budgetapp.database.dao.SpendingPatternDao;
import com.example.budgetapp.database.dao.SpendingPatternDao_Impl;
import com.example.budgetapp.database.dao.StoreChainDao;
import com.example.budgetapp.database.dao.StoreChainDao_Impl;
import com.example.budgetapp.database.dao.StoreDao;
import com.example.budgetapp.database.dao.StoreDao_Impl;
import com.example.budgetapp.database.dao.TransactionNotificationDao;
import com.example.budgetapp.database.dao.TransactionNotificationDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class BudgetDatabase_Impl extends BudgetDatabase {
  private volatile ProductDao _productDao;

  private volatile ProductCategoryDao _productCategoryDao;

  private volatile CategoryDao _categoryDao;

  private volatile StoreDao _storeDao;

  private volatile StoreChainDao _storeChainDao;

  private volatile ProductStoreDao _productStoreDao;

  private volatile IncomeDao _incomeDao;

  private volatile ExpenseDao _expenseDao;

  private volatile LoanDao _loanDao;

  private volatile BudgetDao _budgetDao;

  private volatile ReceiptDao _receiptDao;

  private volatile ReceiptItemDao _receiptItemDao;

  private volatile ShoppingListDao _shoppingListDao;

  private volatile ShoppingListItemDao _shoppingListItemDao;

  private volatile PriceHistoryDao _priceHistoryDao;

  private volatile ReminderSettingsDao _reminderSettingsDao;

  private volatile AppUsageDao _appUsageDao;

  private volatile TransactionNotificationDao _transactionNotificationDao;

  private volatile FinancialInsightDao _financialInsightDao;

  private volatile FinancialGoalDao _financialGoalDao;

  private volatile SpendingPatternDao _spendingPatternDao;

  private volatile SpendingForecastDao _spendingForecastDao;

  private volatile InvestmentDao _investmentDao;

  private volatile ExchangeRateDao _exchangeRateDao;

  private volatile AutomationRuleDao _automationRuleDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(21) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `products` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `productCategoryId` INTEGER, `hasDeposit` INTEGER NOT NULL, `depositAmount` REAL, `barcode` TEXT, `productName` TEXT, `description` TEXT, `amount` REAL, `unit` TEXT, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL, FOREIGN KEY(`productCategoryId`) REFERENCES `product_categories`(`id`) ON UPDATE NO ACTION ON DELETE SET NULL )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_products_productCategoryId` ON `products` (`productCategoryId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `product_categories` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `color` TEXT, `iconName` TEXT, `isDefault` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `categories` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `color` TEXT NOT NULL, `iconResName` TEXT NOT NULL, `type` TEXT NOT NULL, `parentCategoryId` INTEGER, `isSubcategory` INTEGER NOT NULL, `associatedKeywords` TEXT NOT NULL, `transactionCount` INTEGER NOT NULL, `isActive` INTEGER NOT NULL, `sortOrder` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `stores` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `address` TEXT, `city` TEXT, `chain` TEXT, `latitude` REAL, `longitude` REAL, `phoneNumber` TEXT, `website` TEXT, `openingHours` TEXT, `isActive` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `store_chains` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `logoUrl` TEXT, `website` TEXT, `description` TEXT, `isDefault` INTEGER NOT NULL, `isActive` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `product_stores` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `productId` INTEGER NOT NULL, `storeId` INTEGER NOT NULL, `price` REAL NOT NULL, `hasCampaignPrice` INTEGER NOT NULL, `campaignQuantity` INTEGER, `campaignPrice` REAL, `lastSeen` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL, FOREIGN KEY(`productId`) REFERENCES `products`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`storeId`) REFERENCES `stores`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_product_stores_productId` ON `product_stores` (`productId`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_product_stores_storeId` ON `product_stores` (`storeId`)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_product_stores_productId_storeId` ON `product_stores` (`productId`, `storeId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `incomes` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT NOT NULL, `amount` REAL NOT NULL, `description` TEXT, `category` TEXT, `date` INTEGER NOT NULL, `isRecurring` INTEGER NOT NULL, `recurringType` TEXT, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `expenses` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT NOT NULL, `amount` REAL NOT NULL, `description` TEXT, `category` TEXT, `store` TEXT, `date` INTEGER NOT NULL, `isRecurring` INTEGER NOT NULL, `recurringType` TEXT, `paymentMethod` TEXT, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `loans` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT NOT NULL, `amount` REAL NOT NULL, `description` TEXT, `personName` TEXT NOT NULL, `type` TEXT NOT NULL, `interestRate` REAL NOT NULL, `dueDate` INTEGER, `isPaidBack` INTEGER NOT NULL, `paidBackDate` INTEGER, `paidBackAmount` REAL NOT NULL, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `budgets` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `categoryName` TEXT NOT NULL, `budgetAmount` REAL NOT NULL, `period` TEXT NOT NULL, `startDate` INTEGER NOT NULL, `endDate` INTEGER NOT NULL, `isActive` INTEGER NOT NULL, `alertThreshold` REAL NOT NULL, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `receipts` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `expenseId` INTEGER NOT NULL, `receiptNumber` TEXT, `totalAmount` REAL NOT NULL, `tax` REAL NOT NULL, `discount` REAL NOT NULL, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL, FOREIGN KEY(`expenseId`) REFERENCES `expenses`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE TABLE IF NOT EXISTS `receipt_items` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `receiptId` INTEGER NOT NULL, `productId` INTEGER, `productName` TEXT NOT NULL, `quantity` REAL NOT NULL, `unitPrice` REAL NOT NULL, `totalPrice` REAL NOT NULL, `unit` TEXT NOT NULL, `productSize` TEXT, `discount` REAL NOT NULL, `hasDeposit` INTEGER NOT NULL, `depositAmount` REAL NOT NULL, `storeName` TEXT, `storeChain` TEXT, `storeCity` TEXT, `createdAt` INTEGER NOT NULL, FOREIGN KEY(`receiptId`) REFERENCES `receipts`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`productId`) REFERENCES `products`(`id`) ON UPDATE NO ACTION ON DELETE SET NULL )");
        db.execSQL("CREATE TABLE IF NOT EXISTS `shopping_lists` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `description` TEXT, `isCompleted` INTEGER NOT NULL, `totalEstimatedCost` REAL NOT NULL, `targetStoreId` INTEGER, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL, `completedAt` INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `shopping_list_items` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `shoppingListId` INTEGER NOT NULL, `productId` INTEGER, `customItemName` TEXT, `quantity` INTEGER NOT NULL, `unit` TEXT, `estimatedPrice` REAL, `actualPrice` REAL, `notes` TEXT, `isCompleted` INTEGER NOT NULL, `priority` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL, `completedAt` INTEGER, FOREIGN KEY(`shoppingListId`) REFERENCES `shopping_lists`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`productId`) REFERENCES `products`(`id`) ON UPDATE NO ACTION ON DELETE SET NULL )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_shopping_list_items_shoppingListId` ON `shopping_list_items` (`shoppingListId`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_shopping_list_items_productId` ON `shopping_list_items` (`productId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `price_history` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `productId` INTEGER NOT NULL, `storeId` INTEGER NOT NULL, `price` REAL NOT NULL, `campaignPrice` REAL, `campaignQuantity` INTEGER, `hasCampaignPrice` INTEGER NOT NULL, `recordedAt` INTEGER NOT NULL, `source` TEXT NOT NULL, `notes` TEXT, FOREIGN KEY(`productId`) REFERENCES `products`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`storeId`) REFERENCES `stores`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_price_history_productId` ON `price_history` (`productId`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_price_history_storeId` ON `price_history` (`storeId`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_price_history_recordedAt` ON `price_history` (`recordedAt`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_price_history_productId_storeId_recordedAt` ON `price_history` (`productId`, `storeId`, `recordedAt`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `reminder_settings` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `reminderType` TEXT NOT NULL, `isEnabled` INTEGER NOT NULL, `alertTime` TEXT NOT NULL, `title` TEXT NOT NULL, `message` TEXT NOT NULL, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `app_usage` (`id` INTEGER NOT NULL, `lastOpenedAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `transaction_notifications` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `transactionType` TEXT NOT NULL, `transactionId` INTEGER NOT NULL, `transactionTitle` TEXT NOT NULL, `isEnabled` INTEGER NOT NULL, `reminderDaysBefore` INTEGER NOT NULL, `customMessage` TEXT, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `financial_insights` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `insightType` TEXT NOT NULL, `title` TEXT NOT NULL, `description` TEXT NOT NULL, `severity` TEXT NOT NULL, `category` TEXT, `amount` REAL, `percentage` REAL, `confidenceScore` REAL NOT NULL, `actionable` INTEGER NOT NULL, `suggestedAction` TEXT, `isRead` INTEGER NOT NULL, `isActedUpon` INTEGER NOT NULL, `validUntil` INTEGER, `data` TEXT, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `financial_goals` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `description` TEXT, `goalType` TEXT NOT NULL, `targetAmount` REAL NOT NULL, `currentAmount` REAL NOT NULL, `targetDate` INTEGER, `monthlyTarget` REAL, `priority` TEXT NOT NULL, `category` TEXT, `isActive` INTEGER NOT NULL, `isCompleted` INTEGER NOT NULL, `completedAt` INTEGER, `autoTransferEnabled` INTEGER NOT NULL, `autoTransferAmount` REAL, `linkedAccountId` TEXT, `reminderEnabled` INTEGER NOT NULL, `reminderFrequency` TEXT NOT NULL, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `spending_patterns` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `category` TEXT NOT NULL, `timeframe` TEXT NOT NULL, `averageAmount` REAL NOT NULL, `totalAmount` REAL NOT NULL, `transactionCount` INTEGER NOT NULL, `trendDirection` TEXT NOT NULL, `trendPercentage` REAL NOT NULL, `confidenceLevel` REAL NOT NULL, `seasonality` TEXT NOT NULL, `anomalyScore` REAL NOT NULL, `predictedNextAmount` REAL, `volatility` REAL NOT NULL, `frequency` TEXT NOT NULL, `analysisDate` INTEGER NOT NULL, `dataPoints` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_spending_patterns_category_timeframe` ON `spending_patterns` (`category`, `timeframe`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_spending_patterns_analysisDate` ON `spending_patterns` (`analysisDate`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `spending_forecasts` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `category` TEXT, `forecastDate` INTEGER NOT NULL, `predictedAmount` REAL NOT NULL, `confidenceInterval` REAL NOT NULL, `confidenceLevel` REAL NOT NULL, `forecastMethod` TEXT NOT NULL, `basedOnDays` INTEGER NOT NULL, `seasonalityFactor` REAL NOT NULL, `trendFactor` REAL NOT NULL, `actualAmount` REAL, `accuracy` REAL, `createdAt` INTEGER NOT NULL, `validUntil` INTEGER NOT NULL)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_spending_forecasts_category_forecastDate` ON `spending_forecasts` (`category`, `forecastDate`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_spending_forecasts_forecastDate` ON `spending_forecasts` (`forecastDate`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `investments` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `symbol` TEXT, `type` TEXT NOT NULL, `initialValue` REAL NOT NULL, `currentValue` REAL NOT NULL, `quantity` REAL NOT NULL, `averageCost` REAL NOT NULL, `purchaseDate` INTEGER NOT NULL, `currency` TEXT NOT NULL, `broker` TEXT, `category` TEXT NOT NULL, `notes` TEXT, `isActive` INTEGER NOT NULL, `lastUpdated` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `investment_transactions` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `investmentId` INTEGER NOT NULL, `type` TEXT NOT NULL, `quantity` REAL NOT NULL, `pricePerUnit` REAL NOT NULL, `totalAmount` REAL NOT NULL, `fees` REAL NOT NULL, `transactionDate` INTEGER NOT NULL, `notes` TEXT, `createdAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `investment_price_history` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `investmentId` INTEGER NOT NULL, `price` REAL NOT NULL, `currency` TEXT NOT NULL, `recordedAt` INTEGER NOT NULL, `source` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `investment_dividends` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `investmentId` INTEGER NOT NULL, `amountPerShare` REAL NOT NULL, `totalAmount` REAL NOT NULL, `currency` TEXT NOT NULL, `paymentDate` INTEGER NOT NULL, `exDividendDate` INTEGER, `taxWithheld` REAL NOT NULL, `notes` TEXT, `createdAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `investment_portfolios` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `description` TEXT, `targetAllocation` TEXT, `riskProfile` TEXT NOT NULL, `isActive` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `portfolio_investments` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `portfolioId` INTEGER NOT NULL, `investmentId` INTEGER NOT NULL, `targetPercentage` REAL, `addedAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `exchange_rates` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `fromCurrency` TEXT NOT NULL, `toCurrency` TEXT NOT NULL, `rate` REAL NOT NULL, `lastUpdated` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `currency_alerts` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `fromCurrency` TEXT NOT NULL, `toCurrency` TEXT NOT NULL, `targetRate` REAL NOT NULL, `alertType` TEXT NOT NULL, `isActive` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, `triggeredAt` INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `multi_currency_transactions` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `amount` REAL NOT NULL, `currency` TEXT NOT NULL, `convertedAmount` REAL NOT NULL, `baseCurrency` TEXT NOT NULL, `exchangeRate` REAL NOT NULL, `category` TEXT NOT NULL, `description` TEXT NOT NULL, `transactionDate` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `automation_rules` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `description` TEXT NOT NULL, `conditions` TEXT NOT NULL, `actions` TEXT NOT NULL, `isActive` INTEGER NOT NULL, `priority` INTEGER NOT NULL, `usageCount` INTEGER NOT NULL, `createdBy` TEXT NOT NULL, `confidence` REAL NOT NULL, `createdAt` INTEGER NOT NULL, `lastModified` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `auto_transfer_rules` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `description` TEXT NOT NULL, `sourceAccountId` TEXT NOT NULL, `targetAccountId` TEXT NOT NULL, `transferType` TEXT NOT NULL, `amount` REAL NOT NULL, `frequency` TEXT NOT NULL, `conditions` TEXT NOT NULL, `isActive` INTEGER NOT NULL, `lastExecuted` INTEGER, `totalTransferred` REAL NOT NULL, `createdAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `smart_insights` (`id` TEXT NOT NULL, `type` TEXT NOT NULL, `title` TEXT NOT NULL, `description` TEXT NOT NULL, `actionable` INTEGER NOT NULL, `actions` TEXT NOT NULL, `priority` TEXT NOT NULL, `confidence` REAL NOT NULL, `category` TEXT, `impact` TEXT NOT NULL, `isRead` INTEGER NOT NULL, `isDismissed` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, `expiresAt` INTEGER, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'efe1c484743b689c2966294e37e0ff77')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `products`");
        db.execSQL("DROP TABLE IF EXISTS `product_categories`");
        db.execSQL("DROP TABLE IF EXISTS `categories`");
        db.execSQL("DROP TABLE IF EXISTS `stores`");
        db.execSQL("DROP TABLE IF EXISTS `store_chains`");
        db.execSQL("DROP TABLE IF EXISTS `product_stores`");
        db.execSQL("DROP TABLE IF EXISTS `incomes`");
        db.execSQL("DROP TABLE IF EXISTS `expenses`");
        db.execSQL("DROP TABLE IF EXISTS `loans`");
        db.execSQL("DROP TABLE IF EXISTS `budgets`");
        db.execSQL("DROP TABLE IF EXISTS `receipts`");
        db.execSQL("DROP TABLE IF EXISTS `receipt_items`");
        db.execSQL("DROP TABLE IF EXISTS `shopping_lists`");
        db.execSQL("DROP TABLE IF EXISTS `shopping_list_items`");
        db.execSQL("DROP TABLE IF EXISTS `price_history`");
        db.execSQL("DROP TABLE IF EXISTS `reminder_settings`");
        db.execSQL("DROP TABLE IF EXISTS `app_usage`");
        db.execSQL("DROP TABLE IF EXISTS `transaction_notifications`");
        db.execSQL("DROP TABLE IF EXISTS `financial_insights`");
        db.execSQL("DROP TABLE IF EXISTS `financial_goals`");
        db.execSQL("DROP TABLE IF EXISTS `spending_patterns`");
        db.execSQL("DROP TABLE IF EXISTS `spending_forecasts`");
        db.execSQL("DROP TABLE IF EXISTS `investments`");
        db.execSQL("DROP TABLE IF EXISTS `investment_transactions`");
        db.execSQL("DROP TABLE IF EXISTS `investment_price_history`");
        db.execSQL("DROP TABLE IF EXISTS `investment_dividends`");
        db.execSQL("DROP TABLE IF EXISTS `investment_portfolios`");
        db.execSQL("DROP TABLE IF EXISTS `portfolio_investments`");
        db.execSQL("DROP TABLE IF EXISTS `exchange_rates`");
        db.execSQL("DROP TABLE IF EXISTS `currency_alerts`");
        db.execSQL("DROP TABLE IF EXISTS `multi_currency_transactions`");
        db.execSQL("DROP TABLE IF EXISTS `automation_rules`");
        db.execSQL("DROP TABLE IF EXISTS `auto_transfer_rules`");
        db.execSQL("DROP TABLE IF EXISTS `smart_insights`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsProducts = new HashMap<String, TableInfo.Column>(12);
        _columnsProducts.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProducts.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProducts.put("productCategoryId", new TableInfo.Column("productCategoryId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProducts.put("hasDeposit", new TableInfo.Column("hasDeposit", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProducts.put("depositAmount", new TableInfo.Column("depositAmount", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProducts.put("barcode", new TableInfo.Column("barcode", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProducts.put("productName", new TableInfo.Column("productName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProducts.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProducts.put("amount", new TableInfo.Column("amount", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProducts.put("unit", new TableInfo.Column("unit", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProducts.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProducts.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysProducts = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysProducts.add(new TableInfo.ForeignKey("product_categories", "SET NULL", "NO ACTION", Arrays.asList("productCategoryId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesProducts = new HashSet<TableInfo.Index>(1);
        _indicesProducts.add(new TableInfo.Index("index_products_productCategoryId", false, Arrays.asList("productCategoryId"), Arrays.asList("ASC")));
        final TableInfo _infoProducts = new TableInfo("products", _columnsProducts, _foreignKeysProducts, _indicesProducts);
        final TableInfo _existingProducts = TableInfo.read(db, "products");
        if (!_infoProducts.equals(_existingProducts)) {
          return new RoomOpenHelper.ValidationResult(false, "products(com.example.budgetapp.database.entities.Product).\n"
                  + " Expected:\n" + _infoProducts + "\n"
                  + " Found:\n" + _existingProducts);
        }
        final HashMap<String, TableInfo.Column> _columnsProductCategories = new HashMap<String, TableInfo.Column>(6);
        _columnsProductCategories.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductCategories.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductCategories.put("color", new TableInfo.Column("color", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductCategories.put("iconName", new TableInfo.Column("iconName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductCategories.put("isDefault", new TableInfo.Column("isDefault", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductCategories.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysProductCategories = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesProductCategories = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoProductCategories = new TableInfo("product_categories", _columnsProductCategories, _foreignKeysProductCategories, _indicesProductCategories);
        final TableInfo _existingProductCategories = TableInfo.read(db, "product_categories");
        if (!_infoProductCategories.equals(_existingProductCategories)) {
          return new RoomOpenHelper.ValidationResult(false, "product_categories(com.example.budgetapp.database.entities.ProductCategory).\n"
                  + " Expected:\n" + _infoProductCategories + "\n"
                  + " Found:\n" + _existingProductCategories);
        }
        final HashMap<String, TableInfo.Column> _columnsCategories = new HashMap<String, TableInfo.Column>(11);
        _columnsCategories.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCategories.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCategories.put("color", new TableInfo.Column("color", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCategories.put("iconResName", new TableInfo.Column("iconResName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCategories.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCategories.put("parentCategoryId", new TableInfo.Column("parentCategoryId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCategories.put("isSubcategory", new TableInfo.Column("isSubcategory", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCategories.put("associatedKeywords", new TableInfo.Column("associatedKeywords", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCategories.put("transactionCount", new TableInfo.Column("transactionCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCategories.put("isActive", new TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCategories.put("sortOrder", new TableInfo.Column("sortOrder", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCategories = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCategories = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCategories = new TableInfo("categories", _columnsCategories, _foreignKeysCategories, _indicesCategories);
        final TableInfo _existingCategories = TableInfo.read(db, "categories");
        if (!_infoCategories.equals(_existingCategories)) {
          return new RoomOpenHelper.ValidationResult(false, "categories(com.example.budgetapp.database.entities.Category).\n"
                  + " Expected:\n" + _infoCategories + "\n"
                  + " Found:\n" + _existingCategories);
        }
        final HashMap<String, TableInfo.Column> _columnsStores = new HashMap<String, TableInfo.Column>(13);
        _columnsStores.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStores.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStores.put("address", new TableInfo.Column("address", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStores.put("city", new TableInfo.Column("city", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStores.put("chain", new TableInfo.Column("chain", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStores.put("latitude", new TableInfo.Column("latitude", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStores.put("longitude", new TableInfo.Column("longitude", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStores.put("phoneNumber", new TableInfo.Column("phoneNumber", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStores.put("website", new TableInfo.Column("website", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStores.put("openingHours", new TableInfo.Column("openingHours", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStores.put("isActive", new TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStores.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStores.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysStores = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesStores = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoStores = new TableInfo("stores", _columnsStores, _foreignKeysStores, _indicesStores);
        final TableInfo _existingStores = TableInfo.read(db, "stores");
        if (!_infoStores.equals(_existingStores)) {
          return new RoomOpenHelper.ValidationResult(false, "stores(com.example.budgetapp.database.entities.Store).\n"
                  + " Expected:\n" + _infoStores + "\n"
                  + " Found:\n" + _existingStores);
        }
        final HashMap<String, TableInfo.Column> _columnsStoreChains = new HashMap<String, TableInfo.Column>(9);
        _columnsStoreChains.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStoreChains.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStoreChains.put("logoUrl", new TableInfo.Column("logoUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStoreChains.put("website", new TableInfo.Column("website", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStoreChains.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStoreChains.put("isDefault", new TableInfo.Column("isDefault", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStoreChains.put("isActive", new TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStoreChains.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStoreChains.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysStoreChains = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesStoreChains = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoStoreChains = new TableInfo("store_chains", _columnsStoreChains, _foreignKeysStoreChains, _indicesStoreChains);
        final TableInfo _existingStoreChains = TableInfo.read(db, "store_chains");
        if (!_infoStoreChains.equals(_existingStoreChains)) {
          return new RoomOpenHelper.ValidationResult(false, "store_chains(com.example.budgetapp.database.entities.StoreChain).\n"
                  + " Expected:\n" + _infoStoreChains + "\n"
                  + " Found:\n" + _existingStoreChains);
        }
        final HashMap<String, TableInfo.Column> _columnsProductStores = new HashMap<String, TableInfo.Column>(10);
        _columnsProductStores.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductStores.put("productId", new TableInfo.Column("productId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductStores.put("storeId", new TableInfo.Column("storeId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductStores.put("price", new TableInfo.Column("price", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductStores.put("hasCampaignPrice", new TableInfo.Column("hasCampaignPrice", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductStores.put("campaignQuantity", new TableInfo.Column("campaignQuantity", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductStores.put("campaignPrice", new TableInfo.Column("campaignPrice", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductStores.put("lastSeen", new TableInfo.Column("lastSeen", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductStores.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductStores.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysProductStores = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysProductStores.add(new TableInfo.ForeignKey("products", "CASCADE", "NO ACTION", Arrays.asList("productId"), Arrays.asList("id")));
        _foreignKeysProductStores.add(new TableInfo.ForeignKey("stores", "CASCADE", "NO ACTION", Arrays.asList("storeId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesProductStores = new HashSet<TableInfo.Index>(3);
        _indicesProductStores.add(new TableInfo.Index("index_product_stores_productId", false, Arrays.asList("productId"), Arrays.asList("ASC")));
        _indicesProductStores.add(new TableInfo.Index("index_product_stores_storeId", false, Arrays.asList("storeId"), Arrays.asList("ASC")));
        _indicesProductStores.add(new TableInfo.Index("index_product_stores_productId_storeId", true, Arrays.asList("productId", "storeId"), Arrays.asList("ASC", "ASC")));
        final TableInfo _infoProductStores = new TableInfo("product_stores", _columnsProductStores, _foreignKeysProductStores, _indicesProductStores);
        final TableInfo _existingProductStores = TableInfo.read(db, "product_stores");
        if (!_infoProductStores.equals(_existingProductStores)) {
          return new RoomOpenHelper.ValidationResult(false, "product_stores(com.example.budgetapp.database.entities.ProductStore).\n"
                  + " Expected:\n" + _infoProductStores + "\n"
                  + " Found:\n" + _existingProductStores);
        }
        final HashMap<String, TableInfo.Column> _columnsIncomes = new HashMap<String, TableInfo.Column>(10);
        _columnsIncomes.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIncomes.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIncomes.put("amount", new TableInfo.Column("amount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIncomes.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIncomes.put("category", new TableInfo.Column("category", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIncomes.put("date", new TableInfo.Column("date", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIncomes.put("isRecurring", new TableInfo.Column("isRecurring", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIncomes.put("recurringType", new TableInfo.Column("recurringType", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIncomes.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsIncomes.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysIncomes = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesIncomes = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoIncomes = new TableInfo("incomes", _columnsIncomes, _foreignKeysIncomes, _indicesIncomes);
        final TableInfo _existingIncomes = TableInfo.read(db, "incomes");
        if (!_infoIncomes.equals(_existingIncomes)) {
          return new RoomOpenHelper.ValidationResult(false, "incomes(com.example.budgetapp.database.entities.Income).\n"
                  + " Expected:\n" + _infoIncomes + "\n"
                  + " Found:\n" + _existingIncomes);
        }
        final HashMap<String, TableInfo.Column> _columnsExpenses = new HashMap<String, TableInfo.Column>(12);
        _columnsExpenses.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("amount", new TableInfo.Column("amount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("category", new TableInfo.Column("category", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("store", new TableInfo.Column("store", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("date", new TableInfo.Column("date", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("isRecurring", new TableInfo.Column("isRecurring", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("recurringType", new TableInfo.Column("recurringType", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("paymentMethod", new TableInfo.Column("paymentMethod", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExpenses.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysExpenses = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesExpenses = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoExpenses = new TableInfo("expenses", _columnsExpenses, _foreignKeysExpenses, _indicesExpenses);
        final TableInfo _existingExpenses = TableInfo.read(db, "expenses");
        if (!_infoExpenses.equals(_existingExpenses)) {
          return new RoomOpenHelper.ValidationResult(false, "expenses(com.example.budgetapp.database.entities.Expense).\n"
                  + " Expected:\n" + _infoExpenses + "\n"
                  + " Found:\n" + _existingExpenses);
        }
        final HashMap<String, TableInfo.Column> _columnsLoans = new HashMap<String, TableInfo.Column>(13);
        _columnsLoans.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoans.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoans.put("amount", new TableInfo.Column("amount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoans.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoans.put("personName", new TableInfo.Column("personName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoans.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoans.put("interestRate", new TableInfo.Column("interestRate", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoans.put("dueDate", new TableInfo.Column("dueDate", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoans.put("isPaidBack", new TableInfo.Column("isPaidBack", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoans.put("paidBackDate", new TableInfo.Column("paidBackDate", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoans.put("paidBackAmount", new TableInfo.Column("paidBackAmount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoans.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsLoans.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysLoans = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesLoans = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoLoans = new TableInfo("loans", _columnsLoans, _foreignKeysLoans, _indicesLoans);
        final TableInfo _existingLoans = TableInfo.read(db, "loans");
        if (!_infoLoans.equals(_existingLoans)) {
          return new RoomOpenHelper.ValidationResult(false, "loans(com.example.budgetapp.database.entities.Loan).\n"
                  + " Expected:\n" + _infoLoans + "\n"
                  + " Found:\n" + _existingLoans);
        }
        final HashMap<String, TableInfo.Column> _columnsBudgets = new HashMap<String, TableInfo.Column>(11);
        _columnsBudgets.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBudgets.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBudgets.put("categoryName", new TableInfo.Column("categoryName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBudgets.put("budgetAmount", new TableInfo.Column("budgetAmount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBudgets.put("period", new TableInfo.Column("period", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBudgets.put("startDate", new TableInfo.Column("startDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBudgets.put("endDate", new TableInfo.Column("endDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBudgets.put("isActive", new TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBudgets.put("alertThreshold", new TableInfo.Column("alertThreshold", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBudgets.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsBudgets.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysBudgets = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesBudgets = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoBudgets = new TableInfo("budgets", _columnsBudgets, _foreignKeysBudgets, _indicesBudgets);
        final TableInfo _existingBudgets = TableInfo.read(db, "budgets");
        if (!_infoBudgets.equals(_existingBudgets)) {
          return new RoomOpenHelper.ValidationResult(false, "budgets(com.example.budgetapp.database.entities.Budget).\n"
                  + " Expected:\n" + _infoBudgets + "\n"
                  + " Found:\n" + _existingBudgets);
        }
        final HashMap<String, TableInfo.Column> _columnsReceipts = new HashMap<String, TableInfo.Column>(8);
        _columnsReceipts.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReceipts.put("expenseId", new TableInfo.Column("expenseId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReceipts.put("receiptNumber", new TableInfo.Column("receiptNumber", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReceipts.put("totalAmount", new TableInfo.Column("totalAmount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReceipts.put("tax", new TableInfo.Column("tax", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReceipts.put("discount", new TableInfo.Column("discount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReceipts.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReceipts.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysReceipts = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysReceipts.add(new TableInfo.ForeignKey("expenses", "CASCADE", "NO ACTION", Arrays.asList("expenseId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesReceipts = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoReceipts = new TableInfo("receipts", _columnsReceipts, _foreignKeysReceipts, _indicesReceipts);
        final TableInfo _existingReceipts = TableInfo.read(db, "receipts");
        if (!_infoReceipts.equals(_existingReceipts)) {
          return new RoomOpenHelper.ValidationResult(false, "receipts(com.example.budgetapp.database.entities.Receipt).\n"
                  + " Expected:\n" + _infoReceipts + "\n"
                  + " Found:\n" + _existingReceipts);
        }
        final HashMap<String, TableInfo.Column> _columnsReceiptItems = new HashMap<String, TableInfo.Column>(16);
        _columnsReceiptItems.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReceiptItems.put("receiptId", new TableInfo.Column("receiptId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReceiptItems.put("productId", new TableInfo.Column("productId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReceiptItems.put("productName", new TableInfo.Column("productName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReceiptItems.put("quantity", new TableInfo.Column("quantity", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReceiptItems.put("unitPrice", new TableInfo.Column("unitPrice", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReceiptItems.put("totalPrice", new TableInfo.Column("totalPrice", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReceiptItems.put("unit", new TableInfo.Column("unit", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReceiptItems.put("productSize", new TableInfo.Column("productSize", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReceiptItems.put("discount", new TableInfo.Column("discount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReceiptItems.put("hasDeposit", new TableInfo.Column("hasDeposit", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReceiptItems.put("depositAmount", new TableInfo.Column("depositAmount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReceiptItems.put("storeName", new TableInfo.Column("storeName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReceiptItems.put("storeChain", new TableInfo.Column("storeChain", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReceiptItems.put("storeCity", new TableInfo.Column("storeCity", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReceiptItems.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysReceiptItems = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysReceiptItems.add(new TableInfo.ForeignKey("receipts", "CASCADE", "NO ACTION", Arrays.asList("receiptId"), Arrays.asList("id")));
        _foreignKeysReceiptItems.add(new TableInfo.ForeignKey("products", "SET NULL", "NO ACTION", Arrays.asList("productId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesReceiptItems = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoReceiptItems = new TableInfo("receipt_items", _columnsReceiptItems, _foreignKeysReceiptItems, _indicesReceiptItems);
        final TableInfo _existingReceiptItems = TableInfo.read(db, "receipt_items");
        if (!_infoReceiptItems.equals(_existingReceiptItems)) {
          return new RoomOpenHelper.ValidationResult(false, "receipt_items(com.example.budgetapp.database.entities.ReceiptItem).\n"
                  + " Expected:\n" + _infoReceiptItems + "\n"
                  + " Found:\n" + _existingReceiptItems);
        }
        final HashMap<String, TableInfo.Column> _columnsShoppingLists = new HashMap<String, TableInfo.Column>(9);
        _columnsShoppingLists.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShoppingLists.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShoppingLists.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShoppingLists.put("isCompleted", new TableInfo.Column("isCompleted", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShoppingLists.put("totalEstimatedCost", new TableInfo.Column("totalEstimatedCost", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShoppingLists.put("targetStoreId", new TableInfo.Column("targetStoreId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShoppingLists.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShoppingLists.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShoppingLists.put("completedAt", new TableInfo.Column("completedAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysShoppingLists = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesShoppingLists = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoShoppingLists = new TableInfo("shopping_lists", _columnsShoppingLists, _foreignKeysShoppingLists, _indicesShoppingLists);
        final TableInfo _existingShoppingLists = TableInfo.read(db, "shopping_lists");
        if (!_infoShoppingLists.equals(_existingShoppingLists)) {
          return new RoomOpenHelper.ValidationResult(false, "shopping_lists(com.example.budgetapp.database.entities.ShoppingList).\n"
                  + " Expected:\n" + _infoShoppingLists + "\n"
                  + " Found:\n" + _existingShoppingLists);
        }
        final HashMap<String, TableInfo.Column> _columnsShoppingListItems = new HashMap<String, TableInfo.Column>(14);
        _columnsShoppingListItems.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShoppingListItems.put("shoppingListId", new TableInfo.Column("shoppingListId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShoppingListItems.put("productId", new TableInfo.Column("productId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShoppingListItems.put("customItemName", new TableInfo.Column("customItemName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShoppingListItems.put("quantity", new TableInfo.Column("quantity", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShoppingListItems.put("unit", new TableInfo.Column("unit", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShoppingListItems.put("estimatedPrice", new TableInfo.Column("estimatedPrice", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShoppingListItems.put("actualPrice", new TableInfo.Column("actualPrice", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShoppingListItems.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShoppingListItems.put("isCompleted", new TableInfo.Column("isCompleted", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShoppingListItems.put("priority", new TableInfo.Column("priority", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShoppingListItems.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShoppingListItems.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsShoppingListItems.put("completedAt", new TableInfo.Column("completedAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysShoppingListItems = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysShoppingListItems.add(new TableInfo.ForeignKey("shopping_lists", "CASCADE", "NO ACTION", Arrays.asList("shoppingListId"), Arrays.asList("id")));
        _foreignKeysShoppingListItems.add(new TableInfo.ForeignKey("products", "SET NULL", "NO ACTION", Arrays.asList("productId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesShoppingListItems = new HashSet<TableInfo.Index>(2);
        _indicesShoppingListItems.add(new TableInfo.Index("index_shopping_list_items_shoppingListId", false, Arrays.asList("shoppingListId"), Arrays.asList("ASC")));
        _indicesShoppingListItems.add(new TableInfo.Index("index_shopping_list_items_productId", false, Arrays.asList("productId"), Arrays.asList("ASC")));
        final TableInfo _infoShoppingListItems = new TableInfo("shopping_list_items", _columnsShoppingListItems, _foreignKeysShoppingListItems, _indicesShoppingListItems);
        final TableInfo _existingShoppingListItems = TableInfo.read(db, "shopping_list_items");
        if (!_infoShoppingListItems.equals(_existingShoppingListItems)) {
          return new RoomOpenHelper.ValidationResult(false, "shopping_list_items(com.example.budgetapp.database.entities.ShoppingListItem).\n"
                  + " Expected:\n" + _infoShoppingListItems + "\n"
                  + " Found:\n" + _existingShoppingListItems);
        }
        final HashMap<String, TableInfo.Column> _columnsPriceHistory = new HashMap<String, TableInfo.Column>(10);
        _columnsPriceHistory.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceHistory.put("productId", new TableInfo.Column("productId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceHistory.put("storeId", new TableInfo.Column("storeId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceHistory.put("price", new TableInfo.Column("price", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceHistory.put("campaignPrice", new TableInfo.Column("campaignPrice", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceHistory.put("campaignQuantity", new TableInfo.Column("campaignQuantity", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceHistory.put("hasCampaignPrice", new TableInfo.Column("hasCampaignPrice", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceHistory.put("recordedAt", new TableInfo.Column("recordedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceHistory.put("source", new TableInfo.Column("source", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPriceHistory.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPriceHistory = new HashSet<TableInfo.ForeignKey>(2);
        _foreignKeysPriceHistory.add(new TableInfo.ForeignKey("products", "CASCADE", "NO ACTION", Arrays.asList("productId"), Arrays.asList("id")));
        _foreignKeysPriceHistory.add(new TableInfo.ForeignKey("stores", "CASCADE", "NO ACTION", Arrays.asList("storeId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesPriceHistory = new HashSet<TableInfo.Index>(4);
        _indicesPriceHistory.add(new TableInfo.Index("index_price_history_productId", false, Arrays.asList("productId"), Arrays.asList("ASC")));
        _indicesPriceHistory.add(new TableInfo.Index("index_price_history_storeId", false, Arrays.asList("storeId"), Arrays.asList("ASC")));
        _indicesPriceHistory.add(new TableInfo.Index("index_price_history_recordedAt", false, Arrays.asList("recordedAt"), Arrays.asList("ASC")));
        _indicesPriceHistory.add(new TableInfo.Index("index_price_history_productId_storeId_recordedAt", false, Arrays.asList("productId", "storeId", "recordedAt"), Arrays.asList("ASC", "ASC", "ASC")));
        final TableInfo _infoPriceHistory = new TableInfo("price_history", _columnsPriceHistory, _foreignKeysPriceHistory, _indicesPriceHistory);
        final TableInfo _existingPriceHistory = TableInfo.read(db, "price_history");
        if (!_infoPriceHistory.equals(_existingPriceHistory)) {
          return new RoomOpenHelper.ValidationResult(false, "price_history(com.example.budgetapp.database.entities.PriceHistory).\n"
                  + " Expected:\n" + _infoPriceHistory + "\n"
                  + " Found:\n" + _existingPriceHistory);
        }
        final HashMap<String, TableInfo.Column> _columnsReminderSettings = new HashMap<String, TableInfo.Column>(8);
        _columnsReminderSettings.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminderSettings.put("reminderType", new TableInfo.Column("reminderType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminderSettings.put("isEnabled", new TableInfo.Column("isEnabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminderSettings.put("alertTime", new TableInfo.Column("alertTime", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminderSettings.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminderSettings.put("message", new TableInfo.Column("message", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminderSettings.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsReminderSettings.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysReminderSettings = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesReminderSettings = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoReminderSettings = new TableInfo("reminder_settings", _columnsReminderSettings, _foreignKeysReminderSettings, _indicesReminderSettings);
        final TableInfo _existingReminderSettings = TableInfo.read(db, "reminder_settings");
        if (!_infoReminderSettings.equals(_existingReminderSettings)) {
          return new RoomOpenHelper.ValidationResult(false, "reminder_settings(com.example.budgetapp.database.entities.ReminderSettings).\n"
                  + " Expected:\n" + _infoReminderSettings + "\n"
                  + " Found:\n" + _existingReminderSettings);
        }
        final HashMap<String, TableInfo.Column> _columnsAppUsage = new HashMap<String, TableInfo.Column>(3);
        _columnsAppUsage.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppUsage.put("lastOpenedAt", new TableInfo.Column("lastOpenedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAppUsage.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAppUsage = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAppUsage = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAppUsage = new TableInfo("app_usage", _columnsAppUsage, _foreignKeysAppUsage, _indicesAppUsage);
        final TableInfo _existingAppUsage = TableInfo.read(db, "app_usage");
        if (!_infoAppUsage.equals(_existingAppUsage)) {
          return new RoomOpenHelper.ValidationResult(false, "app_usage(com.example.budgetapp.database.entities.AppUsage).\n"
                  + " Expected:\n" + _infoAppUsage + "\n"
                  + " Found:\n" + _existingAppUsage);
        }
        final HashMap<String, TableInfo.Column> _columnsTransactionNotifications = new HashMap<String, TableInfo.Column>(9);
        _columnsTransactionNotifications.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionNotifications.put("transactionType", new TableInfo.Column("transactionType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionNotifications.put("transactionId", new TableInfo.Column("transactionId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionNotifications.put("transactionTitle", new TableInfo.Column("transactionTitle", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionNotifications.put("isEnabled", new TableInfo.Column("isEnabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionNotifications.put("reminderDaysBefore", new TableInfo.Column("reminderDaysBefore", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionNotifications.put("customMessage", new TableInfo.Column("customMessage", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionNotifications.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionNotifications.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTransactionNotifications = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTransactionNotifications = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTransactionNotifications = new TableInfo("transaction_notifications", _columnsTransactionNotifications, _foreignKeysTransactionNotifications, _indicesTransactionNotifications);
        final TableInfo _existingTransactionNotifications = TableInfo.read(db, "transaction_notifications");
        if (!_infoTransactionNotifications.equals(_existingTransactionNotifications)) {
          return new RoomOpenHelper.ValidationResult(false, "transaction_notifications(com.example.budgetapp.database.entities.TransactionNotification).\n"
                  + " Expected:\n" + _infoTransactionNotifications + "\n"
                  + " Found:\n" + _existingTransactionNotifications);
        }
        final HashMap<String, TableInfo.Column> _columnsFinancialInsights = new HashMap<String, TableInfo.Column>(17);
        _columnsFinancialInsights.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFinancialInsights.put("insightType", new TableInfo.Column("insightType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFinancialInsights.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFinancialInsights.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFinancialInsights.put("severity", new TableInfo.Column("severity", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFinancialInsights.put("category", new TableInfo.Column("category", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFinancialInsights.put("amount", new TableInfo.Column("amount", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFinancialInsights.put("percentage", new TableInfo.Column("percentage", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFinancialInsights.put("confidenceScore", new TableInfo.Column("confidenceScore", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFinancialInsights.put("actionable", new TableInfo.Column("actionable", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFinancialInsights.put("suggestedAction", new TableInfo.Column("suggestedAction", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFinancialInsights.put("isRead", new TableInfo.Column("isRead", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFinancialInsights.put("isActedUpon", new TableInfo.Column("isActedUpon", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFinancialInsights.put("validUntil", new TableInfo.Column("validUntil", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFinancialInsights.put("data", new TableInfo.Column("data", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFinancialInsights.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFinancialInsights.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFinancialInsights = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFinancialInsights = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFinancialInsights = new TableInfo("financial_insights", _columnsFinancialInsights, _foreignKeysFinancialInsights, _indicesFinancialInsights);
        final TableInfo _existingFinancialInsights = TableInfo.read(db, "financial_insights");
        if (!_infoFinancialInsights.equals(_existingFinancialInsights)) {
          return new RoomOpenHelper.ValidationResult(false, "financial_insights(com.example.budgetapp.database.entities.FinancialInsight).\n"
                  + " Expected:\n" + _infoFinancialInsights + "\n"
                  + " Found:\n" + _existingFinancialInsights);
        }
        final HashMap<String, TableInfo.Column> _columnsFinancialGoals = new HashMap<String, TableInfo.Column>(20);
        _columnsFinancialGoals.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFinancialGoals.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFinancialGoals.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFinancialGoals.put("goalType", new TableInfo.Column("goalType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFinancialGoals.put("targetAmount", new TableInfo.Column("targetAmount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFinancialGoals.put("currentAmount", new TableInfo.Column("currentAmount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFinancialGoals.put("targetDate", new TableInfo.Column("targetDate", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFinancialGoals.put("monthlyTarget", new TableInfo.Column("monthlyTarget", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFinancialGoals.put("priority", new TableInfo.Column("priority", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFinancialGoals.put("category", new TableInfo.Column("category", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFinancialGoals.put("isActive", new TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFinancialGoals.put("isCompleted", new TableInfo.Column("isCompleted", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFinancialGoals.put("completedAt", new TableInfo.Column("completedAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFinancialGoals.put("autoTransferEnabled", new TableInfo.Column("autoTransferEnabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFinancialGoals.put("autoTransferAmount", new TableInfo.Column("autoTransferAmount", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFinancialGoals.put("linkedAccountId", new TableInfo.Column("linkedAccountId", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFinancialGoals.put("reminderEnabled", new TableInfo.Column("reminderEnabled", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFinancialGoals.put("reminderFrequency", new TableInfo.Column("reminderFrequency", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFinancialGoals.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsFinancialGoals.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFinancialGoals = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFinancialGoals = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFinancialGoals = new TableInfo("financial_goals", _columnsFinancialGoals, _foreignKeysFinancialGoals, _indicesFinancialGoals);
        final TableInfo _existingFinancialGoals = TableInfo.read(db, "financial_goals");
        if (!_infoFinancialGoals.equals(_existingFinancialGoals)) {
          return new RoomOpenHelper.ValidationResult(false, "financial_goals(com.example.budgetapp.database.entities.FinancialGoal).\n"
                  + " Expected:\n" + _infoFinancialGoals + "\n"
                  + " Found:\n" + _existingFinancialGoals);
        }
        final HashMap<String, TableInfo.Column> _columnsSpendingPatterns = new HashMap<String, TableInfo.Column>(17);
        _columnsSpendingPatterns.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSpendingPatterns.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSpendingPatterns.put("timeframe", new TableInfo.Column("timeframe", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSpendingPatterns.put("averageAmount", new TableInfo.Column("averageAmount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSpendingPatterns.put("totalAmount", new TableInfo.Column("totalAmount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSpendingPatterns.put("transactionCount", new TableInfo.Column("transactionCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSpendingPatterns.put("trendDirection", new TableInfo.Column("trendDirection", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSpendingPatterns.put("trendPercentage", new TableInfo.Column("trendPercentage", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSpendingPatterns.put("confidenceLevel", new TableInfo.Column("confidenceLevel", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSpendingPatterns.put("seasonality", new TableInfo.Column("seasonality", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSpendingPatterns.put("anomalyScore", new TableInfo.Column("anomalyScore", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSpendingPatterns.put("predictedNextAmount", new TableInfo.Column("predictedNextAmount", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSpendingPatterns.put("volatility", new TableInfo.Column("volatility", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSpendingPatterns.put("frequency", new TableInfo.Column("frequency", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSpendingPatterns.put("analysisDate", new TableInfo.Column("analysisDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSpendingPatterns.put("dataPoints", new TableInfo.Column("dataPoints", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSpendingPatterns.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSpendingPatterns = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSpendingPatterns = new HashSet<TableInfo.Index>(2);
        _indicesSpendingPatterns.add(new TableInfo.Index("index_spending_patterns_category_timeframe", false, Arrays.asList("category", "timeframe"), Arrays.asList("ASC", "ASC")));
        _indicesSpendingPatterns.add(new TableInfo.Index("index_spending_patterns_analysisDate", false, Arrays.asList("analysisDate"), Arrays.asList("ASC")));
        final TableInfo _infoSpendingPatterns = new TableInfo("spending_patterns", _columnsSpendingPatterns, _foreignKeysSpendingPatterns, _indicesSpendingPatterns);
        final TableInfo _existingSpendingPatterns = TableInfo.read(db, "spending_patterns");
        if (!_infoSpendingPatterns.equals(_existingSpendingPatterns)) {
          return new RoomOpenHelper.ValidationResult(false, "spending_patterns(com.example.budgetapp.database.entities.SpendingPattern).\n"
                  + " Expected:\n" + _infoSpendingPatterns + "\n"
                  + " Found:\n" + _existingSpendingPatterns);
        }
        final HashMap<String, TableInfo.Column> _columnsSpendingForecasts = new HashMap<String, TableInfo.Column>(14);
        _columnsSpendingForecasts.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSpendingForecasts.put("category", new TableInfo.Column("category", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSpendingForecasts.put("forecastDate", new TableInfo.Column("forecastDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSpendingForecasts.put("predictedAmount", new TableInfo.Column("predictedAmount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSpendingForecasts.put("confidenceInterval", new TableInfo.Column("confidenceInterval", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSpendingForecasts.put("confidenceLevel", new TableInfo.Column("confidenceLevel", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSpendingForecasts.put("forecastMethod", new TableInfo.Column("forecastMethod", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSpendingForecasts.put("basedOnDays", new TableInfo.Column("basedOnDays", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSpendingForecasts.put("seasonalityFactor", new TableInfo.Column("seasonalityFactor", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSpendingForecasts.put("trendFactor", new TableInfo.Column("trendFactor", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSpendingForecasts.put("actualAmount", new TableInfo.Column("actualAmount", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSpendingForecasts.put("accuracy", new TableInfo.Column("accuracy", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSpendingForecasts.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSpendingForecasts.put("validUntil", new TableInfo.Column("validUntil", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSpendingForecasts = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSpendingForecasts = new HashSet<TableInfo.Index>(2);
        _indicesSpendingForecasts.add(new TableInfo.Index("index_spending_forecasts_category_forecastDate", false, Arrays.asList("category", "forecastDate"), Arrays.asList("ASC", "ASC")));
        _indicesSpendingForecasts.add(new TableInfo.Index("index_spending_forecasts_forecastDate", false, Arrays.asList("forecastDate"), Arrays.asList("ASC")));
        final TableInfo _infoSpendingForecasts = new TableInfo("spending_forecasts", _columnsSpendingForecasts, _foreignKeysSpendingForecasts, _indicesSpendingForecasts);
        final TableInfo _existingSpendingForecasts = TableInfo.read(db, "spending_forecasts");
        if (!_infoSpendingForecasts.equals(_existingSpendingForecasts)) {
          return new RoomOpenHelper.ValidationResult(false, "spending_forecasts(com.example.budgetapp.database.entities.SpendingForecast).\n"
                  + " Expected:\n" + _infoSpendingForecasts + "\n"
                  + " Found:\n" + _existingSpendingForecasts);
        }
        final HashMap<String, TableInfo.Column> _columnsInvestments = new HashMap<String, TableInfo.Column>(16);
        _columnsInvestments.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestments.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestments.put("symbol", new TableInfo.Column("symbol", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestments.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestments.put("initialValue", new TableInfo.Column("initialValue", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestments.put("currentValue", new TableInfo.Column("currentValue", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestments.put("quantity", new TableInfo.Column("quantity", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestments.put("averageCost", new TableInfo.Column("averageCost", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestments.put("purchaseDate", new TableInfo.Column("purchaseDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestments.put("currency", new TableInfo.Column("currency", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestments.put("broker", new TableInfo.Column("broker", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestments.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestments.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestments.put("isActive", new TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestments.put("lastUpdated", new TableInfo.Column("lastUpdated", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestments.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysInvestments = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesInvestments = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoInvestments = new TableInfo("investments", _columnsInvestments, _foreignKeysInvestments, _indicesInvestments);
        final TableInfo _existingInvestments = TableInfo.read(db, "investments");
        if (!_infoInvestments.equals(_existingInvestments)) {
          return new RoomOpenHelper.ValidationResult(false, "investments(com.example.budgetapp.analytics.investments.entities.Investment).\n"
                  + " Expected:\n" + _infoInvestments + "\n"
                  + " Found:\n" + _existingInvestments);
        }
        final HashMap<String, TableInfo.Column> _columnsInvestmentTransactions = new HashMap<String, TableInfo.Column>(10);
        _columnsInvestmentTransactions.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestmentTransactions.put("investmentId", new TableInfo.Column("investmentId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestmentTransactions.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestmentTransactions.put("quantity", new TableInfo.Column("quantity", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestmentTransactions.put("pricePerUnit", new TableInfo.Column("pricePerUnit", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestmentTransactions.put("totalAmount", new TableInfo.Column("totalAmount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestmentTransactions.put("fees", new TableInfo.Column("fees", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestmentTransactions.put("transactionDate", new TableInfo.Column("transactionDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestmentTransactions.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestmentTransactions.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysInvestmentTransactions = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesInvestmentTransactions = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoInvestmentTransactions = new TableInfo("investment_transactions", _columnsInvestmentTransactions, _foreignKeysInvestmentTransactions, _indicesInvestmentTransactions);
        final TableInfo _existingInvestmentTransactions = TableInfo.read(db, "investment_transactions");
        if (!_infoInvestmentTransactions.equals(_existingInvestmentTransactions)) {
          return new RoomOpenHelper.ValidationResult(false, "investment_transactions(com.example.budgetapp.analytics.investments.entities.InvestmentTransaction).\n"
                  + " Expected:\n" + _infoInvestmentTransactions + "\n"
                  + " Found:\n" + _existingInvestmentTransactions);
        }
        final HashMap<String, TableInfo.Column> _columnsInvestmentPriceHistory = new HashMap<String, TableInfo.Column>(6);
        _columnsInvestmentPriceHistory.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestmentPriceHistory.put("investmentId", new TableInfo.Column("investmentId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestmentPriceHistory.put("price", new TableInfo.Column("price", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestmentPriceHistory.put("currency", new TableInfo.Column("currency", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestmentPriceHistory.put("recordedAt", new TableInfo.Column("recordedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestmentPriceHistory.put("source", new TableInfo.Column("source", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysInvestmentPriceHistory = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesInvestmentPriceHistory = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoInvestmentPriceHistory = new TableInfo("investment_price_history", _columnsInvestmentPriceHistory, _foreignKeysInvestmentPriceHistory, _indicesInvestmentPriceHistory);
        final TableInfo _existingInvestmentPriceHistory = TableInfo.read(db, "investment_price_history");
        if (!_infoInvestmentPriceHistory.equals(_existingInvestmentPriceHistory)) {
          return new RoomOpenHelper.ValidationResult(false, "investment_price_history(com.example.budgetapp.analytics.investments.entities.InvestmentPriceHistory).\n"
                  + " Expected:\n" + _infoInvestmentPriceHistory + "\n"
                  + " Found:\n" + _existingInvestmentPriceHistory);
        }
        final HashMap<String, TableInfo.Column> _columnsInvestmentDividends = new HashMap<String, TableInfo.Column>(10);
        _columnsInvestmentDividends.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestmentDividends.put("investmentId", new TableInfo.Column("investmentId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestmentDividends.put("amountPerShare", new TableInfo.Column("amountPerShare", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestmentDividends.put("totalAmount", new TableInfo.Column("totalAmount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestmentDividends.put("currency", new TableInfo.Column("currency", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestmentDividends.put("paymentDate", new TableInfo.Column("paymentDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestmentDividends.put("exDividendDate", new TableInfo.Column("exDividendDate", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestmentDividends.put("taxWithheld", new TableInfo.Column("taxWithheld", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestmentDividends.put("notes", new TableInfo.Column("notes", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestmentDividends.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysInvestmentDividends = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesInvestmentDividends = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoInvestmentDividends = new TableInfo("investment_dividends", _columnsInvestmentDividends, _foreignKeysInvestmentDividends, _indicesInvestmentDividends);
        final TableInfo _existingInvestmentDividends = TableInfo.read(db, "investment_dividends");
        if (!_infoInvestmentDividends.equals(_existingInvestmentDividends)) {
          return new RoomOpenHelper.ValidationResult(false, "investment_dividends(com.example.budgetapp.analytics.investments.entities.InvestmentDividend).\n"
                  + " Expected:\n" + _infoInvestmentDividends + "\n"
                  + " Found:\n" + _existingInvestmentDividends);
        }
        final HashMap<String, TableInfo.Column> _columnsInvestmentPortfolios = new HashMap<String, TableInfo.Column>(7);
        _columnsInvestmentPortfolios.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestmentPortfolios.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestmentPortfolios.put("description", new TableInfo.Column("description", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestmentPortfolios.put("targetAllocation", new TableInfo.Column("targetAllocation", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestmentPortfolios.put("riskProfile", new TableInfo.Column("riskProfile", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestmentPortfolios.put("isActive", new TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestmentPortfolios.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysInvestmentPortfolios = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesInvestmentPortfolios = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoInvestmentPortfolios = new TableInfo("investment_portfolios", _columnsInvestmentPortfolios, _foreignKeysInvestmentPortfolios, _indicesInvestmentPortfolios);
        final TableInfo _existingInvestmentPortfolios = TableInfo.read(db, "investment_portfolios");
        if (!_infoInvestmentPortfolios.equals(_existingInvestmentPortfolios)) {
          return new RoomOpenHelper.ValidationResult(false, "investment_portfolios(com.example.budgetapp.analytics.investments.entities.InvestmentPortfolio).\n"
                  + " Expected:\n" + _infoInvestmentPortfolios + "\n"
                  + " Found:\n" + _existingInvestmentPortfolios);
        }
        final HashMap<String, TableInfo.Column> _columnsPortfolioInvestments = new HashMap<String, TableInfo.Column>(5);
        _columnsPortfolioInvestments.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPortfolioInvestments.put("portfolioId", new TableInfo.Column("portfolioId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPortfolioInvestments.put("investmentId", new TableInfo.Column("investmentId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPortfolioInvestments.put("targetPercentage", new TableInfo.Column("targetPercentage", "REAL", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPortfolioInvestments.put("addedAt", new TableInfo.Column("addedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPortfolioInvestments = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPortfolioInvestments = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPortfolioInvestments = new TableInfo("portfolio_investments", _columnsPortfolioInvestments, _foreignKeysPortfolioInvestments, _indicesPortfolioInvestments);
        final TableInfo _existingPortfolioInvestments = TableInfo.read(db, "portfolio_investments");
        if (!_infoPortfolioInvestments.equals(_existingPortfolioInvestments)) {
          return new RoomOpenHelper.ValidationResult(false, "portfolio_investments(com.example.budgetapp.analytics.investments.entities.PortfolioInvestment).\n"
                  + " Expected:\n" + _infoPortfolioInvestments + "\n"
                  + " Found:\n" + _existingPortfolioInvestments);
        }
        final HashMap<String, TableInfo.Column> _columnsExchangeRates = new HashMap<String, TableInfo.Column>(5);
        _columnsExchangeRates.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExchangeRates.put("fromCurrency", new TableInfo.Column("fromCurrency", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExchangeRates.put("toCurrency", new TableInfo.Column("toCurrency", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExchangeRates.put("rate", new TableInfo.Column("rate", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsExchangeRates.put("lastUpdated", new TableInfo.Column("lastUpdated", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysExchangeRates = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesExchangeRates = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoExchangeRates = new TableInfo("exchange_rates", _columnsExchangeRates, _foreignKeysExchangeRates, _indicesExchangeRates);
        final TableInfo _existingExchangeRates = TableInfo.read(db, "exchange_rates");
        if (!_infoExchangeRates.equals(_existingExchangeRates)) {
          return new RoomOpenHelper.ValidationResult(false, "exchange_rates(com.example.budgetapp.analytics.investments.multicurrency.ExchangeRate).\n"
                  + " Expected:\n" + _infoExchangeRates + "\n"
                  + " Found:\n" + _existingExchangeRates);
        }
        final HashMap<String, TableInfo.Column> _columnsCurrencyAlerts = new HashMap<String, TableInfo.Column>(8);
        _columnsCurrencyAlerts.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCurrencyAlerts.put("fromCurrency", new TableInfo.Column("fromCurrency", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCurrencyAlerts.put("toCurrency", new TableInfo.Column("toCurrency", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCurrencyAlerts.put("targetRate", new TableInfo.Column("targetRate", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCurrencyAlerts.put("alertType", new TableInfo.Column("alertType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCurrencyAlerts.put("isActive", new TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCurrencyAlerts.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCurrencyAlerts.put("triggeredAt", new TableInfo.Column("triggeredAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCurrencyAlerts = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCurrencyAlerts = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCurrencyAlerts = new TableInfo("currency_alerts", _columnsCurrencyAlerts, _foreignKeysCurrencyAlerts, _indicesCurrencyAlerts);
        final TableInfo _existingCurrencyAlerts = TableInfo.read(db, "currency_alerts");
        if (!_infoCurrencyAlerts.equals(_existingCurrencyAlerts)) {
          return new RoomOpenHelper.ValidationResult(false, "currency_alerts(com.example.budgetapp.analytics.investments.multicurrency.CurrencyAlert).\n"
                  + " Expected:\n" + _infoCurrencyAlerts + "\n"
                  + " Found:\n" + _existingCurrencyAlerts);
        }
        final HashMap<String, TableInfo.Column> _columnsMultiCurrencyTransactions = new HashMap<String, TableInfo.Column>(10);
        _columnsMultiCurrencyTransactions.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMultiCurrencyTransactions.put("amount", new TableInfo.Column("amount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMultiCurrencyTransactions.put("currency", new TableInfo.Column("currency", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMultiCurrencyTransactions.put("convertedAmount", new TableInfo.Column("convertedAmount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMultiCurrencyTransactions.put("baseCurrency", new TableInfo.Column("baseCurrency", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMultiCurrencyTransactions.put("exchangeRate", new TableInfo.Column("exchangeRate", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMultiCurrencyTransactions.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMultiCurrencyTransactions.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMultiCurrencyTransactions.put("transactionDate", new TableInfo.Column("transactionDate", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMultiCurrencyTransactions.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMultiCurrencyTransactions = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMultiCurrencyTransactions = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMultiCurrencyTransactions = new TableInfo("multi_currency_transactions", _columnsMultiCurrencyTransactions, _foreignKeysMultiCurrencyTransactions, _indicesMultiCurrencyTransactions);
        final TableInfo _existingMultiCurrencyTransactions = TableInfo.read(db, "multi_currency_transactions");
        if (!_infoMultiCurrencyTransactions.equals(_existingMultiCurrencyTransactions)) {
          return new RoomOpenHelper.ValidationResult(false, "multi_currency_transactions(com.example.budgetapp.analytics.investments.multicurrency.MultiCurrencyTransaction).\n"
                  + " Expected:\n" + _infoMultiCurrencyTransactions + "\n"
                  + " Found:\n" + _existingMultiCurrencyTransactions);
        }
        final HashMap<String, TableInfo.Column> _columnsAutomationRules = new HashMap<String, TableInfo.Column>(12);
        _columnsAutomationRules.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAutomationRules.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAutomationRules.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAutomationRules.put("conditions", new TableInfo.Column("conditions", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAutomationRules.put("actions", new TableInfo.Column("actions", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAutomationRules.put("isActive", new TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAutomationRules.put("priority", new TableInfo.Column("priority", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAutomationRules.put("usageCount", new TableInfo.Column("usageCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAutomationRules.put("createdBy", new TableInfo.Column("createdBy", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAutomationRules.put("confidence", new TableInfo.Column("confidence", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAutomationRules.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAutomationRules.put("lastModified", new TableInfo.Column("lastModified", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAutomationRules = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAutomationRules = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAutomationRules = new TableInfo("automation_rules", _columnsAutomationRules, _foreignKeysAutomationRules, _indicesAutomationRules);
        final TableInfo _existingAutomationRules = TableInfo.read(db, "automation_rules");
        if (!_infoAutomationRules.equals(_existingAutomationRules)) {
          return new RoomOpenHelper.ValidationResult(false, "automation_rules(com.example.budgetapp.automation.AutomationRule).\n"
                  + " Expected:\n" + _infoAutomationRules + "\n"
                  + " Found:\n" + _existingAutomationRules);
        }
        final HashMap<String, TableInfo.Column> _columnsAutoTransferRules = new HashMap<String, TableInfo.Column>(13);
        _columnsAutoTransferRules.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAutoTransferRules.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAutoTransferRules.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAutoTransferRules.put("sourceAccountId", new TableInfo.Column("sourceAccountId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAutoTransferRules.put("targetAccountId", new TableInfo.Column("targetAccountId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAutoTransferRules.put("transferType", new TableInfo.Column("transferType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAutoTransferRules.put("amount", new TableInfo.Column("amount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAutoTransferRules.put("frequency", new TableInfo.Column("frequency", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAutoTransferRules.put("conditions", new TableInfo.Column("conditions", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAutoTransferRules.put("isActive", new TableInfo.Column("isActive", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAutoTransferRules.put("lastExecuted", new TableInfo.Column("lastExecuted", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAutoTransferRules.put("totalTransferred", new TableInfo.Column("totalTransferred", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAutoTransferRules.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAutoTransferRules = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAutoTransferRules = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAutoTransferRules = new TableInfo("auto_transfer_rules", _columnsAutoTransferRules, _foreignKeysAutoTransferRules, _indicesAutoTransferRules);
        final TableInfo _existingAutoTransferRules = TableInfo.read(db, "auto_transfer_rules");
        if (!_infoAutoTransferRules.equals(_existingAutoTransferRules)) {
          return new RoomOpenHelper.ValidationResult(false, "auto_transfer_rules(com.example.budgetapp.automation.AutoTransferRule).\n"
                  + " Expected:\n" + _infoAutoTransferRules + "\n"
                  + " Found:\n" + _existingAutoTransferRules);
        }
        final HashMap<String, TableInfo.Column> _columnsSmartInsights = new HashMap<String, TableInfo.Column>(14);
        _columnsSmartInsights.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSmartInsights.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSmartInsights.put("title", new TableInfo.Column("title", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSmartInsights.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSmartInsights.put("actionable", new TableInfo.Column("actionable", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSmartInsights.put("actions", new TableInfo.Column("actions", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSmartInsights.put("priority", new TableInfo.Column("priority", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSmartInsights.put("confidence", new TableInfo.Column("confidence", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSmartInsights.put("category", new TableInfo.Column("category", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSmartInsights.put("impact", new TableInfo.Column("impact", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSmartInsights.put("isRead", new TableInfo.Column("isRead", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSmartInsights.put("isDismissed", new TableInfo.Column("isDismissed", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSmartInsights.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSmartInsights.put("expiresAt", new TableInfo.Column("expiresAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSmartInsights = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSmartInsights = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSmartInsights = new TableInfo("smart_insights", _columnsSmartInsights, _foreignKeysSmartInsights, _indicesSmartInsights);
        final TableInfo _existingSmartInsights = TableInfo.read(db, "smart_insights");
        if (!_infoSmartInsights.equals(_existingSmartInsights)) {
          return new RoomOpenHelper.ValidationResult(false, "smart_insights(com.example.budgetapp.automation.ml.SmartInsight).\n"
                  + " Expected:\n" + _infoSmartInsights + "\n"
                  + " Found:\n" + _existingSmartInsights);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "efe1c484743b689c2966294e37e0ff77", "c615859d5635d44677a6509cd9f080a5");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "products","product_categories","categories","stores","store_chains","product_stores","incomes","expenses","loans","budgets","receipts","receipt_items","shopping_lists","shopping_list_items","price_history","reminder_settings","app_usage","transaction_notifications","financial_insights","financial_goals","spending_patterns","spending_forecasts","investments","investment_transactions","investment_price_history","investment_dividends","investment_portfolios","portfolio_investments","exchange_rates","currency_alerts","multi_currency_transactions","automation_rules","auto_transfer_rules","smart_insights");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `products`");
      _db.execSQL("DELETE FROM `product_categories`");
      _db.execSQL("DELETE FROM `categories`");
      _db.execSQL("DELETE FROM `stores`");
      _db.execSQL("DELETE FROM `store_chains`");
      _db.execSQL("DELETE FROM `product_stores`");
      _db.execSQL("DELETE FROM `incomes`");
      _db.execSQL("DELETE FROM `expenses`");
      _db.execSQL("DELETE FROM `loans`");
      _db.execSQL("DELETE FROM `budgets`");
      _db.execSQL("DELETE FROM `receipts`");
      _db.execSQL("DELETE FROM `receipt_items`");
      _db.execSQL("DELETE FROM `shopping_lists`");
      _db.execSQL("DELETE FROM `shopping_list_items`");
      _db.execSQL("DELETE FROM `price_history`");
      _db.execSQL("DELETE FROM `reminder_settings`");
      _db.execSQL("DELETE FROM `app_usage`");
      _db.execSQL("DELETE FROM `transaction_notifications`");
      _db.execSQL("DELETE FROM `financial_insights`");
      _db.execSQL("DELETE FROM `financial_goals`");
      _db.execSQL("DELETE FROM `spending_patterns`");
      _db.execSQL("DELETE FROM `spending_forecasts`");
      _db.execSQL("DELETE FROM `investments`");
      _db.execSQL("DELETE FROM `investment_transactions`");
      _db.execSQL("DELETE FROM `investment_price_history`");
      _db.execSQL("DELETE FROM `investment_dividends`");
      _db.execSQL("DELETE FROM `investment_portfolios`");
      _db.execSQL("DELETE FROM `portfolio_investments`");
      _db.execSQL("DELETE FROM `exchange_rates`");
      _db.execSQL("DELETE FROM `currency_alerts`");
      _db.execSQL("DELETE FROM `multi_currency_transactions`");
      _db.execSQL("DELETE FROM `automation_rules`");
      _db.execSQL("DELETE FROM `auto_transfer_rules`");
      _db.execSQL("DELETE FROM `smart_insights`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(ProductDao.class, ProductDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ProductCategoryDao.class, ProductCategoryDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(CategoryDao.class, CategoryDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(StoreDao.class, StoreDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(StoreChainDao.class, StoreChainDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ProductStoreDao.class, ProductStoreDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(IncomeDao.class, IncomeDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ExpenseDao.class, ExpenseDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(LoanDao.class, LoanDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(BudgetDao.class, BudgetDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ReceiptDao.class, ReceiptDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ReceiptItemDao.class, ReceiptItemDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ShoppingListDao.class, ShoppingListDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ShoppingListItemDao.class, ShoppingListItemDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PriceHistoryDao.class, PriceHistoryDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ReminderSettingsDao.class, ReminderSettingsDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AppUsageDao.class, AppUsageDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(TransactionNotificationDao.class, TransactionNotificationDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(FinancialInsightDao.class, FinancialInsightDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(FinancialGoalDao.class, FinancialGoalDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(SpendingPatternDao.class, SpendingPatternDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(SpendingForecastDao.class, SpendingForecastDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(InvestmentDao.class, InvestmentDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ExchangeRateDao.class, ExchangeRateDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AutomationRuleDao.class, AutomationRuleDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public ProductDao productDao() {
    if (_productDao != null) {
      return _productDao;
    } else {
      synchronized(this) {
        if(_productDao == null) {
          _productDao = new ProductDao_Impl(this);
        }
        return _productDao;
      }
    }
  }

  @Override
  public ProductCategoryDao productCategoryDao() {
    if (_productCategoryDao != null) {
      return _productCategoryDao;
    } else {
      synchronized(this) {
        if(_productCategoryDao == null) {
          _productCategoryDao = new ProductCategoryDao_Impl(this);
        }
        return _productCategoryDao;
      }
    }
  }

  @Override
  public CategoryDao categoryDao() {
    if (_categoryDao != null) {
      return _categoryDao;
    } else {
      synchronized(this) {
        if(_categoryDao == null) {
          _categoryDao = new CategoryDao_Impl(this);
        }
        return _categoryDao;
      }
    }
  }

  @Override
  public StoreDao storeDao() {
    if (_storeDao != null) {
      return _storeDao;
    } else {
      synchronized(this) {
        if(_storeDao == null) {
          _storeDao = new StoreDao_Impl(this);
        }
        return _storeDao;
      }
    }
  }

  @Override
  public StoreChainDao storeChainDao() {
    if (_storeChainDao != null) {
      return _storeChainDao;
    } else {
      synchronized(this) {
        if(_storeChainDao == null) {
          _storeChainDao = new StoreChainDao_Impl(this);
        }
        return _storeChainDao;
      }
    }
  }

  @Override
  public ProductStoreDao productStoreDao() {
    if (_productStoreDao != null) {
      return _productStoreDao;
    } else {
      synchronized(this) {
        if(_productStoreDao == null) {
          _productStoreDao = new ProductStoreDao_Impl(this);
        }
        return _productStoreDao;
      }
    }
  }

  @Override
  public IncomeDao incomeDao() {
    if (_incomeDao != null) {
      return _incomeDao;
    } else {
      synchronized(this) {
        if(_incomeDao == null) {
          _incomeDao = new IncomeDao_Impl(this);
        }
        return _incomeDao;
      }
    }
  }

  @Override
  public ExpenseDao expenseDao() {
    if (_expenseDao != null) {
      return _expenseDao;
    } else {
      synchronized(this) {
        if(_expenseDao == null) {
          _expenseDao = new ExpenseDao_Impl(this);
        }
        return _expenseDao;
      }
    }
  }

  @Override
  public LoanDao loanDao() {
    if (_loanDao != null) {
      return _loanDao;
    } else {
      synchronized(this) {
        if(_loanDao == null) {
          _loanDao = new LoanDao_Impl(this);
        }
        return _loanDao;
      }
    }
  }

  @Override
  public BudgetDao budgetDao() {
    if (_budgetDao != null) {
      return _budgetDao;
    } else {
      synchronized(this) {
        if(_budgetDao == null) {
          _budgetDao = new BudgetDao_Impl(this);
        }
        return _budgetDao;
      }
    }
  }

  @Override
  public ReceiptDao receiptDao() {
    if (_receiptDao != null) {
      return _receiptDao;
    } else {
      synchronized(this) {
        if(_receiptDao == null) {
          _receiptDao = new ReceiptDao_Impl(this);
        }
        return _receiptDao;
      }
    }
  }

  @Override
  public ReceiptItemDao receiptItemDao() {
    if (_receiptItemDao != null) {
      return _receiptItemDao;
    } else {
      synchronized(this) {
        if(_receiptItemDao == null) {
          _receiptItemDao = new ReceiptItemDao_Impl(this);
        }
        return _receiptItemDao;
      }
    }
  }

  @Override
  public ShoppingListDao shoppingListDao() {
    if (_shoppingListDao != null) {
      return _shoppingListDao;
    } else {
      synchronized(this) {
        if(_shoppingListDao == null) {
          _shoppingListDao = new ShoppingListDao_Impl(this);
        }
        return _shoppingListDao;
      }
    }
  }

  @Override
  public ShoppingListItemDao shoppingListItemDao() {
    if (_shoppingListItemDao != null) {
      return _shoppingListItemDao;
    } else {
      synchronized(this) {
        if(_shoppingListItemDao == null) {
          _shoppingListItemDao = new ShoppingListItemDao_Impl(this);
        }
        return _shoppingListItemDao;
      }
    }
  }

  @Override
  public PriceHistoryDao priceHistoryDao() {
    if (_priceHistoryDao != null) {
      return _priceHistoryDao;
    } else {
      synchronized(this) {
        if(_priceHistoryDao == null) {
          _priceHistoryDao = new PriceHistoryDao_Impl(this);
        }
        return _priceHistoryDao;
      }
    }
  }

  @Override
  public ReminderSettingsDao reminderSettingsDao() {
    if (_reminderSettingsDao != null) {
      return _reminderSettingsDao;
    } else {
      synchronized(this) {
        if(_reminderSettingsDao == null) {
          _reminderSettingsDao = new ReminderSettingsDao_Impl(this);
        }
        return _reminderSettingsDao;
      }
    }
  }

  @Override
  public AppUsageDao appUsageDao() {
    if (_appUsageDao != null) {
      return _appUsageDao;
    } else {
      synchronized(this) {
        if(_appUsageDao == null) {
          _appUsageDao = new AppUsageDao_Impl(this);
        }
        return _appUsageDao;
      }
    }
  }

  @Override
  public TransactionNotificationDao transactionNotificationDao() {
    if (_transactionNotificationDao != null) {
      return _transactionNotificationDao;
    } else {
      synchronized(this) {
        if(_transactionNotificationDao == null) {
          _transactionNotificationDao = new TransactionNotificationDao_Impl(this);
        }
        return _transactionNotificationDao;
      }
    }
  }

  @Override
  public FinancialInsightDao financialInsightDao() {
    if (_financialInsightDao != null) {
      return _financialInsightDao;
    } else {
      synchronized(this) {
        if(_financialInsightDao == null) {
          _financialInsightDao = new FinancialInsightDao_Impl(this);
        }
        return _financialInsightDao;
      }
    }
  }

  @Override
  public FinancialGoalDao financialGoalDao() {
    if (_financialGoalDao != null) {
      return _financialGoalDao;
    } else {
      synchronized(this) {
        if(_financialGoalDao == null) {
          _financialGoalDao = new FinancialGoalDao_Impl(this);
        }
        return _financialGoalDao;
      }
    }
  }

  @Override
  public SpendingPatternDao spendingPatternDao() {
    if (_spendingPatternDao != null) {
      return _spendingPatternDao;
    } else {
      synchronized(this) {
        if(_spendingPatternDao == null) {
          _spendingPatternDao = new SpendingPatternDao_Impl(this);
        }
        return _spendingPatternDao;
      }
    }
  }

  @Override
  public SpendingForecastDao spendingForecastDao() {
    if (_spendingForecastDao != null) {
      return _spendingForecastDao;
    } else {
      synchronized(this) {
        if(_spendingForecastDao == null) {
          _spendingForecastDao = new SpendingForecastDao_Impl(this);
        }
        return _spendingForecastDao;
      }
    }
  }

  @Override
  public InvestmentDao investmentDao() {
    if (_investmentDao != null) {
      return _investmentDao;
    } else {
      synchronized(this) {
        if(_investmentDao == null) {
          _investmentDao = new InvestmentDao_Impl(this);
        }
        return _investmentDao;
      }
    }
  }

  @Override
  public ExchangeRateDao exchangeRateDao() {
    if (_exchangeRateDao != null) {
      return _exchangeRateDao;
    } else {
      synchronized(this) {
        if(_exchangeRateDao == null) {
          _exchangeRateDao = new ExchangeRateDao_Impl(this);
        }
        return _exchangeRateDao;
      }
    }
  }

  @Override
  public AutomationRuleDao automationRuleDao() {
    if (_automationRuleDao != null) {
      return _automationRuleDao;
    } else {
      synchronized(this) {
        if(_automationRuleDao == null) {
          _automationRuleDao = new AutomationRuleDao_Impl(this);
        }
        return _automationRuleDao;
      }
    }
  }
}
