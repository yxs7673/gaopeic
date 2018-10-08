package com.kxkg.youehu.gaopeic.mvp.order.model;

import com.kxkg.youehu.gaopeic.entity.OrderInfo;
import com.kxkg.youehu.gaopeic.http.HttpData;
import com.kxkg.youehu.gaopeic.http.OnResponseListener;
import com.kxkg.youehu.gaopeic.mvp.base.BaseResponse;

import rx.Observer;

/**
 * Created by yxs on 2018/9/14.
 */

public class OrderDetailModel {
    public static void query(final OnResponseListener listener,String odercode){
        HttpData.getInstance().orderDetail(new Observer<BaseResponse<OrderInfo>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(e);
            }

            @Override
            public void onNext(BaseResponse<OrderInfo> userBaseResponse) {
                listener.onSuccess(userBaseResponse);
            }
        },odercode);
    }


}
