package com.kxkg.youehu.gaopeic.mvp.order.presenter;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kxkg.youehu.gaopeic.constant.Constant;
import com.kxkg.youehu.gaopeic.entity.Common;
import com.kxkg.youehu.gaopeic.entity.OrderInfo;
import com.kxkg.youehu.gaopeic.http.OnResponseListener;
import com.kxkg.youehu.gaopeic.mvp.base.BaseResponse;
import com.kxkg.youehu.gaopeic.mvp.base.BaseView;
import com.kxkg.youehu.gaopeic.mvp.order.model.OrderDetailModel;
import com.kxkg.youehu.gaopeic.mvp.user.model.VerificationCodeModel;

/**
 * Created by yxs on 2018/9/14.
 */

public class OrderDetailPresenter implements OnResponseListener<BaseResponse<OrderInfo>> {

    private final BaseView<OrderInfo> view;
    private final OrderDetailModel model;

    public void loadData(String orderCode){
        model.query(this,orderCode);
    }


    public OrderDetailPresenter(BaseView<OrderInfo> view) {
        this.view = view;
        this.model = new OrderDetailModel();
    }

    @Override
    public void onSuccess(BaseResponse<OrderInfo> data) {
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
