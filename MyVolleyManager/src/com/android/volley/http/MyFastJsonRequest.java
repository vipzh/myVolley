package com.android.volley.http;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import android.util.Base64;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.android.volley.base.AuthFailureError;
import com.android.volley.base.DefaultRetryPolicy;
import com.android.volley.base.NetworkResponse;
import com.android.volley.base.ParseError;
import com.android.volley.base.Request;
import com.android.volley.base.Response;
import com.android.volley.base.RetryPolicy;
import com.android.volley.base.Response.ErrorListener;
import com.android.volley.base.Response.Listener;
import com.android.volley.base.VolleyError;
import com.android.volley.base.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.app.core.util.AESTools;
import com.app.core.util.LogUtils;

/**
 * 封装网络工具类
 * @ClassName: MyFastJsonRequest
 * @Description: TODO(fastjson解析bean)
 * @author zhanghao
 * @date 2014-8-12 下午2:48:59
 * @param <T>
 */
public class MyFastJsonRequest<T> extends Request<T> {

    private final Class<T> mClazz;

    private final Listener<T> mListener;

    /** Charset for request. */
    private static final String PROTOCOL_CHARSET="utf-8";

    /** Content type for request. */
    private static final String PROTOCOL_CONTENT_TYPE=String.format("application/json; charset=%s", PROTOCOL_CHARSET);

    private static final String TAG="MyFastJsonRequest";

    private final String mRequestBody;

    private Priority priority=null;

    private Map<String, String> headers=new HashMap<String, String>();

    private String desEncrypt=null;

    private String stringCut=null;

    private String[] splits=null;

    private String newjson1=null;

    private String newjson2=null;

    public MyFastJsonRequest(int method, String url, String RequestBody, Class<T> clazz, Listener<T> listener,
        ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mClazz=clazz;
        this.mListener=listener;
        this.mRequestBody=RequestBody;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {

        try {
            String json=new String(response.data, "UTF-8");

            // LogUtil.i(TAG, "json:" + json);

            if(json != null) {
                newjson1=json.replaceAll("\n", "");
            }

            if(newjson1 != null) {
                newjson2=new StringBuilder(newjson1).reverse().toString();
            }

            try {
                // 解密
                if(AESTools.desEncrypt(newjson2).trim() != null) {
                    desEncrypt=AESTools.desEncrypt(newjson2).trim();
                }

                // 字符串分割
                String key="faa8c21fc829a7b3d9150f00e91ffced";

                if(desEncrypt.split(key) != null) {
                    splits=desEncrypt.split(key);
                }

                if(splits[1] != null) {
                    stringCut=splits[1];
                    LogUtils.i(TAG, "stringCut:" + stringCut);
                }

            } catch(Exception e) {
                e.printStackTrace();
            }

            return Response.success(JSON.parseObject(stringCut, mClazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch(UnsupportedEncodingException ue) {
            return Response.error(new ParseError(ue));
        } catch(JSONException je) {
            return Response.error(new ParseError(je));
        } catch(Exception ep) {
            return Response.error(new ParseError(ep));
        }
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        if(null != headers) {
            return headers;
        }
        return super.getParams();
    }

    @Override
    protected void deliverResponse(T response) {
        if(mListener != null) {
            mListener.onResponse(response);
        }
    }

    @Override
    public void deliverError(VolleyError error) {
        super.deliverError(error);
    }

    @Override
    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }

    public Priority getPriority() {
        if(this.priority != null) {
            return priority;
        } else {
            return Priority.NORMAL;
        }
    }

    @Override
    public RetryPolicy getRetryPolicy() {
        RetryPolicy retryPolicy=new DefaultRetryPolicy(5000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        return retryPolicy;
    }

    public void setPriority(Priority priority) {
        this.priority=priority;
    }

    @Override
    public byte[] getBody() {
        try {
            return mRequestBody == null ? null : mRequestBody.getBytes(PROTOCOL_CHARSET);
        } catch(UnsupportedEncodingException uee) {
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, PROTOCOL_CHARSET);
            return null;
        }
    }

}
