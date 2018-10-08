package com.kxkg.youehu.gaopeic.mvp.order.model;

import com.kxkg.youehu.gaopeic.entity.OrderInfo;
import com.kxkg.youehu.gaopeic.entity.OrderTendsBean;
import com.kxkg.youehu.gaopeic.entity.ReceiveOrder;
import com.kxkg.youehu.gaopeic.http.HttpData;
import com.kxkg.youehu.gaopeic.http.OnResponseListener;
import com.kxkg.youehu.gaopeic.mvp.base.BaseResponse;

import java.util.List;

import rx.Observer;

/**
 * Created by yxs on 2018/9/18.
 */

public class TendListModel {


    public static void query(final OnResponseListener listener){
        HttpData.getInstance().tendList(new Observer<BaseResponse<List<OrderInfo>>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                listener.onFailure(e);
            }

            @Override
            public void onNext(BaseResponse<List<OrderInfo>> listBaseResponse) {
                listener.onSuccess(listBaseResponse);
            }



        });
    }


}
