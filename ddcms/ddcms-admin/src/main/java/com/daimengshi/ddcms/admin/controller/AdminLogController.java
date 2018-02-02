package com.daimengshi.ddcms.admin.controller;

import com.alibaba.fastjson.JSON;
import com.daimengshi.ddcms.admin.support.auth.AuthUtils;
import com.daimengshi.ddcms.pub.HtmlView;
import com.daimengshi.ddcms.rest.datatable.TablePage;
import com.daimengshi.ddcms.service.api.DmsLogService;
import com.daimengshi.ddcms.service.entity.model.DmsLog;
import com.daimengshi.ddcms.utils.ServiceUtils;
import com.daimengshi.ddcms.web.base.BaseController;
import com.daimengshi.ddcms.web.base.ResponseData;
import com.jfinal.plugin.activerecord.Page;
import com.xiaoleilu.hutool.date.DateUtil;
import io.jboot.web.controller.annotation.RequestMapping;

import java.util.List;


/**
 * 功能:日志控制器
 * 作者:zhoufeng
 */
@RequestMapping("/admin/log")
public class AdminLogController extends BaseController{

    private DmsLogService logService;

    public AdminLogController() {
        this.logService = ServiceUtils.getService(DmsLogService.class);
    }

    /**
     * 默认页(查询日志列表页面)
     */
    @Override
    public void index() {
        HtmlView.adminPop(this, "日志管理", "/log/logList.html");
    }

    /**
     * 添加日志界面
     */
    @Override
    public void addView() {
        HtmlView.adminPop(this, "添加日志", "/log/logAdd.html");
    }

    /**
     * 编辑日志界面
     */
    @Override
    public void editView() {
        String id = getPara("id");
        DmsLog dmsLog = logService.findById(id);
        log.info(dmsLog.toJson());

        setAttr("log", dmsLog);//当前模型

        HtmlView.adminPop(this, "查看日志详情", "/log/logEdit.html");
    }

    /**
     * 获取日志列表(用于ajax异步请求)
     */
    @Override
    public void getList() {
        Page pages = logService.findPage(this, "log.getPage", logService);

        renderJson(TablePage.dataTableFormat(pages));
    }

    /**
     * 添加日志(用于ajax异步请求)
     */
    @Override
    public void postAdd() {
        String json = getBodyString();
        DmsLog log = JSON.parseObject(json, DmsLog.class);

        log.setCreateTime(DateUtil.date());
        log.setLastUpdataTime(DateUtil.date());
        log.setLastUpdataAcct(AuthUtils.getLoginUser().getName());
        logService.save(log);

        renderJson(ResponseData.ok());
    }

    /**
     * 编辑日志(用于ajax异步请求)
     */
    @Override
    public void postEdit() {
        String json = getBodyString();
        log.info(json);
        DmsLog log = JSON.parseObject(json, DmsLog.class);

        log.setLastUpdataTime(DateUtil.date());
        log.setLastUpdataAcct(AuthUtils.getLoginUser().getName());

        logService.saveOrUpdate(log);

        renderJson(ResponseData.ok());
    }

    /**
     * 删除一个日志(用于ajax异步请求)
     */
    @Override
    public void postDelete() {
        String json = getBodyString();
        DmsLog log = JSON.parseObject(json, DmsLog.class);
        if (log == null) {
            renderJson(ResponseData.apiError("id不能为空"));
            return;
        }
        logService.deleteById(log.getId());
        renderJson(ResponseData.ok());
    }

    /**
     * 删除多个日志(用于ajax异步请求)
     */
    @Override
    public void postDeletes() {
        String json = getBodyString();
        List<DmsLog> logList = JSON.parseArray(json, DmsLog.class);

        for (DmsLog log : logList) {
            logService.deleteById(log.getId());
        }

        renderJson(ResponseData.ok());
    }

    /**
     * 清空所有日志
     */
    public void postDeleteAll() {
        logService.deleteAll();
        renderJson(ResponseData.ok());
    }

}