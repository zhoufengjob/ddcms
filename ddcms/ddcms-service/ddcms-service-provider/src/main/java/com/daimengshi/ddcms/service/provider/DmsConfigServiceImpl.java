package com.daimengshi.ddcms.service.provider;


import com.daimengshi.ddcms.service.api.DmsConfigService;
import com.daimengshi.ddcms.service.entity.model.DmsConfig;
import io.jboot.aop.annotation.Bean;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
public class DmsConfigServiceImpl extends JbootServiceBase<DmsConfig> implements DmsConfigService {

}