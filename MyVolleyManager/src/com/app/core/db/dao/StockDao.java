package com.app.core.db.dao;

import java.sql.SQLException;

import android.content.Context;

import com.app.core.db.DatabaseHelper;
import com.app.core.db.bean.Stock;
import com.j256.ormlite.dao.Dao;

public class StockDao {

    private Dao<Stock, Integer> stockDao;

    private DatabaseHelper helper;

    @SuppressWarnings("unchecked")
    public StockDao(Context context) {
        try {
            helper=DatabaseHelper.getHelper(context);
            stockDao=helper.getDao(Stock.class);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加一个Stock
     * @param stock
     */
    public void add(Stock stock) {
        try {
            stockDao.create(stock);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    /**
     * 通过Id得到一个Article
     * 
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    public Stock getArticleWithUser(int id)
    {
        Stock stock = null;
        try
        {
            stock = stockDao.queryForId(id);
            helper.getDao(Stock.class).refresh(stock);

        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return stock;
    }
    

}
