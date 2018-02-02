package com.daimengshi.ddcms.service.provider;


import com.daimengshi.ddcms.service.api.DmsArticleService;
import com.daimengshi.ddcms.service.entity.model.DmsArticle;
import com.daimengshi.ddcms.web.base.BaseServiceImpl;
import io.jboot.aop.annotation.Bean;

import javax.inject.Singleton;
import java.util.List;

@Bean
@Singleton
public class DmsArticleServiceImpl extends BaseServiceImpl<DmsArticle> implements DmsArticleService {


    /**
     * 根据url查询文章
     */
    @Override
    public DmsArticle findByUrl(String url) {
        DmsArticle article = getDao().findFirst(getDao().getSql("article.findByUrl"), url);
        return article;
    }

    /**
     * 更改置顶状态
     */
    @Override
    public void  changeTopStatus(String id,String status){
        DmsArticle article = findById(id);
        article.setIsTop(status);
        update(article);
    }
    /**
     * 更改置顶状态
     */
    @Override
    public void  changeQuintessenceStatus(String id,String status){
        DmsArticle article = findById(id);
        article.setIsQuintessence(status);
        update(article);
    }


    /**
     * 获取置顶文章
     * @return
     */
    @Override
    public List<DmsArticle> getTopArticleList(){
        List<DmsArticle> articleList = getDao().find(getDao().getSql("article.getTopArticleList"));
        return articleList;
    }

    @Override
    public List<DmsArticle> findByUId(Object id) {
        List<DmsArticle> articleList = getDao().find(getDao().getSql("article.findByUId"),id);
        return articleList;    }
}