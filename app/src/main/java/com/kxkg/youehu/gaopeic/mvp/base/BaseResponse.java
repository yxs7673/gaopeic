package com.kxkg.youehu.gaopeic.mvp.base;

import java.io.Serializable;

/**
 * Created by wangyuyuan on 2017/8/18.
 */

public class BaseResponse<T> implements Serializable {


    public T data;

    public int code;
    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public BaseResponse() {
    }
}
