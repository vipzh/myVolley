package com.huigou.home.fragment;

import android.view.View;

import com.alibaba.fastjson.JSON;
import com.app.core.fragment.BaseFragment;
import com.app.core.presenter.DataCallback;
import com.app.core.presenter.ParseData;
import com.app.core.presenter.ParseDataImpl;
import com.app.core.util.LogUtils;
import com.huigou.R;

public class HomeFragment extends BaseFragment implements DataCallback {
    

    @Override
    public void onClick(View arg0) {

    }

    @Override
    public String getFragmentName() {
        return HomeFragment.class.getSimpleName();
    }

    @Override
    protected void findViewbyId() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void process() {

    }

    @Override
    protected void getData() {

    }

    @Override
    public int setFragmentView() {
        return R.layout.fragment_loading;
    }

    @Override
    public void handleData(int tag, String data) {
        switch(tag) {
            case 0:
                break;
            case 1:
                break;
            default:
                break;
        }
    }

    @Override
    public void handleErrorMessage(int tag, String message) {
        switch(tag) {
            case 0:
                break;
            case 1:
                break;
            default:
                break;
        }

    }

}
