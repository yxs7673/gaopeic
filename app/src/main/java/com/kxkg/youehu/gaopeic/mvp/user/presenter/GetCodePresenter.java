package com.kxkg.youehu.gaopeic.mvp.user.presenter;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kxkg.youehu.gaopeic.constant.Constant;
import com.kxkg.youehu.gaopeic.entity.Common;
import com.kxkg.youehu.gaopeic.entity.User;
import com.kxkg.youehu.gaopeic.http.OnResponseListener;
import com.kxkg.youehu.gaopeic.mvp.base.BaseResponse;
import com.kxkg.youehu.gaopeic.mvp.base.BaseView;
import com.kxkg.youehu.gaopeic.mvp.user.model.GetCodeModel;
import com.kxkg.youehu.gaopeic.mvp.user.model.LoginModel;

import java.util.HashMap;

/**
 * Created by yxs on 2018/9/12.
 */

public class GetCodePresenter  implements OnResponseListener<BaseResponse<String>> {
    private final BaseView<String> view;
    private final GetCodeModel model;

    public GetCodePresenter(BaseView<String> view) {
        this.view = view;
        this.model = new GetCodeModel();
    }

    public void loadData(HashMap map){
        model.query(this,map);
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
