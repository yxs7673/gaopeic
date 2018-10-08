package com.kxkg.youehu.developlib.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kxkg.youehu.developlib.R;


/**
 * Created by yxs on 2018/3/25.
 */

public class LoadingDiaolg extends Dialog {
    private TextView tv;

    public TextView getTv() {
        return tv;
    }

    public void setTv(TextView tv) {
        this.tv = tv;
    }

    /**
     * style很关键
     */
    public LoadingDiaolg(Context context) {
        super(context, R.style.loadingDialogStyle);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.alpha = 1.0f;//alpha在0.0f到1.0f之间。1.0完全不透明，0.0f完全透明
        lp.dimAmount = 0.9f;//dimAmount在0.0f和1.0f之间，0.0f完全不暗，1.0f全暗
        window.setAttributes(lp);
        window.setBackgroundDrawable(new BitmapDrawable());
        setContentView(R.layout.dialog_com_loading);
        tv = (TextView) findViewById(R.id.tv);
        LinearLayout linearLayout = (LinearLayout) this.findViewById(R.id.LinearLayout);
        linearLayout.getBackground().setAlpha(250);
    }

    public void setText(String title) {
        tv.setText(title);
    }
}
