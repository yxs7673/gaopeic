package com.kxkg.youehu.developlib.base;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.kxkg.youehu.developlib.R;
import com.kxkg.youehu.developlib.adapter.DialogSingleChoiceAdapter;
import com.kxkg.youehu.developlib.dialog.LoadingDiaolg;
import com.kxkg.youehu.developlib.util.ActivityStack;

import java.util.List;

import butterknife.ButterKnife;


/**
 * Created by Administrator on 2018/3/22.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener{

    protected Context mActivity;
    protected LoadingDiaolg mDiaolg;
    public LayoutInflater mInflater;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);// 锁定竖屏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        mActivity = getActivityContext();
        initView();
        //初始化butterKnife
        ButterKnife.bind(this);

        inidata();
        ActivityStack.push(BaseActivity.this);
        mInflater = LayoutInflater.from(this);
    }

    private void initView() {
        loadViewLayout();

    }

    private void inidata() {
        findViewById();
        processLogic();
        setListener();

    }

    public void jumpToActivity(Intent intent) {
        startActivity(intent);
    }

    public void jumpToActivity(Class activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
    }


    public void jumpToActivity(Class activity,Bundle bundle) {
        Intent intent = new Intent(this, activity);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void jumpToActivityAndClearTask(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    public void jumpToActivityAndClearTop(Class activity) {
        Intent intent = new Intent(this, activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }



    @Override
    public void onClick(View view) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityStack.pop(this);
            hideLoading();
    }

    public void showLoading() {
        hideLoading();
        if (mDiaolg==null){
            mDiaolg=new LoadingDiaolg(this);
            mDiaolg.show();
        }else {
            mDiaolg.show();
        }

    }

    public void hideLoading() {
        if (mDiaolg!=null&&mDiaolg.isShowing()){
            mDiaolg.dismiss();
            mDiaolg=null;
        }

    }

    /**
     * 获得上下文本
     * @return
     */
    protected abstract Context getActivityContext();

    /**
     * 加载界面
     */
    protected abstract void loadViewLayout();


    /**
     * 加载页面元素
     */
    protected abstract void findViewById();

    /**
     * 设置各种事件的监听器
     */
    protected abstract void setListener();

    /**
     * 业务逻辑处理，主要与后端交互
     */
    protected abstract void processLogic();



    //单选无标题弹窗
    public void showListSingleDialog(final List<String> items, final ListDialogListener listDialogListener) {
        if (items == null) return;

        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.show();

        Window window = alertDialog.getWindow();
        window.setBackgroundDrawable(new BitmapDrawable());

        WindowManager windowManager = this.getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lParams = window.getAttributes();
        if (items.size() > 10) {
            lParams.height = (int) (display.getHeight() * 0.6);
        }
        lParams.width = (int) (display.getWidth() * 0.75);
        window.setAttributes(lParams);

        View alertView = LayoutInflater.from(this).inflate(R.layout.layout_alert_dialog, null);
        window.setContentView(alertView);

        ListView alertList = (ListView) alertView.findViewById(R.id.listview_alertdialog);
        alertList.setAdapter(new DialogSingleChoiceAdapter(items, this));

        alertList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listDialogListener.itemClick(position);
                listDialogListener.itemClick(items.get(position));
                alertDialog.dismiss();
            }
        });
    }

    public interface ListDialogListener {
        void itemClick(int position);

        void itemClick(String str);
    }


}
