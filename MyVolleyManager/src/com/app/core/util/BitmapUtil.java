package com.app.core.util;

import java.io.ByteArrayOutputStream;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * Bitmap工具类
 * @author adison
 */
public class BitmapUtil {

    /**
     * 从资源中获取Bitmap
     * @param act
     * @param resId
     * @return
     */
    public static Bitmap getBitmapFromResources(Activity act, int resId) {
        Resources res=act.getResources();
        Bitmap bitmap=BitmapFactory.decodeResource(res, resId);
        return BitmapFactory.decodeResource(res, resId);
    }

    /**
     * byte[] → Bitmap
     * @param b
     * @return
     */
    public static Bitmap convertBytes2Bimap(byte[] b) {
        if(b.length == 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(b, 0, b.length);
    }

    /**
     * Bitmap → byte[]
     * @param bm
     * @return
     */
    public static byte[] convertBitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    /**
     * 1)Drawable → Bitmap
     * @param drawable
     * @return
     */
    public static Bitmap convertDrawable2BitmapByCanvas(Drawable drawable) {
        Bitmap bitmap=
            Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas=new Canvas(bitmap);
        // canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * 2)Drawable → Bitmap
     * @param drawable
     * @return
     */
    public static Bitmap convertDrawable2BitmapSimple(Drawable drawable) {
        BitmapDrawable bd=(BitmapDrawable)drawable;
        return bd.getBitmap();
    }

    /**
     * Bitmap → Drawable
     * @param bitmap
     * @return
     */
    public static Drawable convertBitmap2Drawable(Bitmap bitmap) {
        BitmapDrawable bd=new BitmapDrawable(bitmap);
        // 因为BtimapDrawable是Drawable的子类，最终直接使用bd对象即可。
        return bd;
    }
}
