package com.kxkg.youehu.gaopeic.mvp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.kxkg.youehu.developlib.base.BaseActivity;
import com.kxkg.youehu.developlib.util.ActivityStack;
import com.kxkg.youehu.gaopeic.R;
import com.kxkg.youehu.gaopeic.mvp.factory.FragmentController;
import com.xiaochao.lcrapiddeveloplibrary.Cache.ACache;

import butterknife.BindView;

public class MainActivity extends BaseActivity {


    @BindView(R.id.hometab_context)
    FrameLayout hometabContext;
    @BindView(R.id.rb_order)
    RadioButton rbOrder;
    @BindView(R.id.rb_bill)
    RadioButton rbBill;
    @BindView(R.id.rb_user)
    RadioButton rbUser;
    @BindView(R.id.hometab_radio)
    RadioGroup hometabRadio;
    private FragmentController controller;
    //双击两次退出app
    private boolean isExit = false;

    @Override
    protected Context getActivityContext() {
        return this;
    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_main);


        //防止第一次安装，按home键再打开应用，重复开打界面（闪一下黑屏）
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }

        if (controller == null) {
            controller = FragmentController.getController(this, R.id.hometab_context);
            controller.initFragment();
            controller.showFragment(0);
        }
    }

    /*防止界面重叠*/
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void findViewById() {

    }

    @Override
    protected void setListener() {
        hometabRadio.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    @Override
    protected void processLogic() {

    }


    private RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
            switch (checkedId) {
                case R.id.rb_order:
                    controller.showFragment(0);
                    break;
                case R.id.rb_bill:
                    controller.showFragment(1);
                    break;
                case R.id.rb_user:
                    controller.showFragment(2);
                    break;
            }
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
       ACache.get(this).clear();//清除所有缓存
        if (controller!=null) {
            controller.onDestroy();
        }
    }

    /**
     * 双击两次退出app
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (isExit) {
             ActivityStack.finishAll();
                // System.exit(0);
                //退出时候结束进程
                android.os.Process.killProcess(android.os.Process.myPid());
            } else {
                isExit = true;
               ToastUtils.showShort("再按一次,退出应用");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isExit = false;
                    }
                }, 2000);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



}
