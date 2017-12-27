package com.daimengshi.ddcms.admin.service.impl;

import io.jboot.aop.annotation.Bean;
import com.daimengshi.ddcms.admin.service.DmsArticleService;
import com.daimengshi.ddcms.admin.model.DmsArticle;
import io.jboot.service.JbootServiceBase;

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
}