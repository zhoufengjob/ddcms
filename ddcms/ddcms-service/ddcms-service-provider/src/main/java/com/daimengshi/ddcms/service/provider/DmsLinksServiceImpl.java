package com.daimengshi.ddcms.service.provider;

import com.daimengshi.ddcms.web.base.BaseController;
import com.daimengshi.ddcms.web.base.BaseService;
import com.jfinal.plugin.activerecord.Page;
import io.jboot.aop.annotation.Bean;
import com.daimengshi.ddcms.service.api.DmsLinksService;
import com.daimengshi.ddcms.service.entity.model.DmsLinks;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
public class DmsLinksServiceImpl extends JbootServiceBase<DmsLinks> implements DmsLinksService {

    @Override
    public Page findPage(BaseController baseController, String sqlKey, BaseService baseService) {
        return null;
    }
}