package com.kxkg.youehu.gaopeic.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yxs on 2018/9/13.
 */

public class OrderInfo implements Serializable{

        /**
         * id : 1037626525266976769
         * enable : true
         * createTime : 2018-09-06 17:00:16
         * updateTime : 2018-09-06 17:50:51
         * orderCode : HW1808245575
         * carerId : 1037285652087947266
         * carerName : 12
         * status : 2
         * reservationTime : 2018-09-05 11:38:48
         * startTime : 2018-09-05 00:00:00
         * endTime : 2018-09-10 00:00:00
         * deptId : 912235168071786498
         * address : 血液科病一区
         * contact : 测试
         * contactPhone : 18668159162
         * openId : 1
         * orderTends : [{"id":"1037626525405388801","enable":true,"createTime":"2018-09-06 17:00:16","updateTime":"2018-09-06 17:50:51","carerId":1037285652087947266,"carerName":"12","price":180,"orderCode":"HW1808245575","startTime":"2018-09-05 00:00:00","endTime":"2018-09-10 00:00:00","half":0,"status":1,"refuseReason":"不在医院无法接单","statusStr":"已拒绝"}]
         * orderPays : []
         * statusStr : 待指派
         */

        private String id;
        private String createTime;
        private String updateTime;
        private String orderCode;
        private String carerId;
        private String carerName;
        private String status;
        private String reservationTime;
        private String startTime;
        private String endTime;
        private String deptId;
        private String address;
        private String contact;
        private String contactPhone;
        private String openId;
        private String statusStr;
        private String price;
        private List<OrderTendsBean> orderTends;
        private List<?> orderPays;



    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(String reservationTime) {
        this.reservationTime = reservationTime;
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

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public List<OrderTendsBean> getOrderTends() {
        return orderTends;
    }

    public void setOrderTends(List<OrderTendsBean> orderTends) {
        this.orderTends = orderTends;
    }

    public List<?> getOrderPays() {
        return orderPays;
    }

    public void setOrderPays(List<?> orderPays) {
        this.orderPays = orderPays;
    }
}
