package com.kxkg.youehu.gaopeic.mvp.base;

/**
 * Created by wangyuyuan on 2017/8/18.
 */

public interface BaseView<T> {

    //数据加载成功
    void onSuccess(T data);

    //显示加载失败
    void onFail(String msg);


}
