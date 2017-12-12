package com.daimengshi.ddcms.admin.controller;

import com.daimengshi.ddcms.admin.model.DmsMenu;
import com.daimengshi.ddcms.admin.service.impl.DmsMenuServiceImpl;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by zhoufeng on 2017/12/6.
 * 后台主页
 */
@RequestMapping("/admin")
public class AdminIndexController extends JbootController {
    private static final Log log = LogFactory.get();

    @Inject
    private DmsMenuServiceImpl menuService;

    /**
     * 主页
     */
    public void index() {
//        DmsMenu menu = new DmsMenu();
//        menu.setName("仪表盘");
//        menu.setCreateTime(new Date());
//        menu.setTypeId("1");
//        menu.setUrl("/admin");
//        menuService.save(menu);

        //获取菜单列表
        List<DmsMenu> menus = menuService.findAll();
        setAttr("menus", menus);


        setAttr("title", "后台主页");
//        setAttr("mainTP", "/htmls/admin/index/index.html");

        //调用通用模板
        renderTemplate("/htmls/admin/global.html");
//        renderJson(menus);
    }


    /**
     * 主页
     */
    public void main() {

        setAttr("title", "仪表盘");
        setAttr("mainTP", "/htmls/admin/index/index.html");
        //调用通用模板
        renderTemplate("/htmls/admin/pop.html");
    }
}
