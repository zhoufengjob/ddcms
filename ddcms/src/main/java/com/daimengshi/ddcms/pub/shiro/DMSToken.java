package com.daimengshi.ddcms.pub.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * Created by zhoufeng on 2017/12/26.
 *
 */
public class DMSToken extends UsernamePasswordToken {
    private String uid;
    private String nikeName;

    public DMSToken(String username, String password) {
        super(username, password);
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
