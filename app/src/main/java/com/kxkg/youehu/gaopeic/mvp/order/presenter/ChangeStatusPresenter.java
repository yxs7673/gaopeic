package com.kxkg.youehu.gaopeic.mvp.order.presenter;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kxkg.youehu.gaopeic.constant.Constant;
import com.kxkg.youehu.gaopeic.entity.Login;
import com.kxkg.youehu.gaopeic.entity.User;
import com.kxkg.youehu.gaopeic.http.OnResponseListener;
import com.kxkg.youehu.gaopeic.mvp.base.BaseResponse;
import com.kxkg.youehu.gaopeic.mvp.base.BaseView;
import com.kxkg.youehu.gaopeic.mvp.order.model.ChangeStatusModel;
import com.kxkg.youehu.gaopeic.mvp.user.model.LoginModel;

/**
 * Created by yxs on 2018/9/13.
 */

public class ChangeStatusPresenter implements OnResponseListener<BaseResponse<String>> {
    private final BaseView<String> view;
    private final ChangeStatusModel model;


    public ChangeStatusPresenter(BaseView<String> view) {
        this.view = view;
        this.model = new ChangeStatusModel();

    }


    public void loadData(){
        model.query(this);
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
