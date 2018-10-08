package com.kxkg.youehu.gaopeic.mvp.order.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kxkg.youehu.developlib.base.BaseFragment;
import com.kxkg.youehu.developlib.dialog.LoadingDiaolg;
import com.kxkg.youehu.developlib.dialog.MyDialogHint;
import com.kxkg.youehu.gaopeic.R;
import com.kxkg.youehu.gaopeic.constant.Constant;
import com.kxkg.youehu.gaopeic.entity.NurseInfo;
import com.kxkg.youehu.gaopeic.entity.OrderInfo;
import com.kxkg.youehu.gaopeic.entity.OrderTendsBean;
import com.kxkg.youehu.gaopeic.entity.ReceiveOrder;
import com.kxkg.youehu.gaopeic.entity.SpecailBean;
import com.kxkg.youehu.gaopeic.entity.User;
import com.kxkg.youehu.gaopeic.mvp.base.BaseView;
import com.kxkg.youehu.gaopeic.mvp.order.presenter.ChangeStatusPresenter;
import com.kxkg.youehu.gaopeic.mvp.order.presenter.GetInfoPresenter;
import com.kxkg.youehu.gaopeic.mvp.order.presenter.OrderDetailPresenter;
import com.kxkg.youehu.gaopeic.mvp.order.presenter.ReceiveOrderPresenter;
import com.kxkg.youehu.gaopeic.mvp.order.presenter.WorkConfirmPresenter;
import com.kxkg.youehu.gaopeic.mvp.user.activity.CashGetActivity;
import com.kxkg.youehu.gaopeic.mvp.user.activity.ChoiceBankActivity;
import com.kxkg.youehu.gaopeic.mvp.user.activity.IdCardActivity;
import com.kxkg.youehu.gaopeic.util.GdpUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.rx_cache.internal.migration.GetCacheVersion_Factory;

/**
 * Created by yxs on 2018/8/27.
 */
public class WorkFragment extends BaseFragment {


    @BindView(R.id.work_state_img)
    ImageView workStateImg;
    @BindView(R.id.work_state_tv)
    TextView workStateTv;
    @BindView(R.id.ll_order_rest)
    LinearLayout llOrderRest;
    @BindView(R.id.ll_order_work)
    LinearLayout llOrderWork;
    @BindView(R.id.tv_order_start)
    TextView tvOrderStart;
    @BindView(R.id.tv_order_end)
    TextView tvOrderEnd;
    @BindView(R.id.tv_order_days)
    TextView tvOrderDays;
    @BindView(R.id.tv_order_price)
    TextView tvOrderPrice;
    @BindView(R.id.tv_order_name)
    TextView tvOrderName;
    @BindView(R.id.tv_order_address)
    TextView tvOrderAddress;
    @BindView(R.id.tv_order_tagflow)
    TagFlowLayout tvOrderTagflow;
    @BindView(R.id.btn_order_cancle)
    Button btnOrderCancle;
    @BindView(R.id.tv_order_accept)
    Button tvOrderAccept;
    @BindView(R.id.tv_order_work)
    Button tvOrderWork;
    @BindView(R.id.work_cardview)
    CardView workCardview;
    @BindView(R.id.ll_work_content)
    RelativeLayout llWorkContent;
    @BindView(R.id.work_swipe)
    SwipeRefreshLayout workSwipe;
    @BindView(R.id.work_phone_btn)
    TextView workPhoneBtn;
    @BindView(R.id.view_none_ll)
    LinearLayout viewNoneLl;
    @BindView(R.id.status_work_tv)
    TextView statusWorkTv;
    @BindView(R.id.status_work_ll)
    RelativeLayout statusWorkLl;
    @BindView(R.id.ll_order_info)
    LinearLayout llOrderInfo;

    private ChangeStatusPresenter changeStatusPresenter;
    private GetInfoPresenter getInfoPresenter;
    private ReceiveOrderPresenter receiveOrderPresenter;
    private OrderDetailPresenter orderDetailPresenter;
    private WorkConfirmPresenter workConfirmPresenter;

