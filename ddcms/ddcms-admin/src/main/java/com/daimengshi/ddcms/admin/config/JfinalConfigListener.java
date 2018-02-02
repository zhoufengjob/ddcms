package com.daimengshi.ddcms.admin.config;

import com.daimengshi.ddcms.admin.interceptor.AdminInterceptor;
import com.daimengshi.ddcms.admin.support.log.LogInterceptor;
import com.daimengshi.ddcms.captcha.CaptchaCache;
import com.daimengshi.ddcms.common.AppInfo;
import com.daimengshi.ddcms.interceptor.BusinessExceptionInterceptor;
import com.daimengshi.ddcms.interceptor.NotNullParaInterceptor;
import com.daimengshi.ddcms.web.render.AppRenderFactory;
import com.google.inject.Binder;
import com.jfinal.captcha.CaptchaManager;
import com.jfinal.config.Constants;
import com.jfinal.config.Interceptors;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.json.JFinalJsonFactory;
import com.jfinal.template.Engine;
import io.jboot.Jboot;
import io.jboot.aop.jfinal.JfinalHandlers;
import io.jboot.aop.jfinal.JfinalPlugins;
import io.jboot.server.ContextListeners;
import io.jboot.server.JbootServer;
import io.jboot.server.Servlets;
import io.jboot.server.listener.JbootAppListenerBase;

/**
 * jfinal config
 *
 */
public class JfinalConfigListener extends JbootAppListenerBase {
    @Override
    public void onJfinalConstantConfig(Constants constants) {
        AppInfo app = Jboot.config(AppInfo.class);
        constants.setError401View("/htmls/401.html");
        constants.setError403View("/htmls/403.html");
        constants.setError404View("/htmls/404.html");
        constants.setError500View("/htmls/500.html");
        constants.setBaseUploadPath(app.getBaseUploadPath());
        constants.setRenderFactory(new AppRenderFactory());
        constants.setJsonFactory(new JFinalJsonFactory());
    }

    @Override
    public void onJfinalRouteConfig(Routes routes) {
        routes.setBaseViewPath("/htmls");
    }

    @Override
    public void onJfinalEngineConfig(Engine engine) {
        engine.setDevMode(true);
        AppInfo app = Jboot.config(AppInfo.class);
        engine.addSharedObject("APP", app);
        engine.addSharedObject("RESOURCE_HOST", app.getResourceHost());
    }

    @Override
    public void onInterceptorConfig(Interceptors interceptors) {
        interceptors.add(new AdminInterceptor());
        interceptors.add(new LogInterceptor());
//        interceptors.add(new AuthInterceptor());//还未实现
        interceptors.add(new NotNullParaInterceptor("/htmls/exception.html"));
        interceptors.add(new BusinessExceptionInterceptor("/htmls/exception.html"));
    }

    @Override
    public void onJfinalPluginConfig(JfinalPlugins plugins) {

    }

    @Override
    public void onHandlerConfig(JfinalHandlers handlers) {
        handlers.add(new ContextPathHandler("ctxPath"));
    }

    @Override
    public void onJFinalStarted() {
    }

    @Override
    public void onJFinalStop() {
    }

    @Override
    public void onJbootStarted() {
        /** 集群模式下验证码使用 redis 缓存 */
        CaptchaManager.me().setCaptchaCache(new CaptchaCache());
    }

    @Override
    public void onAppStartBefore(JbootServer jbootServer) {

    }

    @Override
    public void onJbootDeploy(Servlets servlets, ContextListeners listeners) {

    }

    @Override
    public void onGuiceConfigure(Binder binder) {

    }
}
