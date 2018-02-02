package com.daimengshi.ddcms.admin.controller;

import com.alibaba.fastjson.JSON;
import com.daimengshi.ddcms.admin.support.auth.AuthUtils;
import com.daimengshi.ddcms.pub.HtmlView;
import com.daimengshi.ddcms.rest.datatable.TablePage;
import com.daimengshi.ddcms.service.api.DmsLinksService;
import com.daimengshi.ddcms.service.entity.model.DmsLinks;
import com.daimengshi.ddcms.utils.ServiceUtils;
import com.daimengshi.ddcms.web.base.BaseController;
import com.daimengshi.ddcms.web.base.ResponseData;
import com.jfinal.plugin.activerecord.Page;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.StrUtil;

import java.util.List;


/**
 * 功能:
 * 作者:zhoufeng
 */
public class AdminLinksController extends BaseController{

    private DmsLinksService linksService;

    public AdminLinksController() {
        this.linksService = ServiceUtils.getService(DmsLinksService.class);
    }

    /**
     * 默认页(查询列表页面)
     */
    @Override
    public void index() {
        HtmlView.adminPop(this, "角色管理", "/links/linksList.html");
    }

    /**
     * 添加界面
     */
    @Override
    public void addView() {
        HtmlView.adminPop(this, "添加角色", "/links/linksAdd.html");
    }

    /**
     * 编辑界面
     */
    @Override
    public void editView() {
        String id = getPara("id");
        DmsLinks links = linksService.findById(id);
        log.info(links.toJson());

        setAttr("links", links);//当前模型

        HtmlView.adminPop(this, "查看角色详情", "/links/linksEdit.html");
    }

    /**
     * 获取列表(用于ajax异步请求)
     */
    @Override
    public void getList() {
        Page pages = linksService.findPage(this, "links.getPage", linksService);

        renderJson(TablePage.dataTableFormat(pages));
    }

    /**
     * 添加(用于ajax异步请求)
     */
    @Override
    public void postAdd() {
        String json = getBodyString();
        DmsLinks links = JSON.parseObject(json, DmsLinks.class);

        links.setCreateTime(DateUtil.date());
        links.setLastUpdataTime(DateUtil.date());
        links.setLastUpdataAcct(AuthUtils.getLoginUser().getName());
        linksService.save(links);

        renderJson(ResponseData.ok());
    }

    /**
     * 编辑(用于ajax异步请求)
     */
    @Override
    public void postEdit() {
        String json = getBodyString();
        log.info(json);
        DmsLinks links = JSON.parseObject(json, DmsLinks.class);

        if (StrUtil.isEmpty(links.getIsOpen())) {
            links.setIsOpen("off");
        }

        links.setLastUpdataTime(DateUtil.date());
        links.setLastUpdataAcct(AuthUtils.getLoginUser().getName());

        linksService.saveOrUpdate(links);

        renderJson(ResponseData.ok());
    }

    /**
     * 删除一个(用于ajax异步请求)
     */
    @Override
    public void postDelete() {
        String json = getBodyString();
        DmsLinks links = JSON.parseObject(json, DmsLinks.class);
        if (links == null) {
            renderJson(ResponseData.apiError("id不能为空"));
            return;
        }
        linksService.deleteById(links.getId());
        renderJson(ResponseData.ok());
    }

    /**
     * 删除多个(用于ajax异步请求)
     */
    @Override
    public void postDeletes() {
        String json = getBodyString();
        List<DmsLinks> linksList = JSON.parseArray(json, DmsLinks.class);

        for (DmsLinks links : linksList) {
            linksService.deleteById(links.getId());
        }

        renderJson(ResponseData.ok());
    }
}