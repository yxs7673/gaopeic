package com.kxkg.youehu.gaopeic.entity;

/**
 * Created by yxs on 2018/9/14.
 */

public class ReceiveOrder {

    private String  tendId;//陪护记录id
    private int  result;//1接单，0删除
    private String  reason;//陪护记录id


    public String getTendId() {
        return tendId;
    }

    public void setTendId(String tendId) {
        this.tendId = tendId;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
