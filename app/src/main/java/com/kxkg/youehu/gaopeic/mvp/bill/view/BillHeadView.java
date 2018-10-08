package com.kxkg.youehu.gaopeic.mvp.bill.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.kxkg.youehu.gaopeic.R;
import com.kxkg.youehu.gaopeic.entity.OrderInfo;
import com.kxkg.youehu.gaopeic.entity.WageBean;

import java.util.List;

/**
 * Created by yxs on 2018/9/19.
 */

public class BillHeadView extends LinearLayout {

    private TextView monthTv;
    private TextView moneyTv;
    private TextView totalTv;
    private TextView numberTv;
    private TextView percentTv;


    private OnClickListener onClickListener;
    private WageBean wageBean;
    private Context context;
    private String time;


    public BillHeadView(Context context) {
        super(context);
        initView(context);
    }

    public BillHeadView(Context context, OnClickListener onClickListener,WageBean wageBean,String time) {
        super(context);
        this.onClickListener = onClickListener;
        this.wageBean = wageBean;
        this.time = time;
        initView(context);
    }



    private void initView(Context context) {
        this.context=context;
        LayoutInflater.from(context).inflate(R.layout.view_bill_head,this,true);
        findbyId();
        initInfoView(wageBean);
        initListener();

    }


    private void initListener() {
        monthTv.setOnClickListener(onClickListener);
    }

    public void initInfoView(WageBean wageBean) {
        monthTv.setText(time);

        if (wageBean!=null&&wageBean.getData()!=null) {

            if (!StringUtils.isEmpty(wageBean.getData().getTotal())){
                moneyTv.setText(wageBean.getData().getTotal());
                totalTv.setText(wageBean.getData().getTotal());
            }
            if (wageBean.getData().getRecord()!=null){
                numberTv.setText(wageBean.getData().getRecord().size());
            }
            if (!StringUtils.isEmpty(wageBean.getData().getRate())){
                percentTv.setText(Double.parseDouble(wageBean.getData().getRate())*100+"%");
            }
        }
    }

    public void setTime(String time){
        monthTv.setText(time);
    }

    private void findbyId() {
        monthTv=findViewById(R.id.time_bill_tv);
        moneyTv=findViewById(R.id.money_bill_tv);
        totalTv=findViewById(R.id.total_bill_tv);
        numberTv=findViewById(R.id.number_bill_tv);
        percentTv=findViewById(R.id.percent_bill_tv);
    }


}
