package com.daimengshi.ddcms.admin.controller;

import com.daimengshi.ddcms.admin.interceptor.AdminInterceptor;
import com.daimengshi.ddcms.admin.service.impl.DmsMenuServiceImpl;
import com.jfinal.aop.Before;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;

import javax.inject.Inject;

/**
 * Created by zhoufeng on 2017/12/6.
 * 后台主页
 */
@Before(AdminInterceptor.class) //拦截器,获取菜单和系统配置
@RequestMapping("/admin")
public class AdminIndexController extends JbootController {
    private static final Log log = LogFactory.get();

    @Inject
    private DmsMenuServiceImpl menuService;

    /**
     * 后台主页
     */
    public void index() {


        setAttr("title", "后台主页");
//        setAttr("mainTP", "/htmls/admin/index/index.html");
        //调用通用模板
        renderTemplate("/htmls/admin/global.html");
//        renderJson(sysInfos);
    }


    /**
     * 仪表盘
     */
    public void main() {
        setAttr("title", "仪表盘");
        setAttr("mainTP", "/htmls/admin/index/index.html");
        //调用通用模板
        renderTemplate("/htmls/admin/pop.html");
    }
}
