package com.kxkg.youehu.gaopeic.mvp.base;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;


import com.kxkg.youehu.developlib.base.BaseActivity;
import com.kxkg.youehu.gaopeic.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/12.
 */

public class BaseDialog extends Dialog{

    /**
     * Special value for the height or width requested by a View.
     * MATCH_PARENT means that the view wants to be as big as its parent,
     * minus the parent's padding, if any. Introduced in API Level 8.
     */
    public static final int MATCH_PARENT = -1;

    /**
     * Special value for the height or width requested by a View.
     * WRAP_CONTENT means that the view wants to be just large enough to fit
     * its own internal content, taking its own padding into account.
     */
    public static final int WRAP_CONTENT = -2;

    public BaseActivity mActivity;
    public LayoutInflater mInflater;


    public BaseDialog(BaseActivity activity) {
        super(activity, R.style.FullScreenDialog);
        mActivity = activity;
        mInflater = LayoutInflater.from(mActivity);;
    }

    public BaseDialog(@NonNull Context context) {
        super(context, R.style.FullScreenDialog);
        mInflater = LayoutInflater.from(context);;

    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    /**
     * 添加Viwe 宽高度参数(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
     * @param layoutResID
     * @param layoutWidth
     * @param layoutHeight
     */
    public void setContentView(int layoutResID, int layoutWidth, int layoutHeight) {
        setContentView(layoutResID, layoutWidth, layoutHeight, true);
    }

    /**
     * 添加Viwe 宽高度参数(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
     * @param layoutResID viewID
     * @param layoutWidth 宽度
     * @param layoutHeight 高度
     * @param cancel true is click框外 dismiss
     */
    public void setContentView(int layoutResID, int layoutWidth, int layoutHeight, boolean cancel) {
        setCanceledOnTouchOutside(cancel);
        View viewRoot = mInflater.inflate(layoutResID, null);
        setContentView(viewRoot);
        getWindow().setLayout(layoutWidth, layoutHeight);
        //初始化butterKnife
        ButterKnife.bind(this);
    }

    /**
     * 添加进出场动画
     * @param anumStyleResID
     */
    protected void setAnimations(int anumStyleResID) {
        getWindow().setWindowAnimations(anumStyleResID);
    }

    /**
     * 添加相对位置
     * @param gravity
     */
    protected void setGravity(int gravity) {
        getWindow().setGravity(gravity);
    }



}
