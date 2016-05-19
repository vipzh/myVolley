package com.android.volley.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;

import com.android.volley.base.AuthFailureError;
import com.android.volley.base.NetworkResponse;
import com.android.volley.base.Request;
import com.android.volley.base.Response;
import com.android.volley.base.Response.ErrorListener;
import com.android.volley.base.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.app.core.util.LogUtils;

/**
 * ByteArrayRequest override getBody() and getParams()
 * @author steven pan
 */
class ByteArrayRequest extends Request<NetworkResponse> {

    private static final String TAG="ByteArrayRequest";

    private final Listener<NetworkResponse> mListener;

    private Object mPostBody=null;

    private HttpEntity httpEntity=null;

    public ByteArrayRequest(int method, String url, Object postBody, Listener<NetworkResponse> listener, ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mPostBody=postBody;
        this.mListener=listener;

        if(this.mPostBody != null && this.mPostBody instanceof RequestMap) {// contains file
            this.httpEntity=((RequestMap)this.mPostBody).getEntity();
        }
    }

    /**
     * mPostBody is null or Map<String, String>, then execute this method
     */
    @SuppressWarnings("unchecked")
    protected Map<String, String> getParams() throws AuthFailureError {
        if(this.httpEntity == null && this.mPostBody != null && this.mPostBody instanceof Map<?, ?>) {
            return((Map<String, String>)this.mPostBody);// common Map<String, String>
        }
        return null;// process as json, xml or MultipartRequestParams
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers=super.getHeaders();
        if(null == headers || headers.equals(Collections.emptyMap())) {
            headers=new HashMap<String, String>();
        }
        return headers;
    }

    @Override
    public String getBodyContentType() {
        if(httpEntity != null) {
            return httpEntity.getContentType().getValue();
        }
        return null;
        
//        return String.format("application/x-www-form-urlencoded; charset=%s", "utf-8");
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        if(this.mPostBody != null && this.mPostBody instanceof String) {// process as json or xml
            String postString=(String)mPostBody;
            LogUtils.e(TAG, "postString:"+postString);
            if(postString.length() != 0) {
                try {
                    return postString.getBytes("UTF-8");
                } catch(UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else {
                return null;
            }
        }
        if(this.httpEntity != null) {// process as MultipartRequestParams
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            try {
                httpEntity.writeTo(baos);
            } catch(IOException e) {
                e.printStackTrace();
                return null;
            }
            return baos.toByteArray();
        }
        return super.getBody();// mPostBody is null or Map<String, String>
    }

    @Override
    protected Response<NetworkResponse> parseNetworkResponse(NetworkResponse response) {
        return Response.success(response, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(NetworkResponse response) {
        this.mListener.onResponse(response);
    }

}