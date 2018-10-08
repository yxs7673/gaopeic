package com.kxkg.youehu.gaopeic.util;

import android.content.Context;

/**
 * Created by Administrator on 2017/12/19.
 */

public class WindowUtils {
    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int toPx(Context context,float dpValue) {
        float scale =context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int toDp(Context context,float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获得手机屏幕宽度得到像素px
     */
    public static int getWith(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获得手机屏幕高度得到像素px
     */
    public static int getHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }




}
