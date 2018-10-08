package com.kxkg.youehu.gaopeic.mvp.user.presenter;

import android.content.Context;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kxkg.youehu.gaopeic.constant.Constant;
import com.kxkg.youehu.gaopeic.entity.Common;
import com.kxkg.youehu.gaopeic.entity.NurseInfo;
import com.kxkg.youehu.gaopeic.http.OnResponseListener;
import com.kxkg.youehu.gaopeic.mvp.base.BaseResponse;
import com.kxkg.youehu.gaopeic.mvp.base.BaseView;
import com.kxkg.youehu.gaopeic.mvp.user.model.GetCodeModel;
import com.kxkg.youehu.gaopeic.mvp.user.model.NurseInfoModel;
import com.kxkg.youehu.gaopeic.mvp.user.model.VerificationCodeModel;
import com.kxkg.youehu.gaopeic.util.GdpUtils;

/**
 * Created by yxs on 2018/9/12.
 */

public class NurseInfoPresenter  implements OnResponseListener<BaseResponse<NurseInfo>> {

    private final BaseView<NurseInfo> view;
    private final NurseInfoModel model;
    private Context context;

    public NurseInfoPresenter(BaseView<NurseInfo> view,Context context) {
        this.view = view;
        this.context = context;
        this.model = new NurseInfoModel();
    }

    public void loadData(NurseInfo nurseInfo){
        model.query(this,nurseInfo);
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
