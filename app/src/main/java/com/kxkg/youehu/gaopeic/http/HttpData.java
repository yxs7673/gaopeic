package com.kxkg.youehu.gaopeic.http;





import com.kxkg.youehu.gaopeic.entity.BankName;
import com.kxkg.youehu.gaopeic.entity.Common;
import com.kxkg.youehu.gaopeic.entity.Login;
import com.kxkg.youehu.gaopeic.entity.NurseInfo;
import com.kxkg.youehu.gaopeic.entity.OrderInfo;
import com.kxkg.youehu.gaopeic.entity.OrderTendsBean;
import com.kxkg.youehu.gaopeic.entity.ReceiveOrder;
import com.kxkg.youehu.gaopeic.entity.User;
import com.kxkg.youehu.gaopeic.entity.WageBean;
import com.kxkg.youehu.gaopeic.http.api.CacheProviders;
import com.kxkg.youehu.gaopeic.http.api.Service;
import com.kxkg.youehu.gaopeic.mvp.base.BaseResponse;
import com.kxkg.youehu.gaopeic.util.FileUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import io.rx_cache.Reply;
import io.rx_cache.internal.RxCache;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by wangyuyuan on 2017/7/28.
 * * *所有的请求数据的方法集中地
 * 根据MovieService的定义编写合适的方法
 * 其中observable是获取API数据
 * observableCahce获取缓存数据
 * new EvictDynamicKey(true) false使用缓存  true 加载数据不使用缓存
 */

public class HttpData extends RetrofitUtils {


    private static File cacheDirectory = FileUtil.getcacheDirectory();
    private static final CacheProviders providers = new RxCache.Builder().persistence(
            cacheDirectory).using(CacheProviders.class);
    protected static final Service service = getRetrofit().create(Service.class);


    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final HttpData INSTANCE = new HttpData();
    }

    //获取单例
    public static HttpData getInstance() {
        return SingletonHolder.INSTANCE;
    }


    /**
     * 用来统一处理RxCacha的结果
     */
    private class HttpResultFuncCcche<T> implements Func1<Reply<T>, T> {
        @Override
        public T call(Reply<T> httpResult) {
            return httpResult.getData();
        }
    }


    /**
     * 插入观察者
     */
    public static <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io()).subscribeOn(Schedulers.newThread())//子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
    }



    //   登陆接口
    public void login(Observer<BaseResponse<User>> observer, Login login) {
        service.login(login).subscribeOn(Schedulers.newThread())//在新线程中实现该方法
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//在Android主线程中展示
                .subscribe(observer);
    }

    //   获取验证码
    public void getcode(Observer<BaseResponse<String>> observer, HashMap<String, String> phone) {
        service.getCode(phone).subscribeOn(Schedulers.newThread())//在新线程中实现该方法
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//在Android主线程中展示
                .subscribe(observer);
    }

    //   验证码验证
    public void verificationCode(Observer<BaseResponse<String>> observer, Common common) {
        service.verificationCode(common).subscribeOn(Schedulers.newThread())//在新线程中实现该方法
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//在Android主线程中展示
                .subscribe(observer);
    }

    //   修改护工信息
    public void nurseInfo(Observer<BaseResponse<NurseInfo>> observer, NurseInfo info) {
        service.nurseInfo(info).subscribeOn(Schedulers.newThread())//在新线程中实现该方法
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//在Android主线程中展示
                .subscribe(observer);
    }


    //  注册
    public void register(Observer<BaseResponse<String>> observer, NurseInfo info) {
        service.register(info).subscribeOn(Schedulers.newThread())//在新线程中实现该方法
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//在Android主线程中展示
                .subscribe(observer);
    }


    //  注册
    public void changeStatus(Observer<BaseResponse<String>> observer) {
        service.changeStatus().subscribeOn(Schedulers.newThread())//在新线程中实现该方法
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//在Android主线程中展示
                .subscribe(observer);
    }
    //获得当前状态
    public void getUserInfo(Observer<BaseResponse<NurseInfo>> observer) {
        service.getUserInfo().subscribeOn(Schedulers.newThread())//在新线程中实现该方法
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//在Android主线程中展示
                .subscribe(observer);
    }
    //获得当前状态
    public void orderDetail(Observer<BaseResponse<OrderInfo>> observer, String orderCode) {
        service.orderDetail(orderCode).subscribeOn(Schedulers.newThread())//在新线程中实现该方法
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//在Android主线程中展示
                .subscribe(observer);
    }

    //接单
    public void receiveOrder(Observer<BaseResponse<String>> observer, ReceiveOrder receiveOrder) {
        service.receiveOrder(receiveOrder).subscribeOn(Schedulers.newThread())//在新线程中实现该方法
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//在Android主线程中展示
                .subscribe(observer);
    }

    //接单
    public void workConfirm(Observer<BaseResponse<String>> observer, ReceiveOrder receiveOrder) {
        service.workConfirm(receiveOrder).subscribeOn(Schedulers.newThread())//在新线程中实现该方法
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//在Android主线程中展示
                .subscribe(observer);
    }

    //接单
    public void tendList(Observer<BaseResponse<List<OrderInfo>>> observer) {
        service.tendList().subscribeOn(Schedulers.newThread())//在新线程中实现该方法
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//在Android主线程中展示
                .subscribe(observer);
    }

    //结算记录
    public void wageList(Observer<BaseResponse<WageBean>> observer, HashMap<String, String> time) {
        service.wageList(time).subscribeOn(Schedulers.newThread())//在新线程中实现该方法
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//在Android主线程中展示
                .subscribe(observer);
    }


    //结算记录
    public void changePass(Observer<BaseResponse<String>> observer, Common common) {
        service.changePass(common).subscribeOn(Schedulers.newThread())//在新线程中实现该方法
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//在Android主线程中展示
                .subscribe(observer);
    }

    //  获得银行卡
    public void bankName(Observer<BaseResponse<List<BankName>>> observer) {
        service.bankName().subscribeOn(Schedulers.newThread())//在新线程中实现该方法
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())//在Android主线程中展示
                .subscribe(observer);
    }
}
