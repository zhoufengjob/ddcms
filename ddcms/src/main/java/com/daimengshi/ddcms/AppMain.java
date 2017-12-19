package com.daimengshi.ddcms;

import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import io.jboot.Jboot;
import io.jboot.server.JbootServer;
import io.jboot.server.listener.JbootAppListenerBase;

/**
 * Created by zhoufeng on 2017/12/14.
 * 程序启动入口
 */
public class AppMain  extends JbootAppListenerBase{

    private static final Log log = LogFactory.get();

    public static void main(String[] args) {
        Jboot.run(args);
    }

    @Override
    public void onJbootStarted() {
        super.onJbootStarted();
        log.info("程序启动成功!");
    }

    @Override
    public void onAppStartBefore(JbootServer underTowServer) {
        super.onAppStartBefore(underTowServer);
        log.info("程序启动中...");
    }
}
