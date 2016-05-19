/**
 * Copyright 2013 Mani Selvaraj Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required
 * by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package com.android.volley.http;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.base.AuthFailureError;
import com.android.volley.base.DefaultRetryPolicy;
import com.android.volley.base.NetworkResponse;
import com.android.volley.base.ParseError;
import com.android.volley.base.Response;
import com.android.volley.base.RetryPolicy;
import com.android.volley.base.Response.ErrorListener;
import com.android.volley.base.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.app.core.util.AESTools;
import com.app.core.util.LogUtils;

/**
 * Custom Implementation of com.android.volley.toolbox.JsonObjectRequest to handle the headers.
 * @author Mani Selvaraj
 */
public class MyJsonObjectRequest extends JsonObjectRequest {

    private static final String TAG="MyJsonObjectRequest";

    private Map<String, String> headers=new HashMap<String, String>();

    private Priority priority=null;

    private String desEncrypt="";

    private String stringCut="";

    private String[] splits=null;

    private String newjson1="";

    private String newjson2="";

    public MyJsonObjectRequest(int method, String url, String jsonRequest, Listener<JSONObject> listener,
        ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return headers;
    }

    public void setHeader(String title, String content) {
        headers.put(title, content);
    }

    public void setPriority(Priority priority) {
        this.priority=priority;
    }

    /*
     * If prioirty set use it,else returned NORMAL
     * @see com.android.volley.Request#getPriority()
     */
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

    @SuppressWarnings("unused")
    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {

        try {
            String json=new String(response.data, "UTF-8");

            if(newjson1 != null) {
                newjson1=json.replaceAll("\n", "");
                newjson2=new StringBuilder(newjson1).reverse().toString();
            }

            try {
                // 解密
                if(AESTools.desEncrypt(newjson2).trim() != null) {
                    desEncrypt=AESTools.desEncrypt(newjson2).trim();
                }

                String key="faa8c21fc829a7b3d9150f00e91ffced";
                // 字符串分割
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

            return Response.success(new JSONObject(stringCut), HttpHeaderParser.parseCacheHeaders(response));
        } catch(UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch(JSONException je) {
            return Response.error(new ParseError(je));
        } catch(Exception np) {
            return Response.error(new ParseError(np));
        }
    }
}
