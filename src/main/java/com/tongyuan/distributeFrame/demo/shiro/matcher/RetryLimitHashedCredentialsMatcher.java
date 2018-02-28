package com.tongyuan.distributeFrame.demo.shiro.matcher;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhangcy on 2018/2/27
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    private Cache<String,AtomicInteger> passwordRetryCache;

    private static final String namespace = "passwordRetryCache";

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager){
        this.passwordRetryCache = cacheManager.getCache(namespace);
    }


    /*
    自定义一个验证过程：当用户连续输入密码错误5次以上禁止用户登录一段时间
    真正匹配的过程还是交给它的直接父类去完成。
    原子操作考虑到多台设备同时登陆
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info){
        String username = (String) token.getPrincipal();
        AtomicInteger retryCount = passwordRetryCache.get(username);
        if(retryCount == null){
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username,retryCount);
        }
        //用户连续输入密码5此以上则禁止登陆一段时间，具体查看配置redis.expiration
        if(retryCount.incrementAndGet()>5){
            throw new ExcessiveAttemptsException();
        }
        //更新缓存
        passwordRetryCache.put(username,retryCount);

        super.setHashAlgorithmName("MD5");
        boolean match = super.doCredentialsMatch(token,info);
        if(match){
            passwordRetryCache.remove(username);
        }
        return match;
    }

}
