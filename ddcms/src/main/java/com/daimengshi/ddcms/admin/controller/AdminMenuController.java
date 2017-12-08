package com.daimengshi.ddcms.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.daimengshi.ddcms.admin.model.DmsMenu;
import com.daimengshi.ddcms.admin.model.DmsMenuType;
import com.daimengshi.ddcms.admin.service.impl.DmsMenuServiceImpl;
import com.daimengshi.ddcms.admin.service.impl.DmsMenuTypeServiceImpl;
import com.daimengshi.ddcms.pub.*;
import com.jfinal.kit.HttpKit;
import com.jfinal.plugin.activerecord.Page;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import com.xiaoleilu.hutool.util.StrUtil;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;
import org.slf4j.event.Level;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zhoufeng on 2017/12/6.
 * 后台主页
 */
@RequestMapping("/admin/menu")
public class AdminMenuController extends JbootController {
    private static final Log log = LogFactory.get();
    @Inject
    private DmsMenuServiceImpl menuService;
    @Inject
    private DmsMenuTypeServiceImpl menuTypeService;

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
        //获取所有请求参数
        Map<String, String[]> reqParas = getParaMap();
        //获取数据表格请求
        TableDataRequest tableDataRequest = getBean(TableDataRequest.class, "");

        TablePage tablePage = pageFind();
        renderJson(tablePage);

    }

    /**
     * 添加菜单
     */
    public void add() {
        List<DmsMenu> menuList = menuService.findAll();
        List<DmsMenuType> menuTypeList = menuTypeService.findAll();

        DmsMenu superMenu = new DmsMenu();
        superMenu.setId("");
        superMenu.setName("顶级");
        superMenu.setSuperId("");
        menuList.add(0, superMenu);

        setAttr("menuList", menuList);
        setAttr("menuTypeList", menuTypeList);


        setAttr("formTitle", "添加菜单");
        setAttr("mainTP", "/htmls/admin/menu/add.html");
        //调用通用模板
        renderTemplate("/htmls/admin/pop.html");
    }

    /**
     * 查看详情
     */
    public void edit() {
        String id = getPara("id");
        DmsMenu menu = menuService.findById(id);

        List<DmsMenu> menuList = menuService.findAll();
        List<DmsMenuType> menuTypeList = menuTypeService.findAll();

        DmsMenu superMenu = new DmsMenu();
        superMenu.setId("");
        superMenu.setName("顶级");
        menuList.add(0, superMenu);
        menuList.remove(menu);

        setAttr("menu", menu);
        setAttr("menuList", menuList);
        setAttr("menuTypeList", menuTypeList);

        setAttr("formTitle", "查看菜单详情");
        setAttr("mainTP", "/htmls/admin/menu/edit.html");
        //调用通用模板
        renderTemplate("/htmls/admin/pop.html");
    }


    /**
     * 添加菜单
     */
    public void addMenu() {
        String json = HttpKit.readData(getRequest());
        log.info(json, Level.INFO);

        DmsMenu menu = JSON.parseObject(json, DmsMenu.class);

        if (StrUtil.isEmpty(menu.getIsOpen())) {
            menu.setIsOpen("off");
        }

        menu.setCreateTime(DateUtil.date());
        menuService.save(menu);
        renderJson(ResponseData.ok());
    }

    /**
     * 编辑菜单
     */
    public void editdMenu() {
        String json = HttpKit.readData(getRequest());
        log.info("\n" + json);
        DmsMenu menu = JSON.parseObject(json, DmsMenu.class);

        if (StrUtil.isEmpty(menu.getIsOpen())) {
            menu.setIsOpen("off");
        }

        menuService.update(menu);

        renderJson(ResponseData.ok());

    }


    /**
     * 删除菜单
     */

    public void deleteMenu() {
        String id = getPara("id", "");

        if (StrUtil.isEmpty(id)) {
            renderJson(ResponseData.apiError("id不能为空"));
        }

        menuService.deleteById(id);
        renderJson(ResponseData.ok());
    }

    /**
     * 多选删除
     */
    public void deleteMenus() {
        //获取所有请求参数
        String json = HttpKit.readData(getRequest());
        TableCheckStatus mTableCheckStatus = JSON.parseObject(json, TableCheckStatus.class);

        for (Object obj : mTableCheckStatus.getData()) {
            DmsMenu menu = JSONObject.parseObject(obj.toString(), DmsMenu.class);
            menuService.delete(menu);
        }

        renderJson(ResponseData.ok());
    }

    /**
     * 分页查询 返回layui 数据表格 格式数据
     */
    private TablePage pageFind() {
        Page<DmsMenu> menusPage;

        //分页
        int size = getParaToInt("size", 10);            //每页返回条数
        int page = getParaToInt("page", 1);             //第几页
        //时间范围
        String dateStartStr = getPara("dateStart", "");  //开始时间
        String dateEndStr = getPara("dateEnd", "");      //结束时间
        //搜素关键字
        String searchKey = getPara("searchKey", "");      //结束时间
        String dateValue = getPara("dateValue", "");      //时间范围值


        if (StrUtil.isNotEmpty(dateValue)) {
            TableDate tableDateStart = JSON.parseObject(dateStartStr, TableDate.class);
            TableDate tableDateEnd = JSON.parseObject(dateEndStr, TableDate.class);
            //转换成Date 对象
            Date startDate = TableDate.toDate(tableDateStart);
            Date endDate = TableDate.toDate(tableDateEnd);
            //格式化
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            dateStartStr = formatter.format(startDate);
            dateEndStr = formatter.format(endDate);
        }

        //如果有日期 则查询日期范围内数据
        if (StrUtil.isEmpty(dateValue)) {
            //当前时间
            dateEndStr = DateUtil.now();
        }

        //分页查询
        menusPage = menuService.DAO.paginate(page, size,
                "select *", "from dms_menu WHERE dms_menu.`name` LIKE ? OR dms_menu.url LIKE ? AND dms_menu.create_time BETWEEN ? AND ? ORDER BY dms_menu.serial_num",
                "%" + searchKey + "%", "%" + searchKey + "%", dateStartStr, dateEndStr);


        //返回转换格式
        return TablePage.ok(menusPage);
    }
}
