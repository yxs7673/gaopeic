package com.kxkg.youehu.gaopeic.mvp.bill.fragment;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.kxkg.youehu.developlib.base.BaseActivity;
import com.kxkg.youehu.developlib.base.BaseFragment;
import com.kxkg.youehu.developlib.util.ActivityStack;
import com.kxkg.youehu.gaopeic.R;
import com.kxkg.youehu.gaopeic.entity.OrderTendsBean;
import com.kxkg.youehu.gaopeic.entity.WageBean;
import com.kxkg.youehu.gaopeic.mvp.base.BaseView;
import com.kxkg.youehu.gaopeic.mvp.bill.adapter.BillAdapter;
import com.kxkg.youehu.gaopeic.mvp.bill.presenter.WageListPresenter;
import com.kxkg.youehu.gaopeic.mvp.bill.view.BillHeadView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

/**
 * Created by yxs on 2018/8/27.
 */


public class BillFragment extends BaseFragment {


    @BindView(R.id.bill_listview)
    ListView billListview;
    @BindView(R.id.bikk_swip)
    SwipeRefreshLayout bikkSwip;
    @BindView(R.id.bill_empty_ll)
    LinearLayout billEmptyLl;
    private WageListPresenter wageListPresenter;
    private BillAdapter adapter;
    private BillHeadView billHeadView;
    private WageBean wageBean;
    private List<OrderTendsBean> orderTendsList;
    private String time = "2018-09";
    private HashMap<String, String> map = new HashMap<>();
    List<String> timelist;


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, boolean b) {
        return inflater.inflate(R.layout.fragment_bill, container, false);
    }

    @Override
    protected void processLogic() {
        getMouth();
        wageListPresenter = new WageListPresenter(wageView, getContext());
        orderTendsList = new ArrayList<>();
        adapter = new BillAdapter(getContext(), orderTendsList, R.layout.item_history_list);
        billHeadView = new BillHeadView(getContext(), onClickListener, wageBean, time);
        billListview.addHeaderView(billHeadView);
        billListview.setAdapter(adapter);
    }

    private void getMouth() {

        timelist = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date date = calendar.getTime();//获取当前年月
        time = format.format(date);
        timelist.add(format.format(date));
        for (int i = 0; i < 6; i++) {
            calendar.add(Calendar.MONTH, -1);//每次月数减一，如果需要当前月份以后的就填1
            date = calendar.getTime();
            timelist.add(format.format(date));
        }

    }

    @Override
    protected void initListener() {
        bikkSwip.setColorSchemeColors(Color.parseColor("#FF4081"), Color.parseColor("#303F9F"));
        bikkSwip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                map.clear();
                map.put("month", time);
                wageListPresenter.loadData(map);
            }
        });
    }

    @Override
    protected void initData() {
        map.clear();
        map.put("month", time);
        wageListPresenter.loadData(map);

    }


    private BaseView<WageBean> wageView = new BaseView<WageBean>() {
        @Override
        public void onSuccess(WageBean data) {
            if (bikkSwip.isRefreshing()) {
                bikkSwip.setRefreshing(false);
            }

            orderTendsList.clear();
            if (data.getData() != null && data.getData().getRecord() != null && data.getData().getRecord().size() > 0) {
                List<OrderTendsBean> list = data.getData().getRecord();
                orderTendsList.addAll(list);
                billHeadView.initInfoView(data);
            }

            if (orderTendsList.size()>0){
                billEmptyLl.setVisibility(View.GONE);
            }else {
                billEmptyLl.setVisibility(View.VISIBLE);
            }

            adapter.notifyDataSetChanged();
        }

        @Override
        public void onFail(String msg) {

            if (bikkSwip.isRefreshing()) {
                bikkSwip.setRefreshing(false);
            }
            if (orderTendsList.size()>0){
                billEmptyLl.setVisibility(View.GONE);
            }else {
                billEmptyLl.setVisibility(View.VISIBLE);
            }
            //  ToastUtils.showShort(Constant.REQUEST_FAIL);
        }
    };

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.time_bill_tv:
                    showSexChooseDialog();

                    break;
            }
        }
    };


    /* 选择时间 */

    private void showSexChooseDialog() {
        ActivityStack.getLastActivity().showListSingleDialog(timelist, new BaseActivity.ListDialogListener() {
            @Override
            public void itemClick(int position) {

            }

            @Override
            public void itemClick(String str) {
                billHeadView.setTime(str);
                map.clear();
                map.put("month", str);
                wageListPresenter.loadData(map);
            }
        });
    }
}
