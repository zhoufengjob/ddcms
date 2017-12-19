package com.daimengshi.ddcms.pub.interceptor;

import com.daimengshi.ddcms.pub.ResponseData;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import io.jboot.Jboot;
import io.jboot.JbootConfig;
import io.jboot.utils.StringUtils;
import io.jboot.web.controller.JbootController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhoufeng on 2017/12/12.
 * 全局拦截器
 */
public class GlobalInterceptor implements Interceptor {
    private static final Log log = LogFactory.get();

    JbootController controller;
    HttpServletRequest request;

    @Override
    public void intercept(Invocation inv) {

        controller = (JbootController) inv.getController();
        request = controller.getRequest();
        boolean successed = false;
        try {
            inv.invoke();
            successed = true;
        } catch (Exception e) {
            //
            doErrLog(inv, e);
            //判断是否ajax请求
            String msg = formatException(e);
            if (controller.isAjaxRequest()) {
                controller.renderJson(ResponseData.apiError(msg));
            } else {
                String redirctUrl = request.getHeader("referer");
                if (StringUtils.isBlank(redirctUrl)) {
                    redirctUrl = request.getRequestURI();
                }
                controller.setAttr("message", msg);
                controller.setAttr("redirctUrl", redirctUrl);
                controller.render("../public/failed.ftl");
            }
        }
    }


    /**
     * 错误日志打印
     *
     * @param inv
     * @param e
     */
    private void doErrLog(Invocation inv, Exception e) {
        //当前时间字符串，格式：yyyy-MM-dd HH:mm:ss
        String now = DateUtil.now();
        StringBuilder sb = new StringBuilder("\n--------Exception Log Begin------------------------------\n");
        sb.append("Time:").append(now + "\n");
        sb.append("Exception Type:").append(e.getClass().getName()).append("\n");
        sb.append("Exception Details:" + e.getMessage()).append("\n");
        sb.append("Referer:" + controller.getReferer()).append("\n");
        sb.append("IPAddress:" + controller.getIPAddress()).append("\n");
        sb.append("Controller:").append(inv.getController().getClass().getName()).append("\n");
        sb.append("Method:").append(inv.getMethodName());
        sb.append("\n--------Exception Log End------------------------------\n");
        log.error(sb.toString(), e);
        e.printStackTrace();
    }


    /**
     * 是否开发模式
     *
     * @return
     */
    private boolean isDev() {
        JbootConfig config = Jboot.config(JbootConfig.class);
        return Jboot.MODE.DEV.getValue().equals(config.getMode());
    }


    /**
     * 格式化异常信息，用于友好响应用户
     *
     * @param e
     * @return
     */
    private static String formatException(Exception e) {
        String message = null;
        Throwable ourCause = e;
        while ((ourCause = e.getCause()) != null) {
            e = (Exception) ourCause;
        }
        String eClassName = e.getClass().getName();
        //一些常见异常提示
        if ("java.lang.NumberFormatException".equals(eClassName)) {
            message = "请输入正确的数字";
            if (StringUtils.isBlank(message)) message = e.toString();
        }

        //获取默认异常提示
        if (StringUtils.isBlank(message)) {
            message = "系统繁忙,请稍后再试";
        }
        //替换特殊字符
        message = message.replaceAll("\"", "'");
        return message;
    }
}
