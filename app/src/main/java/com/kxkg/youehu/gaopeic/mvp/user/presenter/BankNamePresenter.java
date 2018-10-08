package com.kxkg.youehu.gaopeic.mvp.user.presenter;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kxkg.youehu.gaopeic.constant.Constant;
import com.kxkg.youehu.gaopeic.entity.BankName;
import com.kxkg.youehu.gaopeic.http.OnResponseListener;
import com.kxkg.youehu.gaopeic.mvp.base.BaseResponse;
import com.kxkg.youehu.gaopeic.mvp.base.BaseView;
import com.kxkg.youehu.gaopeic.mvp.user.model.BankNameModel;


import java.util.List;

/**
 * Created by Administrator on 2018/4/16.
 */

public class BankNamePresenter implements OnResponseListener<BaseResponse<List<BankName>>> {

    private BaseView<List<BankName>> view;
    private BankNameModel model;


    public BankNamePresenter(BaseView<List<BankName>> view) {
        this.view = view;
        this.model = new BankNameModel();
    }


    public void loadData(){
        model.query(this);
    }


    @Override
    public void onSuccess(BaseResponse<List<BankName>> data) {
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
