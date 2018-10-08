package com.youehu.android.getui;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import com.igexin.sdk.PushManager;

/**
 * 个推推送实例
 * Created by wanglei on 2016/11/25.
 */
public class GetuiPushInstance {

    private static final int REQUEST_PERMISSION = 911;

    /**
     * 初始化个推推送
     * @param activity 当前Activity
     * @param pushIntentServiceClass 要接收推送的ServiceClass
     */
    public static void init(Activity activity, Class pushIntentServiceClass) {
        PackageManager pkgManager = activity.getPackageManager();
        // 读写 sd card 权限非常重要, android6.0默认禁止的, 建议初始化之前就弹窗让用户赋予该权限
        boolean sdCardWritePermission = pkgManager.checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, activity.getPackageName()) == PackageManager.PERMISSION_GRANTED;
        // read phone state用于获取 imei 设备信息
        boolean phoneSatePermission = pkgManager.checkPermission(Manifest.permission.READ_PHONE_STATE, activity.getPackageName()) == PackageManager.PERMISSION_GRANTED;
        if (Build.VERSION.SDK_INT >= 23 && !sdCardWritePermission || !phoneSatePermission) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE}, REQUEST_PERMISSION);
        } else {
            PushManager.getInstance().initialize(activity, GetuiPushService.class);
        }
        PushManager.getInstance().registerPushIntentService(activity, pushIntentServiceClass);
    }


    /**
     * 权限回调处理 在6.0版会使用到
     * @param activity
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    public static void onRequestPermissionsResult(Activity activity, int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION) {
            if ((grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
                PushManager.getInstance().initialize(activity, GetuiPushService.class);
            } else {
                PushManager.getInstance().initialize(activity, GetuiPushService.class);
            }
        }
    }

    /**
     * 停止推送
     * @param activity
     */
    public static void stopPush(Activity activity) {
        PushManager.getInstance().stopService(activity);// 当前为运行状态，停止SDK服务
    }

    /**
     * 重新开启推送
     * @param activity
     */
    public static void rstartPush(Activity activity) {
        PushManager.getInstance().initialize(activity, GetuiPushService.class); // 重新初始化SDK服务
    }
}
