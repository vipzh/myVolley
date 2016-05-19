package com.app.core.presenter;

/**
 * 回调接口，用于对返回数据的处理
 * @author zh
 */
public interface DataCallback {

    void handleData(int tag, String response);

    void handleErrorMessage(int tag, String message);

}
