package com.daimengshi.ddcms.admin.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.daimengshi.ddcms.admin.model.DmsPermission;
import com.daimengshi.ddcms.admin.model.DmsRole;
import com.daimengshi.ddcms.admin.model.DmsRolePermission;
import com.daimengshi.ddcms.admin.service.impl.DmsPermissionServiceImpl;
import com.daimengshi.ddcms.admin.service.impl.DmsRolePermissionServiceImpl;
import com.daimengshi.ddcms.admin.service.impl.DmsRoleServiceImpl;
import com.daimengshi.ddcms.pub.ResponseData;
import com.daimengshi.ddcms.pub.TableCheckStatus;
import com.daimengshi.ddcms.pub.TablePage;
import com.daimengshi.ddcms.pub.Tools;
import com.jfinal.kit.HttpKit;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import com.xiaoleilu.hutool.util.StrUtil;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;
import org.slf4j.event.Level;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 功能:角色管理
 * 小写标识:role
 * 驼峰标识:Role
 * 作者:zhoufeng
 */
@RequestMapping("/admin/role")
public class AdminRoleController extends JbootController {
    private static final Log log = LogFactory.get();

    @Inject
    DmsRoleServiceImpl roleService;

    @Inject
    DmsPermissionServiceImpl permissionServiceImpl;

    @Inject
    DmsRolePermissionServiceImpl rolePermissionService;


    /**
     * 角色管理默认页
     */
    public void index() {
        setAttr("title", "角色管理");
        setAttr("mainTP", "/htmls/admin/role/index.html");
        //调用通用模板
        renderTemplate("/htmls/admin/pop.html");
    }

    /**
     * 获取角色管理列表
     */
    public void list() {
        TablePage tablePage = Tools.pageFind(this, "role.getPage");
        renderJson(tablePage);
    }


    /**
     * 角色管理添加页面
     */
    public void addView() {

        setAttr("formTitle", "角色管理");
        setAttr("mainTP", "/htmls/admin/role/add.html");
        //调用通用模板
        renderTemplate("/htmls/admin/pop.html");
    }

    /**
     * 角色管理添加请求
     */
    public void add() {
        String json = HttpKit.readData(getRequest());
        log.info(json, Level.INFO);

        DmsRole role = JSON.parseObject(json, DmsRole.class);

        role.setCreateTime(DateUtil.date());
        roleService.save(role);


        JSONObject tableValue = JSON.parseObject(json).getJSONObject("tableValue");

        if (tableValue != null) {
            JSONArray dataList = tableValue.getJSONArray("data");
            if (dataList.size() > 0) {
                //删除以前的
                rolePermissionService.deleteByRid(role.getId());

                for (int i = 0; i < dataList.size(); i++) {
                    JSONObject obj = (JSONObject) dataList.get(i);
                    DmsRolePermission rolePermission = new DmsRolePermission();
                    rolePermission.setPid(obj.getInteger("id"));
                    rolePermission.setRid(role.getId());
                    rolePermission.setCreateTime(new Date());
                    rolePermissionService.save(rolePermission);
                }
            }
        }

        renderJson(ResponseData.ok());
    }


    /**
     * 角色管理的查看详情和编辑页面
     */
    public void editView() {
        String id = getPara("id");
        DmsRole role = roleService.DAO.findByIdWithoutCache(id);
        log.info(role.toJson());

        //找出当前角色的所有权限
        List<DmsRolePermission> roleRolePermissions = role.getRolePermissions();
        List<DmsPermission> permissionList = new ArrayList<>();
        for (DmsRolePermission rolePermission : roleRolePermissions) {
            List<DmsPermission> permissions = rolePermission.getUserRolePermissions();
            permissionList.addAll(permissions);
        }

        setAttr("role", role);
        setAttr("permissionList", permissionList);

        setAttr("formTitle", "查看用户详情");
        setAttr("mainTP", "/htmls/admin/role/edit.html");
        //调用通用模板
        renderTemplate("/htmls/admin/pop.html");
    }

    /**
     * 角色管理的编辑菜单请求
     */
    public void edit() {
        String json = HttpKit.readData(getRequest());
        log.info(json);
        DmsRole role = JSON.parseObject(json, DmsRole.class);

        if (StrUtil.isEmpty(role.getIsOpen())) {
            role.setIsOpen("off");
        }
        roleService.update(role);


        JSONObject tableValue = JSON.parseObject(json).getJSONObject("tableValue");

        if (tableValue != null) {
            JSONArray dataList = tableValue.getJSONArray("data");
            if (dataList.size() > 0) {
                //删除以前的
                rolePermissionService.deleteByRid(role.getId());

                for (int i = 0; i < dataList.size(); i++) {
                    JSONObject obj = (JSONObject) dataList.get(i);
                    DmsRolePermission rolePermission = new DmsRolePermission();
                    rolePermission.setPid(obj.getInteger("id"));
                    rolePermission.setRid(role.getId());
                    rolePermission.setCreateTime(new Date());
                    rolePermissionService.save(rolePermission);
                }
            }
        }

        renderJson(ResponseData.ok());

    }





    /**
     * 角色管理的删除请求
     */
    public void delete() {
        String id = getPara("id", "");
        if (StrUtil.isEmpty(id)) {
            renderJson(ResponseData.apiError("角色管理的id不能为空"));
        }
        roleService.deleteById(id);
        renderJson(ResponseData.ok());
    }

    /**
     * 角色管理的多选删除
     */
    public void deletes() {
        //获取所有请求参数
        String json = HttpKit.readData(getRequest());
        TableCheckStatus mTableCheckStatus = JSON.parseObject(json, TableCheckStatus.class);

        for (Object obj : mTableCheckStatus.getData()) {
            DmsRole role = JSONObject.parseObject(obj.toString(), DmsRole.class);
            roleService.delete(role);
        }
        renderJson(ResponseData.ok());
    }


}

