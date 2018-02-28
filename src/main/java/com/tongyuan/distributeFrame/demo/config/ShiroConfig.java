package com.tongyuan.distributeFrame.demo.config;


import com.tongyuan.distributeFrame.demo.shiro.filter.OnlineLimitFilter;
import com.tongyuan.distributeFrame.demo.shiro.listener.CustomSessionListener;
import com.tongyuan.distributeFrame.cache.shiro.RedisCacheManager;
import com.tongyuan.distributeFrame.demo.shiro.matcher.RetryLimitHashedCredentialsMatcher;
import com.tongyuan.distributeFrame.demo.shiro.realm.UserRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangcy on 2018/2/27
 */
@Configuration
public class ShiroConfig {
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(userRealm());
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(cacheManager());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        //注入记住我管理器;
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    @Bean
    public CacheManager cacheManager(){
        CacheManager cacheManager = new RedisCacheManager();
        return cacheManager;
    }

    @Bean
    public UserRealm userRealm(){
        UserRealm userRealm = new UserRealm();
        //先注释掉自定义的matcher
        //userRealm.setCredentialsMatcher(credentialsMatcher());
        return userRealm;
    }


    @Bean
    public SessionManager sessionManager(){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        List<SessionListener> sessionListeners = new ArrayList<SessionListener>();
        sessionListeners.add(new CustomSessionListener());
        sessionManager.setSessionListeners(sessionListeners);
        //相隔多久检查一次session的有效性
        sessionManager.setSessionValidationInterval(1800000);
        //session有效时间为半个小时（毫秒为单位）
        sessionManager.setGlobalSessionTimeout(1800000);
        return sessionManager;
    }

    /*
    注入自定义凭证匹配器
     */
    @Bean
    public CredentialsMatcher credentialsMatcher(){
        CacheManager cacheManager = cacheManager();
        RetryLimitHashedCredentialsMatcher matcher = new RetryLimitHashedCredentialsMatcher(cacheManager);
        return matcher;
    }

    /**
     * cookie对象;
     * @return
     */
    public SimpleCookie rememberMeCookie(){
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(2592000);
        return simpleCookie;
    }
    /**
     * cookie管理对象;记住我功能
     * @return
     */
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }


    /**
     * 限制同一账号登录同时登录人数控制
     */
    public OnlineLimitFilter onlineLimitFilter(){
        OnlineLimitFilter filter = new OnlineLimitFilter(cacheManager());
        filter.setKickoutAfter(false);
        filter.setMaxSession(1);
        return filter;
    }
    /**
     * ShiroFilterFactoryBean 处理拦截资源文件问题。
     * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
     * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
     *
     * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
     * 3、部分过滤器可指定参数，如perms，roles
     *
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager());

        //自定义拦截器
        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
        //限制同一帐号同时在线的个数。
        filtersMap.put("kickout", onlineLimitFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);

        return shiroFilterFactoryBean;
    }
}
