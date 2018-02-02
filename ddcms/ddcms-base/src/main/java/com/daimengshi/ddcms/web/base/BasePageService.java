package com.daimengshi.ddcms.web.base;

import com.daimengshi.ddcms.rest.datatable.TableDataRequest;
import com.daimengshi.ddcms.rest.datatable.TablePage;

/**
 * Created by zhoufeng on 2018/1/17.
 * 通用分页实现
 */
public interface BasePageService {
    /**
     * 分页查询数据字典信息
     * 直接传参数
     *
     * @param pageNumber   每页返回条数
     * @param pageSize     第几页
     * @param dateStartStr 开始时间
     * @param dateEndStr   结束时间
     * @param searchKey    搜索关键字
     * @param dateValue    时间范围数值
     * @return
     */
    public TablePage findPage(
            String sqlKey,
            int pageNumber,
            int pageSize,
            String dateStartStr,
            String dateEndStr,
            String searchKey,
            String dateValue
    );

    /**
     * 直接传递分页对象
     * @param sqlKey
     * @param tableDataRequest
     * @return
     */
    public TablePage findPage(
            String sqlKey,
            TableDataRequest tableDataRequest
    );
}
