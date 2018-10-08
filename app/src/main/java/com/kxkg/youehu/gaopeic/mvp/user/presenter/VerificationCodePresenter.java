package com.kxkg.youehu.gaopeic.mvp.user.presenter;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kxkg.youehu.gaopeic.constant.Constant;
import com.kxkg.youehu.gaopeic.entity.Common;
import com.kxkg.youehu.gaopeic.entity.Login;
import com.kxkg.youehu.gaopeic.http.OnResponseListener;
import com.kxkg.youehu.gaopeic.mvp.base.BaseResponse;
import com.kxkg.youehu.gaopeic.mvp.base.BaseView;
import com.kxkg.youehu.gaopeic.mvp.user.model.GetCodeModel;
import com.kxkg.youehu.gaopeic.mvp.user.model.VerificationCodeModel;

/**
 * Created by yxs on 2018/9/12.
 */

public class VerificationCodePresenter implements OnResponseListener<BaseResponse<String>> {

    private final BaseView<String> view;
    private final VerificationCodeModel model;

    public void loadData(Common common){
        model.query(this,common);
    }


    public VerificationCodePresenter(BaseView<String> view) {
        this.view = view;
        this.model = new VerificationCodeModel();
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
