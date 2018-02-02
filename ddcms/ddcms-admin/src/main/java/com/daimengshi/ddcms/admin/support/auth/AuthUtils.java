package com.daimengshi.ddcms.admin.support.auth;

import com.daimengshi.ddcms.common.Consts;
import com.daimengshi.ddcms.plugin.shiro.ShiroUtils;
import com.daimengshi.ddcms.service.entity.model.DmsUser;
import com.jfinal.kit.StrKit;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * 授权认证工具类
 */
public class AuthUtils {

    /**
     * 是否登录
     * @return
     */
    public static boolean isLogin() {
        return ShiroUtils.isAuthenticated();
    }

    /**
     * 获取平台登录用户
     * @return
     */
    public static DmsUser getLoginUser() {
        DmsUser user = new DmsUser();
        if (ShiroUtils.isAuthenticated()) {
            user = (DmsUser) SecurityUtils.getSubject().getSession().getAttribute(Consts.SESSION_USER);
        }
        return user;
    }

    /**
     * 校验用户登录密码
     * @param newPwd 新未加密的密码
     * @param oldPwd 旧加密后的密码
     * @param oldSalt2 旧加密盐
     * @return true-校验一致 否则 false
     */
    public static boolean checkPwd(String newPwd, String oldPwd, String oldSalt2) {
        return ShiroUtils.checkPwd(newPwd, oldPwd, oldSalt2);
    }


    /**
     * 更新用户盐值
     * @param user
     */
    public static void updataUserSalt2(DmsUser user) {
        if (StrKit.notBlank(user.getPassword())) {
            String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
            SimpleHash hash = new SimpleHash("md5", user.getPassword(), salt2, 2);
            user.setPassword(hash.toHex());
            user.setSalt2(salt2);
        }
    }
}
