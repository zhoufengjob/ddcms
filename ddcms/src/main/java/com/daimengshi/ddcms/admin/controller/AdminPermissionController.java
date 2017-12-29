package com.daimengshi.ddcms.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.daimengshi.ddcms.admin.model.DmsPermission;
import com.daimengshi.ddcms.admin.service.impl.DmsPermissionServiceImpl;
import com.daimengshi.ddcms.pub.ResponseData;
import com.daimengshi.ddcms.pub.TableCheckStatus;
import com.daimengshi.ddcms.pub.TablePage;
import com.daimengshi.ddcms.pub.Tools;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import com.xiaoleilu.hutool.util.StrUtil;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;
import org.slf4j.event.Level;

import javax.inject.Inject;

/**
 * 功能:权限管理
 * 小写标识:permission
 * 驼峰标识:Permission
 * 作者:zhoufeng
 */
@RequestMapping("/admin/permission")
public class AdminPermissionController extends JbootController {
    private static final Log log = LogFactory.get();

    @Inject
    DmsPermissionServiceImpl permissionService;


    /**
     * 权限管理默认页
     */
    public void index() {
        setAttr("title", "权限列表");
        setAttr("mainTP", "/htmls/admin/permission/index.html");
        //调用通用模板
        render("/htmls/admin/pop.html");
    }

    /**
     * 获取权限管理列表
     */
    public void list() {
        TablePage tablePage = Tools.pageFind(this, "permission.getPage");
        renderJson(tablePage);
    }


    /**
     * 权限管理添加页面
     */
    public void addView() {

        setAttr("formTitle", "权限管理");
        setAttr("mainTP", "/htmls/admin/permission/add.html");
        //调用通用模板
        render("/htmls/admin/pop.html");
    }

    /**
     * 权限管理添加请求
     */
    public void add() {
        String json = getBodyString();
        log.info(json, Level.INFO);

        DmsPermission permission = JSON.parseObject(json, DmsPermission.class);

        permission.setCreateTime(DateUtil.date());
        permissionService.save(permission);
        renderJson(ResponseData.ok());
    }


    /**
     * 权限管理的查看详情和编辑页面
     */
    public void editView() {
        String id = getPara("id");
        DmsPermission permission = permissionService.DAO.findByIdWithoutCache(id);
        log.info(permission.toJson());

        setAttr("permission", permission);

        setAttr("formTitle", "查看用户详情");
        setAttr("mainTP", "/htmls/admin/permission/edit.html");
        //调用通用模板
        render("/htmls/admin/pop.html");
    }

    /**
     * 权限管理的编辑菜单请求
     */
    public void edit() {
        String json = getBodyString();
        log.info(json);
        DmsPermission permission = JSON.parseObject(json, DmsPermission.class);

        if (StrUtil.isEmpty(permission.getIsOpen())) {
            permission.setIsOpen("off");
        }

        permissionService.update(permission);
        renderJson(ResponseData.ok());

    }

    /**
     * 权限管理的删除请求
     */
    public void delete() {
        String id = getPara("id", "");
        if (StrUtil.isEmpty(id)) {
            renderJson(ResponseData.apiError("权限管理的id不能为空"));
        }
        permissionService.deleteById(id);
        renderJson(ResponseData.ok());
    }

    /**
     * 权限管理的多选删除
     */
    public void deletes() {
        //获取所有请求参数
        String json = getBodyString();
        TableCheckStatus mTableCheckStatus = JSON.parseObject(json, TableCheckStatus.class);

        for (Object obj : mTableCheckStatus.getData()) {
            DmsPermission permission = JSONObject.parseObject(obj.toString(), DmsPermission.class);
            permissionService.delete(permission);
        }
        renderJson(ResponseData.ok());
    }


}
