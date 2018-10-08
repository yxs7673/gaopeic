package com.kxkg.youehu.gaopeic.mvp.user.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.kxkg.youehu.developlib.base.BaseActivity;
import com.kxkg.youehu.gaopeic.R;
import com.kxkg.youehu.gaopeic.constant.Constant;
import com.kxkg.youehu.gaopeic.entity.BankName;
import com.kxkg.youehu.gaopeic.mvp.base.BaseView;
import com.kxkg.youehu.gaopeic.mvp.user.presenter.BankNamePresenter;
import com.kxkg.youehu.gaopeic.util.baseViewholder.CommonAdapter;
import com.kxkg.youehu.gaopeic.util.baseViewholder.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ChoiceBankActivity extends BaseActivity {


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
    @BindView(R.id.bankname_listview)
    ListView banknameListview;

    private List<BankName> bankNameList;

    private ChoiceBankAdapter adapter;
    private BankNamePresenter present;

    @Override
    protected Context getActivityContext() {
        return this;
    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_choice_bank);
    }

    @Override
    protected void findViewById() {
        titlebarTitle.setText("银行卡列表");
        titlebarInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void setListener() {
        banknameListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BankName bankName=bankNameList.get(position);
                if (bankName!=null) {
                    Intent intent = new Intent();
                    intent.putExtra("name",bankName);
                    setResult(RESULT_OK,intent);
                    finish();
                }else {
                    ToastUtils.showShort("银行数据出错");
                }
            }
        });
    }

    @Override
    protected void processLogic() {
        bankNameList = new ArrayList<>();
        adapter = new ChoiceBankAdapter(this, bankNameList, R.layout.item_dept_name);
        banknameListview.setAdapter(adapter);
        present = new BankNamePresenter(lister);
        showLoading();
        present.loadData();
    }


    private BaseView<List<BankName>> lister = new BaseView<List<BankName>>() {
        @Override
        public void onSuccess(List<BankName> data) {
            hideLoading();
            if (data != null && data.size() > 0) {
                bankNameList.addAll(data);
            }

            adapter.notifyDataSetChanged();

        }

        @Override
        public void onFail(String msg) {
            hideLoading();
            ToastUtils.showShort(Constant.REQUEST_FAIL);
        }
    };


    public class ChoiceBankAdapter extends CommonAdapter<BankName> {

        public ChoiceBankAdapter(Context context, List<BankName> datas, int layoutId) {
            super(context, datas, layoutId);
        }

        @Override
        public void convert(ViewHolder holder, BankName item) {
            holder.setText(R.id.item_deptname_tv, item.getName());
        }
    }
}
