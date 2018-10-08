package com.kxkg.youehu.gaopeic.mvp.order.model;

import com.kxkg.youehu.gaopeic.entity.Login;
import com.kxkg.youehu.gaopeic.entity.User;
import com.kxkg.youehu.gaopeic.http.HttpData;
import com.kxkg.youehu.gaopeic.http.OnResponseListener;
import com.kxkg.youehu.gaopeic.mvp.base.BaseResponse;

import rx.Observer;

/**
 * Created by yxs on 2018/9/13.
 */

public class ChangeStatusModel {
    public static void query(final OnResponseListener listener){
        HttpData.getInstance().changeStatus(new Observer<BaseResponse<String>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(e);
            }

            @Override
            public void onNext(BaseResponse<String> userBaseResponse) {
                listener.onSuccess(userBaseResponse);
            }
        });
    }

}
