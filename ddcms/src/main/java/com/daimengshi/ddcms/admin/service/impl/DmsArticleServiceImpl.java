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
}