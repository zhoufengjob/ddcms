package com.daimengshi.ddcms.admin.service.impl;

import com.daimengshi.ddcms.admin.model.DmsArticle;
import com.daimengshi.ddcms.admin.service.DmsArticleService;
import io.jboot.aop.annotation.Bean;
import io.jboot.service.JbootServiceBase;

import java.util.List;

@Bean
public class DmsArticleServiceImpl extends JbootServiceBase<DmsArticle> implements DmsArticleService {


    /**
     * 根据url查询文章
     */
    public DmsArticle findByUrl(String url) {
        DmsArticle article = getDao().findFirst(getDao().getSql("article.findByUrl"), url);
        return article;
    }

    /**
     * 更改置顶状态
     */
    public void  changeTopStatus(String id,String status){
        DmsArticle article = findById(id);
        article.setIsTop(status);
        update(article);
    }
    /**
     * 更改置顶状态
     */
    public void  changeQuintessenceStatus(String id,String status){
        DmsArticle article = findById(id);
        article.setIsQuintessence(status);
        update(article);
    }


    /**
     * 获取置顶文章
     * @return
     */
    public List<DmsArticle> getTopArticleList(){
        List<DmsArticle> articleList = getDao().find(getDao().getSql("article.getTopArticleList"));
        return articleList;
    }
}