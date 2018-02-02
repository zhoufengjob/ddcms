package com.daimengshi.ddcms.admin.controller;

import com.daimengshi.ddcms.pub.HtmlView;
import com.daimengshi.ddcms.service.api.DmsMenuService;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import io.jboot.core.rpc.annotation.JbootrpcService;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;

/**
 * Created by zhoufeng on 2017/12/6.
 * 后台主页
 */
@RequestMapping("/admin")
public class AdminIndexController extends JbootController {
    private static final Log log = LogFactory.get();

    @JbootrpcService
    private DmsMenuService menuService;


    /**
     * 后台主页
     */
    public void index() {
        setAttr("title", "后台主页");
        HtmlView.adminRender(this, "global.html");
    }


    /**
     * 仪表盘
     */
    public void main() {
        HtmlView.adminPop(this, "仪表盘", "/index/index.html");
    }
}
