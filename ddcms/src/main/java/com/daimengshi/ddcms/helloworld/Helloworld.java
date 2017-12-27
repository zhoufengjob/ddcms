package com.daimengshi.ddcms.helloworld;


import com.daimengshi.ddcms.config.MyConfigModel;
import io.jboot.Jboot;
import io.jboot.event.annotation.EventConfig;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Michael Yang 杨福海 （fuhai999@gmail.com）
 * @version V1.0
 * @Package io.jboot.demo.helloworld
 */
@RequestMapping("/test")
@EventConfig(action = {"event2"})
@Api(description = "这个接口集合的描述", basePath = "/swaggerTest", tags = "abc")
public class Helloworld extends JbootController {

    private final static Logger logger = LoggerFactory.getLogger(Helloworld.class);




    @ApiOperation(value = "用户列表", httpMethod = "GET", notes = "user list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", paramType = "form", dataType = "string", required = true),
            @ApiImplicitParam(name = "k1", value = "k1", paramType = "form", dataType = "string", required = true),
    })
    public void index() {

        //去掉最后一个顿号
        StringBuffer buffer = new StringBuffer();

        String[] strings = {"111", "222", "333", "333", "333", "333", "333"};

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strings.length; i++) {
            sb.append(strings[i]);
            sb.append("、");
        }
        sb.replace(sb.lastIndexOf("、"), sb.length(), "");
        buffer.append(sb.toString());


        logger.info("logback 成功了");
        logger.error("logback 成功了");
        logger.debug("logback 成功了");

        MyConfigModel config = Jboot.config(MyConfigModel.class);


//        Jboot.me().getCache().put("ceshi", "test", "你好");


        Jboot.sendEvent("event1", "推送一个钩子");


//        renderText(Jboot.me().getCache().get("ceshi", "test").toString());

        setAttr("title", "你好");
        setAttr("test", "测试");
        setAttr("config", config);
        render("/htmls/test.html");


    }

}
