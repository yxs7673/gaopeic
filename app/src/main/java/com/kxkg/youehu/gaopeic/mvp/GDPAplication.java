package com.kxkg.youehu.gaopeic.mvp;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.kxkg.youehu.developlib.util.SharedPrefUtil;

/**
 * Created by Administrator on 2018/8/24.
 */

public class GDPAplication extends Application {


    /**
     * 应用实例
     **/
    private static GDPAplication mApplication;


    @Override
    public void onCreate() {
        super.onCreate();

        mApplication=this;
        //AndroidUtilCode初始化
        Utils.init(this);
        //仅仅是缓存Application的Context，，不做其他操作
        SharedPrefUtil.getInstance().init(this);

    }

    public static GDPAplication getInstance(){
        return mApplication;
    }
}
