package com.app.core.presenter;

import java.util.Map;

import org.json.JSONObject;

import android.util.Log;

import com.android.volley.base.Request;
import com.android.volley.base.Request.Method;
import com.android.volley.base.Response.ErrorListener;
import com.android.volley.base.Response.Listener;
import com.android.volley.base.VolleyError;
import com.android.volley.http.MyFastJsonRequest;
import com.android.volley.http.MyJsonObjectRequest;
import com.android.volley.http.RequestManager;
import com.android.volley.http.RequestManager.RequestListener;
import com.app.core.configuration.Constants;
import com.app.core.util.LogUtils;
import com.huigou.home.model.BaseModel;

/**
 * @ClassName: ParseDataImpl
 * @Description: 网络请求数据通用请求实现类
 * @author zhanghao
 * @date 2016年4月26日 下午2:47:49
 */
public class ParseDataImpl implements ParseData {

    protected static final String TAG="ParseDataImpl";

    @Override
    public void commonRequest(final DataCallback dataCallback, int tag, String url, String params, boolean isPost) {

        LogUtils.e(TAG, "url:" + url);

        if(isPost) {
            // post请求
            RequestManager.getInstance().post(url, params, new RequestListener() {

                @Override
                public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {

                    LogUtils.e(TAG, "onSuccessPost");
                    dataCallback.handleData(actionId, response);

                }

                @Override
                public void onRequest() {

                }

                @Override
                public void onError(String errorMsg, String url, int actionId) {
                    LogUtils.e(TAG, "onErrorPost");
                    dataCallback.handleErrorMessage(actionId, errorMsg);

                }

            }, tag);
        } else {
            // get请求
            RequestManager.getInstance().get(url, new RequestListener() {

                @Override
                public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {

                    LogUtils.e(TAG, "onSuccessGet");
                    dataCallback.handleData(actionId, response);

                }

                @Override
                public void onRequest() {

                }

                @Override
                public void onError(String errorMsg, String url, int actionId) {
                    LogUtils.e(TAG, "onErrorGet");
                    dataCallback.handleErrorMessage(actionId, errorMsg);

                }

            }, tag);
        }

    }

}
