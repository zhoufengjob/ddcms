package com.daimengshi.ddcms.web.base;

import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import io.jboot.web.controller.JbootController;

/**
 * 控制器基类
 * 这里定义了模板设计模式
 *
 * @author Rlax
 */
public abstract class BaseController extends JbootController {

    protected static final Log log = LogFactory.get();


    /**
     * 默认页(查询列表页面)
     */
    public abstract void index();

    /**
     * 添加界面
     */
    public abstract void addView();

    /**
     * 编辑界面
     */
    public abstract void editView();

    /**
     * 获取列表(用于ajax异步请求)
     */
    public abstract void getList();

    /**
     * 添加(用于ajax异步请求)
     */
    public abstract void postAdd();

    /**
     * 编辑(用于ajax异步请求)
     */
    public abstract void postEdit();

    /**
     * 删除一个(用于ajax异步请求)
     */
    public abstract void postDelete();

    /**
     * 删除多个(用于ajax异步请求)
     */
    public abstract void postDeletes();


}
