package com.kxkg.youehu.gaopeic.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/8/24.
 */

public class User implements Serializable{

    /*
    * {"data":{"id":"1040059608658219009","token":"9d31b164195d47df9c6afe58b8a81a1b","accountType":1,"status":1},"code":20000}
    * {"data":{"id":"1040059608658219009","token":"d0dc8842b4ca4c88b0c5daeb74068ff1","accountType":1,"status":1},"code":20000}
    * */


    private String id;
    private String avatar;
    private String token;
    private String accountType;
    private String status;//1 休息 2 待岗 3 待接单 4 待到岗 10 进行中


    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", avatar='" + avatar + '\'' +
                ", token='" + token + '\'' +
                ", accountType='" + accountType + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
