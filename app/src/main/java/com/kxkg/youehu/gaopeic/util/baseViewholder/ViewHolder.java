package com.kxkg.youehu.gaopeic.util.baseViewholder;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 用于BaseAdapter封装类的Viewholder
 @author mc */

public class ViewHolder {
    private SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;

    public ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mViews = new SparseArray<View>();
        this.mPosition = position;
        this.mConvertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        this.mConvertView.setTag(this);
    }

    public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (null == convertView) {
            return new ViewHolder(context, parent, layoutId, position);
        } else {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.mPosition = position;

            return holder;
        }
    }

    public int getmPosition() {
        return mPosition;
    }

    public void setmPosition(int mPosition) {
        this.mPosition = mPosition;
    }

    public View getConvertView() {
        return mConvertView;
    }

    /** 通过ViewId获取控件
     *
     * @param viewId
     * @return */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);

        if (null == view) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }

        return (T) view;
    }

    /** 给ID为viewId的TextView设置文字text，并返回this
     *
     * @param viewId
     * @param text
     * @return */
    public ViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        if (TextUtils.isEmpty(text)) {
            tv.setText("");
            return this;
        }
        tv.setText(text);
        return this;
    }

    /** 给ID为viewId的ImageView设置 Resource，并返回this
     *
     * @param viewId
     * @param Resource
     * @return */
    public ViewHolder setImageResource(int viewId, int Resource) {
        ImageView imageView = getView(viewId);
        imageView.setImageResource(Resource);
        return this;
    }

}
