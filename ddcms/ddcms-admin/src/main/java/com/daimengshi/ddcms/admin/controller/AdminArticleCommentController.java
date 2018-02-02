package com.daimengshi.ddcms.admin.controller;

import com.alibaba.fastjson.JSON;
import com.daimengshi.ddcms.admin.support.auth.AuthUtils;
import com.daimengshi.ddcms.pub.HtmlView;
import com.daimengshi.ddcms.rest.datatable.TablePage;
import com.daimengshi.ddcms.service.api.DmsArticleCommentService;
import com.daimengshi.ddcms.service.entity.model.DmsArticleComment;
import com.daimengshi.ddcms.utils.ServiceUtils;
import com.daimengshi.ddcms.web.base.BaseController;
import com.daimengshi.ddcms.web.base.ResponseData;
import com.jfinal.plugin.activerecord.Page;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import io.jboot.web.controller.annotation.RequestMapping;

import java.util.List;


/**
 * 功能:评论控制器
 * 作者:zhoufeng
 */
@RequestMapping("/admin/articleComment")
public class AdminArticleCommentController extends BaseController{

    private DmsArticleCommentService articleCommentService;

    public AdminArticleCommentController() {
        this.articleCommentService = ServiceUtils.getService(DmsArticleCommentService.class);
    }

    /**
     * 默认页(查询评论列表页面)
     */
    @Override
    public void index() {
        HtmlView.adminPop(this, "评论管理", "/articleComment/articleCommentList.html");
    }

    /**
     * 添加评论界面
     */
    @Override
    public void addView() {
        HtmlView.adminPop(this, "添加评论", "/articleComment/articleCommentAdd.html");
    }

    /**
     * 编辑评论界面
     */
    @Override
    public void editView() {
        String id = getPara("id");
        DmsArticleComment articleComment = articleCommentService.findById(id);
        log.info(articleComment.toJson());

        setAttr("articleComment", articleComment);//当前模型

        HtmlView.adminPop(this, "查看评论详情", "/articleComment/articleCommentEdit.html");
    }

    /**
     * 获取评论列表(用于ajax异步请求)
     */
    @Override
    public void getList() {
        Page pages = articleCommentService.findPage(this, "articleComment.getPage", articleCommentService);

        renderJson(TablePage.dataTableFormat(pages));
    }

    /**
     * 添加评论(用于ajax异步请求)
     */
    @Override
    public void postAdd() {
        String json = getBodyString();
        DmsArticleComment articleComment = JSON.parseObject(json, DmsArticleComment.class);

        articleComment.setCreateTime(DateUtil.date());
        articleComment.setLastUpdataTime(DateUtil.date());
        articleComment.setLastUpdataAcct(AuthUtils.getLoginUser().getName());
        articleCommentService.save(articleComment);

        renderJson(ResponseData.ok());
    }

    /**
     * 编辑评论(用于ajax异步请求)
     */
    @Override
    public void postEdit() {
        String json = getBodyString();
        log.info(json);
        DmsArticleComment articleComment = JSON.parseObject(json, DmsArticleComment.class);

        if (StrUtil.isEmpty(articleComment.getIsOpen())) {
            articleComment.setIsOpen("off");
        }

        articleComment.setLastUpdataTime(DateUtil.date());
        articleComment.setLastUpdataAcct(AuthUtils.getLoginUser().getName());

        articleCommentService.saveOrUpdate(articleComment);

        renderJson(ResponseData.ok());
    }

    /**
     * 删除一个评论(用于ajax异步请求)
     */
    @Override
    public void postDelete() {
        String json = getBodyString();
        DmsArticleComment articleComment = JSON.parseObject(json, DmsArticleComment.class);
        if (articleComment == null) {
            renderJson(ResponseData.apiError("id不能为空"));
            return;
        }
        articleCommentService.deleteById(articleComment.getId());
        renderJson(ResponseData.ok());
    }

    /**
     * 删除多个评论(用于ajax异步请求)
     */
    @Override
    public void postDeletes() {
        String json = getBodyString();
        List<DmsArticleComment> articleCommentList = JSON.parseArray(json, DmsArticleComment.class);

        for (DmsArticleComment articleComment : articleCommentList) {
            articleCommentService.deleteById(articleComment.getId());
        }

        renderJson(ResponseData.ok());
    }
}