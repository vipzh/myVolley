package com.app.core.configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;

import com.app.core.util.AESTools;
import com.app.core.util.DateFormatUtils;
import com.app.core.util.DeviceUtil;
import com.app.core.util.LogUtils;

public class ParamUtils {

    private static final String TAG="ParamUtils";

    public static String app_secret="8b302b35903667b5bdbd8556b480240f";
    
    
    /**
     * 
     * @MethodName: OkoerList
     * @Description: 恪闻-获取列表 
     * @param  @param page
     * @param  @param page_rows
     * @param  @param key_word
     * @param  @return
     * @return String    返回类型
     * @author zhanghao
     */
    public static String OkoerList(int page,int page_rows,String key_word){
        StringBuilder sb=new StringBuilder();
       
        if(null==key_word) {
            sb.append("page=" + page + "&");
            sb.append("page_rows=" + page_rows);
        }else {
            sb.append("page=" + page + "&");
            sb.append("page_rows=" + page_rows+"&");
            sb.append("page_rows=" + key_word);
        }
        
        return sb.toString();
        
    }
    
    
    

    /**
     * @MethodName: Login
     * @Description: 用户登陆
     * @param @param username
     * @param @param password
     * @param @return
     * @return String 返回类型
     * @author zhanghao
     */
    public static String Login(String username, String password) {
        StringBuilder sb=new StringBuilder();
        sb.append("username=" + username + "&");
        sb.append("password=" + password + "&");
        sb.append("imei=" + "1122334455667766" + "&");
        sb.append("os=" + "android");

        return sb.toString();
    }

    /**
     * @MethodName: SmsCaptcha
     * @Description: 获取短信验证码
     * @param @param username
     * @param @return
     * @return String 返回类型
     * @author zhanghao
     */
    public static String SmsCaptcha(String username) {
        StringBuilder sb=new StringBuilder();
        sb.append("mobile=" + username + "&");
        sb.append("timestamp=" + DateFormatUtils.getTimeStamp() + "&");
        sb.append("sign_code=" + AESTools.md5Encode(username + DateFormatUtils.getTimeStamp() + app_secret));

        return sb.toString();
    }

    // private static void setUserUid(JSONObject paramDatas) throws JSONException {
    // if(App.currentUserBean != null) {
    // paramDatas.put("uid", App.currentUserBean.uid);
    // } else {
    // paramDatas.put("uid", 0);
    // }
    //
    //
    // }

}
