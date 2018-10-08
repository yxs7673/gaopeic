package com.kxkg.youehu.gaopeic.entity;

import java.io.Serializable;

/**
 * Created by yxs on 2018/9/10.
 */

public class Common implements Serializable{
    private String mobile;
    private String code;
    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
