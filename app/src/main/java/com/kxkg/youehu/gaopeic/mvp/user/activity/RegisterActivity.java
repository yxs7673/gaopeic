package com.kxkg.youehu.gaopeic.mvp.user.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kxkg.youehu.developlib.base.BaseActivity;
import com.kxkg.youehu.developlib.dialog.MyDialogHint;
import com.kxkg.youehu.developlib.util.ActivityStack;
import com.kxkg.youehu.gaopeic.R;
import com.kxkg.youehu.gaopeic.cash.UserCash;
import com.kxkg.youehu.gaopeic.constant.Constant;
import com.kxkg.youehu.gaopeic.entity.Common;
import com.kxkg.youehu.gaopeic.entity.Login;
import com.kxkg.youehu.gaopeic.entity.NurseInfo;
import com.kxkg.youehu.gaopeic.entity.User;
import com.kxkg.youehu.gaopeic.mvp.LoginActivity;
import com.kxkg.youehu.gaopeic.mvp.MainActivity;
import com.kxkg.youehu.gaopeic.mvp.base.BaseView;
import com.kxkg.youehu.gaopeic.mvp.user.presenter.ChangePassPresenter;
import com.kxkg.youehu.gaopeic.mvp.user.presenter.LoginPresenter;
import com.kxkg.youehu.gaopeic.mvp.user.presenter.NurseInfoPresenter;
import com.kxkg.youehu.gaopeic.mvp.user.presenter.RegisterPresenter;
import com.kxkg.youehu.gaopeic.util.GdpUtils;

import butterknife.BindView;

import static com.kxkg.youehu.gaopeic.push.PushIntentService.ONRECEIVESERVICEPID;

public class RegisterActivity extends BaseActivity {


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
    @BindView(R.id.pass_fir_et)
    EditText passFirEt;
    @BindView(R.id.pass_sec_et)
    EditText passSecEt;
    @BindView(R.id.reg_cofirm)
    Button regCofirm;
    private String mobile;
    private String type;
    private RegisterPresenter registerPresenter;
    private ChangePassPresenter changePassPresenter;
    private LoginPresenter loginPresenter;

    private final  String  REGISTER="register";
    private final String CHANGE="change";
    private String code;
    @Override
    protected Context getActivityContext() {
        return this;
    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_change_pas);
    }

    @Override
    protected void findViewById() {
        titlebarTitle.setText("设置密码");
        titlebarInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    protected void setListener() {
        regCofirm.setOnClickListener(this);
    }

    @Override
    protected void processLogic() {
        mobile=getIntent().getStringExtra("mobile");
        type=getIntent().getStringExtra("type");
        code=getIntent().getStringExtra("code");
        registerPresenter=new RegisterPresenter(regView);
        changePassPresenter=new ChangePassPresenter(changeView,mActivity);
        loginPresenter=new LoginPresenter(view);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.reg_cofirm:
                if (StringUtils.isEmpty(type)){
                    return;
                }
                switch (type){
                    case REGISTER:
                        register();
                        break;
                    case CHANGE:
                        change();
                        break;
                }
                break;
        }

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



    private void change() {
        String firPass=passFirEt.getText().toString().trim();
        String secPass=passSecEt.getText().toString().trim();

        if (StringUtils.isEmpty(firPass)){
            ToastUtils.showShort("请输入密码");
            return;
        }
        if (StringUtils.isEmpty(secPass)){
            ToastUtils.showShort("请输入确认密码");
            return;
        }

        if (!StringUtils.equals(firPass,secPass)){
            ToastUtils.showShort("两次密码不一致");
            return;
        }

        if (StringUtils.isEmpty(mobile)){
            ToastUtils.showShort("手机号为空");
            return;
        }

        final Common common=new Common();
        common.setNewPassword(secPass);
        if (!StringUtils.isEmpty(code)) {
            common.setCode(code);
        }

        new MyDialogHint(mActivity, R.style.MyDialogConfirm,
                "确定修改密码吗？", new MyDialogHint.ClickCallBack() {
            @Override
            public void onCancelClick() {

            }

            @Override
            public void onOkClick() {
                changePassPresenter.loadData(common);
            }
        }).show();

    }

    private void register() {
        String firPass=passFirEt.getText().toString().trim();
        String secPass=passSecEt.getText().toString().trim();

        if (StringUtils.isEmpty(firPass)){
            ToastUtils.showShort("请输入密码");
            return;
        }
        if (StringUtils.isEmpty(secPass)){
            ToastUtils.showShort("请输入确认密码");
            return;
        }
        if (passFirEt.length()<6){
            ToastUtils.showShort("密码长度为6-20");
            return;
        }

        if (!StringUtils.equals(firPass,secPass)){
            ToastUtils.showShort("两次密码不一致");
            return;
        }

        if (StringUtils.isEmpty(mobile)){
            ToastUtils.showShort("手机号为空");
            return;
        }

        final NurseInfo nurseInfo=new NurseInfo();
        nurseInfo.setMobile(mobile);
        nurseInfo.setPassword(secPass);

        showLoading();
        registerPresenter.loadData(nurseInfo);
    }

    private BaseView<String> changeView=new BaseView<String>() {
        @Override
        public void onSuccess(String data) {
            hideLoading();
            GdpUtils.toLogin(mActivity);
        }

        @Override
        public void onFail(String msg) {
            hideLoading();
            ToastUtils.showShort(Constant.REQUEST_FAIL);
        }
    };


    private BaseView<String> regView=new BaseView<String>() {
        @Override
        public void onSuccess(String data) {
            hideLoading();
            // {"data":{"id":"1039832534832078850","enable":true,"updateTime":"2018-09-12 19:06:10","mobile":"13867114494","status":1},"code":20000}
                userLogin();
        }

        @Override
        public void onFail(String msg) {
            hideLoading();
            ToastUtils.showShort(Constant.REQUEST_FAIL);
        }
    };

    private void userLogin() {
        Login loginBean=new Login();
        String password=passSecEt.getText().toString().trim();
        loginBean.setPassword(password);
        loginBean.setMobile(mobile);
        loginBean.setCid(ONRECEIVESERVICEPID);
        loginPresenter.loadData(loginBean);
        showLoading();
    }

}
