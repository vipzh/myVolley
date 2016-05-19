package com.app.core.activity;

import com.app.core.configuration.MyApp;
import com.app.core.presenter.ParseData;
import com.app.core.presenter.ParseDataImpl;
import com.app.core.util.LogUtils;
import com.github.kevinsawicki.wishlist.ViewFinder;
import com.github.kevinsawicki.wishlist.ViewUtils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

public abstract class BaseActivity extends FragmentActivity implements OnClickListener {

    public static final String TAG="BaseActivity";

    /** 上下文 **/
    protected Context mContext;

    /**
     * Activity是否第一次启动
     */
    protected boolean wasCreated;

    /**
     * Activity是否中断了
     */
    private boolean wasInterrupted;

    /** 定时提醒 **/
    private AlarmManager am;

    /** 延期意图 **/
    private PendingIntent pi;

    /** 绑定这个activity布局的Finder **/
    protected ViewFinder finder;

    /** 网络参数 **/
    protected String params;

    protected Intent intent=new Intent();
    
    protected ParseData pData=new ParseDataImpl();

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);

        init();

    }

    private void init() {
        setContentView(setContentView());
        mContext=MyApp.getInstance();
        finder=new ViewFinder(this);
        findViewbyId();
        setListener();
        getData();
        process();
        if(getApplication() instanceof MyApp) {
            // 添加活动实例
            ((MyApp)getApplication()).setActiveContext(getClass().getCanonicalName(), this);
        }

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        wasInterrupted=true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtils.i(getActiviyName(), "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.i(getActiviyName(), "onResume()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtils.i(getActiviyName(), "onRestart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        wasCreated=wasInterrupted=false;
        LogUtils.i(getActiviyName(), "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtils.i(getActiviyName(), "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 移除活动实例
        if(getApplication() instanceof MyApp) {
            ((MyApp)getApplication()).resetActiveContext(getClass().getCanonicalName());
        }

        LogUtils.i(getActiviyName(), "onDestroy()");
    }

    /**
     * activity name
     */
    public abstract String getActiviyName();

    /**
     * 加载布局
     */
    protected abstract void findViewbyId();

    /**
     * 设置监听
     */
    protected abstract void setListener();

    /**
     * 处理一般逻辑
     */
    protected abstract void process();

    /**
     * 获取数据（联网或者取本地数据库）
     */
    protected abstract void getData();

    /**
     * 展示View
     * @param view
     * @return
     */
    protected BaseActivity show(final View view) {
        ViewUtils.setGone(view, false);
        return this;
    }

    /**
     * 隐藏View
     * @param view
     * @return
     */
    protected BaseActivity hide(final View view) {
        ViewUtils.setGone(view, true);
        return this;
    }

    /**
     * 设置ContentView{@link #onCreate(Bundle)}
     * @return 资源id
     */
    protected abstract int setContentView();

    /**
     * 启动提醒
     */
    protected void startRemind() {
        LogUtils.i("onReceive", "startRemind");
        Intent intent=new Intent("com.culiu.purchase.remind");
        pi=PendingIntent.getBroadcast(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        am=(AlarmManager)getSystemService(ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 3 * 24 * 60 * 60 * 1000, 1 * 24 * 60 * 60 * 1000, pi);
    }

    /**
     * 停止提醒
     */
    protected void stopRemind() {
        Intent intent=new Intent("com.culiu.purchase.remind");
        pi=PendingIntent.getBroadcast(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        am=(AlarmManager)getSystemService(ALARM_SERVICE);
        am.cancel(pi);
    }

}
