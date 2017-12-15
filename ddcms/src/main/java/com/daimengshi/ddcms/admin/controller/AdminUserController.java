package com.daimengshi.ddcms.admin.controller;

import com.daimengshi.ddcms.pub.TablePage;
import com.daimengshi.ddcms.pub.Tools;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;

/**
 * Created by zhoufeng on 2017/12/14.
 * 用户管理
 */
@RequestMapping("/admin/user")
public class AdminUserController extends JbootController {
    private static final Log log = LogFactory.get();


    /**
     * 管理员列表
     */
    public void index() {
        setAttr("title", "用户列表");
        setAttr("mainTP", "/htmls/admin/user/index.html");
        //调用通用模板
        renderTemplate("/htmls/admin/pop.html");
    }

    /**
     * 获取管理员列表
     */
    public void list() {
        TablePage tablePage = Tools.pageFind(this, "user.getPage");
        renderJson(tablePage);
    }




}
