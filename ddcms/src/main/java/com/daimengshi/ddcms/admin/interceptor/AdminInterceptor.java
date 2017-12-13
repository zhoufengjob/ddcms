package com.daimengshi.ddcms.admin.interceptor;

import com.daimengshi.ddcms.admin.bean.SysInfo;
import com.daimengshi.ddcms.admin.model.DmsConfig;
import com.daimengshi.ddcms.admin.model.DmsMenu;
import com.daimengshi.ddcms.admin.service.impl.DmsConfigServiceImpl;
import com.daimengshi.ddcms.admin.service.impl.DmsMenuServiceImpl;
import com.daimengshi.ddcms.pub.Tools;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhoufeng on 2017/12/12.
 * 后台拦截器
 */
public class AdminInterceptor implements Interceptor {
    private static final Log log = LogFactory.get();

    @Inject
    private DmsConfigServiceImpl configService;

    @Inject
    private DmsMenuServiceImpl menuService;

    @Override
    public void intercept(Invocation inv) {
        //获取站点配置
        List<DmsConfig> dmsConfigs = configService.findAll();
        Map<String, DmsConfig> configMap = new HashMap<>();
        for (DmsConfig config : dmsConfigs) {
            configMap.put(config.getKey(),config);
        }
        inv.getController().setAttr("configMap", configMap);


        //获取本机环境信息
        List<SysInfo> sysInfos = Tools.getSysInfos();
        inv.getController().setAttr("sysInfos", sysInfos);


        //获取菜单列表
        List<DmsMenu> menus = menuService.findAll();
        inv.getController().setAttr("menus", menus);

        inv.invoke();
    }


}
