package com.daimengshi.ddcms;

import com.daimengshi.ddcms.common.AppInfo;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import io.jboot.Jboot;
import io.jboot.server.JbootServer;
import io.jboot.server.listener.JbootAppListenerBase;

/**
 * Created by zhoufeng on 2017/12/14.
 * 程序启动入口
 */
public class AppMain extends JbootAppListenerBase {

    private static final Log log = LogFactory.get();

    public static void main(String[] args) {

        AppInfo app = Jboot.config(AppInfo.class);

        //判断是否启动分布式(动态配置)
        if ("1".equals(app.getIsRpc())) {
            iniRpcConfig();
        }

        Jboot.run(args);
    }

    /**
     * 初始化分布式配置
     */
    private static void iniRpcConfig() {
        log.info("启用分布式配置.");
        //#type default motan (support:local,motan,dubbo)
        //#use motan + consul
        Jboot.setBootArg("jboot.rpc.type", "motan");
        Jboot.setBootArg("jboot.rpc.registryType", "consul");
        Jboot.setBootArg("jboot.rpc.registryAddress", "127.0.0.1:8500");

        //#use dubbo + zookeeper
        //Jboot.setBootArg("jboot.rpc.type ", "dubbo");
        //Jboot.setBootArg("jboot.rpc.registryType ", "zookeeper");
        //Jboot.setBootArg("jboot.rpc.registryAddress", "127.0.0.1:2181");

        Jboot.setBootArg("jboot.rpc.requestTimeOut", 5000);
        Jboot.setBootArg("jboot.rpc.callMode", "registry");
        Jboot.setBootArg("jboot.rpc.registryName", "register");
        Jboot.setBootArg("jboot.rpc.registryUserName", "");
        Jboot.setBootArg("jboot.rpc.registryPassword", "");
        //#rpc service config
        Jboot.setBootArg("jboot.rpc.host", "127.0.0.1");
        Jboot.setBootArg("jboot.rpc.defaultPort", "");
        Jboot.setBootArg("jboot.rpc.defaultGroup", "ddcms-service");
        Jboot.setBootArg("jboot.rpc.defaultVersion", "1.0");
        //#rpc hystrix config
        Jboot.setBootArg("jboot.rpc.proxy", "");
        Jboot.setBootArg("jboot.rpc.hystrixEnable", true);
        Jboot.setBootArg("jboot.rpc.hystrixTimeout", 30000);
        Jboot.setBootArg("jboot.rpc.hystrixKeys", "");
        Jboot.setBootArg("jboot.rpc.hystrixAutoConfig", true);
        Jboot.setBootArg("jboot.rpc.hystrixFallbackFactory", "");
        Jboot.setBootArg("jboot.rpc.serialization", "fst");
    }

    @Override
    public void onJbootStarted() {
        super.onJbootStarted();
    }

    @Override
    public void onAppStartBefore(JbootServer underTowServer) {
        super.onAppStartBefore(underTowServer);
    }
}
