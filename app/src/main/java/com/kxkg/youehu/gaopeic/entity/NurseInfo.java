package com.kxkg.youehu.gaopeic.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yxs on 2018/9/10.
 */

public class NurseInfo implements Serializable{
    /*
    *  {
                "mobile": "15168258300",
                "password":"123456",
                "carerName": "12",
                "gender": 2,
                "idcard": "330327199204052157",
                "avatar": "",
                "specialty": "",
                "licensed": "",
                "idCardUp": "",
                "idCardDown": "",
                "healthCertificate": ""
            }
    * */

    private String mobile;
    private String password;
    private String carerName;
    private String idcard;
    private String gender;
    private String avatar;
    private String specialty;
    private String licensed;
    private String idCardUp;
    private String idCardDown;
    private String healthCertificate;
    private String id;
    private String status;
    private String orderCode;
    private String accountName;
    private String bankCode;
    private String bankCardNo;
    private String audit;
    private String auditStatus;

    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    private List<OrderTendsBean> orderTends;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<OrderTendsBean> getOrderTends() {
        return orderTends;
    }

    public void setOrderTends(List<OrderTendsBean> orderTends) {
        this.orderTends = orderTends;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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

    public String getCarerName() {
        return carerName;
    }

    public void setCarerName(String carerName) {
        this.carerName = carerName;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getLicensed() {
        return licensed;
    }

    public void setLicensed(String licensed) {
        this.licensed = licensed;
    }

    public String getIdCardUp() {
        return idCardUp;
    }

    public void setIdCardUp(String idCardUp) {
        this.idCardUp = idCardUp;
    }

    public String getIdCardDown() {
        return idCardDown;
    }

    public void setIdCardDown(String idCardDown) {
        this.idCardDown = idCardDown;
    }

    public String getHealthCertificate() {
        return healthCertificate;
    }

    public void setHealthCertificate(String healthCertificate) {
        this.healthCertificate = healthCertificate;
    }
}
