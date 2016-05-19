package com.app.core.configuration;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import org.afinal.simplecache.ACache;

import com.android.volley.base.Request;
import com.android.volley.base.RequestQueue;
import com.android.volley.base.VolleyLog;
import com.android.volley.http.RequestManager;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.app.core.db.DatabaseHelper;
import com.app.core.util.Sputil;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

public class MyApp extends Application {

    public static final String TAG="MyApp";

    /** MyApp实例 **/
    private static MyApp myApp;

    /** 上下文 **/
    private Context context;

    /** SharedPreferences工具类 **/
    private Sputil sp;

    /** 缓存工具类 **/
    private ACache aCache;

    /** imageload磁盘缓存大小 **/
    private static final int MAXCACHESIZE=50 * 1024 * 1024;

    /** volley队列 **/
    private RequestQueue mRequestQueue;

    /** 保存活动实例的弱映射 **/
    private HashMap<String, WeakReference<Context>> contextObjects=new HashMap<String, WeakReference<Context>>();

    @Override
    public void onCreate() {
        super.onCreate();

        init();

    }

    /**
     * @description: 初始化一些配置
     * @author: Administrator
     * @date: 2015年7月17日 下午2:19:40
     */
    private void init() {
        myApp=this;
        context=getApplicationContext();
        RequestManager.getInstance().init(context);
        sp=new Sputil(context, TAG);
        initImageLoader(context);
        // 初始化缓存
        aCache=ACache.get(context);

        // 初始化数据库
        DatabaseHelper helper=DatabaseHelper.getHelper(context);
        helper.onCreate(helper.getWritableDatabase());
    }

    /**
     * @description: 返回application实例
     * @author: zhanghao
     * @date: 2015年7月7日 下午4:50:02
     * @return
     */
    public static synchronized MyApp getInstance() {
        if(null == myApp) {
            myApp=new MyApp();
        }
        return myApp;
    }

    /**
     * 初始化ImageLoader
     * @param context
     */
    @SuppressWarnings("deprecation")
    private void initImageLoader(Context context) {
        final int memoryCacheSize=(int)(Runtime.getRuntime().maxMemory() / 8);
        ImageLoaderConfiguration config=
            new ImageLoaderConfiguration.Builder(context).threadPriority(Thread.NORM_PRIORITY - 2).diskCacheSize(MAXCACHESIZE)
                .denyCacheImageMultipleSizesInMemory().diskCacheFileNameGenerator(new HashCodeFileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).writeDebugLogs() // Remove for release appz
                .memoryCacheSize(memoryCacheSize).build();
        ImageLoader.getInstance().init(config);
    }

    /**
     * 获取活动实例
     * @param className
     * @return
     */
    public synchronized Context getActiveContext(String className) {
        final WeakReference<Context> ref=contextObjects.get(className);
        if(ref == null) {
            return null;
        }
        final Context c=ref.get();
        if(c == null) { // 如果弱引用已经失效，确保移除它

            contextObjects.remove(className);
        }

        return c;
    }

    /**
     * 添加活动实例
     * @param className
     * @return
     */
    public synchronized void setActiveContext(String className, Context context) {
        final WeakReference<Context> ref=new WeakReference<Context>(context);
        this.contextObjects.put(className, ref);
    }

    /**
     * 移除活动实例
     * @param className
     * @return
     */
    public synchronized void resetActiveContext(String className) {
        contextObjects.remove(className);
    }

    /**
     * @description: 获得SharedPreferences实例
     * @author: Administrator
     * @date: 2015年7月16日 下午5:53:59
     * @return
     */
    public Sputil getSp() {
        return sp;
    }

    /**
     * @description: 获得缓存实例
     * @author: Administrator
     * @date: 2015年7月16日 下午5:53:59
     * @return
     */
    public ACache getaCache() {
        return aCache;
    }

}
