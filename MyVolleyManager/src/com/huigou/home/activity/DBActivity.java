package com.huigou.home.activity;

import java.sql.SQLException;
import java.util.List;

import com.app.core.db.DatabaseHelper;
import com.app.core.db.bean.Stock;
import com.app.core.db.dao.StockDao;
import com.j256.ormlite.dao.Dao;

import android.app.Activity;
import android.os.Bundle;

public class DBActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Dao dao=DatabaseHelper.getHelper(getApplicationContext()).getDao(Stock.class);
            Stock stock=new Stock();
            stock.setId("1");
            stock.setStock_code("1111");
            stock.setStock_name("春哥");
//            dao.createIfNotExists(stock);
            
            
            StockDao stockDao =new StockDao(getApplicationContext());
            stockDao.add(stock);
            

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

}
