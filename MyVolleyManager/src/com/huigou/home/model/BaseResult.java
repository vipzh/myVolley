package com.huigou.home.model;

/**
 * @ClassName: BaseResult
 * @Description: 返回带结果
 * @author zhanghao
 * @date 2016年4月26日 下午2:53:09
 */
public class BaseResult extends BaseModel {

    private static final long serialVersionUID=1L;

    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result=result;
    }

}