    private User user;
    private NurseInfo nurseInfo;
    private String oderCode;
    private List<String> specialList = new ArrayList<>();
    private final String accept = "accept";
    private final String work = "work";
    private final String cancle = "cancle";
    private String oderStatue;
    private OrderInfo orderInfo;
    private LoadingDiaolg diaolg;
    private boolean isFirst = true;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, boolean b) {
        return inflater.inflate(R.layout.fragment_work, container, false);
    }


    @Override
    public void onResume() {
        super.onResume();
        getInfoPresenter.loadData();

    }

    @Override
    protected void processLogic() {

        diaolg = new LoadingDiaolg(getContext());

        changeStatusPresenter = new ChangeStatusPresenter(statusView);
        getInfoPresenter = new GetInfoPresenter(nurseInfoBaseView, getContext());
        orderDetailPresenter = new OrderDetailPresenter(detailView);
        receiveOrderPresenter = new ReceiveOrderPresenter(receiveOrderView);
        workConfirmPresenter = new WorkConfirmPresenter(receiveOrderView);
       // getInfoPresenter.loadData();
    }


    /**
     * 加载刷新界面
     */
    private void initWorkView(NurseInfo data) {
        //首先判断护工的状态
        if (!StringUtils.isEmpty(data.getStatus())) {

       /*     //通过陪护记录的第一条数据中的结束时间，与当前时间相比较，获取是否有当前订单
            if (data.getOrderTends() != null && data.getOrderTends().size() > 0) {

                OrderTendsBean orderTendsBean = data.getOrderTends().get(0);
                if (orderTendsBean != null) {
                    OrderInfo orderInfo = orderTendsBean.getOrder();
                    if (orderInfo != null && !StringUtils.isEmpty(orderInfo.getStatus())) {
                        if (!StringUtils.equals(orderTendsBean.getStatus(), "1") && !StringUtils.equals(orderTendsBean.getStatus(), "0")) {//0 取消 1 拒绝 2 待接单 3 待到岗  10进行中
                            workCardview.setVisibility(View.VISIBLE);
                            oderCode = orderTendsBean.getOrderCode();
                            orderDetailPresenter.loadData(oderCode);
                        } else {
                            workCardview.setVisibility(View.GONE);
                        }
                    }
                }
                //  initOrderData(orderTendsBean);
            } else {
                changeWorkRest(data);
            }*/

            if (StringUtils.equals("2",data.getStatus())||StringUtils.equals("1",data.getStatus())||StringUtils.equals("0",data.getStatus())){
                changeWorkRest(data);
            }else {
                if (data.getOrderTends() != null && data.getOrderTends().size() > 0) {

                    OrderTendsBean orderTendsBean = data.getOrderTends().get(0);
                    if (orderTendsBean != null) {
                        OrderInfo orderInfo = orderTendsBean.getOrder();
                        if (orderInfo != null && !StringUtils.isEmpty(orderInfo.getStatus())) {
                            if (!StringUtils.equals(orderTendsBean.getStatus(), "1") && !StringUtils.equals(orderTendsBean.getStatus(), "0")) {//0 取消 1 拒绝 2 待接单 3 待到岗  10进行中
                                workCardview.setVisibility(View.VISIBLE);
                                oderCode = orderTendsBean.getOrderCode();
                                orderDetailPresenter.loadData(oderCode);
                            } else {
                                workCardview.setVisibility(View.GONE);
                            }
                        }
                    }
                    //  initOrderData(orderTendsBean);
                }
            }



        }
    }

    private void changeWorkRest(NurseInfo data) {

        workCardview.setVisibility(View.GONE);
        String status = data.getStatus();


        //判断是否提交资料 audit  审核原因
       // auditStatus  0 待审核 1审核通过 2审核不通过  注册时这是null


        if (StringUtils.isEmpty(data.getAuditStatus())) {
            workStateTv.setText("请提交个人信息与银行卡信息，\n并等待审核");
            llOrderInfo.setVisibility(View.VISIBLE);
            toEditInfo();
        }else {
            switch (data.getAuditStatus()){
                case "0":
                    if (StringUtils.isEmpty(data.getIdcard())||StringUtils.isEmpty(data.getBankCardNo())) {
                        workStateTv.setText("请提交个人信息与银行卡信息，\n并等待审核");
                        llOrderInfo.setVisibility(View.VISIBLE);
                        toEditInfo();
                    }else {
                        llOrderInfo.setVisibility(View.GONE);
                        workStateTv.setText(R.string.str_order_verify);
                    }
                    break;
                case "1"://审核通过
                    initadpot(status);
                    llOrderInfo.setVisibility(View.GONE);
                    break;
                case "2":
                    String reason="";
                    if (!StringUtils.isEmpty(nurseInfo.getAudit())){
                        reason=nurseInfo.getAudit();
                    }
                    workStateTv.setText("审核未通过\n原因："+reason);
                    llOrderInfo.setVisibility(View.GONE);
                    break;

            }
        }

    }

    private void toEditInfo() {
        if (isFirst){
            isFirst=false;
            Intent intent=new Intent();
            if (StringUtils.isEmpty(nurseInfo.getIdcard())) {
                intent.setClass(getContext(), IdCardActivity.class);
            }else {
                intent.setClass(getContext(), CashGetActivity.class);
            }
            intent.putExtra("info",nurseInfo);
            startActivity(intent);
        }


    }

    private void initadpot(String status) {
        if (!"".equals(status)) {
            switch (status) {
                case "0"://审核中
                    llOrderRest.setVisibility(View.GONE);
                    llOrderWork.setVisibility(View.GONE);
                    workStateTv.setText(R.string.str_order_verify);
                    workStateImg.setImageResource(R.mipmap.bg_org_empty);
                    break;

                case "1"://休息
                    llOrderRest.setVisibility(View.VISIBLE);
                    llOrderWork.setVisibility(View.GONE);
                    workStateTv.setText(R.string.str_order_rest);
                    workStateImg.setImageResource(R.mipmap.bg_org_word);
                    break;
                case "2"://待岗
                    llOrderRest.setVisibility(View.GONE);
                    llOrderWork.setVisibility(View.VISIBLE);
                    workStateTv.setText(R.string.str_order_wait);
                    workStateImg.setImageResource(R.mipmap.bg_org_rest);
                    break;
            }
        }
    }

    @Override
    protected void initListener() {
        llOrderRest.setOnClickListener(onClickListener);
        llOrderWork.setOnClickListener(onClickListener);
        tvOrderAccept.setOnClickListener(onClickListener);
        tvOrderWork.setOnClickListener(onClickListener);
        btnOrderCancle.setOnClickListener(onClickListener);
        workPhoneBtn.setOnClickListener(onClickListener);
        llOrderInfo.setOnClickListener(onClickListener);

        workSwipe.setColorSchemeColors(Color.parseColor("#FF4081"), Color.parseColor("#303F9F"));
        workSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getInfoPresenter.loadData();
            }
        });
    }

    @Override
    protected void initData() {

    }


    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ll_order_rest://我要上岗
                    new MyDialogHint(getContext(), R.style.MyDialogConfirm,
                            "您现在的工作状态是下班休息状态，请确认是否上班？", new MyDialogHint.ClickCallBack() {
                        @Override
                        public void onCancelClick() {

                        }

                        @Override
                        public void onOkClick() {
                            changeStatusPresenter.loadData();
                        }
                    }).show();

                    break;
                case R.id.ll_order_work://我要休息
                    new MyDialogHint(getContext(), R.style.MyDialogConfirm,
                            "下工后将无法接单，请确认是否下工？", new MyDialogHint.ClickCallBack() {
                        @Override
                        public void onCancelClick() {

                        }

                        @Override
                        public void onOkClick() {
                            changeStatusPresenter.loadData();
                        }
                    }).show();
                    break;


                case R.id.work_phone_btn://拨打电话
                    if (orderInfo != null && !StringUtils.isEmpty(orderInfo.getContactPhone())) {
                        GdpUtils.callPhoneNo(getActivity(), orderInfo.getContactPhone());
                    } else {
                        ToastUtils.showShort("电话获取失败");
                    }

                    break;
                case R.id.tv_order_accept://确认接单
                    orderConfirm(accept);
                    break;
                case R.id.tv_order_work://确认到岗
                    orderConfirm(work);
                    break;
                case R.id.btn_order_cancle://取消订单
                    orderConfirm(cancle);
                    break;
                case R.id.ll_order_info:
                    Intent intent=new Intent();
                    if (StringUtils.isEmpty(nurseInfo.getIdcard())) {
                        intent.setClass(getContext(), IdCardActivity.class);
                    }else {
                        intent.setClass(getContext(), CashGetActivity.class);
                    }
                    intent.putExtra("info",nurseInfo);
                    startActivity(intent);
                    break;
            }
        }
    };

    private void orderConfirm(final String s) {
        String title = "确认修改吗？";
        switch (s) {
            case accept:
                title = "是否确认接单？";
                break;
            case work:
                title = "确认选择到岗吗？";
                break;
            case cancle:
                title = "确认取消该订单吗？";
                break;
        }

        new MyDialogHint(getContext(), R.style.MyDialogConfirm,
                title, new MyDialogHint.ClickCallBack() {
            @Override
            public void onCancelClick() {

            }

            @Override
            public void onOkClick() {
                orderconfirmClick(s);
            }
        }).show();
    }

    private void orderconfirmClick(String s) {

        final ReceiveOrder receiveOrder = new ReceiveOrder();
        String id = null;//第一条陪护记录的id
        if (nurseInfo != null && nurseInfo.getOrderTends() != null && nurseInfo.getOrderTends().size() > 0) {
            id = nurseInfo.getOrderTends().get(0).getId();
        }
        if (StringUtils.isEmpty(id)) {
            return;
        }

        diaolg.show();

        switch (s) {
            case accept:
                receiveOrder.setTendId(id);
                receiveOrder.setResult(1);
                receiveOrderPresenter.loadData(receiveOrder);
                break;
            case work:
                if (nurseInfo != null && nurseInfo.getOrderTends() != null && nurseInfo.getOrderTends().size() > 0) {
                    long nowTIme=System.currentTimeMillis();
                  String  startTimestr = nurseInfo.getOrderTends().get(0).getStartTime();
                    long  startTime= TimeUtils.string2Date(startTimestr).getTime();
                    if (startTime>nowTIme){
                        ToastUtils.showShort("还未到到岗时间");
                        diaolg.dismiss();
                        return;
                    }
                }

                receiveOrder.setTendId(id);
                receiveOrder.setResult(1);
                workConfirmPresenter.loadData(receiveOrder);
                break;
            case cancle:
                receiveOrder.setTendId(id);
                receiveOrder.setResult(0);
                receiveOrder.setReason("原因");
                if (StringUtils.equals(oderStatue, "3")) {
                    receiveOrderPresenter.loadData(receiveOrder);
                } else if (StringUtils.equals(oderStatue, "4")) {
                    workConfirmPresenter.loadData(receiveOrder);
                }
                break;
        }

    }

    BaseView<String> receiveOrderView = new BaseView<String>() {
        @Override
        public void onSuccess(String data) {
            if (diaolg.isShowing()) {
                diaolg.dismiss();
            }
            getInfoPresenter.loadData();
        }

        @Override
        public void onFail(String msg) {
            if (diaolg.isShowing()) {
                diaolg.dismiss();
            }
            ToastUtils.showShort(Constant.REQUEST_FAIL);
        }
    };

    /**
     * 订单详情
     */
    BaseView<OrderInfo> detailView = new BaseView<OrderInfo>() {
        @Override
        public void onSuccess(OrderInfo data) {
            if (data != null) {
                initOrderInfo(data);
                orderInfo = data;
            }
        }

        @Override
        public void onFail(String msg) {
            // ToastUtils.showShort(Constant.REQUEST_FAIL);
        }
    };

    /**
     * 填写
     *
     * @param data
     */
    @SuppressLint("ResourceAsColor")
    private void initOrderInfo(OrderInfo data) {
        OrderTendsBean orderTendsBean=nurseInfo.getOrderTends().get(0);
        if (orderTendsBean==null){
            return;
        }

        if (!StringUtils.isEmpty(data.getStartTime())) {
            tvOrderStart.setText("到岗时间：" + GdpUtils.deletHour(orderTendsBean.getStartTime()));
        }
        if (!StringUtils.isEmpty(data.getEndTime())) {
            tvOrderEnd.setText("结束时间：" + GdpUtils.deletHour(orderTendsBean.getEndTime()));
            tvOrderDays.setText("累计天数：" + GdpUtils.daysWork(orderTendsBean.getStartTime(),orderTendsBean.getEndTime()));
        }

        if (!StringUtils.isEmpty(data.getPrice())) {
            tvOrderPrice.setText(data.getPrice());
        }

        if (!StringUtils.isEmpty(data.getContact())) {
            tvOrderName.setText("病人姓名：" + data.getContact());
        }
        if (!StringUtils.isEmpty(data.getAddress())) {
            tvOrderAddress.setText("病人地址：" + data.getAddress());
        }
        if (!StringUtils.isEmpty(data.getStatus())) {
            oderStatue = data.getStatus();
            switch (data.getStatus()) {
                case "1"://代付费
                    tvOrderAccept.setVisibility(View.GONE);
                    btnOrderCancle.setVisibility(View.GONE);
                    tvOrderWork.setVisibility(View.GONE);
                    statusWorkLl.setVisibility(View.VISIBLE);
                    statusWorkTv.setText("待支付");
                    break;

                case "3"://带接单
                    tvOrderAccept.setVisibility(View.VISIBLE);
                    btnOrderCancle.setVisibility(View.VISIBLE);
                    tvOrderWork.setVisibility(View.GONE);
                    statusWorkLl.setVisibility(View.GONE);

                    break;
                case "4"://待到刚
                    tvOrderAccept.setVisibility(View.GONE);
                    btnOrderCancle.setVisibility(View.VISIBLE);
                    tvOrderWork.setVisibility(View.VISIBLE);
                    statusWorkLl.setVisibility(View.GONE);
                    break;
                case "10"://进行中
                   if (!StringUtils.isEmpty(orderTendsBean.getStatus())){//0取消 1 拒绝 2 待接单 3 待到岗  10进行中
                    if (StringUtils.equals("2",orderTendsBean.getStatus())){
                        tvOrderAccept.setVisibility(View.VISIBLE);
                        btnOrderCancle.setVisibility(View.VISIBLE);
                        tvOrderWork.setVisibility(View.GONE);
                        statusWorkLl.setVisibility(View.GONE);
                    }else if (StringUtils.equals("3",orderTendsBean.getStatus())){
                        tvOrderAccept.setVisibility(View.GONE);
                        btnOrderCancle.setVisibility(View.VISIBLE);
                        tvOrderWork.setVisibility(View.VISIBLE);
                        statusWorkLl.setVisibility(View.GONE);
                    }else {
                        tvOrderAccept.setVisibility(View.GONE);
                        btnOrderCancle.setVisibility(View.GONE);
                        tvOrderWork.setVisibility(View.GONE);
                        statusWorkLl.setVisibility(View.VISIBLE);
                        statusWorkTv.setText("进行中");
                    }
                   }else {
                       tvOrderAccept.setVisibility(View.GONE);
                       btnOrderCancle.setVisibility(View.GONE);
                       tvOrderWork.setVisibility(View.GONE);
                       statusWorkLl.setVisibility(View.VISIBLE);
                       statusWorkTv.setText("进行中");
                   }

                    break;
                case "99"://
                    tvOrderAccept.setVisibility(View.GONE);
                    btnOrderCancle.setVisibility(View.GONE);
                    tvOrderWork.setVisibility(View.GONE);
                    statusWorkLl.setVisibility(View.VISIBLE);
                    statusWorkTv.setText("已结束");

                    break;

            }
        }

        if (data.getOrderTends() != null && data.getOrderTends().size() > 0) {
            List<SpecailBean> beanList = data.getOrderTends().get(0).getSpecialCares();
            specialList.clear();
            if (beanList.size() > 0) {
                for (SpecailBean bean : beanList) {
                    specialList.add(bean.getItem());
                }
            }

            specialList.add("清洁护理（6项）");
            specialList.add("日常照顾（4项）");
            specialList.add("饮食照顾（3项）");
            specialList.add("治疗辅助（4项）");


            tvOrderTagflow.setAdapter(new TagAdapter<String>(specialList) {

                @Override
                public View getView(FlowLayout parent, int position, String s) {
                    TextView tv = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.view_flow,
                            tvOrderTagflow, false);
                    tv.setText(s);
                    return tv;
                }
            });
        }

    }

    BaseView<String> statusView = new BaseView<String>() {
        @Override
        public void onSuccess(String data) {
            if (data != null) {
                ToastUtils.showShort("修改成功");
                nurseInfo.setStatus(data);
                initWorkView(nurseInfo);
            }
        }

        @Override
        public void onFail(String msg) {
            ToastUtils.showShort(Constant.REQUEST_FAIL);
        }
    };


    private BaseView<NurseInfo> nurseInfoBaseView = new BaseView<NurseInfo>() {
        @Override
        public void onSuccess(NurseInfo data) {
            if (workSwipe.isRefreshing()) {
                workSwipe.setRefreshing(false);
            }

            if (data != null) {
                nurseInfo = data;
                initWorkView(nurseInfo);
            }
        }

        @Override
        public void onFail(String msg) {
            if (workSwipe.isRefreshing()) {
                workSwipe.setRefreshing(false);
            }
            ToastUtils.showShort(Constant.REQUEST_FAIL);
        }
    };


}
