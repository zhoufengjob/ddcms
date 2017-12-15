package com.daimengshi.ddcms.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.daimengshi.ddcms.admin.interceptor.AdminInterceptor;
import com.daimengshi.ddcms.admin.model.DmsMenu;
import com.daimengshi.ddcms.admin.model.DmsMenuType;
import com.daimengshi.ddcms.admin.service.impl.DmsMenuServiceImpl;
import com.daimengshi.ddcms.admin.service.impl.DmsMenuTypeServiceImpl;
import com.daimengshi.ddcms.pub.*;
import com.jfinal.kit.HttpKit;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import com.xiaoleilu.hutool.util.StrUtil;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;
import org.slf4j.event.Level;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 * Created by zhoufeng on 2017/12/6.
 * 后台主页
 */
@RequestMapping("/admin/menu")
public class AdminMenuController extends JbootController {
    private static final Log log = LogFactory.get();
    @Inject
    private DmsMenuServiceImpl menuService;
    @Inject
    private DmsMenuTypeServiceImpl menuTypeService;

    /**
     * 菜单管理
     */
    public void index() {
        //获取菜单列表
        List<DmsMenu> menus = menuService.findAll();
        setAttr("menus", menus);
        setAttr("title", "菜单列表");
        setAttr("mainTP", "/htmls/admin/menu/index.html");

        //调用通用模板
//        renderTemplate("/htmls/admin/global.html");
        //调用通用模板
        renderTemplate("/htmls/admin/pop.html");
    }


    /**
     * 获取菜单列表
     */
    public void list() {
        //获取所有请求参数
        Map<String, String[]> reqParas = getParaMap();
        //获取数据表格请求
        TableDataRequest tableDataRequest = getBean(TableDataRequest.class, "");

        TablePage tablePage = Tools.pageFind(this, "menu.getPage");
        renderJson(tablePage);
    }

    /**
     * 添加菜单
     */
    public void add() {
        List<DmsMenu> menuList = menuService.findAll();
        List<DmsMenuType> menuTypeList = menuTypeService.findAll();

        DmsMenu superMenu = new DmsMenu();
        superMenu.setId(0);
        superMenu.setName("顶级");
        superMenu.setSuperId(0);
        menuList.add(0, superMenu);

        setAttr("menuList", menuList);
        setAttr("menuTypeList", menuTypeList);


        setAttr("formTitle", "添加菜单");
        setAttr("mainTP", "/htmls/admin/menu/add.html");
        //调用通用模板
        renderTemplate("/htmls/admin/pop.html");
    }

    /**
     * 查看详情和编辑
     */
    public void edit() {
        String id = getPara("id");
        DmsMenu menu = menuService.DAO.findByIdWithoutCache(id);
        DmsMenuType menuType = menu.getMenuType();

        List<DmsMenu> subMenus = menu.getSubDmsMenu();

        log.info(menu.toJson());

        List<DmsMenu> menuList = menuService.findAll();
        List<DmsMenuType> menuTypeList = menuTypeService.findAll();
        DmsMenu superMenu = menu.getSuperDmsMenu();

        DmsMenu defaultMenu = new DmsMenu();
        defaultMenu.setId(0);
        defaultMenu.setName("顶级");
        defaultMenu.setSerialNum(0);
        defaultMenu.setSuperId(0);
        menuList.add(0, defaultMenu);

        if (superMenu == null) {
            superMenu = defaultMenu;
        }

        setAttr("menu", menu);
        setAttr("superMenu", superMenu);
        setAttr("menuList", menuList);
        setAttr("menuTypeList", menuTypeList);

        setAttr("formTitle", "查看菜单详情");
        setAttr("mainTP", "/htmls/admin/menu/edit.html");
        //调用通用模板
        renderTemplate("/htmls/admin/pop.html");
    }


    /**
     * 添加菜单
     */
    public void addMenu() {
        String json = HttpKit.readData(getRequest());
        log.info(json, Level.INFO);

        DmsMenu menu = JSON.parseObject(json, DmsMenu.class);

        if (StrUtil.isEmpty(menu.getIsOpen())) {
            menu.setIsOpen("off");
        }
        if (StrUtil.isEmpty(menu.getUrl()) || "无".equals(menu.getUrl())) {
            menu.setUrl(null);
        }

        menu.setCreateTime(DateUtil.date());
        menuService.save(menu);
        AdminInterceptor.menus = null;
        renderJson(ResponseData.ok());
    }

    /**
     * 编辑菜单
     */
    public void editdMenu() {
        String json = HttpKit.readData(getRequest());
        log.info("\n" + json);
        DmsMenu menu = JSON.parseObject(json, DmsMenu.class);

        if (StrUtil.isEmpty(menu.getIsOpen())) {
            menu.setIsOpen("off");
        }

        if (StrUtil.isEmpty(menu.getUrl()) || "无".equals(menu.getUrl())) {
            menu.setUrl(null);
        }

        menuService.update(menu);
        AdminInterceptor.menus = null;
        renderJson(ResponseData.ok());

    }


    /**
     * 删除菜单
     */

    public void deleteMenu() {
        String id = getPara("id", "");

        if (StrUtil.isEmpty(id)) {
            renderJson(ResponseData.apiError("id不能为空"));
        }
        menuService.deleteById(id);
        AdminInterceptor.menus = null;
        renderJson(ResponseData.ok());
    }

    /**
     * 多选删除
     */
    public void deleteMenus() {
        //获取所有请求参数
        String json = HttpKit.readData(getRequest());
        TableCheckStatus mTableCheckStatus = JSON.parseObject(json, TableCheckStatus.class);

        for (Object obj : mTableCheckStatus.getData()) {
            DmsMenu menu = JSONObject.parseObject(obj.toString(), DmsMenu.class);
            menuService.delete(menu);
        }
        AdminInterceptor.menus = null;
        renderJson(ResponseData.ok());
    }

}
