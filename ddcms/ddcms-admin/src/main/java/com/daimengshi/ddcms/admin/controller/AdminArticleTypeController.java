package com.daimengshi.ddcms.admin.controller;

import com.alibaba.fastjson.JSON;
import com.daimengshi.ddcms.admin.support.auth.AuthUtils;
import com.daimengshi.ddcms.pub.HtmlView;
import com.daimengshi.ddcms.rest.datatable.TablePage;
import com.daimengshi.ddcms.service.api.DmsArticleTypeService;
import com.daimengshi.ddcms.service.entity.model.DmsArticleType;
import com.daimengshi.ddcms.utils.ServiceUtils;
import com.daimengshi.ddcms.web.base.BaseController;
import com.daimengshi.ddcms.web.base.ResponseData;
import com.jfinal.plugin.activerecord.Page;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import io.jboot.web.controller.annotation.RequestMapping;

import java.util.List;


/**
 * 功能:文章类型控制器
 * 作者:zhoufeng
 */
@RequestMapping("/admin/articleType")
public class AdminArticleTypeController extends BaseController{

    private DmsArticleTypeService articleTypeService;

    public AdminArticleTypeController() {
        this.articleTypeService = ServiceUtils.getService(DmsArticleTypeService.class);
    }

    /**
     * 默认页(查询文章类型列表页面)
     */
    @Override
    public void index() {
        HtmlView.adminPop(this, "文章类型管理", "/articleType/articleTypeList.html");
    }

    /**
     * 添加文章类型界面
     */
    @Override
    public void addView() {
        HtmlView.adminPop(this, "添加文章类型", "/articleType/articleTypeAdd.html");
    }

    /**
     * 编辑文章类型界面
     */
    @Override
    public void editView() {
        String id = getPara("id");
        DmsArticleType articleType = articleTypeService.findById(id);
        log.info(articleType.toJson());

        setAttr("articleType", articleType);//当前模型

        HtmlView.adminPop(this, "查看文章类型详情", "/articleType/articleTypeEdit.html");
    }

    /**
     * 获取文章类型列表(用于ajax异步请求)
     */
    @Override
    public void getList() {
        Page pages = articleTypeService.findPage(this, "articleType.getPage", articleTypeService);

        renderJson(TablePage.dataTableFormat(pages));
    }

    /**
     * 添加文章类型(用于ajax异步请求)
     */
    @Override
    public void postAdd() {
        String json = getBodyString();
        DmsArticleType articleType = JSON.parseObject(json, DmsArticleType.class);

        articleType.setCreateTime(DateUtil.date());
        articleType.setLastUpdataTime(DateUtil.date());
        articleType.setLastUpdataAcct(AuthUtils.getLoginUser().getName());
        articleTypeService.save(articleType);

        renderJson(ResponseData.ok());
    }

    /**
     * 编辑文章类型(用于ajax异步请求)
     */
    @Override
    public void postEdit() {
        String json = getBodyString();
        log.info(json);
        DmsArticleType articleType = JSON.parseObject(json, DmsArticleType.class);

        if (StrUtil.isEmpty(articleType.getIsOpen())) {
            articleType.setIsOpen("off");
        }

        articleType.setLastUpdataTime(DateUtil.date());
        articleType.setLastUpdataAcct(AuthUtils.getLoginUser().getName());

        articleTypeService.saveOrUpdate(articleType);

        renderJson(ResponseData.ok());
    }

    /**
     * 删除一个文章类型(用于ajax异步请求)
     */
    @Override
    public void postDelete() {
        String json = getBodyString();
        DmsArticleType articleType = JSON.parseObject(json, DmsArticleType.class);
        if (articleType == null) {
            renderJson(ResponseData.apiError("id不能为空"));
            return;
        }
        articleTypeService.deleteById(articleType.getId());
        renderJson(ResponseData.ok());
    }

    /**
     * 删除多个文章类型(用于ajax异步请求)
     */
    @Override
    public void postDeletes() {
        String json = getBodyString();
        List<DmsArticleType> articleTypeList = JSON.parseArray(json, DmsArticleType.class);

        for (DmsArticleType articleType : articleTypeList) {
            articleTypeService.deleteById(articleType.getId());
        }

        renderJson(ResponseData.ok());
    }
}