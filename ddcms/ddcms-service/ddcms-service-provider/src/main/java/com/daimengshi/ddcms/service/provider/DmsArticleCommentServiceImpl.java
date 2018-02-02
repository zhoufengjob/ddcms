package com.daimengshi.ddcms.service.provider;

import com.daimengshi.ddcms.service.api.DmsArticleCommentService;
import com.daimengshi.ddcms.service.entity.model.DmsArticleComment;
import com.daimengshi.ddcms.web.base.BaseServiceImpl;
import io.jboot.aop.annotation.Bean;

import javax.inject.Singleton;

@Bean
@Singleton
public class DmsArticleCommentServiceImpl extends BaseServiceImpl<DmsArticleComment> implements DmsArticleCommentService {

}