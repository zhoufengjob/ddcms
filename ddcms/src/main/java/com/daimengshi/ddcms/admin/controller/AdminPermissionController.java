package com.daimengshi.ddcms.admin.controller;

import com.daimengshi.ddcms.pub.TablePage;
import com.daimengshi.ddcms.pub.Tools;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;

/**
 * Created by zhoufeng on 2017/12/14.
 * 权限管理
 */
@RequestMapping("/admin/permission")
public class AdminPermissionController extends JbootController {
    private static final Log log = LogFactory.get();


    /**
     * 管理员列表
     */
    public void index() {
        setAttr("title", "权限列表");
        setAttr("mainTP", "/htmls/admin/permission/index.html");
        //调用通用模板
        renderTemplate("/htmls/admin/pop.html");
    }

    /**
     * 获取管理员列表
     */
    public void list() {
        TablePage tablePage = Tools.pageFind(this, "permission.getPage");
        renderJson(tablePage);
    }




}
