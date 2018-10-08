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
import com.kxkg.youehu.gaopeic.R;
import com.kxkg.youehu.gaopeic.cash.UserCash;
import com.kxkg.youehu.gaopeic.constant.Constant;
import com.kxkg.youehu.gaopeic.entity.BankName;
import com.kxkg.youehu.gaopeic.entity.NurseInfo;
import com.kxkg.youehu.gaopeic.entity.User;
import com.kxkg.youehu.gaopeic.mvp.base.BaseView;
import com.kxkg.youehu.gaopeic.mvp.user.presenter.BankNamePresenter;
import com.kxkg.youehu.gaopeic.mvp.user.presenter.NurseInfoPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CashGetActivity extends BaseActivity {


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
    @BindView(R.id.cash_amount_et)
    EditText cashAmountEt;
    @BindView(R.id.cash_name_et)
    EditText cashNameEt;
    @BindView(R.id.cash_bank_tv)
    TextView cashBankTv;
    @BindView(R.id.cash_bank_ll)
    LinearLayout cashBankLl;
    @BindView(R.id.cash_no_et)
    EditText cashNoEt;
    @BindView(R.id.cash_scan_img)
    ImageView cashScanImg;
    @BindView(R.id.cash_save_btn)
    Button cashSaveBtn;
    @BindView(R.id.cash_get_btn)
    Button cashGetBtn;
    @BindView(R.id.cash_record_tv)
    TextView cashRecordTv;
    private NurseInfo nurseInfo;
    private BankNamePresenter bankNamePresenter;
    private List<BankName> bankNameList;
    private final int CHOICE_BANK = 1003;
    private BankName bankName;
    private String bankCode;
    private NurseInfoPresenter infoPresenter;
    @Override
    protected Context getActivityContext() {
        return this;
    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_cash);
    }

    @Override
    protected void findViewById() {
        titlebarTitle.setText("银行卡");
        titlebarInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void setListener() {
        cashScanImg.setOnClickListener(this);
        cashSaveBtn.setOnClickListener(this);
        cashBankLl.setOnClickListener(this);
    }

    @Override
    protected void processLogic() {
        bankNamePresenter=new BankNamePresenter(bankNameBaseView);
        infoPresenter=new NurseInfoPresenter(view,mActivity);
        bankNamePresenter.loadData();
        bankNameList = new ArrayList<>();
        nurseInfo= (NurseInfo) getIntent().getSerializableExtra("info");
        if (nurseInfo!=null){
            initBankView(nurseInfo);
        }

    }
    private BaseView<NurseInfo> view=new BaseView<NurseInfo>() {
        @Override
        public void onSuccess(NurseInfo data) {
            finish();
        }

        @Override
        public void onFail(String msg) {
            ToastUtils.showShort(Constant.REQUEST_FAIL);
        }
    };


    private void initBankView(NurseInfo nurseInfo) {
        if (!StringUtils.isEmpty(nurseInfo.getAccountName())){
            cashNameEt.setText(nurseInfo.getAccountName());
        }

        if (!StringUtils.isEmpty(nurseInfo.getCarerName())){
            cashNameEt.setText(nurseInfo.getCarerName());
        }

        if (!StringUtils.isEmpty(nurseInfo.getBankCardNo())){
            cashNoEt.setText(nurseInfo.getBankCardNo());
        }

    }


    private BaseView<List<BankName>> bankNameBaseView = new BaseView<List<BankName>>() {
        @Override
        public void onSuccess(List<BankName> data) {
            if (data != null && data.size() > 0) {
                bankNameList.addAll(data);
            }
            if (!StringUtils.isEmpty(nurseInfo.getBankCode())&&bankNameList.size()>0){
                bankCode=nurseInfo.getBankCode();
                for (BankName bean : bankNameList) {
                    if (StringUtils.equals(nurseInfo.getBankCode(), bean.getCode())) {
                        cashBankTv.setText(bean.getName());
                    }
                }

            }
        }

        @Override
        public void onFail(String msg) {
            if (StringUtils.isEmpty(msg)) {
                ToastUtils.showShort(Constant.REQUEST_FAIL);
            }
        }
    };


    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.cash_scan_img:

                break;
            case R.id.cash_save_btn:
                saveInfo();

                break;
            case R.id.cash_bank_ll:
                Intent intent = new Intent(this, ChoiceBankActivity.class);
                startActivityForResult(intent, CHOICE_BANK);
                break;

        }

    }

    /**
     * 保存信息
     */
    private void saveInfo() {
        String cardName=cashNameEt.getText().toString().trim();
        String cardNo=cashNoEt.getText().toString().trim();
        if (StringUtils.isEmpty(cardName)){
            ToastUtils.showShort("请输入姓名");
            return;
        }

        if (!StringUtils.isEmpty(nurseInfo.getCarerName())&&!StringUtils.equals(cardName,nurseInfo.getCarerName())){
            ToastUtils.showShort("银行卡姓名与身份证姓名不一致");
            return;
        }

        if (StringUtils.isEmpty(cardNo)){
            ToastUtils.showShort("请输入卡号");
            return;
        }

        if (StringUtils.isEmpty(bankCode)&&bankName==null){
            ToastUtils.showShort("请选择银行");
            return;
        }

        final NurseInfo nurseInfo=new NurseInfo();
        nurseInfo.setId(UserCash.getUser().getId());
        nurseInfo.setAccountName(cardName);
        nurseInfo.setBankCardNo(cardNo);
        if (bankName==null) {
            nurseInfo.setBankCode(bankCode);
        }else {
            nurseInfo.setBankCode(bankName.getCode());
        }

        new MyDialogHint(mActivity, R.style.MyDialogConfirm,
                "确定提交吗？", new MyDialogHint.ClickCallBack() {
            @Override
            public void onCancelClick() {

            }

            @Override
            public void onOkClick() {
                infoPresenter.loadData(nurseInfo);
            }
        }).show();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CHOICE_BANK:
                    if (data != null) {
                        bankName = (BankName) data.getSerializableExtra("name");
                        if (bankName != null) {
                            if (!StringUtils.isEmpty(bankName.getName())) {
                                cashBankTv.setText(bankName.getName());
                            }
                        }
                    }
                    break;
            }

        }
    }
}
