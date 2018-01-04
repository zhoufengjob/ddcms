package com.daimengshi.ddcms.pub;

import com.alibaba.fastjson.JSON;
import com.daimengshi.ddcms.admin.bean.SysInfo;
import com.daimengshi.ddcms.admin.model.DmsConfig;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import io.jboot.web.controller.JbootController;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zhoufeng on 2017/12/8.
 * 静态工具类
 */
public class Tools implements AppConstant {

    public static Map<String, DmsConfig> configMap;

    private static List<SysInfo> sysInfos;

    /**
     * 获取系统信息
     *
     * @return
     */
    public static List<SysInfo> getSysInfos() {
        if (sysInfos == null) {
            sysInfos = new ArrayList<>();
            Properties sysProperty = System.getProperties(); //系统属性
            sysInfos.add(new SysInfo("操作系统的名称：", sysProperty.getProperty("os.name")));
            sysInfos.add(new SysInfo("操作系统的构架：", sysProperty.getProperty("os.arch")));
            sysInfos.add(new SysInfo("操作系统的版本：", sysProperty.getProperty("os.version")));
            sysInfos.add(new SysInfo("用户的账户名称：", sysProperty.getProperty("user.name")));
            sysInfos.add(new SysInfo("用户的主目录：", sysProperty.getProperty("user.home")));
            sysInfos.add(new SysInfo("用户的当前工作目录：", sysProperty.getProperty("user.dir")));
            sysInfos.add(new SysInfo("Java的运行环境版本：", sysProperty.getProperty("java.version")));
            sysInfos.add(new SysInfo("Java的运行环境供应商：", sysProperty.getProperty("java.vendor")));
            sysInfos.add(new SysInfo("Java供应商的URL：", sysProperty.getProperty("java.vendor.url")));
            sysInfos.add(new SysInfo("Java的安装路径：", sysProperty.getProperty("java.home")));

        }
        return sysInfos;
    }

    /**
     * 分页查询 返回layui 数据表格 格式数据
     */
    public static TablePage pageFind(JbootController controller, String sqlKey) {
        Page<Record> menusPage;

        //分页
        int size = controller.getParaToInt("size", 10);            //每页返回条数
        int page = controller.getParaToInt("page", 1);             //第几页
        //时间范围
        String dateStartStr = controller.getPara("dateStart", "");  //开始时间
        String dateEndStr = controller.getPara("dateEnd", "");      //结束时间
        //搜素关键字
        String searchKey = controller.getPara("searchKey", "");      //结束时间
        String dateValue = controller.getPara("dateValue", "");      //时间范围值


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

        Kv paras = Kv.by("searchKey", "%" + searchKey + "%")
                .set("dateStartStr", dateStartStr)
                .set("dateEndStr", dateEndStr);

        SqlPara sqlPara = Db.getSqlPara(sqlKey, paras);

        //分页查询
        menusPage = Db.paginate(page, size, sqlPara);

        //返回转换格式
        return TablePage.ok(menusPage);
    }

}
