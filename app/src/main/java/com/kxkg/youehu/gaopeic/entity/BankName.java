package com.kxkg.youehu.gaopeic.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/29.
 */

public class BankName implements Serializable{
    /**
     * id : 943341620764467202
     * enable : true
     * createTime : 2017-12-20 12:45:43
     * updateTime : 2017-12-20 12:45:43
     * code : 0104
     * name : 中国银行
     * status : 1
     * editable : 1
     */

    private String id;
    private Boolean enable;
    private String createTime;
    private String updateTime;
    private String code;
    private String name;
    private String status;
    private String editable;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEditable() {
        return editable;
    }

    public void setEditable(String editable) {
        this.editable = editable;
    }
}
