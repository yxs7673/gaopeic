package com.kxkg.youehu.gaopeic.mvp.user.presenter;

import android.content.Context;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kxkg.youehu.gaopeic.constant.Constant;
import com.kxkg.youehu.gaopeic.entity.Common;
import com.kxkg.youehu.gaopeic.http.OnResponseListener;
import com.kxkg.youehu.gaopeic.mvp.base.BaseResponse;
import com.kxkg.youehu.gaopeic.mvp.base.BaseView;
import com.kxkg.youehu.gaopeic.mvp.user.model.ChangePassModel;
import com.kxkg.youehu.gaopeic.mvp.user.model.GetCodeModel;

import java.util.HashMap;

/**
 * Created by yxs on 2018/9/27.
 */

public class ChangePassPresenter implements OnResponseListener<BaseResponse<String>> {
    private final BaseView<String> view;
    private final ChangePassModel model;
    private Context context;

    public ChangePassPresenter(BaseView<String> view,Context context) {
        this.view = view;
        this.model = new ChangePassModel();
        this.context = context;
    }

    public void loadData(Common common){
        model.query(this,common);
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
