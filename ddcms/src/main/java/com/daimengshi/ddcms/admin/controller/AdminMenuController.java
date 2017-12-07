package com.daimengshi.ddcms.admin.controller;

import com.daimengshi.ddcms.admin.model.DmsMenu;
import com.daimengshi.ddcms.admin.service.impl.DmsMenuServiceImpl;
import com.daimengshi.ddcms.pub.TablePage;
import com.jfinal.plugin.activerecord.Page;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by zhoufeng on 2017/12/6.
 * 后台主页
 */
@RequestMapping("/admin/menu")
public class AdminMenuController extends JbootController {
    private final static Logger logger = LoggerFactory.getLogger(AdminMenuController.class);

    @Inject
    private DmsMenuServiceImpl menuService;

    /**
     * 菜单管理
     */
    public void index() {
        //获取菜单列表
        List<DmsMenu> menus = menuService.findAll();
        setAttr("menus", menus);
        setAttr("title", "菜单管理");
        setAttr("mainTP", "/htmls/admin/menu/index.html");

        //调用通用模板
        renderTemplate("/htmls/admin/global.html");
    }


    /**
     * 获取菜单列表
     */
    public void list() {

        List<DmsMenu> menus = menuService.findAll();
        Page<DmsMenu> menusPage = menuService.DAO.paginate(1,10,"select *","from dms_menu");
        renderJson(TablePage.ok(menusPage));


//        ResponseData.ok().putDataValue(this, "menus", menusPage);
//        paginate(int pageNumber, int pageSize, String select, String sqlExceptSelect, Object... paras)
//        其中的参数含义分别为：当前页的页号、每页数据条数、sql语句的select部分、sql语句除了select以外的部分、查询参数。绝大多数情况下使用这个API即可。以下是使用示例：

    }
}
