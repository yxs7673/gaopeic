package com.kxkg.youehu.gaopeic.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yxs on 2018/9/14.
 */

public class SpecailBean implements Serializable{

        /**
         * id : 1
         * enable : true
         * createTime : 2018-09-05 17:03:45
         * updateTime : 2018-09-05 17:03:46
         * item : 鼻饲喂食
         * price : 10
         */

        private String id;
        private String createTime;
        private String updateTime;
        private String item;
        private String price;

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

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
