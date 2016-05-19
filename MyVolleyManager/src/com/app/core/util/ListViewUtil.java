package com.app.core.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListViewUtil {

    /**
     * @description: TODO 解决ScrollView和listview冲突问题
     * @author: zhanghao
     * @date: 2014-11-5 下午5:15:05
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        // 如果没有设置数据适配器，则ListView没有子项，返回。
        ListAdapter listAdapter=listView.getAdapter();
        int totalHeight=0;
        if(listAdapter == null) {
            return;
        }
        for(int i=0, len=listAdapter.getCount(); i < len; i++) {
            View listViewItem=listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listViewItem.measure(0, 0);
            // 计算所有子项的高度和
            totalHeight+=listViewItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params=listView.getLayoutParams();
        // listView.getDividerHeight()获取子项间分隔符的高度
        // params.height设置ListView完全显示需要的高度
        params.height=totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
