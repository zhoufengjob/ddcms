package com.daimengshi.ddcms.admin.controller;

import com.alibaba.fastjson.JSON;
import com.daimengshi.ddcms.pub.HtmlView;
import com.daimengshi.ddcms.rest.datatable.PageTools;
import com.daimengshi.ddcms.rest.datatable.TablePage;
import com.daimengshi.ddcms.service.api.DmsRoleService;
import com.daimengshi.ddcms.service.api.DmsUserRoleService;
import com.daimengshi.ddcms.service.api.DmsUserService;
import com.daimengshi.ddcms.service.entity.model.DmsRole;
import com.daimengshi.ddcms.service.entity.model.DmsUser;
import com.daimengshi.ddcms.utils.ServiceUtils;
import com.daimengshi.ddcms.web.base.BaseController;
import com.daimengshi.ddcms.web.base.ResponseData;
import com.jfinal.plugin.activerecord.Page;
import io.jboot.web.controller.annotation.RequestMapping;

import java.util.List;

/**
 * 功能:管理员管理
 * 小写标识:master
 * 驼峰标识:Master
 * 作者:zhoufeng
 */
@RequestMapping("/admin/master")
public class AdminMasterController extends BaseController {


    private DmsUserService userService;
    private DmsRoleService roleService;
    private DmsUserRoleService userRoleService;

    public AdminMasterController() {
        this.userService = ServiceUtils.getService(DmsUserService.class);
        this.roleService = ServiceUtils.getService(DmsRoleService.class);
        this.userRoleService = ServiceUtils.getService(DmsUserRoleService.class);
    }

    /**
     * 默认页(查询列表页面)
     */
    @Override
    public void index() {
        HtmlView.adminPop(this, "管理员管理", "/master/masterList.html");
    }

    /**
     * 添加界面
     */
    @Override
    public void addView() {
        List<DmsUser> userList = userService.findAll();
        List<DmsRole> roleList = roleService.findAll();


        Page userTablePage = PageTools.pageFind(this, "user.getPage", userService);
        setAttr("userTablePage", TablePage.dataTableFormat(userTablePage));//用户列表分页查询
        setAttr("userList", userList);//用户列表
        setAttr("roleList", roleList);//权限列表

        HtmlView.adminPop(this, "管理员管理", "master/masterAdd.html");
    }

    /**
     * 编辑界面
     */
    @Override
    public void editView() {
        render("/htmls/admin/layui/pop.html");
        HtmlView.adminPop(this, "查看用户详情", "master/masterEdit.html");

    }

    /**
     * 获取列表(用于ajax异步请求)
     */
    @Override
    public void getList() {
        Page page = userService.findPage(this, "master.getPage", userService);
        renderJson(TablePage.dataTableFormat(page));
    }

    /**
     * 添加(用于ajax异步请求)
     */
    @Override
    public void postAdd() {
//        String json = getBodyString();
//        log.info(json, Level.INFO);
//
//        DmsMaster master = JSON.parseObject(json, DmsMaster.class);
//
//        master.setCreateTime(DateUtil.date());
//        masterService.save(master);
//        renderJson(ResponseData.ok());
    }

    /**
     * 编辑(用于ajax异步请求)
     */
    @Override
    public void postEdit() {

    }

    /**
     * 删除一个(用于ajax异步请求)
     */
    @Override
    public void postDelete() {
        String json = getBodyString();
        String userId = JSON.parseObject(json).getString("id");

        DmsUser user = userService.findById(userId);
        if ("admin".equals(user.getName())) {//跳过删除超级管理员
            renderJson(ResponseData.apiError("admin 不能降权"));
            return;
        }
        userRoleService.deleteByUID(userId);
        renderJson(ResponseData.ok());
    }

    /**
     * 删除多个(用于ajax异步请求)
     */
    @Override
    public void postDeletes() {
    }
}
