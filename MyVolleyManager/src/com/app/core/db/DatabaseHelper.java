package com.app.core.db;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.app.core.configuration.Constants;
import com.app.core.db.bean.Dept;
import com.app.core.db.bean.Stock;
import com.app.core.db.bean.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final int databaseVersion=1;

    private static DatabaseHelper instance;

    private Map<String, Dao> daos=new HashMap<String, Dao>();

    private DatabaseHelper(Context context) {
        super(context, Constants.DB_NAME, null, databaseVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Stock.class);
            TableUtils.createTableIfNotExists(connectionSource, Dept.class);
            TableUtils.createTableIfNotExists(connectionSource, User.class);

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    /**
     * 单例获取该Helper
     * @param context
     * @return
     */
    public static synchronized DatabaseHelper getHelper(Context context) {
        context=context.getApplicationContext();
        if(instance == null) {
            synchronized(DatabaseHelper.class) {
                if(instance == null)
                    instance=new DatabaseHelper(context);
            }
        }

        return instance;
    }

    /**
     * 获取dao
     * @param context
     * @return
     */
    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao=null;
        String className=clazz.getSimpleName();

        if(daos.containsKey(className)) {
            dao=daos.get(className);
        }
        if(dao == null) {
            dao=super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close() {
        super.close();

        for(String key: daos.keySet()) {
            Dao dao=daos.get(key);
            dao=null;
        }
    }

}
