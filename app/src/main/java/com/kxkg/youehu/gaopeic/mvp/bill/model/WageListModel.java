package com.kxkg.youehu.gaopeic.mvp.bill.model;

import com.kxkg.youehu.gaopeic.entity.WageBean;
import com.kxkg.youehu.gaopeic.http.HttpData;
import com.kxkg.youehu.gaopeic.http.OnResponseListener;
import com.kxkg.youehu.gaopeic.mvp.base.BaseResponse;

import java.util.HashMap;

import rx.Observer;

/**
 * Created by yxs on 2018/9/21.
 */

public class WageListModel {
    public static void query(final OnResponseListener listener, HashMap<String, String> time){
        HttpData.getInstance().wageList(new Observer<BaseResponse<WageBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(e);
            }

            @Override
            public void onNext(BaseResponse<WageBean> wage) {
                listener.onSuccess(wage);
            }
        },time);

    }






}
