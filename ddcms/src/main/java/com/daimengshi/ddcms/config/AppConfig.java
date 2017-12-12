package com.daimengshi.ddcms.config;

import com.google.inject.Binder;
import com.jfinal.config.Constants;
import com.jfinal.config.Interceptors;
import com.jfinal.config.Routes;
import com.jfinal.template.Engine;
import io.jboot.aop.jfinal.JfinalHandlers;
import io.jboot.aop.jfinal.JfinalPlugins;
import io.jboot.server.ContextListeners;
import io.jboot.server.JbootServer;
import io.jboot.server.Servlets;
import io.jboot.server.listener.JbootAppListenerBase;

/**
 * Created by zhoufeng on 2017/12/12.
 */
public class AppConfig extends JbootAppListenerBase {
    @Override
    public void onJbootDeploy(Servlets servlets, ContextListeners listeners) {
        super.onJbootDeploy(servlets, listeners);
    }

    @Override
    public void onJfinalConstantConfig(Constants constants) {
        super.onJfinalConstantConfig(constants);
    }

    @Override
    public void onJfinalRouteConfig(Routes routes) {
        super.onJfinalRouteConfig(routes);
    }

    @Override
    public void onJfinalEngineConfig(Engine engine) {
        super.onJfinalEngineConfig(engine);
    }

    @Override
    public void onJfinalPluginConfig(JfinalPlugins plugins) {
        super.onJfinalPluginConfig(plugins);
    }

    @Override
    public void onInterceptorConfig(Interceptors interceptors) {
        super.onInterceptorConfig(interceptors);
//        interceptors.addGlobalActionInterceptor(new GlobalInterceptor());
    }

    @Override
    public void onHandlerConfig(JfinalHandlers handlers) {
        super.onHandlerConfig(handlers);
    }

    @Override
    public void onJFinalStarted() {
        super.onJFinalStarted();
    }

    @Override
    public void onJFinalStop() {
        super.onJFinalStop();
    }

    @Override
    public void onJbootStarted() {
        super.onJbootStarted();
    }

    @Override
    public void onAppStartBefore(JbootServer underTowServer) {
        super.onAppStartBefore(underTowServer);
    }

    @Override
    public void onGuiceConfigure(Binder binder) {
        super.onGuiceConfigure(binder);
    }
}