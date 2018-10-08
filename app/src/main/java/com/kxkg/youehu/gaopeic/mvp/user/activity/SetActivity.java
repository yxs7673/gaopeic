package com.kxkg.youehu.gaopeic.mvp.user.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kxkg.youehu.developlib.base.BaseActivity;
import com.kxkg.youehu.developlib.dialog.MyDialogHint;
import com.kxkg.youehu.developlib.util.ActivityStack;
import com.kxkg.youehu.gaopeic.R;
import com.kxkg.youehu.gaopeic.cash.UserCash;
import com.kxkg.youehu.gaopeic.mvp.LoginActivity;

import butterknife.BindView;

public class SetActivity extends BaseActivity {


    @BindView(R.id.titlebar_info)
    ImageView titlebarInfo;
    @BindView(R.id.titlebar_title)
    TextView titlebarTitle;
    @BindView(R.id.titlebar_select)
    TextView titlebarSelect;
    @BindView(R.id.titlebar_more)
    TextView titlebarMore;
    @BindView(R.id.title_bar_ll)
    LinearLayout titleBarLl;
    @BindView(R.id.ll_set_change)
    LinearLayout llSetChange;
    @BindView(R.id.ll_set_about)
    LinearLayout llSetAbout;
    @BindView(R.id.ll_set_update)
    LinearLayout llSetUpdate;
    @BindView(R.id.log_out_btn)
    Button logOutBtn;

    private final String CHANGE="change";

    @Override
    protected Context getActivityContext() {
        return this;
    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_set);
    }

    @Override
    protected void findViewById() {
        titlebarTitle.setText("系统设置");
        titlebarInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void setListener() {
        llSetChange.setOnClickListener(this);
        llSetAbout.setOnClickListener(this);
        llSetUpdate.setOnClickListener(this);
        logOutBtn.setOnClickListener(this);
    }

    @Override
    protected void processLogic() {

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.ll_set_change:
                Intent intent=new Intent(mActivity,GetCodeActivity.class);
                intent.putExtra("type",CHANGE);
                startActivity(intent);
                break;
            case R.id.ll_set_about:
               // jumpToActivity(AboutActivity.class);
                break;
            case R.id.ll_set_update:

                break;
            case R.id.log_out_btn:
                logout();
                break;
        }


    }


    private void logout() {
        new MyDialogHint(mActivity, R.style.MyDialogConfirm,
                "确定退出登录吗？", new MyDialogHint.ClickCallBack() {
            @Override
            public void onCancelClick() {

            }

            @Override
            public void onOkClick() {
                UserCash.cleanUser();
                ActivityStack.finishAll();
                Intent intent = new Intent(mActivity, LoginActivity.class);
               startActivity(intent);
            }
        }).show();

    }
}
