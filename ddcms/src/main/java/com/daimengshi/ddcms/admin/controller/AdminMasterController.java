package com.daimengshi.ddcms.admin.controller;

import com.daimengshi.ddcms.admin.model.DmsRole;
import com.daimengshi.ddcms.admin.model.DmsUser;
import com.daimengshi.ddcms.admin.service.impl.DmsRoleServiceImpl;
import com.daimengshi.ddcms.admin.service.impl.DmsUserServiceImpl;
import com.daimengshi.ddcms.pub.ResponseData;
import com.daimengshi.ddcms.pub.TablePage;
import com.daimengshi.ddcms.pub.Tools;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;

import javax.inject.Inject;
import java.util.List;

/**
 * 功能:管理员管理
 * 小写标识:master
 * 驼峰标识:Master
 * 作者:zhoufeng
 */
@RequestMapping("/admin/master")
public class AdminMasterController extends JbootController {
    private static final Log log = LogFactory.get();

    @Inject
    private DmsUserServiceImpl userService;
    @Inject
    private DmsRoleServiceImpl roleService;

    /**
     * 管理员管理默认页
     */
    public void index() {
        setAttr("title", "管理员管理");
        setAttr("mainTP", "/htmls/admin/master/index.html");
        //调用通用模板
        render("/htmls/admin/pop.html");
    }

    /**
     * 获取管理员管理列表
     */
    public void list() {
        TablePage tablePage = Tools.pageFind(this, "master.getPage");
        renderJson(tablePage);
    }


    /**
     * 管理员管理添加页面
     */
    public void addView() {


        List<DmsUser> userList = userService.findAll();
        List<DmsRole> roleList = roleService.findAll();


        TablePage userTablePage = Tools.pageFind(this, "user.getPage");
        setAttr("userTablePage", "userTablePage");//用户列表分页查询
        setAttr("userList", "userList");//用户列表
        setAttr("roleList", "roleList");//权限列表


        setAttr("formTitle", "管理员管理");
        setAttr("mainTP", "/htmls/admin/master/add.html");
        //调用通用模板
        render("/htmls/admin/pop.html");
    }

    /**
     * 管理员管理添加请求
     */
    public void add() {
//        String json = getBodyString();
//        log.info(json, Level.INFO);
//
//        DmsMaster master = JSON.parseObject(json, DmsMaster.class);
//
//        master.setCreateTime(DateUtil.date());
//        masterService.save(master);
        renderJson(ResponseData.ok());
    }


    /**
     * 管理员管理的查看详情和编辑页面
     */
    public void editView() {
//        String id = getPara("id");
//        DmsMaster master = masterService.DAO.findByIdWithoutCache(id);
//        log.info(master.toJson());
//
//        setAttr("master", master);

        setAttr("formTitle", "查看用户详情");
        setAttr("mainTP", "/htmls/admin/master/edit.html");
        //调用通用模板
        render("/htmls/admin/pop.html");
    }

    /**
     * 管理员管理的编辑菜单请求
     */
    public void edit() {
//        String json = getBodyString();
//        log.info(json);
//        DmsMaster master = JSON.parseObject(json, DmsMaster.class);
//
//        if (StrUtil.isEmpty(master.getIsOpen())) {
//            master.setIsOpen("off");
//        }
//
//        masterService.update(master);
        renderJson(ResponseData.ok());

    }

    /**
     * 管理员管理的删除请求
     */
    public void delete() {
//        String id = getPara("id", "");
//        if (StrUtil.isEmpty(id)) {
//            renderJson(ResponseData.apiError("管理员管理的id不能为空"));
//        }
//        masterService.deleteById(id);
        renderJson(ResponseData.ok());
    }

    /**
     * 管理员管理的多选删除
     */
    public void deletes() {
        //获取所有请求参数
//        String json = getBodyString();
//        TableCheckStatus mTableCheckStatus = JSON.parseObject(json, TableCheckStatus.class);
//
//        for (Object obj : mTableCheckStatus.getData()) {
//            DmsMaster master = JSONObject.parseObject(obj.toString(), DmsMaster.class);
//            masterService.delete(master);
//        }
        renderJson(ResponseData.ok());
    }


}
