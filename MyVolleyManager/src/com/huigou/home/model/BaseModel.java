package com.huigou.home.model;

import java.io.Serializable;

/**
 * 
 * @ClassName: BaseModel
 * @Description: 只返回状态码和消息
 * @author zhanghao
 * @date 2016年4月26日 下午2:52:23
 *
 */
public class BaseModel implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer ret_code;

    private String err_msg;

    public Integer getRet_code() {
        return ret_code;
    }

    public void setRet_code(Integer ret_code) {
        this.ret_code=ret_code;
    }

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg=err_msg;
    }

}
