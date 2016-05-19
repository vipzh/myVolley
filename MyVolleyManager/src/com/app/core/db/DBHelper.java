package com.app.core.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * sqlite的连接方式实际上为单连接方式，即使实用多线程也是用的一个连接 getWritableDatabase()和getReadableDatabase()都为synchronized方法，但不是static方法
 * 所以都只对同一个对象起同步作用，对于不同的对象没有任何作用 所以使用sqlite的时候可以提供一个单一的入口，防止多对象修改数据库而造成死锁 所以可以提供一个static的instance对象+它的get方法，
 * 连接可一直挂着，即使多次调用getWritableDatabase()和/或getReadableDatabase()方法也没关系， 因为你只是在获得一个已有的连接而已 数据库不用关闭，退出程序，系统会自动回收
 * 其实最主要就是synchronized关键字的作用范围的问题 不过使用一个对象不知道会不会影响程序效率
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="stock.db";

    // 版本
    private static final int DB_VERSION=1;

    /*
     * 私有的静态对象，为整个应用程序提供一个sqlite操作的静态实例，并保证只能通过下面的静态方法getHelper(Context context)获得， 防止使用时绕过同步方法改变它
     */
    private SQLiteDatabase mDb;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /**
     * 获取数据库操作对象
     * @param isWrite 是否可写
     * @return
     */
    public synchronized SQLiteDatabase getDatabase(boolean isWrite) {
        if(mDb == null || mDb.isOpen()) {
            if(isWrite) {
                try {
                    mDb=getWritableDatabase();
                } catch(Exception e) {
                    // 当数据库不可写时
                    mDb=getReadableDatabase();
                    return mDb;
                }
            } else {
                mDb=getReadableDatabase();
            }
        }
        return mDb;
    }

    public int delete(String table, String whereClause, String[] whereArgs) {
        getDatabase(true);
        return mDb.delete(table, whereClause, whereArgs);
    }

    public long insert(String table, String nullColumnHack, ContentValues values) {
        getDatabase(true);
        return mDb.insertOrThrow(table, nullColumnHack, values);
    }

    public int update(String table, ContentValues values, String whereClause, String[] whereArgs) {
        getDatabase(true);
        return mDb.update(table, values, whereClause, whereArgs);
    }

    public Cursor rawQuery(String sql, String[] selectionArgs) {
        getDatabase(false);
        return mDb.rawQuery(sql, selectionArgs);
    }

    public void execSQL(String sql) {
        getDatabase(true);
        mDb.execSQL(sql);
    }

    public Cursor query(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having,
        String orderBy) {
        getDatabase(false);
        return mDb.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
    }
}
