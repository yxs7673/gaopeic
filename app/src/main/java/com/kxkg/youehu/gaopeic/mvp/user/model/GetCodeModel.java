package com.kxkg.youehu.gaopeic.mvp.user.model;

import com.kxkg.youehu.gaopeic.entity.Login;
import com.kxkg.youehu.gaopeic.entity.User;
import com.kxkg.youehu.gaopeic.http.HttpData;
import com.kxkg.youehu.gaopeic.http.OnResponseListener;
import com.kxkg.youehu.gaopeic.mvp.base.BaseResponse;

import java.util.HashMap;

import rx.Observer;

/**
 * Created by yxs on 2018/9/12.
 */

public class GetCodeModel {
    public static void query(final OnResponseListener listener, HashMap<String, String> phone){
        HttpData.getInstance().getcode(new Observer<BaseResponse<String>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(e);
            }

            @Override
            public void onNext(BaseResponse<String> code) {
                listener.onSuccess(code);
            }
        },phone);

    }


}
