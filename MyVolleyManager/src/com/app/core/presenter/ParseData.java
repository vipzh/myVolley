package com.app.core.presenter;

/**
 * @ClassName: ParseData
 * @Description: 网络请求数据通用请求接口
 * @author zhanghao
 * @date 2016年4月26日 下午2:48:26
 */
public interface ParseData {

    /**
     * @MethodName: commonRequest
     * @Description: 请求实现方法
     * @param @param dataCallback 回调
     * @param @param tag 请求标识位
     * @param @param url 请求url
     * @param @param params 请求参数
     * @param @param isPost 是否是true为post请求, false为get请求
     * @return void 返回类型
     * @author zhanghao
     */
    void commonRequest(DataCallback dataCallback, int tag, String url, String params, boolean isPost);

}
