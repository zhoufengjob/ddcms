package com.daimengshi.ddcms.pub;

import io.jboot.web.controller.JbootController;

/**
 * Created by zhoufeng on 2018/1/4.
 * Html视图渲染
 */
public class HtmlView implements AppConstant {


    /**
     * 以窗口形式渲染 渲染到后台html
     *
     * @param jbootController 控制器
     * @param title           窗口标题
     * @param layoutPath      动态布局路径
     */
    public static void adminPop(JbootController jbootController, String title, String layoutPath) {
        String adminTheme = Tools.configMap.get("adminTheme").getValue();//获得后端主题
        jbootController.setAttr("title", title);
        jbootController.setAttr("mainTP", ADMIN_HTML_PATH + "/" + adminTheme + "/" + layoutPath);
        adminRender(jbootController, HTML_POP);
    }

    /**
     * 渲染到后台html
     */
    public static void adminRender(JbootController mJbootController, String path) {
        String adminTheme = Tools.configMap.get("adminTheme").getValue();//获得后端主题
        mJbootController.render(ADMIN_HTML_PATH + "/" + adminTheme + "/" + path);
    }

    /**
     * 渲染到前台html
     */
    public static void homeRender(JbootController mJbootController, String path) {
        String homeTheme = Tools.configMap.get("homeTheme").getValue();//获得前端主题
        mJbootController.render(ADMIN_HTML_PATH + "/" + homeTheme + "/" + path);
    }
}
