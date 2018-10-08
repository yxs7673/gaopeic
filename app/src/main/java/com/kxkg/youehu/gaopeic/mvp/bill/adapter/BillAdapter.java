package com.kxkg.youehu.gaopeic.mvp.bill.adapter;

import android.content.Context;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.kxkg.youehu.gaopeic.R;
import com.kxkg.youehu.gaopeic.entity.OrderTendsBean;
import com.kxkg.youehu.gaopeic.entity.WageBean;
import com.kxkg.youehu.gaopeic.util.GdpUtils;
import com.kxkg.youehu.gaopeic.util.baseViewholder.CommonAdapter;
import com.kxkg.youehu.gaopeic.util.baseViewholder.ViewHolder;


import java.util.List;

/**
 * Created by yxs on 2018/9/21.
 */

public class BillAdapter extends CommonAdapter<OrderTendsBean> {


    public BillAdapter(Context context, List<OrderTendsBean> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(ViewHolder helper, OrderTendsBean item) {

        TextView status=helper.getView(R.id.status_his_tv);


        if (!StringUtils.isEmpty(item.getStartTime())){
            helper.setText(R.id.time_his_tv,"到岗时间："+ GdpUtils.deletHour(item.getStartTime()));
        }else {
            helper.setText(R.id.time_his_tv,"到岗时间：");
        }


        if (!StringUtils.isEmpty(item.getContact())){
            helper.setText(R.id.name_his_tv,"病人姓名："+item.getContact());
        }else {
            helper.setText(R.id.name_his_tv,"病人姓名：");
        }

        if (!StringUtils.isEmpty(item.getDays())){
            helper.setText(R.id.days_his_tv,item.getDays());
        }else {
            helper.setText(R.id.days_his_tv,"");
        }

        if (!StringUtils.isEmpty(item.getAddress())){
            helper.setText(R.id.address_his_tv,"服务地址："+item.getAddress());
        }else {
            helper.setText(R.id.address_his_tv,"服务地址：");
        }

        if (!StringUtils.isEmpty(item.getPrice())){
            helper.setText(R.id.price_his_tv,item.getPrice());
        }else {
            helper.setText(R.id.price_his_tv,"");
        }


    }
}
