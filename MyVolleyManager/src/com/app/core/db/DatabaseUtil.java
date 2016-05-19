package com.app.core.db;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import android.R.string;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

/**
 * 数据库工具类(封装对数据库的增删改查操作)
 * @author adison
 */
public class DatabaseUtil {

    private static final String TAG="DatabaseUtil";

    private static DatabaseUtil instance;

    /** 数据库帮助类 **/
    private DBHelper dbHelper;

    public synchronized static DatabaseUtil getInstance(Context context) {
        if(instance == null) {
            instance=new DatabaseUtil(context);
        }
        return instance;
    }

    /**
     * 初始化
     * @param context
     */
    private DatabaseUtil(Context context) {
        dbHelper=new DBHelper(context);
    }

    /**
     * 销毁
     */
    public static void destory() {
        if(instance != null) {
            instance.onDestory();
        }
    }

    /**
     * 销毁
     */
    public void onDestory() {
        instance=null;
        if(dbHelper != null) {
            dbHelper.close();
            dbHelper=null;
        }
    }

    // /**
    // * @MethodName: selectStockBuy
    // * @Description: TODO(根据输入条件查询股票买入数据)
    // * @param @param key
    // * @param @return
    // * @return List<SearchContent> 返回类型
    // * @author zhanghao
    // */
    // public List<SearchContent> selectStockBuy(String key) {
    //
    // List<SearchContent> stockList=new ArrayList<SearchContent>();
    // Cursor cursor=null;
    // if(null != key && !"".equals(key)) {
    // // 查询的列字段名
    // String[] columns=
    // {StockSearchColumms.STOCK_CODE, StockSearchColumms.STOCK_NAME, StockSearchColumms.STOCK_JIANPIN,
    // StockSearchColumms.STOCK_EXCHANGE, StockSearchColumms.FAVORITES};
    // // 查询条件
    // String where=
    // StockSearchColumms.STOCK_TYPE + " = ? AND " + StockSearchColumms.IS_DELISTED + " = ? AND " + " ( "
    // + StockSearchColumms.STOCK_CODE + " like ? or " + StockSearchColumms.STOCK_NAME + " like ? or "
    // + StockSearchColumms.STOCK_JIANPIN + " like ? " + " ) ";
    // // 查询参数
    // String[] selectArgs={"1", "0", key + "%", key + "%", key + "%"};
    // // 执行查询
    // cursor=dbHelper.query(StockSearchColumms.TABLE_NAME, columns, where, selectArgs, null, null, null);
    //
    // if(cursor == null) {
    // return null;
    // }
    // cursor.moveToFirst();
    // // 循环读取数据
    // while(!cursor.isAfterLast()) {
    // SearchContent content=new SearchContent();
    // String code=cursor.getString(cursor.getColumnIndex(StockSearchColumms.STOCK_CODE));
    // String name=cursor.getString(cursor.getColumnIndex(StockSearchColumms.STOCK_NAME));
    // String exchange=cursor.getString(cursor.getColumnIndex(StockSearchColumms.STOCK_EXCHANGE));
    // String favorites=cursor.getString(cursor.getColumnIndex(StockSearchColumms.FAVORITES));
    // content.setStock_code(code);
    // content.setFavorites(favorites);
    // content.setStock_name(name);
    // content.setStock_exchange(exchange);
    // stockList.add(content);
    // cursor.moveToNext();
    // }
    // if(cursor != null) {
    // cursor.close();
    // }
    // return stockList;
    // }
    // return null;
    // }

    /**
     * @MethodName: insertContent
     * @Description: TODO(股票搜索表单批量插入)
     * @param @param contentList
     * @param @return
     * @return long 返回类型
     * @author zhanghao
     */
    // public long insertListSearchContent(final List<SearchContent> contentList, final Handler handler) {
    // long insert=0;
    // new AsyncTask<Void, Void, Void>() {
    //
    // @Override
    // protected Void doInBackground(Void... params) {
    // for(Iterator iterator=contentList.iterator(); iterator.hasNext();) {
    // SearchContent content=(SearchContent)iterator.next();
    // ContentValues cv=new ContentValues();
    // cv.put(StockSearchColumms.ID, content.getId());
    // cv.put(StockSearchColumms.STOCK_CODE, content.getStock_code());
    // cv.put(StockSearchColumms.STOCK_EXCHANGE, content.getStock_exchange());
    // cv.put(StockSearchColumms.STOCK_NAME, content.getStock_name());
    // cv.put(StockSearchColumms.STOCK_PINYIN, content.getStock_pinyin());
    // cv.put(StockSearchColumms.STOCK_JIANPIN, content.getStock_jianpin());
    // cv.put(StockSearchColumms.UPDATE_TIME, content.getUpdate_time());
    // cv.put(StockSearchColumms.STATUS, content.getStatus());
    // cv.put(StockSearchColumms.STOCK_TYPE, content.getStock_type());
    // cv.put(StockSearchColumms.FAVORITES, content.getFavorites());
    // cv.put(StockSearchColumms.IS_DELISTED, content.getIs_delisted());
    // long insert2=dbHelper.insert(StockSearchColumms.TABLE_NAME, null, cv);
    // // StockGodLogUtil.info("insert2="+insert2);
    // }
    // return null;
    // }
    //
    // protected void onPostExecute(Void result) {
    // BaseActivity.sp.setValue("first_start", true);
    // // 获取数据完毕，就更新自选
    // if(handler != null)
    // handler.sendEmptyMessage(55);
    // };
    //
    // }.execute();
    // return insert;
    // }

    /**
     * @MethodName: deleteSearchContent
     * @Description: TODO(股票搜索表单条数据删除)
     * @param @param content
     * @param @return
     * @return int 返回类型
     * @author zhanghao
     */
    // public int deleteSearchContent(SearchContent content) {
    // String whereClause=StockSearchColumms.ID + " = " + content.getId();
    // int delete=dbHelper.delete(StockSearchColumms.TABLE_NAME, whereClause, null);
    // return delete;
    // }

}
