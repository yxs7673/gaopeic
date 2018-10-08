package com.kxkg.youehu.gaopeic.http.retrofit;


import com.kxkg.youehu.gaopeic.http.basereponse.Control;

/**
 * Created by wangyuyuan on 2017/7/28.
 */

public class MovieBaseResponse<T> {

    public Control control;
    public int status;
    public T data;

    public Control getControl() {
        return control;
    }

    public MovieBaseResponse() {
    }

    public void setControl(Control control) {
        this.control = control;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
