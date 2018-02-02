package com.daimengshi.ddcms.admin.controller;


import com.alibaba.fastjson.JSON;
import com.daimengshi.ddcms.admin.interceptor.AdminInterceptor;
import com.daimengshi.ddcms.admin.support.auth.AuthUtils;
import com.daimengshi.ddcms.pub.HtmlView;
import com.daimengshi.ddcms.service.api.DmsMenuService;
import com.daimengshi.ddcms.service.entity.model.DmsMenu;
import com.daimengshi.ddcms.utils.ServiceUtils;
import com.daimengshi.ddcms.web.base.BaseController;
import com.daimengshi.ddcms.web.base.ResponseData;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import io.jboot.web.controller.annotation.RequestMapping;

import java.util.List;

/**
 * Created by zhoufeng on 2017/12/6.
 * 后台主页
 */
@RequestMapping("/admin/menu")
public class AdminMenuController extends BaseController {
    private DmsMenuService menuService;

    public AdminMenuController() {
        menuService = ServiceUtils.getService(DmsMenuService.class);
    }

    /**
     * 默认页(查询列表页面)
     */
    @Override
    public void index() {
//        //获取菜单列表
//        List<DmsMenu> menus = menuService.findAll();
//        setAttr("menus", menus);
//        setAttr("title", "菜单列表");
//        setAttr("mainTP", "/htmls/admin/layui/menu/index.html");
//
//        //调用通用模板
////        render("/htmls/admin/global.html");
//        //调用通用模板
//        render("/htmls/admin/layui/pop.html");

        HtmlView.adminPop(this, "管理员管理", "menu/menuList.html");

    }

    /**
     * 添加界面
     */
    @Override
    public void addView() {
        List<DmsMenu> menuList = menuService.findAll();

        DmsMenu superMenu = new DmsMenu();
        superMenu.setName("顶级");
        superMenu.setSuperId(0);
        menuList.add(0, superMenu);

        setAttr("menuList", menuList);

        setAttr("formTitle", "添加菜单");
        setAttr("mainTP", "/htmls/admin/layui/menu/add.html");
        //调用通用模板
        HtmlView.adminPop(this, "管理员管理", "menu/menuAdd.html");
    }

    /**
     * 编辑界面
     */
    @Override
    public void editView() {
        int id = getParaToInt("id");
        DmsMenu menu = menuService.findById(id);
        log.info(menu.toJson());

        List<DmsMenu> menuList = menuService.findAll();
        DmsMenu superMenu = menu.getSuperDmsMenu();

        DmsMenu defaultMenu = new DmsMenu();
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

//        HtmlView.adminPop(this, "查看菜单详情", "menu/edit.html");
        HtmlView.adminPop(this, "管理员管理", "menu/menuEdit.html");

    }

    /**
     * 获取列表(用于ajax异步请求)
     */
    @Override
    public void getList() {

        List<DmsMenu> menuList = menuService.findAll();
        setAttr("menuList", menuList);
        renderJson(menuList);
    }

    /**
     * 添加(用于ajax异步请求)
     */
    @Override
    public void postAdd() {
        String json = getBodyString();

        DmsMenu menu = JSON.parseObject(json, DmsMenu.class);

        if (StrUtil.isEmpty(menu.getIsOpen())) {
            menu.setIsOpen("off");
        }
        if (StrUtil.isEmpty(menu.getUrl()) || "无".equals(menu.getUrl())) {
            menu.setUrl(null);
        }
        if (menu.getSuperId() == null) {
            menu.setSuperId(0);
        }

        menu.setCreateTime(DateUtil.date());
        menu.setLastUpdataTime(DateUtil.date());
        menu.setLastUpdataAcct(AuthUtils.getLoginUser().getName());
        menuService.save(menu);
        AdminInterceptor.menus = null;
        renderJson(ResponseData.ok());
    }

    /**
     * 编辑(用于ajax异步请求)
     */
    @Override
    public void postEdit() {
        String json = getBodyString();
        log.info("\n" + json);
        DmsMenu menu = JSON.parseObject(json, DmsMenu.class);

        if (StrUtil.isEmpty(menu.getIsOpen())) {
            menu.setIsOpen("off");
        }

        if (StrUtil.isEmpty(menu.getUrl()) || "无".equals(menu.getUrl())) {
            menu.setUrl(null);
        }

        menu.setLastUpdataTime(DateUtil.date());
        menu.setLastUpdataAcct(AuthUtils.getLoginUser().getName());
        menuService.update(menu);
        AdminInterceptor.menus = null;
        renderJson(ResponseData.ok());

    }

    /**
     * 删除一个(用于ajax异步请求)
     */
    @Override
    public void postDelete() {
        String json = getBodyString();
        DmsMenu menu = JSON.parseObject(json, DmsMenu.class);
        if (menu == null) {
            renderJson(ResponseData.apiError("id不能为空"));
            return;
        }
        List<DmsMenu> subMenuList = menu.getSubDmsMenu();
        if (subMenuList.size() >0) {
            renderJson(ResponseData.apiError("请先删除子菜单"));
            return;
        }
        menuService.deleteById(menu.getId());
        AdminInterceptor.menus = null;
        renderJson(ResponseData.ok());

    }

    /**
     * 删除多个(用于ajax异步请求)
     */
    @Override
    public void postDeletes() {
        String json = getBodyString();
        List<DmsMenu> menus = JSON.parseArray(json, DmsMenu.class);

        for (DmsMenu menu : menus) {
            menuService.delete(menu);
        }
        AdminInterceptor.menus = null;
        renderJson(ResponseData.ok());

    }
}
