package com.daimengshi.ddcms.admin.support.auth;

import com.daimengshi.ddcms.plugin.shiro.auth.MuitiAuthenticatied;
import com.daimengshi.ddcms.service.api.*;
import com.daimengshi.ddcms.service.entity.model.*;
import com.daimengshi.ddcms.utils.ServiceUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理端认证授权
 *
 * @author Rlax
 */
public class LoginAuth implements MuitiAuthenticatied {

    private DmsUserService dmsUserService;
    private DmsRoleService dmsRoleService;
    private DmsUserRoleService dmsUserRoleService;


    public LoginAuth() {
        this.dmsUserService = ServiceUtils.getService(DmsUserService.class);
        this.dmsRoleService = ServiceUtils.getService(DmsRoleService.class);
        this.dmsUserRoleService = ServiceUtils.getService(DmsUserRoleService.class);
    }

    @Override
    public boolean hasToken(AuthenticationToken authenticationToken) {
        String loginName = authenticationToken.getPrincipal().toString();
        return dmsUserService.hasUser(loginName);
    }

    @Override
    public boolean wasLocked(AuthenticationToken authenticationToken) {
        String loginName = authenticationToken.getPrincipal().toString();

        DmsUser sysUser = dmsUserService.findByName(loginName);
//        return !sysUser.getStatus().equals(UserStatus.USED);
        return false;
    }

    @Override
    public AuthenticationInfo buildAuthenticationInfo(AuthenticationToken authenticationToken) {
        String loginName = authenticationToken.getPrincipal().toString();

        DmsUser sysUser = dmsUserService.findByName(loginName);
        String salt2 = sysUser.getSalt2();
        String pwd = sysUser.getPassword();

        return new SimpleAuthenticationInfo(loginName, pwd, ByteSource.Util.bytes(salt2), "ShiroDbRealm");
    }

    @Override
    public AuthorizationInfo buildAuthorizationInfo(PrincipalCollection principals) {

        // 从 principals获取主身份信息
        // 将getPrimaryPrincipal方法返回值转为真实身份类型（在上边的doGetAuthenticationInfo认证通过填充到SimpleAuthenticationInfo中身份类型），
        String username = (String) principals.getPrimaryPrincipal();                //得到用户名
        DmsUser user = dmsUserService.getUserByAccount(username);


        // 根据身份信息获取权限信息
        // 从数据库获取到权限数据
        // 利用join关联查询提高效率
        DmsUserRole userRole = dmsUserRoleService.findFirstByUID(user.getId());
        dmsRoleService.join(userRole, "rid");
        dmsUserService.join(userRole, "uid");
        DmsRole dmsRole = userRole.get("dmsRole");


        List<String> roleStrList = new ArrayList<>();
        List<String> permissionStrList = new ArrayList<>();

        //授权角色
        roleStrList.add(dmsRole.getFlagKey());
        //授权权限
        permissionStrList.add(dmsRole.getFlagKey());



        // 查到权限数据，返回授权信息(要包括 上边的permissions)
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 将上边查询到授权信息填充到simpleAuthorizationInfo对象中
        simpleAuthorizationInfo.addRoles(roleStrList);
        simpleAuthorizationInfo.addStringPermissions(permissionStrList);
        return simpleAuthorizationInfo;

    }
}
