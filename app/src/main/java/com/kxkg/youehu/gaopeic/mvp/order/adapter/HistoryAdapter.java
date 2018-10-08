package com.kxkg.youehu.gaopeic.mvp.order.adapter;

import android.annotation.SuppressLint;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.kxkg.youehu.gaopeic.R;
import com.kxkg.youehu.gaopeic.entity.NurseInfo;
import com.kxkg.youehu.gaopeic.entity.OrderInfo;
import com.kxkg.youehu.gaopeic.util.GdpUtils;
import com.xiaochao.lcrapiddeveloplibrary.BaseQuickAdapter;
import com.xiaochao.lcrapiddeveloplibrary.BaseViewHolder;

import java.util.List;

/**
 * Created by yxs on 2018/9/18.
 */

public class HistoryAdapter extends BaseQuickAdapter<OrderInfo> {


    public HistoryAdapter(int layoutResId, List<OrderInfo> data) {
        super(layoutResId, data);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void convert(BaseViewHolder helper, OrderInfo item) {

        TextView status=helper.getView(R.id.status_his_tv);


        if (!StringUtils.isEmpty(item.getStartTime())){
           helper.setText(R.id.time_his_tv,"到岗时间："+ GdpUtils.deletHour(item.getStartTime()));
        }else {
            helper.setText(R.id.time_his_tv,"到岗时间：");
        }

        if (!StringUtils.isEmpty(item.getStartTime())&&!StringUtils.isEmpty(item.getEndTime())){
            helper.setText(R.id.days_his_tv,"" + GdpUtils.daysWork(item.getStartTime(),item.getEndTime()));
        }else {
            helper.setText(R.id.days_his_tv,"" );
        }

        if (!StringUtils.isEmpty(item.getContact())){
            helper.setText(R.id.name_his_tv,"病人姓名："+item.getContact());
        }else {
            helper.setText(R.id.name_his_tv,"病人姓名：");
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

        if (!StringUtils.isEmpty(item.getStatus())){
            switch (item.getStatus()){//1 待付费 2 待指派 3 待接单 4 待到岗 10 进行中 99 完结
                case "1":
                    status.setBackgroundResource(R.drawable.bg_state_pink);
                    helper.setTextColorRes(R.id.status_his_tv,R.color.bg_pink);
                    status.setText("待付费");
                    break;
                case "2":
                    status.setBackgroundResource(R.drawable.bg_state_pink);
                    helper.setTextColorRes(R.id.status_his_tv,R.color.bg_pink);
                    status.setText("待指派");
                    break;

                case "3":
                    status.setBackgroundResource(R.drawable.bg_state_pink);
                    helper.setTextColorRes(R.id.status_his_tv,R.color.bg_pink);
                    status.setText("待接单");
                    break;
                case "4":
                    status.setBackgroundResource(R.drawable.bg_state_pink);
                    helper.setTextColorRes(R.id.status_his_tv,R.color.bg_pink);
                    status.setText("待到岗");
                    break;
                case "10":
                    status.setBackgroundResource(R.drawable.bg_state_pink);
                    helper.setTextColorRes(R.id.status_his_tv,R.color.bg_pink);
                    status.setText("进行中");
                    break;
                case "99":
                    status.setBackgroundResource(R.drawable.bg_btn_gray);
                    helper.setTextColorRes(R.id.status_his_tv,R.color.white);
                    status.setText("已完结");
                    break;
            }
        }
    }
}
