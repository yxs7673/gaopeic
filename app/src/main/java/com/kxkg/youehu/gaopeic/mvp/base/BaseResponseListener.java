package com.kxkg.youehu.gaopeic.mvp.base;


import com.kxkg.youehu.gaopeic.http.OnResponseListener;

/**
 * Created by Administrator on 2018/3/28.
 */

public abstract   class BaseResponseListener<T> implements OnResponseListener {
    @Override
    public void onSuccess(Object data) {

    }

    @Override
    public void onFailure(Throwable e) {

    }

   abstract void onSuccessNet(T data);

  abstract   void onFailureNet(Throwable e);

}
