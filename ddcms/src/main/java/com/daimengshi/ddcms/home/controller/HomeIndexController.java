package com.daimengshi.ddcms.home.controller;

import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;

/**
 * Created by zhoufeng on 2017/12/21.
 * 前台首页
 */
@RequestMapping("/")
public class HomeIndexController extends JbootController {
    private static final Log log = LogFactory.get();

    public void index() {
        render("/htmls/home/index/index.html");
    }

}
