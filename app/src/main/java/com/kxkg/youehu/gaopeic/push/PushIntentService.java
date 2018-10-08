package com.kxkg.youehu.gaopeic.push;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.blankj.utilcode.util.StringUtils;
import com.google.gson.Gson;
import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.PushConsts;
import com.igexin.sdk.PushManager;
import com.igexin.sdk.message.FeedbackCmdMessage;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTTransmitMessage;
import com.igexin.sdk.message.SetTagCmdMessage;
import com.kxkg.youehu.gaopeic.R;
import com.kxkg.youehu.gaopeic.entity.Push;

import java.util.List;

/**
 * 继承 GTIntentService 接收来自个推的消息, 所有消息在线程中回调, 如果注册了该服务, 则务必要在 AndroidManifest中声明, 否则无法接受消息<br>
 * onReceiveMessageData 处理透传消息<br>
 * onReceiveClientId 接收 cid <br>
 * onReceiveOnlineState cid 离线上线通知 <br>
 * onReceiveCommandResult 各种事件处理回执 <br>
 */
public class PushIntentService extends GTIntentService {
    public static final String TAG = "PushIntentService";
    public static boolean online = false;
    public static     String ONRECEIVESERVICEPID;
    public PushIntentService() {
    }

    @Override
    public void onReceiveServicePid(Context context, int pid) {
        Log.d(TAG, "onReceiveServicePid -> " + pid);

    }

    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
        String appid = msg.getAppid();
        String taskid = msg.getTaskId();
        String messageid = msg.getMessageId();
        byte[] payload = msg.getPayload();
        String pkg = msg.getPkgName();
        String cid = msg.getClientId();

