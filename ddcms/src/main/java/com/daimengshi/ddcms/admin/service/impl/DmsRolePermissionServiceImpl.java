package com.daimengshi.ddcms.admin.service.impl;

import com.jfinal.plugin.activerecord.Db;
import io.jboot.aop.annotation.Bean;
import com.daimengshi.ddcms.admin.service.DmsRolePermissionService;
import com.daimengshi.ddcms.admin.model.DmsRolePermission;
import io.jboot.service.JbootServiceBase;

@Bean
public class DmsRolePermissionServiceImpl extends JbootServiceBase<DmsRolePermission> implements DmsRolePermissionService {

    /**
     * 根据Rid删除 对应关系
     * @return
     */
    public int deleteByRid(int rid) {
        return Db.delete(Db.getSql("role_permission.deleteByRid"),rid);
    }
}