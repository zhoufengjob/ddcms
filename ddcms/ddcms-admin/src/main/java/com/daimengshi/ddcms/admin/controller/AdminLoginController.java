package com.daimengshi.ddcms.admin.controller;

import com.daimengshi.ddcms.admin.validator.LoginValidator;
import com.daimengshi.ddcms.common.Consts;
import com.daimengshi.ddcms.plugin.shiro.MuitiLoginToken;
import com.daimengshi.ddcms.pub.Tools;
import com.daimengshi.ddcms.service.api.DmsUserService;
import com.daimengshi.ddcms.service.entity.model.DmsUser;
import com.daimengshi.ddcms.utils.ServiceUtils;
import com.daimengshi.ddcms.web.base.ResponseData;
import com.jfinal.aop.Before;
import com.jfinal.ext.interceptor.POST;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Created by zhoufeng on 2017/12/6.
 * 登录
 */
@RequestMapping("/admin/login")
public class AdminLoginController extends JbootController {
    private static final Log log = LogFactory.get();

    private DmsUserService userService;

    public AdminLoginController() {
        this.userService = ServiceUtils.getService(DmsUserService.class);
    }

    /**
     * 后台主页
     */
    public void index() {

        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            redirect("/admin");
        } else {
            String adminTheme = Tools.configMap.get("adminTheme").getValue();//获得后端主题
            render("/htmls/admin/" + adminTheme + "/login/index.html");

        }
    }


    /**
     * 验证码
     */
    public void captcha() {
        renderCaptcha();
    }

    /**
     * 登录
     */
    @Before({POST.class, LoginValidator.class})
    public void login() {

        String username = getPara("username");
        String pwd = getPara("password");

        MuitiLoginToken token = new MuitiLoginToken(username, pwd);
        Subject subject = SecurityUtils.getSubject();


        try {
            if (!subject.isAuthenticated()) {
                token.setRememberMe(false);
                subject.login(token);

                DmsUser u = userService.findByName(username);
                subject.getSession(true).setAttribute(Consts.SESSION_USER, u);
            }
            if (getParaToBoolean("rememberMe") != null && getParaToBoolean("rememberMe")) {
                setCookie("username", username, 60 * 60 * 24 * 7);
            } else {
                removeCookie("username");
            }
        } catch (UnknownAccountException une) {
            renderJson(ResponseData.apiError("用户名不存在"));
            return;
        } catch (LockedAccountException lae) {
//            renderJson(ResponseData.apiError("用户被锁定"));
            return;
        } catch (IncorrectCredentialsException ine) {
            renderJson(ResponseData.apiError("用户名或密码不正确"));
            return;
        } catch (ExcessiveAttemptsException exe) {
            renderJson(ResponseData.apiError("账户密码错误次数过多，账户已被限制登录1小时"));
            return;
        } catch (Exception e) {
            e.printStackTrace();
            renderJson(ResponseData.apiError("服务异常，请稍后重试"));
            return;
        }


        renderJson(ResponseData.ok());
    }

    /**
     * 退出
     */
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        redirect(subject.isAuthenticated() ? "/admin" : "/admin/login");

    }


    /**
     * 测试
     */
    public void test() {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();

        if (subject.isPermitted("admin:view")) {
            //有权限
            renderJson(ResponseData.ok().putDataValue("s", "有权限").putDataValue("session", session));
        } else {
            subject.getSession();
            //无权限
            renderJson(ResponseData.ok().putDataValue("s", "无权限").putDataValue("session", session));

        }
    }


//    public String updateAdminUserPassword() {
    // 从shiro的session中取activeUser
//        Subject subject = SecurityUtils.getSubject();
//        // 取身份信息
//        TAdminUser adminUser = (TAdminUser) subject.getPrincipal();
//        // 生成salt,随机生成
//        SecureRandomNumberGenerator secureRandomNumberGenerator = new SecureRandomNumberGenerator();
//        String salt = secureRandomNumberGenerator.nextBytes().toHex();
//        Md5Hash md5 = new Md5Hash(newPassword, salt, 6);
//        String newMd5Password = md5.toHex();
//        // 设置新密码
//        adminUser.setPassword(newMd5Password);
//        // 设置盐
//        adminUser.setSalt(salt);
//        adminUserService.updateAdminUserPassword(adminUser);
//        return newPassword;
//    }

}
