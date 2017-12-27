package com.daimengshi.ddcms.pub.shiro.credentials;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

/**
 * Created by zhoufeng on 2017/12/15.
 *
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

//    private Ehcache passwordRetryCache;

    public RetryLimitHashedCredentialsMatcher() {
//        CacheManager cacheManager = CacheManager.create(CacheManager.class.getClassLoader().getResource("password-ehcache.xml"));
//        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }


    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials()); //得到密码
/*
        //retry count + 1
        Element element = passwordRetryCache.get(username);
        if (element == null) {
            element = new Element(username, new AtomicInteger(0));
            passwordRetryCache.put(element);
        }
        AtomicInteger retryCount = (AtomicInteger) element.getObjectValue();
        if (retryCount.incrementAndGet() > 5) {
            //if retry count > 5 throw
            throw new ExcessiveAttemptsException();
        }

//        boolean matches = super.doCredentialsMatch(token, info);
        if (true) {
            //clear retry count
            passwordRetryCache.remove(username);
        }
        */
        return true;
    }
}