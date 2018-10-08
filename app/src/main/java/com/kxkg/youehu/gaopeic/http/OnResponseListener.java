package com.kxkg.youehu.gaopeic.http;

/**
 * Created by wangyuyuan on 2017/7/28.
 */

public interface OnResponseListener<T> {
    void onSuccess(T data);

    void onFailure(Throwable e);
}
