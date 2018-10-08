package com.kxkg.youehu.gaopeic.mvp.order.presenter;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kxkg.youehu.gaopeic.constant.Constant;
import com.kxkg.youehu.gaopeic.entity.OrderInfo;
import com.kxkg.youehu.gaopeic.entity.OrderTendsBean;
import com.kxkg.youehu.gaopeic.entity.ReceiveOrder;
import com.kxkg.youehu.gaopeic.http.OnResponseListener;
import com.kxkg.youehu.gaopeic.mvp.base.BaseResponse;
import com.kxkg.youehu.gaopeic.mvp.base.BaseView;
import com.kxkg.youehu.gaopeic.mvp.order.model.ReceiveOrderModel;
import com.kxkg.youehu.gaopeic.mvp.order.model.TendListModel;

import java.util.List;

/**
 * Created by yxs on 2018/9/18.
 */

public class TendListPresenter implements OnResponseListener<BaseResponse<List<OrderInfo>>> {

    private final BaseView<List<OrderInfo>> view;
    private final TendListModel model;


    public TendListPresenter(BaseView<List<OrderInfo>> view) {
        this.view = view;
        this.model = new TendListModel();
    }

    public void loadData( ){
        model.query(this);
    }

    @Override
    public void onSuccess(BaseResponse<List<OrderInfo>> data) {


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
