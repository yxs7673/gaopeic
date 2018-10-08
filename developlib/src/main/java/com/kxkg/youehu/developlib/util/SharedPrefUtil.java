package com.kxkg.youehu.developlib.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2018/8/24.
 */

public class SharedPrefUtil {
    private static final String PREF_GLOBAL = "pref_global";
    private volatile static SharedPrefUtil INSTANCE = null;
    private Context mContext;

    public static SharedPrefUtil getInstance(){
        if (INSTANCE==null){
            synchronized (SharedPrefUtil.class){
                if (INSTANCE==null){
                    INSTANCE=new SharedPrefUtil();
                }

            }
        }
        return INSTANCE;
    }


    public void init(Context context){
        this.mContext=context;
    }

    private SharedPreferences getSharedPref() {
        return mContext.getSharedPreferences(PREF_GLOBAL, Context.MODE_PRIVATE);
    }




    public void putString(String key, String value) {
        getSharedPref().edit().putString(key, value).apply();
    }

    public String getString(String key) {
        return getSharedPref().getString(key, null);
    }

    public void cleanString(String key){
        getSharedPref().edit().remove(key).commit();

    }


}
