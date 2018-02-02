package com.daimengshi.ddcms.service.provider;


import com.alibaba.fastjson.JSON;
import com.daimengshi.ddcms.service.api.DmsUserService;
import com.daimengshi.ddcms.service.entity.model.DmsUser;
import com.daimengshi.ddcms.web.base.BaseServiceImpl;
import com.jfinal.plugin.activerecord.Db;
import io.jboot.aop.annotation.Bean;

import javax.inject.Singleton;

@Bean
@Singleton
public class DmsUserServiceImpl extends BaseServiceImpl<DmsUser> implements DmsUserService {


    @Override
    public boolean hasUser(String name) {
        return findByName(name) != null;
    }

    @Override
    public DmsUser findByName(String name) {
        return DAO.findFirstByColumn("name", name);
    }

    /**
     * 使用帐号查询用户
     * @param account
     * @return
     */
    @Override
    public DmsUser getUserByAccount(String account) {
        String sql = Db.getSql("user.getUserByAccount");
        DmsUser mRecord = getDao().findFirst(sql, account);
        DmsUser mDmsUser = JSON.parseObject(mRecord.toJson(), DmsUser.class);
        return mDmsUser;
    }



}