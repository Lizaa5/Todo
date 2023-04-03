package com.example.final_todo.database;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.final_todo.model.Todo;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TodoDao_Impl implements TodoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Todo> __insertionAdapterOfTodo;

  private final EntityDeletionOrUpdateAdapter<Todo> __deletionAdapterOfTodo;

  private final EntityDeletionOrUpdateAdapter<Todo> __updateAdapterOfTodo;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllCompleted;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllTodoWithCategory;

  private final SharedSQLiteStatement __preparedStmtOfCompleteTask;

  public TodoDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTodo = new EntityInsertionAdapter<Todo>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `Todo` (`todoId`,`title`,`description`,`todoDate`,`isCompleted`,`priority`,`categoryId`,`createdOn`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Todo value) {
        stmt.bindLong(1, value.getTodoId());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        final Long _tmp = DateConvertor.toTimeStamp(value.getTodoDate());
        if (_tmp == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, _tmp);
        }
        final int _tmp_1 = value.isCompleted() ? 1 : 0;
        stmt.bindLong(5, _tmp_1);
        stmt.bindLong(6, value.getPriority());
        stmt.bindLong(7, value.getCategoryId());
        final Long _tmp_2 = DateConvertor.toTimeStamp(value.getCreatedOn());
        if (_tmp_2 == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindLong(8, _tmp_2);
        }
      }
    };
    this.__deletionAdapterOfTodo = new EntityDeletionOrUpdateAdapter<Todo>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Todo` WHERE `todoId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Todo value) {
        stmt.bindLong(1, value.getTodoId());
      }
    };
    this.__updateAdapterOfTodo = new EntityDeletionOrUpdateAdapter<Todo>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Todo` SET `todoId` = ?,`title` = ?,`description` = ?,`todoDate` = ?,`isCompleted` = ?,`priority` = ?,`categoryId` = ?,`createdOn` = ? WHERE `todoId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Todo value) {
        stmt.bindLong(1, value.getTodoId());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        final Long _tmp = DateConvertor.toTimeStamp(value.getTodoDate());
        if (_tmp == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, _tmp);
        }
        final int _tmp_1 = value.isCompleted() ? 1 : 0;
        stmt.bindLong(5, _tmp_1);
        stmt.bindLong(6, value.getPriority());
        stmt.bindLong(7, value.getCategoryId());
        final Long _tmp_2 = DateConvertor.toTimeStamp(value.getCreatedOn());
        if (_tmp_2 == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindLong(8, _tmp_2);
        }
        stmt.bindLong(9, value.getTodoId());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from todo";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllCompleted = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from todo where isCompleted=1";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAllTodoWithCategory = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "delete from todo where categoryId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfCompleteTask = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "update todo set isCompleted=1 where todoId = ?";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Todo todo) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTodo.insert(todo);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Todo todo) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfTodo.handle(todo);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateTodo(final Todo todo) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfTodo.handle(todo);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public void deleteAllCompleted() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllCompleted.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllCompleted.release(_stmt);
    }
  }

  @Override
  public void deleteAllTodoWithCategory(final int categoryId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllTodoWithCategory.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, categoryId);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllTodoWithCategory.release(_stmt);
    }
  }

  @Override
  public void completeTask(final int todoId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfCompleteTask.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, todoId);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfCompleteTask.release(_stmt);
    }
  }

  @Override
  public LiveData<Todo> loadTodoById(final int todoId) {
    final String _sql = "select * from todo where todoId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, todoId);
    return __db.getInvalidationTracker().createLiveData(new String[]{"todo"}, false, new Callable<Todo>() {
      @Override
      public Todo call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTodoId = CursorUtil.getColumnIndexOrThrow(_cursor, "todoId");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTodoDate = CursorUtil.getColumnIndexOrThrow(_cursor, "todoDate");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfCreatedOn = CursorUtil.getColumnIndexOrThrow(_cursor, "createdOn");
          final Todo _result;
          if(_cursor.moveToFirst()) {
            _result = new Todo();
            final int _tmpTodoId;
            _tmpTodoId = _cursor.getInt(_cursorIndexOfTodoId);
            _result.setTodoId(_tmpTodoId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            _result.setTitle(_tmpTitle);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            _result.setDescription(_tmpDescription);
            final Date _tmpTodoDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfTodoDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfTodoDate);
            }
            _tmpTodoDate = DateConvertor.toDate(_tmp);
            _result.setTodoDate(_tmpTodoDate);
            final boolean _tmpIsCompleted;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_1 != 0;
            _result.setCompleted(_tmpIsCompleted);
            final int _tmpPriority;
            _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
            _result.setPriority(_tmpPriority);
            final int _tmpCategoryId;
            _tmpCategoryId = _cursor.getInt(_cursorIndexOfCategoryId);
            _result.setCategoryId(_tmpCategoryId);
            final Date _tmpCreatedOn;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfCreatedOn)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfCreatedOn);
            }
            _tmpCreatedOn = DateConvertor.toDate(_tmp_2);
            _result.setCreatedOn(_tmpCreatedOn);
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
  public LiveData<List<Todo>> loadTodoByCategoryId(final int categoryId) {
    final String _sql = "select * from todo where categoryId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, categoryId);
    return __db.getInvalidationTracker().createLiveData(new String[]{"todo"}, false, new Callable<List<Todo>>() {
      @Override
      public List<Todo> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTodoId = CursorUtil.getColumnIndexOrThrow(_cursor, "todoId");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTodoDate = CursorUtil.getColumnIndexOrThrow(_cursor, "todoDate");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfCreatedOn = CursorUtil.getColumnIndexOrThrow(_cursor, "createdOn");
          final List<Todo> _result = new ArrayList<Todo>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Todo _item;
            _item = new Todo();
            final int _tmpTodoId;
            _tmpTodoId = _cursor.getInt(_cursorIndexOfTodoId);
            _item.setTodoId(_tmpTodoId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            _item.setTitle(_tmpTitle);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            _item.setDescription(_tmpDescription);
            final Date _tmpTodoDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfTodoDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfTodoDate);
            }
            _tmpTodoDate = DateConvertor.toDate(_tmp);
            _item.setTodoDate(_tmpTodoDate);
            final boolean _tmpIsCompleted;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_1 != 0;
            _item.setCompleted(_tmpIsCompleted);
            final int _tmpPriority;
            _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
            _item.setPriority(_tmpPriority);
            final int _tmpCategoryId;
            _tmpCategoryId = _cursor.getInt(_cursorIndexOfCategoryId);
            _item.setCategoryId(_tmpCategoryId);
            final Date _tmpCreatedOn;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfCreatedOn)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfCreatedOn);
            }
            _tmpCreatedOn = DateConvertor.toDate(_tmp_2);
            _item.setCreatedOn(_tmpCreatedOn);
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
  public LiveData<List<Todo>> TodoloadAll() {
    final String _sql = "select * from todo";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"todo"}, false, new Callable<List<Todo>>() {
      @Override
      public List<Todo> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfTodoId = CursorUtil.getColumnIndexOrThrow(_cursor, "todoId");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfTodoDate = CursorUtil.getColumnIndexOrThrow(_cursor, "todoDate");
          final int _cursorIndexOfIsCompleted = CursorUtil.getColumnIndexOrThrow(_cursor, "isCompleted");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfCategoryId = CursorUtil.getColumnIndexOrThrow(_cursor, "categoryId");
          final int _cursorIndexOfCreatedOn = CursorUtil.getColumnIndexOrThrow(_cursor, "createdOn");
          final List<Todo> _result = new ArrayList<Todo>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Todo _item;
            _item = new Todo();
            final int _tmpTodoId;
            _tmpTodoId = _cursor.getInt(_cursorIndexOfTodoId);
            _item.setTodoId(_tmpTodoId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            _item.setTitle(_tmpTitle);
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            _item.setDescription(_tmpDescription);
            final Date _tmpTodoDate;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfTodoDate)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfTodoDate);
            }
            _tmpTodoDate = DateConvertor.toDate(_tmp);
            _item.setTodoDate(_tmpTodoDate);
            final boolean _tmpIsCompleted;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsCompleted);
            _tmpIsCompleted = _tmp_1 != 0;
            _item.setCompleted(_tmpIsCompleted);
            final int _tmpPriority;
            _tmpPriority = _cursor.getInt(_cursorIndexOfPriority);
            _item.setPriority(_tmpPriority);
            final int _tmpCategoryId;
            _tmpCategoryId = _cursor.getInt(_cursorIndexOfCategoryId);
            _item.setCategoryId(_tmpCategoryId);
            final Date _tmpCreatedOn;
            final Long _tmp_2;
            if (_cursor.isNull(_cursorIndexOfCreatedOn)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getLong(_cursorIndexOfCreatedOn);
            }
            _tmpCreatedOn = DateConvertor.toDate(_tmp_2);
            _item.setCreatedOn(_tmpCreatedOn);
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

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
