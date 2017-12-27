package com.daimengshi.ddcms.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.daimengshi.ddcms.admin.model.DmsArticle;
import com.daimengshi.ddcms.admin.service.impl.DmsArticleServiceImpl;
import com.daimengshi.ddcms.pub.ResponseData;
import com.daimengshi.ddcms.pub.TableCheckStatus;
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
 * Created by zhoufeng on 2017/12/21.
 * 功能:文章管理
 * 小写标识:article
 * 驼峰标识:Article
 * 时间:2017/12/21.
 * 作者:zhoufeng
 */
@RequestMapping("/admin/article")
public class AdminArticleController extends JbootController {
    private static final Log log = LogFactory.get();

    @Inject
    DmsArticleServiceImpl articleService;


    /**
     * 文章管理默认页
     */
    public void index() {
        setAttr("title", "文章管理");
        setAttr("mainTP", "/htmls/admin/article/index.html");
        //调用通用模板
        render("/htmls/admin/pop.html");
    }

    /**
     * 获取文章管理列表
     */
    public void list() {
        TablePage tablePage = Tools.pageFind(this, "article.getPage");
        renderJson(tablePage);
    }


    /**
     * 文章管理添加页面
     */
    public void addView() {

        setAttr("formTitle", "文章管理");
        setAttr("mainTP", "/htmls/admin/article/add.html");
        //调用通用模板
        render("/htmls/admin/pop.html");
    }

    /**
     * 文章管理添加请求
     */
    public void add() {
        String json = HttpKit.readData(getRequest());
        log.info(json, Level.INFO);

        DmsArticle article = JSON.parseObject(json, DmsArticle.class);
        article.setUid(getSessionAttr("uid"));

        article.setCreateTime(DateUtil.date());
        articleService.save(article);
        renderJson(ResponseData.ok());
    }


    /**
     * 文章管理的查看详情和编辑页面
     */
    public void editView() {
        String id = getPara("id");
        DmsArticle article = articleService.DAO.findByIdWithoutCache(id);
        log.info(article.toJson());

        setAttr("article", article);

        setAttr("formTitle", "查看用户详情");
        setAttr("mainTP", "/htmls/admin/article/edit.html");
        //调用通用模板
        render("/htmls/admin/pop.html");
    }

    /**
     * 文章管理的编辑菜单请求
     */
    public void edit() {
        String json = HttpKit.readData(getRequest());
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

        articleService.update(article);
        renderJson(ResponseData.ok());

    }

    /**
     * 文章管理的删除请求
     */
    public void delete() {
        String id = getPara("id", "");
        if (StrUtil.isEmpty(id)) {
            renderJson(ResponseData.apiError("文章管理的id不能为空"));
        }
        articleService.deleteById(id);
        renderJson(ResponseData.ok());
    }

    /**
     *  文章管理的多选删除
     */
    public void deletes() {
        //获取所有请求参数
        String json = HttpKit.readData(getRequest());
        TableCheckStatus mTableCheckStatus = JSON.parseObject(json, TableCheckStatus.class);

        for (Object obj : mTableCheckStatus.getData()) {
            DmsArticle article = JSONObject.parseObject(obj.toString(), DmsArticle.class);
            articleService.delete(article);
        }
        renderJson(ResponseData.ok());
    }


}
