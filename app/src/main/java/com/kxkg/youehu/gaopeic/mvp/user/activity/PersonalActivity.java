package com.kxkg.youehu.gaopeic.mvp.user.activity;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.kxkg.youehu.developlib.base.BaseActivity;
import com.kxkg.youehu.gaopeic.R;
import com.kxkg.youehu.gaopeic.entity.NurseInfo;

import butterknife.BindView;

public class PersonalActivity extends BaseActivity {


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
    @BindView(R.id.per_head_img)
    ImageView perHeadImg;
    @BindView(R.id.per_name_tv)
    TextView perNameTv;
    @BindView(R.id.per_phone_tv)
    TextView perPhoneTv;
    @BindView(R.id.per_rb)
    RatingBar perRb;
    @BindView(R.id.per_code_img)
    ImageView perCodeImg;
    @BindView(R.id.per_share_wx)
    TextView perShareWx;
    @BindView(R.id.per_share_circle)
    TextView perShareCircle;
    private NurseInfo nurseInfo;


    @Override
    protected Context getActivityContext() {
        return this;
    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_recommend);
    }

    @Override
    protected void findViewById() {
        titlebarTitle.setText("个人推荐");
        titlebarInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic() {
        nurseInfo= (NurseInfo) getIntent().getSerializableExtra("info");
        if (!StringUtils.isEmpty(nurseInfo.getCarerName())){
            perNameTv.setText(nurseInfo.getCarerName());
        }
        if (!StringUtils.isEmpty(nurseInfo.getMobile())){
            perPhoneTv.setText(nurseInfo.getMobile());
        }

    }
}
