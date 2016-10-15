package com.pro.happyjuzi.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/13 0013.
 */

public class ResponseRecommBean {

    private Data data;

    public static class Data {
        private List<LuBean> info;
        private List<ReBean> list;

        public List<LuBean> getInfo() {
            return info;
        }

        public void setInfo(List<LuBean> info) {
            this.info = info;
        }

        public List<ReBean> getList() {
            return list;
        }

        public void setList(List<ReBean> list) {
            this.list = list;
        }
    }


    public Data getData() {
        return data;
    }


}
