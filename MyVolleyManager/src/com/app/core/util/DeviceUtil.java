package com.app.core.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.TimeZone;
import java.util.UUID;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.view.Display;
import android.view.WindowManager;

/**
 * 设备信息工具类
 * @author adison
 */
public class DeviceUtil {

    /**
     * 得到手机IMEI
     * @param context
     * @return
     */
    public static String getImei(Context context) {
        TelephonyManager tm=(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        if(tm != null) {
            String deviceId=tm.getDeviceId();
            if(deviceId != null)
                return deviceId;
        }
        return getMac(context);
    }

    /**
     * 得到Mac地址
     * @param contex
     * @return
     */
    public static String getMac(Context context) {
        String mac=null;
        WifiManager wifi=(WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        if(wifi != null) {
            WifiInfo info=wifi.getConnectionInfo();
            if(info != null) {
                mac=info.getMacAddress();
                return mac;
            }
        }
        return UUID.randomUUID().toString();
    }

    /**
     * 获取androidid
     * @param context
     * @return
     */
    public static String getAndroidId(Context context) {
        String androidId=Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
        if(androidId != null)
            return androidId;
        return UUID.randomUUID().toString();
    }

    /**
     * 获取CPU序列号
     * @return CPU序列号(16位) 读取失败为"0000000000000000"
     */
    public static String getCPUSerial() {
        String str="", strCPU="", cpuAddress="0000000000000000";
        try {
            // 读取CPU信息
            Process pp=Runtime.getRuntime().exec("cat /proc/cpuinfo");
            InputStreamReader ir=new InputStreamReader(pp.getInputStream());
            LineNumberReader input=new LineNumberReader(ir);
            // 查找CPU序列号
            for(int i=1; i < 100; i++) {
                str=input.readLine();
                if(str != null) {
                    // 查找到序列号所在行
                    if(str.indexOf("Serial") > -1) {
                        // 提取序列号
                        strCPU=str.substring(str.indexOf(":") + 1, str.length());
                        // 去空格
                        cpuAddress=strCPU.trim();
                        break;
                    }
                } else {
                    // 文件结尾
                    break;
                }
            }
        } catch(IOException ex) {
            // 赋予默认值
            ex.printStackTrace();
        }
        return cpuAddress;
    }

    /**
     * 获取设备品牌
     * @param context
     * @return
     */
    public static String getBrand(Context context) {
        return android.os.Build.BRAND;
    }

    /**
     * 获取设备型号
     * @param context
     * @return
     */
    public static String getModel(Context context) {
        return android.os.Build.MODEL;
    }

    /**
     * 获取OS版本号
     * @param context
     * @return
     */
    public static String getOSversion(Context context) {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取当前所处国家
     * @param context
     * @return
     */
    public static String getCountry(Context context) {
        return context.getResources().getConfiguration().locale.getCountry();
    }

    /**
     * 获取当前手机语言
     * @param context
     * @return
     */
    public static String getLanguage(Context context) {
        return context.getResources().getConfiguration().locale.getLanguage();
    }

    /**
     * 获取当前手机时区
     * @param context
     * @return GMT+08:00
     */
    public static String getTimeZone(Context context) {
        return TimeZone.getDefault().getDisplayName(false, TimeZone.SHORT);
    }

    /**
     * 获取移动国家码mcc，460表示中国
     * @param context
     * @return
     */
    public static String getMcc(Context context) {
        TelephonyManager mTelephonyManager=(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        String imsi=mTelephonyManager.getSubscriberId();
        if(imsi != null && imsi.length() >= 3) {
            return imsi.substring(0, 3);
        }
        return "";
    }

    /**
     * 获取运营商
     * @param context
     * @return
     */
    public static String getProviderName(Context context) {
        TelephonyManager mTelephonyManager=(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        String imsi=mTelephonyManager.getSubscriberId();
        if(imsi != null) {
            String providerName="";
            if(imsi.startsWith("46000") || imsi.startsWith("46002")) {
                providerName="中国移动";
            } else if(imsi.startsWith("46001")) {
                providerName="中国联通";
            } else if(imsi.startsWith("46003")) {
                providerName="中国电信";
            }
            return providerName;
        }
        return "";
    }

    /**
     * 获取应用分发渠道
     * @param context
     * @return
     */
    public static String getChannel(Context context) {
        String appChannel="";
        try {
            ApplicationInfo appInfo=
                context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            appChannel=appInfo.metaData.getString("UMENG_CHANNEL");
            // LogUtil.i(TAG,appId.substring(3));
        } catch(NameNotFoundException e) {
            e.printStackTrace();
        }
        return appChannel;
    }

    /**
     * 打开拨号界面
     * @param number
     * @param context
     */
    public static void dial(String number, Context context) {
        Class<TelephonyManager> c=TelephonyManager.class;
        Method getITelephonyMethod=null;
        try {
            getITelephonyMethod=c.getDeclaredMethod("getITelephony", (Class[])null);
            getITelephonyMethod.setAccessible(true);
        } catch(SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch(NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            TelephonyManager tManager=(TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
            Object iTelephony;
            iTelephony=(Object)getITelephonyMethod.invoke(tManager, (Object[])null);
            Method dial=iTelephony.getClass().getDeclaredMethod("dial", String.class);
            dial.invoke(iTelephony, number);
        } catch(IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch(IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch(SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch(NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch(InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 获取屏幕分辨率
     * @param context
     * @return
     */
    public static String getScreen(Context context) {
        try {
            WindowManager manager=(WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
            Display display=manager.getDefaultDisplay();
            return display.getWidth() + "*" + display.getHeight();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * SDK版本大于Android 2.2
     * @return
     */
    public static boolean hasFroyo() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO;
    }

    /**
     * SDK版本大于Android 2.3
     * @return
     */
    public static boolean hasGingerbread() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
    }

    /**
     * SDK版本大于Android3.0
     * @return
     */
    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    /**
     * SDK版本大于Android3.1
     * @return
     */
    public static boolean hasHoneycombMR1() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1;
    }

    /**
     * SDK版本大于Android4.1
     * @return
     */
    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN;
    }
}
