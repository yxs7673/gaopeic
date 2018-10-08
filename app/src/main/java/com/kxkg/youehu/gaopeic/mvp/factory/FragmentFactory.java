package com.kxkg.youehu.gaopeic.mvp.factory;

import com.kxkg.youehu.gaopeic.mvp.bill.fragment.BillFragment;
import com.kxkg.youehu.gaopeic.mvp.order.fragment.OrderAllFragment;
import com.kxkg.youehu.gaopeic.mvp.user.fragment.UserFragment;

/**
 * Created by yxs on 2018/8/27.
 */

public class FragmentFactory {

    static  FragmentFactory mInstance;

    public FragmentFactory() {

    }

    public static FragmentFactory getmInstance(){
        if (mInstance==null){
            synchronized (FragmentFactory.class){
                if (mInstance==null){
                    mInstance=new FragmentFactory();
                }
            }
        }
        return mInstance;
    }


    private OrderAllFragment orderAllFragment;
    private BillFragment billFragment;
    private UserFragment userFragment;



    public OrderAllFragment getOrderAllFragment(){
        if (orderAllFragment==null){
            synchronized (OrderAllFragment.class){
                orderAllFragment=new OrderAllFragment();
            }
        }

       return orderAllFragment;

    }


    public BillFragment getBillFragment(){
        if (billFragment==null){
            synchronized (BillFragment.class){
                billFragment=new BillFragment();
            }
        }
        return billFragment;
    }


    public UserFragment getUserFragment(){
        if (userFragment==null){
            synchronized (UserFragment.class){
                userFragment=new UserFragment();
            }
        }
        return userFragment;


    }




}
