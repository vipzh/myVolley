package com.app.core.util;

import com.app.core.configuration.MyApp;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

/**
 * ScreenUtils
 * <ul>
 * <strong>Convert between dp and sp</strong>
 * <li>{@link ScreenUtils#dpToPx(Context, float)}</li>
 * <li>{@link ScreenUtils#pxToDp(Context, float)}</li>
 * </ul>
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2014-2-14
 */
public class ScreenUtils {

    private ScreenUtils() {
        throw new AssertionError();
    }

    public static int dpToPx(Context context, float dp) {
        if(context == null) {
            return -1;
        }
        return (int)(dp * context.getResources().getDisplayMetrics().density);
    }

    public static int pxToDp(Context context, float px) {
        if(context == null) {
            return -1;
        }
        return (int)(px / context.getResources().getDisplayMetrics().density);
    }

    public static float dpToPxInt(Context context, float dp) {
        return (int)(dpToPx(context, dp) + 0.5f);
    }

    public static float pxToDpCeilInt(Context context, float px) {
        return (int)(pxToDp(context, px) + 0.5f);
    }

    /**
     * 获取屏幕宽高
     * @param activity
     * @return
     */
    public static int[] getScreenSize() {
        int[] screens;
        DisplayMetrics dm=new DisplayMetrics();
        dm=MyApp.getInstance().getResources().getDisplayMetrics();
        screens=new int[]{dm.widthPixels, dm.heightPixels};
        return screens;
    }

    public static float[] getBitmapConfiguration(Bitmap bitmap, ImageView imageView, float screenRadio) {
        int screenWidth=getScreenSize()[0];
        float rawWidth=0;
        float rawHeight=0;
        float width=0;
        float height=0;
        if(bitmap == null) {
            width=(float)(screenWidth / screenRadio);
            height=(float)width;
            imageView.setScaleType(ScaleType.FIT_XY);
        } else {
            rawWidth=bitmap.getWidth();
            rawHeight=bitmap.getHeight();
            if(rawHeight > 10 * rawWidth) {
                imageView.setScaleType(ScaleType.CENTER);
            } else {
                imageView.setScaleType(ScaleType.FIT_XY);
            }
            float radio=rawHeight / rawWidth;
            width=(float)(screenWidth / screenRadio);
            height=(float)(radio * width);
        }
        return new float[]{width, height};
    }
}
