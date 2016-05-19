package com.app.core.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * @className: MyListAdapter
 * @description: listview工具类
 * @author: zhanghao
 * @date: 2015年5月5日 下午4:06:09
 */
public abstract class MyListAdapter<T> extends BaseAdapter {

    public static final String TAG="MyListAdapter";

    private List<T> list;

    protected Context context;

    protected Handler handler;

    public MyListAdapter(Context context) {
        init(context, new ArrayList<T>(), handler);
    }

    public MyListAdapter(Context context, List<T> list, Handler handler) {
        init(context, list, handler);
    }

    private void init(Context context, List<T> list, Handler handler) {
        this.list=list;
        this.context=context;
        this.handler=handler;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list=list;
    }

    public void clear() {
        this.list.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<T> list) {
        if(list != null) {
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(null == convertView) {
            convertView=inflate(getContentView());
        }
        onInitView(convertView, position);
        return convertView;
    }

    /** 加载布局 */
    private View inflate(int layoutResID) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(layoutResID, null);
        return view;
    }

    /** 布局适配 */
    public abstract int getContentView();

    /** 初始化布局 */
    public abstract void onInitView(View view, int position);

}
