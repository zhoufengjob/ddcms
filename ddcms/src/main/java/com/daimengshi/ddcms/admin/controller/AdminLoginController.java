package com.daimengshi.ddcms.admin.controller;

import com.daimengshi.ddcms.admin.interceptor.AdminInterceptor;
import com.daimengshi.ddcms.pub.ResponseData;
import com.jfinal.aop.Before;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

/**
 * Created by zhoufeng on 2017/12/6.
 * 登录
 */
@Before(AdminInterceptor.class) //拦截器,获取菜单和系统配置
@RequestMapping("/admin/login")
public class AdminLoginController extends JbootController {
    private static final Log log = LogFactory.get();


    /**
     * 后台主页
     */
    public void index() {
        //1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
//        //2、得到SecurityManager实例 并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
//        //3、得到Subject及创建用户名/密码身份验证Token（即用户身份/凭证）
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("admin", "admin");

        try {
            //4、登录，即身份验证
            subject.login(token);
            log.info(token.getUsername() + "登录成功");
        } catch (AuthenticationException e) {
            //5、身份验证失败
            log.info(token.getUsername() + "登录失败--" + e);
            renderJson(ResponseData.apiError("登录失败"));
            return;
        }
        //6、退出
//        subject.logout();
        log.info(token.getUsername() + "登出成功");


        renderJson(ResponseData.ok());
    }

    /**
     * 测试
     */
    @RequiresRoles("admin")
    public void logout() {

    }


    /**
     * 测试
     */
    @RequiresUser
    public void test() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isPermitted("user:view")) {
            //有权限
            renderText("有权限");
        } else {
            //无权限
            renderText("无权限");

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
