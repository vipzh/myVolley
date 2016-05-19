package com.app.core.fragment;

import com.app.core.configuration.MyApp;
import com.app.core.presenter.ParseData;
import com.app.core.presenter.ParseDataImpl;
import com.app.core.util.LogUtils;
import com.github.kevinsawicki.wishlist.ViewFinder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public abstract class BaseFragment extends FixedOnActivityResultBugFragment implements OnClickListener {

    private static final String TAG="BaseFragment";

    /** 上下文 **/
    protected Context fContext;

    /** 绑定这个activity布局的Finder **/
    protected ViewFinder finder;

    /** 网络参数 **/
    protected String params;

    protected Intent intent=new Intent();
    
    protected ParseData pData=new ParseDataImpl();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        LogUtils.i(getFragmentName(), "onAttach()");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fContext=MyApp.getInstance();
        LogUtils.i(getFragmentName(), "onCreate()");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtils.i(getFragmentName(), "onCreateView()");
        return inflater.inflate(setFragmentView(), null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        finder=new ViewFinder(view);
        findViewbyId();
        setListener();
        LogUtils.i(getFragmentName(), "onViewCreated()");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getData();
        process();
        LogUtils.i(getFragmentName(), "onActivityCreated()");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.i(getFragmentName(), "onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.i(getFragmentName(), "onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.i(getFragmentName(), "onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtils.i(getFragmentName(), "onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.i(getFragmentName(), "onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.i(getFragmentName(), "onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        LogUtils.i(getFragmentName(), "onDetach()");
    }

    /**
     * fragment name
     */
    public abstract String getFragmentName();

    /**
     * 加载布局
     */
    protected abstract void findViewbyId();

    /**
     * 设置监听
     */
    protected abstract void setListener();

    /**
     * 处理一般逻辑
     */
    protected abstract void process();

    /**
     * 获取数据（联网或者取本地数据库）
     */
    protected abstract void getData();

    /**
     * 加载布局
     */
    public abstract int setFragmentView();

}
