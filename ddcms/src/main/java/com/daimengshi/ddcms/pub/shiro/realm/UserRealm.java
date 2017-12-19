package com.daimengshi.ddcms.pub.shiro.realm;

import com.daimengshi.ddcms.admin.model.DmsPermission;
import com.daimengshi.ddcms.admin.model.DmsRole;
import com.daimengshi.ddcms.admin.model.DmsRolePermission;
import com.daimengshi.ddcms.admin.model.DmsUser;
import com.daimengshi.ddcms.admin.service.impl.DmsUserServiceImpl;
import com.jfinal.aop.Before;
import com.jfinal.aop.Duang;
import com.jfinal.plugin.ehcache.CacheInterceptor;
import com.xiaoleilu.hutool.log.Log;
import com.xiaoleilu.hutool.log.LogFactory;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoufeng on 2017/12/14.
 * 自定义 Shiro 配置
 */
public class UserRealm extends AuthorizingRealm {
    private static final Log log = LogFactory.get();

    private DmsUserServiceImpl userService = Duang.duang(DmsUserServiceImpl.class);


    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持UsernamePasswordToken类型的Token
        return token instanceof UsernamePasswordToken;
    }


    //用于认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        log.info("验证当前Subject时获取到token为:" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
        log.info("getUsername:" + token.getUsername());


        // 第一步从token中取出用户发送过来的身份信息
        String username = (String) token.getPrincipal();  //得到用户名
        String password = new String((char[]) token.getCredentials()); //得到密码

        DmsUser user = userService.getUserByAccount(username);

        //第二步根据用户输入的帐号从数据库查询
        //...

        if (!username.equals(user.getName())) {
            throw new UnknownAccountException(); //如果用户名错误
        }
        if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException(); //如果密码错误
        }

        //如果查询不到返回null
        //如果查询到，返回认证信息：AuthenticationInfo
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, password, this.getName());

//        if (simpleAuthenticationInfo != null) {
//            Jboot.me().getCache().put("AuthenticationInfo_Cache", username, simpleAuthenticationInfo);
//        }


        return simpleAuthenticationInfo;
    }

    @Before(CacheInterceptor.class)
    //用于授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 从 principals获取主身份信息
        // 将getPrimaryPrincipal方法返回值转为真实身份类型（在上边的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中身份类型），
        String username = (String) principals.getPrimaryPrincipal();                //得到用户名
        DmsUser user = userService.getUserByAccount(username);

        // 根据身份信息获取权限信息
        // 从数据库获取到权限数据
        DmsRole role = user.getUserRole();                                      //得到所有角色
        List<DmsPermission> permissions = new ArrayList<>();
        for (DmsRolePermission rolePermission : role.getRolePermissions()) {    //得到所有角色中的角色权限
            permissions.addAll(rolePermission.getUserRolePermissions());        //获取所有角色权限对象
        }

        List<String> roleStrList = new ArrayList<>();
        List<String> permissionStrList = new ArrayList<>();

        //授权角色
        roleStrList.add(role.getKey());
        //授权权限
        for (DmsPermission permission : permissions) {
            permissionStrList.add(permission.getKey());
        }

        // 查到权限数据，返回授权信息(要包括 上边的permissions)
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 将上边查询到授权信息填充到simpleAuthorizationInfo对象中
        simpleAuthorizationInfo.addRoles(roleStrList);
        simpleAuthorizationInfo.addStringPermissions(permissionStrList);

        return simpleAuthorizationInfo;
    }


    /*
     * 清理缓存
     */
    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }


}
