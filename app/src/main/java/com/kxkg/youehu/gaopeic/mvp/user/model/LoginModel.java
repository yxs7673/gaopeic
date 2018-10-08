package com.kxkg.youehu.gaopeic.mvp.user.model;

import com.kxkg.youehu.gaopeic.entity.Login;
import com.kxkg.youehu.gaopeic.entity.User;
import com.kxkg.youehu.gaopeic.http.HttpData;
import com.kxkg.youehu.gaopeic.http.OnResponseListener;
import com.kxkg.youehu.gaopeic.mvp.base.BaseResponse;

import rx.Observer;

/**
 * Created by yxs on 2018/9/10.
 */

public class LoginModel {
    public static void query(final OnResponseListener listener, Login login){
        HttpData.getInstance().login(new Observer<BaseResponse<User>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(e);
            }

            @Override
            public void onNext(BaseResponse<User> userBaseResponse) {
                listener.onSuccess(userBaseResponse);
            }
        },login);

    }





}
