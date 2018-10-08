package com.kxkg.youehu.developlib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.kxkg.youehu.developlib.R;

import java.util.List;


/**
 * 用途：
 *
 * @version V1.0
 * @FileName: com.woosiyuan.atie.adapter.dialog.DialogSingleChoiceAdapter.java
 * @author: ycj
 * @date: 2016-09-13 16:46
 */
public class DialogSingleChoiceAdapter extends BaseAdapter {
    private List<String> data;
    private LayoutInflater    mInflater;

    public DialogSingleChoiceAdapter(List<String> data, Context context) {
        this.data = data;
        mInflater = LayoutInflater.from(context);
    }

    @Override public int getCount() {
        if (data == null) {
            return 0;
        }
        return data.size();
    }

    @Override public Object getItem(int position) {
        return data.get(position);
    }

    @Override public long getItemId(int position) {
        return position;
    }

    @Override public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.listitem_dialog_single_choice, null);
            holder.content = (TextView) convertView.findViewById(R.id.content_alertdialog);
            holder.icon = (ImageView) convertView.findViewById(R.id.icon_alertdialog);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.content.setText(data.get(position));
        return convertView;
    }

    class ViewHolder {
        public TextView  content;
        public ImageView icon;
    }
}
