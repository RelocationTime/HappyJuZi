package com.pro.happyjuzi.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/10/13 0013.
 */

public class InfoBean  implements BaseBean {
    private List<LuBean> info;

    public List<LuBean> getInfo() {
        return info;
    }

    public void setInfo(List<LuBean> info) {
        this.info = info;
    }
}
