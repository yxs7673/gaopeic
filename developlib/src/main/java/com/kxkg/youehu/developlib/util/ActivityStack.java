package com.kxkg.youehu.developlib.util;

import com.kxkg.youehu.developlib.base.BaseActivity;

import java.util.Stack;

/**
 * Created by Administrator on 2018/8/24.
 */

public class ActivityStack {

    private static Stack<BaseActivity> activites = new Stack<BaseActivity>();

    private ActivityStack() {
    }

    public static void finish(BaseActivity activity) {
        if (activity != null) {
            activity.finish();
            activites.remove(activity);
            activity = null;
        }
    }

    public static BaseActivity getLastActivity() {
        if (!activites.isEmpty()) {
            return activites.lastElement();
        }
        return null;
    }

    public static void pop(BaseActivity activity) {
        if (activity != null) {
            activites.remove(activity);
        }
    }

    public static void push(BaseActivity activity) {
        if (activity != null && null != activites) {
            activites.push(activity);
        }
    }

    public static int size() {
        if (null != activites) {
            return activites.size();
        }
        return 0;
    }

    public static BaseActivity getTopActivity() {
        if (!activites.isEmpty()) {
            return activites.pop();
        }
        return null;
    }

    public static void finishAll() {
        while (!activites.isEmpty()) {
            finish(getTopActivity());
        }
        activites.clear();
    }












}
