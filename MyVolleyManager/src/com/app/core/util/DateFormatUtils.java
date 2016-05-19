package com.app.core.util;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint("SimpleDateFormat")
public class DateFormatUtils {

    public static String clientTime() {

        Date date=new Date();

        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return format.format(date);

    }

    public static String getToday() {
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
        Date curDate=new Date(System.currentTimeMillis());// 获取当前时间
        String str=formatter.format(curDate);
        return str;
    }

    public static String getCurremtTime() {
        SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate=new Date(System.currentTimeMillis());// 获取当前时间
        String str=formatter.format(curDate);
        return str;
    }

    public static Long getTimeStamp() {

        return System.currentTimeMillis() / 1000;

    }

}
