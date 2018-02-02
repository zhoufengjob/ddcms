package com.daimengshi.ddcms.service.provider;

import com.daimengshi.ddcms.service.api.DmsLogService;
import com.daimengshi.ddcms.service.entity.model.DmsLog;
import com.daimengshi.ddcms.web.base.BaseServiceImpl;
import com.jfinal.plugin.activerecord.Db;
import io.jboot.aop.annotation.Bean;

import javax.inject.Singleton;

@Bean
@Singleton
public class DmsLogServiceImpl extends BaseServiceImpl<DmsLog> implements DmsLogService {


    /**
     * 删除所有日志
     *
     * @return
     */
    @Override
    public void deleteAll() {
        Db.delete("truncate table dms_log");
    }
}