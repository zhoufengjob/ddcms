package com.daimengshi.ddcms.service.provider;


import com.daimengshi.ddcms.service.api.DmsUserRoleService;
import com.daimengshi.ddcms.service.entity.model.DmsUserRole;
import com.daimengshi.ddcms.web.base.BaseServiceImpl;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;
import io.jboot.aop.annotation.Bean;

import javax.inject.Singleton;

@Bean
@Singleton
public class DmsUserRoleServiceImpl extends BaseServiceImpl<DmsUserRole> implements DmsUserRoleService {
    @Override
    public DmsUserRole findFirstByUID(String uid) {
        Kv paras = Kv.by("uid", uid);
        SqlPara sqlPara = getDao().getSqlPara("user_role.findFirstByUID", paras);
        return getDao().findFirst(sqlPara);
    }

    /**
     * 通过用户ID删除角色
     *
     * @param uid
     */
    @Override
    public void deleteByUID(String uid) {
        Db.delete(Db.getSql("user_role.deleteByUID"), uid);
    }
}