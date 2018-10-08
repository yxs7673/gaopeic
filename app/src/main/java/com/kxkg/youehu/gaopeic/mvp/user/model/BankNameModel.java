package com.kxkg.youehu.gaopeic.mvp.user.model;


import com.kxkg.youehu.gaopeic.entity.BankName;
import com.kxkg.youehu.gaopeic.http.HttpData;
import com.kxkg.youehu.gaopeic.http.OnResponseListener;
import com.kxkg.youehu.gaopeic.mvp.base.BaseResponse;

import java.util.List;

import rx.Observer;

/**
 * Created by Administrator on 2018/1/29.
 */

public class BankNameModel {

    public static void query(final OnResponseListener listener) {
        HttpData.getInstance().bankName(
                new Observer<BaseResponse<List<BankName>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onFailure(e);

                    }

                    @Override
                    public void onNext(BaseResponse<List<BankName>> listBaseResponse) {
                        listener.onSuccess(listBaseResponse);

                    }

                });
    }

}
