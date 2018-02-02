package com.daimengshi.ddcms.admin.controller;

import com.alibaba.fastjson.JSON;
import com.daimengshi.ddcms.admin.support.auth.AuthUtils;
import com.daimengshi.ddcms.common.Consts;
import com.daimengshi.ddcms.pub.HtmlView;
import com.daimengshi.ddcms.rest.datatable.TablePage;
import com.daimengshi.ddcms.service.api.DmsArticleService;
import com.daimengshi.ddcms.service.entity.model.DmsArticle;
import com.daimengshi.ddcms.service.entity.model.DmsUser;
import com.daimengshi.ddcms.utils.ServiceUtils;
import com.daimengshi.ddcms.web.base.BaseController;
import com.daimengshi.ddcms.web.base.ResponseData;
import com.jfinal.plugin.activerecord.Page;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import io.jboot.web.controller.annotation.RequestMapping;
import org.apache.shiro.SecurityUtils;

import java.util.List;

/**
 * Created by zhoufeng on 2017/12/21.
 * 功能:文章管理
 * 小写标识:article
 * 驼峰标识:Article
 * 时间:2017/12/21.
 * 作者:zhoufeng
 */
@RequestMapping("/admin/article")
public class AdminArticleController extends BaseController {

    private DmsArticleService articleService;

    public AdminArticleController() {
        this.articleService = ServiceUtils.getService(DmsArticleService.class);
    }

    /**
     * 默认页(查询列表页面)
     */
    @Override
    public void index() {
        HtmlView.adminPop(this, "文章管理", "/article/articleList.html");
    }

    /**
     * 添加界面
     */
    @Override
    public void addView() {
        HtmlView.adminPop(this, "添加文章", "/article/articleAdd.html");
    }

    /**
     * 编辑界面
     */
    @Override
    public void editView() {
        String id = getPara("id");
        DmsArticle article = articleService.findById(id);
        log.info(article.toJson());

        setAttr("article", article);

        HtmlView.adminPop(this, "查看文章详情", "/article/articleEdit.html");
    }

    /**
     * 获取列表(用于ajax异步请求)
     */
    @Override
    public void getList() {
        Page pages = articleService.findPage(this, "article.getPage", articleService);
        renderJson(TablePage.dataTableFormat(pages));
    }

    /**
     * 添加(用于ajax异步请求)
     */
    @Override
    public void postAdd() {
        String json = getBodyString();
        DmsArticle article = JSON.parseObject(json, DmsArticle.class);
        DmsUser user = (DmsUser) SecurityUtils.getSubject().getSession().getAttribute(Consts.SESSION_USER);
        article.setUid(user.getId());
        article.setCreateTime(DateUtil.date());
        article.setLastUpdataTime(DateUtil.date());
        article.setLastUpdataAcct(AuthUtils.getLoginUser().getName());
        articleService.save(article);
        renderJson(ResponseData.ok());
    }

    /**
     * 编辑(用于ajax异步请求)
     */
    @Override
    public void postEdit() {
        String json = getBodyString();
        log.info(json);
        DmsArticle article = JSON.parseObject(json, DmsArticle.class);

        if (StrUtil.isEmpty(article.getIsQuintessence())) {
            article.setIsQuintessence("off");
        }
        if (StrUtil.isEmpty(article.getIsTop())) {
            article.setIsTop("off");
        }
        if (StrUtil.isEmpty(article.getIsOpen())) {
            article.setIsOpen("off");
        }
        article.setLastUpdataTime(DateUtil.date());
        article.setLastUpdataAcct(AuthUtils.getLoginUser().getName());
        articleService.update(article);
        renderJson(ResponseData.ok());
    }

    /**
     * 删除一个(用于ajax异步请求)
     */
    @Override
    public void postDelete() {
        String json = getBodyString();
        DmsArticle article = JSON.parseObject(json, DmsArticle.class);
        if (article == null) {
            renderJson(ResponseData.apiError("文章管理的id不能为空"));
            return;
        }
        articleService.deleteById(article.getId());
        renderJson(ResponseData.ok());
    }

    /**
     * 删除多个(用于ajax异步请求)
     */
    @Override
    public void postDeletes() {
        String json = getBodyString();
        List<DmsArticle> articleList = JSON.parseArray(json, DmsArticle.class);
        for (DmsArticle article : articleList) {
            articleService.delete(article);
        }
        renderJson(ResponseData.ok());
    }
}
