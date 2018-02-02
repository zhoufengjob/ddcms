package com.daimengshi.ddcms.service.provider;


import com.daimengshi.ddcms.service.api.DmsMenuService;
import com.daimengshi.ddcms.service.entity.model.DmsMenu;
import com.daimengshi.ddcms.web.base.BaseServiceImpl;
import io.jboot.aop.annotation.Bean;

import javax.inject.Singleton;

@Bean
@Singleton
public class DmsMenuServiceImpl extends BaseServiceImpl<DmsMenu> implements DmsMenuService {

}