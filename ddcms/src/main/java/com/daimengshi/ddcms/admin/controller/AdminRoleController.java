package com.daimengshi.ddcms.admin.controller;

import com.daimengshi.ddcms.pub.TablePage;
import com.daimengshi.ddcms.pub.Tools;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;

/**
 * Created by zhoufeng on 2017/12/14.
 * 角色管理
 */
@RequestMapping("/admin/role")
public class AdminRoleController extends JbootController {
    private static final Log log = LogFactory.get();


    /**
     * 管理员列表
     */
    public void index() {
        setAttr("title", "角色列表");
        setAttr("mainTP", "/htmls/admin/role/index.html");
        //调用通用模板
        renderTemplate("/htmls/admin/pop.html");
    }

    /**
     * 获取管理员列表
     */
    public void list() {
        TablePage tablePage = Tools.pageFind(this, "role.getPage");
        renderJson(tablePage);
    }




}
