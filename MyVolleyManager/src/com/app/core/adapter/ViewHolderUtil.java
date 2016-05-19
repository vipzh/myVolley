package com.app.core.adapter;

import android.util.SparseArray;
import android.view.View;

/**
 * @className: ViewHolderUtil
 * @description: ViewHolder的优化
 * @author: Administrator
 * @date: 2015年5月4日 下午6:24:56
 */
public class ViewHolderUtil {

    @SuppressWarnings("unchecked")
    public static <T extends View> T getHolderView(View view, int id) {
        SparseArray<View> viewHolder=(SparseArray<View>)view.getTag();
        View childView=null;

        if(viewHolder == null) {
            viewHolder=new SparseArray<View>();
            view.setTag(viewHolder);
            // 先创建的ViewHolder自然没有缓存View所以不用判断
            childView=view.findViewById(id);
            viewHolder.put(id, childView);
        } else {
            childView=viewHolder.get(id);
            if(childView == null) {
                childView=view.findViewById(id);
                viewHolder.put(id, childView);
            }
        }
        return (T)childView;
    }

    // public static <T extends View> T getHolderView(View view, int id) {
    // @SuppressWarnings("unchecked")
    // SparseArray<View> viewHolder=(SparseArray<View>)view.getTag();
    // if(viewHolder == null) {
    // viewHolder=new SparseArray<View>();
    // view.setTag(viewHolder);
    // }
    // View childView=viewHolder.get(id);
    // if(childView == null) {
    // childView=view.findViewById(id);
    // viewHolder.put(id, childView);
    // }
    // return (T)childView;
    // }
}
