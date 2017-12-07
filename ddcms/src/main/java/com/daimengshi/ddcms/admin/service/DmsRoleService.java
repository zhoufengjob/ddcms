package com.daimengshi.ddcms.admin.service;

import com.daimengshi.ddcms.admin.model.DmsRole;

public interface DmsRoleService  {



    /**
     * 根据ID查找model
     *
     * @param id
     * @return
     */
    public DmsRole findById(Object id);


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
    public boolean delete(DmsRole model);


    /**
     * 保存到数据库
     *
     * @param model
     * @return
     */
    public boolean save(DmsRole model);

    /**
     * 保存或更新
     *
     * @param model
     * @return
     */
    public boolean saveOrUpdate(DmsRole model);

    /**
     * 更新 model
     *
     * @param model
     * @return
     */
    public boolean update(DmsRole model);
}