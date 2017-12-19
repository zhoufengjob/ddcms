package com.daimengshi.ddcms.admin.service.impl;

import com.daimengshi.ddcms.admin.model.DmsUserRole;
import com.daimengshi.ddcms.admin.service.DmsUserRoleService;
import com.jfinal.plugin.activerecord.Db;
import io.jboot.aop.annotation.Bean;
import io.jboot.service.JbootServiceBase;

@Bean
public class DmsUserRoleServiceImpl extends JbootServiceBase<DmsUserRole> implements DmsUserRoleService {
    /**
     * 通过用户ID删除角色
     * @param uid
     */
    public void deleteByUID(String uid){
        Db.delete(Db.getSql("user_role.deleteByUID"),uid);
    }
}