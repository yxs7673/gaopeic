package com.kxkg.youehu.gaopeic.mvp.order.model;

import com.kxkg.youehu.gaopeic.entity.ReceiveOrder;
import com.kxkg.youehu.gaopeic.http.HttpData;
import com.kxkg.youehu.gaopeic.http.OnResponseListener;
import com.kxkg.youehu.gaopeic.mvp.base.BaseResponse;

import rx.Observer;

/**
 * Created by yxs on 2018/9/17.
 */

public class WorkConfirmModel {

    public static void query(final OnResponseListener listener, ReceiveOrder receiveOrder){
        HttpData.getInstance().workConfirm(new Observer<BaseResponse<String>>() {
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
        },receiveOrder);
    }


}
