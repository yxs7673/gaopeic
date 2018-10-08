package com.kxkg.youehu.gaopeic.mvp.user.model;

import com.kxkg.youehu.gaopeic.entity.Login;
import com.kxkg.youehu.gaopeic.entity.NurseInfo;
import com.kxkg.youehu.gaopeic.entity.User;
import com.kxkg.youehu.gaopeic.http.HttpData;
import com.kxkg.youehu.gaopeic.http.OnResponseListener;
import com.kxkg.youehu.gaopeic.mvp.base.BaseResponse;

import rx.Observer;

/**
 * Created by yxs on 2018/9/12.
 */

public class NurseInfoModel {
    public static void query(final OnResponseListener listener, NurseInfo nurseInfo){
        HttpData.getInstance().nurseInfo(new Observer<BaseResponse<NurseInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(e);
            }

            @Override
            public void onNext(BaseResponse<NurseInfo> nurseInfo) {
                listener.onSuccess(nurseInfo);
            }
        },nurseInfo);

    }



}
