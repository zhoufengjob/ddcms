package com.daimengshi.ddcms.admin.service;

import com.daimengshi.ddcms.admin.model.DmsMenu;

public interface DmsMenuService  {



    /**
     * 根据ID查找model
     *
     * @param id
     * @return
     */
    public DmsMenu findById(Object id);


    /**
     * 根据ID删除model
     *
     * @param id
     * @return
     */
    public boolean deleteById(Object id);

    /**
     * 删除
     *
     * @param model
     * @return
     */
    public boolean delete(DmsMenu model);


    /**
     * 保存到数据库
     *
     * @param model
     * @return
     */
    public boolean save(DmsMenu model);

    /**
     * 保存或更新
     *
     * @param model
     * @return
     */
    public boolean saveOrUpdate(DmsMenu model);

    /**
     * 更新 model
     *
     * @param model
     * @return
     */
    public boolean update(DmsMenu model);
}