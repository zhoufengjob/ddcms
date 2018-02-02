package com.daimengshi.ddcms.rest.datatable;

import com.alibaba.fastjson.JSON;
import com.daimengshi.ddcms.common.Constant;
import com.daimengshi.ddcms.web.base.BaseService;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.SqlPara;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import io.jboot.db.model.JbootModel;
import io.jboot.web.controller.JbootController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhoufeng on 2017/12/8.
 * 静态工具类
 */
public class PageTools implements Constant {

    /**
     * 分页查询  数据表格 格式数据
     */
    public static <M extends JbootModel> Page<M> pageFind(JbootController controller, String sqlKey, BaseService mBaseService) {
        Page menusPage;
        //分页
        int pageSize = controller.getParaToInt("pageSize", 20);            //每页返回条数
        int pageNumber = controller.getParaToInt("pageNumber", 0);             //第几页
        String sortName = controller.getPara("sortName");             //排序字段
        String sortOrder = controller.getPara("sortOrder");             //排序升降类型
        //时间范围
        String dateStartStr = controller.getPara("dateStart", "");  //开始时间
        String dateEndStr = controller.getPara("dateEnd", "");      //结束时间
        //搜素关键字
        String searchKey = controller.getPara("searchText", "");      //搜索关键字
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
                .set("dateEndStr", dateEndStr)
                .set("sortName", sortName)
                .set("sortOrder", sortOrder);

        SqlPara sqlPara = mBaseService.getDao().getSqlPara(sqlKey, paras);
        //分页查询

        if(mBaseService != null){
            menusPage = mBaseService.getDao().paginate(pageNumber, pageSize, sqlPara);
        }else {
            menusPage = Db.paginate(pageNumber, pageSize, sqlPara);
        }

        //返回转换格式
        return menusPage;
    }

}
