package com.kxkg.youehu.gaopeic.cash;

import com.blankj.utilcode.util.StringUtils;
import com.google.gson.Gson;
import com.kxkg.youehu.developlib.util.SharedPrefUtil;
import com.kxkg.youehu.gaopeic.constant.Constant;
import com.kxkg.youehu.gaopeic.entity.User;

/**
 * Created by Administrator on 2018/8/24.
 */

public class UserCash {
    /**
     * 保存用户信息
     * @param user
     */
    public static  void putUser(User user){
        SharedPrefUtil.getInstance().putString(Constant.USER_CASH,user.toString());

    }



    /**
     * 获得用户信息
     * @return
     */
    public static User getUser(){
        String userStr=SharedPrefUtil.getInstance().getString(Constant.USER_CASH);
        if (!StringUtils.isEmpty(userStr)){
            Gson mGson =new Gson();
          //  User user=mGson.fromJson(userStr,User.class);
            return  mGson.fromJson(userStr,User.class);
        }
        return null;
    }


    public static void changeStatue(String statue){
        String userStr=SharedPrefUtil.getInstance().getString(Constant.USER_CASH);
        Gson mGson =new Gson();
        User user= mGson.fromJson(userStr,User.class);
        user.setStatus(statue);
        SharedPrefUtil.getInstance().putString(Constant.USER_CASH,user.toString());
    }

    /**
     * 获取token
     * @return
     */
    public static String getAccessToken(){
        User user=getUser();
        if (user!=null){
            return user.getToken();
        }
        return null;
    }

    /**
     * 清除用户数据
     */
    public static void cleanUser(){
        SharedPrefUtil.getInstance().cleanString(Constant.USER_CASH);
    }



}
