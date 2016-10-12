package com.pro.happyjuzi.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2016/10/12 0012.
 */

@Entity
public class Student {

    private String password;

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Generated(hash = 2014694434)
    public Student(String password) {
        this.password = password;
    }

    @Generated(hash = 1556870573)
    public Student() {
    }
}
