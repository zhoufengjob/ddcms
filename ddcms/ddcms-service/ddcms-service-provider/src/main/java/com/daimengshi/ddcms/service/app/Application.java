package com.daimengshi.ddcms.service.app;

import com.daimengshi.ddcms.common.AppInfo;
import com.daimengshi.ddcms.service.api.*;
import com.daimengshi.ddcms.service.provider.*;
import io.jboot.Jboot;
import io.jboot.core.rpc.Jbootrpc;

/**
 * 服务启动入口
 */
public class Application {

    final static int RPC_DEFAULT_PORT = 9011; //远程调用端口
    final static String RPC_DEFAULTGROUP = "ddcms-service";//分组
    final static String RPC_DEFAULTVERSION = "1.0"; //版本

    public static void main(String[] args) {

        Jboot.run(args);

        AppInfo app = Jboot.config(AppInfo.class);

        iniService();

        System.out.println("MotanServer1ZookeeperDemo started...");
    }

    /**
     * 初始化暴露接口
     */
    static void iniService() {
        Jbootrpc factory = Jboot.me().getRpc();
        factory.serviceExport(DmsArticleService.class, new DmsArticleServiceImpl(), RPC_DEFAULTGROUP, RPC_DEFAULTVERSION, RPC_DEFAULT_PORT);
        factory.serviceExport(DmsConfigService.class, new DmsConfigServiceImpl(), RPC_DEFAULTGROUP, RPC_DEFAULTVERSION, RPC_DEFAULT_PORT);
        factory.serviceExport(DmsMenuService.class, new DmsMenuServiceImpl(), RPC_DEFAULTGROUP, RPC_DEFAULTVERSION, RPC_DEFAULT_PORT);
        factory.serviceExport(DmsRoleService.class, new DmsRoleServiceImpl(), RPC_DEFAULTGROUP, RPC_DEFAULTVERSION, RPC_DEFAULT_PORT);
        factory.serviceExport(DmsUserRoleService.class, new DmsUserRoleServiceImpl(), RPC_DEFAULTGROUP, RPC_DEFAULTVERSION, RPC_DEFAULT_PORT);
        factory.serviceExport(DmsUserService.class, new DmsUserServiceImpl(), RPC_DEFAULTGROUP, RPC_DEFAULTVERSION, RPC_DEFAULT_PORT);
        factory.serviceExport(DmsLogService.class, new DmsLogServiceImpl(), RPC_DEFAULTGROUP, RPC_DEFAULTVERSION, RPC_DEFAULT_PORT);
        factory.serviceExport(DmsLinksService.class, new DmsLinksServiceImpl(), RPC_DEFAULTGROUP, RPC_DEFAULTVERSION, RPC_DEFAULT_PORT);
        factory.serviceExport(DmsArticleTypeService.class, new DmsArticleTypeServiceImpl(), RPC_DEFAULTGROUP, RPC_DEFAULTVERSION, RPC_DEFAULT_PORT);

    }

}
