package com.kxkg.youehu.gaopeic.mvp.user.model;

import com.kxkg.youehu.gaopeic.entity.Common;
import com.kxkg.youehu.gaopeic.entity.NurseInfo;
import com.kxkg.youehu.gaopeic.http.HttpData;
import com.kxkg.youehu.gaopeic.http.OnResponseListener;
import com.kxkg.youehu.gaopeic.mvp.base.BaseResponse;

import rx.Observer;

/**
 * Created by yxs on 2018/9/27.
 */

public class ChangePassModel {

    public static void query(final OnResponseListener listener, Common common){
        HttpData.getInstance().changePass(new Observer<BaseResponse<String>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(e);
            }

            @Override
            public void onNext(BaseResponse<String> nurseInfo) {
                listener.onSuccess(nurseInfo);
            }
        },common);
    }

}
