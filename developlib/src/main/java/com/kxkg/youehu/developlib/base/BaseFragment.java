package com.kxkg.youehu.developlib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kxkg.youehu.developlib.util.ActivityStack;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/8/21.
 */

public abstract   class BaseFragment extends Fragment {

    private View mRootView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mRootView = initView(inflater, container,false);
        ButterKnife.bind(this, mRootView);//绑定到butterknife
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        processLogic();
        initListener();
        initData();
    }

    /**
     * 点击屏幕或者按返回键loading
     */
    public void showLoading() {
        hideLoading();
        ActivityStack.getLastActivity().showLoading();

    }

    public void hideLoading() {
        ActivityStack.getLastActivity().hideLoading();
    }

    protected abstract View initView(LayoutInflater inflater, ViewGroup container, boolean b);

    protected abstract void processLogic();

    protected abstract void initListener();

    protected abstract void initData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mRootView != null) {
            ViewGroup parentViewGroup = (ViewGroup) mRootView.getParent();
            if (parentViewGroup != null) {
                parentViewGroup.removeAllViews();
            }
        }

    }





}
