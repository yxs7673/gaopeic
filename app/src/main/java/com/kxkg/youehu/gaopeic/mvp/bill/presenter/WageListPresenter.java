package com.kxkg.youehu.gaopeic.mvp.bill.presenter;

import android.content.Context;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kxkg.youehu.gaopeic.constant.Constant;
import com.kxkg.youehu.gaopeic.entity.ReceiveOrder;
import com.kxkg.youehu.gaopeic.entity.WageBean;
import com.kxkg.youehu.gaopeic.http.OnResponseListener;
import com.kxkg.youehu.gaopeic.mvp.base.BaseResponse;
import com.kxkg.youehu.gaopeic.mvp.base.BaseView;
import com.kxkg.youehu.gaopeic.mvp.bill.model.WageListModel;
import com.kxkg.youehu.gaopeic.mvp.user.model.GetCodeModel;
import com.kxkg.youehu.gaopeic.util.GdpUtils;

import java.util.HashMap;

/**
 * Created by yxs on 2018/9/21.
 */

public class WageListPresenter implements OnResponseListener<BaseResponse<WageBean>> {

    private final BaseView<WageBean> view;
    private final WageListModel model;
    private Context context;

    public WageListPresenter(BaseView<WageBean> view,Context context) {
        this.view = view;
        this.context = context;
        this.model = new WageListModel();

    }

    public void loadData(HashMap<String,String> map){
        model.query(this,map);
    }



    @Override
    public void onSuccess(BaseResponse<WageBean> data) {
        if (StringUtils.equals(Constant.REQUEST_OVERTIME,data.getCode()+"")){
            GdpUtils.toLogin(context);
            return;
        }


        if (StringUtils.equals(Constant.REQUEST_SUCCESS,data.getCode()+"")){
            view.onSuccess(data.getData());
        }else {
            view.onFail(data.getMsg());
           // ToastUtils.showShort(data.getMsg());
        }
    }

    @Override
    public void onFailure(Throwable e) {
        view.onFail(null);
    }
}
