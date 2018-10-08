package com.kxkg.youehu.gaopeic.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/9/18.
 */

public class UploadEntity {


    /**
     * data : ["/youehu/upload/9bb5f484-0c03-4bf6-84b5-aebc2af82cbd.jpg"]
     * code : 20000
     */

    private String code;
    private List<String> data;
    private String msg;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
