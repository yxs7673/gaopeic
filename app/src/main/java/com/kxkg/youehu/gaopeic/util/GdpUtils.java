package com.kxkg.youehu.gaopeic.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;

import com.blankj.utilcode.util.PhoneUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.kxkg.youehu.developlib.dialog.MyDialogHint;
import com.kxkg.youehu.gaopeic.R;
import com.kxkg.youehu.gaopeic.cash.UserCash;
import com.kxkg.youehu.gaopeic.mvp.LoginActivity;

import kr.co.namee.permissiongen.PermissionGen;

/**
 * Created by yxs on 2018/9/12.
 */

public class GdpUtils {
    /**
     * 验证手机号
     *
     * @param mobileNo
     * @return true:验证失败
     * <p>
     * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
     * 联通：130、131、132、152、155、156、185、186
     * 电信：133、153、180、189、（1349卫通）
     * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
     */
    public static boolean isMobileFormat(String mobileNo) {
        if (TextUtils.isEmpty(mobileNo)) {
            ToastUtils.showShort("请输入手机号码");
            return true;
        }
        if (!mobileNo.matches("[1][3578]\\d{9}")) {
            ToastUtils.showShort("请输入正确的手机号码");
            return true;
        }
       /* if (mobileNo.length()<11){
            PrintUtil.toastMakeText("请输入正确的手机号码");
            return true;
        }*/
        return false;
    }



    public static void toLogin(Context context) {
        ToastUtils.showShort("请登录");
        if (UserCash.getUser()!=null){
            UserCash.cleanUser();
        }
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }



    /**
     * 拨打电话
     * @param context
     * @param phone
     */
    public static void callPhoneNo(final Activity context, final String phone) {

        new MyDialogHint(context, R.style.MyDialogConfirm,
                "是否拨打电话\n" + phone, new MyDialogHint.ClickCallBack() {
            @Override
            public void onCancelClick() {

            }

            @Override
            public void onOkClick() {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    PermissionGen.with(context)
                            .addRequestCode(100)
                            .permissions(
                                    Manifest.permission.CALL_PHONE)
                            .request();
                    return;
                }
                PhoneUtils.call(phone);
            }
        }).show();


    }


    /**
     * 去掉时分秒
     */
    public static String deletHour(String time) {
        String newTime = "";
        if (!StringUtils.isEmpty(time)) {
            newTime = time.substring(0, 10);
        }
        return newTime;
    }


    /**
     * 获取两个时间相差天数
     * @param startTime
     * @param endTime
     * @return
     */
    public static String daysWork(String startTime,String endTime){
        if (StringUtils.isEmpty(startTime)||StringUtils.isEmpty(endTime)){
            return "0";
        }
        Long time=TimeUtils.string2Date(endTime).getTime()- TimeUtils.string2Date(startTime).getTime();

        return (Math.round(time/1000/60/60/24))+1+"";
    }

}
