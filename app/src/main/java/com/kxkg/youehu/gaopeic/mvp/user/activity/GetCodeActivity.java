package com.kxkg.youehu.gaopeic.mvp.user.activity;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kxkg.youehu.developlib.base.BaseActivity;
import com.kxkg.youehu.gaopeic.R;
import com.kxkg.youehu.gaopeic.constant.Constant;
import com.kxkg.youehu.gaopeic.entity.Common;
import com.kxkg.youehu.gaopeic.mvp.base.BaseView;
import com.kxkg.youehu.gaopeic.mvp.user.presenter.GetCodePresenter;
import com.kxkg.youehu.gaopeic.mvp.user.presenter.VerificationCodePresenter;
import com.kxkg.youehu.gaopeic.util.GdpUtils;

import java.util.HashMap;

import butterknife.BindView;

public class GetCodeActivity extends BaseActivity {


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
    @BindView(R.id.get_code_et)
    EditText getCodeEt;
    @BindView(R.id.get_code_btn)
    TextView getCodeBtn;
    @BindView(R.id.code_et)
    EditText codeEt;
    @BindView(R.id.code_time_tv)
    TextView codeTimeTv;
    @BindView(R.id.input_code_ll)
    LinearLayout inputCodeLl;
    @BindView(R.id.next_btn)
    Button nextBtn;

    private String mobile;
    private String code;
    private GetCodePresenter getCodePresenter;
    private VerificationCodePresenter  verCodePresenter;
    private final  String  REGISTER="register";
    private final String CHANGE="change";

    private String type;
    @Override
    protected Context getActivityContext() {
        return this;
    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_get_code);
    }

    @Override
    protected void findViewById() {

    }

    @Override
    protected void setListener() {
        getCodeBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
    }

    @Override
    protected void processLogic() {
        titlebarTitle.setText("获取验证码");
        titlebarInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        type=getIntent().getStringExtra("type");

        getCodePresenter=new GetCodePresenter(getView);
        verCodePresenter=new VerificationCodePresenter(verView);

    }


    /**
     * 获取验证码
     */
    private BaseView<String> getView =new BaseView<String>() {
        @Override
        public void onSuccess(String data) {
            countDown();
        }

        @Override
        public void onFail(String msg) {
            ToastUtils.showShort(Constant.REQUEST_FAIL);
        }
    };

    /**
     * 验证码验证
     */
    private BaseView<String> verView=new BaseView<String>() {
        @Override
        public void onSuccess(String data) {

            String code=codeEt.getText().toString().trim();
            Intent intent=new Intent(mActivity,RegisterActivity.class);
            intent.putExtra("type",type);
            intent.putExtra("mobile",mobile);
            intent.putExtra("code",code);
            startActivity(intent);
            finish();
        }

        @Override
        public void onFail(String msg) {
            ToastUtils.showShort(Constant.REQUEST_FAIL);
        }
    };

    /**
     * 获取验证码
     */
    private void getCheckCode() {
        mobile = getCodeEt.getText().toString().trim();
        if (GdpUtils.isMobileFormat(mobile)){
            return;
        }

        HashMap<String,String> map=new HashMap<>();
        map.put("phone",mobile);
        getCodePresenter.loadData(map);
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.get_code_btn:
              //  getCheckCode();
                break;

            case R.id.next_btn:

                mobile = getCodeEt.getText().toString().trim();
                if (GdpUtils.isMobileFormat(mobile)){
                    return;
                }
                Intent intent=new Intent(mActivity,RegisterActivity.class);
                intent.putExtra("type",type);
                intent.putExtra("mobile",mobile);
                startActivity(intent);
                finish();

               // verCode();
                break;
        }
    }

    private void verCode() {

        mobile = getCodeEt.getText().toString().trim();
        code=codeEt.getText().toString().trim();
        if (GdpUtils.isMobileFormat(mobile)){
            return;
        }
        if (StringUtils.isEmpty(code)){
            ToastUtils.showShort("请输入验证码");
            return;
        }

        Common common=new Common();
        common.setMobile(mobile);
        common.setCode(code);

        verCodePresenter.loadData(common);


    }


    private void countDown() {
        getCodeBtn.setVisibility(View.GONE);
        inputCodeLl.setVisibility(View.VISIBLE);

        CountDownTimer countDownTimer=new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long l) {
                codeTimeTv.setText(l/1000+"s");
            }

            @Override
            public void onFinish() {
                getCodeBtn.setVisibility(View.VISIBLE);
                inputCodeLl.setVisibility(View.GONE);
            }
        }.start();


    }
}
