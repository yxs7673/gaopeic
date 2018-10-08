package com.kxkg.youehu.gaopeic.mvp;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kxkg.youehu.developlib.base.BaseActivity;
import com.kxkg.youehu.developlib.util.ActivityStack;
import com.kxkg.youehu.gaopeic.R;
import com.kxkg.youehu.gaopeic.cash.UserCash;
import com.kxkg.youehu.gaopeic.constant.Constant;
import com.kxkg.youehu.gaopeic.entity.Login;
import com.kxkg.youehu.gaopeic.entity.User;
import com.kxkg.youehu.gaopeic.mvp.base.BaseView;
import com.kxkg.youehu.gaopeic.mvp.user.activity.GetCodeActivity;
import com.kxkg.youehu.gaopeic.mvp.user.presenter.LoginPresenter;

import butterknife.BindView;
import kr.co.namee.permissiongen.PermissionGen;

import static com.kxkg.youehu.gaopeic.push.PushIntentService.ONRECEIVESERVICEPID;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.phone_et)
    EditText phoneEt;
    @BindView(R.id.pawss_et)
    EditText pawssEt;
    @BindView(R.id.register_btn)
    Button registerBtn;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.forget_btn)
    TextView forgetBtn;

    //双击两次退出app
    private boolean isExit = false;
    private LoginPresenter presenter;
    private final  String  REGISTER="register";
    private final String CHANGE="change";
    private Intent intent;

    @Override
    protected Context getActivityContext() {
        return this;
    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_login);
        PermissionGen.with(LoginActivity.this)
                .addRequestCode(100)
                .permissions(
                        Manifest.permission.RECEIVE_SMS,
                        Manifest.permission.WRITE_CONTACTS,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.CALL_PHONE)
                .request();
    }

    @Override
    protected void findViewById() {
        //输入框弹出时将确定按钮顶上来
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    protected void setListener() {
        registerBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        forgetBtn.setOnClickListener(this);
    }

    @Override
    protected void processLogic() {
        presenter=new LoginPresenter(view);
    }

    private BaseView<User> view=new BaseView<User>() {
        @Override
        public void onSuccess(User data) {
            hideLoading();
            if (data!=null){
                UserCash.putUser(data);
            }
            jumpToActivity(MainActivity.class);
            finish();
        }

        @Override
        public void onFail(String msg) {
            hideLoading();
            if (StringUtils.isEmpty(msg)){
                ToastUtils.showShort(Constant.REQUEST_FAIL);
            }
        }
    };


    @Override
    public void onClick(View view) {
        super.onClick(view);

        intent=new Intent();
        switch (view.getId()){
            case R.id.register_btn:
                intent.setClass(mActivity, GetCodeActivity.class);
                intent.putExtra("type",REGISTER);
                startActivity(intent);
                break;
            case R.id.login_btn:
                login();
                break;
            case R.id.forget_btn:
                intent.setClass(mActivity, GetCodeActivity.class);
                intent.putExtra("type",CHANGE);
                startActivity(intent);
                break;
        }

    }


    /**
     * 登录
     */
    private void login() {
        Login loginBean=new Login();

        String password=pawssEt.getText().toString().trim();

        if (!StringUtils.isEmpty(phoneEt.getText().toString().trim())) {
            loginBean.setMobile(phoneEt.getText().toString().trim());
        } else {
            ToastUtils.showShort("请输入用户名");
            return;
        }
        if (password.length()<6){
            ToastUtils.showShort("密码长度为6-20");
            return;
        }
        if (StringUtils.isEmpty(password)) {
            ToastUtils.showShort("密码不能为空");
            return;
        } else {
            loginBean.setPassword(pawssEt.getText().toString().trim());
        }
        loginBean.setCid(ONRECEIVESERVICEPID);
      /*  if (StringUtils.isEmpty(ONRECEIVESERVICEPID)){
            ToastUtils.showShort("获取数据中，请重试");
        }*/
        showLoading();
        presenter.loadData(loginBean);
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
