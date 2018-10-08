package com.kxkg.youehu.gaopeic.mvp.order.fragment;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kxkg.youehu.developlib.base.BaseFragment;
import com.kxkg.youehu.gaopeic.R;
import com.kxkg.youehu.gaopeic.constant.Constant;
import com.kxkg.youehu.gaopeic.entity.OrderInfo;
import com.kxkg.youehu.gaopeic.mvp.base.BaseView;
import com.kxkg.youehu.gaopeic.mvp.order.adapter.HistoryAdapter;
import com.kxkg.youehu.gaopeic.mvp.order.presenter.TendListPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by yxs on 2018/8/27.
 */
public class HistoryFragment extends BaseFragment {

    @BindView(R.id.work_state_img)
    ImageView workStateImg;
    @BindView(R.id.work_state_tv)
    TextView workStateTv;
    @BindView(R.id.ll_order_rest)
    LinearLayout llOrderRest;
    @BindView(R.id.ll_order_work)
    LinearLayout llOrderWork;
    @BindView(R.id.recycleview_history)
    RecyclerView recycleviewHistory;
    @BindView(R.id.view_none_ll)
    LinearLayout viewNoneLl;
    @BindView(R.id.history_swip)
    SwipeRefreshLayout historySwip;

    private TendListPresenter tendListPresenter;
    private List<OrderInfo> orderInfoList;
    private HistoryAdapter adapter;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, boolean b) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    protected void processLogic() {
        orderInfoList = new ArrayList<>();
        adapter = new HistoryAdapter(R.layout.item_history_list, orderInfoList);
        //设置RecyclerView的显示模式  当前List模式
        recycleviewHistory.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleviewHistory.setItemAnimator(new DefaultItemAnimator());
        //如果Item高度固定  增加该属性能够提高效率
        recycleviewHistory.setHasFixedSize(true);
        recycleviewHistory.setAdapter(adapter);
        tendListPresenter = new TendListPresenter(view);
        tendListPresenter.loadData();
    }

    @Override
    protected void initListener() {
        historySwip.setColorSchemeColors(Color.parseColor("#FF4081"), Color.parseColor("#303F9F"));
        historySwip.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                tendListPresenter.loadData();
            }
        });
    }

    @Override
    protected void initData() {
        workStateTv.setText(R.string.str_his_none);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };


    private BaseView<List<OrderInfo>> view = new BaseView<List<OrderInfo>>() {
        @Override
        public void onSuccess(List<OrderInfo> data) {
            if (historySwip.isRefreshing()) {
                historySwip.setRefreshing(false);
            }

            orderInfoList.clear();
            if (data != null && data.size() > 0) {
                List<OrderInfo> list = data;
                for (OrderInfo bean:list){
                    if (StringUtils.equals(bean.getStatus(),"99")){
                        orderInfoList.add(bean);
                    }
                }
               // orderInfoList.addAll(list);
            }
            if (orderInfoList.size()>0){
                viewNoneLl.setVisibility(View.GONE);
            }else {
                viewNoneLl.setVisibility(View.VISIBLE);
            }

            adapter.notifyDataSetChanged();
        }

        @Override
        public void onFail(String msg) {

            if (historySwip.isRefreshing()) {
                historySwip.setRefreshing(false);
            }
            ToastUtils.showShort(Constant.REQUEST_FAIL);
        }
    };


}
