package com.kxkg.youehu.gaopeic.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yxs on 2018/9/21.
 */

public class WageBean implements Serializable{


    /**
     * data : {"total":123,"rate":0.5,"record":[{"carerName":"后台测试","price":160,"orderCode":"HW1808243492","startTime":"2018-09-20 00:00:00","endTime":"2018-09-25 12:00:00","half":1,"score":3,"carerWage":123,"manageFee":0,"days":1,"contact":"测试","address":"血液科病一区"}]}
     * code : 20000
     */

    private DataBean data;
    private int code;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static class DataBean {
        /**
         * total : 123
         * rate : 0.5
         * record : [{"carerName":"后台测试","price":160,"orderCode":"HW1808243492","startTime":"2018-09-20 00:00:00","endTime":"2018-09-25 12:00:00","half":1,"score":3,"carerWage":123,"manageFee":0,"days":1,"contact":"测试","address":"血液科病一区"}]
         */

        private String total;
        private String rate;
        private List<OrderTendsBean> record;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public List<OrderTendsBean> getRecord() {
            return record;
        }

        public void setRecord(List<OrderTendsBean> record) {
            this.record = record;
        }


    }
}
