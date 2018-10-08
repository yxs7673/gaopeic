package com.kxkg.youehu.gaopeic.mvp.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.kxkg.youehu.developlib.base.BaseActivity;
import com.kxkg.youehu.gaopeic.R;
import com.kxkg.youehu.gaopeic.mvp.base.BaseDialog;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/12.
 */

public class ChangeHeadDialog extends BaseDialog {


    @BindView(R.id.dialog_head_title)
    TextView dialogHeadTitle;
    @BindView(R.id.dialog_choice_photo)
    TextView dialogChoicePhoto;
    @BindView(R.id.head_second_btn)
    TextView headSecondBtn;
    @BindView(R.id.dialog_head_cancle)
    TextView dialogHeadCancle;
    private View.OnClickListener onClickListener;
    private Context context;

    public ChangeHeadDialog(BaseActivity activity, View.OnClickListener onClickListener) {
        super(activity);
        this.onClickListener=onClickListener;
    }

    public ChangeHeadDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public ChangeHeadDialog(@NonNull Context context, View.OnClickListener onClickListener) {
        super(context);
        this.onClickListener = onClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_change_head, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setGravity(Gravity.BOTTOM);
        setAnimations(R.style.DialogAnimBottom);
        setCanceledOnTouchOutside(true);
        initView();

    }

    private void initView() {
        dialogChoicePhoto.setOnClickListener(onClickListener);
        dialogHeadCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }
}
