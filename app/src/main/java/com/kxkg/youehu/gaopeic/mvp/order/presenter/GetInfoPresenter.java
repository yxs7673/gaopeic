package com.kxkg.youehu.gaopeic.mvp.order.presenter;

import android.content.Context;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kxkg.youehu.gaopeic.constant.Constant;
import com.kxkg.youehu.gaopeic.entity.NurseInfo;
import com.kxkg.youehu.gaopeic.entity.User;
import com.kxkg.youehu.gaopeic.http.OnResponseListener;
import com.kxkg.youehu.gaopeic.mvp.base.BaseResponse;
import com.kxkg.youehu.gaopeic.mvp.base.BaseView;
import com.kxkg.youehu.gaopeic.mvp.order.model.ChangeStatusModel;
import com.kxkg.youehu.gaopeic.mvp.order.model.GetInfoModel;
import com.kxkg.youehu.gaopeic.util.GdpUtils;

/**
 * Created by yxs on 2018/9/13.
 */

public class GetInfoPresenter implements OnResponseListener<BaseResponse<NurseInfo>> {
    private final BaseView<NurseInfo> view;
    private final GetInfoModel model;
    private Context context;


    public GetInfoPresenter(BaseView<NurseInfo> view,Context context) {
        this.view = view;
        this.model = new GetInfoModel();
        this.context=context;

    }


    public void loadData(){
        model.query(this);
    }


    @Override
    public void onSuccess(BaseResponse<NurseInfo> data) {
        if (StringUtils.equals(Constant.REQUEST_OVERTIME,data.getCode()+"")){
            GdpUtils.toLogin(context);
            return;
        }


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
