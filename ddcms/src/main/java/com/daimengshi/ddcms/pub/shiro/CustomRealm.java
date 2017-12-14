package com.daimengshi.ddcms.pub.shiro;

import com.daimengshi.ddcms.admin.model.DmsUser;
import com.daimengshi.ddcms.admin.service.impl.DmsUserServiceImpl;
import com.jfinal.aop.Duang;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * Created by zhoufeng on 2017/12/14.
 */
public class CustomRealm extends AuthorizingRealm {

    final String realmName = "customRealm";

    DmsUserServiceImpl userService  = Duang.duang(DmsUserServiceImpl.class);


    @Override
    public void setName(String name) {
        super.setName(realmName);
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        //仅支持UsernamePasswordToken类型的Token
        return token instanceof UsernamePasswordToken;
    }


    //用于认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        // 第一步从token中取出用户发送过来的身份信息
        String username = (String) token.getPrincipal();  //得到用户名
        String password = new String((char[]) token.getCredentials()); //得到密码

        DmsUser user = userService.getUserByAccount(username);

        //第二步根据用户输入的帐号从数据库查询
        //...

        if(!username.equals(user.getAccount())) {
            throw new UnknownAccountException(); //如果用户名错误
        }
        if(!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException(); //如果密码错误
        }

        //如果查询不到返回null
        //如果查询到，返回认证信息：AuthenticationInfo
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, password, this.getName());

        return simpleAuthenticationInfo;
    }


    //用于授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }
}
