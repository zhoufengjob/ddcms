package com.daimengshi.ddcms.admin.controller;

import com.alibaba.fastjson.JSON;
import com.daimengshi.ddcms.admin.model.DmsUser;
import com.daimengshi.ddcms.admin.service.impl.DmsUserServiceImpl;
import com.daimengshi.ddcms.pub.ResponseData;
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

/**
 * Created by zhoufeng on 2017/12/14.
 * 用户管理
 */
@RequestMapping("/admin/user")
public class AdminUserController extends JbootController {
    private static final Log log = LogFactory.get();


    @Inject
    private DmsUserServiceImpl userService;

    /**
     * 默认页
     */
    public void index() {
        setAttr("title", "用户列表");
        setAttr("mainTP", "/htmls/admin/user/index.html");
        //调用通用模板
        renderTemplate("/htmls/admin/pop.html");
    }

    /**
     * 获取用户列表
     */
    public void list() {
        TablePage tablePage = Tools.pageFind(this, "user.getPage");
        renderJson(tablePage);
    }


    /**
     * 添加页面
     */
    public void addView() {

        setAttr("formTitle", "添加用户");
        setAttr("mainTP", "/htmls/admin/user/add.html");
        //调用通用模板
        renderTemplate("/htmls/admin/pop.html");
    }

    /**
     * 添加
     */
    public void add() {
        String json = HttpKit.readData(getRequest());
        log.info(json, Level.INFO);

        DmsUser user = JSON.parseObject(json, DmsUser.class);

        user.setCreateTime(DateUtil.date());
        userService.save(user);
        renderJson(ResponseData.ok());
    }


    /**
     * 查看详情和编辑
     */
    public void editView() {
        String id = getPara("id");
        DmsUser user = userService.DAO.findByIdWithoutCache(id);
        log.info(user.toJson());

        setAttr("user", user);

        setAttr("formTitle", "查看用户详情");
        setAttr("mainTP", "/htmls/admin/user/edit.html");
        //调用通用模板
        renderTemplate("/htmls/admin/pop.html");
    }

    /**
     * 编辑菜单
     */
    public void edit() {
        String json = HttpKit.readData(getRequest());
        log.info(json);
        DmsUser user = JSON.parseObject(json, DmsUser.class);

        if (StrUtil.isEmpty(user.getIsOpen())) {
            user.setIsOpen("off");
        }

        userService.update(user);
        renderJson(ResponseData.ok());

    }

    /**
     * 删除菜单
     */

    public void delete() {
        String id = getPara("id", "");
        if (StrUtil.isEmpty(id)) {
            renderJson(ResponseData.apiError("id不能为空"));
        }
        userService.deleteById(id);
        renderJson(ResponseData.ok());
    }

}
