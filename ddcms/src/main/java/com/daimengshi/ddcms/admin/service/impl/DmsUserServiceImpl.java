package com.daimengshi.ddcms.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.daimengshi.ddcms.admin.model.DmsUser;
import com.daimengshi.ddcms.admin.service.DmsUserService;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import io.jboot.aop.annotation.Bean;
import io.jboot.service.JbootServiceBase;

@Bean
public class DmsUserServiceImpl extends JbootServiceBase<DmsUser> implements DmsUserService {


    /**
     * 使用帐号查询用户
     * @param account
     * @return
     */
    public DmsUser getUserByAccount(String account) {
        String sql = Db.getSql("user.getUserByAccount");
        Record mRecord = Db.findFirst(sql, account);
        DmsUser mDmsUser = JSON.parseObject(mRecord.toJson(), DmsUser.class);
        return mDmsUser;
    }
}