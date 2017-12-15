package com.daimengshi.ddcms.admin.controller;

import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;

/**
 * Created by zhoufeng on 2017/12/14.
 * 管理员
 */
@RequestMapping("/admin/master")
public class AdminMasterController extends JbootController {
    private static final Log log = LogFactory.get();


    /**
     * 管理员列表
     */
    public void index() {
        setAttr("title", "管理员列表");
        setAttr("mainTP", "/htmls/admin/master/index.html");
        //调用通用模板
        renderTemplate("/htmls/admin/pop.html");
    }


}
