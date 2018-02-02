package com.daimengshi.ddcms.admin.controller;

import com.daimengshi.ddcms.pub.HtmlView;
import com.daimengshi.ddcms.service.api.DmsConfigService;
import com.daimengshi.ddcms.service.entity.model.DmsConfig;
import com.daimengshi.ddcms.utils.ServiceUtils;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;

import java.util.List;

/**
 * Created by zhoufeng on 2018/1/3.
 * 网站配置
 */
@RequestMapping("/admin/config")
public class AdminConfigController extends JbootController {
    private static final Log log = LogFactory.get();


    DmsConfigService configService;

    public AdminConfigController() {
        this.configService = ServiceUtils.getService(DmsConfigService.class);
    }

    /**
     * 后台主页
     */
    public void index() {
        List<DmsConfig> configList = configService.findAll();

        setAttr("configList", configList);
        HtmlView.adminPop(this,"网站配置","/config/index.html");

    }

}
