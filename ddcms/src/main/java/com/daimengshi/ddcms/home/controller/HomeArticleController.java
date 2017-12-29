package com.daimengshi.ddcms.home.controller;

import com.alibaba.fastjson.JSON;
import com.daimengshi.ddcms.admin.model.DmsArticle;
import com.daimengshi.ddcms.admin.service.impl.DmsArticleServiceImpl;
import com.daimengshi.ddcms.pub.ResponseData;
import com.jfinal.aop.Before;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.plugin.activerecord.Page;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import com.xiaoleilu.hutool.util.StrUtil;
import io.jboot.web.cache.EnableActionCache;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;
import org.apache.shiro.authz.annotation.RequiresRoles;

import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

/**
 * Created by zhoufeng on 2017/12/21.
 * 文章控制器
 */
@RequestMapping("/article")
@Before(SessionInViewInterceptor.class)
public class HomeArticleController extends JbootController {
    private static final Log log = LogFactory.get();


    @Inject
    DmsArticleServiceImpl articleService;

    /**
     * 文章列表
     */
    @EnableActionCache(group = "article_index", liveSeconds = 5)//jboot.me.getCache.removeAll 通过这个方法清除缓存 可以传入group指定
    public void index() {

        int pageIndex = getParaToInt(0, 1);
        int limit = 5;//每页条数

        //获得所有不置顶文章分页
        Page<DmsArticle> articlePage = articleService.DAO.paginate(
                pageIndex,
                limit,
                "select *",
                "FROM  dms_article WHERE dms_article.is_top = 'off' GROUP BY dms_article.create_time desc");

        List<DmsArticle> topArticleList = articleService.getTopArticleList();//获取所有置顶文章


        setAttr("topArticleList", topArticleList);//所有置顶文章
        setAttr("articlePage", articlePage);//文章分页
        setAttr("limit", limit);//每页显示条数
        setAttr("pageIndex", pageIndex);//当前页数

        setAttr("leftTP", "/htmls/home/default/_left.html"); //左边动态内容模板
        setAttr("rightTP", "/htmls/home/default/_right.html"); //右边动态内容模板

        render("/htmls/home/lr_global.html");   //加载左右模式模板

    }

    /**
     * 详情展示
     */
    public void detail() {
        String url = getPara(0);
        String id = getPara(1);

        String decode;
        try {
            decode = URLDecoder.decode(url, "UTF-8");// GBK解码
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return;
        }


        DmsArticle article;

        //获取文章
        if (id != null) {
            article = articleService.findById(id);
        }else {
            article = articleService.findByUrl(decode);
        }

        int pv = article.getPv() + 1;
        article.setPv(pv);
        articleService.update(article);

        setAttr("article", article);

        setAttr("leftTP", "/htmls/home/article/left.html"); //左边内容模板
        setAttr("rightTP", "/htmls/home/default/_right.html"); //右边内容模板

        render("/htmls/home/lr_global.html");

    }

    /**
     * 用户文章编辑页面
     */
    public void editView() {
        String url = getPara(0);

        //获取文章
        DmsArticle article = articleService.findByUrl(url);
        setAttr("article", article);
        setAttr("isOpenTopNav2", "off"); //关闭二级导航,用于手机适配

        setAttr("mainTP", "/htmls/home/article/edit.html"); //中间内容模板
        render("/htmls/home/lr_global.html");

    }


    /**
     * 用户发布文章页面
     */
    public void addView() {

        setAttr("isOpenTopNav2", "off"); //关闭二级导航,用于手机适配
        setAttr("mainTP", "/htmls/home/article/add.html"); //中间内容模板
        render("/htmls/home/lr_global.html");

    }

    /**
     * 提交编辑数据
     */
    public void add() {
        String json = getBodyString();
        log.info(json);
        DmsArticle article = JSON.parseObject(json, DmsArticle.class);
        article.setUid(getSessionAttr("uid"));
        article.setCreateTime(new Date());
        articleService.save(article);
        renderJson(ResponseData.ok());
    }


    /**
     * 提交编辑数据
     */
    public void edit() {
        String json = getBodyString();
        log.info(json);
        DmsArticle article = JSON.parseObject(json, DmsArticle.class);
        //这里做控制 只修改文章部分(否则修改json可以直接改其他属性)
        DmsArticle articleOld = articleService.findById(article.getId());
        articleOld.setTitle(article.getTitle());
        articleOld.setContent(article.getContent());
        articleOld.setCreateTime(new Date());
        articleService.update(articleOld);
        renderJson(ResponseData.ok());
    }

    /**
     * 删除
     */
    @RequiresRoles("admin")//只有拥有管理员角色才能调用
    public void delete() {
        String id = getPara("id", "");
        if (StrUtil.isEmpty(id)) {
            renderJson(ResponseData.apiError("文章管理的id不能为空"));
            return;
        }
        articleService.deleteById(id);
        renderJson(ResponseData.ok());
    }

    /**
     * 置顶
     */
    @RequiresRoles("admin")//只有拥有管理员角色才能调用
    public void setTop() {
        String id = getPara("id", "");
        if (StrUtil.isEmpty(id)) {
            renderJson(ResponseData.apiError("文章管理的id不能为空"));
            return;
        }

        articleService.changeTopStatus(id, "on");
        renderJson(ResponseData.ok());
    }

    /**
     * 加精
     */
    @RequiresRoles("admin")//只有拥有管理员角色才能调用
    public void setQuintessence() {
        String id = getPara("id", "");
        if (StrUtil.isEmpty(id)) {
            renderJson(ResponseData.apiError("文章管理的id不能为空"));
            return;
        }

        articleService.changeQuintessenceStatus(id, "on");
        renderJson(ResponseData.ok());
    }

    /**
     * 取消置顶
     */
    @RequiresRoles("admin")//只有拥有管理员角色才能调用
    public void cancelTop() {
        String id = getPara("id", "");
        if (StrUtil.isEmpty(id)) {
            renderJson(ResponseData.apiError("文章管理的id不能为空"));
            return;
        }

        articleService.changeTopStatus(id, "off");
        renderJson(ResponseData.ok());
    }

    /**
     * 取消加精
     */
    @RequiresRoles("admin")//只有拥有管理员角色才能调用
    public void cancelQuintessence() {
        String id = getPara("id", "");
        if (StrUtil.isEmpty(id)) {
            renderJson(ResponseData.apiError("文章管理的id不能为空"));
            return;
        }

        articleService.changeQuintessenceStatus(id, "off");
        renderJson(ResponseData.ok());
    }


}
