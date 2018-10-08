package com.kxkg.youehu.developlib.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.StringUtils;
import com.kxkg.youehu.developlib.R;


public class MyDialogHint extends Dialog implements View.OnClickListener {
    private String msg;
    private ImageView loading_image;
    private AnimationDrawable animationDrawable;
    private TextView dialog_hint_text_context;
    private TextView dialog_hint_text_yes;
    private TextView dialoh_hint_text_no;
    private LinearLayout ly_ok_cancel;
    private View view;
    private ClickCallBack callBack;
    private int color;
    private Context context;
    private String no;
    private String ok;

    public interface ClickCallBack {
        void onCancelClick();

        void onOkClick();
    }

    public MyDialogHint(Context context, int theme, String msg, ClickCallBack callBack) {
        super(context, theme);
        this.msg = msg;
        this.callBack = callBack;
        this.context = context;
    }

    public MyDialogHint(Context context, int theme, int color, String msg, ClickCallBack callBack) {
        super(context, theme);
        this.msg = msg;
        this.callBack = callBack;
        this.color = color;
        this.context = context;
    }

    public MyDialogHint(Context context, int theme, String no, String ok, String msg, ClickCallBack callBack) {
        super(context, theme);
        this.msg = msg;
        this.callBack = callBack;
        this.context = context;
        this.no = no;
        this.ok = ok;
    }

    protected MyDialogHint(Context context, boolean cancelable,
                           OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public MyDialogHint(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_hint);
        initview();
        initData();
    }

    private void initData() {
        if (!StringUtils.isEmpty(no) && !StringUtils.isEmpty(ok)) {
            ly_ok_cancel.setVisibility(View.GONE);
            view.setVisibility(View.GONE);
        }
    }

    public void initview() {
        ly_ok_cancel = (LinearLayout) findViewById(R.id.ly_ok_cancel);
        view = findViewById(R.id.view);
        dialog_hint_text_context = (TextView) findViewById(R.id.dialog_hint_text_context);
        dialog_hint_text_yes = (TextView) findViewById(R.id.dialog_hint_text_yes);
        dialoh_hint_text_no = (TextView) findViewById(R.id.dialoh_hint_text_no);
        dialoh_hint_text_no.setOnClickListener(this);
        dialog_hint_text_yes.setOnClickListener(this);
        if (msg != null) {
            dialog_hint_text_context.setText(msg);
        }
        if (color != 0) {
            dialog_hint_text_yes.setTextColor(context.getResources().getColor(color));
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.dialoh_hint_text_no) {
            if (callBack != null) {
                callBack.onCancelClick();
            }
            dismiss();

        } else if (i == R.id.dialog_hint_text_yes) {
            if (callBack != null) {
                callBack.onOkClick();
            }
            dismiss();

        }
    }
}
