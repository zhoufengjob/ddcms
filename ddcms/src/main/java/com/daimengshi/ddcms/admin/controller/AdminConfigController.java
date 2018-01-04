package com.daimengshi.ddcms.admin.controller;

import com.daimengshi.ddcms.admin.interceptor.AdminInterceptor;
import com.daimengshi.ddcms.admin.model.DmsConfig;
import com.daimengshi.ddcms.admin.service.impl.DmsConfigServiceImpl;
import com.daimengshi.ddcms.pub.HtmlView;
import com.jfinal.aop.Before;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by zhoufeng on 2018/1/3.
 * 网站配置
 */
@Before(AdminInterceptor.class) //拦截器,获取菜单和系统配置
@RequestMapping("/admin/config")
public class AdminConfigController extends JbootController {
    private static final Log log = LogFactory.get();


    @Inject
    DmsConfigServiceImpl configService;

    /**
     * 后台主页
     */
    public void index() {
        List<DmsConfig> configList = configService.findAll();

        setAttr("configList", configList);
        HtmlView.adminPop(this,"网站配置","/config/index.html");

    }

}
