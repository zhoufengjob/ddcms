package com.daimengshi.ddcms.admin.controller;

import com.alibaba.fastjson.JSON;
import com.daimengshi.ddcms.admin.support.auth.AuthUtils;
import com.daimengshi.ddcms.pub.HtmlView;
import com.daimengshi.ddcms.rest.datatable.TablePage;
import com.daimengshi.ddcms.service.api.DmsRoleService;
import com.daimengshi.ddcms.service.api.DmsUserRoleService;
import com.daimengshi.ddcms.service.api.DmsUserService;
import com.daimengshi.ddcms.service.entity.model.DmsRole;
import com.daimengshi.ddcms.service.entity.model.DmsUser;
import com.daimengshi.ddcms.service.entity.model.DmsUserRole;
import com.daimengshi.ddcms.utils.JsonVerifyUtils;
import com.daimengshi.ddcms.utils.ServiceUtils;
import com.daimengshi.ddcms.web.base.BaseController;
import com.daimengshi.ddcms.web.base.ResponseData;
import com.jfinal.plugin.activerecord.Page;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import io.jboot.web.controller.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

/**
 * Created by zhoufeng on 2017/12/14.
 * 用户管理
 */
@RequestMapping("/admin/user")
public class AdminUserController extends BaseController {

    private DmsUserService userService;
    private DmsRoleService roleService;
    private DmsUserRoleService userRoleService;

    public AdminUserController() {
        this.userService = ServiceUtils.getService(DmsUserService.class);
        this.roleService = ServiceUtils.getService(DmsRoleService.class);
        this.userRoleService = ServiceUtils.getService(DmsUserRoleService.class);
    }


    /**
     * 默认页(查询列表页面)
     */
    @Override
    public void index() {
        HtmlView.adminPop(this, "用户列表", "/user/userList.html");
    }

    /**
     * 添加界面
     */
    @Override
    public void addView() {
        List<DmsRole> dmsRoleList = roleService.findAll();
        DmsRole wuRole = new DmsRole();
        wuRole.setName("无");
        wuRole.setId("");
        dmsRoleList.add(0, wuRole);
        setAttr("dmsRoleList", dmsRoleList);//系统所有的角色

        HtmlView.adminPop(this, "添加用户", "/user/userAdd.html");
    }

    /**
     * 获取列表(用于ajax异步请求)
     */
    @Override
    public void getList() {
        Page pages = userService.findPage(this, "user.getPage", userService);
        renderJson(TablePage.dataTableFormat(pages));
    }

    /**
     * 编辑界面
     */
    @Override
    public void editView() {
        String id = getPara("id");
        DmsUser user = userService.findById(id);
        log.info(user.toJson());

        List<DmsRole> dmsRoleList = roleService.findAll();
        DmsUserRole dmsUserRole = userRoleService.findFirstByUID(id);
        roleService.join(dmsUserRole, "rid");
        DmsRole dmsRole = null;

        if (dmsUserRole != null) {
            dmsRole = dmsUserRole.get("dmsRole");
        }

        if (dmsRole == null) {
            dmsRole = new DmsRole();
            dmsRole.setName("无");
            dmsRole.setId("");
            dmsRoleList.add(0, dmsRole);
        }

        setAttr("dmsRole", dmsRole);//该用户的角色
        setAttr("dmsRoleList", dmsRoleList);//系统所有的角色
        setAttr("user", user);


        HtmlView.adminPop(this, "查看用户详情", "/user/userEdit.html");

    }

