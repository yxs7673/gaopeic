package com.kxkg.youehu.gaopeic.constant;

/**
 * Created by Administrator on 2018/8/24.
 */

public class Constant {
    public final static  boolean isDebug = true;


    public static final String TOKEN_USER = "token_user";
    public static final String USER_CASH = "user_cash";


    //    服务器地址
    public static final String API_SERVER = "http://www.youehu.com";
    //    开发服务器地址
    public static final String TEST_SERVER = "http://192.168.17.177:8800";
    //public static final String TEST_SERVER = "http://192.168.6.140:8800";
    //    服务协议静态地址
    public static final String SERVICE_URL = "http://www.youehu.com/";

    public static final String URL = isDebug ? TEST_SERVER : API_SERVER;



    public static final String REQUEST_SUCCESS = "20000";//请求成功
    public static final String REQUEST_OVERTIME = "42007";//token过期
    public static final String REQUEST_NODATA = "40000";//
    public static final String REQUEST_FAIL = "获取失败，请检查网络是否连接是否正确";//token过期

}
