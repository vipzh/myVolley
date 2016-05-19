package com.huigou.home.activity;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.alibaba.fastjson.JSON;
import com.app.core.activity.BaseActivity;
import com.app.core.configuration.Constants;
import com.app.core.configuration.HttpApi;
import com.app.core.configuration.ParamUtils;
import com.app.core.presenter.DataCallback;
import com.app.core.presenter.ParseData;
import com.app.core.presenter.ParseDataImpl;
import com.app.core.util.ActivitiesManager;
import com.app.core.util.HttpUtils;
import com.app.core.util.LogUtils;
import com.huigou.R;
import com.huigou.home.fragment.HomeFragment;
import com.huigou.home.model.OkoerDetails;

import android.R.fraction;
import android.R.string;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

public class MainActivity extends BaseActivity implements DataCallback {

    private TextView tv_text;

    private String username="tester";

    private String password="q11111";
    
    private String mobile="13260097242";
    private String mobile2="13260097242";

    @Override
    protected void findViewbyId() {
        tv_text=finder.find(R.id.tv_text);

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void process() {

    }

    @Override
    protected void getData() {
        // get请求
//        pData.commonRequest(this, 0, Constants.HTTPS_URL + HttpApi.SMSCAPTCHA + ParamUtils.SmsCaptcha(mobile), null, false);
        // post请求
//        pData.commonRequest(this, 1, Constants.HTTPS_URL + HttpApi.LOGIN, ParamUtils.Login(username, password), true);
//        pData.commonRequest(this, 0, Constants.HTTP_URL + HttpApi.OKOERLIST+ ParamUtils.OkoerList(1, 10, null),null, false);
        
        pData.commonRequest(this, 1, Constants.HTTP_URL + HttpApi.getOkoerDetails(360),null, false);
        
        
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public String getActiviyName() {

        return MainActivity.class.getSimpleName();
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void handleData(int tag, String response) {
        switch(tag) {
            case 0:
                tv_text.setText(response);
                LogUtils.e(TAG, "response1:" + response);
                break;
            case 1:
//                tv_text.setText(okoerDetails.getRet_code());
                LogUtils.e(TAG, "response2:" + response);
                try {
                    OkoerDetails okoerDetails=JSON.parseObject(response, OkoerDetails.class);
                    String report_lead=okoerDetails.getReport_lead();
                    tv_text.setMovementMethod(ScrollingMovementMethod.getInstance());
                    tv_text.setText(Html.fromHtml(report_lead));
//                    List<Integer> relateds=okoerDetails.getRelateds();
//                    for(int i=0; i < relateds.size(); i++) {
//                        LogUtils.e(TAG, "response2:" + relateds.get(i));
//                        tv_text.setText(relateds.get(i)+"");
//                    }
                    
                } catch(Exception e) {
                   e.printStackTrace();
                }
    
                break;
            default:
                break;
        }
    }

    @Override
    public void handleErrorMessage(int tag, String message) {
        switch(tag) {
            case 0:
                tv_text.setText(message);
                LogUtils.e(TAG, "message1:" + message);
                break;
            case 1:
                tv_text.setText(message);
                LogUtils.e(TAG, "message2:" + message);
                break;
            default:
                break;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) { // 按下的如果是BACK，同时没有重复
            ActivitiesManager.getInstance().popAllActivities();
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

}