    /**
     * 添加(用于ajax异步请求)
     */
    @Override
    public void postAdd() {
        String json = getBodyString();

        if (JsonVerifyUtils.verifyStringIsEmpty(this, json, "name", "用户名不能为空"))
            return;
        if (JsonVerifyUtils.verifyStringIsEmpty(this, json, "password", "密码不能为空"))
            return;
        if (JsonVerifyUtils.verifyStringIsEmpty(this, json, "is_open", "启用不能为空"))
            return;
        if (JsonVerifyUtils.verifyStringIsNotNunber(this, json, "point", "积分只能是数字"))
            return;

        DmsUser user = JSON.parseObject(json, DmsUser.class);
        user.setCreateTime(DateUtil.date());
        user.setLastUpdataTime(DateUtil.date());
        user.setLastUpdataAcct(AuthUtils.getLoginUser().getName());

        AuthUtils.updataUserSalt2(user);//撒点盐

        userService.save(user);

        String user_role_id = JSON.parseObject(json).getString("user_role_id");
        DmsUserRole dmsUserRole = userRoleService.findFirstByUID(user.getId());

        if (dmsUserRole == null) {
            dmsUserRole = new DmsUserRole();
        }

        dmsUserRole.setUid(user.getId());
        dmsUserRole.setRid(user_role_id);
        dmsUserRole.setCreateTime(new Date());
        dmsUserRole.setLastUpdataTime(DateUtil.date());
        dmsUserRole.setLastUpdataAcct(AuthUtils.getLoginUser().getName());
        userRoleService.saveOrUpdate(dmsUserRole);

        renderJson(ResponseData.ok());
    }

    /**
     * 编辑(用于ajax异步请求)
     */
    @Override
    public void postEdit() {
        String json = getBodyString();
        log.info(json);
        if (JsonVerifyUtils.verifyStringIsEmpty(this, json, "id", "id不能为空"))
            return;
        if (JsonVerifyUtils.verifyStringIsEmpty(this, json, "name", "用户名不能为空"))
            return;
        if (JsonVerifyUtils.verifyStringIsEmpty(this, json, "password", "密码不能为空"))
            return;
        if (JsonVerifyUtils.verifyStringIsNotNunber(this, json, "point", "积分只能是数字"))
            return;

        DmsUser user = JSON.parseObject(json, DmsUser.class);

        if (StrUtil.isEmpty(user.getIsOpen())) {
            user.setIsOpen("off");
        }

        user.setLastUpdataTime(DateUtil.date());
        user.setLastUpdataAcct(AuthUtils.getLoginUser().getName());

        String user_role_id = JSON.parseObject(json).getString("user_role_id");

        DmsUserRole dmsUserRole = userRoleService.findFirstByUID(user.getId());

        if (dmsUserRole == null) {
            dmsUserRole = new DmsUserRole();
        }

        dmsUserRole.setUid(user.getId());
        dmsUserRole.setRid(user_role_id);
        dmsUserRole.setLastUpdataTime(DateUtil.date());
        dmsUserRole.setLastUpdataAcct(AuthUtils.getLoginUser().getName());

        AuthUtils.updataUserSalt2(user);//重新撒把盐

        userRoleService.saveOrUpdate(dmsUserRole);

        userService.saveOrUpdate(user);
        renderJson(ResponseData.ok());

    }




    /**
     * 删除一个(用于ajax异步请求)
     */
    @Override
    public void postDelete() {
        String json = getBodyString();
        DmsUser user = JSON.parseObject(json, DmsUser.class);
        if ("admin".equals(user.getName())) {
            renderJson(ResponseData.apiError("admin 不能删除"));
            return;
        }
        if (StrUtil.isEmpty(user.getId())) {
            renderJson(ResponseData.apiError("id不能为空"));
            return;
        }
        userService.deleteById(user.getId());
        userRoleService.deleteByUID(user.getId());
        renderJson(ResponseData.ok());
    }

    /**
     * 删除多个(用于ajax异步请求)
     */
    @Override
    public void postDeletes() {
        String json = getBodyString();
        List<DmsUser> users = JSON.parseArray(json, DmsUser.class);


        for (DmsUser user : users) {
            if ("admin".equals(user.getName())) {//跳过删除超级管理员
                continue;
            }

            userService.deleteById(user.getId());
            userRoleService.deleteByUID(user.getId());
        }

        renderJson(ResponseData.ok());
    }
}
