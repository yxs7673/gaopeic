package com.kxkg.youehu.gaopeic.mvp.order.presenter;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kxkg.youehu.gaopeic.constant.Constant;
import com.kxkg.youehu.gaopeic.entity.OrderInfo;
import com.kxkg.youehu.gaopeic.entity.ReceiveOrder;
import com.kxkg.youehu.gaopeic.http.OnResponseListener;
import com.kxkg.youehu.gaopeic.mvp.base.BaseResponse;
import com.kxkg.youehu.gaopeic.mvp.base.BaseView;
import com.kxkg.youehu.gaopeic.mvp.order.model.OrderDetailModel;
import com.kxkg.youehu.gaopeic.mvp.order.model.ReceiveOrderModel;

/**
 * Created by yxs on 2018/9/14.
 */

public class ReceiveOrderPresenter implements OnResponseListener<BaseResponse<String>> {
    private final BaseView<String> view;
    private final ReceiveOrderModel model;

    public void loadData( ReceiveOrder receiveOrder){
        model.query(this,receiveOrder);
    }


    public ReceiveOrderPresenter(BaseView<String> view) {
        this.view = view;
        this.model = new ReceiveOrderModel();
    }

    @Override
    public void onSuccess(BaseResponse<String> data) {
        if (StringUtils.equals(Constant.REQUEST_SUCCESS,data.getCode()+"")){
            view.onSuccess(data.getData());
        }else {
            view.onFail(data.getMsg());
            ToastUtils.showShort(data.getMsg());
        }
    }

    @Override
    public void onFailure(Throwable e) {
        view.onFail(null);
    }


}
