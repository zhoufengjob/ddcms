package com.daimengshi.ddcms.config;

import io.jboot.config.annotation.PropertieConfig;

/**
 * Created by zhoufeng on 2017/12/5.
 * 自定义配置
 */
@PropertieConfig(prefix="daimengshi.config")
public class MyConfigModel{
    private String name;
    private String password;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
