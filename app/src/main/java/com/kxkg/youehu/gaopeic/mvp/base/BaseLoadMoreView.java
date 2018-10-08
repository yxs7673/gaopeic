package com.kxkg.youehu.gaopeic.mvp.base;

/**
 * Created by wangyuyuan on 2017/8/18.
 */

public interface BaseLoadMoreView<T> extends BaseView<T> {

    //添加更多数据
    void addData(T addData);

    //显示已加载所有数据
    void showLoadCompleteAllData();
}
