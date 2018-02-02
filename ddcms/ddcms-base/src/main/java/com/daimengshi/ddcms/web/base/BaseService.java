package com.daimengshi.ddcms.web.base;

import com.jfinal.plugin.activerecord.Page;
import io.jboot.db.model.JbootModel;

/**
 * Created by zhoufeng on 2018/1/22.
 */
public interface BaseService<M extends JbootModel<M>> {
    public M getDao();

    public Page<M> findPage(BaseController baseController, String sqlKey, BaseService baseService);

}
