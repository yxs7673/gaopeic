package com.kxkg.youehu.gaopeic.entity;

import java.util.List;

/**
 * Created by yxs on 2018/9/14.
 */

public class test {


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

        private int total;
        private double rate;
        private List<RecordBean> record;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        public List<RecordBean> getRecord() {
            return record;
        }

        public void setRecord(List<RecordBean> record) {
            this.record = record;
        }

        public static class RecordBean {
            /**
             * carerName : 后台测试
             * price : 160
             * orderCode : HW1808243492
             * startTime : 2018-09-20 00:00:00
             * endTime : 2018-09-25 12:00:00
             * half : 1
             * score : 3
             * carerWage : 123
             * manageFee : 0
             * days : 1
             * contact : 测试
             * address : 血液科病一区
             */

            private String carerName;
            private int price;
            private String orderCode;
            private String startTime;
            private String endTime;
            private int half;
            private int score;
            private int carerWage;
            private int manageFee;
            private int days;
            private String contact;
            private String address;

            public String getCarerName() {
                return carerName;
            }

            public void setCarerName(String carerName) {
                this.carerName = carerName;
            }

            public int getPrice() {
                return price;
            }

            public void setPrice(int price) {
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

            public int getHalf() {
                return half;
            }

            public void setHalf(int half) {
                this.half = half;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public int getCarerWage() {
                return carerWage;
            }

            public void setCarerWage(int carerWage) {
                this.carerWage = carerWage;
            }

            public int getManageFee() {
                return manageFee;
            }

            public void setManageFee(int manageFee) {
                this.manageFee = manageFee;
            }

            public int getDays() {
                return days;
            }

            public void setDays(int days) {
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
        }
    }
}
