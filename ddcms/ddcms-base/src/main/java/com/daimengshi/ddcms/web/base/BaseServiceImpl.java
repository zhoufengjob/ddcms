package com.daimengshi.ddcms.web.base;

import com.daimengshi.ddcms.rest.datatable.PageTools;
import com.jfinal.plugin.activerecord.Page;
import io.jboot.db.model.JbootModel;
import io.jboot.service.JbootServiceBase;

/**
 * Created by zhoufeng on 2018/1/17.
 *
 */
public class BaseServiceImpl<M extends JbootModel<M>> extends JbootServiceBase<M> {

    /**
     * 分页通用方法
     *
     * @param baseController 控制器
     * @param sqlKey         外部sql对应key
     * @param baseService    接口
     * @return
     */
    public Page<M> findPage(BaseController baseController, String sqlKey, BaseService baseService) {
        return PageTools.pageFind(baseController, sqlKey, baseService);
    }

}
