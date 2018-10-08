package com.kxkg.youehu.gaopeic.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yxs on 2018/9/13.
 */

public class OrderTendsBean implements Serializable{
    /**
     * id : 1037626525405388801
     * enable : true
     * createTime : 2018-09-06 17:00:16
     * updateTime : 2018-09-06 17:50:51
     * carerId : 1037285652087947266
     * carerName : 12
     * price : 180
     * orderCode : HW1808245575
     * startTime : 2018-09-05 00:00:00
     * endTime : 2018-09-10 00:00:00
     * half : 0
     * status : 1
     * refuseReason : 不在医院无法接单
     * statusStr : 已拒绝
     */

    private String id;
    private String createTime;
    private String updateTime;
    private String carerId;
    private String carerName;
    private String price;
    private String orderCode;
    private String startTime;
    private String endTime;
    private String half;
    private String status;
    private String refuseReason;
    private String statusStr;
    private String address;
    private List<SpecailBean> specialCares;
    private OrderInfo order;
    private String days;
    private String contact;
    private String score;
    private String carerWage;
    private String manageFee;


    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCarerWage() {
        return carerWage;
    }

    public void setCarerWage(String carerWage) {
        this.carerWage = carerWage;
    }

    public String getManageFee() {
        return manageFee;
    }

    public void setManageFee(String manageFee) {
        this.manageFee = manageFee;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public OrderInfo getOrder() {
        return order;
    }

    public void setOrder(OrderInfo order) {
        this.order = order;
    }

    public List<SpecailBean> getSpecialCares() {
        return specialCares;
    }

    public void setSpecialCares(List<SpecailBean> specialCares) {
        this.specialCares = specialCares;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCarerId() {
        return carerId;
    }

    public void setCarerId(String carerId) {
        this.carerId = carerId;
    }

    public String getCarerName() {
        return carerName;
    }

    public void setCarerName(String carerName) {
        this.carerName = carerName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getHalf() {
        return half;
    }

    public void setHalf(String half) {
        this.half = half;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRefuseReason() {
        return refuseReason;
    }

    public void setRefuseReason(String refuseReason) {
        this.refuseReason = refuseReason;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }
}
