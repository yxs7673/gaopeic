package com.kxkg.youehu.gaopeic.mvp.factory;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.kxkg.youehu.developlib.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by yxs on 2018/8/27.
 */

public class FragmentController {

    private FragmentManager fm;
    private ArrayList<Fragment> fragments;
    private static FragmentController controller;
    private int currentFragmentIndex=0;




    public static FragmentController getController(FragmentActivity activity, int currentFragmentIndex){
        if (controller==null){
            controller=new FragmentController(activity, currentFragmentIndex);
        }
        return controller;
    }


    private FragmentController(FragmentActivity activity, int currentFragmentIndex) {
        this.currentFragmentIndex = currentFragmentIndex;
        fm = activity.getSupportFragmentManager();
    }



    public void initFragment() {
        fragments=new ArrayList<>();
        fragments.add(FragmentFactory.getmInstance().getOrderAllFragment());
        fragments.add(FragmentFactory.getmInstance().getBillFragment());
        fragments.add(FragmentFactory.getmInstance().getUserFragment());

        FragmentTransaction ft = fm.beginTransaction();
        for (Fragment fragment : fragments) {
            ft.add(currentFragmentIndex, fragment);
        }
        ft.commitAllowingStateLoss();
    }

    public void onDestroy() {
        if (fragments != null) {
            fragments.clear();
        }
        controller = null;
    }

    public void showFragment(int position) {
        if (position >= fragments.size()) return;
        currentFragmentIndex = position;
        hideFragments();
        Fragment fragment = fragments.get(position);
        FragmentTransaction ft = fm.beginTransaction();

        ft.show(fragment);

        ft.commitAllowingStateLoss();
    }

    public void hideFragments() {
        FragmentTransaction ft = fm.beginTransaction();
        for (Fragment fragment : fragments) {
            if (fragment != null ) {
                ft.hide(fragment);
            }
        }
        ft.commitAllowingStateLoss();
    }

    public Fragment getFragment(int position) {
        return fragments.get(position);
    }

    public BaseFragment getCurrentFragment() {
        return (BaseFragment) fragments.get(currentFragmentIndex);
    }

    public int getCurrentFragmentInedx() {
        return currentFragmentIndex;
    }



}
