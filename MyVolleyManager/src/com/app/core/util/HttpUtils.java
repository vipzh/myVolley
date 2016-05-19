package com.app.core.util;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * @author ANXAINJIE
 */
public class HttpUtils {

    private static final String TAG="HttpUtils";

    private static final String KEY=
        "#8pi=F$So+E74799nOWS=NOXt,qQ@a%f.3V17D1)$kqdbuu7H#(Dv~mTMQ0d[dGwPBPVfNUZvty+35n~#9$61dn%l#cdQ$qh";

    // 创建请求json数据
    public static String createRequestJson(String action, JSONObject paramDatas) {

        String sign=null;
        String sort=null;
        String md5Encode=null;
        String encrypt=null;
        String reverse=null;

        JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("header", getHeader());// 添加header
            jsonObject.put("action", action);// 添加事件
            jsonObject.put("data", paramDatas);// 添加参数
        } catch(JSONException e) {
            e.printStackTrace();
        }

        // 字符串排序
        sort=AESTools.stringSort(String.valueOf(jsonObject));
        // md5加密
        md5Encode=AESTools.md5Encode(sort + KEY);
        // sha1算签名
        sign=AESTools.SHA1(KEY + md5Encode);

        // 重新组织jsonbject
        try {
            JSONObject object=jsonObject.getJSONObject("header");
            object.put("sign", sign);
            try {
                // des加密
                encrypt=AESTools.encrypt(String.valueOf(jsonObject));
            } catch(Exception e) {
                e.printStackTrace();
            }
            // 加密后字符串反转
            reverse=new StringBuilder(encrypt).reverse().toString();
            LogUtils.e(TAG, "String.valueOf(jsonObject)----:" + String.valueOf(jsonObject));
            return reverse;

        } catch(JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static JSONObject getHeader() {
        JSONObject header=new JSONObject();
        try {
            header.put("token", "06ddbf97784bc82b4cfeaa350ac83128");
            header.put("device_id", "1111");// 设备id
            header.put("client_type", "android--" + android.os.Build.MODEL + " " + android.os.Build.VERSION.RELEASE + "-->"
                + android.os.Build.VERSION.SDK_INT);// 客户端类型，
            header.put("channel_no", "wandoujia");// 通道号
            header.put("version", "v_3.3");// 当前版本
            header.put("push_id", "2222999888");
            header.put("client_time", DateFormatUtils.clientTime());

        } catch(JSONException e) {
            e.printStackTrace();
        }
        return header;
    }

}
