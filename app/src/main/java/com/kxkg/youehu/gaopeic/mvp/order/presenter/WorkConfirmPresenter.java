package com.kxkg.youehu.gaopeic.mvp.order.presenter;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kxkg.youehu.gaopeic.constant.Constant;
import com.kxkg.youehu.gaopeic.entity.ReceiveOrder;
import com.kxkg.youehu.gaopeic.http.OnResponseListener;
import com.kxkg.youehu.gaopeic.mvp.base.BaseResponse;
import com.kxkg.youehu.gaopeic.mvp.base.BaseView;
import com.kxkg.youehu.gaopeic.mvp.order.model.ReceiveOrderModel;
import com.kxkg.youehu.gaopeic.mvp.order.model.WorkConfirmModel;

/**
 * Created by yxs on 2018/9/17.
 */

public class WorkConfirmPresenter implements OnResponseListener<BaseResponse<String>> {

    private final BaseView<String> view;
    private final WorkConfirmModel model;

    public void loadData( ReceiveOrder receiveOrder){
        model.query(this,receiveOrder);
    }


    public WorkConfirmPresenter(BaseView<String> view) {
        this.view = view;
        this.model = new WorkConfirmModel();
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
