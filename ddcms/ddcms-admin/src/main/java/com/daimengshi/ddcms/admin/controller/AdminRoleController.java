package com.daimengshi.ddcms.admin.controller;


import com.alibaba.fastjson.JSON;
import com.daimengshi.ddcms.admin.support.auth.AuthUtils;
import com.daimengshi.ddcms.pub.HtmlView;
import com.daimengshi.ddcms.rest.datatable.TablePage;
import com.daimengshi.ddcms.service.api.DmsRoleService;
import com.daimengshi.ddcms.service.entity.model.DmsRole;
import com.daimengshi.ddcms.utils.ServiceUtils;
import com.daimengshi.ddcms.web.base.BaseController;
import com.daimengshi.ddcms.web.base.ResponseData;
import com.jfinal.plugin.activerecord.Page;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import io.jboot.web.controller.annotation.RequestMapping;

import java.util.List;

/**
 * 功能:角色管理
 * 小写标识:role
 * 驼峰标识:Role
 * 作者:zhoufeng
 */
@RequestMapping("/admin/role")
public class AdminRoleController extends BaseController {

    private DmsRoleService roleService;

    public AdminRoleController() {
        this.roleService = ServiceUtils.getService(DmsRoleService.class);
    }

    /**
     * 默认页(查询列表页面)
     */
    @Override
    public void index() {
        HtmlView.adminPop(this, "角色管理", "/role/roleList.html");
    }


    /**
     * 添加界面
     */
    @Override
    public void addView() {
        HtmlView.adminPop(this, "添加角色", "/role/roleAdd.html");
    }

    /**
     * 编辑界面
     */
    @Override
    public void editView() {
        String id = getPara("id");
        DmsRole role = roleService.findById(id);
        log.info(role.toJson());

        setAttr("role", role);//当前角色

        HtmlView.adminPop(this, "查看角色详情", "/role/roleEdit.html");

    }

    /**
     * 获取列表(用于ajax异步请求)
     */
    @Override
    public void getList() {
        Page pages = roleService.findPage(this, "role.getPage", roleService);

        renderJson(TablePage.dataTableFormat(pages));
    }

    /**
     * 添加(用于ajax异步请求)
     */
    @Override
    public void postAdd() {
        String json = getBodyString();
        DmsRole role = JSON.parseObject(json, DmsRole.class);

        role.setCreateTime(DateUtil.date());
        role.setLastUpdataTime(DateUtil.date());
        role.setLastUpdataAcct(AuthUtils.getLoginUser().getName());
        roleService.save(role);

        renderJson(ResponseData.ok());
    }

    /**
     * 编辑(用于ajax异步请求)
     */
    @Override
    public void postEdit() {
        String json = getBodyString();
        log.info(json);
        DmsRole role = JSON.parseObject(json, DmsRole.class);

        if (StrUtil.isEmpty(role.getIsOpen())) {
            role.setIsOpen("off");
        }

        role.setLastUpdataTime(DateUtil.date());
        role.setLastUpdataAcct(AuthUtils.getLoginUser().getName());

        roleService.saveOrUpdate(role);

        renderJson(ResponseData.ok());

    }

    /**
     * 删除一个(用于ajax异步请求)
     */
    @Override
    public void postDelete() {
        String json = getBodyString();
        DmsRole role = JSON.parseObject(json, DmsRole.class);
        if (role == null) {
            renderJson(ResponseData.apiError("角色管理的id不能为空"));
            return;
        }
        if ("admin".equals(role.getName())) {//跳过删除超级管理员
            renderJson(ResponseData.apiError("超级角色不能删除"));
            return;
        }
        roleService.deleteById(role.getId());
        renderJson(ResponseData.ok());
    }

    /**
     * 删除多个(用于ajax异步请求)
     */
    @Override
    public void postDeletes() {
        String json = getBodyString();
        List<DmsRole> roleList = JSON.parseArray(json, DmsRole.class);

        for (DmsRole permission : roleList) {
            if ("admin".equals(permission.getName())) {//跳过删除超级管理员
                continue;
            }
            roleService.deleteById(permission.getId());
        }

        renderJson(ResponseData.ok());
    }
}