        // 第三方回执调用接口，actionid范围为90000-90999，可根据业务场景执行
        boolean result = PushManager.getInstance().sendFeedbackMessage(context, taskid, messageid, 90001);
        Log.d(TAG, "call sendFeedbackMessage = " + (result ? "success" : "failed"));
        Log.d(TAG, "onReceiveMessageData -> " + "appid = " + appid + "\ntaskid = " + taskid + "\nmessageid = " + messageid + "\npkg = " + pkg + "\ncid = " + cid);
        if (payload == null) {
            Log.e(TAG, "receiver payload = null");
        } else {
            String data = new String(payload);
            Log.d(TAG, "receiver payload = " + data);
            Gson gson=new Gson();
            Push push= gson.fromJson(data, Push.class);
            /*switch (push.getType()){
                case "order.new":
                    if (!StringUtils.isEmpty(getIslogin())){
                        Intent intent=new Intent();
                        intent.setClass(context, AchieveOrderActivity.class);
                        intent.putExtra("oderId", push.getData());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }else {
                        Intent intent=new Intent();
                        intent.setClass(context, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                    break;
            }*/
            notification(push);
            }
    }

    /**
     * 设置要跳转的界面
     * @param push
     */
    private void notification(Push push) {
        Intent remind = new Intent();
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this,0,remind,PendingIntent.FLAG_UPDATE_CURRENT);

        createNotification(push,resultPendingIntent);
    }

    /**
     * 生成弹出图标
     * @param push
     * @param resultPendingIntent
     */
    private void createNotification(Push push, PendingIntent resultPendingIntent) {
        Uri soundUrine;
        String content="您有新的预约订单";//推送内容
       /* if (push!=null&&!StringUtils.isEmpty(push.getData())){
            content=push.getData();
        }*/
    //    soundUrine = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.new_order);

        Notification notification ;
        notification = new Notification.Builder(this)// 建立所要创建的Notification的配置信息，并有notifyBuilder来保存。
                .setAutoCancel(true)//触摸之后，通知立即消失
                .setWhen(System.currentTimeMillis())//显示的时间
                .setSmallIcon(R.mipmap.icon)//设置通知的小图标
                .setTicker(this.getString(R.string.app_name))//设置状态栏显示的文本
                .setContentTitle("您有新消息")//设置通知的标题
                .setContentText(content)//设置通知的内容
                .setDefaults(Notification.DEFAULT_VIBRATE)//设置声音（系统默认的）
             //   .setSound(soundUrine)//设置声音（自定义的）
                .setContentIntent(resultPendingIntent).getNotification();//设置跳转的activity

        NotificationManager mNotifyMgr = (NotificationManager) this.getSystemService(this.NOTIFICATION_SERVICE);
        mNotifyMgr.notify((int) Math.random() * 100 + 1,notification);

    }



    /**
     * 返回app运行状态
     * 1:程序在前台运行
     * 2:程序在后台运行
     * 3:程序未启动
     * 注意：需要配置权限<uses-permission android:name="android.permission.GET_TASKS" />
     */
    public int getAppSatus(Context context, String pageName) {

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(20);

        //判断程序是否在栈顶
        if (list.get(0).topActivity.getPackageName().equals(pageName)) {
            return 1;
        } else {
            //判断程序是否在栈里
            for (ActivityManager.RunningTaskInfo info : list) {
                if (info.topActivity.getPackageName().equals(pageName)) {
                    return 2;
                }
            }
            return 3;//栈里找不到，返回3
        }
    }





    @Override
    public void onReceiveClientId(Context context, String clientid) {
        Log.e(TAG, "onReceiveClientId -> " + "cid = " + clientid);
        ONRECEIVESERVICEPID=clientid+"";

    }

    @Override
    public void onReceiveOnlineState(Context context, boolean online) {
        this.online = online;
        Log.d(TAG, "onReceiveOnlineState -> " + (online ? "online" : "offline"));
    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage cmdMessage) {
        Log.d(TAG, "onReceiveCommandResult -> " + cmdMessage);
        int action = cmdMessage.getAction();
        if (action == PushConsts.SET_TAG_RESULT) {
            setTagResult((SetTagCmdMessage) cmdMessage);
        } else if ((action == PushConsts.THIRDPART_FEEDBACK)) {
            feedbackResult((FeedbackCmdMessage) cmdMessage);
        }
    }

    private void setTagResult(SetTagCmdMessage setTagCmdMsg) {
        String sn = setTagCmdMsg.getSn();
        String code = setTagCmdMsg.getCode();

        String text = "设置标签失败, 未知异常";
        switch (Integer.valueOf(code)) {
            case PushConsts.SETTAG_SUCCESS:
                text = "设置标签成功";
                break;

            case PushConsts.SETTAG_ERROR_COUNT:
                text = "设置标签失败, tag数量过大, 最大不能超过200个";
                break;

            case PushConsts.SETTAG_ERROR_FREQUENCY:
                text = "设置标签失败, 频率过快, 两次间隔应大于1s且一天只能成功调用一次";
                break;

            case PushConsts.SETTAG_ERROR_REPEAT:
                text = "设置标签失败, 标签重复";
                break;

            case PushConsts.SETTAG_ERROR_UNBIND:
                text = "设置标签失败, 服务未初始化成功";
                break;

            case PushConsts.SETTAG_ERROR_EXCEPTION:
                text = "设置标签失败, 未知异常";
                break;

            case PushConsts.SETTAG_ERROR_NULL:
                text = "设置标签失败, tag 为空";
                break;

            case PushConsts.SETTAG_NOTONLINE:
                text = "还未登陆成功";
                break;

            case PushConsts.SETTAG_IN_BLACKLIST:
                text = "该应用已经在黑名单中,请联系售后支持!";
                break;

            case PushConsts.SETTAG_NUM_EXCEED:
                text = "已存 tag 超过限制";
                break;

            default:
                break;
        }
        Log.d(TAG, "settag result sn = " + sn + ", code = " + code + ", text = " + text);
    }

    private void feedbackResult(FeedbackCmdMessage feedbackCmdMsg) {
        String appid = feedbackCmdMsg.getAppid();
        String taskid = feedbackCmdMsg.getTaskId();
        String actionid = feedbackCmdMsg.getActionId();
        String result = feedbackCmdMsg.getResult();
        long timestamp = feedbackCmdMsg.getTimeStamp();
        String cid = feedbackCmdMsg.getClientId();
        Log.d(TAG, "onReceiveCommandResult -> " + "appid = " + appid + "\ntaskid = " + taskid + "\nactionid = " + actionid + "\nresult = " + result + "\ncid = " + cid + "\ntimestamp = " + timestamp);
    }
}