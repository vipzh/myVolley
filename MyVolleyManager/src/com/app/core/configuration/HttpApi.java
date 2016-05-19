package com.app.core.configuration;

/**
 * 
 * @ClassName: HttpApi
 * @Description: 接口后缀
 * @author zhanghao
 * @date 2016年4月28日 下午2:51:33
 *
 */
public class HttpApi {
    
    /**用户登陆**/
    public static final String LOGIN="/v1/user/login.json";
    /**用户-获取短信验证码**/
    public static final String SMSCAPTCHA="/v1/user/sms_captcha.json?";
    /**恪闻-获取列表 **/
    public static final String OKOERLIST="/v1/report/list.json?";
    /**恪闻-获取单个详情**/
    public static final String getOkoerDetails(int nid){
        return "/v1/report/"+nid+".json";
    }
    
    

}
