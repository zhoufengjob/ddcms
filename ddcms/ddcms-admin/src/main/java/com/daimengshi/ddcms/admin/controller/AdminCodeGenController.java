package com.daimengshi.ddcms.admin.controller;

import com.alibaba.fastjson.JSON;
import com.daimengshi.ddcms.pub.HtmlView;
import com.daimengshi.ddcms.pub.gen.CodeGenerator;
import com.daimengshi.ddcms.web.base.ResponseData;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;

/**
 * Created by zhoufeng on 2018/1/3.
 * 代码生成
 */
@RequestMapping("/admin/dveTools")
public class AdminCodeGenController extends JbootController {
    private static final Log log = LogFactory.get();

    /**
     * 后台主页
     */
    public void index() {
        HtmlView.adminPop(this,"网站配置","/codeGen/index.html");
    }

    /**
     * 开始代码生成
     */
    public void postCodeGen(){
        String json = getBodyString();
        String className = JSON.parseObject(json).getString("className");
        String chinaName = JSON.parseObject(json).getString("chinaName");
        String tableName = JSON.parseObject(json).getString("tableName");

        try {
            CodeGenerator.me.codeRender(className, tableName,chinaName);
        }catch (Exception e){
            renderJson(ResponseData.apiError("生成错误"));
        }
        renderJson(ResponseData.ok());
    }

}
