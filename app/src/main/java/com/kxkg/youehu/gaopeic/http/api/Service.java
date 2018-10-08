package com.kxkg.youehu.gaopeic.http.api;

import com.kxkg.youehu.gaopeic.entity.BankName;
import com.kxkg.youehu.gaopeic.entity.Common;
import com.kxkg.youehu.gaopeic.entity.Login;
import com.kxkg.youehu.gaopeic.entity.NurseInfo;
import com.kxkg.youehu.gaopeic.entity.OrderInfo;
import com.kxkg.youehu.gaopeic.entity.OrderTendsBean;
import com.kxkg.youehu.gaopeic.entity.ReceiveOrder;
import com.kxkg.youehu.gaopeic.entity.User;
import com.kxkg.youehu.gaopeic.entity.WageBean;
import com.kxkg.youehu.gaopeic.mvp.base.BaseResponse;

import java.util.HashMap;
import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Administrator on 2018/8/24.
 */

public interface Service {

    //    登陆接口
    @POST("h/api/v1/carers/login")
    Observable<BaseResponse<User>> login(@Body Login login) ;

    //    获取验证码
    @GET("manage/api/v1/verificationCode")
    Observable<BaseResponse<String>> getCode(@QueryMap HashMap<String, String> phone);

    //   验证验证码
    @POST("manage/api/v1/verificationCode ")
    Observable<BaseResponse<String>> verificationCode(@Body Common common) ;


    // 注册
    @POST("h/api/v1/carers/register")
    Observable<BaseResponse<String>> register(@Body NurseInfo nurse) ;


    // 修改护工信息
    @POST("h/api/v1/carers")
    Observable<BaseResponse<NurseInfo>> nurseInfo(@Body NurseInfo nurse) ;

    //获得当前状态
    @GET("h/api/v1/carers/info")
    Observable<BaseResponse<NurseInfo>> getUserInfo() ;

    // 护工上下工
    @POST("h/api/v1/carers/changeStatus")
    Observable<BaseResponse<String>> changeStatus();


    // 护工接单
    @POST("h/api/v1/carerOrders/orderConfirm")
    Observable<BaseResponse<String>> receiveOrder(@Body ReceiveOrder common) ;

    //护工到岗
    @POST("h/api/v1/carerOrders/workConfirm")
    Observable<BaseResponse<String>> workConfirm(@Body ReceiveOrder common) ;

    //  根据订单id查询
    @GET("h/api/v1/orders/detail/{ordercode}")
    Observable<BaseResponse<OrderInfo>> orderDetail(@Path("ordercode") String typeid);

    //  陪护记录
    @GET("h/api/v1/carerOrders/tends")
    Observable<BaseResponse<List<OrderInfo>>> tendList();

    //结算记录
    @GET("h/api/v1/carerOrders/wages")
    Observable<BaseResponse<WageBean>> wageList(@QueryMap HashMap<String, String> phone) ;


    // 修改密码
    @POST("h/api/v1/carers/changePassowrd")
    Observable<BaseResponse<String>> changePass(@Body Common common) ;

    //    获得银行卡列表
    @GET("manage/api/v1/sysDics/index/BANK")
    Observable<BaseResponse<List<BankName>>> bankName();

}
