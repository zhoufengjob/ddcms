package com.daimengshi.ddcms.service.api;

import com.daimengshi.ddcms.service.entity.model.DmsUser;
import com.daimengshi.ddcms.web.base.BaseService;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

import java.util.List;

public interface DmsUserService extends BaseService {
    /**
     * 使用帐号查询用户
     *
     * @param account
     * @return
     */
    public DmsUser getUserByAccount(String account);


    /**
     * 用户名是否存在
     *
     * @param name
     * @return 存在返回-true，否则返回false
     */
    public boolean hasUser(String name);

    /**
     * 根据用户名查询系统用户信息
     *
     * @param name
     * @return
     */
    public DmsUser findByName(String name);


    public List<DmsUser> findAll();

    /**
     * 根据ID查找model
     *
     * @param id
     * @return
     */
    public DmsUser findById(Object id);


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
    public boolean delete(DmsUser model);


    /**
     * 保存到数据库
     *
     * @param model
     * @return
     */
    public boolean save(DmsUser model);

    /**
     * 保存或更新
     *
     * @param model
     * @return
     */
    public boolean saveOrUpdate(DmsUser model);

    /**
     * 更新 model
     *
     * @param model
     * @return
     */
    public boolean update(DmsUser model);


    public void join(Page<? extends Model> page, String joinOnField);

    public void join(Page<? extends Model> page, String joinOnField, String[] attrs);

    public void join(Page<? extends Model> page, String joinOnField, String joinName);

    public void join(Page<? extends Model> page, String joinOnField, String joinName, String[] attrs);


    public void join(List<? extends Model> models, String joinOnField);

    public void join(List<? extends Model> models, String joinOnField, String[] attrs);

    public void join(List<? extends Model> models, String joinOnField, String joinName);

    public void join(List<? extends Model> models, String joinOnField, String joinName, String[] attrs);

    /**
     * 添加关联数据到某个model中去，避免关联查询，提高性能。
     *
     * @param model       要添加到的model
     * @param joinOnField model对于的关联字段
     */
    public void join(Model model, String joinOnField);

    /**
     * 添加关联数据到某个model中去，避免关联查询，提高性能。
     *
     * @param model
     * @param joinOnField
     * @param attrs
     */
    public void join(Model model, String joinOnField, String[] attrs);


    /**
     * 添加关联数据到某个model中去，避免关联查询，提高性能。
     *
     * @param model
     * @param joinOnField
     * @param joinName
     */
    public void join(Model model, String joinOnField, String joinName);


    /**
     * 添加关联数据到某个model中去，避免关联查询，提高性能。
     *
     * @param model
     * @param joinOnField
     * @param joinName
     * @param attrs
     */
    public void join(Model model, String joinOnField, String joinName, String[] attrs);


    public void keep(Model model, String... attrs);

    public void keep(List<? extends Model> models, String... attrs);
}