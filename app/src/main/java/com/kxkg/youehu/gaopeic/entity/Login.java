package com.kxkg.youehu.gaopeic.entity;

/**
 * Created by yxs on 2018/9/10.
 */

public class Login {

    private String mobile;
    private String password;
    private String cid;


    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
