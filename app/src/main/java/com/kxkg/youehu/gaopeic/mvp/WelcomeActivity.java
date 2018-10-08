package com.kxkg.youehu.gaopeic.mvp;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.kxkg.youehu.developlib.base.BaseActivity;
import com.kxkg.youehu.gaopeic.R;
import com.kxkg.youehu.gaopeic.cash.UserCash;
import com.kxkg.youehu.gaopeic.push.PushIntentService;
import com.youehu.android.getui.GetuiPushInstance;

public class WelcomeActivity extends BaseActivity {



    @Override
    protected Context getActivityContext() {
        return this;
    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_welcome);
    }

    @Override
    protected void findViewById() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic() {

        //界面全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //初始化个推推送
        GetuiPushInstance.init(WelcomeActivity.this, PushIntentService.class);
        //防止第一次安装，按home键再打开应用，重复开打界面（闪一下黑屏）
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }else {
            toNextActivity();
        }

    }


    private void toNextActivity() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (UserCash.getUser()!=null){
                    jumpToActivityAndClearTask(MainActivity.class);
                }else {
                    jumpToActivityAndClearTask(LoginActivity.class);
                }
            }
        }, 1500);
    }
}
