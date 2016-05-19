package com.app.core.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * @author adison APK相关功能帮助器类
 */
public class ApkUtil {

    private static final String TAG="ApkUtil";

    /**
     * 判断APK包是否已经安装
     * @param context 上下文，一般为Activity
     * @param packageName 包名
     * @return 包存在则返回true，否则返回false
     */
    public static boolean isPackageExists(Context context, String packageName) {
        if(null == packageName || "".equals(packageName)) {
            throw new IllegalArgumentException("Package name cannot be null or empty !");
        }
        try {
            ApplicationInfo info=
                context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return null != info;
        } catch(NameNotFoundException e) {
            return false;
        }
    }

    /**
     * 安装指定APK文件
     * @param activity Activity
     * @param apkFile APK文件对象
     */
    public static void install(Activity activity, File apkFile) {
        Intent intent=new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        activity.startActivity(intent);
    }

    /**
     * 启动一个指定包名的应用的默认Activity
     * @param activity Activity
     * @param packageName 包名
     */
    public static void launch(Activity activity, String packageName) {
        Intent intent=activity.getPackageManager().getLaunchIntentForPackage(packageName);
        if(null != intent) {
            activity.startActivity(intent);
        }
    }

    public static void StartApp(Context context, String packageName) {
        Intent intent=context.getPackageManager().getLaunchIntentForPackage(packageName);
        if(null != intent) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

    /**
     * 获取APK包信息
     * @param context 上下文，一般为Activity
     * @param packageName 包名
     * @return 包存在则返回true，否则返回false
     */
    public static PackageInfo getPackageInfo(Context context, String packageName) {
        if(null == packageName || "".equals(packageName)) {
            throw new IllegalArgumentException("Package name cannot be null or empty !");
        }
        try {
            PackageInfo info=context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return info;
        } catch(NameNotFoundException e) {
            return null;
        }
    }

    /**
     * 修改apk文件权限
     * @param permission
     * @param path
     */
    public static void chmod(String permission, String path) {
        try {
            String command="chmod " + permission + " " + path;
            Runtime runtime=Runtime.getRuntime();
            runtime.exec(command);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static String channel=null;

    /**
     * 获取渠道号
     * @param context
     * @return
     */
    public static String getChannel(Context context) {
        if(channel == null) {
            try {
                ApplicationInfo applicationInfo=
                    context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                channel=applicationInfo.metaData.getString("UMENG_CHANNEL");
                if(channel == null) {
                    channel=String.valueOf(applicationInfo.metaData.getInt("UMENG_CHANNEL"));
                }
                return channel;
            } catch(NameNotFoundException e) {
            }
        }
        return channel;
    }

    // 获取包id
    public static String getPackageId(Context context) {
        String packageId=null;
        try {
            ApplicationInfo applicationInfo=
                context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            packageId=applicationInfo.metaData.getString("PACKAGE_ID");
            if(packageId == null) {
                packageId=String.valueOf(applicationInfo.metaData.getInt("PACKAGE_ID"));
            }
            return packageId;
        } catch(NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageId;
    }

    /**
     * @description: 通过包名启动一个应用
     * @author: zhanghao
     * @date: 2015年6月15日 上午11:21:46
     * @param context
     * @param packagename
     */
    private void doStartApplicationWithPackageName(Context context, String packagename) {

        // 通过包名获取此APP详细信息，包括Activities、services、versioncode、name等等
        PackageInfo packageinfo=null;
        try {
            packageinfo=context.getPackageManager().getPackageInfo(packagename, 0);
        } catch(NameNotFoundException e) {
            e.printStackTrace();
        }
        if(packageinfo == null) {
            return;
        }

        // 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent
        Intent resolveIntent=new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(packageinfo.packageName);

        // 通过getPackageManager()的queryIntentActivities方法遍历
        List<ResolveInfo> resolveinfoList=context.getPackageManager().queryIntentActivities(resolveIntent, 0);

        ResolveInfo resolveinfo=resolveinfoList.iterator().next();
        if(resolveinfo != null) {
            // packagename = 参数packname
            String packageName=resolveinfo.activityInfo.packageName;
            // 这个就是我们要找的该APP的LAUNCHER的Activity[组织形式：packagename.mainActivityname]
            String className=resolveinfo.activityInfo.name;
            // LAUNCHER Intent
            Intent intent=new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);

            // 设置ComponentName参数1:packagename参数2:MainActivity路径
            ComponentName cn=new ComponentName(packageName, className);

            intent.setComponent(cn);
            context.startActivity(intent);
        }
    }

    /**
     * check the application process name if process name is not qualified, then we think it is a service process and we will not
     * init SDK
     * @param pID
     * @return
     */
    public static String getAppName(Context appContext, int pID) {
        String processName=null;
        ActivityManager am=(ActivityManager)appContext.getSystemService(Context.ACTIVITY_SERVICE);
        List l=am.getRunningAppProcesses();
        Iterator i=l.iterator();
        PackageManager pm=appContext.getPackageManager();
        while(i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info=(ActivityManager.RunningAppProcessInfo)(i.next());
            try {
                if(info.pid == pID) {
                    CharSequence c=pm.getApplicationLabel(pm.getApplicationInfo(info.processName, PackageManager.GET_META_DATA));
                    // Log.d("Process", "Id: "+ info.pid +" ProcessName: "+
                    // info.processName +"  Label: "+c.toString());
                    // processName = c.toString();
                    processName=info.processName;
                    return processName;
                }
            } catch(Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }
}
