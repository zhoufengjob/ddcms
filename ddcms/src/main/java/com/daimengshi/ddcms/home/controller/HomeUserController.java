package com.daimengshi.ddcms.home.controller;

import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;

/**
 * Created by zhoufeng on 2017/12/21.
 * 用户中心
 * 正在实现请关注ddcms
 */
@RequestMapping("/user")
public class HomeUserController extends JbootController {
    private static final Log log = LogFactory.get();

    /**
     * 用户中心
     */
    public void index() {

        setAttr("isOpenTopNav2", "off"); //关闭二级导航,用于手机适配
        setAttr("mainTP", "/htmls/home/user/index.html"); //中间内容模板
        render("/htmls/home/lr_global.html");
    }


    /**
     * 我的基本信息设置
     */
    public void config() {

        setAttr("isOpenTopNav2", "off"); //关闭二级导航,用于手机适配
        setAttr("mainTP", "/htmls/home/user/config.html"); //中间内容模板
        render("/htmls/home/lr_global.html");
    }

    /**
     * 我的消息页面
     */
    public void message() {

        setAttr("isOpenTopNav2", "off"); //关闭二级导航,用于手机适配
        setAttr("mainTP", "/htmls/home/user/message.html"); //中间内容模板
        render("/htmls/home/lr_global.html");
    }

    /**
     * 我的主页
     */
    public void home() {

        setAttr("isOpenTopNav2", "off"); //关闭二级导航,用于手机适配
        setAttr("mainTP", "/htmls/home/user/home.html"); //中间内容模板
        render("/htmls/home/lr_global.html");
    }

}
