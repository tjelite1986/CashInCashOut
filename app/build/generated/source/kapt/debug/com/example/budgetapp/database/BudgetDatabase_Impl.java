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
import com.example.budgetapp.database.dao.CategoryDao;
import com.example.budgetapp.database.dao.CategoryDao_Impl;
import com.example.budgetapp.database.dao.ExpenseDao;
import com.example.budgetapp.database.dao.ExpenseDao_Impl;
import com.example.budgetapp.database.dao.IncomeDao;
import com.example.budgetapp.database.dao.IncomeDao_Impl;
import com.example.budgetapp.database.dao.ProductDao;
import com.example.budgetapp.database.dao.ProductDao_Impl;
import com.example.budgetapp.database.dao.ProductStoreDao;
import com.example.budgetapp.database.dao.ProductStoreDao_Impl;
import com.example.budgetapp.database.dao.StoreDao;
import com.example.budgetapp.database.dao.StoreDao_Impl;
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

  private volatile CategoryDao _categoryDao;

  private volatile StoreDao _storeDao;

  private volatile ProductStoreDao _productStoreDao;

  private volatile IncomeDao _incomeDao;

  private volatile ExpenseDao _expenseDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(6) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `products` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `categoryId` INTEGER, `hasDeposit` INTEGER NOT NULL, `depositAmount` REAL, `barcode` TEXT, `productName` TEXT, `description` TEXT, `amount` REAL, `unit` TEXT, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL, FOREIGN KEY(`categoryId`) REFERENCES `categories`(`id`) ON UPDATE NO ACTION ON DELETE SET NULL )");
        db.execSQL("CREATE TABLE IF NOT EXISTS `categories` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `color` TEXT, `iconName` TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `stores` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT NOT NULL, `address` TEXT, `city` TEXT, `chain` TEXT, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `product_stores` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `productId` INTEGER NOT NULL, `storeId` INTEGER NOT NULL, `price` REAL NOT NULL, `hasCampaignPrice` INTEGER NOT NULL, `campaignQuantity` INTEGER, `campaignPrice` REAL, `lastSeen` INTEGER NOT NULL, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL, FOREIGN KEY(`productId`) REFERENCES `products`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE , FOREIGN KEY(`storeId`) REFERENCES `stores`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_product_stores_productId` ON `product_stores` (`productId`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_product_stores_storeId` ON `product_stores` (`storeId`)");
        db.execSQL("CREATE UNIQUE INDEX IF NOT EXISTS `index_product_stores_productId_storeId` ON `product_stores` (`productId`, `storeId`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `incomes` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT NOT NULL, `amount` REAL NOT NULL, `description` TEXT, `category` TEXT, `date` INTEGER NOT NULL, `isRecurring` INTEGER NOT NULL, `recurringType` TEXT, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `expenses` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT NOT NULL, `amount` REAL NOT NULL, `description` TEXT, `category` TEXT, `store` TEXT, `date` INTEGER NOT NULL, `isRecurring` INTEGER NOT NULL, `recurringType` TEXT, `paymentMethod` TEXT, `createdAt` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'bf9dc9247d3fdd6e37ac1ec70b7f4cac')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `products`");
        db.execSQL("DROP TABLE IF EXISTS `categories`");
        db.execSQL("DROP TABLE IF EXISTS `stores`");
        db.execSQL("DROP TABLE IF EXISTS `product_stores`");
        db.execSQL("DROP TABLE IF EXISTS `incomes`");
        db.execSQL("DROP TABLE IF EXISTS `expenses`");
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
        _columnsProducts.put("categoryId", new TableInfo.Column("categoryId", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
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
        _foreignKeysProducts.add(new TableInfo.ForeignKey("categories", "SET NULL", "NO ACTION", Arrays.asList("categoryId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesProducts = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoProducts = new TableInfo("products", _columnsProducts, _foreignKeysProducts, _indicesProducts);
        final TableInfo _existingProducts = TableInfo.read(db, "products");
        if (!_infoProducts.equals(_existingProducts)) {
          return new RoomOpenHelper.ValidationResult(false, "products(com.example.budgetapp.database.entities.Product).\n"
                  + " Expected:\n" + _infoProducts + "\n"
                  + " Found:\n" + _existingProducts);
        }
        final HashMap<String, TableInfo.Column> _columnsCategories = new HashMap<String, TableInfo.Column>(4);
        _columnsCategories.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCategories.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCategories.put("color", new TableInfo.Column("color", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCategories.put("iconName", new TableInfo.Column("iconName", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCategories = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCategories = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCategories = new TableInfo("categories", _columnsCategories, _foreignKeysCategories, _indicesCategories);
        final TableInfo _existingCategories = TableInfo.read(db, "categories");
        if (!_infoCategories.equals(_existingCategories)) {
          return new RoomOpenHelper.ValidationResult(false, "categories(com.example.budgetapp.database.entities.Category).\n"
                  + " Expected:\n" + _infoCategories + "\n"
                  + " Found:\n" + _existingCategories);
        }
        final HashMap<String, TableInfo.Column> _columnsStores = new HashMap<String, TableInfo.Column>(7);
        _columnsStores.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStores.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStores.put("address", new TableInfo.Column("address", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStores.put("city", new TableInfo.Column("city", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsStores.put("chain", new TableInfo.Column("chain", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
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
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "bf9dc9247d3fdd6e37ac1ec70b7f4cac", "c7720fc82f11e56f5ce4113c2fd419ae");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "products","categories","stores","product_stores","incomes","expenses");
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
      _db.execSQL("DELETE FROM `categories`");
      _db.execSQL("DELETE FROM `stores`");
      _db.execSQL("DELETE FROM `product_stores`");
      _db.execSQL("DELETE FROM `incomes`");
      _db.execSQL("DELETE FROM `expenses`");
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
    _typeConvertersMap.put(CategoryDao.class, CategoryDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(StoreDao.class, StoreDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ProductStoreDao.class, ProductStoreDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(IncomeDao.class, IncomeDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ExpenseDao.class, ExpenseDao_Impl.getRequiredConverters());
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
}
