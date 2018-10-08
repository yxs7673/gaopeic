package com.kxkg.youehu.gaopeic.mvp.user.presenter;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kxkg.youehu.gaopeic.constant.Constant;
import com.kxkg.youehu.gaopeic.entity.NurseInfo;
import com.kxkg.youehu.gaopeic.http.OnResponseListener;
import com.kxkg.youehu.gaopeic.mvp.base.BaseResponse;
import com.kxkg.youehu.gaopeic.mvp.base.BaseView;
import com.kxkg.youehu.gaopeic.mvp.user.model.NurseInfoModel;
import com.kxkg.youehu.gaopeic.mvp.user.model.RegisterModel;

/**
 * Created by yxs on 2018/9/13.
 */

public class RegisterPresenter implements OnResponseListener<BaseResponse<String>> {
    private final BaseView<String> view;
    private final RegisterModel model;

    public RegisterPresenter(BaseView<String> view) {
        this.view = view;
        this.model = new RegisterModel();
    }

    public void loadData(NurseInfo nurseInfo){
        model.query(this,nurseInfo);
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
